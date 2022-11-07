package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportIncandexpstasgmt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PbcrsReportIncandexpstasgmtMapper extends BaseExternalSpi {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCANDEXPSTASGMT
	 * @mbggenerated  Tue Apr 09 19:47:24 CST 2019
	 */
	int deleteByPrimaryKey(String IncomeAndESSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCANDEXPSTASGMT
	 * @mbggenerated  Tue Apr 09 19:47:24 CST 2019
	 */
	int insert(PbcrsReportIncandexpstasgmt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCANDEXPSTASGMT
	 * @mbggenerated  Tue Apr 09 19:47:24 CST 2019
	 */
	PbcrsReportIncandexpstasgmt selectByPrimaryKey(String IncomeAndESSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCANDEXPSTASGMT
	 * @mbggenerated  Tue Apr 09 19:47:24 CST 2019
	 */
	List<PbcrsReportIncandexpstasgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INCANDEXPSTASGMT
	 * @mbggenerated  Tue Apr 09 19:47:24 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportIncandexpstasgmt record);
	
	int getKey();
	PbcrsReportIncandexpstasgmt selectByIncomeAndESSeqNo(String IncomeAndESSeqNo);
}