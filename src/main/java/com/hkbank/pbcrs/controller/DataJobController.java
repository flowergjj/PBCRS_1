package com.hkbank.pbcrs.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
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
import com.hkbank.pbcrs.service.DataJobService;

@Controller
@RequestMapping("/datajob")
public class DataJobController {
	
	private static final Logger log = LogManager.getLogger(DataJobController.class);
	@Autowired
	private DataJobService dataJobService;
	@ResponseBody
	@RequestMapping("/create")
	public Map<String, Object> download(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(request);
		Object scoreDate = params.get("scoreDate");
		try {
			String indScoreData = dataJobService.getIndScoreData(params);
			byte[] buffer = indScoreData.getBytes();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("个人量化评分"+scoreDate.toString().replace("-", "")+".txt", "UTF-8"));
			response.addHeader("Content-Length", "" + buffer.length);
			OutputStream ous = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			ous.write(buffer);
			ous.flush();
			ous.close();
		} catch (Exception e) {
			log.error("生成出错",e);
			e.printStackTrace();
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("RET_CODE", "FAILED");
			rslt.put("MSG", "生成出错!");
			return rslt;
		}
		return null;
	}
}
