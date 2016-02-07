package com.ohayoyo.gateway.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

public class DefaultResponseEntityBuilder<ResponseBody> extends AbstractHttpEntityBuilder<ResponseBody, ResponseEntity<ResponseBody>> {

    private HttpStatus httpStatus;

    public static <ResponseBody> DefaultResponseEntityBuilder<ResponseBody> newInstance() {
        return new DefaultResponseEntityBuilder<ResponseBody>();
    }

    public DefaultResponseEntityBuilder<ResponseBody> headers(Map<String, List<String>> headers) {
        return (DefaultResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public DefaultResponseEntityBuilder<ResponseBody> headers(MultiValueMap<String, String> headers) {
        return (DefaultResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public DefaultResponseEntityBuilder<ResponseBody> headers(HttpHeaders headers) {
        return (DefaultResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public DefaultResponseEntityBuilder<ResponseBody> body(ResponseBody responseBody) {
        return (DefaultResponseEntityBuilder<ResponseBody>) super.body(responseBody);
    }

    public DefaultResponseEntityBuilder<ResponseBody> httpStatus(HttpStatus httpStatus) {
        Assert.notNull(httpStatus);
        this.httpStatus = httpStatus;
        return this;
    }

    protected HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public ResponseEntity<ResponseBody> build() {
        HttpStatus httpStatus = this.getHttpStatus();
        HttpHeaders httpHeaders = this.getHeaders();
        ResponseBody responseBody = this.getBody();
        ResponseEntity<ResponseBody> responseEntity = new ResponseEntity<ResponseBody>(responseBody, httpHeaders, httpStatus);
        return responseEntity;
    }

}
