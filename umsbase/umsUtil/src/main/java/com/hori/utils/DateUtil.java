/**   
 * 文件名：DateUtil.java   
 *   
 * 版本信息：   
 * 日期：Mar 4, 2010   
 * Copyright 2010 JHSYS     
 * 版权所有   
 *   
 */
package com.hori.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**   
 *    
 * 项目名称：trisunCommon   
 * 类名称：DateUtil   
 * 类描述：   
 * 创建人：daihf   
 * 创建时间：Mar 4, 2010 8:08:51 PM   
 * 修改人：Administrator   
 * 修改时间：Mar 4, 2010 8:08:51 PM   
 * 修改备注：   
 * @version    
 *    
 */
public class DateUtil {
	/**
	 * log4j日志 
	 */
	static private Log log = LogFactory.getLog("DateUtil.class");
	/**   
	  
	 * 把这种形式yyyy-MM-dd HH:mm:ss的字符串转换成时间类型。   
	  
	 * @param   dateStr   这种形式yyyy-MM-dd HH:mm:ss的字符串
	  
	 * @return 时间类型   Date  
	  
	 * @Exception 异常对象   
	  
	 * @since  CodingExample　Ver(编码范例查看) 1.1   
	  
	*/
	static public  Date string2Date(String dateStr){
		if(dateStr == null){
			log.info("字符串转换成时间类型不能为空。");
		}
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newCreateTime = null;
		if(!"".equals(dateStr) ){
			try {
				 newCreateTime = sdf.parse(dateStr);
			} catch (ParseException e) {
				log.warn("时间转换出错!!create_time:"+dateStr);
				e.printStackTrace();
			}
		}
		return newCreateTime;
	}
	
	static public String getDateStr(Date date) {    
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
        return format.format(date);    
    }  
	
	  /**
	    * 获取当前日期的前一天
	    * @param date
	    * @return
	    */
	static public Date getBeforeDay(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH,-1);
			date = calendar.getTime();
			return date;
		}
	
	/**
	 * 日期字符串转date
	 * @param strDate
	 * @return
	 */
	static public Date changeStringToDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date uDate = null;
		try {
			uDate = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return uDate;
	}
    
	/**
	 * 获取第二天的日期
	 * 
	 * @param date
	 */
	public static Date getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
	      calendar.setTime(date);
	      calendar.add(Calendar.DAY_OF_MONTH,1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
	      date=calendar.getTime(); 
	   return date;
	}
}
