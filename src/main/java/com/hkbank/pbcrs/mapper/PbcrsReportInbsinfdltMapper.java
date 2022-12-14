package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportInbsinfdlt;

import java.util.List;
import java.util.Map;

public interface PbcrsReportInbsinfdltMapper extends BaseExternalSpi {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INBSINFDLT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(String InBsInfDltSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INBSINFDLT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportInbsinfdlt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INBSINFDLT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportInbsinfdlt selectByPrimaryKey(String InBsInfDltSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INBSINFDLT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportInbsinfdlt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INBSINFDLT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportInbsinfdlt record);

	List<PbcrsReportInbsinfdlt> selectByMap(Map<String, Object> paramMap);
}