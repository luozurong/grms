package com.hori.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hori.dao.queryBean.UserManagementQueryBean;
import com.hori.dao.queryBean.UserQueryBean;
import com.hori.db.HibernateBaseDao;
import com.hori.db.exception.DataQueryFailException;
import com.hori.db.exception.DatabaseConnectException;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.enums.UserType;
import com.hori.model.User;
import com.hori.ums.webservice.bean.UserDto;
import com.hori.util.StringUtil;
import com.hori.utils.FuzzyQueryUtils;
import com.hori.vo.SelectVo;
import com.hori.vo.UserDetailVo;
import com.hori.vo.UserInfoDto;
import com.hori.vo.UserManagementVo;



@Repository
public class UserDao extends HibernateBaseDao<User> {
	
	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(UserDao.class);

	public List<User> listAll(){
	    String hql = "from User t WHERE t.status = 1";
	    List<User> list = this.find(hql);
	    if(null!=list&&list.size()>0){
	    	 return list;
	    }
	    return new ArrayList<User>();
	}
	
	public List<User> getUserByUserType(String userType){
	    StringBuilder hql = new StringBuilder("from User t WHERE 1=1 AND t.status = 1 ");
	    if(StringUtils.isNotBlank(userType)){
	    	hql.append(" AND t.userType = '"+userType+"' ");
	    }
	    List<User> list = this.find(hql.toString());
	    if(null!=list&&list.size()>0){
	    	 return list;
	    }
	    return new ArrayList<User>();
	}
	//
	public List<UserDto> getUserByDepartId(String areaId,String departId){
	
	    if(StringUtils.isEmpty(areaId)||StringUtils.isEmpty(departId)){
			return null;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select  t.user_id,t.user_account   ");
		sql.append("FROM user t where  t.status = 1  ");
		sql.append(" AND t.user_type = '2' ");
		sql.append(" AND t.user_detail_id in   (select d.user_detail_id from user_detail d where d.depart_id in ");
		sql.append("  ( select depart_id from department where FIND_IN_SET(depart_id, queryChildrenAreaInfo('"+departId+"')) and  depart_id!='"+departId+"'))   ");
		sql.append(" AND t.user_id in (select a.user_id from authorize_area a where  a.area_id='"+areaId+"');");
		
		
		RowMapper<UserDto> rmp = new RowMapper<UserDto>(){
			@Override
			public UserDto mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				UserDto userVo =new UserDto();
				userVo.setUserId(rs.getString("user_id"));
				userVo.setUserAccount(rs.getString("user_account"));
				return userVo;
			}
		};
		List<UserDto> vos = getJdbcTemplate().query(sql.toString(),new Object[]{},rmp);
	    if(null!=vos&&vos.size()>0){
	    	 return vos;
	    }
	    return vos;
	}
	/**
	 * 用户登录
	 */
	public User login(String userAccount,String password) {
		List<Object> conditionsValue = new ArrayList<Object>();
	    conditionsValue.add(userAccount);
		conditionsValue.add(password);
		User u = this.get("from User u where 1=1 and u.userAccount=?  and u.password=? ",conditionsValue.toArray());
		return u ;
	}

	
	public Long total(UserQueryBean queryBean) {
		StringBuilder hql = new StringBuilder("select count(*) from User t where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		if(FuzzyQueryUtils.isCondition(queryBean.getForwardParams())){
			hql.append("AND ( t.userAccount like ? or t.phone like ? ) ");
			values.add(FuzzyQueryUtils
					.fuzzyQueryCondition(queryBean.getForwardParams().trim()));
			values.add(FuzzyQueryUtils
					.fuzzyQueryCondition(queryBean.getForwardParams().trim()));
		}
		return this.count(hql.toString(), values);
	}

	public User getUserByAccount(String userAccount){
		String hql = "from User t  WHERE 1=1 AND t.dr='0' AND (t.userAccount = ? or t.mobile = ? )";
		List<Object> values = new ArrayList<Object>();
		values.add(userAccount);
		values.add(userAccount);
	    List<User> list = this.find(hql, values);
	    if(null!=list&&list.size()>0){
	    	 return list.get(0);
	    }
	    return null;
	}
	
	public User getUserByUserId(String userId){
		String hql = "from User t  WHERE 1=1 AND t.dr='0' AND t.userId= ?";
		List<Object> values = new ArrayList<Object>();
		values.add(userId);
	    List<User> list = this.find(hql, values);
	    if(null!=list&&list.size()>0){
	    	 return list.get(0);
	    }
	    return null;
	}
	
	public List<User> getSYSAndADSSYS(){
		String hql = "from User u where 1=1 AND u.userType in ('"+UserType.GGGLY.getValue()+"','"+UserType.XTCJGLY.getValue()+"') ";
		List<User> userList = this.find(hql);
		if(userList!=null&&userList.size()>0){
			return userList;
		}
		return new ArrayList<User>();
	}
	//分页查询
	public DataGridPage findUserManagementPage(UserManagementQueryBean queryBean,String userType,byte dataArea,List<String> userList){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.user_id,a.data_area,c.role_id,a.create_time,a.user_account,b.`name`,a.mobile,c.role_name,c.role_type,b.depart_name,b.depart_id,a.user_detail_id ");
		sql.append(" from `user` a,user_detail b,user_role c  ");
	    sql.append(" where a.user_detail_id=b.user_detail_id and   c.user_id=a.user_id  ");
	    sql.append("     and c.system_id='"+queryBean.getSystemId().trim()+"' and a.dr='0' ");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    if(queryBean.getCreateTimeBegin()!=null&&!queryBean.getCreateTimeBegin().equals("")){
			sql.append(" and a.create_time >='"+sdf.format(queryBean.getCreateTimeBegin())+"'");
		}
	    if(queryBean.getCreateTimeEnd()!=null&&!queryBean.getCreateTimeEnd().equals("")){
			sql.append(" and a.create_time <='"+sdf.format(queryBean.getCreateTimeEnd())+"'");
		}
	    if(StringUtils.isNotBlank(queryBean.getUserAccount())){
			sql.append(" and a.user_account  like '%"+queryBean.getUserAccount().trim()+"%'");
		}
	    if(StringUtils.isNotBlank(queryBean.getName())){
			sql.append(" and b.`name` like '%"+queryBean.getName().trim()+"%'");
		}
	    if(StringUtils.isNotBlank(queryBean.getMobile())){
			sql.append(" and b.mobile like '%"+queryBean.getMobile().trim()+"%'");
		}
	    if(StringUtils.isNotBlank(queryBean.getRoleName())){
			sql.append(" and  c.role_name like '%"+queryBean.getRoleName().trim()+"%'");
		}
	    if(StringUtils.isNotBlank(queryBean.getDepartId())){
			sql.append(" and  b.depart_id='"+queryBean.getDepartId().trim()+"'");
		}
	    //按照数据域返回数据
	    if(userType.equals("0")||dataArea==2){
	    	
	    }else{
	    	String userAccountIds="";
			for(int i=0;i<userList.size();i++){
				    if(i==0){
				    	userAccountIds="'"+userList.get(i)+"'";
				    }else{
				    	userAccountIds=userAccountIds+",'"+userList.get(i)+"'";
				    }
			}
	    	sql.append(" and a.user_account in (" +userAccountIds+")");
	    }
		PageBean pb = new PageBean();
		pb.setPage(queryBean.getPageNumber());
		pb.setRp(queryBean.getPageSize());
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				UserManagementVo userManagementVo =new UserManagementVo();
				userManagementVo.setUserAccount(rs.getString("user_account"));
				userManagementVo.setDepartId(rs.getString("depart_id"));
				userManagementVo.setMobile(rs.getString("mobile"));
				userManagementVo.setRoleName(rs.getString("role_name"));
				userManagementVo.setRoleType(rs.getByte("role_type"));
				userManagementVo.setDataArea(rs.getByte("data_area"));
				userManagementVo.setName(rs.getString("name"));
				userManagementVo.setCreateTime(rs.getTimestamp("create_time"));
				userManagementVo.setDepartName(rs.getString("depart_name"));
				userManagementVo.setUserDetailId(rs.getString("user_detail_id"));
				userManagementVo.setRoleId(rs.getString("role_id"));
				userManagementVo.setUserId(rs.getString("user_id"));
				return userManagementVo;
			}
		};
		DataGridPage dbp =this.pagedQuery(sql.toString() ,pb,rmp);
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		
		return dbp;
	}
	//当前帐号的下级帐号返回
	public static String returnSqlForAccount(String departId){
		StringBuilder sql = new StringBuilder();
        sql.append(" select depart_id from department where FIND_IN_SET(depart_id, queryChildrenAreaInfo('"+departId+"'))");	
        sql.append(" and  depart_id!='"+departId+"'");
        return sql.toString();
		
	}
	public DataGridPage queryUsers(UserQueryBean queryBean){
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT u.user_id,u.user_account,ud.mobile,ud.name,ud.depart_name,ur.role_name  ");
		sql.append(" FROM user u ");
		
		sql.append(" join user_detail ud on ud.user_detail_id=u.user_detail_id ");
		sql.append(" join user_role ur on ur.user_id=u.user_id ");
		
		List<String> values=new ArrayList<String>();
		
		if(queryBean.getUserIds()==null){
			sql.append(" join (SELECT DISTINCT user_id from authorize_area) a on a.user_id=u.user_id ");
			
		}
		
		sql.append(" WHERE 1=1 ");
		
		if(queryBean.getUserIds()!=null && queryBean.getUserIds().size()>0){
			String ids = FuzzyQueryUtils.getIds(queryBean.getUserIds());
			sql.append(" and u.user_id in ("+ids+")");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getUserAccount())){
			sql.append(" and u.user_account like ? ");
			values.add("%"+queryBean.getUserAccount()+"%");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getUserName())){
			sql.append(" and ud.name like ? ");
			values.add("%"+queryBean.getUserName()+"%");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getMobile())){
			sql.append(" and ud.mobile like ? ");
			values.add("%"+queryBean.getMobile()+"%");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getDepartment())){
			sql.append(" and ud.depart_name like ? ");
			values.add("%"+queryBean.getDepartment()+"%");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getRole())){
			sql.append(" and ur.role_name like ? ");
			values.add("%"+queryBean.getRole()+"%");
		}
		
		sql.append(" ORDER BY u.create_time ");
		
		//UserDetailVo
		
		PageBean pb = new PageBean();
		pb.setPage(queryBean.getPageNumber());
		pb.setRp(queryBean.getPageSize());
		RowMapper<UserDetailVo> rmp = new RowMapper<UserDetailVo>(){
			@Override
			public UserDetailVo mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				UserDetailVo userVo =new UserDetailVo();
				
				userVo.setUserId(rs.getString("user_id"));
				userVo.setName(rs.getString("name"));
				userVo.setUserAccount(rs.getString("user_account"));
				userVo.setMobile(rs.getString("mobile"));
				userVo.setDepartment(rs.getString("depart_name"));
				userVo.setRole(rs.getString("role_name"));
				
				return userVo;
			}
		};
		DataGridPage dbp =  null;
		
		if(values.size()>0){ 
			dbp=this.pagedQueryForJdbc(sql.toString() ,pb,rmp,values.toArray());
		}else{ 
			dbp=this.pagedQueryForJdbc(sql.toString() ,pb,rmp);
		} 
		
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList<UserDetailVo>());
		}
		 
		return dbp;
	}
	
	public List<UserDetailVo> getUsersByIds(String[] userIds) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT u.user_id,u.user_account,ud.mobile,ud.name,ud.depart_name,ur.role_name  ");
		sql.append(" FROM user u ");
		
		sql.append(" join user_detail ud on ud.user_detail_id=u.user_detail_id ");
		sql.append(" join user_role ur on ur.user_id=u.user_id ");
		sql.append(" WHERE 1=1 ");
		
		if(userIds!=null && userIds.length >0){
			String ids = FuzzyQueryUtils.getIds(userIds);
			sql.append(" and u.user_id in ("+ids+")");
		}
		
		sql.append(" ORDER BY u.create_time ");
		
		
		RowMapper<UserDetailVo> rmp = new RowMapper<UserDetailVo>(){
			@Override
			public UserDetailVo mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				UserDetailVo userVo =new UserDetailVo();
				
				userVo.setUserId(rs.getString("user_id"));
				userVo.setName(rs.getString("name"));
				userVo.setUserAccount(rs.getString("user_account"));
				userVo.setMobile(rs.getString("mobile"));
				userVo.setDepartment(rs.getString("depart_name"));
				userVo.setRole(rs.getString("role_name"));
				
				return userVo;
			}
		};
		
		List<UserDetailVo> vos = getJdbcTemplate().query(sql.toString(),rmp);
		
		if(vos==null){
			vos=new ArrayList<UserDetailVo>();
		}
		return vos;
	}
	
	public UserDetailVo getUserById(String userId) {
		
		if(StringUtils.isEmpty(userId)){
			return null;
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT u.user_id,u.user_account,ud.mobile,ud.name,ud.depart_name,ur.role_name  ");
		sql.append(" FROM user u ");
		
		sql.append(" join user_detail ud on ud.user_detail_id=u.user_detail_id ");
		sql.append(" join user_role ur on ur.user_id=u.user_id ");
		sql.append(" WHERE  u.user_id=? and u.dr='0' ");
		
		
		RowMapper<UserDetailVo> rmp = new RowMapper<UserDetailVo>(){
			@Override
			public UserDetailVo mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				UserDetailVo userVo =new UserDetailVo();
				
				userVo.setUserId(rs.getString("user_id"));
				userVo.setName(rs.getString("name"));
				userVo.setUserAccount(rs.getString("user_account"));
				userVo.setMobile(rs.getString("mobile"));
				userVo.setDepartment(rs.getString("depart_name"));
				userVo.setRole(rs.getString("role_name"));
				
				return userVo;
			}
		};
		
		List<UserDetailVo> vos = getJdbcTemplate().query(sql.toString(),new Object[]{userId},rmp);
		
		if(vos==null||vos.size()==0){
			return null;
		}
		
		UserDetailVo userDetailVo = vos.get(0);
		
		List<String> areaNames = getJdbcTemplate().queryForList(" SELECT area_name from authorize_area where user_id=? ",
				new Object[]{userId},String.class);
		
		userDetailVo.setCountrys(areaNames);
		
		return userDetailVo;
	}

	//当前系统账号及下级账号创建的角色
	public List<SelectVo> findRoleByAccount(String systemId,String userAccountIds,byte dataArea){
 		List<SelectVo> selectVoList=new ArrayList<SelectVo>();
    	StringBuilder sql = new StringBuilder();
        sql.append("select role_id as id,role_name as text from role where  system_id='"+systemId+"' ");
        if(dataArea!=2){
        	sql.append(	" and create_account in ("+userAccountIds+")");
        }
        List list= this.createNavtiveSQLQuery(sql.toString());
      for(int i=0;i<list.size();i++){
    	  	Object[] obj = (Object[]) list.get(i);
        	SelectVo selectVo=new SelectVo();
        	selectVo.setId(obj[0].toString());
        	selectVo.setText(obj[1].toString());
        	selectVoList.add(selectVo);
        }
        return selectVoList;
        
	}
	/**
	 * 当前系统账号的下级账号
	 * @param 
	 */
	public List<Map<String, String>> getUserAccountNext(String departId) {
    	StringBuilder sql = new StringBuilder();
	    sql.append("	select a.user_account from `user` a ,user_detail b ");
        sql.append("				where a.user_detail_id=b.user_detail_id and a.dr='0' and b.depart_id in ");
        sql.append("			(select depart_id from department where FIND_IN_SET(depart_id, queryChildrenAreaInfo('"+departId+"'))");	
        sql.append(" and  depart_id!='"+departId+"')");
        System.out.println(sql);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("userAccount", rs.getString("user_account"));
				return map;
			}
		};
		List<Map<String, String>> voList = super.getJdbcTemplate().query(sql.toString() ,rmp);
		return voList;

	}

	public List<String> getUserIds(UserQueryBean queryBean) {
		
		List<String> values=new ArrayList<String>();

		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT u.user_id ");
		sql.append(" FROM user u ");
		
		sql.append(" join user_detail ud on ud.user_detail_id=u.user_detail_id ");
		sql.append(" join user_role ur on ur.user_id=u.user_id ");
		
		if(queryBean.getUserIds()==null){
			sql.append(" join (SELECT DISTINCT user_id from authorize_area) a on a.user_id=u.user_id ");
		}
		
		sql.append(" WHERE 1=1 ");
		
		if(queryBean.getUserIds()!=null && queryBean.getUserIds().size()>0){
			String ids = FuzzyQueryUtils.getIds(queryBean.getUserIds());
			sql.append(" and u.user_id in ("+ids+")");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getUserAccount())){
			sql.append(" and u.user_account like ? ");
			values.add("%"+queryBean.getUserAccount()+"%");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getUserName())){
			sql.append(" and ud.name like ? ");
			values.add("%"+queryBean.getUserName()+"%");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getMobile())){
			sql.append(" and ud.mobile like ? ");
			values.add("%"+queryBean.getMobile()+"%");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getDepartment())){
			sql.append(" and ud.depart_name like ? ");
			values.add("%"+queryBean.getDepartment()+"%");
		}
		if(FuzzyQueryUtils.isCondition(queryBean.getRole())){
			sql.append(" and ur.role_name like ? ");
			values.add("%"+queryBean.getRole()+"%");
		}
		
		List<String> ids = getJdbcTemplate().queryForList(sql.toString(), values.toArray(),String.class);
		 
		
		return ids;
	}

	public UserDetailVo getUserByMobile(String mobile) {
		
		if(StringUtils.isEmpty(mobile)){
			return null;
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT u.user_id,u.user_account,ud.mobile,ud.name,ud.depart_name,ur.role_name,u.password  ");
		sql.append(" FROM user u ");
		
		sql.append(" join user_detail ud on ud.user_detail_id=u.user_detail_id ");
		sql.append(" join user_role ur on ur.user_id=u.user_id ");
		sql.append(" WHERE  ud.mobile=? ");
		
		
		RowMapper<UserDetailVo> rmp = new RowMapper<UserDetailVo>(){
			@Override
			public UserDetailVo mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				UserDetailVo userVo =new UserDetailVo();
				
				userVo.setUserId(rs.getString("user_id"));
				userVo.setName(rs.getString("name"));
				userVo.setUserAccount(rs.getString("user_account"));
				userVo.setMobile(rs.getString("mobile"));
				userVo.setDepartment(rs.getString("depart_name"));
				userVo.setRole(rs.getString("role_name"));
				userVo.setPassword(rs.getString("password"));
				return userVo;
			}
		};
		
		List<UserDetailVo> vos = getJdbcTemplate().query(sql.toString(),new Object[]{mobile},rmp);
		
		if(vos==null||vos.size()==0){
			return null;
		}
		
		UserDetailVo userDetailVo = vos.get(0);
		
/*		List<String> areaNames = jdbcTemplate.queryForList("SELECT area_name from authorize_area where user_id=? ",
				new Object[]{mobile},String.class);
		
		userDetailVo.setCountrys(areaNames);*/
		
		return userDetailVo;
	}
	//修改关联用户资料ID
	public void updateUserDetailById(String userId,byte dataArea,String userDetailId){
		 	String hql = " UPDATE FROM User SET userDetailId=?,dataArea=? WHERE userId=?  ";
			Object[] params = new Object[] { userDetailId,dataArea, userId};
			this.executeUpdate(hql,params);
	}
	
	//修改绑定手机号码
	public void updateUserMobileById(User user,String mobile){
		    //修改用户号码
			String hql = " UPDATE FROM User SET mobile=? WHERE userId=?  ";
			String[] params = new String[] { mobile, user.getUserId()};
			this.executeUpdate(hql,params);
			//修改用户资料号
			String hql2 = " UPDATE FROM UserDetail SET mobile=? WHERE userDetailId=?  ";
			String[] params2 = new String[] { mobile, user.getUserDetailId()};
			this.executeUpdate(hql2,params2);
		}
	
	
	//修改绑定手机号码
	public void updateMobileById(String userDetailId,String mobile){
		    //修改用户号码
			String hql = " UPDATE FROM User SET mobile=? WHERE userDetailId=?  ";
			String[] params = new String[] { mobile, userDetailId};
			this.executeUpdate(hql,params);
		
		}
	
	/**
	 * 通过帐号获取用户信息
	 * @param userAccount
	 * @return
	 */
	public List<Map<String,String>> findUserDetail(String systemId,String userAccount){
		StringBuilder sql = new StringBuilder();
        sql.append(" select   a.user_type,a.user_id,c.role_id,a.create_time,a.user_account,b.`name`,b.mobile,c.role_name,b.depart_name,b.depart_id,a.user_detail_id ");
		sql.append(" from `user` a,user_detail b,user_role c  ");
	    sql.append(" where a.user_detail_id=b.user_detail_id and   c.user_id=a.user_id  ");
	    sql.append(" and c.system_id='"+systemId+"' ");
		sql.append(" and (a.user_account  = '"+userAccount+"' or a.mobile = '"+userAccount+"')");

		//System.out.println(sql);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("role_name", rs.getString("role_name"));
				map.put("user_id", rs.getString("user_id"));
				map.put("mobile", rs.getString("mobile"));
				map.put("user_account", rs.getString("user_account"));
				map.put("user_name", rs.getString("name"));
				map.put("user_type", rs.getString("user_type"));
				return map;
			}
		};
		
		List voList = super.getJdbcTemplate().query(sql.toString() ,rmp);
		if(null != voList && 0 < voList.size()){
			return  voList;
		}
		
		return null;
	}
	
	public <V> DataGridPage pagedQueryForJdbc(String sql, PageBean pageBean,RowMapper<V> rmp,Object... values) {
		String countQueryString = "select count(*) from" + "(" + sql + ") as temp";
		Long total;
		try {
			//total = getJdbcTemplate().queryForLong(countQueryString,values);
			total = getJdbcTemplate().queryForObject(countQueryString, Long.class, values);
		} catch (Exception e) {
			log.info("DatabaseConnect Fail!");
			e.printStackTrace();
			throw new DatabaseConnectException();
		}
		DataGridPage dgp = new DataGridPage();
		if (total == 0) {
			return dgp;
		}
		
		try {
			log.info("-----------"+sql+"-------------");
			
			int offset = (pageBean.getPage() - 1) * pageBean.getRp();
			
			List<V> voList = getJdbcTemplate().query(sql + " limit "+offset+","+pageBean.getRp(),values,rmp);
			dgp.setRows(voList);
		} catch (Exception e) {
			log.error("PagedQuery Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("PagedQuery Fail!");
		}
		dgp.setTotal(total.intValue());
		dgp.setPage(pageBean.getPage());
		dgp.setTotalPage(total.intValue()/pageBean.getRp() + (total.intValue()%pageBean.getRp()>0?1:0));
		return dgp;
	}
	/**
	 * 通过id获取用户信息
	 * @param 
	 * @return
	 */
	public Map<String,String> findUserDetailById(String systemId,String userid){
		StringBuilder sql = new StringBuilder();
        sql.append(" select   a.user_type,a.user_id,c.role_id,a.create_time,a.user_account,b.`name`,b.mobile,c.role_name,b.depart_name,b.depart_id,a.user_detail_id ");
		sql.append(" from `user` a,user_detail b,user_role c  ");
	    sql.append(" where a.user_detail_id=b.user_detail_id and   c.user_id=a.user_id  ");
	    sql.append("     and c.system_id='"+systemId+"' ");
		sql.append(" and a.user_id  = '"+userid+"'");

		System.out.println(sql);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("user_id", rs.getString("user_id"));
				map.put("mobile", rs.getString("mobile"));
				map.put("user_account", rs.getString("user_account"));
				map.put("user_name", rs.getString("name"));
				map.put("user_type", rs.getString("user_type"));
				return map;
			}
		};
		
		List<Map<String, String>> voList = super.getJdbcTemplate().query(sql.toString() ,rmp);
		if(null != voList && 0 < voList.size()){
			return  voList.get(0);
		}
		
		return null;
	}

	public UserInfoDto getUserInfoByAccount(String account) {
		if(StringUtils.isEmpty(account)){
			return null;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT u.user_id,u.user_account,u.user_type,ud.name,ud.mobile,u.status,ud.depart_name,ur.role_name,ur.user_role_id  ");
		sql.append(" FROM user u ");
		sql.append(" join user_detail ud on ud.user_detail_id=u.user_detail_id ");
		sql.append(" join user_role ur on ur.user_id=u.user_id ");
		sql.append(" WHERE u.user_account=? limit 1 ");
		
		
		RowMapper<UserInfoDto> rmp = new RowMapper<UserInfoDto>(){
			@Override
			public UserInfoDto mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				UserInfoDto userVo =new UserInfoDto();
				userVo.setUserId(rs.getString("user_id"));
				userVo.setName(rs.getString("name"));
				userVo.setUserAccount(rs.getString("user_account"));
				userVo.setMobile(rs.getString("mobile"));
				userVo.setStatus(rs.getString("status"));
				userVo.setDepartment(rs.getString("depart_name"));
				userVo.setRoleName(rs.getString("role_name"));
				userVo.setRoleId(rs.getString("user_role_id"));
				userVo.setUserType(rs.getString("user_type"));
				return userVo;
			}
		};
		List<UserInfoDto> vos = getJdbcTemplate().query(sql.toString(),new Object[]{account},rmp);
		if(vos!=null&&vos.size()>0){
			return vos.get(0);
		}
		return null;
	}
	/**
	 * 删除当前系统帐号关联角色
	 * @param 
	 * @return
	 */
	public void deleteUserRole(String userId,String systemId){
		String hql = " Delete FROM UserRole WHERE systemId=? and userId=? ";
		String hql2=" update  User set dr='1' WHERE  userId=? ";
		Object[] params2 = new Object[] { userId};

		Object[] params = new Object[] { systemId, userId};
		this.executeUpdate(hql,params);
		this.executeUpdate(hql2,params2);

		
	}
	
	/**
	 * 修改用户类型
	 * @param 
	 * @return
	 */
	public void updateUserType(String userId,String userType){
		String hql=" update User set userType=? WHERE  userId=? ";
         byte userIdB=Byte.valueOf(userType);
		Object[] params = new Object[] { userIdB, userId};
		this.executeUpdate(hql,params);

		
	}
	/**
	 * 检查是否当前员工已经拥有帐号
	 * @return 
	 */
	public boolean isExistUserDetail(String userDetailId) {
		StringBuilder hql = new StringBuilder("from User WHERE 1=1  ");
		List<Object> conditionsValue = new ArrayList<Object>();
			hql.append(" And dr='0' AND userDetailId = ?");
			conditionsValue.add(userDetailId);

		List<User> list = this.find(hql.toString(),conditionsValue);
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 检查修改后员工是否拥有帐号
	 * @return 
	 */
	public boolean isExistUserDetail(String userDetailId,String userId) {
		StringBuilder hql = new StringBuilder("from User WHERE 1=1  ");
		List<Object> conditionsValue = new ArrayList<Object>();
			hql.append(" And dr='0' AND userDetailId = ?");
			hql.append(" AND userId != ?");
			conditionsValue.add(userDetailId);
			conditionsValue.add(userId);

		List<User> list = this.find(hql.toString(),conditionsValue);
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 通过SQL获取所有的用户
	 * @return
	 */
	public List<String> getAllUsersBySend() {
		String sql = "SELECT DISTINCT u.user_account from user u where u.user_account is not null and u.user_account != ''  ORDER BY u.create_time desc ";
		return this.getJdbcTemplate().queryForList(sql, String.class);
	}
	
	/**
	 * 通过SQL获取所有的用户，用户类型
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UserDto> getAllUsersTypeBySend() {
		StringBuilder hql = new StringBuilder("SELECT DISTINCT u.user_account,u.user_type from user u where u.user_account is not null and u.user_account != ''");
	    hql.append(" ORDER BY u.create_time desc ");
		
		RowMapper rmp = new RowMapper(){
            @Override
            public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
            	UserDto userDto = new UserDto();
            	userDto.setUserAccount(rs.getString("user_account"));
            	userDto.setUsesType(rs.getString("user_type"));
                return userDto;
            }
	    };
	    List<UserDto> list = this.getJdbcTemplate().query(hql.toString(), rmp);
	    return list;
		
	}
	
	/**
	 *获取数据域为部门数据的对应可以操作的帐号信息
	 * @param 
	 */
	public List<String> getUserAccountForDepart(String departId) {
		String sqlDept = "select depart_id from department where FIND_IN_SET(depart_id, queryChildrenAreaInfo('"+departId+"'))";
		String sql=" select a.user_account from user a,user_detail b where a.user_detail_id=b.user_detail_id  and b.depart_id in ("+sqlDept+")";
		System.out.println(sql);
		
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				return rs.getString("user_account");
			}
		};
		List<String> voList = super.getJdbcTemplate().query(sql.toString() ,rmp);
		return voList;

	}
	/**
	 * 获取数据域为个人数据的对应可以操作的帐号信息
	 * @param 
	 */
	public List<String> getUserAccountForOne(String departId,String userAccount) {
		String sqlDept = " select depart_id from department where FIND_IN_SET(depart_id, queryChildrenAreaInfo('"+departId+"')) and depart_id!='"+departId+"'";
		String sql=" select a.user_account from user a,user_detail b where a.user_detail_id=b.user_detail_id and b.depart_id in ("+sqlDept+")";
		System.out.println(sql);
		
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				return rs.getString("user_account");
			}
		};
		List<String> voList = super.getJdbcTemplate().query(sql.toString() ,rmp);
		//个人数据域还要加上自己的
		voList.add(userAccount);
		return voList;

	}
	
	/**
	 *获取数据域为部门数据的对应可以操作的帐号信息
	 * @param 
	 */
	public List<String> getDepartForAll(String departId) {
		String sql = "select depart_id from department where FIND_IN_SET(depart_id, queryChildrenAreaInfo('"+departId+"'))";
		System.out.println(sql);
		
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				return rs.getString("depart_id");
			}
		};
		List<String> voList = super.getJdbcTemplate().query(sql.toString() ,rmp);
		return voList;

	}
	/**
	 * 获取数据域为个人数据的对应可以操作的帐号信息
	 * @param 
	 */
	public List<String> getDepartForOne(String departId) {
		String sql = " select depart_id from department where FIND_IN_SET(depart_id, queryChildrenAreaInfo('"+departId+"')) and depart_id!='"+departId+"'";
		System.out.println(sql);
		
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				return rs.getString("depart_id");
			}
		};
		List<String> voList = super.getJdbcTemplate().query(sql.toString() ,rmp);
		return voList;

	}
}
