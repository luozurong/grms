package com.jlit.uaas.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlit.uaas.model.User;
import com.jlit.uaas.service.impl.LoginResultBean;

/**
 * 用户登录业务逻辑service
 * @author laizs
 * @time 2014-3-26 下午2:25:43
 * @file LoginService.java
 */
public interface LoginService {
	/**
	 * 登录验证逻辑
	 * @param userAccount 用户账号
	 * @param password 密码
	 * @param vevifiCode 验证码
	 * @param request 
	 * @return
	 */
	LoginResultBean login(String userAccount,String password,String vevifiCode,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 判断用户是否登录
	 * @param request
	 * @return
	 */
	boolean isLogin(HttpServletRequest request);
	/**
	 * 用户登出请求
	 * @param request
	 * @param response
	 * @return
	 */
	String logout(HttpServletRequest request,HttpServletResponse response);
	/**
	 * 获取当天密码连续输入错误次数存储的key
	 * @param userAccount 用户账号
	 *@author laizs
	 *@return
	 */
	String getPassErrerTimsKey(String userAccount);
	/**
	 * 获取当天密码连续输入错误次数
	 * @param userAccount 用户账号
	 *@author laizs
	 *@return
	 */
	int getPassErrerTims(String userAccount);
	/**
	 * 获取密码连续输入错误次数限制后登录需要验证码
	 *@author laizs
	 *@return
	 */
	int getPassErrerTimsLimitVevifiCodeNeed();
	
	
	
}
