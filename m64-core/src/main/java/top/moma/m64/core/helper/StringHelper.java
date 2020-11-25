package top.moma.m64.core.helper;

import org.springframework.util.StringUtils;
import top.moma.m64.core.constants.StringConstants;
import top.moma.m64.core.helper.text.StringFormatter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Set;

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
   * Get SubString
   *
   * @author Created by ivan on 5:19 PM 11/25/20.
   * @param data : string
   * @param start : strart index
   * @param end : end index
   * @return java.lang.String
   */
  public static String substring(final String data, int start, int end) {
    if (null == data) {
      return null;
    }
    // handle negatives
    if (end < 0) {
      end = data.length() + end; // remember end is negative
    }
    if (start < 0) {
      start = data.length() + start; // remember start is negative
    }
    // check length next
    if (end > data.length()) {
      end = data.length();
    }
    // if start is greater than end, return ""
    if (start > end) {
      return StringConstants.EMPTY;
    }
    if (start < 0) {
      start = 0;
    }
    if (end < 0) {
      end = 0;
    }
    return data.substring(start, end);
  }
  /**
   * cast Object toString, or null
   *
   * @author Created by ivan on 5:10 PM 11/23/20.
   * @param object : given Object
   * @return java.lang.String
   */
  public static String toString(Object object) {
    return toString(object, null);
  }

  /**
   * ast Object toString, or default value
   *
   * @author Created by ivan on 10:43 AM 11/25/20.
   * @param object :
   * @param defaultValue :
   * @return java.lang.String
   */
  public static String toString(Object object, String defaultValue) {
    return toString(object, defaultValue, null);
  }

  /**
   * cast Object to toString, or default value, consider charset for byte
   *
   * @author Created by ivan on 10:18 AM 11/25/20.
   * @param object : given Object
   * @param defaultValue : default Value
   * @return java.lang.String
   */
  public static String toString(Object object, String defaultValue, String charset) {
    if (null == object) {
      return defaultValue;
    }
    if (object instanceof String) {
      return (String) object;
    } else if (object instanceof byte[] || object instanceof Byte[]) {
      return toString((byte[]) object, charset);
    } else if (object instanceof ByteBuffer) {
      return toString((ByteBuffer) object, charset);
    } else {
      return ObjectHelper.toString(object);
    }
  }

  /**
   * 将编码的byteBuffer数据转换为字符串
   *
   * @param data 数据
   * @param charset 字符集，如果为空使用当前系统字符集
   * @return 字符串
   */
  public static String toString(ByteBuffer data, Charset charset) {
    if (null == charset) {
      charset = Charset.defaultCharset();
    }
    return charset.decode(data).toString();
  }
  /**
   * 将编码的byteBuffer数据转换为字符串
   *
   * @param data 数据
   * @param charset 字符集，如果为空使用当前系统字符集
   * @return 字符串
   */
  public static String toString(ByteBuffer data, String charset) {
    if (null == data) {
      return null;
    }
    return toString(data, Charset.forName(charset));
  }

  /**
   * 解码字节码
   *
   * @param data 字符串
   * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
   * @return 解码后的字符串
   */
  public static String toString(byte[] data, Charset charset) {
    if (null == data) {
      return null;
    }
    if (null == charset) {
      return new String(data);
    }
    return new String(data, charset);
  }

  /**
   * 将byte数组转为字符串
   *
   * @param bytes byte数组
   * @param charset 字符集
   * @return 字符串
   */
  public static String toString(byte[] bytes, String charset) {
    return toString(bytes, isEmpty(charset) ? Charset.defaultCharset() : Charset.forName(charset));
  }

  /**
   * 半角转全角
   *
   * @param input String.
   * @return 全角字符串.
   */
  public static String toSBC(String input) {
    return toSBC(input, null);
  }

  /**
   * 半角转全角
   *
   * @param input String
   * @param notConvertSet 不替换的字符集合
   * @return 全角字符串.
   */
  public static String toSBC(String input, Set<Character> notConvertSet) {
    char[] chars = input.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (null != notConvertSet && notConvertSet.contains(chars[i])) {
        // 跳过不替换的字符
        continue;
      }

      if (chars[i] == ' ') {
        chars[i] = '\u3000';
      } else if (chars[i] < '\177') {
        chars[i] = (char) (chars[i] + 65248);
      }
    }
    return new String(chars);
  }

  /**
   * 全角转半角
   *
   * @param input String.
   * @return 半角字符串
   */
  public static String toDBC(String input) {
    return toDBC(input, null);
  }

  /**
   * 替换全角为半角
   *
   * @param text 文本
   * @param notConvertSet 不替换的字符集合
   * @return 替换后的字符
   */
  public static String toDBC(String text, Set<Character> notConvertSet) {
    char[] chars = text.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (null != notConvertSet && notConvertSet.contains(chars[i])) {
        // 跳过不替换的字符
        continue;
      }

      if (chars[i] == '\u3000') {
        chars[i] = ' ';
      } else if (chars[i] > '\uFF00' && chars[i] < '\uFF5F') {
        chars[i] = (char) (chars[i] - 65248);
      }
    }
    return new String(chars);
  }

  /**
   * 格式化字符串<br>
   * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
   * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
   * 例：<br>
   * 通常使用：format("this is {} for {}", "a", "b") =》 this is a for b<br>
   * 转义{}： format("this is \\{} for {}", "a", "b") =》 this is \{} for a<br>
   * 转义\： format("this is \\\\{} for {}", "a", "b") =》 this is \a for b<br>
   *
   * @author Created by ivan on 2:51 PM 11/25/20.
   * @param charSequence : String template
   * @param params : Replace value
   * @return java.lang.String
   */
  public static String format(CharSequence charSequence, Object... params) {
    if (null == charSequence) {
      return StringConstants.NULL;
    }
    if (ObjectHelper.isEmpty(params) || isBlank(charSequence)) {
      return charSequence.toString();
    }
    return StringFormatter.format(charSequence.toString(), params);
  }
}
