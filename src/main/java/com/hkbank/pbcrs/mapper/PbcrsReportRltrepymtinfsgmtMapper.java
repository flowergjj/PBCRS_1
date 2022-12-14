package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportRltrepymtinfsgmt;

import java.util.List;
import java.util.Map;

public interface PbcrsReportRltrepymtinfsgmtMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_RLTREPYMTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(String RltRepymtInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_RLTREPYMTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportRltrepymtinfsgmt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_RLTREPYMTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportRltrepymtinfsgmt selectByPrimaryKey(String RltRepymtInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_RLTREPYMTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportRltrepymtinfsgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_RLTREPYMTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportRltrepymtinfsgmt record);

	int getKey();

	int getRltRepymtInfListTotal(Map<String, Object> param);

	List<Map<String, Object>> getRltRepymtInfList(Map<String, Object> param);

	int deleteByPrimaryKeyM(String RltRepymtInfSgmtSeqNo);

	int insertM(String rltRepymtInfSgmtSeqNo);
	
	
}