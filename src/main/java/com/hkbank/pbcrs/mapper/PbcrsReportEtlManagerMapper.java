package com.hkbank.pbcrs.mapper;

import java.util.List;
import java.util.Map;

public interface PbcrsReportEtlManagerMapper {
	/**
	 * 得到时间下拉框列表
	 * @param params
	 * @return
	 */
   public List<Map<String,Object>> getDataDate(Map<String,Object> params);
   
   /**
    * 得到集市层日志信息
    * @param map
    * @return
    */
   public List<Map<String, Object>> getDsEtlLog(Map<String, Object> map);
   
   /**
    * 得到报送层日志信息
    * @param map
    * @return
    */
   public List<Map<String, Object>> getRptEtlLog(Map<String, Object> map);

   /**
    * 查询checkAll的错误类型种数
    * @param param
    * @return
    */
   public int getCheckAllCount(Map<String, Object> param);

   /**
    * 查询checkAll的错误列表
    * @param param
    * @return
    */
   public List<Map<String, Object>> getCheckAllList(Map<String, Object> param);

   /**
    * 查询错误个数
    * @param param
    * @return
    */
   public int getErrorPKCount(Map<String, Object> param);

   /**
    * 查询错误主键列表
    * @param param
    * @return
    */
   public List<Map<String, Object>> getErrorPKList(Map<String, Object> param);
   
   /**
    * 查询段落时间
    * @param params
    * @return
    */
   public List<Map<String, Object>> getByEtlType(Map<String, Object> params);

   public Map<String, Object> getErrorDate(Map<String, Object> params);

   public List<Map<String, Object>> getCheckByGroup(Map<String, Object> params);
}

