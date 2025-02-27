package com.beicroon.construct.cache.template;

public interface CacheTemplate {

    void set(String key, String value);

    String get(String key);

    void delete(String key);

}
