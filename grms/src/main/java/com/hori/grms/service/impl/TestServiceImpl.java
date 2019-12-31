package com.hori.grms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.grms.dao.TestMapper;
import com.hori.grms.model.Test;
import com.hori.grms.service.TestService;
@Service
public class TestServiceImpl implements TestService{
	@Autowired
	private TestMapper testMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Test record) {
		
		return testMapper.insert(record);
	}

	@Override
	public int insertSelective(Test record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Test selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Test record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Test record) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
