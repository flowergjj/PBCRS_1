package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("MdfcSgmt")
public class PbcrsReportInaccmdfmdfsgmt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1033250737486387002L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCMDFMDFSGMT.MDFCSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String MdfcSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCMDFMDFSGMT.MDFCSGMTREFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String MdfcSgmtRefSeqNo;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCMDFMDFSGMT.MDFCSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_INACCMDFMDFSGMT.MDFCSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamAlias("AcctBsSgmt")
	private PbcrsReportAcctbssgmt AcctBsSgmt;
	
	@XStreamAlias("AcctBsInfSgmt")
	private PbcrsReportAcctbsinfsgmt AcctBsInfSgmt;
	
	@XStreamAlias("MotgaCltalCtrctInfSgmt")
	private PbcrsReportMotcltctrinfsgmt MotCltCtrInfSgmt;
	
	@XStreamAlias("AcctCredSgmt")
	private PbcrsReportAcctcredsgmt AcctCredSgmt;
	
	@XStreamAlias("AcctMthlyBlgInfSgmt")
	private PbcrsReportAccmthblginfsgmt AccMthBlgInfSgmt;
	
	@XStreamAlias("SpecPrdSgmt")
	private PbcrsReportSpecprdsgmt SpecPrdSgmt;
	
	@XStreamAlias("AcctDbtInfSgmt")
	private PbcrsReportAcctdbtinfsgmt AcctDbtInfSgmt;
	
	@XStreamAlias("AcctSpecTrstDspnSgmt")
	private PbcrsReportAccspetrsdspsgmt AccSpeTrsDspSgmt;

	public String getMdfcSgmtSeqNo() {
		return MdfcSgmtSeqNo;
	}

	
	public void setMdfcSgmtSeqNo(String MdfcSgmtSeqNo) {
		this.MdfcSgmtSeqNo = MdfcSgmtSeqNo;
	}

	
	public String getMdfcSgmtRefSeqNo() {
		return MdfcSgmtRefSeqNo;
	}

	
	public void setMdfcSgmtRefSeqNo(String MdfcSgmtRefSeqNo) {
		this.MdfcSgmtRefSeqNo = MdfcSgmtRefSeqNo;
	}


	public PbcrsReportAcctbssgmt getAcctBsSgmt() {
		return AcctBsSgmt;
	}


	public void setAcctBsSgmt(PbcrsReportAcctbssgmt acctBsSgmt) {
		AcctBsSgmt = acctBsSgmt;
	}


	public PbcrsReportAcctbsinfsgmt getAcctBsInfSgmt() {
		return AcctBsInfSgmt;
	}


	public void setAcctBsInfSgmt(PbcrsReportAcctbsinfsgmt acctBsInfSgmt) {
		AcctBsInfSgmt = acctBsInfSgmt;
	}


	public PbcrsReportMotcltctrinfsgmt getMotCltCtrInfSgmt() {
		return MotCltCtrInfSgmt;
	}


	public void setMotCltCtrInfSgmt(PbcrsReportMotcltctrinfsgmt motCltCtrInfSgmt) {
		MotCltCtrInfSgmt = motCltCtrInfSgmt;
	}


	public PbcrsReportAcctcredsgmt getAcctCredSgmt() {
		return AcctCredSgmt;
	}


	public void setAcctCredSgmt(PbcrsReportAcctcredsgmt acctCredSgmt) {
		AcctCredSgmt = acctCredSgmt;
	}


	public PbcrsReportAccmthblginfsgmt getAccMthBlgInfSgmt() {
		return AccMthBlgInfSgmt;
	}


	public void setAccMthBlgInfSgmt(PbcrsReportAccmthblginfsgmt accMthBlgInfSgmt) {
		AccMthBlgInfSgmt = accMthBlgInfSgmt;
	}


	public PbcrsReportSpecprdsgmt getSpecPrdSgmt() {
		return SpecPrdSgmt;
	}


	public void setSpecPrdSgmt(PbcrsReportSpecprdsgmt specPrdSgmt) {
		SpecPrdSgmt = specPrdSgmt;
	}


	public PbcrsReportAcctdbtinfsgmt getAcctDbtInfSgmt() {
		return AcctDbtInfSgmt;
	}


	public void setAcctDbtInfSgmt(PbcrsReportAcctdbtinfsgmt acctDbtInfSgmt) {
		AcctDbtInfSgmt = acctDbtInfSgmt;
	}


	public PbcrsReportAccspetrsdspsgmt getAccSpeTrsDspSgmt() {
		return AccSpeTrsDspSgmt;
	}


	public void setAccSpeTrsDspSgmt(PbcrsReportAccspetrsdspsgmt accSpeTrsDspSgmt) {
		AccSpeTrsDspSgmt = accSpeTrsDspSgmt;
	}


	
	
}