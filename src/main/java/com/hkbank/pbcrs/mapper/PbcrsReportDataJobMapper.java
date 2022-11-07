package com.hkbank.pbcrs.mapper;

import java.util.List;
import java.util.Map;

public interface PbcrsReportDataJobMapper {
	public List<Map<String,String>> getIndScoreData(Map<String,Object> params);

}
