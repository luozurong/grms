package com.hori.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.hori.pageModel.Json;
import com.hori.vo.AuthorLoginVo;
import com.hori.vo.EasyUiTreeVo;

/**
 * 初始化按钮管理
 * 
 * @author hhb
 * 
 */
@Action(value = "initButtonAction")
public class InitButtonAction extends BaseAction{
	
	/**
	 * menuUrl在session找到对应的按钮
	 * @return
	 */
	public void initMenuButton(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		//从session获取菜单信息
		List<AuthorLoginVo> resourceButton= (List<AuthorLoginVo>) request.getSession().getAttribute("resourceButton");
		List<AuthorLoginVo> resourceMenu1= (List<AuthorLoginVo>) request.getSession().getAttribute("resourceMenu1");
		List<AuthorLoginVo> resourceMenu2= (List<AuthorLoginVo>) request.getSession().getAttribute("resourceMenu2");
		String userType=(String) request.getSession().getAttribute("userType");
        List<AuthorLoginVo> resourceButtonNow=new ArrayList<AuthorLoginVo>();
		if(!userType.equals("0")){
			String menuUrl=getRequest().getParameter("menuUrl");
			String menuId="";
			//首先遍历一级菜单
			for(int i=0;i<resourceMenu1.size();i++){
				if(StringUtils.isNotBlank(resourceMenu1.get(i).getResourceUrl())){
					if(resourceMenu1.get(i).getResourceUrl().contains(menuUrl)){
						menuId=resourceMenu1.get(i).getResourceId();
						break;
					}
				}
			}
			//如果找不到继续遍历2级菜单
			if(!StringUtils.isNotBlank(menuId)){
				for(int i=0;i<resourceMenu2.size();i++){
					if(StringUtils.isNotBlank(resourceMenu2.get(i).getResourceUrl())){
						if(resourceMenu2.get(i).getResourceUrl().contains(menuUrl)){
							menuId=resourceMenu2.get(i).getResourceId();
							break;
						}
					}
				}
			}
        
        //找到该菜单需要添加的按钮
			if(StringUtils.isNotBlank(menuId)){
				for(int i=0;i<resourceButton.size();i++){
					if(menuId.equals(resourceButton.get(i).getResourceParentId())){
						resourceButtonNow.add(resourceButton.get(i));
					}
				}
			}
			Json j = new Json();
			j.setSuccess(true);
			j.setMsg("sucess");
			j.setObj(resourceButtonNow);
			writeJson(j);
		}else{
			Json j = new Json();
			j.setSuccess(true);
			j.setMsg("admin");
			j.setObj(userType);
			writeJson(j);
		}
		
		
	}

}
