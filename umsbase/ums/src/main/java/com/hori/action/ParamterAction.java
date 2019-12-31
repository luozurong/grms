package com.hori.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.hori.pageModel.Json;
import com.hori.service.ParamterService;

/**
 * 用户ACTION
 * 
 * @author zhangdaihao
 * 
 */
@Action(value = "paramterAction")
public class ParamterAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(ParamterAction.class);

	@Autowired
	private ParamterService paramterService = null;
	
	public void getTypeList(){
		String type = this.getRequest().getParameter("type");
		super.writeJson(paramterService.findByType(type));
	}
	
}










