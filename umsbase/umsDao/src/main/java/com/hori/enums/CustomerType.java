package com.hori.enums;



public enum CustomerType {
	
	CHIMS_DLS("代理商","1"),
	
	CHIMS_GGS("广告商","2");

	
	

	private CustomerType(String name, String value) {
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
