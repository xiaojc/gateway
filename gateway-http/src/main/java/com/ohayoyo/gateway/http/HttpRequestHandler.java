package com.ohayoyo.gateway.http;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.IOException;
import java.util.List;

/**
 * @author 蓝明乐
 */
public interface HttpRequestHandler {

    String CUSTOM_REQUEST_CONTENT_TYPE = "Custom-Request-Content-Type";

    String DEFAULT_REQUEST_CONTENT_TYPE = "Default-Request-Content-Type";

    <RequestBody> void requestHandler(MediaType requestContentType, RequestEntity<RequestBody> requestEntity, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpClientException, IOException;

    <RequestBody> void requestCallback(MediaType requestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws HttpClientException, IOException;

}
