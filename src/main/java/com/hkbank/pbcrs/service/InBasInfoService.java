package com.hkbank.pbcrs.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.util.Constants;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.BaseExternalSpi;
import com.hkbank.pbcrs.mapper.PbcrsReportBssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEduinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportFcsinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportIdrecMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportIdsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInbsinfdltMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportIncinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMlginfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportOctpninfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportRedncinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportSpsinfsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportBssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEduinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;
import com.hkbank.pbcrs.model.PbcrsReportFcsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportIdrec;
import com.hkbank.pbcrs.model.PbcrsReportIdsgmt;
import com.hkbank.pbcrs.model.PbcrsReportInbsinfdlt;
import com.hkbank.pbcrs.model.PbcrsReportIncinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMlginfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportOctpninfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportRedncinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpsinfsgmt;

/**
 * 个人基本信息
 * 
 * @author hs1112
 * 
 */
@Service
@SuppressWarnings("all")
public class InBasInfoService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(InBasInfoService.class);
	// 全表复制操作service
	@Autowired
	private InvestigationTypeService investigationtypeservice;
	// 痕迹表
	@Autowired
	PbcrsReportTraceService pbcrsReportTraceService;
	// 基础段
	@Autowired
	private PbcrsReportBssgmtMapper bssgmtMapper;
	// 基本概况
	@Autowired
	private PbcrsReportFcsinfsgmtMapper fcsinfsgmtMapper;
	// 其他标示段
	@Autowired
	private PbcrsReportIdsgmtMapper sgmtMapper;
	// 其他标示详细
	@Autowired
	private PbcrsReportIdrecMapper idRecMapper;
	// 婚姻信息段
	@Autowired
	private PbcrsReportSpsinfsgmtMapper spsinfsgmtMapper;
	// 教育信息段
	@Autowired
	private PbcrsReportEduinfsgmtMapper sEduInfSgmtMapper;
	// 职业信息段
	@Autowired
	private PbcrsReportOctpninfsgmtMapper octpnInfSgmtMapper;
	// 居住地址段
	@Autowired
	private PbcrsReportRedncinfsgmtMapper redncInfSgmtMapper;
	// 通讯地址段
	@Autowired
	private PbcrsReportMlginfsgmtMapper mlgInfSgmtMapper;
	// 收入信息段
	@Autowired
	private PbcrsReportIncinfsgmtMapper incInfSgmtMapper;
	@Autowired
	// 整笔删除
	private PbcrsReportInbsinfdltMapper inbsinfdltMapper;

	// 查询主列表信息
	public Map<String, Object> getBaseInfoList(Map<String, Object> param) {
		List<Map<String, Object>> list;

		Map<String, Object> resultMap = new HashMap<String, Object>();
		param = getStartAndEnd(param);
		// 查询总数
		int total = bssgmtMapper.getCount(param);
		// 判断total有没有值，或者total小于要查询开始的index
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			// 查询列表
			list = bssgmtMapper.getAllandPage(param);
			if (list == null) {
				list = new ArrayList<Map<String, Object>>();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;
	}

	// 查询基础段信息
	public PbcrsReportBssgmt getBssgmtInfo(String BsSgmtSeqNo) {
		PbcrsReportBssgmt result = bssgmtMapper.selectByPrimaryKey(BsSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportBssgmt();
		return result;

	}

	// 查询基本概况段
	public PbcrsReportFcsinfsgmt getFcsinfsgmtInfo(String FcsInfSgmtSeqNo) {
		PbcrsReportFcsinfsgmt result = fcsinfsgmtMapper.selectByPrimaryKey(FcsInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportFcsinfsgmt();
		return result;

	}

	// 查询其他标识段信息
	public Map<String, Object> getIdrecInfo(Map<String, Object> param) {
		List<Map<String, Object>> list;
		param = getStartAndEnd(param);
		int total = sgmtMapper.getIdRecListTotal(param);
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			list = sgmtMapper.getIdRecList(param);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", list);
		return result;

	}

	// 查询其他标示单个详细
	public PbcrsReportIdrec getIDrecBySeqAndId(Map<String, Object> param) {
		PbcrsReportIdrec result = idRecMapper.getIDrecBySeqAndId(param);
		if (result == null)
			result = new PbcrsReportIdrec();
		return result;
	}

	// 查询婚姻信息段
	public PbcrsReportSpsinfsgmt getSpsinfsgmtInfo(String SpsInfSgmtSeqNo) {
		/* spsinfsgmtMapper; */
		PbcrsReportSpsinfsgmt result = spsinfsgmtMapper.selectByPrimaryKey(SpsInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportSpsinfsgmt();
		return result;

	}

	// 查询教育信息段

	public PbcrsReportEduinfsgmt getEduinfsgmtInfo(String EduInfSgmtSeqNo) {
		/* sEduInfSgmtMapper; */
		PbcrsReportEduinfsgmt result = sEduInfSgmtMapper.selectByPrimaryKey(EduInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportEduinfsgmt();
		return result;

	}

	// 查询职业信息段

	public PbcrsReportOctpninfsgmt getOctpninfsgmtInfo(String OctpnInfSgmtSeqNo) {
		/* octpnInfSgmtMapper; */
		PbcrsReportOctpninfsgmt result = octpnInfSgmtMapper.selectByPrimaryKey(OctpnInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportOctpninfsgmt();
		return result;

	}

	// 查询居住地址段

	public PbcrsReportRedncinfsgmt getRedncinfsgmtInfo(String RedncInfSgmtSeqNo) {
		/* redncInfSgmtMapper; */
		PbcrsReportRedncinfsgmt result = redncInfSgmtMapper.selectByPrimaryKey(RedncInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportRedncinfsgmt();
		return result;

	}

	// 查询通讯地址段

	public PbcrsReportMlginfsgmt getMlginfsgmtInfo(String MlgInfSgmtSeqNo) {
		/* mlgInfSgmtMapper; */
		PbcrsReportMlginfsgmt result = mlgInfSgmtMapper.selectByPrimaryKey(MlgInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportMlginfsgmt();
		return result;

	}

	// 查询 收入信息段

	public PbcrsReportIncinfsgmt getIncinfsgmtInfo(String IncInfSgmtSeqNo) {
		/* incInfSgmtMapper; */
		PbcrsReportIncinfsgmt result = incInfSgmtMapper.selectByPrimaryKey(IncInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportIncinfsgmt();
		return result;

	}

	// 修改基本概况段
	public int updateFcsInfSgmt(Map<String, Object> params) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		// 修改段参数信息封装
		PbcrsReportFcsinfsgmt fcsInfSgmt = (PbcrsReportFcsinfsgmt) Util.map2Object2(params,PbcrsReportFcsinfsgmt.class);
		//PbcrsReportFcsinfsgmt fcsInfSgmt = (PbcrsReportFcsinfsgmt) params.get("FcsInfSgmt");
		// String INBASINFOSEQNO = params.get("INBASINFOSEQNO").toString();
		PbcrsReportFcsinfsgmt pbcrsreportfcsinfsgmt = fcsinfsgmtMapper.selectByPrimaryKey(fcsInfSgmt.getFcsInfSgmtSeqNo());
		PbcrsReportBssgmt bssgmt = bssgmtMapper.selectByPrimaryKey(fcsInfSgmt.getFcsInfSgmtSeqNo());
		String updateType = params.get("updateType").toString();
		if (pbcrsreportfcsinfsgmt != null) {
			if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
				fcsInfSgmt.setFcsInfoUpDate(now);
			}
			
			fcsInfSgmt.setReportflag("0");
			fcsinfsgmtMapper.updateByPrimaryKey(fcsInfSgmt);
		} else {
			log.info("修改基本概况段出错");
			throw new Exception();
		}
		//bssgmt.setRptDate(now);
		
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			bssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			bssgmt.setRptDateCode("20");
		}

		bssgmt.setReportflag("0");
		log.info("修改类型为:" + updateType);
		int cnt = bssgmtMapper.updateByPrimaryKey(bssgmt);
		if (cnt != 1)
			throw new Exception();
		cnt = investigationtypeservice.changeUpdate("inBasInfo", fcsInfSgmt.getFcsInfSgmtSeqNo(), null);
		if (cnt != 1)
			throw new Exception();
		// fcsInfSgmt.
		Map<String, Object> newMap = Util.Obj2Map(fcsInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportfcsinfsgmt, newMap, "FcsInfSgmt", fcsInfSgmt.getFcsInfSgmtSeqNo());
		return 1;
	}

	// 修改婚姻信息段
	public int updateSpsInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		// 修改段参数信息封装
		//PbcrsReportSpsinfsgmt spsinfsgmt = (PbcrsReportSpsinfsgmt) params.get("SpsInfSgmt");
		PbcrsReportSpsinfsgmt spsinfsgmt = (PbcrsReportSpsinfsgmt) Util.map2Object2(params,PbcrsReportSpsinfsgmt.class);
		PbcrsReportSpsinfsgmt pbcrsreportspsinfsgmt = spsinfsgmtMapper.selectByPrimaryKey(spsinfsgmt.getSpsInfSgmtSeqNo());
		PbcrsReportBssgmt bssgmt = bssgmtMapper.selectByPrimaryKey(spsinfsgmt.getSpsInfSgmtSeqNo());
		if (pbcrsreportspsinfsgmt != null) {
			spsinfsgmt.setReportflag("0");
			spsinfsgmt.setSpsInfoUpDate(now);
			spsinfsgmtMapper.updateByPrimaryKey(spsinfsgmt);
		} else {
			throw new Exception();
		}

		//bssgmt.setRptDate(now);
		String updateType = params.get("updateType").toString();
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			bssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			bssgmt.setRptDateCode("20");
		}
		
		bssgmt.setReportflag("0");
		log.info("修改类型为:" + updateType);
		int cnt = bssgmtMapper.updateByPrimaryKey(bssgmt);
		if (cnt != 1)
			throw new Exception();
		cnt = investigationtypeservice.changeUpdate("inBasInfo", spsinfsgmt.getSpsInfSgmtSeqNo(), null);
		if (cnt != 1)
			throw new Exception();
		// fcsInfSgmt.
		Map<String, Object> newMap = Util.Obj2Map(spsinfsgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportspsinfsgmt, newMap, "SpsInfSgmt", spsinfsgmt.getSpsInfSgmtSeqNo());
		return 1;

	}

	// 修改教育信息段
	public int updateEduInfSgmt(Map<String, Object> params) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		// 修改段参数信息封装
		//PbcrsReportEduinfsgmt eduInfSgmt = (PbcrsReportEduinfsgmt) params.get("EduInfSgmt");
		PbcrsReportEduinfsgmt eduInfSgmt = (PbcrsReportEduinfsgmt) Util.map2Object2(params,PbcrsReportEduinfsgmt.class);
		PbcrsReportEduinfsgmt pbcrsreporteduinfsgmt = sEduInfSgmtMapper.selectByPrimaryKey(eduInfSgmt.getEduInfSgmtSeqNo());
		PbcrsReportBssgmt bssgmt = bssgmtMapper.selectByPrimaryKey(eduInfSgmt.getEduInfSgmtSeqNo());
		if (pbcrsreporteduinfsgmt != null) {
			eduInfSgmt.setReportflag("0");
			eduInfSgmt.setEduInfoUpDate(now);
			sEduInfSgmtMapper.updateByPrimaryKey(eduInfSgmt);
		} else {
			throw new Exception();
		}

		//bssgmt.setRptDate(now);
		String updateType = params.get("updateType").toString();
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			bssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			bssgmt.setRptDateCode("20");
		}
		
		bssgmt.setReportflag("0");
		log.info("修改类型为:" + updateType);
		int cnt = bssgmtMapper.updateByPrimaryKey(bssgmt);
		if (cnt != 1)
			throw new Exception();
		cnt = investigationtypeservice.changeUpdate("inBasInfo", eduInfSgmt.getEduInfSgmtSeqNo(), null);
		if (cnt != 1)
			throw new Exception();
		// fcsInfSgmt.
		Map<String, Object> newMap = Util.Obj2Map(eduInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreporteduinfsgmt, newMap, "EduInfSgmt", eduInfSgmt.getEduInfSgmtSeqNo());
		return 1;

	}

	// 修改职业信息段
	public int updateOctpnInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		// 修改段参数信息封装
		//PbcrsReportOctpninfsgmt octpnInfSgmt = (PbcrsReportOctpninfsgmt) params.get("OctpnInfSgmt");
		PbcrsReportOctpninfsgmt octpnInfSgmt = (PbcrsReportOctpninfsgmt)Util.map2Object2(params,PbcrsReportOctpninfsgmt.class);
		PbcrsReportOctpninfsgmt pbcrsreportoctpninfsgmt = octpnInfSgmtMapper.selectByPrimaryKey(octpnInfSgmt.getOctpnInfSgmtSeqNo());
		PbcrsReportBssgmt bssgmt = bssgmtMapper.selectByPrimaryKey(octpnInfSgmt.getOctpnInfSgmtSeqNo());
		if (pbcrsreportoctpninfsgmt != null) {
			octpnInfSgmt.setReportflag("0");
			octpnInfSgmt.setOctpnInfoUpDate(now);
			octpnInfSgmtMapper.updateByPrimaryKey(octpnInfSgmt);
		} else {
			throw new Exception();
		}

		//bssgmt.setRptDate(now);
		String updateType = params.get("updateType").toString();
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			bssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			bssgmt.setRptDateCode("20");
		}
		
		bssgmt.setReportflag("0");
		log.info("修改类型为:" + updateType);
		int cnt = bssgmtMapper.updateByPrimaryKey(bssgmt);
		if (cnt != 1)
			throw new Exception();
		cnt = investigationtypeservice.changeUpdate("inBasInfo", octpnInfSgmt.getOctpnInfSgmtSeqNo(), null);
		if (cnt != 1)
			throw new Exception();
		// fcsInfSgmt.
		Map<String, Object> newMap = Util.Obj2Map(octpnInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportoctpninfsgmt, newMap, "OctpnInfSgmt", octpnInfSgmt.getOctpnInfSgmtSeqNo());
		return 1;

	}

	// 修改居住地址段
	public int updateRedncInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		// 修改段参数信息封装
		//PbcrsReportRedncinfsgmt redncInfSgmt = (PbcrsReportRedncinfsgmt) params.get("RedncInfSgmt");
		PbcrsReportRedncinfsgmt redncInfSgmt = (PbcrsReportRedncinfsgmt)Util.map2Object2(params,PbcrsReportRedncinfsgmt.class);
		PbcrsReportRedncinfsgmt pbcrsreportredncinfsgmt = redncInfSgmtMapper.selectByPrimaryKey(redncInfSgmt.getRedncInfSgmtSeqNo());
		PbcrsReportBssgmt bssgmt = bssgmtMapper.selectByPrimaryKey(redncInfSgmt.getRedncInfSgmtSeqNo());
		if (pbcrsreportredncinfsgmt != null) {
			redncInfSgmt.setResiInfoUpDate(now);
			redncInfSgmt.setReportflag("0");
			redncInfSgmtMapper.updateByPrimaryKey(redncInfSgmt);
		} else {
			throw new Exception();
		}

		//bssgmt.setRptDate(now);
		String updateType = params.get("updateType").toString();
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			bssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			bssgmt.setRptDateCode("20");
		}
		
		bssgmt.setReportflag("0");
		log.info("修改类型为:" + updateType);
		int cnt = bssgmtMapper.updateByPrimaryKey(bssgmt);
		if (cnt != 1)
			throw new Exception();
		cnt = investigationtypeservice.changeUpdate("inBasInfo", redncInfSgmt.getRedncInfSgmtSeqNo(), null);
		if (cnt != 1)
			throw new Exception();
		// fcsInfSgmt.
		Map<String, Object> newMap = Util.Obj2Map(redncInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportredncinfsgmt, newMap, "RedncInfSgmt", redncInfSgmt.getRedncInfSgmtSeqNo());
		return 1;

	}

	// 修改通讯地址段
	public int updateMlgInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		// 修改段参数信息封装
		//PbcrsReportMlginfsgmt mlgInfSgmt = (PbcrsReportMlginfsgmt) params.get("MlgInfSgmt");
		PbcrsReportMlginfsgmt mlgInfSgmt = (PbcrsReportMlginfsgmt)Util.map2Object2(params,PbcrsReportMlginfsgmt.class);
		PbcrsReportMlginfsgmt pbcrsreportmlginfsgmt = mlgInfSgmtMapper.selectByPrimaryKey(mlgInfSgmt.getMlgInfSgmtSeqNo());
		PbcrsReportBssgmt bssgmt = bssgmtMapper.selectByPrimaryKey(mlgInfSgmt.getMlgInfSgmtSeqNo());
		if (pbcrsreportmlginfsgmt != null) {
			mlgInfSgmt.setReportflag("0");
			mlgInfSgmt.setMlgInfoUpDate(now);
			mlgInfSgmtMapper.updateByPrimaryKey(mlgInfSgmt);
		} else {
			throw new Exception();
		}

		//bssgmt.setRptDate(now);
		String updateType = params.get("updateType").toString();
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			bssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			bssgmt.setRptDateCode("20");
		}
		
		bssgmt.setReportflag("0");
		log.info("修改类型为:" + updateType);
		int cnt = bssgmtMapper.updateByPrimaryKey(bssgmt);
		if (cnt != 1)
			throw new Exception();
		cnt = investigationtypeservice.changeUpdate("inBasInfo", mlgInfSgmt.getMlgInfSgmtSeqNo(), null);
		if (cnt != 1)
			throw new Exception();
		// fcsInfSgmt.
		Map<String, Object> newMap = Util.Obj2Map(mlgInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportmlginfsgmt, newMap, "MlgInfSgmt", mlgInfSgmt.getMlgInfSgmtSeqNo());
		return 1;

	}

	// 修改收入信息段
	public int updateIncInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		// 修改段参数信息封装
		//PbcrsReportIncinfsgmt IncInfSgmt = (PbcrsReportIncinfsgmt) params.get("IncInfSgmt");
		PbcrsReportIncinfsgmt IncInfSgmt = (PbcrsReportIncinfsgmt)Util.map2Object2(params,PbcrsReportIncinfsgmt.class);
		PbcrsReportIncinfsgmt pbcrsreportincinfsgmt = incInfSgmtMapper.selectByPrimaryKey(IncInfSgmt.getIncInfSgmtSeqNo());
		PbcrsReportBssgmt bssgmt = bssgmtMapper.selectByPrimaryKey(IncInfSgmt.getIncInfSgmtSeqNo());
		if (pbcrsreportincinfsgmt != null) {
			IncInfSgmt.setReportflag("0");
			IncInfSgmt.setIncInfoUpDate(now);
			incInfSgmtMapper.updateByPrimaryKey(IncInfSgmt);
		} else {
			throw new Exception();
		}

		//bssgmt.setRptDate(now);
		String updateType = params.get("updateType").toString();
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			bssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			bssgmt.setRptDateCode("20");
		}
		
		bssgmt.setReportflag("0");
		log.info("修改类型为:" + updateType);
		int cnt = bssgmtMapper.updateByPrimaryKey(bssgmt);
		if (cnt != 1)
			throw new Exception();
		cnt = investigationtypeservice.changeUpdate("inBasInfo", IncInfSgmt.getIncInfSgmtSeqNo(), null);
		if (cnt != 1)
			throw new Exception();
		// fcsInfSgmt.
		Map<String, Object> newMap = Util.Obj2Map(IncInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportincinfsgmt, newMap, "IncInfSgmt", IncInfSgmt.getIncInfSgmtSeqNo());
		return 1;
	}

	// 修改基础段
	public int updateBsSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		// 修改段参数信息封装
		//PbcrsReportBssgmt bsSgmt = (PbcrsReportBssgmt) params.get("BsSgmt");
		PbcrsReportBssgmt bsSgmt = (PbcrsReportBssgmt)Util.map2Object2(params,PbcrsReportBssgmt.class);
		PbcrsReportBssgmt pbcrsreportbssgmt = bssgmtMapper.selectByPrimaryKey(bsSgmt.getBsSgmtSeqNo());
		if (pbcrsreportbssgmt != null) {
			
			String updateType = params.get("updateType").toString();
			if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
				bsSgmt.setRptDate(now);
				bsSgmt.setRptDateCode("20");
			}
			bsSgmt.setReportflag("0");
			log.info("修改类型为:" + updateType);
			bssgmtMapper.updateByPrimaryKey(bsSgmt);
		} else {
			throw new Exception();
		}
		int cnt = investigationtypeservice.changeUpdate("inBasInfo", bsSgmt.getBsSgmtSeqNo(), null);
		if (cnt != 1)
			throw new Exception();
		// fcsInfSgmt.
		Map<String, Object> newMap = Util.Obj2Map(bsSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportbssgmt, newMap, "BsSgmt", bsSgmt.getBsSgmtSeqNo());
		return 1;
	}

	// 修改其他标示段
	public int updateIdSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		// 修改段参数信息封装
		//PbcrsReportIdrec idrec = (PbcrsReportIdrec) params.get("IDRec");
		PbcrsReportIdrec idrec = (PbcrsReportIdrec)Util.map2Object2(params,PbcrsReportIdrec.class);
		PbcrsReportIdrec pbcrsreportidrec = idRecMapper.selectByPrimaryKey(idrec.getIDSgmtSeqNo(), idrec.getIDSgmtListSeqNo());
		PbcrsReportIdsgmt pbcrsreportidsgmt = sgmtMapper.selectByPrimaryKey(idrec.getIDSgmtSeqNo());
		if (pbcrsreportidrec != null) {
			idrec.setReportflag("0");
			idRecMapper.updateByPrimaryKey(idrec);
			pbcrsreportidsgmt.setIDInfoUpDate(now);
			pbcrsreportidsgmt.setReportflag("0");
			sgmtMapper.updateByPrimaryKey(pbcrsreportidsgmt);
		} else {
			throw new Exception();
		}
		PbcrsReportBssgmt bssgmt = bssgmtMapper.selectByPrimaryKey(idrec.getIDSgmtSeqNo());
		String updateType = params.get("updateType").toString();
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			bssgmt.setRptDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			bssgmt.setRptDateCode("20");
		}
		
		bssgmt.setReportflag("0");
		log.info("修改类型为:" + updateType);
		int cnt = bssgmtMapper.updateByPrimaryKey(bssgmt);
		if (cnt != 1)
			throw new Exception();
		cnt = investigationtypeservice.changeUpdate("inBasInfo", idrec.getIDSgmtSeqNo(), null);
		if (cnt != 1)
			throw new Exception();
		// fcsInfSgmt.
		Map<String, Object> newMap = Util.Obj2Map(idrec);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportidrec, newMap, "IDRec", idrec.getIDSgmtSeqNo());
		return 1;

	}

	public int delete(Map<String, Object> params) throws Exception {
		String infoKey = params.get("infoKey").toString();
		PbcrsReportInbsinfdlt inbsinfdl = inbsinfdltMapper.selectByPrimaryKey(infoKey);
		if (inbsinfdl != null ) {
			return 0;
		}
		PbcrsReportBssgmt bssgmt = bssgmtMapper.selectByPrimaryKey(infoKey);
		if (bssgmt != null) {
			String isDel = bssgmt.getIsDel();
			if (StringUtils.isNotEmpty(isDel) && isDel.equals("1")) {
				return 0;
			}
		}
		PbcrsReportInbsinfdlt pbcrsReportInbsinfdlt = new PbcrsReportInbsinfdlt();
		pbcrsReportInbsinfdlt.setInBsInfDltSeqNo(infoKey);
		pbcrsReportInbsinfdlt.setName(bssgmt.getName());
		pbcrsReportInbsinfdlt.setIDNum(bssgmt.getIDNum());
		pbcrsReportInbsinfdlt.setIDType(bssgmt.getIDType());
		pbcrsReportInbsinfdlt.setInfSurcCode(bssgmt.getInfSurcCode());
		pbcrsReportInbsinfdlt.setInfRecType("114");
		pbcrsReportInbsinfdlt.setReportFlag("0");
		pbcrsReportInbsinfdlt.setEtlDate(new Date());
		pbcrsReportInbsinfdlt.setSourceSys(bssgmt.getSourceSys());
		pbcrsReportInbsinfdlt.setOrgCode(bssgmt.getOrgCode());
		int cnt = inbsinfdltMapper.insert(pbcrsReportInbsinfdlt);
		if (cnt != 1) {
			throw new Exception();
		}
		bssgmt.setIsDel("1");
		cnt = bssgmtMapper.updateByPrimaryKey(bssgmt);
		if (cnt != 1) {
			throw new Exception();
		}
		return 1;
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

	/**
	 * 批量修改reportflag
	 */
	@Override
	public int updateByMap(Map map) {
		// 基础段
		bssgmtMapper.updateByMap(map);
		// 基本概况
		fcsinfsgmtMapper.updateByMap(map);
		// 婚姻信息段
		spsinfsgmtMapper.updateByMap(map);
		// 教育信息段
		sEduInfSgmtMapper.updateByMap(map);
		// 职业信息段
		octpnInfSgmtMapper.updateByMap(map);
		// 居住地址段
		redncInfSgmtMapper.updateByMap(map);
		// 通讯地址段
		mlgInfSgmtMapper.updateByMap(map);
		return 1;
	}

	@Override
	public Object selectByPrimaryKey(String seqId) {
		return null;
	}

	@Override
	public int deleteByPrimaryKeyM(String seqId) {
		return 0;
	}

	@Override
	public int insertM(String selectStr) {
		return 0;
	}

	@Override
	public int updateByMapMdc(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
