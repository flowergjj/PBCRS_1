package com.hkbank.pbcrs.service.system;

import com.google.gson.Gson;
import com.hkbank.pbcrs.dao.system.UserDao;

import com.hkbank.pbcrs.exception.EismException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SystemUser {

	@Autowired
	private UserDao userDao;

	public SystemUser() {
		// TODO Auto-generated constructor stub
	}

	public String createUser(Map<String, Object> userInfo, String operuser) {
		String msg = null;

		// 检查输入数据完整性
		try {
			this.checkData(userInfo);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		if (msg != null) {

			Gson gson = new Gson();
			String json = gson.toJson(msg);

			return json;
		}

		// 设置用户状态为启用
		userInfo.put("STATUS", "1");
		// 设置更新人
		userInfo.put("UPDATEUSER", operuser);
		// 设置更新时间
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:SS.ssss");
		String datetime = format.format(new Date(System.currentTimeMillis()));
		userInfo.put("UPDATEDATE", datetime);
		try {
			this.userDao.createUser(userInfo);
		} catch (Exception e) {
			msg = "更新数据库发生错误";
		}

		Gson gson = new Gson();
		String json = gson.toJson(msg);

		return json;
	}

	public String modifyUser(Map<String, Object> newUserInfo, String operuser) {
		String msg = null;

		// 检查输入数据完整性
		try {
			this.checkData(newUserInfo);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		if (msg != null) {

			Gson gson = new Gson();
			String json = gson.toJson(msg);

			return json;
		}

		// 设置更新人
		newUserInfo.put("UPDATEUSER", operuser);
		// 设置更新时间
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:SS.ssss");
		String datetime = format.format(new Date(System.currentTimeMillis()));
		newUserInfo.put("UPDATEDATE", datetime);
		try {
			this.userDao.modifyUser(newUserInfo);
		} catch (Exception e) {
			msg = "更新数据库发生错误";
		}

		Gson gson = new Gson();
		String json = gson.toJson(msg);

		return json;
	}

	public String deleteUser(String userid, String operuser) {
		String msg = null;

		// 检查输入数据完整性
		if (userid == null || "".equals(userid.trim())) {
			msg = "用户ID不能为空";

			Gson gson = new Gson();
			String json = gson.toJson(msg);

			return json;
		}

		// 设置更新人
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("UPDATEUSER", operuser);
		// 设置更新时间
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:SS.ssss");
		String datetime = format.format(new Date(System.currentTimeMillis()));
		userInfo.put("UPDATEDATE", datetime);
		try {
			this.userDao.deleteUser(userInfo);
		} catch (Exception e) {
			msg = "更新数据库发生错误";
		}

		Gson gson = new Gson();
		String json = gson.toJson(msg);

		return json;
	}

	public String disableUser(String userid, String operuser) {
		String msg = null;

		// 检查输入数据完整性
		if (userid == null || "".equals(userid.trim())) {
			msg = "用户ID不能为空";

			Gson gson = new Gson();
			String json = gson.toJson(msg);

			return json;
		}

		// 设置更新人
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("UPDATEUSER", operuser);
		// 设置更新时间
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:SS.ssss");
		String datetime = format.format(new Date(System.currentTimeMillis()));
		userInfo.put("UPDATEDATE", datetime);
		try {
			this.userDao.disableUser(userInfo);
		} catch (Exception e) {
			msg = "更新数据库发生错误";
		}

		Gson gson = new Gson();
		String json = gson.toJson(msg);

		return json;
	}

	public String enableUser(String userid, String operuser) {
		String msg = null;

		// 检查输入数据完整性
		if (userid == null || "".equals(userid.trim())) {
			msg = "用户ID不能为空";

			Gson gson = new Gson();
			String json = gson.toJson(msg);

			return json;
		}

		// 设置更新人
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("UPDATEUSER", operuser);
		// 设置更新时间
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:SS.ssss");
		String datetime = format.format(new Date(System.currentTimeMillis()));
		userInfo.put("UPDATEDATE", datetime);
		try {
			this.userDao.enableUser(userInfo);
		} catch (Exception e) {
			msg = "更新数据库发生错误";
		}

		Gson gson = new Gson();
		String json = gson.toJson(msg);

		return json;
	}

	private void checkData(Map<String, Object> userInfo) throws EismException {
		String userid = (String) userInfo.get("USERID");
		String username = (String) userInfo.get("USERNAME");
		String orgid = (String) userInfo.get("ORGID");

		if (userid == null || "".equals(userid.trim())) {
			throw new EismException("用户ID不能为空");
		}

		if (username == null || "".equals(username.trim())) {
			throw new EismException("用户名称不能为空");
		}

		if (orgid == null || "".equals(orgid.trim())) {
			throw new EismException("用户所属机构不能为空");
		}

	}
}
