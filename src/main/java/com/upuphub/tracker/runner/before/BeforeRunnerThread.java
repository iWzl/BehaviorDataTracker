package com.upuphub.tracker.runner.before;

import com.upuphub.tracker.BehaviorData;
import com.upuphub.tracker.BehaviorDataEvent;
import com.upuphub.tracker.factory.TrackerHandlerFactory;
import com.upuphub.tracker.intercept.Invocation;
import com.upuphub.tracker.lang.DataPointDefinition;
import com.upuphub.tracker.lang.MethodDefinition;
import com.upuphub.tracker.lang.ParamDefinition;
import com.upuphub.tracker.logging.Logger;
import com.upuphub.tracker.logging.LoggerFactory;
import com.upuphub.tracker.utils.ReflectionUtils;
import com.upuphub.tracker.utils.StringUtils;

import static com.upuphub.tracker.config.CommonConfig.SERVER_HOST_NAME;


/**
 * 用户数据处理的前置处理器
 *
 * @author Inspiration S.P.A Leo
 **/
public class BeforeRunnerThread implements Runnable{
    /**
     * 日志打印工具对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeRunnerThread.class);

    /**
     * 数据点的注解接口属性参数值
     */
    private DataPointDefinition dataPointDefinition;
    /**
     * 代理方法的调用属性和方法参数
     */
    private final Invocation invocation;
    /**
     * 原代理对象的调用返回结果
     */
    private final Object result;
    /**
     * 原代理对象可能会抛出的异常
     */
    private final Throwable throwable;
    /**
     * 原调用方法的执行时间
     */
    private final int use;
    /**
     * 完成前置处理后的回调执行方法
     */
    private final BeforeRunnerCallback beforeRunnerCallback;

    /**
     * 数据处理器的运行线程的基本构造器
     *
     * @param dataPointDefinition 数据点的注解接口属性参数值
     * @param invocation 代理方法的调用属性和方法参数
     * @param result 原代理对象的调用返回结果
     * @param throwable 原代理对象可能会抛出的异常
     * @param use 原调用方法的执行时间
     * @param beforeRunnerCallback 完成前置处理后的回调执行方法
     */
    public BeforeRunnerThread(
            DataPointDefinition dataPointDefinition,
            Invocation invocation,
            Object result,
            Throwable throwable,
            int use,
            BeforeRunnerCallback beforeRunnerCallback) {
        this.dataPointDefinition = dataPointDefinition;
        this.invocation = invocation;
        this.result = result;
        this.throwable = throwable;
        this.use = use;
        this.beforeRunnerCallback = beforeRunnerCallback;
    }

    /**
     * 数据处理器的运行的核心逻辑
     * 1. 完成HandlerMethod方法和属性获取
     * 2. 组装HandlerMethod需要的入参参数
     * 3. 反射调用HandlerMethod方法
     * 4. 根据HandlerMethod定义返回类型处理返回的数据
     * 5. 将返回的处理结果带到beforeRunnerCallback中继续执行发送相关的逻辑
     */
    @Override
    public void run() {
        MethodDefinition methodDefinition = TrackerHandlerFactory.getMethodByClazzAndMethodName(
                dataPointDefinition.getHandlerClazz(), dataPointDefinition.getHandlerMethod());
        if (null == methodDefinition) {
            LOGGER.warn("did not match the correct execution method");
            return;
        }
        Object[] handlerArgs = null;
        if (null != methodDefinition.getParamDefinitions() && !methodDefinition.getParamDefinitions().isEmpty()) {
            handlerArgs = new Object[methodDefinition.getParamDefinitions().size()];
            for (int index = 0; index < methodDefinition.getParamDefinitions().size(); index++) {
                ParamDefinition paramDefinition = methodDefinition.getParamDefinitions().get(index);
                switch (paramDefinition.getParamType()) {
                    case INPUT:
                        handlerArgs[index] = invocation.getArgs()[paramDefinition.getIndex()];
                        break;
                    case RESULT:
                        handlerArgs[index] = result;
                        break;
                    case EXCEPTION:
                        handlerArgs[index] = throwable;
                        break;
                    default:
                        handlerArgs[index] = null;
                }
            }
        }
        Object handlerResult = ReflectionUtils.executeTargetMethod(
                methodDefinition.getTargetObject(), methodDefinition.getMethod(), handlerArgs);
        BehaviorData behaviorData = completeBehaviorFromHandlerMethodResult(handlerResult, methodDefinition);
        beforeRunnerCallback.onReturnBehaviorData(dataPointDefinition, behaviorData);
    }

    /**
     * 根据HandlerMethod定义返回类型处理返回的数据
     *
     * @param handlerResult  Handler的处理返回结果
     * @param methodDefinition Handler方法的定义属性参数
     * @return BehaviorData完成处理后的用户行为属性信息
     */
    private BehaviorData completeBehaviorFromHandlerMethodResult(Object handlerResult,
                                                                 MethodDefinition methodDefinition){
        BehaviorData behaviorData = null;
        switch (methodDefinition.getResultType()) {
            case ID:
                if (handlerResult instanceof String || handlerResult instanceof Number) {
                    behaviorData = buildBehaviorDataFromDataPoint(
                            String.valueOf(handlerResult), invocation.getArgs(), throwable, result, use);
                } else {
                    LOGGER.error("Handler Result Type Error [String|Number]");
                }
                break;
            case BEHAVIOR_DATA:
                if (handlerResult instanceof BehaviorData) {
                    behaviorData = completeBehaviorDataAttributes((BehaviorData) handlerResult,
                            invocation, result, throwable, use);
                } else {
                    LOGGER.error("Handler Result Type Error [BehaviorData]");
                }
                break;
            case BEHAVIOR_DATA_OVERALL:
                if (handlerResult instanceof BehaviorData) {
                    behaviorData = (BehaviorData) handlerResult;
                    behaviorData.setServer(SERVER_HOST_NAME);
                } else {
                    LOGGER.error("Handler Result Type Error [BehaviorData]");
                }
                break;
            case BEHAVIOR_DATA_EVENT:
                if (handlerResult instanceof BehaviorDataEvent) {
                    BehaviorDataEvent behaviorDataEvent = (BehaviorDataEvent) handlerResult;
                    behaviorData = completeBehaviorDataAttributes(
                            behaviorDataEvent.getBehaviorData(), invocation, result, throwable, use);
                    DataPointDefinition reDataPointDefinition = new DataPointDefinition(dataPointDefinition);
                    reDataPointDefinition.setTopic(behaviorDataEvent.getTopic());
                    reDataPointDefinition.setEvent(behaviorDataEvent.getEvent());
                    dataPointDefinition = reDataPointDefinition;
                } else {
                    LOGGER.error("Handler Result Type Error [BehaviorDataEvent]");
                }
                break;
            case BEHAVIOR_DATA_EVENT_OVERALL:
                if (handlerResult instanceof BehaviorDataEvent) {
                    BehaviorDataEvent behaviorDataEventOverAll = (BehaviorDataEvent) handlerResult;
                    behaviorData = behaviorDataEventOverAll.getBehaviorData();
                    DataPointDefinition reDataPointDefinitionOverAll = new DataPointDefinition(dataPointDefinition);
                    reDataPointDefinitionOverAll.setTopic(behaviorDataEventOverAll.getTopic());
                    reDataPointDefinitionOverAll.setEvent(behaviorDataEventOverAll.getEvent());
                    dataPointDefinition = reDataPointDefinitionOverAll;
                } else {
                    LOGGER.error("Handler Result Type Error [BehaviorDataEvent]");
                }
                break;
            default:
                behaviorData = buildBehaviorDataFromDataPoint("No-Set",
                        invocation.getArgs(), throwable, handlerResult, use);
                break;
        }
        return behaviorData;
    }

    /**
     * 根据用户行为和相关结果返回构造补充完善用户行为属性信息
     *
     * @param behaviorDataIn 用户行为输入数据
     * @param invocationIn 代理方法数据参数
     * @param resultIn 原执行方法的返回属性参数
     * @param throwableIn 原方法可能会发生的异常参数
     * @param useIn 原执行的方法调用用时
     * @return 完成数据补充后的用户行为属性信息
     */
    private BehaviorData completeBehaviorDataAttributes(BehaviorData behaviorDataIn,
                                                        Invocation invocationIn,
                                                        Object resultIn,
                                                        Throwable throwableIn,
                                                        int useIn) {
        if (StringUtils.isEmpty(behaviorDataIn.getId())) {
            behaviorDataIn.setId("No-Set");
        }
        if (null == behaviorDataIn.getEnter()) {
            behaviorDataIn.setEnter(invocationIn.getArgs());
        }
        if (null == behaviorDataIn.getResult()) {
            behaviorDataIn.setResult(resultIn);
        }
        if (null == behaviorDataIn.getThrowable()) {
            behaviorDataIn.setThrowable(throwableIn);
        }
        if (0 == behaviorDataIn.getUse()) {
            behaviorDataIn.setUse(useIn);
        }
        behaviorDataIn.setTimestamp(System.currentTimeMillis());
        behaviorDataIn.setServer(SERVER_HOST_NAME);
        return behaviorDataIn;
    }

    private static BehaviorData buildBehaviorDataFromDataPoint(
            String id,
            Object[] args,
            Throwable throwable,
            Object resultObject,
            Integer use) {
        BehaviorData behaviorData = new BehaviorData();
        behaviorData.setId(id);
        behaviorData.setEnter(args);
        behaviorData.setThrowable(throwable);
        behaviorData.setTimestamp(System.currentTimeMillis());
        behaviorData.setResult(resultObject);
        behaviorData.setServer(SERVER_HOST_NAME);
        behaviorData.setUse(use);
        return behaviorData;
    }
}

