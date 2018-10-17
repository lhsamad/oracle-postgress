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
import java.util.TimeZone;
import org.apache.commons.lang3.time.DateUtils;

public class TimePrinterUtil {


  static String start = "1539561600";
  static String end = "1539647999";


  public static void main(String arg[]) throws Throwable {
    String SQL_ONE = "Select * FROM AA_MRPI_ACTIVE_CREDIT_PST WHERE DATE_UPDATED between "+getOracleDate(start)+" and "+getOracleDate(end)+" order by MRPI_ACTIVE_SEQ_NO";
    System.out.println(SQL_ONE);


    String SQL_TWO = "Select * FROM booker.AA_MRPI_ACTIVE_CREDIT_PST WHERE DATE_UPDATED between "+getPostgresDate(start)+" and "+getPostgresDate(end)+" order by MRPI_ACTIVE_SEQ_NO";
    System.out.println(SQL_TWO);

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
