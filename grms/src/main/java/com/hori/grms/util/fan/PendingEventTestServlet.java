package com.hori.grms.util.fan;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hori.grms.service.PendingEventService;

/**
 * 三位一体项目待办事件测试接口
 * @author FWQ
 *
 */
@WebServlet(name="pendingEventTestServlet", urlPatterns = "/serlvet/pendingEventTest",loadOnStartup = 1)
public class PendingEventTestServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	/**
	 * 日志对象
	 */
	private final static Logger logger=Logger.getLogger(PendingEventTestServlet.class);
	
	@Autowired
	private PendingEventService pendingEventService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		pendingEventService=(PendingEventService) ctx.getBean("pendingEventService");
	}
	
	@SuppressWarnings({"unused"})
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String projectName = "";
		String projectCode = "";
		String closeCaseCode = "";
		int roleType = -100;
		String methodName = "";
		int status = -100;
		String userAccount = "";
		String userName = "";
		String contractCode="";
		String exceptionId = "";
	
		String token=null;
		//请求结果
		String result=null;
		//说明
		String reason=null;
		
		try {
			// 客户端请求JSON串
			String reqStr = ServletUtil.praseRequst(request);
			logger.info("reqStr:::"+reqStr);
			ServletMessageReq smr = new ServletMessageReq(reqStr);
			token = smr.getHeader().getString("token");
			
		
			
			if(smr.getBody().containsKey("projectName")){
				projectName = smr.getBody().getString("projectName");
			}
			if(smr.getBody().containsKey("projectCode")){
				projectCode = smr.getBody().getString("projectCode");
			}
			if(smr.getBody().containsKey("closeCaseCode")){
				closeCaseCode = smr.getBody().getString("closeCaseCode");
			}
			if(smr.getBody().containsKey("roleType")){
				roleType = smr.getBody().getInt("roleType");
			}
			if(smr.getBody().containsKey("methodName")){
				methodName = smr.getBody().getString("methodName");
			}
			if(smr.getBody().containsKey("status")){
				status = smr.getBody().getInt("status");
			}
			if(smr.getBody().containsKey("userAccount")){
				userAccount = smr.getBody().getString("userAccount");
			}
			if(smr.getBody().containsKey("userName")){
				userName = smr.getBody().getString("userName");
			}
			if(smr.getBody().containsKey("contractCode")){
				contractCode = smr.getBody().getString("contractCode");
			}
			if(smr.getBody().containsKey("exceptionId")){
				exceptionId = smr.getBody().getString("exceptionId");
			}
			if("createCloseCasePendingEvent".equals(methodName)){
				this.pendingEventService.createCloseCasePendingEvent(projectName, projectCode, closeCaseCode, roleType);
			}else if("createContractPendingEvent".equals(methodName)){
				this.pendingEventService.createContractPendingEvent(projectName, projectCode, contractCode, status, roleType, userAccount, userName);
			}else if("createProjectActionPendingEvent".equals(methodName)){
//				this.pendingEventService.createProjectActionPendingEvent(projectName, projectCode, contractCode, roleType);
			}else if("createProjectActionExceptionPendingEvent".equals(methodName)){
//				this.pendingEventService.createProjectActionExceptionPendingEvent(projectName, projectCode, contractCode, exceptionId, status, roleType);
			}else if("createBackMoneyPendingEventByShenHe".equals(methodName)){
				//财务审核
				this.pendingEventService.createBackMoneyPendingEventByShenHe(projectName, projectCode, contractCode, userAccount, userName);
			}else if("createBackMoneyPendingEventByContractEnd".equals(methodName)){
				//新添收款模块的待办事件
				this.pendingEventService.createBackMoneyPendingEventByContractEnd(projectName, projectCode);
			}else if("updateBackMoneyPendingEventByContractEnd".equals(methodName)){
				//删除收款模块的待办事件
				this.pendingEventService.updateBackMoneyPendingEventByContractEnd(projectCode);
			}
			
			
			
			
			this.sendMessage(response, result, reason);
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "005";
			reason = "服务器响应异常";
			this.sendMessage(response, result, reason);
			return;
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	public void sendMessage(HttpServletResponse response,String result,String reason){
		//请求响应
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		resultMap.put("reason", reason);
		try {
			ServletUtil.sendResponse(response, JSON.toJSONString(resultMap, SerializerFeature.WriteDateUseDateFormat));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
