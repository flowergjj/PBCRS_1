package com.hkbank.pbcrs.dao.system;


import com.hkbank.pbcrs.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class LoginLogDao {

	@Autowired
	private IBaseDao<Map<String, Object>> baseDaoImpl;

	private static final String SQL_NAMESPACE = "Users.";

	public void insertLoginLog(Map<String, Object> loginLog) {
		String sqlStr = SQL_NAMESPACE + "insertLoginLog";
		baseDaoImpl.save(sqlStr, loginLog);
	}

	public String obtainSysLogSeq() {
		String sqlStr = SQL_NAMESPACE + "obtainSEQ_PBCRS_SYS_LOGGING";
		Map<?, ?> result = baseDaoImpl.selectOne(sqlStr, null);
		String nextval = String.valueOf(result.get("NEXTVAL"));
		return nextval;
	}

	public Map<String, Object> selectLastLogin(Map<String, Object> params) {
		String sqlStr = SQL_NAMESPACE + "selectLastLogin";
		return this.baseDaoImpl.selectOne(sqlStr, params);
	}
}