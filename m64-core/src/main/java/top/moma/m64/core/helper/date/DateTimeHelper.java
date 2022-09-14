package top.moma.m64.core.helper.date;

import top.moma.m64.core.constants.DateTimePatterns;
import top.moma.m64.core.constants.PatternExpressions;
import top.moma.m64.core.constants.StringConstants;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.StringHelper;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * DateTimeHelper
 *
 * <p>Locate Date Time Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/20/20.
 */
public class DateTimeHelper {

  private DateTimeHelper() {}

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

  /**
   * afterSpecifiedTimeGap
   *
   * <p>build LDT from given LDT plus gap time
   *
   * <p>default timeUnit is SECOND
   *
   * @author Created by ivan at 下午4:10 2020/1/13.
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime afterSpecifiedTimeGap(
      LocalDateTime base, long gap, ChronoUnit timeUnit) {
    LocalDateTime result = null;
    if (Objects.nonNull(base)) {
      if (Objects.isNull(timeUnit)) {
        timeUnit = ChronoUnit.SECONDS;
      }
      result = base.plus(gap, timeUnit);
    }
    return result;
  }

  /**
   * afterSpecifiedTimeGap
   *
   * <p>build LDT from timestamp plus gap time
   *
   * @author Created by ivan at 下午4:12 2020/1/13.
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime afterSpecifiedTimeGap(long baseTs, long gap, ChronoUnit timeUnit) {
    LocalDateTime base = of(baseTs);
    return afterSpecifiedTimeGap(base, gap, timeUnit);
  }

  /**
   * isAfterComparedDayGaps
   *
   * <p>check if given LD is after compare LD in gap Days
   *
   * @author Created by ivan at 下午4:13 2020/1/13.
   * @return boolean
   */
  public static boolean isAfterComparedDayGaps(LocalDate given, LocalDate compare, long gap) {
    LocalDate localDate = compare.minusDays(gap);
    return given.isAfter(localDate);
  }

  /**
   * isEqualComparedDayGaps
   *
   * <p>check if given LD is gap days after compare LD
   *
   * @author Created by ivan at 下午4:14 2020/1/13.
   * @return boolean
   */
  public static boolean isEqualComparedDayGaps(LocalDate given, LocalDate compare, long gap) {
    LocalDate localDate = compare.minusDays(gap);
    return given.isEqual(localDate);
  }

  /**
   * isBeforeSecondLDt
   *
   * <p>check if first LDT is before second LDT
   *
   * @author Created by ivan at 下午4:15 2020/1/13.
   * @return boolean
   */
  public static boolean isBeforeSecondLDt(LocalDateTime firstTime, LocalDateTime secondTime) {
    Duration duration = Duration.between(firstTime, secondTime);
    long nano = duration.toNanos();
    return 0 <= nano;
  }

  /**
   * getTodayStartTime
   *
   * <p>get today's Start LDT
   *
   * <p>2020-01-13T00:00
   *
   * @author Created by ivan at 下午4:15 2020/1/13.
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime getTodayStartTime() {
    return LocalDateTime.now().with(LocalTime.MIN);
  }
  /**
   * getTodayEndTime
   *
   * <p>get today's End LDT
   *
   * <p>2020-01-13T23:59:59.9999s99999
   *
   * @author Created by ivan at 下午4:17 2020/1/13.
   * @return java.time.LocalDateTime
   */
  public static LocalDateTime getTodayEndTime() {
    return LocalDateTime.now().with(LocalTime.MAX);
  }

  /**
   * getCurrentHour24Format
   *
   * <p>get current hour in 24
   *
   * @author Created by ivan at 下午4:17 2020/1/13.
   * @return java.lang.String
   */
  public static String getCurrentHour24Format() {
    return getGivenHour24Format(LocalDateTime.now());
  }

  /**
   * getGivenHour24Format
   *
   * <p>get hour in 24 of given LDT
   *
   * @author Created by ivan at 下午4:18 2020/1/13.
   * @return java.lang.String
   */
  public static String getGivenHour24Format(LocalDateTime localDateTime) {
    return "" + localDateTime.getHour();
  }

  /**
   * getDateTime25Length
   *
   * <p>build String on current LDT in "yyyy-MM-dd+HH:mm:ss.SSSSSS" format
   *
   * <p>2020-01-13+16:19:04.24900
   *
   * @author Created by ivan at 下午4:18 2020/1/13.
   * @return java.lang.String
   */
  public static String getDateTime26Length() {
    return toString(LocalDateTime.now(), DateTimePatterns.DATE_TIME_LENGTH_26);
  }
  /**
   * getDateTime8Length
   *
   * <p>build String on current LDT in yyyyMMdd format
   *
   * <p>20200113
   *
   * @author Created by ivan at 下午4:19 2020/1/13.
   * @return java.lang.String
   */
  public static String getDateTime8Length() {
    return toString(LocalDateTime.now(), DateTimePatterns.ABSOLUTE_DATE_PATTERN);
  }

  /**
   * getDate
   *
   * <p>Get now in "yyyy-MM-dd HH:mm:ss"
   *
   * @author Created by ivan at 下午2:30 2020/6/8.
   * @return java.lang.String
   */
  public static String getDate() {
    return toString(LocalDateTime.now(), DateTimePatterns.DEFAULT_DATETIME_PATTERN);
  }

  /**
   * @author Created by Ivan at 2020/11/16.
   *     <p>Format LDT to String
   * @param localDateTime : given ldt
   * @param datePattern : given pattern
   * @return java.lang.String
   */
  public static String toString(LocalDateTime localDateTime, String datePattern) {
    return localDateTime.format(DateTimeFormatter.ofPattern(datePattern));
  }

  /**
   * LDT to String in format yyyyMMdd HH:mm:ss
   *
   * @author Created by ivan on 2:33 PM 11/24/20.
   * @param localDateTime : given ldt
   * @return java.lang.String
   */
  public static String toString(LocalDateTime localDateTime) {
    return toString(localDateTime, DateTimePatterns.DEFAULT_DATETIME_PATTERN);
  }

  /**
   * LDT toTimestamp
   *
   * @author Created by ivan on 5:17 PM 1/4/21.
   * @param localDateTime : given ldt
   * @return long
   */
  public static long toTimestamp(LocalDateTime localDateTime) {
    return toTimestamp(localDateTime, ZoneId.systemDefault());
  }

  /**
   * LDT toTimestamp
   *
   * @author Created by ivan on 5:17 PM 1/4/21.
   * @param localDateTime : given ldt
   * @param zoneId :
   * @return long
   */
  public static long toTimestamp(LocalDateTime localDateTime, ZoneId zoneId) {
    return localDateTime
        .atZone(ObjectHelper.defaultIfNull(zoneId, ZoneId.systemDefault()))
        .toInstant()
        .toEpochMilli();
  }

  public static int getDayOfWeek(CharSequence date) {
    LocalDateTime localDateTime = parse(date, getDateFormat(date.toString()));
    return ObjectHelper.isEmpty(localDateTime) ? -1 : localDateTime.getDayOfWeek().getValue();
  }

  /**
   * 常规自动日期格式识别
   *
   * <p>0: year 1: month 2: day 3: hour 4: minutes 5: second 6: mill-Second
   *
   * @param str 时间字符串
   * @return Date Pattern
   * @author Ivan
   */
  public static String getDateFormat(String str) {
    boolean year = false;
    Pattern pattern = PatternExpressions.DATE_PATTERN;
    if (pattern.matcher(str.substring(0, 4)).matches()) {
      year = true;
    }
    StringBuilder sb = new StringBuilder();
    int index = 0;
    if (!year) {
      if (str.contains("月") || str.contains("-") || str.contains("/")) {
        if (Character.isDigit(str.charAt(0))) {
          index = 1;
        }
      } else {
        index = 3;
      }
    }
    for (int i = 0; i < str.length(); i++) {
      char chr = str.charAt(i);
      if (Character.isDigit(chr)) {
        if (index == 0) {
          sb.append("y");
        }
        if (index == 1) {
          sb.append("M");
        }
        if (index == 2) {
          sb.append("d");
        }
        if (index == 3) {
          sb.append("H");
        }
        if (index == 4) {
          sb.append("m");
        }
        if (index == 5) {
          sb.append("s");
        }
        if (index == 6) {
          sb.append("S");
        }
      } else {
        if (i > 0) {
          char lastChar = str.charAt(i - 1);
          if (Character.isDigit(lastChar)) {
            index++;
          }
        }
        sb.append(chr);
      }
    }
    return sb.toString();
  }
}
