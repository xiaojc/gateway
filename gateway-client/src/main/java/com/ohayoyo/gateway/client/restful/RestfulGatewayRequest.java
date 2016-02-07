package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.GatewayRequest;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class RestfulGatewayRequest<RequestBody> implements GatewayRequest<RequestBody> {

    private String select;

    private Map<String, String> requestPathVariables;

    private MultiValueMap<String, String> requestQueries;

    private MultiValueMap<String, String> requestHeaders;

    private RequestBody requestBody;

    @Override
    public String getSelect() {
        return select;
    }

    @Override
    public RestfulGatewayRequest<RequestBody> setSelect(String select) {
        this.select = select;
        return this;
    }

    @Override
    public Map<String, String> getRequestPathVariables() {
        return requestPathVariables;
    }

    @Override
    public RestfulGatewayRequest<RequestBody> setRequestPathVariables(Map<String, String> requestPathVariables) {
        this.requestPathVariables = requestPathVariables;
        return this;
    }

    @Override
    public MultiValueMap<String, String> getRequestQueries() {
        return requestQueries;
    }

    @Override
    public RestfulGatewayRequest<RequestBody> setRequestQueries(MultiValueMap<String, String> requestQueries) {
        this.requestQueries = requestQueries;
        return this;
    }

    @Override
    public MultiValueMap<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    @Override
    public RestfulGatewayRequest<RequestBody> setRequestHeaders(MultiValueMap<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
        return this;
    }

    @Override
    public RequestBody getRequestBody() {
        return requestBody;
    }

    @Override
    public RestfulGatewayRequest<RequestBody> setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

}
