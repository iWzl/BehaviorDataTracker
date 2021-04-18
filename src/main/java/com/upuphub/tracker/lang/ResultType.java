package com.upuphub.tracker.lang;

/**
 * 数据处理器的返回类型
 *
 * @author Inspiration S.P.A Leo
 **/
public enum  ResultType {
    /**
     * 仅处理行为数据的ID
     */
    ID,
    /**
     * 完成部分行为数据的处理
     */
    BEHAVIOR_DATA,
    /**
     * 完成所有的行为数据的覆盖处理
     */
    BEHAVIOR_DATA_OVERALL,

    /**
     * 行为数据事件
     */
    BEHAVIOR_DATA_EVENT,

    /**
     * 行为数据加上事件的覆盖
     */
    BEHAVIOR_DATA_EVENT_OVERALL
}
