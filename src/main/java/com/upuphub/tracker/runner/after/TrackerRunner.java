package com.upuphub.tracker.runner.after;

import com.upuphub.tracker.BehaviorData;

/**
 * 方法打点处理器
 *
 * @author Inspiration S.P.A Leo
 **/
public interface TrackerRunner extends Runnable{
    /**
     * 打点数据完成处理后的后置信息发送处理器的处理逻辑
     *
     * @param topic 打点数据监听的topic
     * @param event 打点数据关注的事件
     * @param behaviorData 获取的用户行为属性记录信息
     */
    void handler(String topic, String event, BehaviorData behaviorData);


}
