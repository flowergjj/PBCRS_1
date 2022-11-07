package com.hkbank.pbcrs.dao.system;

import com.hkbank.pbcrs.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SysComboBoxDataDao {

	private static final String SQL_NAMESPACE = "SysComboBox.";

	@Autowired
	private IBaseDao<Map<String, Object>> baseDaoImpl;

	public List<Map<String, Object>> getComboboxDataList(
			Map<String, Object> params) {
		String sqlStr = SQL_NAMESPACE + "getComboboxDataList";
		return baseDaoImpl.selectList(sqlStr, params);
	}

	public List<Map<String, Object>> getBussTypeList(Map<String, Object> params) {
		String sqlStr = SQL_NAMESPACE + "getBussTypeList";
		return baseDaoImpl.selectList(sqlStr, params);
	}
	
	public List<Map<String, Object>> getChannelList(Map<String, Object> params) {
		String sqlStr = SQL_NAMESPACE + "getChannelList";
		return baseDaoImpl.selectList(sqlStr, params);
	}

}
