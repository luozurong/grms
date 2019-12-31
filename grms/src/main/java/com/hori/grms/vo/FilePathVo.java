package com.hori.grms.vo;

import java.io.Serializable;

/**
 * 上传的文件的访问路径
 * @author laizs
 * @time 2014-5-26 下午2:20:09
 * @file ImagePathVo.java
 */
public class FilePathVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 原图访问路径
	private String path = "";
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
