package com.ohayoyo.gateway.http.client;

import com.ohayoyo.gateway.http.converter.GatewayHttpMessageConverters;
import com.ohayoyo.gateway.http.exception.GatewayHttpException;
import com.ohayoyo.gateway.http.interceptor.GatewayHttpRequestIntercepting;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author 蓝明乐
 */
public interface GatewayHttpClient extends GatewayHttpAccessor {

    GatewayHttpMessageConverters getGatewayHttpMessageConverters();

    void setGatewayHttpMessageConverters(GatewayHttpMessageConverters gatewayHttpMessageConverters);

    GatewayHttpRequest getGatewayHttpRequest();

    void setGatewayHttpRequest(GatewayHttpRequest gatewayHttpRequest);

    GatewayHttpResponse getGatewayHttpResponse();

    void setGatewayHttpResponse(GatewayHttpResponse gatewayHttpResponse);

    GatewayHttpRequestIntercepting getGatewayHttpRequestIntercepting();

    void setGatewayHttpRequestIntercepting(GatewayHttpRequestIntercepting gatewayHttpRequestIntercepting);

    <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws GatewayHttpException;

}
