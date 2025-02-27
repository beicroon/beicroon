package com.beicroon.construct.mq.consumer;

import com.beicroon.construct.json.template.JsonTemplate;
import jakarta.annotation.Resource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GenericMqConsumer<T> {

    @Resource
    private JsonTemplate jsonTemplate;

    @SuppressWarnings("unchecked")
    public Class<T> getClazz() {
        Type type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return (Class<T>) type;
    }

    public void consume(byte[] content) {
        this.consume(this.jsonTemplate.toObject(content, this.getClazz()));
    }

    public abstract void consume(T t);

}
