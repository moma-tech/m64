package top.moma.m64.core.helper.date;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

/**
 * TemporalAccessorHelper
 *
 * <p>{@link java.time.temporal.TemporalAccessor}
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/23/20.
 */
public class TemporalAccessorHelper {

  private TemporalAccessorHelper() {}

  /**
   * Get Filed Value from Date, if non-support, return MIN/0;
   *
   * @author Created by ivan on 11:03 AM 11/23/20.
   * @param temporalAccessor : Date Object
   * @param temporalField : Request Filed
   * @return int, Value or 0
   */
  public static int get(TemporalAccessor temporalAccessor, TemporalField temporalField) {
    if (temporalAccessor.isSupported(temporalField)) {
      return temporalAccessor.get(temporalField);
    }
    return (int) temporalField.range().getMinimum();
  }
}
