package com.upuphub.tracker.logging.stdout;


import com.upuphub.tracker.logging.AbstractLogger;

/**
 *
 * StdOutImpl
 *
 * @author Clinton Begin
 * @author Inspiration S.P.A Leo
 */
public class StdOutImpl extends AbstractLogger {
  /**
   * Convenience method to return a named logger.
   * @param clazz logical name of the <code>Log</code> instance to be returned
   */
  public StdOutImpl(String clazz) {
    // Do Nothing
  }
  /**
   * 是否开启了Debug日志
   *
   * @return 开启了Debug日志的判断结果
   */
  @Override
  public boolean isDebugEnabled() {
    return true;
  }

  /**
   * 是否开启了Trace日志的判断
   *
   * @return Trace日志的判断结果
   */
  @Override
  public boolean isTraceEnabled() {
    return true;
  }

  /**
   * 是否开启了Info日子的判断结果
   *
   * @return Info日志的判断
   */
  @Override
  public boolean isInfoEnabled() {
    return true;
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   * @param e the exception (throwable) to log
   */
  @Override
  public void error(String s, Throwable e) {
    System.err.println(s);
    e.printStackTrace(System.err);
  }

  /**
   * 打印Error日志
   *
   * @param s 打印的字符串
   */
  @Override
  public void error(String s) {
    System.err.println(s);
  }

  /**
   * 打印Info日志
   *
   * @param s 打印的字符串
   */
  @SuppressWarnings("checkstyle:Regexp")
  @Override
  public void debug(String s) {
    System.out.println(s);
  }

  /**
   * 打印的Debug日志
   *
   * @param s 打印的字符串
   */
  @SuppressWarnings("checkstyle:Regexp")
  @Override
  public void info(String s) {
    System.out.println(s);
  }

  /**
   * 打印的trace日志
   *
   * @param s 打印的字符串
   */
  @SuppressWarnings("checkstyle:Regexp")
  @Override
  public void trace(String s) {
    System.out.println(s);
  }


  /**
   * 打印的warn日志
   *
   * @param s 打印的字符串
   */
  @SuppressWarnings("checkstyle:Regexp")
  @Override
  public void warn(String s) {
    System.out.println(s);
  }
}
