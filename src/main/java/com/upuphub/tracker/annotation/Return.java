package com.upuphub.tracker.annotation;

import java.lang.annotation.*;

/**
 * 打点执行结果参数到执行方法输入参数参数标识
 *
 * @author Inspiration S.P.A Leo
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Return {
    /**
     * 打点数据点返回参数类型 不强制 保留
     * @return 打点数据点返回参数类型
     */
    Class<?> clazz() default Object.class;
}
