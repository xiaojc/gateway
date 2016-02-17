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
public class HttpGatewayResponseEntityBuilder<ResponseBody> extends HttpGatewayEntityBuilder<ResponseBody, ResponseEntity<ResponseBody>> {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGatewayResponseEntityBuilder.class);

    private HttpStatus httpStatus;

    public static <ResponseBody> HttpGatewayResponseEntityBuilder<ResponseBody> newInstance() {
        return new HttpGatewayResponseEntityBuilder<ResponseBody>();
    }

    public HttpGatewayResponseEntityBuilder<ResponseBody> headers(Map<String, List<String>> headers) {
        return (HttpGatewayResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public HttpGatewayResponseEntityBuilder<ResponseBody> headers(MultiValueMap<String, String> headers) {
        return (HttpGatewayResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public HttpGatewayResponseEntityBuilder<ResponseBody> headers(HttpHeaders headers) {
        return (HttpGatewayResponseEntityBuilder<ResponseBody>) super.headers(headers);
    }

    public HttpGatewayResponseEntityBuilder<ResponseBody> body(ResponseBody responseBody) {
        return (HttpGatewayResponseEntityBuilder<ResponseBody>) super.body(responseBody);
    }

    public HttpGatewayResponseEntityBuilder<ResponseBody> httpStatus(HttpStatus httpStatus) {
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
