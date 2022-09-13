package top.moma.m64.core.constants;

/**
 * StringConstants
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/23/20.
 */
public class StringConstants {
  /** ISO-8859-1 */
  public static final String ISO_8859_1 = "ISO-8859-1";
  /** UTF-8 */
  public static final String UTF_8 = "UTF-8";
  /** GBK */
  public static final String GBK = "GBK";

  /** 字符串常量：空格符 {@code " "} */
  public static final String SPACE = " ";

  /** 字符串常量：制表符 {@code "\t"} */
  public static final String TAB = "	";

  /** 字符串常量：点 {@code "."} */
  public static final String DOT = ".";

  /**
   * 字符串常量：双点 {@code ".."} <br>
   * 用途：作为指向上级文件夹的路径，如：{@code "../path"}
   */
  public static final String DOUBLE_DOT = "..";

  /** 字符串常量：斜杠 {@code "/"} */
  public static final String SLASH = "/";

  /** 字符串常量：反斜杠 {@code "\\"} */
  public static final String BACKSLASH = "\\";

  /** 字符串常量：空字符串 {@code ""} */
  public static final String EMPTY = "";

  /**
   * 字符串常量：{@code "null"} <br>
   * 注意：{@code "null" != null}
   */
  public static final String NULL = "null";

  /**
   * 字符串常量：回车符 {@code "\r"} <br>
   * 解释：该字符常用于表示 Linux 系统和 MacOS 系统下的文本换行
   */
  public static final String CR = "\r";

  /** 字符串常量：换行符 {@code "\n"} */
  public static final String LF = "\n";

  /**
   * 字符串常量：Windows 换行 {@code "\r\n"} <br>
   * 解释：该字符串常用于表示 Windows 系统下的文本换行
   */
  public static final String CRLF = "\r\n";

  /** 字符串常量：下划线 {@code "_"} */
  public static final String UNDERLINE = "_";

  /** 字符串常量：减号（连接符） {@code "-"} */
  public static final String DASHED = "-";

  /** 字符串常量：逗号 {@code ","} */
  public static final String COMMA = ",";

  /** 字符串常量：花括号（左） <code>"{"</code> */
  public static final String DELIM_START = "{";

  /** 字符串常量：花括号（右） <code>"}"</code> */
  public static final String DELIM_END = "}";

  /** 字符串常量：中括号（左） {@code "["} */
  public static final String BRACKET_START = "[";

  /** 字符串常量：中括号（右） {@code "]"} */
  public static final String BRACKET_END = "]";

  /** 字符串常量：冒号 {@code ":"} */
  public static final String COLON = ":";

  /** 字符串常量：艾特 <code>"@"</code> */
  public static final String AT = "@";

  /** 字符串常量：等于 {@code "="} */
  public static final String EQUAL = "=";

  /** 字符串常量：和 {@code "&"} */
  public static final String AMP = "&";

  /** 字符串常量：HTML 空格转义 {@code "&nbsp;" -> " "} */
  public static final String HTML_NBSP = "&nbsp;";

  /** 字符串常量：HTML And 符转义 {@code "&amp;" -> "&"} */
  public static final String HTML_AMP = "&amp;";

  /** 字符串常量：HTML 双引号转义 {@code "&quot;" -> "\""} */
  public static final String HTML_QUOTE = "&quot;";

  /** 字符串常量：HTML 单引号转义 {@code "&apos" -> "'"} */
  public static final String HTML_APOS = "&apos;";

  /** 字符串常量：HTML 小于号转义 {@code "&lt;" -> "<"} */
  public static final String HTML_LT = "&lt;";

  /** 字符串常量：HTML 大于号转义 {@code "&gt;" -> ">"} */
  public static final String HTML_GT = "&gt;";

  /** 字符串常量：空 JSON <code>"{}"</code> */
  public static final String EMPTY_JSON = "{}";

  /** 字符串常量：空 ARRAY <code>"{}"</code> */
  public static final String EMPTY_ARRAY = "{}";

  /** 字符串常量：ARRAY元素分隔 <code>", "</code> */
  public static final String ARRAY_ELEMENT_SEPARATOR = ", ";
}
