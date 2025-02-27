package com.beicroon.provider.redis.template;

import com.beicroon.construct.cache.template.CacheTemplate;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCacheTemplate implements CacheTemplate {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void set(String key, String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        this.redisTemplate.delete(key);
    }

}
