package com.beicroon.construct.cache.template;

import java.time.Duration;

public interface CacheTemplate {

    void set(String key, String value);

    void set(String key, String value, Duration duration);

    String get(String key);

    void delete(String key);

}
