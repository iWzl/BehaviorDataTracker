package com.upuphub.tracker.runner.after.logger;

import com.upuphub.tracker.BehaviorData;
import com.upuphub.tracker.logging.Logger;
import com.upuphub.tracker.logging.LoggerFactory;
import com.upuphub.tracker.runner.after.AbstractTrackerRunner;
import com.upuphub.tracker.serializer.jackson.JacksonSerializer;

/**
 * Logger Tracker Handler 实现
 *
 * @author Inspiration S.P.A Leo
 **/
public class LoggerTrackerRunner extends AbstractTrackerRunner {
    /**
     * 日志打印工具
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerTrackerRunner.class);
    /**
     * Jackson序列化器实现
     */
    private final JacksonSerializer jacksonSerializer = new JacksonSerializer();

    /**
     * 基本的创建构造器,用于构建数据发送器的线程执行对象
     *
     * @param topic 用户事件的Topic
     * @param event 用户行为具体描述的事件
     * @param behaviorData 通过数据接入点获取到的用户行为属性信息
     */
    public LoggerTrackerRunner(String topic, String event, BehaviorData behaviorData) {
        super(topic, event, behaviorData);
    }

    /**
     * LoggerTrackerRunner的运行处理了
     *
     * @param topic        打点数据监听的topic
     * @param event        打点数据关注的事件
     * @param behaviorData 获取的用户行为属性记录信息
     */
    @Override
    public void handler(String topic, String event, BehaviorData behaviorData) {
        LOGGER.info("Topic {} event {} behaviorData {}", topic, event,
                jacksonSerializer.serializeToString(behaviorData));
    }
}
