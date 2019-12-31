package com.hori.util;

import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Table;


public class JpaUtil {

	/**
	 * 通过字段属性返回对应数据库字段名
	 * @param o
	 * @param name
	 * @return
	 */
	public static String getColumnName(Object o,String name) {
		Method[] methods = o.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String methodName = method.getName().replace("get", "");
			if (methodName.toLowerCase().equals(name.toLowerCase())) {
				Column excel = method.getAnnotation(Column.class);
				if (null != excel) 
					return excel.name();
			}
		}
		return "";
	}

	/**
	 * 通过字段属性返回对应数据库表名
	 * @param o
	 * @return
	 */
	public static String getTableName(Object o) {
		Table table;
		table = o.getClass().getAnnotation(Table.class);
		if (null != table) {
			return table.name();
		}
		return "";
	}

	

}
