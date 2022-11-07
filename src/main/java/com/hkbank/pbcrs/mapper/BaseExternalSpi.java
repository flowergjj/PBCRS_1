package com.hkbank.pbcrs.mapper;

import java.util.Map;

/**
 * 调用外部接口实现类
 * 
 */
public interface BaseExternalSpi {
	Object  selectByPrimaryKey(String seqId);
	
	int deleteByPrimaryKeyM(String seqId);
	
	int insertM(String selectStr);
	/**
	 * 根据系统来源以及时间修改reportflag=1
	 * @param map
	 * @return
	 */
	int updateByMap(Map map);
	
	
	/**
	 * 更正段
	 * 根据系统来源以及时间修改reportflag=1
	 * @param map
	 * @return
	 */
	int updateByMapMdc(Map map);
}
