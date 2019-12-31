package com.hori.db;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class BaseDao<T> {
	
	protected Class<T> entityClass;
	protected HibernateTemplate hibernateTemplate;
	protected transient final Log log = LogFactory.getLog(getClass());

	public BaseDao() {
		entityClass = getGenericType(0);
	}
	
	protected Query setParameters(Query query, Object... values) {
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	/**
	 * 去除HQL的select子句，未考虑union的情况，用于pagedQuery。
	 * 
	 * @param hql
	 * @return
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	public static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, "HQL: \"" + hql + "\" must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	
	/**
	 * 去除HQL的order by子句，用于pagedQuery。
	 * 
	 * @param hql
	 * @return
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	public static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
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
	protected <T> Criteria createCriteria(Session session, Class<T> entityClass, String orderBy, boolean isAsc,
			Criterion... criterions) {
		Assert.hasText(orderBy);
		Criteria criteria = createCriteria(session, entityClass, criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria;
	}
	
	protected Criteria addCriterions(Criteria criteria, Criterion... criterions){
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	/**
	 * 取得对象的主键名，辅助函数。
	 * 
	 * @param clazz
	 * @return
	 */
	public String getIdName(Class clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getHibernateTemplate().getSessionFactory().getClassMetadata(clazz);
		Assert.notNull(meta, "Class " + clazz + " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, clazz.getSimpleName() + " has no identifier property define.");
		return idName;
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
	public <T> Serializable getId(Class<T> entityClass, Object entity) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		Assert.notNull(entity);
		Assert.notNull(entityClass);
		return (Serializable) PropertyUtils.getProperty(entity, getIdName(entityClass));
	}

	/**
	 * 获取泛型的具体类
	 * @param index
	 *            泛型序号 0
	 * @return
	 */
	protected Class getGenericType(int index) {
		Type genType = getClass().getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index outof bounds");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
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
	 * 删除对象。
	 * 
	 * @param o
	 */
	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}
	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	protected void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
