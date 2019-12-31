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
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.hori.model.User;
import com.hori.service.RedisCacheService;
import com.hori.service.UserService;
import com.hori.service.XmppAdvertisementService;
import com.hori.util.Encrypt;
import com.hori.utils.Md5;
import com.hori.utils.ServletUtil;
import com.alibaba.fastjson.JSONObject;

@WebServlet(name="ModifyPwdByOldPwdServlet",value="/servlet/modifyPwdByOldPwd")
public class ModifyPwdByOldPwdServlet extends HttpServlet{
	/**
	 * 日志对象
	 */
	private final static Logger logger=Logger.getLogger(ModifyPwdByMobileServlet.class);
	
	/**
	 * 手机验证码的key
	 */
	private static final String CODEBYMOBILEKEY = "cache|getCodeByMobile";
	
	
    
	private static final long serialVersionUID = -2781468355582755656L;
	private UserService userService;
	private XmppAdvertisementService xmppAdvertisementService;

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		userService=(UserService) ctx.getBean("userService");
		xmppAdvertisementService=(XmppAdvertisementService) ctx.getBean("xmppAdvertisementService");

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
		String password = body.getString("password");
		String passwordNew = body.getString("passwordNew");
		
		if(StringUtils.isBlank(passwordNew)){
			result="005";
			reason ="新密码不能为空！";
			returnResult(response,result,reason);

			return ;
		}
		
		if(StringUtils.isBlank(userAccount)){
			result="005";
			reason ="用户名不能为空！";
			returnResult(response,result,reason);
			return ;
		}
		
		if(StringUtils.isBlank(password)){			
			result="005";
			reason ="密码不能为空！";
			returnResult(response,result,reason);
			return ;
		}
	
		
	/*	if(passwordNew.length()<6 || passwordNew.length()>20){
			result="005";
			reason ="密码长度错误，请输入6-20位长度的密码！";
			returnResult(response,result,reason);
			return ;
		}*/
		User user  = userService.getUserByAccount(userAccount);
		if(null==user){
			result="005";
			reason ="该用户不存在！";
			returnResult(response,result,reason);
			return ;
		}
		String password1=Encrypt.e(user.getPassword()+userAccount).toUpperCase();
	
		if(password1.equals(password)){
			//重置验证码，以防复用
			//修改密码为新密码
			String passwd =Encrypt.e(passwordNew).toUpperCase();
			user.setPassword(passwd);
			userService.update(user);
			returnResult(response,result,reason);
			try {
				int i=xmppAdvertisementService.updateUser(user, passwordNew, "");
				if(i!=0){
					throw new DocumentException();
				}
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="006";
				reason ="同步openfire错误！";
				returnResult(response,result,reason);	
			}
		}else{
			result="005";
			reason ="密码错误！";
			returnResult(response,result,reason);		
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
