package com.beicroon.construct.http.utils;

import com.beicroon.construct.http.entity.Http;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.UrlUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
public final class HttpUtils {

    private static final RestTemplate REST_TEMPLATE;

    static {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(30);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        REST_TEMPLATE = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));

        REST_TEMPLATE.setErrorHandler(new ResponseErrorHandler() {

            @Override
            public boolean hasError(@NonNull ClientHttpResponse response) {
                return false;
            }

            @Override
            public void handleError(@NonNull ClientHttpResponse response) {

            }

        });
    }

    private HttpUtils() {

    }

    @SuppressWarnings("unchecked")
    public static MultiValueMap<String, ?> getMultiValueMap(Map<String, ?> map) {
        return (MultiValueMap<String, ?>) map;
    }

    public static String getFormDataString(MultiValueMap<String, ?> formData) {
        StringBuilder sb = new StringBuilder();

        formData.forEach((key, values) -> {
            if (values == null || values.isEmpty()) {
                sb.append(key).append("=").append("&");

                return;
            }

            if (values.size() == 1) {
                sb.append(key).append("=").append(values.get(0)).append("&");

                return;
            }

            for (Object value : values) {
                sb.append(key).append("[]=").append(value).append("&");
            }
        });

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
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
        return HttpUtils.exchange(url, HttpMethod.GET, EmptyUtils.emptyMap(), EmptyUtils.emptyMap(), EmptyUtils.emptyMap());
    }

    public static Http get(String url, Map<String, String> query) {
        return HttpUtils.exchange(url, HttpMethod.GET, query, EmptyUtils.emptyMap(), EmptyUtils.emptyMap());
    }

    public static Http get(String url, Map<String, String> query, Map<String, String> headers) {
        return HttpUtils.exchange(url, HttpMethod.GET, query, EmptyUtils.emptyMap(), headers);
    }

    public static Http post(String url) {
        return HttpUtils.exchange(url, HttpMethod.POST, EmptyUtils.emptyMap(), EmptyUtils.emptyMap(), EmptyUtils.emptyMap());
    }

    public static Http post(String url, Map<String, ?> body) {
        return HttpUtils.exchange(url, HttpMethod.POST, EmptyUtils.emptyMap(), body, EmptyUtils.emptyMap());
    }

    public static Http post(String url, Map<String, ?> body, Map<String, String> headers) {
        return HttpUtils.exchange(url, HttpMethod.POST, EmptyUtils.emptyMap(), body, headers);
    }

    public static Http exchange(String url, HttpMethod method, Map<String, String> query, Map<String, ?> body, Map<String, String> headers) {
        UriComponents uri = UriComponentsBuilder.fromUriString(HttpUtils.getFullUrl(url, query)).build();

        ResponseEntity<byte[]> response = HttpUtils.getTemplate().exchange(
                uri.toUri(), method, HttpUtils.getRequest(body, headers), byte[].class
        );

        Http http = new Http(uri.toUriString(), method.name(), headers, body);

        http.setResponse(response.getBody());
        http.setStatus(response.getStatusCode().value());

        return http;
    }

    public static HttpEntity<Map<String, ?>> getRequest(Map<String, ?> body, Map<String, String> headers) {
        if (body instanceof MultiValueMap) {
            headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        } else {
            headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        }

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAll(headers);

        return new HttpEntity<>(body, httpHeaders);
    }

    public static RestTemplate getTemplate() {
        return REST_TEMPLATE;
    }

}
