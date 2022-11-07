package com.hkbank.pbcrs.model;

/**
 * @description:
 * @author: Wang
 * @date: 2022-01-20 14:55
 */
public class FilterData {
    private String seqNo;
    private String name;
    private String id;
    private String createDate;
    private String sourcecustID;
    private String createTime;
    private String reason;
    private String isEnable;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSourcecustID() {
        return sourcecustID;
    }

    public void setSourcecustID(String sourcecustID) {
        this.sourcecustID = sourcecustID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }
}
