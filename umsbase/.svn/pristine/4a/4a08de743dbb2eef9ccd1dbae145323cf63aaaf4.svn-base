package com.hori.service;

import java.util.List;

import com.hori.model.RoleAuth;

public interface RoleAuthService  {
	/**
	 * 保存角色和权限的关联
	 */
	public void saveRoleAuth(RoleAuth roleAuth);
	/**
	 * 角色查找权限
	 * @param roleId
	 * @return
	 */
	public List<RoleAuth> getRoleAuthListByRoleId(String roleId);
	
	/**
	 * 删除角色关联的权限
	 * @param roleId
	 */
	public void deleteRoleAuthByRoleId(String roleId);
}
