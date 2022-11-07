package com.hkbank.pbcrs.dao.system;


import com.hkbank.pbcrs.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NoticeBoradDao {

	@Autowired
	private IBaseDao<Map<String, Object>> baseDaoImpl;

	public List<Map<String, Object>> getNotices(Map<String, Object> params) {
		String sqlStr = "SysNotice.getNotices";
		List<Map<String, Object>> result = baseDaoImpl.selectList(sqlStr,
				params);
		return result;
	}

}