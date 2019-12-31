package com.hori.db.support;

import java.util.ArrayList;
import java.util.List;

public class DataGridPage {
	
    /**
     * 每页的记录数
     */
    private int pageSize;

	/**
	 * 当前页数
	 */
	private int page;
	/**
	 * 总记录数
	 */
	private int total;
	/**
	 * 该页数据
	 */
	private List rows =  new ArrayList();
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	public DataGridPage(){
	}
	
	public DataGridPage(int page, int total, List rows) {
		super();
		this.page = page;
		this.total = total;
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
