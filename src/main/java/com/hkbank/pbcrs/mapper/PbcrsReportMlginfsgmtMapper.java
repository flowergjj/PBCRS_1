package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportMlginfsgmt;

import java.util.List;

public interface PbcrsReportMlginfsgmtMapper extends BaseExternalSpi{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MLGINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int deleteByPrimaryKey(String MlgInfSgmtSeqNo);
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MLGINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int insert(PbcrsReportMlginfsgmt record);
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MLGINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	PbcrsReportMlginfsgmt selectByPrimaryKey(String MlgInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MLGINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	List<PbcrsReportMlginfsgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MLGINFSGMT
	 * @mbggenerated  Tue Mar 26 16:20:32 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportMlginfsgmt record);

	int getKey();
}