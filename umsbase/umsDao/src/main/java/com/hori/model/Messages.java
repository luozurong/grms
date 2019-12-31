package com.hori.model;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Messages")
@Table(name = "message")
public class Messages implements java.io.Serializable {
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
	private Integer status;
	/**
	 * 消息创建日期
	 */
	private Date createTime;
	
	/*
	 * 广告id
	 */
	private String advertisementId;
	/*
	 * 消息类型
	 */
	private Integer type;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "com.hori.hibernate.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="view_level_no")
	public String getViewLevelNo() {
		return viewLevelNo;
	}

	public void setViewLevelNo(String viewLevelNo) {
		this.viewLevelNo = viewLevelNo;
	}
    
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Basic(optional=true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time", insertable=false, updatable=false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
   
	
	@Column(name="advertisement_id")
	public String getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}

	@Column(name="type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
}
