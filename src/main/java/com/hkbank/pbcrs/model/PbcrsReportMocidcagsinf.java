package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("MoCIDCagsInf")
public class PbcrsReportMocidcagsinf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5573557393171314340L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_MOCIDCAGSINF.MOCIDCAGSINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String MoCIDCagsInfSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_MOCIDCAGSINF.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InfRecType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_MOCIDCAGSINF.ODBNESCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String OdBnesCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_MOCIDCAGSINF.NWBNESCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String NwBnesCode;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_MOCIDCAGSINF.MOCIDCAGSINFSEQNO
	 * @return  the value of PBCRS_REPORT_MOCIDCAGSINF.MOCIDCAGSINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getMoCIDCagsInfSeqNo() {
		return MoCIDCagsInfSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_MOCIDCAGSINF.MOCIDCAGSINFSEQNO
	 * @param MoCIDCagsInfSeqNo  the value for PBCRS_REPORT_MOCIDCAGSINF.MOCIDCAGSINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setMoCIDCagsInfSeqNo(String MoCIDCagsInfSeqNo) {
		this.MoCIDCagsInfSeqNo = MoCIDCagsInfSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_MOCIDCAGSINF.INFRECTYPE
	 * @return  the value of PBCRS_REPORT_MOCIDCAGSINF.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInfRecType() {
		return InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_MOCIDCAGSINF.INFRECTYPE
	 * @param InfRecType  the value for PBCRS_REPORT_MOCIDCAGSINF.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInfRecType(String InfRecType) {
		this.InfRecType = InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_MOCIDCAGSINF.ODBNESCODE
	 * @return  the value of PBCRS_REPORT_MOCIDCAGSINF.ODBNESCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getOdBnesCode() {
		return OdBnesCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_MOCIDCAGSINF.ODBNESCODE
	 * @param OdBnesCode  the value for PBCRS_REPORT_MOCIDCAGSINF.ODBNESCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setOdBnesCode(String OdBnesCode) {
		this.OdBnesCode = OdBnesCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_MOCIDCAGSINF.NWBNESCODE
	 * @return  the value of PBCRS_REPORT_MOCIDCAGSINF.NWBNESCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getNwBnesCode() {
		return NwBnesCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_MOCIDCAGSINF.NWBNESCODE
	 * @param NwBnesCode  the value for PBCRS_REPORT_MOCIDCAGSINF.NWBNESCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setNwBnesCode(String NwBnesCode) {
		this.NwBnesCode = NwBnesCode;
	}
}