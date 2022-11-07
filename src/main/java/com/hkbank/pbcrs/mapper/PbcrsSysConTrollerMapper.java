package com.hkbank.pbcrs.mapper;

import java.util.List;
import java.util.Map;

public interface PbcrsSysConTrollerMapper {
	
	public List<Map<?, ?>> findAllSysCon(Map<String,Object> param); 
	
	public List<Map<String, Object>> findSysConByType(Map<String,Object> param); 
	
	public String getKey();
	
	public void updSysCon(Map<String,Object> param);
	
	public void insertConInfo(Map<String,Object> param);
	
	public void updSysConInfo(Map<String,Object> param);
	
	public String execProc(Map<String,Object> param);
	
	public void test(Map<String,Object> param);
	
	public List<Map<?,?>> findSysConByconParentId(String conParentId);
	
	public Map<?,?> findByconId(String conId);
	
	public List<Map<?,?>> findListByconId(String conId);
	
	public int getCountByProcLog(Map<String,Object> param);
	
	public Map<String,Object> getSysCon(String key);
	
	public int getEtlLogInfoCount(Map<String,Object> param);
	
	public List<Map<String, Object>> getEtlLogInfo(Map<String,Object> param);
	
	public List<Map<String, Object>> findLogDetail(String webProcId);

}
