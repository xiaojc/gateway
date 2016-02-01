package com.ohayoyo.gateway.client.components;

import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayResponse;
import com.ohayoyo.gateway.client.core.GatewayWrapper;
import com.ohayoyo.gateway.client.restful.RestfulResponse;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class GatewayWrapperEntity<T> extends AbstractGatewayComponent<GatewayWrapper<T>> implements GatewayWrapper<T> {

    private Class<T> responseType;

    private T responseResult;

    private ClientHttpRequest clientHttpRequest;

    private ClientHttpResponse clientHttpResponse;

    public GatewayWrapperEntity(Class<T> responseType, T responseResult, ClientHttpRequest clientHttpRequest, ClientHttpResponse clientHttpResponse) {
        this.responseType = responseType;
        this.responseResult = responseResult;
        this.clientHttpRequest = clientHttpRequest;
        this.clientHttpResponse = clientHttpResponse;
    }

    @Override
    public GatewayResponse<T> wrapGatewayResponse() throws GatewayException {
        RestfulResponse<T> restfulResponse;
        try {
            restfulResponse = new RestfulResponse<T>();
            restfulResponse.setResponseEntity(this.getResponseResult());
            restfulResponse.setStatusCode(String.valueOf(clientHttpResponse.getRawStatusCode()));
            restfulResponse.setReasonPhrase(clientHttpResponse.getStatusText());
            restfulResponse.setResponseHeaders(clientHttpResponse.getHeaders());
        } catch (IOException e) {
            throw new GatewayException(e);
        }
        return restfulResponse;
    }

    @Override
    public GatewayWrapper<T> getComponent() throws GatewayException {
        return this;
    }

    public Class<T> getResponseType() {
        return responseType;
    }

    public GatewayWrapperEntity setResponseType(Class<T> responseType) {
        this.responseType = responseType;
        return this;
    }

    public T getResponseResult() {
        return responseResult;
    }

    public GatewayWrapperEntity setResponseResult(T responseResult) {
        this.responseResult = responseResult;
        return this;
    }

    public ClientHttpRequest getClientHttpRequest() {
        return clientHttpRequest;
    }

    public GatewayWrapperEntity setClientHttpRequest(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
        return this;
    }

    public ClientHttpResponse getClientHttpResponse() {
        return clientHttpResponse;
    }

    public GatewayWrapperEntity setClientHttpResponse(ClientHttpResponse clientHttpResponse) {
        this.clientHttpResponse = clientHttpResponse;
        return this;
    }

}
