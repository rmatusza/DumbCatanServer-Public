package com.dumbcatan.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ObtainDate {
	
	private TimeZone timeZone = TimeZone.getTimeZone("US/Central");
//	private String dateFormat = "MMMM dd,yyyy "; //MMMM dd,yyyy G
	private String dateFormat = "MM/dd/yyyy "; //MMMM dd,yyyy G
	private String timeFormat = "hh:mm a zzzz";
	private String dayFormat = "EEEEEE";
	
  public String getTodaysDay(String dayFormat, TimeZone timeZone)
  {
      Date date = new Date();
      /* Specifying the format */
      DateFormat requiredFormat = new SimpleDateFormat(dayFormat);
      /* Setting the Timezone */
      requiredFormat.setTimeZone(timeZone);
      /* Picking the day value in the required Format */
      String strCurrentDay = requiredFormat.format(date).toUpperCase();
      return strCurrentDay;
   }

   public String getCurrentTime(String timeFormat, TimeZone timeZone)
   {
      /* Specifying the format */ 
      DateFormat dateFormat = new SimpleDateFormat(timeFormat);
      /* Setting the Timezone */
      Calendar cal = Calendar.getInstance(timeZone);
      dateFormat.setTimeZone(cal.getTimeZone());
      /* Picking the time value in the required Format */
      String currentTime = dateFormat.format(cal.getTime());
      return currentTime;
   }

  
   public String getTodayDate(String dateFormat, TimeZone timeZone)
   {
       Date todayDate = new Date();
       /* Specifying the format */
       DateFormat todayDateFormat = new SimpleDateFormat(dateFormat);
       /* Setting the Timezone */
       todayDateFormat.setTimeZone(timeZone);
       /* Picking the date value in the required Format */
       String strTodayDate = todayDateFormat.format(todayDate);
       return strTodayDate;
   }
   
	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getDayFormat() {
		return dayFormat;
	}

	public void setDayFormat(String dayFormat) {
		this.dayFormat = dayFormat;
	}

	public ObtainDate() {
		   
	}
}