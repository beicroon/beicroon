package com.beicroon.starter.mq.config;

import com.beicroon.construct.mq.register.IMqRegister;
import com.beicroon.provider.rocketmq.property.RocketMqProperty;
import com.beicroon.provider.rocketmq.register.RocketMqRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RocketMqProperty.class})
//@EnableConfigurationProperties(RocketMqProperty.class)
public class MqConfig {

    @Bean
    public IMqRegister mqRegisterTemplate(RocketMqProperty property) {
        return new RocketMqRegister(property);
    }

}
