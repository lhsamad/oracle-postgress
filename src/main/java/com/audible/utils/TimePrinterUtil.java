package com.audible.utils;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.lang3.time.DateUtils;

public class TimePrinterUtil {


  public static void main(String[] args) throws Exception{

    Date today = new Date();
    Date startOfDate = atStartOfDay(today);
    Date endOfDate = atEndOfDay(today);

    System.out.println("Start Epoch: " + startOfDate.getTime()/1000);
    System.out.println("End Epoch: " + endOfDate.getTime()/1000);

    long unixTimestamp = Instant.ofEpochMilli(today.getTime()).toEpochMilli();
    System.out.println(unixTimestamp);


    System.out.println(today);
    Calendar calendar =  DateUtils.toCalendar(today, TimeZone.getTimeZone("GMT-7"));
    System.out.println(calendar.getTime());


  }

  public static Date atEndOfDay(Date date) {
    return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
  }

  public static Date atStartOfDay(Date date) {
    return DateUtils.truncate(date, Calendar.DATE);
  }

}
