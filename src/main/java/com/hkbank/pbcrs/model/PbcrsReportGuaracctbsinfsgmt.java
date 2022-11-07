package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("GuarAcctBsInfSgmt")
public class PbcrsReportGuaracctbsinfsgmt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7318784375893998917L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.GUARACCTBSINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String GuarAcctBsInfSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.BUSILINES
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String BusiLines;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.BUSIDTILLINES
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String BusiDtilLines;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.OPENDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String OpenDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.ACCTCREDLINE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String AcctCredLine;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.CY
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String Cy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.DUEDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String DueDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.GUARMODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String GuarMode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.OTHREPYGUARWAY
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String OthRepyGuarWay;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.SECDEP
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String SecDep;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_GUARACCTBSINFSGMT.CTRCTTXTCD
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String CtrctTxtCd;
	/**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @mbggenerated Wed Apr 24 09:13:16 CST 2019
    */
	@XStreamOmitField
   private String reportflag;
	 /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @return the value of PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @mbggenerated Wed Apr 24 09:13:16 CST 2019
    */
   public String getReportflag() {
       return reportflag;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @param reportflag the value for PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @mbggenerated Wed Apr 24 09:13:16 CST 2019
    */
   public void setReportflag(String reportflag) {
       this.reportflag = reportflag;
   }
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.GUARACCTBSINFSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.GUARACCTBSINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getGuarAcctBsInfSgmtSeqNo() {
		return GuarAcctBsInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.GUARACCTBSINFSGMTSEQNO
	 * @param GuarAcctBsInfSgmtSeqNo  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.GUARACCTBSINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setGuarAcctBsInfSgmtSeqNo(String GuarAcctBsInfSgmtSeqNo) {
		this.GuarAcctBsInfSgmtSeqNo = GuarAcctBsInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.BUSILINES
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.BUSILINES
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getBusiLines() {
		return BusiLines;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.BUSILINES
	 * @param BusiLines  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.BUSILINES
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setBusiLines(String BusiLines) {
		this.BusiLines = BusiLines;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.BUSIDTILLINES
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.BUSIDTILLINES
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getBusiDtilLines() {
		return BusiDtilLines;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.BUSIDTILLINES
	 * @param BusiDtilLines  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.BUSIDTILLINES
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setBusiDtilLines(String BusiDtilLines) {
		this.BusiDtilLines = BusiDtilLines;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.OPENDATE
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.OPENDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getOpenDate() {
		return OpenDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.OPENDATE
	 * @param OpenDate  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.OPENDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setOpenDate(String OpenDate) {
		this.OpenDate = OpenDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.ACCTCREDLINE
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.ACCTCREDLINE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getAcctCredLine() {
		return AcctCredLine;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.ACCTCREDLINE
	 * @param AcctCredLine  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.ACCTCREDLINE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setAcctCredLine(String AcctCredLine) {
		this.AcctCredLine = AcctCredLine;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.CY
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.CY
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getCy() {
		return Cy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.CY
	 * @param Cy  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.CY
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setCy(String Cy) {
		this.Cy = Cy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.DUEDATE
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.DUEDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getDueDate() {
		return DueDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.DUEDATE
	 * @param DueDate  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.DUEDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setDueDate(String DueDate) {
		this.DueDate = DueDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.GUARMODE
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.GUARMODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getGuarMode() {
		return GuarMode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.GUARMODE
	 * @param GuarMode  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.GUARMODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setGuarMode(String GuarMode) {
		this.GuarMode = GuarMode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.OTHREPYGUARWAY
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.OTHREPYGUARWAY
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getOthRepyGuarWay() {
		return OthRepyGuarWay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.OTHREPYGUARWAY
	 * @param OthRepyGuarWay  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.OTHREPYGUARWAY
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setOthRepyGuarWay(String OthRepyGuarWay) {
		this.OthRepyGuarWay = OthRepyGuarWay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.SECDEP
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.SECDEP
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getSecDep() {
		return SecDep;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.SECDEP
	 * @param SecDep  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.SECDEP
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setSecDep(String SecDep) {
		this.SecDep = SecDep;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.CTRCTTXTCD
	 * @return  the value of PBCRS_REPORT_GUARACCTBSINFSGMT.CTRCTTXTCD
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getCtrctTxtCd() {
		return CtrctTxtCd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_GUARACCTBSINFSGMT.CTRCTTXTCD
	 * @param CtrctTxtCd  the value for PBCRS_REPORT_GUARACCTBSINFSGMT.CTRCTTXTCD
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setCtrctTxtCd(String CtrctTxtCd) {
		this.CtrctTxtCd = CtrctTxtCd;
	}
}