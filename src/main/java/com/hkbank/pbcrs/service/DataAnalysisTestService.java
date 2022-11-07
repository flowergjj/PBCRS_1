package com.hkbank.pbcrs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.mapper.PbcrsReportDataAnalysisTestMapper;

@Service
public class DataAnalysisTestService {
	@Autowired
	private PbcrsReportDataAnalysisTestMapper pbcrsReportDataAnalysisTestMapper;

	public Map<String, Object> getCustomerReportInfo(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		list = pbcrsReportDataAnalysisTestMapper.getCustomerReportInfo(param);
		resultMap.put("count", list.size());
		resultMap.put("rows", list);
		return resultMap;
	}

	public Map<String, Object> getBaseInfoAnalysisInfo(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		list = pbcrsReportDataAnalysisTestMapper.getBaseInfoAnalysisInfo(param);
		resultMap.put("rows", list);
		return resultMap;
	}

	public Map<String, Object> getReportFromInfo(Map<String, Object> param) {
		List<Map<String, Object>> list;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		list = pbcrsReportDataAnalysisTestMapper.getReportFromInfo(param);
		resultMap.put("rows", list);
		return resultMap;
	}
}
