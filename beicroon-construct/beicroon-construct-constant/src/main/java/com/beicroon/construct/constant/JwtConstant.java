package com.beicroon.construct.constant;

public interface JwtConstant {

    String SECRET = "ecb656c0707efa8ced460394ac942435";

    String ISSUER = SystemConstant.PACKAGE_BASE + ".jwt";

    String PREFIX = "Bearer ";

    String SUFFIX = " ==--";

    long DURATION = 1000 * 60 * 60 * 24 * 7;

    String HOME_LOGIN_KEY = "auth:login:home:%s";

    String ADMIN_LOGIN_KEY = "auth:login:admin:%s";

}
