package com.upuphub.tracker.intercept;


/**
 * BeanPostProcessor的接口实现定义
 *
 * @author Inspiration S.P.A Leo
 **/
public interface BeanPostProcessor {

    /**
     * 初始化Bean增强的后处理器
     *
     * @param bean 需要进行处理的Bean实例
     * @return 完成处理后的Bean实例
     */
    default Object postProcessAfterInitialization(Object bean) {
        return bean;
    }
}
