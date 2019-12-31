package com.hori.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.hori.dao.queryBean.CommunityQueryBean;
import com.hori.db.HibernateBaseDao;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.model.Community;
import com.hori.utils.FuzzyQueryUtils;

@Repository
public class CommunityDao extends HibernateBaseDao<Community>{
	@Autowired
    private JdbcTemplate vdcsJdbcTemplate;
	
	
	public void bachSave(List<Community> addCommunities) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				for(int i=0;i<addCommunities.size();i++){
					session.save(addCommunities.get(i));
					if(i%200==0){
						session.flush();
						session.clear();
					}
				}
				session.flush();
				session.clear();
				return null;
			}
		});
	}
	
   
	 /**
	  * 通过日期获取需要同步更新的小区信息
	  * @param queryBean
	  * @return
	  */
	public List<Community> getUpdateCommunityByTime(CommunityQueryBean queryBean) {
		String sql = "Select a.id as id,a.area_name as name,a.organization_seq as organization,a.province as province,"
				 + "a.city as city,a.country as country,a.town as town,a.serial as serial,a.create_time as createTime,a.update_time as updateTime"
				 +" From area  a Where 1=1 "
				 +" AND a.update_time<= '"+queryBean.getEndTime()+"' and a.update_time >= '"+queryBean.getStartTime()+"'";
	List<Community> result = new ArrayList<Community>();
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Community community =new Community();
				community.setId(rs.getString("id"));
				community.setCommunityName(rs.getString("name"));
				community.setOrganizationSeq(rs.getString("organization"));
				community.setProvince(rs.getString("province"));
				community.setCity(rs.getString("city"));
				community.setCountry(rs.getString("country"));
				community.setTown(rs.getString("town"));
				community.setSerial(rs.getString("serial"));
				community.setCreateTime(rs.getDate("createTime"));
				community.setUpdateTime(rs.getDate("updateTime"));
				return community;
			}
		};
		
		result = this.vdcsJdbcTemplate.query(sql.toString(), rmp);
	return result;
	}
    
	 /**
	  * 通过日期获取需要同步新增的小区信息
	  * @param queryBean
	  * @return
	  */
	public List<Community> getAddCommunityByTime(CommunityQueryBean queryBean) {
		String sql = "Select a.area_name as name,a.organization_seq as organization,a.province as province,"
					 +"a.city as city,a.country as country,a.town as town,a.serial as serial,a.create_time as createTime,"
					 +"a.update_time as updateTime,ATal.total AS grandCard,a.address AS address "
//					 +"familyCount.householdRegisterNum AS familyCount,peopleCount.appUseNum AS peopleCount "
					 +"From area  a "
					 +"LEFT JOIN "
					 +"( SELECT a.organization_seq,COUNT(*) AS total FROM area a "
					 +"LEFT JOIN issue_card_info i ON a.organization_seq = i.organization_seq "
					 +"WHERE i.is_reset=0 GROUP BY a.organization_seq "
					 +") AS ATal  ON ATal.organization_seq = a.organization_seq ";
//					 +"LEFT JOIN ("
//					 +"select count(*) as householdRegisterNum ,organizationSeq AS org from household "
//					 +"where   userType=1 "
//					 +"AND (householdName !='' AND householdName is NOT NULL) "
//					 +"AND ((contact !='' AND contact is NOT NULL) OR (fixedPhone !='' AND fixedPhone is NOT NULL)) "
//					 +"GROUP BY organizationSeq "
//					 +")AS familyCount ON a.organization_seq=familyCount.org "
//					 +"LEFT JOIN "
//					 +"(select count(DISTINCT(user_account)) as appUseNum ,organizationSeq AS orgSeq from app_oauth "
//					 +"GROUP BY organizationSeq) AS peopleCount ON peopleCount.orgSeq = a.organization_seq;";
//				    + " AND (a.create_time <= '"+queryBean.getEndTime()+"' and a.create_time>='"+queryBean.getStartTime()+"')";
	List<Community> result = new ArrayList<Community>();
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Community community =new Community();
//				community.setId(rs.getString("id").trim());
				community.setCommunityName(rs.getString("name").trim());
				community.setOrganizationSeq(rs.getString("organization").trim());
				community.setProvince(rs.getString("province"));
				community.setCity(rs.getString("city"));
				community.setCountry(rs.getString("country")); 
				community.setTown(rs.getString("town"));
				community.setSerial(rs.getString("serial"));
				community.setCreateTime(rs.getDate("createTime"));
				community.setUpdateTime(rs.getDate("updateTime"));
				community.setAddress(rs.getString("address").trim());
				community.setGrandCardCount(rs.getInt("grandCard"));
				community.setFamilyCount(0);
				community.setPeopleCount(0);
				community.setCommunityType(1);
				return community;
			}
		};
		
		result = this.vdcsJdbcTemplate.query(sql.toString(), rmp);
	return result;
	}
    
	/**
	 * 通过查询bean获取小区信息
	 * @param queryBean
	 * @return
	 */
	public List<Community> getByQueryBean(CommunityQueryBean queryBean) {
		StringBuffer hql =new StringBuffer("FROM Community  WHERE 1=1");
		List<Object> param =  new ArrayList<Object>();
		if(queryBean.getIds()!=null){
			String ids=FuzzyQueryUtils.getIds(queryBean.getIds());
			hql.append(" and id IN("+ids+")");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getCommunityName())){
			String name=queryBean.getCommunityName();
			hql.append(" and name like '").append(FuzzyQueryUtils.fuzzyQueryCondition(name)).append("'");
		}
		if(queryBean.getTerminalKey()!=0){
			if(queryBean.getTerminalKey()==1){
				hql.append(" and terminalCount >="+queryBean.getTerminalCount()+"");
			}else{
				hql.append(" and terminalCount <="+queryBean.getTerminalCount()+"");
			}
		}
		if(queryBean.getPeopleKey()!=0){
			if(queryBean.getPeopleKey()==1){
				hql.append(" and familyCount >="+queryBean.getFamilyCount()+"");
			}else{
				hql.append(" and familyCount <="+queryBean.getFamilyCount()+"");
			}
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getProvince())){
			hql.append(" and province="+queryBean.getProvince()+"");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getCity())){
			hql.append(" and city="+queryBean.getCity()+"");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getCountry())){
			hql.append(" and country="+queryBean.getCountry()+"");
		}
		if(queryBean.getOrganizationSeqs()!=null){
			String orgs=FuzzyQueryUtils.getIds(queryBean.getOrganizationSeqs());
			hql.append(" and organizationSeq IN("+orgs+")");
		}
		if(queryBean.getCommunityType()!=null){
			hql.append(" and communityType="+queryBean.getCommunityType()+"");
		}
		
		return this.find(hql.toString());
	}
	
	/**
	 * 通过查询province,city,country获取小区信息
	 * @param province 省ID
	 * @param city 城市ID
	 * @param country 镇区ID
	 * @return
	 */
	public List<Community> getByLocation(String province,String city,String country) {
		StringBuffer hql =new StringBuffer("FROM Community  WHERE 1=1 AND province = ? AND city = ? AND country = ?");		
		return this.find(hql.toString() , province , city , country);
	}
	
	/**
	 * 通过查询country获取小区信息
	 * @param country 镇区ID
	 * @return
	 */
	public List<Map<String,Object>> getAreaTreeByCountry(String country) {
		String sql = "SELECT id , name ,organization_seq " 
				+ "FROM community " 
				+ "where 1=1 AND country = '" + country + "'";
		
		System.out.println(sql);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				Map<String,Object> attributes =new HashMap<String,Object>();
				map.put("id", rs.getString("id"));
				map.put("text", rs.getString("name"));
				attributes.put("organizationSeq", rs.getString("organization_seq"));
				map.put("attributes", attributes);
				map.put("state", "open");
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
	 * 批量保存小区信息
	 * @param addCommunities
	 * @return
	 */
	public void saveAll(List<Community> addCommunities) {
		StringBuffer sql = new StringBuffer("INSERT INTO community (id,name,address,serial,organization_seq,province,city,country,town,create_time,update_time,type,family_count,people_count,terminal_count,grand_card_count,enable_promotion_active,active_location_detail,mall_name,treatment_department_name,education_department_name,banking_department_name) VALUES");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(addCommunities!=null&&addCommunities.size()>0){
				for(Community community:addCommunities){
					if(StringUtils.isNotBlank(community.getAddress())){
						sql.append("('"+community.getId()+"', '"+community.getCommunityName()+"', '"+community.getAddress()+"', '"+community.getSerial()+"'"
								   + ", '"+community.getOrganizationSeq()+"', '"+community.getProvince()+"', '"+community.getCity()+"'"
								   + ", '"+community.getCountry()+"', '"+community.getTown()+"','"+(FuzzyQueryUtils.isCondition(community.getCreateTime())?sdf.format(community.getCreateTime()):sdf.format(new Date()))+"', '"+(FuzzyQueryUtils.isCondition(community.getUpdateTime())?sdf.format(community.getUpdateTime()):sdf.format(new Date()))+"', -1, 0, 0, 0, "+community.getGrandCardCount()+", 0, NULL, NULL,NULL, NULL, NULL),");
					}else{
						sql.append("('"+community.getId()+"', '"+community.getCommunityName()+"',NULL,'"+community.getSerial()+"'"
								   + ", '"+community.getOrganizationSeq()+"', '"+community.getProvince()+"', '"+community.getCity()+"'"
								   + ", '"+community.getCountry()+"', '"+community.getTown()+"','"+(FuzzyQueryUtils.isCondition(community.getCreateTime())?sdf.format(community.getCreateTime()):sdf.format(new Date()))+"', '"+(FuzzyQueryUtils.isCondition(community.getUpdateTime())?sdf.format(community.getUpdateTime()):sdf.format(new Date()))+"', -1, 0, 0, 0, "+community.getGrandCardCount()+", 0, NULL, NULL,NULL, NULL, NULL),");
					
					}
					
				}
				String sqlString =sql.toString().substring(0,sql.toString().length()-1);
				this.jdbcTemplate.batchUpdate(sqlString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	public DataGridPage findPage(Community community ,int pageNo, int pageSize){
		String hql = " FROM Community  WHERE 1=1 ";
		
		if(null != community){
			if(true == StringUtils.isNotBlank(community.getProvince())){
				hql += " AND province ='" + community.getProvince() + "'";
			}
			
			if(true == StringUtils.isNotBlank(community.getCity())){
				hql += " AND city ='" + community.getCity() + "'";
			}
			
			if(true == StringUtils.isNotBlank(community.getCountry())){
				hql += " AND country ='" + community.getCountry() + "'";
			}
			
			if(true == StringUtils.isNotBlank(community.getTown())){
				hql += " AND town ='" + community.getTown() + "'";
			}
			
			if(true == StringUtils.isNotBlank(community.getOrganizationSeq())){
				hql += " AND organizationSeq ='" + community.getOrganizationSeq() + "'";
			}
		}
			
		PageBean pb = new PageBean();
		pb.setPage(pageNo);
		pb.setRp(pageSize);
		
		DataGridPage dbp = super.pagedQuery(hql, pb);
		
		dbp.setPage(pageNo);
		dbp.setPageSize(pageSize);
		
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		
		return dbp;
	}

	public DataGridPage findAll(com.hori.pageModel.Community community) {
		PageBean pb = new PageBean();
		pb.setPage(community.getPageNumber());
		pb.setRp(community.getPageSize());
		StringBuffer hql = new StringBuffer("select c.* from community c where 1=1 ");
		if(FuzzyQueryUtils.isCondition(community.getCommunityName())){
			String name=community.getCommunityName();
			hql.append(" and c.name like '").append(FuzzyQueryUtils.fuzzyQueryCondition(name)).append("'");
		}
		if(community.getTerminalKey()!=0){
			if(community.getTerminalKey()==1){
				hql.append(" and c.terminal_count >="+community.getTerminalCount()+"");
			}else{
				hql.append(" and c.terminal_count <="+community.getTerminalCount()+"");
			}
		}
		if(community.getPeopleKey()!=0){
			if(community.getPeopleKey()==1){
				hql.append(" and c.family_count >="+community.getFamilyCount()+"");
			}else{
				hql.append(" and c.family_count <="+community.getFamilyCount()+"");
			}
		}
		if(FuzzyQueryUtils.isCondition(community.getProvince())){
			hql.append(" and c.province="+community.getProvince()+"");
		}
		if(FuzzyQueryUtils.isCondition(community.getCity())){
			hql.append(" and c.city="+community.getCity()+"");
		}
		if(FuzzyQueryUtils.isCondition(community.getCountry())){
			hql.append(" and c.country="+community.getCountry()+"");
		}
		if(community.getCommunityType()!=null){
			hql.append(" and c.community_type="+community.getCommunityType()+"");
		}
		
		hql.append(" ORDER BY c.create_time ASC");
		
		List<Object> values = new ArrayList<Object>();
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("id", rs.getString("id"));
				map.put("communityName", rs.getString("name"));
//				map.put("province", rs.getString("province"));
//				map.put("country", rs.getString("country"));
//				map.put("city", rs.getString("city"));
				map.put("address", rs.getString("address").trim());
				map.put("familyCount", rs.getString("family_count"));
				map.put("peopleCount", rs.getString("people_count"));
				map.put("terminalCount", rs.getString("terminal_count"));
				map.put("grandCardCount", rs.getString("grand_card_count"));
				return map;
			}
		};
		DataGridPage dbp =this.pagedQuery(hql.toString() ,pb,rmp);
		dbp.setPage(community.getPageNumber());
		dbp.setPageSize(community.getPageSize());
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		
		return dbp;
//		StringBuffer hql = new StringBuffer("FROM Community WHERE 1=1");
//		
//		return this.find(hql.toString(), community.getPage(), community.getRows(), values);
	}
    
	
	public Long total(com.hori.pageModel.Community community) {
		StringBuffer hql=new StringBuffer("select count(*) from Community where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		return this.count(hql.toString(), values);
	}

	/**
	 * 获取省份
	 * @return
	 */
	public List<Community> getAllPrivateInfo() {
		StringBuffer hql=new StringBuffer("from Community c group by c.province");
		
		List<Community> list = this.find(hql.toString());
		
		return list;
	}

	public Community getByOrganizationSeq(String organizationSeq) {
		StringBuffer hql=new StringBuffer("from Community c where c.organizationSeq="+organizationSeq);
		List<Community> list = this.find(hql.toString());
		return list.get(0);
	}
     
	/**
	 * 通过小区机构编号集合获取小区集合
	 * @param orgList
	 * @return
	 */
	public List<Community> getByOrganizationSeqs(List<String> orgList) {
		List<Community> result = new ArrayList<Community>();
		if(orgList.size()>0&&orgList!=null){
			String orgs = FuzzyQueryUtils.getIds(orgList);
			result = this.find("FROM Community WHERE organizationSeq IN("+orgs+")");
			return result;
		}
		return null;
	}
 
	/**
	 * hibernate批量更新数据
	 * @param communities
	 */
	public void bachUpdate(List<Community> communities) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				for(int inx=0;inx<communities.size();inx++){
					session.update(communities.get(inx));
					if(inx%200==0){
						session.flush();
						session.clear();
					}
				}
				session.flush();
				session.clear();				
				return null;
			}
			
		});
	}

	/**
	 * 通过终端序列号或许小区信息
	 * @param terminalSerial
	 * @return
	 */
	public Community getBySerial(String terminalSerial) {
		String sql = "SELECT c.name as name,c.organization_seq as organization FROM community c RIGHT JOIN terminal t ON t.organization_seq = c.organization_seq WHERE t.terminal_serial='"
				+ terminalSerial + "'";
		List<Community> result = new ArrayList<Community>();
		RowMapper rmp = new RowMapper() {
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Community community = new Community();
				community.setCommunityName(rs.getString("name").trim());
				community.setOrganizationSeq(rs.getString("organization").trim());
				return community;
			}
		};

		result = this.jdbcTemplate.query(sql.toString(), rmp);
		return result.get(0);
	}


}
