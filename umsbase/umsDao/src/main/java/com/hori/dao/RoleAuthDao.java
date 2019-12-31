package com.hori.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.hori.db.HibernateBaseDao;
import com.hori.model.RoleAuth;
@Repository
public class RoleAuthDao extends HibernateBaseDao<RoleAuth> {
	/**
	 * 角色查找权限
	 * @param roleId
	 * @return
	 */
	public List<RoleAuth> getRoleAuthListByRoleId(String roleId){
		StringBuilder hql = new StringBuilder(" FROM  RoleAuth  WHERE 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if(StringUtils.isNotBlank(roleId)){
			hql.append(" AND roleId = ? ");
			param.add(roleId);
		}
		List<RoleAuth> list =  this.find(hql.toString(), param);
		if(null!=list&&list.size()>0){
			return list;
		}
		return new ArrayList<RoleAuth>();
	}
	
	/**
	 * 删除角色关联的权限
	 * @param roleId
	 */
	public void deleteRoleAuthByRoleId(String roleId){
		String hql = " Delete FROM RoleAuth  WHERE roleId = '"+roleId+"' ";
		this.executeUpdate(hql);
	}
}
