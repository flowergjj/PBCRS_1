package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportEnaccinfidcaginf;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportEnaccinfidcaginfMapper extends BaseExternalSpi {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ENACCINFIDCAGINF
	 * @mbggenerated  Fri Mar 15 11:11:26 CST 2019
	 */
	int deleteByPrimaryKey(String EnAcctInfIDCagsInfSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ENACCINFIDCAGINF
	 * @mbggenerated  Fri Mar 15 11:11:26 CST 2019
	 */
	int insert(PbcrsReportEnaccinfidcaginf record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ENACCINFIDCAGINF
	 * @mbggenerated  Fri Mar 15 11:11:26 CST 2019
	 */
	PbcrsReportEnaccinfidcaginf selectByPrimaryKey(
			String EnAcctInfIDCagsInfSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ENACCINFIDCAGINF
	 * @mbggenerated  Fri Mar 15 11:11:26 CST 2019
	 */
	List<PbcrsReportEnaccinfidcaginf> selectAll();
	
	List<PbcrsReportEnaccinfidcaginf> selectByMap(Map map);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_ENACCINFIDCAGINF
	 * @mbggenerated  Fri Mar 15 11:11:26 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportEnaccinfidcaginf record);
	
	int getKey();
}