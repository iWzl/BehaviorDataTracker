package com.upuphub.tracker.exception;

/**
 * 方法反射调用实现运行时异常异常
 *
 * @author Inspiration S.P.A Leo
 **/
public class CanNotInvokeTargetMethodException extends AbstractTrackerRuntimeException {
    /**
     * 方法反射调用实现运行时异常的构造
     *
     * @param message 异常抛出的消息内容
     */
    public CanNotInvokeTargetMethodException(String message) {
        super(message);
    }
}
