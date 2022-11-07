package com.hkbank.pbcrs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("BalanceSheet")
public class PbcrsReportBalancesheet {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_BALANCESHEET.BALANCESHEETSEQNO
	 * 
	 * @mbggenerated Mon Mar 18 10:16:17 CST 2019
	 */
	@XStreamOmitField
	private String BalanceSheetSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_BALANCESHEET.BALANCESHEETBSSGMTSEQNO
	 * 
	 * @mbggenerated Mon Mar 18 10:16:17 CST 2019
	 */
	@XStreamOmitField
	private String BalanceSheetBsSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_BALANCESHEET.BALANCESHEET2007SGMTSEQNO
	 * 
	 * @mbggenerated Mon Mar 18 10:16:17 CST 2019
	 */
	@XStreamOmitField
	private String BalanceSheet2007SgmtSeqNo;
	@XStreamAlias("BalanceSheetBsSgmt")
	private PbcrsReportBalancesheetbssgm BalanceSheetBsSgmt;
	@XStreamAlias("BalanceSheet2007Sgmt")
	private PbcrsReportBalancesheet2007 BalanceSheet2007Sgmt;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_BALANCESHEET.BALANCESHEETSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_BALANCESHEET.BALANCESHEETSEQNO
	 * @mbggenerated Mon Mar 18 10:16:17 CST 2019
	 */
	public String getBalanceSheetSeqNo() {
		return BalanceSheetSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_BALANCESHEET.BALANCESHEETSEQNO
	 * 
	 * @param BalanceSheetSeqNo
	 *            the value for PBCRS_REPORT_BALANCESHEET.BALANCESHEETSEQNO
	 * @mbggenerated Mon Mar 18 10:16:17 CST 2019
	 */
	public void setBalanceSheetSeqNo(String BalanceSheetSeqNo) {
		this.BalanceSheetSeqNo = BalanceSheetSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column
	 * PBCRS_REPORT_BALANCESHEET.BALANCESHEETBSSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_BALANCESHEET.BALANCESHEETBSSGMTSEQNO
	 * @mbggenerated Mon Mar 18 10:16:17 CST 2019
	 */
	public String getBalanceSheetBsSgmtSeqNo() {
		return BalanceSheetBsSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column
	 * PBCRS_REPORT_BALANCESHEET.BALANCESHEETBSSGMTSEQNO
	 * 
	 * @param BalanceSheetBsSgmtSeqNo
	 *            the value for
	 *            PBCRS_REPORT_BALANCESHEET.BALANCESHEETBSSGMTSEQNO
	 * @mbggenerated Mon Mar 18 10:16:17 CST 2019
	 */
	public void setBalanceSheetBsSgmtSeqNo(String BalanceSheetBsSgmtSeqNo) {
		this.BalanceSheetBsSgmtSeqNo = BalanceSheetBsSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column
	 * PBCRS_REPORT_BALANCESHEET.BALANCESHEET2007SGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_BALANCESHEET.BALANCESHEET2007SGMTSEQNO
	 * @mbggenerated Mon Mar 18 10:16:17 CST 2019
	 */
	public String getBalanceSheet2007SgmtSeqNo() {
		return BalanceSheet2007SgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column
	 * PBCRS_REPORT_BALANCESHEET.BALANCESHEET2007SGMTSEQNO
	 * 
	 * @param BalanceSheet2007SgmtSeqNo
	 *            the value for
	 *            PBCRS_REPORT_BALANCESHEET.BALANCESHEET2007SGMTSEQNO
	 * @mbggenerated Mon Mar 18 10:16:17 CST 2019
	 */
	public void setBalanceSheet2007SgmtSeqNo(String BalanceSheet2007SgmtSeqNo) {
		this.BalanceSheet2007SgmtSeqNo = BalanceSheet2007SgmtSeqNo;
	}

	public PbcrsReportBalancesheetbssgm getBalanceSheetBsSgmt() {
		return BalanceSheetBsSgmt;
	}

	public void setBalanceSheetBsSgmt(
			PbcrsReportBalancesheetbssgm balanceSheetBsSgmt) {
		BalanceSheetBsSgmt = balanceSheetBsSgmt;
	}

	public PbcrsReportBalancesheet2007 getBalanceSheet2007Sgmt() {
		return BalanceSheet2007Sgmt;
	}

	public void setBalanceSheet2007Sgmt(
			PbcrsReportBalancesheet2007 balanceSheet2007Sgmt) {
		BalanceSheet2007Sgmt = balanceSheet2007Sgmt;
	}

}