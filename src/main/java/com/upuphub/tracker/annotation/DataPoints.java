package com.upuphub.tracker.annotation;

import java.lang.annotation.*;

import com.upuphub.tracker.handler.SimpleTrackerHandler;
import com.upuphub.tracker.lang.RunnerType;


/**
 *  标识数据埋点的处理类
 *
 * @author Inspiration S.P.A Leo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPoints {
    /**
     * 数据打点位置标明的主题
     * eg: USER_BEHAVIOR| SYSTEM
     *
     * @return 主题
     */
    String topic();

    /**
     * 处理打点事件的处理类型
     *
     * @return 处理时间的Handler类型
     */
    RunnerType type() default RunnerType.TO_LOG;

    /**
     * 返回数据处理器的Handler实现类
     * 该类会的实例会被本工具维护和创建
     *
     * @return 数据处理器的Handler实现类
     */
    Class<?> handlerClazz() default SimpleTrackerHandler.class;

}
