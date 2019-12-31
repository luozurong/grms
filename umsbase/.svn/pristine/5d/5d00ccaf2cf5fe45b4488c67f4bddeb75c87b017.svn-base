package com.hori.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class StaticValue {

	public static String LOGINPASSERRERTIMES = "|LoginPassErrer|msgErrer";
	/**
	 * 资源文件名
	 */
	private static final String GLOBAL_PROPERTIES = "global.properties";
	
	/**
	 * 持久属性集
	 */
	private static Properties properties;
	
	
	public static String MSGERRENUM;
	
	public static String CODEERRENUM;
	
	public static String FMS_SERVER_URL;
	
	public static String TERMINAL_PASSWORD;
	
		
	static {
		try {
			properties = new Properties();
			
			properties.load(StaticValue.class.getClassLoader().getResourceAsStream(GLOBAL_PROPERTIES));
			
			MSGERRENUM = properties.getProperty("MSGERRENUM");
			
			CODEERRENUM = properties.getProperty("CODEERRENUM");
			
			FMS_SERVER_URL = properties.getProperty("FMS_SERVER_URL");
			
			TERMINAL_PASSWORD = properties.getProperty("TERMINAL_PASSWORD");
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("获取全局变量失败;");
		}

	}
	public static Map<String,String> getModuleMap(){
		Map<String,String> moduleMap=new HashMap();
		moduleMap.put("com.hori.action.UserAction", "人员管理-帐号管理");
		moduleMap.put("com.hori.action.RoleAction", "人员管理-角色管理");
/*		moduleMap.put("com.hori.action.MenuButtonAction", "人员管理-功能按钮管理");
*/		moduleMap.put("com.hori.action.AreaManagementAction", "人员管理-责任区域管理");
		moduleMap.put("com.hori.action.OrganizationAction", "人员管理-组织架构管理");
		moduleMap.put("com.hori.action.UserDetailAction", "人员管理-员工信息管理");
		return moduleMap;
	}
	
	public static Map<String,String> getMethodMap(){
		Map<String,String> methodMap = new HashMap();
		methodMap.put("deleteAccountRole", "删除");
		methodMap.put("saveOrUpdateAccount", "新增或者修改");
		methodMap.put("addSystemMenu", "新增或者修改");
		methodMap.put("deleteRoleById", "删除");
		methodMap.put("addUserArea", "新增或者修改");
		methodMap.put("addSystemOrganization", "新增");
		methodMap.put("delSystemOrganization", "删除");
		methodMap.put("editSystemOrganization", "修改");
		methodMap.put("saveUserDetail", "新增或者修改");
		methodMap.put("deleteUserDetail", "删除");

		return methodMap;
	}

}