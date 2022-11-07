package com.hkbank.pbcrs.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.mapper.DataIntegrityTestMapper;

@Service
public class DataIntegrityTestService {

	@Autowired
	private DataIntegrityTestMapper dataIntegrityTestMapper;
	
	public Map<String,Object> listPage(Map<String,Object> params){
		List<Map<String,Object>> list = null;
		String tableType = params.get("tableType").toString();
		if(tableType.equals("DcTable")){
			list = dataIntegrityTestMapper.selectDcList(params);
		}else if(tableType.equals("GuTable")){
			list = dataIntegrityTestMapper.selectGuList(params);
		}else if(tableType.equals("BaseTable")){
			list = dataIntegrityTestMapper.selectBaseList(params);
		}else if(tableType.equals("ReportTable")){
			list = dataIntegrityTestMapper.selectReportList(params);
		}else if(tableType.equals("D1Table")){
			list = dataIntegrityTestMapper.selectD1List(params);
		}else if(tableType.equals("D2Table")){
			list = dataIntegrityTestMapper.selectD2List(params);
		}else if(tableType.equals("R4Table")){
			String date = params.get("queryRptDate").toString();
			params.put("parDate", "P"+date.replaceAll("-", ""));
			list = dataIntegrityTestMapper.selectR4List(params);
		}else if(tableType.equals("ClTable")){
			list = dataIntegrityTestMapper.selectClList(params);
		}else if(tableType.equals("G1Table")){
			list = dataIntegrityTestMapper.selectG1List(params);
		}else if(tableType.equals("G2Table")){
			list = dataIntegrityTestMapper.selectG2List(params);
		}else if(tableType.equals("G3Table")){
			list = dataIntegrityTestMapper.selectG3List(params);
		}else if(tableType.equals("DzyTable")){
			list = dataIntegrityTestMapper.selectDzyList(params);
		}		
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
		
	}
	
	
	/*public Map<String,Object> inListPage(Map<String,Object> params) throws ParseException{
		List<Map<String,Object>> list = null;
		String tableType = params.get("tableType").toString();
		if(tableType.equals("infoManager")){
			list = dataIntegrityTestMapper.getInfoManager(params);
		}else if(tableType.equals("PLN_infoManager")){
			list = dataIntegrityTestMapper.getPLN_infoManager(params);
		}else if(tableType.equals("ILN_infoManager")){
			list = dataIntegrityTestMapper.getILN_infoManager(params);
		}else if(tableType.equals("SLN_infoManager")){
			list = dataIntegrityTestMapper.getSLN_infoManager(params);
		}else if(tableType.equals("indBaseAddCheck")){
			String queryRptDate = params.get("queryRptDate").toString();
			if(StringUtils.isNotEmpty(queryRptDate)){
				Date date = DateUtils.parseDate(queryRptDate, "yyyy-MM-dd");
				Date addMonths = DateUtils.addMonths(date, -1);
				params.put("preDate", DateFormatUtils.format(addMonths, "yyyy-MM-dd"));
			}
			list = dataIntegrityTestMapper.getIndBaseAddCheck(params);
		}else if(tableType.equals("d1FirstCheckResult")){
			String queryRptDate = params.get("queryRptDate").toString();
			if(StringUtils.isNotEmpty(queryRptDate)){
				Date date = DateUtils.parseDate(queryRptDate, "yyyy-MM-dd");
				Date addMonths = DateUtils.addMonths(date, -2);
				queryRptDate = DateFormatUtils.format(addMonths, "yyyy-MM-dd");
				params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
				params.put("queryRptDate",queryRptDate);
			} 
			list = dataIntegrityTestMapper.getD1CheckResult1(params);
		}else if(tableType.equals("d1SecondCheckResult")){
			String queryRptDate = params.get("queryRptDate").toString();
			if(StringUtils.isNotEmpty(queryRptDate)){
				Date date = DateUtils.parseDate(queryRptDate, "yyyy-MM-dd");
				Date addMonths = DateUtils.addMonths(date, -1);
				queryRptDate = DateFormatUtils.format(addMonths, "yyyy-MM-dd");
				params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
				params.put("queryRptDate",queryRptDate);
			} 
			list = dataIntegrityTestMapper.getD1CheckResult2(params);
		}else if(tableType.equals("d1ThirdCheckResult")){
			String queryRptDate = params.get("queryRptDate").toString();
			if(StringUtils.isNotEmpty(queryRptDate)){
				params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
			}
			list = dataIntegrityTestMapper.getD1CheckResult3(params);
		}else if(tableType.equals("r4FirstCheckResult")){
			String queryRptDate = params.get("queryRptDate").toString();
			if(StringUtils.isNotEmpty(queryRptDate)){
				Date date = DateUtils.parseDate(queryRptDate, "yyyy-MM-dd");
				Date addMonths = DateUtils.addMonths(date, -2);
				queryRptDate = DateFormatUtils.format(addMonths, "yyyy-MM-dd");
				params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
				params.put("queryRptDate",queryRptDate);
			} 
			list = dataIntegrityTestMapper.getR4CheckResult1(params);
		}else if(tableType.equals("r4SecondCheckResult")){
			String queryRptDate = params.get("queryRptDate").toString();
			if(StringUtils.isNotEmpty(queryRptDate)){
				Date date = DateUtils.parseDate(queryRptDate, "yyyy-MM-dd");
				Date addMonths = DateUtils.addMonths(date, -1);
				queryRptDate = DateFormatUtils.format(addMonths, "yyyy-MM-dd");
				params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
				params.put("queryRptDate",queryRptDate);
			} 
			list = dataIntegrityTestMapper.getR4CheckResult2(params);
		}else if(tableType.equals("r4ThirdCheckResult")){
			String queryRptDate = params.get("queryRptDate").toString();
			if(StringUtils.isNotEmpty(queryRptDate)){
				params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
			}
			list = dataIntegrityTestMapper.getR4CheckResult3(params);
		}else if(tableType.equals("indCtrCheckResult")){
			list = dataIntegrityTestMapper.getIndCtrCheckResult(params);
		}else if(tableType.equals("modCheckResult")){
		    list = dataIntegrityTestMapper.getModCheckResult(params);
		}else if(tableType.equals("baseInfoJoinCheckResult")){
		    list = dataIntegrityTestMapper.getBaseInfoJoinCheckResult(params);
		}else if(tableType.equals("ctrInfoJoinCheckResult")){
		    list = dataIntegrityTestMapper.getCtrInfoJoinCheckResult(params);
		}else if(tableType.equals("modInfoJoinCheckResult")){
		    list = dataIntegrityTestMapper.getModInfoJoinCheckResult(params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
		
		}*/

	/**
	 * 客户资料统一管理
	 * @param params
	 * @return
	 */
	public Map<String, Object> infoManager(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		list = dataIntegrityTestMapper.getInfoManager(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}

	/**
	 * 客户资料分系统管理 个贷
	 * @param params
	 * @return
	 */
	public Map<String, Object> PLNinfoManager(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		list = dataIntegrityTestMapper.getPLN_infoManager(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}
	
	/**
	 * 客户资料分系统管理 网贷
	 * @param params
	 * @return
	 */
	public Map<String, Object> ILNinfoManager(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		list = dataIntegrityTestMapper.getILN_infoManager(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}

	/**
	 * 客户资料分系统管理 小微
	 * @param params
	 * @return
	 */
	public Map<String, Object> SLNinfoManager(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		list = dataIntegrityTestMapper.getSLN_infoManager(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}

	/**
	 * 基本信息增量数据
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> indBaseAddCheck(Map<String, Object> params) throws ParseException {
		List<Map<String,Object>> list = null;
		String queryRptDate = params.get("queryRptDate").toString();
		if(StringUtils.isNotEmpty(queryRptDate)){
			Date date = DateUtils.parseDate(queryRptDate, "yyyy-MM-dd");
			Date addMonths = DateUtils.addMonths(date, -1);
			params.put("preDate", DateFormatUtils.format(addMonths, "yyyy-MM-dd"));
		}
		list = dataIntegrityTestMapper.getIndBaseAddCheck(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}


	/**
	 * D1账户第一个月
	 * @param params
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> d1FirstCheckResult(Map<String, Object> params) throws ParseException {
		List<Map<String,Object>> list = null;
		/*String queryRptDate = params.get("queryRptDate").toString();
		
		if(StringUtils.isNotEmpty(queryRptDate)){
			Date date = DateUtils.parseDate(queryRptDate, "yyyy-MM-dd");
			Date addMonths = DateUtils.addMonths(date, -2);
			queryRptDate = DateFormatUtils.format(addMonths, "yyyy-MM-dd");
			params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
			params.put("queryRptDate",queryRptDate);
		} */
		System.out.println("D1第一个月参数:"+params);
		list = dataIntegrityTestMapper.getD1CheckResult1(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		return rslt;
		
	}


	/**
	 * D1账户第二个月
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> d1SecondCheckResult(Map<String, Object> params) throws ParseException {
		List<Map<String,Object>> list = null;
		/*String queryRptDate = params.get("queryRptDate").toString();
		if(StringUtils.isNotEmpty(queryRptDate)){
			Date date = DateUtils.parseDate(queryRptDate, "yyyy-MM-dd");
			Date addMonths = DateUtils.addMonths(date, -1);
			queryRptDate = DateFormatUtils.format(addMonths, "yyyy-MM-dd");
			params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
			params.put("queryRptDate",queryRptDate);
		} */
		System.out.println("D1第二个月参数:"+params);
		list = dataIntegrityTestMapper.getD1CheckResult2(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		return rslt;
		
	}


	/**
	 * D1账户第三个月
	 * @param params
	 * @return
	 */
	public Map<String, Object> d1ThirdCheckResult(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		/*String queryRptDate = params.get("queryRptDate").toString();
		if(StringUtils.isNotEmpty(queryRptDate)){
			params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
		}*/
		System.out.println("D1第三个月参数:"+params);
		list = dataIntegrityTestMapper.getD1CheckResult3(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		return rslt;
		
	}


	/**
	 * R4账户第一个月
	 * @param params
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> r4FirstCheckResult(Map<String, Object> params) throws ParseException {
		List<Map<String,Object>> list = null;
		/*String queryRptDate = params.get("queryRptDate").toString();
		if(StringUtils.isNotEmpty(queryRptDate)){
			Date date = DateUtils.parseDate(queryRptDate, "yyyy-MM-dd");
			Date addMonths = DateUtils.addMonths(date, -2);
			queryRptDate = DateFormatUtils.format(addMonths, "yyyy-MM-dd");
			params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
			params.put("queryRptDate",queryRptDate);
		} */
		list = dataIntegrityTestMapper.getR4CheckResult1(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}


	/**
	 * R4账户第二个月
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> r4SecondCheckResult(Map<String, Object> params) throws ParseException {
		List<Map<String,Object>> list = null;
		/*String queryRptDate = params.get("queryRptDate").toString();
		if(StringUtils.isNotEmpty(queryRptDate)){
			Date date = DateUtils.parseDate(queryRptDate, "yyyy-MM-dd");
			Date addMonths = DateUtils.addMonths(date, -1);
			queryRptDate = DateFormatUtils.format(addMonths, "yyyy-MM-dd");
			params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
			params.put("queryRptDate",queryRptDate);
		} */
		list = dataIntegrityTestMapper.getR4CheckResult2(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}


	/**
	 * R4账户第三个月
	 * @param params
	 * @return
	 */
	public Map<String, Object> r4ThirdCheckResult(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		/*String queryRptDate = params.get("queryRptDate").toString();
		if(StringUtils.isNotEmpty(queryRptDate)){
			params.put("parDate", "P"+queryRptDate.replaceAll("-", ""));
		}*/
		list = dataIntegrityTestMapper.getR4CheckResult3(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}

	/**
	 * 个人授信协议
	 * @param params
	 * @return
	 */
	public Map<String, Object> indCtrCheckResult(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		list = dataIntegrityTestMapper.getIndCtrCheckResult(params);
		
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}


	/**
	 * 抵质押信息
	 * @param params
	 * @return
	 */
	public Map<String, Object> modCheckResult(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		list = dataIntegrityTestMapper.getModCheckResult(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}


	/**
	 * 信息关联信对比-个人基本信息
	 * @param params
	 * @return
	 */
	public Map<String, Object> baseInfoJoinCheckResult(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		list = dataIntegrityTestMapper.getBaseInfoJoinCheckResult(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}


	/**
	 * 信息关联信对比-个人授信信息
	 * @param params
	 * @return
	 */
	public Map<String, Object> ctrInfoJoinCheckResult(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		list = dataIntegrityTestMapper.getCtrInfoJoinCheckResult(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}


	/**
	 * 信息关联信对比-个人抵质押信息
	 * @param params
	 * @return
	 */
	public Map<String, Object> modInfoJoinCheckResult(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		list = dataIntegrityTestMapper.getModInfoJoinCheckResult(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		if(list != null ){
			return rslt;
		}else{
			return null;
		}
	}
	
	
	
	
	
	
}
