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
   * @param object object
   * @param defaultValue defaultValue
   * @param <T> object Type
   * @return T
   * @author Created by ivan
   * @since 2023/3/29 17:32
   */
  public static <T> T defaultIfNull(final T object, final T defaultValue) {
    return isEmpty(object) ? object : defaultValue;
  }

  /**
   * isEmpty
   *
   * @param array array
   * @return boolean
   * @author Created by ivan
   * @since 2023/3/29 17:32
   */
  public static boolean isEmpty(Object[] array) {
    return array == null || array.length == 0;
  }

  /**
   * If Object null or content is empty
   *
   * @param obj obj
   * @return boolean
   * @author Created by ivan
   * @since 2023/3/29 17:33
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
   * @param obj obj
   * @return boolean
   * @author Created by ivan
   * @since 2023/3/29 17:33
   */
  public static boolean isNotEmpty(Object obj) {
    return !isEmpty(obj);
  }

  /**
   * Cast Object to String,return "null" or ""
   *
   * @param object object
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/29 17:33
   */
  public static String toString(Object object) {
    return StringHelper.nullSafeToString(object);
  }

  /**
   * System Array Copy
   *
   * @param src src
   * @param dest dest
   * @param length length
   * @return java.lang.Object
   * @author Created by ivan
   * @since 2023/3/29 17:34
   */
  public static Object arrayCopy(Object src, Object dest, int length) {
    return arrayCopy(src, dest, 0, 0, length);
  }

  /**
   * System Array Copy, different length
   *
   * @param src src
   * @param dest dest
   * @param srcPos srcPos
   * @param desPos desPos
   * @param length length
   * @return java.lang.Object
   * @author Created by ivan
   * @since 2023/3/29 17:34
   */
  public static Object arrayCopy(Object src, Object dest, int srcPos, int desPos, int length) {
    System.arraycopy(src, srcPos, dest, desPos, length);
    return dest;
  }

  /**
   * Convert the given array (which may be a primitive array) to an * object array (if necessary of
   * primitive wrapper objects).
   *
   * @param source source
   * @return java.lang.Object[]
   * @author Created by ivan
   * @since 2023/3/29 17:34
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
