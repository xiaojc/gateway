package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayRequest;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class RestfulRequest implements GatewayRequest {

    private String select;

    private Map<String, String> requestPathVariables;

    @Deprecated
    private MultiValueMap<String, String> requestParameters;

    private MultiValueMap<String, String> requestQueries;

    private MultiValueMap<String, String> requestHeaders;

    private Object requestEntity;

    public String getSelect() {
        return select;
    }

    public RestfulRequest setSelect(String select) {
        this.select = select;
        return this;
    }

    public Map<String, String> getRequestPathVariables() {
        return requestPathVariables;
    }

    public RestfulRequest setRequestPathVariables(Map<String, String> requestPathVariables) {
        this.requestPathVariables = requestPathVariables;
        return this;
    }

    @Deprecated
    public MultiValueMap<String, String> getRequestParameters() {
        return requestParameters;
    }

    @Deprecated
    public RestfulRequest setRequestParameters(MultiValueMap<String, String> requestParameters) {
        this.requestParameters = requestParameters;
        return this;
    }

    public MultiValueMap<String, String> getRequestQueries() {
        return requestQueries;
    }

    public RestfulRequest setRequestQueries(MultiValueMap<String, String> requestQueries) {
        this.requestQueries = requestQueries;
        return this;
    }

    public MultiValueMap<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public RestfulRequest setRequestHeaders(MultiValueMap<String, String> requestHeaders) {
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

}
