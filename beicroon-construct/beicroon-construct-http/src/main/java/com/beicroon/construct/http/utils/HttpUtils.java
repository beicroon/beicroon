package com.beicroon.construct.http.utils;

import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.UrlUtils;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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

    public static com.beicroon.construct.http.entity.Http get(String url) {
        return HttpUtils.exchange(url, HttpMethod.GET, EmptyUtils.emptyMap(), EmptyUtils.emptyMap(), EmptyUtils.emptyMap());
    }

    public static com.beicroon.construct.http.entity.Http get(String url, Map<String, String> query) {
        return HttpUtils.exchange(url, HttpMethod.GET, query, EmptyUtils.emptyMap(), EmptyUtils.emptyMap());
    }

    public static com.beicroon.construct.http.entity.Http get(String url, Map<String, String> query, Map<String, String> headers) {
        return HttpUtils.exchange(url, HttpMethod.GET, query, EmptyUtils.emptyMap(), headers);
    }

    public static com.beicroon.construct.http.entity.Http post(String url) {
        return HttpUtils.exchange(url, HttpMethod.POST, EmptyUtils.emptyMap(), EmptyUtils.emptyMap(), EmptyUtils.emptyMap());
    }

    public static com.beicroon.construct.http.entity.Http post(String url, Map<String, ?> body) {
        return HttpUtils.exchange(url, HttpMethod.POST, EmptyUtils.emptyMap(), body, EmptyUtils.emptyMap());
    }

    public static com.beicroon.construct.http.entity.Http post(String url, Map<String, ?> body, Map<String, String> headers) {
        return HttpUtils.exchange(url, HttpMethod.POST, EmptyUtils.emptyMap(), body, headers);
    }

    public static com.beicroon.construct.http.entity.Http exchange(String url, HttpMethod method, Map<String, String> query, MultiValueMap<String, String> body, Map<String, String> headers) {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(getFullUrl(url, query)).build();

        ResponseEntity<byte[]> response = HttpUtils.generic().exchange(
                uri.toUri(), method, HttpUtils.getRequest(body, headers), byte[].class
        );

        com.beicroon.construct.http.entity.Http http = new com.beicroon.construct.http.entity.Http(uri.toUriString(), method.name(), headers, body);

        http.setStatus(response.getStatusCode().value());
        http.setResponse(response.getBody());

        return http;
    }

    public static com.beicroon.construct.http.entity.Http exchange(String url, HttpMethod method, Map<String, String> query, Map<String, ?> body, Map<String, String> headers) {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(getFullUrl(url, query)).build();

        ResponseEntity<byte[]> response = HttpUtils.generic().exchange(
                uri.toUri(), method, HttpUtils.getRequest(body, headers), byte[].class
        );

        com.beicroon.construct.http.entity.Http http = new com.beicroon.construct.http.entity.Http(uri.toUriString(), method.name(), headers, body);

        http.setResponse(response.getBody());
        http.setStatus(response.getStatusCode().value());

        return http;
    }

    public static HttpEntity<Map<String, ?>> getRequest(Map<String, ?> body, Map<String, String> headers) {
        if (!headers.containsKey(HttpHeaders.CONTENT_TYPE)) {
            if (body instanceof MultiValueMap) {
                headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            } else {
                headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            }
        }

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAll(headers);

        return new HttpEntity<>(body, httpHeaders);
    }

    public static RestTemplate generic() {
        RestTemplate template = new RestTemplate();

        template.setErrorHandler(new ResponseErrorHandler() {

            @Override
            public boolean hasError(@Nonnull ClientHttpResponse response) {
                return false;
            }

            @Override
            public void handleError(@Nonnull ClientHttpResponse response) {

            }

        });

        return template;
    }

}
