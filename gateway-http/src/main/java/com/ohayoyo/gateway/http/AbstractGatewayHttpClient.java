package com.ohayoyo.gateway.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URI;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewayHttpClient implements GatewayHttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGatewayHttpClient.class);

    private GatewayHttpMessageConverters gatewayHttpMessageConverters;

    private GatewayHttpRequest gatewayHttpRequest;

    private GatewayHttpResponse gatewayHttpResponse;

    private GatewayHttpRequestInterceptors gatewayHttpRequestInterceptors;

    private ClientHttpRequestFactory clientHttpRequestFactory;

    private boolean bufferingClientHttpRequest = true;

    protected <RequestBody> ClientHttpRequest createClientHttpRequest(RequestEntity<RequestBody> requestEntity) throws GatewayHttpException, IOException {
        URI uri = requestEntity.getUrl();
        HttpMethod httpMethod = requestEntity.getMethod();
        ClientHttpRequestFactory clientHttpRequestFactory = this.getClientHttpRequestFactory();
        GatewayHttpRequestInterceptors gatewayHttpRequestInterceptors = this.getGatewayHttpRequestInterceptors();
        ClientHttpRequestFactory executeClientHttpRequestFactory = clientHttpRequestFactory;
        if (this.isBufferingClientHttpRequest()) {
            executeClientHttpRequestFactory = new BufferingClientHttpRequestFactory(executeClientHttpRequestFactory);
        }
        if (!CollectionUtils.isEmpty(gatewayHttpRequestInterceptors)) {
            executeClientHttpRequestFactory = new InterceptingClientHttpRequestFactory(executeClientHttpRequestFactory, gatewayHttpRequestInterceptors);
        }
        return executeClientHttpRequestFactory.createRequest(uri, httpMethod);
    }

    @Override
    public final <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws GatewayHttpException {
        Assert.notNull(responseBodyClass);
        Assert.notNull(requestEntity);
        ResponseEntity<ResponseBody> responseEntity = null;
        try {
            responseEntity = this.doHandler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);
        } catch (IOException ex) {
            GatewayHttpException.exception(ex);
        }
        return responseEntity;
    }

    protected abstract <ResponseBody, RequestBody> ResponseEntity<ResponseBody> doHandler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws GatewayHttpException, IOException;

    public boolean isBufferingClientHttpRequest() {
        return bufferingClientHttpRequest;
    }

    public void setBufferingClientHttpRequest(boolean bufferingClientHttpRequest) {
        this.bufferingClientHttpRequest = bufferingClientHttpRequest;
    }

    public GatewayHttpMessageConverters getGatewayHttpMessageConverters() {
        return gatewayHttpMessageConverters;
    }

    public void setGatewayHttpMessageConverters(GatewayHttpMessageConverters gatewayHttpMessageConverters) {
        this.gatewayHttpMessageConverters = gatewayHttpMessageConverters;
    }

    public GatewayHttpRequest getGatewayHttpRequest() {
        return gatewayHttpRequest;
    }

    public void setGatewayHttpRequest(GatewayHttpRequest gatewayHttpRequest) {
        Assert.notNull(gatewayHttpRequest);
        this.gatewayHttpRequest = gatewayHttpRequest;
    }

    public GatewayHttpResponse getGatewayHttpResponse() {
        return gatewayHttpResponse;
    }

    public void setGatewayHttpResponse(GatewayHttpResponse gatewayHttpResponse) {
        Assert.notNull(gatewayHttpResponse);
        this.gatewayHttpResponse = gatewayHttpResponse;
    }

    public GatewayHttpRequestInterceptors getGatewayHttpRequestInterceptors() {
        return gatewayHttpRequestInterceptors;
    }

    public void setGatewayHttpRequestInterceptors(GatewayHttpRequestInterceptors gatewayHttpRequestInterceptors) {
        this.gatewayHttpRequestInterceptors = gatewayHttpRequestInterceptors;
    }

    @Override
    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        return clientHttpRequestFactory;
    }

    @Override
    public void setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory) {
        Assert.notNull(clientHttpRequestFactory);
        this.clientHttpRequestFactory = clientHttpRequestFactory;
    }

}