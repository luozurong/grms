package com.hori.adms.util;

import java.io.IOException;
import java.util.Properties;
/**
 * global全局文件属性
 * @author laizs
 *
 */
public class GlobalPropertiesValue {

	/**
	 * 资源文件名
	 */
	private static final String GLOBAL_PROPERTIES = "config.properties";

	/**
	 * 持久属性集
	 */
	private static Properties properties;
	/**
	 * sso登录页面路径
	 */
	public static String ssoLoginUri = "";
	/**
	 * #sso登录成功后回调uri
	 */
	public static String callbackUri = "";
	static {
		try {
			properties = new Properties();
			properties.load(GlobalPropertiesValue.class.getClassLoader().getResourceAsStream(GLOBAL_PROPERTIES));
			ssoLoginUri = properties.getProperty("sso_login_uri");
			callbackUri = properties.getProperty("callback_uri");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("获取全局变量失败;");
		}

	}

}
