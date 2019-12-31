/**
 * 
 */
package com.hori.grms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.filter.AutoLoad;
import com.alibaba.fastjson.JSON;
import com.hori.grms.model.Project;
import com.hori.grms.model.ProjectRole;
import com.hori.grms.service.ProjectRoleService;

/** 
 * @ClassName: ProjectRoleController 
 * @Description: 项目角色 
 * @author zhuqiang
 * @date 2018年8月10日 下午2:39:08 
 */
@Controller
@RequestMapping("/projectRole")
public class ProjectRoleController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProjectRoleService projectRoleService;
	
	@RequestMapping(value="/getProjectRoleList",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String getProjectRoleList(){
		List<Map<String, Object>> projectRoles=projectRoleService.findProjectRoleList();
		/*Map<String, Object> map=new HashMap<>();
		map.put("id", -1);
		map.put("name", "请选择");
		map.put("selected",true);
		
		projectRoles.add(map);*/
		return JSON.toJSONString(projectRoles);
	}
}
