package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("CccInf")
public class PbcrsReportGuacccinf implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2421672818354154607L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_GUACCCINF.GUAMOTCLTCTRINFSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:54:59 CST 2019
     */
    private String GuaMotCltCtrInfSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_GUACCCINF.CCCINFSEQNO
     *
     * @mbggenerated Fri Mar 15 10:54:59 CST 2019
     */
    private String CccInfSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_GUACCCINF.CCC
     *
     * @mbggenerated Fri Mar 15 10:54:59 CST 2019
     */
    private String Ccc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_GUACCCINF.GUAMOTCLTCTRINFSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_GUACCCINF.GUAMOTCLTCTRINFSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:54:59 CST 2019
     */
    public String getGuaMotCltCtrInfSgmtSeqNo() {
        return GuaMotCltCtrInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_GUACCCINF.GUAMOTCLTCTRINFSGMTSEQNO
     *
     * @param GuaMotCltCtrInfSgmtSeqNo the value for PBCRS_REPORT_GUACCCINF.GUAMOTCLTCTRINFSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:54:59 CST 2019
     */
    public void setGuaMotCltCtrInfSgmtSeqNo(String GuaMotCltCtrInfSgmtSeqNo) {
        this.GuaMotCltCtrInfSgmtSeqNo = GuaMotCltCtrInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_GUACCCINF.CCCINFSEQNO
     *
     * @return the value of PBCRS_REPORT_GUACCCINF.CCCINFSEQNO
     *
     * @mbggenerated Fri Mar 15 10:54:59 CST 2019
     */
    public String getCccInfSeqNo() {
        return CccInfSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_GUACCCINF.CCCINFSEQNO
     *
     * @param CccInfSeqNo the value for PBCRS_REPORT_GUACCCINF.CCCINFSEQNO
     *
     * @mbggenerated Fri Mar 15 10:54:59 CST 2019
     */
    public void setCccInfSeqNo(String CccInfSeqNo) {
        this.CccInfSeqNo = CccInfSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_GUACCCINF.CCC
     *
     * @return the value of PBCRS_REPORT_GUACCCINF.CCC
     *
     * @mbggenerated Fri Mar 15 10:54:59 CST 2019
     */
    public String getCcc() {
        return Ccc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_GUACCCINF.CCC
     *
     * @param Ccc the value for PBCRS_REPORT_GUACCCINF.CCC
     *
     * @mbggenerated Fri Mar 15 10:54:59 CST 2019
     */
    public void setCcc(String Ccc) {
        this.Ccc = Ccc;
    }
}