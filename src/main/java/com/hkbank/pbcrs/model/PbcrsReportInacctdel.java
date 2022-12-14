package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("InAcctDel")
public class PbcrsReportInacctdel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1796960047499129799L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTDEL.INACCTDELSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InAcctDelSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTDEL.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InfRecType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTDEL.DELRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String DelRecCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTDEL.DELSGMTCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String DelSgmtCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTDEL.DELSTARTDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String DelStartDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTDEL.DELENDDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String DelEndDate;
	
	private String ReportFlag;
	
	private Date Etl_Date;
	
	private String SourceSys;
	
	private String orgCode;
	

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getReportFlag() {
		return ReportFlag;
	}

	public void setReportFlag(String reportFlag) {
		ReportFlag = reportFlag;
	}

	public Date getEtl_Date() {
		return Etl_Date;
	}

	public void setEtl_Date(Date etl_Date) {
		Etl_Date = etl_Date;
	}

	public String getSourceSys() {
		return SourceSys;
	}

	public void setSourceSys(String sourceSys) {
		SourceSys = sourceSys;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTDEL.INACCTDELSEQNO
	 * @return  the value of PBCRS_REPORT_INACCTDEL.INACCTDELSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInAcctDelSeqNo() {
		return InAcctDelSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTDEL.INACCTDELSEQNO
	 * @param InAcctDelSeqNo  the value for PBCRS_REPORT_INACCTDEL.INACCTDELSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInAcctDelSeqNo(String InAcctDelSeqNo) {
		this.InAcctDelSeqNo = InAcctDelSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTDEL.INFRECTYPE
	 * @return  the value of PBCRS_REPORT_INACCTDEL.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInfRecType() {
		return InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTDEL.INFRECTYPE
	 * @param InfRecType  the value for PBCRS_REPORT_INACCTDEL.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInfRecType(String InfRecType) {
		this.InfRecType = InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTDEL.DELRECCODE
	 * @return  the value of PBCRS_REPORT_INACCTDEL.DELRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getDelRecCode() {
		return DelRecCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTDEL.DELRECCODE
	 * @param DelRecCode  the value for PBCRS_REPORT_INACCTDEL.DELRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setDelRecCode(String DelRecCode) {
		this.DelRecCode = DelRecCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTDEL.DELSGMTCODE
	 * @return  the value of PBCRS_REPORT_INACCTDEL.DELSGMTCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getDelSgmtCode() {
		return DelSgmtCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTDEL.DELSGMTCODE
	 * @param DelSgmtCode  the value for PBCRS_REPORT_INACCTDEL.DELSGMTCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setDelSgmtCode(String DelSgmtCode) {
		this.DelSgmtCode = DelSgmtCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTDEL.DELSTARTDATE
	 * @return  the value of PBCRS_REPORT_INACCTDEL.DELSTARTDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getDelStartDate() {
		return DelStartDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTDEL.DELSTARTDATE
	 * @param DelStartDate  the value for PBCRS_REPORT_INACCTDEL.DELSTARTDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setDelStartDate(String DelStartDate) {
		this.DelStartDate = DelStartDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTDEL.DELENDDATE
	 * @return  the value of PBCRS_REPORT_INACCTDEL.DELENDDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getDelEndDate() {
		return DelEndDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTDEL.DELENDDATE
	 * @param DelEndDate  the value for PBCRS_REPORT_INACCTDEL.DELENDDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setDelEndDate(String DelEndDate) {
		this.DelEndDate = DelEndDate;
	}
}