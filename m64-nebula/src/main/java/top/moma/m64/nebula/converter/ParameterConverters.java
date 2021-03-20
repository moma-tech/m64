package top.moma.m64.nebula.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.date.DateTimeHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ParameterConverters
 *
 * @author Ivan
 * @version 1.0 Created by Ivan at 2021/3/20.
 */
public class ParameterConverters implements GenericConverter {
  @Override
  public Set<ConvertiblePair> getConvertibleTypes() {
    Set<ConvertiblePair> convertiblePairs = new HashSet<>();
    convertiblePairs.add(new ConvertiblePair(String.class, LocalDateTime.class));
    convertiblePairs.add(new ConvertiblePair(String.class, LocalDate.class));
    convertiblePairs.add(new ConvertiblePair(String.class, Date.class));
    return convertiblePairs;
  }

  @Override
  public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    if (ObjectHelper.isEmpty(source)
        || ObjectHelper.isEmpty(sourceType)
        || ObjectHelper.isEmpty(targetType)) {
      return null;
    }
    // String to Local Date Time
    if (sourceType.getType().equals(String.class)
        && targetType.getType().equals(LocalDateTime.class)) {
      return DateTimeHelper.parse((String) source, DateTimeHelper.getDateFormat((String) source));
    }
    // String to Local Date
    if (sourceType.getType().equals(String.class) && targetType.getType().equals(LocalDate.class)) {
      return DateTimeHelper.parseDate(
          (String) source, DateTimeHelper.getDateFormat((String) source));
    }
    // String to Date
    if (sourceType.getType().equals(String.class) && targetType.getType().equals(Date.class)) {
      SimpleDateFormat simpleDateFormat = null;
      String param = (String) source;
      if (param.contains("-") || param.contains(":")) {
        simpleDateFormat = new SimpleDateFormat(DateTimeHelper.getDateFormat(param));
        try {
          return simpleDateFormat.parse(param);
        } catch (ParseException e) {
          throw new RuntimeException("Parse " + param + " to Date Fail.", e);
        }
      } else if ("^\\d+$".matches(param)) {
        long timestamp = Long.parseLong(param);
        return new Date(timestamp);
      }
    }
    return null;
  }
}
