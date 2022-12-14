package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportGserltrepymtinf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportGserltrepymtinfMapper extends BaseExternalSpi{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_GSERLTREPYMTINF
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    int deleteByPrimaryKey(@Param("RltRepymtInfSgmtSeqNo") String RltRepymtInfSgmtSeqNo, @Param("RltRepymtInfSeqNo") String RltRepymtInfSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_GSERLTREPYMTINF
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    int insert(PbcrsReportGserltrepymtinf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_GSERLTREPYMTINF
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    PbcrsReportGserltrepymtinf selectByPrimaryKey(@Param("RltRepymtInfSgmtSeqNo") String RltRepymtInfSgmtSeqNo, @Param("RltRepymtInfSeqNo") String RltRepymtInfSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_GSERLTREPYMTINF
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    List<PbcrsReportGserltrepymtinf> selectAll();
    
    List<PbcrsReportGserltrepymtinf> selectByMap(Map map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_GSERLTREPYMTINF
     *
     * @mbggenerated Thu Mar 14 16:52:51 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportGserltrepymtinf record);
    
    int getKey();
    
    List<PbcrsReportGserltrepymtinf> selectByRltRepymtInfSgmtSeqNo(String RltRepymtInfSgmtSeqNo);
    
}