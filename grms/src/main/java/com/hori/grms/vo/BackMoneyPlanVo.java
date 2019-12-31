package com.hori.grms.vo;

import java.util.Date;
import java.util.List;

import com.hori.grms.model.BackMoneyPlanPeriods;

public class BackMoneyPlanVo {
	private String id;
	// 收款计划ID
	private String backMoneyPlanCode;
	// 项目ID
	private String projectCode;
	// 合同ID
	private String contractCode;
	// 合同名称
	private String contractName;
	// 合同总金额
	private Double money;
	// 客户名称
	private String name;
	// 业务员
	private String createrName;
	// 合同审批时间(取合同表最新更新时间update_time字段)
	private Date approveTime;
	// 合同实收金额
	private Double collectedMoney;
	// 合同未收金额
	private Double unCollectedMoney;
	// 扣款金额
	private Double reduceMoney;
	// 合同收款状态
	private String planStatus;
	//计划审核状态（审核状态：-1已删除 0待审核 1已审核2已结案）
	private Short checkStatus;
	
	private List<BackMoneyPlanPeriods> planList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBackMoneyPlanCode() {
		return backMoneyPlanCode;
	}

	public void setBackMoneyPlanCode(String backMoneyPlanCode) {
		this.backMoneyPlanCode = backMoneyPlanCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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

	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getCollectedMoney() {
		return collectedMoney;
	}

	public void setCollectedMoney(Double collectedMoney) {
		this.collectedMoney = collectedMoney;
	}

	public Double getUnCollectedMoney() {
		return unCollectedMoney;
	}

	public void setUnCollectedMoney(Double unCollectedMoney) {
		this.unCollectedMoney = unCollectedMoney;
	}

	public Double getReduceMoney() {
		return reduceMoney;
	}

	public void setReduceMoney(Double reduceMoney) {
		this.reduceMoney = reduceMoney;
	}

	public List<BackMoneyPlanPeriods> getPlanList() {
		return planList;
	}

	public void setPlanList(List<BackMoneyPlanPeriods> planList) {
		this.planList = planList;
	}

	public Short getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Short checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}

