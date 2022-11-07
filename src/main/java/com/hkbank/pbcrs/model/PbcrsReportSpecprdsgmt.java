package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("SpecPrdSgmt")
public class PbcrsReportSpecprdsgmt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2208078956998284626L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_SPECPRDSGMT.SPECPRDSGMTSEQNO
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String SpecPrdSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_SPECPRDSGMT.SPECLINE
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String SpecLine;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_SPECPRDSGMT.SPECEFCTDATE
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String SpecEfctDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_SPECPRDSGMT.SPECENDDATE
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String SpecEndDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_SPECPRDSGMT.USEDINSTAMT
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String UsedInstAmt;
	/**
	 * 
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_BSSGMT.REPORTFLAG
	 * 
	 * @mbggenerated Wed Apr 24 09:13:16 CST 2019
	 */
	@XStreamOmitField
	private String reportflag;
	
	@XStreamOmitField
	private String ParagraphDel;
	
	public String getParagraphDel() {
		return ParagraphDel;
	}

	public void setParagraphDel(String paragraphDel) {
		ParagraphDel = paragraphDel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_BSSGMT.REPORTFLAG
	 * 
	 * @return the value of PBCRS_REPORT_BSSGMT.REPORTFLAG
	 * 
	 * @mbggenerated Wed Apr 24 09:13:16 CST 2019
	 */
	public String getReportflag() {
		return reportflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_BSSGMT.REPORTFLAG
	 * 
	 * @param reportflag
	 *            the value for PBCRS_REPORT_BSSGMT.REPORTFLAG
	 * 
	 * @mbggenerated Wed Apr 24 09:13:16 CST 2019
	 */
	public void setReportflag(String reportflag) {
		this.reportflag = reportflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_SPECPRDSGMT.SPECPRDSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_SPECPRDSGMT.SPECPRDSGMTSEQNO
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */

	public String getSpecPrdSgmtSeqNo() {
		return SpecPrdSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_SPECPRDSGMT.SPECPRDSGMTSEQNO
	 * 
	 * @param SpecPrdSgmtSeqNo
	 *            the value for PBCRS_REPORT_SPECPRDSGMT.SPECPRDSGMTSEQNO
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setSpecPrdSgmtSeqNo(String SpecPrdSgmtSeqNo) {
		this.SpecPrdSgmtSeqNo = SpecPrdSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_SPECPRDSGMT.SPECLINE
	 * 
	 * @return the value of PBCRS_REPORT_SPECPRDSGMT.SPECLINE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getSpecLine() {
		return SpecLine;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_SPECPRDSGMT.SPECLINE
	 * 
	 * @param SpecLine
	 *            the value for PBCRS_REPORT_SPECPRDSGMT.SPECLINE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setSpecLine(String SpecLine) {
		this.SpecLine = SpecLine;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_SPECPRDSGMT.SPECEFCTDATE
	 * 
	 * @return the value of PBCRS_REPORT_SPECPRDSGMT.SPECEFCTDATE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getSpecEfctDate() {
		return SpecEfctDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_SPECPRDSGMT.SPECEFCTDATE
	 * 
	 * @param SpecEfctDate
	 *            the value for PBCRS_REPORT_SPECPRDSGMT.SPECEFCTDATE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setSpecEfctDate(String SpecEfctDate) {
		this.SpecEfctDate = SpecEfctDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_SPECPRDSGMT.SPECENDDATE
	 * 
	 * @return the value of PBCRS_REPORT_SPECPRDSGMT.SPECENDDATE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getSpecEndDate() {
		return SpecEndDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_SPECPRDSGMT.SPECENDDATE
	 * 
	 * @param SpecEndDate
	 *            the value for PBCRS_REPORT_SPECPRDSGMT.SPECENDDATE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setSpecEndDate(String SpecEndDate) {
		this.SpecEndDate = SpecEndDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_SPECPRDSGMT.USEDINSTAMT
	 * 
	 * @return the value of PBCRS_REPORT_SPECPRDSGMT.USEDINSTAMT
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getUsedInstAmt() {
		return UsedInstAmt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_SPECPRDSGMT.USEDINSTAMT
	 * 
	 * @param UsedInstAmt
	 *            the value for PBCRS_REPORT_SPECPRDSGMT.USEDINSTAMT
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setUsedInstAmt(String UsedInstAmt) {
		this.UsedInstAmt = UsedInstAmt;
	}
}