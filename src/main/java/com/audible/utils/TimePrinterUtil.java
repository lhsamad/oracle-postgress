package com.audible.utils;

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


  public static void main(String[] args) throws Exception{
    LocalDateTime localTime = LocalDateTime.now(ZoneId.of("GMT+02:30"));
    System.out.println(localTime);




  }

}
