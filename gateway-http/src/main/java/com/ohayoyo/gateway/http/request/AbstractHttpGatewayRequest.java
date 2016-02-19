package com.ohayoyo.gateway.http.request;

import com.ohayoyo.gateway.http.core.HttpGatewayRequest;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.IOException;
import java.util.List;

/**
 * @author 蓝明乐
 */
public abstract class AbstractHttpGatewayRequest implements HttpGatewayRequest {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpGatewayRequest.class);

    @Override
    public final <RequestBody> void requestHandler(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException {
        this.requestHeadersHandler(customRequestContentType, requestEntity, clientHttpRequest);
        this.requestAcceptHeaderHandler(httpMessageConverters, clientHttpRequest);
        this.requestBodyHandler(requestEntity, httpMessageConverters, clientHttpRequest);
    }

    protected abstract <RequestBody> void requestHeadersHandler(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException;

    protected abstract void requestAcceptHeaderHandler(List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException;

    protected abstract <RequestBody> void requestBodyHandler(RequestEntity<RequestBody> requestEntity, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException;

    @Override
    public <RequestBody> void requestCallback(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException {
    }

}
