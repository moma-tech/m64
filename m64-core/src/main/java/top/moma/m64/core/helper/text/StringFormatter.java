package top.moma.m64.core.helper.text;

import top.moma.m64.core.constants.CharConstants;
import top.moma.m64.core.constants.StringConstants;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.StringHelper;

/**
 * StringFormatter
 *
 * <p>Format String
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/25/20.
 */
public class StringFormatter {

  private StringFormatter() {}

  /**
   * 格式化字符串<br>
   * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
   * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
   * 例：<br>
   * 通常使用：format("this is {} for {}", "a", "b") =》 this is a for b<br>
   * 转义{}： format("this is \\{} for {}", "a", "b") =》 this is \{} for a<br>
   * 转义\： format("this is \\\\{} for {}", "a", "b") =》 this is \a for b<br>
   *
   * @param strPattern 字符串模板
   * @param argArray 参数列表
   * @return 结果
   */
  public static String format(final String strPattern, final Object... argArray) {
    if (StringHelper.isBlank(strPattern) || ObjectHelper.isEmpty(argArray)) {
      return strPattern;
    }
    final int strPatternLength = strPattern.length();

    // 初始化定义好的长度以获得更好的性能
    StringBuilder sbuf = new StringBuilder(strPatternLength + 50);

    int handledPosition = 0; // 记录已经处理到的位置
    int delimIndex; // 占位符所在位置
    for (int argIndex = 0; argIndex < argArray.length; argIndex++) {
      delimIndex = strPattern.indexOf(StringConstants.EMPTY_JSON, handledPosition);
      if (delimIndex == -1) { // 剩余部分无占位符
        if (handledPosition == 0) { // 不带占位符的模板直接返回
          return strPattern;
        }
        // 字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
        sbuf.append(strPattern, handledPosition, strPatternLength);
        return sbuf.toString();
      }

      // 转义符
      if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == CharConstants.BACKSLASH) { // 转义符
        if (delimIndex > 1
            && strPattern.charAt(delimIndex - 2) == CharConstants.BACKSLASH) { // 双转义符
          // 转义符之前还有一个转义符，占位符依旧有效
          sbuf.append(strPattern, handledPosition, delimIndex - 1);
          sbuf.append(StringHelper.toString(argArray[argIndex], StringConstants.UTF_8));
          handledPosition = delimIndex + 2;
        } else {
          // 占位符被转义
          argIndex--;
          sbuf.append(strPattern, handledPosition, delimIndex - 1);
          sbuf.append(CharConstants.DELIM_START);
          handledPosition = delimIndex + 1;
        }
      } else { // 正常占位符
        sbuf.append(strPattern, handledPosition, delimIndex);
        sbuf.append(StringHelper.toString(argArray[argIndex], StringConstants.UTF_8));
        handledPosition = delimIndex + 2;
      }
    }

    // append the characters following the last {} pair.
    // 加入最后一个占位符后所有的字符
    sbuf.append(strPattern, handledPosition, strPattern.length());

    return sbuf.toString();
  }
}
