package com.jlit.uaas.enums;
/**
 * 运营商子类型
 * @author laizs
 * @time 2015-11-12上午9:33:37
 * @file PlatformManageType.java
 *
 */
public enum PlatformManageType {

	PTGLY("平台管理员", "2"),
	YWGLY("业务管理员", "3"),
	QDGLY("渠道管理员","4"),
	ZYYS("总运营商","5"),
	QYYYS("区域运营商","6"),
	SONGYYS("子运营商","7");

	private PlatformManageType(String name, String value) {
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
	public static void main(String[] args) {
	}

}
