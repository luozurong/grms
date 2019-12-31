package com.hori.grms.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.grms.httpclient.HttpResult;
import com.hori.grms.service.HttpClientApiService;
@Service("httpClientApiService")
public class HttpClientApiServiceImpl implements HttpClientApiService{  
    @Autowired  
    private CloseableHttpClient httpClient;  
    @Autowired  
    private RequestConfig requestConfig;  
  
    /** 
     * 执行get请求,200返回响应内容，其他状态码返回null 
     * 
     * @param url 
     * @return 
     * @throws IOException 
     */  
    public String doGet(String url) throws IOException {  
        //创建httpClient对象  
        CloseableHttpResponse response = null;  
        HttpGet httpGet = new HttpGet(url);  
        //设置请求参数  
        httpGet.setConfig(requestConfig);  
        try {  
            //执行请求  
            response = httpClient.execute(httpGet);  
            //判断返回状态码是否为200  
            if (response.getStatusLine().getStatusCode() == 200) {  
                return EntityUtils.toString(response.getEntity(), "UTF-8");  
            }  
        } finally {  
            if (response != null) {  
                response.close();  
            }  
        }  
        return null;  
    }  
  
    /** 
     * 执行带有参数的get请求 
     * 
     * @param url 
     * @param paramMap 
     * @return 
     * @throws IOException 
     * @throws URISyntaxException 
     */  
    public String doGet(String url, Map<String, String> paramMap) throws IOException, URISyntaxException {  
        URIBuilder builder = new URIBuilder(url);  
        for (String s : paramMap.keySet()) {  
            builder.addParameter(s, paramMap.get(s));  
        }  
        return doGet(builder.build().toString());  
    }  
  
  
    /** 
     * 执行post请求 
     * 
     * @param url 
     * @return 
     * @throws IOException 
     */  
    public HttpResult doPost(String url) throws IOException {  
        return doPostJson(url, null);  
    }  
  
  
    /** 
     * 提交json数据 
     * 
     * @param url 
     * @param json 
     * @return 
     * @throws ClientProtocolException 
     * @throws IOException 
     */  
    public HttpResult doPostJson(String url, String json) throws ClientProtocolException, IOException {  
        // 创建http POST请求  
        HttpPost httpPost = new HttpPost(url);  
        httpPost.setConfig(this.requestConfig);  
  
        if (json != null) {  
            // 构造一个请求实体  
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);  
            // 将请求实体设置到httpPost对象中  
            httpPost.setEntity(stringEntity);  
        }  
        CloseableHttpResponse response = null;  
        try {  
            // 执行请求  
            response = this.httpClient.execute(httpPost);  
            return new HttpResult(response.getStatusLine().getStatusCode(),  
                    EntityUtils.toString(response.getEntity(), "UTF-8"));  
        }catch(Exception e){
        	e.printStackTrace();
        	  return new HttpResult(400,"");
        }
        
        finally {  
            if (response != null) {  
                response.close();  
            }  
        }  
       
    }  
    
    public static void main(String[] args) {
    	HttpClientApiServiceImpl httpClientApiServiceImpl = new HttpClientApiServiceImpl();
    	String url = "http://tt.hori-gz.com:8090/vdcs_hori/servlet/updateLastLoginForMobile";
    	String json ="{\"header\":{\"time_stamp\":\"201806163143609\",\"token\":\"test\"},\"body\":{\"lastLoginOs\":\"1\",\"clientCode\":\"lxj_u\",\"appType\":\"1\",\"lastLoginDeviceToken\":\"25e5b7207a953b77bc45a9b90972360758d6090504b28acee376110986cd8a92\",\"operType\":\"1\",\"account\":\"18603034989\"}}";
    	try {
			httpClientApiServiceImpl.doPostJson(url, json);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
