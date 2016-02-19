package com.ohayoyo.gateway.http.core;

import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.IOException;
import java.util.List;

/**
 * @author 蓝明乐
 */
public interface HttpGatewayRequest {

    String CUSTOM_REQUEST_CONTENT_TYPE = "Custom-Request-Content-Type";

    String DEFAULT_REQUEST_CONTENT_TYPE = "Default-Request-Content-Type";

    <RequestBody> void requestHandler(MediaType requestContentType, RequestEntity<RequestBody> requestEntity, HttpGatewayMessageConverters httpGatewayMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException;

    <RequestBody> void requestCallback(MediaType requestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException;

}
