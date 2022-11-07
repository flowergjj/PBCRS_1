package com.hkbank.pbcrs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("CashFlowsBsSgmt")
public class PbcrsReportCashflowsbssgmt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.CASHFLOWSBSSGMTSEQNO
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
	@XStreamOmitField
    private String CashFlowsBsSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.INFRECTYPE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String InfRecType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.ENTNAME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String EntName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.ENTCERTTYPE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String EntCertType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.ENTCERTNUM
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String EntCertNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.RPTDATE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String RptDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETYEAR
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String SheetYear;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETTYPE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String SheetType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String SheetTypeDivide;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITFIRMNAME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String AuditFirmName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITORNAME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String AuditorName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITTIME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String AuditTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.CIMOC
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String Cimoc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_CASHFLOWSBSSGMT.RPTDATECODE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    private String RptDateCode;
    
    @XStreamOmitField
    private String ReportFlag;
	@XStreamOmitField
	private String IsDel;
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

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.CASHFLOWSBSSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.CASHFLOWSBSSGMTSEQNO
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getCashFlowsBsSgmtSeqNo() {
        return CashFlowsBsSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.CASHFLOWSBSSGMTSEQNO
     *
     * @param CashFlowsBsSgmtSeqNo the value for PBCRS_REPORT_CASHFLOWSBSSGMT.CASHFLOWSBSSGMTSEQNO
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setCashFlowsBsSgmtSeqNo(String CashFlowsBsSgmtSeqNo) {
        this.CashFlowsBsSgmtSeqNo = CashFlowsBsSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.INFRECTYPE
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.INFRECTYPE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getInfRecType() {
        return InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.INFRECTYPE
     *
     * @param InfRecType the value for PBCRS_REPORT_CASHFLOWSBSSGMT.INFRECTYPE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setInfRecType(String InfRecType) {
        this.InfRecType = InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.ENTNAME
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.ENTNAME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getEntName() {
        return EntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.ENTNAME
     *
     * @param EntName the value for PBCRS_REPORT_CASHFLOWSBSSGMT.ENTNAME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setEntName(String EntName) {
        this.EntName = EntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.ENTCERTTYPE
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.ENTCERTTYPE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getEntCertType() {
        return EntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.ENTCERTTYPE
     *
     * @param EntCertType the value for PBCRS_REPORT_CASHFLOWSBSSGMT.ENTCERTTYPE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setEntCertType(String EntCertType) {
        this.EntCertType = EntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.ENTCERTNUM
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.ENTCERTNUM
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getEntCertNum() {
        return EntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.ENTCERTNUM
     *
     * @param EntCertNum the value for PBCRS_REPORT_CASHFLOWSBSSGMT.ENTCERTNUM
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setEntCertNum(String EntCertNum) {
        this.EntCertNum = EntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.RPTDATE
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.RPTDATE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getRptDate() {
        return RptDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.RPTDATE
     *
     * @param RptDate the value for PBCRS_REPORT_CASHFLOWSBSSGMT.RPTDATE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setRptDate(String RptDate) {
        this.RptDate = RptDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETYEAR
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETYEAR
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getSheetYear() {
        return SheetYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETYEAR
     *
     * @param SheetYear the value for PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETYEAR
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setSheetYear(String SheetYear) {
        this.SheetYear = SheetYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETTYPE
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETTYPE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getSheetType() {
        return SheetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETTYPE
     *
     * @param SheetType the value for PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETTYPE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setSheetType(String SheetType) {
        this.SheetType = SheetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETTYPEDIVIDE
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getSheetTypeDivide() {
        return SheetTypeDivide;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETTYPEDIVIDE
     *
     * @param SheetTypeDivide the value for PBCRS_REPORT_CASHFLOWSBSSGMT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setSheetTypeDivide(String SheetTypeDivide) {
        this.SheetTypeDivide = SheetTypeDivide;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITFIRMNAME
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITFIRMNAME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getAuditFirmName() {
        return AuditFirmName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITFIRMNAME
     *
     * @param AuditFirmName the value for PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITFIRMNAME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setAuditFirmName(String AuditFirmName) {
        this.AuditFirmName = AuditFirmName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITORNAME
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITORNAME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getAuditorName() {
        return AuditorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITORNAME
     *
     * @param AuditorName the value for PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITORNAME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setAuditorName(String AuditorName) {
        this.AuditorName = AuditorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITTIME
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITTIME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getAuditTime() {
        return AuditTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITTIME
     *
     * @param AuditTime the value for PBCRS_REPORT_CASHFLOWSBSSGMT.AUDITTIME
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setAuditTime(String AuditTime) {
        this.AuditTime = AuditTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.CIMOC
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.CIMOC
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getCimoc() {
        return Cimoc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.CIMOC
     *
     * @param Cimoc the value for PBCRS_REPORT_CASHFLOWSBSSGMT.CIMOC
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setCimoc(String Cimoc) {
        this.Cimoc = Cimoc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.RPTDATECODE
     *
     * @return the value of PBCRS_REPORT_CASHFLOWSBSSGMT.RPTDATECODE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public String getRptDateCode() {
        return RptDateCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_CASHFLOWSBSSGMT.RPTDATECODE
     *
     * @param RptDateCode the value for PBCRS_REPORT_CASHFLOWSBSSGMT.RPTDATECODE
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    public void setRptDateCode(String RptDateCode) {
        this.RptDateCode = RptDateCode;
    }

	public String getReportFlag() {
		return ReportFlag;
	}

	public void setReportFlag(String reportFlag) {
		ReportFlag = reportFlag;
	}

	public String getIsDel() {
		return IsDel;
	}

	public void setIsDel(String isDel) {
		IsDel = isDel;
	}

	public String getSourceSys() {
		return SourceSys;
	}

	public void setSourceSys(String sourceSys) {
		SourceSys = sourceSys;
	}
    
    
}