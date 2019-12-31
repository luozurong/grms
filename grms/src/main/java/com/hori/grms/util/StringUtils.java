package com.hori.grms.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;


/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	 final static DecimalFormat  df   = new DecimalFormat("######0.00");
	public static String lowerFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	public static String upperFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
	}
		
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	/**
	 * 获取1~1000的随机数
	 * @return
	 */
	public static Integer randomVal(){
		double randomVal = Math.random()*1000 +1;
		return (int)randomVal;
	}
	
	/**
	  * 删除字符的首个0
	  * @param val
	  * @return
	  */
	 public static String delFirstZero(String val){
		 if(val.startsWith("0")){
			 return val.substring(1,val.length());
		 }
		 return val;
	 }
	
	 public static List<String> stringSpilt(String s){
			if(StringUtils.isNotBlank(s)){
				String[] strArr =  s.split(",");
				List<String> list = new ArrayList<String>();
				for (String str : strArr) {
					list.add(str);
				}
				return list;
			}
			return null;
		}
	 
	 public static String numPercentage(double f){
		 if(f<=0)
			 return "0%";
		 return df.format(f*100)+"%";
	 }
	 
	 /**
	  * 格式化秒
	  * @return 00:00:00
	  */
	 public static String secondFormat(Object obj){
		 
		 if(obj == null){
			return "00:00:00"; 
		 }
		 
		 long seconds = 0;
		 long hours = 0;
		 long minutes = 0;
		 String str = obj.toString();
		 double strl = Double.parseDouble(str)/1000;
		 
		 seconds = Math.round(strl);
         
         if(seconds >0 && seconds < 1){
        	 seconds = 1;
         }else{
        	 seconds = seconds % 86400;            //剩余秒数
    		 hours = seconds / 3600;            //转换小时
             seconds = seconds % 3600;                //剩余秒数
             minutes = seconds /60;            //转换分钟
             seconds = seconds % 60;                //剩余秒数
         }
		 
		 return unitFormat(hours) + ":" + unitFormat(minutes) + ":" + unitFormat(seconds);
	 }
	 public static String unitFormat(long i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + String.valueOf(i);
        else
            retStr = "" + i;
        return retStr;
    }
	 
	/**
	 * <b>Desc:</b> 毫秒转化为时间字符串,不足1秒算作1秒
	 * @return
	 */
	public static String msecToTimeStr(long msec) {
		if (msec > 0) {
			msec += 500;
			long sec = msec/ 1000 % 60;
			long min = msec / 1000 / 60 % 60;
			long hour = msec / 1000 / 60 / 60;
			return (hour < 10 ? ("0" + hour) : hour) + ":"
					+ (min < 10 ? ("0" + min) : min) + ":"
					+ (sec < 10 ? ("0" + sec) : sec);
		} else {
			return "00:00:00";
		}
	}
}
