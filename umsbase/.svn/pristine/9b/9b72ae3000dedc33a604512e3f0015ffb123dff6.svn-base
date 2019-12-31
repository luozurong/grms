package com.hori.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	/**
	 * 首字母大写
	 * @param realName
	 * @return
	 */
	public static String firstUpperCase(String realName) {
		return StringUtils.replaceChars(realName, realName.substring(0, 1),realName.substring(0, 1).toUpperCase());
	}

	/**
	 * 首字母小写
	 * @param realName
	 * @return
	 */
	public static String firstLowerCase(String realName) {
		return StringUtils.replaceChars(realName, realName.substring(0, 1),realName.substring(0, 1).toLowerCase());
	}
	
	
	/**
	 * 将集合的数据拼装成标准SQL
	 * EG：[a,b,c] --> 'a','b','c'
	 * @param lists
	 * @return
	 */
	public static String sqlAssembling(List<String> lists){
		StringBuffer sb = new StringBuffer();
		if(lists != null && lists.size()>0){
			for(int i=0;i<lists.size();i++){
				sb.append("'");
				sb.append(lists.get(i));
				sb.append("'");
				if(i!=lists.size()-1){
					sb.append(",");
				}
			}
			return sb.toString();
		}
		return null;
	}

}
