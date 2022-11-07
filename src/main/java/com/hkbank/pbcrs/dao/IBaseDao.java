package com.hkbank.pbcrs.dao;

import com.hkbank.pbcrs.model.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * @author yangbo
 * @category 默认数据访问对象接口
 * @version 0.0.1
 * @date 2012/12/25
 */
public interface IBaseDao<T> {
	/**
	 * 保存
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param params
	 *            SQL语句参数
	 */
	public int save(String sqlName, Object params);

	public int update(String sqlName);

	/**
	 * 更新
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param paramList
	 *            SQL语句参数
	 */
	public int update(String sqlName, Object params);

	public int delete(String sqlName);

	/**
	 * 删除
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param id
	 *            SQL语句参数
	 */
	public int delete(String sqlName, Object params);

	/**
	 * 查询单条记录
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @return 查询结果
	 */
	public T selectOne(String sqlName);

	/**
	 * 查询单条记录
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param params
	 *            SQL语句参数
	 * @return 查询结果
	 */
	public T selectOne(String sqlName, Object params);

	/**
	 * 查询多条记录
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @return 查询结果
	 */
	public List<T> selectList(String sqlName);

	/**
	 * 查询多条记录
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param params
	 *            SQL语句参数
	 * @return 查询结果
	 */
	public List<T> selectList(String sqlName, Object params);
	
	/**
	 * 查询记录数
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param params
	 *            SQL语句参数
	 * @return 查询结果
	 */
	public int selectCount(String sqlName, Object params);

	/**
	 * 查询多条记录
	 * 
	 * @param sqlName
	 *            SQL语句ID
	 * @param params
	 *            SQL语句参数
	 * @param pageNo
	 *            �?��查询的实际页
	 * @param pageSize
	 *            每页记录行数
	 * @return 查询结果
	 */
//	public List<T> selectList(String sqlName, Object params, int pageNo,
//			int pageSize);
	
	/**
	 * 分页查询
	 * 
	 * @param countKey
	 * 			用于查询所有记录数量的SQL语句ID
	 * @param listKey
	 * 			用于查询总记录列表的SQL语句ID
	 * @param params
	 * 			SQL的查询条件
	 * @param pageNo
	 * 			当前页数, 对应JQuery前台传递参数"page"
	 * @param pageSize
	 * 			每页记录数, 对应easyui前台传递参数"rows"
	 * @return
	 * 			含有返回结果及结果数量的Map, 可作为easyui所需的返回值直接return
	 */
	public Map<String, Object> pageQuery(String countKey, String listKey, Object params, int pageNo, int pageSize );

	/**
	 * 清空SESSION缓存，强制从数据库获取最新资料
	 */
	public void clearCache();

	List<SysMenu> selectMenuList(String sqlStr, T params);
}
