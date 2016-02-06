package com.ohayoyo.gateway.http;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;

public interface HttpClientHandler extends HttpClientAccessor, HttpClientIntercepting {

    HttpRequestHandler getHttpRequestHandler();

    HttpResponseHandler getHttpResponseHandler();

    List<HttpMessageConverter<?>> getHttpMessageConverters();

    HttpClientHandler setHttpRequestHandler(HttpRequestHandler httpRequestHandler);

    HttpClientHandler setHttpResponseHandler(HttpResponseHandler httpResponseHandler);

    HttpClientHandler setHttpMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters);

    <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpClientException;

    <RequestBody, ResponseBody> ResponseEntity<ResponseBody> handler(MediaType customRequestContentType, MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, RequestEntity<RequestBody> requestEntity) throws HttpClientException;

}
