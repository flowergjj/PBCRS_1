package com.hkbank.pbcrs.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportBatchDelRecordInfoMapper {
    int insert(Map<String,Object> param);
    /**
     * 批量删除操作记录查询
     * @param params
     * @return
     */
	List<Map<String, Object>> selectListPage(Map<String, Object> params);

	int selectListCount(Map<String, Object> params);
	
	void execProc(Map<String, Object> params);
}
