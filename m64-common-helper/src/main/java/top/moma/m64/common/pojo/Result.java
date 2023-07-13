package top.moma.m64.common.pojo;

import java.io.Serial;
import top.moma.m64.core.exceptions.M64Exception;

/**
 * Result
 *
 * <p>General Result Data Holder
 *
 * @version 1.0
 * @author Created by ivan at 15:30.
 */
public class Result<T> implements java.io.Serializable {

  @Serial private static final long serialVersionUID = 8814723944893850290L;

  /* Default Code And Message */
  public static final String SUCCESS_CODE = "000000";
  public static final String DEFAULT_MESSAGE_SUCCESS = "SUCCESSFUL";
  public static final String FAIL_CODE = "-1";
  public static final String DEFAULT_FAILED_MESSAGE = "SERVICE FAILED";

  /** Result Status Code,Numeric Style */
  private String code;
  /** Result Message, Short version */
  private String message;
  /** Result Object with Serializable */
  private T data;
  /** Result Error Info if exceptions occurs */
  private String exceptions;

  public Result() {
    this(null, null, null, null);
  }

  public Result(String code, String message, T data, String exceptions) {
    this.code = code;
    this.message = message;
    this.data = data;
    this.exceptions = exceptions;
  }

  /**
   * success
   *
   * <p>build a successful result with given code,message and data
   *
   * @param code code
   * @param message message
   * @param data data
   * @return top.moma.m64.common.pojo.Result
   * @author Created by ivan
   * @since 2023/7/13 15:36
   */
  public Result<T> success(String code, String message, T data) {
    return this.setCode(code).setMessage(message).setData(data);
  }

  /**
   * success
   *
   * <p>build a successful result with given code and message
   *
   * @param code code
   * @param message message
   * @return top.moma.m64.common.pojo.Result
   * @author Created by ivan
   * @since 2023/7/13 15:36
   */
  public Result<T> success(String code, String message) {
    return success(code, message, null);
  }

  /**
   * success
   *
   * <p>build a successful result with given data and default code/message
   *
   * @param data data
   * @return top.moma.m64.common.pojo.Result
   * @author Created by ivan
   * @since 2023/7/13 15:37
   */
  public Result<T> success(T data) {
    return success(Result.SUCCESS_CODE, Result.DEFAULT_MESSAGE_SUCCESS, data);
  }

  /**
   * success
   *
   * <p>build a default successful result
   *
   * @return top.moma.m64.common.pojo.Result
   * @author Created by ivan
   * @since 2023/7/13 15:38
   */
  public Result<T> success() {
    return success(Result.SUCCESS_CODE, Result.DEFAULT_MESSAGE_SUCCESS);
  }

  /**
   * failed
   *
   * <p>build a failed result with given code, message ,data and exception infos.
   *
   * @param code code
   * @param message message
   * @param data data
   * @param exceptions exceptions
   * @return top.moma.m64.common.pojo.Result
   * @author Created by ivan
   * @since 2023/7/13 15:38
   */
  public Result<T> failed(String code, String message, T data, String exceptions) {
    return this.setCode(code).setMessage(message).setData(data).setExceptions(exceptions);
  }

  /**
   * failed
   *
   * <p>build a default failed result.
   *
   * @return top.moma.m64.common.pojo.Result
   * @author Created by ivan
   * @since 2023/7/13 15:39
   */
  public Result<T> failed() {
    return failed(Result.FAIL_CODE, Result.DEFAULT_FAILED_MESSAGE, null, null);
  }

  /**
   * failed
   *
   * <p>build a failed result with given M64 Runtime Exception and default code/message.
   *
   * @param m64Exception m64Exception
   * @return top.moma.m64.common.pojo.Result
   * @author Created by ivan
   * @since 2023/7/13 15:39
   */
  public Result<T> failed(M64Exception m64Exception) {
    return failed(Result.FAIL_CODE, Result.DEFAULT_FAILED_MESSAGE, null, m64Exception.getMessage());
  }

  /**
   * failed
   *
   * <p>build a failed result with given exception infos and default code/message.
   *
   * @param exceptions exceptions
   * @return top.moma.m64.common.pojo.Result
   * @author Created by ivan
   * @since 2023/7/13 15:39
   */
  public Result<T> failed(String exceptions) {
    return failed(Result.FAIL_CODE, Result.DEFAULT_FAILED_MESSAGE, null, exceptions);
  }

  /**
   * failed
   *
   * <p>build a failed result with given message and exception infos
   *
   * @param message message
   * @param exceptions exceptions
   * @return top.moma.m64.common.pojo.Result
   * @author Created by ivan
   * @since 2023/7/13 15:39
   */
  public Result<T> failed(String message, String exceptions) {
    return failed(Result.FAIL_CODE, message, null, exceptions);
  }

  /**
   * failed
   *
   * <p>build a failed result with given code, message and exception infos
   *
   * @param code code
   * @param message message
   * @param exceptions exceptions
   * @return top.moma.m64.common.pojo.Result
   * @author Created by ivan
   * @since 2023/7/13 15:39
   */
  public Result<T> failed(String code, String message, String exceptions) {
    return failed(code, message, null, exceptions);
  }

  public String getCode() {
    return code;
  }

  public Result<T> setCode(String code) {
    this.code = code;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public Result<T> setMessage(String message) {
    this.message = message;
    return this;
  }

  public T getData() {
    return data;
  }

  public Result<T> setData(T data) {
    this.data = data;
    return this;
  }

  public String getExceptions() {
    return exceptions;
  }

  public Result<T> setExceptions(String exceptions) {
    this.exceptions = exceptions;
    return this;
  }
}
