package com.hkbank.pbcrs.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.DataAnalysisTestService;

@Controller
@RequestMapping("/analysistest")
public class DataAnalysisTestController {
	private static final Logger log = LogManager.getLogger(DataAnalysisTestController.class);
	@Autowired
	private DataAnalysisTestService dataAnalysisTestService;

	// 查询页面列表
	@RequestMapping("/getCustomerReportInfo")
	@ResponseBody
	public Map<String, Object> getCustomerReportInfo(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return dataAnalysisTestService.getCustomerReportInfo(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询页面列表
	@RequestMapping("/getBaseInfoAnalysisInfo")
	@ResponseBody
	public Map<String, Object> getBaseInfoAnalysisInfo(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return dataAnalysisTestService.getBaseInfoAnalysisInfo(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}
	
	// 查询页面列表
		@RequestMapping("/getReportFromInfo")
		@ResponseBody
		public Map<String, Object> getReportFromInfo(HttpServletRequest request) {
			Map<String, Object> param = Util.parseWebParas(request);
			try {
				return dataAnalysisTestService.getReportFromInfo(param);
			} catch (Exception e) {
				log.error("列表查询出现异常!", e);
			}
			return null;
		}
	
	
	
}
