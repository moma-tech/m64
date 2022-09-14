package top.moma.m64.core.helper;

import top.moma.m64.core.constants.StringConstants;
import top.moma.m64.core.helper.text.StringFormatter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;

/**
 * StringHelper
 *
 * <p>String Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/20/20.
 */
public class StringHelper {

  private StringHelper() {}

  /**
   * if String isNotBlank
   *
   * @author Created by ivan on 1:41 PM 11/23/20.
   * @param charSequence : String value
   * @return boolean
   */
  public static boolean isNotBlank(CharSequence charSequence) {
    return (charSequence != null && charSequence.length() > 0 && containsText(charSequence));
  }

  /**
   * description containsText
   *
   * @param str str
   * @return boolean
   * @author Created by ivan
   * @since 2022/9/13 17:34
   */
  private static boolean containsText(CharSequence str) {
    int strLen = str.length();
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }
    return false;
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
   * @author Created by ivan on 5:06 PM 11/23/20.
   * @param charSequence :
   * @return boolean
   */
  public static boolean isNotEmpty(CharSequence charSequence) {
    return (charSequence != null && charSequence.length() > 0);
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
   * String end with given suffix, case sensitive
   *
   * @author Created by ivan on 5:12 PM 11/30/20.
   * @param str : String
   * @param suffix : suffix
   * @return boolean
   */
  public static boolean endsWith(CharSequence str, CharSequence suffix) {
    return endsWith(str, suffix, false);
  }

  /**
   * String end with given suffix / ignoreCase
   *
   * @author Created by ivan on 5:12 PM 11/30/20.
   * @param str : String
   * @param suffix : suggix
   * @return boolean
   */
  public static boolean endsWithIgnoreCase(CharSequence str, CharSequence suffix) {
    return endsWith(str, suffix, true);
  }

  /**
   * String end with given suffix
   *
   * @author Created by ivan on 5:13 PM 11/30/20.
   * @param str : String
   * @param suffix : suffix
   * @param ignoreCase : if ignorcase
   * @return boolean
   */
  private static boolean endsWith(CharSequence str, CharSequence suffix, boolean ignoreCase) {
    if (str != null && suffix != null) {
      if (suffix.length() > str.length()) {
        return false;
      } else {
        int strOffset = str.length() - suffix.length();
        return regionMatches(str, ignoreCase, strOffset, suffix, 0, suffix.length());
      }
    } else {
      return str == null && suffix == null;
    }
  }

  /**
   * CharSequence Region Match given char sequence
   *
   * @author Created by ivan on 5:13 PM 11/30/20.
   * @param cs : Source Char Sequence
   * @param ignoreCase : if ignore case
   * @param thisStart : Start indexer of source string
   * @param substring : Given Char Sequence
   * @param start : Start indexer of given string
   * @param length : Length of Given Char Sequence
   * @return boolean
   */
  public static boolean regionMatches(
      CharSequence cs,
      boolean ignoreCase,
      int thisStart,
      CharSequence substring,
      int start,
      int length) {
    if (cs instanceof String && substring instanceof String) {
      return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
    } else {
      int index1 = thisStart;
      int index2 = start;
      int totalLength = length;

      while (totalLength-- > 0) {
        char c1 = cs.charAt(index1++);
        char c2 = substring.charAt(index2++);
        if (c1 != c2) {
          if (!ignoreCase) {
            return false;
          }

          if (Character.toUpperCase(c1) != Character.toUpperCase(c2)
              && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
            return false;
          }
        }
      }

      return true;
    }
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
   * Compare if String equals any of follows
   *
   * @author Created by ivan on 5:34 PM 11/25/20.
   * @param charSequence1 : given string
   * @param searchStrings : given patterns
   * @return boolean
   */
  public static boolean equalsAnyIgnoreCase(
      CharSequence charSequence1, CharSequence... searchStrings) {
    if (ObjectHelper.isNotEmpty(charSequence1) && ObjectHelper.isNotEmpty(searchStrings)) {
      CharSequence[] charSequences2 = searchStrings;
      int counter = searchStrings.length;

      for (int member = 0; member < counter; ++member) {
        CharSequence next = charSequences2[member];
        if (charSequence1.toString().equalsIgnoreCase(next.toString())) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Test If two Char Sequences are equals;
   *
   * @author Created by ivan on 5:36 PM 11/30/20.
   * @param cs1 : Char Sequence 1
   * @param cs2 : Char Sequence 2
   * @return boolean
   */
  public static boolean equals(CharSequence cs1, CharSequence cs2) {
    if (cs1 == cs2) {
      return true;
    } else if (cs1 != null && cs2 != null) {
      return cs1 instanceof String && cs2 instanceof String
          ? cs1.equals(cs2)
          : regionMatches(cs1, false, 0, cs2, 0, Math.max(cs1.length(), cs2.length()));
    } else {
      return false;
    }
  }

  /**
   * Split String on delimiter
   *
   * @author Created by ivan on 2:11 PM 11/30/20.
   * @param toSplit : String
   * @param delimiter : delimiter
   * @return java.lang.String[]
   */
  public static String[] split(String toSplit, String delimiter) {
    return tokenizeToStringArray(toSplit, delimiter);
  }

  public static String[] tokenizeToStringArray(String str, String delimiters) {
    return tokenizeToStringArray(str, delimiters, true, true);
  }

  public static String[] tokenizeToStringArray(
      String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

    if (str == null) {
      return new String[] {};
    }

    StringTokenizer st = new StringTokenizer(str, delimiters);
    List<String> tokens = new ArrayList<>();
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if (trimTokens) {
        token = token.trim();
      }
      if (!ignoreEmptyTokens || token.length() > 0) {
        tokens.add(token);
      }
    }
    return toStringArray(tokens);
  }

  /**
   * Copy the given {@link Enumeration} into a {@code String} array.
   *
   * <p>The {@code Enumeration} must contain {@code String} elements only.
   *
   * @param enumeration the {@code Enumeration} to copy (potentially {@code null} or empty)
   * @return the resulting {@code String} array
   */
  public static String[] toStringArray(Enumeration<String> enumeration) {
    return (enumeration != null ? toStringArray(Collections.list(enumeration)) : new String[] {});
  }
  /**
   * Copy the given {@link Collection} into a {@code String} array.
   *
   * <p>The {@code Collection} must contain {@code String} elements only.
   *
   * @param collection the {@code Collection} to copy (potentially {@code null} or empty)
   * @return the resulting {@code String} array
   */
  public static String[] toStringArray(Collection<String> collection) {
    return (CollectionHelper.isNotEmpty(collection)
        ? collection.toArray(new String[] {})
        : new String[] {});
  }

  /**
   * Capitalize first char
   *
   * @author Created by ivan on 3:28 PM 11/30/20.
   * @param str : Given String
   * @return java.lang.String
   */
  public static String capitalize(String str) {
    if (str != null && str.length() != 0) {
      char firstChar = str.charAt(0);
      return Character.isTitleCase(firstChar)
          ? str
          : Character.toTitleCase(firstChar) + str.substring(1);
    } else {
      return str;
    }
  }

  /**
   * Un-Capitalize first char
   *
   * @author Created by ivan on 3:29 PM 11/30/20.
   * @param str :
   * @return java.lang.String
   */
  public static String uncapitalize(String str) {
    if (str != null && str.length() != 0) {
      char firstChar = str.charAt(0);
      return Character.isLowerCase(firstChar)
          ? str
          : Character.toLowerCase(firstChar) + str.substring(1);
    } else {
      return str;
    }
  }

  /**
   * Replace String context
   *
   * @author Created by ivan on 5:03 PM 11/30/20.
   * @param text : given string
   * @param searchString : search string
   * @param replacement : replacement
   * @return java.lang.String
   */
  public static String replace(String text, String searchString, String replacement) {
    return replace(text, searchString, replacement, -1);
  }

  /**
   * Replace String context
   *
   * @author Created by ivan on 5:04 PM 11/30/20.
   * @param text :
   * @param searchString :
   * @param replacement :
   * @param max :
   * @return java.lang.String
   */
  public static String replace(String text, String searchString, String replacement, int max) {
    if (!isEmpty(text) && !isEmpty(searchString) && replacement != null && max != 0) {
      int start = 0;
      int end = text.indexOf(searchString, start);
      if (end == -1) {
        return text;
      } else {
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = increase < 0 ? 0 : increase;
        increase *= max < 0 ? 16 : (max > 64 ? 64 : max);

        StringBuilder buf;
        for (buf = new StringBuilder(text.length() + increase);
            end != -1;
            end = text.indexOf(searchString, start)) {
          buf.append(text, start, end).append(replacement);
          start = end + replLength;
          --max;
          if (max == 0) {
            break;
          }
        }

        buf.append(text.substring(start));
        return buf.toString();
      }
    } else {
      return text;
    }
  }

  /**
   * Replace All Matchs with replacement
   *
   * @author Created by ivan on 5:24 PM 11/30/20.
   * @param text : Given String
   * @param regex : regex pattern String
   * @param replacement : replacement
   * @return java.lang.String
   */
  public static String replaceAllWithRegex(String text, String regex, String replacement) {
    return text != null && regex != null && replacement != null
        ? text.replaceAll(regex, replacement)
        : text;
  }

  /**
   * Sub String between String tags
   *
   * @author Created by ivan on 5:07 PM 11/30/20.
   * @param str : Given String
   * @param tag : open/close tag
   * @return java.lang.String
   */
  public static String substringBetween(String str, String tag) {
    return substringBetween(str, tag, tag);
  }

  /**
   * Sub String between String tags(open/close)
   *
   * @author Created by ivan on 5:07 PM 11/30/20.
   * @param str : Given String
   * @param open : Open String tag
   * @param close : Close String tag
   * @return java.lang.String
   */
  public static String substringBetween(String str, String open, String close) {
    if (str != null && open != null && close != null) {
      int start = str.indexOf(open);
      if (start != -1) {
        int end = str.indexOf(close, start + open.length());
        if (end != -1) {
          return str.substring(start + open.length(), end);
        }
      }

      return null;
    } else {
      return null;
    }
  }

  /**
   * Get SubString of Given String before separator
   *
   * @author Created by ivan on 5:20 PM 11/30/20.
   * @param str : Given String
   * @param separator : separator
   * @return java.lang.String
   */
  public static String substringBefore(String str, String separator) {
    if (!isEmpty(str) && separator != null) {
      if (separator.isEmpty()) {
        return "";
      } else {
        int pos = str.indexOf(separator);
        return pos == -1 ? str : str.substring(0, pos);
      }
    } else {
      return str;
    }
  }

  /**
   * Find Index of search Char Sequence in Source Char Sequence
   *
   * @author Created by ivan on 5:18 PM 11/30/20.
   * @param seq : Source Char Sequence
   * @param searchSeq : search Char Sequence
   * @return int
   */
  public static int indexOf(CharSequence seq, CharSequence searchSeq) {
    return seq != null && searchSeq != null ? seq.toString().indexOf(searchSeq.toString()) : -1;
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

  /**
   * to Snake Case
   *
   * @author Created by Ivan at 2021/3/15.
   * @return String in Snake Case
   * @param s : String
   */
  public static String toSnakeCase(String s) {
    if (s == null) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    boolean upperCase = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      boolean nextUpperCase = true;

      if (i < (s.length() - 1)) {
        nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
      }

      if (Character.isUpperCase(c)) {
        if ((!upperCase || !nextUpperCase) && i > 0) {
          sb.append('_');
        }
        upperCase = true;
      } else {
        upperCase = false;
      }

      sb.append(Character.toLowerCase(c));
    }

    return sb.toString();
  }

  /**
   * to Camel Case
   *
   * @author Created by Ivan at 2021/3/15.
   * @return java.lang.String : Camel Case
   * @param s : String
   */
  public static String toCamelCase(String s) {
    if (s == null) {
      return null;
    }

    s = s.toLowerCase();

    StringBuilder sb = new StringBuilder(s.length());
    boolean upperCase = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '_') {
        upperCase = true;
      } else if (upperCase) {
        sb.append(Character.toUpperCase(c));
        upperCase = false;
      } else {
        sb.append(c);
      }
    }

    return sb.toString();
  }

  /**
   * To Capitalize Camel Case
   *
   * @author Created by Ivan at 2021/3/15.
   * @return java.lang.String: Capitalize Camel Case
   * @param s : String
   */
  public static String toCapitalizeCamelCase(String s) {
    if (s == null) {
      return null;
    }
    s = toCamelCase(s);
    return s.substring(0, 1).toUpperCase() + s.substring(1);
  }

  /**
   * Return a String representation of the specified Object.
   *
   * <p>Builds a String representation of the contents in case of an array. Returns a {@code "null"}
   * String if {@code obj} is {@code null}.
   *
   * @param obj the object to build a String representation for
   * @return a String representation of {@code obj}
   */
  public static String nullSafeToString(Object obj) {
    if (obj == null) {
      return StringConstants.NULL;
    }
    if (obj instanceof String) {
      return (String) obj;
    }
    if (obj instanceof Object[]) {
      return nullSafeToString((Object[]) obj);
    }
    if (obj instanceof boolean[]) {
      return nullSafeToString((boolean[]) obj);
    }
    if (obj instanceof byte[]) {
      return nullSafeToString((byte[]) obj);
    }
    if (obj instanceof char[]) {
      return nullSafeToString((char[]) obj);
    }
    if (obj instanceof double[]) {
      return nullSafeToString((double[]) obj);
    }
    if (obj instanceof float[]) {
      return nullSafeToString((float[]) obj);
    }
    if (obj instanceof int[]) {
      return nullSafeToString((int[]) obj);
    }
    if (obj instanceof long[]) {
      return nullSafeToString((long[]) obj);
    }
    if (obj instanceof short[]) {
      return nullSafeToString((short[]) obj);
    }
    String str = obj.toString();
    return (str != null ? str : StringConstants.EMPTY);
  }

  /**
   * Return a String representation of the contents of the specified array.
   *
   * <p>The String representation consists of a list of the array's elements, enclosed in curly
   * braces ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns a {@code "null"} String if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(Object[] array) {
    if (array == null) {
      return StringConstants.NULL;
    }
    int length = array.length;
    if (length == 0) {
      return StringConstants.EMPTY_ARRAY;
    }
    StringJoiner stringJoiner =
        new StringJoiner(
            StringConstants.ARRAY_ELEMENT_SEPARATOR,
            StringConstants.DELIM_START,
            StringConstants.DELIM_END);
    for (Object o : array) {
      stringJoiner.add(String.valueOf(o));
    }
    return stringJoiner.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   *
   * <p>The String representation consists of a list of the array's elements, enclosed in curly
   * braces ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns a {@code "null"} String if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(boolean[] array) {
    if (array == null) {
      return StringConstants.NULL;
    }
    int length = array.length;
    if (length == 0) {
      return StringConstants.EMPTY_ARRAY;
    }
    StringJoiner stringJoiner =
        new StringJoiner(
            StringConstants.ARRAY_ELEMENT_SEPARATOR,
            StringConstants.DELIM_START,
            StringConstants.DELIM_END);
    for (boolean b : array) {
      stringJoiner.add(String.valueOf(b));
    }
    return stringJoiner.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   *
   * <p>The String representation consists of a list of the array's elements, enclosed in curly
   * braces ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns a {@code "null"} String if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(byte[] array) {
    if (array == null) {
      return StringConstants.NULL;
    }
    int length = array.length;
    if (length == 0) {
      return StringConstants.EMPTY_ARRAY;
    }
    StringJoiner stringJoiner =
        new StringJoiner(
            StringConstants.ARRAY_ELEMENT_SEPARATOR,
            StringConstants.DELIM_START,
            StringConstants.DELIM_END);
    for (byte b : array) {
      stringJoiner.add(String.valueOf(b));
    }
    return stringJoiner.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   *
   * <p>The String representation consists of a list of the array's elements, enclosed in curly
   * braces ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns a {@code "null"} String if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(char[] array) {
    if (array == null) {
      return StringConstants.NULL;
    }
    int length = array.length;
    if (length == 0) {
      return StringConstants.EMPTY_ARRAY;
    }
    StringJoiner stringJoiner =
        new StringJoiner(
            StringConstants.ARRAY_ELEMENT_SEPARATOR,
            StringConstants.DELIM_START,
            StringConstants.DELIM_END);
    for (char c : array) {
      stringJoiner.add('\'' + String.valueOf(c) + '\'');
    }
    return stringJoiner.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   *
   * <p>The String representation consists of a list of the array's elements, enclosed in curly
   * braces ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns a {@code "null"} String if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(double[] array) {
    if (array == null) {
      return StringConstants.NULL;
    }
    int length = array.length;
    if (length == 0) {
      return StringConstants.EMPTY_ARRAY;
    }
    StringJoiner stringJoiner =
        new StringJoiner(
            StringConstants.ARRAY_ELEMENT_SEPARATOR,
            StringConstants.DELIM_START,
            StringConstants.DELIM_END);
    for (double d : array) {
      stringJoiner.add(String.valueOf(d));
    }
    return stringJoiner.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   *
   * <p>The String representation consists of a list of the array's elements, enclosed in curly
   * braces ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns a {@code "null"} String if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(float[] array) {
    if (array == null) {
      return StringConstants.NULL;
    }
    int length = array.length;
    if (length == 0) {
      return StringConstants.EMPTY_ARRAY;
    }
    StringJoiner stringJoiner =
        new StringJoiner(
            StringConstants.ARRAY_ELEMENT_SEPARATOR,
            StringConstants.DELIM_START,
            StringConstants.DELIM_END);
    for (float f : array) {
      stringJoiner.add(String.valueOf(f));
    }
    return stringJoiner.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   *
   * <p>The String representation consists of a list of the array's elements, enclosed in curly
   * braces ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns a {@code "null"} String if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(int[] array) {
    if (array == null) {
      return StringConstants.NULL;
    }
    int length = array.length;
    if (length == 0) {
      return StringConstants.EMPTY_ARRAY;
    }
    StringJoiner stringJoiner =
        new StringJoiner(
            StringConstants.ARRAY_ELEMENT_SEPARATOR,
            StringConstants.DELIM_START,
            StringConstants.DELIM_END);
    for (int i : array) {
      stringJoiner.add(String.valueOf(i));
    }
    return stringJoiner.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   *
   * <p>The String representation consists of a list of the array's elements, enclosed in curly
   * braces ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns a {@code "null"} String if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(long[] array) {
    if (array == null) {
      return StringConstants.NULL;
    }
    int length = array.length;
    if (length == 0) {
      return StringConstants.EMPTY_ARRAY;
    }
    StringJoiner stringJoiner =
        new StringJoiner(
            StringConstants.ARRAY_ELEMENT_SEPARATOR,
            StringConstants.DELIM_START,
            StringConstants.DELIM_END);
    for (long l : array) {
      stringJoiner.add(String.valueOf(l));
    }
    return stringJoiner.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   *
   * <p>The String representation consists of a list of the array's elements, enclosed in curly
   * braces ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns a {@code "null"} String if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(short[] array) {
    if (array == null) {
      return StringConstants.NULL;
    }
    int length = array.length;
    if (length == 0) {
      return StringConstants.EMPTY_ARRAY;
    }
    StringJoiner stringJoiner =
        new StringJoiner(
            StringConstants.ARRAY_ELEMENT_SEPARATOR,
            StringConstants.DELIM_START,
            StringConstants.DELIM_END);
    for (short s : array) {
      stringJoiner.add(String.valueOf(s));
    }
    return stringJoiner.toString();
  }
}
