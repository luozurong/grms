package com.hori.interceptor;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hori.model.Author;
import com.hori.pageModel.SessionInfo;
import com.hori.util.RequestUtil;
import com.hori.util.ResourceUtil;
import com.hori.vo.AuthVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 文件上传下载权限拦截器
 * 
 * @author 
 * 
 */
public class FileAuthInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(FileAuthInterceptor.class);

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext actionContext = actionInvocation.getInvocationContext();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo.getLoginName().equals("admin")) {// admin用户不需要验证权限
			return actionInvocation.invoke();
		}
		String requestPath = RequestUtil.getRequestPath(ServletActionContext.getRequest());
		logger.debug(actionInvocation.getAction().getClass());
		logger.debug(requestPath);
		List<Author> auths = sessionInfo.getAuthVos();
		if (auths != null && auths.size() > 0) {
			boolean b = false;
			for (Author a : auths) {
			/*	if (requestPath.equals(a.getCurl())) {
					b = true;
					break;
				}*/
			}
			if (b) {
				return actionInvocation.invoke();
			}
		}
		ServletActionContext.getRequest().setAttribute("msg", "您没有访问此功能的权限！权限路径为[" + requestPath + "]请联系管理员给你赋予相应权限。");
		return "noFileAuth";
	}

}
