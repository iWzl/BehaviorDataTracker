package com.upuphub.tracker.intercept;
import java.lang.reflect.Method;

import com.upuphub.tracker.utils.ReflectionUtils;

/**
 * 针对本工具自己的Bean动态代理的装饰器实现
 *
 * @author Inspiration S.P.A Leo
 **/
public class MethodInvocation implements Invocation{

    /**
     * 触发的原对象实例
     */
    private final Object targetObject;
    /**
     * 触发的方法
     */
    private final Method targetMethod;

    /**
     * 触发的请求入参
     */
    private final Object[] args;

    /**
     * MethodInvocation的创建构造器
     *
     * @param targetObjectIn 触发的对象
     * @param targetMethodIn 触发的方法
     * @param argsIn 触发的请求入参
     */
    public MethodInvocation(Object targetObjectIn, Method targetMethodIn, Object[] argsIn) {
        this.targetObject = targetObjectIn;
        this.targetMethod = targetMethodIn;
        this.args = argsIn;
    }

    /**
     * 获取代理触发的原始对象
     *
     * @return 代理触发的原始对象
     */
    @Override
    public Object getTargetObject() {
        return targetObject;
    }

    /**
     * 获取当前被触发方法
     *
     * @return 当前被触发方法
     */
    @Override
    public Method getTargetMethod() {
        return targetMethod;
    }

    /**
     * 获取方法请求入参参数
     *
     * @return 请求入参参数
     */
    @Override
    public Object[] getArgs() {
        return args;
    }

    /**
     * 执行原方法的调用
     *
     * @return 原方法的产生返回
     */
    @Override
    public Object proceed() {
        return ReflectionUtils.executeTargetMethod(targetObject, targetMethod, args);
    }
}
