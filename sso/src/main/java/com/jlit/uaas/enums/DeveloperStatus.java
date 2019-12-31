package com.jlit.uaas.enums;

public enum DeveloperStatus {
	
	STATUS_NOCHECK("审核中",0),
	
	STATUS_CHECKSUCCESS("审核通过",1),
	
	STATUS_CHECKFAIL("审核不通过",2);

	private DeveloperStatus(String name, int value) {
		this.name = name;
		this.value = value;
	}
	/**
     * 状态名称
     */
    private String name;
    
    

    public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}



	/**
     * 状态值
     */
    private int value;
	
}
