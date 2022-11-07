package com.hkbank.pbcrs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.EnBasInfBsSgmtService;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 企业基本信息
 * @author admin
 *
 */
@Controller
@RequestMapping("/EnBasInfBsSgmt")
public class EnBasInfBsSgmtController {
	
	private static final Logger log = LogManager.getLogger(EnBasInfBsSgmtController.class);
	
	@Autowired
	private EnBasInfBsSgmtService service;
	
	@ResponseBody
	@RequestMapping("/listPage")
	public Map<String, Object> listPage(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		User user =	(User)request.getSession().getAttribute("user");
		if(!user.getOrgId().equals("T")){
			params.put("orgCode",user.getOrgId());
		}
		try {
			return service.getEnBaseInfoListPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listPageid")
	public Map<String, Object> listPageid(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnBaseInfoListPageid(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listPageMnShaHodInfSgmtTable")
	public Map<String, Object> listPageMnShaHodInfSgmtTable(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listPageMnShaHodInfSgmtTable(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listPageloadActuCotrlInfSgmtTable")
	public Map<String, Object> listPageloadActuCotrlInfSgmtTable(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listPageloadActuCotrlInfSgmtTable(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listPageMnMmbInfTable")
	public Map<String, Object> listPageMnMmbInfTable(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listPageMnMmbInfTable(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEnbasinffcssgmt")
	public Map<String, Object> getEnbasinffcssgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnbasinffcssgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getMnMmbInf")
	public Map<String, Object> getMnMmbInf(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			String IDSgmtSeqNo = params.get("MnMmbInfSgmtSeqNo").toString();
			String IDSgmtListSeqNo = params.get("MmbInfSeqNo").toString();
			return service.getMnMmbInf(IDSgmtSeqNo,IDSgmtListSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getMnShaHodInf")
	public Map<String, Object> getMnShaHodInf(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			String IDSgmtSeqNo = params.get("MnShaHodInfSgmtSeqNo").toString();
			String IDSgmtListSeqNo = params.get("SharHodInfSeqNo").toString();
			return service.getMnShaHodInf(IDSgmtSeqNo,IDSgmtListSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getActuCotrlInf")
	public Map<String, Object> getActuCotrlInf(HttpServletRequest request) {
		
		
		
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			String IDSgmtSeqNo = params.get("ActuCotrlInfSgmtSeqNo").toString();
			String IDSgmtListSeqNo = params.get("ActuCotrlInfSeqNo").toString();
			Map<String,Object> res = service.getActuCotrlInf(IDSgmtSeqNo,IDSgmtListSeqNo);
			return res;
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getIdsSgmt")
	public Map<String, Object> getIdsSgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			String IDSgmtSeqNo = params.get("IDSgmtSeqNo").toString();
			String IDSgmtListSeqNo = params.get("IDSgmtListSeqNo").toString();
			
			return service.getIdsSgmt(IDSgmtSeqNo,IDSgmtListSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping("/getEnBasInfBsSgmt")
	public Map<String, Object> getEnBasInfBsSgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnBasInfBsSgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	//企业基本信息基本概况段
	@ResponseBody
	@RequestMapping("/updateEnbasinffcssgmt")
	public Map<String, Object> updateEnbasinffcssgmt(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();

		Map<String, Object> params = Util.parseWebParas(request);
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业基本信息基本概况段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());

			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEnbasinffcssgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业基本信息基本概况段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//企业基本信息基础段
	@ResponseBody
	@RequestMapping("/updateEnBasInfBsSgmt")
	public Map<String, Object> updateEnBasInfBsSgmt(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业基本信息基础段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEnBasInfBsSgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业基本信息基础段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	@ResponseBody
	@RequestMapping("/getSpvAthInfSgmt")
	public Map<String, Object> getSpvAthInfSgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getSpvAthInfSgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	//企业基本信息上级机构段
	@ResponseBody
	@RequestMapping("/updateSpvAthInfSgmt")
	public Map<String, Object> updateSpvAthInfSgmt(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业基本信息上级机构段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());

			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateSpvAthInfSgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业基本信息上级机构段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	@ResponseBody
	@RequestMapping("/getCotaInfSgmt")
	public Map<String, Object> getCotaInfSgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getCotaInfSgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	//企业基本信息联系方式段
	@ResponseBody
	@RequestMapping("/updateCotaInfSgmt")
	public Map<String, Object> updateCotaInfSgmt(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业基本信息联系方式段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());

			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateCotaInfSgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业基本信息联系方式段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//企业基本信息其他表示段
	@ResponseBody
	@RequestMapping("/updateIdsSgmt")
	public Map<String, Object> updateIdsSgmt(HttpServletRequest request) {	
		Map<String,Object> rslt = new HashMap<String, Object>();

		Map<String, Object> params = Util.parseWebParas(request);	
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业基本信息其他表示段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());

			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateIdsSgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业基本信息其他表示段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//企业基本信息实际控制人段
	@ResponseBody
	@RequestMapping("/updatectuCotrlInfSgmt")
	public Map<String, Object> updatectuCotrlInfSgmt(HttpServletRequest request) {	
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);		
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业基本信息实际控制人段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());

			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updatectuCotrlInfSgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业基本信息实际控制人段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//企业基本信息组织成员信息段
	@ResponseBody
	@RequestMapping("/updateMnMmbInf")
	public Map<String, Object> updateMnMmbInf(HttpServletRequest request) {		
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);		
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业基本信息组织成员信息段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());

			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateMnMmbInf(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业基本信息组织成员信息段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//企业基本信息注册资本及主要出资人段
	@ResponseBody
	@RequestMapping("/updateMnShaHodInf")
	public Map<String, Object> updateMnShaHodInf(HttpServletRequest request) {		
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);		
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业基本信息注册资本及主要出资人段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());

			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateMnShaHodInf(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业基本信息注册资本及主要出资人段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	//企业整笔删除
	@ResponseBody
	@RequestMapping("/updateByIsDel")
	public Map<String, Object> updateByIsDel(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业基本信息整笔删除时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateByIsDel(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			rslt.put("RET_CODE", "FAILED");
			log.error("企业基本信息整笔删除更新出现异常!", e);
		}
		
		return rslt;
	}
	
	

}
