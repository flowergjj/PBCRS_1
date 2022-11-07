package com.hkbank.pbcrs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.PbcrsSysConTrollerService;


@Controller
@RequestMapping("/sysConController")
public class PbcrsSysConController {
	private Logger log = LogManager.getLogger(PbcrsSysConController.class);
	@Autowired
	private PbcrsSysConTrollerService pbcrsSysConTrollerService;
	
	
	
	@RequestMapping(value = "/sysConByType")
	@ResponseBody
	public List<Map<String, Object>> sysConByType(HttpServletRequest req,
			HttpServletResponse response) {
		String type = req.getParameter("type");

		List<Map<String, Object>> list = pbcrsSysConTrollerService.findSysConByType(type);

		log.info("sysConByConId={}", list);

		return list;
	}
	
	@RequestMapping(value = "/deal")
	@ResponseBody
	public Map<String,Object> deal(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(req);
		try {
			User user = new User();
			user = (User) req.getSession().getAttribute("user");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sdf.format(new Date());
			params.put("dealDt", now);
			params.put("insertUserNo", user.getUserId());
			params.put("insertUserName", user.getUserName());
			params.put("reportFlag","0");
			Map<String, Object> rsmap = pbcrsSysConTrollerService.deal(params);
			return rsmap;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调度执行失败",e);
		}
		  
		  return null;
	}
	
	@RequestMapping(value = "/getEtlLogInfo")
	@ResponseBody
	public Map<String,Object> getEtlLogInfo(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(req);
		try {
			Map<String, Object> result = pbcrsSysConTrollerService.getEtlLogInfo(params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调度日志查询失败",e);
		}
		return null;
	}
	
	@RequestMapping(value = "/findLogDetail")
	@ResponseBody
	public Map<String,Object> findLogDetail(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(req);
		try {
			Map<String, Object> result = pbcrsSysConTrollerService.findLogDetail(params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("日志详细信息查询失败",e);
		}
		return null;
	}
	
	
	
}
