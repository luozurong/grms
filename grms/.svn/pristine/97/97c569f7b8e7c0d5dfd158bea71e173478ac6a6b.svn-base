package com.hori.grms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.grms.dao.CustomerTypeMapper;
import com.hori.grms.model.CustomerType;
import com.hori.grms.service.CustomerTypeService;
@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {
	@Autowired
	private CustomerTypeMapper customerTypeMapper;
	
	@Override
	public List<CustomerType> getCustomerType() {
		return customerTypeMapper.selectAll();
	}

	@Override
	public void delAll() {
		// TODO Auto-generated method stub
		customerTypeMapper.delAll();
	}

	@Override
	public void insert(CustomerType customerType) {
		// TODO Auto-generated method stub
		customerTypeMapper.insert(customerType);
	}

	@Override
	public CustomerType getCustomerTypeById(String id) {
		// TODO Auto-generated method stub
		return customerTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(CustomerType type) {
		// TODO Auto-generated method stub
		customerTypeMapper.updateByPrimaryKeySelective(type);
	}
	
}
