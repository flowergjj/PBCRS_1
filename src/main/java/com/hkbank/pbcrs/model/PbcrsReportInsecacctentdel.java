package com.hkbank.pbcrs.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("InSecAcctEntDel")
public class PbcrsReportInsecacctentdel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2599047736076620369L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INSECACCTENTDEL.INSECACCTENTDELSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InSecAcctEntDelSeqNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INSECACCTENTDEL.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String InfRecType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PBCRS_REPORT_INSECACCTENTDEL.DELRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	private String DelRecCode;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INSECACCTENTDEL.INSECACCTENTDELSEQNO
	 * @return  the value of PBCRS_REPORT_INSECACCTENTDEL.INSECACCTENTDELSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInSecAcctEntDelSeqNo() {
		return InSecAcctEntDelSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INSECACCTENTDEL.INSECACCTENTDELSEQNO
	 * @param InSecAcctEntDelSeqNo  the value for PBCRS_REPORT_INSECACCTENTDEL.INSECACCTENTDELSEQNO
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInSecAcctEntDelSeqNo(String InSecAcctEntDelSeqNo) {
		this.InSecAcctEntDelSeqNo = InSecAcctEntDelSeqNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INSECACCTENTDEL.INFRECTYPE
	 * @return  the value of PBCRS_REPORT_INSECACCTENTDEL.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getInfRecType() {
		return InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INSECACCTENTDEL.INFRECTYPE
	 * @param InfRecType  the value for PBCRS_REPORT_INSECACCTENTDEL.INFRECTYPE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setInfRecType(String InfRecType) {
		this.InfRecType = InfRecType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PBCRS_REPORT_INSECACCTENTDEL.DELRECCODE
	 * @return  the value of PBCRS_REPORT_INSECACCTENTDEL.DELRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public String getDelRecCode() {
		return DelRecCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PBCRS_REPORT_INSECACCTENTDEL.DELRECCODE
	 * @param DelRecCode  the value for PBCRS_REPORT_INSECACCTENTDEL.DELRECCODE
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	public void setDelRecCode(String DelRecCode) {
		this.DelRecCode = DelRecCode;
	}
}