package com.hori.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.model.Role;
import com.hori.model.UserRole;

@Repository
public class UserRoleDao extends HibernateBaseDao<UserRole>{
	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(UserRole.class);

	public List<UserRole> getByRoleId(String roleId) {
		String hql = "FROM UserRole where roleId=? ";
		List<Object> values = new ArrayList<Object>();
		values.add(roleId);
		return this.find(hql,values);
	}
	public void upateRoleName(String roleId,String roleName) {
		String hql = "update  UserRole set roleName=? where roleId=? ";
		String[] params = new String[] { roleName, roleId};
		this.executeUpdate(hql,params);
	}
	
	public void deletUserRoleById(String systemId,String userId){
		 String hql = " Delete FROM UserRole  WHERE systemId=? and userId=? ";

			String[] params = new String[] { systemId, userId};


			this.executeUpdate(hql,params);
		}
	
	//查找角色
	public UserRole getUserRole(String systemId,String userId) {
		StringBuilder hql = new StringBuilder("from UserRole WHERE 1=1  ");
		List<Object> conditionsValue = new ArrayList<Object>();
		hql.append(" AND systemId = ?");
		conditionsValue.add(systemId);
		hql.append(" AND userId = ?");
		conditionsValue.add(userId);
		List<UserRole> list = this.find(hql.toString(),conditionsValue);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	

}
