package com.hori.enums;

public enum RoleUNTowLevelMenu {

	ADMIN_SYS_RESETPASS("系统管理员","重置密码", "0", "1008003"),

	ADS_SYS_ROLEAUTH("广告管理员","角色与权限", "6", "1008001"),
	ADS_SYS_USERMANAGE("广告管理员","账号管理", "6", "1008002"),
	ADS_SYS_PREVIEWTERMINAL("广告管理员","预览终端", "6", "1008004"),

	DELIVERYDIRECTOR_SYS_ROLEAUTH("投放主管","角色与权限", "1", "1008001"), 
	DELIVERYDIRECTOR_SYS_USERMANAGE("投放主管","账号管理", "1", "1008002"),
	DELIVERYDIRECTOR_SYS_PREVIEWTERMINAL("投放主管","预览终端", "1", "1008004"),

	DELIVERYCLERK_SYS_ROLEAUTH("投放员","角色与权限","2","1008001"),
	DELIVERYCLERK_SYS_USERMANAGE("投放员","账号管理","2","1008002"),
	DELIVERYCLERK_SYS_PREVIEWTERMINAL("投放员","预览终端","2","1008004"),
	
	SALESMANAGER_SYS_ROLEAUTH("销售经理","角色与权限","3","1008001"),
	SALESMANAGER_SYS_USERMANAGE("销售经理","账号管理","3","1008002"),
	SALESMANAGER_SYS_PREVIEWTERMINAL("销售经理","预览终端","3","1008004"),
	
	SALESDIRECTOR_SYS_ROLEAUTH("销售主管","角色与权限","4","1008001"),
	SALESDIRECTOR_SYS_USERMANAGE("销售主管","账号管理","4","1008002"),
	SALESDIRECTOR_SYS_PREVIEWTERMINAL("销售主管","预览终端","4","1008004"),
	
	SALESCLERK_SYS_ROLEAUTH("销售员","角色与权限","5","1008001"),
	SALESCLERK_SYS_USERMANAGE("销售员","账号管理","5","1008002"),
	SALESCLERK_SYS_PREVIEWTERMINAL("销售员","预览终端","5","1008004");
	
	

	private RoleUNTowLevelMenu(String name,String menuName,String userType, String value) {
		this.name = name;
		this.menuName = menuName;
		this.userType = userType;
		this.value = value;
	}

	public static boolean isExistMenu(String userType, String index) {
		for (RoleUNTowLevelMenu c : RoleUNTowLevelMenu.values()) {
			if (c.getValue().equals(index) && c.getUserType().equals(userType)) {
				return true;
			}
		}
		return false;
	}

	// 普通方法
	public static String getName(String index) {
		for (RoleUNTowLevelMenu c : RoleUNTowLevelMenu.values()) {
			if (c.getValue().equals(index)) {
				return c.name;
			}
		}
		return null;
	}

	// 普通方法
	public static String getUserType(String index) {
		for (RoleUNTowLevelMenu c : RoleUNTowLevelMenu.values()) {
			if (c.getValue().equals(index)) {
				return c.userType;
			}
		}
		return null;
	}

	/**
	 * 名称
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 类型的值
	 */
	private String value;

	/**
	 * 用户类型
	 */
	private String userType;

	/**
	 * 菜单名称
	 */
	private String menuName;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
