package top.moma.m64.core.helper;

import org.springframework.util.StringUtils;

/**
 * StringHelper
 *
 * <p>String Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/20/20.
 */
public class StringHelper {

  /**
   * if String isNotBlank
   *
   * <p>{@link StringUtils#hasText(CharSequence)}
   *
   * @author Created by ivan on 1:41 PM 11/23/20.
   * @param charSequence : String value
   * @return boolean
   */
  public static boolean isNotBlank(CharSequence charSequence) {
    return StringUtils.hasText(charSequence);
  }

  /**
   * if String is Blank
   *
   * @author Created by ivan on 1:41 PM 11/23/20.
   * @param charSequence :
   * @return boolean
   */
  public static boolean isBlank(CharSequence charSequence) {
    return !isNotBlank(charSequence);
  }
  /**
   * if String is not Empty
   *
   * <p>{@link StringUtils#hasLength(String)}
   *
   * @author Created by ivan on 5:06 PM 11/23/20.
   * @param charSequence :
   * @return boolean
   */
  public static boolean isNotEmpty(CharSequence charSequence) {
    return StringUtils.hasLength(charSequence);
  }

  /**
   * if String is empty
   *
   * @author Created by ivan on 5:06 PM 11/23/20.
   * @param charSequence : String
   * @return boolean
   */
  public static boolean isEmpty(CharSequence charSequence) {
    return !isNotEmpty(charSequence);
  }

  /**
   * if String start with prefix if/not if ignore case
   *
   * @author Created by ivan on 5:07 PM 11/23/20.
   * @param charSequence : String
   * @param prefix : prefix String
   * @param ignoreCase : true/false
   * @return boolean
   */
  public static boolean startWith(
      CharSequence charSequence, CharSequence prefix, boolean ignoreCase) {
    if (null == charSequence || null == prefix) {
      return false;
    }
    if (ignoreCase) {
      return charSequence.toString().toLowerCase().startsWith(prefix.toString().toLowerCase());
    } else {
      return charSequence.toString().startsWith(prefix.toString());
    }
  }

  /**
   * if String start with prefix, Case sensitive
   *
   * @author Created by ivan on 5:07 PM 11/23/20.
   * @param charSequence : String
   * @param prefix : prefix String
   * @return boolean
   */
  public static boolean startWith(CharSequence charSequence, CharSequence prefix) {
    return startWith(charSequence, prefix, false);
  }

  /**
   * if String start with prefix, ignore case
   *
   * @author Created by ivan on 5:08 PM 11/23/20.
   * @param charSequence : String
   * @param prefix : prefix String
   * @return boolean
   */
  public static boolean startWithIgnoreCase(CharSequence charSequence, CharSequence prefix) {
    return startWith(charSequence, prefix, true);
  }

  /**
   * remove Prefix from given String, then return
   *
   * <p>if String not start with prefix, do nothing
   *
   * @author Created by ivan on 5:08 PM 11/23/20.
   * @param charSequence : String
   * @param prefix : prefix String
   * @return java.lang.String
   */
  public static String removePrefix(CharSequence charSequence, CharSequence prefix) {
    if (null == charSequence || null == prefix) {
      return toString(charSequence);
    }
    if (startWith(charSequence, prefix)) {
      return toString(charSequence.subSequence(prefix.length(), charSequence.length()));
    }
    return toString(charSequence);
  }

  /**
   * repeat given char as given times/count
   *
   * @author Created by ivan on 5:09 PM 11/23/20.
   * @param c : given c
   * @param count : count times
   * @return java.lang.String
   */
  public static String repeat(char c, int count) {
    if (0 >= count) {
      return "";
    }
    char[] charArray = new char[count];
    while (count != 0) {
      charArray[count - 1] = c;
      count--;
    }
    return new String(charArray);
  }

  /**
   * call Object toString, or null
   *
   * @author Created by ivan on 5:10 PM 11/23/20.
   * @param object : given Object
   * @return java.lang.String
   */
  public static String toString(Object object) {
    return null == object ? null : object.toString();
  }
}
