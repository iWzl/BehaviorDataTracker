package com.upuphub.tracker.serializer;

import com.upuphub.tracker.logging.Logger;
import com.upuphub.tracker.logging.LoggerFactory;

/**
 * 对象序列话工具
 *
 * @author Inspiration S.P.A Leo
 **/
public interface Serializer {
    /**
     * 日志打定数据工具
     */
    Logger LOGGER = LoggerFactory.getLogger(Serializer.class);

    /**
     * 将对象转换成二进制数组
     *
     * @param object 需要转换的数据对象
     * @return 转换后的处理结果
     */
    String serializeToString(Object object);

}
