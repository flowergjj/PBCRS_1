package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnsecaccmdfbssgmt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PbcrsReportEnsecaccmdfbssgmtMapper extends BaseExternalSpi {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCMDFBSSGMT
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
	int deleteByPrimaryKey(@Param("BsSgmtSeqNo")String BsSgmtSeqNo,@Param("MdfcSgmtCode")String MsgCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCMDFBSSGMT
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    int insert(PbcrsReportEnsecaccmdfbssgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCMDFBSSGMT
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    PbcrsReportEnsecaccmdfbssgmt selectByPrimaryKey(String BsSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCMDFBSSGMT
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    List<PbcrsReportEnsecaccmdfbssgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCMDFBSSGMT
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnsecaccmdfbssgmt record);
    
    int getKey();
}