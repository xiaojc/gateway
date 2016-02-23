package com.ohayoyo.gateway.http;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author 蓝明乐
 */
public interface GatewayHttpClient extends GatewayHttpAccessor {

    boolean isBufferingClientHttpRequest();

    void setBufferingClientHttpRequest(boolean bufferingClientHttpRequest);

    GatewayHttpMessageConverters getGatewayHttpMessageConverters();

    void setGatewayHttpMessageConverters(GatewayHttpMessageConverters gatewayHttpMessageConverters);

    GatewayHttpRequest getGatewayHttpRequest();

    void setGatewayHttpRequest(GatewayHttpRequest gatewayHttpRequest);

    GatewayHttpResponse getGatewayHttpResponse();

    void setGatewayHttpResponse(GatewayHttpResponse gatewayHttpResponse);

    GatewayHttpRequestInterceptors getGatewayHttpRequestInterceptors();

    void setGatewayHttpRequestInterceptors(GatewayHttpRequestInterceptors gatewayHttpRequestInterceptors);

    <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws GatewayHttpException;

}
