package com.hori.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.hori.db.support.DataGridPage;
import com.hori.db.support.PageBean;

/**
 * Hibernate数据操作基本接口
 * @author chenkl
 * @version 1.0
 * @param <T>
 */
public interface IBaseDao<T> {

	public T getById(Serializable id);
	
	public T loadById(Serializable id);
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	public void deleteById(Serializable id);
	
	public int deleteByIds(Serializable[] ids);
	
	public void delete(T t);
	
	public Serializable save(T t);
	
	public void update(T t);
	
	public DataGridPage pagedQuery(String hql, PageBean pageBean, Object... values);
	
	public List<T> getAll();
	
	public List<T> find(Map map);
	
	public List<T> find(String hql, Object... values);
	
	public List<T> findBy(String propertyName, Object value);
	
	/**
	 * 根据属性名和属性值查询对象，带排序参数。
	 * 
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @return 符合条件的对象列表
	 */
	public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc);
	
	/**
	 * 根据属性名和属性值查询唯一对象。如果对象不存在则返回null，如果存在多个对象则抛出异常。
	 * 
	 * @param propertyName
	 * @param value
	 * @return 符合条件的唯一对象 or null
	 * @see HibernateGenericDao#findUniqueBy(Class,String,Object)
	 */
	public T findUniqueBy(String propertyName, Object value);
	
	/**
	 * 判断对象某些属性的值在数据库中唯一。
	 * 
	 * @param entity
	 * @param uniquePropertyNames
	 *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
	 */
	public boolean isUnique(Object entity, String[] uniquePropertyNames);
	
	public int executeUpdate(String hql, Object... values);
	
	/**
	 * 执行SQL的Upate语句
	 * @param sql
	 * @return
	 */
	public int executeSQLUpdate(String sql);
	
	public int executeSQLUpdate(String sql, Object... values);
	
	/**
	 * 执行SQL的查询语句
	 * @param sql
	 * @return
	 */
	public List createNavtiveSQLQuery(String sql);
	
	public List createNavtiveSQLQuery(String sql, Object... values);
	
	/**
	 * 获取当前会话的Session
	 * @return
	 */
	public Session getCurrentSession();

	/**
	 * 清除Session中缓存的所有对象，并取消当前Session中所有维持在内存中的保存、更新和删除持久化状态。
	 * 该方法不会关闭已经打开的迭代器或ScrollableResults实例。
	 */
	public void clear();
	
	/**
	 * 强制Session刷新。将当前Session中所有维持在内存中的保存、更新和删除持久化状态同步到数据库。
	 * 该方法必须在事务提交和Session关闭之前调用
	 * 。建议只在相同的事务内后续操作依赖于之前操作对数据库的改变时使用，一般情况建议依赖于事务提交时的自动冲刷即可，无需手动调用此方法。
	 */
	public void flush();
}
