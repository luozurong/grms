package com.hori.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hori.dao.queryBean.AreaQueryBean;
import com.hori.dao.queryBean.MenuQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.Menu;
import com.hori.model.Operation;
import com.hori.pageModel.Json;
import com.hori.service.AreaManagementService;
import com.hori.service.MenuButtonService;
import com.hori.vo.EasyUiTreeVo;

/**
 * 功能按钮管理
 * 
 * @author 
 * 
 */

@Action(value = "menuButtonAction", 
  results = { @Result(name = "menuButton", location = "/peopleManagement/functionButtonManagement.jsp"),
		      @Result(name ="button", location = "/peopleManagement/buttonManagement.jsp")
      })
public class MenuButtonAction extends BaseAction {
	@Autowired
	MenuButtonService menuButtonService;
	
	/**
	 * 调转到功能按钮管理
	 * @return
	 */
	public String goMenuButtonList(){
	
		return "menuButton";
	}
	
	
	/**
	 * 调转到功能按钮管理
	 * @return
	 */
	public String goButtonList(){
		String menuIdl=getRequest().getParameter("menuId");
		String newStr=menuIdl.replace("‘", "");
		String menuAppl=getRequest().getParameter("menuApp");
		String newStr1=menuAppl.replace("‘", "");
		getRequest().getSession().setAttribute("menuId", newStr);
		getRequest().getSession().setAttribute("menuApp", newStr1);
		return "button";
	}
	/**
	 * 根据参数分页查询
	 * @return
	 */
	public void getMenuButtonByParam(){
		String menuQueryBean=getRequest().getParameter("menuQueryBean");
		String newStr = menuQueryBean.replaceAll("“","\"");
		MenuQueryBean data=JSON.parseObject(newStr, MenuQueryBean.class);  
	    DataGridPage dataGridPage=menuButtonService.findMenuByParam(data);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		j.setObj(dataGridPage);
		writeJson(j);

	}
	/**
	 * 初始化下拉框变量
	 * @return
	 */
	public void initSystem(){
		List<EasyUiTreeVo>  systemList=menuButtonService.initSystem();
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		j.setObj(systemList);
		writeJson(j);
		
	}
	/**
	 * 初始化下拉框变量
	 * @return
	 */
	public void initMenu(){
		String systemId=getRequest().getParameter("systemId");
		String menuApp=getRequest().getParameter("menuApp");
		List<EasyUiTreeVo>  systemList=menuButtonService.initMenu(systemId,menuApp);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		j.setObj(systemList);
		writeJson(j);
		
	}
	
	/**
	 * 保存或修改菜单信息
	 * @return
	 */
	public void saveMenu(){
		String menuVo=getRequest().getParameter("menuVo");
		String newStr = menuVo.replaceAll("“","\"");
		Menu menu=JSON.parseObject(newStr, Menu.class);  
	    menuButtonService.saveMenu(menu);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		writeJson(j);

	}
	
	/**
	 * 删除菜单信息
	 * @return
	 */
	public void deleteMenu(){
		String ids=getRequest().getParameter("ids"); 
		String[] idsArray= ids.split("'");
		menuButtonService.deleteMenu(idsArray);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		writeJson(j);	
	}
	
	/**
	 * 初始化按钮信息
	 * @return
	 */
	public void initButton(){
		String menuId=(String) getRequest().getSession().getAttribute("menuId");
		List<Operation>  operationList=menuButtonService.initButton(menuId);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		j.setObj(operationList);
		writeJson(j);
		
	}
	
	/**
	 * 保存或修改按钮信息
	 * @return
	 */
	public void saveButton(){
		String buttonVo=getRequest().getParameter("buttonVo");
		String newStr = buttonVo.replaceAll("“","\"");
		Operation operation=JSON.parseObject(newStr, Operation.class); 
		String menuId=(String) getRequest().getSession().getAttribute("menuId");
		String menuApp=(String) getRequest().getSession().getAttribute("menuApp");
		operation.setMenuId(menuId);
		operation.setBtnApp(Byte.valueOf(menuApp));
	    menuButtonService.saveButton(operation);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		writeJson(j);

	}
	
	/**
	 * 删除按钮信息
	 * @return
	 */
	public void deleteButton(){
		String id=getRequest().getParameter("id"); 
		menuButtonService.deleteButton(id);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		writeJson(j);	
	}
	

}
