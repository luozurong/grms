/***********************************************************************
 * Module:  DeptManage.java
 * Author:  daihf
 * Purpose: Defines the Class DeptManage
 ***********************************************************************/

package com.hori.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 操作记录
 * @author ChenPuzhen  
 * @date 2017年5月18日 上午11:01:56 
 * @version V1.0
 */
public class OperationRecord implements java.io.Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//id序号
	private String id;
    //操作时间
    private Date operationTime;
    //角色
    private String role;
    //账号
    private String account;
    //IP地址
    private String ipAddress;
    //操作类型
    private String operationType;
    //操作模块
    private String operationModule;
    //操作内容
    private String operationContent;
    //操作结果
    private String operationResult;
    //客户端标识
    private String client;
    
	public OperationRecord() {
		
	}

	
	public OperationRecord(String id, Date operationTime, String role,
			String account, String ipAddress, String operationType,
			String operationModule, String operationContent,
			String operationResult, String client) {
		super();
		this.id = id;
		this.operationTime = operationTime;
		this.role = role;
		this.account = account;
		this.ipAddress = ipAddress;
		this.operationType = operationType;
		this.operationModule = operationModule;
		this.operationContent = operationContent;
		this.operationResult = operationResult;
		this.client = client;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationModule() {
		return operationModule;
	}

	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}

	public String getOperationContent() {
		return operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	public String getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

}