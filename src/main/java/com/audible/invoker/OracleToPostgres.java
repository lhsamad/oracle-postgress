package com.audible.invoker;

import com.audible.utils.DBUtil;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class OracleToPostgres {

  static String start = "1539561600";
  static String end = "1539647999";


  public static void main(String arg[]) throws Throwable {
    String SQL = "Select * FROM AA_MRPI_ACTIVE_CREDIT_PST WHERE DATE_UPDATED between "+getOracleDate(start)+" and "+getOracleDate(end)+" order by MRPI_ACTIVE_SEQ_NO";
    Connection connection = DBUtil.getOracleConnection();
    List<Map<String, Object>> list = DBUtil.query(connection, SQL);
    System.out.println(list);
    System.out.println(list.size());

    String SQL2 = "Select * FROM booker.AA_MRPI_ACTIVE_CREDIT_PST WHERE DATE_UPDATED between "+getPostgresDate(start)+" and "+getPostgresDate(end)+" order by MRPI_ACTIVE_SEQ_NO";
    Connection connection2 = DBUtil.getPostgresConnection();
    List<Map<String, Object>> list2 = DBUtil.query(connection2, SQL2);
    System.out.println(list2);
    System.out.println(list2.size());


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
