package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
@XStreamAlias("EnCtrctInf")
public class PbcrsReportEnctrctinf implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6727943131687171975L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINF.ENCTRCTINFSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
	@XStreamOmitField
    private String EnCtrctInfSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINF.CTRCTBSSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
	@XStreamOmitField
    private String CtrctBsSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINF.CTRCTCERTRELSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
	@XStreamOmitField
    private String CtrctCertRelSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINF.CREDITLIMSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
	@XStreamOmitField
    private String CreditLimSgmtSeqNo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINF.ENCTRCTINFSEQNO
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINF.ENCTRCTINFSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
	@XStreamAlias("CtrctBsSgmt")
	private PbcrsReportEnctrctbssgmt CtrctBsSgmt;
	@XStreamAlias("CtrctCertRelSgmt")
	private PbcrsReportEnctrcerrelsgmt CtrctCertRelSgmt;
	@XStreamAlias("CreditLimSgmt")
	private PbcrsReportEncreditlimsgmt CreditLimSgmt;

	
    public String getEnCtrctInfSeqNo() {
        return EnCtrctInfSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINF.ENCTRCTINFSEQNO
     *
     * @param EnCtrctInfSeqNo the value for PBCRS_REPORT_ENCTRCTINF.ENCTRCTINFSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    public void setEnCtrctInfSeqNo(String EnCtrctInfSeqNo) {
        this.EnCtrctInfSeqNo = EnCtrctInfSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINF.CTRCTBSSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINF.CTRCTBSSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    public String getCtrctBsSgmtSeqNo() {
        return CtrctBsSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINF.CTRCTBSSGMTSEQNO
     *
     * @param CtrctBsSgmtSeqNo the value for PBCRS_REPORT_ENCTRCTINF.CTRCTBSSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    public void setCtrctBsSgmtSeqNo(String CtrctBsSgmtSeqNo) {
        this.CtrctBsSgmtSeqNo = CtrctBsSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINF.CTRCTCERTRELSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINF.CTRCTCERTRELSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    public String getCtrctCertRelSgmtSeqNo() {
        return CtrctCertRelSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINF.CTRCTCERTRELSGMTSEQNO
     *
     * @param CtrctCertRelSgmtSeqNo the value for PBCRS_REPORT_ENCTRCTINF.CTRCTCERTRELSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    public void setCtrctCertRelSgmtSeqNo(String CtrctCertRelSgmtSeqNo) {
        this.CtrctCertRelSgmtSeqNo = CtrctCertRelSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINF.CREDITLIMSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINF.CREDITLIMSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    public String getCreditLimSgmtSeqNo() {
        return CreditLimSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINF.CREDITLIMSGMTSEQNO
     *
     * @param CreditLimSgmtSeqNo the value for PBCRS_REPORT_ENCTRCTINF.CREDITLIMSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    public void setCreditLimSgmtSeqNo(String CreditLimSgmtSeqNo) {
        this.CreditLimSgmtSeqNo = CreditLimSgmtSeqNo;
    }
    
	public PbcrsReportEnctrctbssgmt getCtrctBsSgmt() {
		return CtrctBsSgmt;
	}

	public void setCtrctBsSgmt(PbcrsReportEnctrctbssgmt ctrctBsSgmt) {
		CtrctBsSgmt = ctrctBsSgmt;
	}

	public PbcrsReportEnctrcerrelsgmt getCtrctCertRelSgmt() {
		return CtrctCertRelSgmt;
	}

	public void setCtrctCertRelSgmt(PbcrsReportEnctrcerrelsgmt ctrctCertRelSgmt) {
		CtrctCertRelSgmt = ctrctCertRelSgmt;
	}

	public PbcrsReportEncreditlimsgmt getCreditLimSgmt() {
		return CreditLimSgmt;
	}

	public void setCreditLimSgmt(PbcrsReportEncreditlimsgmt creditLimSgmt) {
		CreditLimSgmt = creditLimSgmt;
	}
}