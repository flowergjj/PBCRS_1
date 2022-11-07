package com.hkbank.pbcrs.dao.impl;

import com.hkbank.pbcrs.dao.IBaseDao;
import com.hkbank.pbcrs.model.SysMenu;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangbo
 * @category 默认数据访问对象接口展现
 * @version 0.0.1
 * @date 2012/12/26
 */
@Repository
public class BaseDaoImpl<T> extends BaseSessionDaoSupportImpl implements
		IBaseDao<T> {

	@Override
	public void clearCache() {
		SqlSession session = getSqlSession();
		session.clearCache();
	}

	@Override
	public List<SysMenu> selectMenuList(String sqlStr, T params) {
		return  getSqlSession().selectList(sqlStr, params);
	}

	/**
	 * 保存
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param parames
	 *            SQL语句参数
	 */
	public int save(String sqlName, Object parames) {

		return getSqlSession().insert(sqlName, parames);
	}

	public int update(String sqlName) {
		return getSqlSession().update(sqlName);
	}


	public int update(String sqlName, Object parames) {
		return getSqlSession().update(sqlName, parames);
	}

	public int delete(String sqlName) {
		return getSqlSession().delete(sqlName);
	}

	/**
	 * 删除
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param id
	 *            SQL语句参数
	 */
	public int delete(String sqlName, Object parames) {
		return getSqlSession().delete(sqlName, parames);
	}

	/**
	 * 查询单条记录
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @return 查询结果
	 */
	public T selectOne(String sqlName) {
		T rslt = null;
		List<T> datalist = getSqlSession().selectList(sqlName);
		if (datalist != null && (!datalist.isEmpty())) {
			rslt = datalist.get(0);
		}
		return rslt;
	}

	/**
	 * 查询单条记录
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param params
	 *            SQL语句参数
	 * @return 查询结果
	 */
	public T selectOne(String sqlName, Object params) {
		T rslt = null;
		List<T> datalist = getSqlSession().selectList(sqlName, params);
		if (datalist != null && (!datalist.isEmpty())) {
			rslt = datalist.get(0);
		}
		return rslt;
	}

	/**
	 * 查询多条记录
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @return 查询结果
	 */
	public List<T> selectList(String sqlName) {
		return getSqlSession().selectList(sqlName);
	}

	/**
	 * 查询多条记录
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param params
	 *            SQL语句参数
	 * @return 查询结果
	 */
	public List<T> selectList(String sqlName, Object params) {
		return getSqlSession().selectList(sqlName, params);
	}

	/**
	 * 查询记录数
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param params
	 *            SQL语句参数
	 * @return 查询结果
	 */
	public int selectCount(String sqlName, Object params) {
		return (Integer) getSqlSession().selectOne(sqlName, params);
	}

	/**
	 * 
	 * @param countKey
	 * @param listKey
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	// @SuppressWarnings("rawtypes")
	public Map<String, Object> pageQuery(String countKey, String listKey,
			Object params, int pageNo, int pageSize) {
		/* 1. 计算当前记录数 */
		int skip = (pageNo - 1) * pageSize;

		/* 2. 计算总记录数 */
		int total = selectCount(countKey, params);

		/* 3. 计算记录数 , 避免记录数超过 */
		// while (total > 0 && total <= skip) {
		// skip = skip - pageSize;
		// pageNo = pageNo - 1;
		// }
		/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
		List<Map<String, Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String, Object>>();
		} else {
			list = getSqlSession().selectList(listKey, params,
					new RowBounds(skip, pageSize));
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
	}

}
