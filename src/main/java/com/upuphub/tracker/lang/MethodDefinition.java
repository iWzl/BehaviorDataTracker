package com.upuphub.tracker.lang;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Handler Method Define
 *
 * @author Inspiration S.P.A Leo
 **/
public class MethodDefinition {
    /**
     * 方法名称
     */
    private String name;
    /**
     * 方法的执行方式
     */
    private boolean async;
    /**
     * 触发的对象targetObject
     */
    private Object targetObject;
    /**
     * 方法参数属性定义
     */
    private List<ParamDefinition> paramDefinitions;
    /**
     * 返回参数类型
     */
    private ResultType resultType;
    /**
     * 运行的执行方法
     */
    private Method method;

    /**
     * 获取的方法名称
     *
     * @return 获取的方法名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置方法定义的名称
     *
     * @param nameIn 方法定义的名称
     */
    public void setName(String nameIn) {
        this.name = nameIn;
    }

    /**
     * 获取改执行方法是否是异步执行的
     *
     * @return 是异步执行的判断结果
     */
    public boolean isAsync() {
        return async;
    }

    /**
     * 设置同步方法是否是同步执行方法
     *
     * @param asyncIn 异常执行方法
     */
    public void setAsync(boolean asyncIn) {
        this.async = asyncIn;
    }

    /**
     * 获取原始的触发对象
     *
     * @return 获取触发属性的属性对象
     */
    public Object getTargetObject() {
        return targetObject;
    }

    /**
     * 设置原始的触发对象
     *
     * @param targetObjectIn Handler方法的触发对象
     */
    public void setTargetObject(Object targetObjectIn) {
        this.targetObject = targetObjectIn;
    }

    /**
     * 获取数据处理器的方法定义属性信息
     *
     * @return 数据处理器的方法定义属性信息
     */
    public List<ParamDefinition> getParamDefinitions() {
        return paramDefinitions;
    }

    /**
     * 设置参数定义paramDefinitions属性信息
     *
     * @param paramDefinitionsIn 数据处理器的方法定义属性信息
     */
    public void setParamDefinitions(List<ParamDefinition> paramDefinitionsIn) {
        this.paramDefinitions = paramDefinitionsIn;
    }

    /**
     * 获取handler的结果返回
     *
     * @return 返回类型
     */
    public ResultType getResultType() {
        return resultType;
    }

    /**
     * 设置返回类型属性类型
     *
     * @param resultTypeIn 返回属性类型
     */
    public void setResultType(ResultType resultTypeIn) {
        this.resultType = resultTypeIn;
    }

    /**
     * 获取数据处理器的执行方法
     *
     * @return 获取方法
     */
    public Method getMethod() {
        return method;
    }

    /**
     * 设置数据处理器的执行方法
     *
     * @param methodIn 数据处理器的执行方法
     */
    public void setMethod(Method methodIn) {
        this.method = methodIn;
    }
}
