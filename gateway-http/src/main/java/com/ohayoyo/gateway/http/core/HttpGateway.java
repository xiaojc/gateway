package com.ohayoyo.gateway.http.core;

import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;

/**
 * @author 蓝明乐
 */
public interface HttpGateway extends HttpGatewayAccessor, HttpGatewayRequestIntercepting {

    List<HttpMessageConverter<?>> getHttpMessageConverters();

    HttpGateway setHttpMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters);

    HttpGatewayRequest getHttpGatewayRequest();

    HttpGateway setHttpGatewayRequest(HttpGatewayRequest httpGatewayRequest);

    HttpGatewayResponse getHttpGatewayResponse();

    HttpGateway setHttpGatewayResponse(HttpGatewayResponse httpGatewayResponse);

    <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpGatewayException;

}
