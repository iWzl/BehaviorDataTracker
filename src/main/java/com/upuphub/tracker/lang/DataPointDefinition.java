package com.upuphub.tracker.lang;

/**
 * 数据点的数据映射Bean属性对象
 *
 * @author Inspiration S.P.A Leo
 **/
public class DataPointDefinition {
    /**
     * 数据打点位置标明的主题
     * eg: USER_BEHAVIOR| SYSTEM
     */
    private String topic;

    /**
     * 数据打点位置标明的事件
     * eg: REGISTER | AI_REVIEW
     */
    private String event;

    /**
     * 处理打点事件的处理类型
     */
    private RunnerType type;

    /**
     * 数据处理器的类、可选参数、可通过@DataPoints简化
     */
    private Class<?> handlerClazz;

    /**
     * 数据处理的方法、可选参数、默认为数据点方法名
     */
    private String handlerMethod;

    /**
     * 默认的无参构造器
     */
    public DataPointDefinition() {
    }

    /**
     * 基本复制的dataPointDefinition定义属性定义
     *
     * @param dataPointDefinition 需要复制的dataPointDefinition定义
     */
    public DataPointDefinition(DataPointDefinition dataPointDefinition) {
        this.setEvent(dataPointDefinition.getEvent());
        this.setTopic(dataPointDefinition.getTopic());
        this.setType(dataPointDefinition.getType());
        this.setHandlerMethod(dataPointDefinition.getHandlerMethod());
        this.setHandlerClazz(dataPointDefinition.getHandlerClazz());
    }

    /**
     * 获取数据点包含的主题
     *
     * @return 数据点包含的主题
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置数据点包含的主题
     *
     * @param topicIn 数据点包含的主题
     */
    public void setTopic(String topicIn) {
        this.topic = topicIn;
    }

    /**
     * 获取数据点对应的参数事件
     *
     * @return 数据点对应的参数事件
     */
    public String getEvent() {
        return event;
    }

    /**
     * 设置数据点对应的参数事件
     *
     * @param eventIn 数据点对应的参数事件
     */
    public void setEvent(String eventIn) {
        this.event = eventIn;
    }

    /**
     * 获取RunnerType的运行时返回类型
     *
     * @return 返回RunnerType的运行时返回类型
     */
    public RunnerType getType() {
        return type;
    }

    /**
     * 设置运行时返回类型
     *
     * @param typeIn 运行时返回类型
     */
    public void setType(RunnerType typeIn) {
        this.type = typeIn;
    }

    /**
     * 获取数据处理器的类
     *
     * @return 数据处理器的类
     */
    public Class<?> getHandlerClazz() {
        return handlerClazz;
    }

    /**
     * 设置数据处理器的类
     *
     * @param handlerClazzIn 数据处理器的类
     */
    public void setHandlerClazz(Class<?> handlerClazzIn) {
        this.handlerClazz = handlerClazzIn;
    }

    /**
     * 数据处理器的类的处理方法
     *
     * @return 处理HandlerMethod
     */
    public String getHandlerMethod() {
        return handlerMethod;
    }

    /**
     * 设置Handler方法的处理器类
     *
     * @param handlerMethodIn 处理器下的处理方法
     */
    public void setHandlerMethod(String handlerMethodIn) {
        this.handlerMethod = handlerMethodIn;
    }
}
