package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("InSecAcctInf")
public class PbcrsReportInsecacctinf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 851017837726533966L;
	
	@XStreamAlias("GuarAcctBsSgmt")
	private PbcrsReportGuaracctbssgmt GuarAcctBsSgmt;
	
	@XStreamAlias("GuarAcctBsInfSgmt")
	private PbcrsReportGuaracctbsinfsgmt GuarAcctBsInfSgmt;
	
	@XStreamAlias("GuarRltRepymtInfSgmt")
	private PbcrsReportGuarltrepinfsgmt GuaRltRepInfSgmt;
	
	@XStreamAlias("RltRepymtInfSgmt")
	private PbcrsReportGsrltrepinfsgmt GsRltRepInfSgmt;
	
	@XStreamAlias("GuarMotgaCltalCtrctInfSgmt")
	private PbcrsReportGuamotcltctrsgmt GuaMotCltCtrSgmt;
	
	public PbcrsReportGuaracctbssgmt getGuarAcctBsSgmt() {
		return GuarAcctBsSgmt;
	}

	public void setGuarAcctBsSgmt(PbcrsReportGuaracctbssgmt guarAcctBsSgmt) {
		GuarAcctBsSgmt = guarAcctBsSgmt;
	}

	public PbcrsReportGuaracctbsinfsgmt getGuarAcctBsInfSgmt() {
		return GuarAcctBsInfSgmt;
	}

	public void setGuarAcctBsInfSgmt(PbcrsReportGuaracctbsinfsgmt guarAcctBsInfSgmt) {
		GuarAcctBsInfSgmt = guarAcctBsInfSgmt;
	}

	public PbcrsReportGuarltrepinfsgmt getGuaRltRepInfSgmt() {
		return GuaRltRepInfSgmt;
	}

	public void setGuaRltRepInfSgmt(PbcrsReportGuarltrepinfsgmt guaRltRepInfSgmt) {
		GuaRltRepInfSgmt = guaRltRepInfSgmt;
	}

	public PbcrsReportGsrltrepinfsgmt getGsRltRepInfSgmt() {
		return GsRltRepInfSgmt;
	}

	public void setGsRltRepInfSgmt(PbcrsReportGsrltrepinfsgmt gsRltRepInfSgmt) {
		GsRltRepInfSgmt = gsRltRepInfSgmt;
	}

	public PbcrsReportGuamotcltctrsgmt getGuaMotCltCtrSgmt() {
		return GuaMotCltCtrSgmt;
	}

	public void setGuaMotCltCtrSgmt(PbcrsReportGuamotcltctrsgmt guaMotCltCtrSgmt) {
		GuaMotCltCtrSgmt = guaMotCltCtrSgmt;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INSECACCTINF.INSECACCTINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InSecAcctInfSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INSECACCTINF.GUARACCTBSSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String GuarAcctBsSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INSECACCTINF.GUARACCTBSINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String GuarAcctBsInfSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INSECACCTINF.GUARRLTREPYMTINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String GuarRltRepymtInfSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INSECACCTINF.RLTREPYMTINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String RltRepymtInfSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INSECACCTINF.GUAMOTCLTCTRINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String GuaMotCltCtrInfSgmtSeqNo;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INSECACCTINF.INSECACCTINFSEQNO
	 * @return  the value of PBCRS_REPORT_INSECACCTINF.INSECACCTINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInSecAcctInfSeqNo() {
		return InSecAcctInfSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INSECACCTINF.INSECACCTINFSEQNO
	 * @param InSecAcctInfSeqNo  the value for PBCRS_REPORT_INSECACCTINF.INSECACCTINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInSecAcctInfSeqNo(String InSecAcctInfSeqNo) {
		this.InSecAcctInfSeqNo = InSecAcctInfSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INSECACCTINF.GUARACCTBSSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_INSECACCTINF.GUARACCTBSSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getGuarAcctBsSgmtSeqNo() {
		return GuarAcctBsSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INSECACCTINF.GUARACCTBSSGMTSEQNO
	 * @param GuarAcctBsSgmtSeqNo  the value for PBCRS_REPORT_INSECACCTINF.GUARACCTBSSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setGuarAcctBsSgmtSeqNo(String GuarAcctBsSgmtSeqNo) {
		this.GuarAcctBsSgmtSeqNo = GuarAcctBsSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INSECACCTINF.GUARACCTBSINFSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_INSECACCTINF.GUARACCTBSINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getGuarAcctBsInfSgmtSeqNo() {
		return GuarAcctBsInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INSECACCTINF.GUARACCTBSINFSGMTSEQNO
	 * @param GuarAcctBsInfSgmtSeqNo  the value for PBCRS_REPORT_INSECACCTINF.GUARACCTBSINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setGuarAcctBsInfSgmtSeqNo(String GuarAcctBsInfSgmtSeqNo) {
		this.GuarAcctBsInfSgmtSeqNo = GuarAcctBsInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INSECACCTINF.GUARRLTREPYMTINFSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_INSECACCTINF.GUARRLTREPYMTINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getGuarRltRepymtInfSgmtSeqNo() {
		return GuarRltRepymtInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INSECACCTINF.GUARRLTREPYMTINFSGMTSEQNO
	 * @param GuarRltRepymtInfSgmtSeqNo  the value for PBCRS_REPORT_INSECACCTINF.GUARRLTREPYMTINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setGuarRltRepymtInfSgmtSeqNo(String GuarRltRepymtInfSgmtSeqNo) {
		this.GuarRltRepymtInfSgmtSeqNo = GuarRltRepymtInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INSECACCTINF.RLTREPYMTINFSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_INSECACCTINF.RLTREPYMTINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getRltRepymtInfSgmtSeqNo() {
		return RltRepymtInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INSECACCTINF.RLTREPYMTINFSGMTSEQNO
	 * @param RltRepymtInfSgmtSeqNo  the value for PBCRS_REPORT_INSECACCTINF.RLTREPYMTINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setRltRepymtInfSgmtSeqNo(String RltRepymtInfSgmtSeqNo) {
		this.RltRepymtInfSgmtSeqNo = RltRepymtInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INSECACCTINF.GUAMOTCLTCTRINFSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_INSECACCTINF.GUAMOTCLTCTRINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getGuaMotCltCtrInfSgmtSeqNo() {
		return GuaMotCltCtrInfSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INSECACCTINF.GUAMOTCLTCTRINFSGMTSEQNO
	 * @param GuaMotCltCtrInfSgmtSeqNo  the value for PBCRS_REPORT_INSECACCTINF.GUAMOTCLTCTRINFSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setGuaMotCltCtrInfSgmtSeqNo(String GuaMotCltCtrInfSgmtSeqNo) {
		this.GuaMotCltCtrInfSgmtSeqNo = GuaMotCltCtrInfSgmtSeqNo;
	}
}