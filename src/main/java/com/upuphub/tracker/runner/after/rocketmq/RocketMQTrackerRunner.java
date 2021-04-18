package com.upuphub.tracker.runner.after.rocketmq;

import com.upuphub.tracker.BehaviorData;
import com.upuphub.tracker.runner.after.AbstractTrackerRunner;

/**
 * RocketMQ Tracker Handler 实现
 *
 * @author Inspiration S.P.A Leo
 **/
public class RocketMQTrackerRunner extends AbstractTrackerRunner {


    /**
     * RocketMQ的Tracker创建构造器实现
     *
     * @param topic 打点数据监听的topic
     * @param event 打点数据关注的事件
     * @param behaviorData 获取的用户行为属性记录信息
     */
    public RocketMQTrackerRunner(String topic, String event, BehaviorData behaviorData) {
        super(topic, event, behaviorData);
    }

    /**
     * RocketMQ Handler 实际的处理逻辑
     *
     * @param topic        打点数据监听的topic
     * @param event        打点数据关注的事件
     * @param behaviorData 获取的用户行为属性记录信息
     */
    @Override
    public void handler(String topic, String event, BehaviorData behaviorData) {

    }

    /**
     * 实现Runnable的Run方法执行数据发送处理器属性逻辑
     */
    @Override
    public void run() {

    }
}
