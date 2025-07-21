package com.beicroon.construct.json.utils;

import com.beicroon.construct.json.template.JsonTemplate;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public final class JsonUtils {

    @Setter
    private static JsonTemplate jsonTemplate;

    private JsonUtils() {

    }

    public static String toJson(Object object) {
        return JsonUtils.jsonTemplate.toJson(object);
    }

    public static <T> T toObject(byte[] bytes, Class<T> clazz) {
        return JsonUtils.jsonTemplate.toObject(bytes, clazz);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return JsonUtils.jsonTemplate.toObject(json, clazz);
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        return JsonUtils.jsonTemplate.toList(json, clazz);
    }

    public static Map<String, Object> toMap(String json) {
        return JsonUtils.jsonTemplate.toMap(json);
    }

}
