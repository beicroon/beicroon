package com.beicroon.starter.mq.producer;

import com.beicroon.construct.utils.EmptyUtils;
import org.springframework.stereotype.Component;

@Component
public class MqProducer {

    public <T> String send(String topic, String tag, String key, T body) {
        return EmptyUtils.emptyString();
    }

}
