package com.hkbank.pbcrs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
@XStreamAlias("InstitutionBalanceSheetDlt")
public class PbcrsReportInsbalshedlt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSBALSHEDLT.INSBALSHEDLTSEQNO
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
	@XStreamOmitField
    private String InsBalSheDltSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSBALSHEDLT.INFRECTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String InfRecType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSBALSHEDLT.ENTNAME
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String EntName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSBALSHEDLT.ENTCERTTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String EntCertType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSBALSHEDLT.ENTCERTNUM
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String EntCertNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSBALSHEDLT.SHEETYEAR
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String SheetYear;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSBALSHEDLT.SHEETTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String SheetType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSBALSHEDLT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    private String SheetTypeDivide;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSBALSHEDLT.RPTDATE
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
     * This method returns the value of the database column PBCRS_REPORT_INSBALSHEDLT.INSBALSHEDLTSEQNO
     *
     * @return the value of PBCRS_REPORT_INSBALSHEDLT.INSBALSHEDLTSEQNO
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getInsBalSheDltSeqNo() {
        return InsBalSheDltSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSBALSHEDLT.INSBALSHEDLTSEQNO
     *
     * @param InsBalSheDltSeqNo the value for PBCRS_REPORT_INSBALSHEDLT.INSBALSHEDLTSEQNO
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setInsBalSheDltSeqNo(String InsBalSheDltSeqNo) {
        this.InsBalSheDltSeqNo = InsBalSheDltSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INSBALSHEDLT.INFRECTYPE
     *
     * @return the value of PBCRS_REPORT_INSBALSHEDLT.INFRECTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getInfRecType() {
        return InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSBALSHEDLT.INFRECTYPE
     *
     * @param InfRecType the value for PBCRS_REPORT_INSBALSHEDLT.INFRECTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setInfRecType(String InfRecType) {
        this.InfRecType = InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INSBALSHEDLT.ENTNAME
     *
     * @return the value of PBCRS_REPORT_INSBALSHEDLT.ENTNAME
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getEntName() {
        return EntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSBALSHEDLT.ENTNAME
     *
     * @param EntName the value for PBCRS_REPORT_INSBALSHEDLT.ENTNAME
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setEntName(String EntName) {
        this.EntName = EntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INSBALSHEDLT.ENTCERTTYPE
     *
     * @return the value of PBCRS_REPORT_INSBALSHEDLT.ENTCERTTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getEntCertType() {
        return EntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSBALSHEDLT.ENTCERTTYPE
     *
     * @param EntCertType the value for PBCRS_REPORT_INSBALSHEDLT.ENTCERTTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setEntCertType(String EntCertType) {
        this.EntCertType = EntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INSBALSHEDLT.ENTCERTNUM
     *
     * @return the value of PBCRS_REPORT_INSBALSHEDLT.ENTCERTNUM
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getEntCertNum() {
        return EntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSBALSHEDLT.ENTCERTNUM
     *
     * @param EntCertNum the value for PBCRS_REPORT_INSBALSHEDLT.ENTCERTNUM
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setEntCertNum(String EntCertNum) {
        this.EntCertNum = EntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INSBALSHEDLT.SHEETYEAR
     *
     * @return the value of PBCRS_REPORT_INSBALSHEDLT.SHEETYEAR
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getSheetYear() {
        return SheetYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSBALSHEDLT.SHEETYEAR
     *
     * @param SheetYear the value for PBCRS_REPORT_INSBALSHEDLT.SHEETYEAR
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setSheetYear(String SheetYear) {
        this.SheetYear = SheetYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INSBALSHEDLT.SHEETTYPE
     *
     * @return the value of PBCRS_REPORT_INSBALSHEDLT.SHEETTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getSheetType() {
        return SheetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSBALSHEDLT.SHEETTYPE
     *
     * @param SheetType the value for PBCRS_REPORT_INSBALSHEDLT.SHEETTYPE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setSheetType(String SheetType) {
        this.SheetType = SheetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INSBALSHEDLT.SHEETTYPEDIVIDE
     *
     * @return the value of PBCRS_REPORT_INSBALSHEDLT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getSheetTypeDivide() {
        return SheetTypeDivide;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSBALSHEDLT.SHEETTYPEDIVIDE
     *
     * @param SheetTypeDivide the value for PBCRS_REPORT_INSBALSHEDLT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setSheetTypeDivide(String SheetTypeDivide) {
        this.SheetTypeDivide = SheetTypeDivide;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INSBALSHEDLT.RPTDATE
     *
     * @return the value of PBCRS_REPORT_INSBALSHEDLT.RPTDATE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public String getRptDate() {
        return RptDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSBALSHEDLT.RPTDATE
     *
     * @param RptDate the value for PBCRS_REPORT_INSBALSHEDLT.RPTDATE
     *
     * @mbggenerated Tue Mar 26 14:16:53 CST 2019
     */
    public void setRptDate(String RptDate) {
        this.RptDate = RptDate;
    }
}