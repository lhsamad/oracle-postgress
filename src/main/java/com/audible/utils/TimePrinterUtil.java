package com.audible.utils;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.time.DateUtils;

public class TimePrinterUtil {


  static String start = "1539561600";
  static String end = "1539647999";


  public static void main(String arg[]) throws Throwable {
    System.out.println("between "+getOracleDate(start)+" and "+getOracleDate(end));
    System.out.println("between "+getPostgresDate(start)+" and "+getPostgresDate(end));

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    System.out.println(sdf.format(new Date()));

    System.out.println("print start epoch="+getStringDateToEpoch("2018-10-06T00:00:00Z"));
    System.out.println("print end epoch="+getStringDateToEpoch("2018-10-06T23:59:59Z"));
  }

  public static Long getStringDateToEpoch(String stringDate) throws Throwable{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    Date date =  sdf.parse(stringDate);
    return (date.getTime() / 1000);
  }

  public static String getPostgresDate(String epoch) throws Throwable{
    long date = Long.parseLong(epoch);
    Instant i = Instant.ofEpochSecond(date);
    System.out.println(i);
    return "'"+i+"'::timestamp";
  }

  public static String getOracleDate(String epoch) throws Throwable{
    long date = Long.parseLong(epoch);
    Instant i = Instant.ofEpochSecond(date);
    System.out.println(i);
    return "to_timestamp('"+i+"', 'yyyy-mm-dd\"T\"hh24:mi:ss.ff3\"Z\"')";
  }

}
