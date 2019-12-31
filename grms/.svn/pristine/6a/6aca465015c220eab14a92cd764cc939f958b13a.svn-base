package com.hori.grms.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.time.DateFormatUtils;


/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	private static int size = 7;
	
	/**
	 * 字符串转换成时间
	 * @param dateStr
	 * @param fmt
	 * @return
	 */
	static public Date getDate(String dateStr,String fmt) {    
        SimpleDateFormat format = new SimpleDateFormat(fmt); 
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
           return date;   
    }		
	
	static public String getDateStr(Date date,String fmt) {    
		if(date==null){
			return "";
		}
        SimpleDateFormat format = new SimpleDateFormat(fmt);    
        return format.format(date);    
    }

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear(Date date) {
		if (null == date)
			return null;
		return formatDate(date, "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth(Date date) {
		if (null == date)
			return null;
		return formatDate(date, "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay(Date date) {
		if (null == date)
			return null;
		return formatDate(date, "dd");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	public static Date getDateStart(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getStrDateStart(Date date) {
		if (date == null) {
			return null;
		}
		String str = formatDate(date, "yyyy-MM-dd") + " 00:00:00";
		return str;
	}

	public static String getStrDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		String str = formatDate(date, "yyyy-MM-dd") + " 23:59:59";
		return str;
	}

	/**
	 * 判断字符串是否是日期
	 * 
	 * @param timeString
	 * @return
	 */
	public static boolean isDate(String timeString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try {
			format.parse(timeString);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 格式化时间
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String dateFormat(Date timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}

	/**
	 * 获取系统时间Timestamp
	 * 
	 * @return
	 */
	public static Timestamp getSysTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 获取系统时间Date
	 * 
	 * @return
	 */
	public static Date getSysDate() {
		return new Date();
	}

	/**
	 * 生成时间随机数
	 * 
	 * @return
	 */
	public static String getDateRandom() {
		String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		return s;
	}

	/**
	 * 获取当前天（包含当天）的前N天的日期
	 * 
	 * @param dateBegin
	 *            指定开始的日期
	 * @param size
	 *            获取多少个日期记录
	 * @param back
	 *            每次后退的天数
	 * @return
	 */
	public static List<String> getSevenDays(Date dateBegin, int size, int back, String parsePattern) {
		List<String> listDates = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			java.util.Calendar rightNow = java.util.Calendar.getInstance();
			rightNow.setTime(dateBegin);
			// java.text.SimpleDateFormat sim = new
			// java.text.SimpleDateFormat("yyyy/MM/dd");
			java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat(parsePattern);
			rightNow.add(java.util.Calendar.DAY_OF_MONTH, -i * back);// 如果是后退几天，就写
																		// -天数
																		// 例如：
			String date = sim.format(rightNow.getTime()); // 进行时间转换
			listDates.add(date);
		}

		return listDates;
	}

	/**
	 * 获取dateEnd开始（包含当天到dateBegin结尾的 日期集合：：： 日期是按日或月递减
	 * 
	 * @param dateEnd
	 *            开始
	 * @param dateBegin
	 *            结尾
	 * @param size
	 *            默认生成的日期集合数 默认为7
	 * @param back
	 *            每次递减间隔的大小 获取日日期为1；获取周日期为7
	 * @return
	 */
	public static List<String> newlySevenDays(String dateEnd, String dateBegin, int size, int back) {
		List<String> listDates = new ArrayList<String>();
		if (dateEnd == null || "".equals(dateEnd))
			return null;
		if (dateBegin != null && !"".equals(dateBegin)) { // 如果有开始和结束时间，默认获取10000个日期记录
			size = 10000;
		}
		if (size == 0)
			size = 7;
		for (int i = 0; i < size; i++) {
			java.util.Calendar rightNow = java.util.Calendar.getInstance();
			rightNow.setTime(DataSpliUtil.changeStringToDate(dateEnd));
			java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			rightNow.add(java.util.Calendar.DAY_OF_MONTH, -i * back);// 如果是后退几天，就写
																		// -天数
																		// 例如：
			String date = sim.format(rightNow.getTime()); // 进行时间转换
			if (dateBegin != null && !"".equals(dateBegin)) {
				if (DataSpliUtil.changeStringToDate(date).getTime() < DataSpliUtil.changeStringToDate(dateBegin)
						.getTime()) {
					break;
				}
			}

			listDates.add(date);
		}

		return listDates;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String DateFormatString(Date date) {

		String formatDate = DateFormatUtils.format(date, "yyyy/MM/dd");

		return formatDate;
	}

	public static String StringFormatString(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date date = format.parse(str);
			str = DateFormatUtils.format(date, "yyyy/MM");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String DateFormatString(Date date, String parse) {

		String formatDate = DateFormatUtils.format(date, parse);

		return formatDate;
	}

	public static Date changeStringToDate(String strDate, String parse) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(parse);
			return format.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static int daysBetween(Date smdate, Date bdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int secondBetween(String smdate, String bdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_second = (time2 - time1) / (1000);
			return Integer.parseInt(String.valueOf(between_second));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	public static int secondBetween(Date smdate, Date bdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_second = (time2 - time1) / (1000);

			return Integer.parseInt(String.valueOf(between_second));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * 比较
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int compareDate(Date d1, Date d2) {
		if (d1.getTime() > d2.getTime()) {
			System.out.println("dt1 在dt2前");
			return 1;
		} else if (d1.getTime() < d2.getTime()) {
			System.out.println("dt1在dt2后");
			return -1;
		} else {// 相等
			return 0;
		}
	}

	/**
	 * 大于时间的比较
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean moreThanDate(Date d1, Date d2) {
		if (d1.getTime() >= d2.getTime()) {
			return true;
		} else {// 相等
			return false;
		}
	}

	public static boolean lessThanDate(Date d1, Date d2) {
		if (d1.getTime() <= d2.getTime()) {
			return true;
		} else {// 相等
			return false;
		}
	}

	/**
	 * 上周周一
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastWeekMonday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 获取上周日
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastWeekSunday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime();
	}

	/**
	 * 获取本周周一
	 * 
	 * @param date
	 * @return
	 */
	public static Date getCurrentWeekMonday(Date date) {

		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 获取本周周日
	 * 
	 * @param date
	 * @return
	 */
	public static Date getCurrentWeekSunday(Date date) {

		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(a);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime();
	}

	/**
	 * 上周周一
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBackSomeWeekMonday(Date date, int back) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.add(Calendar.WEEK_OF_YEAR, -back);// 一周
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}
	/**
	 * 上周周日
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBackSomeWeekSunday(Date date, int back) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.add(Calendar.WEEK_OF_YEAR, -back+1);// 一周
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime();
	}

	/**
	 * 得到上月的第一天
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getMonthFirstDay(Date date, int back)  {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.MONTH, -back);
		calendar.add(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}

	/**
	 * 得到上个月的最后一天
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getMonthLastDay(Date date, int back) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		/*calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.MONTH, -back);
		calendar.add(Calendar.DAY_OF_MONTH, 0);*/
		calendar.add(Calendar.MONTH, -back+1);  
		calendar.set(Calendar.DAY_OF_MONTH, 0);  
		return calendar.getTime();
	}

	/**
	 * 两个日期之间的所有月份
	 * 
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
		SimpleDateFormat sdfResult = new SimpleDateFormat("yyyy/MM");
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdfResult.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		return result;
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static boolean compareDate(String DATE1, String DATE2) {
		boolean flag = false;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() >= dt2.getTime()) {
				flag = true;
				return flag;
			}
			if (dt1.getTime() < dt2.getTime()) {
				flag = false;
				return flag;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获取两个时间段的周的时间 得出的周末
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getWeeksBetween(String t1, String t2) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		// 初始化第一个日期
		Calendar cal1 = Calendar.getInstance();
		// 初始化第二个日期，这里的天数可以随便的设置
		Calendar cal2 = Calendar.getInstance();

		// 设置传入的时间格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 指定一个日期
		Date date1 = dateFormat.parse(t1);
		Date date2 = dateFormat.parse(t2);
		// 对 calendar 设置为 date 所定的日期
		cal1.setTime(date1);
		cal2.setTime(date2);
		while (cal1.compareTo(cal2) <= 0) {
			if (cal1.get(Calendar.DAY_OF_WEEK) == 1) {
				result.add(new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime()));
			}
			cal1.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}

	/**
	 * 给两个日期 封装成周的数据 [2017/02/15~2017/02/19, 2017/02/20~2017/02/26,
	 * 2017/02/27~2017/03/05, 2017/03/06~2017/03/08]
	 * 
	 * @param startTime
	 *            2017-02-15 13:43:54
	 * @param endTime
	 *            2017-03-08 13:43:54
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getBuildWeeksBetween(String startTime, String endTime) throws ParseException {
		List<String> listWeeks = getWeeksBetween(startTime, endTime);
		/**
		 * 默认七周会有重复 所以简单处理
		 */
		Set<String> set = new TreeSet<String>();
		List<String> resultWeeks = new ArrayList<String>();
		for (int i = 0; i < listWeeks.size(); i++) {
			String str = listWeeks.get(i);
			/*
			 * if(listWeeks.size()>0&&i==0){
			 * set.add(DateFormatString(changeStringToDate(startTime,
			 * "yyyy-MM-dd HH:mm:ss"
			 * ))+"~"+DateFormatString(changeStringToDate(str, "yyyy-MM-dd")));
			 * if(listWeeks.size()==1&&!listWeeks.get(i).equals(DateFormatString
			 * (changeStringToDate(endTime, "yyyy-MM-dd HH:mm:ss"
			 * ),"yyyy-MM-dd"))){//不是相同的一直
			 * set.add(DateFormatString(getCurrentWeekMonday(changeStringToDate(
			 * endTime, "yyyy-MM-dd HH:mm:ss"
			 * )))+"~"+DateFormatString(changeStringToDate(endTime,
			 * "yyyy-MM-dd HH:mm:ss"))); } continue; }
			 */
			set.add(DateFormatString(getCurrentWeekMonday(changeStringToDate(str, "yyyy-MM-dd"))) + "~"
					+ DateFormatString(changeStringToDate(str, "yyyy-MM-dd")));
			/*
			 * if(listWeeks.size()>0&&i==listWeeks.size()-1){
			 * set.add(DateFormatString(getCurrentWeekMonday(changeStringToDate(
			 * endTime, "yyyy-MM-dd HH:mm:ss"
			 * )))+"~"+DateFormatString(changeStringToDate(endTime,
			 * "yyyy-MM-dd HH:mm:ss"))); continue; }
			 */
		}
		// 不处理不是当前周了
		/*
		 * if(listWeeks.size()<1){ String str =
		 * DateFormatString(changeStringToDate(startTime, "yyyy-MM-dd HH:mm:ss"
		 * ))+"~"+DateFormatString(changeStringToDate(endTime,
		 * "yyyy-MM-dd HH:mm:ss")); set.add(str); }
		 */
		resultWeeks.addAll(set);
		return resultWeeks;
	}
	
	/** 
	   * 得到几天前的时间 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	  public static Date getDateBefore(Date d,int day){  
	   Calendar now =Calendar.getInstance();  
	   now.setTime(d);  
	   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);  
	   return now.getTime();  
	  }  
	    
	  /** 
	   * 得到几天后的时间 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	  public static Date getDateAfter(Date d,int day){  
	   Calendar now =Calendar.getInstance();  
	   now.setTime(d);  
	   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
	   return now.getTime();  
	  }  
	  
      public static Date getCreateTime(Date d){
    	String str = DateUtils.formatDate(DataSpliUtil.changeStringToDate(DataSpliUtil.getAppointDate(d, 1))) ;
  		str +=" 23:59:59";
  		Date newDate  = DataSpliUtil.changeStringToDate(str);
  		//System.out.println(newDate);
  		return newDate;
      } 
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		Date dateNew = DateUtils.getCreateTime(date);
		System.out.println(dateNew);
		
		
		// System.out.println(formatDate(parseDate("2010/3/6")));
		// System.out.println(getDate("yyyy年MM月dd日 E"));
		// long time = new Date().getTime()-parseDate("2012-11-19").getTime();
		// System.out.println(time/(24*60*60*1000));
		// System.out.println(formatDateTime(getLastWeekMonday(new Date())));
		// System.out.println(formatDateTime(getLastWeekSunday(new Date())));
		// System.out.println(formatDateTime(getCurrentWeekMonday(new Date())));
		// System.out.println(formatDateTime(getCurrentWeekSunday(new Date())));
		// String startTime = "2017-02-28 13:43:14";
		// String endTime = "2017-02-28 13:43:54";
		// startTime = formatDateTime(getMonthFirstDay(new Date(), 12));
		// endTime = formatDateTime(getMonthLastDay(new Date(), 1));
		// System.out.println(secondBetween((endTime), startTime));
		// System.out.println(secondBetween(endTime, startTime));
		// getMonthLastDay(new Date(), back)
		// System.out.println(getMonthBetween(startTime, endTime));;
		// System.out.println(getMonthBetween(startTime, endTime));
		/*
		 * List<String> listWeeks = getWeeksBetween(startTime, endTime);
		 * List<String> resultWeeks = new ArrayList<String>(); for (int i = 0; i
		 * < listWeeks.size(); i++) { String str = listWeeks.get(i);
		 * if(listWeeks.size()>0&&i==0){
		 * resultWeeks.add(DateFormatString(changeStringToDate(startTime,
		 * "yyyy-MM-dd HH:mm:ss"))+"~"+DateFormatString(changeStringToDate(str,
		 * "yyyy-MM-dd")));
		 * if(listWeeks.size()==1&&!listWeeks.get(i).equals(DateFormatString(
		 * changeStringToDate(endTime, "yyyy-MM-dd HH:mm:ss"
		 * ),"yyyy-MM-dd"))){//不是相同的一直
		 * resultWeeks.add(DateFormatString(getCurrentWeekMonday(
		 * changeStringToDate(endTime, "yyyy-MM-dd HH:mm:ss"
		 * )))+"~"+DateFormatString(changeStringToDate(endTime,
		 * "yyyy-MM-dd HH:mm:ss"))); } continue; }
		 * resultWeeks.add(DateFormatString(getCurrentWeekMonday(
		 * changeStringToDate(str,
		 * "yyyy-MM-dd")))+"~"+DateFormatString(changeStringToDate(str,
		 * "yyyy-MM-dd"))); if(listWeeks.size()>0&&i==listWeeks.size()-1){
		 * resultWeeks.add(DateFormatString(getCurrentWeekMonday(
		 * changeStringToDate(endTime, "yyyy-MM-dd HH:mm:ss"
		 * )))+"~"+DateFormatString(changeStringToDate(endTime,
		 * "yyyy-MM-dd HH:mm:ss"))); continue; } } if(listWeeks.size()<1){
		 * String str = DateFormatString(changeStringToDate(startTime,
		 * "yyyy-MM-dd HH:mm:ss"
		 * ))+"~"+DateFormatString(changeStringToDate(endTime,
		 * "yyyy-MM-dd HH:mm:ss")); resultWeeks.add(str); }
		 * System.out.println(resultWeeks);
		 */
		
		/*String createTimes0 = "2016-11-11 10:38:55,2016-11-11 13:38:55,2016-11-13 11:38:03,2016-11-14 09:54:19,2016-11-14 17:33:30,2016-11-14 17:37:09,2016-11-22 12:32:01,2016-11-22 15:36:28,2016-11-22 19:40:49,2016-11-23 10:29:05,2016-11-23 11:15:50,2016-11-23 11:19:06,2016-11-23 11:45:09,2016-11-23 16:33:42,2016-11-23 16:35:32,2016-11-23 19:31:30,2016-11-23 19:43:45,2016-11-23 19:44:00,2016-11-23 19:49:25,2016-11-23 20:02:15,2016-11-23 20:48:35,2016-11-23 21:21:01,2016-11-24 09:37:29,2016-11-24 09:49:49,2016-11-24 10:29:27,2016-11-24 10:31:34,2016-11-24 10:32:25,2016-11-24 10:34:58,2016-11-24 10:44:08,2016-11-24 10:44:37,2016-11-24 10:50:26,2016-11-24 11:09:10,2016-11-24 11:11:35,2016-11-24 11:30:27,2016-11-24 11:35:38,2016-11-24 11:53:49,2016-11-24 12:00:48,2016-11-24 12:32:16,2016-11-24 13:43:01,2016-11-24 13:54:47,2016-11-24 14:59:26,2016-11-24 14:59:56,2016-11-24 15:03:16,2016-11-24 15:13:12,2016-11-24 15:34:27,2016-11-24 16:41:54,2016-11-24 16:43:28,2016-11-24 17:05:00,2016-11-29 10:10:05,2016-12-29 10:57:22";
		String createTimes1 = "2017-05-08 11:51:57,2017-05-08 16:05:56";
		String createTimes2 = "2016-11-11 10:38:55";
		String createTimes3 = "2016-11-16 17:05:16,2016-11-16 17:36:13,2016-11-18 14:48:08,2016-11-23 11:51:23";
		String createTimes4 = "2017-05-10 14:20:17,2017-05-15 11:44:18";

		List<String> listTimes = new ArrayList<String>();
		listTimes.add(createTimes0);
		listTimes.add(createTimes1);
		listTimes.add(createTimes2);
		listTimes.add(createTimes3);
		listTimes.add(createTimes4);
		List<UseIntervalVo> listVo = new ArrayList<UseIntervalVo>();
		for (int i = 0; i < listTimes.size(); i++) {
			UseIntervalVo vo = new UseIntervalVo();
			vo.setCreateTimes(listTimes.get(i));
			listVo.add(vo);
		}

		List<List<Integer>> listIntervalSub = new ArrayList<List<Integer>>();*/
		/*for (UseIntervalVo useIntervalVo : listVo) {
			String[] strArr = useIntervalVo.getCreateTimes().split(",");

			List<Integer> intervalList = new ArrayList<Integer>();
			if (strArr.length > 0) {
				if (strArr.length == 1)
					// 首次使用
					intervalList.add(judgmentUseIntervalType(-1));
				else
					for (int i = 0; i < strArr.length; i++) {
						String startTimes = strArr[i];
						String endTimes = "";
						if (i + 1 >= strArr.length) {
							Date date = new Date();
							endTimes = formatDateTime(date);
						} else {
							endTimes = strArr[i + 1];
						}
						int sec = secondBetween(startTimes, endTimes);
						intervalList.add(judgmentUseIntervalType(sec));
					}
			}
			int[] x = new int[11];

			x = getO2oUseLoyaltyList(intervalList, x);
			Integer[] is = ArrayUtils.toObject(x);
			List<Integer> list = Arrays.asList(is);
			listIntervalSub.add(list);
		}*/

		/*List<Integer> listIntervalTotal = new ArrayList<Integer>();
		int j = 0;
		for (; j < UseIntervalEnums.values().length;) {// 遍历数组里面的list
			int sum = 0;
			for (int k = 0; k < listIntervalSub.size(); k++) {
				sum += listIntervalSub.get(k).get(j);// 当前全部加完
			}
			listIntervalTotal.add(sum);
			j++;
		}

		System.out.println(listIntervalTotal);*/

	}

	/**
	 * 统计分组数次
	 * @param intervalList
	 * @param x
	 * @return
	 */
	private static int[] getO2oUseLoyaltyList(List<Integer> intervalList, int[] x) {
		for (Integer integer : intervalList) {
			x[integer - 1] += 1;
		}
		return x;
	}

	/** 
	 * 获取今天还剩下多少秒 
	 * @return 
	 */  
	public static int surplusMiao(){  
	    Calendar curDate = Calendar.getInstance();  
	    Calendar tommorowDate = new GregorianCalendar(curDate  
	            .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate  
	            .get(Calendar.DATE) + 1, 0, 0, 0);  
	    return (int)(tommorowDate.getTimeInMillis() - curDate .getTimeInMillis()) / 1000;  
	}  
}
