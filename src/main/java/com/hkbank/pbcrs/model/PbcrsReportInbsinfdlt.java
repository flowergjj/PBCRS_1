package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("InBsInfDlt")
public class PbcrsReportInbsinfdlt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 938058881702025437L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INBSINFDLT.INBSINFDLTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InBsInfDltSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INBSINFDLT.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InfRecType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INBSINFDLT.NAME
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String Name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INBSINFDLT.IDTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String IDType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INBSINFDLT.IDNUM
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String IDNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INBSINFDLT.INFSURCCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InfSurcCode;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INBSINFDLT.INBSINFDLTSEQNO
	 * @return  the value of PBCRS_REPORT_INBSINFDLT.INBSINFDLTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	
	private String ReportFlag;
	private Date EtlDate;
	private String SourceSys;
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

	public String getReportFlag() {
		return ReportFlag;
	}

	public void setReportFlag(String reportFlag) {
		ReportFlag = reportFlag;
	}

	public String getInBsInfDltSeqNo() {
		return InBsInfDltSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INBSINFDLT.INBSINFDLTSEQNO
	 * @param InBsInfDltSeqNo  the value for PBCRS_REPORT_INBSINFDLT.INBSINFDLTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInBsInfDltSeqNo(String InBsInfDltSeqNo) {
		this.InBsInfDltSeqNo = InBsInfDltSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INBSINFDLT.INFRECTYPE
	 * @return  the value of PBCRS_REPORT_INBSINFDLT.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInfRecType() {
		return InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INBSINFDLT.INFRECTYPE
	 * @param InfRecType  the value for PBCRS_REPORT_INBSINFDLT.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInfRecType(String InfRecType) {
		this.InfRecType = InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INBSINFDLT.NAME
	 * @return  the value of PBCRS_REPORT_INBSINFDLT.NAME
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getName() {
		return Name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INBSINFDLT.NAME
	 * @param Name  the value for PBCRS_REPORT_INBSINFDLT.NAME
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setName(String Name) {
		this.Name = Name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INBSINFDLT.IDTYPE
	 * @return  the value of PBCRS_REPORT_INBSINFDLT.IDTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getIDType() {
		return IDType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INBSINFDLT.IDTYPE
	 * @param IDType  the value for PBCRS_REPORT_INBSINFDLT.IDTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setIDType(String IDType) {
		this.IDType = IDType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INBSINFDLT.IDNUM
	 * @return  the value of PBCRS_REPORT_INBSINFDLT.IDNUM
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getIDNum() {
		return IDNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INBSINFDLT.IDNUM
	 * @param IDNum  the value for PBCRS_REPORT_INBSINFDLT.IDNUM
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setIDNum(String IDNum) {
		this.IDNum = IDNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INBSINFDLT.INFSURCCODE
	 * @return  the value of PBCRS_REPORT_INBSINFDLT.INFSURCCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInfSurcCode() {
		return InfSurcCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INBSINFDLT.INFSURCCODE
	 * @param InfSurcCode  the value for PBCRS_REPORT_INBSINFDLT.INFSURCCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInfSurcCode(String InfSurcCode) {
		this.InfSurcCode = InfSurcCode;
	}
}