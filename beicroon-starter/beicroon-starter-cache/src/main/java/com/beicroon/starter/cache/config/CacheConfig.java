package com.beicroon.starter.cache.config;

import com.beicroon.construct.cache.template.CacheTemplate;
import com.beicroon.provider.redis.template.RedisCacheTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheTemplate cacheTemplate() {
        return new RedisCacheTemplate();
    }

}
