package com.ohayoyo.gateway.http.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * @author 蓝明乐
 */
public class GatewayHttpResponseEntityBuilder<ResponseBody> extends GatewayHttpEntityBuilder<ResponseBody, ResponseEntity<ResponseBody>> {

    public static final Logger LOGGER = LoggerFactory.getLogger(GatewayHttpResponseEntityBuilder.class);

    private HttpStatus httpStatus;

    public static <ResponseBody> GatewayHttpResponseEntityBuilder<ResponseBody> newInstance() {
        return new GatewayHttpResponseEntityBuilder<ResponseBody>();
    }

    public GatewayHttpResponseEntityBuilder<ResponseBody> headers(Map<String, List<String>> headers) {
        return (GatewayHttpResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public GatewayHttpResponseEntityBuilder<ResponseBody> headers(MultiValueMap<String, String> headers) {
        return (GatewayHttpResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public GatewayHttpResponseEntityBuilder<ResponseBody> headers(HttpHeaders headers) {
        return (GatewayHttpResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public GatewayHttpResponseEntityBuilder<ResponseBody> body(ResponseBody responseBody) {
        return (GatewayHttpResponseEntityBuilder<ResponseBody>) super.body(responseBody);
    }

    public GatewayHttpResponseEntityBuilder<ResponseBody> httpStatus(HttpStatus httpStatus) {
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
