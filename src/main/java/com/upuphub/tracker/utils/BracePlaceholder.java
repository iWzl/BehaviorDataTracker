package com.upuphub.tracker.utils;

import java.util.Map;
import java.util.function.Function;

/**
 * {}占位符解析工具
 *
 * @author Inspiration S.P.A Leo
 */
public final class BracePlaceholder {
    /**
     * FLAG标记
     */
    private static final Integer FLAG = -1;


    /**
     * 隐藏的私有话构造器
     */
    private BracePlaceholder() {
    }

    /**
     * 花括号占位符解析器
     */
    private static final PlaceholderResolver RESOLVER = PlaceholderResolver.getResolver("{", "}");

    /**
     * 花括号占位符解析器
     *
     * @param content content
     * @param objs objs
     * @return 完成替换的处理结果
     */
    public static String resolve(String content, Object... objs) {
        return RESOLVER.resolve(content, objs);
    }

    /**
     * 花括号占位符解析器
     *
     * @param content content
     * @param map map
     * @return 完成替换的处理结果
     */
    public static String resolveByMap(String content, Map<String, Object> map) {
        return RESOLVER.resolveByMap(content, map);
    }

    /**
     * 花括号占位符解析器
     *
     * @param content content
     * @param object map
     * @return 完成替换的处理结果
     */
    public static String resolveByObject(String content, Object object) {
        return RESOLVER.resolveByObject(content, object);
    }

    /**
     * 花括号占位符解析器
     *
     * @param content content
     * @param rule rule
     * @return 完成替换的处理结果
     */
    public static String resolveByRule(String content, Function<String, String> rule) {
        return RESOLVER.resolveByRule(content, rule);
    }

    /**
     * 花括号占位符解析器
     *
     * @param temp temp
     * @param content content
     * @param res res
     */
    public static void reverTemplate(String temp, String content, Map<String, String> res) {
        int index = temp.indexOf("{");
        int endIndex = temp.indexOf("}") + 1;

        String next = temp.substring(endIndex).trim();
        int nextContain = next.indexOf("{");
        String nexIndexValue = next;
        if (nextContain != FLAG) {
            nexIndexValue = next.substring(0, nextContain);
        }
        String key = temp.substring(index + 1, endIndex - 1);

        int valueLastIndex;
        if ("".equals(nexIndexValue)) {
            valueLastIndex = content.length();
        } else {
            valueLastIndex = content.indexOf(nexIndexValue);
        }
        String value = content.substring(index, valueLastIndex).trim();
        res.put(key, value);

        if (nextContain != FLAG) {
            reverTemplate(next, content.substring(content.indexOf(value) +
                    value.length(), content.length()).trim(), res);
        }
    }
}
