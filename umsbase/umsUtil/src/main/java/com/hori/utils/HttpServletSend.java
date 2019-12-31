package com.hori.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class HttpServletSend {
	
	/** 
     * 以URL方式发送数据 
     *  
     * @param urlStr 
     *            发送地址 
     * @param contentStr 
     *            发送内容 
     * @param charset 
     *            发送字符集 
     * @param sResult 
     *            返回数据Buffer 
     * @return boolean 发送是否成功 
     */  
    public static boolean sendStrOfPost(String urlStr, String contentStr) {  
    	String charset = "UTF-8";
    	StringBuffer sResult = new StringBuffer();
        boolean bResult = false;  
        String charsetName = charset;  
        URL url = null;  
        HttpURLConnection httpConnection = null;  
        InputStream httpIS = null;  
        java.io.BufferedReader http_reader = null;  
        try {  
            url = new URL(urlStr);  
            httpConnection = (HttpURLConnection) url.openConnection();  
  
            // 设置连接主机超时(单位:毫秒)  
//            httpConnection.setConnectTimeout(Util.getInstance().getIntFromProperties("c1.timeout.httpconn"));  
//            // 设置从主机读取数据超时(单位:毫秒)  
//            httpConnection.setReadTimeout(Util.getInstance().getIntFromProperties("c1.timeout.httpread"));  
  
            httpConnection.setRequestMethod("POST"); // POST方式提交数据  
            httpConnection.setDoOutput(true);  
            httpConnection.setRequestProperty("Content-Length", String.valueOf(contentStr.getBytes().length));  
            PrintWriter out = null;  
            out = new PrintWriter(new OutputStreamWriter(httpConnection.getOutputStream(), charsetName));// 此处改动  
            // 发送请求  
            out.print(contentStr);  
            out.flush();  
            out.close();  
            int responseCode = httpConnection.getResponseCode();  
            if (responseCode == HttpURLConnection.HTTP_OK) {  
                // 发送正常  
                bResult = true;  
  
                // 读取数据  
                httpIS = httpConnection.getInputStream();  
                http_reader = new java.io.BufferedReader(new java.io.InputStreamReader(httpIS, charsetName));  
                String line = null;  
                while ((line = http_reader.readLine()) != null) {  
                    if (sResult.length() > 0) {  
                        sResult.append("\n");  
                    }  
                    sResult.append(line); 
                    String result = sResult.toString();
                    System.out.println(result);
                    if("1".equals(result)){
                    	bResult = false;  
                    }
                }  
//                logger.info("[URL][response][success]" + sResult);  
            } else {  
//                logger.info("[URL][response][failure][code : " + responseCode + " ]");  
            }  
        } catch (IOException e) {  
//            logger.error("[HttpUtil]sendStrOfPost error", e);  
        }  
        finally {  
            try {  
                if (http_reader != null)  
                    http_reader.close();  
                if (httpIS != null)  
                    httpIS.close();  
                if (httpConnection != null)  
                    httpConnection.disconnect();  
            } catch (IOException e) {  
//                logger.error("[HttpUtil]finally error", e);  
            }  
        }  
  
        return bResult;  
    } 
    
    public static void main(String[] args) {
		Map header=new HashMap();
		header.put("token", "test");
		header.put("time_stamp", "13454354");
		Map body=new HashMap();
		body.put("pageSize", "10");
		body.put("pageNum", "1");
		body.put("roleType", "2");
		Map data=new HashMap();
		data.put("header", header);
		data.put("body", body);
		String content=JSONObject.fromObject(data).toString();
		System.out.println(content);
		String url="http://192.168.0.142:8080/chims/servlet/searchPatientCaseList";
		sendStrOfPost(url,content ) ;
	}
}
