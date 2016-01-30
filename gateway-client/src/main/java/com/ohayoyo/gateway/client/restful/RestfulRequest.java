package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayRequest;

import java.lang.reflect.Type;
import java.util.Map;

public class RestfulRequest implements GatewayRequest {

    private String select;

    private Map<String, Object> requestPathVariables;

    @Deprecated
    private Map<String, Object> requestParameters;

    private Map<String, Object> requestQueries;

    private Map<String, Object> requestHeaders;

    private Object requestEntity;

    private Type responseType;

    public String getSelect() {
        return select;
    }

    public RestfulRequest setSelect(String select) {
        this.select = select;
        return this;
    }

    public Map<String, Object> getRequestPathVariables() {
        return requestPathVariables;
    }

    public RestfulRequest setRequestPathVariables(Map<String, Object> requestPathVariables) {
        this.requestPathVariables = requestPathVariables;
        return this;
    }

    @Deprecated
    public Map<String, Object> getRequestParameters() {
        return requestParameters;
    }

    @Deprecated
    public RestfulRequest setRequestParameters(Map<String, Object> requestParameters) {
        this.requestParameters = requestParameters;
        return this;
    }

    public Map<String, Object> getRequestQueries() {
        return requestQueries;
    }

    public RestfulRequest setRequestQueries(Map<String, Object> requestQueries) {
        this.requestQueries = requestQueries;
        return this;
    }

    public Map<String, Object> getRequestHeaders() {
        return requestHeaders;
    }

    public RestfulRequest setRequestHeaders(Map<String, Object> requestHeaders) {
        this.requestHeaders = requestHeaders;
        return this;
    }

    public Object getRequestEntity() {
        return requestEntity;
    }

    public RestfulRequest setRequestEntity(Object requestEntity) {
        this.requestEntity = requestEntity;
        return this;
    }

    public Type getResponseType() {
        return responseType;
    }

    public RestfulRequest setResponseType(Type responseType) {
        this.responseType = responseType;
        return this;
    }

    @Override
    public GatewayRequest setResponseType(Class<?> responseType) {
        return setResponseType((Type) responseType);
    }
}
