package com.hori.pageModel;

import java.io.Serializable;
import java.util.Date;

import com.hori.dao.queryBean.BaseQueryBean;

public class SLogPage extends BaseQueryBean implements Serializable {
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
	private String createTime;
	/**
	 * 同步状态
	 */
	private Integer status;
	
	private String startTime;

	private String endTime;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogsContent() {
		return logsContent;
	}

	public void setLogsContent(String logsContent) {
		this.logsContent = logsContent;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
