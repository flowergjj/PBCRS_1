package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("BsSgmt")
public class PbcrsReportInacctmdfcbssgmt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204346509553591433L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTMDFCBSSGMT.BSSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String BsSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTMDFCBSSGMT.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InfRecType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTMDFCBSSGMT.MODRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String ModRecCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTMDFCBSSGMT.RPTDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String RptDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTMDFCBSSGMT.ACCTTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String AcctType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTMDFCBSSGMT.MDFCSGMTCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String MdfcSgmtCode;
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
	@XStreamOmitField
	private Date EtlDate;
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

public Date getEtlDate() {
		return EtlDate;
	}

	public void setEtlDate(Date etlDate) {
		EtlDate = etlDate;
	}

	public String getSourceSys() {
		return SourceSys;
	}

	public void setSourceSys(String sourceSys) {
		SourceSys = sourceSys;
	}

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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.BSSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_INACCTMDFCBSSGMT.BSSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getBsSgmtSeqNo() {
		return BsSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.BSSGMTSEQNO
	 * @param BsSgmtSeqNo  the value for PBCRS_REPORT_INACCTMDFCBSSGMT.BSSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setBsSgmtSeqNo(String BsSgmtSeqNo) {
		this.BsSgmtSeqNo = BsSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.INFRECTYPE
	 * @return  the value of PBCRS_REPORT_INACCTMDFCBSSGMT.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInfRecType() {
		return InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.INFRECTYPE
	 * @param InfRecType  the value for PBCRS_REPORT_INACCTMDFCBSSGMT.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInfRecType(String InfRecType) {
		this.InfRecType = InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.MODRECCODE
	 * @return  the value of PBCRS_REPORT_INACCTMDFCBSSGMT.MODRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getModRecCode() {
		return ModRecCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.MODRECCODE
	 * @param ModRecCode  the value for PBCRS_REPORT_INACCTMDFCBSSGMT.MODRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setModRecCode(String ModRecCode) {
		this.ModRecCode = ModRecCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.RPTDATE
	 * @return  the value of PBCRS_REPORT_INACCTMDFCBSSGMT.RPTDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getRptDate() {
		return RptDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.RPTDATE
	 * @param RptDate  the value for PBCRS_REPORT_INACCTMDFCBSSGMT.RPTDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setRptDate(String RptDate) {
		this.RptDate = RptDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.ACCTTYPE
	 * @return  the value of PBCRS_REPORT_INACCTMDFCBSSGMT.ACCTTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getAcctType() {
		return AcctType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.ACCTTYPE
	 * @param AcctType  the value for PBCRS_REPORT_INACCTMDFCBSSGMT.ACCTTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setAcctType(String AcctType) {
		this.AcctType = AcctType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.MDFCSGMTCODE
	 * @return  the value of PBCRS_REPORT_INACCTMDFCBSSGMT.MDFCSGMTCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getMdfcSgmtCode() {
		return MdfcSgmtCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTMDFCBSSGMT.MDFCSGMTCODE
	 * @param MdfcSgmtCode  the value for PBCRS_REPORT_INACCTMDFCBSSGMT.MDFCSGMTCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setMdfcSgmtCode(String MdfcSgmtCode) {
		this.MdfcSgmtCode = MdfcSgmtCode;
	}
}