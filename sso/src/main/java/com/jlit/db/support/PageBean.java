package com.jlit.db.support;

/**
 * 接收扩展后的FlexGrid请求页面时的参数 new该对象时，对象名称必须为pageBean
 * 
 * @author chenkl
 */
public class PageBean {
	/**
	 * 请求页数
	 */
	private int page;
	/**
	 * 请求的记录数
	 */
	private int rp;
	/**
	 * 需要排序的属性名称
	 */
	private String sortname;
	/**
	 * 排序类型
	 */
	private String sortorder;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

}
