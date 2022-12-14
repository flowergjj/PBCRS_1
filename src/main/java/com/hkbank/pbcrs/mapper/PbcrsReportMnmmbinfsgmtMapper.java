package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportMnmmbinfsgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportMnmmbinfsgmtMapper extends BaseExternalSpi{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_MNMMBINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int deleteByPrimaryKey(String MnMmbInfSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_MNMMBINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int insert(PbcrsReportMnmmbinfsgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_MNMMBINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    PbcrsReportMnmmbinfsgmt selectByPrimaryKey(String MnMmbInfSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_MNMMBINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    List<PbcrsReportMnmmbinfsgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_MNMMBINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportMnmmbinfsgmt record);
    int selectAllbyContCount(Map<String,Object> params);
    
    List<Map<String,Object>> selectAllbyContPage(Map<String,Object> params);
    
    int getKey();
}