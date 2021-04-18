package com.upuphub.tracker.intercept;

import com.upuphub.tracker.proxy.JdkAspectProxy;

/**
 * 使用Jdk实现的AopProxyBeanPostProcessor
 *
 * @author Inspiration S.P.A Leo
 **/
public class JdkAopProxyBeanPostProcessor extends AbstractAopProxyBeanPostProcessor {


    /**
     * 织入Bean的基本初始化进程
     *
     * @param target 需要织入的目标对象
     * @return 完成织入的Bean对象实例
     */
    @Override
    public Object wrapBean(Object target, AbstractInterceptor abstractInterceptor) {
        return JdkAspectProxy.wrap(target, abstractInterceptor);
    }
}
