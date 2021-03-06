/*
 * Project.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.model;

import java.util.Date;

/**
 * 项目
 * 
 * @author 
 * @version 1.0 2018-08-10
 */
public class Project {

    //主键id
    private String id;
    //项目编号(表关联使用),唯一性
    private String productCode;
    //项目名称
    private String name;
    //客户id
    private String customerId;
    //客户名称
    private String customerName;
    //项目说明
    private String description;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //创建者账号
    private String createrAccount;
    //创建者名称
    private String createrName;
    //创建者账号权限级别
    private String createrLevel;
    //创建者部门id
    private String createrDedpartmentId;
    //执行合同编号
    private String contractCode;
    //状态：-1删除  0待审核 1审核通过 2审核不通过 3立项终止 4撤回
    private Short status;
    //项目执行状态：0未执行 1执行中 2执行完成
    private Short actionStatus;
    //是否有异常：0无 1有
    private Short exceptionStatus;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getCreaterAccount() {
        return createrAccount;
    }
    public void setCreaterAccount(String createrAccount) {
        this.createrAccount = createrAccount;
    }
    public String getCreaterName() {
        return createrName;
    }
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }
    public String getCreaterLevel() {
        return createrLevel;
    }
    public void setCreaterLevel(String createrLevel) {
        this.createrLevel = createrLevel;
    }
    public String getCreaterDedpartmentId() {
        return createrDedpartmentId;
    }
    public void setCreaterDedpartmentId(String createrDedpartmentId) {
        this.createrDedpartmentId = createrDedpartmentId;
    }
    public String getContractCode() {
        return contractCode;
    }
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
    public Short getStatus() {
        return status;
    }
    public void setStatus(Short status) {
        this.status = status;
    }
    public Short getActionStatus() {
        return actionStatus;
    }
    public void setActionStatus(Short actionStatus) {
        this.actionStatus = actionStatus;
    }
    public Short getExceptionStatus() {
        return exceptionStatus;
    }
    public void setExceptionStatus(Short exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }
}