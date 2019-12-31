package com.hori.memcached.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hori.memcached.session.SessionService;



public class Webutil {

	private static final Log log = LogFactory.getLog(Webutil.class);
	
	
	private static String useMemcache = "";
	
	 /**
	 * 资源文件名
	 */	
	private static final String GLOBAL_PROPERTIES="memcached.properties";
	
	/**
	 * 持久属性集
	 */
	private static Properties properties;
	
	private static String propertyname = "useMemcache";
	
	
	static{
		
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream input = cl.getResourceAsStream(GLOBAL_PROPERTIES);
		properties = new Properties();
		try {
			properties.load(input);
			useMemcache = (String) properties.getProperty(propertyname);
		} catch (IOException e) {
			e.printStackTrace();
			log.info("load properties文件出错！文件名称：" + GLOBAL_PROPERTIES);
		}
	}
	
	/**
	 * 设置request属性
	 * @param request 
	 * @param attrname 属性名称
	 * @param value 属性值<br>
	 * 调用方式如：Webutil.setRequestAttribute(req,usernam,allen)
	 */
	public static void setRequestAttribute(HttpServletRequest request,String attrname,Object value){
		request.setAttribute(attrname, value);		
	}
		
	/**
	 * 获取某属性在request中的属性
	 * @param request 
	 * @param attrname 属性名称
	 * 调用方式如：Webutil.getRequestAttribute(req,usernam)
	 */
	public static Object getRequestAttribute(HttpServletRequest request,String attrname){
		return request.getAttribute(attrname);
	}
	
	/**
	 * 设置session属性
	 * @param request request对象
	 * @param attrname 属性名称
	 * @param value 属性值<br>
	 * 调用方式如：Webutil.setSessionAttribute(request,usernam,allen)
	 */
	public static void setSessionAttribute(HttpServletRequest request,String attrname,Object value){
		
		if(!"false".equals(useMemcache) && useMemcache!=null){
			
			String cid = getCid(request);
			String key = cid+"_"+attrname;
			
			SessionService.getInstance().save(key, value);
		}else{
			request.getSession().setAttribute(attrname, value);
		}
	}
	
	
	
	/**   
	 * getSid:取session id  
	 *   
	 * @param  @param request
	 * @param  @return    设定文件   
	 * @return String    DOM对象   
	 * @throws    
	 * @since  CodingExample　Ver 1.0  
	 * @author   daihf
	 * @Date     2011    May 5, 2011     1:35:48 PM
	*/   
	private static String getSid(HttpServletRequest request){
		StringBuffer sb = new StringBuffer(request.getSession().getId());
		sb.append("_");
		sb.append(Constants.serverName);
		return sb.toString();
	}
	
	
	/**   
	 * getCid:取cookie id  
	 *   
	 * @param  @param request
	 * @param  @return    设定文件   
	 * @return String    DOM对象   
	 * @throws    
	 * @since  CodingExample　Ver 1.0  
	 * @author   daihf
	 * @Date     2011    May 5, 2011     1:36:16 PM
	*/   
	public static String getCid(HttpServletRequest request){
		Cookie cookie = RequestUtils.getCookie(request,
				Constants.session_key_flag_of_cookie);
		if (cookie != null) {
			// 取出session的ID
			return cookie.getValue();
			
		}else{
			return "";
		}
	}
	
	public static void setCookie(HttpServletRequest request,HttpServletResponse response){
		//如果cookie未空，则设置一个；如果不为空，则什么都不做
		if(null==RequestUtils.getCookie(request, Constants.session_key_flag_of_cookie)||"".equals(RequestUtils.getCookie(request, Constants.session_key_flag_of_cookie))){
			String sid = getSid( request);
			RequestUtils.setCookie(request, response, Constants.session_key_flag_of_cookie, sid);
		}
	}
	
	
	public static void validateCookie(HttpServletRequest request,HttpServletResponse response){
		Cookie cookie = RequestUtils.getCookie(request,
				Constants.session_key_flag_of_cookie);
		
		if (cookie != null) {
			// 清除cookie
			RequestUtils.setCookie(request, response,
					Constants.session_key_flag_of_cookie, "", 0);
		}
	}
	
	/**
	 * 获取某属性在session中的属性
	 * @param request 
	 * @param attrname 属性名称
	 * 调用方式如：Webutil.getSessionAttribute(request,usernam)
	 */
	public static Object getSessionAttribute(HttpServletRequest request,String attrname){
		
		if(!"false".equals(useMemcache)){
			
			String cid = getCid( request);
			if("".equals(cid)){
				return null;
			}
			String key = cid+"_"+attrname;
			return SessionService.getInstance().get(key, true);
			
		}else{
			
			return request.getSession().getAttribute(attrname);
			
		}
	}
	
	public static Object getSessionAttribute(HttpServletRequest request, String cookieId, String attrname){
		if(!"false".equals(useMemcache)){
			if("".equals(cookieId)){
				return null;
			}
			String key = cookieId+"_"+attrname;
			return SessionService.getInstance().get(key, true);
			
		}else{
			
			return request.getSession().getAttribute(attrname);
			
		}
	}
	/**
	 * 获取某属性在session中的属性
	 * @param request 
	 * @param attrname 属性名称
	 * 调用方式如：Webutil.getSessionAttribute(request,usernam)
	 */
	public static Object getAttribute(HttpServletRequest request,String attrname){
		
		if(!"false".equals(useMemcache)){
			
			String sid = getSid( request);
			String key = sid+"_"+attrname;
			
			return SessionService.getInstance().get(key, true);
		}else{
			return request.getSession().getAttribute(attrname);
		}
	}
	
	/**
	 * 设置session属性
	 * @param request request对象
	 * @param attrname 属性名称
	 * @param value 属性值<br>
	 * 调用方式如：Webutil.setSessionAttribute(request,usernam,allen)
	 */
	public static void setAttribute(HttpServletRequest request,String attrname,String value){
		
		if(!"false".equals(useMemcache) && useMemcache!=null){
			String sid = getSid( request);
			String key = sid+"_"+attrname;
			SessionService.getInstance().save(key, value);
		}else{
			request.getSession().setAttribute(attrname, value);
		}
	}
	
	
	
	/**
	 * 从session中移除某一属性值
	 * @param session
	 * @param attrname
	 */
	public static void removeSessionAttribute(HttpSession session,String attrname){
		session.removeAttribute(attrname);
	}	
	
	/**
	 * 从session中移除某一属性值
	 * @param session
	 * @param attrname
	 */
	public static void removeSessionAttribute(HttpServletRequest request,String attrname){
		String cid = getCid( request);
		String key = cid+"_"+attrname;
		SessionService.getInstance().remove(key);
	}
	
	/**
	 * 设置session失效
	 * @param session
	 */
	public static void sessionInvalidate(HttpSession session){
		session.invalidate();
	}

	/**
	 * 取得Sessionid
	 * @param session
	 * @return
	 */
	public static String getSessionId(HttpSession session){
		return session.getId();
	}

	
	public static void sessionInvalidate(HttpServletRequest request){
		String cid = getCid( request);
		SessionService.getInstance().remove(cid);
	}
	
}
