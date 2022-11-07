package com.hkbank.pbcrs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.mapper.PbcrsReportDataJobMapper;
@Service
public class DataJobService {
	@Autowired 
	private PbcrsReportDataJobMapper dataJobMapper;
	public String getIndScoreData(Map<String,Object> params){
		List<Map<String, String>> indScoreData = dataJobMapper.getIndScoreData(params);
		StringBuilder builder = new StringBuilder();
		for (Map<String, String> map : indScoreData) {
			String rowData = map.get("DATA").toString();
			if(rowData != null){
				builder.append(rowData);
			}
			if(indScoreData.indexOf(map)!=indScoreData.size()){
				builder.append("\n");
			}
		}
		return builder.toString();
	}

}
