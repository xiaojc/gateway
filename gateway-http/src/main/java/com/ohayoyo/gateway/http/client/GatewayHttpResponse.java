package com.ohayoyo.gateway.http.client;

import com.ohayoyo.gateway.http.converter.GatewayHttpMessageConverters;
import com.ohayoyo.gateway.http.exception.GatewayHttpException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public interface GatewayHttpResponse {

    String CUSTOM_RESPONSE_CONTENT_TYPE = "Custom-Response-Content-Type";

    String DEFAULT_RESPONSE_CONTENT_TYPE = "Default-Response-Content-Type";

    ResponseErrorHandler getResponseErrorHandler();

    GatewayHttpResponse setResponseErrorHandler(ResponseErrorHandler responseErrorHandler);

    <ResponseBody> ResponseEntity<ResponseBody> responseHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, GatewayHttpMessageConverters gatewayHttpMessageConverters, ClientHttpResponse clientHttpResponse) throws GatewayHttpException, IOException;

    <ResponseBody> void responseCallback(MediaType customResponseContentType, ResponseEntity<ResponseBody> responseEntity, ClientHttpResponse clientHttpResponse) throws GatewayHttpException, IOException;

    void responseErrorHandler(ClientHttpResponse clientHttpResponse) throws GatewayHttpException, IOException;

}
