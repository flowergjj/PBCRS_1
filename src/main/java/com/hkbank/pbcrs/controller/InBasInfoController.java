package com.hkbank.pbcrs.controller;

import java.util.HashMap;
import java.util.List;
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
import com.hkbank.pbcrs.model.PbcrsReportEduinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportFcsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportIdrec;
import com.hkbank.pbcrs.model.PbcrsReportInbasinfo;
import com.hkbank.pbcrs.model.PbcrsReportIncinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMlginfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportOctpninfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportRedncinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpsinfsgmt;
import com.hkbank.pbcrs.service.InBasInfoService;

/**
 * 个人基本信息web
 * 
 * @author hs1112
 * 
 */
@Controller
@RequestMapping("/InBasInfo")
@SuppressWarnings("all")
public class InBasInfoController {
	@Autowired
	private InBasInfoService service;
	private static final Logger log = LogManager
			.getLogger(InBasInfoController.class);

	// 查询页面列表
	@RequestMapping("/getDataList")
	@ResponseBody
	public Map<String, Object> getDataList(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
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

	// 查询基础概况段
	@RequestMapping("/getFcsInfSgmtInfo")
	@ResponseBody
	public PbcrsReportFcsinfsgmt getFcsInfSgmtInfo(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String fcsInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getFcsinfsgmtInfo(fcsInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	

	// 查询基础段
	@RequestMapping("/getBsSgmtInfo")
	@ResponseBody
	public PbcrsReportBssgmt getBssgmtInfo(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String BsSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getBssgmtInfo(BsSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 婚姻信息

	@RequestMapping("/getSpsInfSgmtInfo")
	@ResponseBody
	public PbcrsReportSpsinfsgmt getSpsinfsgmtInfo(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String SpsInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getSpsinfsgmtInfo(SpsInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 教育信息
	@RequestMapping("/getEduInfSgmtInfo")
	@ResponseBody
	public PbcrsReportEduinfsgmt getEduinfsgmtInfo(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String EduInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getEduinfsgmtInfo(EduInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 职业段信息
	@RequestMapping("/getOctpnInfSgmtInfo")
	@ResponseBody
	public PbcrsReportOctpninfsgmt getOctpninfsgmtInfo(
			HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String OctpnInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getOctpninfsgmtInfo(OctpnInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 居住地址信息
	@RequestMapping("/getRedncInfSgmtInfo")
	@ResponseBody
	public PbcrsReportRedncinfsgmt getRedncinfsgmtInfo(
			HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String RedncInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getRedncinfsgmtInfo(RedncInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 通信地址信息
	@RequestMapping("/getMlgInfSgmtInfo")
	@ResponseBody
	public PbcrsReportMlginfsgmt getMlginfsgmtInfo(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String MlgInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getMlginfsgmtInfo(MlgInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 收入信息
	@RequestMapping("/getIncInfSgmtInfo")
	@ResponseBody
	public PbcrsReportIncinfsgmt getIncinfsgmtInfo(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String IncInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getIncinfsgmtInfo(IncInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}
		// 查询其他标示单个详细信息
		@RequestMapping("/getIDRec")
		@ResponseBody
		public PbcrsReportIdrec getIDrecBySeqAndId(HttpServletRequest request) {
			Map<String, Object> param = Util.parseWebParas(request);
			
			try {
				return service.getIDrecBySeqAndId(param);
			} catch (Exception e) {
				log.error("列表查询出现异常!", e);
			}
			return null;
		}
	// 其他标示信息
	@RequestMapping("/getIdSgmtInfo")
	@ResponseBody
	public Map<String, Object> getIdSgmtInfo(HttpServletRequest request) {

		try {

			Map<String, Object> param = Util.parseWebParas(request);
			return service.getIdrecInfo(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 修改基本概况段
	@RequestMapping("/updateFcsInfSgmt")
	@ResponseBody
	public Map<String, Object> updateFcsInfSgmt(HttpServletRequest request,
			PbcrsReportFcsinfsgmt FcsInfSgmt) {
		
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			
			
			
			Map<String, Object> param = Util.parseWebParas(request);
			//FcsInfSgmt.setDOB(param.get("dob").toString());
			//param.put("FcsInfSgmt", FcsInfSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int row=service.updateFcsInfSgmt(param);
			if (row != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			rslt.put("RET_CODE", "FAILED");
			log.error("列表查询出现异常!", e);
		}

		return rslt;

	}
	 // 修改婚姻信息段
		@RequestMapping("/updateSpsInfSgmt")
		@ResponseBody
		public Map<String, Object> updateSpsInfSgmt(HttpServletRequest request,
				PbcrsReportSpsinfsgmt SpsInfSgmt) {
			Map<String, Object> rslt = new HashMap<String, Object>();
			try {
				Map<String, Object> param = Util.parseWebParas(request);
				
				//param.put("SpsInfSgmt", SpsInfSgmt);
				User user =	(User)request.getSession().getAttribute("user");
				param.put("userid", user.getUserId());
				int row=service.updateSpsInfSgmt(param);
				if (row != 1) {
					rslt.put("RET_CODE", "FAILED");
				} else{
					rslt.put("RET_CODE", "SUCCESS");
				}
			} catch (Exception e) {
				rslt.put("RET_CODE", "FAILED");
				log.error("列表查询出现异常!", e);
			}

			return rslt;

		}
		 // 修改教育信息段
		@RequestMapping("/updateEduInfSgmt")
		@ResponseBody
		public Map<String, Object> updateEduInfSgmt(HttpServletRequest request,
				PbcrsReportEduinfsgmt EduInfSgmt) {
			Map<String, Object> rslt = new HashMap<String, Object>();
			try {
				Map<String, Object> param = Util.parseWebParas(request);
			
				//param.put("EduInfSgmt", EduInfSgmt);
				User user =	(User)request.getSession().getAttribute("user");
				param.put("userid", user.getUserId());
				int row=service.updateEduInfSgmt(param);
				if (row != 1) {
					rslt.put("RET_CODE", "FAILED");
				} else{
					rslt.put("RET_CODE", "SUCCESS");
				}
			} catch (Exception e) {
				rslt.put("RET_CODE", "FAILED");
				log.error("列表查询出现异常!", e);
			}

			return rslt;

		}
		
		 // 修改其他标示段
		@RequestMapping("/updateIdSgmt")
		@ResponseBody
		public Map<String, Object> updateIdSgmt(HttpServletRequest request,
				PbcrsReportIdrec IDRec) {
			Map<String, Object> rslt = new HashMap<String, Object>();
			try {
				Map<String, Object> param = Util.parseWebParas(request);
				//IDRec.setIDSgmtSeqNo(param.get("idsgmtSeqNo").toString());
				//IDRec.setIDSgmtListSeqNo(param.get("idsgmtListSeqNo").toString());
				//param.put("IDRec", IDRec);
				User user =	(User)request.getSession().getAttribute("user");
				param.put("userid", user.getUserId());
				int row=service.updateIdSgmt(param);
				if (row != 1) {
					rslt.put("RET_CODE", "FAILED");
				} else{
					rslt.put("RET_CODE", "SUCCESS");
				}
			} catch (Exception e) {
				rslt.put("RET_CODE", "FAILED");
				log.error("列表查询出现异常!", e);
			}

			return rslt;

		}
		 // 修改职业信息段
			@RequestMapping("/updateOctpnInfSgmt")
			@ResponseBody
			public Map<String, Object> updateOctpnInfSgmt(HttpServletRequest request,
					PbcrsReportOctpninfsgmt OctpnInfSgmt) {
				Map<String, Object> rslt = new HashMap<String, Object>();
				try {
					Map<String, Object> param = Util.parseWebParas(request);
					
					//param.put("OctpnInfSgmt", OctpnInfSgmt);
					User user =	(User)request.getSession().getAttribute("user");
					param.put("userid", user.getUserId());
					int row=service.updateOctpnInfSgmt(param);

					if (row != 1) {
						rslt.put("RET_CODE", "FAILED");
					} else{
						rslt.put("RET_CODE", "SUCCESS");
					}
				} catch (Exception e) {
					rslt.put("RET_CODE", "FAILED");
					log.error("列表查询出现异常!", e);
				}

				return rslt;

			}
			 // 修改居住地址段
			@RequestMapping("/updateRedncInfSgmt")
			@ResponseBody
			public Map<String, Object> updateRedncInfSgmt(HttpServletRequest request,
					PbcrsReportRedncinfsgmt RedncInfSgmt) {
				Map<String, Object> rslt = new HashMap<String, Object>();
				try {
					Map<String, Object> param = Util.parseWebParas(request);
					
					//param.put("RedncInfSgmt", RedncInfSgmt);
					User user =	(User)request.getSession().getAttribute("user");
					param.put("userid", user.getUserId());
					int row=service.updateRedncInfSgmt(param);

					if (row != 1) {
						rslt.put("RET_CODE", "FAILED");
					} else{
						rslt.put("RET_CODE", "SUCCESS");
					}
				} catch (Exception e) {
					rslt.put("RET_CODE", "FAILED");
					log.error("列表查询出现异常!", e);
				}

				return rslt;

			}
			 // 修改通讯地址段
			@RequestMapping("/updateMlgInfSgmt")
			@ResponseBody
			public Map<String, Object> updateMlgInfSgmt(HttpServletRequest request,
					PbcrsReportMlginfsgmt MlgInfSgmt) {
				Map<String, Object> rslt = new HashMap<String, Object>();
				try {
					Map<String, Object> param = Util.parseWebParas(request);
					
					//param.put("MlgInfSgmt", MlgInfSgmt);
					User user =	(User)request.getSession().getAttribute("user");
					param.put("userid", user.getUserId());
					int row=service.updateMlgInfSgmt(param);

					if (row != 1) {
						rslt.put("RET_CODE", "FAILED");
					} else{
						rslt.put("RET_CODE", "SUCCESS");
					}
				} catch (Exception e) {
					rslt.put("RET_CODE", "FAILED");
					log.error("列表查询出现异常!", e);
				}
				return rslt;

			}
			
			 // 修改收入信息段
			@RequestMapping("/updateIncInfSgmt")
			@ResponseBody
			public Map<String, Object> updateIncInfSgmt(HttpServletRequest request,
					PbcrsReportIncinfsgmt IncInfSgmt) {
				Map<String, Object> rslt = new HashMap<String, Object>();
				try {
					Map<String, Object> param = Util.parseWebParas(request);
					
					//param.put("IncInfSgmt", IncInfSgmt);
					User user =	(User)request.getSession().getAttribute("user");
					param.put("userid", user.getUserId());
					int row=service.updateIncInfSgmt(param);

					if (row != 1) {
						rslt.put("RET_CODE", "FAILED");
					} else{
						rslt.put("RET_CODE", "SUCCESS");
					}
				} catch (Exception e) {
					rslt.put("RET_CODE", "FAILED");
					log.error("列表查询出现异常!", e);
				}

				return rslt;

			}
			
			 // 修改基本信息段
			@RequestMapping("/updateBsSgmt")
			@ResponseBody
			public Map<String, Object> updateBsSgmt(HttpServletRequest request,
					PbcrsReportBssgmt BsSgmt) {
				Map<String, Object> rslt = new HashMap<String, Object>();
				try {
					Map<String, Object> param = Util.parseWebParas(request);
					//BsSgmt.setIDType(param.get("idtype").toString());
					//BsSgmt.setIDNum(param.get("idnum").toString());
					//param.put("BsSgmt", BsSgmt);
					User user =	(User)request.getSession().getAttribute("user");
					param.put("userid", user.getUserId());
					int row=service.updateBsSgmt(param);
					if (row != 1) {
						rslt.put("RET_CODE", "FAILED");
					} else{
						rslt.put("RET_CODE", "SUCCESS");
					}
				} catch (Exception e) {
					rslt.put("RET_CODE", "FAILED");
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
			
			
			

}
