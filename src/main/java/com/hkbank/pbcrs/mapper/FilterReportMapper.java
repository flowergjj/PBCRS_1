package com.hkbank.pbcrs.mapper;

import java.util.List;
import java.util.Map;

/**
 * @description: 过滤信息查询接口
 * @author: HS1112
 * @date: 2022-01-20 14:52
 */
public interface FilterReportMapper {
    /**
     * 分页查询列表
     * @param params
     * @return
     */
    List<Map<String,Object>> selectList(Map<String,Object> params);

    /**
     * 查询总数
     * @param params
     * @return
     */
    int selectCount(Map<String,Object> params);

    List<Map<String,Object>> selectOne(Map<String,Object> params);

    int updateEnBasinfbBssgmt(Map<String,Object> params);

    int updateEnacctBssgmt(Map<String,Object> params);

    int updateMotcltctrBssgmt(Map<String,Object> params);
}
