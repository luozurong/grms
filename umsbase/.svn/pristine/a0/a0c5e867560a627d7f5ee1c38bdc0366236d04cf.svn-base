package com.hori.utils;

public class PhoneNumUtil {
	/**
	 * 判断是不是手机号码
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile){
		if(null==mobile){
			return false;
		}
		return mobile.matches("^(13|15|18)\\d{9}$");
	}
	public static void main(String[] args) {
		System.out.println(isMobile("1380013800"));
	}
}
