package com.hori.utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.Properties;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @FileName：PropertyContext.java
 * @Copyright: Copyright (c)　景联科技, 2012-2013
 * @Description：PropertyContext 读取资源文件实现类
 * @author [2012-02-04 13:47:00] by lzs
 * @version 1.0
 */
public class PropertyContext {
	
	private Properties propertie;
	private String filePath;
	private String coding = "UTF-8";
	private static PropertyContext context = null;
	
	
	public static PropertyContext PropertyContextFactory(String pathFile){
		if (StringUtils.isNotBlank(ObjectUtils.toString(context)))
			return context;
		return new PropertyContext(pathFile);
	}
	
	private PropertyContext() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 初始化 propertie 对像
	 * @param pathFile
	 */
	private PropertyContext(String pathFile) {
		// TODO Auto-generated constructor stub
		Properties prope;
		InputStream inputStream = null;
		try{
			URLConnection urlConnection = this.getFileCon(pathFile);
			if (StringUtils.isBlank(ObjectUtils.toString(urlConnection))){
				System.out.println("ERROR File:"+pathFile+" not find!");
				return;
			}else{
				inputStream = urlConnection.getInputStream();
			}
			if (StringUtils.isNotBlank(ObjectUtils.toString(inputStream))){
				prope = new Properties();
			}else{
				System.out.println("ERROR File:"+pathFile+" not find!");
				return;
			}
			prope.load(inputStream);
			this.propertie = prope;
			this.filePath = pathFile;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if (StringUtils.isNotBlank(ObjectUtils.toString(inputStream))){
					inputStream.close();
					inputStream = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 初始化 propertie 对像
	 * @param pathFile
	 */
	public PropertyContext(String pathFile, String coding) {
		// TODO Auto-generated constructor stub
		Properties prope;
		InputStream inputStream = null;
		try{
			URLConnection urlConnection = this.getFileCon(pathFile);
			if (StringUtils.isBlank(ObjectUtils.toString(urlConnection))){
				System.out.println("ERROR File:"+pathFile+" not find!");
				return;
			}else{
				inputStream = urlConnection.getInputStream();
			}
			if (StringUtils.isNotBlank(ObjectUtils.toString(inputStream))){
				prope = new Properties();
			}else{
				System.out.println("ERROR File:"+pathFile+" not find!");
				return;
			}
			prope.load(inputStream);
			this.propertie = prope;
			this.filePath = pathFile;
			this.coding = coding;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if (StringUtils.isNotBlank(ObjectUtils.toString(inputStream))){
					inputStream.close();
					inputStream = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 得到URLConnection 对像
	 * @param filename 
	 * @return URLConnection
	 */
	private URLConnection getFileCon(String filename) {
		try {
			Resource resource = new ClassPathResource(filename);
			
			if (resource.exists()) {
				return resource.getURL().openConnection();
			} else {
				System.out.println("ERROR File:" + filename + " not find!");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getValue(String key) {
		try {
			String value = this.propertie.getProperty(key);
			if (StringUtils.isNotBlank(value)){
				byte[] bytes = value.getBytes("iso8859-1");
				String str = new String(bytes, this.coding);
				return str;
			}
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	public String get(String key) {
		try {
			return this.propertie.getProperty(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
}
