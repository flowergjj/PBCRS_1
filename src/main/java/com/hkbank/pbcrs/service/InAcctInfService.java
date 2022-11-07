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
	// 痕迹表service
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;
	// 基础段
	@Autowired
	private PbcrsReportAcctbssgmtMapper AcctbssgmtMapper;
	// 基本信息段
	@Autowired
	private PbcrsReportAcctbsinfsgmtMapper AcctbsinfsgmtMapper;
	// 相关还款责任人信息段
	@Autowired
	private PbcrsReportRltrepymtinfsgmtMapper RltrepymtinfsgmtMapper;
	// 相关还款责任人信息详细信息
	@Autowired
	private PbcrsReportRltrepymtinfMapper RltrepymtinfMapper;
	// 授信额度信息
	@Autowired
	private PbcrsReportAcctcredsgmtMapper AcctCredSgmtMapper;
	// 初始债权说明段
	@Autowired
	private PbcrsReportOricreinfsgmtMapper OricreinfsgmtMapper;
	// 月度表现信息段
	@Autowired
	private PbcrsReportAccmthblginfsgmtMapper AccmthblginfsgmtMapper;
	// 大额专项分期信息段
	@Autowired
	private PbcrsReportSpecprdsgmtMapper SpecprdsgmtMapper;
	// 非月度表现
	@Autowired
	private PbcrsReportAcctdbtinfsgmtMapper AcctdbtinfsgmtMapper;
	// 特殊交易说明信息段
	@Autowired
	private PbcrsReportAccspetrsdspsgmtMapper AccspetrsdspsgmtMapper;
	// 特殊交易信息详细
	@Autowired
	private PbcrsReportCagoftrdinfMapper CagoftrdinfMapper;
	// 抵质押物信息段
	@Autowired
	private PbcrsReportMotcltctrinfsgmtMapper MotcltctrinfsgmtMapper;

	// 待更正段-基础段
	@Autowired
	private PbcrsReportInacctmdfcbssgmtMapper InacctmdfcbssgmtMapper;
	// 待更正段-待更正
	@Autowired
	private PbcrsReportInaccmdfmdfsgmtMapper InaccmdfmdfsgmtMapper;
	// 标识变更请求记录
	@Autowired
	private PbcrsReportInacctidcagsinfMapper InacctidcagsinfMapper;

	@Autowired
	private PbcrsReportInacctentdelMapper InacctentdelMapper;
	
	//按段删除基本信息段
	@Autowired
	private PbcrsReportInacctdelMapper InacctdelMapper;

	// 查询主列表信息
	public Map<String, Object> getBaseInfoList(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		param = getStartAndEnd(param);
		// 查询总数
		int total = AcctbssgmtMapper.getCount(param);
		// 判断total有没有值，或者total小于要查询开始的index
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// 查询列表
			list = AcctbssgmtMapper.getAllandPage(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;
	}

	// 查询基础段信息
	public PbcrsReportAcctbssgmt getAcctBsSgmt(String AcctBsSgmtSeqNo) {
		PbcrsReportAcctbssgmt result = AcctbssgmtMapper.selectByPrimaryKey(AcctBsSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportAcctbssgmt();
		return result;

	}

	// 查询基本信息段
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

	// 查询初始债权说明段
	public PbcrsReportOricreinfsgmt getOriCreInfSgmt(String OrigCreditorInfSgmtSeqNo) {
		PbcrsReportOricreinfsgmt result = OricreinfsgmtMapper.selectByPrimaryKey(OrigCreditorInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportOricreinfsgmt();
		return result;

	}

	// 查询月度表现信息段
	public PbcrsReportAccmthblginfsgmt getAccMthBlgInfSgmt(String AcctMthlyBlgInfSgmtSeqNo) {
		PbcrsReportAccmthblginfsgmt result = AccmthblginfsgmtMapper.selectByPrimaryKey(AcctMthlyBlgInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportAccmthblginfsgmt();
		return result;

	}

	// 查询大额专项分期信息段
	public PbcrsReportSpecprdsgmt getSpecPrdSgmt(String SpecPrdSgmtSeqNo) {
		PbcrsReportSpecprdsgmt result = SpecprdsgmtMapper.selectByPrimaryKey(SpecPrdSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportSpecprdsgmt();
		return result;

	}

	// 查询非月度表现
	public PbcrsReportAcctdbtinfsgmt getAcctDbtInfSgmt(String AcctDbtInfSgmtSeqNo) {
		PbcrsReportAcctdbtinfsgmt result = AcctdbtinfsgmtMapper.selectByPrimaryKey(AcctDbtInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportAcctdbtinfsgmt();
		return result;

	}

	// 查询相关还款责任人信息段
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

	// 查询相关还款责任人单个信息
	public PbcrsReportRltrepymtinf getRltRepymtInf(String RltRepymtInfSgmtSeqNo, String RltRepymtInfSeqNo) {
		PbcrsReportRltrepymtinf result = RltrepymtinfMapper.selectByPrimaryKey(RltRepymtInfSgmtSeqNo, RltRepymtInfSeqNo);
		if (result == null)
			result = new PbcrsReportRltrepymtinf();
		return result;

	}

	// 查询特殊交易说明信息段
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

	// 查询特殊交易说明单个信息
	public PbcrsReportCagoftrdinf getCagOfTrdInf(String AcctSpecTrstDspnSgmtSeqNo, String CagOfTrdInfSeqNo) {
		PbcrsReportCagoftrdinf result = CagoftrdinfMapper.selectByPrimaryKey(AcctSpecTrstDspnSgmtSeqNo, CagOfTrdInfSeqNo);
		if (result == null)
			result = new PbcrsReportCagoftrdinf();
		return result;

	}

	// 查询抵质押物信息段
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

	// 修改基本信息段
	public int updateAcctBsInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 基本信息段值
		// PbcrsReportAcctbsinfsgmt acctBsInfSgmt = (PbcrsReportAcctbsinfsgmt)
		// params.get("AcctBsInfSgmt");
		PbcrsReportAcctbsinfsgmt acctBsInfSgmt = (PbcrsReportAcctbsinfsgmt) Util.map2Object2(params, PbcrsReportAcctbsinfsgmt.class);
		PbcrsReportAcctbsinfsgmt pbcrsreportacctbsinfsgmt = AcctbsinfsgmtMapper.selectByPrimaryKey(acctBsInfSgmt.getAcctBsInfSgmtSeqNo());
		if (pbcrsreportacctbsinfsgmt != null) {
			acctBsInfSgmt.setReportflag(pbcrsreportacctbsinfsgmt.getReportflag());
			// 更新原表信息
			AcctbsinfsgmtMapper.updateByPrimaryKey(acctBsInfSgmt);
		} else {
			throw new Exception();
		}

		String updateType = params.get("updateType").toString();
		log.info("修改类型为:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(acctBsInfSgmt.getAcctBsInfSgmtSeqNo());
			// 待更正段
			PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
			inAcctMdfcBsSgmt.setBsSgmtSeqNo(acctBsInfSgmt.getAcctBsInfSgmtSeqNo());
			inAcctMdfcBsSgmt.setInfRecType("212");
			inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
			inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
			//20220608业务新需求rptdate统一采用原记录到，不再采用最新时间
			inAcctMdfcBsSgmt.setRptDate(acctbssgmt.getRptDate());
			inAcctMdfcBsSgmt.setMdfcSgmtCode("C");
			inAcctMdfcBsSgmt.setReportflag("0");
			//20220608业务新需求rptdate统一采用原记录到，不再采用最新时间
			inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(acctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
			InacctmdfcbssgmtMapper.deleteByPrimaryKey(acctBsInfSgmt.getAcctBsInfSgmtSeqNo(), "C");
			int cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
			if (cnt != 1)
				throw new Exception();
		} else {
			// 删除修改表中的记录
			AcctbsinfsgmtMapper.deleteByPrimaryKeyM(acctBsInfSgmt.getAcctBsInfSgmtSeqNo());

			// 插入新记录（实际就是做信息更新）
			int cnt = AcctbsinfsgmtMapper.insertM(acctBsInfSgmt.getAcctBsInfSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}

		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(acctBsInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportacctbsinfsgmt, newMap, "AcctBsSgmt", acctBsInfSgmt.getAcctBsInfSgmtSeqNo());
		return 1;
	}

	// 修改基础段
	public int updateAcctBsSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 页面传递过来的需要修改的基础段信息
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
		log.info("修改类型为:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// 判断修改类型(除了信息报告日期以外的信息):0.只修改账户标识码 1.只修改除账户标识码的其他信息 2.修改了账户标识码和其他信息
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
				// 删除修改表中的记录
				// AcctbssgmtMapper.deleteByPrimaryKeyM(acctBsSgmt.getAcctBsSgmtSeqNo());
				// 临时对象
				PbcrsReportAcctbssgmt acctBsSgmtTemp = acctBsSgmt;

				// 插入新记录（实际就是做信息更新）
				// cnt =
				// AcctbssgmtMapper.insertM(acctBsSgmt.getAcctBsSgmtSeqNo());
				// if (cnt != 1)
				// throw new Exception();
				// 待更正段
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
				// 1.生成账户标识码变更记录
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
				// 2.生成按段更正信息记录
				// 删除修改表中的记录
				// AcctbssgmtMapper.deleteByPrimaryKeyM(acctBsSgmt.getAcctBsSgmtSeqNo());
				// 临时对象
				// PbcrsReportAcctbssgmt acctBsSgmtTemp = acctBsSgmt;
				// 将报送状态改为未报送
				// acctBsSgmtTemp.setReportflag("0");
				// 插入新记录（实际就是做信息更新）
				// cnt =
				// AcctbssgmtMapper.insertM(acctBsSgmt.getAcctBsSgmtSeqNo());
				if (cnt != 1)
					throw new Exception();
				// 待更正段
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
			// 删除修改表中的记录
			AcctbssgmtMapper.deleteByPrimaryKeyM(acctBsSgmt.getAcctBsSgmtSeqNo());
			// 插入新记录（实际就是做信息更新）
			cnt = AcctbssgmtMapper.insertM(acctBsSgmt.getAcctBsSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();

		}

		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(acctBsSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportacctbssgmt, newMap, "AcctBsSgmt", acctBsSgmt.getAcctBsSgmtSeqNo());
		return 1;
	}

	// 修改相关还款责任人段
	public int updateRltRepymtInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 相关还款人详细信息
		// PbcrsReportRltrepymtinf rltRepymtInf = (PbcrsReportRltrepymtinf)
		// params.get("RltRepymtInf");
		PbcrsReportRltrepymtinf rltRepymtInf = (PbcrsReportRltrepymtinf) Util.map2Object2(params, PbcrsReportRltrepymtinf.class);
		PbcrsReportRltrepymtinf pbcrsreportrltrepymtinf = RltrepymtinfMapper.selectByPrimaryKey(rltRepymtInf.getRltRepymtInfSgmtSeqNo(), rltRepymtInf.getRltRepymtInfSeqNo());
		if (pbcrsreportrltrepymtinf != null) {
			rltRepymtInf.setReportflag(pbcrsreportrltrepymtinf.getReportflag());
			// 更新原表信息
			RltrepymtinfMapper.updateByPrimaryKey(rltRepymtInf);
		} else {
			throw new Exception();
		}
		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(rltRepymtInf.getRltRepymtInfSgmtSeqNo());
			// 待更正段
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
			// 将相关还款人的所有详细列表都插入到修改表中
			List<PbcrsReportRltrepymtinf> list = RltrepymtinfMapper.getByFirstKey(rltRepymtInf.getRltRepymtInfSgmtSeqNo());
			for (PbcrsReportRltrepymtinf temp : list) {
				// 插入相关还款人详细表
				RltrepymtinfMapper.deleteByPrimaryKeyM(temp.getRltRepymtInfSgmtSeqNo(), temp.getRltRepymtInfSeqNo());
				// 将报送状态改为未报送
				// temp.setReportflag("0");
				// 插入新记录（实际就是做信息更新）
				cnt = RltrepymtinfMapper.insertM(temp.getRltRepymtInfSgmtSeqNo(), temp.getRltRepymtInfSeqNo());
				if (cnt != 1)
					throw new Exception();
			}
			// 插入相关还款人修改表
			PbcrsReportRltrepymtinfsgmt rltrepymtinfsgmt = RltrepymtinfsgmtMapper.selectByPrimaryKey(rltRepymtInf.getRltRepymtInfSgmtSeqNo());
			RltrepymtinfsgmtMapper.deleteByPrimaryKeyM(rltrepymtinfsgmt.getRltRepymtInfSgmtSeqNo());

			cnt = RltrepymtinfsgmtMapper.insertM(rltrepymtinfsgmt.getRltRepymtInfSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();

		}

		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(rltRepymtInf);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportrltrepymtinf, newMap, "RltRepymtInfSgmt", rltRepymtInf.getRltRepymtInfSgmtSeqNo());
		return 1;
	}

	// 修改授信额度段
	public int updateAcctCredSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 基本信息段值
		// PbcrsReportAcctcredsgmt acctcredsgmt = (PbcrsReportAcctcredsgmt)
		// params.get("Acctcredsgmt");
		PbcrsReportAcctcredsgmt acctcredsgmt = (PbcrsReportAcctcredsgmt) Util.map2Object2(params, PbcrsReportAcctcredsgmt.class);
		PbcrsReportAcctcredsgmt pbcrsReportAcctcredsgmt = AcctCredSgmtMapper.selectByPrimaryKey(acctcredsgmt.getAcctCredSgmtSeqNo());
		if (pbcrsReportAcctcredsgmt != null) {
			acctcredsgmt.setReportflag(pbcrsReportAcctcredsgmt.getReportflag());
			// 更新原表信息
			AcctCredSgmtMapper.updateByPrimaryKey(acctcredsgmt);
		} else {
			throw new Exception();
		}

		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(acctcredsgmt.getAcctCredSgmtSeqNo());
			// 待更正段
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
			// 删除修改表中的记录
			AcctCredSgmtMapper.deleteByPrimaryKeyM(acctcredsgmt.getAcctCredSgmtSeqNo());

			// 插入新记录（实际就是做信息更新）
			cnt = AcctCredSgmtMapper.insertM(acctcredsgmt.getAcctCredSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();

		}

		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(acctcredsgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsReportAcctcredsgmt, newMap, "AcctCredSgmt", acctcredsgmt.getAcctCredSgmtSeqNo());
		return 1;
	}

	// 修改初始债权说明段
	public int updateOriCreInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 页面参数
		// PbcrsReportOricreinfsgmt oriCreInfSgmt = (PbcrsReportOricreinfsgmt)
		// params.get("OriCreInfSgmt");
		PbcrsReportOricreinfsgmt oriCreInfSgmt = (PbcrsReportOricreinfsgmt) Util.map2Object2(params, PbcrsReportOricreinfsgmt.class);
		PbcrsReportOricreinfsgmt pbcrsreportoricreinfsgmt = OricreinfsgmtMapper.selectByPrimaryKey(oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());
		if (pbcrsreportoricreinfsgmt != null) {
			oriCreInfSgmt.setReportflag(pbcrsreportoricreinfsgmt.getReportflag());
			// 更新原表信息
			OricreinfsgmtMapper.updateByPrimaryKey(oriCreInfSgmt);
		} else {
			throw new Exception();
		}
		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());
			// 待更正段
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

			// 删除修改表中的记录
			OricreinfsgmtMapper.deleteByPrimaryKeyM(oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());

			// 插入新记录（实际就是做信息更新）
			cnt = OricreinfsgmtMapper.insertM(oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();

		}

		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(oriCreInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportoricreinfsgmt, newMap, "OriCreInfSgmt", oriCreInfSgmt.getOrigCreditorInfSgmtSeqNo());
		return 1;
	}

	// 修改月度表现信息段
	public int updateAccMthBlgInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 页面参数
		// PbcrsReportAccmthblginfsgmt accMthBlgInfSgmt =
		// (PbcrsReportAccmthblginfsgmt) params.get("AccMthBlgInfSgmt");
		PbcrsReportAccmthblginfsgmt accMthBlgInfSgmt = (PbcrsReportAccmthblginfsgmt) Util.map2Object2(params, PbcrsReportAccmthblginfsgmt.class);
		PbcrsReportAccmthblginfsgmt pbcrsreportaccmthblginfsgmt = AccmthblginfsgmtMapper.selectByPrimaryKey(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());
		if (pbcrsreportaccmthblginfsgmt != null) {
			accMthBlgInfSgmt.setReportflag(pbcrsreportaccmthblginfsgmt.getReportflag());
			// 更新原表信息
			AccmthblginfsgmtMapper.updateByPrimaryKey(accMthBlgInfSgmt);
		} else {
			throw new Exception();
		}

		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());
			// 待更正段
			PbcrsReportInacctmdfcbssgmt inAcctMdfcBsSgmt = new PbcrsReportInacctmdfcbssgmt();
			inAcctMdfcBsSgmt.setBsSgmtSeqNo(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());
			inAcctMdfcBsSgmt.setInfRecType("212");
			inAcctMdfcBsSgmt.setModRecCode(acctbssgmt.getAcctCode());
			inAcctMdfcBsSgmt.setAcctType(acctbssgmt.getAcctType());
			inAcctMdfcBsSgmt.setRptDate(acctbssgmt.getRptDate());//--rptdate与月度表现段必须在同一个月或者相差一个月
			inAcctMdfcBsSgmt.setMdfcSgmtCode("H");
			inAcctMdfcBsSgmt.setReportflag("0");
			inAcctMdfcBsSgmt.setEtlDate(DateUtils.parseDate(acctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inAcctMdfcBsSgmt.setSourceSys(acctbssgmt.getSourceSys());
			InacctmdfcbssgmtMapper.deleteByPrimaryKey(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo(), "H");
			cnt = InacctmdfcbssgmtMapper.insert(inAcctMdfcBsSgmt);
			if (cnt != 1)
				throw new Exception();
		} else {

			// 删除修改表中的记录
			AccmthblginfsgmtMapper.deleteByPrimaryKeyM(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());

			// 插入新记录（实际就是做信息更新）
			cnt = AccmthblginfsgmtMapper.insertM(accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}

		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(accMthBlgInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportaccmthblginfsgmt, newMap, "AccMthBlgInfSgmt", accMthBlgInfSgmt.getAcctMthlyBlgInfSgmtSeqNo());
		return 1;
	}

	// 修改大额专项分期信息段
	public int updateSpecPrdSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 页面参数
		// PbcrsReportSpecprdsgmt specPrdSgmt = (PbcrsReportSpecprdsgmt)
		// params.get("SpecPrdSgmt");
		PbcrsReportSpecprdsgmt specPrdSgmt = (PbcrsReportSpecprdsgmt) Util.map2Object2(params, PbcrsReportSpecprdsgmt.class);
		PbcrsReportSpecprdsgmt pbcrsreportspecprdsgmt = SpecprdsgmtMapper.selectByPrimaryKey(specPrdSgmt.getSpecPrdSgmtSeqNo());
		if (pbcrsreportspecprdsgmt != null) {
			specPrdSgmt.setReportflag(pbcrsreportspecprdsgmt.getReportflag());
			// 更新原表信息
			SpecprdsgmtMapper.updateByPrimaryKey(specPrdSgmt);
		} else {
			throw new Exception();
		}

		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(specPrdSgmt.getSpecPrdSgmtSeqNo());
			// 待更正段
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
			// 删除修改表中的记录
			SpecprdsgmtMapper.deleteByPrimaryKeyM(specPrdSgmt.getSpecPrdSgmtSeqNo());
			// 临时对象

			// 插入新记录（实际就是做信息更新）
			cnt = SpecprdsgmtMapper.insertM(specPrdSgmt.getSpecPrdSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}

		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(specPrdSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportspecprdsgmt, newMap, "SpecPrdSgmt", specPrdSgmt.getSpecPrdSgmtSeqNo());
		return 1;
	}

	// 修改非月度表现
	public int updateAcctDbtInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 页面参数
		// PbcrsReportAcctdbtinfsgmt acctDbtInfSgmt =
		// (PbcrsReportAcctdbtinfsgmt) params.get("AcctDbtInfSgmt");
		PbcrsReportAcctdbtinfsgmt acctDbtInfSgmt = (PbcrsReportAcctdbtinfsgmt) Util.map2Object2(params, PbcrsReportAcctdbtinfsgmt.class);
		PbcrsReportAcctdbtinfsgmt pbcrsreportacctdbtinfsgmt = AcctdbtinfsgmtMapper.selectByPrimaryKey(acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());
		if (pbcrsreportacctdbtinfsgmt != null) {
			acctDbtInfSgmt.setReportflag(pbcrsreportacctdbtinfsgmt.getReportflag());
			// 更新原表信息
			AcctdbtinfsgmtMapper.updateByPrimaryKey(acctDbtInfSgmt);
		} else {
			throw new Exception();
		}

		int cnt = 0;

		String updateType = params.get("updateType").toString();
		log.info("修改类型为:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());
			// 待更正段
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
			// 删除修改表中的记录
			AcctdbtinfsgmtMapper.deleteByPrimaryKeyM(acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());

			// 插入新记录（实际就是做信息更新）
			cnt = AcctdbtinfsgmtMapper.insertM(acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}

		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(acctDbtInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportacctdbtinfsgmt, newMap, "AcctDbtInfSgmt", acctDbtInfSgmt.getAcctDbtInfSgmtSeqNo());
		return 1;
	}

	// 修改特殊交易说明信息段
	public int updateAccSpeTrsDspSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 特殊交易详细信息
		// PbcrsReportCagoftrdinf cagOfTrdInf = (PbcrsReportCagoftrdinf)
		// params.get("CagOfTrdInf");
		PbcrsReportCagoftrdinf cagOfTrdInf = (PbcrsReportCagoftrdinf) Util.map2Object2(params, PbcrsReportCagoftrdinf.class);
		PbcrsReportCagoftrdinf pbcrsreportcagoftrdinf = CagoftrdinfMapper.selectByPrimaryKey(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo(), cagOfTrdInf.getCagOfTrdInfSeqNo());
		if (pbcrsreportcagoftrdinf != null) {
			// 更新原表信息
			cagOfTrdInf.setReportflag(pbcrsreportcagoftrdinf.getReportflag());
			CagoftrdinfMapper.updateByPrimaryKey(cagOfTrdInf);
		} else {
			throw new Exception();
		}

		int cnt = 0;
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:" + updateType);
		if (StringUtils.isNotEmpty(updateType) && updateType.equals(Constants.TYPE_UPDSUB)) {
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportAcctbssgmt acctbssgmt = AcctbssgmtMapper.selectByPrimaryKey(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());
			// 待更正段
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
			// 将特殊交易详细信息列表都插入到修改表中
			List<PbcrsReportCagoftrdinf> list = CagoftrdinfMapper.getByFirstKey(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());
			for (PbcrsReportCagoftrdinf temp : list) {
				// 插入特殊交易详细信息详细表
				CagoftrdinfMapper.deleteByPrimaryKeyM(temp.getAcctSpecTrstDspnSgmtSeqNo(), temp.getCagOfTrdInfSeqNo());

				// 插入新记录（实际就是做信息更新）
				cnt = CagoftrdinfMapper.insertM(temp.getAcctSpecTrstDspnSgmtSeqNo(), temp.getCagOfTrdInfSeqNo());
				if (cnt != 1)
					throw new Exception();
			}
			// 插入特殊交易详细信息修改表
			PbcrsReportAccspetrsdspsgmt accspetrsdspsgmt = AccspetrsdspsgmtMapper.selectByPrimaryKey(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());
			AccspetrsdspsgmtMapper.deleteByPrimaryKeyM(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());

			cnt = AccspetrsdspsgmtMapper.insertM(cagOfTrdInf.getAcctSpecTrstDspnSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}

		// 插入痕迹表
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
		//设置删除标志为1,表示该段已经被删除
		pbcrsReportAcctbsinfsgmt.setParagraphDel("1");
		int updateCount = AcctbsinfsgmtMapper.updateByPrimaryKey(pbcrsReportAcctbsinfsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//基础段：取业务标识码和报送日期
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
		//设置删除标志为1,表示该段已经被删除
		 pbcrsReportAccmthblginfsgmt.setParagraphDel("1");
		int updateCount = AccmthblginfsgmtMapper.updateByPrimaryKey(pbcrsReportAccmthblginfsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//基础段：取业务标识码和报送日期
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
		//设置删除标志为1,表示该段已经被删除
		pbcrsReportSpecprdsgmt.setParagraphDel("1");
		int updateCount = SpecprdsgmtMapper.updateByPrimaryKey(pbcrsReportSpecprdsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//基础段：取业务标识码和报送日期
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
		//设置删除标志为1,表示该段已经被删除
		 pbcrsReportAcctdbtinfsgmt.setParagraphDel("1");
		int updateCount = AcctdbtinfsgmtMapper.updateByPrimaryKey(pbcrsReportAcctdbtinfsgmt);
		if(updateCount != 1){
			throw new Exception();
		}
		//基础段：取业务标识码和报送日期
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
		//设置删除标志为1,表示该段已经被删除
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
		
		
		//基础段：取业务标识码和报送日期
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
	 * 检查基础段修改字段
	 * 
	 * @return
	 */
	public static int checkParameter(PbcrsReportAcctbssgmt bssgmt1, PbcrsReportAcctbssgmt bssgmt2) {

		if (bssgmt1.getAcctCode().equals(bssgmt2.getAcctCode())) {

			return 0;
		} else {
			// 重写过的toString()方法，并且去除掉主键和账户标示，生成字符串
			if (bssgmt1.toString().equals(bssgmt2.toString())) {

				return 1;
			} else {

				return 2;
			}
		}

	}

	/**
	 * 判断修改的数据类型
	 * 
	 * @return 0：只修改账户标识码 1:只修改其他数据 2:修改了账户标识码以及其他数据
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * 
	 */
	public int changeDateType(PbcrsReportAcctbssgmt oldData, PbcrsReportAcctbssgmt newData) throws Exception {
		String oldAcctCode = String.valueOf(oldData.getAcctCode());
		String newAcctCode = String.valueOf(newData.getAcctCode());
		// 判断账户标识码是否相同
		if (!oldAcctCode.equals(newAcctCode)) {

			Class clazz = oldData.getClass();
			Class clazz1 = newData.getClass();
			Field[] fields = clazz.getDeclaredFields();
			// 判断除了账户标识码和信息更新日期以外的数据是否相同
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
							// 有一个不相同则返回2
							return 2;
						}
					}
				}
			}
			// 全部相同则返回0
			return 0;
		} else {
			// 账户标识码相同则是修改的其他信息
			return 1;
		}
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
	public int updateByMap(Map map) {
		// 基础段
		AcctbssgmtMapper.updateByMap(map);
		// 基本信息段
		AcctbsinfsgmtMapper.updateByMap(map);
		// 月度表现信息段
		AccmthblginfsgmtMapper.updateByMap(map);
		// 非月度表现
		AcctdbtinfsgmtMapper.updateByMap(map);
		// 特殊交易说明信息段
		AccspetrsdspsgmtMapper.updateByMap(map);
		// 特殊交易信息详细
		CagoftrdinfMapper.updateByMap(map);
		// 抵质押物信息段
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
