package com.hori.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.model.Menu;
import com.hori.model.Operation;

@Repository
public class OperationDao extends HibernateBaseDao<Operation>{
	private static final Log log = LogFactory.getLog(MenuDao.class);
	
	public List<Operation> getMenuStart(String systemId,String menuApp) {
		//只查询启用状态的菜单
		String hql = "FROM Operation where menuId in (select a.menuId from Menu a where a.systemId=? and a.menuStatus=1 and a.menuApp=?  ) and btnStatus=1 order by btnOrder";
		List<Object> values = new ArrayList<Object>();
		Byte menuAppB=Byte.valueOf(menuApp);
		values.add(systemId);
		values.add(menuAppB);
		return this.find(hql,values);
	}
	
	public List<Operation> getMenuAll(String menuId) {
		//只查询启用状态的菜单
		String hql = "FROM Operation where menuId=?";
		List<Object> values = new ArrayList<Object>();
		values.add(menuId);
		return this.find(hql,values);
	}
	

}
