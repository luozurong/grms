package com.hori.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hori.dao.queryBean.AreaQueryBean;
import com.hori.db.HibernateBaseDao;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.model.AuthorizeArea;
import com.hori.ums.webservice.bean.UserDto;
import com.hori.util.StringUtil;
import com.hori.utils.FuzzyQueryUtils;
import com.hori.vo.AreaManagementVo;
import com.hori.vo.AuthorizeAreaVo;

@Repository
public class AreaAuthorizeDao extends HibernateBaseDao<AuthorizeArea>{
	
	public List<AuthorizeArea> getAreaAuthorizeById(String userId,String systemId) {
		String hql = "FROM AuthorizeArea where systemId=? and  userId=?";
		List<Object> values = new ArrayList<Object>();
		values.add(systemId);
		values.add(userId);

		return this.find(hql,values);
	}
	
	public List<AuthorizeArea> getAuthorizeAreaById(String userId,String systemId) {
		String hql = "FROM AuthorizeArea where systemId=? and  userId=?";
		return this.find(hql,systemId,userId);
	}
	
	/**
	 * 删除用户责任范围
	 * @param roleId
	 */
	public void deletAreaAuthorizeById(String userId,String systemId,String areaId){
	 String hql = " Delete FROM AuthorizeArea  WHERE systemId=? and  userId=? and areaId in ("+areaId+")";

		String[] params = new String[] { systemId,userId};


		this.executeUpdate(hql,params);
	}
	/**
	 * 分页查询
	 * @param 
	 */
	public DataGridPage findUserPage(AreaQueryBean queryBean,String userType,String userAccountIds){
		StringBuilder sql = new StringBuilder();
		List<Object> params=new ArrayList<Object>();
		sql.append(" select a.user_id,a.user_type,a.user_account,c.`name`,c.mobile,b.role_name,c.depart_id, c.depart_name ");
		sql.append(" from user a,user_role b,user_detail c ");
	    sql.append(" where a.user_id=b.user_id and b.system_id='"+queryBean.getSystemId()+"' and a.user_detail_id=c.user_detail_id ");
	    if(userType.equals("0")){
	    	
	    }else{
			sql.append(" and a.user_account in ("+userAccountIds+") ");
	    }
	    if(StringUtils.isNotBlank(queryBean.getUserAccount())){
			sql.append(" and a.user_account like ?");
			params.add("%"+queryBean.getUserAccount().trim()+"%");
		}
	    if(StringUtils.isNotBlank(queryBean.getDepartName())){
			sql.append(" and c.depart_name like ?");
			params.add("%"+queryBean.getDepartName().trim()+"%");

		}
	    if(StringUtils.isNotBlank(queryBean.getDepartId())){
			sql.append(" and c.depart_id=? ");
			params.add(queryBean.getDepartId().trim());

		}
	    if(StringUtils.isNotBlank(queryBean.getName())){
			sql.append(" and c.`name` like ?");
			params.add("%"+queryBean.getName().trim()+"%");

		}
	    if(StringUtils.isNotBlank(queryBean.getMobile())){
			sql.append(" and a.mobile like ?");
			params.add("%"+queryBean.getMobile().trim()+"%");

		}
	    if(StringUtils.isNotBlank(queryBean.getRoleName())){
			sql.append(" and  b.role_name like ? ");
			params.add("%"+queryBean.getRoleName().trim()+"%");

		}
		sql.append(" ORDER BY a.`create_time` ");
		PageBean pb = new PageBean();
		pb.setPage(queryBean.getPageNumber());
		pb.setRp(queryBean.getPageSize());
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				AreaManagementVo areaManagementVo =new AreaManagementVo();
				areaManagementVo.setUserId(rs.getString("user_id"));
				areaManagementVo.setUserAccount(rs.getString("user_account"));
				areaManagementVo.setDepartId(rs.getString("depart_id"));
				areaManagementVo.setDepartName(rs.getString("depart_name"));
				areaManagementVo.setMobile(rs.getString("mobile"));
				areaManagementVo.setRoleName(rs.getString("role_name"));
				areaManagementVo.setName(rs.getString("name"));
				areaManagementVo.setUserType(rs.getString("user_type"));
				return areaManagementVo;
			}
		};
		Object[] v=new Object[params.size()];
		v=params.toArray(v);
		DataGridPage dbp =this.pagedQuery(sql.toString() ,pb,rmp,v);
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		
		return dbp;
	}
	
	/**
	 *  获取某个责任区域内的所有运维人员账号
	 * @param provinceId 省
	 * @param cityId 市
	 * @param areaId 区域
	 */
	public List<String> getUserIdsByAuthorizeArea(String provinceId,String cityId,String countryId,List<String> countrys){
		
		StringBuilder sql = new StringBuilder("SELECT DISTINCT user_id from authorize_area where 1=1 ");
		
		List<Object> values = new ArrayList<Object>();

		if(FuzzyQueryUtils.isCondition(provinceId)){
			sql.append(" and province_id=?" );
			values.add(provinceId);
		}
		if(FuzzyQueryUtils.isCondition(cityId)){
			sql.append(" and city_id=?" );
			values.add(cityId);
		}
		if(FuzzyQueryUtils.isCondition(countryId)){
			sql.append(" and area_id=?" );
			values.add(countryId);
		}
		
		if(countrys!=null&&countrys.size()>0){
			sql.append(" and area_id in ("+FuzzyQueryUtils.getIds(countrys)+")" );
		}
		
		List<String> userIds = jdbcTemplate.queryForList(sql.toString(),values.toArray(),String.class);
		
		return userIds;
	}
	
	/**
	 * 获取用户的责任区域列表
	 * @param userId 用户id
	 * @return 返回区(县)id集合
	 */
	public List<String> getAuthorizeAreasByUserId(String userId){
		
		StringBuilder sql = new StringBuilder("SELECT area_id from authorize_area where user_id=? ");
		
		List<String> areaIds = jdbcTemplate.queryForList(sql.toString(),new Object[]{userId},String.class);
		
		return areaIds;
	}
	
	public List<AuthorizeArea> getAreaAuthorize(String userId,String systemId) {
		String hql = " FROM AuthorizeArea where systemId=? and  userId=?";
		List<Object> values = new ArrayList<Object>();
		values.add(systemId);
		values.add(userId);

		return this.find(hql,values);
	}
	
	/**
	 * 根据责任区域分页获取user
	 * @param areaId
	 * @param pageNo
	 * @param pageSize
	 * @param userAccount
	 * @return
	 */
	public DataGridPage getUserByAuthorizeAreaForPage(String userName,String areaId,String pageNo, String pageSize,String userType) {
		StringBuilder hql = new StringBuilder("SELECT u.*,d.name as name from authorize_area a,user u,user_detail d WHERE area_id = "+areaId+" AND a.user_id = u.user_id AND d.user_detail_id = u.user_detail_id and u.dr = '0'");
	    if(StringUtils.isNotBlank(userType)){
	    	hql.append(" and u.user_type='"+userType+"'");
		}
	    if (StringUtils.isNotBlank(userName)) {
	    	hql.append(" and d.name like '%"+userName+"%'");
		}
	    hql.append(" ORDER BY u.create_time ");
	    System.out.println(hql.toString());
		PageBean pb = new PageBean();
		if(StringUtils.isNotBlank(pageNo)){
			pb.setPage(Integer.parseInt(pageNo));
		}else {
			pb.setPage(1);
		}
		if(StringUtils.isNotBlank(pageSize)){
			pb.setRp(Integer.parseInt(pageSize));
		}else {
			pb.setRp(6);
		}
		
		RowMapper rmp = new RowMapper(){
            @Override
            public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
                    Map<String,String> map =new HashMap<String,String>();
                    map.put("user_id", rs.getString("user_id"));
                    map.put("user_account", rs.getString("user_account"));
                    map.put("user_name", rs.getString("name"));
                    map.put("user_mobile", rs.getString("mobile"));
                    return map;
            }
	    };
	    DataGridPage dbp =this.pagedQuery(hql.toString() ,pb,rmp);
	    dbp.setPage(pb.getPage());
	    dbp.setPageSize(pb.getRp());
	    if (0 >= dbp.getTotal()) {
	            dbp.setRows(new ArrayList());
	    }
	    
	    return dbp;
	}

	public DataGridPage getUserByAuthorizeAreasForPage(String userName,String[] areaIds,
			String pageNo, String pageSize, String userType) {
		StringBuilder hql = new StringBuilder("select u.user_id,u.user_account,u.mobile,d.name from ");
	    hql.append(" (select a.user_id from authorize_area a where a.area_id in (");
	    		//'天河区','龙岗区','白云区'
	    for (int i = 0; i < areaIds.length; i++) {
	    	if (i==0) {
	    		hql.append("'"+areaIds[i]+"'");
			}else {
				hql.append(",'"+areaIds[i]+"'");
			}
			
		}
	    		
	    hql.append(") and a.system_id = '1'");
	    hql.append(" GROUP BY a.user_id having COUNT(*)>="+areaIds.length+") w,user u ,user_detail d where w.user_id = u.user_id AND u.user_detail_id = d.user_detail_id and u.dr = '0'");
	    if(StringUtils.isNotBlank(userType)){
	    	hql.append(" and u.user_type='"+userType+"'");
		}
	    if (StringUtils.isNotBlank(userName)) {
	    	hql.append(" and d.name like '%"+userName+"%'");
		}
	    hql.append(" ORDER BY u.create_time ");
	    System.out.println(hql.toString());
		PageBean pb = new PageBean();
		if(StringUtils.isNotBlank(pageNo)){
			pb.setPage(Integer.parseInt(pageNo));
		}else {
			pb.setPage(1);
		}
		if(StringUtils.isNotBlank(pageSize)){
			pb.setRp(Integer.parseInt(pageSize));
		}else {
			pb.setRp(6);
		}
		
		RowMapper rmp = new RowMapper(){
            @Override
            public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
                    Map<String,Object> map =new HashMap<String,Object>();
                    map.put("user_id", rs.getString("user_id"));
                    map.put("user_account", rs.getString("user_account"));
                    map.put("user_name", rs.getString("name"));
                    map.put("user_mobile", rs.getString("mobile"));
                    return map;
            }
	    };
	    DataGridPage dbp =this.pagedQuery(hql.toString() ,pb,rmp);
	    dbp.setPage(pb.getPage());
	    dbp.setPageSize(pb.getRp());
	    if (0 >= dbp.getTotal()) {
	            dbp.setRows(new ArrayList());
	    }
	    
	    return dbp;
	}
	
	
	
	public List<AuthorizeAreaVo> getProCityAreaTownByUserAccount(String account) {
		StringBuffer sql=new StringBuffer()
				.append(" SELECT a.province_id,a.province_name,a.city_id,a.city_name,a.area_id,a.area_name ")
				.append(" FROM user u,authorize_area a WHERE u.user_id=a.user_id ")
				.append(" AND u.user_account='").append(account).append("'");
		List<AuthorizeAreaVo> list = jdbcTemplate.query(sql.toString(), new RowMapper<AuthorizeAreaVo>() {
			@Override
			public AuthorizeAreaVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				AuthorizeAreaVo vo=new AuthorizeAreaVo();
				vo.setProvinceId(rs.getString("province_id"));
				vo.setProvinceName(rs.getString("province_name"));
				vo.setCityId(rs.getString("city_id"));
				vo.setCityName(rs.getString("city_name"));
				vo.setAreaId(rs.getString("area_id"));
				vo.setAreaName(rs.getString("area_name"));
				return vo;
			}
		});
		return list;
	}

	public List<Map<String, String>> getUserByAuthorizeArea(String areaId, String userType) {
		StringBuilder hql = new StringBuilder("SELECT u.*,d.name as name from authorize_area a,user u,user_detail d WHERE area_id = "+areaId+" AND a.user_id = u.user_id AND d.user_detail_id = u.user_detail_id and u.dr = '0'");
	    if(StringUtils.isNotBlank(userType)){
	    	hql.append(" and u.user_type='"+userType+"'");
		}
	    hql.append(" group by user_id ");
	    System.out.println(hql.toString());
		
		RowMapper rmp = new RowMapper(){
            @Override
            public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
                    Map<String,Object> map =new HashMap<String,Object>();
                    map.put("user_id", rs.getString("user_id"));
                    map.put("user_account", rs.getString("user_account"));
                    map.put("user_name", rs.getString("name"));
                    map.put("user_mobile", rs.getString("mobile"));
                    return map;
            }
	    };
	    
	    return this.jdbcTemplate.query(hql.toString(), rmp);
		
	}
	
	/**
	 *  获取某个责任区域内的所有运维人员账号
	 * @param provinceId 省
	 * @param cityId 市
	 * @param areaId 区域
	 */
	public List<String> getUserIdsByAuthorizeArea(String provinceId,String cityId,String countryId){
		
		StringBuilder sql = new StringBuilder("SELECT DISTINCT user_id from authorize_area where 1=1 ");
		
		List<Object> values = new ArrayList<Object>();

		if(FuzzyQueryUtils.isCondition(provinceId)){
			sql.append(" and province_id=?" );
			values.add(provinceId);
		}
		if(FuzzyQueryUtils.isCondition(cityId)){
			sql.append(" and city_id=?" );
			values.add(cityId);
		}
		if(FuzzyQueryUtils.isCondition(countryId)){
			sql.append(" and area_id=?" );
			values.add(countryId);
		}
		
		List<String> userIds = jdbcTemplate.queryForList(sql.toString(),values.toArray(),String.class);
		
		return userIds;
	}

	/**
	 * 根据用户id和查询的字段值获取责任区域
	 * @param userId
	 * @param field
	 * @param filedValue
	 * @return
	 */
	public List<AuthorizeArea> getAuthorizeAreasByUserIdAndField(String userId,String field, String filedValue,String groupField) {
		StringBuffer hql = new StringBuffer(" FROM AuthorizeArea where 1=1 ");
		if(StringUtils.isNotBlank(userId)){
			hql.append(" and userId = '").append(userId).append("'");
		}
		if(StringUtils.isNotBlank(filedValue)){
			hql.append(" and ").append(field).append(" = '").append(filedValue).append("'");
		}
		if(StringUtils.isNotBlank(groupField)){
			hql.append(" group by ").append(groupField);
		}
		
		System.out.println(hql.toString());
		
		return this.find(hql.toString());
	}
	
	/**
	 * 通过区域集合，获取对应的用户集合
	 * @param areaId
	 * @param userType
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UserDto> getUsersByAuthorizeAreas(List<String> areaIds, String userType) {
		String areaSqlId = StringUtil.sqlAssembling(areaIds);
		StringBuilder hql = new StringBuilder("SELECT u.*,d.name as name from authorize_area a,user u,user_detail d WHERE area_id in("+areaSqlId+") AND a.user_id = u.user_id AND d.user_detail_id = u.user_detail_id and u.dr = '0'");
	    if(StringUtils.isNotBlank(userType)){
	    	hql.append(" and u.user_type='"+userType+"'");
		}
	    hql.append(" ORDER BY u.create_time ");
		
		RowMapper rmp = new RowMapper(){
            @Override
            public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
            	UserDto userDto = new UserDto();
            	userDto.setUserId(rs.getString("user_id"));
            	userDto.setUserAccount(rs.getString("user_account"));
            	userDto.setName(rs.getString("name"));
            	userDto.setMobile(rs.getString("mobile"));
                return userDto;
            }
	    };
	    
	    return this.jdbcTemplate.query(hql.toString(), rmp);
		
	}
}
