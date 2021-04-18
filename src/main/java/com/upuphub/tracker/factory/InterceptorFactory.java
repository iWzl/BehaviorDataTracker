package com.upuphub.tracker.factory;
import com.upuphub.tracker.intercept.AbstractInterceptor;
import com.upuphub.tracker.intercept.BehaviorDataInterceptor;

/**
 *
 * 代理增强拦截器类的创建工厂
 *
 * @author Inspiration S.P.A Leo
 **/
public abstract class InterceptorFactory {

    /**
     * 私有化构造器
     */
    private InterceptorFactory() {
    }

    /**
     * 获取代理增强拦截器的实现
     *
     * @return 增强拦截器的实现
     */
    public static AbstractInterceptor getInterceptor() {
        return new BehaviorDataInterceptor();
    }
}
