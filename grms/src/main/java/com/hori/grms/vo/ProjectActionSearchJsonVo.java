package com.hori.grms.vo;

import java.util.Date;

/**
 * 页面返回查询jasonVo
 * 
 * @author Chenrw
 * @date 2018年8月25日
 */
public class ProjectActionSearchJsonVo {

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
	// 开始时间
	private Date endDate;
	private int pageSize = 10;
	private int pageNumber = 1;
	// 执行清单ID（异常列表查询用）
	private String projectActionCode;
	// 处理状态，0未处理 1处理中 2已处理（异常列表查询用）
	private Short status;
	// 上报时间（异常列表查询用）
	private Date createTime;
	// 上报部门(业务类型)（异常列表查询用）
	private String businessType;
	// 项目编号（异常列表查询用）
	private String productCode;
	// （异常列表查询用）
	private int pageSizePE = 10;
	// （异常列表查询用）
	private int pageNumberPE = 1;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getProjectActionCode() {
		return projectActionCode;
	}
	public void setProjectActionCode(String projectActionCode) {
		this.projectActionCode = projectActionCode;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getPageSizePE() {
		return pageSizePE;
	}
	public void setPageSizePE(int pageSizePE) {
		this.pageSizePE = pageSizePE;
	}
	public int getPageNumberPE() {
		return pageNumberPE;
	}
	public void setPageNumberPE(int pageNumberPE) {
		this.pageNumberPE = pageNumberPE;
	}
	@Override
	public String toString() {
		return "ProjectActionSearchJsonVo [projectName=" + projectName + ", projectStatus=" + projectStatus
				+ ", projectExceptionStatus=" + projectExceptionStatus + ", province=" + province + ", city=" + city
				+ ", country=" + country + ", areaName=" + areaName + ", actionStatus=" + actionStatus + ", actionCode="
				+ actionCode + ", beginDate=" + beginDate + ", endDate=" + endDate + ", pageSize=" + pageSize
				+ ", pageNumber=" + pageNumber + ", projectActionCode=" + projectActionCode + ", status=" + status
				+ ", createTime=" + createTime + ", businessType=" + businessType + ", productCode=" + productCode
				+ ", pageSizePE=" + pageSizePE + ", pageNumberPE=" + pageNumberPE + "]";
	}
	
	
	
}
