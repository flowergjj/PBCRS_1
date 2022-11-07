package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportBatch;
import com.hkbank.pbcrs.model.PbcrsReportBatchDel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportBatchMapper {
	public int insert(PbcrsReportBatch pbcrsReportBatch);
	public int checkFileName(Map<String, Object> param);
	 /**
     * 批量更正列表查询
     * @param params
     * @return
     */
	List<Map<String, Object>> selectListPage(Map<String, Object> params);

	int selectListCount(Map<String, Object> params);
	/**
	 * 批量更正列表详情查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectListPageinfo(Map<String, Object> params);

	int selectListCountinfo(Map<String, Object> params);
	
	List<PbcrsReportBatch> listBatchGroupByDataDate(Map<String, Object> params);
	List<PbcrsReportBatch> listBatch(Map<String, Object> params);
	/**
	 * 批量删除插入
	 * @param batchDel
	 * @return
	 */
	public int insertDel(PbcrsReportBatchDel batchDel);
	/**
	 * 批量删除上传时校验文件名是否存在
	 * @param param
	 * @return
	 */
	public int checkFileNameDel(Map<String, Object> param);
	
	 /**
     * 批量删除列表查询
     * @param params
     * @return
     */
	List<Map<String, Object>> selectListPageDel(Map<String, Object> params);

	int selectListCountDel(Map<String, Object> params);
	/**
	 * 批量删除详情查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectListPageinfoDel(Map<String, Object> params);

	int selectListCountinfoDel(Map<String, Object> params);
	/**
	 * 批量删除报送
	 * @param params
	 * @return
	 */
	List<PbcrsReportBatchDel> listBatchDel(Map<String, Object> params);
}
