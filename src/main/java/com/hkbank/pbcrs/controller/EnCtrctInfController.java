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
import com.hkbank.pbcrs.model.PbcrsReportBssgmt;
import com.hkbank.pbcrs.service.EnAcctInfService;
import com.hkbank.pbcrs.service.EnBasInfBsSgmtService;
import com.hkbank.pbcrs.service.EnCtrctInfService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 企业授信信息
 * @author admin
 *
 */
@Controller
@RequestMapping("/EnCtrctInf")
public class EnCtrctInfController {
	
	private static final Logger log = LogManager.getLogger(EnCtrctInfController.class);
	
	@Autowired
	private EnCtrctInfService service;
	
	@ResponseBody
	@RequestMapping("/listPage")
	public Map<String, Object> listPage(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEnctrctbssgmt")
	public Object getEnAcctBsSgmt(HttpServletRequest request) {
		
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnctrctbssgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/getEnctrctbssgmtm")
	public Object getEnAcctBsSgmtm(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {

			return service.getEnctrctbssgmtm(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping("/getEnctrctcertrel")
	public Object getEnctrctcertrel(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {

			return service.getEnctrctcertrel(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEncreditlimsgmt")
	public Object getEncreditlimsgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {

			return service.getEncreditlimsgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEncreditlimsgmtm")
	public Object getEncreditlimsgmtm(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {

			return service.getEncreditlimsgmtm(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listEnctrcerrelsgmtPage")
	public Map<String, Object> listEnctrcerrelsgmtPage(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listEnctrcerrelsgmtPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listEnctrcerrelsgmtPagem")
	public Map<String, Object> listEnctrcerrelsgmtPagem(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listEnctrcerrelsgmtPagem(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	//企业额度信息基础段
	@ResponseBody
	@RequestMapping("/updateEnctrctbssgmt")
	public Map<String, Object> updateEnctrctbssgmt(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业额度信息基础段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEnctrctbssgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业额度信息基础段更新时出现异常!", e);
		}
		
		return rslt;
	}
	
	
	//企业额度信息共同授信人段
	@ResponseBody
	@RequestMapping("/updateEnCtrctCertRel")
	public Map<String, Object> updateEnCtrctCertRel(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业额度信息共同授信人段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEnCtrctCertRel(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业额度信息共同授信人段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//企业额度信息额度信息段
	@ResponseBody
	@RequestMapping("/updateEncreditlimsgmt")
	public Map<String, Object> updateEncreditlimsgmt(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业额度信息额度信息段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEncreditlimsgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业额度信息额度信息段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	
	//企业授信信息整笔删除
	@ResponseBody
	@RequestMapping("/updateByIsDel")
	public Map<String, Object> updateByIsDel(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业授信信息整笔删除时session会话失效!");
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
			log.error("企业授信信息整笔删除更新出现异常!", e);
		}
		
		return rslt;
	}
	
	 // 按段删除
	@RequestMapping("/deleteform")
	@ResponseBody
	public Map<String, Object> deleteform(HttpServletRequest request,
			PbcrsReportBssgmt BsSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int row=service.deleteform(param);
			if (row != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			rslt.put("RET_CODE", "FAILED");
			log.error("加入按段删除表失败!", e);
		}

		return rslt;

	}
	
	 // 按段删除
	@RequestMapping("/getCount")
	@ResponseBody
	public Map<String, Object> getCount(HttpServletRequest request) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int row=service.getCount(param);
			rslt.put("count", row);
		} catch (Exception e) {
			rslt.put("RET_CODE", "FAILED");
			log.error("加入按段删除表失败!", e);
		}

		return rslt;

	}
	

}
