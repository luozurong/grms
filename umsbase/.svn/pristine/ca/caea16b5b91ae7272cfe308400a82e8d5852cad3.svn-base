package com.hori.adms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.hori.model.User;
import com.hori.service.RedisCacheService;
import com.hori.service.UserService;
import com.hori.utils.Md5;
import com.hori.utils.ServletUtil;
import com.alibaba.fastjson.JSONObject;

@WebServlet(name="ModifyMobileByCheckCodeServlet",value="/servlet/modifyPwdByCheckCode")
public class ModifyMobileByCheckCodeServlet extends HttpServlet{
	/**
	 * 日志对象
	 */
	private final static Logger logger=Logger.getLogger(ModifyPwdByMobileServlet.class);
	
	/**
	 * 手机验证码的key
	 */
	private static final String CODEBYMOBILEKEY = "cache|getCodeByMobile";
	
	
    
	private static final long serialVersionUID = -2781468355582755656L;
	private RedisCacheService redisCacheService;
	private UserService userService;
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		redisCacheService=(RedisCacheService) ctx.getBean("redisCacheService");
		userService=(UserService) ctx.getBean("userService");
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
			modifyPwd(request,  response);
	}
	
    protected void modifyPwd(HttpServletRequest request, HttpServletResponse response ) throws IOException{
    	
    	String result="0";
    	String reason="";
    	String reqStr = ServletUtil.praseRequst(request);
    	if(StringUtils.isBlank(reqStr)){
    		
    		result="001";
			reason ="消息格式错误！";
			returnResult(response,result,reason);
			return;
    	}
		JSONObject object=JSON.parseObject(reqStr);
		JSONObject body=object.getJSONObject("body");
		String userAccount = body.getString("userAccount");
		String mobileOld = body.getString("mobileOld");
		String mobileNew = body.getString("mobileNew");
		String mobile_check_code_Old = body.getString("checkCodeOld");
		String mobile_check_code_New = body.getString("checkCodeNew");

		if(StringUtils.isBlank(mobileOld)){
			result="005";
			reason ="旧手机号码不能为空！";
			returnResult(response,result,reason);

			return ;
		}
		
		if(StringUtils.isBlank(mobileNew)){
			result="005";
			reason ="新手机号码不能为空！";
			returnResult(response,result,reason);
			return ;
		}
		
		if(StringUtils.isBlank(mobile_check_code_Old)){			
			result="005";
			reason ="旧手机验证码不能为空！";
			returnResult(response,result,reason);
			return ;
		}
		User user  = userService.getUserByAccount(userAccount);
		if(null==user){
			result="005";
			reason ="该用户不存在！";
			returnResult(response,result,reason);
			return ;
		}
		User user2  = userService.getUserByAccount(mobileNew);
		if(user2!=null){
			result="005";
			reason ="新手机号码不能是已有账号 ！";
			returnResult(response,result,reason);
			return ;
		}
		//获取旧验证码
		String cacheCodeKeyOld = mobileOld+"|"+CODEBYMOBILEKEY;
		String randomNumOld = redisCacheService.get(cacheCodeKeyOld);
		randomNumOld = StringUtils.isBlank(randomNumOld)?"":randomNumOld;
		//获取新验证码
		String cacheCodeKeyNew = mobileNew+"|"+CODEBYMOBILEKEY;
		String randomNumNew= redisCacheService.get(cacheCodeKeyNew);
		randomNumNew = StringUtils.isBlank(randomNumNew)?"":randomNumNew;
	     //验证旧
		if(randomNumOld.equals(mobile_check_code_Old)){
			if(randomNumNew.equals(mobile_check_code_New)){
				//重置验证码，以防复用
				redisCacheService.del(cacheCodeKeyOld);
				redisCacheService.del(cacheCodeKeyNew);
				userService.alterMobile(user, mobileNew);
				returnResult(response,result,reason);

			}else{
				result="005";
				reason ="新手机验证码错误！";
				returnResult(response,result,reason);
				return;
			}
		}else{
				result="005";
				reason ="旧手机验证码错误！";
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