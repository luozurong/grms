package com.jlit.uaas.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 发送http请求工具类
 * @author lzs
 *
 */
public class HttpUrlConnectionUtil {
	public final static int ConnectTimeout=5000;//链接超时时间
	/**
	 * 发送get请求
	 * @param url
	 * @param parameters 参数  key参数名  value 参数名称
	 * @return
	 * @throws Exception
	 */
	public static  String sendGetHttpConnetion(String url,Map parameters) throws Exception{
		HttpURLConnection jconn = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			StringBuffer querySb=new StringBuffer(50);
			Iterator iterator= parameters.entrySet().iterator();
			while(iterator.hasNext()){
			 Entry e=(Entry) iterator.next();
			 querySb.append(e.getKey()).append("=").append(urlencode( e.getValue().toString())).append("&");
			}
			String param=new String(querySb);
			url=param==null&&param.length()<1?url:url+"?"+param;
			URL urlStr = new URL(url);
			jconn = (HttpURLConnection) urlStr.openConnection();
			jconn.setDoOutput(true);
			jconn.setDoInput(true);
			jconn.connect();
			InputStream in = jconn.getInputStream();
			byte[] buf = new byte[4096];
			int bytesRead;
			while ((bytesRead = in.read(buf)) != -1) {
				byteArrayOutputStream.write(buf, 0, bytesRead);
			}
			String strRead = new String(byteArrayOutputStream.toByteArray());//响应结果
			return strRead;
		} catch (Exception e) {
			throw e;
		} 
		finally {
			jconn.disconnect();
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				throw e;
			}

		}
	}
	/**
	 * 发送post请求
	 * @param url
	 * @param parameters 参数  key参数名  value 参数名称
	 * @return
	 * @throws Exception
	 */
	public static String sendPostHttpConnetion(String url,Map parameters) throws Exception{
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			StringBuffer querySb=new StringBuffer(50);
			Iterator iterator= parameters.entrySet().iterator();
			while(iterator.hasNext()){
			 Entry e=(Entry) iterator.next();
			 querySb.append(e.getKey()).append("=").append(urlencode(e.getValue().toString())).append("&");
			}
			String param=new String(querySb);
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(ConnectTimeout);
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer rsSb=new StringBuffer();
			while ((line = in.readLine()) != null) {
				rsSb.append(line);
			}
			return new String(rsSb);
		} catch (Exception e) {
			throw e;
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * urlencode
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String urlencode(String str) throws UnsupportedEncodingException{
		if(null==str){
			return null;
		}
		return URLEncoder.encode(str, "utf-8");
	}
	public static void main(String[] args) {
		String url="http://192.168.0.3:8090/uums/servlet/MessageSendControlServlet";
		Map parameters=new HashMap();
		parameters.put("deviceSeril", "liuhd");
		parameters.put("title", "测试");
		parameters.put("url", "测试内容测试内容测试内容测试内容测试内容");
		try{
			System.out.println(sendGetHttpConnetion(url, parameters));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
