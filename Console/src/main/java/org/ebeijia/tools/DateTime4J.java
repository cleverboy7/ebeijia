package org.ebeijia.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * DateTime for JAVA (日期时间处理)
 * 
 * @Author jinjian.feng
 * @version 1.0
 */
public class DateTime4J {


	public static Date formatDtDate(String date) throws Exception {
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMddhhmm");
		return dtFormat.parse(date);
	}

	public static String dtDateFormat(Date date) throws Exception {
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMddhhmm");
		return dtFormat.format(date);
	}

	public static Date formatParseDate(String date) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(date);
	}

	public static Date formatParseDateString(String date) throws Exception {
		SimpleDateFormat dateStringFormat = new SimpleDateFormat("yyyyMMdd");
		return dateStringFormat.parse(date);
	}

	public static String dateTimeFormat(Date date) {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		return datetimeFormat.format(date);
	}

	public static String dateFormat(Date date) {
		SimpleDateFormat dateStringFormat = new SimpleDateFormat("yyyyMMdd");
		return dateStringFormat.format(date);
	}

	public static String timeFormat(Date date) {
		SimpleDateFormat timeStringFormat = new SimpleDateFormat("HHmmss");
		return timeStringFormat.format(date);
	}

	public static Date formatParseDateTime(String date) throws Exception {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		return datetimeFormat.parse(date);
	}

	public static String onlyTime(Date date) {
		SimpleDateFormat onlyTimeFormat = new SimpleDateFormat("hh:mm");
		return onlyTimeFormat.format(date);
	}

	public static String onlyDate(Date date) {
		SimpleDateFormat onlyDateFormat = new SimpleDateFormat("MM-dd");
		return onlyDateFormat.format(date);
	}

	public static String onlyYear(Date date) {
		SimpleDateFormat onlyYearFormat = new SimpleDateFormat("yyyy");
		return onlyYearFormat.format(date);
	}

	public static String yearDate(Date date) {
		SimpleDateFormat yearDateFormat = new SimpleDateFormat("yy-Mm-dd");
		return yearDateFormat.format(date);
	}

	public static Date getYesterDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = calendar.getTime();
		return yesterday;
	}

	// private static void log(Object aMsg) {
	// _logger.info(String.valueOf(aMsg));
	// }

	// private void test1() {
	// DateTime now = DateTime.now(TimeZone.getDefault());
	// String result = now.format("YYYY-MM-DD hh:mm:ss");
	// log("默认时区的当前时间 : " + result);
	// }
	//
	// private void whenIs90DaysFromToday() {
	// DateTime today = DateTime.today(TimeZone.getDefault());
	// log("90天后的日子是 : " + today.plusDays(90).format("YYYY-MM-DD"));
	// }
	//
	// private void whenIs3Months5DaysFromToday() {
	// DateTime today = DateTime.today(TimeZone.getDefault());
	// DateTime result = today.plus(0, 3, 5, 0, 0, 0, 0,
	// DateTime.DayOverflow.FirstDay);
	// log("3个月后多5天的日子是 : " + result.format("YYYY-MM-DD"));
	// }

	public static String firstOfWeek(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		return imptimeBegin;
	}

	public static String laseOfWeek(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		return imptimeEnd;
	}

	/**
	 * 当月第一天
	 *
	 * @return
	 */
	public static String getFirstDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		return str.toString();
	}

	// /**
	// * 当月最后一天
	// *
	// * @return
	// */
	// public static String getLastDay() {
	// SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	// Calendar calendar = Calendar.getInstance();
	// Date theDate = calendar.getTime();
	// String s = df.format(theDate);
	// StringBuffer str = new StringBuffer().append(s);
	// return str.toString();
	// }

	private SimpleDateFormat sysTimeFormat = new SimpleDateFormat("HHmmss");
	private SimpleDateFormat showDateFormatZHCN = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分ss秒");

	/**
	 * 获得系统当前日期时间
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat sysDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		return sysDateFormat.format(new Date());
	}

	/**
	 * 获得系统当前日期
	 * 
	 * @return
	 */
	public static String getSysDateFormat8() {
		SimpleDateFormat sysDateFormat8 = new SimpleDateFormat(
				"yyyyMMdd");
		return sysDateFormat8.format(new Date());

	}

	/**
	 * 获得系统当前日期后一个月 日期时间
	 * 
	 * @return
	 */
	public String getBehindDateTime() {
		SimpleDateFormat sysDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, 1); // 将当前日期加一个月
		return sysDateFormat.format(c.getTime());
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @return
	 */
	public String getCurrentTime() {
		return sysTimeFormat.format(new Date());
	}

	/**
	 * 获得系统当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sysDateFormat8 = new SimpleDateFormat(
				"yyyyMMdd");
		return sysDateFormat8.format(new Date());
	}

	/**
	 * 获得向前系统时间用于显示
	 * 
	 * @return
	 */
	public String getCurrentDateTimeForShow() {
		SimpleDateFormat showDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return showDateFormat.format(new Date());
	}

	/**
	 * 获得中文系统时间
	 * 
	 * @return
	 */
	public String getCurrentDateTimeZHCN() {
		return showDateFormatZHCN.format(new Date());
	}

	/***
	 * 将 日期字符串 转换成 YYYY-MM-DD HH:mm:ss 格式
	 * 
	 * @param val
	 * @return
	 * @throws ParseException
	 */
	public String formatDateTime(String val) throws ParseException {
		SimpleDateFormat showDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sysDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		return showDateFormat.format(sysDateFormat.parse(val));
	}

	/**
	 * 计算两个时间之间相差的秒数
	 * 
	 * @param postTime
	 * @param now
	 * @return
	 */
	public static long betweenTime(Date postTime, Date now) {
		long between = (now.getTime() - postTime.getTime()) / 1000;
		return between;
	}
	
	
	public static boolean isBetweenTime(String postTime, String now,int minute) throws ParseException {
		SimpleDateFormat sysDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		Date date1 = sysDateFormat.parse(postTime);		 
		Date date2 = sysDateFormat.parse(now);		 		 
		boolean flag = false;
		long between = (date1.getTime() - date2.getTime()) / 1000;
		if(between < minute*60){
			flag = true;
		}
		return flag;
	}
	
	// 获取指定日期之后的日期
	public static String getBehindDateTime(int num) {
		SimpleDateFormat sysDateFormat8 = new SimpleDateFormat(
				"yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, num);
		return sysDateFormat8.format(c.getTime());
	}

	// 获取T-1的日期
	public static String getDateTimeP() {
		SimpleDateFormat sysDateFormat8 = new SimpleDateFormat(
				"yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		return sysDateFormat8.format(c.getTime());
	}

	// 获取指定日期T-1的
	public static String getDateBefore(Date date) {
		SimpleDateFormat sysDateFormat8 = new SimpleDateFormat(
				"yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		return sysDateFormat8.format(c.getTime());
	}

	// 获取指定日期T-1的
	public static String getWeekBefore(Date date) {
		SimpleDateFormat sysDateFormat8 = new SimpleDateFormat(
				"yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.WEEK_OF_YEAR, -1);
		return sysDateFormat8.format(c.getTime());
	}
	// 获取指定日期T-1的
	public static String getMonthBefore(Date date) {
		SimpleDateFormat sysDateFormat8 = new SimpleDateFormat(
				"yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		return sysDateFormat8.format(c.getTime());
	}
		
	public static boolean isTrueDate(String date) {
		return false;

	}

	

	public static String addDate(String now, int day) {
		Calendar fromCal = Calendar.getInstance();
		SimpleDateFormat dateStringFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = dateStringFormat.parse(now);
			fromCal.setTime(date);
			fromCal.add(Calendar.DATE, day);
			return (dateStringFormat.format(fromCal.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 比较两个相同格式时间的大小
	 * 
	 * @param time1
	 * @param time2
	 * @return <0 第一个时间小于第二个时间， =0 两个时间相同，>0第一个时间大于第二个时间
	 */
	public static int compareTime(String time1, String time2) {
		return time1.compareTo(time2);
	}

	/**
	 * 得到当前日期前20天内所有的日期
	 * 
	 * @param date
	 * @return
	 */
	public static List<String> getDates30() {
		try {
			String date =DateTime4J.getCurrentDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			long time = sdf.parse(date).getTime();
			List<String> list = new ArrayList<String>();
			list.add(date);
			for (int i = 0; i < 20; i++) {
				Date date2 = new Date();
				date2.setTime(time - (24 * 3600000));
				list.add(sdf.format(date2));
				time = date2.getTime();
			}
			return list;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得两个时间之间的所有月份
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getAllMonths(String start, String end) {
		List<String> list = new ArrayList<String>();
		String temp = end; // 从最大月份开始
		while (temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0) {
			list.add(temp); // 首先加上最大月份,接着计算下一个月份
			int year = Integer.valueOf(temp.substring(0, 4));
			int month = Integer.valueOf(temp.substring(4, 6)) - 1;
			if (month == 0) {
				month = 12;
				year--;
			}
			if (month < 10) {// 补0操作
				temp = year + "0" + month;
			} else {
				temp = Integer.toString(year) + Integer.toString(month);
			}
		}
		return list;
	}

	/**
	 * 获得两个时间之间所有的季度
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getAllQuarters(String start, String end) {
		List<String> list = new ArrayList<String>();
		String temp = end; // 从最大季度开始
		while (temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0) {
			list.add(temp); // 首先加上最大季度,接着计算下一个季度
			int year = Integer.valueOf(temp.substring(0, 4));
			int quarter = Integer.valueOf(temp.substring(4, 5)) - 1;
			if (quarter == 0) {
				quarter = 4;
				year--;
			}
			temp = Integer.toString(year) + Integer.toString(quarter);
		}
		return list;
	}

	/**
	 * 判断当前日期是星期几
	 * 
	 * @param pTime
	 *            修要判断的时间
	 * @return dayForWeek 判断结果
	 * @Exception 发生异常
	 */
	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 根据月份得到对应的季度
	 * 
	 * @param str
	 * @return
	 */
	public static String getQuarter(String str) {
		if ("01".equals(str) || "02".equals(str) || "03".equals(str)) {
			return "1";
		} else if ("04".equals(str) || "05".equals(str) || "06".equals(str)) {
			return "2";
		} else if ("07".equals(str) || "08".equals(str) || "09".equals(str)) {
			return "3";
		} else {
			return "4";
		}
	}

	/**
	 * 获得前一天的当月最后一天
	 * 
	 * @return
	 */
	public static String lastDayOfMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(c.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		String s = df.format(cal.getTime());
		StringBuffer str = new StringBuffer().append(s);
		return str.toString();
	}
	
	/**
	 * 获得上个月最后一天
	 * @return
	 */
	public static String lastDayOfMonth2() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		Date strDateTo = calendar.getTime();  
		return df.format(strDateTo);
	}

	/**
	 * 获得前一天的当月第一天
	 * 
	 * @return
	 */
	public static String firstDayOfMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(c.getTime());
		Date theDate = cal.getTime();
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		return str.toString();
	}

	public static String genSunday(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date d  = format.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return format.format(cal.getTime());
	}
	
	public static String genSaturday(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date d = format.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return format.format(cal.getTime());
	}
	/**
	 * 上周星期六
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static String lastSaturday(String date) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date d = format.parse(genSaturday(date));
		Calendar now = Calendar.getInstance();     
	    now.setTime(d);     
	    now.set(Calendar.DATE, now.get(Calendar.DATE) - 7);     
	    return format.format(now.getTime());     
	}
	
	public static String getNextYearDay(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+1);
		return format.format(cal.getTime());
	}
	
	public static String getSecondYearDay(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+2);
		return format.format(cal.getTime());
	}
	
	public static String getTime(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		Long t = Long.parseLong(time);
		Date d = new Date(t);
		GregorianCalendar gc = new GregorianCalendar();   
	    gc.setTime(d);
	    return format.format(gc.getTime());
	}
	
	public static Long millisTime() {  
//		Date dt= new Date();  
//		Long time= dt.getTime();
		//这就是距离1970年1月1日0点0分0秒的毫秒数  
		return System.currentTimeMillis();
		//与上面的相同   
	}
	
	/**
	 * 根据时间戳转换日期
	 * @param zhicheng.xu
	 * @return
	 */
	public static String timestampFormat(long date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");  
		String sd = sdf.format(new Date(date));
		return sd;  
	}
	
}