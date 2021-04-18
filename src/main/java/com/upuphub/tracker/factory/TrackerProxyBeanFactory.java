package com.upuphub.tracker.factory;

import com.upuphub.tracker.intercept.BeanPostProcessor;
import com.upuphub.tracker.intercept.CglibAopProxyBeanPostProcessor;
import com.upuphub.tracker.intercept.JdkAopProxyBeanPostProcessor;
import com.upuphub.tracker.utils.ReflectionUtils;

/**
 * TrackerProxyBean的创建Factory
 *
 * @author Inspiration S.P.A Leo
 **/
public abstract class TrackerProxyBeanFactory {
    /**
     * 获取Bean的代理处理器对象
     *
     * @param beanClass 目标类
     * @return beanClass 实现了接口就返回jdk动态代理bean后置处理器,否则返回 cglib动态代理bean后置处理器
     */
    public static BeanPostProcessor get(Class<?> beanClass) {
        if (beanClass.isInterface() || beanClass.getInterfaces().length > 0) {
            return new JdkAopProxyBeanPostProcessor();
        } else {
            return new CglibAopProxyBeanPostProcessor();
        }
    }

    /**
     * 通过类,返回指定类的Tracker代理对象
     *
     * @param beanInstanceClazz 需要完成代理的Tracker代理对象类
     * @param <T> beanInstanceClazz的对象泛型
     * @return beanInstanceClazz的代理对象实例
     */
    @SuppressWarnings("unchecked")
    public static <T>T buildProxyWrapBeanInstance(Class<T> beanInstanceClazz){
        BeanPostProcessor beanPostProcessor = get(beanInstanceClazz);
        return (T) beanPostProcessor.postProcessAfterInitialization(ReflectionUtils.newInstance(beanInstanceClazz));
    }

    /**
     * 通过类的实例，返回其的Tracker代理对象
     *
     * @param beanInstance 需要完成代理的Tracker代理对象
     * @param <T> beanInstance的对象泛型
     * @return 完成代理包装的代理对象实例
     */
    @SuppressWarnings("unchecked")
    public static <T>T buildProxyWrapBean(T beanInstance){
        BeanPostProcessor beanPostProcessor = get(beanInstance.getClass());
        return (T) beanPostProcessor.postProcessAfterInitialization(beanInstance);
    }
}
