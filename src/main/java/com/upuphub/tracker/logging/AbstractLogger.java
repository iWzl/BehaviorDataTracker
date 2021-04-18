package com.upuphub.tracker.logging;


import com.upuphub.tracker.utils.BracePlaceholder;

/**
 * BaseLogger
 *
 * @author Inspiration S.P.A Leo
 **/
public abstract class AbstractLogger implements Logger{

    /**
     * 打印Error日志
     *
     * @param content the message accompanying the exception
     * @param values  value
     * @param e       the exception (throwable) to log
     */
    @Override
    public void error(String content, Object[] values, Throwable e) {
        this.error(BracePlaceholder.resolve(content, values), e);
    }

    /**
     * 打印error日志
     *
     * @param content the format string
     * @param values  a list of 3 or more arguments
     */
    @Override
    public void error(String content, Object... values) {
        this.error(BracePlaceholder.resolve(content, values));
    }

    /**
     * 打印Info日志
     *
     * @param content the format string
     * @param values  a list of 3 or more arguments
     */
    @Override
    public void info(String content, Object... values) {
        this.info(BracePlaceholder.resolve(content, values));
    }

    /**
     * 打印debug日志
     *
     * @param content the format string
     * @param values  a list of 3 or more arguments
     */
    @Override
    public void debug(String content, Object... values) {
        this.debug(BracePlaceholder.resolve(content, values));
    }

    /**
     * 打印trace日志
     *
     * @param content the format string
     * @param values  the argument
     */
    @Override
    public void trace(String content, Object... values) {
        this.trace(BracePlaceholder.resolve(content, values));
    }

    /**
     * 打印warn日志
     *
     * @param content the format string
     * @param values  a list of 3 or more arguments
     */
    @Override
    public void warn(String content, Object... values) {
        this.warn(BracePlaceholder.resolve(content, values));
    }
}
