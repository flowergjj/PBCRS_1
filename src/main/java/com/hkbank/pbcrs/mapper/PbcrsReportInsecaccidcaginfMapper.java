package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportInsecaccidcaginf;

import java.util.List;

public interface PbcrsReportInsecaccidcaginfMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INSECACCIDCAGINF
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(String InSecAcctIDCagsInfSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INSECACCIDCAGINF
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportInsecaccidcaginf record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INSECACCIDCAGINF
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportInsecaccidcaginf selectByPrimaryKey(String InSecAcctIDCagsInfSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INSECACCIDCAGINF
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportInsecaccidcaginf> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_INSECACCIDCAGINF
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportInsecaccidcaginf record);
	int getKey();
}