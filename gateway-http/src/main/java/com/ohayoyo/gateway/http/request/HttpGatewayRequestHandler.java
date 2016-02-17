package com.ohayoyo.gateway.http.request;

import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * @author 蓝明乐
 */
public class HttpGatewayRequestHandler extends AbstractHttpGatewayRequest {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGatewayRequestHandler.class);

    @Override
    protected <RequestBody> void requestHeadersHandler(MediaType customRequestContentType, RequestEntity<RequestBody> requestEntity, ClientHttpRequest clientHttpRequest) {
        HttpHeaders httpHeaders = clientHttpRequest.getHeaders();
        HttpHeaders requestHeaders = requestEntity.getHeaders();
        HttpHeaders newHttpHeaders = new HttpHeaders();
        if (null != httpHeaders && (!httpHeaders.isEmpty())) {
            newHttpHeaders.putAll(httpHeaders);
        }
        if (null != requestHeaders && (!requestHeaders.isEmpty())) {
            newHttpHeaders.putAll(requestHeaders);
        }
        if (!newHttpHeaders.isEmpty()) {
            MediaType contentType = newHttpHeaders.getContentType();
            if (null != contentType) {
                String defaultHeaderValues = contentType.toString();
                newHttpHeaders.set(DEFAULT_REQUEST_CONTENT_TYPE, defaultHeaderValues);
            }
            if (null != customRequestContentType) {
                String headerValues = customRequestContentType.toString();
                newHttpHeaders.set(CUSTOM_REQUEST_CONTENT_TYPE, headerValues);
                newHttpHeaders.setContentType(customRequestContentType);
            }
            httpHeaders.putAll(newHttpHeaders);
        }
        if (httpHeaders.getContentLength() == -1) {
            httpHeaders.setContentLength(0L);
        }
    }

    @Override
    protected void requestAcceptHeaderHandler(List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) {
        HttpHeaders httpHeaders = clientHttpRequest.getHeaders();
        List<MediaType> acceptHeader = httpHeaders.getAccept();
        if (CollectionUtils.isEmpty(acceptHeader)) {
            Set<MediaType> supportedMediaTypes = new HashSet<MediaType>();
            for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
                List<MediaType> mediaTypes = httpMessageConverter.getSupportedMediaTypes();
                if (!CollectionUtils.isEmpty(mediaTypes)) {
                    for (MediaType mediaType : mediaTypes) {
                        supportedMediaTypes.add(mediaType);
                    }
                }
            }
            List<MediaType> mediaTypeList = new ArrayList<MediaType>();
            mediaTypeList.addAll(supportedMediaTypes);
            if (!supportedMediaTypes.isEmpty()) {
                MediaType.sortBySpecificity(mediaTypeList);
                httpHeaders.setAccept(mediaTypeList);
            }
        }
    }

    @Override
    protected <RequestBody> void requestBodyHandler(RequestEntity<RequestBody> requestEntity, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpRequest clientHttpRequest) throws HttpGatewayException, IOException {
        if (null != requestEntity && requestEntity.hasBody()) {
            RequestBody requestBody = requestEntity.getBody();
            Class<RequestBody> requestBodyClass = (Class<RequestBody>) requestBody.getClass();
            HttpHeaders requestHeaders = clientHttpRequest.getHeaders();
            MediaType contentType = requestHeaders.getContentType();
            for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
                if (httpMessageConverter.canWrite(requestBodyClass, contentType)) {
                    ((HttpMessageConverter<RequestBody>) httpMessageConverter).write(requestBody, contentType, clientHttpRequest);
                    return;
                }
            }
            HttpGatewayException.exception("没有匹配合适的HTTP消息转换器支持写入数据操作,内容类型:%s,请求实体类型:%s.", contentType, requestBodyClass);
        }
    }

}
