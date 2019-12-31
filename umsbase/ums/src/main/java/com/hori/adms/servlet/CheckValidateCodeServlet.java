package com.hori.adms.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@WebServlet(name="CheckValidateCodeServlet",value="/checkValidateCode")
public class CheckValidateCodeServlet extends HttpServlet{
	/**
	 * 日志对象
	 */
	private final static Logger logger=Logger.getLogger(CheckValidateCodeServlet.class);
	private static final long serialVersionUID = -2781468355582755656L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	//处理登录请求
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vCode=request.getParameter("vCode");
		String sessionVevifiCode=(String) request.getSession().getAttribute(ValidateCodeServlet.LOGIN_VEVIFICODE);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8"); 
		PrintWriter pw = response.getWriter();
        pw.print(StringUtils.equalsIgnoreCase(vCode, sessionVevifiCode));
        pw.flush();
        pw.close();
	}
	
	
	
}
