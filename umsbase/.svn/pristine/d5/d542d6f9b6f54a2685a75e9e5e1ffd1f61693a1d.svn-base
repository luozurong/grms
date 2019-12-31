package com.hori.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.hori.db.HibernateBaseDao;
import com.hori.model.Auth;
import com.hori.vo.AuthVo;
import com.hori.vo.RoleVo;
import com.hori.vo.UserVo;

@Repository
public class AuthDao extends HibernateBaseDao<Auth>{
	/**
	 * 获得权限treegrid
	 */
	public List<Auth> getListFortreegrid(AuthVo authVo) {
		List<Auth> auths;
		if (authVo != null && authVo.getId() != null) {
			auths = this.find("from Auth t where t.ctype='02' and t.auth.id = ? order by t.cseq", authVo.getId());
		} else {
			auths = this.find("from Auth t where t.ctype='02' and  t.auth is null order by t.cseq");
		}
		return auths;
	}

	public Long countChildren(String pid) {
		return this.count("select count(*) from Auth t where t.ctype='02' and  t.auth.id = ?", pid);
	}

	
	public void delTroletauth(Auth t) {
		this.executeHql("delete Troletauth t where t.tauth = ?", t);
	}

	public List<Auth> getListFortree(AuthVo authVo, boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from Auth t where t.auth is null order by t.cseq";
		if (authVo != null && authVo.getId() != null && !authVo.getId().trim().equals("")) {
			hql = "from Auth t where t.auth.id = ? order by t.cseq";
			param.add(authVo.getId());
		}
		List<Auth> l = this.find(hql, param);
		return l;
	}
	
	
	
	/**
	 * 根据请求参数,去权限表查询看是否存在配置
	 * @param auth
	 * @return
	 */
	public boolean findAuthExist(String curl) {
		String hql ="select count(*) from Auth t where t.curl = ?";
		Long l = this.count(hql, curl);
		if(l==null||l.intValue()==0){
			return false;
		}
		return true;
	}

	/**
	 * 追加参数：权限类型
	 */
	public List<Auth> getListForTreeByCtype(AuthVo authVo, boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from Auth t where t.ctype = ? and t.auth is null order by t.cseq";
		param.add(authVo.getCtype());
		if (authVo != null && authVo.getId() != null && !authVo.getId().trim().equals("")) {
			hql = "from Auth t where t.ctype = ? and  t.auth.id = ? order by t.cseq";
			param.add(authVo.getId());
		}
		List<Auth> l = this.find(hql, param);
		return l;
	}

	public List<Auth> getListOrder(){
		List<Auth> auths = this.find("from Auth order by cseq asc");
		return auths;
	}
	
	/**
	 * 获取一级菜单
	 * @return
	 */
	public List<Auth> getTopMenu(){
		List<Auth> auths = this.find("from Auth WHERE 1=1 AND CPID = '0'  order by cseq asc");
		return auths;
	}
	
	public List<AuthVo> getOneLevelMenu(String roleId){
		/*SELECT a.`CNAME`,a.`CURL`,a.`CPID` FROM role r INNER JOIN role_auth ra ON r.`id`=ra.`role_id` LEFT JOIN auth a ON a.`ID` = ra.`auth_id`
		   WHERE 1=1 AND r.`id` = '148653882654c8c461712fd0454cb3d1';*/

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.`ID`, a.`CNAME`,a.`CURL`,a.`CPID`,a.`CICONCLS` FROM role r ");
		sql.append(" INNER JOIN role_auth ra ON r.`role_id`=ra.`role_id`  ");
		sql.append(" LEFT JOIN auth a ON a.`ID` = ra.`auth_id` WHERE 1=1  ");
		if(StringUtils.isNotBlank(roleId)){
			sql.append(" AND r.`role_id` = '"+roleId+"' ");
		}
		sql.append(" ORDER BY a.`CSEQ` ");
		
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				AuthVo authVo =new AuthVo();
				authVo.setId(rs.getString("ID"));
				authVo.setCpid(rs.getString("CPID"));
				authVo.setCname(rs.getString("CNAME"));
				authVo.setCurl(rs.getString("CURL"));
				authVo.setCiconCls(rs.getString("CICONCLS"));
				return authVo;
			}
		};
		List<AuthVo> list = this.getJdbcTemplate().query(sql.toString(), rmp);
		if(null!=list&&list.size()>0){
			return list;
		}
		return new ArrayList<AuthVo>();
	}
	
	public List<Auth> getAuthByPid(String pid) {
		return  this.find("from Auth t where 1=1 and t.auth.id = ? order by t.cseq", pid);
	}
}
