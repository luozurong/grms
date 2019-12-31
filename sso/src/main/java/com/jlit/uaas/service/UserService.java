package com.jlit.uaas.service;

import com.jlit.uaas.model.User;

/**
 * 用户service
 * @author laizs
 * @time 2014-3-24 上午11:59:22
 * @file UserService.java
 */
public interface UserService {
	/**
	 * 
	 * @param loginAccount
	 * @return
	 */
	User getByLoginAccount(String loginAccount);
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	User  getById(String id);
	/**
	 * 根据用户手机注册时填写的手机号获取用户信息
	 * @param phone
	 * @return
	 */
	User getUserByPhone(String phone);
}
