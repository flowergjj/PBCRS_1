package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
@XStreamAlias("GuarAcctBsInfSgmt")
public class PbcrsReportEnguaaccbsinfsgmt implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7064648299486747583L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARACCTBSINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
	@XStreamOmitField
    private String GuarAcctBsInfSgmtSeqNo;
	@XStreamOmitField
	private String ReportFlag;

	 
	public String getReportFlag() {
		return ReportFlag;
	}

	public void setReportFlag(String reportFlag) {
		ReportFlag = reportFlag;
	}

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.BUSILINES
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String BusiLines;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.BUSIDTILLINES
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String BusiDtilLines;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.OPENDATE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String OpenDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARAMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String GuarAmt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.CY
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String Cy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.DUEDATE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String DueDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARMODE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String GuarMode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.OTHREPYGUARWAY
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String OthRepyGuarWay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.SECDEP
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String SecDep;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.CTRCTTXTCODE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String CtrctTxtCode;
    
    
    @XStreamOmitField
	private String ParagraphDel;
	
	
	public String getParagraphDel() {
		return ParagraphDel;
	}

	public void setParagraphDel(String paragraphDel) {
		ParagraphDel = paragraphDel;
	}

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARACCTBSINFSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARACCTBSINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getGuarAcctBsInfSgmtSeqNo() {
        return GuarAcctBsInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARACCTBSINFSGMTSEQNO
     *
     * @param GuarAcctBsInfSgmtSeqNo the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARACCTBSINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setGuarAcctBsInfSgmtSeqNo(String GuarAcctBsInfSgmtSeqNo) {
        this.GuarAcctBsInfSgmtSeqNo = GuarAcctBsInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.BUSILINES
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.BUSILINES
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getBusiLines() {
        return BusiLines;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.BUSILINES
     *
     * @param BusiLines the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.BUSILINES
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setBusiLines(String BusiLines) {
        this.BusiLines = BusiLines;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.BUSIDTILLINES
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.BUSIDTILLINES
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getBusiDtilLines() {
        return BusiDtilLines;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.BUSIDTILLINES
     *
     * @param BusiDtilLines the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.BUSIDTILLINES
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setBusiDtilLines(String BusiDtilLines) {
        this.BusiDtilLines = BusiDtilLines;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.OPENDATE
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.OPENDATE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getOpenDate() {
        return OpenDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.OPENDATE
     *
     * @param OpenDate the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.OPENDATE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setOpenDate(String OpenDate) {
        this.OpenDate = OpenDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARAMT
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARAMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getGuarAmt() {
        return GuarAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARAMT
     *
     * @param GuarAmt the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARAMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setGuarAmt(String GuarAmt) {
        this.GuarAmt = GuarAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.CY
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.CY
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getCy() {
        return Cy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.CY
     *
     * @param Cy the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.CY
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setCy(String Cy) {
        this.Cy = Cy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.DUEDATE
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.DUEDATE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getDueDate() {
        return DueDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.DUEDATE
     *
     * @param DueDate the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.DUEDATE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setDueDate(String DueDate) {
        this.DueDate = DueDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARMODE
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARMODE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getGuarMode() {
        return GuarMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARMODE
     *
     * @param GuarMode the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.GUARMODE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setGuarMode(String GuarMode) {
        this.GuarMode = GuarMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.OTHREPYGUARWAY
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.OTHREPYGUARWAY
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getOthRepyGuarWay() {
        return OthRepyGuarWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.OTHREPYGUARWAY
     *
     * @param OthRepyGuarWay the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.OTHREPYGUARWAY
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setOthRepyGuarWay(String OthRepyGuarWay) {
        this.OthRepyGuarWay = OthRepyGuarWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.SECDEP
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.SECDEP
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getSecDep() {
        return SecDep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.SECDEP
     *
     * @param SecDep the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.SECDEP
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setSecDep(String SecDep) {
        this.SecDep = SecDep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.CTRCTTXTCODE
     *
     * @return the value of PBCRS_REPORT_ENGUAACCBSINFSGMT.CTRCTTXTCODE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getCtrctTxtCode() {
        return CtrctTxtCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENGUAACCBSINFSGMT.CTRCTTXTCODE
     *
     * @param CtrctTxtCode the value for PBCRS_REPORT_ENGUAACCBSINFSGMT.CTRCTTXTCODE
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setCtrctTxtCode(String CtrctTxtCode) {
        this.CtrctTxtCode = CtrctTxtCode;
    }
}