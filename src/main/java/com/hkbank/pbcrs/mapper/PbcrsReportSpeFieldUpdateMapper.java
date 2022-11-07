package com.hkbank.pbcrs.mapper;

import java.util.List;
import java.util.Map;

public interface PbcrsReportSpeFieldUpdateMapper {
	int selectAllbyContCount(Map<String, Object> map);

	List<Map<String, Object>> selectAllbyContPage(Map<String, Object> params);

	int selectFieldInfoCount(Map<String, Object> map);

	List<Map<String, Object>> selectFieldInfoPage(Map<String, Object> params);

	List<Map<String, Object>> getConditionCombox(Map<String, String> params);

	int insertSpedeal(Map<String, Object> params);

	int deleteSpedeal(Map<String, String> map);

}
