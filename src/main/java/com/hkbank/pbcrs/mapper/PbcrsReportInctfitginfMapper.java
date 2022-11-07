package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportInctfitginf;

import java.util.List;
import java.util.Map;

public interface PbcrsReportInctfitginfMapper extends BaseExternalSpi {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCTFITGINF
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int deleteByPrimaryKey(String InCtfItgInfSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCTFITGINF
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int insert(PbcrsReportInctfitginf record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCTFITGINF
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	PbcrsReportInctfitginf selectByPrimaryKey(String InCtfItgInfSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCTFITGINF
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	List<PbcrsReportInctfitginf> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCTFITGINF
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportInctfitginf record);

	List<PbcrsReportInctfitginf> selectByMap(Map<String, Object> paramMap);
}