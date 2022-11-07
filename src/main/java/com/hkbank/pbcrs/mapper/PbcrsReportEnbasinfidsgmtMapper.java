package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidsgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportEnbasinfidsgmtMapper extends BaseExternalSpi {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBASINFIDSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int deleteByPrimaryKey(String IDSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBASINFIDSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int insert(PbcrsReportEnbasinfidsgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBASINFIDSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    PbcrsReportEnbasinfidsgmt selectByPrimaryKey(String IDSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBASINFIDSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    List<PbcrsReportEnbasinfidsgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBASINFIDSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnbasinfidsgmt record);
    
    
    int selectAllbyContCount(Map<String,Object> params);
    
    List<Map<String,Object>> selectAllbyContPage(Map<String,Object> params);
    
    int getKey();
    
    
}