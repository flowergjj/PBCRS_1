package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("FcsInfSgmt")
public class PbcrsReportFcsinfsgmt implements Serializable {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_FCSINFSGMT.FCSINFSGMTSEQNO
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamOmitField
	private String FcsInfSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_FCSINFSGMT.SEX
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamAlias("Sex")
	private String Sex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_FCSINFSGMT.DOB
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamAlias("DOB")
	private String DOB;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_FCSINFSGMT.NATION
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamAlias("Nation")
	private String Nation;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_FCSINFSGMT.HOUSEADD
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamAlias("HouseAdd")
	private String HouseAdd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_FCSINFSGMT.HHDIST
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamAlias("HhDist")
	private String HhDist;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_FCSINFSGMT.CELLPHONE
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamAlias("CellPhone")
	private String CellPhone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_FCSINFSGMT.EMAIL
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamAlias("Email")
	private String Email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_FCSINFSGMT.FCSINFOUPDATE
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamAlias("FcsInfoUpDate")
	private String FcsInfoUpDate;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_FCSINFSGMT.FCSINFSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_FCSINFSGMT.FCSINFSGMTSEQNO
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getFcsInfSgmtSeqNo() {
		return FcsInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_FCSINFSGMT.FCSINFSGMTSEQNO
	 * @param FcsInfSgmtSeqNo  the value for PBCRS_REPORT_FCSINFSGMT.FCSINFSGMTSEQNO
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setFcsInfSgmtSeqNo(String FcsInfSgmtSeqNo) {
		this.FcsInfSgmtSeqNo = FcsInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_FCSINFSGMT.SEX
	 * @return  the value of PBCRS_REPORT_FCSINFSGMT.SEX
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getSex() {
		return Sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_FCSINFSGMT.SEX
	 * @param Sex  the value for PBCRS_REPORT_FCSINFSGMT.SEX
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setSex(String Sex) {
		this.Sex = Sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_FCSINFSGMT.DOB
	 * @return  the value of PBCRS_REPORT_FCSINFSGMT.DOB
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getDOB() {
		return DOB;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_FCSINFSGMT.DOB
	 * @param DOB  the value for PBCRS_REPORT_FCSINFSGMT.DOB
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_FCSINFSGMT.NATION
	 * @return  the value of PBCRS_REPORT_FCSINFSGMT.NATION
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getNation() {
		return Nation;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_FCSINFSGMT.NATION
	 * @param Nation  the value for PBCRS_REPORT_FCSINFSGMT.NATION
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setNation(String Nation) {
		this.Nation = Nation;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_FCSINFSGMT.HOUSEADD
	 * @return  the value of PBCRS_REPORT_FCSINFSGMT.HOUSEADD
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getHouseAdd() {
		return HouseAdd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_FCSINFSGMT.HOUSEADD
	 * @param HouseAdd  the value for PBCRS_REPORT_FCSINFSGMT.HOUSEADD
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setHouseAdd(String HouseAdd) {
		this.HouseAdd = HouseAdd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_FCSINFSGMT.HHDIST
	 * @return  the value of PBCRS_REPORT_FCSINFSGMT.HHDIST
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getHhDist() {
		return HhDist;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_FCSINFSGMT.HHDIST
	 * @param HhDist  the value for PBCRS_REPORT_FCSINFSGMT.HHDIST
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setHhDist(String HhDist) {
		this.HhDist = HhDist;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_FCSINFSGMT.CELLPHONE
	 * @return  the value of PBCRS_REPORT_FCSINFSGMT.CELLPHONE
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getCellPhone() {
		return CellPhone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_FCSINFSGMT.CELLPHONE
	 * @param CellPhone  the value for PBCRS_REPORT_FCSINFSGMT.CELLPHONE
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setCellPhone(String CellPhone) {
		this.CellPhone = CellPhone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_FCSINFSGMT.EMAIL
	 * @return  the value of PBCRS_REPORT_FCSINFSGMT.EMAIL
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_FCSINFSGMT.EMAIL
	 * @param Email  the value for PBCRS_REPORT_FCSINFSGMT.EMAIL
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setEmail(String Email) {
		this.Email = Email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_FCSINFSGMT.FCSINFOUPDATE
	 * @return  the value of PBCRS_REPORT_FCSINFSGMT.FCSINFOUPDATE
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getFcsInfoUpDate() {
		return FcsInfoUpDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_FCSINFSGMT.FCSINFOUPDATE
	 * @param FcsInfoUpDate  the value for PBCRS_REPORT_FCSINFSGMT.FCSINFOUPDATE
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setFcsInfoUpDate(String FcsInfoUpDate) {
		this.FcsInfoUpDate = FcsInfoUpDate;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5375940328925058910L;
}