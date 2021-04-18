package com.upuphub.tracker.intercept;

import java.lang.reflect.Method;

/**
 * 对于代理对象的装饰器基本属性定义
 *
 * @author Inspiration S.P.A Leo
 **/
public interface Invocation {
    /**
     * 获取代理触发的原始对象
     *
     * @return 代理触发的原始对象
     */
    Object getTargetObject();

    /**
     * 获取当前被触发方法
     *
     * @return 当前被触发方法
     */
    Method getTargetMethod();

    /**
     * 获取方法请求入参参数
     *
     * @return 请求入参参数
     */
    Object[] getArgs();

    /**
     * 执行原方法的调用
     *
     * @return 原方法的产生返回
     * @throws Throwable 原执行方法中抛出的异常
     */
    Object proceed() throws Throwable;
}
