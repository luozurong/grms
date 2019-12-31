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
import com.hori.dao.queryBean.MenuQueryBean;
import com.hori.db.HibernateBaseDao;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;
import com.hori.model.Menu;
import com.hori.vo.AreaManagementVo;
import com.hori.vo.MenuButttonVo;
@Repository
public class MenuDao extends HibernateBaseDao<Menu>{
	private static final Log log = LogFactory.getLog(MenuDao.class);
	
	public List<Menu> getMenuStart(String systemId,String menuApp) {
		//只查询启用状态的菜单
		String hql = "FROM Menu where systemId=? and menuStatus=1 and menuApp=? order by menuOrder ";
		Byte menuAppB=Byte.valueOf(menuApp);
		List<Object> values = new ArrayList<Object>();
		values.add(systemId);
		values.add(menuAppB);
		return this.find(hql,values);
	}
	
	//获取菜单的父菜单
	public List<Menu> getMenuFather(String systemId,String menuApp) {
		//只查询启用状态的菜单
		String hql = "FROM Menu where systemId=? and menuStatus=1 and (menuParentId is null or menuParentId='') and menuApp=? ";
		Byte menuAppB=Byte.valueOf(menuApp);
		List<Object> values = new ArrayList<Object>();
		values.add(systemId);
		values.add(menuAppB);
		return this.find(hql,values);
	}
	

	public DataGridPage findMenuPage(MenuQueryBean queryBean){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.menu_app,b.system_name,b.system_id,a.menu_icon,a.note,a.menuname,a.menu_parent_id,a.menu_id,a.menu_order,a.menu_url,a.menu_status,a.create_time,a.modified_time ");
		sql.append(" from menu a,system_admin b  ");
	    sql.append(" where a.system_id=b.system_id ");
	    if(StringUtils.isNotBlank(queryBean.getSystemName())){
			sql.append(" and b.system_name like '"+queryBean.getSystemName().trim()+"%'");
		}
	    if(StringUtils.isNotBlank(queryBean.getMenuname())){
			sql.append(" and  a.menuname like '"+queryBean.getMenuname().trim()+"%'");
		}
	    if(StringUtils.isNotBlank(queryBean.getMenuStatus())){
			sql.append(" and a.menu_status='"+queryBean.getMenuStatus().trim()+"'");
		}

		sql.append(" ORDER BY a.`create_time` ");
		PageBean pb = new PageBean();
		pb.setPage(queryBean.getPageNumber());
		pb.setRp(queryBean.getPageSize());
		RowMapper rmp = new RowMapper(){
			@Override
			public Object mapRow(java.sql.ResultSet rs, int arg1) throws SQLException {
				MenuButttonVo menuButttonVo =new MenuButttonVo();
				menuButttonVo.setSystemId(rs.getString("system_id"));
				menuButttonVo.setSystemName(rs.getString("system_name"));
				menuButttonVo.setMenuId(rs.getString("menu_id"));
				menuButttonVo.setMenuParentId(rs.getString("menu_parent_id"));
				menuButttonVo.setMenuIcon(rs.getString("menu_icon"));
				menuButttonVo.setNote(rs.getString("note"));
				menuButttonVo.setMenuname(rs.getString("menuname"));
				menuButttonVo.setModifiedTime(rs.getTimestamp("modified_time"));
				menuButttonVo.setCreateTime(rs.getTimestamp("create_time"));
				menuButttonVo.setMenuStatus(rs.getByte("menu_status"));
				menuButttonVo.setMenuOrder(rs.getByte("menu_order"));
				menuButttonVo.setMenuUrl(rs.getString("menu_url"));
				menuButttonVo.setMenuApp(rs.getByte("menu_app"));

				return menuButttonVo;
			}
		};
		DataGridPage dbp =this.pagedQuery(sql.toString() ,pb,rmp);
		if (0 >= dbp.getTotal()) {
			dbp.setRows(new ArrayList());
		}
		
		return dbp;
	}
	
	

}
