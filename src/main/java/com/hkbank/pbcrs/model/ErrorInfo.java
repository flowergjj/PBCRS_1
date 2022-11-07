package com.hkbank.pbcrs.model;

import java.util.List;

public class ErrorInfo {
	private String seqno;
	private String seqnofile;
	private String errinfseqno;
	private String reportseqno;
	private String name;
	private String idnum;
	private String fbcode;
	private String fbmsg;
	private String msgInfo;
	private String state;
	private String infrecType;
	private String rptDate;
	private String custNo;
	private String isFilter;
	private String filterSeqno;
	private List<ErrorInfo> children;


	public String getFilterSeqno() {
		return filterSeqno;
	}

	public void setFilterSeqno(String filterSeqno) {
		this.filterSeqno = filterSeqno;
	}

	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getSeqnofile() {
		return seqnofile;
	}
	public void setSeqnofile(String seqnofile) {
		this.seqnofile = seqnofile;
	}
	
	
	public String getErrinfseqno() {
		return errinfseqno;
	}
	public void setErrinfseqno(String errinfseqno) {
		this.errinfseqno = errinfseqno;
	}
	public String getReportseqno() {
		return reportseqno;
	}
	public void setReportseqno(String reportseqno) {
		this.reportseqno = reportseqno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getFbcode() {
		return fbcode;
	}
	public void setFbcode(String fbcode) {
		this.fbcode = fbcode;
	}
	public String getFbmsg() {
		return fbmsg;
	}
	public void setFbmsg(String fbmsg) {
		this.fbmsg = fbmsg;
	}
	
	public String getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<ErrorInfo> getChildren() {
		return children;
	}
	public void setChildren(List<ErrorInfo> children) {
		this.children = children;
	}
	public String getInfrecType() {
		return infrecType;
	}
	public void setInfrecType(String infrecType) {
		this.infrecType = infrecType;
	}
	public String getRptDate() {
		return rptDate;
	}
	public void setRptDate(String rptDate) {
		this.rptDate = rptDate;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getIsFilter() {
		return isFilter;
	}
	public void setIsFilter(String isFilter) {
		this.isFilter = isFilter;
	}
	
	
}
