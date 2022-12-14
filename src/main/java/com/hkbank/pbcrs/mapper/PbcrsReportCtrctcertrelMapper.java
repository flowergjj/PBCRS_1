package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportCtrctcertrel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PbcrsReportCtrctcertrelMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_CTRCTCERTREL
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(@Param("CtrctCertRelSgmtSeqNo") String CtrctCertRelSgmtSeqNo,
			@Param("CtrctCertRelSeqNo") String CtrctCertRelSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_CTRCTCERTREL
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportCtrctcertrel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_CTRCTCERTREL
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportCtrctcertrel selectByPrimaryKey(@Param("CtrctCertRelSgmtSeqNo") String CtrctCertRelSgmtSeqNo,
			@Param("CtrctCertRelSeqNo") String CtrctCertRelSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_CTRCTCERTREL
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportCtrctcertrel> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PBCRS_REPORT_CTRCTCERTREL
	 * @mbggenerated  Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportCtrctcertrel record);

	int getKey();

	List<PbcrsReportCtrctcertrel> getByFirstKey(String CtrctCertRelSgmtSeqNo);

	void deleteByPrimaryKeyM(@Param("CtrctCertRelSgmtSeqNo") String CtrctCertRelSgmtSeqNo,
			@Param("CtrctCertRelSeqNo") String CtrctCertRelSeqNo);

	int insertM(@Param("CtrctCertRelSgmtSeqNo")String ctrctCertRelSgmtSeqNo, @Param("CtrctCertRelSeqNo")String ctrctCertRelSeqNo);
}