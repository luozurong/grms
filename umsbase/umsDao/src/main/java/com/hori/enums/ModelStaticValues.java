package com.hori.enums;

/**   
 * ClassName:ModelStaticValues   
 * Function: 模型静态变量    
 *   
 * @author   damon   
 * @version     
 * @since    Ver 1.0   
 * @Date     2013    Apr 9, 2013     11:44:51 PM   
 *   
 * @see     
 */   
public class ModelStaticValues {

	/**
	 * 用户登陆，用户不存在。
	 */
	public static final String USER_NAME_NOT_EXIST = "user_name_not_exist"; 
	
	/**
	 * 用户登陆，密码错误。
	 */
	
	public static final String PASSWORD_ERROR = "password_error"; 
	
	/**
	 * 用户登陆，密码错误。
	 */
	
	public static final String LOGIN_SUCCESS = "login_success"; 
	
	/**
	 * 档案类型:1个人档案录入
	 */
	public static final String GRJKDA_DALX_GRLR = "1";
	
	/**
	 * 档案类型:2患者基本信息档案录入
	 */
	public static final String GRJKDA_DALX_HZXX = "2";
	
	/**
	 * 档案类型:3体检信息导入
	 */
	public static final String GRJKDA_DALX_TJXX = "3";
	
	/**
	 * 档案类型:4历史数据导入
	 */
	public static final String GRJKDA_DALX_HISTORY = "4";
	
	/**
	 * 档案类型:5设备用户信息导入
	 */
	public static final String GRJKDA_DALX_DEVICE_USER = "5";
	
	/**
	 * 老年人年龄
	 */
	public static final int LNR_AGE = 65;
	
	/**
	 * 佛山城市代号
	 */
	public static final String CITY_CODE = "440604";
	
	/**
	 * 其它乡镇代号起始编号
	 */
	public static final String QT_START_CODE = "500";
}
