package com.upuphub.tracker.annotation;

import java.lang.annotation.*;

/**
 * 打点异常参数到执行方法输入参数参数标识
 *
 * @author Inspiration S.P.A Leo
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Exception {

    /**
     * 打点执行参数异常执行返回类 不强制
     *
     * @return 抛出异常后的异常消息内容
     */
    Class<?> clazz() default java.lang.Exception.class;
}
