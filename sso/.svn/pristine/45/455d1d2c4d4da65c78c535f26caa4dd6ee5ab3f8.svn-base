package com.hori.vo;

import java.util.List;

public class LoginForSystemLoginVo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 请求处理结果0为正常返回，1用户不存在   2密码不正确，3为用户为停用状态请联系管理员 4 传入参数错误
	 */
	private String result;
	/**
	 * 错误内容
	 */
	private String reason;
	/**
	 * 返回32位token
	 */
	private String token;
	/**
	 * 用户类型(编码)
	 */
	private String userType;
	
	/**
	 * 用户帐号（用户真实帐号不是手机号）
	 */
	private String userAccount;
	/**
	 * 区别超级管理员帐号和一般帐号
	 */
	private String userTypeName;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色Id
	 */
	private String roleId;
	/**
	 * '用户类型（0为业务员，1为业务管理员，2为合同管理员，3为财务管理员，4为社区运营执行管理员，
	 *        5为电商运营管理员，6为用户运营执行管理员，7为媒管执行管理员，8为平台支持人员）'
	 */
	private byte roleType;
	/**
	 * 用户对象信息
	 */
	private UserDetailLoginVo userDetail;
	/**
	 * 数据域（0个人数据，1为部门数据，2为全局数据，全局数据没有用户列表限制）
	 */
	private byte dataArea;
	
	/**
	 *  数据域对应权限的用户列表:
	 *  个人数据：用户可查看自己和下级的数据；
	 *	部门数据：用户可查看自己、下级和机构内同级别的数据；
	 *	全局数据：用户可查看系统全部数据
	 */
	private List<String> userList;
	
	
	/**
	 *  数据域对应权限的部门列表:
	 *  个人数据：用户可查看自己和下级的数据；
	 *	部门数据：用户可查看自己、下级和机构内同级别的数据；
	 *	全局数据：用户可查看系统全部数据
	 */
	private List<String> departList;
	/**
	 * 资源权限对象数组(暂时分为3部分菜单2级，按钮1级)
	 */
	private List<AuthorLoginVo> resourceMenu1; 
	
	private List<AuthorLoginVo> resourceMenu2; 
	
	private List<AuthorLoginVo> resourceButton; 
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    
	public byte getRoleType() {
		return roleType;
	}
	public void setRoleType(byte roleType) {
		this.roleType = roleType;
	}
	public byte getDataArea() {
		return dataArea;
	}
	public void setDataArea(byte dataArea) {
		this.dataArea = dataArea;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public List<AuthorLoginVo> getResourceMenu1() {
		return resourceMenu1;
	}
	public void setResourceMenu1(List<AuthorLoginVo> resourceMenu1) {
		this.resourceMenu1 = resourceMenu1;
	}
	public List<AuthorLoginVo> getResourceMenu2() {
		return resourceMenu2;
	}
	public void setResourceMenu2(List<AuthorLoginVo> resourceMenu2) {
		this.resourceMenu2 = resourceMenu2;
	}
	public List<AuthorLoginVo> getResourceButton() {
		return resourceButton;
	}
	public void setResourceButton(List<AuthorLoginVo> resourceButton) {
		this.resourceButton = resourceButton;
	}
	public UserDetailLoginVo getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetailLoginVo userDetail) {
		this.userDetail = userDetail;
	}
	public List<String> getDepartList() {
		return departList;
	}
	public void setDepartList(List<String> departList) {
		this.departList = departList;
	}
	

}