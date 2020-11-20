package top.moma.m64.core.helper.date;

import top.moma.m64.core.constants.DateTimePatterns;
import top.moma.m64.core.helper.ObjectHelper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * DateTimeHelper
 *
 * <p>Locate Date Time Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/20/20.
 */
public class DateTimeHelper {

  public static LocalDateTime of(Instant instant, ZoneId zoneId) {
    if (null == instant) {
      return null;
    }
    return LocalDateTime.ofInstant(
        instant, ObjectHelper.defaultIfNull(zoneId, ZoneId.systemDefault()));
  }

  public static LocalDateTime of(Instant instant) {
    return of(instant, ZoneId.systemDefault());
  }

  public static LocalDateTime ofUTC(Instant instant) {
    return of(instant, ZoneId.of("UTC"));
  }

  public static LocalDateTime ofCTT(Instant instant) {
    return of(instant, ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
  }

  public static LocalDateTime of(Date date, ZoneId zoneId) {
    if (null == date) {
      return null;
    }
    if (null == zoneId) {
      return of(date.toInstant());
    }
    return of(date.toInstant(), zoneId);
  }

  public static LocalDateTime of(long timestamp, ZoneId zoneId) {
    Instant instant = Instant.ofEpochMilli(timestamp);
    return LocalDateTime.ofInstant(
        instant, ObjectHelper.defaultIfNull(zoneId, ZoneId.systemDefault()));
  }

  public static LocalDateTime of(long timestamp) {
    return of(timestamp, ZoneId.systemDefault());
  }

  public static LocalDateTime ofUTC(long timestamp) {
    return of(timestamp, ZoneId.of("UTC"));
  }

  public static LocalDateTime ofTCC(long timestamp) {
    return of(timestamp, ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
  }

  public static LocalDateTime parse(String dateTime, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return LocalDateTime.parse(dateTime, formatter);
  }

  public static LocalDateTime parse(String dateTime) {
    return parse(dateTime, DateTimePatterns.DEFAULT_DATETIME_PATTERN);
  }

  public static LocalDate parseStringToDate(String date, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return LocalDate.parse(date, formatter);
  }

  public static LocalDate parseStringToDate(String date) {
    return parseStringToDate(date, DateTimePatterns.DEFAULT_DATE_PATTERN);
  }
}
