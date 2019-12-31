package com.hori.service;

import java.util.List;

import com.hori.model.UserType;

public interface UserTypeService {
	/**
	 * 查找用户的全部类型
	 * @return
	 */
	public List<UserType> findAll();
}
