package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("MdfcSgmt")
public class PbcrsReportEnaccinfmdfsgmt implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6021958206754671456L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCINFMDFSGMT.MDFCSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 09:06:17 CST 2019
     */
	@XStreamOmitField
    private String MdfcSgmtSeqNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PBCRS_REPORT_ENACCINFMDFSGMT.MDFCSGMTDETAILSEQNO
     *
     * @mbggenerated Fri Mar 15 09:06:17 CST 2019
     */
	@XStreamOmitField
    private String MdfcSgmtDetailSeqNo;

    /**
     * 企业借贷账户更正请求待更正具体段
     * 不确定是某一个段，使用通配符的List集合存放
     */
    @XStreamImplicit
    private List<?> MdfcSgmtDetail;
  
	@XStreamAlias("AcctBsSgmt")
	private PbcrsReportEnacctbssgmt AcctBsSgmt;
	@XStreamAlias("AcctBsInfSgmt")
	private PbcrsReportEnacctbsinfsgmt AcctBsInfSgmt;
	@XStreamAlias("RltRepymtInfSgmt")
	private PbcrsReportEnrltrepinfsgmt RltRepInfSgmt;
	@XStreamAlias("MotgaCltalCtrctInfSgmt")
	private PbcrsReportEnmotcltctrinfsgm MotgaCltalCtrctInfSgmt;
	@XStreamAlias("AcctCredSgmt")
	private PbcrsReportEnacctcredsgmt AcctCredSgmt;
	@XStreamAlias("OrigCreditorInfSgmt")
	private PbcrsReportEnoricreinfsgmt OriCreInfSgmt;
	@XStreamAlias("ActLbltyInfSgmt")
	private PbcrsReportActlbltyinfsgmt ActLbltyInfSgmt;
	@XStreamAlias("AcctSpecTrstDspnSgmt")
	private PbcrsReportEnaccspetrssgmt AccSpeTrsSgmt;
	

	

	public PbcrsReportEnacctbssgmt getAcctBsSgmt() {
		return AcctBsSgmt;
	}

	public void setAcctBsSgmt(PbcrsReportEnacctbssgmt acctBsSgmt) {
		AcctBsSgmt = acctBsSgmt;
	}

	public PbcrsReportEnacctbsinfsgmt getAcctBsInfSgmt() {
		return AcctBsInfSgmt;
	}

	public void setAcctBsInfSgmt(PbcrsReportEnacctbsinfsgmt acctBsInfSgmt) {
		AcctBsInfSgmt = acctBsInfSgmt;
	}

	public PbcrsReportEnrltrepinfsgmt getRltRepInfSgmt() {
		return RltRepInfSgmt;
	}

	public void setRltRepInfSgmt(PbcrsReportEnrltrepinfsgmt rltRepInfSgmt) {
		RltRepInfSgmt = rltRepInfSgmt;
	}

	public PbcrsReportEnmotcltctrinfsgm getMotgaCltalCtrctInfSgmt() {
		return MotgaCltalCtrctInfSgmt;
	}

	public void setMotgaCltalCtrctInfSgmt(
			PbcrsReportEnmotcltctrinfsgm motgaCltalCtrctInfSgmt) {
		MotgaCltalCtrctInfSgmt = motgaCltalCtrctInfSgmt;
	}

	public PbcrsReportEnacctcredsgmt getAcctCredSgmt() {
		return AcctCredSgmt;
	}

	public void setAcctCredSgmt(PbcrsReportEnacctcredsgmt acctCredSgmt) {
		AcctCredSgmt = acctCredSgmt;
	}

	public PbcrsReportEnoricreinfsgmt getOriCreInfSgmt() {
		return OriCreInfSgmt;
	}

	public void setOriCreInfSgmt(PbcrsReportEnoricreinfsgmt oriCreInfSgmt) {
		OriCreInfSgmt = oriCreInfSgmt;
	}

	public PbcrsReportActlbltyinfsgmt getActLbltyInfSgmt() {
		return ActLbltyInfSgmt;
	}

	public void setActLbltyInfSgmt(PbcrsReportActlbltyinfsgmt actLbltyInfSgmt) {
		ActLbltyInfSgmt = actLbltyInfSgmt;
	}

	public PbcrsReportEnaccspetrssgmt getAccSpeTrsSgmt() {
		return AccSpeTrsSgmt;
	}

	public void setAccSpeTrsSgmt(PbcrsReportEnaccspetrssgmt accSpeTrsSgmt) {
		AccSpeTrsSgmt = accSpeTrsSgmt;
	}

	public List<?> getMdfcSgmtDetail() {
		return MdfcSgmtDetail;
	}

	public void setMdfcSgmtDetail(List<?> mdfcSgmtDetail) {
		MdfcSgmtDetail = mdfcSgmtDetail;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCINFMDFSGMT.MDFCSGMTSEQNO
     *
     * @return the value of PBCRS_REPORT_ENACCINFMDFSGMT.MDFCSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 09:06:17 CST 2019
     */
    public String getMdfcSgmtSeqNo() {
        return MdfcSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCINFMDFSGMT.MDFCSGMTSEQNO
     *
     * @param MdfcSgmtSeqNo the value for PBCRS_REPORT_ENACCINFMDFSGMT.MDFCSGMTSEQNO
     *
     * @mbggenerated Fri Mar 15 09:06:17 CST 2019
     */
    public void setMdfcSgmtSeqNo(String MdfcSgmtSeqNo) {
        this.MdfcSgmtSeqNo = MdfcSgmtSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PBCRS_REPORT_ENACCINFMDFSGMT.MDFCSGMTDETAILSEQNO
     *
     * @return the value of PBCRS_REPORT_ENACCINFMDFSGMT.MDFCSGMTDETAILSEQNO
     *
     * @mbggenerated Fri Mar 15 09:06:17 CST 2019
     */
    public String getMdfcSgmtDetailSeqNo() {
        return MdfcSgmtDetailSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PBCRS_REPORT_ENACCINFMDFSGMT.MDFCSGMTDETAILSEQNO
     *
     * @param MdfcSgmtDetailSeqNo the value for PBCRS_REPORT_ENACCINFMDFSGMT.MDFCSGMTDETAILSEQNO
     *
     * @mbggenerated Fri Mar 15 09:06:17 CST 2019
     */
    public void setMdfcSgmtDetailSeqNo(String MdfcSgmtDetailSeqNo) {
        this.MdfcSgmtDetailSeqNo = MdfcSgmtDetailSeqNo;
    }
}