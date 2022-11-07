package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportActucotrlinfsgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface PbcrsReportActucotrlinfsgmtMapper extends BaseExternalSpi{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ACTUCOTRLINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int deleteByPrimaryKey(String ActuCotrlInfSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ACTUCOTRLINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int insert(PbcrsReportActucotrlinfsgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ACTUCOTRLINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    PbcrsReportActucotrlinfsgmt selectByPrimaryKey(String ActuCotrlInfSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ACTUCOTRLINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    List<PbcrsReportActucotrlinfsgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ACTUCOTRLINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportActucotrlinfsgmt record);
    
    int selectAllbyContCount(Map<String,Object> params);
    
    List<Map<String,Object>> selectAllbyContPage(Map<String,Object> params);
    
    int getKey();
}