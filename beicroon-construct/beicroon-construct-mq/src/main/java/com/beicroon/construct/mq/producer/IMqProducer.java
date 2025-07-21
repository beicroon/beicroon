package com.beicroon.construct.mq.producer;

import com.beicroon.construct.mq.enums.MqDelayEnums;

public interface IMqProducer {

    default void send(String topic, String tag, String key, Object content) {
        this.send(topic, tag, key, content, null);
    }

    void send(String topic, String tag, String key, Object content, MqDelayEnums delay);

}
