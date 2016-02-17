package com.ohayoyo.gateway.http.core;

import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * @author 蓝明乐
 */
public class HttpGatewayHandler extends AbstractHttpGateway {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGatewayHandler.class);

    private static HttpGatewayHandler DEFAULT_HTTP_GATEWAY_HANDLER = null;

    private static final Object LOCKED = new Object();

    public HttpGatewayHandler() {
        super();
    }

    public HttpGatewayHandler(List<HttpMessageConverter<?>> httpMessageConverters, ConversionService conversionService) {
        super(httpMessageConverters, conversionService);
    }

    public HttpGatewayHandler(List<HttpMessageConverter<?>> httpMessageConverters, ConversionService conversionService, ClientHttpRequestFactory clientHttpRequestFactory) {
        super(httpMessageConverters, conversionService, clientHttpRequestFactory);
    }

    public HttpGatewayHandler(List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequestFactory clientHttpRequestFactory, List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors, ConversionService conversionService) {
        super(httpMessageConverters, clientHttpRequestFactory, clientHttpRequestInterceptors, conversionService);
    }

    public HttpGatewayHandler(List<HttpMessageConverter<?>> httpMessageConverters, ConversionService conversionService, HttpGatewayRequest httpGatewayRequest, HttpGatewayResponse httpGatewayResponse, ClientHttpRequestFactory clientHttpRequestFactory) {
        super(httpMessageConverters, conversionService, httpGatewayRequest, httpGatewayResponse, clientHttpRequestFactory);
    }

    public HttpGatewayHandler(List<HttpMessageConverter<?>> httpMessageConverters, ConversionService conversionService, HttpGatewayRequest httpGatewayRequest, HttpGatewayResponse httpGatewayResponse, List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors, ClientHttpRequestFactory clientHttpRequestFactory) {
        super(httpMessageConverters, conversionService, httpGatewayRequest, httpGatewayResponse, clientHttpRequestInterceptors, clientHttpRequestFactory);
    }

    public static final HttpGatewayHandler defaultHttpGatewayHandler() {
        if (ObjectUtils.isEmpty(DEFAULT_HTTP_GATEWAY_HANDLER)) {
            synchronized (LOCKED) {
                if (ObjectUtils.isEmpty(DEFAULT_HTTP_GATEWAY_HANDLER)) {
                    DEFAULT_HTTP_GATEWAY_HANDLER = new HttpGatewayHandler();
                }
            }
        }
        return DEFAULT_HTTP_GATEWAY_HANDLER;
    }

    @Override
    protected <ResponseBody, RequestBody> ResponseEntity<ResponseBody> doHandler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpGatewayException, IOException {
        HttpGatewayRequest httpGatewayRequest = this.getHttpGatewayRequest();
        HttpGatewayResponse httpGatewayResponse = this.getHttpGatewayResponse();
        List<HttpMessageConverter<?>> httpMessageConverters = this.getHttpMessageConverters();
        ClientHttpRequestFactory clientHttpRequestFactory = this.getClientHttpRequestFactory();
        List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = this.getClientHttpRequestInterceptors();
        ClientHttpResponse clientHttpResponse = null;
        ResponseEntity<ResponseBody> responseEntity = null;
        try {
            ClientHttpRequest clientHttpRequest = createClientHttpRequest(requestEntity, clientHttpRequestFactory, clientHttpRequestInterceptors);
            httpGatewayRequest.requestHandler(customRequestContentType, requestEntity, httpMessageConverters, clientHttpRequest);
            httpGatewayRequest.requestCallback(customRequestContentType, requestEntity, clientHttpRequest);
            clientHttpResponse = clientHttpRequest.execute();
            httpGatewayResponse.responseErrorHandler(clientHttpResponse);
            responseEntity = httpGatewayResponse.responseHandler(customResponseContentType, responseBodyClass, httpMessageConverters, clientHttpResponse);
            httpGatewayResponse.responseCallback(customResponseContentType, responseEntity, clientHttpResponse);
        } finally {
            if (null != clientHttpResponse) {
                clientHttpResponse.close();
            }
        }
        return responseEntity;
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
