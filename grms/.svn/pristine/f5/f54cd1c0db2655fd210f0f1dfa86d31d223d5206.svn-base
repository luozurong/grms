package com.hori.grms.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 社区/用户/媒管执行清单详情Vo
 * @author lj
 * @datetime 
 */
public class ProjectActionDetailVo {
	// 业务类型：1社区运营 2 媒管 3用户运营 4电商运营
	private String businessType;
	//产品类型
	private String productType;
	//清单类型
	private String productMenu;
	//产品规格
	private String productSpec;
	//购买数量
	private String buyNum;
	// 执行开始时间
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date beginTime;
	// 执行结束时间
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endTime;
	// 清单编号
	private String actionCode;
	//场地数量
	private Long areaNum;
	// 项目清单主键id
	private String projectProductId;
	//项目产品明细id
	private String projectProductMenuId;
	//场次名称
	private String fieldName;
	//已选小区名称(社区运营需要)
	private String areaName;
	//小区名称集合(导出需要)
	private List<String> areas;
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductMenu() {
		return productMenu;
	}
	public void setProductMenu(String productMenu) {
		this.productMenu = productMenu;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}
	public String getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public Long getAreaNum() {
		return areaNum;
	}
	public void setAreaNum(Long areaNum) {
		this.areaNum = areaNum;
	}
	public String getProjectProductId() {
		return projectProductId;
	}
	public void setProjectProductId(String projectProductId) {
		this.projectProductId = projectProductId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	public String getProjectProductMenuId() {
		return projectProductMenuId;
	}
	public void setProjectProductMenuId(String projectProductMenuId) {
		this.projectProductMenuId = projectProductMenuId;
	}
	
	
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
	public List<String> getAreas() {
		return areas;
	}
	public void setAreas(List<String> areas) {
		this.areas = areas;
	}
	@Override
	public String toString() {
		return "ProjectActionDetailVo [businessType=" + businessType + ", productType=" + productType + ", productMenu="
				+ productMenu + ", productSpec=" + productSpec + ", buyNum=" + buyNum + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", actionCode=" + actionCode + ", areaNum=" + areaNum + ", projectProductId="
				+ projectProductId + ", fieldName=" + fieldName + "]";
	}
	
	
}
