package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportAcctdbtinfsgmt;

import java.util.List;

public interface PbcrsReportAcctdbtinfsgmtMapper extends BaseExternalSpi {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCTDBTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(String AcctDbtInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCTDBTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportAcctdbtinfsgmt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCTDBTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportAcctdbtinfsgmt selectByPrimaryKey(String AcctDbtInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCTDBTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportAcctdbtinfsgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCTDBTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportAcctdbtinfsgmt record);

	int getKey();

	int deleteByPrimaryKeyM(String AcctDbtInfSgmtSeqNo);
	
	
}