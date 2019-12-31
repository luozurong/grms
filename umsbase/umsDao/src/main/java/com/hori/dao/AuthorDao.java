package com.hori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.model.Author;
import com.hori.model.Menu;
import com.hori.model.Operation;

@Repository
public class AuthorDao  extends HibernateBaseDao<Author>{
	
	/**
	 * 根据请求参数,返回对应权限
	 * @param 
	 * @return
	 */
	public List<Author> getAuthByUserID(String userId) {
		return  this.find("from Author t where 1=1 and t.roleId = (select roleId from UserRole where systemId='1' and  userId=?) ", userId);
	}
	
	/**
	 * 根据请求参数,返回对应权限
	 * @param 
	 * @return
	 */
	public List<Author> getAuthByRoleID(String roleId,int menuApp) {
		return  this.find("from Author t where 1=1 and t.roleId = ? and t.resourceApp=? order by t.resourseOrder", roleId,menuApp);
	}
	
	
	/**
	 * 根据请求参数,返回按钮权限
	 * @param 
	 * @return
	 */
	public List<Author> getAuthByRoleIDBtn(String roleId) {
		return  this.find("from Author t where 1=1 and t.roleId = ? and t.resourseType='1' ", roleId);
	}
	
	/**
	 * 删除角色对应权限
	 * @param roleId
	 */
	public void deletRoleById(String roleId){
	 String hql = " Delete FROM Author  WHERE roleId=?";

		String[] params = new String[] { roleId };


		this.executeUpdate(hql,params);
	}
	
	/**
	 * 根据请求参数,返回对应权限
	 * @param 
	 * @return
	 */
	public List<Author> getAuthByUserID(String userId,String systemId,String resourceApp) {
		int resourceAppInt=Integer.valueOf(resourceApp);
		Object[] params = new Object[] { systemId,userId,resourceAppInt };
        StringBuffer hql=new StringBuffer();
        hql.append(" from Author t where 1=1 and t.roleId = (select roleId from UserRole where systemId=? and  userId=?) and resourceApp=? order by resourseOrder");
		return  this.find(hql.toString(), params);
	}
	/**
	 * 如果菜单或者按钮改变同步更新授权表数据
	 * @param 
	 * @return
	 */
	public void updateAuthorByMenu(Menu menu) {
        StringBuffer hql=new StringBuffer();
        hql.append(" update Author t set t.resourceDesc=?,t.resourceUrl=?,t.resourseOrder=?,t.resourseIcon=? where t.resourceId=?");
		Object[] params = new Object[] { menu.getMenuname(),menu.getMenuUrl(),Integer.valueOf(menu.getMenuOrder()),menu.getMenuIcon(),menu.getMenuId() };
		this.executeUpdate(hql.toString(),params);

	}
	/**
	 * 如果菜单或者按钮改变同步更新授权表数据
	 * @param 
	 * @return
	 */
	public void updateAuthorByButton(Operation operation) {
        StringBuffer hql=new StringBuffer();
        hql.append(" update Author t set t.resourceDesc=?,t.resourceUrl=?,t.resourseOrder=?,t.resourseIcon=?,t.resourceCode=? where t.resourceId=?");
		Object[] params = new Object[] { operation.getBtnName(),operation.getBtnUrl(),operation.getBtnOrder(),operation.getBtnIcon(),operation.getBtnCode(),operation.getBtnId() };
		this.executeUpdate(hql.toString(),params);

	}

}
