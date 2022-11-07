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
import com.hkbank.pbcrs.mapper.PbcrsReportBalancesheetdltMapper;
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
import com.hkbank.pbcrs.model.PbcrsReportBalancesheetdlt;
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
import com.hkbank.pbcrs.model.PbcrsReportMnmmbinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnshahodinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpvathinfsgmt;












import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;


@Service
public class BalanceSheetService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(BalanceSheetService.class);
	
	
	@Autowired
	PbcrsReportBalancesheetbssgmMapper pbcrsReportBalancesheetbssgmMapper;
	
	@Autowired
	PbcrsReportBalancesheet2007Mapper pbcrsReportBalancesheet2007Mapper;
	
	@Autowired
	PbcrsReportBalancesheetMapper pbcrsReportBalancesheetMapper;

	@Autowired
	private InvestigationTypeService investigationTypeService; //报文修改
	
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;//报文修改痕迹记录
	@Autowired
	PbcrsReportBalancesheetdltMapper pbcrsReportBalancesheetdltMapper;//企业资产负债整笔删除
	
	public Object getBalancesheetbssgm(Map<String,Object> params){
		if(params.containsKey("BalanceSheetSeqNo")){
			String ENBASINFSEQNO = params.get("BalanceSheetSeqNo").toString();
			return pbcrsReportBalancesheetbssgmMapper.selectByBalanceSheetSeqNo(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	public int updateBalanceSheetBsSgm(Map<String,Object> params) throws Exception{
		log.info("updateBalanceSheetBsSgm:{}",JSON.toJSONString(params));
		
		if(params.containsKey("BalanceSheetSeqNo")){
			
			try {
				String type = params.get("type").toString();
				String ENBASINFSEQNO = params.get("BalanceSheetSeqNo").toString();
//				PbcrsReportBalancesheet balancesheet = pbcrsReportBalancesheetMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportBalancesheetbssgm pbcrsReportBalancesheetbssgmOLd = pbcrsReportBalancesheetbssgmMapper.selectByPrimaryKey(ENBASINFSEQNO);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
//				if(pbcrsReportBalancesheetbssgm.getRptDateCode().equals("20")||pbcrsReportBalancesheetbssgm.getRptDate().equals(now)){//修改
				PbcrsReportBalancesheetbssgm pbcrsReportBalancesheetbssgm=(PbcrsReportBalancesheetbssgm) Util.map2Object2(params,PbcrsReportBalancesheetbssgm.class);
//					int balancesheetbssgmkey= pbcrsReportBalancesheetbssgmMapper.getKey();
					pbcrsReportBalancesheetbssgm.setBalanceSheetBsSgmtSeqNo(String.valueOf(ENBASINFSEQNO));
					if(type.equals(Constants.TYPE_SUB)){
						pbcrsReportBalancesheetbssgm.setReportFlag(Constants.REPORTFLAG_IN);
					}else{
						pbcrsReportBalancesheetbssgm.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportBalancesheetbssgm.setRptDateCode("20");//更新
						pbcrsReportBalancesheetbssgm.setRptDate(now);
					}
					
//					balancesheet.setBalanceSheetBsSgmtSeqNo(pbcrsReportBalancesheetbssgm.getBalanceSheetBsSgmtSeqNo());
					int cnt = pbcrsReportBalancesheetbssgmMapper.updateByPrimaryKey(pbcrsReportBalancesheetbssgm);
					if(cnt!=1)
						return cnt;
					
					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(pbcrsReportBalancesheetbssgmOLd, params, "BalanceSheetBsSgm",ENBASINFSEQNO);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("BalanceSheet", ENBASINFSEQNO, null);
					if(cnt!=1)
						return cnt;
//					cnt=pbcrsReportBalancesheetMapper.updateByPrimaryKey(balancesheet);
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
	
	public Object getBalanceSheet2007Sgmt(Map<String,Object> params){
		if(params.containsKey("BalanceSheetSeqNo")){
			String ENBASINFSEQNO = params.get("BalanceSheetSeqNo").toString();
			return pbcrsReportBalancesheet2007Mapper.selectByBalanceSheetSeqNo(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	
	public int upBalanceSheet2007Sgmt(Map<String,Object> params) throws Exception{
		log.info("upBalanceSheet2007Sgmt:{}",JSON.toJSONString(params));
			if(params.containsKey("BalanceSheetSeqNo")){
			
			try {
				String type = params.get("type").toString();
				String ENBASINFSEQNO = params.get("BalanceSheetSeqNo").toString();
//				PbcrsReportBalancesheet balancesheet = pbcrsReportBalancesheetMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportBalancesheetbssgm pbcrsReportBalancesheetbssgm = pbcrsReportBalancesheetbssgmMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportBalancesheet2007 balancesheet2007Old = pbcrsReportBalancesheet2007Mapper.selectByPrimaryKey(ENBASINFSEQNO);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
//				if(pbcrsReportBalancesheetbssgm.getRptDateCode().equals("20")||pbcrsReportBalancesheetbssgm.getRptDate().equals(now)){//修改
					
					PbcrsReportBalancesheet2007 balancesheet2007=(PbcrsReportBalancesheet2007) Util.map2Object2(params,PbcrsReportBalancesheet2007.class);
//					int balancesheet2007key= pbcrsReportBalancesheet2007Mapper.getKey();
					balancesheet2007.setBalanceSheetBsSgmtSeqNo(String.valueOf(ENBASINFSEQNO));
//					balancesheet.setBalanceSheet2007SgmtSeqNo(balancesheet2007.getBalanceSheetBsSgmtSeqNo());
					if(type.equals(Constants.TYPE_SUB)){
						balancesheet2007.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportBalancesheetbssgm.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportBalancesheetbssgmMapper.updateByPrimaryKey(pbcrsReportBalancesheetbssgm);
					}else{
						balancesheet2007.setReportFlag(Constants.REPORTFLAG_IN);
						updateByKey(ENBASINFSEQNO);
					}
					int cnt = pbcrsReportBalancesheet2007Mapper.updateByPrimaryKey(balancesheet2007);
					if(cnt!=1)
						return cnt;
					
					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(balancesheet2007Old, params, "BalanceSheet2007",ENBASINFSEQNO);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("BalanceSheet", ENBASINFSEQNO, null);
					if(cnt!=1)
						return cnt;
//					cnt=pbcrsReportBalancesheetMapper.updateByPrimaryKey(balancesheet);
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
		int total = pbcrsReportBalancesheetbssgmMapper.selectAllbyContCount(params);

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
			list = pbcrsReportBalancesheetbssgmMapper.selectAllbyContPage( params);
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
		pbcrsReportBalancesheetbssgmMapper.updateByMap(map);
		pbcrsReportBalancesheet2007Mapper.updateByMap(map);
		return 1;
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
		params.put("BalanceSheetSeqNo", seqNo);
		params.put("RptDateCode", "20");
	    params.put("reportFlag", Constants.REPORTFLAG_IN);
	    params.put("rptDate", now);
		return pbcrsReportBalancesheetbssgmMapper.updateByKey(params);		
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
    params.put("BalanceSheetSeqNo", seqNo);
    params.put("reportFlag", Constants.REPORTFLAG_IN);
    params.put("rptDate", now);
    return pbcrsReportBalancesheetbssgmMapper.updateByFlagAndDate(params);   
  }
	/**
	 * 修改主表删除状态：已删除
	 * @param seqNo
	 * @return
	 */
	public int updateByIsDel(Map<String,Object> params) throws Exception{
		log.info("updateByIsDel:{}",JSON.toJSONString(params));
		
		if(params.containsKey("BalanceSheetSeqNo")){
			String BalanceSheetSeqNo = params.get("BalanceSheetSeqNo").toString();
			//查询基础段信息
			PbcrsReportBalancesheetbssgm pbcrsReportBalancesheetbssgm = pbcrsReportBalancesheetbssgmMapper.selectByPrimaryKey(BalanceSheetSeqNo);

			//插入删除
			PbcrsReportBalancesheetdlt EnBalanceDlt = new PbcrsReportBalancesheetdlt();
			EnBalanceDlt.setBalanceSheetDltSeqNo(BalanceSheetSeqNo);
			EnBalanceDlt.setInfRecType("614");
			EnBalanceDlt.setEntName(pbcrsReportBalancesheetbssgm.getEntName());
			EnBalanceDlt.setEntCertType(pbcrsReportBalancesheetbssgm.getEntCertType());
			EnBalanceDlt.setEntCertNum(pbcrsReportBalancesheetbssgm.getEntCertNum());
			EnBalanceDlt.setSheetYear(pbcrsReportBalancesheetbssgm.getSheetYear());
			EnBalanceDlt.setSheetType(pbcrsReportBalancesheetbssgm.getSheetType());
			EnBalanceDlt.setSheetTypeDivide(pbcrsReportBalancesheetbssgm.getSheetTypeDivide());
			EnBalanceDlt.setSourceSys(pbcrsReportBalancesheetbssgm.getSourceSys());
			EnBalanceDlt.setReportFlag(Constants.REPORTFLAG_IN);
			EnBalanceDlt.setOrgCode(pbcrsReportBalancesheetbssgm.getOrgCode());
			int dltRt = pbcrsReportBalancesheetdltMapper.insert(EnBalanceDlt);
			if(dltRt > 0){
				params.put("IsDel", "1");
				int crt =  pbcrsReportBalancesheetbssgmMapper.updateByIsDel(params);
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
