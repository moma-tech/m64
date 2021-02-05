package top.moma.m64.core.helper;

import top.moma.m64.core.constants.StringConstants;
import top.moma.m64.core.exceptions.M64Exception;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;

/**
 * TypeHelper
 *
 * <p>Cast Object Type
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/25/20.
 */
public class TypeHelper {
  /**
   * 转换为字符<br>
   * 如果给定的值为null，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static Character toChar(Object value, Character defaultValue) {
    if (null == value) {
      return defaultValue;
    }
    if (value instanceof Character) {
      return (Character) value;
    }

    final String valueStr = StringHelper.toString(value, null);
    return StringHelper.isEmpty(valueStr) ? defaultValue : valueStr.charAt(0);
  }

  /**
   * 转换为字符<br>
   * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static Character toChar(Object value) {
    return toChar(value, null);
  }

  /**
   * 转换为byte<br>
   * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static Byte toByte(Object value, Byte defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (value instanceof Byte) {
      return (Byte) value;
    }
    if (value instanceof Number) {
      return ((Number) value).byteValue();
    }
    final String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    try {
      return Byte.parseByte(valueStr);
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * 转换为byte<br>
   * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static Byte toByte(Object value) {
    return toByte(value, null);
  }

  /**
   * 转换为Short<br>
   * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static Short toShort(Object value, Short defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (value instanceof Short) {
      return (Short) value;
    }
    if (value instanceof Number) {
      return ((Number) value).shortValue();
    }
    final String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    try {
      return Short.parseShort(valueStr.trim());
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * 转换为Short<br>
   * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static Short toShort(Object value) {
    return toShort(value, null);
  }

  /**
   * 转换为Number<br>
   * 如果给定的值为空，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static Number toNumber(Object value, Number defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (value instanceof Number) {
      return (Number) value;
    }
    final String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    try {
      return NumberFormat.getInstance().parse(valueStr);
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * 转换为Number<br>
   * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static Number toNumber(Object value) {
    return toNumber(value, null);
  }

  /**
   * 转换为int<br>
   * 如果给定的值为空，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static Integer toInt(Object value, Integer defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (value instanceof Integer) {
      return (Integer) value;
    }
    if (value instanceof Number) {
      return ((Number) value).intValue();
    }
    final String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    try {
      return Integer.parseInt(valueStr.trim());
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * 转换为int<br>
   * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static Integer toInt(Object value) {
    return toInt(value, null);
  }

  /**
   * 转换为Integer数组<br>
   *
   * @param str 被转换的值
   * @return 结果
   */
  public static Integer[] toIntArray(String str) {
    return toIntArray(StringConstants.COMMA, str);
  }

  /**
   * 转换为Long数组<br>
   *
   * @param str 被转换的值
   * @return 结果
   */
  public static Long[] toLongArray(String str) {
    return toLongArray(StringConstants.COMMA, str);
  }

  /**
   * 转换为Integer数组<br>
   *
   * @param split 分隔符
   * @param split 被转换的值
   * @return 结果
   */
  public static Integer[] toIntArray(String split, String str) {
    if (StringHelper.isEmpty(str)) {
      return new Integer[] {};
    }
    String[] arr = str.split(split);
    final Integer[] ints = new Integer[arr.length];
    for (int i = 0; i < arr.length; i++) {
      final Integer v = toInt(arr[i], 0);
      ints[i] = v;
    }
    return ints;
  }

  /**
   * 转换为Long数组<br>
   *
   * @param split 分隔符
   * @param str 被转换的值
   * @return 结果
   */
  public static Long[] toLongArray(String split, String str) {
    if (StringHelper.isEmpty(str)) {
      return new Long[] {};
    }
    String[] arr = str.split(split);
    final Long[] longs = new Long[arr.length];
    for (int i = 0; i < arr.length; i++) {
      final Long v = toLong(arr[i], null);
      longs[i] = v;
    }
    return longs;
  }

  /**
   * 转换为String数组<br>
   *
   * @param str 被转换的值
   * @return 结果
   */
  public static String[] toStrArray(String str) {
    return toStrArray(",", str);
  }

  /**
   * 转换为String数组<br>
   *
   * @param split 分隔符
   * @param split 被转换的值
   * @return 结果
   */
  public static String[] toStrArray(String split, String str) {
    return str.split(split);
  }

  /**
   * 转换为long<br>
   * 如果给定的值为空，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static Long toLong(Object value, Long defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (value instanceof Long) {
      return (Long) value;
    }
    if (value instanceof Number) {
      return ((Number) value).longValue();
    }
    final String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    try {
      // 支持科学计数法
      return new BigDecimal(valueStr.trim()).longValue();
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * 转换为long<br>
   * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static Long toLong(Object value) {
    return toLong(value, null);
  }

  /**
   * 转换为double<br>
   * 如果给定的值为空，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static Double toDouble(Object value, Double defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (value instanceof Double) {
      return (Double) value;
    }
    if (value instanceof Number) {
      return ((Number) value).doubleValue();
    }
    final String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    try {
      // 支持科学计数法
      return new BigDecimal(valueStr.trim()).doubleValue();
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * 转换为double<br>
   * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static Double toDouble(Object value) {
    return toDouble(value, null);
  }

  /**
   * 转换为Float<br>
   * 如果给定的值为空，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static Float toFloat(Object value, Float defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (value instanceof Float) {
      return (Float) value;
    }
    if (value instanceof Number) {
      return ((Number) value).floatValue();
    }
    final String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    try {
      return Float.parseFloat(valueStr.trim());
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * 转换为Float<br>
   * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static Float toFloat(Object value) {
    return toFloat(value, null);
  }

  /**
   * 转换为boolean<br>
   * String支持的值为：true、false、yes、ok、no，1,0 如果给定的值为空，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static Boolean toBool(Object value, Boolean defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (value instanceof Boolean) {
      return (Boolean) value;
    }
    String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    valueStr = valueStr.trim().toLowerCase();
    switch (valueStr) {
      case "true":
        return true;
      case "false":
        return false;
      case "yes":
        return true;
      case "ok":
        return true;
      case "no":
        return false;
      case "1":
        return true;
      case "0":
        return false;
      default:
        return defaultValue;
    }
  }

  /**
   * 转换为boolean<br>
   * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static Boolean toBool(Object value) {
    return toBool(value, null);
  }

  /**
   * 转换为Enum对象<br>
   * 如果给定的值为空，或者转换失败，返回默认值<br>
   *
   * @param clazz Enum的Class
   * @param value 值
   * @param defaultValue 默认值
   * @return Enum
   */
  public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value, E defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (clazz.isAssignableFrom(value.getClass())) {
      @SuppressWarnings("unchecked")
      E myE = (E) value;
      return myE;
    }
    final String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    try {
      return Enum.valueOf(clazz, valueStr);
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * 转换为Enum对象<br>
   * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
   *
   * @param clazz Enum的Class
   * @param value 值
   * @return Enum
   */
  public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value) {
    return toEnum(clazz, value, null);
  }

  /**
   * 转换为BigInteger<br>
   * 如果给定的值为空，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static BigInteger toBigInteger(Object value, BigInteger defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (value instanceof BigInteger) {
      return (BigInteger) value;
    }
    if (value instanceof Long) {
      return BigInteger.valueOf((Long) value);
    }
    final String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    try {
      return new BigInteger(valueStr);
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * 转换为BigInteger<br>
   * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static BigInteger toBigInteger(Object value) {
    return toBigInteger(value, null);
  }

  /**
   * 转换为BigDecimal<br>
   * 如果给定的值为空，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @param defaultValue 转换错误时的默认值
   * @return 结果
   */
  public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
    if (value == null) {
      return defaultValue;
    }
    if (value instanceof BigDecimal) {
      return (BigDecimal) value;
    }
    if (value instanceof Long) {
      return new BigDecimal((Long) value);
    }
    if (value instanceof Double) {
      return new BigDecimal((Double) value);
    }
    if (value instanceof Integer) {
      return new BigDecimal((Integer) value);
    }
    final String valueStr = StringHelper.toString(value, null);
    if (StringHelper.isEmpty(valueStr)) {
      return defaultValue;
    }
    try {
      return new BigDecimal(valueStr);
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * 转换为BigDecimal<br>
   * 如果给定的值为空，或者转换失败，返回默认值<br>
   * 转换失败不会报错
   *
   * @param value 被转换的值
   * @return 结果
   */
  public static BigDecimal toBigDecimal(Object value) {
    return toBigDecimal(value, null);
  }

  /**
   * 十六进制串转化为byte数组
   *
   * @return the array of byte
   */
  public static byte[] hexToByte(String hex) throws IllegalArgumentException {
    if (hex.length() % 2 != 0) {
      throw new IllegalArgumentException();
    }
    char[] arr = hex.toCharArray();
    byte[] b = new byte[hex.length() / 2];
    for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
      String swap = "" + arr[i++] + arr[i];
      int byteint = Integer.parseInt(swap, 16) & 0xFF;
      b[j] = new Integer(byteint).byteValue();
    }
    return b;
  }

  /**
   * 字节数组转换为十六进制字符串
   *
   * @param b byte[] 需要转换的字节数组
   * @return String 十六进制字符串
   */
  public static String byteToHex(byte b[]) {
    if (b == null) {
      throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
    }
    String hs = "";
    String stmp;
    for (int n = 0; n < b.length; n++) {
      stmp = Integer.toHexString(b[n] & 0xff);
      if (stmp.length() == 1) {
        hs = hs + "0" + stmp;
      } else {
        hs = hs + stmp;
      }
    }
    return hs.toLowerCase();
    // return hs.toUpperCase();
  }

  /**
   * Convert hex string to byte[]
   *
   * @param hexString the hex string
   * @return byte[]
   */
  public static byte[] hexStringToBytes(String hexString) {
    if (hexString == null || hexString.equals("")) {
      return null;
    }

    hexString = hexString.toUpperCase();
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toCharArray();
    byte[] d = new byte[length];
    for (int i = 0; i < length; i++) {
      int pos = i * 2;
      d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
    }
    return d;
  }

  /**
   * Convert char to byte
   *
   * @param c char
   * @return byte
   */
  public static byte charToByte(char c) {
    return (byte) "0123456789ABCDEF".indexOf(c);
  }
  /**
   * 数字金额大写转换 先写个完整的然后将如零拾替换成零
   *
   * @param n 数字
   * @return 中文大写数字
   */
  public static String digitUppercase(double n) {
    String[] fraction = {"角", "分"};
    String[] digit = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    String[][] unit = {{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};

    String head = n < 0 ? "负" : "";
    n = Math.abs(n);

    StringBuilder s = new StringBuilder();
    for (int i = 0; i < fraction.length; i++) {
      s.append(
          (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i])
              .replaceAll("(零.)+", ""));
    }
    if (s.length() < 1) {
      s = new StringBuilder("整");
    }
    int integerPart = (int) Math.floor(n);

    for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
      StringBuilder p = new StringBuilder();
      for (int j = 0; j < unit[1].length && n > 0; j++) {
        p.insert(0, digit[integerPart % 10] + unit[1][j]);
        integerPart = integerPart / 10;
      }
      s.insert(0, p.toString().replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i]);
    }
    return head
        + s.toString()
            .replaceAll("(零.)*零元", "元")
            .replaceFirst("(零.)+", "")
            .replaceAll("(零.)+", "零")
            .replaceAll("^整$", "零元整");
  }

  /**
   * cast Source to T
   *
   * @author Created by ivan on 5:50 PM 2/5/21.
   * @param source : source object
   * @return T: return Object
   */
  @SuppressWarnings("unchecked")
  public static <T> T cast(Object source) {
    try {
      return (T) source;
    } catch (Exception ex) {
      throw new M64Exception(ex);
    }
  }
}
