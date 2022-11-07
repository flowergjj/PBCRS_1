package com.hkbank.pbcrs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.PbcrsSysConTrollerMapper;
import com.hkbank.pbcrs.service.PbcrsReportModService;
import com.hkbank.pbcrs.service.ReportCmdService;

@Controller
@RequestMapping("/reportMod")
public class ReportModController {
	private static final Logger log = LogManager.getLogger(ReportModController.class);

	@Autowired
	PbcrsReportModService service;
	@Autowired
	ReportCmdService reportCmdService;

	@Autowired
	private PbcrsSysConTrollerMapper sysConTrollerMapper;
	
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

	// 个人修改记录信息
	@ResponseBody
	@RequestMapping("/InlistPage")
	public Map<String, Object> InlistPage(HttpServletRequest request) {

		Map<String, Object> params = Util.parseWebParas(request);

		try {
			return service.InlistPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}

		return null;
	}

	// 报文修改报送
	@ResponseBody
	@RequestMapping("/report")
	public Map<String, Object> report(HttpServletRequest request) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);
		Map<String,Object> con_info_params = new HashMap<String, Object>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				log.error("报文修改报送时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("CreateUser", user.getUserId());
			//controller层进行插入记录，防止事务回滚
			Map<String,Object> map = new HashMap<String, Object>();
			String web_proc_id = UUID.randomUUID().toString();
			con_info_params.put("infRecType", params.get("reportId").toString());
			con_info_params.put("insertUserName", user.getUserName());
			con_info_params.put("insertUserNo", user.getUserId());
			con_info_params.put("webProcId", web_proc_id);
			con_info_params.put("state", "0");
			con_info_params.put("etlDate", params.get("etl_date").toString().replaceAll("-", ""));
			sysConTrollerMapper.insertConInfo(con_info_params);
			params.put("webProcId", web_proc_id);
			params.put("reportFlag","0");
			List<String> conditions = reportCmdService.reportGetFile(params);
			//将socket调用提取到controller层防止事务的影响导致读取不到condition表信息
			if (conditions != null && conditions.size()>0) {
				for (String condition : conditions) {
					try {
						reportCmdService.socket(condition+"REPORT_SOURCE_S");
					} catch (Throwable e) {
						e.printStackTrace();
						throw new Exception("连接错误");
					}

				}
			}
			rslt.put("RET_CODE", "SUCCESS");
			con_info_params.put("state", "1");
			sysConTrollerMapper.updSysConInfo(con_info_params);
		} catch (Exception e) {
			log.error("报文修改报送异常!", e);
			rslt.put("RET_CODE", "FAILED");
			if (e.getMessage().contains("连接错误")) {
				rslt.put("ERR_MSG", "报送程序未正常启动,请联系管理员！");
			}
			if (e.getMessage().contains("未查询到指定报送日期数据")) {
				rslt.put("ERR_MSG", "未查询到指定报送日期数据！");
			}

			con_info_params.put("state", "2");
			sysConTrollerMapper.updSysConInfo(con_info_params);
			return rslt;
		}

		return rslt;
	}

	// 企业整笔删除查询
	@ResponseBody
	@RequestMapping("/listPageDel")
	public Map<String, Object> listPageDel(HttpServletRequest request) {

		Map<String, Object> params = Util.parseWebParas(request);

		try {
			return service.listPageDel(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}

		return null;
	}

	// 个人删除记录信息
	@ResponseBody
	@RequestMapping("/InDeletelistPage")
	public Map<String, Object> InDeletelistPage(HttpServletRequest request) {

		Map<String, Object> params = Util.parseWebParas(request);
		try {
			return service.InDeletelistPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}

		return null;
	}

	// 企业更正查询
	@ResponseBody
	@RequestMapping("/listPageUpdSub")
	public Map<String, Object> listPageUpdSub(HttpServletRequest request) {

		Map<String, Object> params = Util.parseWebParas(request);

		try {
			return service.listPageUpdSub(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}

		return null;
	}

	// 个人更正记录信息
	@ResponseBody
	@RequestMapping("/updSublistPage")
	public Map<String, Object> updSublistPage(HttpServletRequest request) {

		Map<String, Object> params = Util.parseWebParas(request);

		try {
			return service.updSublistPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}

		return null;
	}

	// 查询反馈信息
	@ResponseBody
	@RequestMapping("/checkMsg")
	public Map<String, Object> checkMsg(HttpServletRequest request) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			Map<String, Object> checkMsg = service.checkMsg(params);
			if (checkMsg == null || checkMsg.isEmpty()) {
				rslt.put("RET_CODE", "FAILED");
			} else {
				rslt.put("RET_CODE", "SUCCESS");
				rslt.put("condtionId", checkMsg.get("CONDTIONID"));
				if (params.containsKey("BADNAME")) {
					rslt.put("badName", FilenameUtils.getName(checkMsg.get("BADNAME").toString()));
				} else {
					rslt.put("badName", "");
				}

			}
		} catch (Exception e) {
			log.error("查询反馈信息出错!", e);
			rslt.put("RET_CODE", "FAILED");
			return rslt;
		}

		return rslt;
	}

	@ResponseBody
	@RequestMapping("/getLogInfo")
	public Map<String, Object> getLogInfo(HttpServletRequest request) {

		Map<String, Object> params = Util.parseWebParas(request);

		try {
			return service.getLogInfo(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}

		return null;
	}

	// 个人删除记录信息
	@ResponseBody
	@RequestMapping("/byInParagraphDeletePage")
	public Map<String, Object> byInParagraphDeletePage(HttpServletRequest request) {

		Map<String, Object> params = Util.parseWebParas(request);
		try {
			return service.byInParagraphDeletePage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}

		return null;
	}
	
	// 企业删除记录信息
		@ResponseBody
		@RequestMapping("/byEnParagraphDeletePage")
		public Map<String, Object> byEnParagraphDeletePage(HttpServletRequest request) {

			Map<String, Object> params = Util.parseWebParas(request);
			try {
				return service.byEnParagraphDeletePage(params);
			} catch (Exception e) {
				log.error("列表查询出现异常!", e);
			}

			return null;
		}

}
