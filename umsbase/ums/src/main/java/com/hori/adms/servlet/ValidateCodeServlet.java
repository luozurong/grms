package com.hori.adms.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hori.adms.util.ValidateCode;
import com.hori.service.RedisCacheService;

@WebServlet(name="ValidateCodeServlet",value="/servlet/validateCode")
public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger=Logger.getLogger(ValidateCodeServlet.class);
	public final static String LOGIN_VEVIFICODE="login_vevifiCode";
	private RedisCacheService redisCacheService;
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		redisCacheService=(RedisCacheService) ctx.getBean("redisCacheService");
	}
	@Override
	protected void doGet(HttpServletRequest reqeust,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		//禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ValidateCode vCode = new ValidateCode(100,40,5,100);
		response.setHeader("code", vCode.getCode());
		HttpSession session = reqeust.getSession();
		logger.info("生成验证码："+vCode.getCode());
		redisCacheService.set(vCode.getCode(), vCode.getCode(), 5*60);
		session.setAttribute(LOGIN_VEVIFICODE, vCode.getCode());
		vCode.write(response.getOutputStream());
	}

}