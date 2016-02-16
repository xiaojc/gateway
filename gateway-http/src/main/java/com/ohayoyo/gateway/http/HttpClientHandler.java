package com.ohayoyo.gateway.http;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;

/**
 * @author 蓝明乐
 */
public interface HttpClientHandler extends HttpClientAccessor, HttpClientIntercepting {

    List<HttpMessageConverter<?>> getHttpMessageConverters();

    HttpClientHandler setHttpMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters);

    ConversionService getConversionService();

    HttpClientHandler setConversionService(ConversionService conversionService);

    HttpRequestHandler getHttpRequestHandler();

    HttpClientHandler setHttpRequestHandler(HttpRequestHandler httpRequestHandler);

    HttpResponseHandler getHttpResponseHandler();

    HttpClientHandler setHttpResponseHandler(HttpResponseHandler httpResponseHandler);

    <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpClientException;

    <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpClientException;

}
