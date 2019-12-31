package com.hori.grms.queryBean;

import java.util.Date;
import java.util.List;

/**
 * 执行清单queryBean
 * 
 * @author hehj
 * @datetime 2018年8月10日 上午10:16:45
 */
public class ProjectActionQueryBean extends BaseQueryBean {
	// 项目名称
	private String projectName;
	// 项目状态
	private Integer projectStatus;
	// 项目异常状态
	private Integer projectExceptionStatus;
	// 小区地址：省(编号)
	private String province;
	// 小区地址：市(编号)
	private String city;
	// 小区地址：区/县(编号)
	private String country;
	// 小区名称
	private String areaName;
	// 执行状态
	private Integer actionStatus;
	// 执行清单id
	private String actionCode;
	// 开始时间
	private Date beginDate;
	// 结束时间
	private Date endDate;
	// 用户数据域，0为个人数据，1为部门数据，2为全局数据
	private byte dataArea;
	// 部门ID（个人数据域用）
	private String departmentId;
	// 部门名称（个人数据域用）
	private String departmentName;
	// 指定查询创建人账号
	private String account;
	/**
	 * 数据域对应权限的用户列表: 个人数据：用户可查看自己和下级的数据； 部门数据：用户可查看自己、下级和机构内同级别的数据；
	 * 全局数据：用户可查看系统全部数据
	 */
	private List<String> userList;
	/**
	 * 数据域对应权限的部门列表: 个人数据：用户可查看自己和下级部门的数据； 部门数据：用户可查看自己、下级和机构内同级别的数据；
	 * 全局数据：用户可查看系统全部数据
	 */
	private List<String> departList;

	// 当前页
	private int page = 1;
	// 每页显示的数量
	private int rows = 10;

	// 项目编号
	private String projectCode;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public int getStart() {
		return (page - 1) * rows;
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

	public Integer getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Integer getProjectExceptionStatus() {
		return projectExceptionStatus;
	}

	public void setProjectExceptionStatus(Integer projectExceptionStatus) {
		this.projectExceptionStatus = projectExceptionStatus;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	public byte getDataArea() {
		return dataArea;
	}

	public void setDataArea(byte dataArea) {
		this.dataArea = dataArea;
	}

	public List<String> getDepartList() {
		return departList;
	}

	public void setDepartList(List<String> departList) {
		this.departList = departList;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "ProjectActionQueryBean [projectName=" + projectName + ", projectStatus=" + projectStatus
				+ ", projectExceptionStatus=" + projectExceptionStatus + ", province=" + province + ", city=" + city
				+ ", country=" + country + ", areaName=" + areaName + ", actionStatus=" + actionStatus + ", actionCode="
				+ actionCode + ", beginDate=" + beginDate + ", endDate=" + endDate + ", dataArea=" + dataArea
				+ ", departmentId=" + departmentId + ", departmentName=" + departmentName + ", account=" + account
				+ ", userList=" + userList + ", departList=" + departList + ", page=" + page + ", rows=" + rows
				+ ", projectCode=" + projectCode + "]";
	}

}
