package com.upuphub.tracker.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.inspiration.tracker.annotation.*;
import com.upuphub.tracker.annotation.*;
import com.upuphub.tracker.annotation.Exception;
import com.upuphub.tracker.lang.MethodDefinition;
import com.upuphub.tracker.lang.ParamDefinition;
import com.upuphub.tracker.lang.ParamType;
import com.upuphub.tracker.utils.ReflectionUtils;



/**
 * Tracker Handler的创建工厂
 *
 * @author Inspiration S.P.A Leo
 **/
public abstract class TrackerHandlerFactory {
    /**
     * 方法执行器的HandlerClass与其方法的定义映射Map
     */
    private static final Map<Class<?>, Map<String, MethodDefinition>>
            HANDLER_CLAZZ_TO_METHOD_MAPPER = new ConcurrentHashMap<>();
    /**
     * 打点方法中关于{@link DataPoints}的注解缓存映射
     */
    private static final Map<Class<?>, DataPoints> DATA_POINTS_CACHE_MAPPER = new ConcurrentHashMap<>();

    /**
     * 根据数据处理器的HandlerClass懒加载的方式获取{@link DataPoints}的注解属性
     *
     * @param clazz 数据处理器的HandlerClass
     * @return {@link DataPoints}的注解属性
     */
    public static DataPoints getDataPointFromLazyCacheByClazz(Class<?> clazz){
        if (DATA_POINTS_CACHE_MAPPER.containsKey(clazz)){
            return DATA_POINTS_CACHE_MAPPER.get(clazz);
        }else {
            if (clazz.isAnnotationPresent(DataPoints.class)){
                DataPoints dataPoints = clazz.getDeclaredAnnotation(DataPoints.class);
                DATA_POINTS_CACHE_MAPPER.put(clazz, dataPoints);
                return dataPoints;
            }else {
                return null;
            }
        }
    }

    /**
     * 初始化扫描packageNames下的所有标记了Handler的并且加载他们关系入参参数配置的注解属性
     * {@link Input} 入参参数
     * {@link Return} 返回参数
     * {@link Exception} 异常参数
     *
     * @param packageNames 加载的包名
     */
    public static void initTrackerHandler(String[] packageNames){
        Set<Class<?>> trackerHandlerClazzSet = ReflectionUtils.scanAnnotatedClass(
                packageNames, Handler.class);
        if (null == trackerHandlerClazzSet || trackerHandlerClazzSet.isEmpty()){
            return;
        }
        for (Class<?> clazz: trackerHandlerClazzSet) {
            Object targetObject = ReflectionUtils.newInstance(clazz);
            Map<String, MethodDefinition> methodDefinitionMapper = new HashMap<>();
            for (Method method : clazz.getMethods()) {
                HandlerMethod handlerMethod = method.getDeclaredAnnotation(HandlerMethod.class);
                if (null != handlerMethod){
                    String methodName = "".equals(handlerMethod.alias()) ? method.getName() : handlerMethod.alias();
                    Parameter[] parameters = method.getParameters();
                    List<ParamDefinition> paramDefinitionList = new LinkedList<>();
                    for (Parameter parameter : parameters) {
                        ParamDefinition paramDefinition = new ParamDefinition();
                        paramDefinition.setParamType(ParamType.NONE);
                        for (Annotation annotation : parameter.getDeclaredAnnotations()) {
                            if (annotation instanceof Input){
                                Input input = (Input) annotation;
                                paramDefinition.setParamType(ParamType.INPUT);
                                paramDefinition.setIndex(input.index());
                            }else if (annotation instanceof Return){
                                paramDefinition.setParamType(ParamType.RESULT);
                            }else if (annotation instanceof Exception){
                                paramDefinition.setParamType(ParamType.EXCEPTION);
                            }else {
                                paramDefinition.setParamType(ParamType.NONE);
                            }
                        }
                        paramDefinitionList.add(paramDefinition);
                    }
                    MethodDefinition methodDefinition = new MethodDefinition();
                    methodDefinition.setAsync(handlerMethod.async());
                    methodDefinition.setResultType(handlerMethod.type());
                    methodDefinition.setMethod(method);
                    methodDefinition.setName(methodName);
                    methodDefinition.setTargetObject(targetObject);
                    methodDefinition.setParamDefinitions(paramDefinitionList);
                    methodDefinitionMapper.put(methodName, methodDefinition);
                }
            }
            HANDLER_CLAZZ_TO_METHOD_MAPPER.put(clazz, methodDefinitionMapper);
        }
    }

    /**
     * 根据数据处理器HandlerClass和其中的方法名,获取具体的方法定义属性{@link MethodDefinition}
     *
     * @param clazz HandlerClass的属性
     * @param methodName handlerMethod方法别名
     * @return 数据处理器的方法定义属性
     */
    public static MethodDefinition getMethodByClazzAndMethodName(Class<?> clazz, String methodName) {
        if (HANDLER_CLAZZ_TO_METHOD_MAPPER.containsKey(clazz)){
            return HANDLER_CLAZZ_TO_METHOD_MAPPER.get(clazz).get(methodName);
        }
        return null;
    }
}
