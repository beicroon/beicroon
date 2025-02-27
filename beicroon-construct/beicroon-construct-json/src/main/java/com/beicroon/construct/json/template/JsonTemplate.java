package com.beicroon.construct.json.template;

import java.util.List;
import java.util.Map;

public interface JsonTemplate {

    String toJson(Object object);

    <T> T toObject(byte[] bytes, Class<T> clazz);

    <T> T toObject(String json, Class<T> clazz);

    <T> List<T> toList(String json, Class<T> clazz);

    Map<String, Object> toMap(String json);
    
}
