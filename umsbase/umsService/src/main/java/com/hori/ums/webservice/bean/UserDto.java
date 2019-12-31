package com.hori.ums.webservice.bean;

import java.util.List;

public class UserDto {
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 用户账号
	 */
	private String userAccount;
	
	/**
	 * 账号密码
	 */
	private String password;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 角色
	 */
	private String role;
	
	/**
	 * 部门
	 */
	private String department;
	
	/**
	 * 部门
	 */
	private String usesType;

	/**
	 * 用户责任区域
	 */
	private List<String> countrys;
	
	public UserDto() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDto(String userId, String userAccount, String name, String mobile, String role, String department) {
		super();
		this.userId = userId;
		this.userAccount = userAccount;
		this.name = name;
		this.mobile = mobile;
		this.role = role;
		this.department = department;
	}


	public String getUserId() {
		return userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getRole() {
		return role;
	}

	public String getDepartment() {
		return department;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<String> getCountrys() {
		return countrys;
	}

	public void setCountrys(List<String> countrys) {
		this.countrys = countrys;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsesType() {
		return usesType;
	}

	public void setUsesType(String usesType) {
		this.usesType = usesType;
	}
	
}
