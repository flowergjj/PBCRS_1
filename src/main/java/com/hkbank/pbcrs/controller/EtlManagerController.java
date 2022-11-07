package com.hkbank.pbcrs.controller;

import java.util.HashMap;
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

import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.EtlManagerService;

@Controller
@RequestMapping("/etlManager")
public class EtlManagerController {
	private static final Logger log = LogManager.getLogger(EtlManagerController.class);
	@Autowired
	private EtlManagerService etlManagerService;
	@RequestMapping("/getDataDate")
	@ResponseBody
	public Map<String, Object> getDataDate(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("etlDate", req.getParameter("etlDate"));
		map.put("proc_name", req.getParameter("proc_name"));
		try {
			Map<String, Object> rslt = etlManagerService.getDataDate(map);
			return rslt;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询出现异常",e);
		}
		return null;
	}
	
	@RequestMapping("/getByEtlType")
	@ResponseBody
	public Map<String, Object> getByEtlType(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("etlDate", req.getParameter("etlDate"));
		//map.put("sourceSys", req.getParameter("sourceSys"));
		map.put("procName", req.getParameter("sourceSys")+"_"+req.getParameter("etlType")+"."+req.getParameter("etlName"));
		//map.put("etlName", req.getParameter("etlName"));
		/*if(StringUtils.isNotEmpty(req.getParameter("dsDate"))){
			map.put("dsDate", req.getParameter("dsDate"));
		}*/
		try {
			Map<String, Object> rslt = etlManagerService.getByEtlType(map);
			return rslt;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询出现异常",e);
		}
		return null;
	}
	
	@RequestMapping("/getEtlLog")
	@ResponseBody
	public Map<String, Object> getEtlLog(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ds_proc_name", req.getParameter("ds_proc_name"));
		map.put("rpt_proc_name", req.getParameter("rpt_proc_name"));
		map.put("dsDate",req.getParameter("dsDate"));
		map.put("nextDsDate",req.getParameter("nextDsDate"));
		map.put("rptDate",req.getParameter("rptDate"));
		map.put("nextRptDate",req.getParameter("nextRptDate"));
		map.put("etlDate",req.getParameter("etlDate"));
		map.put("etlType",req.getParameter("etlType"));
		map.put("char",req.getParameter("char"));
		try {
			Map<String, Object> result = etlManagerService.getEtlLog(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询出现异常",e);
		}
		return null;
	}
	
	@RequestMapping("/getCheckAllLog")
	@ResponseBody
	public Map<String, Object> getCheckAllLog(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = Util.parseWebParas(request);
		try {
			Map<String, Object> result = etlManagerService.getChackAllLog(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		return null;
	}
	
	@RequestMapping("/getCheckByGroup")
	@ResponseBody
	public Map<String, Object> getCheckByGroup(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = Util.parseWebParas(request);
		try {
			Map<String, Object> result = etlManagerService.getCheckByGroup(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		return null;
	}
	
	@RequestMapping("/getErrorPK")
	@ResponseBody
	public Map<String, Object> getErrorPK(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = Util.parseWebParas(request);
		try {
			Map<String, Object> result = etlManagerService.getErrorPK(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		return null;
	}
	
	@RequestMapping("/getErrorDate")
	@ResponseBody
	public Map<String, Object> getErrorDate(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = Util.parseWebParas(request);
		try {
			Map<String, Object> result = etlManagerService.getErrorDate(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/getEtlType")
	@ResponseBody
	public List<Map<String, Object>> findSexCode(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sourceSys", req.getParameter("sourceSys"));
		try {
			List<Map<String, Object>> list = etlManagerService.getEtlType(map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!",e);
		}
		return null;
		
	}
	
}
