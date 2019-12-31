package com.hori.grms.queryBean;

import java.util.List;

public class BaseQueryBean {
	private int pageSize=10;
	
	private int pageNumber=1;
	
	private String sortType;
		
	private String startTime;
	
	private String endTime;
	//------------------------------------------------
	private String sortFiled;//排序字段
		
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		if(pageNumber < 1){
			pageNumber = 1;
		}
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int getStart() {
		if(pageNumber < 1){
			pageNumber = 1;
		}
		return (pageNumber-1)*pageSize;
	}

	public 	String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
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

	public String getSortFiled() {
		return sortFiled;
	}

	public void setSortFiled(String sortFiled) {
		this.sortFiled = sortFiled;
	}
	
}
