/*
 * ProductMenu.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-17 Created
 */
package com.hori.grms.model;

/**
 * 产品清单
 * 
 * @author 
 * @version 1.0 2018-08-17
 */
public class ProductMenu {

    private String id;
    //业务类型：1社区运营 2 媒管 3用户运营 4电商运营
    private String businessType;
    //产品类型（文字，地推....）
    private String productType;
    //产品清单类型
    private String productMenu;
    //产品规格
    private String productSpec;
    //限制次数，0不限制  
    private Integer numLimit;
    //1按总数限制 2按月限制
    private Short numLimitType;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBusinessType() {
        return businessType;
    }
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public String getProductMenu() {
        return productMenu;
    }
    public void setProductMenu(String productMenu) {
        this.productMenu = productMenu;
    }
    public String getProductSpec() {
        return productSpec;
    }
    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }
    public Integer getNumLimit() {
        return numLimit;
    }
    public void setNumLimit(Integer numLimit) {
        this.numLimit = numLimit;
    }
    public Short getNumLimitType() {
        return numLimitType;
    }
    public void setNumLimitType(Short numLimitType) {
        this.numLimitType = numLimitType;
    }
}