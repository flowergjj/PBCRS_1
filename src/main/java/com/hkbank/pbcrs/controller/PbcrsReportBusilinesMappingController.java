package com.hkbank.pbcrs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.PbcrsReportBusilinesMappingService;

@Controller
@RequestMapping("/Busilines")
public class PbcrsReportBusilinesMappingController {
	private static final Logger log = LogManager.getLogger(PbcrsReportBusilinesMappingController.class);
	@Autowired
	private PbcrsReportBusilinesMappingService service;
	
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
	
	/**
	 * 保存
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveParam")
	public Map<String, Object> saveParam(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.saveParam(params);
		} catch (Exception e) {
			log.error("新增码值异常!", e);
		}
		
		return null;
	}
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delParam")
	public Map<String, Object> delParam(HttpServletRequest request) {
		
		Map<String,Object> rsmap = new HashMap<String, Object>();

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
		 service.delParam(params);
		 rsmap.put("RET_CODE", "SUCCESS");
	     rsmap.put("RET_MSG", "删除成功！");
		} catch (Exception e) {
			log.error("删除业务参数异常异常!", e);
			rsmap.put("RET_CODE", "FAILED");
			rsmap.put("RET_MSG", "删除失败！");
		}
		
		return rsmap;
	}
}
