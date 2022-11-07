package com.hkbank.pbcrs.mapper;

import java.util.List;
import java.util.Map;

public interface PbcrsReportDataAnalysisTestMapper {

	List<Map<String, Object>> getCustomerReportInfo(Map<String, Object> param);

	List<Map<String, Object>> getBaseInfoAnalysisInfo(Map<String, Object> param);

	List<Map<String, Object>> getReportFromInfo(Map<String, Object> param);

}
