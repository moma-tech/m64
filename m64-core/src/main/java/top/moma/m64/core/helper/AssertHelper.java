package top.moma.m64.core.helper;

/**
 * AssertHelper
 *
 * <p>Assert
 *
 * @version 1.0
 * @author Created by ivan at 17:47.
 */
public class AssertHelper {

  private AssertHelper() {}

  /**
   * description notNull
   *
   * @param object object
   * @param message message
   * @author Created by ivan
   * @since 2022/9/13 17:46
   */
  public static void notNull(Object object, String message) {
    if (object == null) {
      throw new IllegalArgumentException(message);
    }
  }
}
