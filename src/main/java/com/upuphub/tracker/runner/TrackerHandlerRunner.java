package com.upuphub.tracker.runner;

import java.lang.reflect.Method;

import com.upuphub.tracker.BehaviorData;
import com.upuphub.tracker.annotation.DataPoint;
import com.upuphub.tracker.annotation.DataPoints;
import com.upuphub.tracker.factory.DataTrackerExecutorFactory;
import com.upuphub.tracker.factory.TrackerHandlerFactory;
import com.upuphub.tracker.handler.SimpleTrackerHandler;
import com.upuphub.tracker.intercept.Invocation;
import com.upuphub.tracker.lang.DataPointDefinition;
import com.upuphub.tracker.lang.MethodDefinition;
import com.upuphub.tracker.lang.RunnerType;
import com.upuphub.tracker.logging.Logger;
import com.upuphub.tracker.logging.LoggerFactory;
import com.upuphub.tracker.runner.after.TrackerRunner;
import com.upuphub.tracker.runner.before.BeforeRunnerThread;


/**
 * TrackerHandlerRunner
 *
 * @author Inspiration S.P.A Leo
 **/
public final class TrackerHandlerRunner {
    /**
     * 日志打印工具
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackerHandlerRunner.class);

    /**
     * 私有话构造器
     */
    private TrackerHandlerRunner() {
    }

    /**
     * 工具的核心运行逻辑
     *
     * @param methodInvocation 原方法的调用属性
     * @return 原执行方法的数据返回
     * @throws Throwable 原执行方法可能会抛出的异常
     */
    public static Object runTracker(Invocation methodInvocation) throws Throwable {
        Exception runException = null;
        Object resultObject = null;
        long startTime = System.currentTimeMillis();
        int use = 0;
        // 这里执行原方法,记录时间和返回参数以及可能出现的异常
        try {
            resultObject = methodInvocation.proceed();
        } catch (Exception ex) {
            runException = ex;
        } finally {
            use = (int) (System.currentTimeMillis() - startTime);
        }
        try {
            Method dataPointMethod = methodInvocation.getTargetMethod();
            DataPoints dataPoints = TrackerHandlerFactory.getDataPointFromLazyCacheByClazz(
                    methodInvocation.getTargetObject().getClass());
            DataPoint dataPoint = dataPointMethod.getAnnotation(DataPoint.class);
            if (null == dataPoint) {
                return methodInvocation.proceed();
            }
            DataPointDefinition dataPointDefinition = buildDataPointDefinition(
                    dataPoint, dataPoints, dataPointMethod.getName());
            // 完成行为数据的处理
            TrackerHandlerRunner.runBehaviorDataHandler(
                    dataPointDefinition, methodInvocation, resultObject, runException, use);
            // 当原方法调用异常,这里对原异常进行原样抛出,不进行相关处理
        } catch (Exception ex) {
            LOGGER.error("TrackerHandlerRunner Handler Error", ex);
        }
        if (null != runException) {
            throw runException;
        }
        return resultObject;
    }

    /**
     * 解析和读取数据注解属性
     *
     * @param methodDataPoint 类属性DataPoint注解
     * @param clazzDataPoints 方法DataPoint注解
     * @param methodName 方法名称
     * @return 解析完成后的DataPointDefinition定义的对象
     */
    private static DataPointDefinition buildDataPointDefinition(DataPoint methodDataPoint,
                                                                DataPoints clazzDataPoints, String methodName) {
        DataPointDefinition dataPointDefinition = new DataPointDefinition();
        String topic = methodName;
        String handlerMethodName;
        RunnerType runnerType = RunnerType.TO_LOG;
        String event;
        Class<?> handlerClazz = SimpleTrackerHandler.class;
        if (null != clazzDataPoints) {
            topic = clazzDataPoints.topic();
            runnerType = clazzDataPoints.type();
            handlerClazz = clazzDataPoints.handlerClazz();
        }
        topic = "".equals(methodDataPoint.topic()) ? topic : methodDataPoint.topic();
        runnerType = methodDataPoint.type() == RunnerType.TO_LOG ? runnerType : methodDataPoint.type();
        handlerClazz = methodDataPoint.handlerClazz() == SimpleTrackerHandler.class ?
                handlerClazz : methodDataPoint.handlerClazz();
        handlerMethodName = "".equals(methodDataPoint.handlerMethod()) ? methodName : methodDataPoint.handlerMethod();
        event = methodDataPoint.event();

        dataPointDefinition.setTopic(topic);
        dataPointDefinition.setType(runnerType);
        dataPointDefinition.setHandlerClazz(handlerClazz);
        dataPointDefinition.setHandlerMethod(handlerMethodName);
        dataPointDefinition.setEvent(event);
        return dataPointDefinition;
    }

    /**
     * 执行用户行为处理逻辑的数据处理器
     *
     * @param dataPointDefinition 解析完成后的DataPointDefinition定义的对象
     * @param invocation 原方法的调用属性
     * @param result 原方法的执行结果
     * @param throwable 原方法可能抛出的异常
     * @param use 原方法执行使用的时间
     */
    public static void runBehaviorDataHandler(
            DataPointDefinition dataPointDefinition, Invocation invocation,
            Object result, Throwable throwable, int use) {
        MethodDefinition methodDefinition = TrackerHandlerFactory.getMethodByClazzAndMethodName(
                dataPointDefinition.getHandlerClazz(), dataPointDefinition.getHandlerMethod());
        if (null == methodDefinition) {
            LOGGER.warn("did not match the correct execution method");
            return;
        }
        BeforeRunnerThread beforeRunnerThread = new BeforeRunnerThread(
                dataPointDefinition, invocation, result, throwable, use, TrackerHandlerRunner::afterToPushData);
        if (methodDefinition.isAsync()) {
            DataTrackerExecutorFactory.getDataTrackerExecutor().runAsync(beforeRunnerThread);
        } else {
            DataTrackerExecutorFactory.getDataTrackerExecutor().run(beforeRunnerThread);
        }
    }


    /**
     * 执行用户行为处理逻辑的后置数据发送器逻辑
     *
     * @param dataPointDefinition 解析完成后的DataPointDefinition定义的对象
     * @param behaviorData 完成处理组装的用户行为属性
     */
    public static void afterToPushData(
            DataPointDefinition dataPointDefinition, BehaviorData behaviorData) {
        if (null == behaviorData) {
            return;
        }
        TrackerRunner trackerRunner = new TrackerAfterRunnerBuilder(dataPointDefinition.getType())
                .behaviorData(behaviorData)
                .event(dataPointDefinition.getEvent())
                .topic(dataPointDefinition.getTopic())
                .build();
        DataTrackerExecutorFactory.getDataTrackerExecutor().runAsync(trackerRunner);
    }

}
