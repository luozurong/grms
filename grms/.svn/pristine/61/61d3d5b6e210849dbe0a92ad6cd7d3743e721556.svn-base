package com.hori.grms.util.fan;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	/**
	 * 将LIST数组拼装成SQL的 IN查询格式
	 * @param list
	 * @return
	 */
	public static String spilSqlParam(List<String> list){
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size()>0){
			sb.append("(");
			for(int i=0;i<list.size();i++){
				sb.append("'");
				sb.append(list.get(i));
				sb.append("'");
				if(i < list.size()-1){
					sb.append(",");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		StringUtil su = new StringUtil();
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println(su.spilSqlParam(list));
	}
}
