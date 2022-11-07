package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("CagOfTrdInf")
public class PbcrsReportCagoftrdinf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6865399424318107246L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_CAGOFTRDINF.ACCTSPECTRSTDSPNSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String AcctSpecTrstDspnSgmtSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_CAGOFTRDINF.CAGOFTRDINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	@XStreamOmitField
	private String CagOfTrdInfSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_CAGOFTRDINF.CHANTRANTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String ChanTranType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_CAGOFTRDINF.TRANDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String TranDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_CAGOFTRDINF.TRANAMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String TranAmt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_CAGOFTRDINF.DUETRANMON
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String DueTranMon;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_CAGOFTRDINF.DETINFO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String DetInfo;
	/**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @mbggenerated Wed Apr 24 09:13:16 CST 2019
    */
	@XStreamOmitField
   private String reportflag;
	
	@XStreamOmitField
	private String ParagraphDel;
	
	
	public String getParagraphDel() {
		return ParagraphDel;
	}

	public void setParagraphDel(String paragraphDel) {
		ParagraphDel = paragraphDel;
	}

	/**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @return the value of PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @mbggenerated Wed Apr 24 09:13:16 CST 2019
    */
   public String getReportflag() {
       return reportflag;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @param reportflag the value for PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @mbggenerated Wed Apr 24 09:13:16 CST 2019
    */
   public void setReportflag(String reportflag) {
       this.reportflag = reportflag;
   }
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_CAGOFTRDINF.ACCTSPECTRSTDSPNSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_CAGOFTRDINF.ACCTSPECTRSTDSPNSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getAcctSpecTrstDspnSgmtSeqNo() {
		return AcctSpecTrstDspnSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_CAGOFTRDINF.ACCTSPECTRSTDSPNSGMTSEQNO
	 * @param AcctSpecTrstDspnSgmtSeqNo  the value for PBCRS_REPORT_CAGOFTRDINF.ACCTSPECTRSTDSPNSGMTSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setAcctSpecTrstDspnSgmtSeqNo(String AcctSpecTrstDspnSgmtSeqNo) {
		this.AcctSpecTrstDspnSgmtSeqNo = AcctSpecTrstDspnSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_CAGOFTRDINF.CAGOFTRDINFSEQNO
	 * @return  the value of PBCRS_REPORT_CAGOFTRDINF.CAGOFTRDINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getCagOfTrdInfSeqNo() {
		return CagOfTrdInfSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_CAGOFTRDINF.CAGOFTRDINFSEQNO
	 * @param CagOfTrdInfSeqNo  the value for PBCRS_REPORT_CAGOFTRDINF.CAGOFTRDINFSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setCagOfTrdInfSeqNo(String CagOfTrdInfSeqNo) {
		this.CagOfTrdInfSeqNo = CagOfTrdInfSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_CAGOFTRDINF.CHANTRANTYPE
	 * @return  the value of PBCRS_REPORT_CAGOFTRDINF.CHANTRANTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getChanTranType() {
		return ChanTranType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_CAGOFTRDINF.CHANTRANTYPE
	 * @param ChanTranType  the value for PBCRS_REPORT_CAGOFTRDINF.CHANTRANTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setChanTranType(String ChanTranType) {
		this.ChanTranType = ChanTranType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_CAGOFTRDINF.TRANDATE
	 * @return  the value of PBCRS_REPORT_CAGOFTRDINF.TRANDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getTranDate() {
		return TranDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_CAGOFTRDINF.TRANDATE
	 * @param TranDate  the value for PBCRS_REPORT_CAGOFTRDINF.TRANDATE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setTranDate(String TranDate) {
		this.TranDate = TranDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_CAGOFTRDINF.TRANAMT
	 * @return  the value of PBCRS_REPORT_CAGOFTRDINF.TRANAMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getTranAmt() {
		return TranAmt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_CAGOFTRDINF.TRANAMT
	 * @param TranAmt  the value for PBCRS_REPORT_CAGOFTRDINF.TRANAMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setTranAmt(String TranAmt) {
		this.TranAmt = TranAmt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_CAGOFTRDINF.DUETRANMON
	 * @return  the value of PBCRS_REPORT_CAGOFTRDINF.DUETRANMON
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getDueTranMon() {
		return DueTranMon;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_CAGOFTRDINF.DUETRANMON
	 * @param DueTranMon  the value for PBCRS_REPORT_CAGOFTRDINF.DUETRANMON
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setDueTranMon(String DueTranMon) {
		this.DueTranMon = DueTranMon;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_CAGOFTRDINF.DETINFO
	 * @return  the value of PBCRS_REPORT_CAGOFTRDINF.DETINFO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getDetInfo() {
		return DetInfo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_CAGOFTRDINF.DETINFO
	 * @param DetInfo  the value for PBCRS_REPORT_CAGOFTRDINF.DETINFO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setDetInfo(String DetInfo) {
		this.DetInfo = DetInfo;
	}
}