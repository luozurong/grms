/**
 * 
 */
package com.hori.utils;

import java.util.UUID;

/**
 * 产生唯一的字符串。
 * 
 * @author cici
 */
public class UUIDString {
	
	public static String getUUIDString(){
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
		for(int i=0;i<10;i++){
			System.out.println(i+"===="+getUUIDString()+"=====" +getUUIDString().length());
		}
	}
}
