package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportGuamotcltctrsgmt;

import java.util.List;
import java.util.Map;

public interface PbcrsReportGuamotcltctrsgmtMapper extends BaseExternalSpi{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_GUAMOTCLTCTRSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(String GuaMotCltCtrInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_GUAMOTCLTCTRSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportGuamotcltctrsgmt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_GUAMOTCLTCTRSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportGuamotcltctrsgmt selectByPrimaryKey(String GuaMotCltCtrInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_GUAMOTCLTCTRSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportGuamotcltctrsgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_GUAMOTCLTCTRSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportGuamotcltctrsgmt record);

	int getKey();

	int getByPageCount(Map<String, Object> param);

	List<Map<String, Object>> getByPage(Map<String, Object> param);

	
}