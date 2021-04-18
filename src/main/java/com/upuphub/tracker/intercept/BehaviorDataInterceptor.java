package com.upuphub.tracker.intercept;


import com.upuphub.tracker.runner.TrackerHandlerRunner;

/**
 *
 * 用户行为的工具自定义代理对象的拦截增强器实现
 *
 * @author Inspiration S.P.A Leo
 **/
public class BehaviorDataInterceptor extends AbstractInterceptor {

    /**
     * 用户行为的工具自定义代理对象的拦截增强方法
     *
     * @param methodInvocation 原方法的调用属性
     * @return 原方法的属性调用
     * @throws Throwable 原方法执行的抛出异常
     */
    @Override
    public Object intercept(Invocation methodInvocation) throws Throwable {
       return TrackerHandlerRunner.runTracker(methodInvocation);
    }
}
