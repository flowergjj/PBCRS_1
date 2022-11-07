package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportInctrctinf;

import java.util.List;
import java.util.Map;


public interface PbcrsReportInctrctinfMapper extends BaseExternalSpi {
	List<PbcrsReportInctrctinf> selectByMap(Map<String, Object> paramMap);

	int selectByMapCount(Map<String, Object> paramMap);

	
}