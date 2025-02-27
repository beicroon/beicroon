package com.beicroon.provider.redis.config;

import com.beicroon.provider.jackson.utils.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableCaching
@Configuration
@EnableTransactionManagement
public class RedisCacheConfig {

    private final static String SEPARATION = ":";

    private final static int ENTRY_TTL_MAX = 300;

    private final static int ENTRY_TTL_MIN = 30;

    private final static int EXPIRE_AFTER_WRITE = 10;

    private final static int MAXIMUM_SIZE = 1000;

    @Value("${spring.application.name}")
    private String appName;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    private final static GenericJackson2JsonRedisSerializer REDIS_SERIALIZER;

    static {
        ObjectMapper mapper = JacksonUtils.getMapper();

        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        REDIS_SERIALIZER = new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    @Primary
    public CacheManager cacheManager() {
        RedisCacheConfiguration config = RedisCacheConfiguration
                .defaultCacheConfig()
                .computePrefixWith(cacheName -> this.appName + SEPARATION + cacheName + SEPARATION)
                .entryTtl(Duration.ofSeconds(ENTRY_TTL_MAX).plusSeconds(new Random().nextInt(ENTRY_TTL_MIN)))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(REDIS_SERIALIZER));

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(this.redisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager();

        manager.setCaffeine(
                Caffeine.newBuilder()
                        .expireAfterWrite(EXPIRE_AFTER_WRITE, TimeUnit.MINUTES)
                        .maximumSize(MAXIMUM_SIZE)
        );

        return manager;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            String keyPrefix = this.appName + SEPARATION + target.getClass().getSimpleName() + SEPARATION + method.getName();

            String keyParams = Stream.of(params)
                    .map(String::valueOf)
                    .collect(Collectors.joining(SEPARATION));

            return keyPrefix + SEPARATION + keyParams;
        };
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        RedisSerializationContext.SerializationPair<?> sp = RedisSerializationContext.SerializationPair
                .fromSerializer(RedisSerializer.json());

        return RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(sp);
    }

    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        return REDIS_SERIALIZER;
    }

    @Bean
    public RedisTemplate<String, ?> redisTemplate() {
        RedisTemplate<String, ?> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(REDIS_SERIALIZER);
        redisTemplate.setHashValueSerializer(REDIS_SERIALIZER);
        redisTemplate.setConnectionFactory(this.redisConnectionFactory);

        return redisTemplate;
    }

}
