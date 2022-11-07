package com.hkbank.pbcrs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.PbcrsReportBusilinesMappingService;
import com.hkbank.pbcrs.service.SpeFieldUpdateService;

@Controller
@RequestMapping("/spefieldupdate")
public class SpeFieldUpdateController {
	private static final Logger log = LogManager.getLogger(SpeFieldUpdateController.class);
	@Autowired
	private SpeFieldUpdateService speFieldUpdateService;
	
	@ResponseBody
	@RequestMapping("/listPage")
	public Map<String, Object> listPage(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return speFieldUpdateService.listPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/fieldinfolist")
	public Map<String, Object> fieldInfoList(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return speFieldUpdateService.fieldInfoList(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}

	@RequestMapping(value = "/getConditionCombox")
	@ResponseBody
	public List<Map<String, Object>> getConditionCombox(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tableName", req.getParameter("tableName"));
		try {
			List<Map<String, Object>> list = speFieldUpdateService.getConditionCombox(map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@RequestMapping(value = "/insertSpedeal")
	@ResponseBody
	public Map<String,String> insertSpedeal(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(req);
		Map<String,String> resultMap = new HashMap<String, String>();
		try {
			speFieldUpdateService.insertSpedeal(params);
			resultMap.put("RET_CODE", "SUCCESS");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/deleteSpedeal")
	@ResponseBody
	public Map<String,String> deleteSpedeal(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(req);
		System.out.println(params);
		Map<String,String> resultMap = new HashMap<String, String>();
		try {
			speFieldUpdateService.deleteSpedeal(params);
			resultMap.put("RET_CODE", "SUCCESS");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		return resultMap;
	}
}
