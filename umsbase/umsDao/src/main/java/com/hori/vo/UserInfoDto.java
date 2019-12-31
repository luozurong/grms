package com.hori.vo;

public class UserInfoDto {
	private String userId;
	
	private String userAccount;
	
	private String name;
	
	private String mobile;
	
	private String roleId;
	
	private String roleName;
	
	private String department;
	
	private String userType;
	
	private String status;
	

	public UserInfoDto() {
		super();
	}

	public UserInfoDto(String userId, String userAccount, String name, String mobile, String roleId, String roleName,
			String department, String userType, String status) {
		super();
		this.userId = userId;
		this.userAccount = userAccount;
		this.name = name;
		this.mobile = mobile;
		this.roleId = roleId;
		this.roleName = roleName;
		this.department = department;
		this.userType = userType;
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
