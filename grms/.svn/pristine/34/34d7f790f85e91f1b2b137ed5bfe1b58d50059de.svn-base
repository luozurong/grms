package com.hori.grms.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hori.vo.AuthorLoginVo;
/**
 * 获取角色对应的按钮
 * @author Chenrw
 * @date 2018年8月23日
 */
@Controller
@RequestMapping("/initButtonController")
public class InitButtonController extends BaseController{
	@RequestMapping("/getButttonList")
	@ResponseBody
	public String getButttonList(HttpServletRequest request, HttpServletResponse response) {
		//从session获取菜单信息
		List<AuthorLoginVo> resourceButton= (List<AuthorLoginVo>) request.getSession().getAttribute("resourceButton");
		List<AuthorLoginVo> resourceMenu1= (List<AuthorLoginVo>) request.getSession().getAttribute("resourceMenu1");
		List<AuthorLoginVo> resourceMenu2= (List<AuthorLoginVo>) request.getSession().getAttribute("resourceMenu2");
		String userType=(String) request.getSession().getAttribute("userType");
        List<AuthorLoginVo> resourceButtonNow=new ArrayList<AuthorLoginVo>();
		
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if((!"0".equals(userType))&&null!=resourceMenu1){
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
			
			resultMap.put("success", true);
			resultMap.put("button", resourceButtonNow);
		}else{
			resultMap.put("success", true);
			resultMap.put("admin", "admin");
			resultMap.put("button",resourceButtonNow);
		}
		return JSON.toJSONString(resultMap);
	}
}
