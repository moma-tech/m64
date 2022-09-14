package top.moma.m64.core.helper.io;

import top.moma.m64.core.constants.IoConstants;
import top.moma.m64.core.exceptions.M64Exception;
import top.moma.m64.core.helper.AssertHelper;

import java.io.*;

/**
 * IoHelper
 *
 * <p>IO Helper, Read/Write
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/25/20.
 */
public class IoHelper {

  private IoHelper() {}

  /**
   * Read Bytes from Input Stream, then close
   *
   * @author Created by ivan on 3:49 PM 11/25/20.
   * @param in : input Stream
   * @return byte[]
   */
  public static byte[] readBytes(InputStream in) throws M64Exception {
    return readBytes(in, true);
  }

  /**
   * Read Bytes from Input Stream
   *
   * @author Created by ivan on 3:50 PM 11/25/20.
   * @param in : input Stream
   * @param closeFlag : if close
   * @return byte[]
   */
  public static byte[] readBytes(InputStream in, boolean closeFlag) throws M64Exception {
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    copy(in, out);
    if (closeFlag) {
      close(in);
    }
    return out.toByteArray();
  }

  /**
   * write Byte[] to out stream
   *
   * @author Created by ivan on 3:51 PM 11/25/20.
   * @param out : Out Stream
   * @param closeFlag : if close
   * @param data : byte []
   */
  public static void write(OutputStream out, boolean closeFlag, byte[] data) throws M64Exception {
    try {
      out.write(data);
    } catch (IOException e) {
      throw new M64Exception(e);
    } finally {
      if (closeFlag) {
        close(out);
      }
    }
  }

  /**
   * copy from in to out with DEFAULT BUFFER SIZE
   *
   * @author Created by ivan on 3:52 PM 11/25/20.
   * @param in : INPUT
   * @param out : OUTPUT
   * @return long
   */
  public static long copy(InputStream in, OutputStream out) throws M64Exception {
    return copy(in, out, IoConstants.DEFAULT_BUFFER_SIZE);
  }

  /**
   * copy from in to out
   *
   * @author Created by ivan on 4:01 PM 11/25/20.
   * @param in : INPUT
   * @param out : OUTPUT
   * @param bufferSize : buffer size
   * @return long : total bytes
   */
  public static long copy(InputStream in, OutputStream out, int bufferSize) throws M64Exception {
    AssertHelper.notNull(in, "InputStream is null !");
    AssertHelper.notNull(out, "OutputStream is null !");
    if (bufferSize <= 0) {
      bufferSize = IoConstants.DEFAULT_BUFFER_SIZE;
    }
    byte[] buffer = new byte[bufferSize];
    long size = 0;
    try {
      int i = in.read(buffer);
      while (i != IoConstants.EOF) {
        out.write(buffer, 0, i);
        out.flush();
        size = size + i;
        i = in.read(buffer);
      }
    } catch (IOException e) {
      throw new M64Exception(e);
    }
    return size;
  }

  /**
   * Close Stream
   *
   * @author Created by ivan on 4:02 PM 11/25/20.
   * @param closeable :
   */
  public static void close(Closeable closeable) {
    if (null != closeable) {
      try {
        closeable.close();
      } catch (Exception e) {
        // 静默关闭
      }
    }
  }
}
