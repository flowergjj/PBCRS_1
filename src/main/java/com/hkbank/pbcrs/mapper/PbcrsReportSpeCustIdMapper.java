package com.hkbank.pbcrs.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportSpeCustIdMapper {
    int selectAllbyContCount(Map<String,Object> map);
    
    List<Map<String,Object>> selectAllbyContPage(Map<String,Object> map);
    /**
     * 主键过滤
     * @param map
     * @return
     */
    int SaveCustId(Map<String,Object> map);

    /**
     * 查询是否添加过
     * @param map
     * @return
     */
    int checkCount(Map<String,Object> map);
    
    void delCustId(Map<String,Object> map);
    
}
