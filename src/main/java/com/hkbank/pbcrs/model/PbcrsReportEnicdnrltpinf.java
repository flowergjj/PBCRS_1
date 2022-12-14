package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
@XStreamAlias("EnIcdnRltpInf")
public class PbcrsReportEnicdnrltpinf implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5975754878241148413L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.ENICDNRLTPINFSEQNO
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
	@XStreamOmitField
    private String EnIcdnRltpInfSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.INFRECTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    private String InfRecType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.ENTNAME
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    private String EntName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.ENTCERTTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    private String EntCertType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.ENTCERTNUM
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    private String EntCertNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOENTNAME
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    private String AssoEntName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOENTCERTTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    private String AssoEntCertType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOENTCERTNUM
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    private String AssoEntCertNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    private String AssoType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOSIGN
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    private String AssoSign;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENICDNRLTPINF.RPTDATE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    private String RptDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ENICDNRLTPINFSEQNO
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.ENICDNRLTPINFSEQNO
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getEnIcdnRltpInfSeqNo() {
        return EnIcdnRltpInfSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ENICDNRLTPINFSEQNO
     *
     * @param EnIcdnRltpInfSeqNo the value for PBCRS_REPORT_ENICDNRLTPINF.ENICDNRLTPINFSEQNO
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setEnIcdnRltpInfSeqNo(String EnIcdnRltpInfSeqNo) {
        this.EnIcdnRltpInfSeqNo = EnIcdnRltpInfSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.INFRECTYPE
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.INFRECTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getInfRecType() {
        return InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.INFRECTYPE
     *
     * @param InfRecType the value for PBCRS_REPORT_ENICDNRLTPINF.INFRECTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setInfRecType(String InfRecType) {
        this.InfRecType = InfRecType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ENTNAME
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.ENTNAME
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getEntName() {
        return EntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ENTNAME
     *
     * @param EntName the value for PBCRS_REPORT_ENICDNRLTPINF.ENTNAME
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setEntName(String EntName) {
        this.EntName = EntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ENTCERTTYPE
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.ENTCERTTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getEntCertType() {
        return EntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ENTCERTTYPE
     *
     * @param EntCertType the value for PBCRS_REPORT_ENICDNRLTPINF.ENTCERTTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setEntCertType(String EntCertType) {
        this.EntCertType = EntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ENTCERTNUM
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.ENTCERTNUM
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getEntCertNum() {
        return EntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ENTCERTNUM
     *
     * @param EntCertNum the value for PBCRS_REPORT_ENICDNRLTPINF.ENTCERTNUM
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setEntCertNum(String EntCertNum) {
        this.EntCertNum = EntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOENTNAME
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.ASSOENTNAME
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getAssoEntName() {
        return AssoEntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOENTNAME
     *
     * @param AssoEntName the value for PBCRS_REPORT_ENICDNRLTPINF.ASSOENTNAME
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setAssoEntName(String AssoEntName) {
        this.AssoEntName = AssoEntName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOENTCERTTYPE
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.ASSOENTCERTTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getAssoEntCertType() {
        return AssoEntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOENTCERTTYPE
     *
     * @param AssoEntCertType the value for PBCRS_REPORT_ENICDNRLTPINF.ASSOENTCERTTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setAssoEntCertType(String AssoEntCertType) {
        this.AssoEntCertType = AssoEntCertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOENTCERTNUM
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.ASSOENTCERTNUM
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getAssoEntCertNum() {
        return AssoEntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOENTCERTNUM
     *
     * @param AssoEntCertNum the value for PBCRS_REPORT_ENICDNRLTPINF.ASSOENTCERTNUM
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setAssoEntCertNum(String AssoEntCertNum) {
        this.AssoEntCertNum = AssoEntCertNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOTYPE
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.ASSOTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getAssoType() {
        return AssoType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOTYPE
     *
     * @param AssoType the value for PBCRS_REPORT_ENICDNRLTPINF.ASSOTYPE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setAssoType(String AssoType) {
        this.AssoType = AssoType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOSIGN
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.ASSOSIGN
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getAssoSign() {
        return AssoSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.ASSOSIGN
     *
     * @param AssoSign the value for PBCRS_REPORT_ENICDNRLTPINF.ASSOSIGN
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setAssoSign(String AssoSign) {
        this.AssoSign = AssoSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENICDNRLTPINF.RPTDATE
     *
     * @return the value of PBCRS_REPORT_ENICDNRLTPINF.RPTDATE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public String getRptDate() {
        return RptDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENICDNRLTPINF.RPTDATE
     *
     * @param RptDate the value for PBCRS_REPORT_ENICDNRLTPINF.RPTDATE
     *
     * @mbggenerated Fri Mar 15 08:56:34 CST 2019
     */
    public void setRptDate(String RptDate) {
        this.RptDate = RptDate;
    }
}