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
import com.hkbank.pbcrs.mapper.PbcrsInvestigationTypeMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportActlbltyinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportActucotrlinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCccinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccinfidcaginfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccinfmdfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccinfmdfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccspetrssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctbsinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctcredsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfmdfcMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfactcotinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinffcssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfidrecMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfidsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfmmbinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfshahodinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEncagoftrdinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEncreditlimsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrcerrelsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrctcertrelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrctinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrctinfdelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrctinfentdelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrctinfidcagsiMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrctinfmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrctinfmdfcMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrinfmdfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnctrinfmdfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnoricreinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnrltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnrltrepymtinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnmmbinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnshahodinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMotcltctrinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportSpvathinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCotainfsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportActlbltyinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportActucotrlinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportCccinf;
import com.hkbank.pbcrs.model.PbcrsReportCotainfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportCtrctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnaccinfidcaginf;
import com.hkbank.pbcrs.model.PbcrsReportEnaccinfmdfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnaccinfmdfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnaccspetrssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctcredsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctinf;
import com.hkbank.pbcrs.model.PbcrsReportEnacctinfm;
import com.hkbank.pbcrs.model.PbcrsReportEnacctinfmdfc;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfactcotinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidrec;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfmmbinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfshahodinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbsinfdlt;
import com.hkbank.pbcrs.model.PbcrsReportEncagoftrdinf;
import com.hkbank.pbcrs.model.PbcrsReportEncreditlimsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnctrcerrelsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnctrctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnctrctcertrel;
import com.hkbank.pbcrs.model.PbcrsReportEnctrctinf;
import com.hkbank.pbcrs.model.PbcrsReportEnctrctinfentdel;
import com.hkbank.pbcrs.model.PbcrsReportEnctrctinfidcagsi;
import com.hkbank.pbcrs.model.PbcrsReportEnctrctinfm;
import com.hkbank.pbcrs.model.PbcrsReportEnctrctinfmdfc;
import com.hkbank.pbcrs.model.PbcrsReportEnctrinfmdfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnctrinfmdfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnoricreinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnrltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnrltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportMnmmbinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnshahodinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMotcltctrinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpvathinfsgmt;















import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;


@Service
public class EnCtrctInfService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(EnCtrctInfService.class);

	@Autowired
	PbcrsReportEnctrctinfMapper pbcrsReportEnctrctinfMapper; //主

	@Autowired
	PbcrsReportEnctrctbssgmtMapper pbcrsReportEnctrctbssgmtMapper; //基础段
	
	@Autowired
	PbcrsReportEnctrcerrelsgmtMapper pbcrsReportEnctrcerrelsgmtMapper; //企业额度信息段共同受信人信息段
	
	@Autowired
	PbcrsReportEnctrctcertrelMapper pbcrsReportEnctrctcertrelMapper; //企业额度信息段共同受信人信息段详细
	
	@Autowired
	PbcrsReportEncreditlimsgmtMapper pbcrsReportEncreditlimsgmtMapper; //企业额度信息段额度信息段
	
	@Autowired
	PbcrsReportEnctrctinfmMapper pbcrsReportEnctrctinfmMapper; //修改
	
	@Autowired
	PbcrsReportEnctrctinfmdfcMapper pbcrsReportEnctrctinfmdfcMapper; //企业借贷账户更正请求记录
	
	@Autowired
	PbcrsReportEnctrinfmdfbssgmtMapper pbcrsReportEnctrinfmdfbssgmtMapper; //企业借贷账户信息更正段基本
	
	@Autowired
	PbcrsReportEnctrinfmdfsgmtMapper pbcrsReportEnctrinfmdfsgmtMapper; //企业借贷账户信息更正段
	
	@Autowired
	PbcrsReportEnctrctinfidcagsiMapper pbcrsReportEnctrctinfidcagsiMapper; //企业借贷账户信息标识变更请求记录
	
	@Autowired
	private InvestigationTypeService investigationTypeService; //报文修改
	
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;//报文修改痕迹记录
	
	@Autowired
	private PbcrsReportEnctrctinfentdelMapper pbcrsReportEnctrctinfentdelMapper; //授信整笔删除
	
	@Autowired
	private PbcrsReportEnctrctinfdelMapper pbcrsReportEnctrctinfdelMapper;
	
	//列表
	public Map<String,Object> listPage(Map<String,Object> params){
			
			int pageNo = MapUtils.getIntValue(params, "page");
			int pageSize = MapUtils.getIntValue(params, "rows");
			
			int skip = (pageNo - 1) * pageSize;

			String countKey;
			/* 2. 计算总记录数 */
			int total = pbcrsReportEnctrctbssgmtMapper.selectAllbyContCount(params);

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
				list = pbcrsReportEnctrctbssgmtMapper.selectAllbyContPage( params);
			}
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("total", total);
			rslt.put("rows", list);

			return rslt;
			
		} 
	
	//基础段
	public Object getEnctrctbssgmt(Map<String,Object> params){
		if(params.containsKey("EnCtrctInfSeqNo")){
			String EnCtrctInfSeqNo = params.get("EnCtrctInfSeqNo").toString();
			return pbcrsReportEnctrctbssgmtMapper.selectByEnCtrctInfSeqNo(EnCtrctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	public Object getEnctrctbssgmtm(Map<String,Object> params){
		if(params.containsKey("EnCtrctInfSeqNo")){
			String EnCtrctInfSeqNo = params.get("EnCtrctInfSeqNo").toString();
			return pbcrsReportEnctrctbssgmtMapper.selectByEnCtrctInfMSeqNo(EnCtrctInfSeqNo);
		}
		else{
			return null;
		}
		
	}
	
	
	
	//企业额度信息段共同受信人信息段列表
	public Map<String,Object> listEnctrcerrelsgmtPage(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportEnctrcerrelsgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportEnctrcerrelsgmtMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	} 
	
	public Map<String,Object> listEnctrcerrelsgmtPagem(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportEnctrcerrelsgmtMapper.selectAllbyContCountm(params);

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
			list = pbcrsReportEnctrcerrelsgmtMapper.selectAllbyContPagem( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	} 
	
	
	//企业额度信息段共同受信人信息段详细
	public Object getEnctrctcertrel(Map<String,Object> params){
		
		
		if(params.containsKey("CtrctCertRelSgmtSeqNo")&&params.containsKey("CtrctCertRelSeqNo")){
			String CtrctCertRelSgmtSeqNo = params.get("CtrctCertRelSgmtSeqNo").toString();
			String CtrctCertRelSeqNo = params.get("CtrctCertRelSeqNo").toString();
			return pbcrsReportEnctrctcertrelMapper.selectByPrimaryKey(CtrctCertRelSgmtSeqNo,CtrctCertRelSeqNo);
		}
		else{
			return null;
		}
		
	}
	 
	
	//企业额度信息段额度信息段
	public Object getEncreditlimsgmt(Map<String,Object> params){
			if(params.containsKey("EnCtrctInfSeqNo")){
				String EnCtrctInfSeqNo = params.get("EnCtrctInfSeqNo").toString();
				return pbcrsReportEncreditlimsgmtMapper.selectByEnCtrctInfSeqNo(EnCtrctInfSeqNo);
			}
			else{
				return null;
			}
			
		}
		
	public Object getEncreditlimsgmtm(Map<String,Object> params){
			if(params.containsKey("EnCtrctInfSeqNo")){
				String EnCtrctInfSeqNo = params.get("EnCtrctInfSeqNo").toString();
				return pbcrsReportEncreditlimsgmtMapper.selectByEnCtrctInfMSeqNo(EnCtrctInfSeqNo);
			}
			else{
				return null;
			}
			
		}
	
	//修改基础pbcrsReportEnctrctbssgmtMapper
	public int updateEnctrctbssgmt(Map<String,Object> params)throws Exception{
		log.info("updateEnctrctbssgmt:{}",JSON.toJSONString(params));
		String a = params.get("userid").toString();
		
		if(params.containsKey("EnCtrctInfSeqNo")){

			PbcrsReportEnctrctbssgmt enctrctbssgmt = new PbcrsReportEnctrctbssgmt();
			
			try {
				String type = params.get("type").toString();
				String EnCtrctInfSeqNo = params.get("EnCtrctInfSeqNo").toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());

					//转换前台传入内容
					enctrctbssgmt=(PbcrsReportEnctrctbssgmt) Util.map2Object2(params,PbcrsReportEnctrctbssgmt.class);
					//查询当前企业额度信息段基础段数据
					PbcrsReportEnctrctbssgmt oldenctrctbssgmt = pbcrsReportEnctrctbssgmtMapper.selectByPrimaryKey(enctrctbssgmt.getCtrctBsSgmtSeqNo());
					//修改一条企业额度信息段基础段数据
					
					if(type.equals(Constants.TYPE_SUB)){
						enctrctbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
					}else{
						//enctrctbssgmt.setRptDate(now);
						enctrctbssgmt.setReportFlag(oldenctrctbssgmt.getReportFlag());
					}
					
					int cnt = pbcrsReportEnctrctbssgmtMapper.updateByPrimaryKey(enctrctbssgmt);
					if(cnt!=1)
						return cnt;
				if(type.equals(Constants.TYPE_UPDSUB)){
					if(!oldenctrctbssgmt.getContractCode().equals(enctrctbssgmt.getContractCode())){//标示有改变需新增标示变更记录
						//int enctrctinfidcagsiKey = pbcrsReportEnctrctinfidcagsiMapper.getKey();
						PbcrsReportEnctrctinfidcagsi pbcrsReportEnctrctinfidcagsi = new PbcrsReportEnctrctinfidcagsi();
						pbcrsReportEnctrctinfidcagsi.setEnCtrctInfIDCagsInfSeqNo(EnCtrctInfSeqNo);
						pbcrsReportEnctrctinfidcagsi.setInfRecType("421");
						pbcrsReportEnctrctinfidcagsi.setOdBnesCode(oldenctrctbssgmt.getContractCode());
						pbcrsReportEnctrctinfidcagsi.setNwBnesCode(enctrctbssgmt.getContractCode());
						pbcrsReportEnctrctinfidcagsiMapper.deleteByPrimaryKey(EnCtrctInfSeqNo);
						pbcrsReportEnctrctinfidcagsi.setReportFlag(Constants.REPORTFLAG_IN);
						cnt=pbcrsReportEnctrctinfidcagsiMapper.insert(pbcrsReportEnctrctinfidcagsi);
						if(cnt!=1)
							return cnt;						
					}
					//授信协议更正请求信息
					PbcrsReportEnctrinfmdfbssgmt pbcrsReportEnctrinfmdfbssgmt = new PbcrsReportEnctrinfmdfbssgmt();
					pbcrsReportEnctrinfmdfbssgmt.setEnCtrctInfMdfcSeqNo(String.valueOf(EnCtrctInfSeqNo));
					
					pbcrsReportEnctrinfmdfbssgmt.setInfRecType("422");
					pbcrsReportEnctrinfmdfbssgmt.setModRecCode(enctrctbssgmt.getContractCode());
					pbcrsReportEnctrinfmdfbssgmt.setRptDate(oldenctrctbssgmt.getRptDate());//更正基础段时需要与库中的信息报告日期一致
					pbcrsReportEnctrinfmdfbssgmt.setMdfcSgmtCode("B");
					pbcrsReportEnctrinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);

					//删除企业授信更正记录
					Map<String,String> map = new HashMap<String, String>();
					map.put("EnCtrctInfMdfcSeqNo", EnCtrctInfSeqNo);
					map.put("MDFCSGMTCODE", "B");
					cnt = pbcrsReportEnctrinfmdfbssgmtMapper.deleteByMap(map);
					//新增企业授信更正记录
					cnt=pbcrsReportEnctrinfmdfbssgmtMapper.insert(pbcrsReportEnctrinfmdfbssgmt);
					if(cnt!=1)
						return cnt;
					}

					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(oldenctrctbssgmt, params, "EnCtrctBsSgmt",EnCtrctInfSeqNo);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("ecsgmt", EnCtrctInfSeqNo, "EnCtrctBsSgmt");
					if(cnt!=1)
						return cnt;
				
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
	
	
	
	//企业额度信息段共同受信人信息段
	public int updateEnCtrctCertRel(Map<String,Object> params) throws Exception{
		log.info("updateEnCtrctCertRel:{}",JSON.toJSONString(params));

		if(params.containsKey("EnCtrctInfSeqNo")){

			PbcrsReportEnctrctcertrel enCtrctCertRel = new PbcrsReportEnctrctcertrel();
			
			try {
				String type= params.get("type").toString();
				String EnCtrctInfSeqNo = params.get("EnCtrctInfSeqNo").toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
					enCtrctCertRel=(PbcrsReportEnctrctcertrel) Util.map2Object2(params,PbcrsReportEnctrctcertrel.class);
					//基础段
					PbcrsReportEnctrctbssgmt enctrctbssgmt = pbcrsReportEnctrctbssgmtMapper.selectByPrimaryKey(EnCtrctInfSeqNo);
					//根据页面传入共同收信人id查询
					PbcrsReportEnctrctcertrel enCtrctCertRels = pbcrsReportEnctrctcertrelMapper.selectByKeyByCtrctCertRelSeqNo(enCtrctCertRel.getCtrctCertRelSeqNo());
					if(type.equals(Constants.TYPE_SUB)){
						enCtrctCertRel.setReportFlag(Constants.REPORTFLAG_IN);
						enctrctbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnctrctbssgmtMapper.updateByPrimaryKey(enctrctbssgmt);
					}else{
						enCtrctCertRel.setReportFlag(enCtrctCertRels.getReportFlag());
					}
					        
							int cnt =pbcrsReportEnctrctcertrelMapper.updateByPrimaryKey(enCtrctCertRel);
							if(cnt!=1)
								return cnt;
					if(type.equals(Constants.TYPE_UPDSUB)){
						PbcrsReportEnctrinfmdfbssgmt pbcrsReportEnctrinfmdfbssgmt = new PbcrsReportEnctrinfmdfbssgmt();
						pbcrsReportEnctrinfmdfbssgmt.setEnCtrctInfMdfcSeqNo(String.valueOf(EnCtrctInfSeqNo));
						
						pbcrsReportEnctrinfmdfbssgmt.setInfRecType("422");
						pbcrsReportEnctrinfmdfbssgmt.setModRecCode(enctrctbssgmt.getContractCode());
						pbcrsReportEnctrinfmdfbssgmt.setRptDate(enctrctbssgmt.getRptDate());
						pbcrsReportEnctrinfmdfbssgmt.setMdfcSgmtCode("C");
						pbcrsReportEnctrinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);

						//删除企业授信更正记录
						Map<String,String> map = new HashMap<String, String>();
						map.put("EnCtrctInfMdfcSeqNo", EnCtrctInfSeqNo);
						map.put("MDFCSGMTCODE", "C");
						cnt = pbcrsReportEnctrinfmdfbssgmtMapper.deleteByMap(map);
						 cnt=pbcrsReportEnctrinfmdfbssgmtMapper.insert(pbcrsReportEnctrinfmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}

					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(enCtrctCertRels, params, "EnCtrctCertRel",EnCtrctInfSeqNo);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("ecsgmt", EnCtrctInfSeqNo, "EnCtrctCertRel");
					if(cnt!=1)
						return cnt;
				
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

	//修改pbcrsReportEncreditlimsgmtMapper; //企业额度信息段额度信息段
	public int updateEncreditlimsgmt(Map<String,Object> params){

		log.info("updateEncreditlimsgmt:{}",JSON.toJSONString(params));
		if(params.containsKey("EnCtrctInfSeqNo")){

			PbcrsReportEncreditlimsgmt encreditlimsgmt = new PbcrsReportEncreditlimsgmt();
			
			try {
				String type = params.get("type").toString();
				String EnCtrctInfSeqNo = params.get("EnCtrctInfSeqNo").toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
					encreditlimsgmt=(PbcrsReportEncreditlimsgmt) Util.map2Object2(params,PbcrsReportEncreditlimsgmt.class);
					//查询基础段
					PbcrsReportEnctrctbssgmt enctrctbssgmt = pbcrsReportEnctrctbssgmtMapper.selectByPrimaryKey(EnCtrctInfSeqNo);
					//查询企业额度信息段额度信息段信息
					PbcrsReportEncreditlimsgmt pbcrsLIssgmt = pbcrsReportEncreditlimsgmtMapper.selectByPrimaryKey(EnCtrctInfSeqNo);
					encreditlimsgmt.setCreditLimSgmtSeqNo(String.valueOf(EnCtrctInfSeqNo));
					if(type.equals(Constants.TYPE_SUB)){
						encreditlimsgmt.setReportFlag(Constants.REPORTFLAG_IN);
						enctrctbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
						pbcrsReportEnctrctbssgmtMapper.updateByPrimaryKey(enctrctbssgmt);
					}else{
						encreditlimsgmt.setReportFlag(pbcrsLIssgmt.getReportFlag());
					}				
					int cnt = pbcrsReportEncreditlimsgmtMapper.updateByPrimaryKey(encreditlimsgmt);
					if(cnt!=1)
						return cnt;
					if(type.equals(Constants.TYPE_UPDSUB)){
						//记录更正段
						PbcrsReportEnctrinfmdfbssgmt pbcrsReportEnctrinfmdfbssgmt = new PbcrsReportEnctrinfmdfbssgmt();
						pbcrsReportEnctrinfmdfbssgmt.setEnCtrctInfMdfcSeqNo(String.valueOf(EnCtrctInfSeqNo));
						pbcrsReportEnctrinfmdfbssgmt.setInfRecType("422");
						pbcrsReportEnctrinfmdfbssgmt.setModRecCode(enctrctbssgmt.getContractCode());
						pbcrsReportEnctrinfmdfbssgmt.setRptDate(enctrctbssgmt.getRptDate());
						pbcrsReportEnctrinfmdfbssgmt.setMdfcSgmtCode("D");
						pbcrsReportEnctrinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);

						//删除企业授信更正记录
						Map<String,String> map = new HashMap<String, String>();
						map.put("EnCtrctInfMdfcSeqNo", EnCtrctInfSeqNo);
						map.put("MDFCSGMTCODE", "D");
						cnt = pbcrsReportEnctrinfmdfbssgmtMapper.deleteByMap(map);
						cnt=pbcrsReportEnctrinfmdfbssgmtMapper.insert(pbcrsReportEnctrinfmdfbssgmt);
						if(cnt!=1)
							return cnt;
					}
					//报文修改痕迹记录
					pbcrsReportTraceService.insertTrace(pbcrsLIssgmt, params, "EnCreditLimSgmt",EnCtrctInfSeqNo);
					//将更正段插入修改表
					cnt=investigationTypeService.changeUpdate("ecsgmt", EnCtrctInfSeqNo, "EnCreditLimSgmt");
					if(cnt!=1)
						return cnt;
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		pbcrsReportEnctrctbssgmtMapper.updateByMap(map);
		pbcrsReportEnctrcerrelsgmtMapper.updateByMap(map);
		pbcrsReportEnctrctcertrelMapper.updateByMap(map);
		pbcrsReportEncreditlimsgmtMapper.updateByMap(map);
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
		
		if(params.containsKey("EnCtrctInfSeqNo")){
			String EnCtrctInfSeqNo = params.get("EnCtrctInfSeqNo").toString();
			//查询基础段信息
			PbcrsReportEnctrctbssgmt enctrctbssgmt = pbcrsReportEnctrctbssgmtMapper.selectByPrimaryKey(EnCtrctInfSeqNo);
			//插入删除
			PbcrsReportEnctrctinfentdel EnCtrtDlt = new PbcrsReportEnctrctinfentdel();
			EnCtrtDlt.setEnCtrctInfEntDelSeqNo(EnCtrctInfSeqNo);
			EnCtrtDlt.setInfRecType("424");
			EnCtrtDlt.setDelRecCode(enctrctbssgmt.getContractCode());
			EnCtrtDlt.setSourceSys(enctrctbssgmt.getSourceSys());
			EnCtrtDlt.setReportFlag(Constants.REPORTFLAG_IN);
			int dltRt = pbcrsReportEnctrctinfentdelMapper.insert(EnCtrtDlt);
			if(dltRt > 0){
				params.put("IsDel", "1");
				int crt =  pbcrsReportEnctrctbssgmtMapper.updateByIsDel(params);
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
	
	
	public int deleteform(Map<String, Object> params) throws Exception {
		String infoKey = params.get("key").toString();
		String startDate = params.get("startDate")==null?null: params.get("startDate").toString();
		
/*		PbcrsReportInctrctentdel inCtrctEntDel = InctrctentdelMapper.selectByPrimaryKey(infoKey);
		if (inCtrctEntDel != null ) {
			return 0;
		}*/
		PbcrsReportEnctrctbssgmt ctrctbssgmt = pbcrsReportEnctrctbssgmtMapper.selectByPrimaryKey(infoKey);
		if (ctrctbssgmt != null) {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("EnCtrctDelSeqNo",infoKey);
			param.put("InfRecType","423");
			param.put("DelRecCode",ctrctbssgmt.getContractCode());
			param.put("DelSgmtCode","D");
			param.put("DelStartDate",startDate);
			param.put("DelEndDate",ctrctbssgmt.getRptDate());
			param.put("reportFlag","0");
			param.put("rptDate",ctrctbssgmt.getRptDate());
			param.put("sourceSys",ctrctbssgmt.getSourceSys());
			param.put("isDel","1");
			//新增按段删除记录
			int cnt = pbcrsReportEnctrctinfdelMapper.insertDelP(param);
			if (cnt != 1) {
				throw new Exception();
			}
			//修改额度信息段isDel
			cnt = pbcrsReportEncreditlimsgmtMapper.update(param);
			if (cnt != 1) {
				throw new Exception();
			}
		}

		return 1;
	}
	
	public int getCount(Map<String, Object> params) throws Exception{
		String infoKey = params.get("key").toString();
		return pbcrsReportEncreditlimsgmtMapper.getCount(infoKey);
	}

}
