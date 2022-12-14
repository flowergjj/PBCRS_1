package com.hkbank.pbcrs.service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.util.Constants;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.BaseExternalSpi;
import com.hkbank.pbcrs.mapper.PbcrsReportAccmthblginfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportAccspetrsdspsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportAcctbsinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportAcctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportAcctcredsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportAcctdbtinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCagoftrdinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInaccmdfmdfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInacctdelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInacctentdelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInacctidcagsinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInacctmdfcbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMotcltctrinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportOricreinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportRltrepymtinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportRltrepymtinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportSpecprdsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportAccmthblginfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportAccspetrsdspsgmt;
import com.hkbank.pbcrs.model.PbcrsReportAcctbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportAcctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportAcctcredsgmt;
import com.hkbank.pbcrs.model.PbcrsReportAcctdbtinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportBssgmt;
import com.hkbank.pbcrs.model.PbcrsReportCagoftrdinf;
import com.hkbank.pbcrs.model.PbcrsReportCtrctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportInacctdel;
import com.hkbank.pbcrs.model.PbcrsReportInacctentdel;
import com.hkbank.pbcrs.model.PbcrsReportInacctidcagsinf;
import com.hkbank.pbcrs.model.PbcrsReportInacctmdfcbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportInbsinfdlt;
import com.hkbank.pbcrs.model.PbcrsReportOricreinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportRltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportRltrepymtinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpecprdsgmt;


@Service
@SuppressWarnings("all")
public class InAcctInfService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(InAcctInfService.class);
	// ?????????service
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;
	// ?????????
	@Autowired
	private PbcrsReportAcctbssgmtMapper AcctbssgmtMapper;
	// ???????????????
	@Autowired
	private PbcrsReportAcctbsinfsgmtMapper AcctbsinfsgmtMapper;
	// ??????????????????????????????
	@Autowired
	private PbcrsReportRltrepymtinfsgmtMapper RltrepymtinfsgmtMapper;
	// ???????????????????????????????????????
	@Autowired
	private PbcrsReportRltrepymtinfMapper RltrepymtinfMapper;
	// ??????????????????
	@Autowired
	private PbcrsReportAcctcredsgmtMapper AcctCredSgmtMapper;
	// ?????????????????????
	@Autowired
	private PbcrsReportOricreinfsgmtMapper OricreinfsgmtMapper;
	// ?????????????????????
	@Autowired
	private PbcrsReportAccmthblginfsgmtMapper AccmthblginfsgmtMapper;
	// ???????????????????????????
	@Autowired
	private PbcrsReportSpecprdsgmtMapper SpecprdsgmtMapper;
	// ???????????????
	@Autowired
	private PbcrsReportAcctdbtinfsgmtMapper AcctdbtinfsgmtMapper;
	// ???????????????????????????
	@Autowired
	private PbcrsReportAccspetrsdspsgmtMapper AccspetrsdspsgmtMapper;
	// ????????????????????????
	@Autowired
	private PbcrsReportCagoftrdinfMapper CagoftrdinfMapper;
	// ?????????????????????
	@Autowired
	private PbcrsReportMotcltctrinfsgmtMapper MotcltctrinfsgmtMapper;

	// ????????????-?????????
	@Autowired
	private PbcrsReportInacctmdfcbssgmtMapper InacctmdfcbssgmtMapper;
	// ????????????-?????????
	@Autowired
	private PbcrsReportInaccmdfmdfsgmtMapper InaccmdfmdfsgmtMapper;
	// ????????????????????????
	@Autowired
	private PbcrsReportInacctidcagsinfMapper InacctidcagsinfMapper;

	@Autowired
	private PbcrsReportInacctentdelMapper InacctentdelMapper;
	
	//???????????????????????????
	@Autowired
	private PbcrsReportInacctdelMapper InacctdelMapper;

	// ?????????????????????
	public Map<String, Object> getBaseInfoList(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		param = getStartAndEnd(param);
		// ????????????
		int total = AcctbssgmtMapper.getCount(param);
		// ??????total?????????????????????total????????????????????????index
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// ????????????
			list = AcctbssgmtMapper.getAllandPage(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;
	}

	// ?????????????????????
	public PbcrsReportAcctbssgmt getAcctBsSgmt(String AcctBsSgmtSeqNo) {
		PbcrsReportAcctbssgmt result = AcctbssgmtMapper.selectByPrimaryKey(AcctBsSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportAcctbssgmt();
		return result;

	}

	// ?????????????????????
	public PbcrsReportAcctbsinfsgmt getAcctBsInfSgmt(String AcctBsInfSgmtSeqNo) {
		PbcrsReportAcctbsinfsgmt result = AcctbsinfsgmtMapper.selectByPrimaryKey(AcctBsInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportAcctbsinfsgmt();
		return result;

	}

	public PbcrsReportAcctcredsgmt getAcctcredsgmt(String acctCredSgmtSeqNo) {

		PbcrsReportAcctcredsgmt result = AcctCredSgmtMapper.selectByPrimaryKey(acctCredSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportAcctcredsgmt();
		return result;

	}

	// ???????????????????????????
	public PbcrsReportOricreinfsgmt getOriCreInfSgmt(String OrigCreditorInfSgmtSeqNo) {
		PbcrsReportOricreinfsgmt result = OricreinfsgmtMapper.selectByPrimaryKey(OrigCreditorInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportOricreinfsgmt();
		return result;

	}

	// ???????????????????????????
	public PbcrsReportAccmthblginfsgmt getAccMthBlgInfSgmt(String AcctMthlyBlgInfSgmtSeqNo) {
		PbcrsReportAccmthblginfsgmt result = AccmthblginfsgmtMapper.selectByPrimaryKey(AcctMthlyBlgInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportAccmthblginfsgmt();
		return result;

	}

	// ?????????????????????????????????
	public PbcrsReportSpecprdsgmt getSpecPrdSgmt(String SpecPrdSgmtSeqNo) {
		PbcrsReportSpecprdsgmt result = SpecprdsgmtMapper.selectByPrimaryKey(SpecPrdSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportSpecprdsgmt();
		return result;

	}

	// ?????????????????????
	public PbcrsReportAcctdbtinfsgmt getAcctDbtInfSgmt(String AcctDbtInfSgmtSeqNo) {
		PbcrsReportAcctdbtinfsgmt result = AcctdbtinfsgmtMapper.selectByPrimaryKey(AcctDbtInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportAcctdbtinfsgmt();
		return result;

	}

	// ????????????????????????????????????
	public Map<String, Object> getRltRepymtInfSgmt(Map<String, Object> param) {

		List<Map<String, Object>> list;
		param = getStartAndEnd(param);
		int total = RltrepymtinfsgmtMapper.getRltRepymtInfListTotal(param);
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			list = RltrepymtinfsgmtMapper.getRltRepymtInfList(param);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", list);
		return result;

	}

	// ???????????????????????????????????????
	public PbcrsReportRltrepymtinf getRltRepymtInf(String RltRepymtInfSgmtSeqNo, String RltRepymtInfSeqNo) {
		PbcrsReportRltrepymtinf result = RltrepymtinfMapper.selectByPrimaryKey(RltRepymtInfSgmtSeqNo, RltRepymtInfSeqNo);
		if (result == null)
			result = new PbcrsReportRltrepymtinf();
		return result;

	}

	// ?????????????????????????????????
	public Map<String, Object> getAccSpeTrsDspSgmt(Map<String, Object> param) {

		List<Map<String, Object>> list;
		param = getStartAndEnd(param);
		int total = AccspetrsdspsgmtMapper.getAccSpeTrsDspSgmtListTotal(param);
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			list = AccspetrsdspsgmtMapper.getAccSpeTrsDspSgmtList(param);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", list);
		return result;

	}

	// ????????????????????????????????????
	public PbcrsReportCagoftrdinf getCagOfTrdInf(String AcctSpecTrstDspnSgmtSeqNo, String CagOfTrdInfSeqNo) {
		PbcrsReportCagoftrdinf result = CagoftrdinfMapper.selectByPrimaryKey(AcctSpecTrstDspnSgmtSeqNo, CagOfTrdInfSeqNo);
		if (result == null)
			result = new PbcrsReportCagoftrdinf();
		return result;

	}

	// ???????????????????????????
	public Map<String, Object> getMotCltCtrInfSgmt(Map<String, Object> param) {
		List<Map<String, Object>> list;
		param = getStartAndEnd(param);
		int total = MotcltctrinfsgmtMapper.getByPageCount(param);
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			list = MotcltctrinfsgmtMapper.getByPage(param);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", list);
		return result;

	}

	// ?????????????????????
	public int updateAcctBsInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ??????????????????
		// PbcrsReportAcctbsinfsgmt acctBsInfSgmt = (PbcrsReportAcctbsinfsgmt)
		// params.get("AcctBsInfSgmt");
		PbcrsReportAcctbsinfsgmt acctBsInfSgmt = (PbcrsReportAcctbsinfsgmt) Util.map2Object2(params, PbcrsReportAcctbsinfsgmt.class);
		PbcrsReportAcctbsinfsgmt pbcrsreportacctbsinfsgmt = AcctbsinfsgmtMapper.selectByPrimaryKey(acctBsInfSgmt.getAcctBsInfSgmtSeqNo());
		if (pbcrsreportacctbsinfsgmt != null) {
			acctBsInfSgmt.setReportflag(pbcrsreportacctbsinfsgmt.getReportflag());
			// ??????????????????
			AcctbsinfsgmtMapper.updateByPrimaryKey(acctBsInfSgmt);
		} else {
			throw new Exception();
		}

		String updateType = params.get("updateType").toString();
		log.info("???????????????:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// ?????????????????????????????????????????????????????????????????????
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(acctBsInfSgmt.getAcctBsInfSgmtSeqNo());
			// ????????????
			PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
			inAcctMdfcBsSgmt.setBsSgmtSeqNo(acctBsInfSgmt.getAcctBsInfSgmtSeqNo());
			inAcctMdfcBsSgmt.setInfRecType("212");
			inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
			inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
			//20220608???????????????rptdate???????????????????????????????????????????????????
			inAcctMdfcBsSgmt.setRptDate(acctbssgmt.getRptDate());
			inAcctMdfcBsSgmt.setMdfcSgmtCode("C");
			inAcctMdfcBsSgmt.setReportflag("0");
			//20220608???????????????rptdate???????????????????????????????????????????????????
			inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(acctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
			InacctmdfcbssgmtMapper.deleteByPrimaryKey(acctBsInfSgmt.getAcctBsInfSgmtSeqNo(), "C");
			int cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
			if (cnt != 1)
				throw new Exception();
		} else {
			// ???????????????????????????
			AcctbsinfsgmtMapper.deleteByPrimaryKeyM(acctBsInfSgmt.getAcctBsInfSgmtSeqNo());

			// ????????????????????????????????????????????????
			int cnt = AcctbsinfsgmtMapper.insertM(acctBsInfSgmt.getAcctBsInfSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}

		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(acctBsInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportacctbsinfsgmt, newMap, "AcctBsSgmt", acctBsInfSgmt.getAcctBsInfSgmtSeqNo());
		return 1;
	}

	// ???????????????
	public int updateAcctBsSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ???????????????????????????????????????????????????
		// PbcrsReportAcctbssgmt acctBsSgmt = (PbcrsReportAcctbssgmt)
		// params.get("AcctBsSgmt");
		PbcrsReportAcctbssgmt acctBsSgmt = (PbcrsReportAcctbssgmt) Util.map2Object2(params, PbcrsReportAcctbssgmt.class);
		PbcrsReportAcctbssgmt pbcrsreportacctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(acctBsSgmt.getAcctBsSgmtSeqNo());
		if (pbcrsreportacctbssgmt != null) {
			acctBsSgmt.setReportflag(pbcrsreportacctbssgmt.getReportflag());
			// acctBsSgmt.setRptDate(nowDate);
			AcctbssgmtMapper.updateByPrimaryKey(acctBsSgmt);
		} else {
			throw new Exception();
		}
		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("???????????????:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// ??????????????????(???????????????????????????????????????):0.???????????????????????? 1.?????????????????????????????????????????? 2.???????????????????????????????????????
			int flag = changeDateType(pbcrsreportacctbssgmt, acctBsSgmt);
			if (flag == 0) {
				PbcrsReportInacctidcagsinf inAcctIDCagsInf = new PbcrsReportInacctidcagsinf();
				// int key = InacctidcagsinfMapper.getKey();
				inAcctIDCagsInf.setInAcctIDCagsInfSeqNo(acctBsSgmt.getAcctBsSgmtSeqNo());
				inAcctIDCagsInf.setInfRecType("211");
				inAcctIDCagsInf.setOdBnesCode(pbcrsreportacctbssgmt.getAcctCode());
				inAcctIDCagsInf.setNwBnesCode(acctBsSgmt.getAcctCode());
				inAcctIDCagsInf.setReportflag("0");
				inAcctIDCagsInf.setEtlDate(new Date());
				inAcctIDCagsInf.setSourceSys(pbcrsreportacctbssgmt.getSourceSys());
				InacctidcagsinfMapper.deleteByPrimaryKey(acctBsSgmt.getAcctBsSgmtSeqNo());
				cnt = InacctidcagsinfMapper.insert(inAcctIDCagsInf);
				if (cnt != 1)
					throw new Exception();

			} else if (flag == 1) {
				// ???????????????????????????
				// AcctbssgmtMapper.deleteByPrimaryKeyM(acctBsSgmt.getAcctBsSgmtSeqNo());
				// ????????????
				PbcrsReportAcctbssgmt acctBsSgmtTemp = acctBsSgmt;

				// ????????????????????????????????????????????????
				// cnt =
				// AcctbssgmtMapper.insertM(acctBsSgmt.getAcctBsSgmtSeqNo());
				// if (cnt != 1)
				// throw new Exception();
				// ????????????
				PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
				inAcctMdfcBsSgmt.setBsSgmtSeqNo(acctBsSgmtTemp.getAcctBsSgmtSeqNo());
				inAcctMdfcBsSgmt.setInfRecType("212");
				inAcctMdfcBsSgmt.setModRecCode(acctBsSgmtTemp.getAcctCode());
				inAcctMdfcBsSgmt.setAcctType(acctBsSgmtTemp.getAcctType());
				inAcctMdfcBsSgmt.setRptDate(pbcrsreportacctbssgmt.getRptDate());
				inAcctMdfcBsSgmt.setMdfcSgmtCode("B");
				inAcctMdfcBsSgmt.setReportflag("0");
				inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(pbcrsreportacctbssgmt.getRptDate(), "yyyy-mm-dd"));
				inAcctMdfcBsSgmt.setSourceSys(pbcrsreportacctbssgmt.getSourceSys());
				InacctmdfcbssgmtMapper.deleteByPrimaryKey(acctBsSgmtTemp.getAcctBsSgmtSeqNo(), "B");
				cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
				if (cnt != 1)
					throw new Exception();
			} else if (flag == 2) {
				// 1.?????????????????????????????????
				PbcrsReportInacctidcagsinf inAcctIDCagsInf = new PbcrsReportInacctidcagsinf();
				// int key = InacctidcagsinfMapper.getKey();
				inAcctIDCagsInf.setInAcctIDCagsInfSeqNo(acctBsSgmt.getAcctBsSgmtSeqNo());
				inAcctIDCagsInf.setInfRecType("211");
				inAcctIDCagsInf.setOdBnesCode(pbcrsreportacctbssgmt.getAcctCode());
				inAcctIDCagsInf.setNwBnesCode(acctBsSgmt.getAcctCode());
				inAcctIDCagsInf.setReportflag("0");
				inAcctIDCagsInf.setEtlDate(new Date());
				inAcctIDCagsInf.setSourceSys(pbcrsreportacctbssgmt.getSourceSys());
				InacctidcagsinfMapper.deleteByPrimaryKey(acctBsSgmt.getAcctBsSgmtSeqNo());
				cnt = InacctidcagsinfMapper.insert(inAcctIDCagsInf);
				if (cnt != 1)
					throw new Exception();
				// 2.??????????????????????????????
				// ???????????????????????????
				// AcctbssgmtMapper.deleteByPrimaryKeyM(acctBsSgmt.getAcctBsSgmtSeqNo());
				// ????????????
				// PbcrsReportAcctbssgmt acctBsSgmtTemp = acctBsSgmt;
				// ??????????????????????????????
				// acctBsSgmtTemp.setReportflag("0");
				// ????????????????????????????????????????????????
				// cnt =
				// AcctbssgmtMapper.insertM(acctBsSgmt.getAcctBsSgmtSeqNo());
				if (cnt != 1)
					throw new Exception();
				// ????????????
				PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
				inAcctMdfcBsSgmt.setBsSgmtSeqNo(acctBsSgmt.getAcctBsSgmtSeqNo());
				inAcctMdfcBsSgmt.setInfRecType("212");
				inAcctMdfcBsSgmt.setModRecCode(acctBsSgmt.getAcctCode());
				inAcctMdfcBsSgmt.setAcctType(acctBsSgmt.getAcctType());
				inAcctMdfcBsSgmt.setRptDate(pbcrsreportacctbssgmt.getRptDate());
				inAcctMdfcBsSgmt.setMdfcSgmtCode("B");
				inAcctMdfcBsSgmt.setReportflag("0");
				inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(pbcrsreportacctbssgmt.getRptDate(), "yyyy-mm-dd"));
				inAcctMdfcBsSgmt.setSourceSys(pbcrsreportacctbssgmt.getSourceSys());
				InacctmdfcbssgmtMapper.deleteByPrimaryKey(acctBsSgmt.getAcctBsSgmtSeqNo(), "B");
				cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
				if (cnt != 1)
					throw new Exception();
			}
		} else {
			// ???????????????????????????
			AcctbssgmtMapper.deleteByPrimaryKeyM(acctBsSgmt.getAcctBsSgmtSeqNo());
			// ????????????????????????????????????????????????
			cnt = AcctbssgmtMapper.insertM(acctBsSgmt.getAcctBsSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();

		}

		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(acctBsSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportacctbssgmt, newMap, "AcctBsSgmt", acctBsSgmt.getAcctBsSgmtSeqNo());
		return 1;
	}

	// ??????????????????????????????
	public int updateRltRepymtInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ???????????????????????????
		// PbcrsReportRltrepymtinf rltRepymtInf = (PbcrsReportRltrepymtinf)
		// params.get("RltRepymtInf");
		PbcrsReportRltrepymtinf rltRepymtInf = (PbcrsReportRltrepymtinf) Util.map2Object2(params, PbcrsReportRltrepymtinf.class);
		PbcrsReportRltrepymtinf pbcrsreportrltrepymtinf = RltrepymtinfMapper.selectByPrimaryKey(rltRepymtInf.getRltRepymtInfSgmtSeqNo(), rltRepymtInf.getRltRepymtInfSeqNo());
		if (pbcrsreportrltrepymtinf != null) {
			rltRepymtInf.setReportflag(pbcrsreportrltrepymtinf.getReportflag());
			// ??????????????????
			RltrepymtinfMapper.updateByPrimaryKey(rltRepymtInf);
		} else {
			throw new Exception();
		}
		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("???????????????:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// ?????????????????????????????????????????????????????????????????????
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(rltRepymtInf.getRltRepymtInfSgmtSeqNo());
			// ????????????
			PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
			inAcctMdfcBsSgmt.setBsSgmtSeqNo(rltRepymtInf.getRltRepymtInfSgmtSeqNo());
			inAcctMdfcBsSgmt.setInfRecType("212");
			inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
			inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
			inAcctMdfcBsSgmt.setRptDate(acctbssgmt.getRptDate());
			inAcctMdfcBsSgmt.setMdfcSgmtCode("D");
			inAcctMdfcBsSgmt.setReportflag("0");
			inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(acctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
			InacctmdfcbssgmtMapper.deleteByPrimaryKey(rltRepymtInf.getRltRepymtInfSgmtSeqNo(), "D");
			cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
			if (cnt != 1)
				throw new Exception();
		} else {
			// ???????????????????????????????????????????????????????????????
			List<PbcrsReportRltrepymtinf> list = RltrepymtinfMapper.getByFirstKey(rltRepymtInf.getRltRepymtInfSgmtSeqNo());
			for (PbcrsReportRltrepymtinf temp : list) {
				// ??????????????????????????????
				RltrepymtinfMapper.deleteByPrimaryKeyM(temp.getRltRepymtInfSgmtSeqNo(), temp.getRltRepymtInfSeqNo());
				// ??????????????????????????????
				// temp.setReportflag("0");
				// ????????????????????????????????????????????????
				cnt = RltrepymtinfMapper.insertM(temp.getRltRepymtInfSgmtSeqNo(), temp.getRltRepymtInfSeqNo());
				if (cnt != 1)
					throw new Exception();
			}
			// ??????????????????????????????
			PbcrsReportRltrepymtinfsgmt rltrepymtinfsgmt = RltrepymtinfsgmtMapper.selectByPrimaryKey(rltRepymtInf.getRltRepymtInfSgmtSeqNo());
			RltrepymtinfsgmtMapper.deleteByPrimaryKeyM(rltrepymtinfsgmt.getRltRepymtInfSgmtSeqNo());

			cnt = RltrepymtinfsgmtMapper.insertM(rltrepymtinfsgmt.getRltRepymtInfSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();

		}

		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(rltRepymtInf);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportrltrepymtinf, newMap, "RltRepymtInfSgmt", rltRepymtInf.getRltRepymtInfSgmtSeqNo());
		return 1;
	}

	// ?????????????????????
	public int updateAcctCredSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ??????????????????
		// PbcrsReportAcctcredsgmt acctcredsgmt = (PbcrsReportAcctcredsgmt)
		// params.get("Acctcredsgmt");
		PbcrsReportAcctcredsgmt acctcredsgmt = (PbcrsReportAcctcredsgmt) Util.map2Object2(params, PbcrsReportAcctcredsgmt.class);
		PbcrsReportAcctcredsgmt pbcrsReportAcctcredsgmt = AcctCredSgmtMapper.selectByPrimaryKey(acctcredsgmt.getAcctCredSgmtSeqNo());
		if (pbcrsReportAcctcredsgmt != null) {
			acctcredsgmt.setReportflag(pbcrsReportAcctcredsgmt.getReportflag());
			// ??????????????????
			AcctCredSgmtMapper.updateByPrimaryKey(acctcredsgmt);
		} else {
			throw new Exception();
		}

		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("???????????????:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// ?????????????????????????????????????????????????????????????????????
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(acctcredsgmt.getAcctCredSgmtSeqNo());
			// ????????????
			PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
			inAcctMdfcBsSgmt.setBsSgmtSeqNo(acctcredsgmt.getAcctCredSgmtSeqNo());
			inAcctMdfcBsSgmt.setInfRecType("212");
			inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
			inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
			inAcctMdfcBsSgmt.setRptDate(acctbssgmt.getRptDate());
			inAcctMdfcBsSgmt.setMdfcSgmtCode("F");
			inAcctMdfcBsSgmt.setReportflag("0");
			inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(acctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
			InacctmdfcbssgmtMapper.deleteByPrimaryKey(acctcredsgmt.getAcctCredSgmtSeqNo(), "F");
			cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
			if (cnt != 1)
				throw new Exception();
		} else {
			// ???????????????????????????
			AcctCredSgmtMapper.deleteByPrimaryKeyM(acctcredsgmt.getAcctCredSgmtSeqNo());

			// ????????????????????????????????????????????????
			cnt = AcctCredSgmtMapper.insertM(acctcredsgmt.getAcctCredSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();

		}

		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(acctcredsgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsReportAcctcredsgmt, newMap, "AcctCredSgmt", acctcredsgmt.getAcctCredSgmtSeqNo());
		return 1;
	}

	// ???????????????????????????
	public int updateOriCreInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ????????????
		// PbcrsReportOricreinfsgmt oriCreInfSgmt = (PbcrsReportOricreinfsgmt)
		// params.get("OriCreInfSgmt");
		PbcrsReportOricreinfsgmt oriCreInfSgmt = (PbcrsReportOricreinfsgmt) Util.map2Object2(params, PbcrsReportOricreinfsgmt.class);
		PbcrsReportOricreinfsgmt pbcrsreportoricreinfsgmt = OricreinfsgmtMapper.selectByPrimaryKey(oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());
		if (pbcrsreportoricreinfsgmt != null) {
			oriCreInfSgmt.setReportflag(pbcrsreportoricreinfsgmt.getReportflag());
			// ??????????????????
			OricreinfsgmtMapper.updateByPrimaryKey(oriCreInfSgmt);
		} else {
			throw new Exception();
		}
		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("???????????????:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// ?????????????????????????????????????????????????????????????????????
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());
			// ????????????
			PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
			inAcctMdfcBsSgmt.setBsSgmtSeqNo(oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());
			inAcctMdfcBsSgmt.setInfRecType("212");
			inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
			inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
			inAcctMdfcBsSgmt.setRptDate(acctbssgmt.getRptDate());
			inAcctMdfcBsSgmt.setMdfcSgmtCode("G");
			inAcctMdfcBsSgmt.setReportflag("0");
			inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(acctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
			InacctmdfcbssgmtMapper.deleteByPrimaryKey(oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo(), "G");
			cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
			if (cnt != 1)
				throw new Exception();
		} else {

			// ???????????????????????????
			OricreinfsgmtMapper.deleteByPrimaryKeyM(oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());

			// ????????????????????????????????????????????????
			cnt = OricreinfsgmtMapper.insertM(oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();

		}

		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(oriCreInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportoricreinfsgmt, newMap, "OriCreInfSgmt", oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());
		return 1;
	}

	// ???????????????????????????
	public int updateAccMthBlgInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ????????????
		// PbcrsReportAccmthblginfsgmt accMthBlgInfSgmt =
		// (PbcrsReportAccmthblginfsgmt) params.get("AccMthBlgInfSgmt");
		PbcrsReportAccmthblginfsgmt accMthBlgInfSgmt = (PbcrsReportAccmthblginfsgmt) Util.map2Object2(params, PbcrsReportAccmthblginfsgmt.class);
		PbcrsReportAccmthblginfsgmt pbcrsreportaccmthblginfsgmt = AccmthblginfsgmtMapper.selectByPrimaryKey(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());
		if (pbcrsreportaccmthblginfsgmt != null) {
			accMthBlgInfSgmt.setReportflag(pbcrsreportaccmthblginfsgmt.getReportflag());
			// ??????????????????
			AccmthblginfsgmtMapper.updateByPrimaryKey(accMthBlgInfSgmt);
		} else {
			throw new Exception();
		}

		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("???????????????:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// ?????????????????????????????????????????????????????????????????????
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());
			// ????????????
			PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
			inAcctMdfcBsSgmt.setBsSgmtSeqNo(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());
			inAcctMdfcBsSgmt.setInfRecType("212");
			inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
			inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
			inAcctMdfcBsSgmt.setRptDate(acctbssgmt.getRptDate());//--rptdate????????????????????????????????????????????????????????????
			inAcctMdfcBsSgmt.setMdfcSgmtCode("H");
			inAcctMdfcBsSgmt.setReportflag("0");
			inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(acctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
			InacctmdfcbssgmtMapper.deleteByPrimaryKey(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo(), "H");
			cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
			if (cnt != 1)
				throw new Exception();
		} else {

			// ???????????????????????????
			AccmthblginfsgmtMapper.deleteByPrimaryKeyM(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());

			// ????????????????????????????????????????????????
			cnt = AccmthblginfsgmtMapper.insertM(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}

		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(accMthBlgInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportaccmthblginfsgmt, newMap, "AccMthBlgInfSgmt", accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());
		return 1;
	}

	// ?????????????????????????????????
	public int updateSpecPrdSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ????????????
		// PbcrsReportSpecprdsgmt specPrdSgmt = (PbcrsReportSpecprdsgmt)
		// params.get("SpecPrdSgmt");
		PbcrsReportSpecprdsgmt specPrdSgmt = (PbcrsReportSpecprdsgmt) Util.map2Object2(params, PbcrsReportSpecprdsgmt.class);
		PbcrsReportSpecprdsgmt pbcrsreportspecprdsgmt = SpecprdsgmtMapper.selectByPrimaryKey(specPrdSgmt.getSpecPrdSgmtSeqNo());
		if (pbcrsreportspecprdsgmt != null) {
			specPrdSgmt.setReportflag(pbcrsreportspecprdsgmt.getReportflag());
			// ??????????????????
			SpecprdsgmtMapper.updateByPrimaryKey(specPrdSgmt);
		} else {
			throw new Exception();
		}

		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("???????????????:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// ?????????????????????????????????????????????????????????????????????
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(specPrdSgmt.getSpecPrdSgmtSeqNo());
			// ????????????
			PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
			inAcctMdfcBsSgmt.setBsSgmtSeqNo(specPrdSgmt.getSpecPrdSgmtSeqNo());
			inAcctMdfcBsSgmt.setInfRecType("212");
			inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
			inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
			inAcctMdfcBsSgmt.setRptDate(acctbssgmt.getRptDate());
			inAcctMdfcBsSgmt.setMdfcSgmtCode("I");
			inAcctMdfcBsSgmt.setReportflag("0");
			inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(acctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
			InacctmdfcbssgmtMapper.deleteByPrimaryKey(specPrdSgmt.getSpecPrdSgmtSeqNo(), "I");
			cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
			if (cnt != 1)
				throw new Exception();
		} else {
			// ???????????????????????????
			SpecprdsgmtMapper.deleteByPrimaryKeyM(specPrdSgmt.getSpecPrdSgmtSeqNo());
			// ????????????

			// ????????????????????????????????????????????????
			cnt = SpecprdsgmtMapper.insertM(specPrdSgmt.getSpecPrdSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}

		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(specPrdSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportspecprdsgmt, newMap, "SpecPrdSgmt", specPrdSgmt.getSpecPrdSgmtSeqNo());
		return 1;
	}

	// ?????????????????????
	public int updateAcctDbtInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ????????????
		// PbcrsReportAcctdbtinfsgmt acctDbtInfSgmt =
		// (PbcrsReportAcctdbtinfsgmt) params.get("AcctDbtInfSgmt");
		PbcrsReportAcctdbtinfsgmt acctDbtInfSgmt = (PbcrsReportAcctdbtinfsgmt) Util.map2Object2(params, PbcrsReportAcctdbtinfsgmt.class);
		PbcrsReportAcctdbtinfsgmt pbcrsreportacctdbtinfsgmt = AcctdbtinfsgmtMapper.selectByPrimaryKey(acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());
		if (pbcrsreportacctdbtinfsgmt != null) {
			acctDbtInfSgmt.setReportflag(pbcrsreportacctdbtinfsgmt.getReportflag());
			// ??????????????????
			AcctdbtinfsgmtMapper.updateByPrimaryKey(acctDbtInfSgmt);
		} else {
			throw new Exception();
		}

		int cnt = 0;

		String updateType = params.get("updateType").toString();
		log.info("???????????????:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// ?????????????????????????????????????????????????????????????????????
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());
			// ????????????
			PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
			inAcctMdfcBsSgmt.setBsSgmtSeqNo(acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());
			inAcctMdfcBsSgmt.setInfRecType("212");
			inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
			inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
			inAcctMdfcBsSgmt.setRptDate(acctbssgmt.getRptDate());
			inAcctMdfcBsSgmt.setMdfcSgmtCode("J");
			inAcctMdfcBsSgmt.setReportflag("0");
			inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(acctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
			InacctmdfcbssgmtMapper.deleteByPrimaryKey(acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo(), "J");
			cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
			if (cnt != 1)
				throw new Exception();
		} else {
			// ???????????????????????????
			AcctdbtinfsgmtMapper.deleteByPrimaryKeyM(acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());

			// ????????????????????????????????????????????????
			cnt = AcctdbtinfsgmtMapper.insertM(acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}

		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(acctDbtInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportacctdbtinfsgmt, newMap, "AcctDbtInfSgmt", acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());
		return 1;
	}

	// ?????????????????????????????????
	public int updateAccSpeTrsDspSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ????????????????????????
		// PbcrsReportCagoftrdinf cagOfTrdInf = (PbcrsReportCagoftrdinf)
		// params.get("CagOfTrdInf");
		PbcrsReportCagoftrdinf cagOfTrdInf = (PbcrsReportCagoftrdinf) Util.map2Object2(params, PbcrsReportCagoftrdinf.class);
		PbcrsReportCagoftrdinf pbcrsreportcagoftrdinf = CagoftrdinfMapper.selectByPrimaryKey(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo(), cagOfTrdInf.getCagOfTrdInfSeqNo());
		if (pbcrsreportcagoftrdinf != null) {
			// ??????????????????
			cagOfTrdInf.setReportflag(pbcrsreportcagoftrdinf.getReportflag());
			CagoftrdinfMapper.updateByPrimaryKey(cagOfTrdInf);
		} else {
			throw new Exception();
		}

		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("???????????????:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// ?????????????????????????????????????????????????????????????????????
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());
			// ????????????
			PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
			inAcctMdfcBsSgmt.setBsSgmtSeqNo(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());
			inAcctMdfcBsSgmt.setInfRecType("212");
			inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
			inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
			inAcctMdfcBsSgmt.setRptDate(acctbssgmt.getRptDate());
			inAcctMdfcBsSgmt.setMdfcSgmtCode("K");
			inAcctMdfcBsSgmt.setReportflag("0");
			inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(acctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
			InacctmdfcbssgmtMapper.deleteByPrimaryKey(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo(), "K");
			cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
			if (cnt != 1)
				throw new Exception();
		} else {
			// ?????????????????????????????????????????????????????????
			List<PbcrsReportCagoftrdinf> list = CagoftrdinfMapper.getByFirstKey(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());
			for (PbcrsReportCagoftrdinf temp : list) {
				// ???????????????????????????????????????
				CagoftrdinfMapper.deleteByPrimaryKeyM(temp.getAcctSpecTrstDspnSgmtSeqNo(), temp.getCagOfTrdInfSeqNo());

				// ????????????????????????????????????????????????
				cnt = CagoftrdinfMapper.insertM(temp.getAcctSpecTrstDspnSgmtSeqNo(), temp.getCagOfTrdInfSeqNo());
				if (cnt != 1)
					throw new Exception();
			}
			// ???????????????????????????????????????
			PbcrsReportAccspetrsdspsgmt accspetrsdspsgmt = AccspetrsdspsgmtMapper.selectByPrimaryKey(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());
			AccspetrsdspsgmtMapper.deleteByPrimaryKeyM(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());

			cnt = AccspetrsdspsgmtMapper.insertM(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}

		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(cagOfTrdInf);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportcagoftrdinf, newMap, "AccSpeTrsDspSgmt", cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());
		return 1;
	}

	public int delete(Map<String, Object> params) throws Exception {
		String infoKey = params.get("infoKey").toString();
		PbcrsReportInacctentdel inAcctEntDel = InacctentdelMapper.selectByPrimaryKey(infoKey);
		if (inAcctEntDel != null) {
			return 0;
		}
		PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(infoKey);
		if (acctbssgmt != null) {
			String isDel = acctbssgmt.getIsDel();
			if (StringUtils.isNotEmpty(isDel) && isDel.equals("1")) {
				return 0;
			}
		}
		PbcrsReportInacctentdel pbcrsReportInacctentdel = new PbcrsReportInacctentdel();
		pbcrsReportInacctentdel.setInAcctEntDelSeqNo(infoKey);
		pbcrsReportInacctentdel.setInfRecType("214");
		pbcrsReportInacctentdel.setDelRecCode(acctbssgmt.getAcctCode());
		pbcrsReportInacctentdel.setReportFlag("0");
		pbcrsReportInacctentdel.setEtlDate(new Date());
		pbcrsReportInacctentdel.setSourceSys(acctbssgmt.getSourceSys());
		int cnt = InacctentdelMapper.insert(pbcrsReportInacctentdel);
		if (cnt != 1) {
			throw new Exception();
		}
		acctbssgmt.setIsDel("1");
		cnt = AcctbssgmtMapper.updateByPrimaryKey(acctbssgmt);
		if (cnt != 1) {
			throw new Exception();
		}
		return 1;
	}
	
	
	public int deleteAcctBsInfSgmt(Map<String, Object> param) throws Exception {
		String seqno = param.get("INACCTINFSEQNO").toString();
		String startDate = param.get("startDate")==null?null:param.get("startDate").toString();
		PbcrsReportAcctbsinfsgmt pbcrsReportAcctbsinfsgmt = AcctbsinfsgmtMapper.selectByPrimaryKey(seqno);
		//?????????????????????1,???????????????????????????
		pbcrsReportAcctbsinfsgmt.setParagraphDel("1");
		int updateCount = AcctbsinfsgmtMapper.updateByPrimaryKey(pbcrsReportAcctbsinfsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//?????????????????????????????????????????????
		PbcrsReportAcctbssgmt pbcrsReportAcctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(seqno);
		String acctCode = pbcrsReportAcctbssgmt.getAcctCode();
		String rptDate = pbcrsReportAcctbssgmt.getRptDate();
		String sourceSys = pbcrsReportAcctbssgmt.getSourceSys();
		Map<String,String> params = new HashMap<String, String>();
		params.put("InAcctDelSeqNo",seqno);
		params.put("DelSgmtCode","C");
		InacctdelMapper.deleteByPrimaryKey(params);
		PbcrsReportInacctdel pbcrsReportInacctdel = new PbcrsReportInacctdel();
		pbcrsReportInacctdel.setInAcctDelSeqNo(seqno);
		pbcrsReportInacctdel.setInfRecType("213");
		pbcrsReportInacctdel.setDelRecCode(acctCode);
		pbcrsReportInacctdel.setDelSgmtCode("C");
		pbcrsReportInacctdel.setReportFlag("0");
		Date etl_date = DateUtils.parseDate(rptDate, "yyyy-MM-dd");
		pbcrsReportInacctdel.setEtl_Date(etl_date);
		pbcrsReportInacctdel.setSourceSys(sourceSys);
		pbcrsReportInacctdel.setDelStartDate(startDate);
		pbcrsReportInacctdel.setDelEndDate(rptDate);
		int insertCount = InacctdelMapper.insert(pbcrsReportInacctdel);
		if(insertCount != 1){
			throw new Exception();
		}
		return 1;
	}
	
	public int deleteAccMthBlgInfSgmt(Map<String, Object> param) throws Exception {
		String seqno = param.get("INACCTINFSEQNO").toString();
		String startDate = param.get("startDate")==null?null:param.get("startDate").toString();
		 PbcrsReportAccmthblginfsgmt pbcrsReportAccmthblginfsgmt = AccmthblginfsgmtMapper.selectByPrimaryKey(seqno);
		//?????????????????????1,???????????????????????????
		 pbcrsReportAccmthblginfsgmt.setParagraphDel("1");
		int updateCount = AccmthblginfsgmtMapper.updateByPrimaryKey(pbcrsReportAccmthblginfsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//?????????????????????????????????????????????
		PbcrsReportAcctbssgmt pbcrsReportAcctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(seqno);
		String acctCode = pbcrsReportAcctbssgmt.getAcctCode();
		String rptDate = pbcrsReportAcctbssgmt.getRptDate();
		String sourceSys = pbcrsReportAcctbssgmt.getSourceSys();
		Map<String,String> params = new HashMap<String, String>();
		params.put("InAcctDelSeqNo",seqno);
		params.put("DelSgmtCode","H");
		InacctdelMapper.deleteByPrimaryKey(params);
		PbcrsReportInacctdel pbcrsReportInacctdel = new PbcrsReportInacctdel();
		pbcrsReportInacctdel.setInAcctDelSeqNo(seqno);
		pbcrsReportInacctdel.setInfRecType("213");
		pbcrsReportInacctdel.setDelRecCode(acctCode);
		pbcrsReportInacctdel.setDelSgmtCode("H");
		pbcrsReportInacctdel.setReportFlag("0");
		Date etl_date = DateUtils.parseDate(rptDate, "yyyy-MM-dd");
		pbcrsReportInacctdel.setEtl_Date(etl_date);
		pbcrsReportInacctdel.setSourceSys(sourceSys);
		pbcrsReportInacctdel.setDelStartDate(startDate);
		pbcrsReportInacctdel.setDelEndDate(rptDate);
		int insertCount = InacctdelMapper.insert(pbcrsReportInacctdel);
		if(insertCount != 1){
			throw new Exception();
		}
		return 1;
	}

	public int deleteSpecPrdSgmt(Map<String, Object> param) throws Exception {
		String seqno = param.get("INACCTINFSEQNO").toString();
		String startDate = param.get("startDate")==null?null:param.get("startDate").toString();
		PbcrsReportSpecprdsgmt pbcrsReportSpecprdsgmt = SpecprdsgmtMapper.selectByPrimaryKey(seqno);
		//?????????????????????1,???????????????????????????
		pbcrsReportSpecprdsgmt.setParagraphDel("1");
		int updateCount = SpecprdsgmtMapper.updateByPrimaryKey(pbcrsReportSpecprdsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//?????????????????????????????????????????????
		PbcrsReportAcctbssgmt pbcrsReportAcctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(seqno);
		String acctCode = pbcrsReportAcctbssgmt.getAcctCode();
		String rptDate = pbcrsReportAcctbssgmt.getRptDate();
		String sourceSys = pbcrsReportAcctbssgmt.getSourceSys();
		Map<String,String> params = new HashMap<String, String>();
		params.put("InAcctDelSeqNo",seqno);
		params.put("DelSgmtCode","I");
		InacctdelMapper.deleteByPrimaryKey(params);
		PbcrsReportInacctdel pbcrsReportInacctdel = new PbcrsReportInacctdel();
		pbcrsReportInacctdel.setInAcctDelSeqNo(seqno);
		pbcrsReportInacctdel.setInfRecType("213");
		pbcrsReportInacctdel.setDelRecCode(acctCode);
		pbcrsReportInacctdel.setDelSgmtCode("I");
		pbcrsReportInacctdel.setReportFlag("0");
		Date etl_date = DateUtils.parseDate(rptDate, "yyyy-MM-dd");
		pbcrsReportInacctdel.setEtl_Date(etl_date);
		pbcrsReportInacctdel.setSourceSys(sourceSys);
		pbcrsReportInacctdel.setDelStartDate(startDate);
		pbcrsReportInacctdel.setDelEndDate(rptDate);
		int insertCount = InacctdelMapper.insert(pbcrsReportInacctdel);
		if(insertCount != 1){
			throw new Exception();
		}
		return 1;
	}
	
	public int deleteAcctDbtInfSgmt(Map<String, Object> param) throws Exception {
		String seqno = param.get("INACCTINFSEQNO").toString();
		String startDate = param.get("startDate")==null?null:param.get("startDate").toString();
		 PbcrsReportAcctdbtinfsgmt pbcrsReportAcctdbtinfsgmt = AcctdbtinfsgmtMapper.selectByPrimaryKey(seqno);
		//?????????????????????1,???????????????????????????
		 pbcrsReportAcctdbtinfsgmt.setParagraphDel("1");
		int updateCount = AcctdbtinfsgmtMapper.updateByPrimaryKey(pbcrsReportAcctdbtinfsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//?????????????????????????????????????????????
		PbcrsReportAcctbssgmt pbcrsReportAcctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(seqno);
		String acctCode = pbcrsReportAcctbssgmt.getAcctCode();
		String rptDate = pbcrsReportAcctbssgmt.getRptDate();
		String sourceSys = pbcrsReportAcctbssgmt.getSourceSys();
		Map<String,String> params = new HashMap<String, String>();
		params.put("InAcctDelSeqNo",seqno);
		params.put("DelSgmtCode","J");
		InacctdelMapper.deleteByPrimaryKey(params);
		PbcrsReportInacctdel pbcrsReportInacctdel = new PbcrsReportInacctdel();
		pbcrsReportInacctdel.setInAcctDelSeqNo(seqno);
		pbcrsReportInacctdel.setInfRecType("213");
		pbcrsReportInacctdel.setDelRecCode(acctCode);
		pbcrsReportInacctdel.setDelSgmtCode("J");
		pbcrsReportInacctdel.setReportFlag("0");
		Date etl_date = DateUtils.parseDate(rptDate, "yyyy-MM-dd");
		pbcrsReportInacctdel.setEtl_Date(etl_date);
		pbcrsReportInacctdel.setSourceSys(sourceSys);
		pbcrsReportInacctdel.setDelStartDate(startDate);
		pbcrsReportInacctdel.setDelEndDate(rptDate);
		int insertCount = InacctdelMapper.insert(pbcrsReportInacctdel);
		if(insertCount != 1){
			throw new Exception();
		}
		return 1;
	}
	
	public int deleteAccSpeTrsDspSgmt(Map<String, Object> param) throws Exception {
		String seqno = param.get("INACCTINFSEQNO").toString();
		String startDate = param.get("startDate")==null?null:param.get("startDate").toString();
		 PbcrsReportAccspetrsdspsgmt pbcrsReportAccspetrsdspsgmt = AccspetrsdspsgmtMapper.selectByPrimaryKey(seqno);
		//?????????????????????1,???????????????????????????
		 pbcrsReportAccspetrsdspsgmt.setParagraphDel("1");
		int updateCount = AccspetrsdspsgmtMapper.updateByPrimaryKey(pbcrsReportAccspetrsdspsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		List<PbcrsReportCagoftrdinf> byFirstKey = CagoftrdinfMapper.getByFirstKey(seqno);
		if(byFirstKey != null){
			for (PbcrsReportCagoftrdinf pbcrsReportCagoftrdinf : byFirstKey) {
				pbcrsReportCagoftrdinf.setParagraphDel("1");
				int subInsertCount = CagoftrdinfMapper.updateByPrimaryKey(pbcrsReportCagoftrdinf);
				if(subInsertCount != 1){
					throw new Exception();
				}
			}
		}
		
		
		//?????????????????????????????????????????????
		PbcrsReportAcctbssgmt pbcrsReportAcctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(seqno);
		String acctCode = pbcrsReportAcctbssgmt.getAcctCode();
		String rptDate = pbcrsReportAcctbssgmt.getRptDate();
		String sourceSys = pbcrsReportAcctbssgmt.getSourceSys();
		Map<String,String> params = new HashMap<String, String>();
		params.put("InAcctDelSeqNo",seqno);
		params.put("DelSgmtCode","K");
		InacctdelMapper.deleteByPrimaryKey(params);
		PbcrsReportInacctdel pbcrsReportInacctdel = new PbcrsReportInacctdel();
		pbcrsReportInacctdel.setInAcctDelSeqNo(seqno);
		pbcrsReportInacctdel.setInfRecType("213");
		pbcrsReportInacctdel.setDelRecCode(acctCode);
		pbcrsReportInacctdel.setDelSgmtCode("K");
		pbcrsReportInacctdel.setReportFlag("0");
		Date etl_date = DateUtils.parseDate(rptDate, "yyyy-MM-dd");
		pbcrsReportInacctdel.setEtl_Date(etl_date);
		pbcrsReportInacctdel.setSourceSys(sourceSys);
		pbcrsReportInacctdel.setDelStartDate(startDate);
		pbcrsReportInacctdel.setDelEndDate(rptDate);
		int insertCount = InacctdelMapper.insert(pbcrsReportInacctdel);
		if(insertCount != 1){
			throw new Exception();
		}
		return 1;
	}
	
	
	/**
	 * ???????????????????????????
	 * 
	 * @return
	 */
	public static int checkParameter(PbcrsReportAcctbssgmt bssgmt1, PbcrsReportAcctbssgmt bssgmt2) {

		if (bssgmt1.getAcctCode().equals(bssgmt2.getAcctCode())) {

			return 0;
		} else {
			// ????????????toString()???????????????????????????????????????????????????????????????
			if (bssgmt1.toString().equals(bssgmt2.toString())) {

				return 1;
			} else {

				return 2;
			}
		}

	}

	/**
	 * ???????????????????????????
	 * 
	 * @return 0??????????????????????????? 1:????????????????????? 2:??????????????????????????????????????????
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * 
	 */
	public int changeDateType(PbcrsReportAcctbssgmt oldData, PbcrsReportAcctbssgmt newData) throws Exception {
		String oldAcctCode = String.valueOf(oldData.getAcctCode());
		String newAcctCode = String.valueOf(newData.getAcctCode());
		// ?????????????????????????????????
		if (!oldAcctCode.equals(newAcctCode)) {

			Class clazz = oldData.getClass();
			Class clazz1 = newData.getClass();
			Field[] fields = clazz.getDeclaredFields();
			// ???????????????????????????????????????????????????????????????????????????
			for (Field oldField : fields) {
				int modifiers = oldField.getModifiers();
				if (!Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers)) {
					oldField.setAccessible(true);
					String name = oldField.getName();
					if (!name.equals("AcctCode") && !name.equals("RptDate") && !name.equals("IsDel") && !name.equals("SourceSys")) {
						Field newField = clazz1.getDeclaredField(name);
						newField.setAccessible(true);
						String oldString = String.valueOf(oldField.get(oldData));
						String newString = String.valueOf(newField.get(newData));
						if (!oldString.equals(newString)) {
							// ???????????????????????????2
							return 2;
						}
					}
				}
			}
			// ?????????????????????0
			return 0;
		} else {
			// ????????????????????????????????????????????????
			return 1;
		}
	}

	// ??????????????????
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
	public int updateByMap(Map map) {
		// ?????????
		AcctbssgmtMapper.updateByMap(map);
		// ???????????????
		AcctbsinfsgmtMapper.updateByMap(map);
		// ?????????????????????
		AccmthblginfsgmtMapper.updateByMap(map);
		// ???????????????
		AcctdbtinfsgmtMapper.updateByMap(map);
		// ???????????????????????????
		AccspetrsdspsgmtMapper.updateByMap(map);
		// ????????????????????????
		CagoftrdinfMapper.updateByMap(map);
		// ?????????????????????
		MotcltctrinfsgmtMapper.updateByMap(map);
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


	public int confirm(Map<String, Object> param) {
		return AcctbssgmtMapper.confirm(param);
	}
}
