package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("EnAcctInf")
public class PbcrsReportEnacctinfm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7131652615306850310L;

	/**
	 * 
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ENACCTINF.ENACCTINFSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	@XStreamOmitField
	private String EnAcctInfSeqNo;

	/**
	 * 
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ENACCTINF.ACCTBSSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	@XStreamOmitField
	private String AcctBsSgmtSeqNo;

	/**
	 * 
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ENACCTINF.ACCTBSINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	@XStreamOmitField
	private String AcctBsInfSgmtSeqNo;

	/**
	 * 
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ENACCTINF.RLTREPYMTINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	@XStreamOmitField
	private String RltRepymtInfSgmtSeqNo;

	/**
	 * 
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ENACCTINF.MOTGACLTALCTRCTINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	@XStreamOmitField
	private String MotgaCltalCtrctInfSgmtSeqNo;

	/**
	 * 
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ENACCTINF.ACCTCREDSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	@XStreamOmitField
	private String AcctCredSgmtSeqNo;

	/**
	 * 
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ENACCTINF.ORIGCREDITORINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	@XStreamOmitField
	private String OrigCreditorInfSgmtSeqNo;

	/**
	 * 
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ENACCTINF.ACTLBLTYINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	@XStreamOmitField
	private String ActLbltyInfSgmtSeqNo;

	/**
	 * 
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column PBCRS_REPORT_ENACCTINF.ACCTSPECTRSTDSPNSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	@XStreamOmitField
	private String AcctSpecTrstDspnSgmtSeqNo;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ENACCTINF.ENACCTINFSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_ENACCTINF.ENACCTINFSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	@XStreamAlias("AcctBsSgmt")
	private PbcrsReportEnacctbssgmt AcctBsSgmt;
	@XStreamAlias("AcctBsInfSgmt")
	private PbcrsReportEnacctbsinfsgmt AcctBsInfSgmt;
	@XStreamAlias("RltRepymtInfSgmt")
	private PbcrsReportEnrltrepinfsgmt RltRepInfSgmt;
	@XStreamAlias("MotgaCltalCtrctInfSgmt")
	private PbcrsReportEnmotcltctrinfsgm MotgaCltalCtrctInfSgmt;
	@XStreamAlias("AcctCredSgmt")
	private PbcrsReportEnacctcredsgmt AcctCredSgmt;
	@XStreamAlias("OrigCreditorInfSgmt")
	private PbcrsReportEnoricreinfsgmt OriCreInfSgmt;
	@XStreamAlias("ActLbltyInfSgmt")
	private PbcrsReportActlbltyinfsgmt ActLbltyInfSgmt;
	@XStreamAlias("AcctSpecTrstDspnSgmt")
	private PbcrsReportEnaccspetrssgmt AccSpeTrsSgmt;
	// private PbcrsRseportEnaccspetrssgmt AccSpeTrsSgmt;
	
	public PbcrsReportEnacctbssgmt getAcctBsSgmt() {
		return AcctBsSgmt;
	}

	public void setAcctBsSgmt(PbcrsReportEnacctbssgmt acctBsSgmt) {
		AcctBsSgmt = acctBsSgmt;
	}

	public PbcrsReportActlbltyinfsgmt getActLbltyInfSgmt() {
		return ActLbltyInfSgmt;
	}

	public void setActLbltyInfSgmt(PbcrsReportActlbltyinfsgmt actLbltyInfSgmt) {
		ActLbltyInfSgmt = actLbltyInfSgmt;
	}

	public String getEnAcctInfSeqNo() {
		return EnAcctInfSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ENACCTINF.ENACCTINFSEQNO
	 * 
	 * @param EnAcctInfSeqNo
	 *            the value for PBCRS_REPORT_ENACCTINF.ENACCTINFSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public void setEnAcctInfSeqNo(String EnAcctInfSeqNo) {
		this.EnAcctInfSeqNo = EnAcctInfSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ENACCTINF.ACCTBSSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_ENACCTINF.ACCTBSSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public String getAcctBsSgmtSeqNo() {
		return AcctBsSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ENACCTINF.ACCTBSSGMTSEQNO
	 * 
	 * @param AcctBsSgmtSeqNo
	 *            the value for PBCRS_REPORT_ENACCTINF.ACCTBSSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public void setAcctBsSgmtSeqNo(String AcctBsSgmtSeqNo) {
		this.AcctBsSgmtSeqNo = AcctBsSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ENACCTINF.ACCTBSINFSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_ENACCTINF.ACCTBSINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public String getAcctBsInfSgmtSeqNo() {
		return AcctBsInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ENACCTINF.ACCTBSINFSGMTSEQNO
	 * 
	 * @param AcctBsInfSgmtSeqNo
	 *            the value for PBCRS_REPORT_ENACCTINF.ACCTBSINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public void setAcctBsInfSgmtSeqNo(String AcctBsInfSgmtSeqNo) {
		this.AcctBsInfSgmtSeqNo = AcctBsInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ENACCTINF.RLTREPYMTINFSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_ENACCTINF.RLTREPYMTINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public String getRltRepymtInfSgmtSeqNo() {
		return RltRepymtInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ENACCTINF.RLTREPYMTINFSGMTSEQNO
	 * 
	 * @param RltRepymtInfSgmtSeqNo
	 *            the value for PBCRS_REPORT_ENACCTINF.RLTREPYMTINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public void setRltRepymtInfSgmtSeqNo(String RltRepymtInfSgmtSeqNo) {
		this.RltRepymtInfSgmtSeqNo = RltRepymtInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column
	 * PBCRS_REPORT_ENACCTINF.MOTGACLTALCTRCTINFSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_ENACCTINF.MOTGACLTALCTRCTINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public String getMotgaCltalCtrctInfSgmtSeqNo() {
		return MotgaCltalCtrctInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column
	 * PBCRS_REPORT_ENACCTINF.MOTGACLTALCTRCTINFSGMTSEQNO
	 * 
	 * @param MotgaCltalCtrctInfSgmtSeqNo
	 *            the value for
	 *            PBCRS_REPORT_ENACCTINF.MOTGACLTALCTRCTINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public void setMotgaCltalCtrctInfSgmtSeqNo(
			String MotgaCltalCtrctInfSgmtSeqNo) {
		this.MotgaCltalCtrctInfSgmtSeqNo = MotgaCltalCtrctInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ENACCTINF.ACCTCREDSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_ENACCTINF.ACCTCREDSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public String getAcctCredSgmtSeqNo() {
		return AcctCredSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ENACCTINF.ACCTCREDSGMTSEQNO
	 * 
	 * @param AcctCredSgmtSeqNo
	 *            the value for PBCRS_REPORT_ENACCTINF.ACCTCREDSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public void setAcctCredSgmtSeqNo(String AcctCredSgmtSeqNo) {
		this.AcctCredSgmtSeqNo = AcctCredSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column
	 * PBCRS_REPORT_ENACCTINF.ORIGCREDITORINFSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_ENACCTINF.ORIGCREDITORINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public String getOrigCreditorInfSgmtSeqNo() {
		return OrigCreditorInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column
	 * PBCRS_REPORT_ENACCTINF.ORIGCREDITORINFSGMTSEQNO
	 * 
	 * @param OrigCreditorInfSgmtSeqNo
	 *            the value for PBCRS_REPORT_ENACCTINF.ORIGCREDITORINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public void setOrigCreditorInfSgmtSeqNo(String OrigCreditorInfSgmtSeqNo) {
		this.OrigCreditorInfSgmtSeqNo = OrigCreditorInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column PBCRS_REPORT_ENACCTINF.ACTLBLTYINFSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_ENACCTINF.ACTLBLTYINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public String getActLbltyInfSgmtSeqNo() {
		return ActLbltyInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column PBCRS_REPORT_ENACCTINF.ACTLBLTYINFSGMTSEQNO
	 * 
	 * @param ActLbltyInfSgmtSeqNo
	 *            the value for PBCRS_REPORT_ENACCTINF.ACTLBLTYINFSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public void setActLbltyInfSgmtSeqNo(String ActLbltyInfSgmtSeqNo) {
		this.ActLbltyInfSgmtSeqNo = ActLbltyInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column
	 * PBCRS_REPORT_ENACCTINF.ACCTSPECTRSTDSPNSGMTSEQNO
	 * 
	 * @return the value of PBCRS_REPORT_ENACCTINF.ACCTSPECTRSTDSPNSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public String getAcctSpecTrstDspnSgmtSeqNo() {
		return AcctSpecTrstDspnSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column
	 * PBCRS_REPORT_ENACCTINF.ACCTSPECTRSTDSPNSGMTSEQNO
	 * 
	 * @param AcctSpecTrstDspnSgmtSeqNo
	 *            the value for PBCRS_REPORT_ENACCTINF.ACCTSPECTRSTDSPNSGMTSEQNO
	 * 
	 * @mbggenerated Wed Mar 13 14:37:40 CST 2019
	 */
	public void setAcctSpecTrstDspnSgmtSeqNo(String AcctSpecTrstDspnSgmtSeqNo) {
		this.AcctSpecTrstDspnSgmtSeqNo = AcctSpecTrstDspnSgmtSeqNo;
	}

	public PbcrsReportEnacctbsinfsgmt getAcctBsInfSgmt() {
		return AcctBsInfSgmt;
	}

	public void setAcctBsInfSgmt(PbcrsReportEnacctbsinfsgmt acctBsInfSgmt) {
		AcctBsInfSgmt = acctBsInfSgmt;
	}

	public PbcrsReportEnrltrepinfsgmt getRltRepInfSgmt() {
		return RltRepInfSgmt;
	}

	public void setRltRepInfSgmt(PbcrsReportEnrltrepinfsgmt rltRepInfSgmt) {
		RltRepInfSgmt = rltRepInfSgmt;
	}

	public PbcrsReportEnacctcredsgmt getAcctCredSgmt() {
		return AcctCredSgmt;
	}

	public void setAcctCredSgmt(PbcrsReportEnacctcredsgmt acctCredSgmt) {
		AcctCredSgmt = acctCredSgmt;
	}

	public PbcrsReportEnoricreinfsgmt getOriCreInfSgmt() {
		return OriCreInfSgmt;
	}

	public void setOriCreInfSgmt(PbcrsReportEnoricreinfsgmt oriCreInfSgmt) {
		OriCreInfSgmt = oriCreInfSgmt;
	}

	public PbcrsReportEnaccspetrssgmt getAccSpeTrsSgmt() {
		return AccSpeTrsSgmt;
	}

	public void setAccSpeTrsSgmt(PbcrsReportEnaccspetrssgmt accSpeTrsSgmt) {
		AccSpeTrsSgmt = accSpeTrsSgmt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PbcrsReportEnmotcltctrinfsgm getMotgaCltalCtrctInfSgmt() {
		return MotgaCltalCtrctInfSgmt;
	}

	public void setMotgaCltalCtrctInfSgmt(
			PbcrsReportEnmotcltctrinfsgm motgaCltalCtrctInfSgmt) {
		MotgaCltalCtrctInfSgmt = motgaCltalCtrctInfSgmt;
	}

	
	

}