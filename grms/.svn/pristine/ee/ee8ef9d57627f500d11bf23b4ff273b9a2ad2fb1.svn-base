package com.hori.grms.queryBean;

/**
 * 执行清单详情queryBean
 */
public class ProjectActionDetailQueryBean {
	
	//业务类型（1社区运营 2 媒管 3用户运营 4电商运营）
	private String businessType;
	//执行清单编码
	private String actionCode;
	//项目编号
	private String projectCode;
	
	//当前页
	private int page = 1;
	//每页显示的数量
	private int rows = 10;

	public int getStart(){
		return (page-1)*rows;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
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

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	
	

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Override
	public String toString() {
		return "ProjectActionDetailQueryBean [businessType=" + businessType + ", actionCode=" + actionCode + ", page="
				+ page + ", rows=" + rows + "]";
	}

	

	
}
