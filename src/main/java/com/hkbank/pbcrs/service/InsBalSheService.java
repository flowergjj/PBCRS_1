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
import com.hkbank.pbcrs.mapper.PbcrsReportInsbalsheMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsbalshebssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsbalshedltMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsbalshesgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnmmbinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnshahodinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportSpvathinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCotainfsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportActucotrlinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheet;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheet2007;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheetbssgm;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheetdlt;
import com.hkbank.pbcrs.model.PbcrsReportCotainfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfactcotinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidrec;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfmmbinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfshahodinf;
import com.hkbank.pbcrs.model.PbcrsReportInsbalshe;
import com.hkbank.pbcrs.model.PbcrsReportInsbalshebssgmt;
import com.hkbank.pbcrs.model.PbcrsReportInsbalshedlt;
import com.hkbank.pbcrs.model.PbcrsReportInsbalshesgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnmmbinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnshahodinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpvathinfsgmt;














import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;


@Service
public class InsBalSheService implements BaseExternalSpi {
	
	private static final Logger log = LogManager.getLogger(InsBalSheService.class);

	
	@Autowired
	PbcrsReportInsbalsheMapper pbcrsReportInsbalsheMapper;//主表
	
	@Autowired
	PbcrsReportInsbalshebssgmtMapper pbcrsReportInsbalshebssgmtMapper;//基础
	
	@Autowired
	PbcrsReportInsbalshesgmtMapper pbcrsReportInsbalshesgmtMapper;//详细
	
	@Autowired
	private InvestigationTypeService investigationTypeService; //报文修改
	
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;//报文修改痕迹记录
	@Autowired
	PbcrsReportInsbalshedltMapper pbcrsReportInsbalshedltMapper;//事业单位资产负债整笔删除

	public Object getInsbalshebssgmt(Map<String,Object> params){
		if(params.containsKey("InstitutionBSSeqNo")){
			String ENBASINFSEQNO = params.get("InstitutionBSSeqNo").toString();
			return pbcrsReportInsbalshebssgmtMapper.selectByInstitutionBSSeqNo(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	public int updateInsbalshebssgmt(Map<String,Object> params) throws Exception{
		log.info("updateInsbalshebssgmt:{}",JSON.toJSONString(params));
		
		if(params.containsKey("InstitutionBSSeqNo")){
			
			try {
				String type =params.get("type").toString();
				String ENBASINFSEQNO = params.get("InstitutionBSSeqNo").toString();
//				PbcrsReportInsbalshe insbalshe = pbcrsReportInsbalsheMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportInsbalshebssgmt pbcrsReportInsbalshebssgmtOLd = pbcrsReportInsbalshebssgmtMapper.selectByPrimaryKey(ENBASINFSEQNO);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
//				if(pbcrsReportInsbalshebssgmt.getRptDateCode().equals("20")||pbcrsReportInsbalshebssgmt.getRptDate().equals(now)){//修改
				PbcrsReportInsbalshebssgmt	pbcrsReportInsbalshebssgmt=(PbcrsReportInsbalshebssgmt) Util.map2Object2(params,PbcrsReportInsbalshebssgmt.class);
//					int insbalshebssgmtkey= pbcrsReportInsbalshebssgmtMapper.getKey();
					pbcrsReportInsbalshebssgmt.setInstitutionBSBsSgmtSeqNo(String.valueOf(ENBASINFSEQNO));
//					insbalshe.setInstitutionBSBsSgmtSeqNo(pbcrsReportInsbalshebssgmt.getInstitutionBSBsSgmtSeqNo());
					if(type.equals(Constants.TYPE_SUB)){
						pbcrsReportInsbalshebssgmt.setReportFlag(Constants.REPORTFLAG_IN);
					}else{
						pbcrsReportInsbalshebssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportInsbalshebssgmt.setRptDateCode("20");
					}
					
					pbcrsReportInsbalshebssgmt.setRptDate(now);
					int cnt = pbcrsReportInsbalshebssgmtMapper.updateByPrimaryKey(pbcrsReportInsbalshebssgmt);
					if(cnt!=1)
						return cnt;
//					cnt=pbcrsReportInsbalsheMapper.updateByPrimaryKey(insbalshe);
					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(pbcrsReportInsbalshebssgmtOLd, params, "InsBalSheBsSgmt",ENBASINFSEQNO);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("InsBalShe", ENBASINFSEQNO, null);
					if(cnt!=1)
						return cnt;
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
	
	public Object getInsBalSheSgmt(Map<String,Object> params){
		if(params.containsKey("InstitutionBSSeqNo")){
			String ENBASINFSEQNO = params.get("InstitutionBSSeqNo").toString();
			return pbcrsReportInsbalshesgmtMapper.selectByInstitutionBSSeqNo(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
		
	}
	
	
	public int upInsBalSheSgmt(Map<String,Object> params) throws Exception{
		log.info("upInsBalSheSgmt:{}",JSON.toJSONString(params));
		if(params.containsKey("InstitutionBSSeqNo")){
			
			try {
				String type = params.get("type").toString();
				String ENBASINFSEQNO = params.get("InstitutionBSSeqNo").toString();
//				PbcrsReportInsbalshe insbalshe = pbcrsReportInsbalsheMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportInsbalshebssgmt pbcrsReportInsbalshebssgmt = pbcrsReportInsbalshebssgmtMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportInsbalshesgmt InsbalshesgmtOld = pbcrsReportInsbalshesgmtMapper.selectByPrimaryKey(ENBASINFSEQNO);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
//				if(pbcrsReportInsbalshebssgmt.getRptDateCode().equals("20")||pbcrsReportInsbalshebssgmt.getRptDate().equals(now)){//修改
					PbcrsReportInsbalshesgmt pbcrsReportInsbalshesgmt=(PbcrsReportInsbalshesgmt) Util.map2Object2(params,PbcrsReportInsbalshesgmt.class);
//					int insbalshesgmtkey= pbcrsReportInsbalshesgmtMapper.getKey();
					pbcrsReportInsbalshesgmt.setInstitutionBSSgmtSeqNo(String.valueOf(ENBASINFSEQNO));
					if(type.equals(Constants.TYPE_SUB)){
						pbcrsReportInsbalshesgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportInsbalshebssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportInsbalshebssgmtMapper.updateByPrimaryKey(pbcrsReportInsbalshebssgmt);
					}else{
						pbcrsReportInsbalshesgmt.setReportFlag(Constants.REPORTFLAG_IN);
						updateByKey(ENBASINFSEQNO);
					}
					
//					insbalshe.setInstitutionBSSgmtSeqNo(pbcrsReportInsbalshesgmt.getInstitutionBSSgmtSeqNo());
					int cnt = pbcrsReportInsbalshesgmtMapper.updateByPrimaryKey(pbcrsReportInsbalshesgmt);
					if(cnt!=1)
						return cnt;
					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(InsbalshesgmtOld, params, "InsBalSheSgmt",ENBASINFSEQNO);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("InsBalShe", ENBASINFSEQNO, null);
					if(cnt!=1)
						return cnt;
//					cnt=pbcrsReportInsbalsheMapper.updateByPrimaryKey(insbalshe);
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
		int total = pbcrsReportInsbalshebssgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportInsbalshebssgmtMapper.selectAllbyContPage( params);
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
		pbcrsReportInsbalshebssgmtMapper.updateByMap(map);
		pbcrsReportInsbalshesgmtMapper.updateByMap(map);
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
		params.put("InstitutionBSBsSgmtSeqNo", seqNo);
		params.put("RptDateCode", "20");
	    params.put("reportFlag", Constants.REPORTFLAG_IN);
	    params.put("rptDate", now);
		return pbcrsReportInsbalshebssgmtMapper.updateByKey(params);		
	}
	
	/**
	 * 修改主表删除状态：已删除
	 * @param seqNo
	 * @return
	 */
	public int updateByIsDel(Map<String,Object> params) throws Exception{
		log.info("updateByIsDel:{}",JSON.toJSONString(params));
		
		if(params.containsKey("InstitutionBSSeqNo")){
			String InstitutionBSSeqNo = params.get("InstitutionBSSeqNo").toString();
			//查询基础段信息
			PbcrsReportInsbalshebssgmt pbcrsReportInsbalshebssgmt = pbcrsReportInsbalshebssgmtMapper.selectByPrimaryKey(InstitutionBSSeqNo);

			//插入删除
			PbcrsReportInsbalshedlt EnInsBalDlt = new PbcrsReportInsbalshedlt();
			EnInsBalDlt.setInsBalSheDltSeqNo(InstitutionBSSeqNo);
			EnInsBalDlt.setInfRecType("644");
			EnInsBalDlt.setEntName(pbcrsReportInsbalshebssgmt.getEntName());
			EnInsBalDlt.setEntCertType(pbcrsReportInsbalshebssgmt.getEntCertType());
			EnInsBalDlt.setEntCertNum(pbcrsReportInsbalshebssgmt.getEntCertNum());
			EnInsBalDlt.setSheetYear(pbcrsReportInsbalshebssgmt.getSheetYear());
			EnInsBalDlt.setSheetType(pbcrsReportInsbalshebssgmt.getSheetType());
			EnInsBalDlt.setSheetTypeDivide(pbcrsReportInsbalshebssgmt.getSheetTypeDivide());
			EnInsBalDlt.setSourceSys(pbcrsReportInsbalshebssgmt.getSourceSys());
			EnInsBalDlt.setOrgCode(pbcrsReportInsbalshebssgmt.getOrgCode());
			EnInsBalDlt.setReportFlag(Constants.REPORTFLAG_IN);
			int dltRt = pbcrsReportInsbalshedltMapper.insert(EnInsBalDlt);
			if(dltRt > 0){
				params.put("IsDel", "1");
				int crt =  pbcrsReportInsbalshebssgmtMapper.updateByIsDel(params);
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
