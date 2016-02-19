package com.ohayoyo.gateway.http.core;

import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author 蓝明乐
 */
public class HttpGatewayHandler extends AbstractHttpGateway {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGatewayHandler.class);

    @Override
    protected <ResponseBody, RequestBody> ResponseEntity<ResponseBody> doHandler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpGatewayException, IOException {
        HttpGatewayRequest httpGatewayRequest = this.getHttpGatewayRequest();
        HttpGatewayResponse httpGatewayResponse = this.getHttpGatewayResponse();
        List<HttpMessageConverter<?>> httpMessageConverters = this.getHttpMessageConverters();
        ClientHttpRequestFactory clientHttpRequestFactory = this.getClientHttpRequestFactory();
        List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = this.getClientHttpRequestInterceptors();
        ClientHttpResponse clientHttpResponse = null;
        ResponseEntity<ResponseBody> responseEntity = null;
        try {
            ClientHttpRequest clientHttpRequest = createClientHttpRequest(requestEntity, clientHttpRequestFactory, clientHttpRequestInterceptors);
            httpGatewayRequest.requestHandler(customRequestContentType, requestEntity, httpMessageConverters, clientHttpRequest);
            httpGatewayRequest.requestCallback(customRequestContentType, requestEntity, clientHttpRequest);
            clientHttpResponse = clientHttpRequest.execute();
            httpGatewayResponse.responseErrorHandler(clientHttpResponse);
            responseEntity = httpGatewayResponse.responseHandler(customResponseContentType, responseBodyClass, httpMessageConverters, clientHttpResponse);
            httpGatewayResponse.responseCallback(customResponseContentType, responseEntity, clientHttpResponse);
        } finally {
            if (!ObjectUtils.isEmpty(clientHttpResponse)) {
                clientHttpResponse.close();
            }
        }
        return responseEntity;
    }

}
