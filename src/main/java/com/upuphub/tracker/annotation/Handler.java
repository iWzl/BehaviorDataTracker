package com.upuphub.tracker.annotation;

import java.lang.annotation.*;

/**
 *  标识数据埋点的处理类
 *
 * @author Inspiration S.P.A Leo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Handler {
}
