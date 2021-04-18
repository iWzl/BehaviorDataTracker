package com.upuphub.tracker.serializer.jackson;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upuphub.tracker.serializer.Serializer;


/**
 * JSON的工具类
 *
 * @author Inspiration S.P.A Leo
 * @version 1.0
 */
public class JacksonSerializer implements Serializer {
    /**
     * Json对象ObjectMapper实例
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换成二进制数组
     *
     * @param object 需要转换的数据对象
     * @return 转换后的处理结果
     */
    @Override
    public String serializeToString(Object object) {
        String jsonValue = "";
        try {
            // Java object to JSON string, default compact-print
            jsonValue = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            jsonValue = "JacksonSerializer Error";
            LOGGER.error("JacksonSerializer.serializeToByteArray Error {}", e.getMessage(), e);
        }
        return jsonValue;
    }

    /**
     * 基本的Json对象ObjectMapper实例创建器
     *
     * @return Json对象ObjectMapper实例
     */
    private static ObjectMapper newGenerateMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.NONE)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return mapper;
    }
}
