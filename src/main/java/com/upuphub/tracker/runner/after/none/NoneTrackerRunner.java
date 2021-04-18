package com.upuphub.tracker.runner.after.none;

import com.upuphub.tracker.BehaviorData;
import com.upuphub.tracker.runner.after.AbstractTrackerRunner;

/**
 * None Tracker Handler 实现
 *
 * @author Inspiration S.P.A Leo
 **/
public class NoneTrackerRunner extends AbstractTrackerRunner {


    /**
     * None的基本构造器
     *
     * @param topic 打点数据监听的topic
     * @param event 打点数据关注的事件
     * @param behaviorData 获取的用户行为属性记录信息
     */
    public NoneTrackerRunner(String topic, String event, BehaviorData behaviorData) {
        super(topic, event, behaviorData);
    }

    /**
     * 白板的Handler实现
     *
     * @param topic        打点数据监听的topic
     * @param event        打点数据关注的事件
     * @param behaviorData 获取的用户行为属性记录信息
     */
    @Override
    public void handler(String topic, String event, BehaviorData behaviorData) {

    }

    @Override
    public void run() {

    }
}
