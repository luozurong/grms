package com.jlit.uaas.web.queryBean;

public class BaseQueryBean {
	
	private Integer pageNumber;
	
	private String sortType;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

}
