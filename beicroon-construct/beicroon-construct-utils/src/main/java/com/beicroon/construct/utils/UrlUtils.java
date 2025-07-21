package com.beicroon.construct.utils;

import com.beicroon.construct.constant.SystemConstant;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

public final class UrlUtils {

    private UrlUtils() {

    }

    public static String query(Map<String, String> query) {
        return UrlUtils.query(query, false, false);
    }

    public static String query(Map<String, String> query, boolean encode) {
        return UrlUtils.query(query, encode, false);
    }

    public static String query(Map<String, String> query, boolean encode, boolean sort) {
        StringBuilder sb = new StringBuilder();

        Iterator<String> keys;

        if (sort) {
            keys = query.keySet().stream().sorted().iterator();
        } else {
            keys = query.keySet().iterator();
        }

        while (keys.hasNext()) {
            String key = keys.next();
            String value = query.get(key);

            sb.append(key);
            sb.append("=");

            if (encode) {
                sb.append(UrlUtils.encode(value));
            } else {
                sb.append(value);
            }

            if (keys.hasNext()) {
                sb.append("&");
            }
        }

        return sb.toString();
    }

    public static String encode(String value) {
        return UrlUtils.encode(value, SystemConstant.CHARSET);
    }

    public static String encode(String value, Charset charset) {
        if (value == null) {
            return EmptyUtils.emptyString();
        }

        return URLEncoder.encode(value, charset);
    }

    public static String decode(String value) {
        return UrlUtils.decode(value, SystemConstant.CHARSET);
    }

    public static String decode(String value, Charset charset) {
        if (value == null) {
            return EmptyUtils.emptyString();
        }

        return URLDecoder.decode(value, charset);
    }

}
