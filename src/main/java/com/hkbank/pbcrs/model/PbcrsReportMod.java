package com.hkbank.pbcrs.model;

public class PbcrsReportMod {
	private String ReportModId;
	private String InfRecType;
	private String entName;
	private String entCertType;
	private String entCertNum;
	private String rptDate;
	public String getReportModId() {
		return ReportModId;
	}
	public void setReportModId(String reportModId) {
		ReportModId = reportModId;
	}
	public String getInfRecType() {
		return InfRecType;
	}
	public void setInfRecType(String infRecType) {
		InfRecType = infRecType;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getEntCertType() {
		return entCertType;
	}
	public void setEntCertType(String entCertType) {
		this.entCertType = entCertType;
	}
	public String getEntCertNum() {
		return entCertNum;
	}
	public void setEntCertNum(String entCertNum) {
		this.entCertNum = entCertNum;
	}
	public String getRptDate() {
		return rptDate;
	}
	public void setRptDate(String rptDate) {
		this.rptDate = rptDate;
	}
	

}
