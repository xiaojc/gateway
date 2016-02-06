package com.ohayoyo.gateway.http;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

public interface HttpEntityBuilder<Body,HttpEntity> {

    HttpEntityBuilder<Body,HttpEntity> headers(Map<String, List<String>> headers);

    HttpEntityBuilder<Body,HttpEntity> headers(MultiValueMap<String, String> headers);

    HttpEntityBuilder<Body,HttpEntity> headers(HttpHeaders headers);

    HttpEntityBuilder<Body,HttpEntity> body(Body body);

    HttpEntity build() ;

}
