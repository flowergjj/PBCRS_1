package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.FilterData;

import java.util.List;
import java.util.Map;

/**
 * @description: 过滤信息处理mapper接口
 * @author: HS1112
 * @date: 2022-01-20 14:52
 */
public interface FilterDataMapper {
    /**
     * 分页查询列表
     * @param params
     * @return
     */
    List<FilterData> selectList(Map<String,Object> params);

    /**
     * 查询总数
     * @param params
     * @return
     */
    int selectCount(Map<String,Object> params);

    /**
     * 插入数据
     * @param filterData
     * @return
     */
    int insert(FilterData filterData);

    /**
     * 删除信息
     * @param seqNo
     * @return
     */
    int delete(String seqNo);

    /**
     * 根据客户号删除信息
     * @param sourcecustID
     * @return
     */
    int deleteBysourcecustID(String sourcecustID);

    /**
     * 更新信息
     * @param seqNo
     * @return
     */
    int updateIsEnableBySeqno(String seqNo,String isEnable);

    List<FilterData> getList();
}
