package top.moma.m64.core.helper;

import top.moma.m64.core.constants.StringConstants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * CharsetHelper
 *
 * <p>Charset Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/25/20.
 */
public class CharsetHelper {
  /** ISO-8859-1 */
  public static final Charset CHARSET_ISO_8859_1 = StandardCharsets.ISO_8859_1;
  /** UTF-8 */
  public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;
  /** GBK */
  public static final Charset CHARSET_GBK = Charset.forName(StringConstants.GBK);

  /**
   * 转换为Charset对象
   *
   * @param charset 字符集，为空则返回默认字符集
   * @return Charset
   */
  public static Charset charset(String charset) {
    return StringHelper.isEmpty(charset) ? Charset.defaultCharset() : Charset.forName(charset);
  }

  /**
   * 转换字符串的字符集编码
   *
   * @param source 字符串
   * @param srcCharset 源字符集，默认ISO-8859-1
   * @param destCharset 目标字符集，默认UTF-8
   * @return 转换后的字符集
   */
  public static String convert(String source, String srcCharset, String destCharset) {
    return convert(source, Charset.forName(srcCharset), Charset.forName(destCharset));
  }

  /**
   * 转换字符串的字符集编码
   *
   * @param source 字符串
   * @param srcCharset 源字符集，默认ISO-8859-1
   * @param destCharset 目标字符集，默认UTF-8
   * @return 转换后的字符集
   */
  public static String convert(String source, Charset srcCharset, Charset destCharset) {
    if (null == srcCharset) {
      srcCharset = StandardCharsets.ISO_8859_1;
    }

    if (null == destCharset) {
      srcCharset = StandardCharsets.UTF_8;
    }

    if (StringHelper.isEmpty(source) || srcCharset.equals(destCharset)) {
      return source;
    }
    return new String(source.getBytes(srcCharset), destCharset);
  }

  /** @return 系统字符集编码 */
  public static String systemCharset() {
    return Charset.defaultCharset().name();
  }
}
