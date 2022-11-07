package com.hkbank.pbcrs.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportBusilinesMappingMapper {
    int selectAllbyContCount(Map<String,Object> map);
    
    List<Map<String,Object>> selectAllbyContPage(Map<String,Object> map);
    /**
     * 码值参数保存
     * @param map
     * @return
     */
    int SaveRes(Map<String,Object> map);
    /**
     * 征信码表保存
     * @param map
     * @return
     */
    int SaveCode(Map<String,Object> map);
    /**
     * 查询是否添加过
     * @param map
     * @return
     */
    int checkCount(Map<String,Object> map);
    
    void delCode(Map<String,Object> map);
    
}
