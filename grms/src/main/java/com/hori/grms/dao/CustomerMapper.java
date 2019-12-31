/*
 * CustomerMapper.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.Customer;

public interface CustomerMapper {
	
	/**
	 * 查询客户
	 * @param userAccount 
	 */
	List<Map<String, Object>> getAll(@Param("condition") String condition ,@Param("userAccount") String userAccount);
	/**
	 * 保存客户
	 */
	int insert(Customer record);
	/**
	 * 删除客户
	 *//*
	void deleteById(List<Customer> custList);*/
	/**
	 * id查询客户
	 */
	Map<String, Object> fetchCustomerById(@Param("id") String id);
	/**
	 * 导出
	 * @param userAccount 
	 */
	List<Map<String, Object>> getExport(@Param("condition") String condition, @Param("userAccount") String userAccount);
	/**
	 * 编辑后更新
	 */
	int updateByPrimaryKeySelective(Customer record);
	/**
	 * 输入公司名称加载相似的
	 * @return
	 */
	List<Map<String, Object>> fetchCompanyByName(@Param("condition") String condition);
	/**
	 * 
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> transferInfoById(@Param("id") String id);
	/**
	 *
	 * @param id
	 */
    Customer selectByPrimaryKey(String id);

	/**
	 * 根据用户账号查看用户信息
	 * @param ownerAccount
	 * @return
	 */
	 List<Customer> selectByOwnerAccount(@Param("ownerAccount") String ownerAccount);
}