package com.hori.log;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.hori.dao.OperationRecordDao;
import com.hori.dao.UserDao;
import com.hori.model.OperationRecord;
import com.hori.utils.StaticValue;
import com.jlit.vdcs.webservice.IWorkOrderFromUmsService;
import com.opensymphony.xwork2.ActionSupport;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

public class LogAction extends ActionSupport{
	
	@Autowired
	private OperationRecordDao operationRecordDao;
	@Autowired
	private UserDao userDao;
	/** 定义日志对象 */
	private Logger logger = Logger.getLogger(LogAction.class);
	
	/** 在调用目标方法前后织入，用来记录业务层方法的性能 */
	public Object around(ProceedingJoinPoint pj) throws Throwable{
		
		Object res = pj.proceed(pj.getArgs());
		String classname = pj.getTarget().getClass().getName();//获取当前的类名
		String method = pj.getSignature().getName();//方法名
		
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();//从session中获取用户信息
		//获取用户账号
		String userAccount = (String) session.getAttribute("userAccount");
		if(userAccount!=null){
			//获取用户角色
			//String role = (String) session.getAttribute("role");
			
			//获得IP地址
			String ip = request.getRemoteAddr();
			//获取客户端浏览器
			UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));  
		    Browser userbrowser = userAgent.getBrowser();  
		    OperatingSystem useros = userAgent.getOperatingSystem();
			
			//获取操作模块
			String operationModule = StaticValue.getModuleMap().get(classname);
			//获取操作类型
			String operationType = StaticValue.getMethodMap().get(method);
			
			List<Map<String,String>> list = userDao.findUserDetail("1", userAccount);
			Map<String, String> map = new HashMap<String, String>();
			if (list!=null&&list.size()>0) {
				map = list.get(0);
			}
			String role = (String) map.get("role_name");
			String userName = (String) map.get("user_name");
			//如果不是左侧菜单的模块则不保存
			OperationRecord operationRecord = new OperationRecord();
			if(operationModule!=null && operationType!=null){
				operationRecord.setOperationTime(new Date());
				operationRecord.setAccount(userAccount);
				if(role!=null&&!role.equals("")){
					operationRecord.setRole(role);
				}else{
					operationRecord.setRole("");
				}
				operationRecord.setIpAddress(ip);
				operationRecord.setOperationType(operationType);
				operationRecord.setOperationModule(operationModule);
				operationRecord.setOperationContent("{"+userName+"对"+operationModule+"模块，进行了"+operationType+"操作}");
				operationRecord.setOperationResult("成功");
				operationRecord.setClient("浏览器 : "+userbrowser+" ; 系统 : "+useros);
				operationRecordDao.save(operationRecord);
			}
		}
		
		return res;
	}
	
	/** 记录业务层异常日志信息 */
	public void error(JoinPoint jp, Throwable ex){
		logger.info("调用【"+ jp.getTarget() +"】对象中【"+ jp.getSignature().getName() +"】方法，出现了异常！");
		logger.error(ex.getMessage(), ex);
	}

}
