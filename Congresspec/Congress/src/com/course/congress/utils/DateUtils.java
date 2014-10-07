package com.course.congress.utils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class for working with dates.
 */
public class DateUtils {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Formats the date so that the time is set to 00:00:00
	 * @param date - the date that needs to be formated
	 * @return formated date (dd/MM/yyyy)
	 */
	public static Date returnDateWithoutTime(Date date) {
		try {
			return formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		};
		return date;
	}
	
	public static String getCurrentMonth(int monthIndex) {
		String monthString = new DateFormatSymbols().getMonths()[monthIndex];
		return monthString;
	}

	public static String getPrevMonth(int monthIndex) {
		if (monthIndex == 0) {
			monthIndex = 12;
		}
		return new DateFormatSymbols().getMonths()[monthIndex - 1];
	}

	public static String getNextMonth(int monthIndex) {
		if (monthIndex == 11) {
			monthIndex = -1;
		}
		return new DateFormatSymbols().getMonths()[monthIndex + 1];
	}

	public static int getDaysOfMonth(int monthIndex, int year) {
		Calendar mycal = new GregorianCalendar(year, monthIndex, 1);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return daysInMonth;
	}
	
}
