package com.beicroon.module.map.app.helper;

import com.beicroon.construct.utils.HashUtils;
import com.beicroon.construct.utils.UrlUtils;

import java.util.Map;

public final class DigestHelper {

    private DigestHelper() {

    }

    public static String gaode(Map<String, String> query, String secret) {
        String queryString = UrlUtils.query(query, false, true);

        String value = String.format("%s%s", queryString, secret);

        return HashUtils.md5(value);
    }

    public static String baidu(String uri, Map<String, String> query, String secret) {
        String queryString = UrlUtils.query(query, false, false);

        String value = UrlUtils.encode(String.format("%s?%s%s", uri, queryString, secret));

        return HashUtils.md5(value);
    }

    public static String tencent(String uri, Map<String, String> query, String secret) {
        String queryString = UrlUtils.query(query, false, true);

        String value = String.format("%s?%s%s", uri, queryString, secret);

        return HashUtils.md5(value);
    }

}
