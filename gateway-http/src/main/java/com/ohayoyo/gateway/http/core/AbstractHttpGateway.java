package com.ohayoyo.gateway.http.core;

import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * @author 蓝明乐
 */
public abstract class AbstractHttpGateway implements HttpGateway {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpGateway.class);

    private List<HttpMessageConverter<?>> httpMessageConverters;

    private HttpGatewayRequest httpGatewayRequest;

    private HttpGatewayResponse httpGatewayResponse;

    private List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors;

    private ClientHttpRequestFactory clientHttpRequestFactory;

    @Override
    public final <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpGatewayException {
        Assert.notNull(responseBodyClass);
        Assert.notNull(requestEntity);
        ResponseEntity<ResponseBody> responseEntity = null;
        try {
            responseEntity = this.doHandler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);
        } catch (IOException ex) {
            HttpGatewayException.exception(ex);
        }
        return responseEntity;
    }

    protected abstract <ResponseBody, RequestBody> ResponseEntity<ResponseBody> doHandler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpGatewayException, IOException;

    @Override
    public List<HttpMessageConverter<?>> getHttpMessageConverters() {
        return httpMessageConverters;
    }

    @Override
    public AbstractHttpGateway setHttpMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
        Assert.notEmpty(httpMessageConverters);
        this.httpMessageConverters = httpMessageConverters;
        return this;
    }

    public HttpGatewayRequest getHttpGatewayRequest() {
        return httpGatewayRequest;
    }

    public AbstractHttpGateway setHttpGatewayRequest(HttpGatewayRequest httpGatewayRequest) {
        Assert.notNull(httpGatewayRequest);
        this.httpGatewayRequest = httpGatewayRequest;
        return this;
    }

    public HttpGatewayResponse getHttpGatewayResponse() {
        return httpGatewayResponse;
    }

    public AbstractHttpGateway setHttpGatewayResponse(HttpGatewayResponse httpGatewayResponse) {
        Assert.notNull(httpGatewayResponse);
        this.httpGatewayResponse = httpGatewayResponse;
        return this;
    }

    @Override
    public List<ClientHttpRequestInterceptor> getClientHttpRequestInterceptors() {
        return clientHttpRequestInterceptors;
    }

    @Override
    public AbstractHttpGateway setClientHttpRequestInterceptors(List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors) {
        this.clientHttpRequestInterceptors = clientHttpRequestInterceptors;
        return this;
    }

    @Override
    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        return clientHttpRequestFactory;
    }

    @Override
    public AbstractHttpGateway setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory) {
        Assert.notNull(clientHttpRequestFactory);
        this.clientHttpRequestFactory = clientHttpRequestFactory;
        return this;
    }

    protected <RequestBody> ClientHttpRequest createClientHttpRequest(RequestEntity<RequestBody> requestEntity, ClientHttpRequestFactory clientHttpRequestFactory, List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors) throws HttpGatewayException, IOException {
        URI uri = requestEntity.getUrl();
        HttpMethod httpMethod = requestEntity.getMethod();
        if (!CollectionUtils.isEmpty(clientHttpRequestInterceptors)) {
            clientHttpRequestFactory = new InterceptingClientHttpRequestFactory(clientHttpRequestFactory, clientHttpRequestInterceptors);
        }
        return clientHttpRequestFactory.createRequest(uri, httpMethod);
    }

}