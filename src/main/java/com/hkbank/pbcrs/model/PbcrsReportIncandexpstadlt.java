package com.hkbank.pbcrs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
@XStreamAlias("IncomeAndExpenseStatementDlt")
public class PbcrsReportIncandexpstadlt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INCANDEXPSTADLT.INCANDEXPSTADLTSEQNO
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
	@XStreamOmitField
    private String IncAndExpStaDltSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INCANDEXPSTADLT.INFRECTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String InfRecType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INCANDEXPSTADLT.ENTNAME
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String EntName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INCANDEXPSTADLT.ENTCERTTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String EntCertType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INCANDEXPSTADLT.ENTCERTNUM
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String EntCertNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INCANDEXPSTADLT.SHEETYEAR
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String SheetYear;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INCANDEXPSTADLT.SHEETTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String SheetType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INCANDEXPSTADLT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String SheetTypeDivide;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INCANDEXPSTADLT.RPTDATE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    @XStreamOmitField
    private String RptDate;
    @XStreamOmitField
    private String  SourceSys;
    @XStreamOmitField
    private String ReportFlag;
    
    @XStreamOmitField
    private String orgCode;
    
    public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.INCANDEXPSTADLTSEQNO
     *
     * @return the value of PBCRS_REPORT_INCANDEXPSTADLT.INCANDEXPSTADLTSEQNO
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getIncAndExpStaDltSeqNo() {
        return IncAndExpStaDltSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.INCANDEXPSTADLTSEQNO
     *
     * @param IncAndExpStaDltSeqNo the value for PBCRS_REPORT_INCANDEXPSTADLT.INCANDEXPSTADLTSEQNO
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setIncAndExpStaDltSeqNo(String IncAndExpStaDltSeqNo) {
        this.IncAndExpStaDltSeqNo = IncAndExpStaDltSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.INFRECTYPE
     *
     * @return the value of PBCRS_REPORT_INCANDEXPSTADLT.INFRECTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getInfRecType() {
        return InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.INFRECTYPE
     *
     * @param InfRecType the value for PBCRS_REPORT_INCANDEXPSTADLT.INFRECTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setInfRecType(String InfRecType) {
        this.InfRecType = InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.ENTNAME
     *
     * @return the value of PBCRS_REPORT_INCANDEXPSTADLT.ENTNAME
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getEntName() {
        return EntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.ENTNAME
     *
     * @param EntName the value for PBCRS_REPORT_INCANDEXPSTADLT.ENTNAME
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setEntName(String EntName) {
        this.EntName = EntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.ENTCERTTYPE
     *
     * @return the value of PBCRS_REPORT_INCANDEXPSTADLT.ENTCERTTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getEntCertType() {
        return EntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.ENTCERTTYPE
     *
     * @param EntCertType the value for PBCRS_REPORT_INCANDEXPSTADLT.ENTCERTTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setEntCertType(String EntCertType) {
        this.EntCertType = EntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.ENTCERTNUM
     *
     * @return the value of PBCRS_REPORT_INCANDEXPSTADLT.ENTCERTNUM
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getEntCertNum() {
        return EntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.ENTCERTNUM
     *
     * @param EntCertNum the value for PBCRS_REPORT_INCANDEXPSTADLT.ENTCERTNUM
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setEntCertNum(String EntCertNum) {
        this.EntCertNum = EntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.SHEETYEAR
     *
     * @return the value of PBCRS_REPORT_INCANDEXPSTADLT.SHEETYEAR
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getSheetYear() {
        return SheetYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.SHEETYEAR
     *
     * @param SheetYear the value for PBCRS_REPORT_INCANDEXPSTADLT.SHEETYEAR
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setSheetYear(String SheetYear) {
        this.SheetYear = SheetYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.SHEETTYPE
     *
     * @return the value of PBCRS_REPORT_INCANDEXPSTADLT.SHEETTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getSheetType() {
        return SheetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.SHEETTYPE
     *
     * @param SheetType the value for PBCRS_REPORT_INCANDEXPSTADLT.SHEETTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setSheetType(String SheetType) {
        this.SheetType = SheetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.SHEETTYPEDIVIDE
     *
     * @return the value of PBCRS_REPORT_INCANDEXPSTADLT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getSheetTypeDivide() {
        return SheetTypeDivide;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.SHEETTYPEDIVIDE
     *
     * @param SheetTypeDivide the value for PBCRS_REPORT_INCANDEXPSTADLT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setSheetTypeDivide(String SheetTypeDivide) {
        this.SheetTypeDivide = SheetTypeDivide;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.RPTDATE
     *
     * @return the value of PBCRS_REPORT_INCANDEXPSTADLT.RPTDATE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getRptDate() {
        return RptDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INCANDEXPSTADLT.RPTDATE
     *
     * @param RptDate the value for PBCRS_REPORT_INCANDEXPSTADLT.RPTDATE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setRptDate(String RptDate) {
        this.RptDate = RptDate;
    }
}