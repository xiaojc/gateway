package com.ohayoyo.gateway.http.core;

import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import com.ohayoyo.gateway.http.interceptor.HttpGatewayRequestIntercepting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public class HttpGatewayHandler extends AbstractHttpGateway {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGatewayHandler.class);

    @Override
    protected <ResponseBody, RequestBody> ResponseEntity<ResponseBody> doHandler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpGatewayException, IOException {
        HttpGatewayRequest httpGatewayRequest = this.getHttpGatewayRequest();
        HttpGatewayResponse httpGatewayResponse = this.getHttpGatewayResponse();
        HttpGatewayMessageConverters httpGatewayMessageConverters = this.getHttpGatewayMessageConverters();
        ClientHttpRequestFactory clientHttpRequestFactory = this.getClientHttpRequestFactory();
        HttpGatewayRequestIntercepting httpGatewayRequestIntercepting = this.getHttpGatewayRequestIntercepting();
        ClientHttpResponse clientHttpResponse = null;
        ResponseEntity<ResponseBody> responseEntity = null;
        try {
            ClientHttpRequest clientHttpRequest = createClientHttpRequest(requestEntity, clientHttpRequestFactory, httpGatewayRequestIntercepting);
            httpGatewayRequest.requestHandler(customRequestContentType, requestEntity, httpGatewayMessageConverters, clientHttpRequest);
            httpGatewayRequest.requestCallback(customRequestContentType, requestEntity, clientHttpRequest);
            clientHttpResponse = clientHttpRequest.execute();
            httpGatewayResponse.responseErrorHandler(clientHttpResponse);
            responseEntity = httpGatewayResponse.responseHandler(customResponseContentType, responseBodyClass, httpGatewayMessageConverters, clientHttpResponse);
            httpGatewayResponse.responseCallback(customResponseContentType, responseEntity, clientHttpResponse);
        } finally {
            if (!ObjectUtils.isEmpty(clientHttpResponse)) {
                clientHttpResponse.close();
            }
        }
        return responseEntity;
    }

}
