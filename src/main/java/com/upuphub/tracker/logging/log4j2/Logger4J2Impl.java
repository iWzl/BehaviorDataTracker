package com.upuphub.tracker.logging.log4j2;

import com.upuphub.tracker.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.spi.AbstractLogger;

/**
 * Logger4J2Impl
 *
 * @author Inspiration S.P.A Leo
 */
public class Logger4J2Impl extends com.upuphub.tracker.logging.AbstractLogger {

  /**
   * 日志属性对象
   */
  private final Logger log;

  /**
   * Convenience method to return a named logger.
   * @param clazz logical name of the <code>Log</code> instance to be returned
   */
  public Logger4J2Impl(String clazz) {
    org.apache.logging.log4j.Logger logger = LogManager.getLogger(clazz);

    if (logger instanceof AbstractLogger) {
      log = new Logger4J2AbstractLoggerImpl((AbstractLogger) logger);
    } else {
      log = new Logger4J2LoggerImpl(logger);
    }
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
    log.error(s, e);
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void error(String s) {
    log.error(s);
  }

  /**
   * 打印Info日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void info(String s) {
    log.info(s);
  }

  /**
   * 打印的Debug日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void debug(String s) {
    log.debug(s);
  }


  /**
   * 打印的trace日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void trace(String s) {
    log.trace(s);
  }

  /**
   * 打印的warn日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void warn(String s) {
    log.warn(s);
  }

}

