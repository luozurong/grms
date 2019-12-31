package com.hori.pageModel;

import java.util.List;

import com.hori.model.Author;


/**
 * sessionInfo模型
 * 
 * @author 
 * 
 */
public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = -5233202461793950959L;

	private String userId;// 用户ID
	private String realName;//用户真名
	private String loginName;// 用户登录名称
	private String loginPassword;// 登录密码
	private Byte userType;//用户类型
	private String ip;// IP地址
	private String systemId;//系统ID
	private List<Author> authVos;// 用户拥有的权限
	




	public List<Author> getAuthVos() {
		return authVos;
	}

	public void setAuthVos(List<Author> authVos) {
		this.authVos = authVos;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return loginName;
	}
	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	
	

	
}
