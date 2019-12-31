package com.hori.grms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 结案表
 * @author wangzhen
 *
 */
public class CloseCaseInfo {
	
	private String id;
	private String closeCaseCode;			//结案编号
	private String projectCode;				//项目编号
	private String projectName;				//项目名称
	private String contractCode;			//合同编号
	private String contractName;			//合同名称
	private String actionTime;				//执行时间
	private String actionArea;				//执行小区
	private String backMoneyPlanCode;		//收款计划编号
	private Integer type;					//结案类型：1正常结案 0：异常结案
	private Integer status;					//结案状态（1：待提交社区运营 2：待提交用户运营 3：待提交电商运营 4：待提交媒管 
											//5：已提交 6：待处理 7：待结案 8：已结案）
	private Integer roleType;				//角色类型（0：业务员 1：业务员管理员 4：社区运营 5：电商运营 6：用户运营 7：媒管 -1：admin）
	@JsonIgnore
	private String createrAccount;			//创建人账号
	private String projectCreaterAccount;	//项目创建者账号
	private String createTime;				//创建时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCloseCaseCode() {
		return closeCaseCode;
	}
	public void setCloseCaseCode(String closeCaseCode) {
		this.closeCaseCode = closeCaseCode;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	public String getActionArea() {
		return actionArea;
	}
	public void setActionArea(String actionArea) {
		this.actionArea = actionArea;
	}
	public String getBackMoneyPlanCode() {
		return backMoneyPlanCode;
	}
	public void setBackMoneyPlanCode(String backMoneyPlanCode) {
		this.backMoneyPlanCode = backMoneyPlanCode;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRoleType() {
		return roleType;
	}
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	public String getCreaterAccount() {
		return createrAccount;
	}
	public void setCreaterAccount(String createrAccount) {
		this.createrAccount = createrAccount;
	}
	public String getProjectCreaterAccount() {
		return projectCreaterAccount;
	}
	public void setProjectCreaterAccount(String projectCreaterAccount) {
		this.projectCreaterAccount = projectCreaterAccount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
