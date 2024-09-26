package com.beicroon.starter.cache.template;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CacheTemplate {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        this.redisTemplate.delete(key);
    }

}
