package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnbsinfdlt;

import java.util.List;
import java.util.Map;

public interface PbcrsReportEnbsinfdltMapper extends BaseExternalSpi {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBSINFDLT
     *
     * @mbggenerated Fri Mar 15 08:59:15 CST 2019
     */
    int deleteByPrimaryKey(String EnBsInfDltSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBSINFDLT
     *
     * @mbggenerated Fri Mar 15 08:59:15 CST 2019
     */
    int insert(PbcrsReportEnbsinfdlt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBSINFDLT
     *
     * @mbggenerated Fri Mar 15 08:59:15 CST 2019
     */
    PbcrsReportEnbsinfdlt selectByPrimaryKey(String EnBsInfDltSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBSINFDLT
     *
     * @mbggenerated Fri Mar 15 08:59:15 CST 2019
     */
    List<PbcrsReportEnbsinfdlt> selectAll();
    
    List<PbcrsReportEnbsinfdlt> selectByMap(Map map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBSINFDLT
     *
     * @mbggenerated Fri Mar 15 08:59:15 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnbsinfdlt record);
}