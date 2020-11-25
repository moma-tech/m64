package top.moma.m64.core.helper.io;

import top.moma.m64.core.exceptions.M64Exception;
import top.moma.m64.core.helper.io.file.FileOperator;

import java.io.File;

/**
 * FileHelper
 *
 * <p>File Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/25/20.
 */
public class FileHelper {
  /**
   * 读取文件所有数据<br>
   * 文件的长度不能超过Integer.MAX_VALUE
   *
   * @param file 文件
   * @return 字节码
   * @throws M64Exception IO异常
   */
  public static byte[] readBytes(File file) throws M64Exception {
    return FileOperator.build(file).readBytes();
  }

  /**
   * 读取文件所有数据<br>
   * 文件的长度不能超过Integer.MAX_VALUE
   *
   * @param filePath 文件路径
   * @return 字节码
   * @throws M64Exception IO异常
   * @since 3.2.0
   */
  public static byte[] readBytes(String filePath) throws M64Exception {
    return readBytes(file(filePath));
  }
  /**
   * 写数据到文件中
   *
   * @param dest 目标文件
   * @param data 数据
   * @return 目标文件
   * @throws M64Exception IO异常
   */
  public static File writeBytes(byte[] data, File dest) throws M64Exception {
    return writeBytes(data, dest, 0, data.length, false);
  }
  /**
   * 写入数据到文件
   *
   * @param data 数据
   * @param dest 目标文件
   * @param off 数据开始位置
   * @param len 数据长度
   * @param isAppend 是否追加模式
   * @return 目标文件
   * @throws M64Exception IO异常
   */
  public static File writeBytes(byte[] data, File dest, int off, int len, boolean isAppend)
      throws M64Exception {
    return FileOperator.build(dest).write(data, off, len, isAppend);
  }

  /**
   * 创建File对象，自动识别相对或绝对路径，相对路径将自动从ClassPath下寻找
   *
   * @param path 文件路径
   * @return File
   */
  public static File file(String path) {
    if (null == path) {
      return null;
    }
    return new File(path);
  }
  /**
   * 创建文件及其父目录，如果这个文件存在，直接返回这个文件<br>
   * 此方法不对File对象类型做判断，如果File不存在，无法判断其类型
   *
   * @param file 文件对象
   * @return 文件，若路径为null，返回null
   * @throws M64Exception IO异常
   */
  public static File touch(File file) throws M64Exception {
    if (null == file) {
      return null;
    }
    if (!file.exists()) {
      mkParentDirs(file);
      try {
        //noinspection ResultOfMethodCallIgnored
        file.createNewFile();
      } catch (Exception e) {
        throw new M64Exception(e);
      }
    }
    return file;
  }

  /**
   * 创建所给文件或目录的父目录
   *
   * @param file 文件或目录
   * @return 父目录
   */
  public static File mkParentDirs(File file) {
    final File parentFile = file.getParentFile();
    if (null != parentFile && !parentFile.exists()) {
      //noinspection ResultOfMethodCallIgnored
      parentFile.mkdirs();
    }
    return parentFile;
  }
}
