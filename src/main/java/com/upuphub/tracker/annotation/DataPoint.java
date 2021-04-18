package com.upuphub.tracker.annotation;
import java.lang.annotation.*;

import com.upuphub.tracker.handler.SimpleTrackerHandler;
import com.upuphub.tracker.lang.RunnerType;


/**
 * 受Spring管理的标识需要打点的数据来源点,并且标明打点的处理方式
 *
 * @author Inspiration S.P.A Leo
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPoint {
    /**
     * 数据打点位置标明的主题
     * eg: USER_BEHAVIOR| SYSTEM
     *
     * @return 主题
     */
    String topic() default "";

    /**
     * 数据打点位置标明的事件
     * eg: REGISTER | AI_REVIEW
     *
     * @return 事件
     */
    String event();

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

    /**
     * 在handlerClazz()的处理方法
     *
     * @return 处理方法的方法名称
     */
    String handlerMethod() default "";

}
