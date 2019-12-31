package com.hori.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.util.ReflectionUtils;

import com.hori.db.exception.DataDeleteFailException;
import com.hori.db.exception.DataQueryFailException;
import com.hori.db.exception.DataSaveFailException;
import com.hori.db.exception.DatabaseConnectException;
import com.hori.db.support.DataGridPage;
import com.hori.db.support.Page;
import com.hori.db.support.PageBean;


/**
 * 
 * @author chenkl
 * @param <T>
 */
public class HibernateBaseDao<T> extends BaseDao<T> implements IBaseDao<T> {
	
	protected JdbcTemplate jdbcTemplate;

	public T getById(Serializable id) {
		try {
			return hibernateTemplate.get(entityClass, id);
		} catch (Exception e) {
			log.info("GetById Fail! ID:" + id);
			e.printStackTrace();
			throw new DataQueryFailException("GetById Fail! ID:" + id);
		}
	}

	public T loadById(Serializable id) {
		try {
			return hibernateTemplate.load(entityClass, id);
		} catch (Exception e) {
			log.info("LoadById Fail! ID:" + id);
			e.printStackTrace();
			throw new DataQueryFailException("LoadById Fail! ID:" + id);
		}
	}

	public void deleteById(Serializable id) {
		try {
			hibernateTemplate.delete(loadById(id));
			flush();
		} catch (Exception e) {
			log.info("DeleteById Fail! ID:" + id);
			e.printStackTrace();
			throw new DataDeleteFailException("DeleteById Fail! ID:" + id);
		}
	}

	public int deleteByIds(Serializable[] ids) {
		int sum = 0;
		for (int i = 0; i < ids.length; i++) {
			try {
				hibernateTemplate.delete(loadById(ids[i]));
				sum++;
			} catch (Exception e) {
				log.info("deleteByIds Fail! ID:" + ids[i]);
				e.printStackTrace();
				throw new DataDeleteFailException("DeleteById Fail! ID:" + ids[i]);
			}
		}
		flush();
		return sum;
	}

	public void delete(T t) {
		try {
			hibernateTemplate.delete(t);
		} catch (Exception e) {
			log.info("Delete Fail! ClassName:" + t.getClass().getName());
			e.printStackTrace();
			throw new DataDeleteFailException("Delete Fail! ClassName:" + t.getClass().getName());
		}
	}

	public Serializable save(T t) {
		try {
			return hibernateTemplate.save(t);
		} catch (Exception e) {
			log.info("Save Fail! ClassName:" + t.getClass().getName());
			e.printStackTrace();
			throw new DataSaveFailException("Save Fail! ClassName:" + t.getClass().getName());
		}
	}

	public void update(T t) {
		try {
			hibernateTemplate.update(t);
		} catch (Exception e) {
			log.info("Update Fail! ClassName:" + t.getClass().getName());
			e.printStackTrace();
			throw new DataSaveFailException("Update Fail! ClassName:" + t.getClass().getName());
		}

	}
	
	
	/**
	 * 使用HQL分页查询。
	 * <p>
	 * 注意:此方法用了group by ,distinct等语句的时候,总数可能是不对的!
	 * 
	 * @param hql
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 *            分页大小
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page pagedQuery(String hql, int pageNo, int pageSize,
			Object... values) {
		// Count查询
		if (hql.toLowerCase().indexOf("group by") > 0) {
			if (log.isWarnEnabled()) {
				log.warn("Using 'group by' may cause error!");
			}
		}
		String countQueryString = "select count (*) "
				+ removeSelect(removeOrders(hql));
	
		/*List<Long> countlist = getHibernateTemplate().find(countQueryString,
				values);
		long totalCount = countlist.get(0);*/
		List<Long> countlist = (List<Long>) getHibernateTemplate().find(countQueryString, values);
		long totalCount = countlist.get(0);
		if (totalCount < 1) {
			return new Page();
		}
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List<?> list = getListForPage(hql, startIndex, pageSize, values);
		return new Page(startIndex, totalCount, pageSize, list);
	}
	
	/**
	 * 使用sql分页查询。
	 * <p>
	 * 注意:此方法用了group by ,distinct等语句的时候,总数可能是不对的!
	 * 
	 * @param sql
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 *            分页大小
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page pagedQueryForJdbc(final String sql ,final String hql ,final int pageNo ,final int pageSize ,
			Object... values) {
		// Count查询
		String countQueryString = "select count (*) "
				+ removeSelect(removeOrders(hql));
		List<Long> countlist = (List<Long>) getHibernateTemplate().find(countQueryString, values);
		long totalCount = countlist.get(0);
		if (totalCount < 1) {
			return new Page();
		}
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = getCurrentSession().createSQLQuery(sql).setCacheable(false);
		setParameters(query, values);
		
		int offset = (pageNo - 1) * pageSize;
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List<?> list = query.list();
		
		return new Page(startIndex, totalCount, pageSize, list);
	}
	
	/**
	 * 用HibernateTemplate实现分页查询。
	 * 
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List getListForPage(final String hql, final int offset,
			final int pageSize, final Object... values) {
		/*return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = createQuery(session, hql, values);
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
		return null;*/
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session){
				Query query = createQuery(session, hql, values);
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}
	
	/**
	 * 使用所给Session创建Query对象。
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数，可以在返回Query后自行设置。
	 * 留意可以连续设置，如下：
	 * 
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下：
	 * 
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 * 
	 * @param session
	 * @param hql
	 * @param values
	 * @return
	 */
	protected Query createQuery(Session session, final String hql,
			final Object... values) {
		Query query = session.createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	/**
	 * 使用HQL分页查询。
	 * 
	 * @param hql
	 * @param pageBean 页面基本属性
	 * @param values 参数
	 * @version 1.1
	 * @bug 后台排序问题未解决
	 * @return
	 */
	public DataGridPage pagedQuery(String hql, PageBean pageBean, Object... values) {
		String countQueryString = "select count(*) " + removeSelect(removeOrders(hql));
		Long total;
		try {
			total = (Long) hibernateTemplate.find(countQueryString, values).get(0);
		} catch (Exception e) {
			log.info("DatabaseConnect Fail!");
			e.printStackTrace();
			throw new DatabaseConnectException();
		}
		DataGridPage dgp = new DataGridPage();
		if (total == 0) {
			return dgp;
		}
		int offset = (pageBean.getPage() - 1) * pageBean.getRp();

		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(pageBean.getRp());
		setParameters(query, values);

		try {
			dgp.setRows(query.list());
		} catch (Exception e) {
			log.info("PagedQuery Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("PagedQuery Fail!");
		}
		dgp.setTotal(total.intValue());
		dgp.setPage(pageBean.getPage());
		return dgp;
	}
	
	public <V> DataGridPage pagedQuery(String sql, PageBean pageBean,RowMapper<V> rmp,Object... values) {
		String countQueryString = "select count(*) from" + "(" + sql + ") as temp";
		Long total;
		try {
			//total = getJdbcTemplate().queryForLong(countQueryString,values);
			total = getJdbcTemplate().queryForObject(countQueryString, Long.class, values);
		} catch (Exception e) {
			log.info("DatabaseConnect Fail!");
			e.printStackTrace();
			throw new DatabaseConnectException();
		}
		DataGridPage dgp = new DataGridPage();
		if (total == 0) {
			return dgp;
		}
		
		try {
			int offset = (pageBean.getPage() - 1) * pageBean.getRp();
			
			List<V> voList = getJdbcTemplate().query(sql + " limit "+offset+","+pageBean.getRp(),values,rmp);
			dgp.setRows(voList);
		} catch (Exception e) {
			log.info("PagedQuery Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("PagedQuery Fail!");
		}
		dgp.setTotal(total.intValue());
		dgp.setPage(total.intValue()/pageBean.getRp() + (total.intValue()%pageBean.getRp()>0?1:0));
		return dgp;
	}
	
	//jdbc模板查询
	public DataGridPage pagedQueryForJdbc(String sql,String hql, PageBean pageBean,RowMapper<T> rmp,Object... values) {
		String countQueryString = "select count(*) from " + "("+hql+") as temp";
		Long total;
		try {
			//total = getJdbcTemplate().queryForLong(countQueryString,values);
			total = getJdbcTemplate().queryForObject(countQueryString, Long.class, values);
		} catch (Exception e) {
			log.info("DatabaseConnect Fail!");
			e.printStackTrace();
			throw new DatabaseConnectException();
		}
		DataGridPage dgp = new DataGridPage();	
		if (total == 0) {
			return dgp;
		}
		
		try {
			
			List<T> voList = getJdbcTemplate().query(sql,rmp);
			dgp.setRows(voList);
		} catch (Exception e) {
			log.info("PagedQuery Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("PagedQuery Fail!");
		}
		dgp.setTotal(total.intValue());
		dgp.setPage(pageBean.getPage());
		return dgp;
	}



	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		try {
			//return hibernateTemplate.find("FROM " + entityClass.getName());
			return (List<T>) hibernateTemplate.find("FROM " + entityClass.getName(), (Object[]) null);
		} catch (Exception e) {
			log.info("GetAll Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("GetAll Fail!");
		}
	}

	public List<T> find(Map map) {
		try {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			criteria.add(Restrictions.allEq(map));
			return criteria.list();
		} catch (Exception e) {
			log.info("Find Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("Find Fail!");
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... values) {
		try {
			//return hibernateTemplate.find(hql, values);
			return  (List<T>) hibernateTemplate.find(hql, values);
		} catch (Exception e) {
			log.info("Find Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("Find Fail!");
		}
	}

	public List<T> findBy(String propertyName, Object value) {
		try {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			criteria.add(Restrictions.eq(propertyName, value));
			return criteria.list();
		} catch (Exception e) {
			log.info("FindBy Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("FindBy Fail!");
		}
	}

	public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc) {
		try {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			criteria.add(Restrictions.eq(propertyName, value));
			if (isAsc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
			return criteria.list();
		} catch (Exception e) {
			log.info("FindBy Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("FindBy Fail!");
		}

	}

	public T findUniqueBy(String propertyName, Object value) {
		try {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			criteria.add(Restrictions.eq(propertyName, value));
			return (T) criteria.uniqueResult();
		} catch (Exception e) {
			log.info("FindUniqueBy Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("FindUniqueBy Fail!");
		}
	}

	@SuppressWarnings("unchecked")
	public boolean isUnique(final Object entity, final String[] uniquePropertyNames) {
		Integer count = (Integer) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session){
				Criteria criteria = session.createCriteria(entityClass).setProjection(Projections.rowCount());
				try {
					// 循环加入唯一列
					for (String name : uniquePropertyNames) {
						criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(entity, name)));
					}

					// 以下代码为了如果是update的情况，排除entity自身。
					String idName = getIdName(entityClass);

					// 取得entity的主键值。
					Serializable id = getId(entityClass, entity);

					// 如果id!=null，说明对象已存在，该操作为update，加入排除自身的判断。
					if (id != null) {
						criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
					}
				} catch (Exception e) {
					ReflectionUtils.handleReflectionException(e);
					e.printStackTrace();
				}
				return criteria.uniqueResult();
			}
		});
		return count == 0;
	}

	public int executeUpdate(String hql, Object... values) {
		try {
			Query query = getCurrentSession().createQuery(hql);
			setParameters(query, values);
			return query.executeUpdate();
		} catch (Exception e) {
			log.info("ExecuteUpdate Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("ExecuteUpdate Fail!");
		}
	}

	public int executeSQLUpdate(String sql) {
		try {
			return getCurrentSession().createSQLQuery(sql).setCacheable(false).executeUpdate();
		} catch (Exception e) {
			log.info("ExecuteSQLUpdate Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("ExecuteSQLUpdate Fail!");
		}
	}

	public int executeSQLUpdate(String sql, Object... values) {
		try {
			Query query = getCurrentSession().createSQLQuery(sql).setCacheable(false);
			setParameters(query, values);
			return query.executeUpdate();
		} catch (Exception e) {
			log.info("ExecuteSQLUpdate Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("ExecuteSQLUpdate Fail!");
		}
	}

	public List createNavtiveSQLQuery(String sql) {
		try {
			return getCurrentSession().createSQLQuery(sql).setCacheable(false).list();
		} catch (Exception e) {
			log.info("CreateNavtiveSQLQuery Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("CreateNavtiveSQLQuery Fail!");
		}
	}

	public List createNavtiveSQLQuery(String sql, Object... values) {
		try {
			Query query = getCurrentSession().createSQLQuery(sql).setCacheable(false);
			setParameters(query, values);
			return query.list();
		} catch (Exception e) {
			log.info("CreateNavtiveSQLQuery Fail!");
			e.printStackTrace();
			throw new DataQueryFailException("CreateNavtiveSQLQuery Fail!");
		}
	}

	public Session getCurrentSession() {
		try {
			return hibernateTemplate.getSessionFactory().getCurrentSession();
		} catch (Exception e) {
			log.error("GetCurrentSession Fail!");
			e.printStackTrace();
			throw new HibernateException("GetCurrentSession Fail!");
		}
	}

	public void clear() {
		try {
			hibernateTemplate.clear();
		} catch (Exception e) {
			log.error("Clear Fail!");
			e.printStackTrace();
			throw new HibernateException("Clear Fail!");
		}
	}

	public void flush() {
		try {
			hibernateTemplate.flush();
		} catch (Exception e) {
			log.error("Flush Fail!");
			e.printStackTrace();
			throw new HibernateException("Flush Fail!");
		}
	}
	/**
	 * 根据ID移除对象。
	 * 
	 * @param id
	 * @see HibernateGenericDao#removeById(Class,Serializable)
	 */
	public void removeById(Serializable id) {
		removeById(getEntityClass(), id);
	}

	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 根据ID获取对象。
	 * 
	 * @param id
	 * @return
	 * @see HibernateGenericDao#getId(Class,Object)
	 */
	public T get(Serializable id) {
		return get(getEntityClass(), id);
	}
	
	public T get(String hql, Object... param) {
		List l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return (T) l.get(0);
		}
		return null;
	}
	
	public List<T> find(String hql, int page, int rows, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	
	public Long count(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}
	
	public Long count(String hql, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}

	public Integer executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	public Integer executeHql(String hql, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	public Integer executeHql(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

	public List<T> find(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}
}

