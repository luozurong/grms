package com.hori.action;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Value;
/**
 * 后台系统主框架action
 * @author laizs
 *
 */
@Action(value = "MainAction", results = { 
		@Result(name = "main", location = "/WEB-INF/pages/main.jsp")})
public class MainAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(MainAction.class);
	/**
	 * 主页面框架
	 * @return
	 * @throws IOException 
	 */
	@Action(value="main",results={@Result(name = "main", location = "/WEB-INF/pages/main.jsp")})
	public String main() throws IOException{
		
		return "main";//主页面jsp
	}
    
	
}
