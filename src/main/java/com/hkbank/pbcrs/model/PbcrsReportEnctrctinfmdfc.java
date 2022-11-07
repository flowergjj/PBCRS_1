package com.hkbank.pbcrs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("EnCtrctInfMdfc")
public class PbcrsReportEnctrctinfmdfc {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINFMDFC.ENCTRCTINFMDFCSEQNO
     *
     * @mbggenerated Fri Mar 15 11:05:23 CST 2019
     */
	@XStreamOmitField
    private String EnCtrctInfMdfcSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINFMDFC.BSSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:05:23 CST 2019
     */
	@XStreamOmitField
    private String BsSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCTINFMDFC.MDFCSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:05:23 CST 2019
     */
	@XStreamOmitField
    private String MdfcSgmtSeqNo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINFMDFC.ENCTRCTINFMDFCSEQNO
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINFMDFC.ENCTRCTINFMDFCSEQNO
     *
     * @mbggenerated Fri Mar 15 11:05:23 CST 2019
     */
	@XStreamAlias("BsSgmt")
	private PbcrsReportEnctrinfmdfbssgmt BsSgmt;
	@XStreamAlias("MdfcSgmt")
	private PbcrsReportEnctrinfmdfsgmt MdfcSgmt;
    public String getEnCtrctInfMdfcSeqNo() {
        return EnCtrctInfMdfcSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINFMDFC.ENCTRCTINFMDFCSEQNO
     *
     * @param EnCtrctInfMdfcSeqNo the value for PBCRS_REPORT_ENCTRCTINFMDFC.ENCTRCTINFMDFCSEQNO
     *
     * @mbggenerated Fri Mar 15 11:05:23 CST 2019
     */
    public void setEnCtrctInfMdfcSeqNo(String EnCtrctInfMdfcSeqNo) {
        this.EnCtrctInfMdfcSeqNo = EnCtrctInfMdfcSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINFMDFC.BSSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINFMDFC.BSSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:05:23 CST 2019
     */
    public String getBsSgmtSeqNo() {
        return BsSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINFMDFC.BSSGMTSEQNO
     *
     * @param BsSgmtSeqNo the value for PBCRS_REPORT_ENCTRCTINFMDFC.BSSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:05:23 CST 2019
     */
    public void setBsSgmtSeqNo(String BsSgmtSeqNo) {
        this.BsSgmtSeqNo = BsSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCTINFMDFC.MDFCSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENCTRCTINFMDFC.MDFCSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:05:23 CST 2019
     */
    public String getMdfcSgmtSeqNo() {
        return MdfcSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCTINFMDFC.MDFCSGMTSEQNO
     *
     * @param MdfcSgmtSeqNo the value for PBCRS_REPORT_ENCTRCTINFMDFC.MDFCSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 11:05:23 CST 2019
     */
    public void setMdfcSgmtSeqNo(String MdfcSgmtSeqNo) {
        this.MdfcSgmtSeqNo = MdfcSgmtSeqNo;
    }

	public PbcrsReportEnctrinfmdfbssgmt getBsSgmt() {
		return BsSgmt;
	}

	public void setBsSgmt(PbcrsReportEnctrinfmdfbssgmt bsSgmt) {
		BsSgmt = bsSgmt;
	}

	public PbcrsReportEnctrinfmdfsgmt getMdfcSgmt() {
		return MdfcSgmt;
	}

	public void setMdfcSgmt(PbcrsReportEnctrinfmdfsgmt mdfcSgmt) {
		MdfcSgmt = mdfcSgmt;
	}
    
}