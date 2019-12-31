package com.jlit.uaas.util;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台与前台交互数据工具类
 * @author wenl
 */
public class AjaxJsonAndXmlUtil {

	/**
	 * Json字符串输出到前台
	 * @param json 
	 * @param response
	 * @throws Exception
	 */
	public static void writeJson(String json, HttpServletResponse response){
		try{
	        response.setHeader("ContentType", "text/json");  
	        response.setCharacterEncoding("utf-8");  
	        PrintWriter pw = response.getWriter();  
	        pw.write(json);  
	        pw.flush();  
	        pw.close(); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Xml字符串输出到前台
	 * @param xml
	 * @param response
	 */
	public static void writeXml(String xml, HttpServletResponse response){
		try{
	        response.setHeader("ContentType", "text/xml");  
	        response.setCharacterEncoding("utf-8");  
	        PrintWriter pw = response.getWriter();  
	        pw.write(xml);  
	        pw.flush();  
	        pw.close(); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * html字符串输出到前台
	 * @param xml
	 * @param response
	 */
	public static void writeHtml(String xml, HttpServletResponse response){
		try{
	        response.setHeader("ContentType", "text/html");  
	        response.setCharacterEncoding("utf-8");  
	        PrintWriter pw = response.getWriter();  
	        pw.write(xml);  
	        pw.flush();  
	        pw.close(); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
 