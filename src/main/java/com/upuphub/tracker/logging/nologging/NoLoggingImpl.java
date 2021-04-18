package com.upuphub.tracker.logging.nologging;

import com.upuphub.tracker.logging.AbstractLogger;

/**
 * NoLoggingImpl
 *
 * @author Inspiration S.P.A Leo
 */
public class NoLoggingImpl extends AbstractLogger {
  /**
   * Convenience method to return a named logger.
   * @param clazz logical name of the <code>Log</code> instance to be returned
   */
  public NoLoggingImpl(String clazz) {
    // Do Nothing
  }

  /**
   * 是否开启了Debug日志
   *
   * @return 开启了Debug日志的判断结果
   */
  @Override
  public boolean isDebugEnabled() {
    return false;
  }

  /**
   * 是否开启了Trace日志的判断
   *
   * @return Trace日志的判断结果
   */
  @Override
  public boolean isTraceEnabled() {
    return false;
  }

  /**
   * 是否开启了Info日子的判断结果
   *
   * @return Info日志的判断
   */
  @Override
  public boolean isInfoEnabled() {
    return false;
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   * @param e the exception (throwable) to log
   */
  @Override
  public void error(String s, Throwable e) {
    // Do Nothing
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void error(String s) {
    // Do Nothing
  }

  /**
   * 打印Info日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void info(String s) {
    // Do Nothing
  }

  /**
   * 打印的Debug日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void debug(String s) {
    // Do Nothing
  }


  /**
   * 打印的trace日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void trace(String s) {
    // Do Nothing
  }

  /**
   * 打印的warn日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void warn(String s) {
    // Do Nothing
  }

}
