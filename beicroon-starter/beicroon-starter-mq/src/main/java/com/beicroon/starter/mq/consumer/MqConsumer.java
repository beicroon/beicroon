package com.beicroon.starter.mq.consumer;

import com.beicroon.construct.jackson.utils.JsonUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MqConsumer<T> {

    public Class<T> getClazz() {
        Type type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return (Class<T>) type;
    }

    public void consume(byte[] content) {
        this.consume(JsonUtils.toObject(content, this.getClazz()));
    }

    public abstract void consume(T t);

}
