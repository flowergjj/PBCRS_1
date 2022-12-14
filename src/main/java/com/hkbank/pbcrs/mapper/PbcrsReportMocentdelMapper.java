package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportMocentdel;

import java.util.List;
import java.util.Map;

public interface PbcrsReportMocentdelMapper extends BaseExternalSpi {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOCENTDEL
	 * @mbggenerated  Mon Mar 18 09:50:34 CST 2019
	 */
	int deleteByPrimaryKey(String MoCEntDelSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOCENTDEL
	 * @mbggenerated  Mon Mar 18 09:50:34 CST 2019
	 */
	int insert(PbcrsReportMocentdel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOCENTDEL
	 * @mbggenerated  Mon Mar 18 09:50:34 CST 2019
	 */
	PbcrsReportMocentdel selectByPrimaryKey(String MoCEntDelSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOCENTDEL
	 * @mbggenerated  Mon Mar 18 09:50:34 CST 2019
	 */
	List<PbcrsReportMocentdel> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOCENTDEL
	 * @mbggenerated  Mon Mar 18 09:50:34 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportMocentdel record);

	List<PbcrsReportMocentdel> selectByMap(Map<String, Object> paramMap);
}