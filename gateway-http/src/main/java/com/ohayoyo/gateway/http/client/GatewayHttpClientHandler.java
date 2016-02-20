package com.ohayoyo.gateway.http.client;

import com.ohayoyo.gateway.http.converter.GatewayHttpMessageConverters;
import com.ohayoyo.gateway.http.exception.GatewayHttpException;
import com.ohayoyo.gateway.http.interceptor.GatewayHttpRequestInterceptors;
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
public class GatewayHttpClientHandler extends AbstractGatewayHttpClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(GatewayHttpClientHandler.class);

    @Override
    protected <ResponseBody, RequestBody> ResponseEntity<ResponseBody> doHandler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws GatewayHttpException, IOException {
        GatewayHttpRequest gatewayHttpRequest = this.getGatewayHttpRequest();
        GatewayHttpResponse gatewayHttpResponse = this.getGatewayHttpResponse();
        GatewayHttpMessageConverters gatewayHttpMessageConverters = this.getGatewayHttpMessageConverters();
        ClientHttpRequestFactory clientHttpRequestFactory = this.getClientHttpRequestFactory();
        GatewayHttpRequestInterceptors gatewayHttpRequestInterceptors = this.getGatewayHttpRequestInterceptors();
        ClientHttpResponse clientHttpResponse = null;
        ResponseEntity<ResponseBody> responseEntity = null;
        try {
            ClientHttpRequest clientHttpRequest = createClientHttpRequest(requestEntity, clientHttpRequestFactory, gatewayHttpRequestInterceptors);
            gatewayHttpRequest.requestHandler(customRequestContentType, requestEntity, gatewayHttpMessageConverters, clientHttpRequest);
            gatewayHttpRequest.requestCallback(customRequestContentType, requestEntity, clientHttpRequest);
            clientHttpResponse = clientHttpRequest.execute();
            gatewayHttpResponse.responseErrorHandler(clientHttpResponse);
            responseEntity = gatewayHttpResponse.responseHandler(customResponseContentType, responseBodyClass, gatewayHttpMessageConverters, clientHttpResponse);
            gatewayHttpResponse.responseCallback(customResponseContentType, responseEntity, clientHttpResponse);
        } finally {
            if (!ObjectUtils.isEmpty(clientHttpResponse)) {
                clientHttpResponse.close();
            }
        }
        return responseEntity;
    }

}
