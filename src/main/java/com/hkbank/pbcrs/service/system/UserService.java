package com.hkbank.pbcrs.service.system;

import com.hkbank.pbcrs.dao.system.LoginLogDao;
import com.hkbank.pbcrs.dao.system.UserDao;
import com.hkbank.pbcrs.domain.User;

import com.hkbank.pbcrs.exception.EismException;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.dao.system.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {

	private static Logger log = LogManager.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private LoginLogDao loginLogDao;

	public boolean hasMatchUser(String userId, String password) {
		int matchCount = userDao.getMatchCount(userId, password);
		return matchCount > 0;
	}

	public User findUserByUserID(String userid) {
		return userDao.findUserByUserID(userid);
	}

	public void loginSuccess(User user) {
		// userDao.updateLoginInfo(user);

		String log_id = loginLogDao.obtainSysLogSeq();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> loginLog = new HashMap<String, Object>();
		loginLog.put("LOG_ID", Integer.parseInt(log_id));
		loginLog.put("USER_ID", user.getUserId());
		loginLog.put("SESSION_ID", user.getSessionId());
		loginLog.put("SERVER_ID", user.getServerId());
		loginLog.put("OPER_TIME", sf.format(new Date(System.currentTimeMillis())));
		loginLog.put("OPER_STATUS", "1");
		loginLog.put("FUNC_ID", "USER_LOGIN");
		loginLog.put("CLIENT_IP", user.getLastIp());

		loginLogDao.insertLoginLog(loginLog);
	}

	// public List<Map<?, ?>> findAllUser(String page, String rows,
	// Map<String, Object> querydata) {
	// int p = Integer.parseInt(page);
	// int r = Integer.parseInt(rows);
	// int maxNum = p * r;
	// int minNum = maxNum - r + 1;
	// querydata.put("maxNum", String.valueOf(maxNum));
	// querydata.put("minNum", String.valueOf(minNum));
	// List<Map<?, ?>> userlist = userDao.getAllUser(querydata);
	// return userlist;
	// }

	public Map<String, Object> findAllUser(Object params, int pageNo,
			int pageSize) {
		return userDao.findAllUser(params, pageNo, pageSize);
	}

	public int getAllUserCounts(Map<String, Object> querydata) {
		return userDao.getAllUserCounts(querydata);
	}

	public int getUserCountsById(Map<String, Object> userdata) {
		return userDao.getUserCountsById(userdata);
	}

	public int saveUserInfo(Map<String, Object> userdata) throws Exception {
		int svint = 0;
		int rltint = 0;
		if ("".equals(userdata.get("roleList").toString())) {
			svint = 1;
		} else {
			String[] roleList = userdata.get("roleList").toString().split(",");
			for (int i = 0; i < roleList.length; i++) {
				userdata.put("roleId", roleList[i]);
				svint += userDao.saveUserRole(userdata);
			}
		}

		if (0 < svint) {
			rltint = userDao.createUser(userdata);
		}
		return rltint;
	}

	public int hasMutex(String list) throws Exception {
		int i = 0;
		log.debug("互斥组待处理的数据----------{}", list);
		ArrayList<String> urlist = new ArrayList<String>();
		String[] urs = list.split(",");
		for (int j = 0; j < urs.length; j++) {
			urlist.add(urs[j]);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleList", urlist);
		List<Map<String, Object>> rllist = userDao.hasMutex(map);
		if (0 < rllist.size()) {
			for (int k = 0; k < rllist.size(); k++) {
				String num = rllist.get(k).get("num").toString();
				if (1 < Integer.valueOf(num)) {
					log.debug("所选角色存在互斥关系的数据----------{}", rllist);
					i = 1;
					throw new Exception("所选角色存在互斥关系!");
				}
			}
		}
		// log.debug("互斥组待处理的数据----------{}",rllist.size());
		return i;
	}

	public int saveUserLog(Map<String, Object> userdata) throws Exception {
		String sqno = userDao.getUserLogSeqNo();
		DecimalFormat df = new DecimalFormat("00000000");
		sqno = df.format(Integer.parseInt(sqno));
		Util util = new Util();
		String userLogSeqNO = util.createUserLogNO(sqno);
		userdata.put("seqNo", userLogSeqNO);
		int svint = 0;
		int rltint = 0;

		String[] roleList = userdata.get("roleList").toString().split(",");
		for (int i = 0; i < roleList.length; i++) {
			userdata.put("roleId", roleList[i]);
			svint += userDao.saveUserRoleLog(userdata);
		}

		if (0 < svint) {
			rltint = userDao.saveUserLog(userdata);
		}
		return rltint;
	}

	public int updateUserInfo(Map<String, Object> userdata) throws Exception {

		String sqno = userDao.getUserLogSeqNo();
		DecimalFormat df = new DecimalFormat("00000000");
		sqno = df.format(Integer.parseInt(sqno));
		Util util = new Util();
		String userLogSeqNO = util.createUserLogNO(sqno);
		userdata.put("seqNo", userLogSeqNO);

		int delnum = userDao.getUserRoleCounts(userdata);
		int delint = 0;
		if (delnum > 0) {
			delint = userDao.deleteUserRole(userdata);
		} else {
			delint = 1;
		}
		int rltint = 0;

		if (0 < delint) {
			if ("".equals(userdata.get("roleList").toString())) {
			} else {
				String[] roleList = userdata.get("roleList").toString()
						.split(",");
				for (int i = 0; i < roleList.length; i++) {
					userdata.put("roleId", roleList[i]);
					userDao.saveUserRole(userdata);
					userDao.saveUserRoleLog(userdata);
				}
			}
		}
		rltint = userDao.modifyUser(userdata);

		return rltint;
	}

	public void changeUserPwd(Map<String, Object> userInfo) {
		userDao.changUserPwd(userInfo);
	}

	@SuppressWarnings("unchecked")
	public int changeUserStatus(Map<String, Object> newUserInfo)
			throws Exception {
		int rt = 0;
		Map<String, Object> map = (Map<String, Object>) userDao
				.getUserInfoById(newUserInfo);
		String sqno = userDao.getUserLogSeqNo();
		DecimalFormat df = new DecimalFormat("00000000");
		sqno = df.format(Integer.parseInt(sqno));
		Util util = new Util();
		String userLogSeqNO = util.createUserLogNO(sqno);
		map.put("seqNo", userLogSeqNO);
		Date nowdate = new Date(System.currentTimeMillis());
		newUserInfo.put("USER_STAT_CHG_TIME", nowdate);
		if ("1".equals(map.get("status"))) {
			userDao.disableUser(newUserInfo);
		} else if ("0".equals(map.get("status"))) {
			userDao.enableUser(newUserInfo);
		}
		map.put("updateuser", newUserInfo.get("updateuser"));
		map.put("updatedate", new Date(System.currentTimeMillis()));
		map.put("delflag", "0");
		map.put("status", "1");
		map.put("USER_STAT_CHG_TIME", nowdate);
		int i = userDao.saveUserLog(map);
		rt = i;
		return rt;
	}

	@SuppressWarnings("unchecked")
	public int deleteUser(Map<String, Object> newUserInfo) {
		int rt = 0;
		Map<String, Object> map = (Map<String, Object>) userDao
				.getUserInfoById(newUserInfo);
		// System.out.println(newUserInfo.get("updateuser"));
		map.put("updateuser", newUserInfo.get("updateuser"));
		map.put("updatedate", new Date(System.currentTimeMillis()));
		map.put("delflag", "1");
		String sqno = userDao.getUserLogSeqNo();
		DecimalFormat df = new DecimalFormat("00000000");
		sqno = df.format(Integer.parseInt(sqno));
		Util util = new Util();
		String userLogSeqNO = util.createUserLogNO(sqno);
		map.put("seqNo", userLogSeqNO);
		// System.out.println("map2" + '	' + map);
		int i = userDao.saveUserLog(map);
		if (0 != i) {
			int j = userDao.deleteUser(newUserInfo);
			j += userDao.deleteUserPassword(map);
			j += userDao.deleteUserRole(map);
			rt = j;
		}
		return rt;
	}

	public int getPerEntUserCounts(Map<String, Object> userInfo) {
		int i = 0;
		i += userDao.getEntUserCounts(userInfo);
		i += userDao.getPerUserCounts(userInfo);
		return i;

	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserInfoById(Map<String, Object> userInfo) {
		return (Map<String, Object>) userDao.getUserInfoById(userInfo);
	}

	public Map<String, Object> getAllUsers(Object params, int pageNo,
			int pageSize) {
		return userDao.getAllUsers(params, pageNo, pageSize);
	}
	
	public Map<String, Object> getAllUsersPower(Object params, int pageNo,
			int pageSize) {
		return userDao.getAllUsersPower(params, pageNo, pageSize);
	}

	public int getUserFlagCount(String userId) {
		log.entry("getUserFlagCount()", userId);
		int result = userDao.getUserFlagCount(userId);
		return log.exit(result);
	}

	/**
	 * 校验用户是否为第一次登陆
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isUserFirstLogin(String userId) {
		Map<String, Object> rslt = (Map<String, Object>) userDao
				.getUserPassword(userId);

		if (rslt != null && rslt.size() > 0) {
			return false;
		}

		return true;
	}

	public void newUserPwd(Map<String, Object> userInfo) throws Exception {
		int rslt = userDao.createPasswd(userInfo);
		if (rslt != 1) {
			throw new EismException("保存个人密码出现异常!");
		}

		/*rslt = userDao.createPasswdLog(userInfo);
		if (rslt != 1) {
			throw new EismException("保存个人密码日志出现异常!");
		}*/
	}

	public List<Map<String, Object>> getSysRoleByUserId(
			Map<String, Object> userInfo) {
		Map<String, Object> orglevel = (Map<String, Object>) userDao
				.findOrgLevelById(userInfo);
		
		log.debug("待查询数据={}", userInfo);
		List<Map<String, Object>> all = (List<Map<String, Object>>) userDao
				.getSysRoleByUserId(userInfo);
		// List<Map<String, Object>> notIn = (List<Map<String, Object>>) userDao
		// .getNotInRole(userInfo);
		// for (int i = 0; i < notIn.size(); i++) {
		// for (int j = 0; j < all.size(); j++) {
		// if (notIn.get(i).get("roleId").toString()
		// .equals(all.get(j).get("roleId").toString())) {
		// all.get(j).put("notIn", "T");
		// }
		// }
		// }
		log.debug("处理完成后的角色内容为---------{}", all);
		return all;
	}

	public List<Map<String, Object>> getUserRoleByUserId(
			Map<String, Object> userInfo) {
		return userDao.getUserRoleByUserId(userInfo);
	}

	public List<Map<String, Object>> getOrgList() {
		return userDao.getOrgList();
	}

	public List<Map<String, Object>> getQueryOrgList(
			Map<String, Object> userInfo) {
		return userDao.getQueryOrgList(userInfo);
	}

	public List<Map<String, Object>> getQueryAuthOrgList(
			Map<String, Object> userInfo) {
		return userDao.getQueryAuthOrgList(userInfo);
	}

	public int getUserRoleCounts(Map<String, Object> userdata) {
		return userDao.getUserRoleCounts(userdata);
	}

	public Map<String, Object> checkDocReset(Map<String, Object> params) {
		log.entry("checkDocReset()", params);
		Map<String, Object> rslt = new HashMap<String, Object>();

		List<Map<String, Object>> roles = userDao
				.getDocResetRoleByLoginUser(params);

		// auth_flag!=null有权限进行重置操作，否则没有权限
		if (roles != null && (!roles.isEmpty())) {
			rslt.put("auth_flag", true);
		} else {
			rslt.put("auth_flag", null);
		}

		return rslt;
	}

	public Map<String, Object> checkLoginUserOrgLev(User user) {
		log.entry("checkLoginUserOrgLev()", user);
		Map<String, Object> rslt = new HashMap<String, Object>();

		rslt.put("USER_ORGLEV", user.getOrgLev());
		return rslt;
	}

	public Map<String, Object> saveValidCode(User user,
			Map<String, Object> params) {
		log.entry("saveValidCode()", params);

		Map<String, Object> rslt = new HashMap<String, Object>();
		Object o_identifyingCode = params.get("identifyingCode");
		if (o_identifyingCode == null) {
			rslt.put("RET_CODE", "FAILED");
			rslt.put("RET_MSG", "请填写登录验证码");
			return rslt;
		}
		String identifyingCode = o_identifyingCode.toString();
		String userid = user.getUserId();

		User userinfo = this.userDao.findUserByUserID(userid);

		String i_userid = userinfo.getUserId();
		String i_valid_code = userinfo.getValid_code();
		String i_valid_code_stat = userinfo.getValid_code_stat();
		Date i_valid_code_time = userinfo.getValid_code_time();

		if (!identifyingCode.equals(userinfo.getValid_code())) {
			rslt.put("RET_CODE", "FAILED");
			rslt.put("RET_MSG", "登录验证码不正确，请确认填写的验证码是否正确，或重新获取验证码");
			return rslt;
		}

		if (userinfo.getValid_code_stat() == null
				|| userinfo.getValid_code_time() == null) {
			rslt.put("RET_CODE", "FAILED");
			rslt.put("RET_MSG", "请重新获取验证码后，使用新的验证码登录");
			return rslt;
		}

		// 生成验证码后，默认有效时间为5分钟
		long valid_limit = 1000 * 60 * 5;
		// 验证码使用后，默认有效时间为4小时
		long valid_login_limit = 1000 * 60 * 60 * 4;

		// 提取验证码状态控制配置，确定非验证状态有效时间和验证状态有效时间
		Map<String, Object> valid_conf = this.userDao.getValidTimeConfig();
		Object o_conf = valid_conf.get("SYS_CODE");

		if (o_conf != null) {
			String conf = o_conf.toString();

			String str_valid_limit = null;
			String str_valid_login_limit = null;

			String[] sp_conf = conf.split(",");
			if (sp_conf != null) {
				if (sp_conf.length >= 1) {
					str_valid_limit = sp_conf[0];
				}
				if (sp_conf.length >= 2) {
					str_valid_login_limit = sp_conf[1];
				}
			}

			if (str_valid_limit != null) {
				try {
					// 验证码生成后，使用前，有效时间单位为分钟
					valid_limit = 1000 * 60 * (Long.parseLong(str_valid_limit));
				} catch (Throwable t) {

				}
			}
			if (str_valid_login_limit != null) {
				try {
					// 验证码生成使用后，有效时间单位为分钟
					valid_login_limit = 1000 * 60 * (Long
							.parseLong(str_valid_login_limit));
				} catch (Throwable t) {

				}
			}

		}
		long nowtime = System.currentTimeMillis();
		long valid_time = i_valid_code_time.getTime();

		if ("1".equals(i_valid_code_stat)) {
			if (valid_time + valid_login_limit < nowtime) {
				rslt.put("RET_CODE", "FAILED");
				rslt.put("RET_MSG", "登录验证码已失效，请重新获取验证码");
				return rslt;
			}
		} else {
			if (valid_time + valid_limit < nowtime) {
				rslt.put("RET_CODE", "FAILED");
				rslt.put("RET_MSG", "登录验证码已失效，请重新获取验证码");
				return rslt;
			}
		}

		if (!"1".equals(i_valid_code_stat)) {
			// 校验通过后，更新验证码状态为已验证
			params.put("USERID", i_userid);
			params.put("VALID_CODE", i_valid_code);
			params.put("VALID_CODE_TIME", i_valid_code_time);
			params.put("VALID_CODE_STAT", "1");
			this.userDao.updateUserIdentifyingCode(params);
		}

		rslt.put("RET_CODE", "SUCCESS");
		rslt.put("RET_MSG", "验证成功！正在跳转至首页，请稍候。。。");
		return rslt;
	}
}
