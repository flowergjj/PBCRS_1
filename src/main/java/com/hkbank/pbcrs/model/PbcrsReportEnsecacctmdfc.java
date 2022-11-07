package com.hkbank.pbcrs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("EnSecAcctMdfc")
public class PbcrsReportEnsecacctmdfc {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENSECACCTMDFC.ENSECACCTMDFCSEQNO
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
	@XStreamOmitField
    private String EnSecAcctMdfcSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENSECACCTMDFC.BSSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
	@XStreamOmitField
    private String BsSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENSECACCTMDFC.MDFCSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
	@XStreamOmitField
    private String MdfcSgmtSeqNo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENSECACCTMDFC.ENSECACCTMDFCSEQNO
     *
     * @return the value of PBCRS_REPORT_ENSECACCTMDFC.ENSECACCTMDFCSEQNO
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
	@XStreamAlias("BsSgmt")
	private PbcrsReportEnsecaccmdfbssgmt BsSgmt;
	@XStreamAlias("MdfcSgmt")
	private PbcrsReportEnsecaccmdfsgmt MdfcSgmt;
	
	
    public String getEnSecAcctMdfcSeqNo() {
        return EnSecAcctMdfcSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENSECACCTMDFC.ENSECACCTMDFCSEQNO
     *
     * @param EnSecAcctMdfcSeqNo the value for PBCRS_REPORT_ENSECACCTMDFC.ENSECACCTMDFCSEQNO
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    public void setEnSecAcctMdfcSeqNo(String EnSecAcctMdfcSeqNo) {
        this.EnSecAcctMdfcSeqNo = EnSecAcctMdfcSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENSECACCTMDFC.BSSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENSECACCTMDFC.BSSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    public String getBsSgmtSeqNo() {
        return BsSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENSECACCTMDFC.BSSGMTSEQNO
     *
     * @param BsSgmtSeqNo the value for PBCRS_REPORT_ENSECACCTMDFC.BSSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    public void setBsSgmtSeqNo(String BsSgmtSeqNo) {
        this.BsSgmtSeqNo = BsSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENSECACCTMDFC.MDFCSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENSECACCTMDFC.MDFCSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    public String getMdfcSgmtSeqNo() {
        return MdfcSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENSECACCTMDFC.MDFCSGMTSEQNO
     *
     * @param MdfcSgmtSeqNo the value for PBCRS_REPORT_ENSECACCTMDFC.MDFCSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    public void setMdfcSgmtSeqNo(String MdfcSgmtSeqNo) {
        this.MdfcSgmtSeqNo = MdfcSgmtSeqNo;
    }

	public PbcrsReportEnsecaccmdfbssgmt getBsSgmt() {
		return BsSgmt;
	}

	public void setBsSgmt(PbcrsReportEnsecaccmdfbssgmt bsSgmt) {
		BsSgmt = bsSgmt;
	}

	public PbcrsReportEnsecaccmdfsgmt getMdfcSgmt() {
		return MdfcSgmt;
	}

	public void setMdfcSgmt(PbcrsReportEnsecaccmdfsgmt mdfcSgmt) {
		MdfcSgmt = mdfcSgmt;
	}
    
}