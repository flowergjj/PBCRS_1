package com.hkbank.pbcrs.mapper;

import java.util.List;
import java.util.Map;

public interface DataIntegrityTestMapper {
	List<Map<String,Object>> selectDzyList(Map<String,Object> map);
	
	List<Map<String,Object>> selectGuList(Map<String,Object> map);
		
	List<Map<String,Object>> selectD1List(Map<String,Object> map);
	
	List<Map<String,Object>> selectD2List(Map<String,Object> map);
	
	List<Map<String,Object>> selectR4List(Map<String,Object> map);
	
	List<Map<String,Object>> selectDcList(Map<String,Object> map);
	
	List<Map<String,Object>> selectReportList(Map<String,Object> map);
	
	List<Map<String,Object>> selectBaseList(Map<String,Object> map);
	
	List<Map<String,Object>> selectG1List(Map<String,Object> map);
	
	List<Map<String,Object>> selectG2List(Map<String,Object> map);
	
	List<Map<String,Object>> selectG3List(Map<String,Object> map);
	
	List<Map<String,Object>> selectClList(Map<String,Object> map);

	List<Map<String, Object>> getInfoManager(Map<String, Object> params);

	List<Map<String, Object>> getPLN_infoManager(Map<String, Object> params);

	List<Map<String, Object>> getILN_infoManager(Map<String, Object> params);

	List<Map<String, Object>> getSLN_infoManager(Map<String, Object> params);

	List<Map<String, Object>> getIndBaseAddCheck(Map<String, Object> params);

	List<Map<String, Object>> getD1CheckResult(Map<String, Object> params);

	List<Map<String, Object>> getR4CheckResult(Map<String, Object> params);

	List<Map<String, Object>> getIndCtrCheckResult(Map<String, Object> params);

	List<Map<String, Object>> getModCheckResult(Map<String, Object> params);

	List<Map<String, Object>> getBaseInfoJoinCheckResult(Map<String, Object> params);

	List<Map<String, Object>> getCtrInfoJoinCheckResult(Map<String, Object> params);

	List<Map<String, Object>> getModInfoJoinCheckResult(Map<String, Object> params);

	List<Map<String, Object>> getD1CheckResult1(Map<String, Object> params);
	List<Map<String, Object>> getD1CheckResult2(Map<String, Object> params);
	List<Map<String, Object>> getD1CheckResult3(Map<String, Object> params);

	List<Map<String, Object>> getR4CheckResult1(Map<String, Object> params);
	List<Map<String, Object>> getR4CheckResult2(Map<String, Object> params);
	List<Map<String, Object>> getR4CheckResult3(Map<String, Object> params);
	

}
