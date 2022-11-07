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
import com.hkbank.pbcrs.service.CashFlowsService;
import com.hkbank.pbcrs.service.EnBasInfBsSgmtService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 企业现金流量表
 * @author admin
 *
 */
@Controller
@RequestMapping("/CashFlows")
public class CashFlowsController {
	
	private static final Logger log = LogManager.getLogger(CashFlowsController.class);
	
	@Autowired
	private CashFlowsService service;
	
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
	@RequestMapping("/getCashflowsbssgmt")
	public Object getCashflowsbssgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getCashflowsbssgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getCashflows2007sgmt")
	public Object getCashflows2007sgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getCashflows2007sgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	//企业现金流量基础段
	@ResponseBody
	@RequestMapping("/updateCashflowsbssgmt")
	public Map<String, Object> updateCashflowsbssgmt(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业现金流量基础段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateCashflowsbssgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业现金流量基础段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//2007版现金流量表段
	@ResponseBody
	@RequestMapping("/upCashflows2007sgmt")
	public Map<String, Object> upCashflows2007sgmt(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("2007版现金流量表段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.upCashflows2007sgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("2007版现金流量表段出现异常!", e);
		}
		
		return rslt;
	}
	
	
	
	
	//企业现金流量信息整笔删除
	@ResponseBody
	@RequestMapping("/updateByIsDel")
	public Map<String, Object> updateByIsDel(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业现金流量信息整笔删除时session会话失效!");
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
			log.error("企业现金流量信息整笔删除更新出现异常!", e);
		}
		
		return rslt;
	}
	

}
