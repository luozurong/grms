package com.hori.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hori.dao.queryBean.AreaQueryBean;
import com.hori.db.HibernateBaseDao;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.model.AreaSystem;
import com.hori.model.Department;
import com.hori.vo.AreaManagementVo;
@Repository
public class AreaManagementDao extends HibernateBaseDao<AreaSystem> {
	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(AreaManagementDao.class);
	public DataGridPage findUserPage(AreaQueryBean queryBean){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.user_id,a.user_account,c.`name`,c.mobile,b.role_name,c.depart_id, c.depart_name ");
		sql.append(" from user a,user_role b,user_detail c ");
	    sql.append(" where a.user_id=b.user_id and a.system_id=b.system_id and a.user_detail_id=c.user_detail_id ");
	    if(StringUtils.isNotBlank(queryBean.getUserAccount())){
			sql.append(" and a.user_account='"+queryBean.getUserAccount().trim()+"'");
		}
	    if(StringUtils.isNotBlank(queryBean.getDepartName())){
			sql.append(" and c.depart_name='"+queryBean.getDepartName().trim()+"'");
		}
	    if(StringUtils.isNotBlank(queryBean.getDepartId())){
			sql.append(" and c.depart_id='"+queryBean.getDepartId().trim()+"'");
		}
	    if(StringUtils.isNotBlank(queryBean.getName())){
			sql.append(" and c.`name`='"+queryBean.getName().trim()+"'");
		}
	    if(StringUtils.isNotBlank(queryBean.getMobile())){
			sql.append(" and a.mobile='"+queryBean.getMobile().trim()+"'");
		}
	    if(StringUtils.isNotBlank(queryBean.getRoleName())){
			sql.append(" and  b.role_name='"+queryBean.getRoleName().trim()+"'");
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
				return areaManagementVo;
			}
		};
		DataGridPage dbp =this.pagedQuery(sql.toString() ,pb,rmp);
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		
		return dbp;
	}
	
	public List<AreaSystem> getAreaAll(String parame) {
		String hql = "FROM AreaSystem where systemId=?";
		List<Object> values = new ArrayList<Object>();
		values.add(parame);
		return this.find(hql,values);
	}
	

}
