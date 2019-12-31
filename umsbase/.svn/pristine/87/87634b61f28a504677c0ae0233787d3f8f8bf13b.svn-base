package com.hori.adms.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import oauth.signpost.OAuthConsumer;
public class OauthHttpUrlConnectionUtil {
	public final static  String RESPONSECODE="responseCode";
	public final static  String RESPONSESTR="responseStr";
	public static Map oauthConnect(String urlStr,OAuthConsumer consumer) throws Exception{
		Map result=new HashMap();
		HttpURLConnection jconn = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			URL url = new URL(urlStr);
			jconn = (HttpURLConnection) url.openConnection();
			//oauth1.0 sign
			consumer.sign(jconn);
			jconn.setDoOutput(true);
			jconn.setDoInput(true);
			jconn.connect();
			InputStream in = null;
			int responseCode=jconn.getResponseCode();
			if(200==responseCode){//正确的响应
				 in = jconn.getInputStream();
			}else{//其他的响应
				in=jconn.getErrorStream();
			}
			
			byte[] buf = new byte[4096];
			int bytesRead;
			while ((bytesRead = in.read(buf)) != -1) {
				byteArrayOutputStream.write(buf, 0, bytesRead);
			}
			String strRead = new String(byteArrayOutputStream.toByteArray());//响应结果
			result.put(RESPONSECODE, responseCode);
			result.put(RESPONSESTR, strRead);
			return result;
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
}
