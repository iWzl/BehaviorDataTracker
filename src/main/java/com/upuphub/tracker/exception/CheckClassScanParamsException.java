package com.upuphub.tracker.exception;

/**
 * 类参数扫描异常
 *
 * @author Inspiration S.P.A Leo
 **/
public class CheckClassScanParamsException extends AbstractTrackerRuntimeException {
    /**
     * 类参数扫描异常的构造器
     *
     * @param message 异常抛出的消息内容
     */
    public CheckClassScanParamsException(String message) {
        super(message);
    }
}
