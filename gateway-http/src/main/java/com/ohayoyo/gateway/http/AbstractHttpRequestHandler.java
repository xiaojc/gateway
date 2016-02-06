package com.ohayoyo.gateway.http;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.IOException;
import java.util.List;

public abstract class AbstractHttpRequestHandler implements HttpRequestHandler {

    @Override
    public <RequestBody> void requestHandler(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpClientException, IOException {
        this.doRequestHeadersHandler(customRequestContentType, requestEntity, clientHttpRequest);
        this.doRequestAcceptHeaderHandler(httpMessageConverters, clientHttpRequest);
        if (requestEntity.hasBody()) {
            this.doRequestBodyHandler(requestEntity, httpMessageConverters, clientHttpRequest);
        }
    }

    protected abstract <RequestBody> void doRequestHeadersHandler(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws HttpClientException, IOException;

    protected abstract void doRequestAcceptHeaderHandler(List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpClientException, IOException;

    protected abstract <RequestBody> void doRequestBodyHandler(RequestEntity<RequestBody> requestEntity, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpClientException, IOException;

    @Override
    public <RequestBody> void requestCallback(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws HttpClientException, IOException {
    }


}
