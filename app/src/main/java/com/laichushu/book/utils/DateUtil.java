/**
 * @(#)UserAction.java	05/22/2014
 * 
 * Copyright (c) 2014 SAILSORS INSTRUMENTS LTD.,BEIJING.All rights reserved.
 * Created by 2014-05-22
 */
package com.laichushu.book.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.util.Log;


/**
 * @title :日期转换工具
 * @description :包含常用日期格式转换操作
 * @author: 吴理琪
 * @data: 2014-5-15
 */
public class DateUtil {

	/** 将给定的date转换为string, 格式为 yyyy-MM-dd HH:mm:ss * */
	public static String getCurrentDateTime(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(date));
	}
public static Date getDate(String time){
	Date date = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	try {
		date = sdf.parse(time);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return date;
}
	/** 将给定的date转换为string, 格式为 yyyy-MM-dd HH:mm:ss * */
	public static String getCurrentDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/** 得到系统当前日期及时间 格式为 yyyy-MM-dd HH:mm:ss * */
	public static String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/** 得到系统当前日期及时间 格式为 yyyy-MM-dd **/
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	/** 将毫秒数转换成分钟 **/
	public static String second2Mimute(long duration) {  
	    String time = "" ;  
	    long minute = duration / 60000 ;  
	    long seconds = duration % 60000 ;  
	      
	    long second = Math.round((float)seconds/1000) ;  
	          
	    if( minute < 10 ){  
	        time += "0" ;  
	    }  
	    time += minute+":" ;   
	          
	    if( second < 10 ){  
	        time += "0" ;  
	    }  
	    time += second ;  
	          
	    return time ;  
	}  
	
	/**
	 * 
	 * 
	 * @param d
	 *            日期
	 * @param day
	 *            天数
	 * @return
	 */
	public static Date addDate(Date d, long day) {
		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		return new Date(time);
	}

	/**
	 * @param d
	 *            日期
	 * @param hour
	 *            小时数
	 * @return
	 */
	public static Date addHour(Date d, long hour) {
		long time = d.getTime();
		hour = hour * 60 * 60 * 1000;
		time += hour;
		return new Date(time);
	}

	/**
	 * 判断当前时间是白天还是晚上。 （气象上白天、夜间是一个较为笼统的概念， 但如果你了解预报的起报时间、实效等概念，再做分析的话， 你会得出一下结论：
	 * 比如“今天白天”指北京时间上午8点到晚上8点， 如“今天夜间”指北京时间晚上八点到明天的8点。
	 * 又如“明天白天到夜间是指，明天的8点到后天8点这一段时间。）
	 * 
	 * @return
	 */
	public static boolean checkDayOrNight() {
		try {

			// 获取当天的日期，格式为：yyyy-MM-dd
			String date = getCurrentDate();
			String time8Str = date + " 08:00:00";
			// yyyy-MM-dd HH:mm:ss
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
			Date date8 = sdf.parse(time8Str);
			// 上午8点
			long time8 = date8.getTime();
			// 晚上8点
			long time20 = time8 + 12 * 60 * 60 * 1000;

			long currt = System.currentTimeMillis();
			if (currt >= time8 && currt <= time20) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			ToastUtil.showToast("检查是否是白天出现异常");
			e.printStackTrace();

			return true;
		}

	}
	
	private static SimpleDateFormat dfDate=new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
	/**
	 * 得到指定年月的日期范围 
	 * 该日期范围包括“当月1号 到 当月最后一天+次月部分”
	 * 比如 2015-11-01|2016-01-09
	 * 也就是万年历中某月(7列*6行)的第一天至最后一天
	 * @return 时间范围，格式如 "2015-11-01|2016-01-09"
	 */
	public static String getDateArea(int year, int month){
		int dayOfWeek = 0;
		boolean isLeapyear = false;//是否为闰年
		int daysOfMonth = 0;      //某月的天数
		int lastDaysOfMonth = 0;  //上一个月的总天数
		String startDayOfMonth,endDayOfMonth;//一个月(包含前月一部分、本月、下月部分)的起始时间和结束时间
		boolean hasPreMonthDay = false;//标记这个月的显示界面中是否有上个月的内容
		String week[] = {"日","一","二","三","四","五","六"};
		boolean isFirstComeCurr = true;//标记是否是第一次进入当前月的逻辑代码，如果是则true(默认)
		String[] dayNumber = new String[49];  //一个gridView中的日期存入此数组中
		SpecialCalendar sc = new SpecialCalendar();
		
		dayOfWeek = sc.getWeekdayOfMonth(year, month);      //某月第一天为星期几
		isLeapyear = sc.isLeapYear(year);
		daysOfMonth = sc.getDaysOfMonth(isLeapyear, month);  //某月的总天数
		lastDaysOfMonth = sc.getDaysOfMonth(isLeapyear, month-1);  //上一个月的总天数
		int j = 1;
		String lunarDay = "";
		
		int temp=0;
		int flagPre=0,flagCurr=0/*flagNext=0*/;//分别代表当前显示页的 "前一个月"、"本月"、"下月" 的最后一天的角标
		for (int i = 0; i < dayNumber.length; i++) {
			// 周一
			if(i<7){
				dayNumber[i]=week[i]+"."+" ";
			}
			else if(i < dayOfWeek+7){  //前一个月
				hasPreMonthDay = true;
				temp = lastDaysOfMonth - dayOfWeek+1-7;
				dayNumber[i] = (temp + i)+"."+lunarDay;
				flagPre = i;
			}else if(i < daysOfMonth + dayOfWeek+7){   //本月
				if(!hasPreMonthDay && isFirstComeCurr){
					flagCurr = i;
					isFirstComeCurr = false;
				}
//				String day = String.valueOf(i-dayOfWeek+1-7);   //得到的日期
				dayNumber[i] = i-dayOfWeek+1-7+"."+lunarDay;
				
			}else{   //下一个月
				dayNumber[i] = j+"."+lunarDay;
				j++;
//				flagNext = j;
			}
		}
        
        ////////////////////////得到本月显示页的起始日期和结束日期////////////////////////
        DecimalFormat df = new DecimalFormat("00");
        if(hasPreMonthDay){
        	if(month==1)
        		startDayOfMonth = (year-1)+"-"+ df.format(12)+"-"+df.format(temp+flagPre);//crb
        	else
        		startDayOfMonth = year+"-"+ df.format(month-1)+"-"+df.format(temp+flagPre);//crb
        }else{
        	startDayOfMonth = year+"-"+df.format(month)+"-"+df.format(flagCurr-dayOfWeek+1-7);
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).parse(startDayOfMonth);
				String formatedDate = dfDate.format(new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000));
				startDayOfMonth = formatedDate;
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        if(month==12){
        	endDayOfMonth = year+1 + "-"+df.format(1)+"-"+df.format(j);
        }else{
        	endDayOfMonth = year + "-"+df.format(month+1)+"-"+df.format(j);
        }
        try{
        	Date dateStart = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).parse(startDayOfMonth);
        	Date dateEnd = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).parse(endDayOfMonth);
        	startDayOfMonth = dfDate.format(new Date(dateStart.getTime()+ 1 * 24 * 60 * 60 * 1000));
        	endDayOfMonth =   dfDate.format(new Date(dateEnd.getTime() -  1 * 24 * 60 * 60 * 1000));
        }catch(ParseException e){
        	e.printStackTrace();
        }
        Log.i("dayAutil", startDayOfMonth+"|"+endDayOfMonth );
        String result = startDayOfMonth+"|"+endDayOfMonth;
        /////////////////////上面打印 获取到了当前显示月份页面的1号--月终+下月局部日期，如2016-02-01--->2016-03-12
        hasPreMonthDay = false;
        return result;
	}

}
