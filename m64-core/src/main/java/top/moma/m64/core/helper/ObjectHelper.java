package top.moma.m64.core.helper;

/**
 * ObjectHelper
 *
 * <p>Object Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/20/20.
 */
public class ObjectHelper {
  /**
   * if object is null, return default
   *
   * @author Created by ivan on 5:30 PM 11/20/20.
   * @return T
   */
  public static <T> T defaultIfNull(final T object, final T defaultValue) {
    return null != object ? object : defaultValue;
  }
}
