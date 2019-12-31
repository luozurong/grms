package com.hori.pageModel;

import java.util.List;

/**
 * easyui的datagrid模型
 * 
 * @author 
 * 
 */
public class DataGrid implements java.io.Serializable {

	private Long total;// 总记录数
	private List rows;// 每行记录
	private List footer;
	private Long page;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	
}
