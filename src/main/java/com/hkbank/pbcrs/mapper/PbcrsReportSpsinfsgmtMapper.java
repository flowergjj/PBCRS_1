package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportSpsinfsgmt;

import java.util.List;

public interface PbcrsReportSpsinfsgmtMapper extends BaseExternalSpi {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_SPSINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int deleteByPrimaryKey(String SpsInfSgmtSeqNo);
	

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_SPSINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int insert(PbcrsReportSpsinfsgmt record);
	

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_SPSINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	PbcrsReportSpsinfsgmt selectByPrimaryKey(String SpsInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_SPSINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	List<PbcrsReportSpsinfsgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_SPSINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportSpsinfsgmt record);
	//得到主键
	int getKey();
}