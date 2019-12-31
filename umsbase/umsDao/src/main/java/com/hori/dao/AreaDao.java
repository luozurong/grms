package com.hori.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.model.Area;

/**
 * 区域管理
 * @author xuht
 *
 */
@Repository
public class AreaDao extends HibernateBaseDao<Area>{
	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(AreaDao.class);

	public List<Area> getAll(String parame, boolean b) {
		String hql = "FROM Area";
		return this.find(hql);
	}
}
