package com.hkbank.pbcrs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.mapper.PbcrsReportSpeCustIdMapper;

@Service
public class PbcrsReportSpeCustIdService {
	@Autowired
	private PbcrsReportSpeCustIdMapper  pbcrsReportSpeCustIdMapper;
	public Map<String,Object> listPage(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportSpeCustIdMapper.selectAllbyContCount(params);

		List<Map<String,Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String,Object>>();
		} else {
			int endindex= skip+pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportSpeCustIdMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}
	
	public Map<String,Object> saveCustId(Map<String,Object> params) throws Exception{
		Map<String,Object> rsmap = new HashMap<String, Object>();
		if(checkFlag(params)){
			int resCount = pbcrsReportSpeCustIdMapper.SaveCustId(params);
			if(resCount > 0){
				rsmap.put("RET_CODE", "SUCCESS");
				rsmap.put("RET_MSG", "添加成功！");
			}else{
				rsmap.put("RET_CODE", "FAILED");
				rsmap.put("RET_MSG", "添加失败！");
			}
		}else{
			rsmap.put("RET_CODE", "FAILED");
			rsmap.put("RET_MSG", "业务主键已存在，请勿重复添加！");
		}

		return rsmap;
	}
	
	public boolean checkFlag(Map<String,Object> map){
		int count = pbcrsReportSpeCustIdMapper.checkCount(map);
		if(count == 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public void delParam(Map<String,Object> params) throws Exception{
		int size = Integer.parseInt(params.get("listSize").toString());
		for (int i = 0; i < size; i++) {
			 String sourceSys = params.get("listdata["+i+"][SOURCESYS]").toString();
			 String sourceCustId = params.get("listdata["+i+"][SOURCECUSTID]").toString();
			 Map<String,Object> code = new HashMap<String, Object>();
			 code.put("sourceSys", sourceSys);
			 code.put("sourceCustId", sourceCustId);
			 pbcrsReportSpeCustIdMapper.delCustId(code);
		}
	}
}
