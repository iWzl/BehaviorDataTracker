package com.upuphub.tracker.logging.slf4j;

import com.upuphub.tracker.logging.AbstractLogger;
import com.upuphub.tracker.logging.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.spi.LocationAwareLogger;

/**
 * Slf4jLocationAwareLoggerImpl
 *
 * @author Inspiration S.P.A Leo
 */
class Slf4jLocationAwareLoggerImpl extends AbstractLogger {

  /**
   * 日志属性对象
   */
  private static final Marker MARKER = MarkerFactory.getMarker(LoggerFactory.MARKER);

  /**
   * 日志属性对象
   */
  private static final String FQCN = Slf4jImpl.class.getName();

  /**
   * 日志属性对象
   */
  private final LocationAwareLogger logger;

  /**
   * Convenience method to return a named logger.
   * @param logger logical name of the <code>Log</code> instance to be returned
   */
  Slf4jLocationAwareLoggerImpl(LocationAwareLogger logger) {
    this.logger = logger;
  }

  /**
   * 是否开启了Debug日志
   *
   * @return 开启了Debug日志的判断结果
   */
  @Override
  public boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }


  /**
   * 是否开启了Trace日志的判断
   *
   * @return Trace日志的判断结果
   */
  @Override
  public boolean isTraceEnabled() {
    return logger.isTraceEnabled();
  }

  /**
   * 是否开启了Info日子的判断结果
   *
   * @return Info日志的判断
   */
  @Override
  public boolean isInfoEnabled() {
    return logger.isInfoEnabled();
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   * @param e the exception (throwable) to log
   */
  @Override
  public void error(String s, Throwable e) {
    logger.log(MARKER, FQCN, LocationAwareLogger.ERROR_INT, s, null, e);
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void error(String s) {
    logger.log(MARKER, FQCN, LocationAwareLogger.ERROR_INT, s, null, null);
  }

  /**
   * 打印Info日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void info(String s) {
    logger.info(s);
  }

  /**
   * 打印的Debug日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void debug(String s) {
    logger.log(MARKER, FQCN, LocationAwareLogger.DEBUG_INT, s, null, null);
  }

  /**
   * 打印的trace日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void trace(String s) {
    logger.log(MARKER, FQCN, LocationAwareLogger.TRACE_INT, s, null, null);
  }

  /**
   * 打印的warn日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void warn(String s) {
    logger.log(MARKER, FQCN, LocationAwareLogger.WARN_INT, s, null, null);
  }

}
