package com.beicroon.construct.rocketmq.register;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.mq.annotation.MqListener;
import com.beicroon.construct.mq.consumer.MqConsumer;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class RocketMqRegister implements SmartInitializingSingleton, DisposableBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private DefaultMQProducer producer;

    private final List<DefaultMQPushConsumer> consumers = new ArrayList<>();

    @Value("${spring.application.name}")
    private String appName;

    @Value("${mq.server-address:}")
    private String serverAddress;

    @Value("${mq.namespace:}")
    private String namespace;

    @Value("${mq.group-id:}")
    private String groupId;

    @Value("${mq.consumer.enable:true}")
    private boolean consumerEnable;

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        if (EmptyUtils.isNotEmpty(this.serverAddress)) {
            // 注册生产者
            ((GenericApplicationContext) this.applicationContext).registerBean(DefaultMQProducer.class, () -> {
                DefaultMQProducer producer = new DefaultMQProducer(this.appName);

                producer.setNamesrvAddr(this.serverAddress);
                producer.setNamespaceV2(this.namespace);

                try {
                    producer.start();
                } catch (MQClientException ex) {
                    throw new RuntimeException(ex);
                }

                producer.setRetryTimesWhenSendAsyncFailed(0);

                this.producer = producer;

                return producer;
            });

            // 关闭注册消费者
            if (!this.consumerEnable) {
                return;
            }

            // 注册消费者
            Map<String, Object> listenerMap = this.applicationContext.getBeansWithAnnotation(MqListener.class);

            if (EmptyUtils.isEmpty(listenerMap)) {
                return;
            }

            Map<String, List<Object>> listenerGroup = ListUtils.toMapList(listenerMap.values(), listener -> {
                if (!(listener instanceof MqConsumer)) {
                    return null;
                }

                MqListener annotation = listener.getClass().getAnnotation(MqListener.class);

                if (annotation == null) {
                    return null;
                }

                return annotation.topic();
            });

            for (Map.Entry<String, List<Object>> consumerEntry : listenerGroup.entrySet()) {
                String topic = consumerEntry.getKey();

                Map<String, List<MqConsumer<?>>> listenerTagMap = new HashMap<>();

                for (Object listener : consumerEntry.getValue()) {
                    if (!(listener instanceof MqConsumer<?> consumer)) {
                        continue;
                    }

                    MqListener annotation = consumer.getClass().getAnnotation(MqListener.class);

                    for (String tag : annotation.tags()) {
                        if (!listenerTagMap.containsKey(tag)) {
                            listenerTagMap.put(tag, new ArrayList<>());
                        }

                        listenerTagMap.get(tag).add(consumer);
                    }
                }

                String tag = ListUtils.join(listenerTagMap.keySet(), "||");

                DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.appName);

                consumer.setNamesrvAddr(this.serverAddress);
                consumer.setNamespaceV2(this.namespace);
                consumer.setConsumerGroup(this.groupId);
                consumer.registerMessageListener((MessageListenerConcurrently) (messages, consumeConcurrentlyContext) -> {
                    for (MessageExt message : messages) {
                        try {
                            List<MqConsumer<?>> tagConsumers = listenerTagMap.get(message.getTags());

                            for (MqConsumer<?> tagConsumer : tagConsumers) {
                                try {
                                    tagConsumer.consume(message.getBody());
                                } catch (Exception ex) {
                                    log.error(
                                            "MQ消息消费失败:topic={}, msgId={}, content={}, message={}",
                                            message.getTopic(),
                                            message.getMsgId(),
                                            new String(message.getBody(), SystemConstant.CHARSET),
                                            ex.getMessage(),
                                            ex
                                    );

                                    throw ex;
                                }
                            }
                        } catch (Exception ex) {
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
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
        }
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
