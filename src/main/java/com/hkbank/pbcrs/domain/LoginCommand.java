package com.hkbank.pbcrs.domain;

public class LoginCommand {
	private String userID;

	private String password;

	/* AD域验证明文密码 */
	private String pw;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
