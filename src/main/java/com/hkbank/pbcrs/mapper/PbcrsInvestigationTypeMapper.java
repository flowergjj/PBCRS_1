package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsInvestigationType;

import java.util.List;

public interface PbcrsInvestigationTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_INVESTIGATION_TYPE
     *
     * @mbggenerated Tue Apr 23 16:35:08 CST 2019
     */
    int insert(PbcrsInvestigationType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_INVESTIGATION_TYPE
     *
     * @mbggenerated Tue Apr 23 16:35:08 CST 2019
     */
    List<PbcrsInvestigationType> selectAll();
    
    /**
     * 根据征信报告类型查询
     * @param type
     * @return
     */
    List<PbcrsInvestigationType> selectByMapperType(PbcrsInvestigationType type);
}