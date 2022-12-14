package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("InCtrctMdfc")
public class PbcrsReportInctrctmdfc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3466943014990900816L;
	
	@XStreamAlias("BsSgmt")
	private PbcrsReportInctrctmdfcbssgmt InCtrctMdfcBsSgmt;
	
	@XStreamAlias("MdfcSgmt")
	private PbcrsReportInctrmdfmdfsgmt InCtrMdfMdfSgmt;
	
	
	
	public PbcrsReportInctrctmdfcbssgmt getInCtrctMdfcBsSgmt() {
		return InCtrctMdfcBsSgmt;
	}

	public void setInCtrctMdfcBsSgmt(PbcrsReportInctrctmdfcbssgmt inCtrctMdfcBsSgmt) {
		InCtrctMdfcBsSgmt = inCtrctMdfcBsSgmt;
	}

	public PbcrsReportInctrmdfmdfsgmt getInCtrMdfMdfSgmt() {
		return InCtrMdfMdfSgmt;
	}

	public void setInCtrMdfMdfSgmt(PbcrsReportInctrmdfmdfsgmt inCtrMdfMdfSgmt) {
		InCtrMdfMdfSgmt = inCtrMdfMdfSgmt;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INCTRCTMDFC.INCTRCTMDFCSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String InCtrctMdfcSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INCTRCTMDFC.BSSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String MdfcSgmtCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INCTRCTMDFC.MDFCSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	

	
	public String getInCtrctMdfcSeqNo() {
		return InCtrctMdfcSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INCTRCTMDFC.INCTRCTMDFCSEQNO
	 * @param InCtrctMdfcSeqNo  the value for PBCRS_REPORT_INCTRCTMDFC.INCTRCTMDFCSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInCtrctMdfcSeqNo(String InCtrctMdfcSeqNo) {
		this.InCtrctMdfcSeqNo = InCtrctMdfcSeqNo;
	}

	public String getMdfcSgmtCode() {
		return MdfcSgmtCode;
	}

	public void setMdfcSgmtCode(String mdfcSgmtCode) {
		MdfcSgmtCode = mdfcSgmtCode;
	}

	

	

	
}