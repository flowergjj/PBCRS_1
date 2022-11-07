package com.hkbank.pbcrs.controller.system;


import com.hkbank.pbcrs.exception.EismException;
import com.hkbank.pbcrs.service.system.SystemNoticeService;
import com.hkbank.pbcrs.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/systemNoticeManage")
public class SystemNoticeManageController {

	private Logger log = LogManager
			.getLogger(SystemNoticeManageController.class);

	@Autowired
	private SystemNoticeService systemNoticeService;

	@RequestMapping(value = "/noticeList")
	@ResponseBody
	public Map<String, Object> noticeList(HttpServletRequest request,
			HttpServletResponse response) {
		return systemNoticeService.noticeList(request);
	}

	@RequestMapping(value = "/saveNotice")
	@ResponseBody
	public Map<String, Object> saveNotice(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> rslt = null;
		try {
			rslt = systemNoticeService.saveNotice(request);
		} catch (Throwable e) {
			log.error("", e);
			rslt = new HashMap<String, Object>();
			rslt.put("RET_CODE", "FAILED");
			rslt.put("RET_MSG", e.getMessage());
		}

		return rslt;
	}

	@RequestMapping(value = "/updateNotice")
	@ResponseBody
	public Map<String, Object> updateNotice(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> rslt = null;
		try {
			rslt = systemNoticeService.updateNotice(request);
		} catch (Throwable e) {
			log.error("", e);
			rslt = new HashMap<String, Object>();
			rslt.put("RET_CODE", "FAILED");
			rslt.put("RET_MSG", e.getMessage());
		}

		return rslt;
	}

	@RequestMapping(value = "/getNoticeByID")
	@ResponseBody
	public Map<String, Object> getNoticeByID(HttpServletRequest req,
			HttpServletResponse response) {
		String notice_id = req.getParameter("NOTICE_ID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("NOTICE_ID", notice_id);
		return systemNoticeService.getNoticeByID(map);
	}

	@RequestMapping(value = "/deleteNotice")
	@ResponseBody
	public Map<String, Object> deleteNotice(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> rslt = null;
		try {
			rslt = systemNoticeService.deleteNotice(request);
		} catch (Exception e) {
			log.error("", e);
			rslt = new HashMap<String, Object>();
			rslt.put("RET_CODE", "FAILED");
			rslt.put("RET_MSG", e.getMessage());
		}
		return rslt;
	}

	@RequestMapping(value = "/findImgList")
	@ResponseBody
	public List<Map<String, Object>> findImgList(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(req);
		log.debug("findImgList, 参数列表={}", params);
		return systemNoticeService.findImgList(params);
	}

	@RequestMapping(value = "/fileDownload")
	@ResponseBody
	public Map<String, Object> fileDownload(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> rslt = new HashMap<String, Object>();

		try {
			systemNoticeService.doFileDownload(request, response);
		} catch (EismException e) {
			log.error("", e);
			rslt.put("RET_CODE", "FAILED");
			rslt.put("RET_MSG", e.getMessage());
		} catch (Throwable e) {
			log.error("文件下载失败", e);
			rslt.put("RET_CODE", "FAILED");
			rslt.put("RET_MSG", e.getMessage());
		}

		return rslt;
	}

	@RequestMapping(value = "/deleteImg")
	@ResponseBody
	public Map<String, Object> deleteImg(HttpServletRequest request,
			HttpServletResponse response) {
		String bussId = request.getParameter("imgId");
		log.debug("准备删除影像文件[{}];", bussId);
		Map<String, Object> rslt = null;
		try {
			systemNoticeService.deleteImg(bussId);
			rslt = new HashMap<String, Object>();
			rslt.put("RET_CODE", "SUCCESS");
			rslt.put("RET_MSG", "附件删除成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("删除附件出现异常!", e);
			rslt = new HashMap<String, Object>();
			rslt.put("RET_CODE", "FAILED");
			rslt.put("RET_MSG", e.getMessage());
		}
		return rslt;
	}

}
