package com.jlit.uaas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jlit.db.extend.HibernateEntityExtendDao;
import com.jlit.uaas.model.User;

public class UserDao extends HibernateEntityExtendDao<User> {
	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(UserDao.class);

	/**
	 * 根据用户账号获取
	 * @param loginAccount
	 * @return
	 */
	public User getByLoginAccount(String loginAccount){
		String hql="from  User where userAccount=?";
		String[] param={loginAccount};
		List<User> list= this.find(hql, param);
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 根据用户手机注册时填写的手机号获取用户信息
	 * @param phone
	 * @return
	 */
	public User getUserByPhone(String phone) {
		String hql="from User where phone=?";
		String[] param={phone};
		List<User> list= this.find(hql, param);
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
