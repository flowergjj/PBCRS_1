package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("InAcctEntDel")
public class PbcrsReportInacctentdel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2755937633158717477L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTENTDEL.INACCTENTDELSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InAcctEntDelSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTENTDEL.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InfRecType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INACCTENTDEL.DELRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String DelRecCode;
	@XStreamOmitField
	private String ReportFlag;
	@XStreamOmitField
	private Date EtlDate;
	@XStreamOmitField
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

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTENTDEL.INACCTENTDELSEQNO
	 * @return  the value of PBCRS_REPORT_INACCTENTDEL.INACCTENTDELSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInAcctEntDelSeqNo() {
		return InAcctEntDelSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTENTDEL.INACCTENTDELSEQNO
	 * @param InAcctEntDelSeqNo  the value for PBCRS_REPORT_INACCTENTDEL.INACCTENTDELSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInAcctEntDelSeqNo(String InAcctEntDelSeqNo) {
		this.InAcctEntDelSeqNo = InAcctEntDelSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTENTDEL.INFRECTYPE
	 * @return  the value of PBCRS_REPORT_INACCTENTDEL.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInfRecType() {
		return InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTENTDEL.INFRECTYPE
	 * @param InfRecType  the value for PBCRS_REPORT_INACCTENTDEL.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInfRecType(String InfRecType) {
		this.InfRecType = InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INACCTENTDEL.DELRECCODE
	 * @return  the value of PBCRS_REPORT_INACCTENTDEL.DELRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getDelRecCode() {
		return DelRecCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INACCTENTDEL.DELRECCODE
	 * @param DelRecCode  the value for PBCRS_REPORT_INACCTENTDEL.DELRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setDelRecCode(String DelRecCode) {
		this.DelRecCode = DelRecCode;
	}
}