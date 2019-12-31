package com.hori.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.UserTypeDao;
import com.hori.model.UserType;
import com.hori.service.UserTypeService;
@Service
public class UserTypeServiceImpl implements UserTypeService {

	@Autowired
	private UserTypeDao userTypeDao;
	
	@Override
	public List<UserType> findAll() {
		// TODO Auto-generated method stub
		return userTypeDao.findAll();
	}

}
