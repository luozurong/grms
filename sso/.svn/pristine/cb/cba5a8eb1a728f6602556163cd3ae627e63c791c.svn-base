/***********************************************************************
 * Module:  User.java
 * Author:  daihf
 * Purpose: Defines the Class User
 ***********************************************************************/

package com.jlit.uaas.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;

/**
 * 用户信息表
 * 
 * @pdOid 9d4b363c-85ce-49a2-8f6f-9aa37954332e
 */
@Entity(name = "User")
@Table(name = "user")
public class User implements java.io.Serializable {
	/**
	 * 主键
	 * 
	 * @pdOid 5da4829d-df92-491a-bed9-e7f7ee9e69d9
	 */
	private java.lang.String id;
	/**
	 * 用户类型
	 * 
	 * @pdOid 6e636dbf-c7f1-4f25-86fe-0ed28ff966ff
	 */
	private java.lang.String userType;
	/**
	 * 客户的名称
	 * 
	 * @pdOid ccf6e943-4dec-45c4-a6e4-afe8d66e4878
	 */
	private java.lang.String userName;

	/**
	 * 客户的帐号
	 * 
	 * @pdOid a35d6cd5-3ac2-4a51-9e59-3f6f957de030
	 */
	private java.lang.String userAccount;

	/**
	 * 客户的密码
	 * 
	 * @pdOid 7fa5cc18-0119-499d-90b1-4def6671c070
	 */
	private java.lang.String password;
	/**
	 * 手机
	 * 
	 * @pdOid d6e304cd-9bcf-4b18-92f5-038fa4de653c
	 */
	public java.lang.String phone;

	/**
	 * 电子邮箱
	 * 
	 * @pdOid d93d8b1d-2773-4caf-b4c9-b240db9999ea
	 */
	public java.lang.String email;

	/**
	 * 地址
	 * 
	 * @pdOid 91a5b3fc-43c0-4371-83c8-bcd1fc0fecab
	 */
	public java.lang.String address;
	/**
	 * 帐号的状态 0 表示帐号失效 1 表示帐号可用
	 * 
	 * @pdOid 58f6117c-8835-4f5b-b129-f6553e9baa06
	 */
	private java.lang.Integer status = 1;
	/**
	 * 记录创建时间
	 * 
	 * @pdOid bb9913c9-c2af-45ec-92d9-8fad43c28ab2
	 */
	private java.util.Date createTime;

	/**
	 * 上级的ID
	 * 
	 * @pdOid 02e53bed-5226-4524-a210-e43feb95adb3
	 */
	private java.lang.String parentId;
	/**
	 * 角色ID
	 * 
	 * @pdOid a7c8e669-505a-4e31-89bd-8e1fb432e2f9
	 */
	private java.lang.String roleId;
	/**
	 * 级别编号(系统维护 超级管理员默认为000，每多一级加三位)
	 * 
	 * @pdOid 8ff9ccf0-1710-429d-ae3b-5d36b0c3f675
	 */
	private java.lang.String levelNo;

	/**
	 * 性别： 0 未知 1男 2女
	 */
	private java.lang.Integer sex;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 *
	 */
	public User() {
		// TODO Add your own initialization code here.
	}

	/**
	 * Get value of id
	 *
	 * @return id
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "com.jlit.hibernate.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Set value of id
	 *
	 * @param newId
	 */
	public void setId(java.lang.String newId) {
		this.id = newId;
	}

	/**
	 * Get value of userType
	 *
	 * @return userType
	 */
	@Basic(optional = true)
	@Column(name = "user_type", insertable = true, updatable = true, length = 2)
	public java.lang.String getUserType() {
		return userType;
	}

	/**
	 * Set value of userType
	 *
	 * @param newUserType
	 */
	public void setUserType(java.lang.String newUserType) {
		this.userType = newUserType;
	}

	/**
	 * Get value of userName
	 *
	 * @return userName
	 */
	@Basic(optional = true)
	@Column(name = "user_name", insertable = true, updatable = true, length = 64)
	public java.lang.String getUserName() {
		return userName;
	}

	/**
	 * Set value of userName
	 *
	 * @param newUserName
	 */
	public void setUserName(java.lang.String newUserName) {
		this.userName = newUserName;
	}

	/**
	 * Get value of userAccount
	 *
	 * @return userAccount
	 */
	@Basic(optional = true)
	@Column(name = "user_account", insertable = true, updatable = true, length = 30)
	public java.lang.String getUserAccount() {
		return userAccount;
	}

	/**
	 * Set value of userAccount
	 *
	 * @param newUserAccount
	 */
	public void setUserAccount(java.lang.String newUserAccount) {
		this.userAccount = newUserAccount;
	}

	/**
	 * Get value of password
	 *
	 * @return password
	 */
	@Basic(optional = true)
	@Column(name = "password", insertable = true, updatable = true, length = 60)
	public java.lang.String getPassword() {
		return password;
	}

	/**
	 * Set value of password
	 *
	 * @param newPassword
	 */
	public void setPassword(java.lang.String newPassword) {
		this.password = newPassword;
	}

	/**
	 * Get value of phone
	 *
	 * @return phone
	 */
	@Basic(optional = true)
	@Column(name = "phone", insertable = true, updatable = true, length = 20)
	public java.lang.String getPhone() {
		return phone;
	}

	/**
	 * Set value of phone
	 *
	 * @param newPhone
	 */
	public void setPhone(java.lang.String newPhone) {
		this.phone = newPhone;
	}

	/**
	 * Get value of email
	 *
	 * @return email
	 */
	@Basic(optional = true)
	@Column(name = "email", insertable = true, updatable = true, length = 30)
	public java.lang.String getEmail() {
		return email;
	}

	/**
	 * Set value of email
	 *
	 * @param newEmail
	 */
	public void setEmail(java.lang.String newEmail) {
		this.email = newEmail;
	}

	/**
	 * Get value of address
	 *
	 * @return address
	 */
	@Basic(optional = true)
	@Column(name = "address", insertable = true, updatable = true, length = 255)
	public java.lang.String getAddress() {
		return address;
	}

	/**
	 * Set value of address
	 *
	 * @param newAddress
	 */
	public void setAddress(java.lang.String newAddress) {
		this.address = newAddress;
	}

	/**
	 * Get value of createTime
	 *
	 * @return createTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true)
	public java.util.Date getCreateTime() {
		return createTime;
	}

	/**
	 * Set value of createTime
	 *
	 * @param newCreateTime
	 */
	public void setCreateTime(java.util.Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	/**
	 * Get value of parentId
	 *
	 * @return parentId
	 */
	@Basic(optional = true)
	@Column(name = "parent_id", insertable = true, updatable = true, length = 32)
	public java.lang.String getParentId() {
		return parentId;
	}

	/**
	 * Set value of parentId
	 *
	 * @param newParentId
	 */
	public void setParentId(java.lang.String newParentId) {
		this.parentId = newParentId;
	}

	/**
	 * Get value of roleId
	 *
	 * @return roleId
	 */
	@Basic(optional = true)
	@Column(name = "role_id", insertable = true, updatable = true, length = 32)
	public java.lang.String getRoleId() {
		return roleId;
	}

	/**
	 * Set value of roleId
	 *
	 * @param newRoleId
	 */
	public void setRoleId(java.lang.String newRoleId) {
		this.roleId = newRoleId;
	}

	/**
	 * Get value of levelNo
	 *
	 * @return levelNo
	 */
	@Basic(optional = true)
	@Column(name = "level_no", insertable = true, updatable = true, length = 30)
	public java.lang.String getLevelNo() {
		return levelNo;
	}

	/**
	 * Set value of levelNo
	 *
	 * @param newLevelNo
	 */
	public void setLevelNo(java.lang.String newLevelNo) {
		this.levelNo = newLevelNo;
	}

	@Basic(optional = true)
	@Column(name = "status", insertable = true, updatable = true)	
	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		if(null==status){
			status = 1;
		}
		this.status = status;
	}

	@Basic(optional = true)
	@Column(name = "sex", insertable = true, updatable = true)	
	public java.lang.Integer getSex() {
		return sex;
	}

	public void setSex(java.lang.Integer sex) {
		if(null==sex){
			sex = 1;
		}
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userType=" + userType + ", userName=" + userName + ", userAccount=" + userAccount
				+ ", password=" + password + ", phone=" + phone + ", email=" + email + ", address=" + address
				+ ", status=" + status + ", createTime=" + createTime + ", parentId=" + parentId + ", roleId=" + roleId
				+ ", levelNo=" + levelNo + ", sex=" + sex + "]";
	}

	
}