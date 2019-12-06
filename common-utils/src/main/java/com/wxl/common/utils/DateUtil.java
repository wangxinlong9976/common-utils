package com.wxl.common.utils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: DateUtil
 * @Description: TODO
 * @author lenovo
 * @date 2019年12月5日
 *
 */
public class DateUtil {
	public static SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat timeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	public static Map<String,String> firstAndLastDayOfWeek(){
//		Map<String,String> map = new HashMap<>();
//		Calendar calen = Calendar.getInstance();
//		System.out.println(calen.toString());
//		return null;
//	}
	
	
	
//	public static String getTimeStrap(String time) {
//		if(time==null || time.equals("")) {
//			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
//		}
//		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//	}
	
	
	
//	@SuppressWarnings("static-access")
//	public static String getMonthLastDay(String time) {
////		Calendar instance = Calendar.getInstance(pars);
//	}
	
	
	
	public static boolean isToday(Date date) {
		String theDay = dateFormat.format(date);
		String nowDay = dateFormat.format(new Date());
//		dateFormat.parse(theDay);
		return theDay.equals(nowDay);
	}
	/**
	 * 
	 * @Title: getFirstDayOfMonth
	 * @Description: TODO 2019-12-06 18:44:25 >>> 2019-12-01 00:00:00
	 * @param date
	 * @return
	 * @throws ParseException    
	 * String    
	 *
	 */
	public static Date getFirstDayOfMonth(String date) throws ParseException {
		//第一种
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		
		Date date1 = timeFormat.parse(date);
		Calendar instance = Calendar.getInstance();
		instance.setTime(date1);
		instance.set(Calendar.DAY_OF_MONTH, 1);
		instance.set(Calendar.HOUR, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		return instance.getTime();
	}
	
	/**
	 * 
	 * @Title: getLastDayOfMonth
	 * @Description: TODO   2019-12-06 18:44:25 >>> 2019-12-31 23:59:59
	 * @param date
	 * @return
	 * @throws ParseException    
	 * String    
	 *
	 */
	public static String getLastDayOfMonth(String date) throws ParseException {
		Date date1 = timeFormat.parse(date);
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.AM_PM, Calendar.AM);
		instance.add(Calendar.MONTH, 1);
		instance.setTime(getFirstDayOfMonth(timeFormat.format(instance.getTime())));
		instance.add(Calendar.SECOND, -1);
		return timeFormat.format(instance.getTime());
	}
	
	/**
	 * 		
	 * @Title: isThisWeek
	 * @Description: TODO		判断一个日期是否在本周
	 * @param date
	 * @return
	 * @throws ParseException    
	 * boolean    
	 *
	 */
	public static boolean isThisWeek(String date) throws ParseException {
		Date date1 = dateFormat.parse(date);
		Calendar instance = Calendar.getInstance();
		int thisYear = instance.get(Calendar.YEAR);
		int thisWeek = instance.get(Calendar.WEEK_OF_YEAR);
		instance.setTime(date1);
		int desYear = instance.get(Calendar.YEAR);
		int desWeek = instance.get(Calendar.WEEK_OF_YEAR);
		if(thisYear!=desYear || desWeek!=thisWeek) {
			return false;
		}
		return true;
	}
}
