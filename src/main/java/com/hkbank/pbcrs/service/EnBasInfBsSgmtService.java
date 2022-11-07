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
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfactcotinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinffcssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfidrecMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfidsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfmmbinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfshahodinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbsinfdltMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnmmbinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnshahodinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportSpvathinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCotainfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportTraceMapper;
import com.hkbank.pbcrs.model.PbcrsReportActucotrlinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportCotainfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfactcotinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidrec;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfmmbinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfshahodinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbsinfdlt;
import com.hkbank.pbcrs.model.PbcrsReportMnmmbinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnshahodinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpvathinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportTrace;

















import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;
import com.hkbank.pbcrs.controller.EnBasInfBsSgmtController;



@Service
public class EnBasInfBsSgmtService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(EnBasInfBsSgmtService.class);

	
	@Autowired
	PbcrsReportEnbasinfbssgmtMapper pbcrsReportEnbasinfbssgmtMapper;
	@Autowired
	PbcrsReportEnbasinffcssgmtMapper pbcrsReportEnbasinffcssgmtMapper;
	
	//@Autowired
	//PbcrsReportEnbasinfMapper pbcrsReportEnbasinfMapper;
	@Autowired
	PbcrsReportSpvathinfsgmtMapper pbcrsReportSpvathinfsgmtMapper;
	
	@Autowired
	PbcrsReportCotainfsgmtMapper pbcrsReportCotainfsgmtMapper;
	
	@Autowired
	PbcrsReportEnbasinfidsgmtMapper pbcrsReportEnbasinfidsgmtMapper;
	@Autowired
	PbcrsReportEnbasinfidrecMapper pbcrsReportEnbasinfidrecMapper;
	
	@Autowired
	PbcrsReportMnmmbinfsgmtMapper pbcrsReportMnmmbinfsgmtMapper;
	
	@Autowired
	PbcrsReportEnbasinfmmbinfMapper pbcrsReportEnBasInfMmbInfMapper;
	
	@Autowired
	PbcrsReportMnshahodinfsgmtMapper pbcrsReportMnShaHodInfSgmtMapper;
	
	@Autowired
	PbcrsReportEnbasinfshahodinfMapper pbcrsReportEnBasInfShaHodInfMapper;
	
	@Autowired
	PbcrsReportActucotrlinfsgmtMapper pbcrsReportActuCotrlInfSgmtMapper;
	
	@Autowired
	PbcrsReportEnbasinfactcotinfMapper pbcrsReportEnBasInfActCotInfMapper;
	
	@Autowired
	PbcrsReportEnbsinfdltMapper pbcrsReportEnbsinfdltMapper;//整笔删除
	

	@Autowired
	PbcrsReportTraceService pbcrsReportTraceService;
	@Autowired
	InvestigationTypeService investigationTypeService;
	

	public Map<String,Object> getEnbasinffcssgmt(Map<String,Object> params){
		if(params.containsKey("ENBASINFSEQNO")){
			String ENBASINFSEQNO = params.get("ENBASINFSEQNO").toString();
			return pbcrsReportEnbasinffcssgmtMapper.selectByEnBasInfSeqno(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	public int updateEnbasinffcssgmt(Map<String,Object> params) throws Exception{
		log.info("updateEnbasinffcssgmt:{}",JSON.toJSONString(params));
		
		if(params.containsKey("BsSgmtSeqNo")){

						
			try {
				String type = params.get("type").toString();
				String BsSgmtSeqNo = params.get("BsSgmtSeqNo").toString();
				//PbcrsReportEnbasinf enbasinfo = pbcrsReportEnbasinfMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportEnbasinfbssgmt enbasinfbssgmtold = pbcrsReportEnbasinfbssgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				PbcrsReportEnbasinffcssgmt enbasinffcssgmt=(PbcrsReportEnbasinffcssgmt) Util.map2Object2(params,PbcrsReportEnbasinffcssgmt.class);
				pbcrsReportTraceService.insertTrace(enbasinfbssgmtold, params, "ENBASINFBSSGMT",BsSgmtSeqNo);
				enbasinffcssgmt.setFcsInfSgmtSeqNo(BsSgmtSeqNo);
				if (type.equals(Constants.TYPE_UPDSUB)) {
					enbasinffcssgmt.setReportFlag(Constants.REPORTFLAG_IN);
					updateByKey(BsSgmtSeqNo);
				}else{
					enbasinffcssgmt.setReportFlag(Constants.REPORTFLAG_IN);
					enbasinfbssgmtold.setReportFlag(Constants.REPORTFLAG_IN);
					pbcrsReportEnbasinfbssgmtMapper.updateByPrimaryKey(enbasinfbssgmtold);
				}
				pbcrsReportEnbasinffcssgmtMapper.updateByPrimaryKey(enbasinffcssgmt);
				investigationTypeService.changeUpdate("enbasinf", BsSgmtSeqNo, null);
				
				return 1;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("updateEnbasinffcssgmt修改失败！",e);
				e.printStackTrace();
				throw new Exception();
			} 
		}else{
			return 0;
		}
		
		
		
	}
	
	public Map<String,Object> getEnBasInfBsSgmt(Map<String,Object> params){
		if(params.containsKey("ENBASINFSEQNO")){
			String ENBASINFSEQNO = params.get("ENBASINFSEQNO").toString();
			return pbcrsReportEnbasinfbssgmtMapper.selectByEnBasInfSeqno(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	public Map<String,Object> getIdsSgmt(String IDSgmtSeqNo,String IDSgmtListSeqNo) throws Exception {
		PbcrsReportEnbasinfidrec ids =pbcrsReportEnbasinfidrecMapper.selectByPrimaryKey(IDSgmtSeqNo,IDSgmtListSeqNo);
		
		return Util.Obj2Map(ids);
	}
	
	public Map<String,Object> getMnMmbInf(String IDSgmtSeqNo,String IDSgmtListSeqNo) throws Exception {
		PbcrsReportEnbasinfmmbinf ids =pbcrsReportEnBasInfMmbInfMapper.selectByPrimaryKey(IDSgmtSeqNo,IDSgmtListSeqNo);
		
		return Util.Obj2Map(ids);
	}
	
	public Map<String,Object> getMnShaHodInf(String IDSgmtSeqNo,String IDSgmtListSeqNo) throws Exception {
		PbcrsReportEnbasinfshahodinf ids =pbcrsReportEnBasInfShaHodInfMapper.selectByPrimaryKey(IDSgmtSeqNo,IDSgmtListSeqNo);
		
		return Util.Obj2Map(ids);
	}
	
	public Map<String,Object> getActuCotrlInf(String ActuCotrlInfSgmtSeqNo,String ActuCotrlInfSeqNo) throws Exception {
		PbcrsReportEnbasinfactcotinf ids =pbcrsReportEnBasInfActCotInfMapper.selectByPrimaryKey(ActuCotrlInfSgmtSeqNo,ActuCotrlInfSeqNo);
		
		return Util.Obj2Map(ids);
	}
	
	public int updateIdsSgmt(Map<String,Object> params) throws Exception{
		log.info("updateIdsSgmt:{}",JSON.toJSONString(params));
		if(params.containsKey("BsSgmtSeqNo")){
			 
			try {
				
				String type = params.get("type").toString();
				String BsSgmtSeqNo = params.get("BsSgmtSeqNo").toString();
				//PbcrsReportEnbasinf enbasinfo = pbcrsReportEnbasinfMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportEnbasinfbssgmt enbasinfbssgmtold = pbcrsReportEnbasinfbssgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				PbcrsReportEnbasinfidrec idrec  =(PbcrsReportEnbasinfidrec) Util.map2Object2(params,PbcrsReportEnbasinfidrec.class);
				PbcrsReportEnbasinfidrec idrecold = pbcrsReportEnbasinfidrecMapper.selectByPrimaryKey(idrec.getIDSgmtSeqNo(),idrec.getIDSgmtListSeqNo());
				pbcrsReportTraceService.insertTrace(idrecold, params, "ENBASINFIDREC",BsSgmtSeqNo);
				if(type.equals(Constants.TYPE_UPDSUB)){
					idrec.setReportFlag(Constants.REPORTFLAG_IN);
					updateByKey(BsSgmtSeqNo);
				}else{
					idrec.setReportFlag(Constants.REPORTFLAG_IN);
					enbasinfbssgmtold.setReportFlag(Constants.REPORTFLAG_IN);
					pbcrsReportEnbasinfbssgmtMapper.updateByPrimaryKey(enbasinfbssgmtold);
				}
				pbcrsReportEnbasinfidrecMapper.updateByPrimaryKey(idrec);
				investigationTypeService.changeUpdate("enbasinf", BsSgmtSeqNo, null);
				return 1;

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
			
		
		}else{
			return 0;
		}
		
	}
	
	
	public int updateMnMmbInf(Map<String,Object> params) throws Exception{
		log.info("updateMnMmbInf:{}",JSON.toJSONString(params));
		if(params.containsKey("BsSgmtSeqNo")){
			 
			try {
				String type = params.get("type").toString();
				String BsSgmtSeqNo = params.get("BsSgmtSeqNo").toString();
				//PbcrsReportEnbasinf enbasinfo = pbcrsReportEnbasinfMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportEnbasinfbssgmt enbasinfbssgmtold = pbcrsReportEnbasinfbssgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
				PbcrsReportEnbasinfmmbinf mmbinf  =(PbcrsReportEnbasinfmmbinf) Util.map2Object2(params,PbcrsReportEnbasinfmmbinf.class);
				PbcrsReportEnbasinfmmbinf mmbinfold= pbcrsReportEnBasInfMmbInfMapper.selectByPrimaryKey(mmbinf.getMnMmbInfSgmtSeqNo(), mmbinf.getMmbInfSeqNo());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
				pbcrsReportTraceService.insertTrace(mmbinfold, params, "ENBASINFMMBINF",BsSgmtSeqNo);
				if(type.equals(Constants.TYPE_UPDSUB)){
					mmbinf.setReportFlag(Constants.REPORTFLAG_IN);
					updateByKey(BsSgmtSeqNo);
				}else{
					mmbinf.setReportFlag(Constants.REPORTFLAG_IN);
					enbasinfbssgmtold.setReportFlag(Constants.REPORTFLAG_IN);
					pbcrsReportEnbasinfbssgmtMapper.updateByPrimaryKey(enbasinfbssgmtold);

				}
				pbcrsReportEnBasInfMmbInfMapper.updateByPrimaryKey(mmbinf);
				investigationTypeService.changeUpdate("enbasinf", BsSgmtSeqNo, null);
				
				return 1;
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
			
		
		}else{
			return 0;
		}
	
	}
	
	
	public int updateMnShaHodInf(Map<String,Object> params) throws Exception{
		log.info("updateMnShaHodInf:{}",JSON.toJSONString(params));
		if(params.containsKey("BsSgmtSeqNo")){
			 
			try {
				String type = params.get("type").toString();
				String BsSgmtSeqNo = params.get("BsSgmtSeqNo").toString();
				//PbcrsReportEnbasinf enbasinfo = pbcrsReportEnbasinfMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportEnbasinfbssgmt enbasinfbssgmtold = pbcrsReportEnbasinfbssgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
				PbcrsReportEnbasinfshahodinf shahodinf  =(PbcrsReportEnbasinfshahodinf) Util.map2Object2(params,PbcrsReportEnbasinfshahodinf.class);
				
				PbcrsReportEnbasinfshahodinf shahodinfold= pbcrsReportEnBasInfShaHodInfMapper.selectByPrimaryKey(shahodinf.getMnShaHodInfSgmtSeqNo(), shahodinf.getSharHodInfSeqNo());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
				pbcrsReportTraceService.insertTrace(shahodinfold, params, "ENBASINFSHAHODINF",BsSgmtSeqNo);
				if(type.equals(Constants.TYPE_UPDSUB)){
					shahodinf.setReportFlag(Constants.REPORTFLAG_IN);
					updateByKey(BsSgmtSeqNo);
				}else{
					shahodinf.setReportFlag(Constants.REPORTFLAG_IN);
					enbasinfbssgmtold.setReportFlag(Constants.REPORTFLAG_IN);
					pbcrsReportEnbasinfbssgmtMapper.updateByPrimaryKey(enbasinfbssgmtold);

				}
				pbcrsReportEnBasInfShaHodInfMapper.updateByPrimaryKey(shahodinf);
				investigationTypeService.changeUpdate("enbasinf", BsSgmtSeqNo, null);
				
				return 1;
	
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
			
		
		}else{
			return 0;
		}
		
	}
	
	public int updatectuCotrlInfSgmt(Map<String,Object> params) throws Exception{
		log.info("updatectuCotrlInfSgmt:{}",JSON.toJSONString(params));
		if(params.containsKey("BsSgmtSeqNo")){
			 
			try {
				String type = params.get("type").toString();
				String BsSgmtSeqNo = params.get("BsSgmtSeqNo").toString();
				String userid =params.get("userid").toString();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				PbcrsReportEnbasinfbssgmt enbasinfbssgmtold = pbcrsReportEnbasinfbssgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
				PbcrsReportEnbasinfactcotinf actcotinf=(PbcrsReportEnbasinfactcotinf) Util.map2Object2(params,PbcrsReportEnbasinfactcotinf.class);
				PbcrsReportEnbasinfactcotinf actcotinfold= pbcrsReportEnBasInfActCotInfMapper.selectByPrimaryKey(actcotinf.getActuCotrlInfSgmtSeqNo(), actcotinf.getActuCotrlInfSeqNo());
				
				pbcrsReportTraceService.insertTrace(actcotinfold, params, "ENBASINFACTCOTINF",BsSgmtSeqNo);
				
				if(type.equals(Constants.TYPE_UPDSUB)){
					actcotinf.setReportFlag(Constants.REPORTFLAG_IN);
					updateByKey(BsSgmtSeqNo);
				}else{
					actcotinf.setReportFlag(Constants.REPORTFLAG_IN);
					enbasinfbssgmtold.setReportFlag(Constants.REPORTFLAG_IN);
					pbcrsReportEnbasinfbssgmtMapper.updateByPrimaryKey(enbasinfbssgmtold);
				}
				pbcrsReportEnBasInfActCotInfMapper.updateByPrimaryKey(actcotinf);
				investigationTypeService.changeUpdate("enbasinf", BsSgmtSeqNo, null);
				return 1;
				
		
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
			
		
		}else{
			return 0;
		}
		
	}
	
	//基础段
	public int updateEnBasInfBsSgmt(Map<String,Object> params) throws Exception{
		log.info("updateEnBasInfBsSgmt:{}",JSON.toJSONString(params));
		
		if(params.containsKey("BsSgmtSeqNo")){

			
			try {
				String type=params.get("type").toString();
				String BsSgmtSeqNo = params.get("BsSgmtSeqNo").toString();
				PbcrsReportEnbasinfbssgmt enbasinfbssgmtold = pbcrsReportEnbasinfbssgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				PbcrsReportEnbasinfbssgmt enbasinfbssgmt=(PbcrsReportEnbasinfbssgmt) Util.map2Object2(params,PbcrsReportEnbasinfbssgmt.class);
				pbcrsReportTraceService.insertTrace(enbasinfbssgmtold, params, "ENBASINFBSSGMT",BsSgmtSeqNo);
				if(type.equals(Constants.TYPE_UPDSUB)){
					enbasinfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);
					enbasinfbssgmt.setRptDateCode("20");
					//enbasinfbssgmt.setUpdateDate(now);
				}else{
					enbasinfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);

				}
				
				enbasinfbssgmt.setInfRecType("310");
				pbcrsReportEnbasinfbssgmtMapper.updateByPrimaryKey(enbasinfbssgmt);
				investigationTypeService.changeUpdate("enbasinf", BsSgmtSeqNo, null);
				return 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
				
			} 
		}else{
			return 0;
		}
		
		
		
	}
		
	public Map<String,Object> getSpvAthInfSgmt(Map<String,Object> params){
		if(params.containsKey("ENBASINFSEQNO")){
			String ENBASINFSEQNO = params.get("ENBASINFSEQNO").toString();
			return pbcrsReportSpvathinfsgmtMapper.selectByEnBasInfSeqno(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	public int updateSpvAthInfSgmt(Map<String,Object> params) throws Exception{
		log.info("updateSpvAthInfSgmt:{}",JSON.toJSONString(params));
		
		if(params.containsKey("BsSgmtSeqNo")){

			
			
			try {
				
				String type = params.get("type").toString();
				String BsSgmtSeqNo = params.get("BsSgmtSeqNo").toString();
				//PbcrsReportEnbasinf enbasinfo = pbcrsReportEnbasinfMapper.selectByPrimaryKey(ENBASINFSEQNO);
				PbcrsReportEnbasinfbssgmt enbasinfbssgmtold = pbcrsReportEnbasinfbssgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
				PbcrsReportSpvathinfsgmt spvathinfsgmt=(PbcrsReportSpvathinfsgmt) Util.map2Object2(params,PbcrsReportSpvathinfsgmt.class);
				
				PbcrsReportSpvathinfsgmt spvathinfsgmtold= pbcrsReportSpvathinfsgmtMapper.selectByPrimaryKey(spvathinfsgmt.getSpvsgAthrtyInfSgmtSeqNo());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				
				pbcrsReportTraceService.insertTrace(spvathinfsgmtold, params, "SPVATHINFSGMT",BsSgmtSeqNo);
				if(type.equals(Constants.TYPE_UPDSUB)){
					spvathinfsgmt.setReportFlag(Constants.REPORTFLAG_IN);
					updateByKey(BsSgmtSeqNo);
				}else{
					spvathinfsgmt.setReportFlag(Constants.REPORTFLAG_IN);
					enbasinfbssgmtold.setReportFlag(Constants.REPORTFLAG_IN);
					pbcrsReportEnbasinfbssgmtMapper.updateByPrimaryKey(enbasinfbssgmtold);
				}
				pbcrsReportSpvathinfsgmtMapper.updateByPrimaryKey(spvathinfsgmt);
				investigationTypeService.changeUpdate("enbasinf", BsSgmtSeqNo, null);
				
				return 1;
		
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
		}else{
			return 0;
		}
		
		
		
	}
	
	
	public Map<String,Object> getCotaInfSgmt(Map<String,Object> params){
		if(params.containsKey("ENBASINFSEQNO")){
			String ENBASINFSEQNO = params.get("ENBASINFSEQNO").toString();
			return pbcrsReportCotainfsgmtMapper.selectByEnBasInfSeqno(ENBASINFSEQNO);
		}
		else{
			return null;
		}
		
	}
	
	public int updateCotaInfSgmt(Map<String,Object> params) throws Exception{
		log.info("updateCotaInfSgmt:{}",JSON.toJSONString(params));
		
		if(params.containsKey("BsSgmtSeqNo")){

			
			
			try {
				String type = params.get("type").toString();
				String BsSgmtSeqNo = params.get("BsSgmtSeqNo").toString();
				String userid =params.get("userid").toString();
				PbcrsReportCotainfsgmt cotainfsgmtold = pbcrsReportCotainfsgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
				PbcrsReportEnbasinfbssgmt enbasinfbssgmtold = pbcrsReportEnbasinfbssgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(new Date());
				PbcrsReportCotainfsgmt cotainfsgmt=(PbcrsReportCotainfsgmt) Util.map2Object2(params,PbcrsReportCotainfsgmt.class);
				pbcrsReportTraceService.insertTrace(cotainfsgmtold, params, "COTAINFSGMT",BsSgmtSeqNo);
				
				if(type.equals(Constants.TYPE_UPDSUB)){
					cotainfsgmt.setReportFlag(Constants.REPORTFLAG_IN);
					updateByKey(BsSgmtSeqNo);
				}else{
					cotainfsgmt.setReportFlag(Constants.REPORTFLAG_IN);
					enbasinfbssgmtold.setReportFlag(Constants.REPORTFLAG_IN);
					pbcrsReportEnbasinfbssgmtMapper.updateByPrimaryKey(enbasinfbssgmtold);
				}
				pbcrsReportCotainfsgmtMapper.updateByPrimaryKey(cotainfsgmt);
				investigationTypeService.changeUpdate("enbasinf", BsSgmtSeqNo, null);
				return 1;
		
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			} 
		}else{
			return 0;
		}
		
		
	}
	
	public Map<String,Object> getEnBaseInfoListPage(Map<String,Object> params){
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
		int total = pbcrsReportEnbasinfbssgmtMapper.selectAllbyContCount(params);

		/* 3. 计算记录数 , 避免记录数超过 */
		// while (total > 0 && total <= skip) {
		// skip = skip - pageSize;
		// pageNo = pageNo - 1;
		// }
		/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
		List<PbcrsReportEnbasinfbssgmt> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<PbcrsReportEnbasinfbssgmt>();
		} else {
			int endindex= skip+pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportEnbasinfbssgmtMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}

	
	public Map<String,Object> getEnBaseInfoListPageid(Map<String,Object> params){
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
		int total = pbcrsReportEnbasinfidsgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportEnbasinfidsgmtMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}
	
	public Map<String,Object> listPageMnMmbInfTable(Map<String,Object> params){
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
		int total = pbcrsReportMnmmbinfsgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportMnmmbinfsgmtMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}
	
	public Map<String,Object> listPageMnShaHodInfSgmtTable(Map<String,Object> params){
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
		int total = pbcrsReportMnShaHodInfSgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportMnShaHodInfSgmtMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}
	
	public Map<String,Object> listPageloadActuCotrlInfSgmtTable(Map<String,Object> params){
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
		int total = pbcrsReportActuCotrlInfSgmtMapper.selectAllbyContCount(params);

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
			list = pbcrsReportActuCotrlInfSgmtMapper.selectAllbyContPage( params);
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
		pbcrsReportEnbasinfbssgmtMapper.updateByMap(map);
		pbcrsReportEnbasinfidsgmtMapper.updateByMap(map);
		pbcrsReportEnbasinfidrecMapper.updateByMap(map);
		pbcrsReportEnbasinffcssgmtMapper.updateByMap(map);
		pbcrsReportMnmmbinfsgmtMapper.updateByMap(map);
		pbcrsReportEnBasInfMmbInfMapper.updateByMap(map);
		pbcrsReportMnShaHodInfSgmtMapper.updateByMap(map);
		pbcrsReportEnBasInfShaHodInfMapper.updateByMap(map);
		pbcrsReportActuCotrlInfSgmtMapper.updateByMap(map);
		pbcrsReportEnBasInfActCotInfMapper.updateByMap(map);
		pbcrsReportSpvathinfsgmtMapper.updateByMap(map);
		pbcrsReportCotainfsgmtMapper.updateByMap(map);
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
		params.put("BsSgmtSeqNo", seqNo);
		params.put("RptDateCode", "20");
		params.put("reportFlag", Constants.REPORTFLAG_IN);
		//params.put("rptDate", now);
		return pbcrsReportEnbasinfbssgmtMapper.updateByKey(params);		
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
		params.put("BsSgmtSeqNo", seqNo);
		params.put("reportFlag", Constants.REPORTFLAG_IN);
		params.put("rptDate", now);
		return pbcrsReportEnbasinfbssgmtMapper.updateByFlagAndDate(params);		
	}
	
	/**
	 * 更改主表删除状态：已删除
	 * @param seqNo
	 * @return
	 */
	public int updateByIsDel(Map<String,Object> params) throws Exception{
		log.info("updateByIsDel:{}",JSON.toJSONString(params));
		
		if(params.containsKey("BsSgmtSeqNo")){
			String BsSgmtSeqNo = params.get("BsSgmtSeqNo").toString();
			//查询基础段信息
			PbcrsReportEnbasinfbssgmt enbasinfbssgmt = pbcrsReportEnbasinfbssgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
			//插入删除
			PbcrsReportEnbsinfdlt EnBaseDlt = new PbcrsReportEnbsinfdlt();
			EnBaseDlt.setEnBsInfDltSeqNo(BsSgmtSeqNo);
			EnBaseDlt.setInfRecType("314");
			EnBaseDlt.setInfSurcCode(enbasinfbssgmt.getInfSurcCode());
			EnBaseDlt.setEntName(enbasinfbssgmt.getEntName());
			EnBaseDlt.setEntCertType(enbasinfbssgmt.getEntCertType());
			EnBaseDlt.setEntCertNum(enbasinfbssgmt.getEntCertNum());
			EnBaseDlt.setSourceSys(enbasinfbssgmt.getSourceSys());
			EnBaseDlt.setReportFlag(Constants.REPORTFLAG_IN);
			EnBaseDlt.setOrgCode(enbasinfbssgmt.getCimoc());
			int dltRt = pbcrsReportEnbsinfdltMapper.insert(EnBaseDlt);
			if(dltRt > 0){
				params.put("IsDel", "1");
				int crt =  pbcrsReportEnbasinfbssgmtMapper.updateByIsDel(params);	
				if(crt > 0){
					return 1;
				}else{
					throw new Exception();
				}
				//return crt;
			}else{
				return 0;
			}

		}else{
			return 0;
		}	
	}
}
