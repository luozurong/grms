package com.jlit.uaas.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * vuid生成工具类
 * @author lzs
 *
 */
public class VuidUtil {
	private static Integer tmpIndex = 10000;

	/**
	 * 生成一个vuid,根据时间长整形和4位随机数生成
	 * 
	 * @return
	 */
	public static String createVuid() {
		int randomNumber = createRandomNumer();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String d = sf.format(new Date());
		return d.toString() + randomNumber + "";
	}

	/**
	 * 生成一个5位数的递增正整数
	 * 
	 * @return
	 */
	private static int createRandomNumer() {
		synchronized (tmpIndex) {
			if (tmpIndex > 99999)
				tmpIndex = 10000;
			tmpIndex++;
			return tmpIndex;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 100; i++) {

			System.out.println(createVuid());
		}
	}
}
