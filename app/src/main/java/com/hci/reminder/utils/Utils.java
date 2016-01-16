package com.hci.reminder.utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	@SuppressWarnings("deprecation")
	public static String getDate(Date date) {
		if(date == null) 
			return "";
		StringBuilder dateStr = new StringBuilder();
		dateStr.append(date.getDate());
		dateStr.append(" ");
		dateStr.append(getMonth(date.getMonth()));
		dateStr.append(" ");
		dateStr.append(date.getYear() + 1900);
		return dateStr.toString();
	}
	
	private static String getMonth(int month) {
		if(month == 0)
			return "stycznia";
		if(month == 1) 
			return "lutego";
		if(month == 2)
			return "marca";
		if(month == 3)
			return "kwietnia";
		if(month == 4)
			return "maja";
		if(month == 5)
			return "czerwca";
		if(month == 6)
			return "lipca";
		if(month == 7)
			return "sierpnia";
		if(month == 8)
			return "wrzeúnia";
		if(month == 9)
			return "paüdziernika";
		if(month == 10)
			return "listopada";
		if(month == 11)
			return "grudnia";
		return "";
	}
	
	public static String getDate(Calendar date) {
		if(date == null) 
			return "";
		StringBuilder dateStr = new StringBuilder();
		dateStr.append(date.get(Calendar.DAY_OF_MONTH));
		dateStr.append(" ");
		dateStr.append(getMonth(date.get(Calendar.MONTH) - 1));
		dateStr.append(" ");
		dateStr.append(date.get(Calendar.YEAR));
		return dateStr.toString();
	}
	
	public static String toString(String[] array) {
		StringBuilder sb = new StringBuilder();
		for(String s: array) {
			sb.append(s);
			sb.append(", ");
		}
		String result = sb.toString();
		if(!result.isEmpty()) {
			result = result.substring(0, result.lastIndexOf(","));
		}
		return result;
 	}
	
}
