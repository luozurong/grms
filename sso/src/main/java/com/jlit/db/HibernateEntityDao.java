package com.jlit.db;

import java.io.Serializable;
import java.util.List;

import com.jlit.utils.GenericsUtils;


/**
 * 负责为单个Entity对象提供CRUD操作的Hibernate DAO基类.
 * <p/>
 * 子类只要在类定义时指定所管理Entity的Class, 即拥有对单个Entity对象的CRUD操作.
 * 
 * <pre>
 * public class UserManager extends HibernateEntityDao&lt;User&gt; {
 * }
 * </pre>
 * 
 * @see com.trisun.common.db.HibernateGenericDao
 */
@SuppressWarnings("unchecked")
public class HibernateEntityDao<T> extends HibernateGenericDao implements
		EntityDao<T> {
	/**
	 * DAO所管理的Entity类型
	 */
	protected Class<T> entityClass;

	/**
	 * 在构造函数中将泛型T.class赋给entityClass。
	 */
	public HibernateEntityDao() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 取得entityClass。JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
	 * 
	 * @return
	 */
	protected Class<T> getEntityClass() {
		return entityClass;
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

	/**
	 * 获取全部对象
	 * 
	 * @return
	 * @see HibernateGenericDao#getAll(Class)
	 * @deprecated 该方法当数据量大的时候会产生性能问题。
	 */
	@Deprecated
	public List<T> getAll() {
		return getAll(getEntityClass());
	}

	/**
	 * 获取全部对象，带排序参数。
	 * 
	 * @param orderBy
	 * @param isAsc
	 * @see HibernateGenericDao#getAll(Class,String,boolean)
	 * @deprecated 该方法当数据量大的时候会产生性能问题。
	 */
	@Deprecated
	public List<T> getAll(String orderBy, boolean isAsc) {
		return getAll(getEntityClass(), orderBy, isAsc);
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

	/**
	 * 根据属性名和属性值查询对象。
	 * 
	 * @param propertyName
	 * @param value
	 * @return 符合条件的对象列表
	 * @see HibernateGenericDao#findBy(Class,String,Object)
	 */
	public List<T> findBy(String propertyName, Object value) {
		return findBy(getEntityClass(), propertyName, value);
	}

	/**
	 * 根据属性名和属性值查询对象，带排序参数。
	 * 
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @return 符合条件的对象列表
	 * @see HibernateGenericDao#findBy(Class,String,Object,String,boolean)
	 */
	public List<T> findBy(String propertyName, Object value, String orderBy,
			boolean isAsc) {
		return findBy(getEntityClass(), propertyName, value, orderBy, isAsc);
	}

	/**
	 * 根据属性名和属性值查询唯一对象。如果对象不存在则返回null，如果存在多个对象则抛出异常。
	 * 
	 * @param propertyName
	 * @param value
	 * @return 符合条件的唯一对象 or null
	 * @see HibernateGenericDao#findUniqueBy(Class,String,Object)
	 */
	public T findUniqueBy(String propertyName, Object value) {
		return findUniqueBy(getEntityClass(), propertyName, value);
	}

	/**
	 * 判断对象某些属性的值在数据库中唯一。
	 * 
	 * @param entity
	 * @param uniquePropertyNames
	 *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
	 * @see HibernateGenericDao#isUnique(Class,Object,String)
	 */
	public boolean isUnique(Object entity, String uniquePropertyNames) {
		return isUnique(getEntityClass(), entity, uniquePropertyNames);
	}

}
