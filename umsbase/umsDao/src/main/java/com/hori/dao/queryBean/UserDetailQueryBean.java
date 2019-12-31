package com.hori.dao.queryBean;

import java.io.Serializable;
import java.util.Date;

public class UserDetailQueryBean extends BaseQueryBean implements Serializable{
	private Date createTimeBegin;
	private Date createTimeEnd;
	private String name;
	private String mobile;
	private String departId;
	private String post;
	public Date getCreateTimeBegin() {
		return createTimeBegin;
	}
	public void setCreateTimeBegin(Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
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

	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}

	
	

}
