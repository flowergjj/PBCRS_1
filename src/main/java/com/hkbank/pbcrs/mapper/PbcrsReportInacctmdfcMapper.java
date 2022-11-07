package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportInacctmdfc;

import java.util.List;
import java.util.Map;

public interface PbcrsReportInacctmdfcMapper extends BaseExternalSpi {

	

	List<PbcrsReportInacctmdfc> selectByMap(Map<String, Object> paramMap);
}