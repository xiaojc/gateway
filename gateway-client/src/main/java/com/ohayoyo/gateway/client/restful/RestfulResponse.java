package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayResponse;

import java.util.Map;

public class RestfulResponse implements GatewayResponse {

    private String statusCode;

    private String reasonPhrase;

    private Map<String, Object> responseHeaders;

    private Object responseEntity;

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

    public Map<String, Object> getResponseHeaders() {
        return responseHeaders;
    }

    public RestfulResponse setResponseHeaders(Map<String, Object> responseHeaders) {
        this.responseHeaders = responseHeaders;
        return this;
    }

    public Object getResponseEntity() {
        return responseEntity;
    }

    public RestfulResponse setResponseEntity(Object responseEntity) {
        this.responseEntity = responseEntity;
        return this;
    }

}
