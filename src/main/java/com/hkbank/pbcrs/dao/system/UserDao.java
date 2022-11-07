package com.hkbank.pbcrs.dao.system;


import com.hkbank.pbcrs.dao.IBaseDao;
import com.hkbank.pbcrs.dao.impl.BaseSessionDaoSupportImpl;
import com.hkbank.pbcrs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao extends BaseSessionDaoSupportImpl {

	@Autowired
	private IBaseDao<Map<String, Object>> baseDaoImpl;

	public int getMatchCount(String userId, String password) {
		String sqlStr = "Users.getUserCount";
		Map<String, String> params = new HashMap<String, String>();
		params.put("USERID", userId);
		params.put("PASSWORD", password);
		int i = baseDaoImpl.selectCount(sqlStr, params);
		return i;
	}

	public void changUserPwd(Map<String, Object> userInfo) {
		String upatesqlStr = "Users.modifyUserPassword";
		baseDaoImpl.update(upatesqlStr, userInfo);
		/*String logsqlStr = "Users.createUserPasswordLog";
		baseDaoImpl.save(logsqlStr, userInfo);*/
	}

	public int getEntUserCounts(Map<String, Object> userInfo) {
		int i = baseDaoImpl.selectCount("Users.getEntUserCount", userInfo);
		return i;
	}

	public int getPerUserCounts(Map<String, Object> userInfo) {
		int i = baseDaoImpl.selectCount("Users.getPerUserCount", userInfo);
		return i;
	}

	public User findUserByUserID(final String userid) {
		String sqlStr = "Users.findUserByUserID";
		Map<String, String> params = new HashMap<String, String>();
		params.put("USERID", userid);
		User user = new User();
		user = (User) baseDaoImpl.selectOne(sqlStr, params);
		return user;
	}

	// public void updateLoginInfo(User user) {
	// String sqlStr = "Users.updateLoginInfo";
	// baseDaoImpl.update(sqlStr, user);
	// }

	public int createUser(Map<String, Object> userInfo) {
		String sqlStr = "Users.createUser";
		int i = baseDaoImpl.save(sqlStr, userInfo);
		return i;
	}

	public int saveUserLog(Map<String, Object> userInfo) {
		String sqlStr = "Users.createUserLog";
		int i = baseDaoImpl.save(sqlStr, userInfo);
		return i;
	}

	public int modifyUser(Map<String, Object> newUserInfo) {
		String sqlStr = "Users.modifyUser";
		int i = baseDaoImpl.update(sqlStr, newUserInfo);
		sqlStr = "Users.createUserLog";
		i = baseDaoImpl.save(sqlStr, newUserInfo);
		return i;
	}

	public int enableUser(Map<String, Object> newUserInfo) {
		String sqlStr = "Users.enableUser";
		int i = baseDaoImpl.save(sqlStr, newUserInfo);
		return i;
	}

	public int disableUser(Map<String, Object> newUserInfo) {
		String sqlStr = "Users.disableUser";
		int i = baseDaoImpl.save(sqlStr, newUserInfo);
		return i;
	}

	public int deleteUser(Map<String, Object> newUserInfo) {
		String sqlStr = "Users.deleteUser";
		int i = baseDaoImpl.save(sqlStr, newUserInfo);
		return i;
	}

	public int deleteUserPassword(Map<String, Object> newUserInfo) {
		String sqlStr = "Users.deleteUserPassword";
		int i = baseDaoImpl.save(sqlStr, newUserInfo);
		return i;
	}

	public int deleteUserRole(Map<String, Object> newUserInfo) {
		String sqlStr = "Users.delUserRole";
		int i = baseDaoImpl.delete(sqlStr, newUserInfo);
		return i;
	}

	public Map<String, Object> findAllUser(Object params, int pageNo,
			int pageSize) {
		return baseDaoImpl.pageQuery("Users.getAllUserCounts",
				"Users.findAllUser", params, pageNo, pageSize);
	}

	public List<Map<String, Object>> getAllUser(Map<String, Object> map) {
		List<Map<String, Object>> userlist = baseDaoImpl.selectList(
				"Users.findAllUser", map);
		return userlist;
	}

	public int getAllUserCounts(Map<String, Object> querydata) {
		return baseDaoImpl.selectCount("Users.getAllUserCounts", querydata);
	}

	public int getUserCountsById(Map<String, Object> userdata) {
		return baseDaoImpl.selectCount("Users.getUserCountsById", userdata);
	}

	public Map<?, ?> getUserInfoById(Map<String, Object> userdata) {
		return baseDaoImpl.selectOne("Users.getUserInfoById", userdata);
	}

	public Map<String, Object> getAllUsers(Object params, int pageNo,
			int pageSize) {
		return baseDaoImpl.pageQuery("Users.getAllUserCounts",
				"Users.findAllUserForPage", params, pageNo, pageSize);
	}
	
	public Map<String, Object> getAllUsersPower(Object params, int pageNo,
			int pageSize) {
		return baseDaoImpl.pageQuery("Users.getAllUserCountsPower",
				"Users.findAllUserForPagePower", params, pageNo, pageSize);
	}

	public int getUserFlagCount(String userId) {
		String sqlStr = "Users.getUserFlagCount";
		int i = baseDaoImpl.selectCount(sqlStr, userId);
		return i;
	}

	public Map<?, ?> getUserPassword(String userId) {
		String sqlStr = "Users.getUserPassword";
		return baseDaoImpl.selectOne(sqlStr, userId);
	}

	public int createPasswd(Map<String, Object> userInfo) {
		String sqlStr = "Users.createUserPassword";
		return baseDaoImpl.save(sqlStr, userInfo);
	}

	public int createPasswdLog(Map<String, Object> userInfo) {
		/*String sqlStr = "Users.createUserPasswordLog";
		return baseDaoImpl.save(sqlStr, userInfo);*/
		return 1;
	}

	public List<Map<String, Object>> getSysRoleByUserId(
			Map<String, Object> userInfo) {
		return baseDaoImpl.selectList("Users.getSysRoleByUserId", userInfo);
	}

	public List<Map<String, Object>> getNotInRole(Map<String, Object> userInfo) {
		return baseDaoImpl.selectList("Users.getNotInRole", userInfo);
	}

	public List<Map<String, Object>> hasMutex(Map<String, Object> userInfo) {
		return baseDaoImpl.selectList("Users.getUserRoleGroupMutex", userInfo);
	}

	public List<Map<String, Object>> getUserRoleByUserId(
			Map<String, Object> userInfo) {
		return baseDaoImpl.selectList("Users.getUserRoleByUserId", userInfo);
	}

	public Map<String, Object> findOrgLevelById(Map<String, Object> userInfo) {
		return (Map<String, Object>) baseDaoImpl.selectOne(
				"Users.findOrgLevel", userInfo);

	}

	public int saveUserRole(Map<String, Object> userInfo) {
		String sqlStr = "Users.saveUserRole";
		return baseDaoImpl.save(sqlStr, userInfo);
	}

	public int saveUserRoleLog(Map<String, Object> userInfo) {
		String sqlStr = "Users.saveUserRoleLog";
		return baseDaoImpl.save(sqlStr, userInfo);
	}

	public String getUserLogSeqNo() {
		String sqlStr = "Users.obtainSEQ_PBCRS_SYS_LOGGING";
		String no = String
				.valueOf(baseDaoImpl.selectOne(sqlStr).get("NEXTVAL"));
		return no;
	}

	public List<Map<String, Object>> getOrgList() {
		return baseDaoImpl.selectList("Users.getOrgList");

	}

	public List<Map<String, Object>> getQueryOrgList(
			Map<String, Object> userInfo) {
		return baseDaoImpl.selectList("Users.getQueryOrgList", userInfo);

	}

	public List<Map<String, Object>> getQueryAuthOrgList(
			Map<String, Object> userInfo) {
		return baseDaoImpl.selectList("Users.getQueryAuthOrgList", userInfo);

	}

	public int getUserRoleCounts(Map<String, Object> userInfo) {
		String sqlStr = "Users.getUserRoleCounts";
		return baseDaoImpl.selectCount(sqlStr, userInfo);
	}

	public List<Map<String, Object>> getDocResetRoleByLoginUser(
			Map<String, Object> params) {
		String sqlStr = "Users.getDocResetRoleByLoginUser";
		return baseDaoImpl.selectList(sqlStr, params);
	}

	public Map<String, Object> getValidTimeConfig() {
		String sqlStr = "Users.getValidTimeConfig";
		return baseDaoImpl.selectOne(sqlStr);
	}

	public void updateUserIdentifyingCode(Map<String, Object> params) {
		String sqlStr = "Users.updateUserIdentifyingCode";
		baseDaoImpl.update(sqlStr, params);
	}

}
