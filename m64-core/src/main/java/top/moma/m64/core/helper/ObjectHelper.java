package top.moma.m64.core.helper;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * ObjectHelper
 *
 * <p>Object Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/20/20.
 */
public class ObjectHelper {

  private ObjectHelper() {}

  /**
   * if object is null, return default
   *
   * @author Created by ivan on 5:30 PM 11/20/20.
   * @return T
   */
  public static <T> T defaultIfNull(final T object, final T defaultValue) {
    return isEmpty(object) ? object : defaultValue;
  }

  public static boolean isEmpty(Object[] array) {
    return array == null || array.length == 0;
  }

  /**
   * If Object null or content is empty
   *
   * @author Created by ivan on 1:39 PM 11/23/20.
   * @param obj : Object
   * @return boolean
   */
  public static boolean isEmpty(Object obj) {
    if (obj == null) {
      return true;
    } else if (obj instanceof Optional) {
      return !((Optional) obj).isPresent();
    } else if (obj instanceof CharSequence) {
      return ((CharSequence) obj).length() == 0;
    } else if (obj.getClass().isArray()) {
      return Array.getLength(obj) == 0;
    } else if (obj instanceof Collection) {
      return ((Collection) obj).isEmpty();
    } else {
      return obj instanceof Map && ((Map) obj).isEmpty();
    }
  }

  /**
   * If Object not null and content is not empty
   *
   * @author Created by ivan on 1:39 PM 11/23/20.
   * @param obj : Object
   * @return boolean
   */
  public static boolean isNotEmpty(Object obj) {
    return !isEmpty(obj);
  }

  /**
   * Cast Object to String,return "null" or ""
   *
   * @author Created by ivan on 11:12 AM 11/25/20.
   * @param object : object
   * @return java.lang.String
   */
  public static String toString(Object object) {
    return StringHelper.nullSafeToString(object);
  }

  /**
   * System Array Copy
   *
   * @author Created by ivan on 2:10 PM 11/25/20.
   * @param src : source object
   * @param dest : destination object
   * @param length : request length
   * @return java.lang.Object SYSTEM
   */
  public static Object arrayCopy(Object src, Object dest, int length) {
    return arrayCopy(src, dest, 0, 0, length);
  }

  /**
   * System Array Copy, different length
   *
   * @author Created by ivan on 5:41 PM 11/30/20.
   * @param src : source object
   * @param dest : destination object
   * @param srcPos : source start position
   * @param desPos : destination start position
   * @param length : wak length
   * @return java.lang.Object
   */
  public static Object arrayCopy(Object src, Object dest, int srcPos, int desPos, int length) {
    System.arraycopy(src, srcPos, dest, desPos, length);
    return dest;
  }

  /**
   * Convert the given array (which may be a primitive array) to an * object array (if necessary of
   * primitive wrapper objects).
   *
   * @author Created by ivan on 2:28 PM 11/30/20.
   * @param source : given source
   * @return java.lang.Object[]
   */
  public static Object[] toObjectArray(Object source) {
    if (source instanceof Object[]) {
      return (Object[]) source;
    }
    if (source == null) {
      return new Object[0];
    }
    if (!source.getClass().isArray()) {
      throw new IllegalArgumentException("Source is not an array: " + source);
    }
    int length = Array.getLength(source);
    if (length == 0) {
      return new Object[0];
    }
    Class<?> wrapperType = Array.get(source, 0).getClass();
    Object[] newArray = (Object[]) Array.newInstance(wrapperType, length);
    for (int i = 0; i < length; i++) {
      newArray[i] = Array.get(source, i);
    }
    return newArray;
  }
}
