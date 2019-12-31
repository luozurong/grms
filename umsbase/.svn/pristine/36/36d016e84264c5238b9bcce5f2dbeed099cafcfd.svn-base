package com.hori.dao;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.model.AuthorizeArea;
import com.hori.model.Province;

/**
 * 省管理
 */
@Repository
public class ProvinceDao extends HibernateBaseDao<Province>{
	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(ProvinceDao.class);

	public List<Province> getAll(String string, boolean b) {
		return this.getAll(string, b);
	}
	public List<Province> getProvinceAll() {
		String hql = "FROM Province ";
		return this.find(hql);
	}
	
	
}
