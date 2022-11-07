package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportInacctinf;

import java.util.List;
import java.util.Map;

public interface PbcrsReportInacctinfMapper  {

	public List<PbcrsReportInacctinf> selectByMap(Map map);

	public int selectByMapCount(Map<String, Object> paramMap);
}