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
import com.hkbank.pbcrs.model.PbcrsReportGsrltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportImpinf;
import com.hkbank.pbcrs.model.PbcrsReportInacctinf;
import com.hkbank.pbcrs.model.PbcrsReportMotcltbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMotcltctrbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportMotcltctrinf;
import com.hkbank.pbcrs.model.PbcrsReportOtrrec;
import com.hkbank.pbcrs.model.PbcrsReportPleinf;
import com.hkbank.pbcrs.service.MotCltCtrInfService;

@Controller
@RequestMapping("/MotCltCtrInf")
@SuppressWarnings("all")
public class MotCltCtrInfController {
	@Autowired
	private MotCltCtrInfService service;
	private static final Logger log = LogManager.getLogger(MotCltCtrInfController.class);

	// 查询页面列表
	@RequestMapping("/getDataList")
	@ResponseBody
	public Map<String, Object> getDataList(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String souceSys = param.get("sourceSys").toString();
		if (souceSys.length() > 0) {
			String[] temp = souceSys.split(",");
			String str = "";
			if (temp.length > 1) {
				for (int i = 0; i < temp.length; i++) {
					if (i + 1 == temp.length) {
						str += "'" + temp[i] + "'";
					} else {
						str += "'" + temp[i] + "',";
					}

				}
			} else {
				str = "'" + souceSys + "'";
			}
			param.put("SOURCESYS", str);

		}
		User user =	(User)request.getSession().getAttribute("user");
		if(!user.getOrgId().equals("T")){
			param.put("orgCode",user.getOrgId());
		}
		try {
			return service.getBaseInfoList(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询其他债务人信息段
	@RequestMapping("/getComRecInfSgmt")
	@ResponseBody
	public Map<String, Object> getComRecInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return service.getComRecInfSgmt(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询其他债务人信息详细
	@RequestMapping("/getOtrRec")
	@ResponseBody
	public PbcrsReportOtrrec getOtrRec(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String ComRecInfSgmtSeqNo = param.get("ComRecInfSgmtSeqNo").toString();
		String OtrRecSeqNo = param.get("OtrRecSeqNo").toString();
		try {
			return service.getOtrRec(ComRecInfSgmtSeqNo, OtrRecSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询抵押物信息段
	@RequestMapping("/getMotgaProptInfSgmt")
	@ResponseBody
	public Map<String, Object> getMotgaProptInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return service.getMotgaProptInfSgmt(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询抵押物信息详细
	@RequestMapping("/getPleInf")
	@ResponseBody
	public PbcrsReportPleinf getPleInf(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String ComRecInfSgmtSeqNo = param.get("ComRecInfSgmtSeqNo").toString();
		String PleInfSeqNo = param.get("PleInfSeqNo").toString();
		try {
			return service.getPleInf(ComRecInfSgmtSeqNo, PleInfSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询质物信息段
	@RequestMapping("/getCltalInfSgmt")
	@ResponseBody
	public Map<String, Object> getCltalInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return service.getCltalInfSgmt(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询 查询质物信息详细
	@RequestMapping("/getImpInf")
	@ResponseBody
	public PbcrsReportImpinf getImpInf(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String CltalInfSgmtSeqNo = param.get("CltalInfSgmtSeqNo").toString();
		String ImpInfSeqNo = param.get("ImpInfSeqNo").toString();
		try {
			return service.getImpInf(CltalInfSgmtSeqNo, ImpInfSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询基本信息段
	@RequestMapping("/getMotCltBsInfSgmt")
	@ResponseBody
	public PbcrsReportMotcltbsinfsgmt getMotCltBsInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String MotgaCltalBsInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getMotCltBsInfSgmt(MotgaCltalBsInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询基础段
	@RequestMapping("/getMotCltCtrBsSgmt")
	@ResponseBody
	public PbcrsReportMotcltctrbssgmt getMotCltCtrBsSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String MotgaCltalCtrctBsSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getMotCltCtrBsSgmt(MotgaCltalCtrctBsSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 修改基本信息段
	@RequestMapping("/updateMotCltBsInfSgmt")
	@ResponseBody
	public Map<String, Object> updateMotCltBsInfSgmt(HttpServletRequest request, PbcrsReportMotcltbsinfsgmt MotCltBsInfSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("MotCltBsInfSgmt", MotCltBsInfSgmt);
			User user = (User) request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateMotCltBsInfSgmt(param);

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

	// 修改其他债务人信息段
	@RequestMapping("/updateComRecInfSgmt")
	@ResponseBody
	public Map<String, Object> updateComRecInfSgmt(HttpServletRequest request, PbcrsReportOtrrec OtrRec) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("OtrRec", OtrRec);
			User user = (User) request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateComRecInfSgmt(param);

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
	@RequestMapping("/updateMotCltCtrBsSgmt")
	@ResponseBody
	public Map<String, Object> updateMotCltCtrBsSgmt(HttpServletRequest request, PbcrsReportMotcltctrbssgmt MotCltCtrBsSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("MotCltCtrBsSgmt", MotCltCtrBsSgmt);
			User user = (User) request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateMotCltCtrBsSgmt(param);

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

	// 修改抵押物信息段
	@RequestMapping("/updateMotgaProptInfSgmt")
	@ResponseBody
	public Map<String, Object> updateMotgaProptInfSgmt(HttpServletRequest request, PbcrsReportPleinf PleInf) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("PleInf", PleInf);
			User user = (User) request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateMotgaProptInfSgmt(param);

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

	// 修改质物信息段
	@RequestMapping("/updateCltalInfSgmt")
	@ResponseBody
	public Map<String, Object> updateCltalInfSgmt(HttpServletRequest request, PbcrsReportImpinf ImpInf) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("ImpInf", ImpInf);
			User user = (User) request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateCltalInfSgmt(param);

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
	public Map<String, Object> delete(HttpServletRequest request, PbcrsReportBssgmt BsSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user = (User) request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int row = service.delete(param);
			if (row != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else {
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}

		return rslt;

	}

}
