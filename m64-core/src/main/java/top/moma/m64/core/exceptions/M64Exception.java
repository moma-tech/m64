package top.moma.m64.core.exceptions;

import top.moma.m64.core.helper.StringHelper;

/**
 * M64Exception
 *
 * <p>Framework Exception
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/25/20.
 */
public class M64Exception extends RuntimeException {
  private static final long serialVersionUID = -1014991939147493778L;

  public M64Exception(Throwable e) {
    super(e.getClass().getSimpleName() + ":" + e.getMessage());
  }

  public M64Exception(String message) {
    super(message);
  }

  public M64Exception(String messageTemplate, Object... params) {
    super(StringHelper.format(messageTemplate, params));
  }

  public M64Exception(String message, Throwable throwable) {
    super(message, throwable);
  }

  public M64Exception(Throwable throwable, String messageTemplate, Object... params) {
    super(StringHelper.format(messageTemplate, params), throwable);
  }
}
