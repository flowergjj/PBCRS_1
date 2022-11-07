package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportSpvathinfsgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface PbcrsReportSpvathinfsgmtMapper extends BaseExternalSpi{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_SPVATHINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int deleteByPrimaryKey(String SpvsgAthrtyInfSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_SPVATHINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int insert(PbcrsReportSpvathinfsgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_SPVATHINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    PbcrsReportSpvathinfsgmt selectByPrimaryKey(String SpvsgAthrtyInfSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_SPVATHINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    List<PbcrsReportSpvathinfsgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_SPVATHINFSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportSpvathinfsgmt record);
    
    int getKey();
    
    Map<String,Object> selectByEnBasInfSeqno(String seqno);
}