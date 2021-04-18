package com.upuphub.tracker.runner.before;

import com.upuphub.tracker.BehaviorData;
import com.upuphub.tracker.lang.DataPointDefinition;

/**
 * 数据处理器完成执行后的函数式回调方法
 *
 * @author Inspiration S.P.A Leo
 **/
@FunctionalInterface
public interface BeforeRunnerCallback {
    /**
     * 当数据处理器的完成对打点数据的处理时的回调参数定义
     *
     * @param dataPointDefinition 数据点属性定义
     * @param behaviorData 用户通过接口读取到的行为数据
     */
    void onReturnBehaviorData(DataPointDefinition dataPointDefinition, BehaviorData behaviorData);
}
