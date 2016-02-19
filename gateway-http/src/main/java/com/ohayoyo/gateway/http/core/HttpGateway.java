package com.ohayoyo.gateway.http.core;

import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import com.ohayoyo.gateway.http.interceptor.HttpGatewayRequestIntercepting;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author 蓝明乐
 */
public interface HttpGateway extends HttpGatewayAccessor {

    HttpGatewayMessageConverters getHttpGatewayMessageConverters();

    HttpGateway setHttpGatewayMessageConverters(HttpGatewayMessageConverters httpGatewayMessageConverters);

    HttpGatewayRequest getHttpGatewayRequest();

    HttpGateway setHttpGatewayRequest(HttpGatewayRequest httpGatewayRequest);

    HttpGatewayResponse getHttpGatewayResponse();

    HttpGateway setHttpGatewayResponse(HttpGatewayResponse httpGatewayResponse);

    HttpGatewayRequestIntercepting getHttpGatewayRequestIntercepting();

    HttpGateway setHttpGatewayRequestIntercepting(HttpGatewayRequestIntercepting httpGatewayRequestIntercepting);

    <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpGatewayException;

}
