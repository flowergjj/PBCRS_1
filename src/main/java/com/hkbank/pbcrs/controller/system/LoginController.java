package com.hkbank.pbcrs.controller.system;


import com.hkbank.pbcrs.cache.CacheManager;
import com.hkbank.pbcrs.domain.LoginCommand;
import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.service.system.UserService;
import com.hkbank.pbcrs.util.Util;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class LoginController {

	private static Logger log = LogManager.getLogger(LoginController.class);

	static {
		try {
			if (!CacheManager.hasCache(Util.KEY_LOGIN)) {
				CacheManager.putCache(Util.KEY_LOGIN,
						Resources.getResourceAsProperties(Util.CONFIG_LOGIN));
			}
		} catch (IOException e) {
			log.error("加载配置文件[{}]失败;", Util.CONFIG_LOGIN, e);
		}
	}

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/")
	public String loginPage() {
		return "forward:/index.jsp";
	}

	/**
	 * TODO 存在浏览器共享session的情况, 针对该情况, 目前进行控制的逻辑如下:(学习目前行内PPM做法)
	 * 
	 * 1. 验证当前登陆用户是否被锁定, 若锁定, 则直接打回并提示信息, 若无锁定进入2; 2. 验证用户名密码是否正确, 若不正确,
	 * 则打回并提示信息, 若正确则进入3; 3. 判断当前登陆者session, 若不存在, 将用户信息记入缓存并返回主页面; 若存在,
	 * 判断与当前登陆用户输入的用户名是否一致: 3.1) 若一致, 用户直接登陆并记录登陆log; 3.2) 若不一致,
	 * 提示当前用户重新打开浏览器再进行登陆;
	 * 
	 * @param request
	 * @param loginCommand
	 * @return
	 */
	@RequestMapping(value = "/loginCheck")
	public ModelAndView loginCheck(HttpServletRequest request,
			LoginCommand loginCommand) {

		/* 初始化参数 */
		log.debug("UserId=[{}]; Password=[{}]; pw=[{}]",
				loginCommand.getUserID(), loginCommand.getPassword(),
				loginCommand.getPw());
		Properties loginCfg = (Properties) CacheManager
				.getCache(Util.KEY_LOGIN);
		log.debug("登陆配置Properties={}", loginCfg);
		String loginMethod = loginCfg.getProperty("login.method");
		boolean isValidUser = false;

		/* 1. 验证用户是否被锁定 */
		int userflag = userService.getUserFlagCount(loginCommand.getUserID());
		log.debug("userflag={}", userflag);
		if (userflag <= 0) {
			return new ModelAndView("login", "error", "该用户已禁用，请联系管理员！");
		}

		/* 验证用户登录 */
		/* AD域认证 */
		if ("AD".equals(loginMethod)) {
			try {
				log.debug("AD验证, 用户=[{}], 密码=[{}]", loginCommand.getUserID(),
						loginCommand.getPw());
				isValidUser = Util.verifyAD(loginCommand.getUserID(),
						loginCommand.getPw());
			} catch (Throwable e) {
				log.error(e);
				isValidUser = false;
			}
		} else {
			/* 2015-5-25,add,junyuli; 添加用户是否为首次登陆的判断, 不校验密码, 提示用户修改密码 */
			if (userService.isUserFirstLogin(loginCommand.getUserID())) {
				return new ModelAndView("redirect:/jsp/modify.jsp", "user",
						loginCommand.getUserID());
			}
			isValidUser = userService.hasMatchUser(loginCommand.getUserID(),
					loginCommand.getPassword());
		}
		log.debug("isValidUser=={}", isValidUser);
		/* 判断验证结果 */
		if (!isValidUser) {
			return new ModelAndView("login", "error", "用户号或密码错！");
		}

		/* 检查用户与角色的关联关系，并返回相关信息 */
		Map<String, Object> userdata = new HashMap<String, Object>();
		userdata.put("userid", loginCommand.getUserID());
		String userrolename = "";
		int userRoleCount = userService.getUserRoleCounts(userdata);
		if (0 == userRoleCount) {
			return new ModelAndView("login", "error",
					"该用户没有分配相关角色及权限，请联系系统管理员进行处理！");
		}

		/* 用户登陆成功, 记录session及登录log */
		HttpSession session = request.getSession();
		User user = new User();
		user = userService.findUserByUserID(loginCommand.getUserID());
		String str = request.getRemoteAddr();
		user.setSessionId(session.getId());
		user.setLastIp(str);
		user.setLastVisit(new Date(System.currentTimeMillis()));
		userService.loginSuccess(user);
		userdata.put("authOrgId", user.getAuthOrgId());
		List<Map<String, Object>> userrolelist = userService
				.getUserRoleByUserId(userdata);
		boolean valid_code_flag = false;
		for (int i = 0; i < userrolelist.size(); i++) {
			if (loginCommand.getUserID().equals(
					userrolelist.get(i).get("userId"))) {
				// log.debug("用户角色循环：{},判断值为：{}",
				// userrolelist.get(i).get("userId"),"".equals(userrolelist.get(i).get("userId")));
				Map<String, Object> userrole = userrolelist.get(i);
				userrolename += userrole.get("roleName") + ",";
				if ("1".equals(userrole.get("VALID_CODE_FLAG"))) {
					valid_code_flag = true;
				}
			}
		}

		userrolename = userrolename.substring(0, userrolename.length() - 1);

		user.setUserRole(userrolename);
		String url = (String) session.getAttribute("toUrl");
		log.debug("Session内的user对象={}， toUrl=[{}]", user, url);
		request.getSession().setAttribute("user", user);
		log.debug("用户登录成功!");

		if (valid_code_flag == true) {
//			request.getSession().setAttribute(AuthorFilter.VALID_CODE_FLAG_ID,
//					AuthorFilter.VALID_CODE_FLAG_ID);
			return new ModelAndView("redirect:/jsp/identifyingCode.jsp");
		} else {
//			request.getSession().removeAttribute(
//					AuthorFilter.VALID_CODE_FLAG_ID);
			return new ModelAndView("redirect:/jsp/main.jsp");
		}

	}

}
