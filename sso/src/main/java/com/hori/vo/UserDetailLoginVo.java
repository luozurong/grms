package com.hori.vo;

import java.util.Date;

public class UserDetailLoginVo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户资料ID
	 */
	private String userDetailId;
	/**
	 * 部门ID
	 */
	private String departId;
	/**
	 * 部门名称
	 */
	private String departName;
	/**
	 * 用户ID(保留暂时用不到)
	 */
	private String userId;
	/**
	 * 性别
	 */
	private Character sex;
	/**
	 * 职位
	 */
	private String post;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 用户昵称
	 */
	private String nickname;
	private Date createTime;
	private Date modifiedTime;
	private String note;

	public UserDetailLoginVo() {
	}

	public UserDetailLoginVo(String userDetailId, String userId, Date createTime, Date modifiedTime) {
		this.userDetailId = userDetailId;
		this.userId = userId;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}

	public UserDetailLoginVo(String userDetailId, String departId, String departName, String userId, Character sex,
			String post, String email, String mobile, String name, String address, String nickname, Date createTime,
			Date modifiedTime, String note) {
		this.userDetailId = userDetailId;
		this.departId = departId;
		this.departName = departName;
		this.userId = userId;
		this.sex = sex;
		this.post = post;
		this.email = email;
		this.mobile = mobile;
		this.name = name;
		this.address = address;
		this.nickname = nickname;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
		this.note = note;
	}

	public String getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(String userDetailId) {
		this.userDetailId = userDetailId;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
