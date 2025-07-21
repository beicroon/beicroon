package com.beicroon.construct.http.utils;

import com.beicroon.construct.http.entity.Http;
import com.beicroon.construct.http.enums.HttpMethodEnums;
import com.beicroon.construct.json.utils.JsonUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.Map;

@Slf4j
public final class HttpUtils {

    private HttpUtils() {

    }

    public static String getFullUrl(String url, Map<String, String> query) {
        StringBuilder fullUrl = new StringBuilder(url);

        if (EmptyUtils.isNotEmpty(query)) {
            if (fullUrl.indexOf("?") < 0) {
                fullUrl.append("?");
            }

            fullUrl.append(UrlUtils.query(query));
        }

        return fullUrl.toString();
    }

    public static Http get(String url) {
        return HttpUtils.exchange(url, HttpMethodEnums.GET, EmptyUtils.emptyMap(), EmptyUtils.emptyMap(), EmptyUtils.emptyMap());
    }

    public static Http get(String url, Map<String, String> query) {
        return HttpUtils.exchange(url, HttpMethodEnums.GET, query, EmptyUtils.emptyMap(), EmptyUtils.emptyMap());
    }

    public static Http get(String url, Map<String, String> query, Map<String, String> headers) {
        return HttpUtils.exchange(url, HttpMethodEnums.GET, query, EmptyUtils.emptyMap(), headers);
    }

    public static Http post(String url) {
        return HttpUtils.exchange(url, HttpMethodEnums.POST, EmptyUtils.emptyMap(), EmptyUtils.emptyMap(), EmptyUtils.emptyMap());
    }

    public static Http post(String url, Map<String, ?> body) {
        return HttpUtils.exchange(url, HttpMethodEnums.POST, EmptyUtils.emptyMap(), body, EmptyUtils.emptyMap());
    }

    public static Http post(String url, Map<String, ?> body, Map<String, String> headers) {
        return HttpUtils.exchange(url, HttpMethodEnums.POST, EmptyUtils.emptyMap(), body, headers);
    }

    public static Http exchange(String url, HttpMethodEnums method, Map<String, String> query, Map<String, ?> body, Map<String, String> headers) {
        String fullUrl = HttpUtils.getFullUrl(url, query);

        Http http = new Http(fullUrl, method.getName(), headers, body);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            ClassicHttpRequest request = HttpUtils.getRequest(fullUrl, method, body, headers);

            byte[] responseBytes = client.execute(request, response -> {
                http.setStatus(response.getCode());

                if (response.getEntity() == null) {
                    return new byte[0];
                }

                return EntityUtils.toByteArray(response.getEntity());
            });

            http.setResponse(responseBytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return http;
    }

    public static ClassicHttpRequest getRequest(String fullUrl, HttpMethodEnums method, Map<String, ?> body, Map<String, String> headers) {
        ClassicHttpRequest request;

        // GET
        if (HttpMethodEnums.GET.equals(method)) {
            request = new HttpGet(fullUrl);
        }
        // POST
        else if (HttpMethodEnums.POST.equals(method)) {
            request = new HttpPost(fullUrl);

            request.setEntity(new StringEntity(JsonUtils.toJson(body), ContentType.APPLICATION_JSON));
        }
        // 暂不实现其他
        else {
            throw new UnsupportedOperationException("不支持的请求方法：" + method.getName());
        }

        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");

        if (EmptyUtils.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }

        return request;
    }

}
