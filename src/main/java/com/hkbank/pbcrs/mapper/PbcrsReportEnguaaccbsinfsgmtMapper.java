package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnguaaccbsinfsgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportEnguaaccbsinfsgmtMapper extends BaseExternalSpi{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENGUAACCBSINFSGMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    int deleteByPrimaryKey(String GuarAcctBsInfSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENGUAACCBSINFSGMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    int insert(PbcrsReportEnguaaccbsinfsgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENGUAACCBSINFSGMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    PbcrsReportEnguaaccbsinfsgmt selectByPrimaryKey(String GuarAcctBsInfSgmtSeqNo);
    
    PbcrsReportEnguaaccbsinfsgmt selectByKey(Map map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENGUAACCBSINFSGMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    List<PbcrsReportEnguaaccbsinfsgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENGUAACCBSINFSGMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnguaaccbsinfsgmt record);
    
    int getKey();
    
    PbcrsReportEnguaaccbsinfsgmt selectByEnSecAcctInfSeqNo(String EnSecAcctInfSeqNo);
    
    PbcrsReportEnguaaccbsinfsgmt selectByEnSecAcctInfMSeqNo(String EnSecAcctInfSeqNo);
    
}