package com.my.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class DateUtils {
	Logger loger = Logger.getLogger(DateUtils.class);
	public static Date parseDate(String dateStr,String formate){
		if(StringUtils.isEmpty(formate)){
			formate = "yyyy-MM-dd hh:mm:ss";
		}
		if(dateStr.indexOf(":")==-1){
			dateStr += " 00:00:00";
		}
		SimpleDateFormat formater = new SimpleDateFormat(formate);
		try {
			return formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String fomateDate(Date date,String formate){
		SimpleDateFormat formater = new SimpleDateFormat(formate);
		return formater.format(date);
	}
	
	public static Date getSimpleDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
		return cal.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(fomateDate(getSimpleDate(new Date()),"yyyy-MM-dd"));
	}

}
