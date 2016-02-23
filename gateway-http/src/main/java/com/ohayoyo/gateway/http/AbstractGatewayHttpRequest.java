package com.ohayoyo.gateway.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequest;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewayHttpRequest implements GatewayHttpRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGatewayHttpRequest.class);

    @Override
    public final <RequestBody> void requestHandler(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, GatewayHttpMessageConverters gatewayHttpMessageConverters, ClientHttpRequest clientHttpRequest) throws GatewayHttpException, IOException {
        this.requestHeadersHandler(customRequestContentType, requestEntity, clientHttpRequest);
        this.requestAcceptHeaderHandler(gatewayHttpMessageConverters, clientHttpRequest);
        this.requestBodyHandler(requestEntity, gatewayHttpMessageConverters, clientHttpRequest);
    }

    protected abstract <RequestBody> void requestHeadersHandler(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws GatewayHttpException, IOException;

    protected abstract void requestAcceptHeaderHandler(GatewayHttpMessageConverters gatewayHttpMessageConverters, ClientHttpRequest clientHttpRequest) throws GatewayHttpException, IOException;

    protected abstract <RequestBody> void requestBodyHandler(RequestEntity<RequestBody> requestEntity, GatewayHttpMessageConverters gatewayHttpMessageConverters, ClientHttpRequest clientHttpRequest) throws GatewayHttpException, IOException;

    @Override
    public <RequestBody> void requestCallback(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws GatewayHttpException, IOException {
    }

}
