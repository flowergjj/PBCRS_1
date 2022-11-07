/**
 * 
 */
package com.hkbank.pbcrs.filter;


import com.hkbank.pbcrs.dao.system.LoginLogDao;
import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.service.system.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.jws.soap.InitParam;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hs1029
 * 
 */
@WebFilter(filterName = "authorFilter", urlPatterns = "/*",
		initParams = {@WebInitParam(name = "VALIDURI", value = "/;/index.jsp;" +
				"/error.jsp;" +
				"/loginCheck.html;" +
				"/ReportCmd/start.html;" +
				"/identifyingCodeManage/obtainValidCode.html;" +
				"/identifyingCodeManage/saveValidCode.html;" +
				"/saveValidCode.html;" +
				"/header.jsp;" +
				"/jsp/modify.jsp;" +
				"/services;" +
				"/userManage/newPwd.html;" +
				".*\\.js;" +
				".*\\.css;" +
				".*\\.png;" +
				".*\\.gif;" +
				".*\\.jpg;"),
				@WebInitParam(name = "authorFlagId" ,value = "user"),
				@WebInitParam(name = "authorVerifyPage" ,value = "/error.jsp"),
				@WebInitParam(name = "sessionCtrl" ,value = "false")
		})
public class AuthorFilter implements Filter {
	@Autowired
	private ApplicationContext applicationContext;

	private Logger log = LogManager.getLogger(AuthorFilter.class);
	@Autowired
	private UserService userService;



	/**
	 * 
	 */
	public AuthorFilter() {
		// 借用服务器启动认证过滤器的机会，强行设置Date类型时区
		//TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		// 允许HTTPServletRequest通过，阻止其他请求
		if (request instanceof HttpServletRequest) {
			
			
			
			//ServletContext cont = ((HttpServletRequest) request).getSession().getServletContext();
			//XmlWebApplicationContext cxt=(XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(cont);
			//UserService userService =(UserService)cxt.getBean("userService");
			//int userflag = userService.getUserFlagCount("105138");
			// 请求识别，对于指定请求不做校验，其他请求全部需要进行session校验
			HttpServletRequest httpRequest = (HttpServletRequest) request;

			String servletPath = httpRequest.getServletPath();

			log.debug("servletPath=={}", servletPath);

			if (this.validUri != null) {
				for (Pattern confUri : this.validUri) {
					Matcher ma = confUri.matcher(servletPath);
					// log.debug("[{}].matches.[{}]", confUri, servletPath);
					if (ma.matches()) {
						 log.info("[{}].matches.[{}]:match successful.",
								 servletPath, confUri);
						if (filterChain != null) {
							filterChain.doFilter(request, response);
						}
						return;
					}
					// log.debug("[{}].matches.[{}]:match failed.", servletPath,
					// confUri);
				}
			}

			// 从HTTPServletRequest中获取HTTPSession
			HttpSession httpSession = httpRequest.getSession();

			// 确认应用上下文中当前Session的合法性
			// 未检出有效session则当前session为非法

			Object authorflag = httpSession.getAttribute(this.authorFlagId);

			// log.debug("check session:{}", authorflag);

			if (authorflag == null || (!(authorflag instanceof User))) {

				RequestDispatcher dispatcher = request
						.getRequestDispatcher(this.authorVerifyPage);

				dispatcher.forward(request, response);

				return;
			} else {

				User user = (User) authorflag;

				if (true == this.sessionCtrl) {

					LoginLogDao loginLogDao = (LoginLogDao) applicationContext
							.getBean("loginLogDao");

					Map<String, Object> params = new HashMap<String, Object>();
					params.put("USER_ID", user.getUserId());
					Map<String, Object> lastLogin = loginLogDao
							.selectLastLogin(params);

					Object o_session_id = null;
					if (lastLogin != null) {
						o_session_id = lastLogin.get("SESSION_ID");
					}

					String last_login_session_id = null;
					if (o_session_id != null) {
						last_login_session_id = o_session_id.toString();
					}

					String now_login_session_id = ((HttpServletRequest) request)
							.getSession().getId();

					if (last_login_session_id == null
							|| (!now_login_session_id
									.equals(last_login_session_id))) {
						// 同最后一个登陆session不同，当前账号已在其他入口使用，需重新登录
						RequestDispatcher dispatcher = request
								.getRequestDispatcher(this.authorVerifyPage);

						dispatcher.forward(request, response);

						return;

					}
				}

				// 检查用户session和登录验证码均未进行异常跳转时，交易正常进行
				if (filterChain != null) {
					filterChain.doFilter(request, response);
				}
				return;

			}

		} else {
			// 非HTTPServletRequest，拒绝请求

			RequestDispatcher dispatcher = request
					.getRequestDispatcher(this.authorVerifyPage);

			dispatcher.forward(request, response);

			return;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 服务器启动的时候读取并保存配置信息
		Enumeration<?> paramNames = filterConfig.getInitParameterNames();

		parameters = new HashMap<String, String>();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement().toString();
			parameters.put(paramName, filterConfig.getInitParameter(paramName));
		}

		log.debug("filterConfig is [{}]", parameters);

		String uris = parameters.get("VALIDURI");
		if (uris != null) {
			String[] validUriStr = uris.split(";");
			validUri = new Pattern[validUriStr.length];
			for (int i = 0; i < validUriStr.length; i++) {
				validUriStr[i] = validUriStr[i].trim();
				validUri[i] = Pattern.compile(validUriStr[i],
						Pattern.CASE_INSENSITIVE);
				// log.debug("validUri[{}]=={}", i, validUri[i]);
			}
		}

		String t_authorFlagId = parameters.get("authorFlagId");
		if (t_authorFlagId != null) {
			this.authorFlagId = t_authorFlagId;
		}
		String t_authorVerifyPage = parameters.get("authorVerifyPage");
		if (t_authorVerifyPage != null) {
			this.authorVerifyPage = t_authorVerifyPage;
		}
		// String t_identifyingCodePage = parameters.get("identifyingCodePage");
		// if (t_identifyingCodePage != null) {
		// this.identifyingCodePage = t_identifyingCodePage;
		// }
		this.sessionCtrl = true;
		try {
			sessionCtrl = Boolean.parseBoolean(parameters.get("sessionCtrl"));
		} catch (Throwable t) {
			sessionCtrl = true;
		}

	}

	private Map<String, String> parameters = null;

	private Pattern[] validUri = null;

	private String authorVerifyPage = "/error.jsp";

	private String authorFlagId = "LOGINPERMITTED";
	public static final String VALID_CODE_FLAG_ID = "VALIDCODEFLAGID";

	// private String identifyingCodePage = "/jsp/identifyingCode.jsp";

	private boolean sessionCtrl = true;

}
