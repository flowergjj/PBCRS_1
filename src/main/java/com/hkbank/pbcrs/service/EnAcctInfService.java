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
import com.hkbank.pbcrs.mapper.PbcrsReportActlbltyinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCccinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccinfidcaginfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccinfmdfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccinfmdfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccspetrssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctbsinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctcredsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfdelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfentdelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfmdfcMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEncagoftrdinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnmotcltctrinfsgmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnoricreinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnrltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnrltrepymtinfMapper;
import com.hkbank.pbcrs.model.PbcrsReportAccspetrsdspsgmt;
import com.hkbank.pbcrs.model.PbcrsReportAcctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportActlbltyinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportCagoftrdinf;
import com.hkbank.pbcrs.model.PbcrsReportCccinf;
import com.hkbank.pbcrs.model.PbcrsReportEnaccinfidcaginf;
import com.hkbank.pbcrs.model.PbcrsReportEnaccinfmdfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnaccspetrssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctcredsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctinfdel;
import com.hkbank.pbcrs.model.PbcrsReportEnacctinfentdel;
import com.hkbank.pbcrs.model.PbcrsReportEncagoftrdinf;
import com.hkbank.pbcrs.model.PbcrsReportEnmotcltctrinfsgm;
import com.hkbank.pbcrs.model.PbcrsReportEnoricreinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnrltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportInacctdel;


@Service
public class EnAcctInfService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(EnAcctInfService.class);

	
	@Autowired
	PbcrsReportEnacctinfMapper pbcrsReportEnacctinfMapper; //主

	@Autowired
	PbcrsReportEnacctbssgmtMapper pbcrsReportEnAcctBsSgmtMapper; //基础段
	
	@Autowired
	PbcrsReportEnacctbsinfsgmtMapper pbcrsReportEnacctbsinfsgmtMapper; //基本信息段
	
	@Autowired
	PbcrsReportEnmotcltctrinfsgmMapper pbcrsReportEnmotcltctrinfsgmMapper; //抵质押列表
	
	@Autowired
	PbcrsReportCccinfMapper pbcrsReportCccinfMapper; //抵质押合同号
	
	@Autowired
	PbcrsReportEnrltrepinfsgmtMapper pbcrsReportEnrltrepinfsgmtMapper; //相关还款责任人段列表
	
	@Autowired
	PbcrsReportEnrltrepymtinfMapper pbcrsReportEnrltrepymtinfMapper; //相关还款责任人段详细
	
	@Autowired
	PbcrsReportEnacctcredsgmtMapper pbcrsReportEnacctcredsgmtMapper; //授信额度信息段
	
	@Autowired
	PbcrsReportEnoricreinfsgmtMapper pbcrsReportEnoricreinfsgmtMapper; //初始债权说明段
	
	@Autowired
	PbcrsReportActlbltyinfsgmtMapper pbcrsReportActlbltyinfsgmtMapper; //还款表现信息段
	
	@Autowired
	PbcrsReportEnaccspetrssgmtMapper pbcrsReportEnaccspetrssgmtMapper; //特定交易说明信息段列表
	
	@Autowired
	PbcrsReportEncagoftrdinfMapper pbcrsReportEncagoftrdinfMapper; //特定交易说明信息段详细
	
	@Autowired
	PbcrsReportEnacctinfmMapper pbcrsReportEnacctinfmMapper; //修改
	
	@Autowired
	PbcrsReportEnacctinfmdfcMapper pbcrsReportEnacctinfmdfcMapper; //企业借贷账户更正请求记录
	
	@Autowired
	PbcrsReportEnaccinfmdfbssgmtMapper pbcrsReportEnaccinfmdfbssgmtMapper; //企业借贷账户信息更正段基本
	
	@Autowired
	PbcrsReportEnaccinfmdfsgmtMapper pbcrsReportEnaccinfmdfsgmtMapper; //企业借贷账户信息更正段
	
	@Autowired
	PbcrsReportEnaccinfidcaginfMapper pbcrsReportEnaccinfidcaginfMapper; //企业借贷账户信息标识变更请求记录
	
	
	@Autowired
	PbcrsReportTraceService pbcrsReportTraceService;//痕迹表
	@Autowired
	InvestigationTypeService investigationTypeService;//自动生成更新端
	@Autowired
	PbcrsReportEnacctinfentdelMapper pbcrsReportEnacctinfentdelMapper;//整笔删除
	
	@Autowired
	private PbcrsReportEnacctinfdelMapper pbcrsReportEnacctinfdelMapper;//按段删除
	//列表
	public Map<String,Object> listPage(Map<String,Object> params){
			
			int pageNo = MapUtils.getIntValue(params, "page");
			int pageSize = MapUtils.getIntValue(params, "rows");
			
			int skip = (pageNo - 1) * pageSize;

			String countKey;
			/* 2. 计算总记录数 */
			int total = pbcrsReportEnAcctBsSgmtMapper.selectAllbyContCount(params);

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
				list = pbcrsReportEnAcctBsSgmtMapper.selectAllbyContPage( params);
			}
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("total", total);
			rslt.put("rows", list);

			return rslt;
			
		} 
	
	//基础段
	public Object getEnAcctBsSgmt(Map<String,Object> params){
		if(params.containsKey("EnAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnAcctInfSeqNo").toString();
			return pbcrsReportEnAcctBsSgmtMapper.selectByEnAcctInfSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	public Object getEnAcctBsSgmtm(Map<String,Object> params){
		if(params.containsKey("EnAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnAcctInfSeqNo").toString();
			return pbcrsReportEnAcctBsSgmtMapper.selectByEnAcctInfMSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	//基本信息
	public Object getEnacctbsinfsgmt(Map<String,Object> params){
		if(params.containsKey("EnAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnAcctInfSeqNo").toString();
			return pbcrsReportEnacctbsinfsgmtMapper.selectByEnAcctInfSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	public Object getEnacctbsinfsgmtm(Map<String,Object> params){
		if(params.containsKey("EnAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnAcctInfSeqNo").toString();
			return pbcrsReportEnacctbsinfsgmtMapper.selectByEnAcctInfMSeqNo(EnAcctInfSeqNo);
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
		int total = pbcrsReportEnmotcltctrinfsgmMapper.getByPageCount(params);

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
			list = pbcrsReportEnmotcltctrinfsgmMapper.getByPage( params);
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
		int total = pbcrsReportEnmotcltctrinfsgmMapper.selectAllbyContCountm(params);

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
			list = pbcrsReportEnmotcltctrinfsgmMapper.selectAllbyContPagem( params);
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
			return pbcrsReportCccinfMapper.selectByPrimaryKey(MotgaCltalCtrctInfSgmtSeqNo,CccInfSeqNo);
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
		int total = pbcrsReportEnrltrepinfsgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportEnrltrepinfsgmtMapper.selectAllbyContPage( params);
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
		int total = pbcrsReportEnrltrepinfsgmtMapper.selectAllbyContCountm(params);

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
			list = pbcrsReportEnrltrepinfsgmtMapper.selectAllbyContPagem( params);
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
			return pbcrsReportEnrltrepymtinfMapper.selectByPrimaryKey(RltRepymtInfSgmtSeqNo,RltRepymtInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	
	//授信额度信息段
	public Object getEnacctcredsgmt(Map<String,Object> params){

			if(params.containsKey("EnAcctInfSeqNo")){
				String EnAcctInfSeqNo = params.get("EnAcctInfSeqNo").toString();
				return pbcrsReportEnacctcredsgmtMapper.selectByEnAcctInfSeqNo(EnAcctInfSeqNo);
			}
			else{
				return null;
			}
			
		}



	public Object getEnacctcredsgmtm(Map<String,Object> params){
		if(params.containsKey("EnAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnAcctInfSeqNo").toString();
			return pbcrsReportEnacctcredsgmtMapper.selectByEnAcctInfMSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	//初始债权说明段
	public Object getEnoricreinfsgmt(Map<String,Object> params){

		if(params.containsKey("EnAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnAcctInfSeqNo").toString();
			return pbcrsReportEnoricreinfsgmtMapper.selectByEnAcctInfSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}



	public Object getEnoricreinfsgmtm(Map<String,Object> params){
		if(params.containsKey("EnAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnAcctInfSeqNo").toString();
			return pbcrsReportEnoricreinfsgmtMapper.selectByEnAcctInfMSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	

	
	//还款表现信息段
	public Object getActlbltyinfsgmt(Map<String,Object> params){

		if(params.containsKey("EnAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnAcctInfSeqNo").toString();
			return pbcrsReportActlbltyinfsgmtMapper.selectByEnAcctInfSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}



	public Object getActlbltyinfsgmtm(Map<String,Object> params){
		if(params.containsKey("EnAcctInfSeqNo")){
			String EnAcctInfSeqNo = params.get("EnAcctInfSeqNo").toString();
			return pbcrsReportActlbltyinfsgmtMapper.selectByEnAcctInfMSeqNo(EnAcctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	

	//特定交易说明信息段列表
	public Map<String,Object> listEnaccspetrssgmtPage(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportEnaccspetrssgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportEnaccspetrssgmtMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	} 
	
	public Map<String,Object> listEnaccspetrssgmtPagem(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportEnaccspetrssgmtMapper.selectAllbyContCountm(params);

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
			list = pbcrsReportEnaccspetrssgmtMapper.selectAllbyContPagem( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	} 
	
	

	//特定交易说明信息段详细
	public Object getEncagoftrdinf(Map<String,Object> params){
		if(params.containsKey("AcctSpecTrstDspnSgmtSeqNo")&&params.containsKey("CagOfTrdInfSeqNo")){
			String AcctSpecTrstDspnSgmtSeqNo = params.get("AcctSpecTrstDspnSgmtSeqNo").toString();
			String CagOfTrdInfSeqNo = params.get("CagOfTrdInfSeqNo").toString();
			return pbcrsReportEncagoftrdinfMapper.selectByPrimaryKey(AcctSpecTrstDspnSgmtSeqNo,CagOfTrdInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	//修改基础
	public int updateEnAcctBsSgmt(Map<String,Object> params) throws Exception{
		log.info("updateEnAcctBsSgmt:{}",JSON.toJSONString(params));
		
		if(params.containsKey("AcctBsSgmtSeqNo")){

			PbcrsReportEnacctbssgmt enAcctBsSgmt = new PbcrsReportEnacctbssgmt();
			
			try {
				String type = params.get("type").toString();
				String AcctBsSgmtSeqNo = params.get("AcctBsSgmtSeqNo").toString();
				/*PbcrsReportEnacctinf enacctinf = pbcrsReportEnacctinfMapper.selectByPrimaryKey(EnAcctInfSeqNo);
				PbcrsReportEnacctinfm enacctinfm = pbcrsReportEnacctinfmMapper.selectByPrimaryKey(EnAcctInfSeqNo);*/
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				//if(enacctinfm!=null){//修改
					enAcctBsSgmt=(PbcrsReportEnacctbssgmt) Util.map2Object2(params,PbcrsReportEnacctbssgmt.class);
					PbcrsReportEnacctbssgmt oldenAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(enAcctBsSgmt.getAcctBsSgmtSeqNo());
					
					pbcrsReportTraceService.insertTrace(oldenAcctBsSgmt, params, "EnAcctBsSgmt",AcctBsSgmtSeqNo);
					if(type.equals(Constants.TYPE_SUB)){
						enAcctBsSgmt.setReportFlag(Constants.REPORTFLAG_IN);
					}else{
						//enAcctBsSgmt.setRptDate(now);
						enAcctBsSgmt.setReportFlag(oldenAcctBsSgmt.getReportFlag());
					}
					
					
					int cnt =pbcrsReportEnAcctBsSgmtMapper.updateByPrimaryKey(enAcctBsSgmt);
					if(cnt!=1)
						return cnt;
					
				/*	int enAcctBsSgmtkey= pbcrsReportEnAcctBsSgmtMapper.getKey();
					enAcctBsSgmt.setAcctBsSgmtSeqNo(String.valueOf(enAcctBsSgmtkey));
					enacctinfm.setAcctBsSgmtSeqNo(enAcctBsSgmt.getAcctBsSgmtSeqNo());
					int cnt = pbcrsReportEnAcctBsSgmtMapper.insert(enAcctBsSgmt);
					if(cnt!=1)
						return cnt;
					cnt=pbcrsReportEnacctinfmMapper.updateByPrimaryKey(enacctinfm);
					if(cnt!=1)
						return cnt;*/
					if(type.equals(Constants.TYPE_UPDSUB)){
						if(!oldenAcctBsSgmt.getAcctCode().equals(enAcctBsSgmt.getAcctCode())){//标示有改变需新增标示变更记录
							/*int enaccinfidcaginfKey = pbcrsReportEnaccinfidcaginfMapper.getKey();*/
							PbcrsReportEnaccinfidcaginf pbcrsReportEnaccinfidcaginf = new PbcrsReportEnaccinfidcaginf();
							pbcrsReportEnaccinfidcaginf.setEnAcctInfIDCagsInfSeqNo(String.valueOf(AcctBsSgmtSeqNo));
							pbcrsReportEnaccinfidcaginf.setInfRecType("411");
							pbcrsReportEnaccinfidcaginf.setOdBnesCode(oldenAcctBsSgmt.getAcctCode());
							pbcrsReportEnaccinfidcaginf.setNwBnesCode(enAcctBsSgmt.getAcctCode());
							pbcrsReportEnaccinfidcaginf.setOrgCode(enAcctBsSgmt.getMngmtOrgCode());
							pbcrsReportEnaccinfidcaginfMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo);
							pbcrsReportEnaccinfidcaginf.setReportFlag(Constants.REPORTFLAG_IN);
							cnt=pbcrsReportEnaccinfidcaginfMapper.insert(pbcrsReportEnaccinfidcaginf);
							if(cnt!=1){
								return cnt;
							}

						}
						//记录更正段
						PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
						pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(String.valueOf(AcctBsSgmtSeqNo));
						
						pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
						pbcrsReportEnaccinfmdfbssgmt.setModRecCode(enAcctBsSgmt.getAcctCode());
						pbcrsReportEnaccinfmdfbssgmt.setAcctType(enAcctBsSgmt.getAcctType());
						pbcrsReportEnaccinfmdfbssgmt.setRptDate(oldenAcctBsSgmt.getRptDate());//更正基础段时需要与库中的信息报告日期一致
						pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("B");
						pbcrsReportEnaccinfmdfbssgmt.setOrgCode(enAcctBsSgmt.getMngmtOrgCode());
						pbcrsReportEnaccinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						
//						PbcrsReportEnaccinfmdfsgmt pbcrsReportEnaccinfmdfsgmt = new PbcrsReportEnaccinfmdfsgmt();
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtSeqNo(String.valueOf(AcctBsSgmtSeqNo));
//						
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtDetailSeqNo(AcctBsSgmtSeqNo);
//						
//						pbcrsReportEnaccinfmdfsgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo);
//						cnt=pbcrsReportEnaccinfmdfsgmtMapper.insert(pbcrsReportEnaccinfmdfsgmt);
//						if(cnt!=1)
							//return cnt;
						pbcrsReportEnaccinfmdfbssgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo,"B");
						cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}

					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "EnAcctBsSgmt");
				/*	cnt=pbcrsReportEnacctinfmdfcMapper.insert(pbcrsReportEnacctinfmdfc);
					if(cnt!=1)
						return cnt;*/
					
					
				
				
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
		
		if(params.containsKey("AcctBsSgmtSeqNo")){

			PbcrsReportEnacctbsinfsgmt enEnAcctBsInfSgmt = new PbcrsReportEnacctbsinfsgmt();
			
			try {
				String type = params.get("type").toString();
				String AcctBsSgmtSeqNo = params.get("AcctBsSgmtSeqNo").toString();
			
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
			
					enEnAcctBsInfSgmt=(PbcrsReportEnacctbsinfsgmt) Util.map2Object2(params,PbcrsReportEnacctbsinfsgmt.class);
					PbcrsReportEnacctbsinfsgmt oldenAcctBsSgmt = pbcrsReportEnacctbsinfsgmtMapper.selectByPrimaryKey(AcctBsSgmtSeqNo);
					pbcrsReportTraceService.insertTrace(oldenAcctBsSgmt, params, "EnAcctBsInfSgmt",AcctBsSgmtSeqNo);
					PbcrsReportEnacctbssgmt enAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(AcctBsSgmtSeqNo);
					
					if(type.equals(Constants.TYPE_SUB)){
						enEnAcctBsInfSgmt.setReportFlag(Constants.REPORTFLAG_IN);
						enAcctBsSgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnAcctBsSgmtMapper.updateByPrimaryKey(enAcctBsSgmt);
					}else{
						enEnAcctBsInfSgmt.setReportFlag(oldenAcctBsSgmt.getReportFlag());
					}
					
					int cnt = pbcrsReportEnacctbsinfsgmtMapper.updateByPrimaryKey(enEnAcctBsInfSgmt);
					if(cnt!=1)
						return cnt;
					
					
					if(type.equals(Constants.TYPE_UPDSUB)){
						//记录更正段

						PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
						pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(AcctBsSgmtSeqNo);					
						pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
						pbcrsReportEnaccinfmdfbssgmt.setModRecCode(enAcctBsSgmt.getAcctCode());
						pbcrsReportEnaccinfmdfbssgmt.setAcctType(enAcctBsSgmt.getAcctType());
						pbcrsReportEnaccinfmdfbssgmt.setRptDate(enAcctBsSgmt.getRptDate());
						pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("C");
						pbcrsReportEnaccinfmdfbssgmt.setOrgCode(enAcctBsSgmt.getMngmtOrgCode());
						pbcrsReportEnaccinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						
//						PbcrsReportEnaccinfmdfsgmt pbcrsReportEnaccinfmdfsgmt = new PbcrsReportEnaccinfmdfsgmt();
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtSeqNo(String.valueOf(AcctBsSgmtSeqNo));					
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtDetailSeqNo(AcctBsSgmtSeqNo);
//						
//						pbcrsReportEnaccinfmdfsgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo);
//						cnt=pbcrsReportEnaccinfmdfsgmtMapper.insert(pbcrsReportEnaccinfmdfsgmt);
//						if(cnt!=1)
//							return cnt;
						pbcrsReportEnaccinfmdfbssgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo,"C");
						cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}

					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "Enacctbsinfsgmt");
					
				
				
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
	
	//修改抵质押
	public int updateCccinf(Map<String,Object> params) throws Exception{
		log.info("updateCccinf:{}",JSON.toJSONString(params));
		
		if(params.containsKey("AcctBsSgmtSeqNo")){

			PbcrsReportCccinf enCccinf = new PbcrsReportCccinf();
			
			try {
				String type = params.get("type").toString();
				String AcctBsSgmtSeqNo = params.get("AcctBsSgmtSeqNo").toString();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
			
					enCccinf=(PbcrsReportCccinf) Util.map2Object2(params,PbcrsReportCccinf.class);
					PbcrsReportCccinf enCccinfold =pbcrsReportCccinfMapper.selectByPrimaryKey(enCccinf.getMotgaCltalCtrctInfSgmtSeqNo(), enCccinf.getCccInfSeqNo());
					
					PbcrsReportEnmotcltctrinfsgm enmotCltCtrInfSgmt=pbcrsReportEnmotcltctrinfsgmMapper.selectByPrimaryKey(enCccinf.getMotgaCltalCtrctInfSgmtSeqNo());
					PbcrsReportEnacctbssgmt enAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(enCccinf.getMotgaCltalCtrctInfSgmtSeqNo());
					
					pbcrsReportTraceService.insertTrace(enCccinfold, params, "Cccinf",AcctBsSgmtSeqNo);
					if(type.equals(Constants.TYPE_SUB)){
						enCccinf.setReportFlag(Constants.REPORTFLAG_IN);
						enAcctBsSgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnAcctBsSgmtMapper.updateByPrimaryKey(enAcctBsSgmt);
						
					}else{
						enCccinf.setReportFlag(enCccinfold.getReportFlag());
					}
					
					int cnt =pbcrsReportCccinfMapper.updateByPrimaryKey(enCccinf);									
					
					if(type.equals(Constants.TYPE_UPDSUB)){
					//记录更正段
				
					PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
					int enaccinfmdfbssgmtKey =pbcrsReportEnaccinfmdfbssgmtMapper.getKey();
					pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(String.valueOf(enaccinfmdfbssgmtKey));
					pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
					pbcrsReportEnaccinfmdfbssgmt.setModRecCode(enAcctBsSgmt.getAcctCode());
					pbcrsReportEnaccinfmdfbssgmt.setAcctType(enAcctBsSgmt.getAcctType());
					pbcrsReportEnaccinfmdfbssgmt.setRptDate(enAcctBsSgmt.getRptDate());
					pbcrsReportEnaccinfmdfbssgmt.setOrgCode(enAcctBsSgmt.getMngmtOrgCode());
					pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("E");					
//					PbcrsReportEnaccinfmdfsgmt pbcrsReportEnaccinfmdfsgmt = new PbcrsReportEnaccinfmdfsgmt();
					
//					pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtSeqNo(String.valueOf(AcctBsSgmtSeqNo));
					pbcrsReportEnaccinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
					
//					pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtDetailSeqNo(AcctBsSgmtSeqNo);
					pbcrsReportEnaccinfmdfbssgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo,"E");
//					pbcrsReportEnaccinfmdfsgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo);
//					cnt=pbcrsReportEnaccinfmdfsgmtMapper.insert(pbcrsReportEnaccinfmdfsgmt);
//					if(cnt!=1)
//						return cnt;
					
					cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
					if(cnt!=1)
						return cnt;
				}
		
					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "MotCltCtrInfSgmt");
					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "Cccinf");
		
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

	//修改相关还款责任人段详细
	public int updateEnrltrepymtinf(Map<String,Object> params) throws Exception{
		log.info("updateEnrltrepymtinf:{}",JSON.toJSONString(params));
		
		if(params.containsKey("AcctBsSgmtSeqNo")){

			PbcrsReportEnrltrepymtinf enrltrepymtinf = new PbcrsReportEnrltrepymtinf();
			
			try {
				String type = params.get("type").toString();
				String AcctBsSgmtSeqNo = params.get("AcctBsSgmtSeqNo").toString();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
					enrltrepymtinf=(PbcrsReportEnrltrepymtinf) Util.map2Object2(params,PbcrsReportEnrltrepymtinf.class);
					PbcrsReportEnrltrepymtinf enrltrepymtinfold =pbcrsReportEnrltrepymtinfMapper.selectByPrimaryKey(enrltrepymtinf.getRltRepymtInfSgmtSeqNo(), enrltrepymtinf.getRltRepymtInfSeqNo());
					pbcrsReportTraceService.insertTrace(enrltrepymtinfold, params, "Enrltrepymtinf",AcctBsSgmtSeqNo);
					PbcrsReportEnacctbssgmt enAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(AcctBsSgmtSeqNo);
					if(type.equals(Constants.TYPE_SUB)){
						enrltrepymtinf.setReportFlag(Constants.REPORTFLAG_IN);
						enAcctBsSgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnAcctBsSgmtMapper.updateByPrimaryKey(enAcctBsSgmt);
					}else{
						enrltrepymtinf.setReportFlag(enrltrepymtinfold.getReportFlag());
					}
					
					int cnt=pbcrsReportEnrltrepymtinfMapper.updateByPrimaryKey(enrltrepymtinf);
					if(cnt!=1)
						return cnt;	
					
					if(type.equals(Constants.TYPE_UPDSUB)){
						//记录更正段
						
						PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
						pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(AcctBsSgmtSeqNo);					
						pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
						pbcrsReportEnaccinfmdfbssgmt.setModRecCode(enAcctBsSgmt.getAcctCode());
						pbcrsReportEnaccinfmdfbssgmt.setAcctType(enAcctBsSgmt.getAcctType());
						pbcrsReportEnaccinfmdfbssgmt.setRptDate(enAcctBsSgmt.getRptDate());
						pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("D");
						pbcrsReportEnaccinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnaccinfmdfbssgmt.setOrgCode(enAcctBsSgmt.getMngmtOrgCode());
//						PbcrsReportEnaccinfmdfsgmt pbcrsReportEnaccinfmdfsgmt = new PbcrsReportEnaccinfmdfsgmt();
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtSeqNo(AcctBsSgmtSeqNo);					
//						
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtDetailSeqNo(AcctBsSgmtSeqNo);
						pbcrsReportEnaccinfmdfbssgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo,"D");
//						pbcrsReportEnaccinfmdfsgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo);
//						cnt=pbcrsReportEnaccinfmdfsgmtMapper.insert(pbcrsReportEnaccinfmdfsgmt);
//						if(cnt!=1)
//							return cnt;
						cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}

					
					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "Enrltrepinfsgmt");
					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "Enrltrepymtinf");
					
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
	

	//修改授信额度信息段
	public int updateEnacctcredsgmt(Map<String,Object> params) throws Exception{
		log.info("updateEnacctcredsgmt:{}",JSON.toJSONString(params));
		
		if(params.containsKey("AcctBsSgmtSeqNo")){

			PbcrsReportEnacctcredsgmt enacctcredsgmt = new PbcrsReportEnacctcredsgmt();
			
			try {
				String type = params.get("type").toString();
				String AcctBsSgmtSeqNo = params.get("AcctBsSgmtSeqNo").toString();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
					enacctcredsgmt=(PbcrsReportEnacctcredsgmt) Util.map2Object2(params,PbcrsReportEnacctcredsgmt.class);
					PbcrsReportEnacctcredsgmt enacctcredsgmtold =pbcrsReportEnacctcredsgmtMapper.selectByPrimaryKey(enacctcredsgmt.getAcctCredSgmtSeqNo());
					PbcrsReportEnacctbssgmt enAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(AcctBsSgmtSeqNo);
					
					pbcrsReportTraceService.insertTrace(enacctcredsgmtold, params, "Enacctcredsgmt",AcctBsSgmtSeqNo);
					if(type.equals(Constants.TYPE_SUB)){
						enacctcredsgmt.setReportFlag(Constants.REPORTFLAG_IN);
						enAcctBsSgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnAcctBsSgmtMapper.updateByPrimaryKey(enAcctBsSgmt);
					}else{
						enacctcredsgmt.setReportFlag(enacctcredsgmtold.getReportFlag());
					}
					
					int cnt = pbcrsReportEnacctcredsgmtMapper.updateByPrimaryKey(enacctcredsgmt);
					if(cnt!=1)
						return cnt;
					
					if(type.equals(Constants.TYPE_UPDSUB)){
						PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
						pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(AcctBsSgmtSeqNo);
											
						pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
						pbcrsReportEnaccinfmdfbssgmt.setModRecCode(enAcctBsSgmt.getAcctCode());
						pbcrsReportEnaccinfmdfbssgmt.setAcctType(enAcctBsSgmt.getAcctType());
						pbcrsReportEnaccinfmdfbssgmt.setRptDate(enAcctBsSgmt.getRptDate());
						pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("F");
						pbcrsReportEnaccinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnaccinfmdfbssgmt.setOrgCode(enAcctBsSgmt.getMngmtOrgCode());
//						PbcrsReportEnaccinfmdfsgmt pbcrsReportEnaccinfmdfsgmt = new PbcrsReportEnaccinfmdfsgmt();
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtSeqNo(AcctBsSgmtSeqNo);
//						
//						
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtDetailSeqNo(AcctBsSgmtSeqNo);
						pbcrsReportEnaccinfmdfbssgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo,"F");
//						pbcrsReportEnaccinfmdfsgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo);
//						cnt=pbcrsReportEnaccinfmdfsgmtMapper.insert(pbcrsReportEnaccinfmdfsgmt);
//						if(cnt!=1)
//							return cnt;
						cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}

					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "Enacctcredsgmt");
					
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
	
	
	//修改初始债权说明段
	public int updateEnoricreinfsgmt(Map<String,Object> params) throws Exception{
		log.info("updateEnoricreinfsgmt:{}",JSON.toJSONString(params));

		
		if(params.containsKey("AcctBsSgmtSeqNo")){

			PbcrsReportEnoricreinfsgmt enoricreinfsgmt = new PbcrsReportEnoricreinfsgmt();
			
			try {
				String type = params.get("type").toString();
				String AcctBsSgmtSeqNo = params.get("AcctBsSgmtSeqNo").toString();
			
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
					enoricreinfsgmt=(PbcrsReportEnoricreinfsgmt) Util.map2Object2(params,PbcrsReportEnoricreinfsgmt.class);
					PbcrsReportEnoricreinfsgmt enoricreinfsgmtold = pbcrsReportEnoricreinfsgmtMapper.selectByPrimaryKey(enoricreinfsgmt.getOrigCreditorInfSgmtSeqNo());
					PbcrsReportEnacctbssgmt enAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(AcctBsSgmtSeqNo);
					pbcrsReportTraceService.insertTrace(enoricreinfsgmtold, params, "Enoricreinfsgmt",AcctBsSgmtSeqNo);
					if(type.equals(Constants.TYPE_SUB)){
						enoricreinfsgmt.setReportFlag(Constants.REPORTFLAG_IN);
						enAcctBsSgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnAcctBsSgmtMapper.updateByPrimaryKey(enAcctBsSgmt);
					}else{
						enoricreinfsgmt.setReportFlag(enoricreinfsgmtold.getReportFlag());
					}
					
					int cnt = pbcrsReportEnoricreinfsgmtMapper.updateByPrimaryKey(enoricreinfsgmt);
					if(cnt!=1)
						return cnt;
				
					if(type.equals(Constants.TYPE_UPDSUB)){
						//记录更正段
						
						PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
						pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(AcctBsSgmtSeqNo);
											
						pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
						pbcrsReportEnaccinfmdfbssgmt.setModRecCode(enAcctBsSgmt.getAcctCode());
						pbcrsReportEnaccinfmdfbssgmt.setAcctType(enAcctBsSgmt.getAcctType());
						pbcrsReportEnaccinfmdfbssgmt.setRptDate(enAcctBsSgmt.getRptDate());
						pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("G");
						pbcrsReportEnaccinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnaccinfmdfbssgmt.setOrgCode(enAcctBsSgmt.getMngmtOrgCode());
//						PbcrsReportEnaccinfmdfsgmt pbcrsReportEnaccinfmdfsgmt = new PbcrsReportEnaccinfmdfsgmt();
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtSeqNo(AcctBsSgmtSeqNo);										
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtDetailSeqNo(AcctBsSgmtSeqNo);
						pbcrsReportEnaccinfmdfbssgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo,"G");
//						pbcrsReportEnaccinfmdfsgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo);
//						cnt=pbcrsReportEnaccinfmdfsgmtMapper.insert(pbcrsReportEnaccinfmdfsgmt);
//						if(cnt!=1)
//							return cnt;
						cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}

					
					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "Enoricreinfsgmt");
				
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
	
	//还款表现信息段
	public int updateActlbltyinfsgmt(Map<String,Object> params) throws Exception{
		log.info("updateActlbltyinfsgmt:{}",JSON.toJSONString(params));
		
		if(params.containsKey("AcctBsSgmtSeqNo")){

			PbcrsReportActlbltyinfsgmt actlbltyinfsgmt = new PbcrsReportActlbltyinfsgmt();
			
			try {
				String type = params.get("type").toString();
				String AcctBsSgmtSeqNo = params.get("AcctBsSgmtSeqNo").toString();
			
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
					actlbltyinfsgmt=(PbcrsReportActlbltyinfsgmt) Util.map2Object2(params,PbcrsReportActlbltyinfsgmt.class);
					PbcrsReportActlbltyinfsgmt actlbltyinfsgmtold =pbcrsReportActlbltyinfsgmtMapper.selectByPrimaryKey(actlbltyinfsgmt.getActLbltyInfSgmtSeqNo());
					PbcrsReportEnacctbssgmt enAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(AcctBsSgmtSeqNo);
					pbcrsReportTraceService.insertTrace(actlbltyinfsgmtold, params, "Actlbltyinfsgmt",AcctBsSgmtSeqNo);
					if(type.equals(Constants.TYPE_SUB)){
						actlbltyinfsgmt.setReportFlag(Constants.REPORTFLAG_IN);
						enAcctBsSgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnAcctBsSgmtMapper.updateByPrimaryKey(enAcctBsSgmt);
					}else{
						actlbltyinfsgmt.setReportFlag(actlbltyinfsgmtold.getReportFlag());
					}					
					
					int cnt = pbcrsReportActlbltyinfsgmtMapper.updateByPrimaryKey(actlbltyinfsgmt);
					if(cnt!=1)
						return cnt;

					if(type.equals(Constants.TYPE_UPDSUB)){
						//记录更正段
						
						PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
						pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(AcctBsSgmtSeqNo);										
						pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
						pbcrsReportEnaccinfmdfbssgmt.setModRecCode(enAcctBsSgmt.getAcctCode());
						pbcrsReportEnaccinfmdfbssgmt.setAcctType(enAcctBsSgmt.getAcctType());
						pbcrsReportEnaccinfmdfbssgmt.setRptDate(enAcctBsSgmt.getRptDate());
						pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("H");
						pbcrsReportEnaccinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnaccinfmdfbssgmt.setOrgCode(enAcctBsSgmt.getMngmtOrgCode());
//						PbcrsReportEnaccinfmdfsgmt pbcrsReportEnaccinfmdfsgmt = new PbcrsReportEnaccinfmdfsgmt();
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtSeqNo(AcctBsSgmtSeqNo);
//											
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtDetailSeqNo(AcctBsSgmtSeqNo);
//						
						pbcrsReportEnaccinfmdfbssgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo,"H");
//						pbcrsReportEnaccinfmdfsgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo);
//						
//						cnt=pbcrsReportEnaccinfmdfsgmtMapper.insert(pbcrsReportEnaccinfmdfsgmt);
//						if(cnt!=1)
//							return cnt;
						cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}

					
					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "Actlbltyinfsgmt");
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
	
	
	//修改特定交易说明信息段详细
	public int updateEncagoftrdinf(Map<String,Object> params) throws Exception{
		log.info("updateEncagoftrdinf:{}",JSON.toJSONString(params));
		
		if(params.containsKey("AcctBsSgmtSeqNo")){

			PbcrsReportEncagoftrdinf encagoftrdinf = new PbcrsReportEncagoftrdinf();
			
			try {
				String type = params.get("type").toString();
				String AcctBsSgmtSeqNo = params.get("AcctBsSgmtSeqNo").toString();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
			
					encagoftrdinf=(PbcrsReportEncagoftrdinf) Util.map2Object2(params,PbcrsReportEncagoftrdinf.class);
					PbcrsReportEncagoftrdinf encagoftrdinfold =pbcrsReportEncagoftrdinfMapper.selectByPrimaryKey(encagoftrdinf.getAcctSpecTrstDspnSgmtSeqNo(), encagoftrdinf.getCagOfTrdInfSeqNo());
					
					PbcrsReportEnacctbssgmt enAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(AcctBsSgmtSeqNo);
					
					pbcrsReportTraceService.insertTrace(encagoftrdinfold, params, "Encagoftrdinf",AcctBsSgmtSeqNo);
					if(type.equals(Constants.TYPE_SUB)){
						encagoftrdinf.setReportFlag(Constants.REPORTFLAG_IN);
						enAcctBsSgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnAcctBsSgmtMapper.updateByPrimaryKey(enAcctBsSgmt);
					}else{
						encagoftrdinf.setReportFlag(encagoftrdinfold.getReportFlag());
					}
					
					int cnt =pbcrsReportEncagoftrdinfMapper.updateByPrimaryKey(encagoftrdinf);
					
					if(type.equals(Constants.TYPE_UPDSUB)){
						//记录更正段
						PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
						pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(AcctBsSgmtSeqNo);
						pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
						pbcrsReportEnaccinfmdfbssgmt.setModRecCode(enAcctBsSgmt.getAcctCode());
						pbcrsReportEnaccinfmdfbssgmt.setAcctType(enAcctBsSgmt.getAcctType());
						pbcrsReportEnaccinfmdfbssgmt.setRptDate(enAcctBsSgmt.getRptDate());
						pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("I");
						pbcrsReportEnaccinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnaccinfmdfbssgmt.setOrgCode(enAcctBsSgmt.getMngmtOrgCode());
//						PbcrsReportEnaccinfmdfsgmt pbcrsReportEnaccinfmdfsgmt = new PbcrsReportEnaccinfmdfsgmt();
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtSeqNo(AcctBsSgmtSeqNo);
//						pbcrsReportEnaccinfmdfsgmt.setMdfcSgmtDetailSeqNo(AcctBsSgmtSeqNo);
						
						pbcrsReportEnaccinfmdfbssgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo,"I");
//						pbcrsReportEnaccinfmdfsgmtMapper.deleteByPrimaryKey(AcctBsSgmtSeqNo);
//						
//						cnt=pbcrsReportEnaccinfmdfsgmtMapper.insert(pbcrsReportEnaccinfmdfsgmt);
//						if(cnt!=1)
//							return cnt;
						cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}

					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "Enaccspetrssgmt");
					investigationTypeService.changeUpdate("EnAcctInf", AcctBsSgmtSeqNo, "Encagoftrdinf");

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
	
	public int deleteEnAcctBsInfSgmt(Map<String, Object> param) throws Exception {
		String seqno = param.get("EnAcctInfSeqNo").toString();
		String startDate = param.get("startDate")==null?null:param.get("startDate").toString();
		PbcrsReportEnacctbsinfsgmt pbcrsReportEnacctbsinfsgmt = pbcrsReportEnacctbsinfsgmtMapper.selectByPrimaryKey(seqno);
		//设置删除标志为1,表示该段已经被删除
		pbcrsReportEnacctbsinfsgmt.setParagraphDel("1");
		int updateCount = pbcrsReportEnacctbsinfsgmtMapper.updateByPrimaryKey(pbcrsReportEnacctbsinfsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//基础段：取业务标识码和报送日期
		PbcrsReportEnacctbssgmt pbcrsReportEnacctbssgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(seqno);
		String acctCode = pbcrsReportEnacctbssgmt.getAcctCode();
		String rptDate = pbcrsReportEnacctbssgmt.getRptDate();
		String sourceSys = pbcrsReportEnacctbssgmt.getSourceSys();
		Map<String,String> params = new HashMap<String, String>();
		params.put("EnAcctInfIDCagsInfSeqNo",seqno);
		params.put("DelSgmtCode","C");
		pbcrsReportEnacctinfdelMapper.deleteByPrimaryKey(params);
		PbcrsReportEnacctinfdel pbcrsReportEnacctinfdel = new PbcrsReportEnacctinfdel();
		pbcrsReportEnacctinfdel.setEnAcctInfIDCagsInfSeqNo(seqno);
		pbcrsReportEnacctinfdel.setInfRecType("413");
		pbcrsReportEnacctinfdel.setDelRecCode(acctCode);
		pbcrsReportEnacctinfdel.setDelSgmtCode("C");			
		pbcrsReportEnacctinfdel.setReportFlag("0");
		pbcrsReportEnacctinfdel.setOrgCode(pbcrsReportEnacctbssgmt.getMngmtOrgCode());
		Date etl_date = DateUtils.parseDate(rptDate, "yyyy-MM-dd");
		pbcrsReportEnacctinfdel.setEtl_Date(etl_date);
		pbcrsReportEnacctinfdel.setSourceSys(sourceSys);
		pbcrsReportEnacctinfdel.setDelEndDate(rptDate);
		pbcrsReportEnacctinfdel.setDelStartDate(startDate);
		int insertCount = pbcrsReportEnacctinfdelMapper.insert(pbcrsReportEnacctinfdel);
		if(insertCount != 1){
			throw new Exception();
		}
		return 1;
	}
	
	public int deleteActLbltyInfSgmt(Map<String, Object> param) throws Exception {
		String seqno = param.get("EnAcctInfSeqNo").toString();
		String startDate = param.get("startDate")==null?null:param.get("startDate").toString();
		PbcrsReportActlbltyinfsgmt pbcrsReportActlbltyinfsgmt = pbcrsReportActlbltyinfsgmtMapper.selectByPrimaryKey(seqno);
		//设置删除标志为1,表示该段已经被删除
		pbcrsReportActlbltyinfsgmt.setParagraphDel("1");
		int updateCount = pbcrsReportActlbltyinfsgmtMapper.updateByPrimaryKey(pbcrsReportActlbltyinfsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//基础段：取业务标识码和报送日期
		PbcrsReportEnacctbssgmt pbcrsReportEnacctbssgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(seqno);
		String acctCode = pbcrsReportEnacctbssgmt.getAcctCode();
		String rptDate = pbcrsReportEnacctbssgmt.getRptDate();
		String sourceSys = pbcrsReportEnacctbssgmt.getSourceSys();
		Map<String,String> params = new HashMap<String, String>();
		params.put("EnAcctInfIDCagsInfSeqNo",seqno);
		params.put("DelSgmtCode","H");
		pbcrsReportEnacctinfdelMapper.deleteByPrimaryKey(params);
		
		PbcrsReportEnacctinfdel pbcrsReportEnacctinfdel = new PbcrsReportEnacctinfdel();
		pbcrsReportEnacctinfdel.setEnAcctInfIDCagsInfSeqNo(seqno);
		pbcrsReportEnacctinfdel.setInfRecType("413");
		pbcrsReportEnacctinfdel.setDelRecCode(acctCode);
		pbcrsReportEnacctinfdel.setDelSgmtCode("H");
		pbcrsReportEnacctinfdel.setReportFlag("0");
		Date etl_date = DateUtils.parseDate(rptDate, "yyyy-MM-dd");
		pbcrsReportEnacctinfdel.setEtl_Date(etl_date);
		pbcrsReportEnacctinfdel.setSourceSys(sourceSys);
		pbcrsReportEnacctinfdel.setDelStartDate(startDate);
		pbcrsReportEnacctinfdel.setDelEndDate(rptDate);
		pbcrsReportEnacctinfdel.setOrgCode(pbcrsReportEnacctbssgmt.getMngmtOrgCode());
		int insertCount = pbcrsReportEnacctinfdelMapper.insert(pbcrsReportEnacctinfdel);
		if(insertCount != 1){
			throw new Exception();
		}
		return 1;
	}
	
	
	public int deleteEnAccSpeTrsSgmtm(Map<String, Object> param) throws Exception {
		String seqno = param.get("EnAcctInfSeqNo").toString();
		String startDate = param.get("startDate")==null?null:param.get("startDate").toString();
		 PbcrsReportEnaccspetrssgmt pbcrsReportEnaccspetrssgmt = pbcrsReportEnaccspetrssgmtMapper.selectByPrimaryKey(seqno);
		//设置删除标志为1,表示该段已经被删除
		 pbcrsReportEnaccspetrssgmt.setParagraphDel("1");
		int updateCount = pbcrsReportEnaccspetrssgmtMapper.updateByPrimaryKey(pbcrsReportEnaccspetrssgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		List<PbcrsReportEncagoftrdinf> byFirstKey = pbcrsReportEncagoftrdinfMapper.selectByAcctSpecTrstDspnSgmtSeqNo(seqno);
		if(byFirstKey != null){
			for (PbcrsReportEncagoftrdinf pbcrsReportEncagoftrdinf : byFirstKey) {
				pbcrsReportEncagoftrdinf.setParagraphDel("1");
				int subInsertCount = pbcrsReportEncagoftrdinfMapper.updateByPrimaryKey(pbcrsReportEncagoftrdinf);
				if(subInsertCount != 1){
					throw new Exception();
				}
			}
		}
		
		//基础段：取业务标识码和报送日期
		PbcrsReportEnacctbssgmt pbcrsReportEnacctbssgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(seqno);
		String acctCode = pbcrsReportEnacctbssgmt.getAcctCode();
		String rptDate = pbcrsReportEnacctbssgmt.getRptDate();
		String sourceSys = pbcrsReportEnacctbssgmt.getSourceSys();
		Map<String,String> params = new HashMap<String, String>();
		params.put("EnAcctInfIDCagsInfSeqNo",seqno);
		params.put("DelSgmtCode","I");
		pbcrsReportEnacctinfdelMapper.deleteByPrimaryKey(params);
		
		PbcrsReportEnacctinfdel pbcrsReportEnacctinfdel = new PbcrsReportEnacctinfdel();
		pbcrsReportEnacctinfdel.setEnAcctInfIDCagsInfSeqNo(seqno);
		pbcrsReportEnacctinfdel.setInfRecType("413");
		pbcrsReportEnacctinfdel.setDelRecCode(acctCode);
		pbcrsReportEnacctinfdel.setDelSgmtCode("I");
		pbcrsReportEnacctinfdel.setReportFlag("0");
		Date etl_date = DateUtils.parseDate(rptDate, "yyyy-MM-dd");
		pbcrsReportEnacctinfdel.setEtl_Date(etl_date);
		pbcrsReportEnacctinfdel.setSourceSys(sourceSys);
		pbcrsReportEnacctinfdel.setDelStartDate(startDate);
		pbcrsReportEnacctinfdel.setDelEndDate(rptDate);
		pbcrsReportEnacctinfdel.setOrgCode(pbcrsReportEnacctbssgmt.getMngmtOrgCode());
		int insertCount = pbcrsReportEnacctinfdelMapper.insert(pbcrsReportEnacctinfdel);
		if(insertCount != 1){
			throw new Exception();
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
		pbcrsReportEnAcctBsSgmtMapper.updateByMap(map);
		pbcrsReportEnacctbsinfsgmtMapper.updateByMap(map);
		pbcrsReportEnrltrepinfsgmtMapper.updateByMap(map);
		pbcrsReportEnrltrepymtinfMapper.updateByMap(map);
		pbcrsReportEnmotcltctrinfsgmMapper.updateByMap(map);
		pbcrsReportCccinfMapper.updateByMap(map);
		pbcrsReportEnacctcredsgmtMapper.updateByMap(map);
		pbcrsReportEnoricreinfsgmtMapper.updateByMap(map);
		pbcrsReportActlbltyinfsgmtMapper.updateByMap(map);
		pbcrsReportEnaccspetrssgmtMapper.updateByMap(map);
		pbcrsReportEncagoftrdinfMapper.updateByMap(map);
		return 1;
	}

	@Override
	public int updateByMapMdc(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 修改主表删除状态：已删除
	 * @param
	 * @return
	 */
	public int updateByIsDel(Map<String,Object> params) throws Exception{
		log.info("updateByIsDel:{}",JSON.toJSONString(params));
		
		if(params.containsKey("AcctBsSgmtSeqNo")){
			String AcctBsSgmtSeqNo = params.get("AcctBsSgmtSeqNo").toString();
			//查询基础段信息
			PbcrsReportEnacctbssgmt EnAcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(AcctBsSgmtSeqNo);
			//插入删除
			PbcrsReportEnacctinfentdel EnAccDlt = new PbcrsReportEnacctinfentdel();
			EnAccDlt.setEnAcctInfEntDelSeqNo(AcctBsSgmtSeqNo);
			EnAccDlt.setInfRecType("414");
			EnAccDlt.setDelRecCode(EnAcctBsSgmt.getAcctCode());
			EnAccDlt.setSourceSys(EnAcctBsSgmt.getSourceSys());
			EnAccDlt.setReportFlag(Constants.REPORTFLAG_IN);
			EnAccDlt.setOrgCode(EnAcctBsSgmt.getMngmtOrgCode());
			int dltRt = pbcrsReportEnacctinfentdelMapper.insert(EnAccDlt);
			if(dltRt > 0){
				params.put("IsDel", "1");
				int crt =  pbcrsReportEnAcctBsSgmtMapper.updateByIsDel(params);
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

	public int confirm(Map<String, Object> param) {
		return pbcrsReportEnAcctBsSgmtMapper.confirm(param);
	}
}
