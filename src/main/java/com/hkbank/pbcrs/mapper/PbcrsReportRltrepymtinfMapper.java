package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportRltrepymtinf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PbcrsReportRltrepymtinfMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table PBCRS_REPORT_RLTREPYMTINF
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	int deleteByPrimaryKey(@Param("RltRepymtInfSgmtSeqNo") String RltRepymtInfSgmtSeqNo, @Param("RltRepymtInfSeqNo") String RltRepymtInfSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table PBCRS_REPORT_RLTREPYMTINF
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	int insert(PbcrsReportRltrepymtinf record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table PBCRS_REPORT_RLTREPYMTINF
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	PbcrsReportRltrepymtinf selectByPrimaryKey(@Param("RltRepymtInfSgmtSeqNo") String RltRepymtInfSgmtSeqNo, @Param("RltRepymtInfSeqNo") String RltRepymtInfSeqNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table PBCRS_REPORT_RLTREPYMTINF
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	List<PbcrsReportRltrepymtinf> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table PBCRS_REPORT_RLTREPYMTINF
	 * 
	 * @mbggenerated Fri Mar 15 10:54:59 CST 2019
	 */
	int updateByPrimaryKey(PbcrsReportRltrepymtinf record);

	List<PbcrsReportRltrepymtinf> getByFirstKey(@Param("RltRepymtInfSgmtSeqNo") String rltRepymtInfSgmtSeqNo);

	int getKey();

	int deleteByPrimaryKeyM(@Param("RltRepymtInfSgmtSeqNo") String RltRepymtInfSgmtSeqNo, @Param("RltRepymtInfSeqNo") String RltRepymtInfSeqNo);

	int insertM(@Param("RltRepymtInfSgmtSeqNo") String rltRepymtInfSgmtSeqNo, @Param("RltRepymtInfSeqNo") String rltRepymtInfSeqNo);

}