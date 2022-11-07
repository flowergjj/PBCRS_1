package com.hkbank.pbcrs.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportModMapper {
	int selectAllbyContCount(Map<String,Object> map);
	
	List<Map<String,Object>> selectAllbyContPage(Map<String,Object> map);

	int selectInListCount(Map<String, Object> params);

	List<Map<String, Object>> selectInListPage(Map<String, Object> params);
	/**
	 * 企业整笔删除
	 * @param map
	 * @return
	 */
	int selectAllbyContCountDel(Map<String,Object> map);
	/**
	 * 企业整笔删除
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectAllbyContPageDel(Map<String,Object> map);
	
	int selectDeleteInListCount(Map<String, Object> params);

	List<Map<String, Object>> selectDeleteInListPage(Map<String, Object> params);


	List<Map<String, Object>> selectUpdSubListPage(Map<String, Object> params);

	int selectUpdSubListCount(Map<String, Object> params);

	
	
	/**
	 * 企业更正
	 * @param map
	 * @return
	 */
	int selectAllbyContCountUpdSub(Map<String,Object> map);
	/**
	 * 企业更正
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectAllbyContPageUpdSub(Map<String,Object> map);

	Map<String,Object> checkMsg(Map<String, Object> params);

	int selectLogInfoCount(Map<String, Object> params);

	List<Map<String, Object>> selectLogInfoPage(Map<String, Object> params);

	int byInParagraphDeleteCount(Map<String, Object> params);

	List<Map<String, Object>> byInParagraphDeleteListPage(Map<String, Object> params);
	
	int byEnParagraphDeleteCount(Map<String, Object> params);

	List<Map<String, Object>> byEnParagraphDeleteListPage(Map<String, Object> params);

}
