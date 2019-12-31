package com.jlit.uaas.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jlit.uaas.dao.UserDao;
import com.jlit.uaas.model.User;
import com.jlit.uaas.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	/**
	 * 用户类型Map
	 */
	private Map<String,String> userTypeMap=new HashMap<String, String>();
	@Resource(name="userDao")
	private UserDao userDao;
	@Override
	public User getByLoginAccount(String loginAccount) {
		return this.userDao.getByLoginAccount(loginAccount);
	}
	@Override
	public User getById(String id) {
		return this.userDao.get(id);
	}
	@Override
	public User getUserByPhone(String phone) {
		return this.userDao.getUserByPhone(phone);
	}
	

}
