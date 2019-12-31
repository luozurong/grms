package com.hori.grms.util;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.math.RandomUtils;
/**
 * 图片处理工具
 * @author laizs
 * @time 2014-5-13 上午11:34:05
 * @file PictureUtil.java
 */
public class PictureUtil {
	/**
	 * 时间格式，精确到毫秒
	 */
	public final static SimpleDateFormat SDF=new SimpleDateFormat("yyyyMMddHHmmssSSS");
	/**
	 * 生成系统唯一的文件名
	 * 采用时间格式精确到毫秒与4位随机数的方式结合
	 * @param oldFileName  旧文件名
	 * @return
	 */
	public static String generateFileName( ){
		String timeStr=SDF.format(new Date());
		//四位随机号，不足四位补全至4位
		int i= RandomUtils.nextInt(10000);
		String no=String.format("%04d",i);
		return timeStr+no;
	}
	public static void main(String[] args) {
		System.out.println(generateFileName());
	}
}
