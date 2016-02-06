package com.ohayoyo.gateway.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

public interface HttpResponseEntityBuilder<ResponseBody> extends HttpEntityBuilder<ResponseBody, ResponseEntity<ResponseBody>> {

    HttpResponseEntityBuilder<ResponseBody> httpStatus(HttpStatus httpStatus);

    @Override
    HttpResponseEntityBuilder<ResponseBody> headers(Map<String, List<String>> headers);

    @Override
    HttpResponseEntityBuilder<ResponseBody> headers(MultiValueMap<String, String> headers);

    @Override
    HttpResponseEntityBuilder<ResponseBody> headers(HttpHeaders headers);

    @Override
    HttpResponseEntityBuilder<ResponseBody> body(ResponseBody responseBody);
}
