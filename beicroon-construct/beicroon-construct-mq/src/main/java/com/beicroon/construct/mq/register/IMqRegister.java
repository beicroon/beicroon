package com.beicroon.construct.mq.register;

import com.beicroon.construct.mq.consumer.GenericMqConsumer;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;
import java.util.Map;

public interface IMqRegister {

    void registerProducer(GenericApplicationContext applicationContext);

    void registerConsumer(String topic, String tag, Map<String, List<GenericMqConsumer<?>>> listenerMap);

    void destroy();

}
