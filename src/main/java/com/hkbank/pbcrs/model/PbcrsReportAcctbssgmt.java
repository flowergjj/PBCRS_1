package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("AcctBsSgmt")
public class PbcrsReportAcctbssgmt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4420903718734638311L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCTBSSGMT.ACCTBSSGMTSEQNO
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String AcctBsSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCTBSSGMT.INFRECTYPE
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String InfRecType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCTBSSGMT.ACCTTYPE
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String AcctType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCTBSSGMT.ACCTCODE
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String AcctCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCTBSSGMT.RPTDATE
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String RptDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCTBSSGMT.RPTDATECODE
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String RptDateCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCTBSSGMT.NAME
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String Name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCTBSSGMT.IDTYPE
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String IDType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCTBSSGMT.IDNUM
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String IDNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ACCTBSSGMT.MNGMTORGCODE
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	private String MngmtOrgCode;
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
	private String IsDel;
	@XStreamOmitField
	private String SourceSys;
	
	@XStreamOmitField
	private String orgCode;
	
	

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getIsDel() {
		return IsDel;
	}

	public void setIsDel(String isDel) {
		IsDel = isDel;
	}

	public String getSourceSys() {
		return SourceSys;
	}

	public void setSourceSys(String sourceSys) {
		SourceSys = sourceSys;
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
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.ACCTBSSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_ACCTBSSGMT.ACCTBSSGMTSEQNO
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getAcctBsSgmtSeqNo() {
		return AcctBsSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.ACCTBSSGMTSEQNO
	 * 
	 * @param AcctBsSgmtSeqNo
	 *            the value for PBCRS_REPORT_ACCTBSSGMT.ACCTBSSGMTSEQNO
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setAcctBsSgmtSeqNo(String AcctBsSgmtSeqNo) {
		this.AcctBsSgmtSeqNo = AcctBsSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.INFRECTYPE
	 * 
	 * @return the value of PBCRS_REPORT_ACCTBSSGMT.INFRECTYPE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInfRecType() {
		return InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.INFRECTYPE
	 * 
	 * @param InfRecType
	 *            the value for PBCRS_REPORT_ACCTBSSGMT.INFRECTYPE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInfRecType(String InfRecType) {
		this.InfRecType = InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.ACCTTYPE
	 * 
	 * @return the value of PBCRS_REPORT_ACCTBSSGMT.ACCTTYPE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getAcctType() {
		return AcctType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.ACCTTYPE
	 * 
	 * @param AcctType
	 *            the value for PBCRS_REPORT_ACCTBSSGMT.ACCTTYPE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setAcctType(String AcctType) {
		this.AcctType = AcctType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.ACCTCODE
	 * 
	 * @return the value of PBCRS_REPORT_ACCTBSSGMT.ACCTCODE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getAcctCode() {
		return AcctCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.ACCTCODE
	 * 
	 * @param AcctCode
	 *            the value for PBCRS_REPORT_ACCTBSSGMT.ACCTCODE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setAcctCode(String AcctCode) {
		this.AcctCode = AcctCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.RPTDATE
	 * 
	 * @return the value of PBCRS_REPORT_ACCTBSSGMT.RPTDATE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getRptDate() {
		return RptDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.RPTDATE
	 * 
	 * @param RptDate
	 *            the value for PBCRS_REPORT_ACCTBSSGMT.RPTDATE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setRptDate(String RptDate) {
		this.RptDate = RptDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.RPTDATECODE
	 * 
	 * @return the value of PBCRS_REPORT_ACCTBSSGMT.RPTDATECODE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getRptDateCode() {
		return RptDateCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.RPTDATECODE
	 * 
	 * @param RptDateCode
	 *            the value for PBCRS_REPORT_ACCTBSSGMT.RPTDATECODE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setRptDateCode(String RptDateCode) {
		this.RptDateCode = RptDateCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.NAME
	 * 
	 * @return the value of PBCRS_REPORT_ACCTBSSGMT.NAME
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getName() {
		return Name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.NAME
	 * 
	 * @param Name
	 *            the value for PBCRS_REPORT_ACCTBSSGMT.NAME
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setName(String Name) {
		this.Name = Name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.IDTYPE
	 * 
	 * @return the value of PBCRS_REPORT_ACCTBSSGMT.IDTYPE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getIDType() {
		return IDType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.IDTYPE
	 * 
	 * @param IDType
	 *            the value for PBCRS_REPORT_ACCTBSSGMT.IDTYPE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setIDType(String IDType) {
		this.IDType = IDType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.IDNUM
	 * 
	 * @return the value of PBCRS_REPORT_ACCTBSSGMT.IDNUM
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getIDNum() {
		return IDNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.IDNUM
	 * 
	 * @param IDNum
	 *            the value for PBCRS_REPORT_ACCTBSSGMT.IDNUM
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setIDNum(String IDNum) {
		this.IDNum = IDNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.MNGMTORGCODE
	 * 
	 * @return the value of PBCRS_REPORT_ACCTBSSGMT.MNGMTORGCODE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public String getMngmtOrgCode() {
		return MngmtOrgCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ACCTBSSGMT.MNGMTORGCODE
	 * 
	 * @param MngmtOrgCode
	 *            the value for PBCRS_REPORT_ACCTBSSGMT.MNGMTORGCODE
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	public void setMngmtOrgCode(String MngmtOrgCode) {
		this.MngmtOrgCode = MngmtOrgCode;
	}

	@Override
	public String toString() {
		return "PbcrsReportAcctbssgmt [InfRecType=" + InfRecType + ", AcctType=" + AcctType + ", RptDate=" + RptDate + ", RptDateCode=" + RptDateCode + ", Name=" + Name + ", IDType=" + IDType
				+ ", IDNum=" + IDNum + ", MngmtOrgCode=" + MngmtOrgCode + "]";
	}

}