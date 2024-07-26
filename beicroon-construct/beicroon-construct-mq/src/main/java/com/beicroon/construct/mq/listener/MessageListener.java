package com.beicroon.construct.mq.listener;

import com.beicroon.construct.mq.consumer.MqConsumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MessageListener {

    private final MqConsumer<?> consumer;

    public MessageListener(MqConsumer<?> consumer) {
        this.consumer = consumer;
    }

    protected void onConsume(String topic, String msgId, byte[] content) {
        try {
            this.consumer.consume(content);
        } catch (Exception ex) {
            log.error("MQ消息消费失败:topic={}, msgId={}, content={}, message={}", topic, msgId, content, ex.getMessage(), ex);

            throw ex;
        }
    }

}
