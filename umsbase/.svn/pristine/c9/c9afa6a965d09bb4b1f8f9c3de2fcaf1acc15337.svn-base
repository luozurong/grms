package com.hori.enums;



public enum UserTypeSuper {
	
	TFY("投放员","1","2"),
	XSZG("销售主管","3","4"),
	XSY("销售员","4","5");

	

	private UserTypeSuper(String name,String superValue, String value) {
		this.name = name;
		this.value = value;
		this.superValue = superValue;
	}

	 // 普通方法
    public static String getSuperValue(String value) {
        for (UserTypeSuper c : UserTypeSuper.values()) {
            if (c.getValue().equals(value)) {
                return c.superValue;
            }
        }
        return null;
    }
    public static String getName(String value) {
    	for (UserTypeSuper c : UserTypeSuper.values()) {
    		if (c.getValue().equals(value)) {
    			return c.name;
    		}
    	}
    	return null;
    }

	
	/**
     * 名称
     */
    private String name;
    
    /**
     * 类型的值
     */
    private String value;
    
    /**
     * 对应的父类类型
     */
    private String superValue;

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



	public String getSuperValue() {
		return superValue;
	}



	public void setSuperValue(String superValue) {
		this.superValue = superValue;
	}
	
}
