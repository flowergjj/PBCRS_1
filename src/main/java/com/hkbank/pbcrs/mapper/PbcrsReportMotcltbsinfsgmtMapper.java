package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportMotcltbsinfsgmt;

import java.util.List;

public interface PbcrsReportMotcltbsinfsgmtMapper extends BaseExternalSpi{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOTCLTBSINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int deleteByPrimaryKey(String MotgaCltalBsInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOTCLTBSINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int insert(PbcrsReportMotcltbsinfsgmt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOTCLTBSINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	PbcrsReportMotcltbsinfsgmt selectByPrimaryKey(String MotgaCltalBsInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOTCLTBSINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	List<PbcrsReportMotcltbsinfsgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOTCLTBSINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportMotcltbsinfsgmt record);

	int getKey();
}