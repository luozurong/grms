package com.hori.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hori.dao.queryBean.RoleQueryBean;
import com.hori.db.HibernateBaseDao;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.model.Role;
import com.hori.utils.FuzzyQueryUtils;
import com.hori.vo.SelectVo;

@Repository
public class RoleDao extends HibernateBaseDao<Role>{

	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(RoleDao.class);
	public List<Role> findAllRole() {
		String hql = "from Role WHERE 1=1 ";
		return this.find(hql);
	}
	
	/**
	 * 删除角色
	 * @param roleId
	 */
	public void deletRoleById(String roleId){
	    String hql = " Delete FROM Role  WHERE roleId=?";
		String[] params = new String[] { roleId };
		this.executeUpdate(hql,params);
	}
	
	
	public boolean isExistRoleByRoleName(String roleName,String roleId,String systemId) {
		StringBuilder hql = new StringBuilder("from Role WHERE 1=1 AND systemId=? ");
		List<Object> conditionsValue = new ArrayList<Object>();
		conditionsValue.add(systemId);

		if(StringUtils.isNotBlank(roleName)){
			hql.append(" AND roleName = ?");
			conditionsValue.add(roleName);
		}
		if(StringUtils.isNotBlank(roleId)){
			hql.append(" AND roleId != ?");
			conditionsValue.add(roleId);
		}
		List<Role> list = this.find(hql.toString(),conditionsValue);
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	
	public Role getRoleByRoleName(String roleName) {
		StringBuilder hql = new StringBuilder("from Role WHERE 1=1  ");
		List<Object> conditionsValue = new ArrayList<Object>();
		if(StringUtils.isNotBlank(roleName)){
			hql.append(" AND name = ?");
			conditionsValue.add(roleName);
		}
		
		List<Role> list = this.find(hql.toString(),conditionsValue);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public List<Role> findRoleBySytemId(String sytemId) {
		StringBuilder hql = new StringBuilder(" from Role WHERE 1=1 "); 
		List<Object> conditionsValue = new ArrayList<Object>();
		if(StringUtils.isNotBlank(sytemId)){
			hql.append("  AND systemId = ? ");
			conditionsValue.add(sytemId);
		}
		return this.find(hql.toString(),conditionsValue);
	}
	
	public DataGridPage findRolePage(RoleQueryBean queryBean,String roleIds,String userType,byte dataArea){
		StringBuilder hql = new StringBuilder();
		List<Object> params=new ArrayList<Object>();
	
		hql.append(" From Role WHERE 1=1 and systemId=?  ");
		if(!userType.equals("0")&&!(dataArea==2)){
			hql.append("and roleId in ("+roleIds+")");
		}
		params.add(queryBean.getSystemId());
		if(StringUtils.isNotBlank(queryBean.getRoleName())){
			hql.append(" AND  roleName  like ? ");
			params.add("%"+queryBean.getRoleName()+"%");
		}
		  if(queryBean.getCreateTimeBegin()!=null){
				hql.append(" and createTime>= ?");
				params.add(queryBean.getCreateTimeBegin());
			}
		    if(queryBean.getCreateTimeEnd()!=null){
				hql.append(" and createTime<= ?");
				params.add(queryBean.getCreateTimeEnd());
			}
		    
		hql.append("  ORDER BY createTime  ");
		PageBean pb = new PageBean();
		pb.setPage(queryBean.getPageNumber());
		pb.setRp(queryBean.getPageSize());
		Object[] v=new Object[params.size()];
		v=params.toArray(v);
		DataGridPage dbp = this.pagedQuery(hql.toString(), pb,v);
		
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		
		return dbp;
	}
	
	
}
