package com.jlit.uaas.enums;

public enum ServiceProductType {
	
	//1、折扣发布商：具折扣券模块权限，可发布该商家的折扣券信息；
	
	SERVICE_PRODUCT_TYPE_DISCOUNT("折扣发布商",new Integer(1)),
	
	//2、家政服务商：具有家政服务模块权限，可管理该商家提供的服务人员信息和处理预约记录；
	
	SERVICE_PRODUCT_TYPE_HOUSEKEEPING("家政服务商",new Integer(2)),
	
	//3、房屋交易商：具有二手房和房屋租赁模块权限，可管理该商家提供的房屋信息和预约记录；
	
	SERVICE_PRODUCT_TYPE_SECONDHANDHOUSE("房屋交易商",new Integer(3)),
	
	//4、零号店商家：可以在零号店开设店铺；
	
	SERVICE_PRODUCT_TYPE_ZERO_SHOP("零号店商家",new Integer(4));
	

	private ServiceProductType(String name, Integer value) {
		this.name = name;
		this.value = value;
	}



	/**
     * 应用名称
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



	public void setValue(Integer value) {
		this.value = value;
	}



	/**
     * 应用类型的值
     */
    private Integer value;
	
}
