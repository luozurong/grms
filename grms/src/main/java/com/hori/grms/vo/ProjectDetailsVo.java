package com.hori.grms.vo;

import java.util.List;

import com.hori.grms.model.ProjectPeople;

/**
 * 
 * 项目详情页VO
 * @author wsc
 *
 */
public class ProjectDetailsVo {
	private String id;                         //项目Id
	private String projectCode;                //项目编码
	private String name;                       //项目名称
	private Short status;                      //项目状态
	private String customerId;                 //客户Id
	private String customerName;               //客户名称          
	private String contractId;                 //合同Id
	private String contractCode;               //合同code
	private String contractName;               //合同名称
	private String contractStatus;             //合同状态
	private String description;                //项目说明
	private List<ProjectPeople>  roles;        //项目角色
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ProjectPeople> getRoles() {
		return roles;
	}
	public void setRoles(List<ProjectPeople> roles) {
		this.roles = roles;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	@Override
	public String toString() {
		return "ProjectDetailsVo [id=" + id + ", projectCode=" + projectCode + ", name=" + name + ", status=" + status
				+ ", customerId=" + customerId + ", customerName=" + customerName + ", contractId=" + contractId
				+ ", contractCode=" + contractCode + ", contractName=" + contractName + ", contractStatus="
				+ contractStatus + ", description=" + description + ", roles=" + roles + "]";
	}
	
}
