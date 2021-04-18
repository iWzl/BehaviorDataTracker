package com.upuphub.tracker.annotation;
import java.lang.annotation.*;

import com.upuphub.tracker.lang.ResultType;


/**
 * 用于标识追踪数据点执行的处理方法
 *
 * @author Inspiration S.P.A Leo
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HandlerMethod {
    /**
     * 用与标识HandlerMethod的别名
     * 与@DataPoint调用方的的HandlerMethod名称一致
     *
     * @return HandlerMethod的别名
     */
    String alias() default "";

    /**
     * 表示该方法的返回类型
     * 参考ResultType的说明{@link ResultType}
     *
     * @return 表示该方法的返回类型
     */
    ResultType type();

    /**
     * 标识该方法是同步还是异步执行
     * 同步调用可能会影响原方法的调用处理时间,异步调用能减少对原方法调用处理的影响
     * 但无法获取到原方法处理的上下文环境
     *
     * @return 方法的执行是同步还是异步执行
     */
    boolean async() default true;
}
