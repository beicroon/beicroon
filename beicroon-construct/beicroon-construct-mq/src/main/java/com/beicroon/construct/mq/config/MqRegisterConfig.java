package com.beicroon.construct.mq.config;

import com.beicroon.construct.mq.annotation.MqListener;
import com.beicroon.construct.mq.consumer.GenericMqConsumer;
import com.beicroon.construct.mq.register.IMqRegister;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.construct.utils.StringUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Configuration
public class MqRegisterConfig implements SmartInitializingSingleton, DisposableBean, ApplicationContextAware {

    @Resource
    private IMqRegister mqRegisterTemplate;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        // 注册生产者
        this.mqRegisterTemplate.registerProducer((GenericApplicationContext) this.applicationContext);

        Map<String, Object> listenerMap = this.applicationContext.getBeansWithAnnotation(MqListener.class);

        if (EmptyUtils.isEmpty(listenerMap)) {
            return;
        }

        // 将所有的消费者按照 topic 分组
        Map<String, List<Object>> listenerGroup = ListUtils.toMapList(listenerMap.values(), listener -> {
            if (!(listener instanceof GenericMqConsumer)) {
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

            Map<String, List<GenericMqConsumer<?>>> listenerTagMap = new HashMap<>();

            // 将消费者按照 tag 分组
            for (Object listener : consumerEntry.getValue()) {
                if (!(listener instanceof GenericMqConsumer<?> consumer)) {
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

            // 注册消费者
            this.mqRegisterTemplate.registerConsumer(
                    topic, StringUtils.join(listenerTagMap.keySet(), "||"), listenerTagMap
            );
        }
    }

    @Override
    public void destroy() {
        this.mqRegisterTemplate.destroy();
    }

}
