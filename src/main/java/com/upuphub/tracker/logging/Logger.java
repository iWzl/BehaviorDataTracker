package com.upuphub.tracker.logging;

/**
 * Log
 *
 * @author Inspiration S.P.A Leo
 **/
public interface Logger {
    /**
     * Is the logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for the DEBUG level,
     *         false otherwise.
     */
    boolean isDebugEnabled();

    /**
     * Is the logger instance enabled for the TRACE level?
     *
     * @return True if this Logger is enabled for the TRACE level,
     *         false otherwise.
     * @since 1.4
     */
    boolean isTraceEnabled();

    /**
     * Is the logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level,
     *         false otherwise.
     */
    boolean isInfoEnabled();

    /**
     * Log an exception (throwable) at the ERROR level with an
     * accompanying message.
     *
     * @param content the message accompanying the exception
     * @param values value
     * @param e   the exception (throwable) to log
     */
    void error(String content, Object[] values, Throwable e);

    /**
     * Log a message at the ERROR level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the ERROR level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for ERROR. The variants taking
     * arguments exist solely in order to avoid this hidden cost.</p>
     *
     * @param content    the format string
     * @param values a list of 3 or more arguments
     */
    void error(String content, Object... values);

    /**
     * Log a message at the INFO level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the INFO level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for INFO. The variants taking
     * arguments exist solely in order to avoid this hidden cost.</p>
     *
     * @param content    the format string
     * @param values a list of 3 or more arguments
     */
    void info(String content, Object... values);

    /**
     * Log a message at the DEBUG level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the DEBUG level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for DEBUG. The variants taking
     * arguments exist solely in order to avoid this hidden cost.</p>
     *
     * @param content    the format string
     * @param values a list of 3 or more arguments
     */
    void debug(String content, Object... values);

    /**
     * Log a message at the TRACE level according to the specified format
     * and argument.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the TRACE level. </p>
     *
     * @param content the format string
     * @param values    the argument
     * @since 1.4
     */
    void trace(String content, Object... values);

    /**
     * Log a message at the WARN level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the WARN level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for WARN. The variants taking
     * arguments exist solely in order to avoid this hidden cost.</p>
     *
     * @param content    the format string
     * @param values a list of 3 or more arguments
     */
    void warn(String content, Object... values);

    /**
     * Log an exception (throwable) at the ERROR level with an
     * accompanying message.
     *
     * @param content the message accompanying the exception
     * @param e   the exception (throwable) to log
     */
    void error(String content, Throwable e);

    /**
     * Log a message at the ERROR level.
     *
     * @param content the message string to be logged
     */
    void error(String content);

    /**
     * Log a message at the INFO level.
     *
     * @param content the message string to be logged
     */
    void info(String content);

    /**
     * Log a message at the DEBUG level.
     *
     * @param content the message string to be logged
     */
    void debug(String content);

    /**
     * Log a message at the TRACE level.
     *
     * @param content the message string to be logged
     * @since 1.4
     */
    void trace(String content);

    /**
     * Log a message at the WARN level.
     *
     * @param content the message string to be logged
     */
    void warn(String content);
}
