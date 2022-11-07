package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinfbssgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportEnbasinfbssgmtMapper extends BaseExternalSpi {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBASINFBSSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int deleteByPrimaryKey(String BsSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBASINFBSSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int insert(PbcrsReportEnbasinfbssgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBASINFBSSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    PbcrsReportEnbasinfbssgmt selectByPrimaryKey(String BsSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBASINFBSSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    List<PbcrsReportEnbasinfbssgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENBASINFBSSGMT
     *
     * @mbggenerated Thu Mar 14 15:00:52 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnbasinfbssgmt record);
    
    
    int selectAllbyContCount(Map<String,Object> params);
    
    List<PbcrsReportEnbasinfbssgmt> selectAllbyContPage(Map<String,Object> params);
    
    int getKey();
    
    Map<String,Object> selectByEnBasInfSeqno(String enBasInfSeqno);
    
    /**
     * 修改报送时点
     */
    
    int updateByKey(Map<String,Object> params);
    /**
     * 修改报送时点
     */
    
    int updateByFlagAndDate(Map<String,Object> params);
    /**
     * 修改列表删除状态：已删除
     * @param params
     * @return
     */
    int updateByIsDel(Map<String,Object> params);
}