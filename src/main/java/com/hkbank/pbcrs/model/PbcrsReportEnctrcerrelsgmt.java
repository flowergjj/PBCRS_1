package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("CtrctCertRelSgmt")
public class PbcrsReportEnctrcerrelsgmt implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1835354204151756265L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCERRELSGMT.CTRCTCERTRELSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
	@XStreamOmitField
    private String CtrctCertRelSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENCTRCERRELSGMT.BRERNM
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    private String BrerNm;

    @XStreamOmitField
    private String ReportFlag;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCERRELSGMT.CTRCTCERTRELSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENCTRCERRELSGMT.CTRCTCERTRELSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    @XStreamImplicit
    private List<PbcrsReportEnctrctcertrel> CtrctCertRel;

    
    public String getCtrctCertRelSgmtSeqNo() {
        return CtrctCertRelSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCERRELSGMT.CTRCTCERTRELSGMTSEQNO
     *
     * @param CtrctCertRelSgmtSeqNo the value for PBCRS_REPORT_ENCTRCERRELSGMT.CTRCTCERTRELSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    public void setCtrctCertRelSgmtSeqNo(String CtrctCertRelSgmtSeqNo) {
        this.CtrctCertRelSgmtSeqNo = CtrctCertRelSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENCTRCERRELSGMT.BRERNM
     *
     * @return the value of PBCRS_REPORT_ENCTRCERRELSGMT.BRERNM
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    public String getBrerNm() {
        return BrerNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENCTRCERRELSGMT.BRERNM
     *
     * @param BrerNm the value for PBCRS_REPORT_ENCTRCERRELSGMT.BRERNM
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    public void setBrerNm(String BrerNm) {
        this.BrerNm = BrerNm;
    }

	public String getReportFlag() {
		return ReportFlag;
	}

	public void setReportFlag(String reportFlag) {
		ReportFlag = reportFlag;
	}
    

		public List<PbcrsReportEnctrctcertrel> getCtrctCertRel() {
			return CtrctCertRel;
		}

		public void setCtrctCertRel(List<PbcrsReportEnctrctcertrel> ctrctCertRel) {
			CtrctCertRel = ctrctCertRel;
		}
    
}