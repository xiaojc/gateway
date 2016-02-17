package com.ohayoyo.gateway.http.core;

import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import com.ohayoyo.gateway.http.request.HttpGatewayRequestHandler;
import com.ohayoyo.gateway.http.response.HttpGatewayResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

/**
 * @author 蓝明乐
 */
public abstract class AbstractHttpGateway implements HttpGateway {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpGateway.class);

    private List<HttpMessageConverter<?>> httpMessageConverters;

    private ConversionService conversionService;

    private HttpGatewayRequest httpGatewayRequest;

    private HttpGatewayResponse httpGatewayResponse;

    private List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors;

    private ClientHttpRequestFactory clientHttpRequestFactory;

    private static final ConversionService CONVERSION_SERVICE = new DefaultFormattingConversionService();

    public AbstractHttpGateway() {
        this(new HttpGatewayMessageConverters(CONVERSION_SERVICE), CONVERSION_SERVICE, new HttpGatewayRequestHandler(), new HttpGatewayResponseHandler(), null, new SimpleClientHttpRequestFactory());
    }

    public AbstractHttpGateway(List<HttpMessageConverter<?>> httpMessageConverters, ConversionService conversionService) {
        this(httpMessageConverters, conversionService, new HttpGatewayRequestHandler(), new HttpGatewayResponseHandler(), null, new SimpleClientHttpRequestFactory());
    }

    public AbstractHttpGateway(List<HttpMessageConverter<?>> httpMessageConverters, ConversionService conversionService, ClientHttpRequestFactory clientHttpRequestFactory) {
        this(httpMessageConverters, conversionService, new HttpGatewayRequestHandler(), new HttpGatewayResponseHandler(), null, clientHttpRequestFactory);
    }

    public AbstractHttpGateway(List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequestFactory clientHttpRequestFactory, List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors, ConversionService conversionService) {
        this(httpMessageConverters, conversionService, new HttpGatewayRequestHandler(), new HttpGatewayResponseHandler(), clientHttpRequestInterceptors, clientHttpRequestFactory);
    }

    public AbstractHttpGateway(List<HttpMessageConverter<?>> httpMessageConverters, ConversionService conversionService, HttpGatewayRequest httpGatewayRequest, HttpGatewayResponse httpGatewayResponse, ClientHttpRequestFactory clientHttpRequestFactory) {
        this(httpMessageConverters, conversionService, httpGatewayRequest, httpGatewayResponse, null, clientHttpRequestFactory);
    }

    public AbstractHttpGateway(List<HttpMessageConverter<?>> httpMessageConverters, ConversionService conversionService, HttpGatewayRequest httpGatewayRequest, HttpGatewayResponse httpGatewayResponse, List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors, ClientHttpRequestFactory clientHttpRequestFactory) {
        Assert.notEmpty(httpMessageConverters);
        Assert.notNull(conversionService);
        Assert.notNull(httpGatewayRequest);
        Assert.notNull(httpGatewayResponse);
        Assert.notNull(clientHttpRequestFactory);
        this.httpMessageConverters = httpMessageConverters;
        this.conversionService = conversionService;
        this.httpGatewayRequest = httpGatewayRequest;
        this.httpGatewayResponse = httpGatewayResponse;
        this.clientHttpRequestInterceptors = clientHttpRequestInterceptors;
        this.clientHttpRequestFactory = clientHttpRequestFactory;
    }

    @Override
    public final <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpGatewayException, IOException {
        return this.handler(null, null, responseBodyClass, requestEntity);
    }

    @Override
    public final <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpGatewayException, IOException {
        Assert.notNull(responseBodyClass);
        Assert.notNull(requestEntity);
        ResponseEntity<ResponseBody> responseEntity = this.doHandler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);
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

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    @Override
    public AbstractHttpGateway setConversionService(ConversionService conversionService) {
        Assert.notNull(conversionService);
        this.conversionService = conversionService;
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
        Assert.notEmpty(clientHttpRequestInterceptors);
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

}