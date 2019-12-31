package com.hori.grms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hori.grms.model.CustomerType;
import com.hori.grms.model.ProjectRole;
import com.hori.grms.service.CustomerTypeService;
import com.hori.grms.service.ProjectRoleService;
import com.hori.grms.vo.ContractDetailVo;
/**
 * 其他设置
 * @author dell
 *
 */
@Controller
@RequestMapping("otherSets")
public class OtherSetsController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CustomerTypeService customerTypeService;
	
	@Autowired
	ProjectRoleService projectRoleService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request){		
		
		return "/systemSettings/otherSets.jsp";
	}
	
	@RequestMapping(value="/getCustomerType")
	@ResponseBody
	public List<CustomerType> getCustomerType(HttpServletRequest request){		
		List<CustomerType> list = customerTypeService.getCustomerType();
		return list;
	}
	
	@RequestMapping(value="/getProjectRole")
	@ResponseBody
	public List<ProjectRole> getProjectRole(HttpServletRequest request){		
		List<ProjectRole> list = projectRoleService.getProjectRole();
		return list;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,String customerType,String customerTypeName,String projectRole,String projectRoleName){	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			
			//保存客户类型
			String[] cId = customerType.split(",");
			String[] cName = customerTypeName.split(",");
			for(int i = 0;i < cId.length;i++){
				if(!cName[i].equals("")&&cName[i]!=null){
						CustomerType type = customerTypeService.getCustomerTypeById(cId[i]);
						if(type!=null){//原来有    则更新
							type.setName(cName[i]);
							customerTypeService.update(type);
						}else{//原来没有则添加
							CustomerType customerTypeNew = new CustomerType();
							customerTypeNew.setId(cId[i]);
							customerTypeNew.setName(cName[i]);
							customerTypeNew.setIsDel("0");
							customerTypeService.insert(customerTypeNew);
						}
				}
			}
			
			//保存项目角色
			String[] pId = projectRole.split(",");
			String[] pName = projectRoleName.split(",");
			for(int i = 0;i < pId.length;i++){
				ProjectRole role = projectRoleService.getProjectRoleById(pId[i]);
				if(role!=null){
					role.setName(pName[i]);
					projectRoleService.update(role);
				}else{
					if(!pName[i].equals("")&&pName[i]!=null){
						ProjectRole projectRoleNew = new ProjectRole();
						projectRoleNew.setId(pId[i]);
						projectRoleNew.setName(pName[i]);
						projectRoleNew.setIsDel("0");
						projectRoleService.insert(projectRoleNew);
					}
				}
			}
			resultMap.put("success", true);
		}catch(Exception e){
			logger.info("发送异常"+e.getMessage());
			resultMap.put("success", false);
		}
		return JSON.toJSONString(resultMap);
		
	}
	//逻辑删除
	@RequestMapping("/delete")
	@ResponseBody
	public String getContractList(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try{
			String id = request.getParameter("id");
			CustomerType type = customerTypeService.getCustomerTypeById(id);
			ProjectRole role = projectRoleService.getProjectRoleById(id);
			if(type!=null){
				type.setIsDel("1");
				customerTypeService.update(type);
			}else if(role!=null){
				role.setIsDel("1");
				projectRoleService.update(role);
			}
			
			
			resultMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
		}
		return JSON.toJSONString(resultMap);
	}
	
}
