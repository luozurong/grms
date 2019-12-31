package com.hori.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.model.Department;
@Repository
public class OrganizationDao extends HibernateBaseDao<Department>{
	private static final Log log = LogFactory.getLog(OrganizationDao.class);
	/**
	 * 获取所有机构
	 * @param 
	 */
	public List<Department> getDepartmentAll(String parame) {
		String hql = "FROM Department where systemId=?";
		List<Object> values = new ArrayList<Object>();
		values.add(parame);
		return this.find(hql,values);
	}
	/**
	 * 获取当前机构的所有下属机构
	 * @param 
	 */
	public List<Department> getDepartmentDepartId(String parame,String ids) {
		String hql = "FROM Department where systemId=? and departId in ("+ids+") ";
		List<Object> values = new ArrayList<Object>();
		values.add(parame);
		return this.find(hql,values);
	}
	
	/**
	 * 删除机构及下属机构
	 * @param roleId
	 */
	public void deletDepartmentById(String orga,String systemId){
	 String hql = " Delete FROM Department  WHERE (departId = ? or departParentId =?) and systemId=?";

		String[] params = new String[] { orga,orga, systemId };


		this.executeUpdate(hql,params);
	}
	
	/**
	 * 更新机构名称及备注
	 * @param roleId
	 */
	public void editDepartmentById(String orgaId,String systemId,String orga,String orgaDesc){
	 String hql = " update  Department set departName=?,note=?  WHERE departId =? and systemId=?";

		String[] params = new String[] { orga,orgaDesc,orgaId, systemId };
		//更改员工信息表
        String hql2="update UserDetail set departName=? where  departId= ? ";

		String[] params2 = new String[] { orga,orgaId};

		this.executeUpdate(hql,params);
		this.executeUpdate(hql2,params2);

	}
	/**
	 * 获取当前机构的下级(包括本级)
	 * @param 
	 */
	public List<Map<String, String>> getDepartmentNext(String departId) {
		String sql = "select depart_id from department where FIND_IN_SET(depart_id, queryChildrenAreaInfo('"+departId+"'))";
		System.out.println(sql);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("departId", rs.getString("depart_id"));
				return map;
			}
		};
		List<Map<String, String>> voList = super.getJdbcTemplate().query(sql.toString() ,rmp);
		return voList;

	}
	/**
	 * 获取当前机构的下级(不包括本级)
	 * @param 
	 */
	public List<Map<String, String>> getDepartmentNext2(String departId) {
		String sql = "select depart_id from department where FIND_IN_SET(depart_id, queryChildrenAreaInfo('"+departId+"')) and depart_id!='"+departId+"'";
		System.out.println(sql);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("departId", rs.getString("depart_id"));
				return map;
			}
		};
		List<Map<String, String>> voList = super.getJdbcTemplate().query(sql.toString() ,rmp);
		return voList;

	}
	/**
	 * 获取系统的所有机构
	 * @param 
	 */
	public List<Map<String, String>> getDepartmentById(String systemId) {
		String sql = "select depart_id from department where  system_id='"+systemId+"'";
		System.out.println(sql);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("departId", rs.getString("depart_id"));
				return map;
			}
		};
		List<Map<String, String>> voList = super.getJdbcTemplate().query(sql.toString() ,rmp);
		return voList;

	}

}
