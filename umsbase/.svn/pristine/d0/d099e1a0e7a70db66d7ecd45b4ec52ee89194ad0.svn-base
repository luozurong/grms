package com.hori.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.model.Paramter;



@Repository
public class ParamterDao extends HibernateBaseDao<Paramter> {
	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(ParamterDao.class);
	
	public List<Paramter> findByType(String type){		
		if(true == StringUtils.isNoneBlank(type)){	
			return super.find(" from Paramter t where type = "+type+" order by order");
		}else{
			return super.find(" from Paramter t order by order");
		}
	}
}















