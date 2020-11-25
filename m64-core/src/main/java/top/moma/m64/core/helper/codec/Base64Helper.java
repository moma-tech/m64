package top.moma.m64.core.helper.codec;

import top.moma.m64.core.helper.CharsetHelper;
import top.moma.m64.core.helper.io.FileHelper;
import top.moma.m64.core.helper.io.IoHelper;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Base64Helper
 *
 * <p>Ref. cn.hutool.core.codec
 *
 * @author Looly
 * @version 1.0 Created by ivan at 11/25/20.
 */
public class Base64Helper {
  // -------------------------------------------------------------------- encode
  /**
   * base64编码
   *
   * @param source 被编码的base64字符串
   * @return 被加密后的字符串
   */
  public static String encode(CharSequence source) {
    return Base64Encoder.encode(source);
  }

  /**
   * base64编码
   *
   * @param source 被编码的base64字符串
   * @param charset 字符集
   * @return 被加密后的字符串
   */
  public static String encode(CharSequence source, String charset) {
    return encode(source, CharsetHelper.charset(charset));
  }

  /**
   * base64编码
   *
   * @param source 被编码的base64字符串
   * @param charset 字符集
   * @return 被加密后的字符串
   */
  public static String encode(CharSequence source, Charset charset) {
    return Base64Encoder.encode(source, charset);
  }

  /**
   * 编码为Base64，非URL安全的
   *
   * @param arr 被编码的数组
   * @param lineSep 在76个char之后是CRLF还是EOF
   * @return 编码后的bytes
   */
  public static byte[] encode(byte[] arr, boolean lineSep) {
    return Base64Encoder.encode(arr, lineSep);
  }
  /**
   * base64编码，URL安全
   *
   * @param source 被编码的base64字符串
   * @return 被加密后的字符串
   */
  public static String encodeUrlSafe(CharSequence source) {
    return Base64Encoder.encodeUrlSafe(source);
  }
  /**
   * base64编码,URL安全
   *
   * @param source 被编码的base64字符串
   * @param charset 字符集
   * @return 被加密后的字符串
   */
  public static String encodeUrlSafe(CharSequence source, String charset) {
    return encodeUrlSafe(source, CharsetHelper.charset(charset));
  }

  /**
   * base64编码，URL安全的
   *
   * @param source 被编码的base64字符串
   * @param charset 字符集
   * @return 被加密后的字符串
   */
  public static String encodeUrlSafe(CharSequence source, Charset charset) {
    return Base64Encoder.encodeUrlSafe(source, charset);
  }

  /**
   * 编码为Base64，URL安全的
   *
   * @param arr 被编码的数组
   * @param lineSep 在76个char之后是CRLF还是EOF
   * @return 编码后的bytes
   */
  public static byte[] encodeUrlSafe(byte[] arr, boolean lineSep) {
    return Base64Encoder.encodeUrlSafe(arr, lineSep);
  }

  /**
   * base64编码
   *
   * @param source 被编码的base64字符串
   * @return 被加密后的字符串
   */
  public static String encode(byte[] source) {
    return Base64Encoder.encode(source);
  }

  /**
   * base64编码,URL安全的
   *
   * @param source 被编码的base64字符串
   * @return 被加密后的字符串
   */
  public static String encodeUrlSafe(byte[] source) {
    return Base64Encoder.encodeUrlSafe(source);
  }

  /**
   * base64编码
   *
   * @param in 被编码base64的流（一般为图片流或者文件流）
   * @return 被加密后的字符串
   */
  public static String encode(InputStream in) {
    return Base64Encoder.encode(IoHelper.readBytes(in));
  }

  /**
   * base64编码,URL安全的
   *
   * @param in 被编码base64的流（一般为图片流或者文件流）
   * @return 被加密后的字符串
   */
  public static String encodeUrlSafe(InputStream in) {
    return Base64Encoder.encodeUrlSafe(IoHelper.readBytes(in));
  }

  /**
   * base64编码
   *
   * @param file 被编码base64的文件
   * @return 被加密后的字符串
   */
  public static String encode(File file) {
    return Base64Encoder.encode(FileHelper.readBytes(file));
  }

  /**
   * base64编码,URL安全的
   *
   * @param file 被编码base64的文件
   * @return 被加密后的字符串
   */
  public static String encodeUrlSafe(File file) {
    return Base64Encoder.encodeUrlSafe(FileHelper.readBytes(file));
  }

  // -------------------------------------------------------------------- decode

  /**
   * base64解码
   *
   * @param source 被解码的base64字符串
   * @return 被加密后的字符串
   */
  public static String decodeStr(CharSequence source) {
    return Base64Decoder.decodeStr(source);
  }

  /**
   * base64解码
   *
   * @param source 被解码的base64字符串
   * @param charset 字符集
   * @return 被加密后的字符串
   */
  public static String decodeStr(CharSequence source, String charset) {
    return decodeStr(source, CharsetHelper.charset(charset));
  }

  /**
   * base64解码
   *
   * @param source 被解码的base64字符串
   * @return 被加密后的字符串
   * @since 4.3.2
   */
  public static String decodeStrGbk(CharSequence source) {
    return decodeStr(source, CharsetHelper.CHARSET_GBK);
  }
  /**
   * base64解码
   *
   * @param source 被解码的base64字符串
   * @param charset 字符集
   * @return 被加密后的字符串
   */
  public static String decodeStr(CharSequence source, Charset charset) {
    return Base64Decoder.decodeStr(source, charset);
  }

  /**
   * base64解码
   *
   * @param base64 被解码的base64字符串
   * @return 被加密后的字符串
   */
  public static byte[] decode(CharSequence base64) {
    return Base64Decoder.decode(base64);
  }

  /**
   * 解码Base64
   *
   * @param in 输入
   * @return 解码后的bytes
   */
  public static byte[] decode(byte[] in) {
    return Base64Decoder.decode(in);
  }

  /**
   * base64解码
   *
   * @param base64 被解码的base64字符串
   * @param destFile 目标文件
   * @return 目标文件
   * @since 4.0.9
   */
  public static File decodeToFile(CharSequence base64, File destFile) {
    return FileHelper.writeBytes(Base64Decoder.decode(base64), destFile);
  }

  /**
   * base64解码
   *
   * @param base64 被解码的base64字符串
   * @param out 写出到的流
   * @param isCloseOut 是否关闭输出流
   * @since 4.0.9
   */
  public static void decodeToStream(CharSequence base64, OutputStream out, boolean isCloseOut) {
    IoHelper.write(out, isCloseOut, Base64Decoder.decode(base64));
  }
}
