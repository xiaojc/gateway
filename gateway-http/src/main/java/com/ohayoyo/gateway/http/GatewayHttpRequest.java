package com.ohayoyo.gateway.http;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequest;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public interface GatewayHttpRequest {

    String CUSTOM_REQUEST_CONTENT_TYPE = "Custom-Request-Content-Type";

    String DEFAULT_REQUEST_CONTENT_TYPE = "Default-Request-Content-Type";

    <RequestBody> void requestHandler(MediaType requestContentType, RequestEntity<RequestBody> requestEntity, GatewayHttpMessageConverters gatewayHttpMessageConverters, ClientHttpRequest clientHttpRequest) throws GatewayHttpException, IOException;

    <RequestBody> void requestCallback(MediaType requestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) throws GatewayHttpException, IOException;

}
