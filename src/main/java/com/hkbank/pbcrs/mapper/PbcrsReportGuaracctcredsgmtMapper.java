package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportGuaracctcredsgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportGuaracctcredsgmtMapper extends BaseExternalSpi{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_GUARACCTCREDSGMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    int deleteByPrimaryKey(String GuarAcctCredSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_GUARACCTCREDSGMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    int insert(PbcrsReportGuaracctcredsgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_GUARACCTCREDSGMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    PbcrsReportGuaracctcredsgmt selectByPrimaryKey(String GuarAcctCredSgmtSeqNo);
    
    PbcrsReportGuaracctcredsgmt selectByKey(Map map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_GUARACCTCREDSGMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    List<PbcrsReportGuaracctcredsgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_GUARACCTCREDSGMT
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportGuaracctcredsgmt record);
    
    int getKey();
    
    PbcrsReportGuaracctcredsgmt selectByEnSecAcctInfSeqNo(String EnSecAcctInfSeqNo);
    
    PbcrsReportGuaracctcredsgmt selectByEnSecAcctInfMSeqNo(String EnSecAcctInfSeqNo);
}