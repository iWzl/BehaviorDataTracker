package com.upuphub.tracker.proxy;


import java.lang.reflect.Method;

import com.upuphub.tracker.config.CommonConfig;
import com.upuphub.tracker.intercept.AbstractInterceptor;
import com.upuphub.tracker.intercept.MethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



/**
 *
 * CglibAspectProxy的代理实现
 *
 * @author Inspiration S.P.A Leo
 **/
public class CglibAspectProxy implements MethodInterceptor {
    /**
     * 触发的原始对象
     */
    private final Object target;
    /**
     * 方法动态代理增强的实现方法
     */
    private final AbstractInterceptor abstractInterceptor;

    /**
     * CglibAspectProxy的初始构造器
     *
     * @param target 触发的原始对象
     * @param abstractInterceptor 方法动态代理增强的实现方法
     */
    public CglibAspectProxy(Object target, AbstractInterceptor abstractInterceptor) {
        this.target = target;
        this.abstractInterceptor = abstractInterceptor;
    }

    /**
     * 实际的织入落地逻辑
     *
     * @param target 触发的原始对象
     * @param abstractInterceptor 方法动态代理增强的实现方法
     * @return 完成织入的动态代理类
     */
    public static Object wrap(Object target, AbstractInterceptor abstractInterceptor) {
        Class<?> rootClass = target.getClass();
        Class<?> proxySuperClass = rootClass;
        // cglib 多级代理处理
        if (target.getClass().getName().contains(CommonConfig.CGLIB_CLASS_FLAG)) {
            proxySuperClass = rootClass.getSuperclass();
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(target.getClass().getClassLoader());
        enhancer.setSuperclass(proxySuperClass);
        enhancer.setCallback(new CglibAspectProxy(target, abstractInterceptor));
        return enhancer.create();
    }

    /**
     * All generated proxied methods call this method instead of the original method.
     * The original method may either be invoked by normal reflection using the Method object,
     * or by using the MethodProxy (faster).
     * @param obj "this", the enhanced object
     * @param method intercepted Method
     * @param args argument array; primitive types are wrapped
     * @param proxy used to invoke super (non-intercepted method); may be called
     * as many times as needed
     * @throws Throwable any exception may be thrown; if so, super method will not be invoked
     * @return any value compatible with the signature of the proxied method.
     *  Method returning void will ignore this value.
     * @see MethodProxy
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        MethodInvocation methodInvocation = new MethodInvocation(target, method, args);
        // the return value is still the result of the proxy class execution
        return abstractInterceptor.intercept(methodInvocation);
    }
}
