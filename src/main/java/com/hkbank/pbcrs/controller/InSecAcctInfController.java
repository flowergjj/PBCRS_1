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
import com.hkbank.pbcrs.model.PbcrsReportGsrltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportGsrltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportGuarltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportInbasinfo;
import com.hkbank.pbcrs.model.PbcrsReportInsecacctinf;
import com.hkbank.pbcrs.model.PbcrsReportSpsinfsgmt;
import com.hkbank.pbcrs.service.InBasInfoService;
import com.hkbank.pbcrs.service.InSecAcctInfService;

/**
 * 个人担保信息
 * 
 * @author hs1112
 * 
 */
@Controller
@RequestMapping("/InSecAcctInf")
@SuppressWarnings("all")
public class InSecAcctInfController {
	@Autowired
	private InSecAcctInfService service;
	private static final Logger log = LogManager.getLogger(InSecAcctInfController.class);

	// 查询页面列表
	@RequestMapping("/getDataList")
	@ResponseBody
	public Map<String, Object> getDataList(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return service.getBaseInfoList(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	

	// 查询基础段
	@RequestMapping("/getGuarAcctBsSgmt")
	@ResponseBody
	public PbcrsReportGuaracctbssgmt getGuarAcctBsSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String GuarAcctBsSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getGuaracctbssgmt(GuarAcctBsSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询基本信息段
	@RequestMapping("/getGuarAcctBsInfSgmt")
	@ResponseBody
	public PbcrsReportGuaracctbsinfsgmt getGuarAcctBsInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String GuarAcctBsInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getGuaracctbsinfsgmt(GuarAcctBsInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询在保责任信息段
	@RequestMapping("/getGuaRltRepInfSgmt")
	@ResponseBody
	public PbcrsReportGuarltrepinfsgmt getGuaRltRepInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String GuarRltRepymtInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getGuarltrepinfsgmt(GuarRltRepymtInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询相关还款责任人段
	@RequestMapping("/getGsRltRepInfSgmt")
	@ResponseBody
	public Map<String, Object> getGsRltRepymtInf(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return service.getGsRltRepInfSgmt(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询相关还款责任人详细
	@RequestMapping("/getGsRltRepymtInf")
	@ResponseBody
	public PbcrsReportGsrltrepymtinf getGsRltRepInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return service.getGsrltrepymtinf(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询抵质押物信息段
	@RequestMapping("/getGuaMotCltCtrSgmt")
	@ResponseBody
	public Map<String, Object> getGuaMotCltCtrSgmtf(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return service.getGuaMotCltCtrSgmt(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 修改基本信息段
	@RequestMapping("/updateGuarAcctBsInfSgmt")
	@ResponseBody
	public Map<String, Object> updateGuarAcctBsInfSgmt(HttpServletRequest request, PbcrsReportGuaracctbsinfsgmt GuarAcctBsInfSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			param.put("GuarAcctBsInfSgmt", GuarAcctBsInfSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateGuaracctbsinfsgmt(param);
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

	// 修改在保责任信息段
	@RequestMapping("/updateGuaRltRepInfSgmt")
	@ResponseBody
	public Map<String, Object> updateGuaRltRepInfSgmt(HttpServletRequest request, PbcrsReportGuarltrepinfsgmt GuaRltRepInfSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			param.put("GuaRltRepInfSgmt", GuaRltRepInfSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateGuaRltRepInfSgmt(param);

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

	// 修改基础段
	@RequestMapping("/updateGuarAcctBsSgmt")
	@ResponseBody
	public Map<String, Object> updateGuaRltRepInfSgmt(HttpServletRequest request, PbcrsReportGuaracctbssgmt GuarAcctBsSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			GuarAcctBsSgmt.setIDType(param.get("idtype").toString());
			GuarAcctBsSgmt.setIDNum(param.get("idnum").toString());
			param.put("GuarAcctBsSgmt", GuarAcctBsSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateGuarAcctBsSgmt(param);
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

	// 修改相关还款责任人段
	@RequestMapping("/updateGsRltRepInfSgmt")
	@ResponseBody
	public Map<String, Object> updateGsRltRepInfSgmt(HttpServletRequest request, PbcrsReportGsrltrepymtinf GsRltRepymtInf) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			param.put("GsRltRepymtInf", GsRltRepymtInf);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateGsRltRepInfSgmt(param);

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
}
