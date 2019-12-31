package com.hori.grms.service;

import java.util.List;
import java.util.Map;

import com.hori.grms.model.Customer;

public interface CustomerManagementSerivce {
	/**
	 * 返回列表信息
	 * @param userAccount 
	 * @param userType 
	 * @return
	 */
	public List<Map<String,Object>> getAll(String condition, String userAccount, String roleType);
	/**
	 * 保存客户
	 */
	public void save(Customer customer, String userAccount);
	/**
	 * 删除客户记录，含单个和批量
	 */
	public void deleteById(List<String> idList);
	/**
	 * 通过Id查询客户
	 */
	public Map<java.lang.String, Object> fetchCustomerById(java.lang.String id);
	/**
	 * 导出客户信息
	 * @param userAccount 
	 * @param userType 
	 */
	public List<Map<java.lang.String, Object>> getExport(java.lang.String condition, String userAccount, String roleType);
	/**
	 * 根据公司名匹配出的公司
	 */
	public List<Map<String, Object>> getAllByName(String condition);
	/**
	 * 客户移交查询
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> transferInfoById(String id);
	/**
	 * 客户移交更新
	 * @param custId
	 * @param acceptId
	 * @param acceptName
	 * @param operatorName 
	 * @param operator 
	 */
	public void transferCustomer(String custId, String acceptAccount, String acceptName, String operator, String operatorName);
	
	/**
	 * 客户下项目的执行状况
	 * @param idArr
	 */
	public String projectStatus(String[] idArr);

	
}
