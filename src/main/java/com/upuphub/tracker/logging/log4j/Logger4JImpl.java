package com.upuphub.tracker.logging.log4j;

import com.upuphub.tracker.logging.AbstractLogger;
import org.apache.log4j.Level;

/**
 * Logger4JImpl
 *
 * @author Inspiration S.P.A Leo
 */
public class Logger4JImpl extends AbstractLogger {

  /**
   * 日志属性对象
   */
  private static final String FQCN = Logger4JImpl.class.getName();

  /**
   * 日志属性对象
   */
  private final org.apache.log4j.Logger log;

  /**
   * Convenience method to return a named logger.
   * @param clazz logical name of the <code>Log</code> instance to be returned
   */
  public Logger4JImpl(String clazz) {
    log = org.apache.log4j.Logger.getLogger(clazz);
  }

  /**
   * 是否开启了Info日子的判断结果
   *
   * @return Info日志的判断
   */
  @Override
  public boolean isInfoEnabled() {
    return log.isInfoEnabled();
  }

  /**
   * 打印Info日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void info(String s) {
    log.log(FQCN, Level.INFO, s, null);
  }

  /**
   * 是否开启了Debug日志
   *
   * @return 开启了Debug日志的判断结果
   */
  @Override
  public boolean isDebugEnabled() {
    return log.isDebugEnabled();
  }

  /**
   * 是否开启了Trace日志的判断
   *
   * @return Trace日志的判断结果
   */
  @Override
  public boolean isTraceEnabled() {
    return log.isTraceEnabled();
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   * @param e the exception (throwable) to log
   */
  @Override
  public void error(String s, Throwable e) {
    log.log(FQCN, Level.ERROR, s, e);
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void error(String s) {
    log.log(FQCN, Level.ERROR, s, null);
  }

  /**
   * 打印的Debug日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void debug(String s) {
    log.log(FQCN, Level.DEBUG, s, null);
  }

  /**
   * 打印的trace日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void trace(String s) {
    log.log(FQCN, Level.TRACE, s, null);
  }

  /**
   * 打印的warn日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void warn(String s) {
    log.log(FQCN, Level.WARN, s, null);
  }

}
