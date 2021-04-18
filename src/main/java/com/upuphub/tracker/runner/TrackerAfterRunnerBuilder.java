package com.upuphub.tracker.runner;

import com.upuphub.tracker.BehaviorData;
import com.upuphub.tracker.lang.RunnerType;
import com.upuphub.tracker.runner.after.TrackerRunner;
import com.upuphub.tracker.runner.after.kafka.KafkaTrackerRunner;
import com.upuphub.tracker.runner.after.logger.LoggerTrackerRunner;
import com.upuphub.tracker.runner.after.none.NoneTrackerRunner;
import com.upuphub.tracker.runner.after.rocketmq.RocketMQTrackerRunner;

/**
 * TrackerRunnerBuilder
 *
 * @author Inspiration S.P.A Leo
 **/
public class TrackerAfterRunnerBuilder {
    /**
     * 数据点的输出处理类型
     */
    private final RunnerType runnerType;
    /**
     * 数据监听的topic事件
     */
    private String topic;
    /**
     * 数据点关注的事件
     */
    private String event;
    /**
     * 数据打点的行为属性数据
     */
    private BehaviorData behaviorData;

    /**
     * Tracker的数据发送处理器实现
     *
     * @param runnerType 数据点的输出处理类型
     */
    public TrackerAfterRunnerBuilder(RunnerType runnerType) {
        this.runnerType = runnerType;
    }

    /**
     * Builder模式对topic事件的设置
     *
     * @param topicIn 数据监听的topic事件
     * @return 构造器属性实现实例
     */
    public TrackerAfterRunnerBuilder topic(String topicIn) {
        this.topic = topicIn;
        return this;
    }

    /**
     * Builder模式对事件的设置
     *
     * @param eventIn 数据点关注的事件
     * @return 构造器属性实现实例
     */
    public TrackerAfterRunnerBuilder event(String eventIn) {
        this.event = eventIn;
        return this;
    }

    /**
     * 用户行为采集到的数据属性属性
     *
     * @param behaviorDataIn 用户行为输入属性
     * @return 构造器属性实现实例
     */
    public TrackerAfterRunnerBuilder behaviorData(BehaviorData behaviorDataIn) {
        this.behaviorData = behaviorDataIn;
        return this;
    }

    /**
     * 创建TrackerRunner的执行器实现
     *
     * @return TrackerRunner的执行器实现
     */
    public TrackerRunner build() {
        switch (runnerType) {
            case TO_LOG:
                return new LoggerTrackerRunner(this.topic, this.event, this.behaviorData);
            case TO_ROCKETMQ:
                return new RocketMQTrackerRunner(this.topic, this.event, this.behaviorData);
            case TO_KAFKA:
                return new KafkaTrackerRunner(this.topic, this.event, this.behaviorData);
            case TO_HANDLER:
            case TO_NONE:
            default:
                return new NoneTrackerRunner(this.topic, this.event, this.behaviorData);
        }
    }
}
