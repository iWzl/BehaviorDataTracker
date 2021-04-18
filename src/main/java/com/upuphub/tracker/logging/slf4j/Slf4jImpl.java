
package com.upuphub.tracker.logging.slf4j;

import com.upuphub.tracker.logging.AbstractLogger;
import com.upuphub.tracker.logging.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

/**
 * Slf4jImpl
 *
 * @author Inspiration S.P.A Leo
 */
public class Slf4jImpl extends AbstractLogger {

  /**
   * 日志属性对象
   */
  private Logger log;

  /**
   * Convenience method to return a named logger.
   * @param clazz logical name of the <code>Log</code> instance to be returned
   */
  public Slf4jImpl(String clazz) {
    org.slf4j.Logger logger = LoggerFactory.getLogger(clazz);

    if (logger instanceof LocationAwareLogger) {
      try {
        // check for slf4j >= 1.6 method signature
        logger.getClass().getMethod(
                "log", Marker.class, String.class, int.class, String.class, Object[].class, Throwable.class);
        log = new Slf4jLocationAwareLoggerImpl((LocationAwareLogger) logger);
        return;
      } catch (SecurityException | NoSuchMethodException e) {
        // fail-back to Slf4jLoggerImpl
      }
    }

    // Logger is not LocationAwareLogger or slf4j version < 1.6
    log = new Slf4jLoggerImpl(logger);
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

