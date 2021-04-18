package com.upuphub.tracker.runner.after;

import com.upuphub.tracker.BehaviorData;
import com.upuphub.tracker.exception.TrackHandlerRuntimeException;

/**
 * 基础的Handler实现
 *
 * @author Inspiration S.P.A Leo
 **/
public abstract class AbstractTrackerRunner implements TrackerRunner{
    /**
     * 用户行为事件需要发送的Topic
     */
    private final String topic;
    /**
     * 用户行为具体描述的事件
     */
    private final String event;
    /**
     * 通过数据接入点获取到的用户行为属性信息
     */
    private final BehaviorData behaviorData;

    /**
     * 基本的创建构造器,用于构建数据发送器的线程执行对象
     *
     * @param topic 用户事件的Topic
     * @param event 用户行为具体描述的事件
     * @param behaviorData 通过数据接入点获取到的用户行为属性信息
     */
    public AbstractTrackerRunner(String topic, String event, BehaviorData behaviorData) {
        this.topic = topic;
        this.event = event;
        this.behaviorData = behaviorData;
    }

    /**
     * 实现Runnable的Run方法执行数据发送处理器属性逻辑
     */
    @Override
    public void run() {
        try {
            handler(topic, event, behaviorData);
        } catch (Exception ex) {
            throw new TrackHandlerRuntimeException("Run Track Handler Exception", ex);
        }
    }
}
