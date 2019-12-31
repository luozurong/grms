package com.jlit.uaas.util;
/**
 * OAuth 工具类
 * @author lzs
 *
 */
public class NumericUtil {
	private static Integer tmpIndex = 10000;
    /**
	 * 生成一个5位数的递增正整数
	 * 
	 * @return
	 */
	public static int createAutoIncNumer() {
		synchronized (tmpIndex) {
			if (tmpIndex > 99999)
				tmpIndex = 10000;
			tmpIndex++;
			return tmpIndex;
		}
	}
}
