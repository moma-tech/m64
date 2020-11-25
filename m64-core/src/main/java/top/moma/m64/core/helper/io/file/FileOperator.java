package top.moma.m64.core.helper.io.file;

import top.moma.m64.core.exceptions.M64Exception;
import top.moma.m64.core.helper.CharsetHelper;
import top.moma.m64.core.helper.StringHelper;
import top.moma.m64.core.helper.io.FileHelper;
import top.moma.m64.core.helper.io.IoHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * FileOperator
 *
 * <p>File Reader/Writer
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/25/20.
 */
public class FileOperator implements java.io.Serializable {
  private static final long serialVersionUID = -2501353655694075987L;

  private File file;
  private Charset charset;

  public FileOperator(File file) {
    this(file, CharsetHelper.CHARSET_UTF_8);
  }

  public FileOperator(File file, Charset charset) {
    this.file = file;
    this.charset = charset;
  }

  /**
   * 读取文件所有数据<br>
   * 文件的长度不能超过 {@link Integer#MAX_VALUE}
   *
   * @return 字节码
   * @throws M64Exception IO异常
   */
  public byte[] readBytes() throws M64Exception {
    long len = file.length();
    if (len >= Integer.MAX_VALUE) {
      throw new M64Exception("File is larger then max array size");
    }

    byte[] bytes = new byte[(int) len];
    FileInputStream in = null;
    int readLength;
    try {
      in = new FileInputStream(file);
      readLength = in.read(bytes);
      if (readLength < len) {
        throw new IOException(
            StringHelper.format("File length is [{}] but read [{}]!", len, readLength));
      }
    } catch (Exception e) {
      throw new M64Exception(e);
    } finally {
      IoHelper.close(in);
    }

    return bytes;
  }
  /**
   * 写入数据到文件
   *
   * @param data 数据
   * @param off 数据开始位置
   * @param len 数据长度
   * @param isAppend 是否追加模式
   * @return 目标文件
   * @throws M64Exception IO异常
   */
  public File write(byte[] data, int off, int len, boolean isAppend) throws M64Exception {
    FileOutputStream out = null;
    try {
      out = new FileOutputStream(FileHelper.touch(file), isAppend);
      out.write(data, off, len);
      out.flush();
    } catch (IOException e) {
      throw new M64Exception(e);
    } finally {
      IoHelper.close(out);
    }
    return file;
  }
  /**
   * Static Builder
   *
   * @author Created by ivan on 3:13 PM 11/25/20.
   * @param file :
   * @return top.moma.m64.core.helper.io.file.FileOperator
   */
  public static FileOperator build(File file) {
    return new FileOperator(file);
  }
}
