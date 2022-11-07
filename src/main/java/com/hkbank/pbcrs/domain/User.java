package com.hkbank.pbcrs.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5257686644744692976L;

	private String sessionId;

	private String serverId;

	private String userId;

	private String userName;

	private String password;

	private int credits;

	private String lastIp;

	private Date lastVisit;

	private String orgId;
	private String orgName;

	private String authOrgId;
	private String authOrgName;

	private String userRole;

	private String orgLev;

	private String valid_code;

	private Date valid_code_time;

	private String valid_code_stat;

	private String tel;

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();

		buff.append("User [");
		buff.append("sessionId=").append(sessionId);
		buff.append(",serverId=").append(serverId);
		buff.append(",userId=").append(userId);
		buff.append(",userName=").append(userName);
		buff.append(",password=").append(password);
		buff.append(",credits=").append(credits);
		buff.append(",lastIp=").append(lastIp);
		buff.append(",lastVisit=").append(lastVisit);
		buff.append(",orgId=").append(orgId);
		buff.append(",orgName=").append(orgName);
		buff.append(",authOrgId=").append(authOrgId);
		buff.append(",authOrgName=").append(authOrgName);
		buff.append(",userRole=").append(userRole);
		buff.append(",orgLev=").append(orgLev);
		buff.append(",valid_code=").append(valid_code);
		buff.append(",valid_code_time=").append(valid_code_time);
		buff.append(",valid_code_stat=").append(valid_code_stat);
		buff.append(",tel=").append(tel);
		buff.append("]");
		return buff.toString();
	}

	public Map<String, Object> toJSON() {

		Map<String, Object> json_data = new HashMap<String, Object>();
		json_data.put("sessionId", sessionId);
		json_data.put("serverId", serverId);
		json_data.put("userId", userId);
		json_data.put("userName", userName);
		json_data.put("password", password);
		json_data.put("credits", credits);
		json_data.put("lastIp", lastIp);
		json_data.put("lastVisit", lastVisit);
		json_data.put("orgId", orgId);
		json_data.put("authOrgId", authOrgId);
		json_data.put("authOrgName", authOrgName);
		json_data.put("userRole", userRole);
		json_data.put("orgLev", orgLev);
		json_data.put("valid_code", valid_code);
		json_data.put("valid_code_time", valid_code_time);
		json_data.put("valid_code_stat", valid_code_stat);

		return json_data;

	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getAuthOrgId() {
		return authOrgId;
	}

	public void setAuthOrgId(String authOrgId) {
		this.authOrgId = authOrgId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getOrgLev() {
		return orgLev;
	}

	public void setOrgLev(String orgLev) {
		this.orgLev = orgLev;
	}

	public String getValid_code() {
		return valid_code;
	}

	public void setValid_code(String valid_code) {
		this.valid_code = valid_code;
	}

	public Date getValid_code_time() {
		return valid_code_time;
	}

	public void setValid_code_time(Date valid_code_time) {
		this.valid_code_time = valid_code_time;
	}

	public String getValid_code_stat() {
		return valid_code_stat;
	}

	public void setValid_code_stat(String valid_code_stat) {
		this.valid_code_stat = valid_code_stat;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAuthOrgName() {
		return authOrgName;
	}

	public void setAuthOrgName(String authOrgName) {
		this.authOrgName = authOrgName;
	}

}
