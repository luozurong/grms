/*
 * ProjectPeople.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.model;

import java.io.Serializable;

/**
 * 项目人员
 * 
 * @author 
 * @version 1.0 2018-08-10
 */
public class ProjectPeople  implements Serializable{
	private static final long serialVersionUID = -3651432806510465448L;
	//主键id
    private String id;
    //项目编号
    private String projectCode;
    //项目角色id
    private String projectRoleId;
    //项目角色名称
    private String projectRoleName;
    //联系人名称
    private String name;
    //联系人电话号码
    private String phone;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProjectCode() {
        return projectCode;
    }
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
    public String getProjectRoleId() {
        return projectRoleId;
    }
    public void setProjectRoleId(String projectRoleId) {
        this.projectRoleId = projectRoleId;
    }
    public String getProjectRoleName() {
        return projectRoleName;
    }
    public void setProjectRoleName(String projectRoleName) {
        this.projectRoleName = projectRoleName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}