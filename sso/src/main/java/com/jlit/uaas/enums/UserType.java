package com.jlit.uaas.enums;

public enum UserType {

	CHIMS_BR("用户", "1"),

	CHIMS_CJGLY("系统超级管理员", "2"),

	CHIMS_PTGLY("普通管理员", "3"),

	CHIMS_YS("医生", "4"),

	CHIMS_PlGLY("分公司管理员", "9"),

	CHIMS_YYGLY("医院管理员", "10"),

	CHIMS_DLS("代理商", "11"),

	CHIMS_SJ("商家", "12"),

	CHIMS_YYS("运营商", "13"), 
	CHIMS_GZYY("工作人员", "15"), 
	CHIMS_WYGLY("物业管理员", "16"),
	CHIMS_ZHJYGLY("支付交易管理员", "17"),
	
	DZCF_DOCTOR("电子处方医师","20"),
	DZCF_MEDICINEMAN("电子处方医师","21"),
	DZCF_YDGLY("药店管理员","19"),
	DZCF_YGSGLY("药品公司管理员","22"),
	DZCF_YJJGLY("药监局管理员","23"),
	DZCF_HZHBGLY("合作伙伴管理员","24"),
	DZCF_YLJGJLY("医疗机构管理员","25"),
	DZCF_MZJSY("门诊结算员","26");

	private UserType(String name, String value) {
		this.name = name;
		this.value = value;
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

}
