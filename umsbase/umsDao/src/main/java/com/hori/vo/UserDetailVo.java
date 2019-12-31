package com.hori.vo;

import java.util.List;

public class UserDetailVo {
	
	private String userId;
	
	private String userAccount;
	
	private String name;
	
	private String mobile;
	
	private String role;
	
	private String department;

	private String password;
	
	private List<String> countrys;
	
	public UserDetailVo() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDetailVo(String userId, String userAccount, String name, String mobile, String role, String department) {
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
}
