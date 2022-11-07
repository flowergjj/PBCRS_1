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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 企业资产负债表
 * @author admin
 *
 */
@Controller
@RequestMapping("/BalanceSheet")
public class BalanceSheetController {
	
	private static final Logger log = LogManager.getLogger(BalanceSheetController.class);
	
	@Autowired
	private BalanceSheetService service;
	
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
	@RequestMapping("/getBalancesheetbssgm")
	public Object getBalancesheetbssgm(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getBalancesheetbssgm(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getBalanceSheet2007Sgmt")
	public Object getBalanceSheet2007Sgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getBalanceSheet2007Sgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	//资产负债基础段
	@ResponseBody
	@RequestMapping("/updateBalanceSheetBsSgm")
	public Map<String, Object> updateBalanceSheetBsSgm(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null ){
				log.error("资产负债基础段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateBalanceSheetBsSgm(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("资产负债基础段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//2007版资产负债表段
	@ResponseBody
	@RequestMapping("/upBalanceSheet2007Sgmt")
	public Map<String, Object> upBalanceSheet2007Sgmt(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("2007版资产负债表段更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.upBalanceSheet2007Sgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("2007版资产负债表段更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	
	
	//企业资产负债信息整笔删除
	@ResponseBody
	@RequestMapping("/updateByIsDel")
	public Map<String, Object> updateByIsDel(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("企业资产负债信息整笔删除时session会话失效!");
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
			log.error("企业资产负债信息整笔删除更新出现异常!", e);
		}
		
		return rslt;
	}
	

}
