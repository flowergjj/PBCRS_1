package com.hkbank.pbcrs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.mapper.PbcrsReportBusilinesMappingMapper;

@Service
public class PbcrsReportBusilinesMappingService {
	@Autowired
	private PbcrsReportBusilinesMappingMapper pbcrsReportBusilinesMappingMapper;
	public Map<String,Object> listPage(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportBusilinesMappingMapper.selectAllbyContCount(params);

		List<Map<String,Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String,Object>>();
		} else {
			int endindex= skip+pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportBusilinesMappingMapper.selectAllbyContPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}
	
	public Map<String,Object> saveParam(Map<String,Object> params) throws Exception{
		Map<String,Object> rsmap = new HashMap<String, Object>();
		params.put("type", "busilines");
		//保存特殊处理码表
		if(checkFlag(params)){
			int res = pbcrsReportBusilinesMappingMapper.SaveRes(params);
		}else{
			rsmap.put("RET_CODE", "FAILED");
			rsmap.put("RET_MSG", "业务编码已存在，添加失败！");
			return rsmap;
		}
		rsmap.put("RET_CODE", "SUCCESS");
		rsmap.put("RET_MSG", "添加成功！");
		return rsmap;
	}
	
	public boolean checkFlag(Map<String,Object> map){
		int count = pbcrsReportBusilinesMappingMapper.checkCount(map);
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
			 String prodId = params.get("listdata["+i+"][PRODID]").toString();

			 Map<String,Object> code = new HashMap<String, Object>();
			 code.put("sourceSys", sourceSys);
			 code.put("prodId", prodId);

			 pbcrsReportBusilinesMappingMapper.delCode(code);
		}
	}
}
