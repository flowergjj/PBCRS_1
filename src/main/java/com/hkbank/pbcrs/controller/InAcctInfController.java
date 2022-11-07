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
import com.hkbank.pbcrs.model.PbcrsReportAccmthblginfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportAccspetrsdspsgmt;
import com.hkbank.pbcrs.model.PbcrsReportAcctbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportAcctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportAcctcredsgmt;
import com.hkbank.pbcrs.model.PbcrsReportAcctdbtinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportBssgmt;
import com.hkbank.pbcrs.model.PbcrsReportCagoftrdinf;
import com.hkbank.pbcrs.model.PbcrsReportCtrctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportGsrltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportInacctinf;
import com.hkbank.pbcrs.model.PbcrsReportInctrctinf;
import com.hkbank.pbcrs.model.PbcrsReportOricreinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportRltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportRltrepymtinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpecprdsgmt;
import com.hkbank.pbcrs.service.InAcctInfService;
/**
 * 个人借贷信息
 * @author admin
 *
 */
@Controller
@RequestMapping("/InAcctInf")
public class InAcctInfController {
	@Autowired
	private InAcctInfService service;
	private static final Logger log = LogManager.getLogger(InAcctInfController.class);

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
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	

	// 查询查询基础段
	@RequestMapping("/getAcctBsSgmt")
	@ResponseBody
	public PbcrsReportAcctbssgmt getAcctBsSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String AcctBsSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getAcctBsSgmt(AcctBsSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询基本信息段
	@RequestMapping("/getAcctBsInfSgmt")
	@ResponseBody
	public PbcrsReportAcctbsinfsgmt getAcctBsInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String AcctBsInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getAcctBsInfSgmt(AcctBsInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询相关还款责任人信息段
	@RequestMapping("/getRltRepymtInfSgmt")
	@ResponseBody
	public Map<String,Object> getRltRepymtInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
	
		try {
			return service.getRltRepymtInfSgmt(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询相关还款责任人单个详细
	@RequestMapping("/getRltRepymtInf")
	@ResponseBody
	public PbcrsReportRltrepymtinf getRltRepymtInf(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String RltRepymtInfSgmtSeqNo = param.get("RltRepymtInfSgmtSeqNo").toString();
		String RltRepymtInfSeqNo = param.get("RltRepymtInfSeqNo").toString();
		try {
			return service.getRltRepymtInf(RltRepymtInfSgmtSeqNo, RltRepymtInfSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}
	//查询授信额度信息段
	@ResponseBody
	@RequestMapping("/getAcctCredSgmt")
	public PbcrsReportAcctcredsgmt getEnacctcredsgmt(HttpServletRequest request) {
		
		

		Map<String, Object> param = Util.parseWebParas(request);
		String acctCredSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getAcctcredsgmt(acctCredSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	
	}
	// 查询初始债权说明段
	@RequestMapping("/getOriCreInfSgmt")
	@ResponseBody
	public PbcrsReportOricreinfsgmt getOriCreInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String OrigCreditorInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getOriCreInfSgmt(OrigCreditorInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询月度表现信息段
	@RequestMapping("/getAccMthBlgInfSgmt")
	@ResponseBody
	public PbcrsReportAccmthblginfsgmt getAccMthBlgInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String AcctMthlyBlgInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getAccMthBlgInfSgmt(AcctMthlyBlgInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询大额专项分期信息段
	@RequestMapping("/getSpecPrdSgmt")
	@ResponseBody
	public PbcrsReportSpecprdsgmt getSpecPrdSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String SpecPrdSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getSpecPrdSgmt(SpecPrdSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询非月度表现
	@RequestMapping("/getAcctDbtInfSgmt")
	@ResponseBody
	public PbcrsReportAcctdbtinfsgmt getAcctDbtInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String AcctDbtInfSgmtSeqNo = param.get("infoKey").toString();
		try {
			return service.getAcctDbtInfSgmt(AcctDbtInfSgmtSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询特殊交易说明信息段
	@RequestMapping("/getAccSpeTrsDspSgmt")
	@ResponseBody
	public Map<String,Object> getAccSpeTrsDspSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return service.getAccSpeTrsDspSgmt(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询特殊交易说明单个信息
	@RequestMapping("/getCagOfTrdInf")
	@ResponseBody
	public PbcrsReportCagoftrdinf getCagOfTrdInf(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		String AcctSpecTrstDspnSgmtSeqNo = param.get("AcctSpecTrstDspnSgmtSeqNo").toString();
		String CagOfTrdInfSeqNo = param.get("CagOfTrdInfSeqNo").toString();
		try {
			return service.getCagOfTrdInf(AcctSpecTrstDspnSgmtSeqNo, CagOfTrdInfSeqNo);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 查询抵质押物信息段
	@RequestMapping("/getMotCltCtrInfSgmt")
	@ResponseBody
	public Map<String, Object> getMotCltCtrInfSgmt(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		try {
			return service.getMotCltCtrInfSgmt(param);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		return null;
	}

	// 修改基本信息段
	@RequestMapping("/updateAcctBsInfSgmt")
	@ResponseBody
	public Map<String, Object> updateAcctBsInfSgmt(HttpServletRequest request, PbcrsReportAcctbsinfsgmt AcctBsInfSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("AcctBsInfSgmt", AcctBsInfSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateAcctBsInfSgmt(param);

			if (count != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else {
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}

		return rslt;

	}

	// 修改基础段
	@RequestMapping("/updateAcctBsSgmt")
	@ResponseBody
	public Map<String, Object> updateAcctBsSgmt(HttpServletRequest request, PbcrsReportAcctbssgmt AcctBsSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//AcctBsSgmt.setIDType(param.get("idtype").toString());
			//AcctBsSgmt.setIDNum(param.get("idnum").toString());
			//param.put("AcctBsSgmt", AcctBsSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateAcctBsSgmt(param);
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
	@RequestMapping("/updateRltRepymtInfSgmt")
	@ResponseBody
	public Map<String, Object> updateRltRepymtInfSgmt(HttpServletRequest request, PbcrsReportRltrepymtinf RltRepymtInf) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("RltRepymtInf", RltRepymtInf);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateRltRepymtInfSgmt(param);

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

	// 修改授信额度
		@RequestMapping("/updateAcctCredSgmt")
		@ResponseBody
		public Map<String, Object> updateAcctCredSgmt(HttpServletRequest request, PbcrsReportAcctcredsgmt Acctcredsgmt) {
			Map<String, Object> rslt = new HashMap<String, Object>();
			try {
				Map<String, Object> param = Util.parseWebParas(request);
				//param.put("Acctcredsgmt", Acctcredsgmt);
				User user =	(User)request.getSession().getAttribute("user");
				param.put("userid", user.getUserId());
				int count = service.updateAcctCredSgmt(param);
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

	// 修改初始债权说明段
	@RequestMapping("/updateOriCreInfSgmt")
	@ResponseBody
	public Map<String, Object> updateOriCreInfSgmt(HttpServletRequest request, PbcrsReportOricreinfsgmt OriCreInfSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("OriCreInfSgmt", OriCreInfSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateOriCreInfSgmt(param);

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

	// 修改月度表现信息段
	@RequestMapping("/updateAccMthBlgInfSgmt")
	@ResponseBody
	public Map<String, Object> updateAccMthBlgInfSgmt(HttpServletRequest request, PbcrsReportAccmthblginfsgmt AccMthBlgInfSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("AccMthBlgInfSgmt", AccMthBlgInfSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateAccMthBlgInfSgmt(param);

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

	// 修改大额专项分期信息段
	@RequestMapping("/updateSpecPrdSgmt")
	@ResponseBody
	public Map<String, Object> updateSpecPrdSgmt(HttpServletRequest request, PbcrsReportSpecprdsgmt SpecPrdSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("SpecPrdSgmt", SpecPrdSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateSpecPrdSgmt(param);

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

	// 修改非月度表现
	@RequestMapping("/updateAcctDbtInfSgmt")
	@ResponseBody
	public Map<String, Object> updateAcctDbtInfSgmt(HttpServletRequest request, PbcrsReportAcctdbtinfsgmt AcctDbtInfSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("AcctDbtInfSgmt", AcctDbtInfSgmt);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateAcctDbtInfSgmt(param);

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

	// 修改特殊交易说明信息段
	@RequestMapping("/updateAccSpeTrsDspSgmt")
	@ResponseBody
	public Map<String, Object> updateAccSpeTrsDspSgmt(HttpServletRequest request, PbcrsReportCagoftrdinf CagOfTrdInf) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			//param.put("CagOfTrdInf", CagOfTrdInf);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			int count = service.updateAccSpeTrsDspSgmt(param);

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
	
	/**
	 * 删除基本信息段
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteAcctBsInfSgmt")
	@ResponseBody
	public Map<String,Object> deleteAcctBsInfSgmt(HttpServletRequest request){
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			service.deleteAcctBsInfSgmt(param);
			rslt.put("RET_CODE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}
		return rslt;
		
	}
	
	/**
	 * 删除月度表现段
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteAccMthBlgInfSgmt")
	@ResponseBody
	public Map<String,Object> deleteAccMthBlgInfSgmt(HttpServletRequest request){
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			service.deleteAccMthBlgInfSgmt(param);
			rslt.put("RET_CODE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}
		return rslt;
		
	}
	
	/**
	 * 删除大额专项分期段
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteSpecPrdSgmt")
	@ResponseBody
	public Map<String,Object> deleteSpecPrdSgmt(HttpServletRequest request){
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			service.deleteSpecPrdSgmt(param);
			rslt.put("RET_CODE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}
		return rslt;
		
	}
	
	
	/**
	 * 删除非月度表现段
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteAcctDbtInfSgmt")
	@ResponseBody
	public Map<String,Object> deleteAcctDbtInfSgmt(HttpServletRequest request){
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			service.deleteAcctDbtInfSgmt(param);
			rslt.put("RET_CODE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}
		return rslt;
		
	}
	
	
	/**
	 * 删除特殊交易段
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteAccSpeTrsDspSgmt")
	@ResponseBody
	public Map<String,Object> deleteAccSpeTrsDspSgmt(HttpServletRequest request){
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			User user =	(User)request.getSession().getAttribute("user");
			param.put("userid", user.getUserId());
			service.deleteAccSpeTrsDspSgmt(param);
			rslt.put("RET_CODE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rslt.put("RET_CODE", "FAILED");
			log.error("加入删除队列失败!", e);
		}
		return rslt;
		
	}


	//不良贷款确认
	@RequestMapping("/confirm")
	@ResponseBody
	public Map<String, Object> confirm(HttpServletRequest request,
									  PbcrsReportBssgmt BsSgmt) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		try {
			Map<String, Object> param = Util.parseWebParas(request);
			int row=service.confirm(param);
			if (row != 1) {
				rslt.put("RET_CODE", "FAILED");
			} else{
				rslt.put("RET_CODE", "SUCCESS");
			}
		} catch (Exception e) {
			rslt.put("RET_CODE", "FAILED");
			log.error("确认成功!", e);
		}
		return rslt;
	}

	
}
