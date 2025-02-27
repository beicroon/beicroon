package com.beicroon.starter.json.config;

import com.beicroon.construct.json.template.JsonTemplate;
import com.beicroon.provider.jackson.template.JacksonJsonTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

    @Bean
    public JsonTemplate jsonTemplate() {
        return new JacksonJsonTemplate();
    }

}
