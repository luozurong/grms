/**
 * 
 */
package com.hori.grms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hori.grms.dao.CustomerMapper;
import com.hori.grms.model.Customer;
import com.hori.grms.page.PageParameter;
import com.hori.grms.service.CustomerService;
import com.hori.grms.vo.CommunityProjectActionVo;

/** 
 * @date 2018年8月14日 下午3:53:26 
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public PageInfo<Customer> findCustomerListByUser(String ownerAccount,Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<Customer> list = customerMapper.selectByOwnerAccount(ownerAccount);
		
		PageInfo<Customer> pageInfo = new PageInfo<Customer>(list);
		
		return pageInfo;		
		
	}
}
