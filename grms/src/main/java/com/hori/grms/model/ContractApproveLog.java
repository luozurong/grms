package com.hori.grms.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ContractApproveLog {
    private String id;

    private String contractCode;

    private String approveRoleName;

    private String approveAccount;

    private String approveName;

    private Date approveTime;

    private String approveDesc;

    private Integer approveStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getApproveRoleName() {
        return approveRoleName;
    }

    public void setApproveRoleName(String approveRoleName) {
        this.approveRoleName = approveRoleName;
    }

    public String getApproveAccount() {
        return approveAccount;
    }

    public void setApproveAccount(String approveAccount) {
        this.approveAccount = approveAccount;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getApproveDesc() {
        return approveDesc;
    }

    public void setApproveDesc(String approveDesc) {
        this.approveDesc = approveDesc;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }
}