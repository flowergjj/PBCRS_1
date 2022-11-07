package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnaccspetrssgmt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportEnaccspetrssgmtMapper extends BaseExternalSpi{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENACCSPETRSSGMT
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
    int deleteByPrimaryKey(String AcctSpecTrstDspnSgmtSeqNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENACCSPETRSSGMT
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
    int insert(PbcrsReportEnaccspetrssgmt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENACCSPETRSSGMT
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
    PbcrsReportEnaccspetrssgmt selectByPrimaryKey(String AcctSpecTrstDspnSgmtSeqNo);
    
    PbcrsReportEnaccspetrssgmt selectByKey(Map map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENACCSPETRSSGMT
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
    List<PbcrsReportEnaccspetrssgmt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCRS_REPORT_ENACCSPETRSSGMT
     *
     * @mbggenerated Thu Mar 14 09:32:06 CST 2019
     */
    int updateByPrimaryKey(PbcrsReportEnaccspetrssgmt record);
    
    int getKey();
    
	int selectAllbyContCount(Map<String,Object> map);
	int selectAllbyContCountm(Map<String,Object> map);
	
	List<Map<String,Object>> selectAllbyContPage(Map<String,Object> map);
	List<Map<String,Object>> selectAllbyContPagem(Map<String,Object> map);
}