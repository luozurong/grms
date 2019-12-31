package com.hori.grms.component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hori.grms.vo.FilePathVo;
import com.hori.grms.vo.ImagePathVo;
/**
 * 文件或图片上传fms系统工具
 * @author laizs
 * @time 2017年8月8日下午1:42:37
 *
 */
@Component("fmsUploadUtil")
@Scope("singleton")
public class FmsUploadUtil {
	private final static Logger logger=LoggerFactory.getLogger(FmsUploadUtil.class);
	/**
	 * fms服务器地址,从配置文件获取
	 */
	@Value(value="${fms_server_address}")
	private String fmsServerAddress;
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
    public  Map transfilesToFmsNoRename(File[] files) throws HttpException, IOException {
    	Map result=new JSONObject();
    	result.put("result", "false");
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	try {
    		//文件上传接口
        	String imgUploadUrl= fmsServerAddress+"/filesUploadNoRename";
			HttpPost httpPost = new HttpPost(imgUploadUrl);
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			for (File file : files) {
				multipartEntityBuilder.addPart("multipartFile", new FileBody(file));
			}
			HttpEntity reqEntity = multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8")).build();
			//设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1800000)
					.setConnectTimeout(1800000)
					.setConnectionRequestTimeout(1800000)
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
						//logger.info("上传成功");
						String res=EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
						logger.info("上传到fms系统的响应:"+res);
						JSONObject json=JSONObject.parseObject(res);
						String resultVal=(String) json.get("result");
						if(StringUtils.isNotBlank(resultVal)&&"0".equals(resultVal)){//图片上传到fms成功的响应
							result.put("result", "true");
							JSONArray jsonArray= json.getJSONArray("list");
							List<FilePathVo> filePathVos=JSONArray.parseArray(jsonArray.toJSONString(), FilePathVo.class);
							result.put("list", filePathVos);
						}
					}
				} else {
					logger.warn("文件上传到fms响应失败，响应码："+statusCode);
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
    
	
}
