package com.hori.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.model.SystemAdmin;
@Repository
public class SystemDao extends HibernateBaseDao<SystemAdmin> {
	private static final Log log = LogFactory.getLog(OrganizationDao.class);
	
	public List<SystemAdmin> getSystemStart() {
		String hql = "FROM SystemAdmin where systemStatus=1 ";
		
		return this.find(hql);
	}
	

}
