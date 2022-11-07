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
import com.hkbank.pbcrs.service.EnAcctInfService;
import com.hkbank.pbcrs.service.EnBasInfBsSgmtService;
import com.hkbank.pbcrs.service.EnSecAcctInfService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 企业担保
 * @author admin
 *
 */
@Controller
@RequestMapping("/EnSecAcctInf")
public class EnSecAcctInfController {
	
	private static final Logger log = LogManager.getLogger(EnSecAcctInfController.class);
	
	@Autowired
	private EnSecAcctInfService service;
	
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
	@RequestMapping("/getEnguaracctbssgmt")
	public Object getEnguaracctbssgmt(HttpServletRequest request) {
		
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnguaracctbssgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/getEnguaracctbssgmtm")
	public Object getEnguaracctbssgmtm(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {

			return service.getEnguaracctbssgmtm(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping("/getEnguaaccbsinfsgmt")
	public Object getEnguaaccbsinfsgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {

			return service.getEnguaaccbsinfsgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEnguaaccbsinfsgmtm")
	public Object getEnguaaccbsinfsgmtm(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {

			return service.getEnguaaccbsinfsgmtm(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listMotCltCtrInfSgmtPage")
	public Map<String, Object> listMotCltCtrInfSgmtPage(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listMotCltCtrInfSgmtPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listMotCltCtrInfSgmtPagem")
	public Map<String, Object> listMotCltCtrInfSgmtPagem(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listMotCltCtrInfSgmtPagem(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getCccinf")
	public Object getCccinf(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getCccinf(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listEnrltrepinfsgmtPage")
	public Map<String, Object> listEnrltrepinfsgmtPage(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listEnrltrepinfsgmtPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listEnrltrepinfsgmtPagem")
	public Map<String, Object> listEnrltrepinfsgmtPagem(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listEnrltrepinfsgmtPagem(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping("/getEnrltrepymtinf")
	public Object getEnrltrepymtinf(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnrltrepymtinf(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEnacctcredsgmt")
	public Object getEnacctcredsgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnacctcredsgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEnacctcredsgmtm")
	public Object getEnacctcredsgmtm(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnacctcredsgmtm(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEnoricreinfsgmt")
	public Object getEnoricreinfsgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnoricreinfsgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEnoricreinfsgmtm")
	public Object getEnoricreinfsgmtm(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnoricreinfsgmtm(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
			return null;
		}
		
		//
	}
	

	
	
	//企业担保信息基础段
	@ResponseBody
	@RequestMapping("/updateEnAcctBsSgmt")
	public Map<String, Object> updateEnAcctBsSgmt(HttpServletRequest request) {
		
		Map<String, Object> params = Util.parseWebParas(request);
		
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业担保信息基础段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEnAcctBsSgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业担保信息基础段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//企业担保信息基本信息段
	@ResponseBody
	@RequestMapping("/updateEnAcctBsInfSgmt")
	public Map<String, Object> updateEnAcctBsInfSgmt(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);		
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user ==null){
				log.error("企业担保信息基本信息段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEnAcctBsInfSgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业担保信息基本信息段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	//企业担保信息相关还款责任人段
	@ResponseBody
	@RequestMapping("/updateEnrltrepymtinf")
	public Map<String, Object> updateEnrltrepymtinf(HttpServletRequest request) {
		
		Map<String, Object> params = Util.parseWebParas(request);		
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业担保信息相关还款责任人段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEnrltrepymtinf(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业担保信息相关还款责任人段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//企业担保信息授信段
	@ResponseBody
	@RequestMapping("/updateGuaracctcredsgmt")
	public Map<String, Object> updateGuaracctcredsgmt(HttpServletRequest request) {
	
		Map<String, Object> params = Util.parseWebParas(request);		
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业担保信息授信段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateGuaracctcredsgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业担保信息授信段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//企业担保信息在保责任信息段
	@ResponseBody
	@RequestMapping("/updateGuarltrepinfsgmt")
	public Map<String, Object> updateGuarltrepinfsgmt(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业担保信息在保责任信息段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateGuarltrepinfsgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业担保信息在保责任信息段出现异常!", e);
		}
		
		return rslt;
	}
	
	
	
	//企业担保信息整笔删除
	@ResponseBody
	@RequestMapping("/updateByIsDel")
	public Map<String, Object> updateByIsDel(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业担保信息整笔删除时session会话失效!");
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
			log.error("企业担保信息整笔删除更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	/**
	 * 删除基本信息段
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteEnGuaAccBsInfSgmt")
	@ResponseBody
	public Map<String,Object> deleteEnGuaAccBsInfSgmt(HttpServletRequest request){
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			service.deleteEnGuaAccBsInfSgmt(param);
			rslt.put("RET_CODE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}
		return rslt;
		
	}
	
	/**
	 * 删除在保责任人信息段
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteGuaRltRepInfSgmt")
	@ResponseBody
	public Map<String,Object> deleteGuaRltRepInfSgmt(HttpServletRequest request){
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			service.deleteGuaRltRepInfSgmt(param);
			rslt.put("RET_CODE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}
		return rslt;
		
	}
	

}
