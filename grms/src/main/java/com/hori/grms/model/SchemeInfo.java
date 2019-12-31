package com.hori.grms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SchemeInfo {
	
	private String id;
	private String name;
	@JsonIgnore
	private String url;
	@JsonIgnore
	private String urlName;
	@JsonIgnore
	private String createrAccount;
	private String createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public String getCreaterAccount() {
		return createrAccount;
	}
	public void setCreaterAccount(String createrAccount) {
		this.createrAccount = createrAccount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
