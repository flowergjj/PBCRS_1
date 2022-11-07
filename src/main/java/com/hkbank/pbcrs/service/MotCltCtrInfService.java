package com.hkbank.pbcrs.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.util.Constants;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.BaseExternalSpi;
import com.hkbank.pbcrs.mapper.PbcrsReportAcctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCccinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCltalinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportComrecinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccinfmdfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnmotcltctrinfsgmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuamotcltctrsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuaracctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportImpinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInaccmdfmdfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInacctmdfcbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsecaccmdfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsecaccmdfmdsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsecacctinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsecacctmdfcMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMocentdelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMocidcagsinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMotcltbsinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMotcltctrbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMotcltctrinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMotcltctrinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMotgaproptinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportOtrrecMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportPleinfMapper;
import com.hkbank.pbcrs.model.PbcrsReportAcctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportBssgmt;
import com.hkbank.pbcrs.model.PbcrsReportCagoftrdinf;
import com.hkbank.pbcrs.model.PbcrsReportCccinf;
import com.hkbank.pbcrs.model.PbcrsReportCtrctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnaccinfmdfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportImpinf;
import com.hkbank.pbcrs.model.PbcrsReportInacctmdfcbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportInctrctentdel;
import com.hkbank.pbcrs.model.PbcrsReportInsecaccmdfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportMocentdel;
import com.hkbank.pbcrs.model.PbcrsReportMocidcagsinf;
import com.hkbank.pbcrs.model.PbcrsReportMotcltbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMotcltctrbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportOtrrec;
import com.hkbank.pbcrs.model.PbcrsReportPleinf;

@Service
@SuppressWarnings("all")
public class MotCltCtrInfService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(MotCltCtrInfService.class);
	//全表复制操作service
	@Autowired
	private InvestigationTypeService investigationtypeservice;
	// 痕迹表service
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;
	
	// 基础段
	@Autowired
	PbcrsReportMotcltctrbssgmtMapper MotcltctrbssgmtMapper;
	// 基本信息段
	@Autowired	
	PbcrsReportMotcltbsinfsgmtMapper MotCltBsInfSgmtMapper;
	// 其他债务人信息段
	@Autowired
	PbcrsReportComrecinfsgmtMapper ComrecinfsgmtMapper;
	// 其他债务人信息详细
	@Autowired
	PbcrsReportOtrrecMapper OtrrecMapper;
	// 抵押物信息段
	@Autowired
	PbcrsReportMotgaproptinfsgmtMapper MotgaproptinfsgmtMapper;
	// 抵押物信息详细
	@Autowired
	PbcrsReportPleinfMapper PleinfMapper;
	// 质物信息段
	@Autowired
	PbcrsReportCltalinfsgmtMapper CltalinfsgmtMapper;
	// 质物信息详细
	@Autowired
	PbcrsReportImpinfMapper ImpinfMapper;
	// 抵质押物合同信息
	@Autowired
	PbcrsReportCccinfMapper CccinfMapper;
	// 个人担保主表
	@Autowired
	private PbcrsReportInsecacctinfMapper InsecacctinfMapper;
	// 个人担保抵质押物
	@Autowired
	private PbcrsReportGuamotcltctrsgmtMapper GuamotcltctrsgmtMapper;
	// 个人担保基础段
	@Autowired
	private PbcrsReportGuaracctbssgmtMapper GuaracctbssgmtMapper;
	// 个人担保待更正基础段
	@Autowired
	private PbcrsReportInsecaccmdfbssgmtMapper InsecaccmdfbssgmtMapper;
	// 个人担保待更正--待更正段
	@Autowired
	private PbcrsReportInsecaccmdfmdsgmtMapper InsecaccmdfmdsgmtMapper;
	// 个人担保待更正--主表
	@Autowired
	private PbcrsReportInsecacctmdfcMapper InsecacctmdfcMapper;
	/*//个人借贷主表
	@Autowired
	private PbcrsReportInacctinfMapper InacctinfMapper ;*/
	//个人借贷基础段
	@Autowired
	private PbcrsReportAcctbssgmtMapper AcctbssgmtMapper  ;
	// 个人借贷抵质押物
	@Autowired
	private PbcrsReportMotcltctrinfsgmtMapper MotcltctrinfsgmtMapper ;
	
	//个人借贷待更正段-基础段
	@Autowired
	private PbcrsReportInacctmdfcbssgmtMapper InacctmdfcbssgmtMapper;
	//个人借贷待更正段-待更正
	@Autowired
	private PbcrsReportInaccmdfmdfsgmtMapper InaccmdfmdfsgmtMapper;
	// 抵质押物标示变更表
	@Autowired
	private PbcrsReportMocidcagsinfMapper MocidcagsinfMapper;
	
	@Autowired
	PbcrsReportEnaccinfmdfbssgmtMapper pbcrsReportEnaccinfmdfbssgmtMapper; //企业借贷账户信息更正段基本
	@Autowired
	PbcrsReportEnacctbssgmtMapper pbcrsReportEnAcctBsSgmtMapper; 
	@Autowired
	PbcrsReportEnmotcltctrinfsgmMapper pbcrsReportEnmotcltctrinfsgmMapper; //抵质押列表
	@Autowired
	private PbcrsReportMocentdelMapper MocentdelMapper;

	// 查询主列表信息
	public Map<String, Object> getBaseInfoList(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		param = getStartAndEnd(param);
		// 查询总数
		int total = MotcltctrbssgmtMapper.getCount(param);
		// 判断total有没有值，或者total小于要查询开始的index
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// 查询列表
			list = MotcltctrbssgmtMapper.getAllandPage(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;
	}

	

	// 查询基础段信息
	public PbcrsReportMotcltctrbssgmt getMotCltCtrBsSgmt(String MotgaCltalCtrctBsSgmtSeqNo) {
		PbcrsReportMotcltctrbssgmt result = MotcltctrbssgmtMapper.selectByPrimaryKey(MotgaCltalCtrctBsSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportMotcltctrbssgmt();
		return result;

	}

	// 查询基本信息段
	public PbcrsReportMotcltbsinfsgmt getMotCltBsInfSgmt(String MotgaCltalBsInfSgmtSeqNo) {
		PbcrsReportMotcltbsinfsgmt result = MotCltBsInfSgmtMapper.selectByPrimaryKey(MotgaCltalBsInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportMotcltbsinfsgmt();
		return result;

	}

	// 查询其他债务人信息段
	public Map<String, Object> getComRecInfSgmt(Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PbcrsReportOtrrec> list;
		param = getStartAndEnd(param);
		int total = ComrecinfsgmtMapper.getOtrRecListTotal(param);
		// 判断total有没有值，或者total小于要查询开始的index
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// 查询列表
			list = ComrecinfsgmtMapper.getOtrRecList(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;

	}

	// 查询其他债务人信息详细
	public PbcrsReportOtrrec getOtrRec(String ComRecInfSgmtSeqNo, String OtrRecSeqNo) {
		PbcrsReportOtrrec result = OtrrecMapper.selectByPrimaryKey(ComRecInfSgmtSeqNo, OtrRecSeqNo);
		if (result == null)
			result = new PbcrsReportOtrrec();
		return result;
	}

	// 查询抵押物信息段
	public Map<String, Object> getMotgaProptInfSgmt(Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PbcrsReportPleinf> list;
		param = getStartAndEnd(param);
		int total = MotgaproptinfsgmtMapper.getPleInfListTotal(param);
		// 判断total有没有值，或者total小于要查询开始的index
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// 查询列表
			list = MotgaproptinfsgmtMapper.getPleInfList(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;

	}

	// 查询抵押物信息详细
	public PbcrsReportPleinf getPleInf(String ComRecInfSgmtSeqNo, String PleInfSeqNo) {
		PbcrsReportPleinf result = PleinfMapper.selectByPrimaryKey(ComRecInfSgmtSeqNo, PleInfSeqNo);
		if (result == null)
			result = new PbcrsReportPleinf();
		return result;
	}

	// 查询质物信息段
	public Map<String, Object> getCltalInfSgmt(Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PbcrsReportImpinf> list;
		param = getStartAndEnd(param);
		int total = CltalinfsgmtMapper.getImpInfListTotal(param);
		// 判断total有没有值，或者total小于要查询开始的index
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// 查询列表
			list = CltalinfsgmtMapper.getImpInfList(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;

	}

	// 质物信息详细
	public PbcrsReportImpinf getImpInf(String CltalInfSgmtSeqNo, String ImpInfSeqNo) {
		PbcrsReportImpinf result = ImpinfMapper.selectByPrimaryKey(CltalInfSgmtSeqNo, ImpInfSeqNo);
		if (result == null)
			result = new PbcrsReportImpinf();
		return result;
	}

	// 修改基本信息段
	public int updateMotCltBsInfSgmt(Map<String, Object> params) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = format.format(new Date());
			// 基本信息段值(页面传递过来)
			//PbcrsReportMotcltbsinfsgmt motCltBsInfSgmt = (PbcrsReportMotcltbsinfsgmt) params.get("MotCltBsInfSgmt");
			PbcrsReportMotcltbsinfsgmt motCltBsInfSgmt = (PbcrsReportMotcltbsinfsgmt) Util.map2Object2(params,PbcrsReportMotcltbsinfsgmt.class);
			PbcrsReportMotcltbsinfsgmt pbcrsreportmotcltbsinfsgmt = MotCltBsInfSgmtMapper.selectByPrimaryKey(motCltBsInfSgmt.getMotgaCltalBsInfSgmtSeqNo());
			if(pbcrsreportmotcltbsinfsgmt!=null){
				motCltBsInfSgmt.setReportflag("0");
				MotCltBsInfSgmtMapper.updateByPrimaryKey(motCltBsInfSgmt);
			}else{
				throw new Exception();
			}
			PbcrsReportMotcltctrbssgmt pbcrsreportmotcltctrbssgmt = MotcltctrbssgmtMapper.selectByPrimaryKey(motCltBsInfSgmt.getMotgaCltalBsInfSgmtSeqNo());
			String updateType = params.get("updateType").toString();
			log.info("修改类型为:"+updateType);
			
			if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
				//当用户点击的是更正按钮则将时点置为30
				pbcrsreportmotcltctrbssgmt.setRptDateCode("30");
				pbcrsreportmotcltctrbssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
				//updateMotCltCtrInfSgmtInfo(motCltBsInfSgmt.getMotgaCltalBsInfSgmtSeqNo(),null);
			}
			
			pbcrsreportmotcltctrbssgmt.setReportflag("0");
			MotcltctrbssgmtMapper.updateByPrimaryKey(pbcrsreportmotcltctrbssgmt);
			int cnt=investigationtypeservice.changeUpdate("MotCltCtrInf", motCltBsInfSgmt.getMotgaCltalBsInfSgmtSeqNo(), null);
			// 插入痕迹表
			Map<String, Object> newMap = Util.Obj2Map(motCltBsInfSgmt);
			newMap.put("userid", params.get("userid"));
			pbcrsReportTraceService.insertTrace(pbcrsreportmotcltbsinfsgmt, newMap, "MotCltBsInfSgmt", motCltBsInfSgmt.getMotgaCltalBsInfSgmtSeqNo());
			return 1;
	}

	// 修改基础段
	public int updateMotCltCtrBsSgmt(Map<String, Object> params) throws Exception {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = format.format(new Date());
			// 基础段信息
			//PbcrsReportMotcltctrbssgmt motCltCtrBsSgmt = (PbcrsReportMotcltctrbssgmt) params.get("MotCltCtrBsSgmt");
			PbcrsReportMotcltctrbssgmt motCltCtrBsSgmt = (PbcrsReportMotcltctrbssgmt)Util.map2Object2(params,PbcrsReportMotcltctrbssgmt.class);
			// 保存从页面传递过来的基础段主键
			String MotgaCltalCtrctBsSgmtSeqNo = motCltCtrBsSgmt.getMotgaCltalCtrctBsSgmtSeqNo();
			PbcrsReportMotcltctrbssgmt pbcrsreportmotcltctrbssgmt = MotcltctrbssgmtMapper.selectByPrimaryKey(MotgaCltalCtrctBsSgmtSeqNo);
			String updateType = params.get("updateType").toString();
			if(pbcrsreportmotcltctrbssgmt!=null){
				motCltCtrBsSgmt.setReportflag("0");
				if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
					motCltCtrBsSgmt.setRptDateCode("30");
					motCltCtrBsSgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
				}
				MotcltctrbssgmtMapper.updateByPrimaryKey(motCltCtrBsSgmt);
				
			}else{
				throw new Exception();
			}
			int cnt = investigationtypeservice.changeUpdate("MotCltCtrInf", MotgaCltalCtrctBsSgmtSeqNo, null);
			if (cnt != 1)
				throw new Exception();
			log.info("修改类型为:"+updateType);
			if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
				boolean flag = pbcrsreportmotcltctrbssgmt.getCcCode().equals(motCltCtrBsSgmt.getCcCode());
				if (!flag) {
					//生成标识码变更记录
					PbcrsReportMocidcagsinf mocidcagsinf = new PbcrsReportMocidcagsinf();
					mocidcagsinf.setMoCIDCagsInfSeqNo(MotgaCltalCtrctBsSgmtSeqNo);
					mocidcagsinf.setInfRecType("511");
					// 原合同标识码
					mocidcagsinf.setOdBnesCode(pbcrsreportmotcltctrbssgmt.getCcCode());
					// 新合同标识码
					mocidcagsinf.setNwBnesCode(motCltCtrBsSgmt.getCcCode());
					mocidcagsinf.setReportflag("0");
					mocidcagsinf.setEtlDate(new Date());
					mocidcagsinf.setOrgCode(pbcrsreportmotcltctrbssgmt.getMngmtOrgCode());
					mocidcagsinf.setSourceSys(pbcrsreportmotcltctrbssgmt.getSourceSys());
					MocidcagsinfMapper.deleteByPrimaryKey(MotgaCltalCtrctBsSgmtSeqNo);
					cnt=MocidcagsinfMapper.insert(mocidcagsinf);
					if(cnt!=1) throw new Exception();
					//更新对应功能里面的抵质押物信息段，标识码变更
					updateMotCltCtrInfSgmtInfo(MotgaCltalCtrctBsSgmtSeqNo,motCltCtrBsSgmt.getCcCode());
					
				}
				//else{
					//更新对应功能里面的抵质押物信息段,没有做标识码变更
				//	updateMotCltCtrInfSgmtInfo(MotgaCltalCtrctBsSgmtSeqNo,null);
				//}
			}	
		
			// 插入痕迹表
			Map<String, Object> newMap = Util.Obj2Map(motCltCtrBsSgmt);
			newMap.put("userid", params.get("userid"));
			pbcrsReportTraceService.insertTrace(pbcrsreportmotcltctrbssgmt, newMap, "MotCltCtrBsSgmt", MotgaCltalCtrctBsSgmtSeqNo);
			return 1;
	}

	// 修改其他债务人信息段
	public int updateComRecInfSgmt(Map<String, Object> params) throws Exception {
		
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = format.format(new Date());
			// 其他债务人信息值(页面传递过来)
			//PbcrsReportOtrrec OtrRec = (PbcrsReportOtrrec) params.get("OtrRec");
			PbcrsReportOtrrec OtrRec = (PbcrsReportOtrrec) Util.map2Object2(params,PbcrsReportOtrrec.class);
			PbcrsReportOtrrec pbcrsreportotrrec = OtrrecMapper.selectByPrimaryKey(OtrRec.getComRecInfSgmtSeqNo(), OtrRec.getOtrRecSeqNo());
			if(pbcrsreportotrrec!=null){
				OtrRec.setReportflag("0");
				OtrrecMapper.updateByPrimaryKey(OtrRec);
			}else{
				throw new Exception();
			}
			
			
			String updateType = params.get("updateType").toString();
			log.info("修改类型为:"+updateType);
			PbcrsReportMotcltctrbssgmt pbcrsreportmotcltctrbssgmt = MotcltctrbssgmtMapper.selectByPrimaryKey(OtrRec.getComRecInfSgmtSeqNo());
			if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
				pbcrsreportmotcltctrbssgmt.setRptDateCode("30");
				//updateMotCltCtrInfSgmtInfo(OtrRec.getComRecInfSgmtSeqNo(),null);
				pbcrsreportmotcltctrbssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			}
		
			pbcrsreportmotcltctrbssgmt.setReportflag("0");
			MotcltctrbssgmtMapper.updateByPrimaryKey(pbcrsreportmotcltctrbssgmt);
			int cnt = investigationtypeservice.changeUpdate("MotCltCtrInf", OtrRec.getComRecInfSgmtSeqNo(), null);
			if (cnt != 1)
				throw new Exception();
			// 插入痕迹表
			Map<String, Object> newMap = Util.Obj2Map(OtrRec);
			newMap.put("userid", params.get("userid"));
			pbcrsReportTraceService.insertTrace(pbcrsreportotrrec, newMap, "OtrRec", OtrRec.getComRecInfSgmtSeqNo());
			return 1;
	}

	// 修改抵押物信息段
	public int updateMotgaProptInfSgmt(Map<String, Object> params) throws Exception {
		
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = format.format(new Date());
			// 抵押物信息值(页面传递过来)
			//PbcrsReportPleinf PleInf = (PbcrsReportPleinf) params.get("PleInf");
			PbcrsReportPleinf PleInf = (PbcrsReportPleinf)Util.map2Object2(params,PbcrsReportPleinf.class);
			PbcrsReportPleinf pbcrsreportpleinf = PleinfMapper.selectByPrimaryKey(PleInf.getComRecInfSgmtSeqNo(), PleInf.getPleInfSeqNo());
			if(pbcrsreportpleinf!=null){
				PleInf.setReportflag("0");
				PleinfMapper.updateByPrimaryKey(PleInf);
			}else{
				throw new Exception();
			}
			
			String updateType = params.get("updateType").toString();
			log.info("修改类型为:"+updateType);
			PbcrsReportMotcltctrbssgmt pbcrsreportmotcltctrbssgmt = MotcltctrbssgmtMapper.selectByPrimaryKey( PleInf.getComRecInfSgmtSeqNo());
			if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
				pbcrsreportmotcltctrbssgmt.setRptDateCode("30");
				//updateMotCltCtrInfSgmtInfo(PleInf.getComRecInfSgmtSeqNo(),null);
				pbcrsreportmotcltctrbssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			}
		
			pbcrsreportmotcltctrbssgmt.setReportflag("0");
			MotcltctrbssgmtMapper.updateByPrimaryKey(pbcrsreportmotcltctrbssgmt);
			int cnt = investigationtypeservice.changeUpdate("MotCltCtrInf", PleInf.getComRecInfSgmtSeqNo(), null);
			if (cnt != 1)
				throw new Exception();
			// 插入痕迹表
			Map<String, Object> newMap = Util.Obj2Map(PleInf);
			newMap.put("userid", params.get("userid"));
			pbcrsReportTraceService.insertTrace(pbcrsreportpleinf, newMap, "PleInf", PleInf.getComRecInfSgmtSeqNo());
			return 1;
	}

	// 修改质物信息段
	public int updateCltalInfSgmt(Map<String, Object> params) throws Exception {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = format.format(new Date());
			// 质物信息段(页面传递过来)
			//PbcrsReportImpinf ImpInf = (PbcrsReportImpinf) params.get("ImpInf");
			PbcrsReportImpinf ImpInf = (PbcrsReportImpinf)Util.map2Object2(params,PbcrsReportImpinf.class);
			PbcrsReportImpinf pbcrsreportimpinf = ImpinfMapper.selectByPrimaryKey(ImpInf.getCltalInfSgmtSeqNo(),ImpInf.getImpInfSeqNo());
			if(pbcrsreportimpinf!=null){
				ImpInf.setReportflag("0");
				ImpinfMapper.updateByPrimaryKey(ImpInf);
			}else{
				throw new Exception();
			}
			
			String updateType = params.get("updateType").toString();
			log.info("修改类型为:"+updateType);
			PbcrsReportMotcltctrbssgmt pbcrsreportmotcltctrbssgmt = MotcltctrbssgmtMapper.selectByPrimaryKey( ImpInf.getCltalInfSgmtSeqNo());
			if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
				pbcrsreportmotcltctrbssgmt.setRptDateCode("30");
				//updateMotCltCtrInfSgmtInfo(ImpInf.getCLTALINFSGMTINFSeqNo(),null);
				pbcrsreportmotcltctrbssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			}
			
			pbcrsreportmotcltctrbssgmt.setReportflag("0");
			MotcltctrbssgmtMapper.updateByPrimaryKey(pbcrsreportmotcltctrbssgmt);
			int cnt = investigationtypeservice.changeUpdate("MotCltCtrInf", ImpInf.getCltalInfSgmtSeqNo(), null);
			if (cnt != 1)
				throw new Exception();
			// 插入痕迹表
			Map<String, Object> newMap = Util.Obj2Map(ImpInf);
			newMap.put("userid", params.get("userid"));
			pbcrsReportTraceService.insertTrace(pbcrsreportimpinf, newMap, "ImpInf", ImpInf.getCltalInfSgmtSeqNo());
			return 1;
	}
	
	public int delete(Map<String, Object> params) throws Exception {
		String infoKey = params.get("infoKey").toString();
		PbcrsReportMocentdel moCEntDel = MocentdelMapper.selectByPrimaryKey(infoKey);
		if (moCEntDel != null ) {
			return 0;
		}
		 PbcrsReportMotcltctrbssgmt motcltctrbssgmt = MotcltctrbssgmtMapper.selectByPrimaryKey(infoKey);
		if (motcltctrbssgmt != null) {
			String isDel = motcltctrbssgmt.getIsDel();
			if (StringUtils.isNotEmpty(isDel) && isDel.equals("1")) {
				return 0;
			}
		}
		PbcrsReportMocentdel pbcrsReportMocentdel = new PbcrsReportMocentdel();
		pbcrsReportMocentdel.setMoCEntDelSeqNo(infoKey);
		pbcrsReportMocentdel.setInfRecType("514");
		pbcrsReportMocentdel.setDelRecCode(motcltctrbssgmt.getCcCode());
		pbcrsReportMocentdel.setReportFlag("0");
		pbcrsReportMocentdel.setEtlDate(new Date());
		pbcrsReportMocentdel.setSourceSys(motcltctrbssgmt.getSourceSys());
		pbcrsReportMocentdel.setOrgCode(motcltctrbssgmt.getOrgCode());
		int cnt = MocentdelMapper.insert(pbcrsReportMocentdel);
		if (cnt != 1) {
			throw new Exception();
		}
		motcltctrbssgmt.setIsDel("1");
		cnt = MotcltctrbssgmtMapper.updateByPrimaryKey(motcltctrbssgmt);
		if (cnt != 1) {
			throw new Exception();
		}
		return 1;
	}

	/**
	 * 根据传入的抵质押物主键号查找抵质押物表(Cccinf)中的信息，如果做了标识码变换同时也更新信息中的账户标识码
	 * @param MotgaCltalCtrctInfSeqNo
	 * @param CcCode
	 * @return
	 * @throws Exception
	 */
	public int updateMotCltCtrInfSgmtInfo(String MotgaCltalCtrctInfSeqNo,String CcCode) throws Exception{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String nowDate = format.format(new Date());
					// 根据抵质押物流水号反查抵质押物合同信息
					PbcrsReportCccinf Cccinf = CccinfMapper.getByMotgaCltalCtrctInfSeqNo(MotgaCltalCtrctInfSeqNo);
					if(Cccinf==null)
						return 1;
					// 质押合同类型
					String type = Cccinf.getMotgaCltalCtrctInfType();
					// 抵质押物主键
					String MotgaCltalCtrctInfSgmtSeqNo = Cccinf.getMotgaCltalCtrctInfSgmtSeqNo();
					//修改抵质押物详细记录表中的合同标识码
					//if(CcCode!=null){
						Cccinf.setCcc(CcCode);
						CccinfMapper.updateByPrimaryKey(Cccinf);	
					//}
					// 企业借贷
					if (type.equals("MO")) {
						// 抵质押物详细列表
						List<PbcrsReportCccinf> lists = CccinfMapper.getByFirstKey(MotgaCltalCtrctInfSgmtSeqNo,"MO");
						for (PbcrsReportCccinf pbcrsReportCccinf : lists) {
							pbcrsReportCccinf.setReportFlag("0");
							CccinfMapper.deleteByPrimaryKeyM(pbcrsReportCccinf.getMotgaCltalCtrctInfSgmtSeqNo(),pbcrsReportCccinf.getCccInfSeqNo());
							int cnt=CccinfMapper.insertM(pbcrsReportCccinf);
							if(cnt==0) throw new Exception();
						}
					
						pbcrsReportEnmotcltctrinfsgmMapper.deleteByPrimaryKeyM(MotgaCltalCtrctInfSgmtSeqNo);
						//guamotcltctrsgmt.setReportflag("0");
						int cnt=pbcrsReportEnmotcltctrinfsgmMapper.insertM(MotgaCltalCtrctInfSgmtSeqNo);
						if(cnt!=1) throw new Exception();
						// 生成按段修改记录
						// 基础段信息，用来获取账户标识码以及客户账户类型
						PbcrsReportEnacctbssgmt AcctBsSgmt = pbcrsReportEnAcctBsSgmtMapper.selectByPrimaryKey(MotgaCltalCtrctInfSgmtSeqNo);
						// 待更正段
						PbcrsReportEnaccinfmdfbssgmt pbcrsReportEnaccinfmdfbssgmt = new PbcrsReportEnaccinfmdfbssgmt();
						pbcrsReportEnaccinfmdfbssgmt.setBsSgmtSeqNo(String.valueOf(MotgaCltalCtrctInfSgmtSeqNo));
						pbcrsReportEnaccinfmdfbssgmt.setInfRecType("412");
						pbcrsReportEnaccinfmdfbssgmt.setModRecCode(AcctBsSgmt.getAcctCode());
						pbcrsReportEnaccinfmdfbssgmt.setAcctType(AcctBsSgmt.getAcctType());
						pbcrsReportEnaccinfmdfbssgmt.setRptDate(nowDate);
						pbcrsReportEnaccinfmdfbssgmt.setMdfcSgmtCode("E");
						pbcrsReportEnaccinfmdfbssgmt.setReportFlag(Constants.REPORTFLAG_IN);

						pbcrsReportEnaccinfmdfbssgmtMapper.deleteByPrimaryKey(MotgaCltalCtrctInfSgmtSeqNo,"E");

						
						cnt=pbcrsReportEnaccinfmdfbssgmtMapper.insert(pbcrsReportEnaccinfmdfbssgmt);
						if(cnt!=1)
							return cnt;
						if (cnt != 1)
							throw new Exception();
						return 1;
					} else
					// 个人借贷
					if (type.equals("GU")) {
						// 抵质押物详细列表
						List<PbcrsReportCccinf> lists = CccinfMapper.getByFirstKey(MotgaCltalCtrctInfSgmtSeqNo,"GU");
						for (PbcrsReportCccinf pbcrsReportCccinf : lists) {
							pbcrsReportCccinf.setReportFlag("0");
							CccinfMapper.deleteByPrimaryKeyM(pbcrsReportCccinf.getMotgaCltalCtrctInfSgmtSeqNo(),pbcrsReportCccinf.getCccInfSeqNo());
							int cnt=CccinfMapper.insertM(pbcrsReportCccinf);
							if(cnt!=1) throw new Exception();
						}
						// 个人借贷抵质押物表信息
						//PbcrsReportMotcltctrinfsgmt motcltctrinfsgmt = MotcltctrinfsgmtMapper.selectByPrimaryKey(MotgaCltalCtrctInfSgmtSeqNo);
						MotcltctrinfsgmtMapper.deleteByPrimaryKeyM(MotgaCltalCtrctInfSgmtSeqNo);
						//motcltctrinfsgmt.setReportFlag("0");
						int cnt=MotcltctrinfsgmtMapper.insertM(MotgaCltalCtrctInfSgmtSeqNo);
						if(cnt!=1) throw new Exception();
						// 生成按段修改记录
						// 基础段信息，用来获取账户标识码以及客户账户类型
						PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(MotgaCltalCtrctInfSgmtSeqNo);
						// 待更正段
						PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
						inAcctMdfcBsSgmt.setBsSgmtSeqNo(MotgaCltalCtrctInfSgmtSeqNo);
						inAcctMdfcBsSgmt.setInfRecType("212");
						inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
						inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
						inAcctMdfcBsSgmt.setRptDate(nowDate);
						inAcctMdfcBsSgmt.setMdfcSgmtCode("E");
						inAcctMdfcBsSgmt.setReportflag("0");
						inAcctMdfcBsSgmt.setEtlDate(new Date());
						inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
						InacctmdfcbssgmtMapper.deleteByPrimaryKey(MotgaCltalCtrctInfSgmtSeqNo, "E");
						cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
						if (cnt != 1)
							throw new Exception();
						return 1;
					}
		return 0;
	}
	// 分页参数封装
	public Map<String, Object> getStartAndEnd(Map<String, Object> param) {
		int pageNo = MapUtils.getIntValue(param, "page");
		int pageSize = MapUtils.getIntValue(param, "rows");
		int skip = (pageNo - 1) * pageSize + 1;
		int endindex = skip + pageSize;
		param.put("endindex", endindex);
		param.put("startindex", skip);
		return param;
	}



	@Override
	public Object selectByPrimaryKey(String seqId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int deleteByPrimaryKeyM(String seqId) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int insertM(String selectStr) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int updateByMap(Map map) {
		// TODO Auto-generated method stub
		MotcltctrbssgmtMapper.updateByMap(map);
		MotCltBsInfSgmtMapper.updateByMap(map);
		MotgaproptinfsgmtMapper.updateByMap(map);
		PleinfMapper.updateByMap(map);
		CltalinfsgmtMapper.updateByMap(map);
		ImpinfMapper.updateByMap(map);
		return 1;
	}



	@Override
	public int updateByMapMdc(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}
}
