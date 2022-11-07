package com.hkbank.pbcrs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hkbank.pbcrs.mapper.PbcrsReportSpeFieldUpdateMapper;

@Service
public class SpeFieldUpdateService {
	@Autowired
	private PbcrsReportSpeFieldUpdateMapper pbcrsReportSpeFieldUpdateMapper;

	public Map<String, Object> listPage(Map<String, Object> params) {

		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");

		int skip = (pageNo - 1) * pageSize;

		/* 2. 计算总记录数 */
		int total = pbcrsReportSpeFieldUpdateMapper.selectAllbyContCount(params);

		List<Map<String, Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			int endindex = skip + pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportSpeFieldUpdateMapper.selectAllbyContPage(params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}

	public Map<String, Object> fieldInfoList(Map<String, Object> params) {

		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");

		int skip = (pageNo - 1) * pageSize;

		/* 2. 计算总记录数 */
		int total = pbcrsReportSpeFieldUpdateMapper.selectFieldInfoCount(params);

		List<Map<String, Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			int endindex = skip + pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportSpeFieldUpdateMapper.selectFieldInfoPage(params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}

	public List<Map<String, Object>> getConditionCombox(Map<String, String> params) {

		return pbcrsReportSpeFieldUpdateMapper.getConditionCombox(params);
	}

	public int insertSpedeal(Map<String, Object> params) {
		if(params.get("conditionDesc").toString().contains("主键")){
			params.put("conditionValue", params.get("conditionValue").toString()+"%");
		}
		return pbcrsReportSpeFieldUpdateMapper.insertSpedeal(params);
	}

	@SuppressWarnings("unchecked")
	public int deleteSpedeal(Map<String, Object> params) {
		String jsonParams = (String) params.get("deleteList");
		List<Map<String, String>> list = (List<Map<String, String>>) JSONObject.parse(jsonParams);
		int rowNum = 0;
		for (Map<String, String> map : list) {
			rowNum += pbcrsReportSpeFieldUpdateMapper.deleteSpedeal(map);
		}
		
		return rowNum;

	}
}
