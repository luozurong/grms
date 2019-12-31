package com.hori.grms.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 主页面框架controller
 *
 */
@Controller
public class MainController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 主页面入口
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/admin/main")
	public String main2(
			HttpServletRequest request){
        
		return "/index.jsp";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/")
	public String main(
			HttpServletRequest request){
        
		return "/index.jsp";
	}
	
	@RequestMapping("/nologin")
	public String nologin(
			HttpServletRequest request){
        
		return "/nologin.jsp";
	}
	
	
}
