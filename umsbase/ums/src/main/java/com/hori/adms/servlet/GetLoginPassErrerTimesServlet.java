package com.hori.adms.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hori.service.RedisCacheService;
import com.hori.utils.StaticValue;
@WebServlet(name="GetLoginPassErrerTimesServlet",value="/getLoginPassErrerTimes")
public class GetLoginPassErrerTimesServlet extends HttpServlet{
	/**
	 * 日志对象
	 */
	private final static Logger logger=Logger.getLogger(GetLoginPassErrerTimesServlet.class);
	private static final long serialVersionUID = -2781468355582755656L;
	private RedisCacheService redisCacheService;
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		redisCacheService=(RedisCacheService) ctx.getBean("redisCacheService");
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
		String userAccount=request.getParameter("userAccount");
		String loginPassErrer =   this.redisCacheService.get(userAccount+StaticValue.LOGINPASSERRERTIMES);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8"); 
		PrintWriter pw = response.getWriter();
        pw.print(StringUtils.isNotBlank(loginPassErrer)?Integer.parseInt(loginPassErrer):0);
        pw.flush();
        pw.close();
	}
	
	
	
}
