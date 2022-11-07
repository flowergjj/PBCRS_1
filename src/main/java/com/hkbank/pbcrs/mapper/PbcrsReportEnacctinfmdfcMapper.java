package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnacctinfmdfc;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportEnacctinfmdfcMapper extends BaseExternalSpi {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ENACCTINFMDFC
	 * @mbggenerated  Fri Mar 15 09:36:37 CST 2019
	 */
	int deleteByPrimaryKey(String EnAcctInfMdfcSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ENACCTINFMDFC
	 * @mbggenerated  Fri Mar 15 09:36:37 CST 2019
	 */
	int insert(PbcrsReportEnacctinfmdfc record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ENACCTINFMDFC
	 * @mbggenerated  Fri Mar 15 09:36:37 CST 2019
	 */
	PbcrsReportEnacctinfmdfc selectByPrimaryKey(String EnAcctInfMdfcSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ENACCTINFMDFC
	 * @mbggenerated  Fri Mar 15 09:36:37 CST 2019
	 */
	List<PbcrsReportEnacctinfmdfc> selectAll();
	
	List<PbcrsReportEnacctinfmdfc> selectByMap(Map map);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ENACCTINFMDFC
	 * @mbggenerated  Fri Mar 15 09:36:37 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportEnacctinfmdfc record);
	int getKey();
}