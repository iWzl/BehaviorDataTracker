package com.upuphub.tracker.annotation;

import java.lang.annotation.*;

/**
 * 打点入参参数到执行方法输入参数参数标识
 *
 * @author Inspiration S.P.A Leo
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Input {
    /**
     * 输入打点方法输入参数的位置 强制填写
     * @return 输入打点方法输入参数的位置
     */
    int index();
}
