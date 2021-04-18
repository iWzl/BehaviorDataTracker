package com.upuphub.tracker;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 该对象是实际获取到的打点数据的获取到所有数据的载体属性，其主要包含7个关键属性字段。
 *
 * @author Inspiration S.P.A Leo
 **/
public class BehaviorData implements Serializable {
    /**
     * 记该行为所属对象
     * 一般来说对系统来说是系统用户的uin,不可能为空,未设置或未知是其值的No-Set
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private String id;

    /**
     * 原处理方法处理完成的时间(数据采取点的信息发送时间)
     * UTC时间戳、毫秒级，不是原处理方法的开始时间，不可能为空
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("timestamp")
    private Long timestamp;

    /**
     * 该事件发生机器机器
     * 包含机器名称和进程的PID,不可能为空
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("server")
    private String server;

    /**
     * 原数据点方法的入参
     * 标记了数据点方法的单个/多个输入参数、可能为空
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("enter")
    private Object[] enter;

    /**
     * 原数据点方法的返回参数
     * 标记了数据点方法的唯一返回参数、可能为空
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("result")
    private Object result;

    /**
     * 原数据点方法执行发送异常的产生属性
     * 标记了数据点方法的唯一异常属性，一般为空
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("throwable")
    private Throwable throwable;

    /**
     * 记录原方法执行的调用用时
     * 单位为毫秒、可以用timestamp-use较为精确的获取到原数据点的调用时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("useTime")
    private int use;

    /**
     * 获取记该行为所属对象
     * 一般来说对系统来说是系统用户的uin,不可能为空,未设置或未知是其值的No-Set
     *
     * @return 标记该行为所属对象
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记该行为所属对象
     * 一般来说对系统来说是系统用户的uin,不可能为空,未设置或未知是其值的No-Set
     *
     * @param idIn 设置记该行为所属对象
     */
    public void setId(String idIn) {
        this.id = idIn;
    }

    /**
     * 获取原处理方法处理完成的时间(数据采取点的信息发送时间)
     * UTC时间戳、毫秒级，不是原处理方法的开始时间，不可能为空
     *
     * @return 原处理方法处理完成的时间(数据采取点的信息发送时间)
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 设置原处理方法处理完成的时间(数据采取点的信息发送时间)
     * UTC时间戳、毫秒级，不是原处理方法的开始时间，不可能为空
     *
     * @param timestampIn 原处理方法处理完成的时间(数据采取点的信息发送时间)
     */
    public void setTimestamp(Long timestampIn) {
        this.timestamp = timestampIn;
    }

    /**
     * 获取该事件发生机器,包含机器名称和进程的PID,不可能为空
     *
     * @return 该事件发生机器,包含机器名称和进程的PID
     */
    public String getServer() {
        return server;
    }

    /**
     * 获取该事件发生机器,包含机器名称和进程的PID,不可能为空
     *
     * @param serverIn 该事件发生机器,包含机器名称和进程的PID
     */
    public void setServer(String serverIn) {
        this.server = serverIn;
    }

    /**
     * 获取原数据点方法的入参
     * 标记了数据点方法的单个/多个输入参数、可能为空
     *
     * @return 原数据点方法的入参
     */
    public Object[] getEnter() {
        return enter;
    }

    /**
     * 设置原数据点方法的入参
     * 标记了数据点方法的单个/多个输入参数、可能为空
     *
     * @param enterIn 原数据点方法的入参
     */
    public void setEnter(Object[] enterIn) {
        this.enter = enterIn;
    }

    /**
     * 获取原数据点方法的返回参数
     * 标记了数据点方法的唯一返回参数、可能为空
     *
     * @return 原数据点方法的返回参数
     */
    public Object getResult() {
        return result;
    }

    /**
     * 设置原数据点方法的返回参数
     * 标记了数据点方法的唯一返回参数、可能为空
     *
     * @param resultIn 原数据点方法的返回参数
     */
    public void setResult(Object resultIn) {
        this.result = resultIn;
    }

    /**
     * 获取原数据点方法执行发送异常的产生属性
     * 标记了数据点方法的唯一异常属性，一般为空
     *
     * @return 原数据点方法执行发送异常的产生属性
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * 设置原数据点方法执行发送异常的产生属性
     * 标记了数据点方法的唯一异常属性，一般为空
     *
     * @param throwableIn 原数据点方法执行发送异常的产生属性
     */
    public void setThrowable(Throwable throwableIn) {
        this.throwable = throwableIn;
    }

    /**
     * 获取设置记录原方法执行的调用用时
     * 单位为毫秒、可以用timestamp-use较为精确的获取到原数据点的调用时间
     *
     * @return 设置记录原方法执行的调用用时
     */
    public int getUse() {
        return use;
    }

    /**
     * 设置原方法执行的调用用时
     * 单位为毫秒、可以用timestamp-use较为精确的获取到原数据点的调用时间
     *
     * @param useIn 设置记录原方法执行的调用用时
     */
    public void setUse(int useIn) {
        this.use = useIn;
    }
}
