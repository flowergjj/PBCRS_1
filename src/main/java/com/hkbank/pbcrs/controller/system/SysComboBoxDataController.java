package com.hkbank.pbcrs.controller.system;

import com.hkbank.pbcrs.service.system.SysComboBoxDataService;
import com.hkbank.pbcrs.util.Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/SysComboBoxData")
public class SysComboBoxDataController {

	private static final Logger log = LogManager
			.getLogger(SysComboBoxDataController.class);

	@Autowired
	private SysComboBoxDataService sysComboBoxDataService;

	@RequestMapping(value = "/getComboboxDataList")
	@ResponseBody
	public List<Map<String, Object>> getComboboxDataList(
			HttpServletRequest req, HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(req);

		List<Map<String, Object>> rslt = null;
		try {
			rslt = sysComboBoxDataService.getComboboxDataList(params);
		} catch (Throwable t) {
			log.error("", t);
		}
		return rslt;
	}

	@RequestMapping(value = "/getBussTypeList")
	@ResponseBody
	public List<Map<String, Object>> getBussTypeList(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(req);

		List<Map<String, Object>> rslt = null;
		try {
			rslt = sysComboBoxDataService.getBussTypeList(params);
		} catch (Throwable t) {
			log.error("", t);
		}
		return rslt;
	}
	

	@RequestMapping(value = "/getChannelList")
	@ResponseBody
	public List<Map<String, Object>> getChannelList(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(req);

		List<Map<String, Object>> rslt = null;
		try {
			rslt = sysComboBoxDataService.getChannelList(params);
		} catch (Throwable t) {
			log.error("", t);
		}
		return rslt;
	}
	
	
}