package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportComrecinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportOtrrec;

import java.util.List;
import java.util.Map;

public interface PbcrsReportComrecinfsgmtMapper extends BaseExternalSpi{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_COMRECINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(String ComRecInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_COMRECINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportComrecinfsgmt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_COMRECINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportComrecinfsgmt selectByPrimaryKey(String ComRecInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_COMRECINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportComrecinfsgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_COMRECINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportComrecinfsgmt record);

	

	int getOtrRecListTotal(Map<String, Object> param);

	List<PbcrsReportOtrrec> getOtrRecList(Map<String, Object> param);
}