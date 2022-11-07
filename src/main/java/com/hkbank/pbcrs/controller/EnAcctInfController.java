package com.hkbank.pbcrs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.hkbank.pbcrs.model.PbcrsReportBssgmt;
import com.hkbank.pbcrs.service.EnAcctInfService;
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
 * 企业借贷信息
 * @author admin
 *
 */
@Controller
@RequestMapping("/EnAcctInf")
public class EnAcctInfController {
	
	private static final Logger log = LogManager.getLogger(EnAcctInfController.class);
	
	@Autowired
	private EnAcctInfService service;
	
	@ResponseBody
	@RequestMapping("/listPage")
	public Map<String, Object> listPage(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		User user =	(User)request.getSession().getAttribute("user");
		if(!user.getOrgId().equals("T")){
			params.put("orgCode",user.getOrgId());
		}
		try {
			return service.listPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEnAcctBsSgmt")
	public Object getEnAcctBsSgmt(HttpServletRequest request) {
		
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEnAcctBsSgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/getEnAcctBsSgmtm")
	public Object getEnAcctBsSgmtm(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {

			return service.getEnAcctBsSgmtm(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping("/getEnacctbsinfsgmt")
	public Object getEnacctbsinfsgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {

			return service.getEnacctbsinfsgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEnacctbsinfsgmtm")
	public Object getEnacctbsinfsgmtm(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {

			return service.getEnacctbsinfsgmtm(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listMotCltCtrInfSgmtPage")
	public Map<String, Object> listMotCltCtrInfSgmtPage(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		params.put("MotgaCltalCtrctInfSgmtSeqNo", params.get("EnAcctInfSeqNo").toString());
		
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
	

	@ResponseBody
	@RequestMapping("/getActlbltyinfsgmt")
	public Object getActlbltyinfsgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getActlbltyinfsgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getActlbltyinfsgmtm")
	public Object getActlbltyinfsgmtm(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getActlbltyinfsgmtm(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listEnaccspetrssgmtPage")
	public Map<String, Object> listEnaccspetrssgmtPage(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listEnaccspetrssgmtPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listEnaccspetrssgmtPagem")
	public Map<String, Object> listEnaccspetrssgmtPagem(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listEnaccspetrssgmtPagem(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getEncagoftrdinf")
	public Object getEncagoftrdinf(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getEncagoftrdinf(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	//借贷账户基础段
	@ResponseBody
	@RequestMapping("/updateEnAcctBsSgmt")
	public Map<String, Object> updateEnAcctBsSgmt(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();				
		Map<String, Object> params = Util.parseWebParas(request);		
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("借贷账户基础段更新时session会话失效!");
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
			log.error("借贷账户基础段更新异常!", e);
		}
		
		return rslt;
	}
	
	//借贷账户基本信息段
	@ResponseBody
	@RequestMapping("/updateEnAcctBsInfSgmt")
	public Map<String, Object> updateEnAcctBsInfSgmt(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("借贷账户基本信息段更新时session会话失效!");
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
			log.error("借贷账户基本信息段更新异常!", e);
		}
		
		return rslt;
	}
	
	//借贷账户抵质押物信息段
	@ResponseBody
	@RequestMapping("/updateCccinf")
	public Map<String, Object> updateCccinf(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);		
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("借贷账户抵质押物信息段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateCccinf(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("借贷账户抵质押物信息段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//借贷账户相关还款责任人段
	@ResponseBody
	@RequestMapping("/updateEnrltrepymtinf")
	public Map<String, Object> updateEnrltrepymtinf(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("借贷账户相关还款责任人段更新时session会话失效!");
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
			log.error("借贷账户相关还款责任人段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//借贷账户授信额度信息段
	@ResponseBody
	@RequestMapping("/updateEnacctcredsgmt")
	public Map<String, Object> updateEnacctcredsgmt(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();	
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("借贷账户授信额度信息段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());

			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEnacctcredsgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("借贷账户授信额度信息段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//借贷账户初始债权人说明段
	@ResponseBody
	@RequestMapping("/updateEnoricreinfsgmt")
	public Map<String, Object> updateEnoricreinfsgmt(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();		
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("借贷账户初始债权人说明段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());

			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEnoricreinfsgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("借贷账户初始债权人说明段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//借贷账户还款表现信息段
	@ResponseBody
	@RequestMapping("/updateActlbltyinfsgmt")
	public Map<String, Object> updateActlbltyinfsgmt(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("借贷账户还款表现信息段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateActlbltyinfsgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("借贷账户还款表现信息段更新时出现异常!", e);
		}
		
		return rslt;
	}
	
	//借贷账户特定交易说明段
	@ResponseBody
	@RequestMapping("/updateEncagoftrdinf")
	public Map<String, Object> updateEncagoftrdinf(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);
	
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("借贷账户特定交易说明段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid", user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateEncagoftrdinf(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("借贷账户特定交易说明段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	//企业借贷信息整笔删除
	@ResponseBody
	@RequestMapping("/updateByIsDel")
	public Map<String, Object> updateByIsDel(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业借贷信息整笔删除时session会话失效!");
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
			log.error("企业借贷信息整笔删除更新出现异常!", e);
		}
		
		return rslt;
	}
	
	/**
	 * 删除基本信息段
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteEnAcctBsInfSgmt")
	@ResponseBody
	public Map<String,Object> deleteEnAcctBsInfSgmt(HttpServletRequest request){
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			service.deleteEnAcctBsInfSgmt(param);
			rslt.put("RET_CODE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}
		return rslt;
		
	}
	
	
	/**
	 * 删除还款表现段
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteActLbltyInfSgmt")
	@ResponseBody
	public Map<String,Object> deleteActLbltyInfSgmt(HttpServletRequest request){
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			service.deleteActLbltyInfSgmt(param);
			rslt.put("RET_CODE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}
		return rslt;
		
	}
	

	/**
	 * 删除特殊交易段
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteEnAccSpeTrsSgmtm")
	@ResponseBody
	public Map<String,Object> deleteEnAccSpeTrsSgmtm(HttpServletRequest request){
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			service.deleteEnAccSpeTrsSgmtm(param);
			rslt.put("RET_CODE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}
		return rslt;
		
	}

	//不良贷款确认
	@RequestMapping("/confirm")
	@ResponseBody
	public Map<String, Object> confirm(HttpServletRequest request,
									   PbcrsReportBssgmt BsSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			int row=service.confirm(param);
			if (row != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			rslt.put("RET_CODE", "FAILED");
			log.error("确认成功!", e);
		}
		return rslt;
	}
	

}
