package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayResponse;
import org.springframework.util.MultiValueMap;

public class RestfulResponse<T> implements GatewayResponse<T> {

    private String statusCode;

    private String reasonPhrase;

    private MultiValueMap<String, String> responseHeaders;

    private T responseEntity;

    public String getStatusCode() {
        return statusCode;
    }

    public RestfulResponse setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public RestfulResponse setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return this;
    }

    public MultiValueMap<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public RestfulResponse setResponseHeaders(MultiValueMap<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
        return this;
    }

    public T getResponseEntity() {
        return responseEntity;
    }

    public RestfulResponse setResponseEntity(T responseEntity) {
        this.responseEntity = responseEntity;
        return this;
    }

}
