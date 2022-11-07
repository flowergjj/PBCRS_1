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
import com.hkbank.pbcrs.mapper.PbcrsReportIncandexpstaMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportIncandexpstadltMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportIncandexpstasgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportIncandexpstbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnmmbinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnshahodinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportSpvathinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCotainfsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportActucotrlinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheet;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheet2007;
import com.hkbank.pbcrs.model.PbcrsReportBalancesheetbssgm;
import com.hkbank.pbcrs.model.PbcrsReportCotainfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfactcotinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidrec;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfmmbinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfshahodinf;
import com.hkbank.pbcrs.model.PbcrsReportIncandexpsta;
import com.hkbank.pbcrs.model.PbcrsReportIncandexpstadlt;
import com.hkbank.pbcrs.model.PbcrsReportIncandexpstasgmt;
import com.hkbank.pbcrs.model.PbcrsReportIncandexpstbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportInsbalshebssgmt;
import com.hkbank.pbcrs.model.PbcrsReportInsbalshedlt;
import com.hkbank.pbcrs.model.PbcrsReportMnmmbinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnshahodinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpvathinfsgmt;














import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;


@Service
public class IncAndExpStaService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(IncAndExpStaService.class);

	
	
	@Autowired
	PbcrsReportIncandexpstaMapper pbcrsReportIncandexpstaMapper;//主
	
	@Autowired
	PbcrsReportIncandexpstbssgmtMapper pbcrsReportIncandexpstbssgmtMapper;//基本
	
	@Autowired
	PbcrsReportIncandexpstasgmtMapper pbcrsReportIncandexpstasgmtMapper;//详细
	
	@Autowired
	private InvestigationTypeService investigationTypeService; //报文修改
	
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;//报文修改痕迹记录
	@Autowired
	PbcrsReportIncandexpstadltMapper pbcrsReportIncandexpstadltMapper;//事业单位收入支出整笔删除

	public Object getIncandexpstbssgmt(Map<String,Object> params){//基本
		if(params.containsKey("IncomeAndESSeqNo")){
			String ENBASINFSEQNO = params.get("IncomeAndESSeqNo").toString();
			return pbcrsReportIncandexpstbssgmtMapper.selectByIncomeAndESSeqNo(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	public int updateIncandexpstbssgmt(Map<String,Object> params) throws Exception{
		log.info("updateIncandexpstbssgmt:{}",JSON.toJSONString(params));
		if(params.containsKey("IncomeAndESSeqNo")){
			
			try {
				String type = params.get("type").toString();
				String ENBASINFSEQNO = params.get("IncomeAndESSeqNo").toString();
//				PbcrsReportIncandexpsta incandexpsta = pbcrsReportIncandexpstaMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportIncandexpstbssgmt pbcrsReportIncandexpstbssgmtOld = pbcrsReportIncandexpstbssgmtMapper.selectByPrimaryKey(ENBASINFSEQNO);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
//				if(pbcrsReportIncandexpstbssgmt.getRptDateCode().equals("20")||pbcrsReportIncandexpstbssgmt.getRptDate().equals(now)){//修改
				PbcrsReportIncandexpstbssgmt pbcrsReportIncandexpstbssgmt=(PbcrsReportIncandexpstbssgmt) Util.map2Object2(params,PbcrsReportIncandexpstbssgmt.class);
//					int incandexpstbssgmtkey= pbcrsReportIncandexpstbssgmtMapper.getKey();
					pbcrsReportIncandexpstbssgmt.setIncomeAndESBsSgmtSeqNo(String.valueOf(ENBASINFSEQNO));
					if(type.equals(Constants.TYPE_SUB)){
						pbcrsReportIncandexpstbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
					}else{
						pbcrsReportIncandexpstbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportIncandexpstbssgmt.setRptDateCode("20");
					}
					
					pbcrsReportIncandexpstbssgmt.setRptDate(now);
//					incandexpsta.setIncomeAndESBsSgmtSeqNo(pbcrsReportIncandexpstbssgmt.getIncomeAndESBsSgmtSeqNo());
					int cnt = pbcrsReportIncandexpstbssgmtMapper.updateByPrimaryKey(pbcrsReportIncandexpstbssgmt);
					if(cnt!=1)
						return cnt;
					
					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(pbcrsReportIncandexpstbssgmtOld, params, "IncAndExpStBsSgmt",ENBASINFSEQNO);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("IncAndExpSta", ENBASINFSEQNO, null);
					if(cnt!=1)
						return cnt;
//					cnt=pbcrsReportIncandexpstaMapper.updateByPrimaryKey(incandexpsta);
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
	
	public Object getIncandexpstasgmt(Map<String,Object> params){
		if(params.containsKey("IncomeAndESSeqNo")){
			String ENBASINFSEQNO = params.get("IncomeAndESSeqNo").toString();
			return pbcrsReportIncandexpstasgmtMapper.selectByIncomeAndESSeqNo(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	
	public int upIncandexpstasgmt(Map<String,Object> params) throws Exception{
		log.info("upIncandexpstasgmt:{}",JSON.toJSONString(params));
if(params.containsKey("IncomeAndESSeqNo")){
			
			try {
				String type = params.get("type").toString();
				String ENBASINFSEQNO = params.get("IncomeAndESSeqNo").toString();
//				PbcrsReportIncandexpsta incandexpsta = pbcrsReportIncandexpstaMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportIncandexpstbssgmt pbcrsReportIncandexpstbssgmt = pbcrsReportIncandexpstbssgmtMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportIncandexpstasgmt IncandexpstasgmtOld = pbcrsReportIncandexpstasgmtMapper.selectByPrimaryKey(ENBASINFSEQNO);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
//				if(pbcrsReportIncandexpstbssgmt.getRptDateCode().equals("20")||pbcrsReportIncandexpstbssgmt.getRptDate().equals(now)){//修改
					PbcrsReportIncandexpstasgmt pbcrsReportIncandexpstasgmt=(PbcrsReportIncandexpstasgmt) Util.map2Object2(params,PbcrsReportIncandexpstasgmt.class);
//					int Incandexpstasgmtkey= pbcrsReportIncandexpstasgmtMapper.getKey();
					pbcrsReportIncandexpstasgmt.setIncomeAndESSgmtSeqNo(String.valueOf(ENBASINFSEQNO));
					if(type.equals(Constants.TYPE_SUB)){
						pbcrsReportIncandexpstasgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportIncandexpstbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportIncandexpstbssgmtMapper.updateByPrimaryKey(pbcrsReportIncandexpstbssgmt);
					}else{
						pbcrsReportIncandexpstasgmt.setReportFlag(Constants.REPORTFLAG_IN);
						updateByKey(ENBASINFSEQNO);
					}
					
//					incandexpsta.setIncomeAndESSgmtSeqNo(pbcrsReportIncandexpstasgmt.getIncomeAndESSgmtSeqNo());
					int cnt = pbcrsReportIncandexpstasgmtMapper.updateByPrimaryKey(pbcrsReportIncandexpstasgmt);
					if(cnt!=1)
						return cnt;
					
					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(IncandexpstasgmtOld, params, "IncAndExpStaSgmt",ENBASINFSEQNO);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("IncAndExpSta", ENBASINFSEQNO, null);
					if(cnt!=1)
						return cnt;
//					cnt=pbcrsReportIncandexpstaMapper.updateByPrimaryKey(incandexpsta);
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
		int total = pbcrsReportIncandexpstbssgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportIncandexpstbssgmtMapper.selectAllbyContPage( params);
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
		pbcrsReportIncandexpstbssgmtMapper.updateByMap(map);
		pbcrsReportIncandexpstasgmtMapper.updateByMap(map);
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
		params.put("IncomeAndESBsSgmtSeqNo", seqNo);
		params.put("RptDateCode", "20");
	    params.put("reportFlag", Constants.REPORTFLAG_IN);
	    params.put("rptDate", now);
		return pbcrsReportIncandexpstbssgmtMapper.updateByKey(params);		
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
    params.put("IncomeAndESBsSgmtSeqNo", seqNo);
    params.put("reportFlag", Constants.REPORTFLAG_IN);
    params.put("rptDate", now);
    return pbcrsReportIncandexpstbssgmtMapper.updateByFlagAndDate(params);   
  }
	
	/**
	 * 修改主表删除状态：已删除
	 * @param seqNo
	 * @return
	 */
	public int updateByIsDel(Map<String,Object> params) throws Exception{
		log.info("updateByIsDel:{}",JSON.toJSONString(params));
		
		if(params.containsKey("IncomeAndESSeqNo")){
			String IncomeAndESSeqNo = params.get("IncomeAndESSeqNo").toString();
			//查询基础段信息
			PbcrsReportIncandexpstbssgmt pbcrsReportIncandexpstbssgmt = pbcrsReportIncandexpstbssgmtMapper.selectByPrimaryKey(IncomeAndESSeqNo);

			//插入删除
			PbcrsReportIncandexpstadlt EnInCanDlt = new PbcrsReportIncandexpstadlt();
			EnInCanDlt.setIncAndExpStaDltSeqNo(IncomeAndESSeqNo);
			EnInCanDlt.setInfRecType("654");
			EnInCanDlt.setEntName(pbcrsReportIncandexpstbssgmt.getEntName());
			EnInCanDlt.setEntCertType(pbcrsReportIncandexpstbssgmt.getEntCertType());
			EnInCanDlt.setEntCertNum(pbcrsReportIncandexpstbssgmt.getEntCertNum());
			EnInCanDlt.setSheetYear(pbcrsReportIncandexpstbssgmt.getSheetYear());
			EnInCanDlt.setSheetType(pbcrsReportIncandexpstbssgmt.getSheetType());
			EnInCanDlt.setSheetTypeDivide(pbcrsReportIncandexpstbssgmt.getSheetTypeDivide());
			EnInCanDlt.setSourceSys(pbcrsReportIncandexpstbssgmt.getSourceSys());
			EnInCanDlt.setReportFlag(Constants.REPORTFLAG_IN);
			EnInCanDlt.setOrgCode(pbcrsReportIncandexpstbssgmt.getOrgCode());
			int dltRt = pbcrsReportIncandexpstadltMapper.insert(EnInCanDlt);
			if(dltRt > 0){
				params.put("IsDel", "1");
				int crt =  pbcrsReportIncandexpstbssgmtMapper.updateByIsDel(params);
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
