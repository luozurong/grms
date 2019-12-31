package com.jlit.uaas.oauth2.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jlit.uaas.util.ValidateCode;

public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger=Logger.getLogger(ValidateCodeServlet.class);
	public final static String LOGIN_VEVIFICODE="login_vevifiCode";
	@Override
	protected void doGet(HttpServletRequest reqeust,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		//禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		HttpSession session = reqeust.getSession();
		
		ValidateCode vCode = new ValidateCode(100,40,5,100);
		logger.info("用户登录页面，生成验证码："+vCode.getCode());
		session.setAttribute(LOGIN_VEVIFICODE, vCode.getCode());
		
		vCode.write(response.getOutputStream());
	}

}