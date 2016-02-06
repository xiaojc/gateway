package com.ohayoyo.gateway.http;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

public abstract class AbstractHttpClientHandler implements HttpClientHandler {

    private HttpRequestHandler httpRequestHandler;

    private HttpResponseHandler httpResponseHandler;

    private List<HttpMessageConverter<?>> httpMessageConverters;

    private ClientHttpRequestFactory clientHttpRequestFactory;

    private List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors;

    @Override
    public HttpRequestHandler getHttpRequestHandler() {
        return httpRequestHandler;
    }

    @Override
    public AbstractHttpClientHandler setHttpRequestHandler(HttpRequestHandler httpRequestHandler) {
        Assert.notNull(httpRequestHandler);
        this.httpRequestHandler = httpRequestHandler;
        return this;
    }

    @Override
    public HttpResponseHandler getHttpResponseHandler() {
        return httpResponseHandler;
    }

    @Override
    public AbstractHttpClientHandler setHttpResponseHandler(HttpResponseHandler httpResponseHandler) {
        Assert.notNull(httpResponseHandler);
        this.httpResponseHandler = httpResponseHandler;
        return this;
    }

    @Override
    public List<HttpMessageConverter<?>> getHttpMessageConverters() {
        return httpMessageConverters;
    }

    @Override
    public AbstractHttpClientHandler setHttpMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
        Assert.notNull(httpMessageConverters);
        this.httpMessageConverters = httpMessageConverters;
        return this;
    }

    @Override
    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        return clientHttpRequestFactory;
    }

    @Override
    public AbstractHttpClientHandler setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory) {
        Assert.notNull(clientHttpRequestFactory);
        this.clientHttpRequestFactory = clientHttpRequestFactory;
        return this;
    }

    @Override
    public List<ClientHttpRequestInterceptor> getClientHttpRequestInterceptors() {
        return clientHttpRequestInterceptors;
    }

    @Override
    public AbstractHttpClientHandler setClientHttpRequestInterceptors(List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors) {
        this.clientHttpRequestInterceptors = clientHttpRequestInterceptors;
        return this;
    }

    @Override
    public final <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpClientException {
        return handler(null, null, responseBodyClass, requestEntity);
    }

    @Override
    public <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpClientException {
        Assert.notNull(responseBodyClass);
        Assert.notNull(requestEntity);
        ResponseEntity<ResponseBody> responseEntity;
        try {
            responseEntity = this.doHandler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);
        } catch (IOException ex) {
            throw new HttpClientException(ex);
        }
        if (null == responseEntity) {
            throw new HttpClientException("response entity is null , see your [HttpClientHandler] implement class.");
        }
        return responseEntity;
    }

    protected abstract <ResponseBody, RequestBody> ResponseEntity<ResponseBody> doHandler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpClientException, IOException;

}