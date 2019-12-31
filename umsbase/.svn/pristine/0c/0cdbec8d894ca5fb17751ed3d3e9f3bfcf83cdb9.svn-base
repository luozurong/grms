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
import com.hori.service.RedisCacheService;
import com.hori.service.UserDetailService;
import com.hori.utils.ServletUtil;

@WebServlet(name="ModifyPhotoHeadServlet",value="/servlet/modifyPhotoHead")
public class ModifyPhotoHeadServlet extends HttpServlet{
	private static final long serialVersionUID = -2l;
	private RedisCacheService redisCacheService;
	private UserDetailService userDetailService;
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		redisCacheService=(RedisCacheService) ctx.getBean("redisCacheService");
		userDetailService=(UserDetailService) ctx.getBean("userDetailService");
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
    	if(StringUtils.isBlank(reqStr)){
    		
    		result="001";
			reason ="消息格式错误！";
			returnResult(response,result,reason);
			return;
    	}
		JSONObject object=JSON.parseObject(reqStr);
		JSONObject body=object.getJSONObject("body");
		JSONObject header=object.getJSONObject("header");
		token = header.getString("token");
		timeStamp  = header.getString("time_stamp");
		String address=body.getString("address");
		if(token!=null){
			String userAccount = redisCacheService.get(token);
			if(userAccount!=null){
				userDetailService.updateUserDetailPhoto(userAccount, address);
				returnResult(response,result,reason);

			}else{
				result="003";
				reason ="token过期！";
				returnResult(response,result,reason);
				return;
			}
		}else{
			result="001";
			reason ="token为必填！";
			returnResult(response,result,reason);
			return;
		}
		
	}

	private  static void returnResult(HttpServletResponse response ,String result,String reason) throws IOException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		resultMap.put("reason", reason);
		String json = JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd HH:mm:ss");
		ServletUtil.sendResponse(response, json);
	}

}