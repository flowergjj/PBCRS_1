package com.hkbank.pbcrs.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
@XStreamAlias("AcctSpecTrstDspnSgmt")
public class PbcrsReportEnaccspetrssgmt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCSPETRSSGMT.ACCTSPECTRSTDSPNSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
	@XStreamOmitField
    private String AcctSpecTrstDspnSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCSPETRSSGMT.CAGOFTRDNM
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
    private String CagOfTrdNm;
    

    @XStreamOmitField
	private String ReportFlag;
    
    @XStreamOmitField
   	private String ParagraphDel;
   	

   	public String getParagraphDel() {
   		return ParagraphDel;
   	}

   	public void setParagraphDel(String paragraphDel) {
   		ParagraphDel = paragraphDel;
   	}
	    
	    
	public String getReportFlag() {
		return ReportFlag;
	}

	public void setReportFlag(String reportFlag) {
		ReportFlag = reportFlag;
	}

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCSPETRSSGMT.ACCTSPECTRSTDSPNSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENACCSPETRSSGMT.ACCTSPECTRSTDSPNSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
    @XStreamImplicit
    private List<PbcrsReportEncagoftrdinf> EnCagOfTrdInf;
    public String getAcctSpecTrstDspnSgmtSeqNo() {
        return AcctSpecTrstDspnSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCSPETRSSGMT.ACCTSPECTRSTDSPNSGMTSEQNO
     *
     * @param AcctSpecTrstDspnSgmtSeqNo the value for PBCRS_REPORT_ENACCSPETRSSGMT.ACCTSPECTRSTDSPNSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
    public void setAcctSpecTrstDspnSgmtSeqNo(String AcctSpecTrstDspnSgmtSeqNo) {
        this.AcctSpecTrstDspnSgmtSeqNo = AcctSpecTrstDspnSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCSPETRSSGMT.CAGOFTRDNM
     *
     * @return the value of PBCRS_REPORT_ENACCSPETRSSGMT.CAGOFTRDNM
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
    public String getCagOfTrdNm() {
        return CagOfTrdNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCSPETRSSGMT.CAGOFTRDNM
     *
     * @param CagOfTrdNm the value for PBCRS_REPORT_ENACCSPETRSSGMT.CAGOFTRDNM
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
    public void setCagOfTrdNm(String CagOfTrdNm) {
        this.CagOfTrdNm = CagOfTrdNm;
    }

	public List<PbcrsReportEncagoftrdinf> getEnCagOfTrdInf() {
		return EnCagOfTrdInf;
	}

	public void setEnCagOfTrdInf(List<PbcrsReportEncagoftrdinf> enCagOfTrdInf) {
		EnCagOfTrdInf = enCagOfTrdInf;
	}
    
}