package com.upuphub.tracker.logging.jdk14;

import java.util.logging.Level;

import com.upuphub.tracker.logging.AbstractLogger;


/**
 * Jdk14LoggingImpl
 *
 * @author Inspiration S.P.A Leo
 */
public class Jdk14LoggingImpl extends AbstractLogger {

  /**
   * 日志属性对象
   */
  private final java.util.logging.Logger log;

  /**
   * Convenience method to return a named logger.
   * @param clazz logical name of the <code>Log</code> instance to be returned
   */
  public Jdk14LoggingImpl(String clazz) {
    log = java.util.logging.Logger.getLogger(clazz);
  }

  /**
   * 是否开启了Debug日志
   *
   * @return 开启了Debug日志的判断结果
   */
  @Override
  public boolean isDebugEnabled() {
    return log.isLoggable(Level.FINE);
  }

  /**
   * 是否开启了Trace日志的判断
   *
   * @return Trace日志的判断结果
   */
  @Override
  public boolean isTraceEnabled() {
    return log.isLoggable(Level.FINER);
  }

  /**
   * 是否开启了Info日子的判断结果
   *
   * @return Info日志的判断
   */
  @Override
  public boolean isInfoEnabled() {
    return  log.isLoggable(Level.INFO);
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   * @param e the exception (throwable) to log
   */
  @Override
  public void error(String s, Throwable e) {
    log.log(Level.SEVERE, s, e);
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void error(String s) {
    log.log(Level.SEVERE, s);
  }

  /**
   * 打印Info日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void info(String s) {
    log.log(Level.INFO, s);
  }

  /**
   * 打印的Debug日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void debug(String s) {
    log.log(Level.FINE, s);
  }

  /**
   * 打印的trace日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void trace(String s) {
    log.log(Level.FINER, s);
  }

  /**
   * 打印的warn日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void warn(String s) {
    log.log(Level.WARNING, s);
  }

}
