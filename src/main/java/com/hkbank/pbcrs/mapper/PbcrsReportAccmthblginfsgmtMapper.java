package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportAccmthblginfsgmt;

import java.util.List;

public interface PbcrsReportAccmthblginfsgmtMapper extends BaseExternalSpi {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCMTHBLGINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(String AcctMthlyBlgInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCMTHBLGINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportAccmthblginfsgmt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCMTHBLGINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportAccmthblginfsgmt selectByPrimaryKey(String AcctMthlyBlgInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCMTHBLGINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportAccmthblginfsgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCMTHBLGINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportAccmthblginfsgmt record);

	int getKey();

	int deleteByPrimaryKeyM(String AcctMthlyBlgInfSgmtSeqNo);
	
	
}