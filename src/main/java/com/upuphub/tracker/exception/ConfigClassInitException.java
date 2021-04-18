package com.upuphub.tracker.exception;

/**
 * 配置初始化异常
 *
 * @author Inspiration S.P.A Leo
 **/
public class ConfigClassInitException extends AbstractTrackerRuntimeException {
    /**
     * 配置类初始化异常构造器
     *
     * @param message 异常抛出的消息内容
     */
    public ConfigClassInitException(String message) {
        super(message);
    }

    /**
     * 配置类初始化异常构造器
     *
     * @param message 异常抛出的消息内容
     * @param cause 上层的异常层级参数
     */
    public ConfigClassInitException(String message, Throwable cause) {
        super(message, cause);
    }
}
