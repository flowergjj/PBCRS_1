package com.hkbank.pbcrs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("AcctBsInfSgmt")
public class PbcrsReportEnacctbsinfsgmt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.ACCTBSINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
	@XStreamOmitField
    private String AcctBsInfSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.BUSILINES
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String BusiLines;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.BUSIDTLLINES
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String BusiDtlLines;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.OPENDATE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String OpenDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.CY
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String Cy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.ACCTCREDLINE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String AcctCredLine;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.LOANAMT
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String LoanAmt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.FLAG
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String Flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.DUEDATE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String DueDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.REPAYMODE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String RepayMode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.REPAYFREQCY
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String RepayFreqcy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.APPLYBUSIDIST
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String ApplyBusiDist;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.GUARMODE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String GuarMode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.OTHREPYGUARWAY
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String OthRepyGuarWay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.LOANTIMELIMCAT
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String LoanTimeLimCat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.LOAFRM
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String LoaFrm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.ACTINVEST
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String ActInvest;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.FUNDSOU
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    private String FundSou;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCTBSINFSGMT.ASSETTRANDFLAG
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    @XStreamAlias("AssetTrandFlag")
    private String AssetTrandFlag;

    
    @XStreamOmitField
	private String ReportFlag;
    
    @XStreamOmitField
	private String ParagraphDel;
	

	public String getParagraphDel() {
		return ParagraphDel;
	}

	public void setParagraphDel(String paragraphDel) {
		ParagraphDel = paragraphDel;
	}
	    
	    
	public String getReportFlag() {
		return ReportFlag;
	}

	public void setReportFlag(String reportFlag) {
		ReportFlag = reportFlag;
	}
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.ACCTBSINFSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.ACCTBSINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getAcctBsInfSgmtSeqNo() {
        return AcctBsInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.ACCTBSINFSGMTSEQNO
     *
     * @param AcctBsInfSgmtSeqNo the value for PBCRS_REPORT_ENACCTBSINFSGMT.ACCTBSINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setAcctBsInfSgmtSeqNo(String AcctBsInfSgmtSeqNo) {
        this.AcctBsInfSgmtSeqNo = AcctBsInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.BUSILINES
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.BUSILINES
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getBusiLines() {
        return BusiLines;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.BUSILINES
     *
     * @param BusiLines the value for PBCRS_REPORT_ENACCTBSINFSGMT.BUSILINES
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setBusiLines(String BusiLines) {
        this.BusiLines = BusiLines;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.BUSIDTLLINES
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.BUSIDTLLINES
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getBusiDtlLines() {
        return BusiDtlLines;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.BUSIDTLLINES
     *
     * @param BusiDtlLines the value for PBCRS_REPORT_ENACCTBSINFSGMT.BUSIDTLLINES
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setBusiDtlLines(String BusiDtlLines) {
        this.BusiDtlLines = BusiDtlLines;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.OPENDATE
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.OPENDATE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getOpenDate() {
        return OpenDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.OPENDATE
     *
     * @param OpenDate the value for PBCRS_REPORT_ENACCTBSINFSGMT.OPENDATE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setOpenDate(String OpenDate) {
        this.OpenDate = OpenDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.CY
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.CY
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getCy() {
        return Cy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.CY
     *
     * @param Cy the value for PBCRS_REPORT_ENACCTBSINFSGMT.CY
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setCy(String Cy) {
        this.Cy = Cy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.ACCTCREDLINE
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.ACCTCREDLINE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getAcctCredLine() {
        return AcctCredLine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.ACCTCREDLINE
     *
     * @param AcctCredLine the value for PBCRS_REPORT_ENACCTBSINFSGMT.ACCTCREDLINE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setAcctCredLine(String AcctCredLine) {
        this.AcctCredLine = AcctCredLine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.LOANAMT
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.LOANAMT
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getLoanAmt() {
        return LoanAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.LOANAMT
     *
     * @param LoanAmt the value for PBCRS_REPORT_ENACCTBSINFSGMT.LOANAMT
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setLoanAmt(String LoanAmt) {
        this.LoanAmt = LoanAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.FLAG
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.FLAG
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getFlag() {
        return Flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.FLAG
     *
     * @param Flag the value for PBCRS_REPORT_ENACCTBSINFSGMT.FLAG
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setFlag(String Flag) {
        this.Flag = Flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.DUEDATE
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.DUEDATE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getDueDate() {
        return DueDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.DUEDATE
     *
     * @param DueDate the value for PBCRS_REPORT_ENACCTBSINFSGMT.DUEDATE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setDueDate(String DueDate) {
        this.DueDate = DueDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.REPAYMODE
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.REPAYMODE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getRepayMode() {
        return RepayMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.REPAYMODE
     *
     * @param RepayMode the value for PBCRS_REPORT_ENACCTBSINFSGMT.REPAYMODE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setRepayMode(String RepayMode) {
        this.RepayMode = RepayMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.REPAYFREQCY
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.REPAYFREQCY
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getRepayFreqcy() {
        return RepayFreqcy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.REPAYFREQCY
     *
     * @param RepayFreqcy the value for PBCRS_REPORT_ENACCTBSINFSGMT.REPAYFREQCY
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setRepayFreqcy(String RepayFreqcy) {
        this.RepayFreqcy = RepayFreqcy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.APPLYBUSIDIST
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.APPLYBUSIDIST
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getApplyBusiDist() {
        return ApplyBusiDist;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.APPLYBUSIDIST
     *
     * @param ApplyBusiDist the value for PBCRS_REPORT_ENACCTBSINFSGMT.APPLYBUSIDIST
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setApplyBusiDist(String ApplyBusiDist) {
        this.ApplyBusiDist = ApplyBusiDist;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.GUARMODE
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.GUARMODE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getGuarMode() {
        return GuarMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.GUARMODE
     *
     * @param GuarMode the value for PBCRS_REPORT_ENACCTBSINFSGMT.GUARMODE
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setGuarMode(String GuarMode) {
        this.GuarMode = GuarMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.OTHREPYGUARWAY
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.OTHREPYGUARWAY
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getOthRepyGuarWay() {
        return OthRepyGuarWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.OTHREPYGUARWAY
     *
     * @param OthRepyGuarWay the value for PBCRS_REPORT_ENACCTBSINFSGMT.OTHREPYGUARWAY
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setOthRepyGuarWay(String OthRepyGuarWay) {
        this.OthRepyGuarWay = OthRepyGuarWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.LOANTIMELIMCAT
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.LOANTIMELIMCAT
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getLoanTimeLimCat() {
        return LoanTimeLimCat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.LOANTIMELIMCAT
     *
     * @param LoanTimeLimCat the value for PBCRS_REPORT_ENACCTBSINFSGMT.LOANTIMELIMCAT
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setLoanTimeLimCat(String LoanTimeLimCat) {
        this.LoanTimeLimCat = LoanTimeLimCat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.LOAFRM
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.LOAFRM
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getLoaFrm() {
        return LoaFrm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.LOAFRM
     *
     * @param LoaFrm the value for PBCRS_REPORT_ENACCTBSINFSGMT.LOAFRM
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setLoaFrm(String LoaFrm) {
        this.LoaFrm = LoaFrm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.ACTINVEST
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.ACTINVEST
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getActInvest() {
        return ActInvest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.ACTINVEST
     *
     * @param ActInvest the value for PBCRS_REPORT_ENACCTBSINFSGMT.ACTINVEST
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setActInvest(String ActInvest) {
        this.ActInvest = ActInvest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.FUNDSOU
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.FUNDSOU
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getFundSou() {
        return FundSou;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.FUNDSOU
     *
     * @param FundSou the value for PBCRS_REPORT_ENACCTBSINFSGMT.FUNDSOU
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setFundSou(String FundSou) {
        this.FundSou = FundSou;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.ASSETTRANDFLAG
     *
     * @return the value of PBCRS_REPORT_ENACCTBSINFSGMT.ASSETTRANDFLAG
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public String getAssetTrandFlag() {
        return AssetTrandFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCTBSINFSGMT.ASSETTRANDFLAG
     *
     * @param AssetTrandFlag the value for PBCRS_REPORT_ENACCTBSINFSGMT.ASSETTRANDFLAG
     *
     * @mbggenerated Thu Mar 14 10:19:55 CST 2019
     */
    public void setAssetTrandFlag(String AssetTrandFlag) {
        this.AssetTrandFlag = AssetTrandFlag;
    }
}