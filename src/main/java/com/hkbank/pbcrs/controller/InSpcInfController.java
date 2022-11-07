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
import com.hkbank.pbcrs.model.PbcrsReportInspcevtdscinf;
import com.hkbank.pbcrs.service.InSpcInfService;

/**
 * 个人信贷特殊事件
 * 
 * @author hs1112
 * 
 */
@Controller
@RequestMapping("/InSpcInf")
public class InSpcInfController {
	private Logger log = LogManager.getLogger(InSpcInfController.class);
	@Autowired
	private InSpcInfService inSpcInfService;

	@ResponseBody
	@RequestMapping("/selectPageList")
	public Map<String, Object> selectPageList(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			Map<String, Object> rsltMap = inSpcInfService.selectPageList(params);
			return rsltMap;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询失败!", e);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/updateInfo")
	public Map<String, Object> updateInfo(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			//PbcrsReportInspcevtdscinf inSpcInf = (PbcrsReportInspcevtdscinf) Util.map2Object2(params, PbcrsReportInspcevtdscinf.class);
			Map<String, Object> rslt = new HashMap<String, Object>();
			int updateRows = inSpcInfService.updateInfo(params);
			if(updateRows!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
			return rslt;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询失败!", e);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/deleteInfo")
	public Map<String, Object> deleteInfo(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			
			Map<String, Object> rslt = new HashMap<String, Object>();
			int deleteRows = inSpcInfService.deleteInfo(params);
			if(deleteRows!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
			return rslt;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询失败!", e);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/insertInfo")
	public Map<String, Object> insertInfo(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			Map<String, Object> rslt = new HashMap<String, Object>();
			int insertRows = inSpcInfService.insertInfo(params);
			if(insertRows!=1){
				rslt.put("RET_CODE", "FAILED");
			}else{
				rslt.put("RET_CODE", "SUCCESS");
			}
			return rslt;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询失败!", e);
		}
		return null;
	}

}
