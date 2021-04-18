package com.upuphub.tracker.lang;


/**
 * 数据点的处理类型
 *
 * @author Inspiration S.P.A Leo
 **/
public enum RunnerType {
    /**
     * 将读取到的数据进行日志打印
     */
    TO_LOG,
    /**
     * 将读取到的数据带到Kafka
     */
    TO_KAFKA,
    /**
     * 将读取的数据带到RocketMQ
     */
    TO_ROCKETMQ,
    /**
     * 将读取到的数据导到自定义的Handler中
     */
    TO_HANDLER,
    /**
     * 不进行特殊处理的RunnerType
     */
    TO_NONE
}
