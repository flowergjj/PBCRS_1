package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportAcctcredsgmt;

import java.util.List;

public interface PbcrsReportAcctcredsgmtMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCTCREDSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(String AcctCredSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCTCREDSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportAcctcredsgmt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCTCREDSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportAcctcredsgmt selectByPrimaryKey(String AcctCredSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCTCREDSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportAcctcredsgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ACCTCREDSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportAcctcredsgmt record);

	int deleteByPrimaryKeyM(String acctCredSgmtSeqNo);

	int insertM(String acctCredSgmtSeqNo);
}