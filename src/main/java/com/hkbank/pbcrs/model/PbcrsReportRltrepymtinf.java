package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("RltRepymtInf")
public class PbcrsReportRltrepymtinf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -194241606395340869L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_RLTREPYMTINF.RLTREPYMTINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String RltRepymtInfSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_RLTREPYMTINF.RLTREPYMTINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String RltRepymtInfSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_RLTREPYMTINF.INFOIDTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InfoIDType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_RLTREPYMTINF.ARLPNAME
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String ArlpName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_RLTREPYMTINF.ARLPCERTTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String ArlpCertType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_RLTREPYMTINF.ARLPCERTNUM
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String ArlpCertNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_RLTREPYMTINF.ARLPTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String ArlpType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_RLTREPYMTINF.ARLPAMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String ArlpAmt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_RLTREPYMTINF.WARTYSIGN
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String WartySign;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_RLTREPYMTINF.MAXGUARMCC
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String MaxGuarMcc;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_RLTREPYMTINF.RLTREPYMTINFSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_RLTREPYMTINF.RLTREPYMTINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getRltRepymtInfSgmtSeqNo() {
		return RltRepymtInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_RLTREPYMTINF.RLTREPYMTINFSGMTSEQNO
	 * @param RltRepymtInfSgmtSeqNo  the value for PBCRS_REPORT_RLTREPYMTINF.RLTREPYMTINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setRltRepymtInfSgmtSeqNo(String RltRepymtInfSgmtSeqNo) {
		this.RltRepymtInfSgmtSeqNo = RltRepymtInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_RLTREPYMTINF.RLTREPYMTINFSEQNO
	 * @return  the value of PBCRS_REPORT_RLTREPYMTINF.RLTREPYMTINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getRltRepymtInfSeqNo() {
		return RltRepymtInfSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_RLTREPYMTINF.RLTREPYMTINFSEQNO
	 * @param RltRepymtInfSeqNo  the value for PBCRS_REPORT_RLTREPYMTINF.RLTREPYMTINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setRltRepymtInfSeqNo(String RltRepymtInfSeqNo) {
		this.RltRepymtInfSeqNo = RltRepymtInfSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_RLTREPYMTINF.INFOIDTYPE
	 * @return  the value of PBCRS_REPORT_RLTREPYMTINF.INFOIDTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInfoIDType() {
		return InfoIDType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_RLTREPYMTINF.INFOIDTYPE
	 * @param InfoIDType  the value for PBCRS_REPORT_RLTREPYMTINF.INFOIDTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInfoIDType(String InfoIDType) {
		this.InfoIDType = InfoIDType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_RLTREPYMTINF.ARLPNAME
	 * @return  the value of PBCRS_REPORT_RLTREPYMTINF.ARLPNAME
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getArlpName() {
		return ArlpName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_RLTREPYMTINF.ARLPNAME
	 * @param ArlpName  the value for PBCRS_REPORT_RLTREPYMTINF.ARLPNAME
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setArlpName(String ArlpName) {
		this.ArlpName = ArlpName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_RLTREPYMTINF.ARLPCERTTYPE
	 * @return  the value of PBCRS_REPORT_RLTREPYMTINF.ARLPCERTTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getArlpCertType() {
		return ArlpCertType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_RLTREPYMTINF.ARLPCERTTYPE
	 * @param ArlpCertType  the value for PBCRS_REPORT_RLTREPYMTINF.ARLPCERTTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setArlpCertType(String ArlpCertType) {
		this.ArlpCertType = ArlpCertType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_RLTREPYMTINF.ARLPCERTNUM
	 * @return  the value of PBCRS_REPORT_RLTREPYMTINF.ARLPCERTNUM
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getArlpCertNum() {
		return ArlpCertNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_RLTREPYMTINF.ARLPCERTNUM
	 * @param ArlpCertNum  the value for PBCRS_REPORT_RLTREPYMTINF.ARLPCERTNUM
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setArlpCertNum(String ArlpCertNum) {
		this.ArlpCertNum = ArlpCertNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_RLTREPYMTINF.ARLPTYPE
	 * @return  the value of PBCRS_REPORT_RLTREPYMTINF.ARLPTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getArlpType() {
		return ArlpType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_RLTREPYMTINF.ARLPTYPE
	 * @param ArlpType  the value for PBCRS_REPORT_RLTREPYMTINF.ARLPTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setArlpType(String ArlpType) {
		this.ArlpType = ArlpType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_RLTREPYMTINF.ARLPAMT
	 * @return  the value of PBCRS_REPORT_RLTREPYMTINF.ARLPAMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getArlpAmt() {
		return ArlpAmt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_RLTREPYMTINF.ARLPAMT
	 * @param ArlpAmt  the value for PBCRS_REPORT_RLTREPYMTINF.ARLPAMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setArlpAmt(String ArlpAmt) {
		this.ArlpAmt = ArlpAmt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_RLTREPYMTINF.WARTYSIGN
	 * @return  the value of PBCRS_REPORT_RLTREPYMTINF.WARTYSIGN
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getWartySign() {
		return WartySign;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_RLTREPYMTINF.WARTYSIGN
	 * @param WartySign  the value for PBCRS_REPORT_RLTREPYMTINF.WARTYSIGN
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setWartySign(String WartySign) {
		this.WartySign = WartySign;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_RLTREPYMTINF.MAXGUARMCC
	 * @return  the value of PBCRS_REPORT_RLTREPYMTINF.MAXGUARMCC
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getMaxGuarMcc() {
		return MaxGuarMcc;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_RLTREPYMTINF.MAXGUARMCC
	 * @param MaxGuarMcc  the value for PBCRS_REPORT_RLTREPYMTINF.MAXGUARMCC
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setMaxGuarMcc(String MaxGuarMcc) {
		this.MaxGuarMcc = MaxGuarMcc;
	}
}