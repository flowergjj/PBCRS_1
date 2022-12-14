package com.hkbank.pbcrs.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
@XStreamAlias("MnMmbInfSgmt")
public class PbcrsReportMnmmbinfsgmt {
	
	private static final long serialVersionUID = 3480122331608785345L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_MNMMBINFSGMT.MNMMBINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
	@XStreamOmitField
    private String MnMmbInfSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_MNMMBINFSGMT.MMBNM
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    private String MmbNm;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_MNMMBINFSGMT.MNMMBINFSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_MNMMBINFSGMT.MNMMBINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    private String MnMmbInfoUpDate;
    
    @XStreamOmitField
    private String ReportFlag;
    
    public String getReportFlag() {
		return ReportFlag;
	}

	public void setReportFlag(String reportFlag) {
		ReportFlag = reportFlag;
	}
    
    
    @XStreamImplicit
    private List<PbcrsReportEnbasinfmmbinf> MmbInf;
    


	public List<PbcrsReportEnbasinfmmbinf> getMmbInf() {
		return MmbInf;
	}

	public void setMmbInf(List<PbcrsReportEnbasinfmmbinf> mmbInf) {
		MmbInf = mmbInf;
	}

	public String getMnMmbInfSgmtSeqNo() {
        return MnMmbInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_MNMMBINFSGMT.MNMMBINFSGMTSEQNO
     *
     * @param MnMmbInfSgmtSeqNo the value for PBCRS_REPORT_MNMMBINFSGMT.MNMMBINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    public void setMnMmbInfSgmtSeqNo(String MnMmbInfSgmtSeqNo) {
        this.MnMmbInfSgmtSeqNo = MnMmbInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_MNMMBINFSGMT.MMBNM
     *
     * @return the value of PBCRS_REPORT_MNMMBINFSGMT.MMBNM
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    public String getMmbNm() {
        return MmbNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_MNMMBINFSGMT.MMBNM
     *
     * @param MmbNm the value for PBCRS_REPORT_MNMMBINFSGMT.MMBNM
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    public void setMmbNm(String MmbNm) {
        this.MmbNm = MmbNm;
    }

	public String getMnMmbInfoUpDate() {
		return MnMmbInfoUpDate;
	}

	public void setMnMmbInfoUpDate(String mnMmbInfoUpDate) {
		MnMmbInfoUpDate = mnMmbInfoUpDate;
	}
    
}