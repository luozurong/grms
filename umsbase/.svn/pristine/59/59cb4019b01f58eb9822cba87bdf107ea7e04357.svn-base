package com.hori.adms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

import org.apache.log4j.Logger;

import com.hori.utils.JsonConfigSetter;

public class ServletUtil {
	static Logger  log =Logger.getLogger(ServletUtil.class);
	/**
	 * 解析请求数据
	 * 
	 * @param req
	 * @return
	 * @throws IOException
	 */
	 public static String praseRequst(HttpServletRequest req) throws IOException{
		 BufferedReader rd = new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
		 StringBuffer sb = new StringBuffer();
		 int ch;
		 while ((ch = rd.read()) > -1){
		 sb.append((char) ch);
		 }
		 return sb.toString();
	 }
//	public static String praseRequst(HttpServletRequest req){
//		GetRepairFormsReq reqMessage = new GetRepairFormsReq();
//		Map<String, Map> header = new HashMap<String, Map>();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("token", "13564053872815705a8d6e1a4593ad29");
//		map.put("time_stamp", "13213212");
//		reqMessage.setHeader(map);
//		Map<String, String> map2 = new HashMap<String, String>();
//		map2.put("pageSize", "50");
//		map2.put("pageNum", "1");
//		reqMessage.setBody(map2);
//		String jsonString = JSONObject.fromObject(reqMessage).toString();
//		return jsonString;
//	}
	
	public static String sendResponse(HttpServletResponse resp,String jsonString) throws IOException{
		log.info("发送给客户端接口响应"+jsonString);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=UTF-8");
		PrintWriter printWriter = resp.getWriter();
		printWriter.write(jsonString);
		printWriter.flush();
		printWriter.close();
		return "success";
	}
	
	
	public static void returnResponseStr(HttpServletResponse resp,String jsonString) throws IOException{
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=UTF-8");
		PrintWriter printWriter = resp.getWriter();
		printWriter.write(jsonString);
		printWriter.flush();
		printWriter.close();
		return ;
	}
	/**
	 * 转换JSON对象属性
	 * @return
	 * @author viliam
	 */
	public static JSONObject JsonDataConvert(Object obj){
		JsonConfig jsonConfig = new JsonConfig();
		// 设置Integer类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Double类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Float类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Float.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		JSONObject json = JSONObject.fromObject(obj, jsonConfig);
		return json;
	}
	/**
	 * 转换JSON对象属性
	 * @return
	 * @author viliam
	 */
	public static JSONArray JsonDataConvert(List list){
		JsonConfig jsonConfig = new JsonConfig();
		// 设置Integer类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Double类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Float类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Float.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		return json;
	}
	
	/**
	 * 转换JSON对象属性
	 * @return
	 * @author viliam
	 */
	public static JSONObject JsonDataConvert(Object obj,String[] dateProperties, String datePattern){
		JsonConfig jsonConfig = new JsonConfig();
		JsonConfigSetter.setJsonDateFormatByDateType(jsonConfig, dateProperties, datePattern);
		// 设置Integer类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Double类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Float类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Float.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		JSONObject json = JSONObject.fromObject(obj, jsonConfig);
		return json;
	}
	
	/**
	 * 转换JSON对象属性(转换字段与转换类型对应)
	 * @return
	 * @author viliam
	 */
	public static JSONObject JsonDataConvert(Object obj,String[] dateProperties,String[] datePattern){
		JsonConfig jsonConfig = new JsonConfig();
		JsonConfigSetter.setJsonDateFormatByDateType(jsonConfig, dateProperties, datePattern);
	    // 设置Integer类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Double类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Float类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Float.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		
		
		
		JSONObject json = JSONObject.fromObject(obj, jsonConfig);
		return json;
	}
	
	/**
	 * 转换JSON List属性
	 * @return
	 * @author viliam
	 */
	public static JSONArray JsonDataConvert(List list,String[] dateProperties,final String datePattern){
		JsonConfig jsonConfig = new JsonConfig();
		JsonConfigSetter.setJsonDateFormatByDateType(jsonConfig, dateProperties, datePattern);
		// 设置Integer类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Double类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Float类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Float.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		return json;
	}
	
	/**
	 * 转换JSON对象属性(转换字段与转换类型对应)
	 * @return
	 * @author viliam
	 */
	public static JSONArray JsonDataConvert(List list,String[] dateProperties,String[] datePattern){
		JsonConfig jsonConfig = new JsonConfig();
		JsonConfigSetter.setJsonDateFormatByDateType(jsonConfig, dateProperties, datePattern);
		// 设置Integer类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Double类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		//设置Float类型为空的默认值  
		jsonConfig.registerDefaultValueProcessor(Float.class, new DefaultValueProcessor(){
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		});
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		return json;
	}
}
