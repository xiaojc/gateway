package com.ohayoyo.gateway.http.request;

import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import com.ohayoyo.gateway.http.core.HttpGatewayRequest;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequest;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public abstract class AbstractHttpGatewayRequest implements HttpGatewayRequest {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpGatewayRequest.class);

    @Override
    public final <RequestBody> void requestHandler(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, HttpGatewayMessageConverters httpGatewayMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException {
        this.requestHeadersHandler(customRequestContentType, requestEntity, clientHttpRequest);
        this.requestAcceptHeaderHandler(httpGatewayMessageConverters, clientHttpRequest);
        this.requestBodyHandler(requestEntity, httpGatewayMessageConverters, clientHttpRequest);
    }

    protected abstract <RequestBody> void requestHeadersHandler(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException;

    protected abstract void requestAcceptHeaderHandler(HttpGatewayMessageConverters httpGatewayMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException;

    protected abstract <RequestBody> void requestBodyHandler(RequestEntity<RequestBody> requestEntity, HttpGatewayMessageConverters httpGatewayMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException;

    @Override
    public <RequestBody> void requestCallback(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException {
    }

}
