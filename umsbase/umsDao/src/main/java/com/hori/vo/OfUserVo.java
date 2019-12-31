package com.hori.vo;

/**
 * openfire 简单封装
 * 
 * @author
 *
 */
public class OfUserVo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2333897091884970527L;
	private String userName;
	private String userAccount;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
