package com.jlit.db;

import java.io.Serializable;
import java.util.List;

/**
 * 针对单个Entity对象的操作定义.不依赖于具体ORM实现方案.
 * 
 */
public interface EntityDao<T> {

	T get(Serializable id);

	List<T> getAll();

	void save(Object o);

	void remove(Object o);

	void removeById(Serializable id);

	/**
	 * 获取Entity对象的主键名。
	 * 
	 * @param clazz
	 * @return
	 */
	String getIdName(Class clazz);
}
