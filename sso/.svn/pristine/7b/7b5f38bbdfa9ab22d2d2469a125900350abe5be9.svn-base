package com.jlit.db;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.QueryTranslatorImpl;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.query.Query;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.jlit.db.support.DataGridPage;
import com.jlit.db.support.Page;
import com.jlit.db.support.PageBean;
import com.jlit.db.support.PageWs;
import com.jlit.utils.BeanUtils;

/**
 * Hibernate Dao的泛型基类。
 * <p/>
 * 继承于Spring的<code>HibernateDaoSupport</code>，提供分页函数和若干便捷查询方法，并对返回值作了泛型类型转换。
 * 
 * @see {@link org.springframework.orm.hibernate3.support.HibernateDaoSupport}
 * @see {@link com.trisun.common.db.HibernateEntityDao}
 */
@SuppressWarnings("unchecked")
public class HibernateGenericDao extends HibernateDaoSupport {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * 返回所给id的实体类持久化实例，如果实例不存在则返回null。该方法不会返回没有初始化的实例。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 * @see {@link org.hibernate.Session#get(Class, Serializable)}
	 */
	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 返回所给id的实体类持久化实例，假定该实例存在。该方法可能返回一个代理实例，这个代理实例在非id的方法被访问的时候根据需要初始化。
	 * 如果查找的实例不存在，抛出异常。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 * @see {@link org.hibernate.Session#load(Class, Serializable)}
	 */
	public <T> T load(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	/**
	 * 获取所给实体类的全部对象。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 * @deprecated 该方法当数据量大的时候会产生性能问题。
	 */
	@Deprecated
	public <T> List<T> getAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 获取所给实体类的全部对象，按所给字段和升降序参数排序。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            升降序参数，true为按升序排列，false为按降序排列
	 * @return
	 * @deprecated 该方法当数据量大的时候会产生性能问题。
	 */
	@Deprecated
	public <T> List<T> getAll(Class<T> entityClass, String orderBy,
			boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc) {
			return (List<T>) getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.asc(orderBy)));
		} else {
			return (List<T>) getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.desc(orderBy)));
		}
	}

	/**
	 * 保存对象。
	 * 
	 * @param o
	 */
	public void save(Object o) {
		getHibernateTemplate().save(o);
		getHibernateTemplate().flush();
	}
	/**
	 * 批量保存或更新，由于spring4中对于hibernate3的HibernateTemplate已经把saveOrUpdateAll删除掉了
	 * 所以自己实现批量保存的接口，但其实并不是真正意义上的批量保存更新
	 * 
	 * @param dataList
	 */
	public void saveOrUpdateAll(List dataList) {
		if(null==dataList || dataList.size()<1){
			return;
		}
		for(int i=0;i<dataList.size();i++){
			getHibernateTemplate().saveOrUpdate(dataList.get(i));
			if(i/50==0){//每隔50条数据fulsh一次内存，免得爆内存溢出oom
				 this.getHibernateTemplate().flush();
				 this.getHibernateTemplate().clear();
			}
		}
		//最后必须flush一次内存
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	/**
	 * 更新对象。
	 * 
	 * @param o
	 */
	public void update(Object o) {
		getHibernateTemplate().merge(o);
		getHibernateTemplate().flush();
	}

	/**
	 * 删除对象。
	 * 
	 * @param o
	 */
	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}

	/**
	 * 根据id删除所给实体类的对象。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param id
	 */
	public <T> void removeById(Class<T> entityClass, Serializable id) {
		remove(get(entityClass, id));
	}

	/**
	 * 强制Session冲刷。将当前Session中所有维持在内存中的保存、更新和删除持久化状态同步到数据库。
	 * 该方法必须在事务提交和Session关闭之前调用
	 * 。建议只在相同的事务内后续操作依赖于之前操作对数据库的改变时使用，一般情况建议依赖于事务提交时的自动冲刷即可，无需手动调用此方法。
	 */
	public void flush() {
		getHibernateTemplate().flush();
	}

	/**
	 * 从Session的缓存中移除该实例。该实例所有的更改将不会被同步到数据库。
	 * 
	 * @param entity
	 */
	public void evit(Object entity) {
		getHibernateTemplate().evict(entity);
	}

	/**
	 * 清除Session中缓存的所有对象，并取消当前Session中所有维持在内存中的保存、更新和删除持久化状态。
	 * 该方法不会关闭已经打开的迭代器或ScrollableResults实例。
	 */
	public void clear() {
		getHibernateTemplate().clear();
	}

	/**
	 * 根据HQL查询。
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public List find(String hql, Object... values) {
		Assert.hasText(hql);
		return getHibernateTemplate().find(hql, values);
	}
	

	/**
	 * 根据属性名和属性值查询对象。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> List<T> findBy(final Class<T> entityClass,
			final String propertyName, final Object value) {
		Assert.hasText(propertyName);
		return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				return createCriteria(session, entityClass,
						Restrictions.eq(propertyName, value)).list();
			}
		});
		
	}

	/**
	 * 
	 * 根据属性名和属性值查询对象，按所给字段和升降序参数排序。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            升降序参数，true为按升序排列，false为按降序排列
	 * @return
	 */
	public <T> List<T> findBy(final Class<T> entityClass,
			final String propertyName, final Object value,
			final String orderBy, final boolean isAsc) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return  getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				return createCriteria(session, entityClass, orderBy, isAsc,
						Restrictions.eq(propertyName, value)).list();
			}
		});
	}

	/**
	 * 根据条件查询，不做分页
	 * @param targetClass	查询对象类型
	 * @param hql			查询hql
	 * @param values		查询条件
	 * @return
	 */
	public <T> List<T> getListMethod(final Class<T> targetClass,final String hql,Object values ){
		List<T> find = (List<T>) this.getHibernateTemplate().find(hql,values);
		return find;
	}
	
	
	/**
	 * 根据属性名和属性值查询唯一对象。如果对象不存在则返回null，如果存在多个对象则抛出异常。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueBy(final Class<T> entityClass,
			final String propertyName, final Object value) {
		Assert.hasText(propertyName);
		return (T) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						return createCriteria(session, entityClass,
								Restrictions.eq(propertyName, value))
								.uniqueResult();
					}
				});
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
	public Page pagedQuery(String hql, int pageNo, int pageSize,
			Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		if (hql.toLowerCase().indexOf("group by") > 0) {
			if (log.isWarnEnabled()) {
				log.warn("Using 'group by' may cause error!");
			}
		}
		String countQueryString = "select count(*) "
				+ removeSelect(removeOrders(hql));
		List<?> countlist = getHibernateTemplate().find(countQueryString,values);
		long totalCount = (Long) countlist.get(0);

		if (totalCount < 1) {
			return new Page();
		}
		if(pageSize == 0){
			pageSize = (int)totalCount;
		}
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List<?> list = getListForPage(hql, startIndex, pageSize, values);
		return new Page(startIndex, totalCount, pageSize, list);
	}
	
	/**
	 * 使用HQL分页查询。
	 * <p>
	 * 解决语句带distinct时总数有可能不对的问题
	 * 
	 * @param hql
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 *            分页大小
	 * @param values
	 * @return
	 */
	public Page pagedQueryWithDistinct(String hql, int pageNo, int pageSize,
			Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		if (hql.toLowerCase().indexOf("group by") > 0) {
			if (log.isWarnEnabled()) {
				log.warn("Using 'group by' may cause error!");
			}
		}
		
		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(hql, hql, Collections.EMPTY_MAP, (SessionFactoryImplementor) this.getSessionFactory());
		queryTranslator.compile(Collections.EMPTY_MAP, false); 
		String tempSQL = queryTranslator.getSQLString(); 
		String countSQL = "select count(*) from (" + tempSQL + ") tmp_count_t"; 
		Query query = this.currentSession().createSQLQuery(countSQL); 
		
		for (int i = 0; i < values.length; i++) { 
			query.setParameter(i, values[i]); 
		} 
		
		List list = query.list(); 
		
		BigInteger totalCount = (BigInteger) list.get(0);

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List<?> list3 = getListForPage(hql, startIndex, pageSize, values);
		return new Page(startIndex, totalCount.intValue(), pageSize, list3);
	}
	
	/**
	 * 使用HQL分页查询。(WebService)
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
	 * @author viliam
	 */
	public <T> PageWs<T> pagedQueryWs(String hql, int pageNo, int pageSize,
			Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		if (hql.toLowerCase().indexOf("group by") > 0) {
			if (log.isWarnEnabled()) {
				log.warn("Using 'group by' may cause error!");
			}
		}
		String countQueryString = "select count(*) "
				+ removeSelect(removeOrders(hql));
		List<Long> countlist = (List<Long>) getHibernateTemplate().find(countQueryString,
				values);
		long totalCount = countlist.get(0);

		if (totalCount < 1) {
			return new PageWs();
		}
		if(pageSize == 0){
			pageSize = (int)totalCount;
		}
		// 实际查询返回分页对象
		int startIndex = PageWs.getStartOfPage(pageNo, pageSize);
		List<?> list = getListForPage(hql, startIndex, pageSize, values);
		return new PageWs(startIndex, totalCount, pageSize, list);
	}
	
	/**
	 * 使用HQL分页查询。
	 * @param hql
	 * @param pageBean 页面基本属性
	 * @param values 参数
	 * @version 1.0
	 * @bug 后台排序问题未解决
	 * @return
	 */
	public DataGridPage pagedQuery(String hql, PageBean pageBean, Object... values){
		String countQueryString = "select count(*) " + removeSelect(removeOrders(hql));
		Long total = (Long)getHibernateTemplate().find(countQueryString, values).get(0);
		DataGridPage dgp = new DataGridPage();
//		int offset = pageBean.getPage() * pageBean.getRp() - pageBean.getRp();
		int offset = (pageBean.getPage() - 1) * pageBean.getRp();
//		hql = hql + " order by " + pageBean.getSortname() + " " + pageBean.getSortorder();
		try{
			dgp.setRows(getListForPage(hql, offset, pageBean.getRp(), values));
		}catch(Exception e){
			e.printStackTrace();
		}
		dgp.setTotal(total.intValue());
		dgp.setPage(pageBean.getPage());
		dgp.setPageSize(pageBean.getRp());
		return dgp;
	}

	/**
	 * 去除HQL的select子句，未考虑union的情况，用于pagedQuery。
	 * 
	 * @param hql
	 * @return
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, "HQL: \"" + hql
				+ "\" must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除HQL的order by子句，用于pagedQuery。
	 * 
	 * @param hql
	 * @return
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 分页查询,需要传入统计总数的HQL。
	 * 
	 * @param hql
	 *            查询的HQL
	 * @param countHql
	 *            统计总数的HQL
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	public Page pagedQuery(final String hql, final String countHql,
			final int pageNo, final int pageSize, Object... values) {
		List<Long> countlist = (List<Long>) getHibernateTemplate().find(countHql, values);
		long totalCount = 0;
		if (countlist != null && !countlist.isEmpty()) {
			totalCount = countlist.get(0);
		}
		if (totalCount < 1) {
			return new Page();
		}
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List<?> list = getListForPage(hql, startIndex, pageSize, values);
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
	public List getListForPage(final String hql, final int offset,
			final int pageSize, final Object... values) {
		return (List) getHibernateTemplate().execute(new HibernateCallback<List>() {
			public List doInHibernate(Session session) {
				Query query = createQuery(session, hql, values);
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}

	/**
	 * 执行HQL语句从数据库中查询最多maxResults条记录，HQL语句可以含有参数。
	 * 
	 * @param hql
	 *            查询使用HQL语句
	 * @param values
	 *            Hibernate的Query类的setParameters需要的参数
	 * @param types
	 *            Hibernate的Query类的setParameters需要的参数
	 * @param maxResults
	 *            最多查询的记录数目
	 * 
	 * @return 查询结果
	 * @deprecated 这个方法有时候有问题
	 */
	public List find(final String hql, final Object[] values,
			final Type[] types, final int maxResults) {
		return getHibernateTemplate().execute(new HibernateCallback<List>() {
			public List doInHibernate(Session session)throws HibernateException {
				Query query = createQuery(session, hql);
				query.setParameters(values, types);
				query.setMaxResults(maxResults);
				return query.list();
			}
		});
	}

	/**
	 * 执行hql语句从数据库中查询最多maxResults条记录，HQL语句不可以含有参数
	 * 
	 * @param hql
	 *            查询使用hql语句
	 * @param maxResults
	 *            最多查询的记录数目
	 * 
	 * @return 以POJO形式封装的查询结果
	 * @deprecated 这个方法有时候有问题
	 */
	public List findWithMaxResults(final String hql, final int maxResults) {
		return getHibernateTemplate().execute(new HibernateCallback<List>() {
			public List doInHibernate(Session session)
					throws HibernateException {
				Query query = createQuery(session, hql);
				query.setMaxResults(maxResults);
				return query.list();
			}
		});
	}

	
	
	/**
	 * 执行HQL update语句。
	 * 
	 * @param hql
	 * @return
	 */
	public int executeUpdate(final String hql, final Object... values) {
		Object o = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = createQuery(session, hql, values);
						int rows = query.executeUpdate();
						return new Integer(rows);
					}
				});
		int ret = 0;
		ret = (o != null) ? Integer.valueOf(o.toString()) : 0;
		return ret;
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
		Assert.hasText(hql);
		Query query = session.createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	/**
	 * 分页查询函数，使用已设好查询条件与排序的<code>Criteria</code>。
	 * 
	 * @param criteria
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 * @return 含总记录数和当前页数据的Page对象
	 */
	public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) {
		Assert.notNull(criteria);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		CriteriaImpl impl = (CriteriaImpl) criteria;

		// 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
		Projection projection = impl.getProjection();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List<CriteriaImpl.OrderEntry>) BeanUtils
					.forceGetProperty(impl, "orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// 执行查询
		int totalCount = (Integer) criteria.setProjection(
				Projections.rowCount()).uniqueResult();

		// 将之前的Projection和OrderBy条件重新设回去
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}

		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// 返回分页对象
		if (totalCount < 1)
			return new Page();

		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List<?> list = criteria.setFirstResult(startIndex).setMaxResults(
				pageSize).list();
		return new Page(startIndex, totalCount, pageSize, list);
	}

	/**
	 * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 * @param criterions
	 * @return 含总记录数和当前页数据的Page对象
	 */
	public <T> Page pagedQuery(final Class<T> entityClass, final int pageNo,
			final int pageSize, final Criterion... criterions) {
		return (Page) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = createCriteria(session,
								entityClass, criterions);
						return pagedQuery(criteria, pageNo, pageSize);
					}
				});
	}

	/**
	 * 分页查询函数，根据entityClass和查询条件参数,排序参数创建默认的<code>Criteria</code>。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return 含总记录数和当前页数据的Page对象
	 */
	public <T> Page pagedQuery(final Class<T> entityClass, final int pageNo,
			final int pageSize, final String orderBy, final boolean isAsc,
			final Criterion... criterions) {
		return (Page) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = createCriteria(session,
								entityClass, orderBy, isAsc, criterions);
						return pagedQuery(criteria, pageNo, pageSize);
					}
				});
	}

	/**
	 * 使用所给Session、实体类和criterions创建Criteria对象。
	 * 
	 * @param <T>
	 * @param session
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	protected <T> Criteria createCriteria(Session session, Class<T> entityClass,
			Criterion... criterions) {
		Criteria criteria = session.createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 使用所给Session、实体类、排序字段、升降序参数、criterions创建Criteria对象。
	 * 
	 * @param <T>
	 * @param session
	 * @param entityClass
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	protected <T> Criteria createCriteria(Session session, Class<T> entityClass,
			String orderBy, boolean isAsc, Criterion... criterions) {
		Assert.hasText(orderBy);
		Criteria criteria = createCriteria(session, entityClass, criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria;
	}

	/**
	 * 判断对象某些属性的值在数据库中是否唯一.
	 * 
	 * @param uniquePropertyNames
	 *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
	 */
	public <T> boolean isUnique(final Class<T> entityClass,
			final Object entity, final String uniquePropertyNames) {
		Assert.hasText(uniquePropertyNames);
		Integer count = (Integer) getHibernateTemplate()
				.executeWithNativeSession(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(entityClass)
								.setProjection(Projections.rowCount());
						String[] nameList = uniquePropertyNames.split(",");
						try {
							// 循环加入唯一列
							for (String name : nameList) {
								criteria.add(Restrictions
										.eq(name, PropertyUtils.getProperty(
												entity, name)));
							}

							// 以下代码为了如果是update的情况，排除entity自身。
							String idName = getIdName(entityClass);

							// 取得entity的主键值。
							Serializable id = getId(entityClass, entity);

							// 如果id!=null，说明对象已存在，该操作为update，加入排除自身的判断。
							if (id != null) {
								criteria.add(Restrictions.not(Restrictions.eq(
										idName, id)));
							}
						} catch (Exception e) {
							ReflectionUtils.handleReflectionException(e);
						}
						return criteria.uniqueResult();
					}
				});
		return count == 0;
	}

	/**
	 * 取得对象的主键值，辅助函数。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param entity
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public <T> Serializable getId(Class<T> entityClass, Object entity)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Assert.notNull(entity);
		Assert.notNull(entityClass);
		return (Serializable) PropertyUtils.getProperty(entity,
				getIdName(entityClass));
	}

	/**
	 * 取得对象的主键名，辅助函数。
	 * 
	 * @param clazz
	 * @return
	 */
	public String getIdName(Class clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		Assert.notNull(meta, "Class " + clazz
				+ " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, clazz.getSimpleName()
				+ " has no identifier property define.");
		return idName;
	}

	/**
	 * 原生SQL查询。
	 * 
	 * @param sql
	 * @return
	 */
	public List createNavtiveSQLQuery(final String sql) {
		Assert.hasText(sql);
		List<?> list = getHibernateTemplate().execute(
				new HibernateCallback<List<?>>() {
					public List<?> doInHibernate(Session session)
							throws HibernateException{
						SQLQuery sqlQuery = session.createSQLQuery(sql);
						sqlQuery.setCacheable(false);
						return sqlQuery.list();
					}
				});
		return list;
	}

	/**
	 * 执行SQL update语句。
	 * 
	 * @param sql
	 * @return
	 */
	public int executeSQLUpdate(final String sql) {
		Object o = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sqlQuery = session.createSQLQuery(sql);
						sqlQuery.setCacheable(false);
						int rows = sqlQuery.executeUpdate();
						return new Integer(rows);
					}
				});
		int ret = 0;
		ret = o != null ? Integer.valueOf(o.toString()) : 0;
		return ret;
	}

	/**
	 * 实现SQL的分页查询。
	 * 
	 * @param sql
	 * @param countSql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page pagedSQLQuery(final String sql, final String countSql,
			int pageNo, final int pageSize) {
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		List<?> countlist = createNavtiveSQLQuery(countSql);
		long totalCount = 0;
		if (countlist != null && !countlist.isEmpty()) {
			totalCount = Long.valueOf(countlist.get(0).toString()).longValue();
		}
		if (totalCount < 1L) {
			return new Page();
		} else {
			final int offset = Page.getStartOfPage(pageNo, pageSize);
			List<?> list = getHibernateTemplate().execute(
					new HibernateCallback<List<?> >() {
						public List<?>  doInHibernate(Session session)
								throws HibernateException {
							SQLQuery sqlQuery = session.createSQLQuery(sql);
							sqlQuery.setCacheable(false);
							sqlQuery.setFirstResult(offset);
							sqlQuery.setMaxResults(pageSize);
							return sqlQuery.list();
						}
					});
			return new Page(offset, totalCount, pageSize, list);
		}
	}
	
	/**
	 * 实现SQL的分页查询。(WebService)
	 * 
	 * @param sql
	 * @param countSql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author viliam
	 */
	public <T> PageWs<T> pagedSQLQueryWs(final String sql, final String countSql,
			int pageNo, final int pageSize) {
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		List<?> countlist = createNavtiveSQLQuery(countSql);
		long totalCount = 0;
		if (countlist != null && !countlist.isEmpty()) {
			totalCount = Long.valueOf(countlist.get(0).toString()).longValue();
		}
		if (totalCount < 1L) {
			return new PageWs();
		} else {
			final int offset = PageWs.getStartOfPage(pageNo, pageSize);
			List<?> list = getHibernateTemplate().execute(
					new HibernateCallback<List<?> >() {
						public List<?>  doInHibernate(Session session)
								throws HibernateException {
							SQLQuery sqlQuery = session.createSQLQuery(sql);
							sqlQuery.setCacheable(false);
							sqlQuery.setFirstResult(offset);
							sqlQuery.setMaxResults(pageSize);
							return sqlQuery.list();
						}
					});
			return new PageWs(offset, totalCount, pageSize, list);
		}
	}

}
