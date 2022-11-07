package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportInctrctmdfc;

import java.util.List;
import java.util.Map;

public interface PbcrsReportInctrctmdfcMapper extends BaseExternalSpi{

	List<PbcrsReportInctrctmdfc> selectByMap(Map<String, Object> paramMap);
	
	
}