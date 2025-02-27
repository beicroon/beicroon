package com.beicroon.provider.jackson.template;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.json.template.JsonTemplate;
import com.beicroon.construct.json.utils.JsonUtils;
import com.beicroon.provider.jackson.utils.JacksonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JacksonJsonTemplate implements JsonTemplate {

    public JacksonJsonTemplate() {
        JsonUtils.setJsonTemplate(this);
    }

    @Override
    public String toJson(Object object) {
        try {
            return JacksonUtils.getMapper().writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> T toObject(byte[] bytes, Class<T> clazz) {
        try {
            return JacksonUtils.getMapper().readValue(new String(bytes, SystemConstant.CHARSET), clazz);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> T toObject(String json, Class<T> clazz) {
        try {
            return JacksonUtils.getMapper().readValue(json, clazz);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> List<T> toList(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = JacksonUtils.getMapper();

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);

            return mapper.readValue(json, javaType);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Map<String, Object> toMap(String json) {
        try {
            ObjectMapper mapper = JacksonUtils.getMapper();

            JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);

            return mapper.readValue(json, javaType);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
