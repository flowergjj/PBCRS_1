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

import com.hkbank.pbcrs.util.DateUtils;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.DataComparisonEndService;

@Controller
@RequestMapping("/compareEnds")
public class DataComparisonEndController {
	private static final Logger log = LogManager.getLogger(DataComparisonEndController.class);

	@Autowired
	private DataComparisonEndService compareService;
	@ResponseBody
	@RequestMapping("/create")
	public Map<String, Object> download(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(request);
		Object queryRptDate = params.get("queryRptDate");
		try {
			params.put("filename","QYD10025210H0005B"+queryRptDate.toString().replace("-", "")+".csv");
			String compareData = compareService.getEntCompare(params);
			byte[] buffer = compareData.getBytes();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("QYD10025210H0005B"+queryRptDate.toString().replace("-", "")+".csv", "UTF-8"));
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
	
	@ResponseBody
	@RequestMapping("/createInter")
	public Map<String, Object> downloadInter(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(request);
		Object queryRptDate = params.get("queryRptDate");
/*		String strDate = DateUtils.addDay(DateUtils.getCurrDateTime(), -1, "yyyyMMdd");
		System.out.println(strDate);*/
		try {
			params.put("filename","QYD10025210H0005B"+queryRptDate.toString().replace("-", "")+".csv");
			String compareData = compareService.getEntCompareByInter(params);
			byte[] buffer = compareData.getBytes();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("QYD10025210H0005B"+queryRptDate.toString().replace("-", "")+".csv", "UTF-8"));
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
	
	@ResponseBody
	@RequestMapping("/listPage")
	public Map<String, Object> listPage(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return compareService.listPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listDetailPage")
	public Map<String, Object> listDetailPage(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return compareService.listDetailPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}

	
	@ResponseBody
	@RequestMapping("/createByCompareNo")
	public Map<String, Object> createByCompareNo(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(request);
		Object compareNo = params.get("compareNo");
		try {
			params.put("filename","QYD10025210H0005B"+compareNo.toString()+".csv");
			String compareData = compareService.getEntByNo(params);
			byte[] buffer = compareData.getBytes();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("QYD10025210H0005B"+compareNo+".csv", "UTF-8"));
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
	
	@ResponseBody
	@RequestMapping("/upd")
	public Map<String, Object> upd(HttpServletRequest request) {
		
		Map<String,Object> rsmap = new HashMap<String, Object>();

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			compareService.upd(params);
		 rsmap.put("RET_CODE", "SUCCESS");
	     rsmap.put("RET_MSG", "修改成功！");
		} catch (Exception e) {
			log.error("修改异常!", e);
			rsmap.put("RET_CODE", "FAILED");
			rsmap.put("RET_MSG", "操作失败！");
		}
		
		return rsmap;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(HttpServletRequest request) {
		
		Map<String,Object> rsmap = new HashMap<String, Object>();

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			compareService.add(params);
		 rsmap.put("RET_CODE", "SUCCESS");
	     rsmap.put("RET_MSG", "添加成功！");
		} catch (Exception e) {
			log.error("添加异常!", e);
			rsmap.put("RET_CODE", "FAILED");
			rsmap.put("RET_MSG", "操作失败！");
		}
		
		return rsmap;
	}
}