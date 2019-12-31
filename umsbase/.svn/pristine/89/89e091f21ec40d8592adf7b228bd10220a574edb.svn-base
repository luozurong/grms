package com.hori.adms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hori.dao.UserDetailDao;
import com.hori.service.RedisCacheService;
import com.hori.utils.ServletUtil;

@WebServlet(name="GetEmployeesByDepServlet",value="/servlet/getEmployeesByDepServlet")
public class GetEmployeesByDepServlet extends HttpServlet {
	private static final long serialVersionUID = -264363l;
	private RedisCacheService redisCacheService;
	private UserDetailDao userDetailDao;
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		redisCacheService=(RedisCacheService) ctx.getBean("redisCacheService");
		userDetailDao=(UserDetailDao) ctx.getBean("userDetailDao");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	//处理登录请求
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result="0";
    	String reason="";
    	String reqStr = ServletUtil.praseRequst(request);
    	String token=null;
		String timeStamp=null;
		 List<Map<String,Object>>  userDetailList=new ArrayList<Map<String,Object>>();
    	if(StringUtils.isBlank(reqStr)){
    		
    		result="001";
			reason ="消息格式错误！";
			returnResult(response,result,reason,userDetailList);
			return;
    	}
		JSONObject object=JSON.parseObject(reqStr);
		JSONObject body=object.getJSONObject("body");
		JSONObject header=object.getJSONObject("header");
		token = header.getString("token");
		timeStamp  = header.getString("time_stamp");
		String departmentId=body.getString("departmentId");
		if(token!=null){
			if(departmentId!=null){
				
				userDetailList = userDetailDao.findUserDetailByDepart(departmentId);
				returnResult(response,result,reason,userDetailList);

			}else{
				result="001";
				reason ="部门标识不能为空为必填！";
				returnResult(response,result,reason,userDetailList);
				return;
			}
		}else{
			result="001";
			reason ="token为必填！";
			returnResult(response,result,reason,userDetailList);
			return;
		}
		
	}

	private  static void returnResult(HttpServletResponse response ,String result,String reason,List<Map<String,Object>> userDetailList) throws IOException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		resultMap.put("reason", reason);
		resultMap.put("userDetailList", userDetailList);
		String json = JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd HH:mm:ss");
		ServletUtil.sendResponse(response, json);
	}

}
