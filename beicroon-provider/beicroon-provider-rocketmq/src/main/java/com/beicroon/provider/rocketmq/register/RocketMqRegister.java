package com.beicroon.provider.rocketmq.register;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.mq.consumer.GenericMqConsumer;
import com.beicroon.construct.mq.register.IMqRegister;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.provider.rocketmq.property.RocketMqProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.support.GenericApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class RocketMqRegister implements IMqRegister {

    private DefaultMQProducer producer;

    private final RocketMqProperty property;

    private final List<DefaultMQPushConsumer> consumers = new ArrayList<>();

    public RocketMqRegister(RocketMqProperty property) {
        this.property = property;
    }

    @Override
    public void registerProducer(GenericApplicationContext applicationContext) {
        if (EmptyUtils.isEmpty(this.property.getServerAddress())) {
            log.warn("未配置 RocketMQ 的服务地址，RocketMQ 生产者将不会被注册");

            return;
        }

        applicationContext.registerBean(DefaultMQProducer.class, () -> {
            DefaultMQProducer producer = new DefaultMQProducer(this.property.getName());

            producer.setNamesrvAddr(this.property.getServerAddress());
            producer.setNamespaceV2(this.property.getNamespace());

            try {
                producer.start();
            } catch (MQClientException ex) {
                throw new RuntimeException(ex);
            }

            producer.setRetryTimesWhenSendAsyncFailed(0);

            this.producer = producer;

            return producer;

        });
    }

    @Override
    public void registerConsumer(String topic, String tag, Map<String, List<GenericMqConsumer<?>>> listenerMap) {
        if (EmptyUtils.isEmpty(this.property.getServerAddress()) || !this.property.isConsumerEnabled()) {
            return;
        }

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.property.getName());

        consumer.setNamesrvAddr(this.property.getServerAddress());
        consumer.setNamespaceV2(this.property.getNamespace());
        consumer.setConsumerGroup(this.property.getGroupId());
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, consumeConcurrentlyContext) -> {
            for (MessageExt message : messages) {
                List<GenericMqConsumer<?>> listeners = listenerMap.get(message.getTags());

                if (EmptyUtils.isEmpty(listeners)) {
                    continue;
                }

                for (GenericMqConsumer<?> listener : listeners) {
                    try {
                        listener.consume(message.getBody());
                    } catch (Exception ex) {
                        log.error(
                                "MQ消息消费失败:topic={}, msgId={}, content={}, message={}",
                                message.getTopic(),
                                message.getMsgId(),
                                new String(message.getBody(), SystemConstant.CHARSET),
                                ex.getMessage(),
                                ex
                        );

                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        try {
            consumer.subscribe(topic, tag);
            consumer.start();
        } catch (MQClientException ex) {
            throw new RuntimeException(ex);
        }

        this.consumers.add(consumer);
    }

    @Override
    public void destroy() {
        try {
            if (this.producer != null) {
                this.producer.shutdown();
            }

            for (DefaultMQPushConsumer consumer : this.consumers) {
                consumer.shutdown();
            }
        } catch (Exception ignore) {

        }
    }

}
