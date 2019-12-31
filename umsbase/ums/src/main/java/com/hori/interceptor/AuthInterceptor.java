package com.hori.interceptor;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hori.pageModel.SessionInfo;
import com.hori.service.AuthService;
import com.hori.util.RequestUtil;
import com.hori.util.ResourceUtil;
import com.hori.vo.AuthVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 权限拦截器
 * 
 * @author 
 * 
 */
public class AuthInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Autowired
	private AuthService authService;
	
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		String userAccount = (String) ServletActionContext.getRequest().getSession().getAttribute("userAccount");
		String userType = (String) ServletActionContext.getRequest().getSession().getAttribute("userType");
		logger.info("---------------------AuthInterceptor-----------------------");
		if ("0".equals(userType)) {// 类型为0的用户不需要验证权限
			return actionInvocation.invoke();
		}
		String requestPath = RequestUtil.getRequestPath(ServletActionContext.getRequest()).substring(1);
		//以下是控制逻辑...
        HttpServletRequest request = ServletActionContext.getRequest();
		Set<String> resourceUrlSet = (Set<String>)request.getSession().getAttribute("resourceUrlSet");
		boolean flag = false; 
		for(String resourceUrl:resourceUrlSet){
			if(resourceUrl.indexOf(requestPath) >= 0){
				flag = true;
				break;
			}
		}
		if(flag){
			return  actionInvocation.invoke();
		}
		return "noAuth";
		
	}
}
