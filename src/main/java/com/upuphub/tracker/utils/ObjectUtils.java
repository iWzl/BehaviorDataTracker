package com.upuphub.tracker.utils;


import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * ObjectUtils
 *
 * @author Inspiration S.P.A Leo
 */
public final class ObjectUtils {

    /**
     * 私有话构造器
     */
    private ObjectUtils() {
    }

    /**
     * 如果obj为null，则返回默认值，不为null，则返回obj
     *
     * @param obj          obj
     * @param defaultValue 默认值
     * @param <T>          值泛型
     * @return obj不为null 返回obj，否则返回默认值
     */
    public static <T> T defaultIfNull(T obj, T defaultValue) {
        return obj != null ? obj : defaultValue;
    }


    /**
     * 判断对象是否是集合
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isCollection(Object obj) {
        return obj instanceof Collection;
    }

    /**
     * 判断对象是否是MAP
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isMap(Object obj) {
        return obj instanceof Map;
    }

    /**
     * 判断对象是否是数字
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isNumber(Object obj) {
        return obj instanceof Number;
    }

    /**
     * 判断对象是否是boolean
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isBoolean(Object obj) {
        return obj instanceof Boolean;
    }

    /**
     * 判断对象是否是枚举
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isEnum(Object obj) {
        return obj instanceof Enum;
    }

    /**
     * 判断对象是否是Date时间
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isDate(Object obj) {
        return obj instanceof Date || obj instanceof TemporalAccessor;
    }

    /**
     * 判断对象是否是Char
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isCharSequence(Object obj) {
        return obj instanceof CharSequence;
    }

    /**
     * 判断对象是否为八大基本类型包装类除外即(boolean, byte, char, short, int, long, float, and double)<br/>
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isPrimitive(Object obj) {
        return obj != null && obj.getClass().isPrimitive();
    }

    /**
     * 判断对象是否为包装类或者非包装类的基本类型
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isWrapperOrPrimitive(Object obj) {
        return isPrimitive(obj) || isNumber(obj) || isCharSequence(obj) || isBoolean(obj);
    }

    /**
     * 判断一个对象是否为数组
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    /**
     * 判断一个对象是否为基本类型数组即(int[], long[], boolean[], double[]....)
     *
     * @param obj 待判断的对象
     * @return 判断的结果
     */
    public static boolean isPrimitiveArray(Object obj) {
        return isArray(obj) && obj.getClass().getComponentType().isPrimitive();
    }
}
