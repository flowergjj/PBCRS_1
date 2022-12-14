package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnrltrepymtinf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportEnrltrepymtinfMapper extends BaseExternalSpi {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENRLTREPYMTINF
     *
     * @mbggenerated Thu Mar 14 09:18:57 CST 2019
     */
    int deleteByPrimaryKey(@Param("RltRepymtInfSgmtSeqNo") String RltRepymtInfSgmtSeqNo, @Param("RltRepymtInfSeqNo") String RltRepymtInfSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENRLTREPYMTINF
     *
     * @mbggenerated Thu Mar 14 09:18:57 CST 2019
     */
    int insert(PbcrsReportEnrltrepymtinf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENRLTREPYMTINF
     *
     * @mbggenerated Thu Mar 14 09:18:57 CST 2019
     */
    PbcrsReportEnrltrepymtinf selectByPrimaryKey(@Param("RltRepymtInfSgmtSeqNo") String RltRepymtInfSgmtSeqNo, @Param("RltRepymtInfSeqNo") String RltRepymtInfSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENRLTREPYMTINF
     *
     * @mbggenerated Thu Mar 14 09:18:57 CST 2019
     */
    List<PbcrsReportEnrltrepymtinf> selectAll();
    
    List<PbcrsReportEnrltrepymtinf> selectByKey(Map map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENRLTREPYMTINF
     *
     * @mbggenerated Thu Mar 14 09:18:57 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnrltrepymtinf record);
    
    int getKey();
    
    List<PbcrsReportEnrltrepymtinf> selectByRltRepymtInfSgmtSeqNo(String RltRepymtInfSgmtSeqNo);
}