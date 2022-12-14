package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnoricreinfsgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportEnoricreinfsgmtMapper extends BaseExternalSpi {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENORICREINFSGMT
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    int deleteByPrimaryKey(String OrigCreditorInfSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENORICREINFSGMT
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    int insert(PbcrsReportEnoricreinfsgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENORICREINFSGMT
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    PbcrsReportEnoricreinfsgmt selectByPrimaryKey(String OrigCreditorInfSgmtSeqNo);
    
    PbcrsReportEnoricreinfsgmt selectByKey(Map map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENORICREINFSGMT
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    List<PbcrsReportEnoricreinfsgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENORICREINFSGMT
     *
     * @mbggenerated Thu Mar 14 09:27:51 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnoricreinfsgmt record);
    
    int getKey();
	
	

    PbcrsReportEnoricreinfsgmt selectByEnAcctInfSeqNo(String EnAcctInfSeqNo);
    PbcrsReportEnoricreinfsgmt selectByEnAcctInfMSeqNo(String EnAcctInfSeqNo);
}