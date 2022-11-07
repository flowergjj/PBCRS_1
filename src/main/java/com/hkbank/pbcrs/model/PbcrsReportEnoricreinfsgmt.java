package com.hkbank.pbcrs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("OrigCreditorInfSgmt")
public class PbcrsReportEnoricreinfsgmt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENORICREINFSGMT.ORIGCREDITORINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
	@XStreamOmitField
    private String OrigCreditorInfSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENORICREINFSGMT.INITCREDNAME
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    private String InitCredName;
    
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
     * This field corresponds to the database column PBCRS_REPORT_ENORICREINFSGMT.INITCEDORGCODE
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    private String InitCedOrgCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENORICREINFSGMT.ORIGDBTCATE
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    private String OrigDbtCate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENORICREINFSGMT.INITRPYSTS
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    private String InitRpySts;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENORICREINFSGMT.ORIGCREDITORINFSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENORICREINFSGMT.ORIGCREDITORINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    public String getOrigCreditorInfSgmtSeqNo() {
        return OrigCreditorInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENORICREINFSGMT.ORIGCREDITORINFSGMTSEQNO
     *
     * @param OrigCreditorInfSgmtSeqNo the value for PBCRS_REPORT_ENORICREINFSGMT.ORIGCREDITORINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    public void setOrigCreditorInfSgmtSeqNo(String OrigCreditorInfSgmtSeqNo) {
        this.OrigCreditorInfSgmtSeqNo = OrigCreditorInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENORICREINFSGMT.INITCREDNAME
     *
     * @return the value of PBCRS_REPORT_ENORICREINFSGMT.INITCREDNAME
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    public String getInitCredName() {
        return InitCredName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENORICREINFSGMT.INITCREDNAME
     *
     * @param InitCredName the value for PBCRS_REPORT_ENORICREINFSGMT.INITCREDNAME
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    public void setInitCredName(String InitCredName) {
        this.InitCredName = InitCredName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENORICREINFSGMT.INITCEDORGCODE
     *
     * @return the value of PBCRS_REPORT_ENORICREINFSGMT.INITCEDORGCODE
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    public String getInitCedOrgCode() {
        return InitCedOrgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENORICREINFSGMT.INITCEDORGCODE
     *
     * @param InitCedOrgCode the value for PBCRS_REPORT_ENORICREINFSGMT.INITCEDORGCODE
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    public void setInitCedOrgCode(String InitCedOrgCode) {
        this.InitCedOrgCode = InitCedOrgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENORICREINFSGMT.ORIGDBTCATE
     *
     * @return the value of PBCRS_REPORT_ENORICREINFSGMT.ORIGDBTCATE
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    public String getOrigDbtCate() {
        return OrigDbtCate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENORICREINFSGMT.ORIGDBTCATE
     *
     * @param OrigDbtCate the value for PBCRS_REPORT_ENORICREINFSGMT.ORIGDBTCATE
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    public void setOrigDbtCate(String OrigDbtCate) {
        this.OrigDbtCate = OrigDbtCate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENORICREINFSGMT.INITRPYSTS
     *
     * @return the value of PBCRS_REPORT_ENORICREINFSGMT.INITRPYSTS
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    public String getInitRpySts() {
        return InitRpySts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENORICREINFSGMT.INITRPYSTS
     *
     * @param InitRpySts the value for PBCRS_REPORT_ENORICREINFSGMT.INITRPYSTS
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    public void setInitRpySts(String InitRpySts) {
        this.InitRpySts = InitRpySts;
    }
}