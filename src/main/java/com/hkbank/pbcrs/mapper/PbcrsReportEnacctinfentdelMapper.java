package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnacctinfentdel;

import java.util.List;
import java.util.Map;

public interface PbcrsReportEnacctinfentdelMapper extends BaseExternalSpi {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENACCTINFENTDEL
     *
     * @mbggenerated Fri Mar 15 09:48:59 CST 2019
     */
    int deleteByPrimaryKey(String EnAcctInfEntDelSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENACCTINFENTDEL
     *
     * @mbggenerated Fri Mar 15 09:48:59 CST 2019
     */
    int insert(PbcrsReportEnacctinfentdel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENACCTINFENTDEL
     *
     * @mbggenerated Fri Mar 15 09:48:59 CST 2019
     */
    PbcrsReportEnacctinfentdel selectByPrimaryKey(String EnAcctInfEntDelSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENACCTINFENTDEL
     *
     * @mbggenerated Fri Mar 15 09:48:59 CST 2019
     */
    List<PbcrsReportEnacctinfentdel> selectAll();
    
    List<PbcrsReportEnacctinfentdel> selectByMap(Map map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENACCTINFENTDEL
     *
     * @mbggenerated Fri Mar 15 09:48:59 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnacctinfentdel record);
}