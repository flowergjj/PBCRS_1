package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportInbasinfo;

import java.util.List;
import java.util.Map;


public interface PbcrsReportInbasinfoMapper {
	public List<PbcrsReportInbasinfo> selectByMap(Map map);

	public int selectByMapCount(Map<String, Object> map);
}

	