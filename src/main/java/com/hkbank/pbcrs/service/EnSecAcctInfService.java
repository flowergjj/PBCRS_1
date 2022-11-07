package com.hkbank.pbcrs.service;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hkbank.pbcrs.util.Constants;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.BaseExternalSpi;
import com.hkbank.pbcrs.mapper.PbcrsReportEGuamotcltctrsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEGuarltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnguaaccbsinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnguaracctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecaccdelsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecaccidcaginfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecaccmdfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecaccmdfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecacctdelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecacctinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecacctinfmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecacctmdfcMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGserltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGserltrepymtinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuacccinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuaracctcredsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportEGuarltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnguaaccbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnguaracctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnsecaccdelsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnsecaccidcaginf;
import com.hkbank.pbcrs.model.PbcrsReportEnsecaccmdfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnsecacctdel;
import com.hkbank.pbcrs.model.PbcrsReportGserltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctcredsgmt;


@Service
public class EnSecAcctInfService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(EnSecAcctInfService.class);

	@Autowired
	PbcrsReportEnsecacctinfMapper pbcrsReportEnsecacctinfMapper; //主
	
	@Autowired
	PbcrsReportEnguaracctbssgmtMapper pbcrsReportEnguaracctbssgmtMapper; //基础段
	
	@Autowired
	PbcrsReportEnguaaccbsinfsgmtMapper pbcrsReportEnguaaccbsinfsgmtMapper; //基本信息段

	@Autowired
	PbcrsReportGserltrepinfsgmtMapper pbcrsReportGserltrepinfsgmtMapper; //保账户记录信息段相关还款责任人信息段
	
	@Autowired
	PbcrsReportGserltrepymtinfMapper pbcrsReportGserltrepymtinfMapper; //担保账户记录信息段相关还款责任人信息段详细
	
	@Autowired
	PbcrsReportEGuamotcltctrsgmtMapper pbcrsReportEGuamotcltctrsgmtMapper; //抵质押列表
	
	@Autowired
	PbcrsReportGuacccinfMapper pbcrsReportGuacccinfMapper; //抵质押合同号
	
	@Autowired
	PbcrsReportGuaracctcredsgmtMapper pbcrsReportGuaracctcredsgmtMapper; //授信额度信息段
	
	@Autowired
	PbcrsReportEGuarltrepinfsgmtMapper pbcrsReportEGuarltrepinfsgmtMapper; //在保责任信息段
	
	@Autowired
	PbcrsReportEnsecacctinfmMapper pbcrsReportEnsecacctinfmMapper; //修改
	
	@Autowired
	PbcrsReportEnsecacctmdfcMapper pbcrsReportEnsecacctmdfcMapper; //更正段
	
	@Autowired
	PbcrsReportEnsecaccmdfbssgmtMapper pbcrsReportEnsecaccmdfbssgmtMapper; //更正段基础段
	
	@Autowired
	PbcrsReportEnsecaccmdfsgmtMapper pbcrsReportEnsecaccmdfsgmtMapper; //更正企业授信协议更正请求记录信息
	
	@Autowired
	PbcrsReportEnsecaccidcaginfMapper pbcrsReportEnsecaccidcaginfMapper; //企业担保账户标识变更请求记录
	
	@Autowired
	PbcrsReportTraceService pbcrsReportTraceService;//痕迹表
	@Autowired
	InvestigationTypeService investigationTypeService;//自动生成更新端
	@Autowired
	PbcrsReportEnsecaccdelsgmtMapper pbcrsReportEnsecaccdelsgmtMapper;//担保整笔删除
	
	@Autowired
	PbcrsReportEnsecacctdelMapper pbcrsReportEnsecacctdelMapper;//按段删除
	
	//列表
	public Map<String,Object> listPage(Map<String,Object> params){
			
			int pageNo = MapUtils.getIntValue(params, "page");
			int pageSize = MapUtils.getIntValue(params, "rows");
			
			int skip = (pageNo - 1) * pageSize;

			String countKey;
			/* 2. 计算总记录数 */
			int total = pbcrsReportEnguaracctbssgmtMapper.selectAllbyContCount(params);

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
				list = pbcrsReportEnguaracctbssgmtMapper.selectAllbyContPage( params);
			}
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("total", total);
			rslt.put("rows", list);

			return rslt;
			
		} 
	
	//基础段
	public Object getEnguaracctbssgmt(Map<String,Object> params){
		if(params.containsKey("EnSecAcctInfSeqNo")){
			String EnSecAcctInfSeqNo = params.get("EnSecAcctInfSeqNo").toString();
			return pbcrsReportEnguaracctbssgmtMapper.selectByEnSecAcctInfSeqNo(EnSecAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	public Object getEnguaracctbssgmtm(Map<String,Object> params){
		if(params.containsKey("EnSecAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnSecAcctInfSeqNo").toString();
			return pbcrsReportEnguaracctbssgmtMapper.selectByEnSecAcctInfMSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	//基本信息
	public Object getEnguaaccbsinfsgmt(Map<String,Object> params){
		if(params.containsKey("EnSecAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnSecAcctInfSeqNo").toString();
			return pbcrsReportEnguaaccbsinfsgmtMapper.selectByEnSecAcctInfMSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	public Object getEnguaaccbsinfsgmtm(Map<String,Object> params){
		if(params.containsKey("EnSecAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnSecAcctInfSeqNo").toString();
			return pbcrsReportEnguaaccbsinfsgmtMapper.selectByEnSecAcctInfMSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	//抵质押列表
	public Map<String,Object> listMotCltCtrInfSgmtPage(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportEGuamotcltctrsgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportEGuamotcltctrsgmtMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	} 
	
	public Map<String,Object> listMotCltCtrInfSgmtPagem(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportEGuamotcltctrsgmtMapper.selectAllbyContCountm(params);

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
			list = pbcrsReportEGuamotcltctrsgmtMapper.selectAllbyContPagem( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	} 
	
	
	//抵质押详细
	public Object getCccinf(Map<String,Object> params){
		
		
		if(params.containsKey("MotgaCltalCtrctInfSgmtSeqNo")&&params.containsKey("CccInfSeqNo")){
			String MotgaCltalCtrctInfSgmtSeqNo = params.get("MotgaCltalCtrctInfSgmtSeqNo").toString();
			String CccInfSeqNo = params.get("CccInfSeqNo").toString();
			return pbcrsReportGuacccinfMapper.selectByPrimaryKey(MotgaCltalCtrctInfSgmtSeqNo,CccInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	 
	//相关还款责任人段列表
	public Map<String,Object> listEnrltrepinfsgmtPage(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportGserltrepinfsgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportGserltrepinfsgmtMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	} 
	
	public Map<String,Object> listEnrltrepinfsgmtPagem(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportGserltrepinfsgmtMapper.selectAllbyContCountm(params);

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
			list = pbcrsReportGserltrepinfsgmtMapper.selectAllbyContPagem( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	} 
	
	//相关还款责任人段详细
	public Object getEnrltrepymtinf(Map<String,Object> params){
		
		
		if(params.containsKey("RltRepymtInfSgmtSeqNo")&&params.containsKey("RltRepymtInfSeqNo")){
			String RltRepymtInfSgmtSeqNo = params.get("RltRepymtInfSgmtSeqNo").toString();
			String RltRepymtInfSeqNo = params.get("RltRepymtInfSeqNo").toString();
			return pbcrsReportGserltrepymtinfMapper.selectByPrimaryKey(RltRepymtInfSgmtSeqNo,RltRepymtInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	
	//授信额度信息段
	public Object getEnacctcredsgmt(Map<String,Object> params){

			if(params.containsKey("EnSecAcctInfSeqNo")){
				String EnAcctInfSeqNo = params.get("EnSecAcctInfSeqNo").toString();
				return pbcrsReportGuaracctcredsgmtMapper.selectByEnSecAcctInfSeqNo(EnAcctInfSeqNo);
			}
			else{
				return null;
			}
			
		}



	public Object getEnacctcredsgmtm(Map<String,Object> params){
		if(params.containsKey("EnSecAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnSecAcctInfSeqNo").toString();
			return pbcrsReportGuaracctcredsgmtMapper.selectByEnSecAcctInfMSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	//在保责任信息段
	public Object getEnoricreinfsgmt(Map<String,Object> params){

		if(params.containsKey("EnSecAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnSecAcctInfSeqNo").toString();
			return pbcrsReportEGuarltrepinfsgmtMapper.selectByEnSecAcctInfSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}



	public Object getEnoricreinfsgmtm(Map<String,Object> params){
		if(params.containsKey("EnSecAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnSecAcctInfSeqNo").toString();
			return pbcrsReportEGuarltrepinfsgmtMapper.selectByEnSecAcctInfMSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	



	
	//修改基础
	public int updateEnAcctBsSgmt(Map<String,Object> params) throws Exception{
		log.info("updateEnAcctBsSgmt:{}",JSON.toJSONString(params));
		
		if(params.containsKey("GuarAcctBsSgmtSeqNo")){

			PbcrsReportEnguaracctbssgmt enguaracctbssgmt = new PbcrsReportEnguaracctbssgmt();
			
			try {
				String type = params.get("type").toString();
				String GuarAcctBsSgmtSeqNo = params.get("GuarAcctBsSgmtSeqNo").toString();
			
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
					enguaracctbssgmt=(PbcrsReportEnguaracctbssgmt) Util.map2Object2(params,PbcrsReportEnguaracctbssgmt.class);
					
					PbcrsReportEnguaracctbssgmt oldenguaracctbssgmt = pbcrsReportEnguaracctbssgmtMapper.selectByPrimaryKey(enguaracctbssgmt.getGuarAcctBsSgmtSeqNo());
					pbcrsReportTraceService.insertTrace(oldenguaracctbssgmt, params, "Enguaracctbssgmt",GuarAcctBsSgmtSeqNo);
					if(type.equals(Constants.TYPE_SUB)){
						enguaracctbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
					}else{
						//enguaracctbssgmt.setRptDate(now);
						enguaracctbssgmt.setReportFlag(oldenguaracctbssgmt.getReportFlag());
					}
					
					int cnt =pbcrsReportEnguaracctbssgmtMapper.updateByPrimaryKey(enguaracctbssgmt);
					if(type.equals(Constants.TYPE_UPDSUB)){
						if(!oldenguaracctbssgmt.getAcctCode().equals(enguaracctbssgmt.getAcctCode())){//标示有改变需新增标示变更记录
							//int enaccinfidcaginfKey = pbcrsReportEnsecaccidcaginfMapper.getKey();
							PbcrsReportEnsecaccidcaginf pbcrsReportEnsecaccidcaginf = new PbcrsReportEnsecaccidcaginf();
							pbcrsReportEnsecaccidcaginf.setEnSecAcctIDCagsInfSeqNo(String.valueOf(GuarAcctBsSgmtSeqNo));
							pbcrsReportEnsecaccidcaginf.setInfRecType("441");
							pbcrsReportEnsecaccidcaginf.setOdBnesCode(oldenguaracctbssgmt.getAcctCode());
							pbcrsReportEnsecaccidcaginf.setNwBnesCode(enguaracctbssgmt.getAcctCode());
							pbcrsReportEnsecaccidcaginfMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo);
							pbcrsReportEnsecaccidcaginf.setReportFlag(Constants.REPORTFLAG_IN);
							cnt=pbcrsReportEnsecaccidcaginfMapper.insert(pbcrsReportEnsecaccidcaginf);
							if(cnt!=1)
								return cnt;						
						}
						//记录更正段
					
						
						PbcrsReportEnsecaccmdfbssgmt pbcrsReportEnsecaccmdfbssgmt = new PbcrsReportEnsecaccmdfbssgmt();
						pbcrsReportEnsecaccmdfbssgmt.setBsSgmtSeqNo(GuarAcctBsSgmtSeqNo);
						pbcrsReportEnsecaccmdfbssgmt.setBsSgmtSeqNo(GuarAcctBsSgmtSeqNo);
						
						pbcrsReportEnsecaccmdfbssgmt.setInfRecType("442");
						pbcrsReportEnsecaccmdfbssgmt.setModRecCode(enguaracctbssgmt.getAcctCode());
						pbcrsReportEnsecaccmdfbssgmt.setAcctType(enguaracctbssgmt.getAcctType());
						pbcrsReportEnsecaccmdfbssgmt.setRptDate(oldenguaracctbssgmt.getRptDate());//更正基础段时需要与库中的信息报告日期一致
						pbcrsReportEnsecaccmdfbssgmt.setMdfcSgmtCode("B");
						pbcrsReportEnsecaccmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						
//						PbcrsReportEnsecaccmdfsgmt pbcrsReportEnsecaccmdfsgmt = new PbcrsReportEnsecaccmdfsgmt();
//						pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtSeqNo(GuarAcctBsSgmtSeqNo);
//						pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtDetialSeqNo(GuarAcctBsSgmtSeqNo);
//						pbcrsReportEnsecaccmdfsgmtMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo);
						pbcrsReportEnsecaccmdfbssgmtMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo,"B");
//						cnt=pbcrsReportEnsecaccmdfsgmtMapper.insert(pbcrsReportEnsecaccmdfsgmt);
//						if(cnt!=1)
//							return cnt;
						cnt=pbcrsReportEnsecaccmdfbssgmtMapper.insert(pbcrsReportEnsecaccmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}

					investigationTypeService.changeUpdate("EnSecAcctInf", GuarAcctBsSgmtSeqNo, "Enguaracctbssgmt");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
		}else{
			return 0;
		}
		return 1;
		
		
	}
	
	//修改基本
	public int updateEnAcctBsInfSgmt(Map<String,Object> params) throws Exception{
		log.info("updateEnAcctBsInfSgmt:{}",JSON.toJSONString(params));
		if(params.containsKey("GuarAcctBsSgmtSeqNo")){

			PbcrsReportEnguaaccbsinfsgmt pbcrsReportEnguaaccbsinfsgmt = new PbcrsReportEnguaaccbsinfsgmt();
			
			try {
				String type = params.get("type").toString();
				String GuarAcctBsSgmtSeqNo = params.get("GuarAcctBsSgmtSeqNo").toString();
			    
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
					pbcrsReportEnguaaccbsinfsgmt=(PbcrsReportEnguaaccbsinfsgmt) Util.map2Object2(params,PbcrsReportEnguaaccbsinfsgmt.class);
					PbcrsReportEnguaracctbssgmt enguaracctbssgmt = pbcrsReportEnguaracctbssgmtMapper.selectByPrimaryKey(pbcrsReportEnguaaccbsinfsgmt.getGuarAcctBsInfSgmtSeqNo());
					PbcrsReportEnguaaccbsinfsgmt oldpbcrsReportEnguaaccbsinfsgmt = pbcrsReportEnguaaccbsinfsgmtMapper.selectByPrimaryKey(pbcrsReportEnguaaccbsinfsgmt.getGuarAcctBsInfSgmtSeqNo());
					pbcrsReportTraceService.insertTrace(oldpbcrsReportEnguaaccbsinfsgmt, params, "Enguaaccbsinfsgmt",GuarAcctBsSgmtSeqNo);
					if(type.equals(Constants.TYPE_SUB)){
						pbcrsReportEnguaaccbsinfsgmt.setReportFlag(Constants.REPORTFLAG_IN);
						enguaracctbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnguaracctbssgmtMapper.updateByPrimaryKey(enguaracctbssgmt);
					}else{
						pbcrsReportEnguaaccbsinfsgmt.setReportFlag(oldpbcrsReportEnguaaccbsinfsgmt.getReportFlag());
					}
					
					int cnt =pbcrsReportEnguaaccbsinfsgmtMapper.updateByPrimaryKey(pbcrsReportEnguaaccbsinfsgmt);
					if(cnt!=1)
						return cnt;
				    if(type.equals(Constants.TYPE_UPDSUB)){
						//记录更正段
						PbcrsReportEnsecaccmdfbssgmt pbcrsReportEnsecaccmdfbssgmt = new PbcrsReportEnsecaccmdfbssgmt();
						pbcrsReportEnsecaccmdfbssgmt.setBsSgmtSeqNo(GuarAcctBsSgmtSeqNo);					
						pbcrsReportEnsecaccmdfbssgmt.setInfRecType("442");
						pbcrsReportEnsecaccmdfbssgmt.setModRecCode(enguaracctbssgmt.getAcctCode());
						pbcrsReportEnsecaccmdfbssgmt.setAcctType(enguaracctbssgmt.getAcctType());
						pbcrsReportEnsecaccmdfbssgmt.setRptDate(enguaracctbssgmt.getRptDate());
						pbcrsReportEnsecaccmdfbssgmt.setMdfcSgmtCode("C");
						pbcrsReportEnsecaccmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						
//						PbcrsReportEnsecaccmdfsgmt pbcrsReportEnsecaccmdfsgmt = new PbcrsReportEnsecaccmdfsgmt();
//						pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtSeqNo(GuarAcctBsSgmtSeqNo);									
//						pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtDetialSeqNo(GuarAcctBsSgmtSeqNo);
//						pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtDetialSeqNo(GuarAcctBsSgmtSeqNo);
//						pbcrsReportEnsecaccmdfsgmtMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo);
						pbcrsReportEnsecaccmdfbssgmtMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo,"C");
//						cnt=pbcrsReportEnsecaccmdfsgmtMapper.insert(pbcrsReportEnsecaccmdfsgmt);
//						if(cnt!=1)
//							return cnt;
						cnt=pbcrsReportEnsecaccmdfbssgmtMapper.insert(pbcrsReportEnsecaccmdfbssgmt);
						if(cnt!=1)
							return cnt;
				    }

					investigationTypeService.changeUpdate("EnSecAcctInf", GuarAcctBsSgmtSeqNo, "Enguaaccbsinfsgmt");
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
		}else{
			return 0;
		}
		return 1;
		
		
		
		
	}
	
	/*//修改抵质押
	public int updateCccinf(Map<String,Object> params){
		if(params.containsKey("EnSecAcctInfSeqNo")){

			PbcrsReportEnguaaccbsinfsgmt pbcrsReportEnguaaccbsinfsgmt = new PbcrsReportEnguaaccbsinfsgmt();
			
			try {
				
				String EnSecAcctInfSeqNo = params.get("EnSecAcctInfSeqNo").toString();
				PbcrsReportEnsecacctinf enacctinf = pbcrsReportEnsecacctinfMapper.selectByPrimaryKey(EnSecAcctInfSeqNo);
				PbcrsReportEnsecacctinfm enacctinfm = pbcrsReportEnsecacctinfmMapper.selectByPrimaryKey(EnSecAcctInfSeqNo);
				PbcrsReportEnguaracctbssgmt enguaracctbssgmt = pbcrsReportEnguaracctbssgmtMapper.selectByPrimaryKey(enacctinf.getGuarAcctBsSgmtSeqNo());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				if(enacctinfm!=null){//修改
					pbcrsReportEnguaaccbsinfsgmt=(PbcrsReportEnguaaccbsinfsgmt) Util.map2Object2(params,PbcrsReportEnguaaccbsinfsgmt.class);
					PbcrsReportEnguaaccbsinfsgmt oldpbcrsReportEnguaaccbsinfsgmt = pbcrsReportEnguaaccbsinfsgmtMapper.selectByPrimaryKey(pbcrsReportEnguaaccbsinfsgmt.getGuarAcctBsInfSgmtSeqNo());
					int enguaracctbssgmtkey= pbcrsReportEnguaaccbsinfsgmtMapper.getKey();
					pbcrsReportEnguaaccbsinfsgmt.setGuarAcctBsInfSgmtSeqNo(String.valueOf(enguaracctbssgmtkey));
					enacctinfm.setGuarAcctBsInfSgmtSeqNo(pbcrsReportEnguaaccbsinfsgmt.getGuarAcctBsInfSgmtSeqNo());
					int cnt = pbcrsReportEnguaaccbsinfsgmtMapper.insert(pbcrsReportEnguaaccbsinfsgmt);
					if(cnt!=1)
						return cnt;
					cnt=pbcrsReportEnsecacctinfmMapper.updateByPrimaryKey(enacctinfm);
					if(cnt!=1)
						return cnt;
				
					//记录更正段
					PbcrsReportEnsecacctmdfc pbcrsReportEnsecacctmdfc = new PbcrsReportEnsecacctmdfc();
					int enacctinfmdfckey= pbcrsReportEnsecacctmdfcMapper.getKey();
					pbcrsReportEnsecacctmdfc.setEnSecAcctMdfcSeqNo(String.valueOf(enacctinfmdfckey));
					
					PbcrsReportEnsecaccmdfbssgmt pbcrsReportEnsecaccmdfbssgmt = new PbcrsReportEnsecaccmdfbssgmt();
					int enaccinfmdfbssgmtKey =pbcrsReportEnsecaccmdfbssgmtMapper.getKey();
					pbcrsReportEnsecaccmdfbssgmt.setBsSgmtSeqNo(String.valueOf(enaccinfmdfbssgmtKey));
					pbcrsReportEnsecaccmdfbssgmt.setBsSgmtSeqNo(String.valueOf(enaccinfmdfbssgmtKey));
					
					pbcrsReportEnsecaccmdfbssgmt.setInfRecType("442");
					pbcrsReportEnsecaccmdfbssgmt.setModRecCode(enguaracctbssgmt.getAcctCode());
					pbcrsReportEnsecaccmdfbssgmt.setAcctType(enguaracctbssgmt.getAcctType());
					pbcrsReportEnsecaccmdfbssgmt.setRptDate(now);
					pbcrsReportEnsecaccmdfbssgmt.setMdfcSgmtCode("C");
					
					PbcrsReportEnsecaccmdfsgmt pbcrsReportEnsecaccmdfsgmt = new PbcrsReportEnsecaccmdfsgmt();
					int enaccinfmdfsgmtKey=pbcrsReportEnsecaccmdfsgmtMapper.getKey();
					pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtSeqNo(String.valueOf(enaccinfmdfsgmtKey));
					pbcrsReportEnsecacctmdfc.setMdfcSgmtSeqNo(String.valueOf(enaccinfmdfsgmtKey));
					
					pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtDetialSeqNo(enguaracctbssgmt.getGuarAcctBsSgmtSeqNo());
					
					cnt=pbcrsReportEnsecaccmdfsgmtMapper.insert(pbcrsReportEnsecaccmdfsgmt);
					if(cnt!=1)
						return cnt;
					cnt=pbcrsReportEnsecaccmdfbssgmtMapper.insert(pbcrsReportEnsecaccmdfbssgmt);
					if(cnt!=1)
						return cnt;
					
					cnt=pbcrsReportEnsecacctmdfcMapper.insert(pbcrsReportEnsecacctmdfc);
					if(cnt!=1)
						return cnt;
					
					
				}else{//新增	
					enacctinfm = new  PbcrsReportEnsecacctinfm();
					enacctinfm.setEnSecAcctInfSeqNo(enacctinf.getEnSecAcctInfSeqNo());
					enacctinfm.setGuarAcctBsSgmtSeqNo(enacctinf.getGuarAcctBsSgmtSeqNo());
					enacctinfm.setGuarAcctBsInfSgmtSeqNo(enacctinf.getGuarAcctBsInfSgmtSeqNo());
					enacctinfm.setRltRepymtInfSgmtSeqNo(enacctinf.getRltRepymtInfSgmtSeqNo());
					enacctinfm.setGuarAcctCredSgmtSeqNo(enacctinf.getGuarAcctCredSgmtSeqNo());
					enacctinfm.setGuaMotCltlCtrInfSgmtSeqNo(enacctinf.getGuaMotCltlCtrInfSgmtSeqNo());
					enacctinfm.setGuarRltRepymtInfSgmtSeqNo(enacctinf.getGuarRltRepymtInfSgmtSeqNo());

					pbcrsReportEnguaaccbsinfsgmt=(PbcrsReportEnguaaccbsinfsgmt) Util.map2Object2(params,PbcrsReportEnguaaccbsinfsgmt.class);
					PbcrsReportEnguaaccbsinfsgmt oldpbcrsReportEnguaaccbsinfsgmt = pbcrsReportEnguaaccbsinfsgmtMapper.selectByPrimaryKey(pbcrsReportEnguaaccbsinfsgmt.getGuarAcctBsInfSgmtSeqNo());
					int enguaracctbssgmtkey= pbcrsReportEnguaaccbsinfsgmtMapper.getKey();
					pbcrsReportEnguaaccbsinfsgmt.setGuarAcctBsInfSgmtSeqNo(String.valueOf(enguaracctbssgmtkey));
					enacctinfm.setGuarAcctBsInfSgmtSeqNo(pbcrsReportEnguaaccbsinfsgmt.getGuarAcctBsInfSgmtSeqNo());
					int cnt = pbcrsReportEnguaaccbsinfsgmtMapper.insert(pbcrsReportEnguaaccbsinfsgmt);
					if(cnt!=1)
						return cnt;
					cnt=pbcrsReportEnsecacctinfmMapper.updateByPrimaryKey(enacctinfm);
					if(cnt!=1)
						return cnt;
				
					//记录更正段
					PbcrsReportEnsecacctmdfc pbcrsReportEnsecacctmdfc = new PbcrsReportEnsecacctmdfc();
					int enacctinfmdfckey= pbcrsReportEnsecacctmdfcMapper.getKey();
					pbcrsReportEnsecacctmdfc.setEnSecAcctMdfcSeqNo(String.valueOf(enacctinfmdfckey));
					
					PbcrsReportEnsecaccmdfbssgmt pbcrsReportEnsecaccmdfbssgmt = new PbcrsReportEnsecaccmdfbssgmt();
					int enaccinfmdfbssgmtKey =pbcrsReportEnsecaccmdfbssgmtMapper.getKey();
					pbcrsReportEnsecaccmdfbssgmt.setBsSgmtSeqNo(String.valueOf(enaccinfmdfbssgmtKey));
					pbcrsReportEnsecaccmdfbssgmt.setBsSgmtSeqNo(String.valueOf(enaccinfmdfbssgmtKey));
					
					pbcrsReportEnsecaccmdfbssgmt.setInfRecType("442");
					pbcrsReportEnsecaccmdfbssgmt.setModRecCode(enguaracctbssgmt.getAcctCode());
					pbcrsReportEnsecaccmdfbssgmt.setAcctType(enguaracctbssgmt.getAcctType());
					pbcrsReportEnsecaccmdfbssgmt.setRptDate(now);
					pbcrsReportEnsecaccmdfbssgmt.setMdfcSgmtCode("C");
					
					PbcrsReportEnsecaccmdfsgmt pbcrsReportEnsecaccmdfsgmt = new PbcrsReportEnsecaccmdfsgmt();
					int enaccinfmdfsgmtKey=pbcrsReportEnsecaccmdfsgmtMapper.getKey();
					pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtSeqNo(String.valueOf(enaccinfmdfsgmtKey));
					pbcrsReportEnsecacctmdfc.setMdfcSgmtSeqNo(String.valueOf(enaccinfmdfsgmtKey));
					
					pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtDetialSeqNo(enguaracctbssgmt.getGuarAcctBsSgmtSeqNo());
					
					cnt=pbcrsReportEnsecaccmdfsgmtMapper.insert(pbcrsReportEnsecaccmdfsgmt);
					if(cnt!=1)
						return cnt;
					cnt=pbcrsReportEnsecaccmdfbssgmtMapper.insert(pbcrsReportEnsecaccmdfbssgmt);
					if(cnt!=1)
						return cnt;
					
					cnt=pbcrsReportEnsecacctmdfcMapper.insert(pbcrsReportEnsecacctmdfc);
					if(cnt!=1)
						return cnt;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else{
			return 0;
		}
		return 1;
		
		if(params.containsKey("EnSecAcctInfSeqNo")){

			PbcrsReportCccinf enCccinf = new PbcrsReportCccinf();
			
			try {
				
				String EnAcctInfSeqNo = params.get("EnSecAcctInfSeqNo").toString();
				PbcrsReportEnacctinf enacctinf = pbcrsReportEnacctinfMapper.selectByPrimaryKey(EnAcctInfSeqNo);
				PbcrsReportEnacctinfm enacctinfm = pbcrsReportEnacctinfmMapper.selectByPrimaryKey(EnAcctInfSeqNo);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				if(enacctinfm!=null){//修改
					enCccinf=(PbcrsReportCccinf) Util.map2Object2(params,PbcrsReportCccinf.class);
					PbcrsReportMotcltctrinfsgmt motCltCtrInfSgmt=pbcrsReportMotCltCtrInfSgmtMapper.selectByPrimaryKey(enCccinf.getMotgaCltalCtrctInfSgmtSeqNo());
					PbcrsReportEnacctbssgmt enAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(enacctinfm.getAcctBsSgmtSeqNo());
					
					
					int enacctinfkey= pbcrsReportCccinfMapper.getKey();
					int motgaCltalCtrctInfSgmtkey = pbcrsReportMotCltCtrInfSgmtMapper.getKey();
					
					
					//enCccinf.setCccInfSeqNo(String.valueOf(enacctinfkey));
					
					List<PbcrsReportCccinf> enCccinfs = pbcrsReportCccinfMapper.selectByMotgaCltalCtrctInfSgmtSeqNo(enCccinf.getMotgaCltalCtrctInfSgmtSeqNo());
					for(PbcrsReportCccinf tempenCccinf:enCccinfs){
						if(tempenCccinf.getCccInfSeqNo().equals(enCccinf.getCccInfSeqNo())){
							enCccinf.setCccInfSeqNo(String.valueOf(enacctinfkey));
							enCccinf.setMotgaCltalCtrctInfSgmtSeqNo(String.valueOf(motgaCltalCtrctInfSgmtkey));
							int cnt =pbcrsReportCccinfMapper.insert(enCccinf);
							if(cnt!=1)
								return cnt;
						}else{
							tempenCccinf.setMotgaCltalCtrctInfSgmtSeqNo(String.valueOf(motgaCltalCtrctInfSgmtkey));
							int cnt =pbcrsReportCccinfMapper.insert(tempenCccinf);
							if(cnt!=1)
								return cnt;
						}
					}
					motCltCtrInfSgmt.setMotgaCltalCtrctInfSgmtSeqNo(String.valueOf(motgaCltalCtrctInfSgmtkey));
					int cnt = pbcrsReportMotCltCtrInfSgmtMapper.insert(motCltCtrInfSgmt);
					if(cnt!=1)
						return cnt;
					
					enacctinfm.setMotgaCltalCtrctInfSgmtSeqNo(motCltCtrInfSgmt.getMotgaCltalCtrctInfSgmtSeqNo());

					cnt=pbcrsReportEnacctinfmMapper.updateByPrimaryKey(enacctinfm);
					if(cnt!=1)
						return cnt;
					
					
					//记录更正段
					PbcrsReportEnacctinfmdfc pbcrsReportEnacctinfmdfc = new PbcrsReportEnacctinfmdfc();
					int enacctinfmdfckey= pbcrsReportEnacctinfmdfcMapper.getKey();
					pbcrsReportEnacctinfmdfc.setEnAcctInfMdfcSeqNo(String.valueOf(enacctinfmdfckey));
					
					PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
					int enaccinfmdfbssgmtKey =pbcrsReportEnaccinfmdfbssgmtMapper.getKey();
					pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(String.valueOf(enaccinfmdfbssgmtKey));
					pbcrsReportEnacctinfmdfc.setBsSgmtSeqNo(String.valueOf(enaccinfmdfbssgmtKey));
					
					pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
					pbcrsReportEnaccinfmdfbssgmt.setModRecCode(enAcctBsSgmt.getAcctCode());
					pbcrsReportEnaccinfmdfbssgmt.setAcctType(enAcctBsSgmt.getAcctType());
					pbcrsReportEnaccinfmdfbssgmt.setRptDate(now);
					pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("E");
					
					PbcrsReportEnaccinfmdfsgmt pbcrsReportEnaccinfmdfsgmt = new PbcrsReportEnaccinfmdfsgmt();
					int enaccinfmdfsgmtKey=pbcrsReportEnaccinfmdfsgmtMapper.getKey();
					pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtSeqNo(String.valueOf(enaccinfmdfsgmtKey));
					pbcrsReportEnacctinfmdfc.setMdfcSgmtSeqNo(String.valueOf(enaccinfmdfsgmtKey));
					
					pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtDetailSeqNo(motCltCtrInfSgmt.getMotgaCltalCtrctInfSgmtSeqNo());
					
					cnt=pbcrsReportEnaccinfmdfsgmtMapper.insert(pbcrsReportEnaccinfmdfsgmt);
					if(cnt!=1)
						return cnt;
					cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
					if(cnt!=1)
						return cnt;
					
					cnt=pbcrsReportEnacctinfmdfcMapper.insert(pbcrsReportEnacctinfmdfc);
					if(cnt!=1)
						return cnt;
					
					
				}else{//新增	
					enacctinfm = new  PbcrsReportEnacctinfm();
					enacctinfm.setEnAcctInfSeqNo(enacctinf.getEnAcctInfSeqNo());
					enacctinfm.setAcctBsSgmtSeqNo(enacctinf.getAcctBsSgmtSeqNo());
					enacctinfm.setAcctBsInfSgmtSeqNo(enacctinf.getAcctBsInfSgmtSeqNo());
					enacctinfm.setRltRepymtInfSgmtSeqNo(enacctinf.getRltRepymtInfSgmtSeqNo());
					enacctinfm.setMotgaCltalCtrctInfSgmtSeqNo(enacctinf.getMotgaCltalCtrctInfSgmtSeqNo());
					enacctinfm.setAcctCredSgmtSeqNo(enacctinf.getAcctCredSgmtSeqNo());
					enacctinfm.setOrigCreditorInfSgmtSeqNo(enacctinf.getOrigCreditorInfSgmtSeqNo());
					enacctinfm.setActLbltyInfSgmtSeqNo(enacctinf.getActLbltyInfSgmtSeqNo());
					enacctinfm.setAcctSpecTrstDspnSgmtSeqNo(enacctinf.getAcctSpecTrstDspnSgmtSeqNo());
					enCccinf=(PbcrsReportCccinf) Util.map2Object2(params,PbcrsReportCccinf.class);
					PbcrsReportMotcltctrinfsgmt motCltCtrInfSgmt=pbcrsReportMotCltCtrInfSgmtMapper.selectByPrimaryKey(enCccinf.getMotgaCltalCtrctInfSgmtSeqNo());
					PbcrsReportEnacctbssgmt enAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(enacctinfm.getAcctBsSgmtSeqNo());
					
					
					int enacctinfkey= pbcrsReportCccinfMapper.getKey();
					int motgaCltalCtrctInfSgmtkey = pbcrsReportMotCltCtrInfSgmtMapper.getKey();
					
					
					//enCccinf.setCccInfSeqNo(String.valueOf(enacctinfkey));
					
					List<PbcrsReportCccinf> enCccinfs = pbcrsReportCccinfMapper.selectByMotgaCltalCtrctInfSgmtSeqNo(enCccinf.getMotgaCltalCtrctInfSgmtSeqNo());
					for(PbcrsReportCccinf tempenCccinf:enCccinfs){
						if(tempenCccinf.getCccInfSeqNo().equals(enCccinf.getCccInfSeqNo())){
							enCccinf.setCccInfSeqNo(String.valueOf(enacctinfkey));
							enCccinf.setMotgaCltalCtrctInfSgmtSeqNo(String.valueOf(motgaCltalCtrctInfSgmtkey));
							int cnt =pbcrsReportCccinfMapper.insert(enCccinf);
							if(cnt!=1)
								return cnt;
						}else{
							tempenCccinf.setMotgaCltalCtrctInfSgmtSeqNo(String.valueOf(motgaCltalCtrctInfSgmtkey));
							int cnt =pbcrsReportCccinfMapper.insert(tempenCccinf);
							if(cnt!=1)
								return cnt;
						}
					}
					motCltCtrInfSgmt.setMotgaCltalCtrctInfSgmtSeqNo(String.valueOf(motgaCltalCtrctInfSgmtkey));
					int cnt = pbcrsReportMotCltCtrInfSgmtMapper.insert(motCltCtrInfSgmt);
					if(cnt!=1)
						return cnt;
					
					enacctinfm.setMotgaCltalCtrctInfSgmtSeqNo(motCltCtrInfSgmt.getMotgaCltalCtrctInfSgmtSeqNo());

					cnt=pbcrsReportEnacctinfmMapper.insert(enacctinfm);
					if(cnt!=1)
						return cnt;
					
					
					//记录更正段
					PbcrsReportEnacctinfmdfc pbcrsReportEnacctinfmdfc = new PbcrsReportEnacctinfmdfc();
					int enacctinfmdfckey= pbcrsReportEnacctinfmdfcMapper.getKey();
					pbcrsReportEnacctinfmdfc.setEnAcctInfMdfcSeqNo(String.valueOf(enacctinfmdfckey));
					
					PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
					int enaccinfmdfbssgmtKey =pbcrsReportEnaccinfmdfbssgmtMapper.getKey();
					pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(String.valueOf(enaccinfmdfbssgmtKey));
					pbcrsReportEnacctinfmdfc.setBsSgmtSeqNo(String.valueOf(enaccinfmdfbssgmtKey));
					
					pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
					pbcrsReportEnaccinfmdfbssgmt.setModRecCode(enAcctBsSgmt.getAcctCode());
					pbcrsReportEnaccinfmdfbssgmt.setAcctType(enAcctBsSgmt.getAcctType());
					pbcrsReportEnaccinfmdfbssgmt.setRptDate(now);
					pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("E");
					
					PbcrsReportEnaccinfmdfsgmt pbcrsReportEnaccinfmdfsgmt = new PbcrsReportEnaccinfmdfsgmt();
					int enaccinfmdfsgmtKey=pbcrsReportEnaccinfmdfsgmtMapper.getKey();
					pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtSeqNo(String.valueOf(enaccinfmdfsgmtKey));
					pbcrsReportEnacctinfmdfc.setMdfcSgmtSeqNo(String.valueOf(enaccinfmdfsgmtKey));
					
					pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtDetailSeqNo(motCltCtrInfSgmt.getMotgaCltalCtrctInfSgmtSeqNo());
					
					cnt=pbcrsReportEnaccinfmdfsgmtMapper.insert(pbcrsReportEnaccinfmdfsgmt);
					if(cnt!=1)
						return cnt;
					cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
					if(cnt!=1)
						return cnt;
					
					cnt=pbcrsReportEnacctinfmdfcMapper.insert(pbcrsReportEnacctinfmdfc);
					if(cnt!=1)
						return cnt;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else{
			return 0;
		}
		return 1;
		
		
	}*/

	
	//修改相关还款责任人段详细
	public int updateEnrltrepymtinf(Map<String,Object> params) throws Exception{
		log.info("updateEnrltrepymtinf:{}",JSON.toJSONString(params));
		
		if(params.containsKey("GuarAcctBsSgmtSeqNo")){

			PbcrsReportGserltrepymtinf enrltrepymtinf = new PbcrsReportGserltrepymtinf();
			
			try {
				String type = params.get("type").toString();
				String GuarAcctBsSgmtSeqNo = params.get("GuarAcctBsSgmtSeqNo").toString();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
					enrltrepymtinf=(PbcrsReportGserltrepymtinf) Util.map2Object2(params,PbcrsReportGserltrepymtinf.class);
					PbcrsReportGserltrepymtinf enrltrepymtinfold= pbcrsReportGserltrepymtinfMapper.selectByPrimaryKey(enrltrepymtinf.getRltRepymtInfSgmtSeqNo(), enrltrepymtinf.getRltRepymtInfSeqNo());
					PbcrsReportEnguaracctbssgmt enguaracctbssgmt = pbcrsReportEnguaracctbssgmtMapper.selectByPrimaryKey(enrltrepymtinf.getRltRepymtInfSgmtSeqNo());
					pbcrsReportTraceService.insertTrace(enrltrepymtinfold, params, "Gserltrepymtinf",GuarAcctBsSgmtSeqNo);
				    if(type.equals(Constants.TYPE_SUB)){
				    	enrltrepymtinf.setReportFlag(Constants.REPORTFLAG_IN);
				    	enguaracctbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
				    	pbcrsReportEnguaracctbssgmtMapper.updateByPrimaryKey(enguaracctbssgmt);
				    }else{
				    	enrltrepymtinf.setReportFlag(enrltrepymtinfold.getReportFlag());
				    }
					
					int cnt = pbcrsReportGserltrepymtinfMapper.updateByPrimaryKey(enrltrepymtinf);				
					if(type.equals(Constants.TYPE_UPDSUB)){
						//记录更正段								
						PbcrsReportEnsecaccmdfbssgmt pbcrsReportEnsecaccmdfbssgmt = new PbcrsReportEnsecaccmdfbssgmt();
						pbcrsReportEnsecaccmdfbssgmt.setBsSgmtSeqNo(GuarAcctBsSgmtSeqNo);					
						pbcrsReportEnsecaccmdfbssgmt.setInfRecType("442");
						pbcrsReportEnsecaccmdfbssgmt.setModRecCode(enguaracctbssgmt.getAcctCode());
						pbcrsReportEnsecaccmdfbssgmt.setAcctType(enguaracctbssgmt.getAcctType());
						pbcrsReportEnsecaccmdfbssgmt.setRptDate(enguaracctbssgmt.getRptDate());
						pbcrsReportEnsecaccmdfbssgmt.setMdfcSgmtCode("E");
						pbcrsReportEnsecaccmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						
//						PbcrsReportEnsecaccmdfsgmt pbcrsReportEnsecaccmdfsgmt = new PbcrsReportEnsecaccmdfsgmt();
//						pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtSeqNo(GuarAcctBsSgmtSeqNo);									
//						pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtDetialSeqNo(GuarAcctBsSgmtSeqNo);
//						
//						pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtDetialSeqNo(GuarAcctBsSgmtSeqNo);
//						pbcrsReportEnsecaccmdfsgmtMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo);
						pbcrsReportEnsecaccmdfbssgmtMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo,"E");
//						cnt=pbcrsReportEnsecaccmdfsgmtMapper.insert(pbcrsReportEnsecaccmdfsgmt);
//						if(cnt!=1)
//							return cnt;
						cnt=pbcrsReportEnsecaccmdfbssgmtMapper.insert(pbcrsReportEnsecaccmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}
					investigationTypeService.changeUpdate("EnSecAcctInf", GuarAcctBsSgmtSeqNo, "Gserltrepinfsgmt");
					investigationTypeService.changeUpdate("EnSecAcctInf", GuarAcctBsSgmtSeqNo, "Gserltrepymtinf");	
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
		}else{
			return 0;
		}
		return 1;
		
		
	}
	

	//修改授信额度信息段pbcrsReportGuaracctcredsgmtMapper
	public int updateGuaracctcredsgmt(Map<String,Object> params) throws Exception{
		log.info("updateGuaracctcredsgmt:{}",JSON.toJSONString(params));
		if(params.containsKey("GuarAcctBsSgmtSeqNo")){

			PbcrsReportGuaracctcredsgmt pbcrsReportGuaracctcredsgmt = new PbcrsReportGuaracctcredsgmt();
			
			try {
				String type = params.get("type").toString();
				String GuarAcctBsSgmtSeqNo = params.get("GuarAcctBsSgmtSeqNo").toString();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
					pbcrsReportGuaracctcredsgmt=(PbcrsReportGuaracctcredsgmt) Util.map2Object2(params,PbcrsReportGuaracctcredsgmt.class);
					PbcrsReportGuaracctcredsgmt pbcrsReportGuaracctcredsgmtold=pbcrsReportGuaracctcredsgmtMapper.selectByPrimaryKey(pbcrsReportGuaracctcredsgmt.getGuarAcctCredSgmtSeqNo());
					PbcrsReportEnguaracctbssgmt enguaracctbssgmt = pbcrsReportEnguaracctbssgmtMapper.selectByPrimaryKey(pbcrsReportGuaracctcredsgmt.getGuarAcctCredSgmtSeqNo());
					
					pbcrsReportTraceService.insertTrace(pbcrsReportGuaracctcredsgmtold, params, "Gserltrepymtinf",GuarAcctBsSgmtSeqNo);
					if(type.equals(Constants.TYPE_SUB)){
						pbcrsReportGuaracctcredsgmt.setReportFlag(Constants.REPORTFLAG_IN);
						enguaracctbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnguaracctbssgmtMapper.updateByPrimaryKey(enguaracctbssgmt);
					}else{
						pbcrsReportGuaracctcredsgmt.setReportFlag(pbcrsReportGuaracctcredsgmtold.getReportFlag());
					}
					
					int cnt = pbcrsReportGuaracctcredsgmtMapper.updateByPrimaryKey(pbcrsReportGuaracctcredsgmt);
					if(cnt!=1)
						return cnt;
					if(type.equals(Constants.TYPE_UPDSUB)){
						//记录更正段

						PbcrsReportEnsecaccmdfbssgmt pbcrsReportEnsecaccmdfbssgmt = new PbcrsReportEnsecaccmdfbssgmt();
						pbcrsReportEnsecaccmdfbssgmt.setBsSgmtSeqNo(GuarAcctBsSgmtSeqNo);					
						pbcrsReportEnsecaccmdfbssgmt.setInfRecType("442");
						pbcrsReportEnsecaccmdfbssgmt.setModRecCode(enguaracctbssgmt.getAcctCode());
						pbcrsReportEnsecaccmdfbssgmt.setAcctType(enguaracctbssgmt.getAcctType());
						pbcrsReportEnsecaccmdfbssgmt.setRptDate(enguaracctbssgmt.getRptDate());
						pbcrsReportEnsecaccmdfbssgmt.setMdfcSgmtCode("G");
						pbcrsReportEnsecaccmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						
//						PbcrsReportEnsecaccmdfsgmt pbcrsReportEnsecaccmdfsgmt = new PbcrsReportEnsecaccmdfsgmt();
//						pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtSeqNo(GuarAcctBsSgmtSeqNo);										
//						pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtDetialSeqNo(GuarAcctBsSgmtSeqNo);					
//						pbcrsReportEnsecaccmdfsgmtMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo);
						pbcrsReportEnsecaccmdfbssgmtMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo,"G");
//						cnt=pbcrsReportEnsecaccmdfsgmtMapper.insert(pbcrsReportEnsecaccmdfsgmt);
//						if(cnt!=1)
//							return cnt;
						cnt=pbcrsReportEnsecaccmdfbssgmtMapper.insert(pbcrsReportEnsecaccmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}

					investigationTypeService.changeUpdate("EnSecAcctInf", GuarAcctBsSgmtSeqNo, "Guaracctcredsgmt");
					
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
		}else{
			return 0;
		}
		return 1;
		
		
		
		
	}
	
	
	//在保责任信息段pbcrsReportGuaracctcredsgmtMapper
	public int updateGuarltrepinfsgmt(Map<String,Object> params) throws Exception{
           log.info("updateGuarltrepinfsgmt:{}",JSON.toJSONString(params));
			if(params.containsKey("GuarAcctBsSgmtSeqNo")){

				PbcrsReportEGuarltrepinfsgmt pbcrsReportGuarltrepinfsgmt = new PbcrsReportEGuarltrepinfsgmt();
				
				try {
					String type = params.get("type").toString();
					String GuarAcctBsSgmtSeqNo = params.get("GuarAcctBsSgmtSeqNo").toString();

					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String now = sdf.format(new Date());
					
						pbcrsReportGuarltrepinfsgmt=(PbcrsReportEGuarltrepinfsgmt) Util.map2Object2(params,PbcrsReportEGuarltrepinfsgmt.class);
						PbcrsReportEGuarltrepinfsgmt pbcrsReportGuarltrepinfsgmtold =pbcrsReportEGuarltrepinfsgmtMapper.selectByPrimaryKey(pbcrsReportGuarltrepinfsgmt.getGuarRltRepymtInfSgmtSeqNo());
						PbcrsReportEnguaracctbssgmt enguaracctbssgmt = pbcrsReportEnguaracctbssgmtMapper.selectByPrimaryKey(pbcrsReportGuarltrepinfsgmt.getGuarRltRepymtInfSgmtSeqNo());
						
						pbcrsReportTraceService.insertTrace(pbcrsReportGuarltrepinfsgmtold, params, "Guarltrepinfsgmt",GuarAcctBsSgmtSeqNo);
						if(type.equals(Constants.TYPE_SUB)){
							pbcrsReportGuarltrepinfsgmt.setReportFlag(Constants.REPORTFLAG_IN);
							enguaracctbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
							pbcrsReportEnguaracctbssgmtMapper.updateByPrimaryKey(enguaracctbssgmt);
						}else{
							pbcrsReportGuarltrepinfsgmt.setReportFlag(pbcrsReportGuarltrepinfsgmtold.getReportFlag());
						}						
						int cnt = pbcrsReportEGuarltrepinfsgmtMapper.updateByPrimaryKey(pbcrsReportGuarltrepinfsgmt);
					
						if(type.equals(Constants.TYPE_UPDSUB)){
							//记录更正段
							
							PbcrsReportEnsecaccmdfbssgmt pbcrsReportEnsecaccmdfbssgmt = new PbcrsReportEnsecaccmdfbssgmt();
							pbcrsReportEnsecaccmdfbssgmt.setBsSgmtSeqNo(GuarAcctBsSgmtSeqNo);
							
							pbcrsReportEnsecaccmdfbssgmt.setInfRecType("442");
							pbcrsReportEnsecaccmdfbssgmt.setModRecCode(enguaracctbssgmt.getAcctCode());
							pbcrsReportEnsecaccmdfbssgmt.setAcctType(enguaracctbssgmt.getAcctType());
							pbcrsReportEnsecaccmdfbssgmt.setRptDate(enguaracctbssgmt.getRptDate());
							pbcrsReportEnsecaccmdfbssgmt.setMdfcSgmtCode("D");
							pbcrsReportEnsecaccmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
							
//							PbcrsReportEnsecaccmdfsgmt pbcrsReportEnsecaccmdfsgmt = new PbcrsReportEnsecaccmdfsgmt();
//							pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtSeqNo(GuarAcctBsSgmtSeqNo);
//							pbcrsReportEnsecaccmdfsgmt.setMdfcSgmtDetialSeqNo(GuarAcctBsSgmtSeqNo);
//							pbcrsReportEnsecaccmdfsgmtMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo);
							pbcrsReportEnsecaccmdfbssgmtMapper.deleteByPrimaryKey(GuarAcctBsSgmtSeqNo,"D");
//							cnt=pbcrsReportEnsecaccmdfsgmtMapper.insert(pbcrsReportEnsecaccmdfsgmt);
//							if(cnt!=1)
//								return cnt;
							cnt=pbcrsReportEnsecaccmdfbssgmtMapper.insert(pbcrsReportEnsecaccmdfbssgmt);
							if(cnt!=1)
								return cnt;
						}

						
						investigationTypeService.changeUpdate("EnSecAcctInf", GuarAcctBsSgmtSeqNo, "EGuarltrepinfsgmt");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new Exception();
				} 
			}else{
				return 0;
			}
			return 1;
			
			
			
			
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
		pbcrsReportEnguaracctbssgmtMapper.updateByMap(map);
		pbcrsReportEnguaaccbsinfsgmtMapper.updateByMap(map);
		pbcrsReportGserltrepinfsgmtMapper.updateByMap(map);
		pbcrsReportGserltrepymtinfMapper.updateByMap(map);
//		pbcrsReportEGuamotcltctrsgmtMapper.updateByMap(map);
//		pbcrsReportGuacccinfMapper.updateByMap(map);
		pbcrsReportGuaracctcredsgmtMapper.updateByMap(map);
		pbcrsReportEGuarltrepinfsgmtMapper.updateByMap(map);
		return 1;
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
		
		if(params.containsKey("GuarAcctBsSgmtSeqNo")){
			String GuarAcctBsSgmtSeqNo = params.get("GuarAcctBsSgmtSeqNo").toString();
			//查询基础段信息
			PbcrsReportEnguaracctbssgmt enguaracctbssgmt = pbcrsReportEnguaracctbssgmtMapper.selectByPrimaryKey(GuarAcctBsSgmtSeqNo);
			//插入删除
			PbcrsReportEnsecaccdelsgmt EnSecAccDlt = new PbcrsReportEnsecaccdelsgmt();
			EnSecAccDlt.setEnSecAcctEntDelSgmtSeqNo(GuarAcctBsSgmtSeqNo);
			EnSecAccDlt.setInfRecType("444");
			EnSecAccDlt.setDelRecCode(enguaracctbssgmt.getAcctCode());
			EnSecAccDlt.setSourceSys(enguaracctbssgmt.getSourceSys());
			EnSecAccDlt.setReportFlag(Constants.REPORTFLAG_IN);
			int dltRt = pbcrsReportEnsecaccdelsgmtMapper.insert(EnSecAccDlt);
			if(dltRt > 0){
				params.put("IsDel", "1");
				int crt =  pbcrsReportEnguaracctbssgmtMapper.updateByIsDel(params);
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


	
	public int deleteEnGuaAccBsInfSgmt(Map<String, Object> param) throws Exception {
		String seqno = param.get("EnSecAcctInfSeqNo").toString();
		String startDate = param.get("startDate")==null?null:param.get("startDate").toString();
		 PbcrsReportEnguaaccbsinfsgmt pbcrsReportEnguaaccbsinfsgmt = pbcrsReportEnguaaccbsinfsgmtMapper.selectByPrimaryKey(seqno);
		//设置删除标志为1,表示该段已经被删除
		 pbcrsReportEnguaaccbsinfsgmt.setParagraphDel("1");
		int updateCount = pbcrsReportEnguaaccbsinfsgmtMapper.updateByPrimaryKey(pbcrsReportEnguaaccbsinfsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//基础段：取业务标识码和报送日期
		PbcrsReportEnguaracctbssgmt pbcrsReportEnguaracctbssgmt = pbcrsReportEnguaracctbssgmtMapper.selectByPrimaryKey(seqno);
		String acctCode = pbcrsReportEnguaracctbssgmt.getAcctCode();
		String rptDate = pbcrsReportEnguaracctbssgmt.getRptDate();
		String sourceSys = pbcrsReportEnguaracctbssgmt.getSourceSys();
		Map<String,String> params = new HashMap<String, String>();
		params.put("EnSecAcctDelSeqNo",seqno);
		params.put("DelSgmtCode","C");
		pbcrsReportEnsecacctdelMapper.deleteByPrimaryKey(params);
		PbcrsReportEnsecacctdel pbcrsReportEnsInacctdel = new PbcrsReportEnsecacctdel();
		pbcrsReportEnsInacctdel.setEnSecAcctDelSeqNo(seqno);
		pbcrsReportEnsInacctdel.setDelRecType("413");
		pbcrsReportEnsInacctdel.setDelRecCode(acctCode);
		pbcrsReportEnsInacctdel.setDelSgmtCode("C");
		pbcrsReportEnsInacctdel.setReportFlag("0");
		Date etl_date = DateUtils.parseDate(rptDate, "yyyy-MM-dd");
		pbcrsReportEnsInacctdel.setEtl_Date(etl_date);
		pbcrsReportEnsInacctdel.setSourceSys(sourceSys);
		pbcrsReportEnsInacctdel.setDelStartDate(startDate);
		pbcrsReportEnsInacctdel.setDelEndDate(rptDate);
		int insertCount = pbcrsReportEnsecacctdelMapper.insert(pbcrsReportEnsInacctdel);
		if(insertCount != 1){
			throw new Exception();
		}
		return 1;
	}
	
	public int deleteGuaRltRepInfSgmt(Map<String, Object> param) throws Exception {
		String seqno = param.get("EnSecAcctInfSeqNo").toString();
		String startDate = param.get("startDate")==null?null:param.get("startDate").toString();
		 PbcrsReportEGuarltrepinfsgmt pbcrsReportEGuarltrepinfsgmt = pbcrsReportEGuarltrepinfsgmtMapper.selectByPrimaryKey(seqno);
		//设置删除标志为1,表示该段已经被删除
		 pbcrsReportEGuarltrepinfsgmt.setParagraphDel("1");
		int updateCount = pbcrsReportEGuarltrepinfsgmtMapper.updateByPrimaryKey(pbcrsReportEGuarltrepinfsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//基础段：取业务标识码和报送日期
		PbcrsReportEnguaracctbssgmt pbcrsReportEnguaracctbssgmt = pbcrsReportEnguaracctbssgmtMapper.selectByPrimaryKey(seqno);
		String acctCode = pbcrsReportEnguaracctbssgmt.getAcctCode();
		String rptDate = pbcrsReportEnguaracctbssgmt.getRptDate();
		String sourceSys = pbcrsReportEnguaracctbssgmt.getSourceSys();
		Map<String,String> params = new HashMap<String, String>();
		params.put("EnSecAcctDelSeqNo",seqno);
		params.put("DelSgmtCode","D");
		pbcrsReportEnsecacctdelMapper.deleteByPrimaryKey(params);
		PbcrsReportEnsecacctdel pbcrsReportEnsInacctdel = new PbcrsReportEnsecacctdel();
		pbcrsReportEnsInacctdel.setEnSecAcctDelSeqNo(seqno);
		pbcrsReportEnsInacctdel.setDelRecType("443");
		pbcrsReportEnsInacctdel.setDelRecCode(acctCode);
		pbcrsReportEnsInacctdel.setDelSgmtCode("D");
		pbcrsReportEnsInacctdel.setReportFlag("0");
		Date etl_date = DateUtils.parseDate(rptDate, "yyyy-MM-dd");
		pbcrsReportEnsInacctdel.setEtl_Date(etl_date);
		pbcrsReportEnsInacctdel.setSourceSys(sourceSys);
		pbcrsReportEnsInacctdel.setDelStartDate(startDate);
		pbcrsReportEnsInacctdel.setDelEndDate(rptDate);
		int insertCount = pbcrsReportEnsecacctdelMapper.insert(pbcrsReportEnsInacctdel);
		if(insertCount != 1){
			throw new Exception();
		}
		return 1;
	}

}
