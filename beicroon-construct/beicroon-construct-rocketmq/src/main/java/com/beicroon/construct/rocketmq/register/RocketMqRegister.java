package com.beicroon.construct.rocketmq.register;

import com.beicroon.construct.mq.annotation.MqListener;
import com.beicroon.construct.mq.consumer.MqConsumer;
import com.beicroon.construct.rocketmq.listener.RocketMqListener;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Value("${mq.server-addr:}")
    private String serverAddr;

    @Value("${mq.consumer.enable:true}")
    private boolean consumerEnable;

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        if (EmptyUtils.isNotEmpty(this.serverAddr)) {
            // 注册生产者
            ((GenericApplicationContext) this.applicationContext).registerBean(DefaultMQProducer.class, () -> {
                DefaultMQProducer producer = new DefaultMQProducer(this.appName);

                producer.setNamesrvAddr(this.serverAddr);

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

//            Map<String, List<Object>> listenerGroup = ListUtils.toMapList(listenerMap.values(), listener -> {
//                if (!(listener instanceof MqConsumer)) {
//                    return null;
//                }
//
//                MqListener annotation = listener.getClass().getAnnotation(MqListener.class);
//
//                if (annotation == null) {
//                    return null;
//                }
//
//                return annotation.topic();
//            });
//
//            for (Map.Entry<String, List<Object>> consumerEntry : listenerGroup.entrySet()) {
//                String topic = consumerEntry.getKey();
//
//                Set<String[]> tags = ListUtils.toSet(consumerEntry.getValue(), consumer -> {
//                    MqListener annotation = consumer.getClass().getAnnotation(MqListener.class);
//
//                    if (annotation == null) {
//                        return null;
//                    }
//
//                    return annotation.tags();
//                });
//
//                String tag = tags.stream()
//                        .flatMap(Arrays::stream)
//                        .collect(Collectors.joining("||"));
//
//                DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.appName);
//
//                consumer.setNamesrvAddr(this.serverAddr);
//                consumer.registerMessageListener(new RocketMqListener((MqConsumer<?>) new MqConsumer<>() {
//
//                }));
//
//                try {
//                    consumer.subscribe(topic, tag);
//                    consumer.start();
//                } catch (MQClientException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//                this.consumers.add(consumer);
//            }

            for (Map.Entry<String, Object> listenerEntry : listenerMap.entrySet()) {
                Object listener = listenerEntry.getValue();

                if (!(listener instanceof MqConsumer)) {
                    continue;
                }

                MqListener annotation = listener.getClass().getAnnotation(MqListener.class);

                if (annotation == null) {
                    continue;
                }

                DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.appName);

                consumer.setNamesrvAddr(this.serverAddr);
                consumer.registerMessageListener(new RocketMqListener((MqConsumer<?>) listener));

                try {
                    consumer.subscribe(annotation.topic(), ListUtils.join(Arrays.asList(annotation.tags()), "||"));
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
