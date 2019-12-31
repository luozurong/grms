package com.hori.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
/**
 * HttpClient工具类
 * @author laizs
 * @time 2014-4-25 上午10:35:51
 * @file HttpClientUtil.java
 */
public class HttpClientUtil {  
  
    public static String CHARSET_ENCODING = "UTF-8";  
    // private static String  
    // USER_AGENT="Mozilla/4.0 (compatible; MSIE 6.0; Win32)";//ie6  
    public static String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 7.0; Win32)";// ie7  
  
    // private static String  
    // USER_AGENT="Mozilla/4.0 (compatible; MSIE 8.0; Win32)";//ie8  
  
    /** 
     * 获取DefaultHttpClient对象 
     *  
     * @param charset 
     *            字符编码 
     * @return DefaultHttpClient对象 
     */  
    private static DefaultHttpClient getDefaultHttpClient(final String charset) {  
        DefaultHttpClient httpclient = new DefaultHttpClient();  
        // 模拟浏览器，解决一些服务器程序只允许浏览器访问的问题  
        httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,  
                USER_AGENT);  
        httpclient.getParams().setParameter(  
                CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);  
        httpclient.getParams().setParameter(  
                CoreProtocolPNames.HTTP_CONTENT_CHARSET,  
                charset == null ? CHARSET_ENCODING : charset);  
          
        // 浏览器兼容性  
        httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY,  
                CookiePolicy.BROWSER_COMPATIBILITY);  
        // 定义重试策略  
        httpclient.setHttpRequestRetryHandler(requestRetryHandler);  
          
        return httpclient;  
    }  
    /** 
     * 访问https的网站 
     * @param httpclient 
     */  
    private static void enableSSL(DefaultHttpClient httpclient){  
        //调用ssl  
         try {  
                SSLContext sslcontext = SSLContext.getInstance("TLS");  
                sslcontext.init(null, new TrustManager[] { truseAllManager }, null);  
                SSLSocketFactory sf = new SSLSocketFactory(sslcontext);  
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
                Scheme https = new Scheme("https", sf, 443);  
                httpclient.getConnectionManager().getSchemeRegistry()  
                        .register(https);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
    }  
    /** 
     * 重写验证方法，取消检测ssl 
     */  
    private static TrustManager truseAllManager = new X509TrustManager(){  
  
        public void checkClientTrusted(  
                java.security.cert.X509Certificate[] arg0, String arg1)  
                throws CertificateException {  
            // TODO Auto-generated method stub  
              
        }  
  
        public void checkServerTrusted(  
                java.security.cert.X509Certificate[] arg0, String arg1)  
                throws CertificateException {  
            // TODO Auto-generated method stub  
              
        }  
  
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
          
    } ;  
  
    /** 
     * 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复 
     */  
    private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {  
        // 自定义的恢复策略  
        public boolean retryRequest(IOException exception, int executionCount,  
                HttpContext context) {  
            // 设置恢复策略，在发生异常时候将自动重试3次  
            if (executionCount >= 3) {  
                // 如果连接次数超过了最大值则停止重试  
                return false;  
            }  
            if (exception instanceof NoHttpResponseException) {  
                // 如果服务器连接失败重试  
                return true;  
            }  
            if (exception instanceof SSLHandshakeException) {  
                // 不要重试ssl连接异常  
                return false;  
            }  
            HttpRequest request = (HttpRequest) context  
                    .getAttribute(ExecutionContext.HTTP_REQUEST);  
            boolean idempotent = (request instanceof HttpEntityEnclosingRequest);  
            if (!idempotent) {  
                // 重试，如果请求是考虑幂等  
                return true;  
            }  
            return false;  
        }  
    };  
  
    /** 
     * 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理 
     */  
    private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {  
        // 自定义响应处理  
        public String handleResponse(HttpResponse response)  
                throws ClientProtocolException, IOException {  
            HttpEntity entity = response.getEntity();  
            if (entity != null) {  
                String charset = EntityUtils.getContentCharSet(entity) == null ? CHARSET_ENCODING  
                        : EntityUtils.getContentCharSet(entity);  
                return new String(EntityUtils.toByteArray(entity), charset);  
            } else {  
                return null;  
            }  
        }  
    };  
  
    /** 
     * 使用post方法获取相关的数据 
     *  
     * @param url 
     * @param paramsList 
     * @return 
     * @throws IOException 
     * @throws ClientProtocolException 
     */  
    public static String post(String url, List<NameValuePair> paramsList) throws ClientProtocolException, IOException {  
        return httpRequest(url, paramsList, "POST", null);  
    }  
  
    /** 
     * 使用post方法并且通过代理获取相关的数据 
     *  
     * @param url 
     * @param paramsList 
     * @param proxy 
     * @return 
     * @throws IOException 
     * @throws ClientProtocolException 
     */  
    public static String post(String url, List<NameValuePair> paramsList,  
            HttpHost proxy) throws ClientProtocolException, IOException {  
        return httpRequest(url, paramsList, "POST", proxy);  
    }  
  
    /** 
     * 使用get方法获取相关的数据 
     *  
     * @param url 
     * @param paramsList 
     * @return 
     * @throws IOException 
     * @throws ClientProtocolException 
     */  
    public static String get(String url, List<NameValuePair> paramsList) throws ClientProtocolException, IOException {  
        return httpRequest(url, paramsList, "GET", null);  
    }  
  
    /** 
     * 使用get方法并且通过代理获取相关的数据 
     *  
     * @param url 
     * @param paramsList 
     * @param proxy 
     * @return 
     * @throws IOException 
     * @throws ClientProtocolException 
     */  
    public static String get(String url, List<NameValuePair> paramsList,  
            HttpHost proxy) throws ClientProtocolException, IOException {  
        return httpRequest(url, paramsList, "GET", proxy);  
    }  
  
    /** 
     * 提交数据到服务器 
     *  
     * @param url 
     * @param params 
     * @param authenticated 
     * @throws IOException 
     * @throws ClientProtocolException 
     */  
    public static String httpRequest(String url,  
            List<NameValuePair> paramsList, String method, HttpHost proxy) throws ClientProtocolException, IOException {  
        String responseStr = null;  
        // 判断输入的值是是否为空  
        if (null == url || "".equals(url)) {  
            return null;  
        }  
          
        // 创建HttpClient实例  
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);  
          
        //判断是否是https请求  
        if(url.startsWith("https")){  
            enableSSL(httpclient);  
        }  
        String formatParams = null;  
        // 将参数进行utf-8编码  
        if (null != paramsList && paramsList.size() > 0) {  
            formatParams = URLEncodedUtils.format(paramsList, CHARSET_ENCODING);  
        }  
        // 如果代理对象不为空则设置代理  
        if (null != proxy) {  
            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,  
                    proxy);  
        }  
        // 如果方法为Get  
        if ("GET".equalsIgnoreCase(method)) {  
            if (formatParams != null) {  
                url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams)  
                        : (url.substring(0, url.indexOf("?") + 1) + formatParams);  
                }  
                HttpGet hg = new HttpGet(url);  
                responseStr = httpclient.execute(hg, responseHandler);  
  
                // 如果方法为Post  
        } else if ("POST".equalsIgnoreCase(method)) {  
            HttpPost hp = new HttpPost(url);  
            if (formatParams != null) {  
                StringEntity entity = new StringEntity(formatParams);  
                entity.setContentType("application/x-www-form-urlencoded");  
                hp.setEntity(entity);  
            }  
            responseStr = httpclient.execute(hp, responseHandler);  
              
        }  
  
        
        return responseStr;  
    }  
    
    /**
     * 将图片上传到图片服务器 ，返回图片路径
     * 注意上传的File类型，必须是图片 ，接文件后缀必须是图片类型后缀，否则fps生成缩略图时会出错
     * 
     * @param imgs 上传图片file数组，可上传多张
     * @param serverUrl fms 服务器图片上传接口地址
     * @return 返回图片上传的路径
     * @throws HttpException
     * @throws IOException
     */
    public static String transImgToFms(File[] imgs,String serverUrl) throws HttpException, IOException {
    	String result="error";
    	List<Part> list=new ArrayList<Part>();
    	for(File img:imgs){
    		list.add(new FilePart(img.getName(),img));
    	}
    	Part[] parts=new Part[list.size()];
    	list.toArray(parts);
		PostMethod filePost = new PostMethod(serverUrl);
		filePost.setRequestEntity(new MultipartRequestEntity(parts,filePost.getParams()));
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		int status = client.executeMethod(filePost);
		if (status == HttpStatus.SC_OK) {
			//logger.info("上传成功");
			result=filePost.getResponseBodyAsString();
		} else {
			result="server response error";
		}
		return result;
    }
  
    /**
     * 将文件上传到文件服务器 ，返回文件路径
     * 注意上传的File类型，必须是小于20M的文件 
     * 
     * @param files 上传文件file数组，可上传多个文件
     * @param serverUrl fms 服务器文件上传接口地址
     * @return 返回文件上传的路径
     * @throws HttpException
     * @throws IOException
     */
    public static String transFileToFms(File[] files,String serverUrl) throws HttpException, IOException {
    	String result="error";
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	try {
			HttpPost httpPost = new HttpPost(serverUrl);
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			for (File file : files) {
				multipartEntityBuilder.addPart("multipartFile", new FileBody(file));
			}
			HttpEntity reqEntity = multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8")).build();
			//设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(120000)
					.setConnectTimeout(120000)
					.setConnectionRequestTimeout(120000)
					.build();
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(reqEntity);
			// 发起请求 并返回请求的响应
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				// 获取响应对象
				HttpEntity resEntity = response.getEntity();
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == 200) {
					if (resEntity!=null) {
						result = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
					}
				} else {
					result = "server response error";
				}
				// 销毁
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			} 
		} finally {
			httpClient.close();
		}
		return result;
    }
    /**
	 * 通过Http与服务器建立连接，获取上传到服务器的软件信息
	 * @param url
	 * @return
	 */
	public static Map<String, String>  saveFileInfoFromUrl(String url){
		try {
		Map<String, String> resultMap =new HashMap<String, String>();
		 MessageDigest md = MessageDigest.getInstance("MD5");
		URL fileUrl;
		HttpURLConnection conn = null;
		InputStream is = null;
			fileUrl = new URL(url);
			conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);
            conn.connect();
         // 获取文件大小
            int picSize = conn.getContentLength();
            resultMap.put("softSize", String.valueOf(picSize));
            is = conn.getInputStream();
                String fileName  = url.substring(url.lastIndexOf("/")+1);
                resultMap.put("fileName", fileName);
                final int buffer_size = 2048;
                byte[] bytes = new byte[buffer_size];
                for (;;) {
                    int count = is.read(bytes, 0, buffer_size);
                    if (count == -1)
                        break;
                    md.update(bytes, 0, count);
                }
                byte[] b = md.digest();
                resultMap.put("md5",byteToHexString(b));
                is.close();
                return resultMap;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	  /**
     * 把byte[]数组转换成十六进制字符串表示形式
     * @param tmp    要转换的byte[]
     * @return 十六进制字符串表示形式
     */
    public static String byteToHexString(byte[] tmp) {
       String s;
       // 用字节表示就是 16 个字节
       char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
       // 所以表示成 16 进制需要 32 个字符
       int k = 0; // 表示转换结果中对应的字符位置
       for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
         // 转换成 16 进制字符的转换
         byte byte0 = tmp[i]; // 取第 i 个字节
         str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, 
         // >>> 为逻辑右移，将符号位一起右移
         str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
       }
       s = new String(str); // 换后的结果转换为字符串
       return s;
    }
	
	static char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8','9', 'a', 'b', 'c', 'd', 'e', 'f' };

	
	
	
	
    public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {  
  
        String url="http://www.baidu.com/";  
        String str=HttpClientUtil.get(url, null);  
        System.out.println(str);  
  
    }  
  
}  
