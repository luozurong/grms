package com.hori.grms.queryBean;

import java.util.List;

/**
 * 待办事项查询Bean
 * @author FWQ
 *
 */
public class PendingEventQueryBean extends BaseQueryBean {
	
	//开始时间
    private String startDate;

    //结束时间
    private String endDate;

    //待办事项名称/模块
    private String key;
    
    /**
     * 角色类型
     * 0为业务员，1为业务管理员，2为合同管理员，3为财务管理员，4为社区运营执行管理员，
     * 5为电商运营管理员，6为用户运营执行管理员，7为媒管执行管理员，8为平台支持人员
     */
    private int roleType;
    
    //登录的用户账号
    private String account;
    
    //当前页
  	private int page = 1;
  	//每页显示的数量
  	private int rows = 10;
  	
  	private List<String> departmentId;
  	



	public List<String> getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(List<String> departmentId) {
		this.departmentId = departmentId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	
}
