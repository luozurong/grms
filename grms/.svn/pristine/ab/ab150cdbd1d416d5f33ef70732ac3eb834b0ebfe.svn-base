package com.hori.grms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class DataSpliUtil {

	/**
	 * 获取dateEnd开始（包含当天到dateBegin结尾的 日期集合：：： 日期是按日或月递减
	 * @param dateEnd 开始
	 * @param dateBegin 结尾
	 * @param size	默认生成的日期集合数  默认为7
	 * @param back	每次递减间隔的大小  获取日日期为1；获取周日期为7
	 * @return
	 */
	public static List<String> newlySevenDays(String dateEnd,String dateBegin,int size,int back) {
       List<String> listDates = new ArrayList<String>();
       if(dateEnd == null || "".equals(dateEnd))
    	   return null;
       if(dateBegin != null && !"".equals(dateBegin)){ //如果有开始和结束时间，默认获取10000个日期记录
    	   size = 10000;
       }
       if(size ==0)
			size = 7;
       for(int i=0;i<size;i++){
        	java.util.Calendar rightNow = java.util.Calendar.getInstance();
        	rightNow.setTime(DataSpliUtil.changeStringToDate(dateEnd));
            java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	rightNow.add(java.util.Calendar.DAY_OF_MONTH, -i*back);//如果是后退几天，就写 -天数 例如：
            String date = sim.format(rightNow.getTime());    //进行时间转换
            if(dateBegin != null && !"".equals(dateBegin)){
            	if(DataSpliUtil.changeStringToDate(date).getTime() < DataSpliUtil.changeStringToDate(dateBegin).getTime()){
                	break;
                }
            }
            listDates.add(date);
       }
        
        return listDates;
    }
	
	
	
	
	/**
	 * 获取dateEnd开始（包含当天到dateBegin结尾的 月份集合
	 * @param dateEnd 开始
	 * @param dateBegin 结尾
	 * @param size	默认生成的日期集合数  默认为7
	 * @return
	 */
	public static List<String> newlyMonthsByYear(String dateEnd,String dateBegin,int size) {
		List<String> listDates = new ArrayList<String>();
		if(dateEnd == null || "".equals(dateEnd))
    	   return null;
		if(dateBegin != null && !"".equals(dateBegin)){ //如果有开始和结束时间，默认获取10000个日期记录
    	   size = 10000;
		}
		if(size ==0)
			size = 7;
		int days = 0;
        for(int i=0;i<size;i++){
        	 java.util.Calendar rightNow = java.util.Calendar.getInstance();
        	 rightNow.setTime(DataSpliUtil.changeStringToDate(dateEnd));
             java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	 rightNow.add(java.util.Calendar.DAY_OF_MONTH, -days);//如果是后退几天，就写 -天数 例如：
             String date = sim.format(rightNow.getTime());    //进行时间转换
             
             if(dateBegin != null && !"".equals(dateBegin)){
            	 if(DataSpliUtil.changeStringToDate(date).getTime() < DataSpliUtil.changeStringToDate(dateBegin).getTime()){
                	 break;
                 }
             }
             
             listDates.add(date);
             
             //将该月的最后一天做为下个月的起始天， 该月的天数做为间隔
             dateEnd = date;
        	 days = DataSpliUtil.getDaysByYearAndMonth(dateEnd.substring(0,4), dateEnd.substring(5,7));
        }
        
        return listDates;
    }
	
	/**
	 * 获取当前时候前面第Ｎ天的日期
	 * @param appointVal
	 * @return
	 */
	public static String getAppointDate(Date date , int appointVal) {
    	 java.util.Calendar rightNow = java.util.Calendar.getInstance();
    	 rightNow.setTime(date);
         java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 rightNow.add(java.util.Calendar.DAY_OF_MONTH, -appointVal);//如果是后退几天，就写 -天数 例如：
         String dateResult = sim.format(rightNow.getTime());    //进行时间转换
         return dateResult;
    }
	/**
	 * 获取当前时候前面第Ｎ天的日期
	 * @param appointVal
	 * @return
	 */
	public static String getAppointDate(Date date , int appointVal, String parse ) {
		java.util.Calendar rightNow = java.util.Calendar.getInstance();
		rightNow.setTime(date);
		java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat(parse);
		rightNow.add(java.util.Calendar.DAY_OF_MONTH, -appointVal);//如果是后退几天，就写 -天数 例如：
		String dateResult = sim.format(rightNow.getTime());    //进行时间转换
		return dateResult;
	}
	
	public static Date changeStringToDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date uDate = null;
		try {   
			uDate = sdf.parse(strDate);  
		} catch (ParseException e) {   
		    e.printStackTrace();   
		}  
		return uDate;
	}
	
	public static Date changeStringToDateOnlyYMD(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date uDate = null;
		try {   
			uDate = sdf.parse(strDate);  
		} catch (ParseException e) {   
		    e.printStackTrace();   
		}  
		return uDate;
	}
	
	public static String changeDateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = dateFormat.format(date);
		return strDate;
	}
	
	public static String changeDateToStringOnlyYMD(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		return strDate;
	}
	
	/**
	 * 拼装string为指定的开始时间
	 * @param dateValue
	 * @return
	 */
	public static String splicStringToBeginTime(String dateValue){
		StringBuffer sb = new StringBuffer();
		String [] values = dateValue.split("\\ ");
		sb.append(values[0]);
		sb.append(" 00:00:00");
		return sb.toString();
	}
	
	
	/**
	 * 拼装string为指定的结束时间
	 * @param dateValue
	 * @return
	 */
	public static String splicStringToEndTime(String dateValue){
		StringBuffer sb = new StringBuffer();
		String [] values = dateValue.split("\\ ");
		sb.append(values[0]);
		sb.append(" 23:59:59");
		return sb.toString();
	}
	
	/**
	 * 通过年，月获取天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysByYearAndMonth(String years, String months) {  
		int year = Integer.parseInt(years);
		int month = Integer.parseInt(months);
	    int days = 0;  
	    if (month != 2) {  
	        switch (month) {  
	        case 1:  
	        case 3:  
	        case 5:  
	        case 7:  
	        case 8:  
	        case 10:  
	        case 12:  
	        days = 31;  
	        break;  
	        case 4:  
	        case 6:  
	        case 9:  
	        case 11:  
	        days = 30;  
	  
	        }  
	    } else {  
	        //闰年  
	        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)  
	        days = 29;  
	        else  
	        days = 28;  
	  
	    }  
	    return days;
	}
	
	/**
	 * 解析日期控件的时间
	 * @param controlTime
	 * @param type 1：开始时间  2：结束时间   other:（开始时间:结束时间）
	 * @return
	 */
	public static String getDateControl(String controlTime,String type){
		String [] times = controlTime.split(" - ");
		String [] start_time = times[0].split("\\/");
		String [] end_time = times[1].split("\\/");
		StringBuffer startSb = new StringBuffer();
		StringBuffer endSb = new StringBuffer();
		startSb.append(start_time[2]).append("-").append(start_time[0]).append("-").append(start_time[1]);
		endSb.append(end_time[2]).append("-").append(end_time[0]).append("-").append(end_time[1]);
		if("1".equals(type)){
			return startSb.toString();
		}else if("2".equals(type)){
			return endSb.toString();
		}else{
			return startSb.toString() + ":" + endSb.toString();
		}
	}
	
	public static String builderString(String citys){
        citys = citys.substring(0, citys.length()-1).replace(";", "','");
		StringBuilder builder = new StringBuilder("'");
		builder.append(citys);
		builder.append("'");
		return citys = builder.toString();
	}
	
	public static void main(String[] args) {
		DataSpliUtil d = new DataSpliUtil();
//		String currentDate = DataSpliUtil.changeDateToStringOnlyYMD(new Date());
//		System.out.println(d.getAppointDate(changeStringToDateOnlyYMD(currentDate),7));
//		String[] dateArrays = {"1","2","3","4"};
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		JSONArray json = JSONArray.fromObject(dateArrays, jsonConfig);
//		System.out.println(json.toString());
//		
//		String afterTime = DataSpliUtil.getAppointDate(new Date(), 1);
//		String beforeTime = DataSpliUtil.getAppointDate(new Date(), 7);
//		System.out.println(afterTime+"  "+beforeTime);
//		
//		List<String> list = DataSpliUtil.newlySevenDays(
//				"2016-11-06 23:59:59",
//				"2016-10-09 23:59:59",7,7);
		
//		List<String> list = DataSpliUtil.newlyMonthsByYear(
//				"2016-10-31 23:59:59",
//				"2016-09-09 23:59:59",7);
//		for(String s:list){
//			System.out.println(s);
//		}
		
//		System.out.println(DataSpliUtil.getAppointDate(DataSpliUtil.changeStringToDate("2016-11-06 23:59:59"), 7*6));//默认7天前为起始时间
		
//		System.out.println(DataSpliUtil.changeStringToDate(DataSpliUtil.splicStringToBeginTime("2016-11-10")));
		//String s = "210100;321100;340500;370200;370600;370700;430100;440100;440600;440800;441200;530100;";
		//System.out.println(d.builderString(s));
		List<String> sevenList =  DataSpliUtil.newlyMonthsByYear(changeDateToString(new Date()),null,12);
		System.out.println(sevenList);
	}
}
