package com.hkbank.pbcrs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("EnCtrctInfDel")
public class PbcrsReportEnctrctinfdel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINFDEL.ENCTRCTINFDELSEQNO
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
	@XStreamOmitField
    private String EnCtrctInfDelSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINFDEL.INFRECTYPE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    private String InfRecType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINFDEL.DELRECCODE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    private String DelRecCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINFDEL.DELSGMTCODE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    private String DelSgmtCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINFDEL.DELSTARTDATE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    private String DelStartDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINFDEL.DELENDDATE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    private String DelEndDate;
    
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
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.ENCTRCTINFDELSEQNO
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINFDEL.ENCTRCTINFDELSEQNO
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public String getEnCtrctInfDelSeqNo() {
        return EnCtrctInfDelSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.ENCTRCTINFDELSEQNO
     *
     * @param EnCtrctInfDelSeqNo the value for PBCRS_REPORT_ENCTRCTINFDEL.ENCTRCTINFDELSEQNO
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public void setEnCtrctInfDelSeqNo(String EnCtrctInfDelSeqNo) {
        this.EnCtrctInfDelSeqNo = EnCtrctInfDelSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.INFRECTYPE
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINFDEL.INFRECTYPE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public String getInfRecType() {
        return InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.INFRECTYPE
     *
     * @param InfRecType the value for PBCRS_REPORT_ENCTRCTINFDEL.INFRECTYPE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public void setInfRecType(String InfRecType) {
        this.InfRecType = InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.DELRECCODE
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINFDEL.DELRECCODE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public String getDelRecCode() {
        return DelRecCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.DELRECCODE
     *
     * @param DelRecCode the value for PBCRS_REPORT_ENCTRCTINFDEL.DELRECCODE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public void setDelRecCode(String DelRecCode) {
        this.DelRecCode = DelRecCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.DELSGMTCODE
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINFDEL.DELSGMTCODE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public String getDelSgmtCode() {
        return DelSgmtCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.DELSGMTCODE
     *
     * @param DelSgmtCode the value for PBCRS_REPORT_ENCTRCTINFDEL.DELSGMTCODE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public void setDelSgmtCode(String DelSgmtCode) {
        this.DelSgmtCode = DelSgmtCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.DELSTARTDATE
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINFDEL.DELSTARTDATE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public String getDelStartDate() {
        return DelStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.DELSTARTDATE
     *
     * @param DelStartDate the value for PBCRS_REPORT_ENCTRCTINFDEL.DELSTARTDATE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public void setDelStartDate(String DelStartDate) {
        this.DelStartDate = DelStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.DELENDDATE
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINFDEL.DELENDDATE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public String getDelEndDate() {
        return DelEndDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINFDEL.DELENDDATE
     *
     * @param DelEndDate the value for PBCRS_REPORT_ENCTRCTINFDEL.DELENDDATE
     *
     * @mbggenerated Fri Mar 15 11:12:59 CST 2019
     */
    public void setDelEndDate(String DelEndDate) {
        this.DelEndDate = DelEndDate;
    }
}