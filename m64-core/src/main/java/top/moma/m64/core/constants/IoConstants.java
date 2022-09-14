package top.moma.m64.core.constants;

/**
 * IoConstants
 *
 * <p>Io/File Constatns
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/25/20.
 */
public class IoConstants {

  private IoConstants() {}

  /** 默认缓存大小 8192 */
  public static final int DEFAULT_BUFFER_SIZE = 2 << 12;
  /** 默认中等缓存大小 16384 */
  public static final int DEFAULT_MIDDLE_BUFFER_SIZE = 2 << 13;
  /** 默认大缓存大小 32768 */
  public static final int DEFAULT_LARGE_BUFFER_SIZE = 2 << 14;

  /** 数据流末尾 */
  public static final int EOF = -1;
}
