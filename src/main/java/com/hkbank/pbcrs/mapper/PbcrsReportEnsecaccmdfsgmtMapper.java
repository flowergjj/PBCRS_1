package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnsecaccmdfsgmt;

import java.util.List;

public interface PbcrsReportEnsecaccmdfsgmtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCMDFSGMT
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    int deleteByPrimaryKey(String MdfcSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCMDFSGMT
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    int insert(PbcrsReportEnsecaccmdfsgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCMDFSGMT
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    PbcrsReportEnsecaccmdfsgmt selectByPrimaryKey(String MdfcSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCMDFSGMT
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    List<PbcrsReportEnsecaccmdfsgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCMDFSGMT
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnsecaccmdfsgmt record);
    
    int getKey();
}