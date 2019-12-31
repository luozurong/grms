package com.jlit.memcached.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestUtils
{

	public static String cookieDomain = "";

	public static String cookiePath = "/";
	
	/**
	 * 获取COOKIE
	 * @param request
	 * @param name
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) 
	{	
		//先从request里面取，取不到再从cookie里面取
		String value=(String) request.getAttribute(name);
		if(null!=value){
			Cookie cookie = new Cookie(name, value);			
			return cookie;
		}
		
		Cookie[] cookies = request.getCookies();
		if(cookies == null)
			return null;
		for (int i = 0; i < cookies.length; i++) 
		{
			if (name.equals(cookies[i].getName())) 
			{
				return cookies[i];
			}
		}
		return null;
	}

	/**
	 * 设置COOKIE
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String value, int maxAge) 
	{
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		if(cookieDomain!=null && cookieDomain.indexOf('.')!=-1)
		{
			cookie.setDomain('.' + cookieDomain);
		}
		cookie.setPath(cookiePath);
		response.addCookie(cookie);
		//设置一份到request中
		request.setAttribute(name, value);
	}
	
	/**
	 * 设置COOKIE
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String value) 
	{
		
		Cookie cookie = new Cookie(name, value);
		if(cookieDomain!=null && cookieDomain.indexOf('.')!=-1)
		{
			cookie.setDomain('.' + cookieDomain);
		}
		cookie.setPath(cookiePath);
		response.addCookie(cookie);
		//设置一份到request中
		request.setAttribute(name, value);
	}
	

	/**
	 * 从URL地址中解析出URL前缀，例如
	 * http://www.sina.com.cn:8081/index.jsp -> http://www.sina.com.cn:8081
	 * @param request
	 * @return
	 */
	public static String getUrlPrefix(HttpServletRequest request)
	{
		StringBuffer url = new StringBuffer(request.getScheme());
		url.append("://");
		url.append(request.getServerName());
		int port = request.getServerPort();
		if(port!=80)
		{
			url.append(":");
			url.append(port);
		}
		return url.toString();
	}
	
	/**
	 * 获取访问的URL全路径
	 * @param request
	 * @return
	 */
	public static String getRequestURL(HttpServletRequest request)
	{
		StringBuffer url = new StringBuffer(request.getRequestURI());
		String param = request.getQueryString();
		if(param!=null){
			url.append('?');
			url.append(param);
		}
		String path = url.toString();
		return path.substring(request.getContextPath().length());
	}
}
