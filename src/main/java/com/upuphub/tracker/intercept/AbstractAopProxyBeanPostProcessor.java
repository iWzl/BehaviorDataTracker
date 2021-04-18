package com.upuphub.tracker.intercept;


import com.upuphub.tracker.factory.InterceptorFactory;

/**
 * 抽象的AbstractAopProxyBeanPostProcessor实现
 *
 * @author Inspiration S.P.A Leo
 **/
public abstract class AbstractAopProxyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 织入Bean的基本初始化进程
     *
     * @param bean 需要织入的目标对象
     * @return 完成织入的Bean对象实例
     */
    @Override
    public Object postProcessAfterInitialization(Object bean) {
        Object wrapperProxyBean = bean;
        AbstractInterceptor abstractInterceptor = InterceptorFactory.getInterceptor();
        wrapperProxyBean = wrapBean(wrapperProxyBean, abstractInterceptor);
        return wrapperProxyBean;
    }

    /**
     * 基本的动态代理目标对象的方法织入实现
     *
     * @param target 目标对象
     * @param abstractInterceptor 拦截器实现
     * @return 完成织入的Bean对象实例
     */
    public abstract Object wrapBean(Object target, AbstractInterceptor abstractInterceptor);
}
