package com.ohayoyo.gateway.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultHttpRequestHandler extends AbstractHttpRequestHandler {

    @Override
    protected <RequestBody> void doRequestHeadersHandler(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) {
        HttpHeaders headers = requestEntity.getHeaders();
        if (null == headers) {
            headers = new HttpHeaders();
        }
        MediaType contentType = headers.getContentType();
        if (null != contentType) {
            String defaultHeaderValues = contentType.toString();
            headers.set(DEFAULT_REQUEST_CONTENT_TYPE, defaultHeaderValues);
        }
        if (null != customRequestContentType) {
            String headerValues = customRequestContentType.toString();
            headers.set(CUSTOM_REQUEST_CONTENT_TYPE, headerValues);
            headers.setContentType(customRequestContentType);
        }
        if (!requestEntity.hasBody()) {
            if (headers.getContentLength() == -1) {
                headers.setContentLength(0L);
            }
        }
        clientHttpRequest.getHeaders().putAll(headers);
    }

    @Override
    protected void doRequestAcceptHeaderHandler(List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) {
        List<MediaType> accept = clientHttpRequest.getHeaders().getAccept();
        if (CollectionUtils.isEmpty(accept)) {
            Set<MediaType> allSupportedMediaTypeSet = new HashSet<MediaType>();
            for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
                List<MediaType> mediaTypes = httpMessageConverter.getSupportedMediaTypes();
                if (!CollectionUtils.isEmpty(mediaTypes)) {
                    for (MediaType mediaType : mediaTypes) {
                        allSupportedMediaTypeSet.add(mediaType);
                    }
                }
            }
            List<MediaType> mediaTypeList = new ArrayList<MediaType>();
            mediaTypeList.addAll(allSupportedMediaTypeSet);
            if (!allSupportedMediaTypeSet.isEmpty()) {
                MediaType.sortBySpecificity(mediaTypeList);
                clientHttpRequest.getHeaders().setAccept(mediaTypeList);
            }
        }
    }

    @Override
    protected <RequestBody> void doRequestBodyHandler(RequestEntity<RequestBody> requestEntity, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpClientException, IOException {
        RequestBody requestBody = requestEntity.getBody();
        Class<RequestBody> requestBodyClass = (Class<RequestBody>) requestBody.getClass();
        HttpHeaders requestHeaders = requestEntity.getHeaders();
        MediaType contentType = requestHeaders.getContentType();
        for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
            if (httpMessageConverter.canWrite(requestBodyClass, contentType)) {
                ((HttpMessageConverter<RequestBody>) httpMessageConverter).write(requestBody, contentType, clientHttpRequest);
                return;
            }
        }
        String message = "no suitable HttpMessageConverter found for request type [" + requestBodyClass + "]";
        if (contentType != null) {
            message += " and content type [" + contentType + "]";
        }
        throw new HttpClientException(message);
    }

}
