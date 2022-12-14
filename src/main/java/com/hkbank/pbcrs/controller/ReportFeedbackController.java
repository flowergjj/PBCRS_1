package com.hkbank.pbcrs.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.PbcrsReportSendfileService;

@Controller
@RequestMapping("/feedBack")
public class ReportFeedbackController {
	private static final Logger log = LogManager.getLogger(ReportFeedbackController.class);

@Autowired
private PbcrsReportSendfileService service;
	@ResponseBody
	@RequestMapping("/getFeed")
	public Map<String, Object> getFeed(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.getFeed(params);
		} catch (Exception e) {
			log.error("文件列表查询出现异常!", e);
		}
		
		return null;
	}

}
