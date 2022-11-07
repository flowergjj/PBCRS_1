package com.hkbank.pbcrs.service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.hkbank.pbcrs.mapper.PbcrsReportAcctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctbssgmtMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hkbank.pbcrs.mapper.PbcrsSysConTrollerMapper;

@Service
public class PbcrsSysConTrollerService {

	private static final Logger log = LogManager.getLogger(PbcrsSysConTrollerService.class);
	@Autowired
	private PbcrsSysConTrollerMapper sysConTrollerMapper;

	// 个人借贷基础段
	@Autowired
	private PbcrsReportAcctbssgmtMapper AcctbssgmtMapper;

	//企业借贷基础段
	@Autowired
	PbcrsReportEnacctbssgmtMapper EnAcctBsSgmtMapper;

	@Autowired
	private ReportCmdService service;
	
	
	public List<Map<String, Object>> findSysConByType(String type) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("type", type);
		param.put("parent_id","0");
		List<Map<String, Object>> result = sysConTrollerMapper.findSysConByType(param);
		if(result != null && result.size()>0) {
			for (Map<String, Object> map : result) {
				findChildSysConByType(param,map);
			}
		}
		
		return result;
	}
	/**
	 * 循环查找调度层级
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> findChildSysConByType(Map<String,Object>  param,Map<String, Object> result) {
		param.put("parent_id",result.get("ID"));
		List<Map<String, Object>> list = sysConTrollerMapper.findSysConByType(param);
		if(list != null && list.size()>0) {
			result.put("state", "closed");
			result.put("children", list);
			for (Map<String, Object> map : list) {
				findChildSysConByType(param,map);
			}
		}
		return null;
	}
	
	public Map<String,Object> deal(Map<String,Object> param) {
		Map<String,Object> map = new HashMap<String, Object>();
		String msgInfo ="" ;
		String web_proc_id = UUID.randomUUID().toString();
		Map<String,Object> con_info_params = new HashMap<String, Object>();
		con_info_params.put("conId", param.get("CON_ID"));
		con_info_params.put("insertUserName", param.get("insertUserName"));
		con_info_params.put("insertUserNo", param.get("insertUserNo"));
		con_info_params.put("webProcId", web_proc_id);
		con_info_params.put("state", "0");
		con_info_params.put("etlDate", param.get("ETL_DATE").toString());
		try {
			sysConTrollerMapper.insertConInfo(con_info_params);
			String proc_name_list = (String) param.get("PROC_NAME");
			//如果有可以执行到存储过程则执行存储过程
			if(proc_name_list != null && proc_name_list.length() > 0 && !proc_name_list.equals("undefined")) {
				String[] proc_name_arr = proc_name_list.split(",");
				
				for (String proc_name : proc_name_arr) {
					//proc_name = "call " + proc_name +"("+param.get("ETL_DATE").toString()+")";
					proc_name = MessageFormat.format("call {0}(''{1}'',''{2}'')",proc_name,param.get("ETL_DATE").toString(),web_proc_id);
					log.info("调用存储过程{},参数为{},{}",proc_name,param.get("ETL_DATE").toString(),web_proc_id);
					execProc(proc_name);
				}
				
			}
			
			String report_code_list = (String) param.get("REPORT_CODE");
			//如果执行的报文是个人借贷信息或者企业借贷信息的话需要进行不良贷款确认以后才能进行报文生成报送
			if (report_code_list.contains("210") || report_code_list.contains("410")) {
				//个人借贷不良贷款未确认查询
				int inCount = AcctbssgmtMapper.selectBadCount(param);
				int enCount = EnAcctBsSgmtMapper.selectBadCount(param);
				if(inCount>0 || enCount>0){
					map.put("RET_CODE", "ERROR");
					map.put("msg","有未确认的不良贷款信息,无法生成借贷报文");
					return map;
				}
			}
			//如果有可以执行到报文生成，则生成对应到报文
			if(report_code_list != null && report_code_list.length() > 0  && !report_code_list.equals("undefined")) {
				String[] report_code_arr = report_code_list.split(",");
				for (String report_code : report_code_arr) {
					//ETLDATE=20211109,SGMTID=110
					Map<String,String> report_params = new HashMap<String, String>();
					report_params.put("ETLDATE", param.get("ETL_DATE").toString());
					report_params.put("SGMTID", report_code);
					report_params.put("webProcId", web_proc_id);
					report_params.put("reportFlag",param.get("reportFlag").toString());
					report_params.put("SYSID",param.get("SYSID").toString());
					report_params.put("ISBATCH",param.get("ISBATCH").toString());

					List<String> conditions = null;
					try {
						conditions = service.genFile(report_params);
					} catch (NullPointerException e) {
						msgInfo +=e.getMessage()+"、";
					}
					if (conditions != null && conditions.size()>0) {
						for (String condition : conditions) {
							try {
								service.socket(condition+"REPORT_SOURCE_P");
							} catch (Throwable e) {
								e.printStackTrace();
								log.error("调度执行失败！",e);
								throw new Exception("连接错误");
							}
						}
					}
				}
				
			}
			con_info_params.put("state", "1");
			sysConTrollerMapper.updSysConInfo(con_info_params);
			map.put("RET_CODE", "SUCCESS");
			if(msgInfo != null && !"".equals(msgInfo)){
				map.put("msg","部分报文未查询到报送数据:"+msgInfo.substring(0,msgInfo.length()-1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调度执行失败！",e);
			con_info_params.put("state", "2");
			sysConTrollerMapper.updSysConInfo(con_info_params);
			map.put("RET_CODE", "ERROR");
			if(e.getMessage().equals("连接错误")) {
				map.put("msg","【报文生成程序未启动！】");
			}else {
				map.put("msg","【调度执行失败！】");
			}
			
		}
		return map;
	}
	
	public void execProc(String param){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("procString", param);
		sysConTrollerMapper.execProc(params);
	}


	public Map<String, Object> getEtlLogInfo(Map<String, Object> params) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		params = getStartAndEnd(params);
		// 查询总数
		int total = sysConTrollerMapper.getEtlLogInfoCount(params);
		// 判断total有没有值，或者total小于要查询开始的index
		if (total <= 0 || total < MapUtils.getInteger(params, "startindex")) {
			list = new ArrayList();
		} else {
			// 查询列表
			list = sysConTrollerMapper.getEtlLogInfo(params);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;
		
	}
	
	public Map<String, Object> findLogDetail(Map<String, Object> params) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> log_list = sysConTrollerMapper.findLogDetail(params.get("webProcId").toString());
		resultMap.put("DATA", log_list);
		resultMap.put("RET_CODE", "SUCCESS");
		return resultMap;
		
	}
	
	// 分页参数封装
		public Map<String, Object> getStartAndEnd(Map<String, Object> param) {
			int pageNo = MapUtils.getIntValue(param, "page");
			int pageSize = MapUtils.getIntValue(param, "rows");
			int skip = (pageNo - 1) * pageSize + 1;
			int endindex = skip + pageSize;
			param.put("endindex", endindex);
			param.put("startindex", skip);
			return param;
		}
	
}
