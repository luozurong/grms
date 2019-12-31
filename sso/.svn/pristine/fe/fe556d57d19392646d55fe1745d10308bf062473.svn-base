package com.jlit.uaas.util;

import java.util.Date;

public class FuzzyQueryUtils {
	
	/**
     * 判断是否为查询条件,条件为NULL或为空字符串,则不能为查询条件
     *
     * @param condition 条件
     * @return boolean
     */
	public static boolean isCondition(String condition) {
        if (condition != null && !condition.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }
	/*
	 * 重定义isCondition方法，判断时间属性为参数是否为空
	 */
	public static boolean isCondition(Date condition) {
		if (condition == null) {
			return false;
		} else {
			return true;
		}
    }

    /**
     * 用于在HQL中,将查询条件加上%,使得查询支持模糊查询
     *
     * @param condition
     * @return String
     */
    public static String fuzzyQueryCondition(String condition) {
        StringBuffer sb = new StringBuffer();
        sb.append("%");
        sb.append(condition.trim());
        sb.append("%");
        return sb.toString();
    }

}
