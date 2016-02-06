package com.ohayoyo.gateway.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;
import java.util.Map;

public interface HttpRequestEntityBuilder<RequestBody> extends HttpEntityBuilder<RequestBody,RequestEntity<RequestBody>> {

    HttpRequestEntityBuilder<RequestBody> httpMethod(HttpMethod httpMethod);

    HttpRequestEntityBuilder<RequestBody> httpMethod(String httpMethod);

    HttpRequestEntityBuilder<RequestBody> url(URI uri);

    @Override
    HttpRequestEntityBuilder<RequestBody> headers(Map<String, List<String>> headers);

    @Override
    HttpRequestEntityBuilder<RequestBody> headers(MultiValueMap<String, String> headers);

    @Override
    HttpRequestEntityBuilder<RequestBody> headers(HttpHeaders headers);

    @Override
    HttpRequestEntityBuilder<RequestBody> body(RequestBody requestBody);

}
