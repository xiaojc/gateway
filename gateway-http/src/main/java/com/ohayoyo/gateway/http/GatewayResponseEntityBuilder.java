package com.ohayoyo.gateway.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

public class GatewayResponseEntityBuilder<ResponseBody> extends AbstractHttpEntityBuilder<ResponseBody, ResponseEntity<ResponseBody>> {

    private HttpStatus httpStatus;

    public static <ResponseBody> GatewayResponseEntityBuilder<ResponseBody> newInstance() {
        return new GatewayResponseEntityBuilder<ResponseBody>();
    }

    public GatewayResponseEntityBuilder<ResponseBody> headers(Map<String, List<String>> headers) {
        return (GatewayResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public GatewayResponseEntityBuilder<ResponseBody> headers(MultiValueMap<String, String> headers) {
        return (GatewayResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public GatewayResponseEntityBuilder<ResponseBody> headers(HttpHeaders headers) {
        return (GatewayResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public GatewayResponseEntityBuilder<ResponseBody> body(ResponseBody responseBody) {
        return (GatewayResponseEntityBuilder<ResponseBody>) super.body(responseBody);
    }

    public GatewayResponseEntityBuilder<ResponseBody> httpStatus(HttpStatus httpStatus) {
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
