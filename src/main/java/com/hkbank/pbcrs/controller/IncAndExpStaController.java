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
import com.hkbank.pbcrs.service.BalanceSheetService;
import com.hkbank.pbcrs.service.EnBasInfBsSgmtService;
import com.hkbank.pbcrs.service.IncAndExpStaService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 事业单位收入支出表信息
 * @author admin
 *
 */
@Controller
@RequestMapping("/IncAndExpSta")
public class IncAndExpStaController {
	
	private static final Logger log = LogManager.getLogger(IncAndExpStaController.class);
	
	@Autowired
	private IncAndExpStaService service;
	
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
	@RequestMapping("/getIncandexpstbssgmt")
	public Object getIncandexpstbssgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getIncandexpstbssgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getIncandexpstasgmt")
	public Object getIncandexpstasgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getIncandexpstasgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	//事业单位收入支出基础段
	@ResponseBody
	@RequestMapping("/updateIncandexpstbssgmt")
	public Map<String, Object> updateIncandexpstbssgmt(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("事业单位收入支出基础段基础段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateIncandexpstbssgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("事业单位收入支出基础段基础段出现异常!", e);
		}
		
		return rslt;
	}
	
	//事业单位收入支出基本概况
	@ResponseBody
	@RequestMapping("/upIncandexpstasgmt")
	public Map<String, Object> upIncandexpstasgmt(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("事业单位收入支出基本概况更新时session失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.upIncandexpstasgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("事业单位收入支出基本概况更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	
	
	//事业单位收入支出信息整笔删除
	@ResponseBody
	@RequestMapping("/updateByIsDel")
	public Map<String, Object> updateByIsDel(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("事业单位收入支出信息整笔删除时session会话失效!");
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
			log.error("事业单位收入支出信息整笔删除更新出现异常!", e);
		}
		
		return rslt;
	}
	
	

}
