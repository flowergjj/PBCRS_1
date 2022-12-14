package com.hkbank.pbcrs.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("IDSgmt")
public class PbcrsReportIdsgmt implements Serializable {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_IDSGMT.IDSGMTSEQNO
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamOmitField
	private String IDSgmtSeqNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_IDSGMT.IDNM
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamAlias("IDNm")
	private String IDNm;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_IDSGMT.IDINFOUPDATE
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	@XStreamAlias("IDInfoUpDate")
	private String IDInfoUpDate;
	/**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column PBCRS_REPORT_BSSGMT.REPORTFLAG
    *
    * @mbggenerated Wed Apr 24 09:13:16 CST 2019
    */
	@XStreamOmitField
   private String reportflag;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_IDSGMT.IDSGMTSEQNO
	 * @return  the value of PBCRS_REPORT_IDSGMT.IDSGMTSEQNO
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getIDSgmtSeqNo() {
		return IDSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_IDSGMT.IDSGMTSEQNO
	 * @param IDSgmtSeqNo  the value for PBCRS_REPORT_IDSGMT.IDSGMTSEQNO
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setIDSgmtSeqNo(String IDSgmtSeqNo) {
		this.IDSgmtSeqNo = IDSgmtSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_IDSGMT.IDNM
	 * @return  the value of PBCRS_REPORT_IDSGMT.IDNM
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getIDNm() {
		return IDNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_IDSGMT.IDNM
	 * @param IDNm  the value for PBCRS_REPORT_IDSGMT.IDNM
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setIDNm(String IDNm) {
		this.IDNm = IDNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_IDSGMT.IDINFOUPDATE
	 * @return  the value of PBCRS_REPORT_IDSGMT.IDINFOUPDATE
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public String getIDInfoUpDate() {
		return IDInfoUpDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_IDSGMT.IDINFOUPDATE
	 * @param IDInfoUpDate  the value for PBCRS_REPORT_IDSGMT.IDINFOUPDATE
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	public void setIDInfoUpDate(String IDInfoUpDate) {
		this.IDInfoUpDate = IDInfoUpDate;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8395819635831302660L;

	@XStreamImplicit
    private List<PbcrsReportIdrec> IDRec;

    public List<PbcrsReportIdrec> getIDRec() {
		return IDRec;
	}

	public void setIDRec(List<PbcrsReportIdrec> iDRec) {
		IDRec = iDRec;
	}
}