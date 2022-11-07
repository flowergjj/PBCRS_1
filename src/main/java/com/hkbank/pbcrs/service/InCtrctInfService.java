package com.hkbank.pbcrs.service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
import com.hkbank.pbcrs.mapper.PbcrsReportCreditlimsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCtrctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCtrctcertrelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCtrctcertrelsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInctrctdelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInctrctentdelMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInctrctidcagsinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInctrctmdfcbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInctrmdfmdfsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportAcctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportBssgmt;
import com.hkbank.pbcrs.model.PbcrsReportCreditlimsgmt;
import com.hkbank.pbcrs.model.PbcrsReportCtrctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportCtrctcertrel;
import com.hkbank.pbcrs.model.PbcrsReportCtrctcertrelsgmt;
import com.hkbank.pbcrs.model.PbcrsReportInbsinfdlt;
import com.hkbank.pbcrs.model.PbcrsReportInctrctentdel;
import com.hkbank.pbcrs.model.PbcrsReportInctrctidcagsinf;
import com.hkbank.pbcrs.model.PbcrsReportInctrctmdfcbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpsinfsgmt;

@Service
@SuppressWarnings("all")
public class InCtrctInfService implements BaseExternalSpi {
	private static final Logger log = LogManager.getLogger(InCtrctInfService.class);
	// 痕迹表service
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;

	// 基础段
	@Autowired
	private PbcrsReportCtrctbssgmtMapper CtrctbssgmtMapper;
	// 额度信息段
	@Autowired
	private PbcrsReportCreditlimsgmtMapper CreditlimsgmtMapper;
	// 共同受信人信息
	@Autowired
	private PbcrsReportCtrctcertrelsgmtMapper CtrctcertrelsgmtMapper;
	// 共同受信人信息详细
	@Autowired
	private PbcrsReportCtrctcertrelMapper CtrctcertrelMapper;
	// 按段更正---基础段
	@Autowired
	private PbcrsReportInctrctmdfcbssgmtMapper InctrctmdfcbssgmtMapper;
	// 按段更正---待更正段
	@Autowired
	private PbcrsReportInctrmdfmdfsgmtMapper InctrmdfmdfsgmtMapper;

	// 授信协议标示变更
	@Autowired
	private PbcrsReportInctrctidcagsinfMapper InctrctidcagsinfMapper;
	
	@Autowired
	private PbcrsReportInctrctdelMapper InctrctdelMapper;
	
	@Autowired
	private PbcrsReportInctrctentdelMapper InctrctentdelMapper;

	// 查询主列表信息
	public Map<String, Object> getInCtrctInfList(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		param = getStartAndEnd(param);
		// 查询总数
		int total = CtrctbssgmtMapper.getCount(param);
		// 判断total有没有值，或者total小于要查询开始的index
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// 查询列表
			list = CtrctbssgmtMapper.getAllandPage(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;
	}

	// 查询基础段信息
	public PbcrsReportCtrctbssgmt getCtrctBsSgmt(String CtrctBsSgmtSeqNo) {
		PbcrsReportCtrctbssgmt result = CtrctbssgmtMapper.selectByPrimaryKey(CtrctBsSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportCtrctbssgmt();
		return result;

	}

	// 查询额度信息段
	public PbcrsReportCreditlimsgmt getCreditLimSgmt(String CreditLimSgmtSeqNo) {
		PbcrsReportCreditlimsgmt result = CreditlimsgmtMapper.selectByPrimaryKey(CreditLimSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportCreditlimsgmt();
		return result;

	}

	// 查询共同受信人信息
	public Map<String, Object> getCtrctCertRelSgmt(Map<String, Object> param) {
		List<Map<String, Object>> list;
		param = getStartAndEnd(param);
		int total = CtrctcertrelsgmtMapper.getCtrctCertRelListTotal(param);
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			list = CtrctcertrelsgmtMapper.getCtrctCertRelList(param);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", list);
		return result;

	}

	// 查询查询共同受信人信息单个详细
	public PbcrsReportCtrctcertrel getCtrctCertRel(Map<String, Object> param) {
		String CtrctCertRelSgmtSeqNo = String.valueOf(param.get("CtrctCertRelSgmtSeqNo"));
		String CtrctCertRelSeqNo = String.valueOf(param.get("CtrctCertRelSeqNo"));
		PbcrsReportCtrctcertrel result = CtrctcertrelMapper.selectByPrimaryKey(CtrctCertRelSgmtSeqNo, CtrctCertRelSeqNo);
		if (result == null)
			result = new PbcrsReportCtrctcertrel();
		return result;
	}

	// 修改额度信息段
	public int updateCreditLimSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 基本信息段值
		//PbcrsReportCreditlimsgmt creditLimSgmt = (PbcrsReportCreditlimsgmt) params.get("CreditLimSgmt");
		PbcrsReportCreditlimsgmt creditLimSgmt = (PbcrsReportCreditlimsgmt)Util.map2Object2(params,PbcrsReportCreditlimsgmt.class);
		PbcrsReportCreditlimsgmt pbcrsreportcreditlimsgmt = CreditlimsgmtMapper.selectByPrimaryKey(creditLimSgmt.getCreditLimSgmtSeqNo());
		if (pbcrsreportcreditlimsgmt != null) {
			//得到数据库中的原表信息的reportflag赋值给修改信息
			creditLimSgmt.setReportflag(pbcrsreportcreditlimsgmt.getReportflag());
			// 更新原表信息
			CreditlimsgmtMapper.updateByPrimaryKey(creditLimSgmt);
		} else {
			throw new Exception();
		}

	int cnt=0;
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:"+updateType);
		if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportCtrctbssgmt ctrctbssgmt = CtrctbssgmtMapper.selectByPrimaryKey(creditLimSgmt.getCreditLimSgmtSeqNo());
			// 待更正段
			PbcrsReportInctrctmdfcbssgmt inctrctmdfcbssgmt = new PbcrsReportInctrctmdfcbssgmt();
			inctrctmdfcbssgmt.setBsSgmtSeqNo(creditLimSgmt.getCreditLimSgmtSeqNo());
			inctrctmdfcbssgmt.setInfRecType("222");
			inctrctmdfcbssgmt.setModRecCode(ctrctbssgmt.getContractCode());
			inctrctmdfcbssgmt.setRptDate(ctrctbssgmt.getRptDate());
			inctrctmdfcbssgmt.setMdfcSgmtCode("D");
			inctrctmdfcbssgmt.setReportflag("0");
			inctrctmdfcbssgmt.setEtlDate(DateUtils.parseDate(ctrctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inctrctmdfcbssgmt.setSourceSys(ctrctbssgmt.getSourceSys());
			InctrctmdfcbssgmtMapper.deleteByPrimaryKey(creditLimSgmt.getCreditLimSgmtSeqNo(), "D");
			cnt = InctrctmdfcbssgmtMapper.insert(inctrctmdfcbssgmt);
			if (cnt != 1)
				throw new Exception();
			
		}	else{
			// 删除修改表中的记录
			CreditlimsgmtMapper.deleteByPrimaryKeyM(pbcrsreportcreditlimsgmt.getCreditLimSgmtSeqNo());
			
			// 插入新记录（实际就是做信息更新）
			 cnt = CreditlimsgmtMapper.insertM(pbcrsreportcreditlimsgmt.getCreditLimSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();

		}
	
		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(creditLimSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportcreditlimsgmt, newMap, "GuarAcctBsInfSgmt", creditLimSgmt.getCreditLimSgmtSeqNo());
		return 1;
	}

	// 修改共同受信人信息
	public int updateCtrctCertRelSgmt(Map<String, Object> params) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 页面信息
		//PbcrsReportCtrctcertrel ctrctCertRel = (PbcrsReportCtrctcertrel) params.get("CtrctCertRel");
		PbcrsReportCtrctcertrel ctrctCertRel = (PbcrsReportCtrctcertrel) Util.map2Object2(params,PbcrsReportCtrctcertrel.class);
		PbcrsReportCtrctcertrel pbcrsreportctrctcertrel = CtrctcertrelMapper.selectByPrimaryKey(ctrctCertRel.getCtrctCertRelSgmtSeqNo(), ctrctCertRel.getCtrctCertRelSeqNo());
		if (pbcrsreportctrctcertrel != null) {
			ctrctCertRel.setReportflag(pbcrsreportctrctcertrel.getReportflag());
			// 更新原表信息
			CtrctcertrelMapper.updateByPrimaryKey(ctrctCertRel);
		} else {
			throw new Exception();
		}
		
		int cnt=0;
		PbcrsReportCtrctcertrelsgmt ctrctcertrelsgmt = CtrctcertrelsgmtMapper.selectByPrimaryKey(ctrctCertRel.getCtrctCertRelSgmtSeqNo());
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:"+updateType);
		if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
			
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportCtrctbssgmt ctrctbssgmt = CtrctbssgmtMapper.selectByPrimaryKey(ctrctcertrelsgmt.getCtrctCertRelSgmtSeqNo());
			// 待更正段
			PbcrsReportInctrctmdfcbssgmt inctrctmdfcbssgmt = new PbcrsReportInctrctmdfcbssgmt();
			inctrctmdfcbssgmt.setBsSgmtSeqNo(ctrctcertrelsgmt.getCtrctCertRelSgmtSeqNo());
			inctrctmdfcbssgmt.setInfRecType("222");
			inctrctmdfcbssgmt.setModRecCode(ctrctbssgmt.getContractCode());
			inctrctmdfcbssgmt.setRptDate(ctrctbssgmt.getRptDate());
			inctrctmdfcbssgmt.setMdfcSgmtCode("C");
			inctrctmdfcbssgmt.setReportflag("0");
			inctrctmdfcbssgmt.setEtlDate(DateUtils.parseDate(ctrctbssgmt.getRptDate(), "yyyy-mm-dd"));
			inctrctmdfcbssgmt.setSourceSys(ctrctbssgmt.getSourceSys());
			InctrctmdfcbssgmtMapper.deleteByPrimaryKey(ctrctcertrelsgmt.getCtrctCertRelSgmtSeqNo(), "C");
			cnt = InctrctmdfcbssgmtMapper.insert(inctrctmdfcbssgmt);
			if (cnt != 1)
				throw new Exception();
		}else{
			// 将相关还款人的所有详细列表都插入到修改表中
			List<PbcrsReportCtrctcertrel> list = CtrctcertrelMapper.getByFirstKey(ctrctCertRel.getCtrctCertRelSgmtSeqNo());
			for (PbcrsReportCtrctcertrel temp : list) {
				// 插入相关还款人详细表
				CtrctcertrelMapper.deleteByPrimaryKeyM(temp.getCtrctCertRelSgmtSeqNo(), temp.getCtrctCertRelSeqNo());
				// 插入新记录（实际就是做信息更新）
				 cnt = CtrctcertrelMapper.insertM(temp.getCtrctCertRelSgmtSeqNo(), temp.getCtrctCertRelSeqNo());
				if (cnt != 1)
					throw new Exception();
			}
			// 插入相关还款人修改表
			//PbcrsReportCtrctcertrelsgmt ctrctcertrelsgmt = CtrctcertrelsgmtMapper.selectByPrimaryKey(ctrctCertRel.getCtrctCertRelSgmtSeqNo());
			CtrctcertrelsgmtMapper.deleteByPrimaryKeyM(ctrctcertrelsgmt.getCtrctCertRelSgmtSeqNo());
			ctrctcertrelsgmt.setReportflag("0");
			 cnt = CtrctcertrelsgmtMapper.insertM(ctrctcertrelsgmt.getCtrctCertRelSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}
		
		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(ctrctCertRel);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportctrctcertrel, newMap, "GuarAcctBsInfSgmt", ctrctCertRel.getCtrctCertRelSgmtSeqNo());
		return 1;
	}

	// 修改基础段
	public int updateCtrctBsSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 页面传递过来的需要修改的基础段信息
		//PbcrsReportCtrctbssgmt ctrctBsSgmt = (PbcrsReportCtrctbssgmt) params.get("CtrctBsSgmt");
		PbcrsReportCtrctbssgmt ctrctBsSgmt = (PbcrsReportCtrctbssgmt)Util.map2Object2(params,PbcrsReportCtrctbssgmt.class);
		PbcrsReportCtrctbssgmt pbcrsreportctrctbssgmt = CtrctbssgmtMapper.selectByPrimaryKey(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
		if (pbcrsreportctrctbssgmt != null) {
			//ctrctBsSgmt.setRptDate(nowDate);
			ctrctBsSgmt.setReportflag(pbcrsreportctrctbssgmt.getReportflag());
			CtrctbssgmtMapper.updateByPrimaryKey(ctrctBsSgmt);
		} else {
			throw new Exception();
		}
		int cnt=0;
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:"+updateType);
		if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
			// 判断修改类型(除了信息报告日期以外的信息):0.只修改账户标识码 1.只修改除账户标识码的其他信息 2.修改了账户标识码和其他信息
			int flag = changeDateType(pbcrsreportctrctbssgmt, ctrctBsSgmt);
			if (flag == 0) {
				PbcrsReportInctrctidcagsinf inCtrctIDCagsInf = new PbcrsReportInctrctidcagsinf();
				// int key = InctrctidcagsinfMapper.getKey();
				inCtrctIDCagsInf.setInCtrctIDCagsInfSeqNo(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				inCtrctIDCagsInf.setInfRecType("221");
				inCtrctIDCagsInf.setOdBnesCode(pbcrsreportctrctbssgmt.getContractCode());
				inCtrctIDCagsInf.setNwBnesCode(ctrctBsSgmt.getContractCode());
				inCtrctIDCagsInf.setReportflag("0");
				inCtrctIDCagsInf.setEtlDate(new Date());
				inCtrctIDCagsInf.setSourceSys(pbcrsreportctrctbssgmt.getSourceSys());
				InctrctidcagsinfMapper.deleteByPrimaryKey(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				 cnt = InctrctidcagsinfMapper.insert(inCtrctIDCagsInf);
				if (cnt != 1)
					throw new Exception();

			} else if (flag == 1) {
				// 删除修改表中的记录
				//CtrctbssgmtMapper.deleteByPrimaryKeyM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				// 插入新记录（实际就是做信息更新）
				// cnt = CtrctbssgmtMapper.insertM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				//if (cnt != 1)
					//throw new Exception();
				// 待更正段
				PbcrsReportInctrctmdfcbssgmt inctrctmdfcbssgmt = new PbcrsReportInctrctmdfcbssgmt();
				inctrctmdfcbssgmt.setBsSgmtSeqNo(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				inctrctmdfcbssgmt.setInfRecType("222");
				inctrctmdfcbssgmt.setModRecCode(ctrctBsSgmt.getContractCode());
				inctrctmdfcbssgmt.setRptDate(pbcrsreportctrctbssgmt.getRptDate());
				inctrctmdfcbssgmt.setMdfcSgmtCode("B");
				inctrctmdfcbssgmt.setReportflag("0");
				inctrctmdfcbssgmt.setEtlDate(DateUtils.parseDate(pbcrsreportctrctbssgmt.getRptDate(), "yyyy-mm-dd"));
				inctrctmdfcbssgmt.setSourceSys(pbcrsreportctrctbssgmt.getSourceSys());
				InctrctmdfcbssgmtMapper.deleteByPrimaryKey(ctrctBsSgmt.getCtrctBsSgmtSeqNo(), "B");
				cnt = InctrctmdfcbssgmtMapper.insert(inctrctmdfcbssgmt);
				if (cnt != 1)
					throw new Exception();
			} else if (flag == 2) {
				// 1.生成账户标识码变更记录
				PbcrsReportInctrctidcagsinf inCtrctIDCagsInf = new PbcrsReportInctrctidcagsinf();
				// int key = InctrctidcagsinfMapper.getKey();
				inCtrctIDCagsInf.setInCtrctIDCagsInfSeqNo(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				inCtrctIDCagsInf.setInfRecType("221");
				inCtrctIDCagsInf.setOdBnesCode(pbcrsreportctrctbssgmt.getContractCode());
				inCtrctIDCagsInf.setNwBnesCode(ctrctBsSgmt.getContractCode());
				inCtrctIDCagsInf.setReportflag("0");
				inCtrctIDCagsInf.setEtlDate(new Date());
				inCtrctIDCagsInf.setSourceSys(pbcrsreportctrctbssgmt.getSourceSys());
				InctrctidcagsinfMapper.deleteByPrimaryKey(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				 cnt = InctrctidcagsinfMapper.insert(inCtrctIDCagsInf);
				if (cnt != 1)
					throw new Exception();
				// 2.生成按段更正信息记录
				// 删除修改表中的记录
				//CtrctbssgmtMapper.deleteByPrimaryKeyM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
			
				// 插入新记录（实际就是做信息更新）
				//cnt = CtrctbssgmtMapper.insertM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				//if (cnt != 1)
				//	throw new Exception();
				// 待更正段
				PbcrsReportInctrctmdfcbssgmt inctrctmdfcbssgmt = new PbcrsReportInctrctmdfcbssgmt();
				inctrctmdfcbssgmt.setBsSgmtSeqNo(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				inctrctmdfcbssgmt.setInfRecType("222");
				inctrctmdfcbssgmt.setModRecCode(ctrctBsSgmt.getContractCode());
				inctrctmdfcbssgmt.setRptDate(pbcrsreportctrctbssgmt.getRptDate());
				inctrctmdfcbssgmt.setMdfcSgmtCode("B");
				inctrctmdfcbssgmt.setReportflag("0");
				inctrctmdfcbssgmt.setEtlDate(DateUtils.parseDate(pbcrsreportctrctbssgmt.getRptDate(), "yyyy-mm-dd"));
				inctrctmdfcbssgmt.setSourceSys(pbcrsreportctrctbssgmt.getSourceSys());
				InctrctmdfcbssgmtMapper.deleteByPrimaryKey(ctrctBsSgmt.getCtrctBsSgmtSeqNo(), "B");
				cnt = InctrctmdfcbssgmtMapper.insert(inctrctmdfcbssgmt);
				if (cnt != 1)
					throw new Exception();
			}
		}else{
			// 删除修改表中的记录
			CtrctbssgmtMapper.deleteByPrimaryKeyM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
			// 插入新记录（实际就是做信息更新）
			cnt = CtrctbssgmtMapper.insertM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}
		
		
		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(ctrctBsSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportctrctbssgmt, newMap, "GuarAcctBsSgmt", ctrctBsSgmt.getCtrctBsSgmtSeqNo());
		return 1;
	}
	
	public int delete(Map<String, Object> params) throws Exception {
		String infoKey = params.get("infoKey").toString();
		PbcrsReportInctrctentdel inCtrctEntDel = InctrctentdelMapper.selectByPrimaryKey(infoKey);
		if (inCtrctEntDel != null ) {
			return 0;
		}
		PbcrsReportCtrctbssgmt ctrctbssgmt = CtrctbssgmtMapper.selectByPrimaryKey(infoKey);
		if (ctrctbssgmt != null) {
			String isDel = ctrctbssgmt.getIsDel();
			if (StringUtils.isNotEmpty(isDel) && isDel.equals("1")) {
				return 0;
			}
		}
		PbcrsReportInctrctentdel pbcrsReportInctrctentdel = new PbcrsReportInctrctentdel();
		pbcrsReportInctrctentdel.setInCtrctEntDelSeqNo(infoKey);
		pbcrsReportInctrctentdel.setInfRecType("224");
		pbcrsReportInctrctentdel.setDelRecCode(ctrctbssgmt.getContractCode());
		pbcrsReportInctrctentdel.setReportFlag("0");
		pbcrsReportInctrctentdel.setEtlDate(new Date());
		pbcrsReportInctrctentdel.setSourceSys(ctrctbssgmt.getSourceSys());
		int cnt = InctrctentdelMapper.insert(pbcrsReportInctrctentdel);
		if (cnt != 1) {
			throw new Exception();
		}
		ctrctbssgmt.setIsDel("1");
		cnt = CtrctbssgmtMapper.updateByPrimaryKey(ctrctbssgmt);
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
	 * 判断修改的数据类型
	 * 
	 * @return 0：只修改账户标识码 1:只修改其他数据 2:修改了账户标识码以及其他数据
	 * 
	 */
	public int changeDateType(PbcrsReportCtrctbssgmt oldData, PbcrsReportCtrctbssgmt newData) throws Exception {
		String oldAcctCode = String.valueOf(oldData.getContractCode());
		String newAcctCode = String.valueOf(newData.getContractCode());
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
					if (!name.equals("ContractCode") && !name.equals("RptDate")&&!name.equals("IsDel")&&!name.equals("SourceSys")) {
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
	@Override
	public int updateByMap(Map map) {
		// 基础段
		 CtrctbssgmtMapper.updateByMap(map);
		// 额度信息段
		 CreditlimsgmtMapper.updateByMap(map);
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

	
	public int deleteform(Map<String, Object> params) throws Exception {
		String infoKey = params.get("key").toString();
		String startDate = params.get("startDate")==null?null:params.get("startDate").toString();
/*		PbcrsReportInctrctentdel inCtrctEntDel = InctrctentdelMapper.selectByPrimaryKey(infoKey);
		if (inCtrctEntDel != null ) {
			return 0;
		}*/
		PbcrsReportCtrctbssgmt ctrctbssgmt = CtrctbssgmtMapper.selectByPrimaryKey(infoKey);
		if (ctrctbssgmt != null) {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("InCtrctDelSeqNo",infoKey);
			param.put("InfRecType","223");
			param.put("DelRecCode",ctrctbssgmt.getContractCode());
			param.put("DelSgmtCode","D");
			param.put("DelStartDate",startDate);
			param.put("DelEndDate",ctrctbssgmt.getRptDate());
			param.put("reportFlag","0");
			param.put("rptDate",ctrctbssgmt.getRptDate());
			param.put("sourceSys",ctrctbssgmt.getSourceSys());
			param.put("isDel","1");
			//新增按段删除记录
			int cnt = InctrctdelMapper.insertDelP(param);
			if (cnt != 1) {
				throw new Exception();
			}
			//修改额度信息段isDel
			cnt = CreditlimsgmtMapper.update(param);
			if (cnt != 1) {
				throw new Exception();
			}
		}

		return 1;
	}
	
	public int getCount(Map<String, Object> params) throws Exception{
		String infoKey = params.get("key").toString();
		return CreditlimsgmtMapper.getCount(infoKey);
	}
	
}
