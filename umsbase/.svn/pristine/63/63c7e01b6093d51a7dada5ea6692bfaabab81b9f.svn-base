package com.hori.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Value;

import com.hori.adms.util.GlobalPropertiesValue;
import com.hori.pageModel.SessionInfo;
import com.hori.util.ResourceUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * session拦截器
 * 
 * @author 
 * 
 */
public class SessionInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		logger.info("----------------SessionInterceptor--------------------------------------");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		//从session中获取用户账号
		String userAccount=(String) request.getSession().getAttribute("userAccount");
		//String userType=(String) request.getSession().getAttribute("userType");
		if(StringUtils.isBlank(userAccount)){
			//未登录
			String loginUri=GlobalPropertiesValue.ssoLoginUri+"?redirectUri="+URLEncoder.encode(GlobalPropertiesValue.callbackUri, "utf-8");
			response.sendRedirect(loginUri);//重定向到sso登录页面
			return null;
		}
		return actionInvocation.invoke();
	}

}
