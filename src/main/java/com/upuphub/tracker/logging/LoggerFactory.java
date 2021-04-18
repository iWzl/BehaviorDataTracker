package com.upuphub.tracker.logging;
import java.lang.reflect.Constructor;

import com.upuphub.tracker.exception.ConfigClassInitException;
import com.upuphub.tracker.logging.log4j.Logger4JImpl;
import com.upuphub.tracker.logging.jdk14.Jdk14LoggingImpl;
import com.upuphub.tracker.logging.log4j2.Logger4J2Impl;
import com.upuphub.tracker.logging.commons.JakartaCommonsLoggingImpl;
import com.upuphub.tracker.logging.nologging.NoLoggingImpl;
import com.upuphub.tracker.logging.slf4j.Slf4jImpl;
import com.upuphub.tracker.logging.stdout.StdOutImpl;


/**
 * LogFactory 这里的处理方式来自Mybatis-3
 *
 * @author Clinton Begin
 * @author Eduardo Macarron
 * @author Inspiration S.P.A Leo
 **/
@SuppressWarnings("checkstyle:JavadocVariable")
public abstract class LoggerFactory {
    /**
     * Marker to be used by logging implementations that support markers.
     */
    public static final String MARKER = "DATA-TRACKER";

    /**
     * logConstructor构造器属性
     */
    private static Constructor<? extends Logger> logConstructor;

    static {
        tryImplementation(LoggerFactory::useSlf4jLogging);
        tryImplementation(LoggerFactory::useCommonsLogging);
        tryImplementation(LoggerFactory::useLog4J2Logging);
        tryImplementation(LoggerFactory::useLog4JLogging);
        tryImplementation(LoggerFactory::useJdkLogging);
        tryImplementation(LoggerFactory::useStdOutLogging);
        tryImplementation(LoggerFactory::useNoLogging);
    }

    private LoggerFactory() {
        // disable construction
    }

    /**
     * 根据类名获取Logger对象实例
     *
     * @param clazz 类
     * @return Logger对象实例
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    /**
     * 通过LoggerName获取Logger对象
     *
     * @param logger Logger对象实例
     * @return logger对象实例
     */
    public static Logger getLogger(String logger) {
        try {
            return logConstructor.newInstance(logger);
        } catch (Throwable t) {
            throw new ConfigClassInitException("Error creating logger for logger " + logger + ".  Cause: " + t, t);
        }
    }

    /**
     * 使用Custom日志
     *
     * @param clazz 使用Custom日志
     */
    public static synchronized void useCustomLogging(Class<? extends Logger> clazz) {
        setImplementation(clazz);
    }

    /**
     * 使用Slf4j的日志
     */
    public static synchronized void useSlf4jLogging() {
        setImplementation(Slf4jImpl.class);
    }

    /**
     * 使用JakartaCommons的日志
     */
    public static synchronized void useCommonsLogging() {
        setImplementation(JakartaCommonsLoggingImpl.class);
    }

    /**
     * 使用Log4j的日志
     */
    public static synchronized void useLog4JLogging() {
        setImplementation(Logger4JImpl.class);
    }

    /**
     * 使用Log4j2的日志框架
     */
    public static synchronized void useLog4J2Logging() {
        setImplementation(Logger4J2Impl.class);
    }

    /**
     * 使用JDK的日志输出
     */
    public static synchronized void useJdkLogging() {
        setImplementation(Jdk14LoggingImpl.class);
    }

    /**
     * 使用系统的控制台输出
     */
    public static synchronized void useStdOutLogging() {
        setImplementation(StdOutImpl.class);
    }

    /**
     * 不适用日志框架
     */
    public static synchronized void useNoLogging() {
        setImplementation(NoLoggingImpl.class);
    }

    private static void tryImplementation(Runnable runnable) {
        if (logConstructor == null) {
            try {
                runnable.run();
            } catch (Throwable ignore) {
                // ignore
            }
        }
    }

    private static void setImplementation(Class<? extends Logger> implClass) {
        try {
            Constructor<? extends Logger> candidate = implClass.getConstructor(String.class);
            Logger log = candidate.newInstance(LoggerFactory.class.getName());
            if (log.isDebugEnabled()) {
                log.debug("Logging initialized using '" + implClass + "' adapter.");
            }
            logConstructor = candidate;
        } catch (Throwable t) {
            throw new ConfigClassInitException("Error setting Log implementation.  Cause: " + t, t);
        }
    }

}
