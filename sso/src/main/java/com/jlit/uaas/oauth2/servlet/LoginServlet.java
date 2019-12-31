package com.jlit.uaas.oauth2.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.jlit.uaas.service.LoginService;
import com.jlit.uaas.service.impl.LoginResultBean;
/**
 * 用户登录servlet
 * @author laizs
 * @time 2014-3-26 上午11:47:15
 * @file LoginServlet.java
 */
public class LoginServlet extends HttpServlet{
	/**
	 * 日志对象
	 */
	private final static Logger logger=Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = -2781468355582755656L;
	private LoginService loginService;

	@Override
	public void init() throws ServletException {
		super.init();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		loginService = (LoginService) ctx.getBean("loginService");
		
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
		try {
			Thread.sleep(300);//为了显示登录动态效果，线程睡眠
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String userAccount=request.getParameter("userAccount");
		String password=request.getParameter("password");
		String vevifiCode=request.getParameter("vevifiCode");//验证码
		LoginResultBean resultBean= this.loginService.login(userAccount, password, vevifiCode, request,response);
		JSONObject resultJson=JSONObject.fromObject(resultBean);
		PrintWriter pw = response.getWriter();
        pw.print(resultJson.toString());
        pw.flush();
        pw.close();
	}
	
	
	
}
