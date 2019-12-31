package com.jlit.uaas.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jlit.uaas.service.LoginService;
import com.jlit.uaas.service.UserService;
import com.jlit.uaas.service.impl.LoginResultBean;

import net.sf.json.JSONObject;

/**
 * 登录controller
 * @author laizs
 *
 */
@Controller
public class LoginController {
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="loginService")
	private LoginService loginService;
	/**
	 * 登录页面
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		//登录成功后返回的uri
		String redirectUri=request.getParameter("redirectUri");
		//登录窗口，默认是default
		String loginWin=request.getParameter("loginWin");
		if(StringUtils.isBlank(loginWin)){
			loginWin="default";
		}
		//存到session
		request.getSession().setAttribute("redirectUri", redirectUri);
		request.getSession().setAttribute("loginWin", loginWin);
		return "/login";
	}
	/**
	 * 登录验证
	 * ajax请求
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/login/loginAction")
	@ResponseBody
	public String loginAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
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
		return resultJson.toString();
	}
	/**
	 * 登录成功后的重定向
	 * @return
	 */
	@RequestMapping("/login/loginedRedirect")
	public String loginedRedirect(HttpServletRequest request, HttpServletResponse response){
		boolean isLogined=this.loginService.isLogin(request);
		if(!isLogined){//登录
			return "redirect:/login";
		}
		//从session中取出返回的uri
		String redirectUri=(String) request.getSession().getAttribute("redirectUri");
		request.setAttribute("redirectUri", redirectUri);
		if(StringUtils.isBlank(redirectUri)){
			throw new  RuntimeException("session获取不到redirectUri");
		}
		return "/logined";
		
	}
	
}
