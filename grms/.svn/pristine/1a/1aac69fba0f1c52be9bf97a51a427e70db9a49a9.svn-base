/*
 * Attachment.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.model;

import java.io.Serializable;

/**
 * 附件表
 * 
 * @author 
 * @version 1.0 2018-08-10
 */
public class Attachment implements Serializable{
	private static final long serialVersionUID = -3503926966892729994L;
	//id
    private String id;
    //文件路径
    private String fileUrl;
    //文件名称
    private String fileName;
    //1:项目附件 2合同附件 3项目执行清单附件 4结案附件
    private Integer type;
    //关联业务id
    private String correlationId;
    //是否额外附件 1:是 0:不是
    private Integer isExtra;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getCorrelationId() {
        return correlationId;
    }
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
	public Integer getIsExtra() {
		return isExtra;
	}
	public void setIsExtra(Integer isExtra) {
		this.isExtra = isExtra;
	}
}