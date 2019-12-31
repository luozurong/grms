package com.hori.service;

import java.util.List;

import com.hori.model.Department;
import com.hori.vo.EasyUiTreeVo;

public interface OrganizationService  extends BaseServiceI {
	
	/**
	 * 获取组织架构基本信息
	 * @return
	 */
	public List<EasyUiTreeVo> dealDepartmentBySystemId(String systemId);
	/**
	 * 增加机构信息
	 * @return
	 */
	public void saveDepartment(String parentOrgaId,String orga,String orgaDesc,String systemId);
	/**
	 * 删除机构信息
	 * @return
	 */
	public void delDepartment(String orgaId,String systemId);

	/**
	 * 修改机构信息
	 * @return
	 */
	public void editDepartment(String orgaId,String systemId,String orga,String orgaDesc);
	
	/**
	 * 判断当前操作机构是否在下级机构里面
	 * 
	 */
	public boolean checkForDepart(String departIdNow,String departId);
	/**
	 * 获取当前机构的下级
	 * 
	 */
	public List<EasyUiTreeVo> dealDepartmentBySystemIdNext(String systemId,String departId);
	/**
	 * 校验是否存在用户
	 * 
	 */
	public boolean delCheckDepartment(String orgaId);

}
