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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.util.Constants;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.PbcrsReportCccinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGsrltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGsrltrepymtinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuamotcltctrsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuaracctbsinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuaracctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuarltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsecaccidcaginfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsecaccmdfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsecaccmdfmdsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsecacctinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInsecacctmdfcMapper;
import com.hkbank.pbcrs.model.PbcrsReportBssgmt;
import com.hkbank.pbcrs.model.PbcrsReportFcsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportGsrltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportGsrltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportGuarltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportInbasinfo;
import com.hkbank.pbcrs.model.PbcrsReportInsecaccidcaginf;
import com.hkbank.pbcrs.model.PbcrsReportInsecaccmdfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportInsecaccmdfmdsgmt;
import com.hkbank.pbcrs.model.PbcrsReportInsecacctinf;
import com.hkbank.pbcrs.model.PbcrsReportInsecacctmdfc;

@Service
@SuppressWarnings("all")
public class InSecAcctInfService {
	private static final Logger log = LogManager.getLogger(InSecAcctInfService.class);
	// 痕迹表service
	@Autowired
	private PbcrsReportTraceService pbcrsReportTraceService;
	// 主表
	@Autowired
	private PbcrsReportInsecacctinfMapper InsecacctinfMapper;
	// 基础段
	@Autowired
	private PbcrsReportGuaracctbssgmtMapper GuaracctbssgmtMapper;
	// 基本信息段
	@Autowired
	private PbcrsReportGuaracctbsinfsgmtMapper GuaracctbsinfsgmtMapper;
	// 在保责任信息段
	@Autowired
	private PbcrsReportGuarltrepinfsgmtMapper GuarltrepinfsgmtMapper;
	// 相关还款责任人段
	@Autowired
	private PbcrsReportGsrltrepinfsgmtMapper GsrltrepinfsgmtMapper;
	// 相关还款责任人详细信息
	@Autowired
	private PbcrsReportGsrltrepymtinfMapper GsrltrepymtinfMapper;
	// 抵质押物信息段
	@Autowired
	private PbcrsReportGuamotcltctrsgmtMapper GuamotcltctrsgmtMapper;
	// 抵质押合同信息
	@Autowired
	private PbcrsReportCccinfMapper CccinfMapper;
	// 待更正基础段
	@Autowired
	private PbcrsReportInsecaccmdfbssgmtMapper InsecaccmdfbssgmtMapper;
	@Autowired
	private PbcrsReportInsecaccidcaginfMapper InsecaccidcaginfMapper;

	// 查询主列表信息
	public Map<String, Object> getBaseInfoList(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		param = getStartAndEnd(param);
		// 查询总数
		int total = GuaracctbssgmtMapper.getCount(param);
		// 判断total有没有值，或者total小于要查询开始的index
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// 查询列表
			list = GuaracctbssgmtMapper.getAllandPage(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;
	}

	// 查询基础段信息
	public PbcrsReportGuaracctbssgmt getGuaracctbssgmt(String GuarAcctBsSgmtSeqNo) {
		PbcrsReportGuaracctbssgmt result = GuaracctbssgmtMapper.selectByPrimaryKey(GuarAcctBsSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportGuaracctbssgmt();
		return result;

	}

	// 在保责任信息段
	public PbcrsReportGuarltrepinfsgmt getGuarltrepinfsgmt(String GuarRltRepymtInfSgmtSeqNo) {
		PbcrsReportGuarltrepinfsgmt result = GuarltrepinfsgmtMapper.selectByPrimaryKey(GuarRltRepymtInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportGuarltrepinfsgmt();
		return result;

	}

	// 查询其他标示详细
	public PbcrsReportGsrltrepymtinf getGsrltrepymtinf(Map<String, Object> param) {
		String rltRepymtInfSgmtSeqNo = param.get("RltRepymtInfSgmtSeqNo") + "";
		String gsRltRepymtInfSeqNo = param.get("GsRltRepymtInfSeqNo") + "";
		PbcrsReportGsrltrepymtinf result = GsrltrepymtinfMapper.selectByPrimaryKey(rltRepymtInfSgmtSeqNo, gsRltRepymtInfSeqNo);
		if (result == null)
			result = new PbcrsReportGsrltrepymtinf();
		return result;

	}

	public Map<String, Object> getGsRltRepInfSgmt(Map<String, Object> param) {
		List<Map<String, Object>> list;
		param = getStartAndEnd(param);
		int total = GsrltrepinfsgmtMapper.getGsRltRepymtInfListTotal(param);
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			list = GsrltrepinfsgmtMapper.getGsRltRepymtInfList(param);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", list);
		return result;

	}

	// 查询抵质押物信息段
	public Map<String, Object> getGuaMotCltCtrSgmt(Map<String, Object> param) {
		List<Map<String, Object>> list;
		param = getStartAndEnd(param);
		int total = GuamotcltctrsgmtMapper.getByPageCount(param);
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			list = GuamotcltctrsgmtMapper.getByPage(param);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", list);
		return result;

	}

	// 查询基本信息段
	public PbcrsReportGuaracctbsinfsgmt getGuaracctbsinfsgmt(String GuarAcctBsInfSgmtSeqNo) {
		PbcrsReportGuaracctbsinfsgmt result = GuaracctbsinfsgmtMapper.selectByPrimaryKey(GuarAcctBsInfSgmtSeqNo);
		if (result == null)
			result = new PbcrsReportGuaracctbsinfsgmt();
		return result;

	}

	// 修改基本信息段
	public int updateGuaracctbsinfsgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 基本信息段值
		PbcrsReportGuaracctbsinfsgmt guarAcctBsInfSgmt = (PbcrsReportGuaracctbsinfsgmt) params.get("GuarAcctBsInfSgmt");
		PbcrsReportGuaracctbsinfsgmt pbcrsreportguaracctbsinfsgmt = GuaracctbsinfsgmtMapper.selectByPrimaryKey(guarAcctBsInfSgmt.getGuarAcctBsInfSgmtSeqNo());
		if (pbcrsreportguaracctbsinfsgmt != null) {
			// 更新原表信息
			guarAcctBsInfSgmt.setReportflag(pbcrsreportguaracctbsinfsgmt.getReportflag());
			GuaracctbsinfsgmtMapper.updateByPrimaryKey(guarAcctBsInfSgmt);
		} else {
			throw new Exception();
		}

		// 删除修改表中的记录
		GuaracctbsinfsgmtMapper.deleteByPrimaryKeyM(pbcrsreportguaracctbsinfsgmt.getGuarAcctBsInfSgmtSeqNo());
		// 临时对象
		PbcrsReportGuaracctbsinfsgmt guarAcctBsInfSgmtTemp = guarAcctBsInfSgmt;
		// 将报送状态改为未报送
		guarAcctBsInfSgmtTemp.setReportflag("0");
		// 插入新记录（实际就是做信息更新）
		int cnt = GuaracctbsinfsgmtMapper.insertM(guarAcctBsInfSgmtTemp);
		if (cnt != 1)
			throw new Exception();
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:"+updateType);
		if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportGuaracctbssgmt guaracctbssgmt = GuaracctbssgmtMapper.selectByPrimaryKey(guarAcctBsInfSgmt.getGuarAcctBsInfSgmtSeqNo());
			// 待更正段
			PbcrsReportInsecaccmdfbssgmt Insecaccmdfbssgmt = new PbcrsReportInsecaccmdfbssgmt();
			Insecaccmdfbssgmt.setBsSgmtSeqNo(guarAcctBsInfSgmt.getGuarAcctBsInfSgmtSeqNo());
			Insecaccmdfbssgmt.setInfRecType("232");
			Insecaccmdfbssgmt.setModRecCode(guaracctbssgmt.getAcctCode());
			Insecaccmdfbssgmt.setAcctType(guaracctbssgmt.getAcctType());
			Insecaccmdfbssgmt.setRptDate(nowDate);
			Insecaccmdfbssgmt.setMdfcSgmtCode("C");
			Insecaccmdfbssgmt.setReportflag("0");
			InsecaccmdfbssgmtMapper.deleteByPrimaryKey(guarAcctBsInfSgmt.getGuarAcctBsInfSgmtSeqNo(), "C");
			cnt = InsecaccmdfbssgmtMapper.insert(Insecaccmdfbssgmt);
			if (cnt != 1)
				throw new Exception();
		}
		
		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(guarAcctBsInfSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportguaracctbsinfsgmt, newMap, "GuarAcctBsInfSgmt",guarAcctBsInfSgmt.getGuarAcctBsInfSgmtSeqNo());
		return 1;

	}

	// 修改在保责任人段
	public int updateGuaRltRepInfSgmt(Map<String, Object> params) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 页面信息
		PbcrsReportGuarltrepinfsgmt Guarltrepinfsgmt = (PbcrsReportGuarltrepinfsgmt) params.get("GuaRltRepInfSgmt");
		// 数据库保存的信息
		PbcrsReportGuarltrepinfsgmt pbcrsreportguarltrepinfsgmt = GuarltrepinfsgmtMapper.selectByPrimaryKey(Guarltrepinfsgmt.getGuarRltRepymtInfSgmtSeqNo());
		if (pbcrsreportguarltrepinfsgmt != null) {
			// 更新原表信息
			Guarltrepinfsgmt.setReportflag(pbcrsreportguarltrepinfsgmt.getReportflag());
			GuarltrepinfsgmtMapper.updateByPrimaryKey(Guarltrepinfsgmt);
		} else {
			throw new Exception();
		}

		// 删除修改表中的记录
		GuarltrepinfsgmtMapper.deleteByPrimaryKeyM(Guarltrepinfsgmt.getGuarRltRepymtInfSgmtSeqNo());
		// 临时对象
		PbcrsReportGuarltrepinfsgmt GuarltrepinfsgmtTemp = Guarltrepinfsgmt;
		// 将报送状态改为未报送
		GuarltrepinfsgmtTemp.setReportflag("0");
		// 插入新记录（实际就是做信息更新）
		int cnt = GuarltrepinfsgmtMapper.insertM(GuarltrepinfsgmtTemp);
		if (cnt != 1)
			throw new Exception();

		String updateType = params.get("updateType").toString();
		log.info("修改类型为:"+updateType);
		if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportGuaracctbssgmt guaracctbssgmt = GuaracctbssgmtMapper.selectByPrimaryKey(Guarltrepinfsgmt.getGuarRltRepymtInfSgmtSeqNo());
			// 待更正段
			PbcrsReportInsecaccmdfbssgmt Insecaccmdfbssgmt = new PbcrsReportInsecaccmdfbssgmt();
			Insecaccmdfbssgmt.setBsSgmtSeqNo(Guarltrepinfsgmt.getGuarRltRepymtInfSgmtSeqNo());
			Insecaccmdfbssgmt.setInfRecType("232");
			Insecaccmdfbssgmt.setModRecCode(guaracctbssgmt.getAcctCode());
			Insecaccmdfbssgmt.setAcctType(guaracctbssgmt.getAcctType());
			Insecaccmdfbssgmt.setRptDate(nowDate);
			Insecaccmdfbssgmt.setMdfcSgmtCode("D");
			Insecaccmdfbssgmt.setReportflag("0");
			InsecaccmdfbssgmtMapper.deleteByPrimaryKey(Insecaccmdfbssgmt.getBsSgmtSeqNo(), "D");
			cnt = InsecaccmdfbssgmtMapper.insert(Insecaccmdfbssgmt);
			if (cnt != 1)
				throw new Exception();
		}
		

		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(Guarltrepinfsgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportguarltrepinfsgmt, newMap, "GuaRltRepInfSgmt",Guarltrepinfsgmt.getGuarRltRepymtInfSgmtSeqNo());
		return 1;
	}

	// 修改基础段
	public int updateGuarAcctBsSgmt(Map<String, Object> params) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 页面传递过来的需要修改的基础段信息
		PbcrsReportGuaracctbssgmt guarAcctBsSgmt = (PbcrsReportGuaracctbssgmt) params.get("GuarAcctBsSgmt");
		PbcrsReportGuaracctbssgmt pbcrsreportguaracctbssgmt = GuaracctbssgmtMapper.selectByPrimaryKey(guarAcctBsSgmt.getGuarAcctBsSgmtSeqNo());
		if (pbcrsreportguaracctbssgmt != null) {
			guarAcctBsSgmt.setReportflag(pbcrsreportguaracctbssgmt.getReportflag());
			guarAcctBsSgmt.setRptDate(nowDate);
			GuaracctbssgmtMapper.updateByPrimaryKey(guarAcctBsSgmt);
		} else {
			throw new Exception();
		}
		// 删除修改表中的记录
		GuaracctbssgmtMapper.deleteByPrimaryKeyM(guarAcctBsSgmt.getGuarAcctBsSgmtSeqNo());
		// 临时对象
		PbcrsReportGuaracctbssgmt guarAcctBsSgmtTemp = guarAcctBsSgmt;
		// 将报送状态改为未报送
		guarAcctBsSgmtTemp.setReportflag("0");
		// 插入新记录（实际就是做信息更新）
		int cnt = GuaracctbssgmtMapper.insertM(guarAcctBsSgmtTemp);
		if (cnt != 1)
			throw new Exception();
		
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:"+updateType);
		if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
			// 判断修改类型(除了信息报告日期以外的信息):0.只修改账户标识码 1.只修改除账户标识码的其他信息 2.修改了账户标识码和其他信息
			int flag = changeDateType(pbcrsreportguaracctbssgmt, guarAcctBsSgmt);
			if (flag == 0) {
				PbcrsReportInsecaccidcaginf inSecAccIDCagInf = new PbcrsReportInsecaccidcaginf();
				//int key = InsecaccidcaginfMapper.getKey();
				inSecAccIDCagInf.setInSecAcctIDCagsInfSeqNo(guarAcctBsSgmt.getGuarAcctBsSgmtSeqNo());
				inSecAccIDCagInf.setInfRecType("231");
				inSecAccIDCagInf.setOdBnesCode(pbcrsreportguaracctbssgmt.getAcctCode());
				inSecAccIDCagInf.setNwBnesCode(guarAcctBsSgmt.getAcctCode());
				InsecaccidcaginfMapper.deleteByPrimaryKey(guarAcctBsSgmt.getGuarAcctBsSgmtSeqNo());
				cnt = InsecaccidcaginfMapper.insert(inSecAccIDCagInf);
				if (cnt != 1)
					throw new Exception();

			} else if (flag == 1) {
				// 待更正段
				PbcrsReportInsecaccmdfbssgmt Insecaccmdfbssgmt = new PbcrsReportInsecaccmdfbssgmt();
				Insecaccmdfbssgmt.setBsSgmtSeqNo(guarAcctBsSgmt.getGuarAcctBsSgmtSeqNo());
				Insecaccmdfbssgmt.setInfRecType("232");
				Insecaccmdfbssgmt.setModRecCode(pbcrsreportguaracctbssgmt.getAcctCode());
				Insecaccmdfbssgmt.setAcctType(pbcrsreportguaracctbssgmt.getAcctType());
				Insecaccmdfbssgmt.setRptDate(nowDate);
				Insecaccmdfbssgmt.setMdfcSgmtCode("B");
				Insecaccmdfbssgmt.setReportflag("0");
				InsecaccmdfbssgmtMapper.deleteByPrimaryKey(Insecaccmdfbssgmt.getBsSgmtSeqNo(), "B");
				cnt = InsecaccmdfbssgmtMapper.insert(Insecaccmdfbssgmt);
				if (cnt != 1)
					throw new Exception();
			} else if (flag == 2) {
				// 1.生成账户标识码变更记录
				PbcrsReportInsecaccidcaginf inSecAccIDCagInf = new PbcrsReportInsecaccidcaginf();
				//int key = InsecaccidcaginfMapper.getKey();
				inSecAccIDCagInf.setInSecAcctIDCagsInfSeqNo(guarAcctBsSgmt.getGuarAcctBsSgmtSeqNo());
				inSecAccIDCagInf.setInfRecType("231");
				inSecAccIDCagInf.setOdBnesCode(pbcrsreportguaracctbssgmt.getAcctCode());
				inSecAccIDCagInf.setNwBnesCode(guarAcctBsSgmt.getAcctCode());
				InsecaccidcaginfMapper.deleteByPrimaryKey(guarAcctBsSgmt.getGuarAcctBsSgmtSeqNo());
				cnt = InsecaccidcaginfMapper.insert(inSecAccIDCagInf);
				if (cnt != 1)
					throw new Exception();
				// 2.生成按段更正信息记录
				
				// 待更正段
				PbcrsReportInsecaccmdfbssgmt Insecaccmdfbssgmt = new PbcrsReportInsecaccmdfbssgmt();
				Insecaccmdfbssgmt.setBsSgmtSeqNo(guarAcctBsSgmt.getGuarAcctBsSgmtSeqNo());
				Insecaccmdfbssgmt.setInfRecType("232");
				Insecaccmdfbssgmt.setModRecCode(pbcrsreportguaracctbssgmt.getAcctCode());
				Insecaccmdfbssgmt.setAcctType(pbcrsreportguaracctbssgmt.getAcctType());
				Insecaccmdfbssgmt.setRptDate(nowDate);
				Insecaccmdfbssgmt.setMdfcSgmtCode("B");
				Insecaccmdfbssgmt.setReportflag("0");
				InsecaccmdfbssgmtMapper.deleteByPrimaryKey(Insecaccmdfbssgmt.getBsSgmtSeqNo(), "B");
				cnt = InsecaccmdfbssgmtMapper.insert(Insecaccmdfbssgmt);
				if (cnt != 1)
					throw new Exception();
			}
		}
		
		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(guarAcctBsSgmt);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportguaracctbssgmt, newMap, "GuarAcctBsSgmt",guarAcctBsSgmt.getGuarAcctBsSgmtSeqNo());
		return 1;

	}

	// 修改相关还款责任人段
	public int updateGsRltRepInfSgmt(Map<String, Object> params) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		// 页面信息
		PbcrsReportGsrltrepymtinf gsRltRepymtInf = (PbcrsReportGsrltrepymtinf) params.get("GsRltRepymtInf");
		// 数据库保存的信息
		PbcrsReportGsrltrepymtinf pbcrsreportgsrltrepymtinf = GsrltrepymtinfMapper.selectByPrimaryKey(gsRltRepymtInf.getRltRepymtInfSgmtSeqNo(), gsRltRepymtInf.getGsRltRepymtInfSeqNo());
		if (pbcrsreportgsrltrepymtinf != null) {
			// 更新原表信息
			gsRltRepymtInf.setReportflag(pbcrsreportgsrltrepymtinf.getReportflag());
			GsrltrepymtinfMapper.updateByPrimaryKey(gsRltRepymtInf);
		} else {
			throw new Exception();
		}
		// 将相关还款人的所有详细列表都插入到修改表中
		List<PbcrsReportGsrltrepymtinf> list = GsrltrepymtinfMapper.getByFirstKey(gsRltRepymtInf.getRltRepymtInfSgmtSeqNo());
		for (PbcrsReportGsrltrepymtinf temp : list) {
			// 插入相关还款人详细表
			GsrltrepymtinfMapper.deleteByPrimaryKeyM(temp.getRltRepymtInfSgmtSeqNo(), temp.getGsRltRepymtInfSeqNo());
			// 将报送状态改为未报送
			temp.setReportflag("0");
			// 插入新记录（实际就是做信息更新）
			int cnt = GsrltrepymtinfMapper.insertM(temp);
			if (cnt != 1)
				throw new Exception();
		}
		// 插入相关还款人修改表
		PbcrsReportGsrltrepinfsgmt pbcrsreportgsrltrepinfsgmt = GsrltrepinfsgmtMapper.selectByPrimaryKey(gsRltRepymtInf.getRltRepymtInfSgmtSeqNo());
		GsrltrepinfsgmtMapper.deleteByPrimaryKeyM(gsRltRepymtInf.getRltRepymtInfSgmtSeqNo());
		pbcrsreportgsrltrepinfsgmt.setReportflag("0");
		int cnt = GsrltrepinfsgmtMapper.insertM(pbcrsreportgsrltrepinfsgmt);
		if (cnt != 1)
			throw new Exception();
		String updateType = params.get("updateType").toString();
		log.info("修改类型为:"+updateType);
		if(StringUtils.isNotEmpty(updateType)&&updateType.equals(Constants.TYPE_UPDSUB)){
			// 基础段信息，用来获取账户标识码以及客户账户类型
			PbcrsReportGuaracctbssgmt guaracctbssgmt = GuaracctbssgmtMapper.selectByPrimaryKey(gsRltRepymtInf.getRltRepymtInfSgmtSeqNo());
			// 待更正段
			PbcrsReportInsecaccmdfbssgmt Insecaccmdfbssgmt = new PbcrsReportInsecaccmdfbssgmt();
			Insecaccmdfbssgmt.setBsSgmtSeqNo(pbcrsreportgsrltrepinfsgmt.getRltRepymtInfSgmtSeqNo());
			Insecaccmdfbssgmt.setInfRecType("232");
			Insecaccmdfbssgmt.setModRecCode(guaracctbssgmt.getAcctCode());
			Insecaccmdfbssgmt.setAcctType(guaracctbssgmt.getAcctType());
			Insecaccmdfbssgmt.setRptDate(nowDate);
			Insecaccmdfbssgmt.setMdfcSgmtCode("E");
			Insecaccmdfbssgmt.setReportflag("0");
			InsecaccmdfbssgmtMapper.deleteByPrimaryKey(Insecaccmdfbssgmt.getBsSgmtSeqNo(), "E");
			cnt = InsecaccmdfbssgmtMapper.insert(Insecaccmdfbssgmt);
			if (cnt != 1)
				throw new Exception();
		}
		
		// 插入痕迹表
		Map<String, Object> newMap = Util.Obj2Map(gsRltRepymtInf);
		newMap.put("userid", params.get("userid"));
		pbcrsReportTraceService.insertTrace(pbcrsreportgsrltrepymtinf, newMap, "GsRltRepInfSgmt",pbcrsreportgsrltrepinfsgmt.getRltRepymtInfSgmtSeqNo());
		return 1;
	}
	/**
	 * 判断修改的数据类型
	 * 
	 * @return 0：只修改账户标识码 1:只修改其他数据 2:修改了账户标识码以及其他数据
	 * 
	 */
	public int changeDateType(PbcrsReportGuaracctbssgmt oldData, PbcrsReportGuaracctbssgmt newData) throws Exception {
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
					if (!name.equals("AcctCode") && !name.equals("RptDate")) {
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

	/**
	 * 
	 * 分页参数封装
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, Object> getStartAndEnd(Map<String, Object> param) {
		int pageNo = MapUtils.getIntValue(param, "page");
		int pageSize = MapUtils.getIntValue(param, "rows");
		int skip = (pageNo - 1) * pageSize + 1;
		int endindex = skip + pageSize;
		param.put("endindex", endindex);
		param.put("startindex", skip);
		return param;
	}

}
