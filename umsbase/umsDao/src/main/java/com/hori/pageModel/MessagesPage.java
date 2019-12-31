package com.hori.pageModel;

import java.io.Serializable;

import com.hori.dao.queryBean.BaseQueryBean;

public class MessagesPage extends BaseQueryBean implements Serializable{
	/**
	 * 主键ID
	 * 
	 * @pdOid 86677714-95f3-4833-a5ee-529dbefaf38b
	 */
	public String id;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 可查看该消息的权限等级
	 */
	private String viewLevelNo;
	/**
	 * 消息状态 （0:未查看，1：已查看）'
	 */
	private Integer status=0;
	/**
	 * 消息创建日期
	 */
	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getViewLevelNo() {
		return viewLevelNo;
	}

	public void setViewLevelNo(String viewLevelNo) {
		this.viewLevelNo = viewLevelNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
