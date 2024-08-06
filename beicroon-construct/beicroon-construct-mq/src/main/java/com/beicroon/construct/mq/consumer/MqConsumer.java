package com.beicroon.construct.mq.consumer;

import com.beicroon.construct.json.utils.JsonUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MqConsumer<T> {

    @SuppressWarnings("unchecked")
    public Class<T> getClazz() {
        Type type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return (Class<T>) type;
    }

    public void consume(byte[] content) {
        this.consume(JsonUtils.toObject(content, this.getClazz()));
    }

    public abstract void consume(T t);

}
