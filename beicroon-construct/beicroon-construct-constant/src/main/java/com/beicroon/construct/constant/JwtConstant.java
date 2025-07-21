package com.beicroon.construct.constant;

public interface JwtConstant {

    String SECRET = "ecb656c0707efa8ced460394ac942435";

    String ISSUER = "com.beicroon.jwt";

    String PREFIX = "Bearer ";

    String SUFFIX = " ==--";

    long DURATION = 1000 * 60 * 60 * 24 * 7;

    String CACHE_LOGIN_KEY = "auth:login:%s:%s";

}
