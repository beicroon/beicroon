package com.beicroon.construct.rocketmq.listener;

import com.beicroon.construct.mq.consumer.MqConsumer;
import com.beicroon.construct.mq.listener.MessageListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

@Slf4j
public class RocketMqListener extends MessageListener implements MessageListenerConcurrently {

    public RocketMqListener(MqConsumer<?> consumer) {
        super(consumer);
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt message : messages) {
            try {
                this.onConsume(message.getTopic(), message.getMsgId(), message.getBody());
            } catch (Exception ex) {
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
