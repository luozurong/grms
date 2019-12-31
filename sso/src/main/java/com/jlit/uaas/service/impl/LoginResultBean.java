package com.jlit.uaas.service.impl;

import java.io.Serializable;

/**
 * 登录结果bean
 * @author laizs
 * @time 2014-3-26 下午2:27:59
 * @file LoginResultBean.java
 */
public class LoginResultBean implements Serializable{
	
	private static final long serialVersionUID = 7077284653306364144L;
	/**
	 * 登录结果 0标表示成功，其他表示失败
	 */
	String result;
	/**
	 * 错误信息
	 */
	String error;
	
	public LoginResultBean(String result, String error) {
		this.result = result;
		this.error = error;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
