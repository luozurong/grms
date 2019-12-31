package com.hori.utils;

public class FormatConvertor {
	
	
	public static long stringToLong (String numberStr) throws NumberFormatException {
		long value = 0L;
		if (numberStr.startsWith("0x")
				|| numberStr.startsWith("0X")) {
			numberStr = numberStr.substring(2);
			value = Long.parseLong(numberStr,16);
		} else {
			value = Long.parseLong(numberStr);
		}
		return value;
	}
	
	public static int stringToInt (String numberStr) throws NumberFormatException {
		int value = 0;
		if (numberStr.startsWith("0x")
				|| numberStr.startsWith("0X")) {
			numberStr = numberStr.substring(2);
			value = Integer.parseInt(numberStr,16);
		} else {
			value = Integer.parseInt(numberStr);
		}
		return value;
	}
	

}
