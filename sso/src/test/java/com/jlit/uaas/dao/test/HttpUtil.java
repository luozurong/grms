package com.jlit.uaas.dao.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	/**
	 * 发送post请求
	 * 
	 * @param url
	 * @param parameters
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public final static void post(String url, Map parameters) throws ClientProtocolException, IOException {
		// POST的URL
		HttpPost httppost = new HttpPost(url);
		// 建立HttpPost对象
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Set<String> keys=parameters.keySet();
		for(String key:keys){
			// 建立一个NameValuePair数组，用于存储欲传送的参数
			params.add(new BasicNameValuePair(key, (String)parameters.get(key)));
		}
		// 添加参数
		httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		// 设置编码
		HttpResponse response =HttpClient4s.getHttpClient().execute(httppost);
		System.out.println(response);
		// 发送Post,并返回一个HttpResponse对象
		// Header header = response.getFirstHeader("Content-Length");
		// String Length=header.getValue();
		// 上面两行可以得到指定的Header
		if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
			String result = EntityUtils.toString(response.getEntity(),"utf-8");
			// 得到返回的字符串
			System.out.println(result);
		}else{
			String result = EntityUtils.toString(response.getEntity(),"utf-8");
			// 得到返回的字符串
			System.out.println(result);
		}
	}
	
	
	 static class  HttpClient4s {
		 public static HttpClient getHttpClient(){
			 HttpClient client = new DefaultHttpClient();  
			// client = WebClientDevWrapper.wrapClient(client); 
			 return client;
		 }
	 }
	

}