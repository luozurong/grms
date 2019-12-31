package com.hori.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.model.User;
import com.hori.model.UserType;
@Repository
public class UserTypeDao extends HibernateBaseDao<UserType> {
	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(UserTypeDao.class);
	
	/**
	 * 查找用户的全部类型
	 * @return
	 */
	public List<UserType> findAll(){
		String hql = " FROM UserType WHERE 1=1 AND valid = 0 ORDER BY cseq ";
		 List<UserType> list = this.find(hql);
		    if(null!=list&&list.size()>0){
		    	 return list;
		    }
		    return new ArrayList<UserType>();
	}
}
