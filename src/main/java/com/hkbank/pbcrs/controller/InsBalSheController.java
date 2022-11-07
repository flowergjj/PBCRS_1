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
import com.hkbank.pbcrs.service.InsBalSheService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 事业单位资产负债信息
 * @author admin
 *
 */
@Controller
@RequestMapping("/InsBalShe")
public class InsBalSheController {
	
	private static final Logger log = LogManager.getLogger(InsBalSheController.class);
	
	@Autowired
	private InsBalSheService service;
	
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
	@RequestMapping("/getInsbalshebssgmt")//基础
	public Object getInsbalshebssgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getInsbalshebssgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getInsBalSheSgmt")//详细
	public Object getInsBalSheSgmt(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getInsBalSheSgmt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	//事业单位资产负债基础
	@ResponseBody
	@RequestMapping("/updateInsbalshebssgmt")//基础
	public Map<String, Object> updateInsbalshebssgmt(HttpServletRequest request) {

		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("事业单位资产负债基础更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.updateInsbalshebssgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("事业单位资产负债基础更新出现异常!", e);
		}
		
		return rslt;
	}
	
	//事业单位资产负债详细
	@ResponseBody
	@RequestMapping("/upInsBalSheSgmt")//详细
	public Map<String, Object> upInsBalSheSgmt(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> rslt = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("事业单位资产负债详细更新时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("userid",user.getUserId());
			//rslt.put("RET_CODE", "FAILED");
			int suc= service.upInsBalSheSgmt(params);
			
			if(suc!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("事业单位资产负债详细更新出现异常!", e);
		}
		
		return rslt;
	}
	
	
	
	
	//事业单位资产负债信息整笔删除
	@ResponseBody
	@RequestMapping("/updateByIsDel")
	public Map<String, Object> updateByIsDel(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("事业单位资产负债信息整笔删除时session会话失效!");
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
			log.error("事业单位资产负债信息整笔删除更新出现异常!", e);
		}
		
		return rslt;
	}
	

}
