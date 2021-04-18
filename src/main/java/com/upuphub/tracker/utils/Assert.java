package com.upuphub.tracker.utils;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * 参数属性判断断言工具
 *
 * @author Inspiration S.P.A Leo
 */
public final class Assert {

    /**
     * 私有话构造器
     */
    private Assert() {
    }

    /**
     * 判断String对象不为空字符串
     *
     * @param string 需要判断的字符串
     * @param message 当字符串为空时,抛出异常携带的message消息
     */
    public static void notEmpty(String string, String message) {
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言字符串不为空字符
     *
     * @param string 需要判断的字符串
     * @param messageSupplier 异常msg生产者
     */
    public static void notEmpty(String string, Supplier<String> messageSupplier) {
        notEmpty(string, messageSupplier.get());
    }

    /**
     * 断言对象不为空
     *
     * @param object 需要判断的对象
     * @param message 抛出异常携带的message消息
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言对象不为空
     *
     * @param object 需要判断的对象
     * @param messageSupplier 异常msg生产者
     */
    public static void notNull(Object object, Supplier<String> messageSupplier) {
        notNull(object, messageSupplier.get());
    }

    /**
     * 断言对象不为空
     *
     * @param object 需要判断的对象
     * @param exceptionSupplier 异常生产者
     * @param <T> RuntimeException运行时异常
     */
    public static <T extends RuntimeException> void notNull2(Object object, Supplier<T> exceptionSupplier) {
        if (object == null) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 断言数组不为空
     *
     * @param array 需要判断的对象
     * @param message 抛出异常携带的message消息
     * @param <T> RuntimeException运行时异常
     */
    public static <T> void notEmpty(T[] array, String message) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言数组不为空
     *
     * @param array 需要判断的对象
     * @param messageSupplier 异常msg生产者
     * @param <T> RuntimeException运行时异常
     */
    public static <T> void notEmpty(T[] array, Supplier<String> messageSupplier) {
        notEmpty(array, messageSupplier.get());
    }

    /**
     * 断言集合不为空
     *
     * @param collection 需要判断的集合对象
     * @param message 抛出异常携带的message消息
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }


    /**
     * 断言一个condition表达式为true，用于需要大量拼接字符串以及一些其他操作等
     *
     * @param condition boolean表达式
     * @param message   抛出异常携带的message消息
     */
    public static void assertState(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 断言一个boolean表达式为true，用于需要大量拼接字符串以及一些其他操作等
     *
     * @param expression boolean表达式
     * @param message   抛出异常携带的message消息
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个boolean表达式为true，用于需要大量拼接字符串以及一些其他操作等
     *
     * @param expression boolean表达式
     * @param supplier   msg生产者
     */
    public static void isTrue(boolean expression, Supplier<String> supplier) {
        if (!expression) {
            throw new IllegalArgumentException(supplier.get());
        }
    }
}
