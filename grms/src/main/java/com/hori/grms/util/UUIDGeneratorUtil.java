package com.hori.grms.util;

import java.util.UUID;

/**
 * uuid生成工具
 * @author laizs
 * @time 2015-2-11上午9:16:44
 * @file UUIDGeneratorUtil.java
 *
 */
public class UUIDGeneratorUtil {
	/**
	 * 生成一个uuid
	 * @return
	 */
    public static final String generateUUID(){
    	String uuidStr =  UUID.randomUUID().toString();
		long time = System.currentTimeMillis();
		String timeStr = time+"";
		
		String timeTemp = timeStr.substring(0, 12);	
		
		StringBuffer sb = new StringBuffer(timeTemp);
		String[] uuidArray = uuidStr.split("-");
		
		for(String uuidStrTemp:uuidArray){
			sb.append(uuidStrTemp);
		}
		return sb.substring(0, 32);
    }
    public static void main(String[] args) {
    	System.out.println(generateUUID());
	}
}
