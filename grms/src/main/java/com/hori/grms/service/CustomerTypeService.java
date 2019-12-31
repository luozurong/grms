package com.hori.grms.service;

import java.util.List;

import com.hori.grms.model.CustomerType;

public interface CustomerTypeService {
	
	public List<CustomerType> getCustomerType();
	
	public void delAll();
	
	public void insert(CustomerType customerType);

	public CustomerType getCustomerTypeById(String id);

	public void update(CustomerType type);
	
	
}
