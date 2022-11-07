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

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.model.PbcrsReportBssgmt;
import com.hkbank.pbcrs.model.PbcrsReportCreditlimsgmt;
import com.hkbank.pbcrs.model.PbcrsReportCtrctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportCtrctcertrel;
import com.hkbank.pbcrs.model.PbcrsReportCtrctcertrelsgmt;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportInctrctinf;
import com.hkbank.pbcrs.model.PbcrsReportInsecacctinf;
import com.hkbank.pbcrs.service.InCtrctInfService;
/**
 * 个人授信信息
 * @author admin
 *
 */
@Controller
@RequestMapping("/InCtrctInf")
public class InCtrctInfController {
	@Autowired
	private InCtrctInfService service;
	private static final Logger log = LogManager.getLogger(InBasInfoController.class);

	// 查询页面列表
	@RequestMapping("/getDataList")
	@ResponseBody
	public Map<String, Object> getDataList(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return service.getInCtrctInfList(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	

	

	// 查询基础段
	@RequestMapping("/getCtrctBsSgmt")
	@ResponseBody
	public PbcrsReportCtrctbssgmt getCtrctBsSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String CtrctBsSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getCtrctBsSgmt(CtrctBsSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 额度信息段
	@RequestMapping("/getCreditLimSgmt")
	@ResponseBody
	public PbcrsReportCreditlimsgmt getCreditLimSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String CreditLimSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getCreditLimSgmt(CreditLimSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 共同受信人信息
	@RequestMapping("/getCtrctCertRelSgmt")
	@ResponseBody
	public Map<String, Object> getCtrctCertRelSgmt(HttpServletRequest request) {
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			return service.getCtrctCertRelSgmt(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 共同受信人信息详细
	@RequestMapping("/getCtrctCertRel")
	@ResponseBody
	public PbcrsReportCtrctcertrel getCtrctCertRel(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);

		try {
			return service.getCtrctCertRel(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 修改额度信息段
	@RequestMapping("/updateCreditLimSgmt")
	@ResponseBody
	public Map<String, Object> updateCreditLimSgmt(HttpServletRequest request, PbcrsReportCreditlimsgmt CreditLimSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("CreditLimSgmt", CreditLimSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateCreditLimSgmt(param);
			if (count != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else {
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}

		return rslt;

	}

	// 修改共同受信人信息
	@RequestMapping("/updateCtrctCertRelSgmt")
	@ResponseBody
	public Map<String, Object> updateCtrctCertRelSgmt(HttpServletRequest request, PbcrsReportCtrctcertrel CtrctCertRel) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("CtrctCertRel", CtrctCertRel);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateCtrctCertRelSgmt(param);
			if (count != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else {
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}

		return rslt;

	}

	// 修改个人授信协议信息记录基础段
	@RequestMapping("/updateCtrctBsSgmt")
	@ResponseBody
	public Map<String, Object> updateCtrctBsSgmt(HttpServletRequest request, PbcrsReportCtrctbssgmt CtrctBsSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//CtrctBsSgmt.setIDType(param.get("idtype").toString());
			//CtrctBsSgmt.setIDNum(param.get("idnum").toString());
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			//param.put("CtrctBsSgmt", CtrctBsSgmt);
			int count = service.updateCtrctBsSgmt(param);
			if (count != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else {
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}

		return rslt;

	}
	 // 整笔删除
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,
			PbcrsReportBssgmt BsSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int row=service.delete(param);
			if (row != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}

		return rslt;

	}
	
	 // 按段删除
	@RequestMapping("/deleteform")
	@ResponseBody
	public Map<String, Object> deleteform(HttpServletRequest request,
			PbcrsReportBssgmt BsSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int row=service.deleteform(param);
			if (row != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			rslt.put("RET_CODE", "FAILED");
			log.error("加入按段删除表失败!", e);
		}

		return rslt;

	}
	

	@RequestMapping("/getCount")
	@ResponseBody
	public Map<String, Object> getCount(HttpServletRequest request) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int row=service.getCount(param);
			rslt.put("count", row);
		} catch (Exception e) {
			rslt.put("RET_CODE", "FAILED");
			log.error("加入按段删除表失败!", e);
		}

		return rslt;

	}
}
