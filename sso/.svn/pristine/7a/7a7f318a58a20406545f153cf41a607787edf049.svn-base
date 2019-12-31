package com.jlit.uaas.util;

import java.security.MessageDigest;
import java.util.Date;

public class MD5Util {

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];

	}
    /**
     * md5加密后密文（小写字母）
     * @param origin
     * @return
     */
	public static String getMD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}
	/**
	 * md5加密后密文（大写字母
	 * @param origin
	 * @return
	 */
	public static String getMD5EncodeUpper(String origin){
		return getMD5Encode(origin).toUpperCase();
	}
	public static void main(String[] args) {
		System.out.println(getMD5Encode(new Date().toString()));
		System.out.println(getMD5Encode(getMD5Encode(new Date().toString())));
	}
}
