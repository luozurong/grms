package com.hori.utils;

import java.util.Date;
import java.util.List;

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
    /**
	 * 将数组集合转化为字符串格式，便于拼接批量删除sql语句
	 * @param allId
	 * @return
	 */
	public static String getIds(String[] allId){
		String idsStr="";
		for(int i=0;i<allId.length;i++){
			idsStr += "'"+allId[i]+"'";
			if(i != allId.length-1){
				idsStr+=",";
			}
		}		
		return idsStr;
	}

	/**
	 * 集合转化为字符串格式，便于拼接批量操作sql语句
	 * @param allId
	 * @return
	 */
	public static String getIds(List<String> allId){
		String idsStr="";
		
		if(allId==null||allId.size()==0){
			return idsStr;
		}
		
		for(int i=0;i<allId.size();i++){
			idsStr += "'"+allId.get(i)+"'";
			if(i != allId.size()-1){
				idsStr+=",";
			}
		}		
		return idsStr;
	}
}
