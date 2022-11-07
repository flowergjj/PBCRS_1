package com.hkbank.pbcrs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hkbank.pbcrs.mapper.PbcrsReportConditionMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.mapper.PbcrsReportSendfileMapper;
import com.hkbank.pbcrs.mapper.PbcrsSysReportconfigMapper;
import com.hkbank.pbcrs.model.ErrorInfo;

@Service

public class PbcrsReportSendfileService {

	@Autowired
	private PbcrsReportSendfileMapper pbcrsReportSendfileMapper;

	@Autowired
	private PbcrsReportConditionMapper pbcrsReportConditionMapper;
	
	@Autowired
	private PbcrsSysReportconfigMapper pbcrsSysReportconfigMapper;
	//企业更正列表
	public Map<String,Object> listPage(Map<String,Object> params){
			
			int pageNo = MapUtils.getIntValue(params, "page");
			int pageSize = MapUtils.getIntValue(params, "rows");
			
			int skip = (pageNo - 1) * pageSize;

			String countKey;
			/* 2. 计算总记录数 */
			int total = pbcrsReportSendfileMapper.selectListCount(params);

			/* 3. 计算记录数 , 避免记录数超过 */
			// while (total > 0 && total <= skip) {
			// skip = skip - pageSize;
			// pageNo = pageNo - 1;
			// }
			/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
			List<Map<String,Object>> list = null;
			if (total <= 0 || total <= skip) {
				list = new ArrayList<Map<String,Object>>();
			} else {
				int endindex= skip+pageSize;
				params.put("endindex", endindex);
				params.put("startindex", skip);
				list = pbcrsReportSendfileMapper.selectListPage( params);
			}
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("total", total);
			rslt.put("rows", list);
			
			return rslt;
			
		}
	
	public Map<String,Object> listPageRptType(Map<String,Object> params){
		
		List<Map<String,Object>> list = pbcrsSysReportconfigMapper.selectAllByRptType(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("query", list);

		return rslt;
		
	}
	
	public Map<String,Object> getFeed(Map<String,Object> params){
		
		Map<String,Object> getFeed = pbcrsSysReportconfigMapper.getFeed(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		if(getFeed == null){
			//如果初始化表无数据，查询msg_code表
			List<Map<String,Object>> getFeedByMsgCode = pbcrsSysReportconfigMapper.getFeedByMsgCode(params);
			if(getFeedByMsgCode == null){
				rslt.put("code", "no");
			}else{
				rslt.put("code", "msgOk");
				rslt.put("query", getFeedByMsgCode);
			}
			
		}else{
			rslt.put("code", "ok");
			rslt.put("query", getFeed);
		}
		
		return rslt;
		
	}
	
	/*----------------------------银联查询-------------------------------------*/
	public Map<String,Object> YLlistPage(Map<String,Object> params){
			int pageNo = MapUtils.getIntValue(params, "page");
			int pageSize = MapUtils.getIntValue(params, "rows");
			int skip = (pageNo - 1) * pageSize;
			//params.get("YLqueryRptDate").toString()
			String countKey;
			/* 2. 计算总记录数 */
			int total = pbcrsReportSendfileMapper.selectYLListCount(params);
			/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
			List<Map<String,Object>> list = null;
			if (total <= 0 || total <= skip) {
				list = new ArrayList<Map<String,Object>>();
			} else {
				int endindex= skip+pageSize;
				params.put("endindex", endindex);
				params.put("startindex", skip);
				list = pbcrsReportSendfileMapper.selectYLListPage( params);
			}
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("total", total);
			rslt.put("rows", list);
			return rslt;
			
		}
	/**
	 * 插入银联数据
	 * @param list
	 * @param date
	 */
	public void insertYl(List<String> list,String date) {
		Map<String,String> delPar = new HashMap<String, String>();
		delPar.put("CREATEDATE", date);
		delPar.put("CONDITIONID", "YL");
		pbcrsReportSendfileMapper.deleteYL(delPar);
		for (String encFile : list) {
			String encName = FilenameUtils.getName(encFile);
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("FILEPATH",FilenameUtils.getFullPathNoEndSeparator(encFile));
			params.put("ENCFILENAME", encName);
			params.put("CREATEDATE", date);
			params.put("CONDITIONID", "YL");
			params.put("LOADFILETIME", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			pbcrsReportSendfileMapper.insertYL(params);		
		}
	}
	
	public Map<String,Object> getSendFileRel(Map<String,Object> params) {
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		int skip = (pageNo - 1) * pageSize;
		String countKey;
		/* 计算总记录数 */
		int total = pbcrsReportSendfileMapper.selectSendFileRelCount(params);
		/* 记录数超限以后，不查询，直接返回空结果集 */
		List<Map<String,Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String,Object>>();
		} else {
			int endindex= skip+pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportSendfileMapper.selectSendFileRel( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);
		return rslt;
	}

	public Map<String, Object> queryErrorInfo(Map<String, Object> params) {
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		int skip = (pageNo - 1) * pageSize;
		String countKey;
		/* 计算总记录数 */
		int total = pbcrsReportSendfileMapper.queryErrorInfoCount(params);
		/* 记录数超限以后，不查询，直接返回空结果集 */
		List<Map<String,Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String,Object>>();
		} else {
			int endindex= skip+pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportSendfileMapper.queryErrorInfo( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);
		return rslt;
	}

	public int addFilter(List<ErrorInfo> parseArray) throws Exception {
		int count = 0;
		for (ErrorInfo errorInfo : parseArray) {
				//直接执行删除语句后再执行插入,防止数据重复
				pbcrsReportSendfileMapper.delete(errorInfo);
				int cnt = pbcrsReportSendfileMapper.addFilter(errorInfo);
				count = count + cnt;
				List<Map<String, Object>> tableInfoList = pbcrsReportConditionMapper.getTableInfo(errorInfo.getInfrecType());
				String tableName = null;
				String tableSeqno = null;
				if (tableInfoList.size() > 0 && tableInfoList.get(0).get("TABLENAME") != null && tableInfoList.get(0).get("TABLESEQNO") != null) {
					tableName = tableInfoList.get(0).get("TABLENAME").toString();
					tableSeqno = tableInfoList.get(0).get("TABLESEQNO").toString();
				} else {
					throw new Exception("查询PBCRS_SYS_REPORTCONFIG配置表出错!");
				}
				Map<String,String> params = new HashMap<>(16);
				params.put("tableName",tableName);
				params.put("tableSeqno",tableSeqno);
				params.put("seqno",errorInfo.getReportseqno());
				params.put("etlDate",errorInfo.getRptDate());
				params.put("reportFlag","2");
				pbcrsReportSendfileMapper.updateFilterFlag(params);

		}
		return count;
	}

	public int removeFilter(List<ErrorInfo> parseArray) throws Exception {
		int count = 0;
		for (ErrorInfo errorInfo : parseArray) {
			int cnt = pbcrsReportSendfileMapper.removeFilter(errorInfo);
			count = count + cnt;
			List<Map<String, Object>> tableInfoList = pbcrsReportConditionMapper.getTableInfo(errorInfo.getInfrecType());
			String tableName = null;
			String tableSeqno = null;
			if (tableInfoList.size() > 0 && tableInfoList.get(0).get("TABLENAME") != null && tableInfoList.get(0).get("TABLESEQNO") != null) {
				tableName = tableInfoList.get(0).get("TABLENAME").toString();
				tableSeqno = tableInfoList.get(0).get("TABLESEQNO").toString();
			} else {
				throw new Exception("查询PBCRS_SYS_REPORTCONFIG配置表出错!");
			}
			Map<String,String> params = new HashMap<>(16);
			params.put("tableName",tableName);
			params.put("tableSeqno",tableSeqno);
			params.put("seqno",errorInfo.getReportseqno());
			params.put("etlDate",errorInfo.getRptDate());
			params.put("reportFlag","0");
			pbcrsReportSendfileMapper.updateFilterFlag(params);
		}
		return count;
	}
}
