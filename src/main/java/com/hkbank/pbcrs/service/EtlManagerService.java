package com.hkbank.pbcrs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.service.system.SysCodeService;
import com.hkbank.pbcrs.mapper.PbcrsReportEtlManagerMapper;

@Service
public class EtlManagerService {
	@Autowired
	private SysCodeService sysCodeService;
	@Autowired
	private PbcrsReportEtlManagerMapper pbcrsReportEtlManagerMapper;

	public Map<String, Object> getDataDate(Map<String, Object> params) {
		List<Map<String, Object>> list = pbcrsReportEtlManagerMapper.getDataDate(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("query", list);
		return rslt;
	}

	public Map<String, Object> getEtlLog(Map<String, Object> map) {
		List<Map<String, Object>> dsEtlLogList = pbcrsReportEtlManagerMapper.getDsEtlLog(map);
		List<Map<String, Object>> rptEtlLogList = pbcrsReportEtlManagerMapper.getRptEtlLog(map);
		Map<String, Object> rslt = new HashMap<String, Object>();
		if (dsEtlLogList != null) {
			rslt.put("dsEtlLog", dsEtlLogList);
		}
		if (rptEtlLogList != null) {
			rslt.put("rptEtlLog", rptEtlLogList);
		}
		return rslt;

	}
	
	@SuppressWarnings("all")
	public Map<String, Object> getChackAllLog(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		param = getStartAndEnd(param);
		int total = pbcrsReportEtlManagerMapper.getCheckAllCount(param);
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// 查询列表
			list = pbcrsReportEtlManagerMapper.getCheckAllList(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;
	}
	
	@SuppressWarnings("all")
	public Map<String, Object> getErrorPK(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		param = getStartAndEnd(param);
		int total = pbcrsReportEtlManagerMapper.getErrorPKCount(param);
		if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
			list = new ArrayList();
		} else {
			// 查询列表
			list = pbcrsReportEtlManagerMapper.getErrorPKList(param);
			if (list == null) {
				list = new ArrayList();
			}
		}
		resultMap.put("total", total);
		resultMap.put("rows", list);
		return resultMap;
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

	public Map<String, Object> getByEtlType(Map<String, Object> params) {
		List<Map<String, Object>> list = pbcrsReportEtlManagerMapper.getByEtlType(params);
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("query", list);
		return rslt;
	}

	public List<Map<String, Object>> getEtlType(Map<String, String> param) {
		Map<String,String> findMap = new HashMap<String, String>();
		findMap.put("type", "ETL_TYPE");
		List<Map<String, Object>> list = sysCodeService.findSysCode(findMap);
		for (Map<String, Object> map2 : list) {
			  String type = param.get("sourceSys").split("_")[1]+"_"+map2.get("id")+"_REPORT_TYPE";
		      findMap.put("type", type);
		      List<Map<String, Object>> RPT_TYPE_LIST = sysCodeService.findSysCode(findMap);
		      map2.put("children", RPT_TYPE_LIST);
		}
		return list;
	}

	public Map<String, Object> getErrorDate(Map<String, Object> params) {
		
		return pbcrsReportEtlManagerMapper.getErrorDate(params);
	}

	public Map<String, Object> getCheckByGroup(Map<String, Object> params) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		list = pbcrsReportEtlManagerMapper.getCheckByGroup(params);
		resultMap.put("data", list);
		return resultMap;
	}

	
}
