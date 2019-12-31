package com.hori.grms.model;

public class DepartmentBusinessType {
    private String id;

    private String departmentId;

    private String departmentName;

    private String businessType;

    private Integer areaFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getAreaFlag() {
        return areaFlag;
    }

    public void setAreaFlag(Integer areaFlag) {
        this.areaFlag = areaFlag;
    }
}