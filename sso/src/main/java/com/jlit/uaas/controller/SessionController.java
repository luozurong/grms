package com.jlit.uaas.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 会话controller
 * @author laizs
 * @file SessionController.java
 * @time 2017年3月23日下午5:19:55
 */
@Controller
@RequestMapping("/session")
public class SessionController {
	/**
	 * 获取session中保存的登录的用户账号
	 * @param request
	 * @return
	 * @author laizs
	 */
	@RequestMapping("/getLoginUserAccount")
	public @ResponseBody String getLoginUserAccount(HttpServletRequest request){
		String userAccount=(String) request.getSession().getAttribute("userAccount");
		return StringUtils.isBlank(userAccount)?"":userAccount;
	}
}
