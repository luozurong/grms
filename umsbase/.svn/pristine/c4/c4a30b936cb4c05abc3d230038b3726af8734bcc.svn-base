package com.hori.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.OrganizationDao;
import com.hori.dao.UserDetailDao;
import com.hori.model.Department;
import com.hori.model.Role;
import com.hori.service.OrganizationService;
import com.hori.vo.EasyUiTreeVo;

@Service("organizationService")
public class OrganizationServiceImpl extends BaseServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private UserDetailDao userDetailDao;
	@Override
	//获取全部的组织架构
	public List<EasyUiTreeVo> dealDepartmentBySystemId(String systemId) {
		
		List<Department> departmentList=organizationDao.getDepartmentAll(systemId);
		List<Map<String,Object>>  map=userDetailDao.findUserDetailCount(systemId);
	    List<EasyUiTreeVo> easyUiTreeVoList=transEasyUiVo2(departmentList,map);
	    List<EasyUiTreeVo> menuList = new ArrayList<EasyUiTreeVo>();
		  // 查看结果
	    for (EasyUiTreeVo menu : easyUiTreeVoList) {
	        System.out.println(menu);
	    }
	    // 最后的结果
	    // 先找到所有的一级菜单
	    for (int i = 0; i < easyUiTreeVoList.size(); i++) {
	        // 一级菜单没有parentId
	        if (StringUtils.isBlank(easyUiTreeVoList.get(i).getParentId())) {
	            menuList.add(easyUiTreeVoList.get(i));
	        }
	    }
	    // 为一级菜单设置子菜单，getChild是递归调用的
	    for (EasyUiTreeVo menu : menuList) {
	        menu.setChildren(getChild(menu.getId(), easyUiTreeVoList));
	    }
	   /* Map<String,Object> jsonMap = new HashMap<>();
	    jsonMap.put("menu", menuList);*/
	   
		return menuList;
	}
	//获取该部门的下级菜单
	public List<EasyUiTreeVo> dealDepartmentBySystemIdNext(String systemId,String departId) {
		    //获取该模板的所有下级机构
		    List<Map<String, String>> mapList=organizationDao.getDepartmentNext(departId);
		    String departIds="";
	        for(int i=0;i<mapList.size();i++){
	        	if(i==0){
	        		departIds="'"+mapList.get(i).get("departId")+"'";
	        	}else{
	        		departIds=departIds+",'"+mapList.get(i).get("departId")+"'";
	        	}
	        }
	        List<Department> departmentList=new ArrayList<Department>();
	        if(!departId.equals("")){
	        	departmentList=organizationDao.getDepartmentDepartId(systemId,departIds);
	        }
		
			List<EasyUiTreeVo> easyUiTreeVoList=transEasyUiVo(departmentList);
			List<EasyUiTreeVo> menuList = new ArrayList<EasyUiTreeVo>();
			// 查看结果
			for (EasyUiTreeVo menu : easyUiTreeVoList) {
				System.out.println(menu);
			}
			// 最后的结果
			// 先找到所有的一级菜单
			for (int i = 0; i < easyUiTreeVoList.size(); i++) {
				// 一级菜单为帐号机构
				if (easyUiTreeVoList.get(i).getId().equals(departId)) {
					menuList.add(easyUiTreeVoList.get(i));
				}
			}
			// 为一级菜单设置子菜单，getChild是递归调用的
			for (EasyUiTreeVo menu : menuList) {
				menu.setChildren(getChild(menu.getId(), easyUiTreeVoList));
			}
			/* Map<String,Object> jsonMap = new HashMap<>();
	    	jsonMap.put("menu", menuList);*/
	   
		return menuList;
	}
	
	public void saveDepartment(String parentOrgaId,String orga,String orgaDesc,String systemId){
		
		Department department=new Department();
		department.setDepartParentId(parentOrgaId);
		department.setDepartName(orga);
		//department.setDepartDesc(orgaDesc);
		department.setNote(orgaDesc);
		department.setModifiedTime(new Date());
		department.setCreateTime(new Date());
		department.setSystemId(systemId);
		organizationDao.save(department);
	}
	
	public void delDepartment(String orgaId,String systemId){
		
		organizationDao.deletDepartmentById(orgaId,systemId);
	}
	
	public void editDepartment(String orgaId,String systemId,String orga,String orgaDesc){
		organizationDao.editDepartmentById(orgaId,systemId,orga,orgaDesc);
	}
	//校验是否存在用户
	public boolean delCheckDepartment(String orgaId){
		
	    List<Map<String, String>> mapList=organizationDao.getDepartmentNext(orgaId);
	    String departIds="";
	    boolean  b=false;
        for(int i=0;i<mapList.size();i++){
    	    b=userDetailDao.isExistUserDtail(mapList.get(i).get("departId"));
    	    if(b){
    	    	return b;
    	    }
        	
        }
		
		return b;
	}

	
	//转换成easyUi
	private List<EasyUiTreeVo> transEasyUiVo(List<Department> department){
		List<EasyUiTreeVo> easyUiTreeVoList=new ArrayList<EasyUiTreeVo>();
		for(int i=0;i<department.size();i++){
			EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
			easyUiTreeVo.setId(department.get(i).getDepartId());
			easyUiTreeVo.setParentId(department.get(i).getDepartParentId());
			easyUiTreeVo.setText(department.get(i).getDepartName());
			easyUiTreeVo.setAttributes(department.get(i).getNote());
			easyUiTreeVoList.add(easyUiTreeVo);
		}
		return easyUiTreeVoList;
		
		
	}
	
	//转换成easyUi
	private List<EasyUiTreeVo> transEasyUiVo2(List<Department> department,List<Map<String,Object>>  map){
		List<EasyUiTreeVo> easyUiTreeVoList=new ArrayList<EasyUiTreeVo>();
		for(int i=0;i<department.size();i++){
			EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
            int temp=0;
			for(int j=0;j<map.size();j++){
				if(department.get(i).getDepartId().equals(map.get(j).get("departId"))){
					easyUiTreeVo.setCount(map.get(j).get("number").toString());
					temp=1;
					break;
				}
			}
			if(temp==0){
				easyUiTreeVo.setCount("0");
			}
			easyUiTreeVo.setId(department.get(i).getDepartId());
			easyUiTreeVo.setParentId(department.get(i).getDepartParentId());
			easyUiTreeVo.setText(department.get(i).getDepartName());
			easyUiTreeVo.setAttributes(department.get(i).getNote());
			easyUiTreeVoList.add(easyUiTreeVo);
		}
		return easyUiTreeVoList;
		
		
	}
	/**
	 * 递归查找子菜单
	 * 
	 * @param id
	 *            当前菜单id
	 * @param rootMenu
	 *            要查找的列表
	 * @return
	 */
	private List<EasyUiTreeVo> getChild(String id, List<EasyUiTreeVo> rootMenu) {
	    // 子菜单
	    List<EasyUiTreeVo> childList = new ArrayList<>();
	    for (EasyUiTreeVo menu : rootMenu) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	        if (StringUtils.isNotBlank(menu.getParentId())) {
	            if (menu.getParentId().equals(id)) {
	                childList.add(menu);
	            }
	        }
	    }
	    // 把子菜单的子菜单再循环一遍
	    for (EasyUiTreeVo menu : childList) {//
	            // 递归
	            menu.setChildren(getChild(menu.getId(), rootMenu));
	        
	    } // 递归退出条件
	    if (childList.size() == 0) {
	        return null;
	    }
	    return childList;
	}
	/**
	 * 判断当前操作机构是否在下级机构里面
	 * 
	 */
	public boolean checkForDepart(String departIdNow,String departId){
		boolean check=false;
		
		List<Map<String, String>> mapDepartId=organizationDao.getDepartmentNext(departId);
		for(int i=0;i<mapDepartId.size();i++){
			if(departIdNow.equals(mapDepartId.get(i).get("departId"))){
				return true;
			}
		}
		return check;
	}

}
