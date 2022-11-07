package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsSysReportconfig;

import java.util.List;
import java.util.Map;

public interface PbcrsSysReportconfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_SYS_REPORTCONFIG
     *
     * @mbggenerated Thu Jun 27 16:00:39 CST 2019
     */
    int deleteByPrimaryKey(String REPORTID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_SYS_REPORTCONFIG
     *
     * @mbggenerated Thu Jun 27 16:00:39 CST 2019
     */
    int insert(PbcrsSysReportconfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_SYS_REPORTCONFIG
     *
     * @mbggenerated Thu Jun 27 16:00:39 CST 2019
     */
    PbcrsSysReportconfig selectByPrimaryKey(String REPORTID);
    PbcrsSysReportconfig selectByCode(String REPORTCODE);
    

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_SYS_REPORTCONFIG
     *
     * @mbggenerated Thu Jun 27 16:00:39 CST 2019
     */
    List<PbcrsSysReportconfig> selectAll();

    List<PbcrsSysReportconfig> selectAllOrderByReportIdAsc();
    
    List<PbcrsSysReportconfig> selectAllOrderByReportIdAsc(Map<String,Object> map);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_SYS_REPORTCONFIG
     *
     * @mbggenerated Thu Jun 27 16:00:39 CST 2019
     */
    int updateByPrimaryKey(PbcrsSysReportconfig record);
    
    List<Map<String, Object>> selectAllByRptType(Map<String,Object> map);
    
    Map<String, Object> getFeed(Map<String,Object> map);
    List<Map<String,Object>> getFeedByMsgCode(Map<String,Object> map);
    
    String getSaveDirPath();
    int updateByConfig(String path);
}