package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("AcctSpecTrstDspnSgmt")
public class PbcrsReportAccspetrsdspsgmt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6565565384484020643L;

	@XStreamImplicit
	private List<PbcrsReportCagoftrdinf> CagOfTrdInf;

	public List<PbcrsReportCagoftrdinf> getCagOfTrdInf() {
		return CagOfTrdInf;
	}

	public void setCagOfTrdInf(List<PbcrsReportCagoftrdinf> cagOfTrdInf) {
		CagOfTrdInf = cagOfTrdInf;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column
	 * PBCRS_REPORT_ACCSPETRSDSPSGMT.ACCTSPECTRSTDSPNSGMTSEQNO
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String AcctSpecTrstDspnSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCSPETRSDSPSGMT.CAGOFTRDNM
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String CagOfTrdNm;
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
	 * value of the database column
	 * PBCRS_REPORT_ACCSPETRSDSPSGMT.ACCTSPECTRSTDSPNSGMTSEQNO
	 * 
	 * @return the value of
	 *         PBCRS_REPORT_ACCSPETRSDSPSGMT.ACCTSPECTRSTDSPNSGMTSEQNO
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getAcctSpecTrstDspnSgmtSeqNo() {
		return AcctSpecTrstDspnSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column
	 * PBCRS_REPORT_ACCSPETRSDSPSGMT.ACCTSPECTRSTDSPNSGMTSEQNO
	 * 
	 * @param AcctSpecTrstDspnSgmtSeqNo
	 *            the value for
	 *            PBCRS_REPORT_ACCSPETRSDSPSGMT.ACCTSPECTRSTDSPNSGMTSEQNO
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setAcctSpecTrstDspnSgmtSeqNo(String AcctSpecTrstDspnSgmtSeqNo) {
		this.AcctSpecTrstDspnSgmtSeqNo = AcctSpecTrstDspnSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ACCSPETRSDSPSGMT.CAGOFTRDNM
	 * 
	 * @return the value of PBCRS_REPORT_ACCSPETRSDSPSGMT.CAGOFTRDNM
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getCagOfTrdNm() {
		return CagOfTrdNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCSPETRSDSPSGMT.CAGOFTRDNM
	 * 
	 * @param CagOfTrdNm
	 *            the value for PBCRS_REPORT_ACCSPETRSDSPSGMT.CAGOFTRDNM
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setCagOfTrdNm(String CagOfTrdNm) {
		this.CagOfTrdNm = CagOfTrdNm;
	}
}