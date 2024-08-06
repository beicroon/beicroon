package com.beicroon.construct.json.utils;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.jackson.utils.JacksonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public final class JsonUtils {

    private JsonUtils() {

    }

    public static String toJson(Object object) {
        try {
            return JacksonUtils.getMapper().writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T toObject(byte[] bytes, Class<T> clazz) {
        try {
            return JacksonUtils.getMapper().readValue(new String(bytes, SystemConstant.CHARSET), clazz);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return JacksonUtils.getMapper().readValue(json, clazz);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = JacksonUtils.getMapper();

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);

            return mapper.readValue(json, javaType);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Map<String, Object> toMap(String json) {
        try {
            ObjectMapper mapper = JacksonUtils.getMapper();

            JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);

            return mapper.readValue(json, javaType);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
