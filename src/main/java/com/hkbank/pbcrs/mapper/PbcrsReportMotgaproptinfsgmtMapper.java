package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportMotgaproptinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportPleinf;

import java.util.List;
import java.util.Map;

public interface PbcrsReportMotgaproptinfsgmtMapper extends BaseExternalSpi {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOTGAPROPTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(String MotgaProptInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOTGAPROPTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportMotgaproptinfsgmt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOTGAPROPTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportMotgaproptinfsgmt selectByPrimaryKey(String MotgaProptInfSgmtSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOTGAPROPTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportMotgaproptinfsgmt> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_MOTGAPROPTINFSGMT
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportMotgaproptinfsgmt record);

	

	int getPleInfListTotal(Map<String, Object> param);

	List<PbcrsReportPleinf> getPleInfList(Map<String, Object> param);
}