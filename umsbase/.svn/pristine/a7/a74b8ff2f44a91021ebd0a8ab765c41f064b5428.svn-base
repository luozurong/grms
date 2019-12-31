package com.hori.enums;



public enum UserType {
	
	
	XTCJGLY("系统超级管理员","0"),
	TFZG("投放主管","1"),
	TFY("投放员","2"),
	XSJL("销售经理","3"),
	XSZG("销售主管","4"),
	XSY("销售员","5"),
	GGGLY("广告管理员","6");
	

	private UserType(String name, String value) {
		this.name = name;
		this.value = value;
	}


	 // 普通方法
    public static String getName(String index) {
        for (UserType c : UserType.values()) {
            if (c.getValue().equals(index)) {
                return c.name;
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
	
}
