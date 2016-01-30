package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.AbstractGatewayScope;
import com.ohayoyo.gateway.client.core.GatewayEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class RestfulEntity extends AbstractGatewayScope implements GatewayEntity {

    private HttpStatus httpStatus;

    private HttpHeaders httpHeaders;

    private Object httpData;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public RestfulEntity setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    @Override
    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public RestfulEntity setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
        return this;
    }

    @Override
    public Object getHttpData() {
        return httpData;
    }

    public RestfulEntity setHttpData(Object httpData) {
        this.httpData = httpData;
        return this;
    }

}
