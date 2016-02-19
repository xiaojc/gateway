package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayRequest;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @author 蓝明乐
 */
@SuppressWarnings("unchecked")
public class RestfulRequest<RequestBody> implements GatewayRequest<RequestBody> {

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
    public RestfulRequest<RequestBody> setSelect(String select) {
        this.select = select;
        return this;
    }

    @Override
    public Map<String, String> getRequestPathVariables() {
        return requestPathVariables;
    }

    @Override
    public RestfulRequest<RequestBody> setRequestPathVariables(Map<String, String> requestPathVariables) {
        this.requestPathVariables = requestPathVariables;
        return this;
    }

    @Override
    public MultiValueMap<String, String> getRequestQueries() {
        return requestQueries;
    }

    @Override
    public RestfulRequest<RequestBody> setRequestQueries(MultiValueMap<String, String> requestQueries) {
        this.requestQueries = requestQueries;
        return this;
    }

    @Override
    public MultiValueMap<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    @Override
    public RestfulRequest<RequestBody> setRequestHeaders(MultiValueMap<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
        return this;
    }

    @Override
    public RequestBody getRequestBody() {
        return requestBody;
    }

    @Override
    public RestfulRequest<RequestBody> setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

}
