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
	// ?????????service
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;

	// ?????????
	@Autowired
	private PbcrsReportCtrctbssgmtMapper CtrctbssgmtMapper;
	// ???????????????
	@Autowired
	private PbcrsReportCreditlimsgmtMapper CreditlimsgmtMapper;
	// ?????????????????????
	@Autowired
	private PbcrsReportCtrctcertrelsgmtMapper CtrctcertrelsgmtMapper;
	// ???????????????????????????
	@Autowired
	private PbcrsReportCtrctcertrelMapper CtrctcertrelMapper;
	// ????????????---?????????
	@Autowired
	private PbcrsReportInctrctmdfcbssgmtMapper InctrctmdfcbssgmtMapper;
	// ????????????---????????????
	@Autowired
	private PbcrsReportInctrmdfmdfsgmtMapper InctrmdfmdfsgmtMapper;

	// ????????????????????????
	@Autowired
	private PbcrsReportInctrctidcagsinfMapper InctrctidcagsinfMapper;
	
	@Autowired
	private PbcrsReportInctrctdelMapper InctrctdelMapper;
	
	@Autowired
	private PbcrsReportInctrctentdelMapper InctrctentdelMapper;

	// ?????????????????????
	public Map<String, Object> getInCtrctInfList(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		param = getStartAndEnd(param);
		// ????????????
		int total = CtrctbssgmtMapper.getCount(param);
		// ??????total?????????????????????total????????????????????????index
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// ????????????
			list = CtrctbssgmtMapper.getAllandPage(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;
	}

	// ?????????????????????
	public PbcrsReportCtrctbssgmt getCtrctBsSgmt(String CtrctBsSgmtSeqNo) {
		PbcrsReportCtrctbssgmt result = CtrctbssgmtMapper.selectByPrimaryKey(CtrctBsSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportCtrctbssgmt();
		return result;

	}

	// ?????????????????????
	public PbcrsReportCreditlimsgmt getCreditLimSgmt(String CreditLimSgmtSeqNo) {
		PbcrsReportCreditlimsgmt result = CreditlimsgmtMapper.selectByPrimaryKey(CreditLimSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportCreditlimsgmt();
		return result;

	}

	// ???????????????????????????
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

	// ?????????????????????????????????????????????
	public PbcrsReportCtrctcertrel getCtrctCertRel(Map<String, Object> param) {
		String CtrctCertRelSgmtSeqNo = String.valueOf(param.get("CtrctCertRelSgmtSeqNo"));
		String CtrctCertRelSeqNo = String.valueOf(param.get("CtrctCertRelSeqNo"));
		PbcrsReportCtrctcertrel result = CtrctcertrelMapper.selectByPrimaryKey(CtrctCertRelSgmtSeqNo, CtrctCertRelSeqNo);
		if (result == null)
			result = new PbcrsReportCtrctcertrel();
		return result;
	}

	// ?????????????????????
	public int updateCreditLimSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ??????????????????
		//PbcrsReportCreditlimsgmt creditLimSgmt = (PbcrsReportCreditlimsgmt) params.get("CreditLimSgmt");
		PbcrsReportCreditlimsgmt creditLimSgmt = (PbcrsReportCreditlimsgmt)Util.map2Object2(params,PbcrsReportCreditlimsgmt.class);
		PbcrsReportCreditlimsgmt pbcrsreportcreditlimsgmt = CreditlimsgmtMapper.selectByPrimaryKey(creditLimSgmt.getCreditLimSgmtSeqNo());
		if (pbcrsreportcreditlimsgmt != null) {
			//????????????????????????????????????reportflag?????????????????????
			creditLimSgmt.setReportflag(pbcrsreportcreditlimsgmt.getReportflag());
			// ??????????????????
			CreditlimsgmtMapper.updateByPrimaryKey(creditLimSgmt);
		} else {
			throw new Exception();
		}

	int cnt=0;
		String updateType = params.get("updateType").toString();
		log.info("???????????????:"+updateType);
		if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
			// ?????????????????????????????????????????????????????????????????????
			PbcrsReportCtrctbssgmt ctrctbssgmt = CtrctbssgmtMapper.selectByPrimaryKey(creditLimSgmt.getCreditLimSgmtSeqNo());
			// ????????????
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
			// ???????????????????????????
			CreditlimsgmtMapper.deleteByPrimaryKeyM(pbcrsreportcreditlimsgmt.getCreditLimSgmtSeqNo());
			
			// ????????????????????????????????????????????????
			 cnt = CreditlimsgmtMapper.insertM(pbcrsreportcreditlimsgmt.getCreditLimSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();

		}
	
		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(creditLimSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportcreditlimsgmt, newMap, "GuarAcctBsInfSgmt", creditLimSgmt.getCreditLimSgmtSeqNo());
		return 1;
	}

	// ???????????????????????????
	public int updateCtrctCertRelSgmt(Map<String, Object> params) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ????????????
		//PbcrsReportCtrctcertrel ctrctCertRel = (PbcrsReportCtrctcertrel) params.get("CtrctCertRel");
		PbcrsReportCtrctcertrel ctrctCertRel = (PbcrsReportCtrctcertrel) Util.map2Object2(params,PbcrsReportCtrctcertrel.class);
		PbcrsReportCtrctcertrel pbcrsreportctrctcertrel = CtrctcertrelMapper.selectByPrimaryKey(ctrctCertRel.getCtrctCertRelSgmtSeqNo(), ctrctCertRel.getCtrctCertRelSeqNo());
		if (pbcrsreportctrctcertrel != null) {
			ctrctCertRel.setReportflag(pbcrsreportctrctcertrel.getReportflag());
			// ??????????????????
			CtrctcertrelMapper.updateByPrimaryKey(ctrctCertRel);
		} else {
			throw new Exception();
		}
		
		int cnt=0;
		PbcrsReportCtrctcertrelsgmt ctrctcertrelsgmt = CtrctcertrelsgmtMapper.selectByPrimaryKey(ctrctCertRel.getCtrctCertRelSgmtSeqNo());
		String updateType = params.get("updateType").toString();
		log.info("???????????????:"+updateType);
		if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
			
			// ?????????????????????????????????????????????????????????????????????
			PbcrsReportCtrctbssgmt ctrctbssgmt = CtrctbssgmtMapper.selectByPrimaryKey(ctrctcertrelsgmt.getCtrctCertRelSgmtSeqNo());
			// ????????????
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
			// ???????????????????????????????????????????????????????????????
			List<PbcrsReportCtrctcertrel> list = CtrctcertrelMapper.getByFirstKey(ctrctCertRel.getCtrctCertRelSgmtSeqNo());
			for (PbcrsReportCtrctcertrel temp : list) {
				// ??????????????????????????????
				CtrctcertrelMapper.deleteByPrimaryKeyM(temp.getCtrctCertRelSgmtSeqNo(), temp.getCtrctCertRelSeqNo());
				// ????????????????????????????????????????????????
				 cnt = CtrctcertrelMapper.insertM(temp.getCtrctCertRelSgmtSeqNo(), temp.getCtrctCertRelSeqNo());
				if (cnt != 1)
					throw new Exception();
			}
			// ??????????????????????????????
			//PbcrsReportCtrctcertrelsgmt ctrctcertrelsgmt = CtrctcertrelsgmtMapper.selectByPrimaryKey(ctrctCertRel.getCtrctCertRelSgmtSeqNo());
			CtrctcertrelsgmtMapper.deleteByPrimaryKeyM(ctrctcertrelsgmt.getCtrctCertRelSgmtSeqNo());
			ctrctcertrelsgmt.setReportflag("0");
			 cnt = CtrctcertrelsgmtMapper.insertM(ctrctcertrelsgmt.getCtrctCertRelSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}
		
		// ???????????????
		Map<String, Object> newMap = Util.Obj2Map(ctrctCertRel);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportctrctcertrel, newMap, "GuarAcctBsInfSgmt", ctrctCertRel.getCtrctCertRelSgmtSeqNo());
		return 1;
	}

	// ???????????????
	public int updateCtrctBsSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// ???????????????????????????????????????????????????
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
		log.info("???????????????:"+updateType);
		if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
			// ??????????????????(???????????????????????????????????????):0.???????????????????????? 1.?????????????????????????????????????????? 2.???????????????????????????????????????
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
				// ???????????????????????????
				//CtrctbssgmtMapper.deleteByPrimaryKeyM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				// ????????????????????????????????????????????????
				// cnt = CtrctbssgmtMapper.insertM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				//if (cnt != 1)
					//throw new Exception();
				// ????????????
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
				// 1.?????????????????????????????????
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
				// 2.??????????????????????????????
				// ???????????????????????????
				//CtrctbssgmtMapper.deleteByPrimaryKeyM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
			
				// ????????????????????????????????????????????????
				//cnt = CtrctbssgmtMapper.insertM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
				//if (cnt != 1)
				//	throw new Exception();
				// ????????????
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
			// ???????????????????????????
			CtrctbssgmtMapper.deleteByPrimaryKeyM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
			// ????????????????????????????????????????????????
			cnt = CtrctbssgmtMapper.insertM(ctrctBsSgmt.getCtrctBsSgmtSeqNo());
			if (cnt != 1)
				throw new Exception();
		}
		
		
		// ???????????????
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

	/**
	 * ???????????????????????????
	 * 
	 * @return 0??????????????????????????? 1:????????????????????? 2:??????????????????????????????????????????
	 * 
	 */
	public int changeDateType(PbcrsReportCtrctbssgmt oldData, PbcrsReportCtrctbssgmt newData) throws Exception {
		String oldAcctCode = String.valueOf(oldData.getContractCode());
		String newAcctCode = String.valueOf(newData.getContractCode());
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
					if (!name.equals("ContractCode") && !name.equals("RptDate")&&!name.equals("IsDel")&&!name.equals("SourceSys")) {
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
	@Override
	public int updateByMap(Map map) {
		// ?????????
		 CtrctbssgmtMapper.updateByMap(map);
		// ???????????????
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
			//????????????????????????
			int cnt = InctrctdelMapper.insertDelP(param);
			if (cnt != 1) {
				throw new Exception();
			}
			//?????????????????????isDel
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
