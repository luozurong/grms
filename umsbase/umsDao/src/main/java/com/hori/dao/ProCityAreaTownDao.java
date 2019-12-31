package com.hori.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.hori.db.HibernateBaseDao;
import com.hori.model.ProCityAreaTown;


/**
 * 区域管理
 * @author liaowl
 *
 */
@Repository
public class ProCityAreaTownDao extends HibernateBaseDao<ProCityAreaTown> {
	
	/**
	 * 查询所有的记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProCityAreaTown> findProCityAreaTownByName(String name){
		//StringBuffer hql = new StringBuffer();
		//hql.append("FROM ProCityAreaTown p where p.name LIKE '%"+name+"%' ");
		
		/*if (name != null) {
			if (FuzzyQueryUtils.isCondition(name)) {
				hql.append(" AND p.name LIKE '%"+name+"%'");
			}
		}*/
		String hql="FROM ProCityAreaTown p where p.name LIKE '%"+name+"%' ";
		List<ProCityAreaTown> list = this.find(hql.toString());
		
		if (null != list && list.size() > 0) {
		     return list;
		}
		return null;
	}
	
	/**
	 * 通过父类parentId查出所有的数据
	 * @param parentId
	 * @return
	 */
	public List<ProCityAreaTown> findProCityAreaTownByParentId(String parentId){
		String hql="FROM ProCityAreaTown p where parentId =?";
		//return this.find(hql);
		String[] param={parentId};
		List<ProCityAreaTown> list= this.find(hql, param);
		return list;
	}
	
	
	/**
	 * 通过父类parentId查出所有的树列表数据
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> findTreeAreaByParentId(String parentId){
		String sql = "SELECT pcat.code , pcat.name " 
				+ "FROM pro_city_area_town pcat, community c " 
				+ "where parentId = '" + parentId + "' and (pcat.code = c.province or pcat.code = c.city or pcat.code = c.country) group by pcat.code ";
		
		System.out.println(sql);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("id", rs.getString("code"));
				map.put("text", rs.getString("name").trim());
				map.put("state", "closed");
				return map;
			}
		};
		
		List voList = super.getJdbcTemplate().query(sql ,rmp);
		if(null != voList && 0 < voList.size()){
			return  voList;
		}
		
		return null;
	}
	

	/**
	 * 查出所有的树列表数据
	 * @return
	 */
	public List<Map<String,Object>> findTreeAreaAll(){
		String sql = "SELECT distinct(pcat.code) , pcat.name ,pcat.parentId " 
				+ "FROM pro_city_area_town pcat, community c " 
				+ "where pcat.code = c.province or pcat.code = c.city or pcat.code = c.country ";
		
		System.out.println(sql);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("id", rs.getString("code"));
				map.put("text", rs.getString("name").trim());
				map.put("state", "closed");
				map.put("parentId", rs.getString("parentId"));
				return map;
			}
		};
		
		List voList = super.getJdbcTemplate().query(sql ,rmp);
		if(null != voList && 0 < voList.size()){
			return  voList;
		}
		
		return null;
	}
	
	/**
	 * 通过多个id查出所有的列表数据
	 * @param ids
	 * @return
	 */
	public List<ProCityAreaTown> findProCityAreaTownByIds(String ids[]){
		String hql="from ProCityAreaTown p where p.id in ('";
		hql += StringUtils.join(ids,"','");
		hql += "')";
		return this.find(hql);
	}
	
	
	/**
	 * 通过区域编码查找出 相关的地址
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProCityAreaTown> findProCityAreaTownByCode(String code){
		String hql="FROM ProCityAreaTown p where code =?";
		//return this.find(hql);
		String[] param={code};
		List<ProCityAreaTown> list= this.find(hql, param);
		if (null != list && list.size() > 0) {
		     return list;
		}
		return null;
	}
	/**
	 * 判断是否是父类parentId
	 * @param code
	 * @return
	 */
	public int findIsParentId(String code ){
		String sql="select count(*) from pro_city_area_town where parentId = ?";
		Integer result = new Integer("0");
		List<ProCityAreaTown> list = this.createNavtiveSQLQuery(sql,code);
		if (null != list && list.size() > 0) {
			Object retObj = list.get(0);
			if (retObj instanceof BigInteger) {
				result =  ((BigInteger)retObj).intValue();
			}
		}
			return result ;
		
	}
	/**
	 * 运行sql的方法
	 * @param sql
	 * @param args
	 * @return
	 */
	public List createNavtiveSQLQuery(final String sql, final Object... args) {
		Assert.hasText(sql);
		SQLQuery sqlQuery =this.getCurrentSession().createSQLQuery(sql);
		if (null != args && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				sqlQuery.setParameter(i, args[i]);
			}
		}
		sqlQuery.setCacheable(false);
		sqlQuery.addEntity(ProCityAreaTown .class); 
		List<ProCityAreaTown> list=sqlQuery.list();
//		List<?> list = getHibernateTemplate().executeFind(
//				new HibernateCallback<Object>() {
//					public Object doInHibernate(Session session)
//							throws HibernateException, SQLException {
//						SQLQuery sqlQuery = session.createSQLQuery(sql);
//						if (null != args && args.length > 0) {
//							for (int i = 0; i < args.length; i++) {
//								sqlQuery.setParameter(i, args[i]);
//							}
//						}
//						sqlQuery.setCacheable(false);
//						return sqlQuery.list();
//					}
//				});
		return list;
	}
	/**
	 * 通过id查找出相关数据
	 * @param id
	 * @return
	 */
	public ProCityAreaTown getById(int id){
		String hql="from ProCityAreaTown p where p.id=?";
		List<ProCityAreaTown> list=this.find(hql, id);
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 通过名字查找出所有父类的数据
	 * @param name
	 * @return
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public String getAreaParentCodeListByName(String name) throws HibernateException, SQLException{
		String sql = "SELECT getAreaParentListByName(?) from dual";
		String[] param={name};
		List list=this.createNavtiveSQLQuery(sql,param);
		String result = list.toString();
		return result;
	}
	/**
	 * 通过code查找出所有的父类code
	 * @param code
	 * @return
	 */
	 public String getAreaParentCodeListByCodes(String code){
		
		String sql = "SELECT getAreaParentListByCodes(?) from dual";
		String[] param={code};
		List list=this.createNavtiveSQLQuery(sql,param);
		String result = list.toString();
		return result;
	}
	/**
	 * 通过code的聚合查出相关数据
	 * @param codes
	 * @return
	 */
	public List getAreaListByCodes(String codes){
		String sql="select a.id as did,a.code as id ,a.name as name ,a.parentId as parentId from pro_city_area_town a where FIND_IN_SET(a.code,?)";
		String[] param={codes};
		List list=this.createNavtiveSQLQuery(sql,param);
		return list;
	}
	/**
	 * 查找到所有的code
	 * @param parentId
	 * @return
	 */
	public String getCodeByParentId(String parentId){
		String hql = "select GROUP_CONCAT(code) from pro_city_area_town where parentId=?";
		String[] param={parentId};
		List list=this.createNavtiveSQLQuery(hql,param);
		String result = list.toString();
		return result;
	}
	
	/**
	 * 查询所有没有父ID的数据(即查询Provence)
	 * @return
	 */
	public List<ProCityAreaTown> getNoParentId(){
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT pcat FROM ProCityAreaTown pcat WHERE pcat.parentId = '0086'");
		List<Object> conditionvalues = new ArrayList<Object>();
		List<ProCityAreaTown> list = this.find(hql.toString(), conditionvalues.toArray());
		return list;
	}
	
	/**
	 * 通过name查找出相关数据
	 * @param name
	 * @return
	 */
	public ProCityAreaTown getByName(String name){
		String hql="from ProCityAreaTown p where p.name=?";
		List<ProCityAreaTown> list=this.find(hql, name);
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public Map<String, String> getAllMap() {
		String sql="SELECT name,code from pro_city_area_town pcat GROUP BY code";
		List<Object> list = this.createNavtiveSQLQuery(sql);
		Map<String, String> map = new HashMap<String,String>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object []data =(Object[]) list.get(i);
				map.put(data[1].toString(), data[0].toString());
			}
			return map;
		}
		return null;
	}
	
}
