package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnsecacctmdfc;

import java.util.List;
import java.util.Map;

public interface PbcrsReportEnsecacctmdfcMapper  extends BaseExternalSpi{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCTMDFC
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    int deleteByPrimaryKey(String EnSecAcctMdfcSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCTMDFC
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    int insert(PbcrsReportEnsecacctmdfc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCTMDFC
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    PbcrsReportEnsecacctmdfc selectByPrimaryKey(String EnSecAcctMdfcSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCTMDFC
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    List<PbcrsReportEnsecacctmdfc> selectAll();

    List<PbcrsReportEnsecacctmdfc> selectByMap(Map map);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENSECACCTMDFC
     *
     * @mbggenerated Fri Mar 15 11:18:18 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnsecacctmdfc record);
    
    int getKey();
}