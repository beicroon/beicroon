package com.beicroon.starter.mq.config;

import com.beicroon.construct.mq.producer.IMqProducer;
import com.beicroon.construct.rocketmq.producer.RocketMqProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Bean
    public IMqProducer mqProducer() {
        return new RocketMqProducer();
    }

}
