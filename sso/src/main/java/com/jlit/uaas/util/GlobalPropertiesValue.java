package com.jlit.uaas.util;

import java.io.IOException;
import java.util.Properties;
/**
 * global全局文件属性
 *
 */
public class GlobalPropertiesValue {

	/**
	 * 资源文件名
	 */
	private static final String GLOBAL_PROPERTIES = "com/jlit/uaas/resources/global.properties";

	/**
	 * 持久属性集
	 */
	private static Properties properties;
	/**
	 * 访问ums路径
	 */
	public static String umsServletUrl = "";
	static {
		try {
			properties = new Properties();
			properties.load(GlobalPropertiesValue.class.getClassLoader().getResourceAsStream(GLOBAL_PROPERTIES));
			umsServletUrl = properties.getProperty("ums_servlet_url");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("获取全局变量失败;");
		}

	}

}
