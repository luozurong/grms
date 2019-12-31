package com.hori.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hori.dao.queryBean.AreaQueryBean;
import com.hori.dao.queryBean.UserDetailQueryBean;
import com.hori.db.HibernateBaseDao;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.model.Area;
import com.hori.model.Role;
import com.hori.model.UserDetail;
import com.hori.vo.AreaManagementVo;
import com.hori.vo.SelectVo;
@Repository
public class UserDetailDao extends HibernateBaseDao<UserDetail>{
	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(UserDetailDao.class);
	/**
	 * 分页查询
	 */
	public DataGridPage findUserDetailPage(UserDetailQueryBean queryBean,String departIds,String userType){
		StringBuilder hql = new StringBuilder();
		List<Object> params=new ArrayList<Object>();
		hql.append(" from UserDetail  ");
	    hql.append(" where  1=1 ");
		hql.append(" and departId in  ("+departIds+") ");

	    if(queryBean.getCreateTimeBegin()!=null){
			hql.append(" and createTime>= ?");
			params.add(queryBean.getCreateTimeBegin());
		}
	    if(queryBean.getCreateTimeEnd()!=null){
			hql.append(" and createTime<= ?");
			params.add(queryBean.getCreateTimeEnd());
		}
	    if(StringUtils.isNotBlank(queryBean.getDepartId())){
			hql.append(" and departId= ? ");
			params.add(queryBean.getDepartId());
		}
	    if(StringUtils.isNotBlank(queryBean.getName())){
			hql.append(" and name like ?");
			params.add("%"+queryBean.getName()+"%");
		}
	    if(StringUtils.isNotBlank(queryBean.getMobile())){
			hql.append(" and mobile like ? ");
			params.add("%"+queryBean.getMobile()+"%");
		}
	    if(StringUtils.isNotBlank(queryBean.getPost())){
			hql.append(" and post like ?");
			params.add("%"+queryBean.getPost()+"%");
		}
		PageBean pb = new PageBean();
		pb.setPage(queryBean.getPageNumber());
		pb.setRp(queryBean.getPageSize());
		Object[] v=new Object[params.size()];
		v=params.toArray(v);
		DataGridPage dbp =this.pagedQuery(hql.toString() ,pb,v);
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		
		return dbp;
	}
	
	public List<UserDetail> getUserDetailById(String departId) {
		String hql = "FROM UserDetail where departId= ?";
		List<Object> param = new ArrayList<Object>();
		param.add(departId);
		return this.find(hql,param);
	}
	
	public UserDetail getUserDetailByOne(String userDetailId) {
		String hql = " FROM UserDetail where userDetailId= ?";
		List<Object> param = new ArrayList<Object>();
		param.add(userDetailId);
		List<UserDetail> userDetailList=this.find(hql,param);
		if(userDetailList.size()>0){
			return userDetailList.get(0);
		}
		return null;
	}
	/**
	 * 检查手机号码是否唯一
	 * @return
	 */
	public boolean isExistMobile(String mobile,String userDetailId) {
		StringBuilder hql = new StringBuilder("from UserDetail WHERE 1=1  ");
		List<Object> conditionsValue = new ArrayList<Object>();

		if(StringUtils.isNotBlank(mobile)){
			hql.append(" AND mobile = ?");
			conditionsValue.add(mobile);
		}
		if(StringUtils.isNotBlank(userDetailId)){
			hql.append(" AND userDetailId != ?");
			conditionsValue.add(userDetailId);
		}
		List<UserDetail> list = this.find(hql.toString(),conditionsValue);
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param parentId
	 * @return
	 */
	public boolean isExistUserDtail(String orgaId) {
		StringBuilder hql = new StringBuilder("from UserDetail WHERE 1=1  ");
		List<Object> conditionsValue = new ArrayList<Object>();

		hql.append(" AND departId = ?");
		conditionsValue.add(orgaId);
	
		List<UserDetail> list = this.find(hql.toString(),conditionsValue);
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 查找对应类
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> findUserDetailOne(String systemId,String userAccount){
		StringBuilder sql = new StringBuilder();
        sql.append(" select a.user_account,b.role_name,c.nickname,c.`name`,c.email,c.user_detail_id from user a,user_role b,user_detail c ");
        sql.append(" where (a.user_account='"+userAccount+"' or a.mobile='"+userAccount+"')  and a.user_id=b.user_id and b.system_id='"+systemId+"' and c.user_detail_id=a.user_detail_id");
		System.out.println(sql);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("userAccount", rs.getString("user_account"));
				map.put("nickname", rs.getString("nickname"));
				map.put("name", rs.getString("name"));
				map.put("email", rs.getString("email"));
				map.put("roleName", rs.getString("role_name"));
				map.put("userDetailId", rs.getString("user_detail_id"));

				return map;
			}
		};
		
		List voList = super.getJdbcTemplate().query(sql.toString(),rmp);
		if(null != voList && 0 < voList.size()){
			return  voList;
		}
		
		return null;
	}
	
	/**
	 * 根据机构Id返回对应人员
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> findUserDetailByDepart(String departmentId){
		StringBuilder sql = new StringBuilder();
		List<Object> params=new ArrayList<Object>();
        sql.append(" select a.`name`,a.mobile from user_detail a where a.depart_id=?");
		System.out.println(sql);
		params.add(departmentId);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("name", rs.getString("name"));
				map.put("mobile", rs.getString("mobile"));
				return map;
			}
		};
		Object[] v=new Object[params.size()];
		v=params.toArray(v);
		List voList = super.getJdbcTemplate().query(sql.toString(),rmp,v);
		if(null != voList && 0 < voList.size()){
			return  voList;
		}
		
		return null;
	}
	
	/**
	 * 根据用户资料Id修改用户资料
	 * @param 
	 * @return
	 */
	public void updateUserDetail(String userDetailId,String nickName,String email,String name){
		String hql = " update UserDetail set  name=? ,nickname=? ,email=? WHERE  userDetailId=? ";
		Object[] params = new Object[] { name,nickName,email,userDetailId};
		this.executeUpdate(hql,params);
		
	}
	
	/**
	 * 修改头像地址
	 * @param 
	 * @return
	 */
	public void updateUserDetailPhoto(String userAccount,String address){
		String hql = " update UserDetail set  address=?  WHERE  userDetailId=(select userDetailId from User where userAccount =?) ";
		Object[] params = new Object[] { address,userAccount};
		this.executeUpdate(hql,params);
		
	}
	
	/**
	 * 查找出每个部门的人数
	 * @param 
	 * @return
	 */
	public List<Map<String,Object>> findUserDetailCount(String systemId){
		StringBuilder sql = new StringBuilder();
		List<Object> params=new ArrayList<Object>();
        sql.append(" select a.depart_id,count(*) as number from user_detail a ,department b where  b.system_id=? and a.depart_id=b.depart_id GROUP BY a.depart_id");
		System.out.println(sql);
		params.add(systemId);
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("departId", rs.getString("depart_id"));
				map.put("number", rs.getString("number"));
				return map;
			}
		};
		Object[] v=new Object[params.size()];
		v=params.toArray(v);
		List voList = super.getJdbcTemplate().query(sql.toString(),rmp,v);
		if(null != voList && 0 < voList.size()){
			return  voList;
		}
		
		return null;
	}

}
