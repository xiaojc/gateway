package com.ohayoyo.gateway.http;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.List;

/**
 * @author 蓝明乐
 */
public interface HttpResponseHandler {

    String CUSTOM_RESPONSE_CONTENT_TYPE = "Custom-Response-Content-Type";

    String DEFAULT_RESPONSE_CONTENT_TYPE = "Default-Response-Content-Type";

    ResponseErrorHandler getResponseErrorHandler();

    HttpResponseHandler setResponseErrorHandler(ResponseErrorHandler responseErrorHandler);

    <ResponseBody> ResponseEntity<ResponseBody> responseHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException;

    <ResponseBody> void responseCallback(MediaType customResponseContentType, ResponseEntity<ResponseBody> responseEntity, ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException;

    void responseErrorHandler(ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException;

}
