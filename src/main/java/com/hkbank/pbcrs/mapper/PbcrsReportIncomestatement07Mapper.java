package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportIncomestatement07;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PbcrsReportIncomestatement07Mapper extends BaseExternalSpi {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCOMESTATEMENT07
	 * @mbggenerated  Fri Mar 15 16:25:20 CST 2019
	 */
	int deleteByPrimaryKey(String IncomeStatementPA2007SgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCOMESTATEMENT07
	 * @mbggenerated  Fri Mar 15 16:25:20 CST 2019
	 */
	int insert(PbcrsReportIncomestatement07 record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCOMESTATEMENT07
	 * @mbggenerated  Fri Mar 15 16:25:20 CST 2019
	 */
	PbcrsReportIncomestatement07 selectByPrimaryKey(
			String IncomeStatementPA2007SgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCOMESTATEMENT07
	 * @mbggenerated  Fri Mar 15 16:25:20 CST 2019
	 */
	List<PbcrsReportIncomestatement07> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCOMESTATEMENT07
	 * @mbggenerated  Fri Mar 15 16:25:20 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportIncomestatement07 record);
	
	int getKey();
	PbcrsReportIncomestatement07 selectByIncomeStatementPASeqNo(String IncomeStatementPASeqNo);
}