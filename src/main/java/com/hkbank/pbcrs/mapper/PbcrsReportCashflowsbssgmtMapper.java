package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportCashflowsbssgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportCashflowsbssgmtMapper extends BaseExternalSpi {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_CASHFLOWSBSSGMT
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    int deleteByPrimaryKey(String CashFlowsBsSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_CASHFLOWSBSSGMT
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    int insert(PbcrsReportCashflowsbssgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_CASHFLOWSBSSGMT
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    PbcrsReportCashflowsbssgmt selectByPrimaryKey(String CashFlowsBsSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_CASHFLOWSBSSGMT
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    List<PbcrsReportCashflowsbssgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_CASHFLOWSBSSGMT
     *
     * @mbggenerated Mon Mar 18 10:44:59 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportCashflowsbssgmt record);
    
    int getKey();
    
    int selectAllbyContCount(Map<String,Object> map);
    List<Map<String,Object>> selectAllbyContPage(Map<String,Object> map);
    
    PbcrsReportCashflowsbssgmt selectByCashFlowsSeqNo(String CashFlowsSeqNo);
    
    /**
     * 修改基础段时点为：更新
     * @param param
     * @return
     */
    int updateByKey(Map<String,Object> param);
    /**
     * 修改基础段flag和rptdate
     * @param param
     * @return
     */
    int updateByFlagAndDate(Map<String,Object> params);
	int updateByIsDel(Map<String,Object> param);
}