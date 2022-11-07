package com.hkbank.pbcrs.controller.system;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.service.system.UserService;
import com.hkbank.pbcrs.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userManage")
public class UserManageController {

	private static final Logger log = LogManager
			.getLogger(UserManageController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getLoginUserInfo")
	@ResponseBody
	public Map<String, Object> getLoginUserInfo(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession sessoin = request.getSession();
		User user = (User) sessoin.getAttribute("user");
		Map<String, Object> json = null;
		if (user != null) {
			json = user.toJSON();
		}
		return json;
	}

	@RequestMapping(value = "/userList")
	@ResponseBody
	public Map<String, Object> getUserList(HttpServletRequest req,
			HttpServletResponse response) {
		// String page = req.getParameter("page");
		// String rows = req.getParameter("rows");
		String userId = req.getParameter("queryUserId");
		String userName = req.getParameter("queryUserName");
		String orgId = req.getParameter("queryOrgId");
		String authOrgId = req.getParameter("queryAuthOrgId");
		String status = req.getParameter("queryStatus");
		String queryRoleId = req.getParameter("queryRoleId");
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		String loginUserId = user.getUserId();

		// Map<String,Object> map = new HashMap<String,Object>();
		Map<String, Object> querydata = new HashMap<String, Object>();
		querydata.put("userId", userId);
		querydata.put("userid", loginUserId);
		querydata.put("userName", userName);
		querydata.put("orgId", orgId);
		querydata.put("authOrgId", authOrgId);
		querydata.put("status", status);
		querydata.put("queryRoleId", queryRoleId);
		// map.put("total", userSrevice.getAllUserCounts(querydata));
		// map.put("rows", userSrevice.findAllUser(page,rows,querydata));
		// Map<String,Object> map = userSrevice.getUserInfoById(querydata);
		querydata.put("loginOrgId", user.getAuthOrgId());
		int page = Integer.parseInt(req.getParameter("page"));
		int rows = Integer.parseInt(req.getParameter("rows"));
		return userService.getAllUsers(querydata, page, rows);

		// return map;
	}
	
	@RequestMapping(value = "/userListPower")
	@ResponseBody
	public Map<String, Object> getUserListPower(HttpServletRequest req,
			HttpServletResponse response) {
		// String page = req.getParameter("page");
		// String rows = req.getParameter("rows");
		String userId = req.getParameter("queryUserId");
		String userName = req.getParameter("queryUserName");
		String orgId = req.getParameter("queryOrgId");
		String authOrgId = req.getParameter("queryAuthOrgId");
		String status = req.getParameter("queryStatus");
		String queryRoleId = req.getParameter("queryRoleId");
		String queryFuncId =req.getParameter("queryFuncId");
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		String loginUserId = user.getUserId();

		// Map<String,Object> map = new HashMap<String,Object>();
		Map<String, Object> querydata = new HashMap<String, Object>();
		querydata.put("userId", userId);
		querydata.put("userName", userName);
		querydata.put("loginUserId", loginUserId);
		querydata.put("queryFuncId", queryFuncId);
		
		
		// map.put("total", userSrevice.getAllUserCounts(querydata));
		// map.put("rows", userSrevice.findAllUser(page,rows,querydata));
		// Map<String,Object> map = userSrevice.getUserInfoById(querydata);
		//querydata.put("loginOrgId", user.getAuthOrgId());
		int page = Integer.parseInt(req.getParameter("page"));
		int rows = Integer.parseInt(req.getParameter("rows"));
		return userService.getAllUsersPower(querydata, page, rows);

		// return map;
	}

	@RequestMapping(value = "/logOut")
	public Map<String, String> logOut(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, String> rt = new HashMap<String, String>();
		HttpSession session = req.getSession();
		session.removeAttribute("user");
		session.invalidate();
		return rt;
	}

	@RequestMapping(value = "/changPwd")
	@ResponseBody
	public Map<String, String> changePwd(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, String> rt = new HashMap<String, String>();
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		Map<String, Object> userInfo = new HashMap<String, Object>();
		String oldpwd = req.getParameter("oldpwd");
		userInfo.put("USERID", user.getUserId());
		userInfo.put("USERNAME", user.getUserName());
		boolean isValidUser = userService
				.hasMatchUser(user.getUserId(), oldpwd);
		if (!isValidUser) {
			rt.put("success", "");
			rt.put("error", "旧密码填写不正确！");
		} else {
			Date nowDate = new Date(System.currentTimeMillis());
			userInfo.put("PASSWORD", req.getParameter("newpwd"));
			userInfo.put("INPURUSER", user.getUserId());
			userInfo.put("UPDATEUSER", user.getUserId());
			userInfo.put("INPUTDATE", nowDate);
			userInfo.put("UPDATEDATE", nowDate);
			userInfo.put("DELFLAG", "0");
			try {

				userService.changeUserPwd(userInfo);
				rt.put("success", "修改成功");
			} catch (Exception e) {
				log.error(e);
				rt.put("success", "");
				rt.put("error", "修改密码错误请联系系统管理员!");
			}

		}
		return rt;
	}

	@RequestMapping(value = "/saveOrUpdateUser")
	@ResponseBody
	public Map<String, String> saveOrUpdateUserInfo(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, String> rt = new HashMap<String, String>();
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("userid", req.getParameter("userId"));
		userInfo.put("username", req.getParameter("userName"));
		userInfo.put("sex", req.getParameter("sex"));
		userInfo.put("orgid", req.getParameter("orgId"));
		userInfo.put("email", req.getParameter("email"));
		userInfo.put("tel", req.getParameter("telephone"));
		userInfo.put("status", "1");
		userInfo.put("updateuser", user.getUserId());
		userInfo.put("updatedate", new Date(System.currentTimeMillis()));
		userInfo.put("delflag", "0");

		userInfo.put("roleList", req.getParameter("roleList"));
		int hm = 0;
		/*try {
			//hm = userService.hasMutex(req.getParameter("roleList"));
		} catch (Exception e) {
			log.error(e);
			hm = 1;
			rt.put("success", "");
			rt.put("error", "该用户所选角色存在互斥关系，请确认！");
		}*/
		if (hm == 0) {
			String type = req.getParameter("updateType");
			log.debug("待插入的用户信息：{}", userInfo);
			if ("save".equals(type)) {
				int usercounts = userService.getUserCountsById(userInfo);

				if (0 == usercounts) {
					int insertNum = 0;
					try {

						insertNum = userService.saveUserInfo(userInfo);
					} catch (Exception e) {
						log.error(e);
						rt.put("success", "");
						rt.put("error", "用户主表信息插入失败，请联系科技人员！");
					}
					if (1 != insertNum) {
						rt.put("success", "");
						rt.put("error", "用户主表信息插入失败，请联系科技人员！");
					} else {
						try {
							Map<String, Object> passwordInfo = new HashMap<String, Object>();
							passwordInfo.put("USERID", req.getParameter("userId"));
							passwordInfo.put("PASSWORD", req.getParameter("password"));
							passwordInfo.put("UPDATEUSER", req.getParameter("userId"));
							Date nowDate = new Date(System.currentTimeMillis());
							passwordInfo.put("UPDATEDATE", nowDate);
							passwordInfo.put("DELFLAG", "0");
							 userService.newUserPwd(passwordInfo);

						} catch (Exception e) {
							log.error(e);
							rt.put("success", "");
							rt.put("error", "用户密码初始化失败，请联系科技人员！");
						}
						try {
							insertNum = userService.saveUserLog(userInfo);
						} catch (Exception e) {
							log.error(e);
							rt.put("success", "");
							rt.put("error", "用户日志表信息插入失败，请联系科技人员！");
						}
						if (1 != insertNum) {
							rt.put("success", "");
							rt.put("error", "用户日志表信息插入失败，请联系科技人员！");
						} else {
							rt.put("success", "success");
							rt.put("error", "");
						}
					}
				} else {
					rt.put("success", "");
					rt.put("error", "该用户已存在，请检查！");
				}
			} else if ("update".equals(type)) {
				int insertNum = 0;
				try {
					insertNum = userService.updateUserInfo(userInfo);
				} catch (Exception e) {
					log.error("", e);
					rt.put("success", "");
					rt.put("error", "用户主表信息更新失败，请联系科技人员！");
				}
				if (1 != insertNum) {
					log.debug("insertNum=={}", insertNum);
					rt.put("success", "");
					rt.put("error", "用户主表信息更新失败，请联系科技人员！");
				} else {
					try {
						insertNum = userService.saveUserLog(userInfo);
					} catch (Exception e) {
						log.error(e);
						rt.put("success", "");
						rt.put("error", "用户日志表信息插入失败，请联系科技人员！");
					}
					if (1 != insertNum) {
						rt.put("success", "");
						rt.put("error", "用户日志表信息插入失败，请联系科技人员！");
					} else {
						rt.put("success", "success");
						rt.put("error", "");
					}
				}
			}
		}
		return rt;
	}

	@RequestMapping(value = "/delOrChangeUsersStatus")
	@ResponseBody
	public Map<String, String> delOrChangeUsersStatus(HttpServletRequest req,
			HttpServletResponse response) {
		int n = 0;
		Map<String, String> rt = new HashMap<String, String>();
		String type = req.getParameter("type");
		String a = req.getParameter("listdata");
		String[] listdata = a.split(",");
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		String rlt = "";
		if ("del".equals(type)) {
			for (int i = 0; i < listdata.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userid", listdata[i]);
				map.put("updateuser", user.getUserId());
					int dc = 0;
					try {
						dc = userService.deleteUser(map);
					} catch (Exception e) {
						log.error(e);
						rt.put("success", "");
						rt.put("error", "删除用户失败，请联系科技人员！");
					}
					if (dc > 0) {
						n += 1;
					}
			}
			if (n != listdata.length) {
				if (!"".equals(rlt)) {
					rt.put("success", "PartOfSuc");
					rt.put("error",
							"部分用户不能删除：" + rlt.substring(0, rlt.length() - 1)
									+ "，请联系科技人员！");
				} else {
					rt.put("success", "");
					rt.put("error", "删除用户失败，请联系科技人员！");
				}
			} else {
				rt.put("success", "success");
				rt.put("error", "");

			}
		} else if ("chn".equals(type)) {
			for (int i = 0; i < listdata.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userid", listdata[i]);
				map.put("updateuser", user.getUserId());
				try {
					n += userService.changeUserStatus(map);
				} catch (Exception e) {
					log.error(e);
					rt.put("success", "");
					rt.put("error", "修改用户状态失败，请联系科技人员！");
				}
			}
			if (n != listdata.length) {
				rt.put("success", "");
				rt.put("error", "修改用户状态失败，请联系科技人员！");
			} else {

				rt.put("success", "success");
				rt.put("error", "");

			}
		}

		return rt;

	}

	@RequestMapping(value = "/getUserInfoById")
	@ResponseBody
	public Map<String, Object> getUserInfoById(HttpServletRequest req,
			HttpServletResponse response) {
		String userid = req.getParameter("userId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		return userService.getUserInfoById(map);
	}

	@RequestMapping(value = "/newPwd")
	@ResponseBody
	public Map<String, String> newPwd(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, String> rt = new HashMap<String, String>();
		Date nowDate = new Date(System.currentTimeMillis());

		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("USERID", req.getParameter("userId"));
		userInfo.put("PASSWORD", req.getParameter("pw"));
		userInfo.put("UPDATEUSER", req.getParameter("userId"));
		userInfo.put("UPDATEDATE", nowDate);
		userInfo.put("DELFLAG", "0");
		try {
			userService.newUserPwd(userInfo);
			rt.put("RET_CODE", "SUCCESS");
			rt.put("RET_MSG", "修改初密成功");
		} catch (Exception e) {
			log.error("创建用户密码出现异常, 参数列表为{}", userInfo, e);
			rt.put("RET_CODE", "FAILED");
			rt.put("RET_MSG", e.getMessage());
		}

		return rt;
	}
	
	@RequestMapping(value = "/resetPwd")
	@ResponseBody
	public Map<String, String> resetPwd(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, String> rt = new HashMap<String, String>();
		Date nowDate = new Date(System.currentTimeMillis());

		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("USERID", req.getParameter("userId"));
		userInfo.put("PASSWORD", "96e79218965eb72c92a549dd5a330112");
		userInfo.put("UPDATEUSER", req.getParameter("userId"));
		userInfo.put("UPDATEDATE", nowDate);
		userInfo.put("DELFLAG", "0");
		try {
			userService.changeUserPwd(userInfo);
			rt.put("RET_CODE", "SUCCESS");
			rt.put("RET_MSG", "重置密码成功,密码为:111111");
		} catch (Exception e) {
			log.error("修改用户密码出现异常, 参数列表为{}", userInfo, e);
			rt.put("RET_CODE", "FAILED");
			rt.put("error", "重置失败,请联系科技人员!");
		}

		return rt;
	}

	@RequestMapping(value = "/getSysRoleByUserId")
	@ResponseBody
	public List<Map<String, Object>> getSysRoleByUserId(HttpServletRequest req,
			HttpServletResponse response) {
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginUserId", user.getUserId());
		map.put("userId", req.getParameter("userId"));
		map.put("authOrgId", user.getAuthOrgId());
		return userService.getSysRoleByUserId(map);
	}

	@RequestMapping(value = "/getOrgList")
	@ResponseBody
	public Map<String, Object> getOrgList(HttpServletRequest req,
			HttpServletResponse response) {
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getUserId());
		log.debug("orgmap={}", map);
		Map<String, Object> rsl = new HashMap<String, Object>();
		List<Map<String, Object>> qulist = userService.getQueryOrgList(map);
		rsl.put("query", qulist);
		//List<Map<String, Object>> list = userService.getQueryAuthOrgList(map);
		//rsl.put("authQuery", list);
		return rsl;
	}

	/**
	 * 根据角色授权配置情况返回操作许可标志
	 * 
	 * @param req
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/checkDocReset")
	@ResponseBody
	public Map<String, Object> checkDocReset(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(req);

		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		params.put("userId", user.getUserId());
		Map<String, Object> rsl = new HashMap<String, Object>();
		rsl = userService.checkDocReset(params);
		return rsl;
	}

	/**
	 * 根据登录者用户ID确认用户所属机构级别
	 * 
	 * @param req
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/checkLoginUserOrgLev")
	@ResponseBody
	public Map<String, Object> checkLoginUserOrgLev(HttpServletRequest req,
			HttpServletResponse response) {
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		Map<String, Object> rsl = userService.checkLoginUserOrgLev(user);
		return rsl;
	}
}
