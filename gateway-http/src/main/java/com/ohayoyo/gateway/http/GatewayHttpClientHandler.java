package com.ohayoyo.gateway.http;

import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
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
public class GatewayHttpClientHandler extends AbstractHttpClientHandler {

    private static GatewayHttpClientHandler DEFAULT_HTTP_CLIENT_HANDLER = null;

    private static final Object LOCKED = new Object();

    public GatewayHttpClientHandler() {
        ConversionService conversionService = new DefaultFormattingConversionService();
        this.setConversionService(conversionService);
        this.setHttpMessageConverters(new GatewayHttpMessageConverters(conversionService));
        this.setClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        this.setHttpRequestHandler(new GatewayHttpRequestHandler());
        this.setHttpResponseHandler(new GatewayHttpResponseHandler());
    }

    public static final GatewayHttpClientHandler getDefaultHttpClientHandler() {
        if (ObjectUtils.isEmpty(DEFAULT_HTTP_CLIENT_HANDLER)) {
            synchronized (LOCKED) {
                if (ObjectUtils.isEmpty(DEFAULT_HTTP_CLIENT_HANDLER)) {
                    DEFAULT_HTTP_CLIENT_HANDLER = new GatewayHttpClientHandler();
                }
            }
        }
        return DEFAULT_HTTP_CLIENT_HANDLER;
    }

    @Override
    protected <ResponseBody, RequestBody> ResponseEntity<ResponseBody> doHandler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpClientException, IOException {
        HttpRequestHandler httpRequestHandler = this.getHttpRequestHandler();
        HttpResponseHandler httpResponseHandler = this.getHttpResponseHandler();
        List<HttpMessageConverter<?>> httpMessageConverters = this.getHttpMessageConverters();
        ClientHttpRequestFactory clientHttpRequestFactory = this.getClientHttpRequestFactory();
        List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = this.getClientHttpRequestInterceptors();
        ClientHttpResponse clientHttpResponse = null;
        ResponseEntity<ResponseBody> responseEntity = null;
        HttpClientException httpClientException = null;
        IOException ioException = null;
        try {
            ClientHttpRequest clientHttpRequest = createClientHttpRequest(requestEntity, clientHttpRequestFactory, clientHttpRequestInterceptors);
            httpRequestHandler.requestHandler(customRequestContentType, requestEntity, httpMessageConverters, clientHttpRequest);
            httpRequestHandler.requestCallback(customRequestContentType, requestEntity, clientHttpRequest);
            clientHttpResponse = clientHttpRequest.execute();
            httpResponseHandler.responseErrorHandler(clientHttpResponse);
            responseEntity = httpResponseHandler.responseHandler(customResponseContentType, responseBodyClass, httpMessageConverters, clientHttpResponse);
            httpResponseHandler.responseCallback(customResponseContentType, responseEntity, clientHttpResponse);
        } catch (HttpClientException hce) {
            httpClientException = hce;
        } catch (IOException ioe) {
            ioException = ioe;
        } finally {
            if (null != clientHttpResponse) {
                clientHttpResponse.close();
            }
        }
        if (null != httpClientException) {
            throw httpClientException;
        }
        if (null != ioException) {
            throw ioException;
        }
        return responseEntity;
    }

    protected <RequestBody> ClientHttpRequest createClientHttpRequest(RequestEntity<RequestBody> requestEntity, ClientHttpRequestFactory clientHttpRequestFactory, List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors) throws HttpClientException, IOException {
        URI uri = requestEntity.getUrl();
        HttpMethod httpMethod = requestEntity.getMethod();
        if (!CollectionUtils.isEmpty(clientHttpRequestInterceptors)) {
            clientHttpRequestFactory = new InterceptingClientHttpRequestFactory(clientHttpRequestFactory, clientHttpRequestInterceptors);
        }
        return clientHttpRequestFactory.createRequest(uri, httpMethod);
    }

}
