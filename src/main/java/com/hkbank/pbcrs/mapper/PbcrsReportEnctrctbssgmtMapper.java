package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnctrctbssgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportEnctrctbssgmtMapper extends BaseExternalSpi {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENCTRCTBSSGMT
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    int deleteByPrimaryKey(String CtrctBsSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENCTRCTBSSGMT
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    int insert(PbcrsReportEnctrctbssgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENCTRCTBSSGMT
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    PbcrsReportEnctrctbssgmt selectByPrimaryKey(String CtrctBsSgmtSeqNo);
    
    PbcrsReportEnctrctbssgmt selectByKey(Map map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENCTRCTBSSGMT
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    List<PbcrsReportEnctrctbssgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENCTRCTBSSGMT
     *
     * @mbggenerated Fri Mar 15 10:00:01 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnctrctbssgmt record);
    
    int getKey();
    
    int selectAllbyContCount(Map<String,Object> map);
    
    List<Map<String,Object>> selectAllbyContPage(Map<String,Object> map);
    
    PbcrsReportEnctrctbssgmt selectByEnCtrctInfSeqNo(String EnCtrctInfSeqNo);
    
    PbcrsReportEnctrctbssgmt selectByEnCtrctInfMSeqNo(String EnCtrctInfSeqNo);
    int updateByIsDel(Map<String,Object> param);
}