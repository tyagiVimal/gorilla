
package com.company.automation.utility;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtility {

	/**
	 * This utility method checks whether given date in eexpected format
	 * "dd-MMM-yyyy"
	 * 
	 * @param date
	 * @return boolean
	 */

	public static boolean isValidDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
		boolean isValid = true;
		sdf.setLenient(false);
		try {
			sdf.parse(date);
		} catch (Exception e) {
			isValid = false;
		}
		return isValid;
	}

	/**
	 * This utility method add no of months in given date.
	 * 
	 * @param int No of month to add
	 * @return String added date
	 */

	public static String addMonthInDate(int month) {
		String date = "";
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, month);
		date = date + now.get(Calendar.DATE) + "-"
				+ (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.YEAR);
		return date;
	}

	/**
	 * This utility method subtract no of months in given date.
	 * 
	 * @param int No of month to add
	 * @return String Subtracted date
	 */

	public static String subtractMonthInDate(int month) {
		String date = "";
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, new Integer("-" + month));
		date = date + now.get(Calendar.DATE) + "-"
				+ (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.YEAR);
		return date;
	}

	/**
	 * This utility method returns a future date after number of days.
	 * 
	 * @param days
	 * @return
	 */

	public static Date getDateAfterDays(int days) {
		long backDateMS = System.currentTimeMillis() + ((long) days) * 24 * 60
				* 60 * 1000;
		Date backDate = new Date();
		backDate.setTime(backDateMS);
		return backDate;
	}

	/**
	 * This utility method returns a past date before number of days.
	 * 
	 * @param days
	 * @return
	 */

	public static Date getDateBeforeDays(int days) {
		long backDateMS = System.currentTimeMillis() - ((long) days) * 24 * 60
				* 60 * 1000;
		Date backDate = new Date();
		backDate.setTime(backDateMS);
		return backDate;
	}

	public static ArrayList<String> isValidTimeStamp(String withFormat,
			String toValidate) {
		ArrayList<String> arrErrorMess = new ArrayList<String>();
		String year = "0000", month = "00", date = "00", hour = "00", minute = "00", second = "00";
		String[] compName = toValidate.split("\\.");
		if (compName[1].length() != withFormat.length()) {
			arrErrorMess
					.add("DateFormat and Actual TimeStamp Length Mismatched!!!");
			return arrErrorMess;
		}
		int lastpointer = 0, i = 0, j = 1;

		while (j < withFormat.length()) {
			if (j < withFormat.length())
				try {
					while (withFormat.charAt(i++) == withFormat.charAt(j++)) {
					}
				} catch (Exception ex) {
				}
			if (charType(Character.toString(withFormat.charAt(lastpointer)))
					.compareToIgnoreCase("year") == 0)
				year = compName[1].substring(lastpointer, i);
			else if (charType(
					Character.toString(withFormat.charAt(lastpointer)))
					.compareToIgnoreCase("month") == 0)
				month = compName[1].substring(lastpointer, i);
			else if (charType(
					Character.toString(withFormat.charAt(lastpointer)))
					.compareToIgnoreCase("date") == 0)
				date = compName[1].substring(lastpointer, i);
			else if (charType(
					Character.toString(withFormat.charAt(lastpointer)))
					.compareToIgnoreCase("hour") == 0)
				hour = compName[1].substring(lastpointer, i);
			else if (charType(
					Character.toString(withFormat.charAt(lastpointer)))
					.compareToIgnoreCase("minute") == 0)
				minute = compName[1].substring(lastpointer, i);
			else if (charType(
					Character.toString(withFormat.charAt(lastpointer)))
					.compareToIgnoreCase("second") == 0)
				second = compName[1].substring(lastpointer, i);
			else
				break;
			lastpointer = i;
		}
		Calendar dtCalendar = Calendar.getInstance();
		int nMaxMonth = dtCalendar.getActualMaximum(Calendar.MONTH);
		int nMaxDate = dtCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (Integer.parseInt(month) > nMaxMonth)
			arrErrorMess.add("The Month Is Not Valid");
		if (Integer.parseInt(date) > nMaxDate)
			arrErrorMess.add("The Date Is Not Valid");
		if (Integer.parseInt(hour) > 23)
			arrErrorMess.add("The Hour Is Not Valid");
		if (Integer.parseInt(minute) > 59)
			arrErrorMess.add("The Minute Is Not Valid");
		if (Integer.parseInt(second) > 59)
			arrErrorMess.add("The Second Is Not Valid");
		if (Integer.parseInt(year) > 10000 || Integer.parseInt(year) < 1000)
			arrErrorMess.add("The Year Is Not Valid");
		return arrErrorMess;
	}

	public static String charType(String c) {
		if (c.compareTo("y") == 0)
			return "year";
		else if (c.compareTo("M") == 0)
			return "month";
		else if (c.compareTo("d") == 0)
			return "date";
		else if (c.compareTo("h") == 0)
			return "hour";
		else if (c.compareTo("m") == 0)
			return "minute";
		else if (c.compareTo("s") == 0)
			return "second";
		else
			return "invalid";
	}
}