package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("MdfcSgmt")
public class PbcrsReportInsecaccmdfmdsgmt implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1587028924807601897L;
	
	@XStreamImplicit
	private List<?> MdfcSgmtList;

	public List<?> getMdfcSgmtList() {
		return MdfcSgmtList;
	}

	public void setMdfcSgmtList(List<?> mdfcSgmtList) {
		MdfcSgmtList = mdfcSgmtList;
	}

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSECACCMDFMDSGMT.MDFCSGMTSEQNO
     *
     * @mbggenerated Tue Mar 26 16:20:32 CST 2019
     */
    private String MdfcSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_INSECACCMDFMDSGMT.MDFCSGMTREFSEQNO
     *
     * @mbggenerated Tue Mar 26 16:20:32 CST 2019
     */
    private String MdfcSgmtRefSeqNo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INSECACCMDFMDSGMT.MDFCSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_INSECACCMDFMDSGMT.MDFCSGMTSEQNO
     *
     * @mbggenerated Tue Mar 26 16:20:32 CST 2019
     */
    public String getMdfcSgmtSeqNo() {
        return MdfcSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSECACCMDFMDSGMT.MDFCSGMTSEQNO
     *
     * @param MdfcSgmtSeqNo the value for PBCRS_REPORT_INSECACCMDFMDSGMT.MDFCSGMTSEQNO
     *
     * @mbggenerated Tue Mar 26 16:20:32 CST 2019
     */
    public void setMdfcSgmtSeqNo(String MdfcSgmtSeqNo) {
        this.MdfcSgmtSeqNo = MdfcSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_INSECACCMDFMDSGMT.MDFCSGMTREFSEQNO
     *
     * @return the value of PBCRS_REPORT_INSECACCMDFMDSGMT.MDFCSGMTREFSEQNO
     *
     * @mbggenerated Tue Mar 26 16:20:32 CST 2019
     */
    public String getMdfcSgmtRefSeqNo() {
        return MdfcSgmtRefSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_INSECACCMDFMDSGMT.MDFCSGMTREFSEQNO
     *
     * @param MdfcSgmtRefSeqNo the value for PBCRS_REPORT_INSECACCMDFMDSGMT.MDFCSGMTREFSEQNO
     *
     * @mbggenerated Tue Mar 26 16:20:32 CST 2019
     */
    public void setMdfcSgmtRefSeqNo(String MdfcSgmtRefSeqNo) {
        this.MdfcSgmtRefSeqNo = MdfcSgmtRefSeqNo;
    }
}