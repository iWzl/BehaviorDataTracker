package com.upuphub.tracker.logging.log4j2;

import com.upuphub.tracker.logging.AbstractLogger;
import com.upuphub.tracker.logging.LoggerFactory;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * Logger4J2LoggerImpl
 *
 * @author Inspiration S.P.A Leo
 */
public class Logger4J2LoggerImpl extends AbstractLogger {

  /**
   * 日志属性对象
   */
  private static final Marker MARKER = MarkerManager.getMarker(LoggerFactory.MARKER);

  /**
   * 日志属性对象
   */
  private final org.apache.logging.log4j.Logger log;

  /**
   * Convenience method to return a named logger.
   * @param logger logical name of the <code>Log</code> instance to be returned
   */
  public Logger4J2LoggerImpl(org.apache.logging.log4j.Logger logger) {
    log = logger;
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
   * 是否开启了Info日子的判断结果
   *
   * @return Info日志的判断
   */
  @Override
  public boolean isInfoEnabled() {
    return log.isInfoEnabled();
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   * @param e the exception (throwable) to log
   */
  @Override
  public void error(String s, Throwable e) {
    log.error(MARKER, s, e);
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void error(String s) {
    log.error(MARKER, s);
  }

  /**
   * 打印Info日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void info(String s) {
      log.info(MARKER, s);
  }


  /**
   * 打印的Debug日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void debug(String s) {
    log.debug(MARKER, s);
  }

  /**
   * 打印的trace日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void trace(String s) {
    log.trace(MARKER, s);
  }

  /**
   * 打印的warn日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void warn(String s) {
    log.warn(MARKER, s);
  }

}
