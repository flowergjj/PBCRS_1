/**
 * Copyright 2014 (C) PANLAB ，All Rights Reserved.
 */
package com.hkbank.pbcrs.util;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>Title: 基础类</p>
 * <p>Description: 日期转换</p>
 * <p>Company:  </p>
 
 * @version 1.0
 */
public class DateUtils {

    public static final String DATE_YMDHMS="yyy-MM-dd HH:mm:ss";
	 /**
     * Date Style
     */
    public static final String DATESTYLE = "yyyyMMddHHmmss";

    /**
     * Date Style for Extention
     */
    public static final String DATESTYLE_EX = "yyyy-MM-dd_HH-mm-ss";
    
    /**
     * Date Style for Extention
     */
    public static final String DATESTYLE_ = "yyyy-MM-dd";

    /**
     * Date Style for Year & Month
     */
    public static final String DATESTYLE_YEAR_MONTH = "yyyyMM";

    /**
     * Date Style for Short
     */
    public static final String DATESTYLE_SHORT = "yyyyMMdd";

    /**
     * Date Style for Extention
     */
    public static final String DATESTYLE_SHORT_EX = "yyyy/MM/dd";

    /**
     * Date Style for Year & Month Extention
     */
    public static final String DATESTYLE_YEAR_MONTH_EX = "yyyy/MM";

    /**
     * Date Style for detail
     */
    public static final String DATESTYLE_DETAIL = "yyyyMMddHHmmssSSS";

    
//    static long now = System.currentTimeMillis();
//    public static Date CurrTime = new Date(now);

    /**
     * 日期转化为字符串
     * @param date 时间
     * @return yyyy-MM-dd HH:mm:ss 格式化的时间字符串
     */
    public static String dateToString(Date date) {
        if(date==null) return "";
        return FormatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 日期转化为字符串
     * @param date 时间
     * @return yyyy-MM-dd 格式化的时间字符串
     */
    public static String dateToStringShort(Date date) {
        if(date==null) return "";
        return FormatDate(date, "yyyy-MM-dd");
    }

    /**
     * 计算两个日期差（毫秒）
     * @param date1 时间1
     * @param date2 时间2
     * @return 相差毫秒数
     */
    public static long diffTwoDate(Date date1, Date date2) {
        long l1 = date1.getTime();
        long l2 = date2.getTime();
        return (l1 - l2);
    }

    /**
     * 计算两个日期差（毫秒）
     * @param date1 时间1
     * @param date2 时间2
     * @return 相差毫秒数
     */
    public static int diffMinterDate(Date date1, Date date2) {
        if(date1==null||date2==null){
            return 0;
        }
        long l1 = date1.getTime();
        long l2 = date2.getTime();
        int deff=Integer.parseInt(""+(l1-l2)/1000/60);
        return deff;
    }
    
    
    
    /**
     * 计算两个日期差（天）
     * @param date1 时间1
     * @param date2 时间2
     * @return 相差天数
     */
    public static int diffTwoDateDay(Date date1, Date date2) {
        long l1 = date1.getTime();
        long l2 = date2.getTime();
        int diff = Integer.parseInt(""+(l1 - l2)/3600/24/1000);
        return diff;
    }

    /**
     * 对日期进行格式化
     * @param date 日期
     * @param sf 日期格式
     * @return 字符串
     */
    public static String FormatDate(Date date, String sf) {
        if(date==null) return "";
        SimpleDateFormat dateformat = new SimpleDateFormat(sf);
        return dateformat.format(date);
    }

    /**
     * 取得当前系统日期
     * @return yyyy-MM-dd
     */
    public static String getCurrDate() {
        Date date_time = new Date();
        return FormatDate(date_time, "yyyy-MM-dd");
    }

    //取系统时间时一定要用这个方法，否则日期可能不动
    public static Date getCurrDateTime(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date etl_date;
		try {
			etl_date = sdf.parse(getCurrDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date(System.currentTimeMillis());
		}
        return etl_date;
    }
    
    //取系统时间时一定要用这个方法，否则日期可能不动带是分秒
    public static Date getCurrDateTimeSS(){
        return new Date(System.currentTimeMillis());
    }

    /**
     * 返回格式化时间
     * @param fmt
     * @return
     */
    public static String getCurrDateTime(String fmt){
        return FormatDate(new Date(System.currentTimeMillis()),fmt);
    }

    /**
     * 取得当前系统时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrTime() {
        Date date_time = new Date();
        return FormatDate(date_time, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 10位时间转为8时间
     * @param str
     * @return
     */
    public static String getDate10to8(String str){
    	String y = str.substring(0,4);
    	String m = str.substring(5,7);
        String d = str.substring(8,10);
    	return y+m+d;
    }

    /**
     * 8位时间转为10时间
     * @param str
     * @return
     */
    public static String getDate8to10(String str){
    	String y = str.substring(0,4);
    	String m = str.substring(4,6);
        String d = str.substring(6,8);
    	return y+"-"+m+"-"+d;
    }

	/**
     * 取得日期的天份
     * @param date 日期
     * @return dd 天字符串
     */
    public static String getDay(Date date) {
        return FormatDate(date, "dd");
    }
	
	
	/**
     * 取得日期的小时
     * @param date 日期
     * @return hh 小时字符串
     */
    public static String getHour(Date date) {
        return FormatDate(date, "HH");
    }

        /**
		 * 取得日期的分钟
		 * @param date 时间
		 * @return mm 分钟字符串
		 */
		public static String getMinute(Date date) {
		    return FormatDate(date, "mm");
		}



    /**
     * 取得日期的月份
     * @param date 日期
     * @return mm 月份字符串
     */
    public static String getMonth(Date date) {
        return FormatDate(date, "MM");
    }

    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * 取得时间的秒
     * @param date 时间
     * @return ss 秒字符串
     */
    public static String getSecond(Date date) {
        return FormatDate(date, "ss");
    }

    /**
     *根据年、月取得月末的日期
     * @param year 年
     * @parm month 月
     * @return time  返回日期格式"yyyy-mm-dd"
     */
    public static String getTime(String year,String month){
        String time="";
        int len=31;
        int iYear=Integer.parseInt(year);
        int iMonth=Integer.parseInt(month);
        if(iMonth==4 || iMonth==6 || iMonth==9 || iMonth==11)
            len=30;
        if(iMonth==2){
            len=28;
            if((iYear%4==0 && iYear%100==0 && iYear%400==0) || (iYear%4==0 && iYear%100!=0)){
                len=29;
            }
        }
        time=year+"-"+month+"-"+String.valueOf(len);
        return time;
    }

    /**
     * 取得日期的年份
     * @param date 日期
     * @return yyyy 年份字符串
     */
    public static String getYear(Date date) {
        return FormatDate(date, "yyyy");
    }


    /**
     * 字符串转换为日期
     * @param dateString yyyy-MM-dd HH:mm:ss
     * @return 日期
     */
    public static Date stringToDate(String dateString) {
        if(dateString==null || dateString.trim().length()==0) return null;
        String datestr = dateString.trim();
        
    	String sf = "yyyy-MM-dd HH:mm:ss";
        Date dt = stringToDate(datestr, sf);
        if(dt==null) dt = stringToDate(datestr, "yyyy-MM-dd");
        if(dt==null) dt = stringToDate(datestr, "MM-dd HH:mm:ss");
        if(dt==null) dt = stringToDate(datestr, "dd HH:mm:ss");
        if(dt==null) dt = stringToDate(datestr, "yyyyMMdd");
        return dt;
    }
    /** 字符串转换为日期
     *  @param dateString 日期格式字符串
     *  @param  sf 日期格式化定义
     *  @return 转换后的日期
     */
    public static Date stringToDate(String dateString, String sf) {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat sdf = new SimpleDateFormat(sf);
        Date dt = sdf.parse(dateString, pos);
        return dt;
    }
    /**
     * 字符串转换为日期
     * @param dateString yyyy-MM-dd
     * @return 日期
     */
    public static Date stringToDateShort(String dateString) {
        String sf = "yyyy-MM-dd";
        Date dt = stringToDate(dateString, sf);
        return dt;
    }
    public DateUtils() {
    }
    /**
	 * 取得日以上粒度起始时间
	 * @param granularity 粒度
	 * @param statisticDate 结束时间
	 * @return 起始时间
	 */
	public static String getBeginDate(String granularity,String statisticDate){
		String beginDate = "";
		Date date = stringToDateShort(statisticDate);
		Date beginDateTemp  = null;
		if(granularity.equals("1")){//日
			beginDateTemp = date;
		}
		if(granularity.equals("2")){//周
			beginDateTemp = getWeekBegin(date);
		}
		if(granularity.equals("3")){//旬
			beginDateTemp = getPeriodBegin(date);
		}
		else if(granularity.equals("4")){//月
			beginDateTemp = getMonthBegin(date);
		}
		else if(granularity.equals("5")){//季
			beginDateTemp = getSeasonBegin(date);
		}
		else if(granularity.equals("6")){//半年
			beginDateTemp = getHalfYearBegin(date);
		}
		else if(granularity.equals("7")){//年
			beginDateTemp = getYearBegin(date);
		}
		beginDate = dateToStringShort(beginDateTemp);
		return beginDate;
	}

    /**
     *
     * @param currentTime 计算日期
     * @param type 偏移的类别
     * @param iQuantity 偏移数量
     * @return 偏移后的时间串
     */
    public String getDateChangeALL(String currentTime, String type,
                                   int iQuantity) {
      Date curr = null;
      String newtype = "";
      if(currentTime.length()==10){
        curr = stringToDateShort(currentTime);
      }
      if(currentTime.length()>10){
        curr = stringToDate(currentTime);
      }
      
      //    日
      if(type.equals("1")){
          iQuantity = iQuantity;
          newtype = "d";
      }
      //    周，按照7天计算
      else if(type.equals("2")){
          iQuantity = iQuantity*7;
          newtype = "d";
      }
//    旬，按照10天计算
      else if(type.equals("3")){
          iQuantity = iQuantity*10;
          newtype = "d";
      }
      //月
      else if(type.equals("4")){
          iQuantity = iQuantity;
          newtype = "m";
      }      
//    旬，按照3个月计算
      else if(type.equals("5")){
        iQuantity = iQuantity*3;
        newtype = "m";
      }
      //半年，按照六个月计算
      else if(type.equals("6")){
          iQuantity = iQuantity*6;
          newtype = "m";
      }
      //年
      else if(type.equals("7")){         
          newtype = "y";
      }
      else{
    	  iQuantity = iQuantity;
          newtype = "d";
      }
      
      Date change = getDateChangeTime(curr, newtype, iQuantity);
      
//      if(!type.equals("d")){
//      change = getMonthEnd(change);
//      }
      
      return dateToStringShort(change);
    }

    /**
     *
     * @param currentTime 计算的日期
     * @param type 偏移的类别
     * @param iQuantity 偏移数量
     * @return 偏移后的时间
     */
    public static Date getDateChangeTime(Date currentTime, String type,
                                  int iQuantity) {
        int year = Integer.parseInt(FormatDate(currentTime, "yyyy"));
        int month = Integer.parseInt(FormatDate(currentTime, "MM"));
        //月份修正
        month=month-1;
        int day = Integer.parseInt(FormatDate(currentTime, "dd"));
        int hour = Integer.parseInt(FormatDate(currentTime, "HH"));
        int mi = Integer.parseInt(FormatDate(currentTime, "mm"));
        int ss = Integer.parseInt(FormatDate(currentTime, "ss"));
        GregorianCalendar gc = new GregorianCalendar(year, month, day,
                hour, mi, ss);
        //月份修正
        //gc.add(GregorianCalendar.MONTH, -1);
        if (type.equalsIgnoreCase("y")) {
            gc.add(GregorianCalendar.YEAR, iQuantity);
        }
        else if (type.equalsIgnoreCase("m")) {
            gc.add(GregorianCalendar.MONTH, iQuantity);
        }
        else if (type.equalsIgnoreCase("d")) {
            gc.add(GregorianCalendar.DATE, iQuantity);
        }
        else if (type.equalsIgnoreCase("h")) {
            gc.add(GregorianCalendar.HOUR, iQuantity);
        }
        else if (type.equalsIgnoreCase("mi")) {
            gc.add(GregorianCalendar.MINUTE, iQuantity);
        }
        else if (type.equalsIgnoreCase("s")) {
            gc.add(GregorianCalendar.SECOND, iQuantity);
        }
        return gc.getTime();
    }


    /**
     *
     * @param currentTime 计算的日期
     * @param type 偏移的类别
     * @param iQuantity 偏移数量
     * @return 偏移后的时间串
     */
    public String getDateChangeTime(String currentTime, String type,
                                    int iQuantity) {
        Date curr = stringToDate(currentTime);
        curr = getDateChangeTime(curr, type, iQuantity);
        return dateToString(curr);
    }
    /**
	 * 取得日以上粒度起始时间
	 * @param granularity 粒度
	 * @param statisticDate 结束时间
	 * @return 起始时间
	 */
	public static String getEndDate(String granularity,String statisticDate){
		String beginDate = "";
		Date date = stringToDateShort(statisticDate);
		Date beginDateTemp  = null;
		
		
		if(granularity.equals("1")){//日
			beginDateTemp = date;
		}
		if(granularity.equals("2")){//周
			beginDateTemp = getWeekEnd(date);
		}
		if(granularity.equals("3")){//旬
			beginDateTemp = getPeriodEnd(date);
		}
		else if(granularity.equals("4")){//月
			beginDateTemp = getMonthEnd(date);
		}
		else if(granularity.equals("5")){//季
			beginDateTemp = getSeasonEnd(date);
		}
		else if(granularity.equals("6")){//半年
			beginDateTemp = getHalfYearEnd(date);
		}
		else if(granularity.equals("7")){//年
			beginDateTemp = getYearEnd(date);
		}
		
		beginDate = dateToStringShort(beginDateTemp);
		return beginDate;
	}

	/**
	 * 取半年初
	 * @param date
	 * @return
	 */
	public static Date getHalfYearBegin(Date date){
		int year = Integer.parseInt( FormatDate(date, "yyyy"));
		int month = Integer.parseInt(FormatDate(date, "MM"));
		String newDateStr = FormatDate(date,"yyyy")+"-";
		if( month<=6 ){
			newDateStr+="01-01";
		}
		else{
			newDateStr+="07-01";
		}
		return stringToDateShort(newDateStr);
	}

    /**
	 * 取半年末
	 * @param date
	 * @return
	 */
	public static Date getHalfYearEnd(Date date){
		int year = Integer.parseInt( FormatDate(date, "yyyy"));
		int month = Integer.parseInt(FormatDate(date, "MM"));
		String newDateStr = FormatDate(date,"yyyy")+"-";
		if( month<=6 ){
			newDateStr+="06-30";
		}
		else{
			newDateStr+="12-31";
		}
		return stringToDateShort(newDateStr);
	}

	/**
	 * 取月初
	 * @param date
	 * @return
	 */
	public static Date getMonthBegin(Date date){
		String newDateStr = FormatDate(date, "yyyy-MM")+"-01";
		//FormatDate(date, "yyyy-MM-dd");
		return stringToDateShort(newDateStr);
	}
	
	
	/**
	 * 取月初字符
	 * @param date
	 * @return
	 */
	public static String getMonthFirstDay(Date date){
		String newDateStr = FormatDate(date, "yyyy-MM")+"-01";
		//FormatDate(date, "yyyy-MM-dd");
		return newDateStr;
	}
	
	/**
	 * 取月末字符
	 * @param date
	 * @return
	 */
	public static String getMonthLastDay(Date date){
		String newDateStr = dateToStringShort(getMonthEnd(date));
		return newDateStr;
	}
	
	/**
     * 取月末时间
     * @param date 日期
     * @return date
     */
    public static Date getMonthEnd(Date date){
        int year = Integer.parseInt( FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        int day = Integer.parseInt(FormatDate(date, "dd"));

        GregorianCalendar calendar = new GregorianCalendar(year,month-1,day,0,0,0);
        int monthLength = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        String newDateStr = FormatDate(date,"yyyy")+"-"+FormatDate(date,"MM")+"-";
        if(monthLength<10)
            newDateStr+="0"+monthLength;
        else
            newDateStr+=""+monthLength;
        return stringToDateShort(newDateStr);
    }
	
	/**
	 * 取旬初
	 * @param date
	 * @return
	 */
	public static Date getPeriodBegin(Date date){
		int days = Integer.parseInt(FormatDate(date, "dd"));
		String newDateStr = FormatDate(date,"yyyy-MM")+"-";
		if( days<=10 ){
			newDateStr+="01";
		}
		else if( days<=20 ){
			newDateStr+="11";
		}else{
			newDateStr+="21";
		}
		return stringToDateShort(newDateStr);
	}
	
	
	/**
	 * 取旬末
	 * @param date
	 * @return
	 */
	public static Date getPeriodEnd(Date date){
		int days = Integer.parseInt(FormatDate(date, "dd"));
		String newDateStr = FormatDate(date,"yyyy-MM")+"-";
		if( days<=10 ){
			newDateStr+="10";
		}
		else if( days<=20 ){
			newDateStr+="20";
		}else{
			newDateStr=FormatDate(getMonthEnd(date),"yyyy-MM-dd");
		}
		return stringToDateShort(newDateStr);
	}
	
	
	/**
	 * 取季初
	 * @param date
	 * @return
	 */
	public static Date getSeasonBegin(Date date){
		int year = Integer.parseInt( FormatDate(date, "yyyy"));
		int month = Integer.parseInt(FormatDate(date, "MM"));
		String newDateStr = FormatDate(date,"yyyy")+"-";
		if( month>=1 && month<=3 ){
			newDateStr+="01-01";
		}
		else if( month>=4 && month<=6 ){
			newDateStr+="04-01";
		}
		else if( month>=7 && month<=9 ){
			newDateStr+="07-01";
		}
		else if( month>=10 && month<=12 ){
			newDateStr+="10-01";
		}
		return stringToDateShort(newDateStr);
	}


    /**
     * 取季度
     * @param date
     * @return
     */
    public static int jidu(Date date){
        int year = Integer.parseInt( FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        if( month>=1 && month<=3 ){
            return 1;
        }
        else if( month>=4 && month<=6 ){
            return 2;
        }
        else if( month>=7 && month<=9 ){
            return 3;
        }
        else if( month>=10 && month<=12 ){
            return 4;
        }
   return 0;
    }


    /**
     * 取季度末时间
     * @param date 日期
     * @return date
     */
    public static Date getSeasonEnd(Date date){
        int year = Integer.parseInt( FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        String newDateStr = FormatDate(date,"yyyy")+"-";
        if( month>=1 && month<=3 ){
            newDateStr+="03-31";
        }
        else if( month>=4 && month<=6 ){
            newDateStr+="06-30";
        }
        else if( month>=7 && month<=9 ){
            newDateStr+="09-30";
        }
        else if( month>=10 && month<=12 ){
            newDateStr+="12-31";
        }
        return stringToDateShort(newDateStr);
    }
	
	
	/**
	 * 取得时间描述
	 * 日 yyyy－mm－dd
	 * 月 yyyy年mm月
	 * 季 yyyy年第×季度
	 * 年 yyyy年
	 * @param granularity 粒度
	 * @param statisticDate 时间
	 * @return 时间对应粒度的描述
	 */
	public String getTimedes(String granularity,String statisticDate){
	  String timedes = "";
	  Date date = stringToDateShort(statisticDate);
	  String year="",month="01",day="01";
	  year = DateUtils.getYear(date);
	  month = DateUtils.getMonth(date);
	  day = DateUtils.getDay(date);
	  if(granularity.equals("1")){//日
	    timedes = statisticDate;
	  }else if(granularity.equals("4")){//月
	    timedes = year+"年"+month+"月";

	  }else if(granularity.equals("8")){//季
	    String quarter = month+"-"+day;
	    if(quarter.equals("03-31")){
	      timedes = year+"年 第1季度";
	    }else if(quarter.equals("06-30")){
	      timedes = year+"年 第2季度";
	    }else if(quarter.equals("09-30")){
	      timedes = year+"年 第3季度";
	    }else if(quarter.equals("12-31")){
	      timedes = year+"年 第4季度";
	    }
	  }else if(granularity.equals("32")){//年
	    timedes = year+"年";
	  }
	  return timedes;
	}

    /**
	 * 取周初
	 * @param date
	 * @return
	 */
	public static Date getWeekBegin(Date date){
		
		 int year = Integer.parseInt(FormatDate(date, "yyyy"));
	        int month = Integer.parseInt(FormatDate(date, "MM"));
	        //月份修正
	        month=month-1;
	        int day = Integer.parseInt(FormatDate(date, "dd"));
	       
	        GregorianCalendar gc = new GregorianCalendar(year, month, day
	                );
	        
		int week =GregorianCalendar.DAY_OF_WEEK-1;
		
		if(week==0){
			week=7;
		}
		
		gc.add(GregorianCalendar.DATE, 0-week+1);
		
		return gc.getTime();
	}

	/**
	 * 取周末
	 * @param date
	 * @return
	 */
	public static Date getWeekEnd(Date date){
		
		 int year = Integer.parseInt(FormatDate(date, "yyyy"));
	        int month = Integer.parseInt(FormatDate(date, "MM"));
	        //月份修正
	        month=month-1;
	        int day = Integer.parseInt(FormatDate(date, "dd"));
	       
	        GregorianCalendar gc = new GregorianCalendar(year, month, day
	                );
	        
		int week =GregorianCalendar.DAY_OF_WEEK-1;
		
		if(week==0){
			week=7;
		}		
		gc.add(GregorianCalendar.DATE, 7-week);
		
		return gc.getTime();
	}

    /**
	 * 取得年初
	 * @param date
	 * @return
	 */
	public static Date getYearBegin(Date date){
		String newDateStr = FormatDate(date,"yyyy")+"-01-01";
		return stringToDateShort(newDateStr);
	}
    
    /**
     * 是否为年末
     * @param date 时间
     * @return
     */
    public static Date getYearEnd(Date date){
        String newDateStr = FormatDate(date,"yyyy")+"-12-31";
        return stringToDateShort(newDateStr);
    }
    
    /**
     * 是否为旬末
     * @param date 时间
     * @return 是或否
     */
    public boolean IsXperiodEnd(Date date){ 
    	
    	boolean flag = false;
        
    	String day = getDay(date);
        String month = getMonth(date);
        
        if( day.equalsIgnoreCase("10") ){
            flag = true;
        }
        else if( day.equalsIgnoreCase("20") ){
        	flag = true;
        }
        //月末不算旬末
//        else if( getMonthEnd(date).compareTo(date)==0 ){
//        	flag = true;
//        }
        
        return flag;
    }
    
    /**
     * 日期加N天
     * @param date 时间
     * @return 加后的日期
     */
	public static String addDay(Date date, int n, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);

			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, n);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}
	public static String addDay(Date date, int n) {
		return addDay(date, n, "yyyy-MM-dd");
	}

    public static Date addDayDate(Date date, int n) {
        try {
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            cd.add(Calendar.DATE, n);// 增加一天
            // cd.add(Calendar.MONTH, n);//增加一个月
            return cd.getTime();

        } catch (Exception e) {
            return null;
        }
    }
	
    public static String delDay(Date date,int n){
		return delDay(date, n, "yyyy-MM-dd");
	}
	
	 /**
     * 日期减N天
     * @param date 时间
     * @return 加后的日期
     */
	public static String delDay(Date date, int n,String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, -n);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月
			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}
	
	/**
     * 日期增/减N天
     * @param date 时间
     * @return 加后的日期
     */
	public static Date delDayDate(Date date, int n,String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, n);// 增加一天
			return cd.getTime();
		} catch (Exception e) {
			return null;
		}
	}

    /**
     * 格式化日期增/减N天
     * @param date 时间
     * @return 加后的日期
     */
    public static Date delDayDatesdf(Date date, int n,String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            cd.add(Calendar.DATE, n);// 增加一天
            String days=sdf.format(cd.getTime());
            return DateUtils.stringToDate(days);
        } catch (Exception e) {
            return null;
        }
    }


    public static Date delDayDate(Date date, int n) {
		return delDayDate(date, n, "yyyy-MM-dd");
	}


    /**
     * 日期减N月
     * @param n 时间
     * @return 加后的日期
     */
    public static Date delMonthDate(Date date, int n) {
        try {
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            cd.add(Calendar.MONTH, -n);//增加一个月
            return cd.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期减N年
     * @param n 时间
     * @return 加后的日期
     */
    public static Date delYearDate(Date date, int n) {
        try {
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            cd.add(Calendar.YEAR, -n);//增加一年
            return cd.getTime();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 日期加N月
     * @param n 时间
     * @return 加后的日期
     */
    public static Date addMonthDate(Date date, int n) {
        try {
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            cd.add(Calendar.MONTH, n);//增加一个月
            return cd.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期增减分钟
     * @param date
     * @param n
     * @return
     */
    public static Date addMinute(Date date,int n){
        try {
            Calendar cd=Calendar.getInstance();
            cd.setTime(date);
            cd.add(Calendar.MINUTE,n);
            return cd.getTime();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 增减小时
     * @param date
     * @param n
     * @return
     */
    public static Date addHour(Date date,int n){
        try {
            Calendar cd=Calendar.getInstance();
            cd.setTime(date);
            cd.add(Calendar.HOUR,n);
            return cd.getTime();
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * 获取时间的一天开始时间或结束时间
     * @param date
     * @param timeType true:开始时间;false:结束时间
     * @return
     */
    public static Date Date2Time(Date date,boolean timeType){
        Date time;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(timeType){
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
        }
        time = calendar.getTime();
        return time;
    }



    /**
     * 获取最近的整点时间
     * @param date
     * @param type 1: 距离最近天;
     *              2016-07-20 13:43:20 return: 2016-07-20 00:00:00
     *              2:距离最近的小时;
     *              2016-07-20 13:43:20 return: 2016-07-20 13:00:00
     *              3:距离最近的时刻(00:00、15:00、30:00、45:00)
     *             2016-07-20 13:23:20 return: 2016-07-20 13:00:00
     *             2016-07-20 13:21:20 return: 2016-07-20 13:15:00
     *             2016-07-20 13:43:20 return: 2016-07-20 13:30:00
     *             2016-07-20 13:58:20 return: 2016-07-20 13:45:00
     * @return
     */
    public static Date beforeDate(Date date, int type) {
        if(date == null) return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (type) {
            case 1:
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                break;
            case 2:
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                break;
            case 3:
                int min = calendar.get(Calendar.MINUTE);
                if(min < 15 ) {
                    min = 0;
                }else if(min < 30 ) {
                    min = 15;
                }else if(min < 45 ) {
                    min = 30;
                }else {
                    min = 45;
                }
                calendar.set(Calendar.MINUTE,min);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            case 4:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                break;
            case 5:
                calendar.set(Calendar.MONTH, 0);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                break;
        }
        return calendar.getTime();
    }

    /**
     * 主函数
     * @param args 测试参数
     */
    public static void main(String[] args) {
//        int s=diffMinterDate(DateUtils.getCurrDateTime(),DateUtils.addMinute(DateUtils.getCurrDateTime(),60));
//        System.out.println(FormatDate(Date2Time(getCurrDateTime(), false), DATE_YMDHMS));

        System.out.println(getBeginDate("4", getCurrDate()));
        System.out.println(getEndDate("4", getCurrDate()));
        System.out.println(FormatDate(beforeDate(getCurrDateTime(), 3), DATE_YMDHMS));
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis){
        long day = timeMillis/(24*60*60*1000);
        long hour = (timeMillis/(60*60*1000)-day*24);
        long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
        long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
        long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
        return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
}
