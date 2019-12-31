package com.hori.adms.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hori.model.User;
import com.hori.service.RedisCacheService;
import com.hori.service.UserService;
import com.hori.service.XmppAdvertisementService;
import com.hori.utils.Md5;
public class ModifyPwdServlet extends HttpServlet{
	/**
	 * 日志对象
	 */
	@SuppressWarnings("unused")
	private final static Logger logger=Logger.getLogger(ModifyPwdServlet.class);
	
	/**
	 * 手机验证码的key
	 */
	private static final String CODEBYMOBILEKEY = "cache|getCodeByMobile";
	
	
    
	private static final long serialVersionUID = -2781468355582755656L;
	private RedisCacheService redisCacheService;
	private UserService userService;
	private XmppAdvertisementService xmppAdvertisementService;
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		redisCacheService=(RedisCacheService) ctx.getBean("redisCacheService");
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
		String errors = "";
		errors = modifyPwd(request,  response);
		
		
		PrintWriter out = response.getWriter();
		out.print(errors);
		out.flush();
		out.close();
	}
	
    protected String modifyPwd(HttpServletRequest request, HttpServletResponse response ){
		
		String errors = "";
		
		String userAccount = request.getParameter("userAccount");
		String password = request.getParameter("password");
		String repassword = request.getParameter("validatepw");
		String mobile_check_code = request.getParameter("check_code");
		
		if(StringUtils.isBlank(mobile_check_code)){
			errors ="验证码不能为空！";
			return errors;
		}
		
		if(StringUtils.isBlank(userAccount)){
			errors = "手机号码不能为空！";
			return errors;
		}
		
		if(StringUtils.isBlank(password)){
			errors ="密码不能为空！";
			return errors;
		}
		if(repassword==null||"".equals(repassword)){
			errors ="确认密码不能为空！";
			return errors;
		}
		
		if(!password.equals(repassword)){
			errors ="密码与确认密码不相同！";
			return errors;
		}
		if(password.length()<6 || password.length()>20){
			errors ="密码长度错误，请输入6-20位长度的密码！";
			return errors;
		}
		
		String cacheCodeKey = userAccount+"|"+CODEBYMOBILEKEY;
		String randomNum = redisCacheService.get(cacheCodeKey);
		randomNum = StringUtils.isBlank(randomNum)?"":randomNum;
		User user  = userService.getUserByAccount(userAccount);
		if(null==user){
			errors ="该用户不存在!";
			return errors;
		}
		if(randomNum.equals(mobile_check_code)){
			//重置验证码，以防复用
			//修改密码为新密码
			Md5 md5 = new Md5();
			String passwd = md5.getMD5Str(password);
			user.setPassword(passwd);
			redisCacheService.del(cacheCodeKey);
			try {
				xmppAdvertisementService.updateUser(user, password,"");
			} catch (Exception e) {
				e.printStackTrace();
			}
			userService.update(user);
			errors = "successful";
			
		}else{
			errors ="验证码错误！";
		}
		return errors;
	}
	
    
   
}
