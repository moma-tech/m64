package top.moma.m64.core.helper.date;

import top.moma.m64.core.constants.DateTimePatterns;
import top.moma.m64.core.constants.StringConstants;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.StringHelper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
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

  /**
   * Get DateTime of instant/timestamp with given time zone
   *
   * @author Created by ivan on 11:04 AM 11/23/20.
   * @param instant : {@link Instant}
   * @param zoneId : {@link ZoneId}
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime of(Instant instant, ZoneId zoneId) {
    if (null == instant) {
      return null;
    }
    return LocalDateTime.ofInstant(
        instant, ObjectHelper.defaultIfNull(zoneId, ZoneId.systemDefault()));
  }

  /**
   * Get DateTime of instant/timestamp with system time zone
   *
   * @author Created by ivan on 11:05 AM 11/23/20.
   * @param instant : {@link Instant}
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime of(Instant instant) {
    return of(instant, ZoneId.systemDefault());
  }

  /**
   * Get DateTime of Temporal Accessor
   *
   * @author Created by ivan on 11:06 AM 11/23/20.
   * @param temporalAccessor : {@link TemporalAccessor}
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime of(TemporalAccessor temporalAccessor) {
    if (null == temporalAccessor) {
      return null;
    }

    if (temporalAccessor instanceof LocalDate) {
      return ((LocalDate) temporalAccessor).atStartOfDay();
    }

    return LocalDateTime.of(
        TemporalAccessorHelper.get(temporalAccessor, ChronoField.YEAR),
        TemporalAccessorHelper.get(temporalAccessor, ChronoField.MONTH_OF_YEAR),
        TemporalAccessorHelper.get(temporalAccessor, ChronoField.DAY_OF_MONTH),
        TemporalAccessorHelper.get(temporalAccessor, ChronoField.HOUR_OF_DAY),
        TemporalAccessorHelper.get(temporalAccessor, ChronoField.MINUTE_OF_HOUR),
        TemporalAccessorHelper.get(temporalAccessor, ChronoField.SECOND_OF_MINUTE),
        TemporalAccessorHelper.get(temporalAccessor, ChronoField.NANO_OF_SECOND));
  }

  /**
   * Get DateTime of Instant/Timestamp with UTC
   *
   * @author Created by ivan on 11:08 AM 11/23/20.
   * @param instant : {@link Instant}
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime ofUTC(Instant instant) {
    return of(instant, ZoneId.of("UTC"));
  }

  /**
   * Get DateTime of Instant/Timestamp with CTT(Shang Hai)
   *
   * @author Created by ivan on 11:08 AM 11/23/20.
   * @param instant : {@link Instant}
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime ofCTT(Instant instant) {
    return of(instant, ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
  }

  /**
   * Get DateTime of Date with given TimeZone
   *
   * @author Created by ivan on 11:09 AM 11/23/20.
   * @param date : {@link Date}
   * @param zoneId : {@link ZoneId}
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime of(Date date, ZoneId zoneId) {
    if (null == date) {
      return null;
    }
    if (null == zoneId) {
      return of(date.toInstant());
    }
    return of(date.toInstant(), zoneId);
  }

  /**
   * Get DateTime of Timestamp/Long value with given Time Zone
   *
   * @author Created by ivan on 11:09 AM 11/23/20.
   * @param timestamp : long value
   * @param zoneId : {@link ZoneId}
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime of(long timestamp, ZoneId zoneId) {
    Instant instant = Instant.ofEpochMilli(timestamp);
    return LocalDateTime.ofInstant(
        instant, ObjectHelper.defaultIfNull(zoneId, ZoneId.systemDefault()));
  }

  /**
   * Get DateTime of Timestamp/Long value with given System Time Zone
   *
   * @author Created by ivan on 11:11 AM 11/23/20.
   * @param timestamp : long value
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime of(long timestamp) {
    return of(timestamp, ZoneId.systemDefault());
  }
  /**
   * Get DateTime of Timestamp/Long value with UTC Time Zone
   *
   * @author Created by ivan on 11:12 AM 11/23/20.
   * @param timestamp : long value
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime ofUTC(long timestamp) {
    return of(timestamp, ZoneId.of("UTC"));
  }

  /**
   * Get DateTime of Timestamp/Long value with CTT/Shanghai Time Zone
   *
   * @author Created by ivan on 11:12 AM 11/23/20.
   * @param timestamp : long value
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime ofTCC(long timestamp) {
    return of(timestamp, ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
  }

  /**
   * Parse String into DateTime with given Formatter
   *
   * @author Created by ivan on 11:12 AM 11/23/20.
   * @param dateTime : String Value
   * @param dateTimeFormatter : {@link DateTimeFormatter}
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime parse(CharSequence dateTime, DateTimeFormatter dateTimeFormatter) {
    if (null == dateTime) {
      return null;
    }
    if (null == dateTimeFormatter) {
      return LocalDateTime.parse(dateTime);
    }
    return of(dateTimeFormatter.parse(dateTime));
  }

  /**
   * Parse String into DateTime with given format pattern
   *
   * @author Created by ivan on 11:13 AM 11/23/20.
   * @param dateTime : String value
   * @param format : date format pattern
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime parse(CharSequence dateTime, String format) {
    if (null == dateTime) {
      return null;
    }
    DateTimeFormatter dateTimeFormatter = null;
    if (StringHelper.isNotBlank(format)) {
      // For Special Case yyyyMMddHHmmssSSS
      // see
      // https://stackoverflow.com/questions/22588051/is-java-time-failing-to-parse-fraction-of-second
      // jdk8 bug at: https://bugs.openjdk.java.net/browse/JDK-8031085
      if (StringHelper.startWithIgnoreCase(format, DateTimePatterns.ABSOLUTE_DATE_PATTERN)
          && !format.contains(StringConstants.DOT)) {
        String fraction = StringHelper.removePrefix(format, DateTimePatterns.ABSOLUTE_DATE_PATTERN);
        if (3 > fraction.length()) {
          dateTime = dateTime + StringHelper.repeat('0', 3 - fraction.length());
        }
        dateTimeFormatter =
            new DateTimeFormatterBuilder()
                .appendPattern(DateTimePatterns.ABSOLUTE_DATE_PATTERN)
                .appendValue(ChronoField.MILLI_OF_SECOND, 3)
                .toFormatter();
      } else {
        dateTimeFormatter = DateTimeFormatter.ofPattern(format);
      }
    }
    return parse(dateTime, dateTimeFormatter);
  }

  /**
   * Parse String into DateTime with Default Pattern/yyyy-MM-dd HH:mm:ss
   *
   * @author Created by ivan on 11:14 AM 11/23/20.
   * @param dateTime : String value
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime parse(CharSequence dateTime) {
    return parse(dateTime, DateTimePatterns.DEFAULT_DATETIME_PATTERN);
  }
  /**
   * get LocalDate of Temporal Accessor
   *
   * @author Created by ivan on 4:35 PM 11/23/20.
   * @param temporalAccessor : {@link TemporalAccessor}
   * @return java.time.LocalDate
   */
  public static LocalDate ofDate(TemporalAccessor temporalAccessor) {
    if (null == temporalAccessor) {
      return null;
    }

    if (temporalAccessor instanceof LocalDateTime) {
      return ((LocalDateTime) temporalAccessor).toLocalDate();
    }

    return LocalDate.of(
        TemporalAccessorHelper.get(temporalAccessor, ChronoField.YEAR),
        TemporalAccessorHelper.get(temporalAccessor, ChronoField.MONTH_OF_YEAR),
        TemporalAccessorHelper.get(temporalAccessor, ChronoField.DAY_OF_MONTH));
  }

  /**
   * parse Date from String with given Formatter
   *
   * @author Created by ivan on 4:36 PM 11/23/20.
   * @param date : Sting Value
   * @param dateTimeFormatter : DateTime Formatter
   * @return java.time.LocalDate
   */
  public static LocalDate parseDate(CharSequence date, DateTimeFormatter dateTimeFormatter) {
    if (null == date) {
      return null;
    }
    if (null == dateTimeFormatter) {
      return LocalDate.parse(date);
    }
    return ofDate(dateTimeFormatter.parse(date));
  }

  /**
   * parse String into Date with given Pattern
   *
   * @author Created by ivan on 11:15 AM 11/23/20.
   * @param date : String Value
   * @param format : String format
   * @return java.time.LocalDate
   */
  public static LocalDate parseDate(CharSequence date, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return LocalDate.parse(date, formatter);
  }

  /**
   * parse String into Date with default pattern/yyyy-MM-dd
   *
   * @author Created by ivan on 4:37 PM 11/23/20.
   * @param date : String value
   * @return java.time.LocalDate
   */
  public static LocalDate parseDate(CharSequence date) {
    return parseDate(date, DateTimePatterns.DEFAULT_DATE_PATTERN);
  }
}
