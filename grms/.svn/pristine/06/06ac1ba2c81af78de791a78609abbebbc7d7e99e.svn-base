package com.hori.grms.queryBean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 综合-执行清单queryBean
 */
public class ZhUmbQueryBean extends BaseQueryBean {
	// 项目名称
	private String projectName;
	// 执行状态
	private Integer actionStatus;
	// 项目执行清单编码
	private String actionCode;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
	//当前页
	private int page = 1;
	//每页显示的数量
	private int rows = 10;

	public int getStart(){
		return (page-1)*rows;
	}
	
	
	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getActionStatus() {
		return actionStatus;
	}


	public void setActionStatus(Integer actionStatus) {
		this.actionStatus = actionStatus;
	}


	public String getActionCode() {
		return actionCode;
	}


	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}


	public Date getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "ZhUmbQueryBean [projectName=" + projectName + ", actionStatus=" + actionStatus + ", actionCode="
				+ actionCode + ", beginDate=" + beginDate + ", endDate=" + endDate + ", page=" + page + ", rows=" + rows
				+ "]";
	}
	
	

	
}
