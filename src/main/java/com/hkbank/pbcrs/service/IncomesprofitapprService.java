package com.hkbank.pbcrs.service;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;














import com.alibaba.fastjson.JSON;
import com.hkbank.pbcrs.util.Constants;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.BaseExternalSpi;
import com.hkbank.pbcrs.mapper.PbcrsReportActucotrlinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportBalancesheet2007Mapper;
import com.hkbank.pbcrs.mapper.PbcrsReportBalancesheetMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportBalancesheetbssgmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfactcotinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinffcssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfidrecMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfidsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfmmbinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfshahodinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportIncomesbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportIncomesprofitapprMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportIncomestatement07Mapper;
import com.hkbank.pbcrs.mapper.PbcrsReportIncstaproappdltMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnmmbinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnshahodinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportSpvathinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCotainfsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportActucotrlinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheet;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheet2007;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheetbssgm;
import com.hkbank.pbcrs.model.PbcrsReportCotainfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctinfentdel;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfactcotinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidrec;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfmmbinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfshahodinf;
import com.hkbank.pbcrs.model.PbcrsReportIncomesbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportIncomesprofitappr;
import com.hkbank.pbcrs.model.PbcrsReportIncomestatement07;
import com.hkbank.pbcrs.model.PbcrsReportIncstaproappdlt;
import com.hkbank.pbcrs.model.PbcrsReportMnmmbinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnshahodinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpvathinfsgmt;














import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;


@Service
public class IncomesprofitapprService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(IncomesprofitapprService.class);

	
	
	@Autowired
	PbcrsReportIncomesprofitapprMapper pbcrsReportIncomesprofitapprMapper;
	
	@Autowired
	PbcrsReportIncomesbssgmtMapper pbcrsReportIncomesbssgmtMapper;
	
	@Autowired
	PbcrsReportIncomestatement07Mapper pbcrsReportIncomestatement07Mapper;
	
	@Autowired
	private InvestigationTypeService investigationTypeService; //报文修改
	
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;//报文修改痕迹记录
	
	@Autowired
	PbcrsReportIncstaproappdltMapper pbcrsReportIncstaproappdltMapper;//利润及分配整笔删除

	public Object getIncomesbssgmt(Map<String,Object> params){
		if(params.containsKey("IncomeStatementPASeqNo")){
			String ENBASINFSEQNO = params.get("IncomeStatementPASeqNo").toString();
			return pbcrsReportIncomesbssgmtMapper.selectByIncomeStatementPASeqNo(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	public int upIncomestatement07(Map<String,Object> params) throws Exception{
		log.info("upIncomestatement07:{}",JSON.toJSONString(params));
		
		if(params.containsKey("IncomeStatementPASeqNo")){
			
			try {
				String type = params.get("type").toString();
				String ENBASINFSEQNO = params.get("IncomeStatementPASeqNo").toString();
//				PbcrsReportIncomesprofitappr incomesprofitappr = pbcrsReportIncomesprofitapprMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportIncomesbssgmt incomesbssgmt = pbcrsReportIncomesbssgmtMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportIncomestatement07 incomestatement07Old = pbcrsReportIncomestatement07Mapper.selectByPrimaryKey(ENBASINFSEQNO);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
//				if(incomesbssgmt.getRptDateCode().equals("20")||incomesbssgmt.getRptDate().equals(now)){//修改
					PbcrsReportIncomestatement07 incomestatement07=(PbcrsReportIncomestatement07) Util.map2Object2(params,PbcrsReportIncomestatement07.class);
//					int incomestatement07key= pbcrsReportIncomestatement07Mapper.getKey();
					incomestatement07.setIncomeStatementPA2007SgmtSeqNo(String.valueOf(ENBASINFSEQNO));
					if(type.equals(Constants.TYPE_SUB)){
						incomestatement07.setReportFlag(Constants.REPORTFLAG_IN);
						incomesbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportIncomesbssgmtMapper.updateByPrimaryKey(incomesbssgmt);
					}else{
						incomestatement07.setReportFlag(Constants.REPORTFLAG_IN);
						updateByKey(ENBASINFSEQNO);
					}
					
//					incomesprofitappr.setIncomeStatementPA2007SgmtSeqNo(incomestatement07.getIncomeStatementPA2007SgmtSeqNo());
					int cnt = pbcrsReportIncomestatement07Mapper.updateByPrimaryKey(incomestatement07);
					if(cnt!=1)
						return cnt;
					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(incomestatement07Old, params, "IncomeStatement07",ENBASINFSEQNO);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("IncomeS", ENBASINFSEQNO, null);
					if(cnt!=1)
						return cnt;
//					cnt=pbcrsReportIncomesprofitapprMapper.updateByPrimaryKey(incomesprofitappr);
					return cnt;

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
		}else{
			return 0;
		}
	}
	
	public Object getIncomeStatement07(Map<String,Object> params){
		if(params.containsKey("IncomeStatementPASeqNo")){
			String ENBASINFSEQNO = params.get("IncomeStatementPASeqNo").toString();
			return pbcrsReportIncomestatement07Mapper.selectByIncomeStatementPASeqNo(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	
	public int updateIncomesbssgmt(Map<String,Object> params) throws Exception{
		log.info("updateIncomesbssgmt:{}",JSON.toJSONString(params));
		
		if(params.containsKey("IncomeStatementPASeqNo")){
			
			try {
				String type = params.get("type").toString();
				String ENBASINFSEQNO = params.get("IncomeStatementPASeqNo").toString();
//				PbcrsReportIncomesprofitappr incomesprofitappr = pbcrsReportIncomesprofitapprMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportIncomesbssgmt incomesbssgmtOLd = pbcrsReportIncomesbssgmtMapper.selectByPrimaryKey(ENBASINFSEQNO);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
//				if(incomesbssgmt.getRptDateCode().equals("20")||incomesbssgmt.getRptDate().equals(now)){//修改
				PbcrsReportIncomesbssgmt incomesbssgmt=(PbcrsReportIncomesbssgmt) Util.map2Object2(params,PbcrsReportIncomesbssgmt.class);
//					int incomesbssgmtkey= pbcrsReportIncomesbssgmtMapper.getKey();
					incomesbssgmt.setIncomeStatementPABsSgmtSeqNo(String.valueOf(ENBASINFSEQNO));
					if(type.equals(Constants.TYPE_SUB)){
						incomesbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
					}else{
						incomesbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						incomesbssgmt.setRptDateCode("20");
						incomesbssgmt.setRptDate(now);
					}
					
					
//					incomesprofitappr.setIncomeStatementPABsSgmtSeqNo(incomesbssgmt.getIncomeStatementPABsSgmtSeqNo());
					int cnt = pbcrsReportIncomesbssgmtMapper.updateByPrimaryKey(incomesbssgmt);
					if(cnt!=1)
						return cnt;
					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(incomesbssgmtOLd, params, "IncomeSBsSgmt",ENBASINFSEQNO);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("IncomeS", ENBASINFSEQNO, null);
					if(cnt!=1)
						return cnt;
//					cnt=pbcrsReportIncomesprofitapprMapper.updateByPrimaryKey(incomesprofitappr);
					return cnt;

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
		}else{
			return 0;
		}

	}
	
	
	
	
	public Map<String,Object> listPage(Map<String,Object> params){
		/*List<PbcrsReportInbasinfo> resultList=mapper.selectAll();
		for (PbcrsReportInbasinfo pbcrsReportInbasinfo : resultList) {
			String sgmtSeqNo=pbcrsReportInbasinfo.getBsSgmtSeqNo();
			PbcrsReportBssgmt bssgmt=bssgmtMapper.selectByPrimaryKey(sgmtSeqNo);
			pbcrsReportInbasinfo.setBsSgmt(bssgmt);
		}
		return resultList;*/
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportIncomesbssgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportIncomesbssgmtMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}

	@Override
	public Object selectByPrimaryKey(String seqId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByPrimaryKeyM(String seqId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertM(String selectStr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByMap(Map map) {
		// TODO Auto-generated method stub
		pbcrsReportIncomesbssgmtMapper.updateByMap(map);
		pbcrsReportIncomestatement07Mapper.updateByMap(map);
		return 1;
	}


	
	/**
	 * 修改基础段时点：更新
	 * @param seqNo
	 * @return
	 */
	public int updateByKey(String seqNo){
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String now = sdf.format(new Date());
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("IncomeStatementPASeqNo", seqNo);
		params.put("RptDateCode", "20");
	    params.put("reportFlag", Constants.REPORTFLAG_IN);
	    params.put("rptDate", now);
		return pbcrsReportIncomesbssgmtMapper.updateByKey(params);		
	}

	@Override
	public int updateByMapMdc(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 修改主表删除状态：已删除
	 * @param seqNo
	 * @return
	 */
	public int updateByIsDel(Map<String,Object> params) throws Exception{
		log.info("updateByIsDel:{}",JSON.toJSONString(params));
		
		if(params.containsKey("IncomeStatementPASeqNo")){
			String IncomeStatementPASeqNo = params.get("IncomeStatementPASeqNo").toString();
			//查询基础段信息
			PbcrsReportIncomesbssgmt incomesbssgmt = pbcrsReportIncomesbssgmtMapper.selectByPrimaryKey(IncomeStatementPASeqNo);
			log.info("incomesbssgmt",JSON.toJSONString(incomesbssgmt));
			//插入删除
			PbcrsReportIncstaproappdlt EnIncomDlt = new PbcrsReportIncstaproappdlt();
			EnIncomDlt.setIncStaProAppDltSeqNo(IncomeStatementPASeqNo);
			EnIncomDlt.setInfRecType("624");
			EnIncomDlt.setEntName(incomesbssgmt.getEntName());
			EnIncomDlt.setEntCertType(incomesbssgmt.getEntCertType());
			EnIncomDlt.setEntCertNum(incomesbssgmt.getEntCertNum());
			EnIncomDlt.setSheetYear(incomesbssgmt.getSheetYear());
			EnIncomDlt.setSheetType(incomesbssgmt.getSheetType());
			EnIncomDlt.setSheetTypeDivide(incomesbssgmt.getSheetTypeDivide());
			EnIncomDlt.setSourceSys(incomesbssgmt.getSourceSys());
			EnIncomDlt.setReportFlag(Constants.REPORTFLAG_IN);
			EnIncomDlt.setOrgCode(incomesbssgmt.getOrgCode());
			int dltRt = pbcrsReportIncstaproappdltMapper.insert(EnIncomDlt);
			if(dltRt > 0){
				params.put("IsDel", "1");
				int crt =  pbcrsReportIncomesbssgmtMapper.updateByIsDel(params);
				if(crt > 0){
					return 1;
				}else{
					throw new Exception();
				}
				
			}else{
				return 0;
			}

		}else{
			return 0;
		}	
	}
}
