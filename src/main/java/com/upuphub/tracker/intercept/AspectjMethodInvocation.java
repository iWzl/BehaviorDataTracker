package com.upuphub.tracker.intercept;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * 针对Aspectj中对于ProceedingJoinPoint的装饰器实现
 *
 * @author Inspiration S.P.A Leo
 **/
public class AspectjMethodInvocation implements Invocation{
    /**
     * ProceedingJoinPoint exposes the proceed(..)
     * method in order to support around advice in @AJ aspects
     */
    private final ProceedingJoinPoint joinPoint;

    /**
     * AspectjMethodInvocation的构造器
     *
     * @param joinPointIn joinPointAOP方法点信息
     */
    public AspectjMethodInvocation(ProceedingJoinPoint joinPointIn) {
        this.joinPoint = joinPointIn;
    }

    /**
     * 获取代理触发的原始对象
     *
     * @return 代理触发的原始对象
     */
    @Override
    public Object getTargetObject() {
        return joinPoint.getTarget();
    }

    /**
     * 获取当前被触发方法
     *
     * @return 当前被触发方法
     */
    @Override
    public Method getTargetMethod() {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

    /**
     * 获取方法请求入参参数
     *
     * @return 请求入参参数
     */
    @Override
    public Object[] getArgs() {
        return joinPoint.getArgs();
    }

    /**
     * 执行原方法的调用
     *
     * @return 原方法的产生返回
     * @throws Throwable 原执行方法中抛出的异常
     */
    @Override
    public Object proceed() throws Throwable {
        return joinPoint.proceed();
    }
}
