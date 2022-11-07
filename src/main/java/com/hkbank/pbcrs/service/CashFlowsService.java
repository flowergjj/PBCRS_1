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
import com.hkbank.pbcrs.mapper.PbcrsReportCashflows2007sgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCashflowsMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCashflowsbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCashflowsdltMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfactcotinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinffcssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfidrecMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfidsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfmmbinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfshahodinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnmmbinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnshahodinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportSpvathinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCotainfsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportActucotrlinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheet;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheet2007;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheetbssgm;
import com.hkbank.pbcrs.model.PbcrsReportCashflows;
import com.hkbank.pbcrs.model.PbcrsReportCashflows2007sgmt;
import com.hkbank.pbcrs.model.PbcrsReportCashflowsbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportCashflowsdlt;
import com.hkbank.pbcrs.model.PbcrsReportCotainfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfactcotinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidrec;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfmmbinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfshahodinf;
import com.hkbank.pbcrs.model.PbcrsReportIncomesbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportIncstaproappdlt;
import com.hkbank.pbcrs.model.PbcrsReportMnmmbinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnshahodinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpvathinfsgmt;

import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;

@Service
public class CashFlowsService implements BaseExternalSpi {
	private static final Logger log = LogManager
			.getLogger(CashFlowsService.class);

	@Autowired
	PbcrsReportCashflowsMapper pbcrsReportCashflowsMapper;

	@Autowired
	PbcrsReportCashflowsbssgmtMapper pbcrsReportCashflowsbssgmtMapper;

	@Autowired
	PbcrsReportCashflows2007sgmtMapper pbcrsReportCashflows2007sgmtMapper;

	@Autowired
	private InvestigationTypeService investigationTypeService; // 报文修改

	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;// 报文修改痕迹记录
	@Autowired
	PbcrsReportCashflowsdltMapper pbcrsReportCashflowsdltMapper;//现金流量整笔删除

	public Object getCashflowsbssgmt(Map<String, Object> params) {
		if (params.containsKey("CashFlowsSeqNo")) {
			String ENBASINFSEQNO = params.get("CashFlowsSeqNo").toString();
			return pbcrsReportCashflowsbssgmtMapper
					.selectByCashFlowsSeqNo(ENBASINFSEQNO);
		} else {
			return null;
		}

	}

	public int updateCashflowsbssgmt(Map<String, Object> params)
			throws Exception {
		log.info("updateCashflowsbssgmt:{}", JSON.toJSONString(params));

		if (params.containsKey("CashFlowsSeqNo")) {

			try {
				String type = params.get("type").toString();
				String ENBASINFSEQNO = params.get("CashFlowsSeqNo").toString();
				// PbcrsReportCashflows cashflows =
				// pbcrsReportCashflowsMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportCashflowsbssgmt pbcrsReportCashflowsbssgmtOld = pbcrsReportCashflowsbssgmtMapper
						.selectByPrimaryKey(ENBASINFSEQNO);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());

				// if(pbcrsReportCashflowsbssgmt.getRptDateCode().equals("20")||pbcrsReportCashflowsbssgmt.getRptDate().equals(now)){//修改
				PbcrsReportCashflowsbssgmt pbcrsReportCashflowsbssgmt = (PbcrsReportCashflowsbssgmt) Util
						.map2Object2(params, PbcrsReportCashflowsbssgmt.class);
				// int cashflowsbssgmtkey=
				// pbcrsReportCashflowsbssgmtMapper.getKey();
				pbcrsReportCashflowsbssgmt.setCashFlowsBsSgmtSeqNo(String
						.valueOf(ENBASINFSEQNO));
				// cashflows.setCashFlowsBsSgmtSeqNo(pbcrsReportCashflowsbssgmt.getCashFlowsBsSgmtSeqNo());
				if(type.equals(Constants.TYPE_SUB)){
					pbcrsReportCashflowsbssgmt
					.setReportFlag(Constants.REPORTFLAG_IN);
				}else{
					pbcrsReportCashflowsbssgmt
					.setReportFlag(Constants.REPORTFLAG_IN);
					pbcrsReportCashflowsbssgmt.setRptDateCode("20");
					pbcrsReportCashflowsbssgmt.setRptDate(now);
				}

				
				int cnt = pbcrsReportCashflowsbssgmtMapper
						.updateByPrimaryKey(pbcrsReportCashflowsbssgmt);
				if (cnt != 1)
					return cnt;
				// 报文修改痕迹记录
				pbcrsReportTraceService.insertTrace(
						pbcrsReportCashflowsbssgmtOld, params,
						"CashFlowsBsSgmt", ENBASINFSEQNO);
				// 将更正段插入修改表
				cnt = investigationTypeService.changeUpdate("CashFlows",
						ENBASINFSEQNO, null);
				if (cnt != 1)
					return cnt;
				// cnt=pbcrsReportCashflowsMapper.updateByPrimaryKey(cashflows);
				return cnt;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		} else {
			return 0;
		}

	}

	public Object getCashflows2007sgmt(Map<String, Object> params) {
		if (params.containsKey("CashFlowsSeqNo")) {
			String ENBASINFSEQNO = params.get("CashFlowsSeqNo").toString();
			return pbcrsReportCashflows2007sgmtMapper
					.selectByCashFlowsSeqNo(ENBASINFSEQNO);
		} else {
			return null;
		}

	}

	public int upCashflows2007sgmt(Map<String, Object> params) throws Exception {
		log.info("upCashflows2007sgmt:{}", JSON.toJSONString(params));
		if (params.containsKey("CashFlowsSeqNo")) {

			try {

				String type = params.get("type").toString();
				String ENBASINFSEQNO = params.get("CashFlowsSeqNo").toString();
				// PbcrsReportCashflows cashflows =
				// pbcrsReportCashflowsMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportCashflowsbssgmt pbcrsReportCashflowsbssgmt = pbcrsReportCashflowsbssgmtMapper
						.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportCashflows2007sgmt cashflows2007sgmtOld = pbcrsReportCashflows2007sgmtMapper
						.selectByPrimaryKey(ENBASINFSEQNO);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());

				// if(pbcrsReportCashflowsbssgmt.getRptDateCode().equals("20")||pbcrsReportCashflowsbssgmt.getRptDate().equals(now)){//修改
				PbcrsReportCashflows2007sgmt pbcrsReportCashflows2007sgmt = (PbcrsReportCashflows2007sgmt) Util
						.map2Object2(params, PbcrsReportCashflows2007sgmt.class);
				// int cashflows2007sgmtkey=
				// pbcrsReportCashflows2007sgmtMapper.getKey();
				pbcrsReportCashflows2007sgmt.setCashFlows2007SgmtSeqNo(String
						.valueOf(ENBASINFSEQNO));
				if(type.equals(Constants.TYPE_SUB)){
					pbcrsReportCashflows2007sgmt
					.setReportFlag(Constants.REPORTFLAG_IN);
					pbcrsReportCashflowsbssgmt
					.setReportFlag(Constants.REPORTFLAG_IN);
					pbcrsReportCashflowsbssgmtMapper.updateByPrimaryKey(pbcrsReportCashflowsbssgmt);
				}else{
					pbcrsReportCashflows2007sgmt
					.setReportFlag(Constants.REPORTFLAG_IN);
					updateByKey(ENBASINFSEQNO);
				}

				// cashflows.setCashFlows2007SgmtSeqNo(pbcrsReportCashflows2007sgmt.getCashFlows2007SgmtSeqNo());
				int cnt = pbcrsReportCashflows2007sgmtMapper
						.updateByPrimaryKey(pbcrsReportCashflows2007sgmt);
				if (cnt != 1)
					return cnt;
				// 报文修改痕迹记录
				pbcrsReportTraceService.insertTrace(cashflows2007sgmtOld,
						params, "CashFlows2007Sgmt", ENBASINFSEQNO);
				// 将更正段插入修改表
				cnt = investigationTypeService.changeUpdate("CashFlows",
						ENBASINFSEQNO, null);
				if (cnt != 1)
					return cnt;
				// cnt=pbcrsReportCashflowsMapper.updateByPrimaryKey(cashflows);
				return cnt;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		} else {
			return 0;
		}

	}

	public Map<String, Object> listPage(Map<String, Object> params) {
		/*
		 * List<PbcrsReportInbasinfo> resultList=mapper.selectAll(); for
		 * (PbcrsReportInbasinfo pbcrsReportInbasinfo : resultList) { String
		 * sgmtSeqNo=pbcrsReportInbasinfo.getBsSgmtSeqNo(); PbcrsReportBssgmt
		 * bssgmt=bssgmtMapper.selectByPrimaryKey(sgmtSeqNo);
		 * pbcrsReportInbasinfo.setBsSgmt(bssgmt); } return resultList;
		 */

		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");

		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportCashflowsbssgmtMapper
				.selectAllbyContCount(params);

		/* 3. 计算记录数 , 避免记录数超过 */
		// while (total > 0 && total <= skip) {
		// skip = skip - pageSize;
		// pageNo = pageNo - 1;
		// }
		/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
		List<Map<String, Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			int endindex = skip + pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportCashflowsbssgmtMapper.selectAllbyContPage(params);
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
		pbcrsReportCashflowsbssgmtMapper.updateByMap(map);
		pbcrsReportCashflows2007sgmtMapper.updateByMap(map);
		return 0;
	}

	@Override
	public int updateByMapMdc(Map map) {
		// TODO Auto-generated method stub
		return 0;
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
		params.put("CashFlowsSeqNo", seqNo);
		params.put("RptDateCode", "20");
	    params.put("reportFlag", Constants.REPORTFLAG_IN);
	    params.put("rptDate", now);
		return pbcrsReportCashflowsbssgmtMapper.updateByKey(params);		
	}
	
    /**
   * 修改基础段flag,rptdate
   * @param seqNo
   * @return
   */
  public int updateByFlagAndDate(String seqNo){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String now = sdf.format(new Date());
    Map<String,Object> params = new HashMap<String, Object>();
    params.put("CashFlowsSeqNo", seqNo);
    params.put("reportFlag", Constants.REPORTFLAG_IN);
    params.put("rptDate", now);
    return pbcrsReportCashflowsbssgmtMapper.updateByFlagAndDate(params);   
  }
	
	/**
	 * 修改主表删除状态：已删除
	 * @param seqNo
	 * @return
	 */
	public int updateByIsDel(Map<String,Object> params) throws Exception{
		log.info("updateByIsDel:{}",JSON.toJSONString(params));
		
		if(params.containsKey("CashFlowsSeqNo")){
			String CashFlowsSeqNo = params.get("CashFlowsSeqNo").toString();
			//查询基础段信息
			PbcrsReportCashflowsbssgmt pbcrsReportCashflowsbssgmt = pbcrsReportCashflowsbssgmtMapper
					.selectByPrimaryKey(CashFlowsSeqNo);
			//插入删除
			PbcrsReportCashflowsdlt EnCashDlt = new PbcrsReportCashflowsdlt();
			EnCashDlt.setCashFlowsDltSeqNo(CashFlowsSeqNo);
			EnCashDlt.setInfRecType("634");
			EnCashDlt.setEntName(pbcrsReportCashflowsbssgmt.getEntName());
			EnCashDlt.setEntCertType(pbcrsReportCashflowsbssgmt.getEntCertType());
			EnCashDlt.setEntCertNum(pbcrsReportCashflowsbssgmt.getEntCertNum());
			EnCashDlt.setSheetYear(pbcrsReportCashflowsbssgmt.getSheetYear());
			EnCashDlt.setSheetType(pbcrsReportCashflowsbssgmt.getSheetType());
			EnCashDlt.setSheetTypeDivide(pbcrsReportCashflowsbssgmt.getSheetTypeDivide());
			EnCashDlt.setSourceSys(pbcrsReportCashflowsbssgmt.getSourceSys());
			EnCashDlt.setReportFlag(Constants.REPORTFLAG_IN);
			EnCashDlt.setOrgCode(pbcrsReportCashflowsbssgmt.getOrgCode());
			int dltRt = pbcrsReportCashflowsdltMapper.insert(EnCashDlt);
			if(dltRt > 0){
				params.put("IsDel", "1");
				int crt =  pbcrsReportCashflowsbssgmtMapper.updateByIsDel(params);
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
