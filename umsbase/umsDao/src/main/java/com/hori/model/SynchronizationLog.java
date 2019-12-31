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

/**
 * 同步数据的日志类
 *
 */
@Entity(name = "SynchronizationLog")
@Table(name = "synchronization_log")
public class SynchronizationLog implements java.io.Serializable {

	private String id;
	/**
	 * 日志内容
	 */
	private String logsContent;
	/**
	 * 记录类型
	 */
	private Integer type;
	/**
	 * 创建日期
	 */
	private Date createTime;
	/**
	 * 同步状态
	 */
	private Integer status;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "com.hori.hibernate.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic(optional = true)
	@Column(name = "logs_content", insertable = true, updatable = true)
	public String getLogsContent() {
		return logsContent;
	}

	public void setLogsContent(String logsContent) {
		this.logsContent = logsContent;
	}

	@Basic(optional = true)
	@Column(name = "type", insertable = true, updatable = true)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
	@Basic(optional = true)
	@Column(name = "status", insertable = true, updatable = true)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
