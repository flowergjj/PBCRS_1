package com.hkbank.pbcrs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("BalanceSheetDlt")
public class PbcrsReportBalancesheetdlt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_BALANCESHEETDLT.BALANCESHEETDLTSEQNO
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
	@XStreamOmitField
    private String BalanceSheetDltSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_BALANCESHEETDLT.INFRECTYPE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    private String InfRecType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_BALANCESHEETDLT.ENTNAME
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    private String EntName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_BALANCESHEETDLT.ENTCERTTYPE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    private String EntCertType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_BALANCESHEETDLT.ENTCERTNUM
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    private String EntCertNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_BALANCESHEETDLT.SHEETYEAR
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    private String SheetYear;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_BALANCESHEETDLT.SHEETTYPE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    private String SheetType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_BALANCESHEETDLT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    private String SheetTypeDivide;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_BALANCESHEETDLT.BALANCESHEETDLTSEQNO
     *
     * @return the value of PBCRS_REPORT_BALANCESHEETDLT.BALANCESHEETDLTSEQNO
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
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

	public String getRptDate() {
		return RptDate;
	}

	public void setRptDate(String rptDate) {
		RptDate = rptDate;
	}

	public String getBalanceSheetDltSeqNo() {
        return BalanceSheetDltSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_BALANCESHEETDLT.BALANCESHEETDLTSEQNO
     *
     * @param BalanceSheetDltSeqNo the value for PBCRS_REPORT_BALANCESHEETDLT.BALANCESHEETDLTSEQNO
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public void setBalanceSheetDltSeqNo(String BalanceSheetDltSeqNo) {
        this.BalanceSheetDltSeqNo = BalanceSheetDltSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_BALANCESHEETDLT.INFRECTYPE
     *
     * @return the value of PBCRS_REPORT_BALANCESHEETDLT.INFRECTYPE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public String getInfRecType() {
        return InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_BALANCESHEETDLT.INFRECTYPE
     *
     * @param InfRecType the value for PBCRS_REPORT_BALANCESHEETDLT.INFRECTYPE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public void setInfRecType(String InfRecType) {
        this.InfRecType = InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_BALANCESHEETDLT.ENTNAME
     *
     * @return the value of PBCRS_REPORT_BALANCESHEETDLT.ENTNAME
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public String getEntName() {
        return EntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_BALANCESHEETDLT.ENTNAME
     *
     * @param EntName the value for PBCRS_REPORT_BALANCESHEETDLT.ENTNAME
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public void setEntName(String EntName) {
        this.EntName = EntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_BALANCESHEETDLT.ENTCERTTYPE
     *
     * @return the value of PBCRS_REPORT_BALANCESHEETDLT.ENTCERTTYPE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public String getEntCertType() {
        return EntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_BALANCESHEETDLT.ENTCERTTYPE
     *
     * @param EntCertType the value for PBCRS_REPORT_BALANCESHEETDLT.ENTCERTTYPE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public void setEntCertType(String EntCertType) {
        this.EntCertType = EntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_BALANCESHEETDLT.ENTCERTNUM
     *
     * @return the value of PBCRS_REPORT_BALANCESHEETDLT.ENTCERTNUM
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public String getEntCertNum() {
        return EntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_BALANCESHEETDLT.ENTCERTNUM
     *
     * @param EntCertNum the value for PBCRS_REPORT_BALANCESHEETDLT.ENTCERTNUM
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public void setEntCertNum(String EntCertNum) {
        this.EntCertNum = EntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_BALANCESHEETDLT.SHEETYEAR
     *
     * @return the value of PBCRS_REPORT_BALANCESHEETDLT.SHEETYEAR
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public String getSheetYear() {
        return SheetYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_BALANCESHEETDLT.SHEETYEAR
     *
     * @param SheetYear the value for PBCRS_REPORT_BALANCESHEETDLT.SHEETYEAR
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public void setSheetYear(String SheetYear) {
        this.SheetYear = SheetYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_BALANCESHEETDLT.SHEETTYPE
     *
     * @return the value of PBCRS_REPORT_BALANCESHEETDLT.SHEETTYPE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public String getSheetType() {
        return SheetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_BALANCESHEETDLT.SHEETTYPE
     *
     * @param SheetType the value for PBCRS_REPORT_BALANCESHEETDLT.SHEETTYPE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public void setSheetType(String SheetType) {
        this.SheetType = SheetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_BALANCESHEETDLT.SHEETTYPEDIVIDE
     *
     * @return the value of PBCRS_REPORT_BALANCESHEETDLT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public String getSheetTypeDivide() {
        return SheetTypeDivide;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_BALANCESHEETDLT.SHEETTYPEDIVIDE
     *
     * @param SheetTypeDivide the value for PBCRS_REPORT_BALANCESHEETDLT.SHEETTYPEDIVIDE
     *
     * @mbggenerated Mon Mar 18 11:08:29 CST 2019
     */
    public void setSheetTypeDivide(String SheetTypeDivide) {
        this.SheetTypeDivide = SheetTypeDivide;
    }
}