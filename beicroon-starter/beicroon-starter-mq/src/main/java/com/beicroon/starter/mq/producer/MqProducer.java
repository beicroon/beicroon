package com.beicroon.starter.mq.producer;

import com.beicroon.construct.mq.enums.MqDelayEnums;
import com.beicroon.construct.mq.producer.IMqProducer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class MqProducer {

    @Resource
    private IMqProducer producer;

    public void send(String topic, String tag, String key, Object content) {
        this.producer.send(topic, tag, key, content, null);
    }

    public void send(String topic, String tag, String key, Object content, MqDelayEnums delay) {
        this.producer.send(topic, tag, key, content, delay);
    }

}
