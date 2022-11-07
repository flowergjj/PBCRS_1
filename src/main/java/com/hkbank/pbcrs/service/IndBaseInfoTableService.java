package com.hkbank.pbcrs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.mapper.PbcrsReportIndBaseInfoTableMapper;

@Service
public class IndBaseInfoTableService {
	
	@Autowired
	private PbcrsReportIndBaseInfoTableMapper pbcrsReportIndBaseInfoTableMapper;

	public Map<String,Object> getList(Map<String, Object> params) {
		List<Map<String,Object>> list = pbcrsReportIndBaseInfoTableMapper.getList(params);
		Map<String,Object> rslt = new HashMap<String, Object>();
		rslt.put("rows", list);
		return rslt;
	}

}
