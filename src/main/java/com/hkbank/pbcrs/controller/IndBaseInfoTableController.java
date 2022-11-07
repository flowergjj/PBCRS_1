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
import com.hkbank.pbcrs.service.IndBaseInfoTableService;

@Controller
@RequestMapping("/indBaseInfoTable")
public class IndBaseInfoTableController {
	private Logger log = LogManager.getLogger(IndBaseInfoTableController.class);
	
	@Autowired
	private IndBaseInfoTableService service;
	@RequestMapping("getList")
	@ResponseBody
	public Map<String,Object> getList(HttpServletRequest request){
		try {
			Map<String, Object> params = Util.parseWebParas(request);
			Map<String, Object> rslt = service.getList(params);
			return rslt;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询列表失败",e);
		}
		return null;
	}

}
