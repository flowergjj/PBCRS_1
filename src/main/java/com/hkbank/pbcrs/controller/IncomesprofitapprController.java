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
import com.hkbank.pbcrs.service.IncomesprofitapprService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 企业利润及利润分配信息
 * @author admin
 *
 */
@Controller
@RequestMapping("/IncomeSProfitAppr")
public class IncomesprofitapprController {
	
	private static final Logger log = LogManager.getLogger(IncomesprofitapprController.class);
	
	@Autowired
	private IncomesprofitapprService service;
	
	//查询资产分配利益列表
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
	@RequestMapping("/getIncomesbssgmt")
	public Object getIncomesbssgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getIncomesbssgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	//企业利润及分配信息基础段
	@ResponseBody
	@RequestMapping("/getIncomeStatement07")
	public Object getIncomeStatement07(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getIncomeStatement07(params);
		} catch (Exception e) {
			log.error("企业利润及分配信息基础段出现异常!", e);
		}
		
		return null;
	}
	
	//修改利润及利润分配表段
	@ResponseBody
	@RequestMapping("/upIncomestatement07")
	public Map<String, Object> upIncomestatement07(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");			
			if(user == null){
				log.error("企业利润及分配信息基本段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.upIncomestatement07(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业利润及分配信息基本段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//企业利润及分配信息基础段
	@ResponseBody
	@RequestMapping("/updateIncomesbssgmt")
	public Map<String, Object> updateIncomesbssgmt(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业利润及分配信息基础段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateIncomesbssgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("企业利润及分配信息基本段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	//企业利润及分配信息整笔删除
	@ResponseBody
	@RequestMapping("/updateByIsDel")
	public Map<String, Object> updateByIsDel(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业利润及分配信息整笔删除时session会话失效!");
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
			log.error("企业利润及分配信息整笔删除更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	

}
