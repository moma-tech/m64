package top.moma.m64.core.helper.regular;

import top.moma.m64.core.helper.StringHelper;

import java.util.regex.Pattern;

/**
 * RegularHelper
 *
 * <p>Regular and Patterns
 *
 * <p>see hutool-core
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/23/20.
 */
public class RegularHelper {
  /**
   * 给定内容是否匹配正则
   *
   * @param regex 正则
   * @param content 内容
   * @return 正则为null或者""则不检查，返回true，内容为null返回false
   */
  public static boolean isMatch(String regex, CharSequence content) {
    if (content == null) {
      // 提供null的字符串为不匹配
      return false;
    }

    if (StringHelper.isEmpty(regex)) {
      // 正则不存在则为全匹配
      return true;
    }
    // Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
    final Pattern pattern = RegularPatterns.get(regex, Pattern.DOTALL);
    return isMatch(pattern, content);
  }

  /**
   * 给定内容是否匹配正则
   *
   * @param pattern 模式
   * @param content 内容
   * @return 正则为null或者""则不检查，返回true，内容为null返回false
   */
  public static boolean isMatch(Pattern pattern, CharSequence content) {
    if (content == null || pattern == null) {
      // 提供null的字符串为不匹配
      return false;
    }
    return pattern.matcher(content).matches();
  }
}
