package com.upuphub.tracker;

/**
 * 在数据处理器中需要对行为数据信息更加只有的数据调整时间,能够通过改对象对行为数据对象进行Topic、Event在内的更加详细的调整。
 *
 * @author Inspiration S.P.A Leo
 **/
public class BehaviorDataEvent {
    /**
     * 数据埋点事件发送的话题
     */
    private String topic;
    /**
     * 数据埋点描述的事件
     */
    private String event;
    /**
     * 数据埋点数据采集到的行为数据
     */
    private BehaviorData behaviorData;

    /**
     * 获取数据埋点事件发送的话题
     *
     * @return 数据埋点事件发送的话题
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置数据埋点事件发送的话题
     *
     * @param topicIn 数据埋点事件发送的话题
     */
    public void setTopic(String topicIn) {
        this.topic = topicIn;
    }

    /**
     * 获取数据埋点描述的事件
     *
     * @return 数据埋点描述的事件
     */
    public String getEvent() {
        return event;
    }

    /**
     * 设置数据埋点描述的事件
     *
     * @param eventIn 数据埋点描述的事件
     */
    public void setEvent(String eventIn) {
        this.event = eventIn;
    }

    /**
     * 获取数据埋点数据采集到的行为数据
     *
     * @return 数据埋点数据采集到的行为数据
     */
    public BehaviorData getBehaviorData() {
        return behaviorData;
    }

    /**
     * 设置数据埋点数据采集到的行为数据
     *
     * @param behaviorDataIn 数据埋点数据采集到的行为数据
     */
    public void setBehaviorData(BehaviorData behaviorDataIn) {
        this.behaviorData = behaviorDataIn;
    }
}
