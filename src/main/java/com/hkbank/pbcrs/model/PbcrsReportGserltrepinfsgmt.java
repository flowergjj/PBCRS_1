package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("RltRepymtInfSgmt")
public class PbcrsReportGserltrepinfsgmt implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1592021779608383177L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_GSERLTREPINFSGMT.RLTREPYMTINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
	@XStreamOmitField

    private String RltRepymtInfSgmtSeqNo;
	
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
     * This field corresponds to the database column PBCRS_REPORT_GSERLTREPINFSGMT.RLTREPYMTNM
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    private String RltRepymtNm;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_GSERLTREPINFSGMT.RLTREPYMTINFSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_GSERLTREPINFSGMT.RLTREPYMTINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    @XStreamImplicit
    private List<PbcrsReportGserltrepymtinf> RltRepymtInf;

    
    
    public String getRltRepymtInfSgmtSeqNo() {
        return RltRepymtInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_GSERLTREPINFSGMT.RLTREPYMTINFSGMTSEQNO
     *
     * @param RltRepymtInfSgmtSeqNo the value for PBCRS_REPORT_GSERLTREPINFSGMT.RLTREPYMTINFSGMTSEQNO
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setRltRepymtInfSgmtSeqNo(String RltRepymtInfSgmtSeqNo) {
        this.RltRepymtInfSgmtSeqNo = RltRepymtInfSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_GSERLTREPINFSGMT.RLTREPYMTNM
     *
     * @return the value of PBCRS_REPORT_GSERLTREPINFSGMT.RLTREPYMTNM
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public String getRltRepymtNm() {
        return RltRepymtNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_GSERLTREPINFSGMT.RLTREPYMTNM
     *
     * @param RltRepymtNm the value for PBCRS_REPORT_GSERLTREPINFSGMT.RLTREPYMTNM
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    public void setRltRepymtNm(String RltRepymtNm) {
        this.RltRepymtNm = RltRepymtNm;
    }

	public List<PbcrsReportGserltrepymtinf> getRltRepymtInf() {
		return RltRepymtInf;
	}

	public void setRltRepymtInf(List<PbcrsReportGserltrepymtinf> rltRepymtInf) {
		RltRepymtInf = rltRepymtInf;
	}
    
}