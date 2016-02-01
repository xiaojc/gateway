package com.ohayoyo.gateway.client.components;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.define.core.EntityDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;
import java.util.*;

/**
 * 网关请求回调器
 */
public class GatewayRequestCallback extends AbstractGatewayComponent<RequestCallback> implements RequestCallback {

    private ClientHttpRequest clientHttpRequest;

    @Override
    public void doWithRequest(ClientHttpRequest request) throws IOException {

        this.setClientHttpRequest(request);

        GatewayConfig gatewayConfig = this.getGatewayConfig();
        GatewayRequest gatewayRequest = this.getGatewayRequest();
        GatewayDefine gatewayDefine = this.getGatewayDefine();
        ClientHttpRequest clientHttpRequest = this.getClientHttpRequest();

        try {
            this.doParameterHeaderRequest(gatewayConfig, gatewayRequest, gatewayDefine, clientHttpRequest);
            this.autoAcceptHeaderRequest(gatewayConfig, gatewayRequest, gatewayDefine, clientHttpRequest);
            this.doHttpEntityRequest(gatewayConfig, gatewayRequest, gatewayDefine, clientHttpRequest);
        } catch (GatewayException e) {
            throw new IOException(e);
        }
    }

    private void doParameterHeaderRequest(GatewayConfig gatewayConfig, GatewayRequest gatewayRequest, GatewayDefine gatewayDefine, ClientHttpRequest clientHttpRequest) throws GatewayException {
        MultiValueMap<String, String> requestHeaders = gatewayRequest.getRequestHeaders();
        if (!CollectionUtils.isEmpty(requestHeaders)) {
            HttpHeaders httpHeaders = clientHttpRequest.getHeaders();
            Set<Map.Entry<String, List<String>>> requestHeadersEntries = requestHeaders.entrySet();
            for (Map.Entry<String, List<String>> requestHeadersEntry : requestHeadersEntries) {
                String headerName = requestHeadersEntry.getKey();
                List<String> headerValues = requestHeadersEntry.getValue();
                if (!CollectionUtils.isEmpty(headerValues)) {
                    for (String headerValue : headerValues) {
                        if (StringUtils.isEmpty(headerValue)) {
                            continue;
                        }
                        httpHeaders.add(headerName, headerValue);
                    }
                }
            }
        }
    }

    private void autoAcceptHeaderRequest(GatewayConfig gatewayConfig, GatewayRequest gatewayRequest, GatewayDefine gatewayDefine, ClientHttpRequest clientHttpRequest) throws GatewayException {
        List<MediaType> accept = clientHttpRequest.getHeaders().getAccept(); //获取所支持接收的媒体类型
        if (CollectionUtils.isEmpty(accept)) { //如果不存在,自动设置所支持的媒体类型
            Set<MediaType> allSupportedMediaTypeSet = new HashSet<MediaType>();
            List<HttpMessageConverter<?>> httpMessageConverters = gatewayConfig.getHttpMessageConverters();
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

    private void doHttpEntityRequest(GatewayConfig gatewayConfig, GatewayRequest gatewayRequest, GatewayDefine gatewayDefine, ClientHttpRequest clientHttpRequest) throws GatewayException {
        HttpEntity<?> httpRequestEntity;
        Object requestEntityData = gatewayRequest.getRequestEntity();
        RequestDefine requestDefine = gatewayDefine.getRequest();
        EntityDefine entityDefine = requestDefine.getEntity();
        if (requestEntityData instanceof HttpEntity) {
            httpRequestEntity = (HttpEntity<?>) requestEntityData;
        } else if (requestEntityData != null) {
            httpRequestEntity = new HttpEntity<Object>(requestEntityData);
            if (null != entityDefine) {
                String entityDefineType = entityDefine.getType(); //必须不为空,前置检查验证
                MediaType entityDefineMediaType = MediaType.parseMediaType(entityDefineType); //解析媒体类型
                HttpHeaders httpHeaders = httpRequestEntity.getHeaders();// 获取实体请求报头
                httpHeaders.setContentType(entityDefineMediaType);//设置内容类型
            }
        } else {
            httpRequestEntity = HttpEntity.EMPTY;
        }
        if (!httpRequestEntity.hasBody()) { //如果没有请求实体
            HttpHeaders httpHeaders = clientHttpRequest.getHeaders(); //获取原来HTTP请求中的全部请求报头
            HttpHeaders requestHeaders = httpRequestEntity.getHeaders(); //获取原来请求实体中的全部请求报头
            if (!requestHeaders.isEmpty()) { //如果存在实体请求报头,进行合并
                httpHeaders.putAll(requestHeaders);
            }
            if (httpHeaders.getContentLength() == -1) {
                httpHeaders.setContentLength(0L);
            }
        } else {
            Object requestBodyData = httpRequestEntity.getBody();
            Class<?> requestType = requestBodyData.getClass();
            HttpHeaders requestHeaders = httpRequestEntity.getHeaders();
            MediaType requestContentType = requestHeaders.getContentType();
            for (HttpMessageConverter<?> messageConverter : gatewayConfig.getHttpMessageConverters()) {
                if (messageConverter.canWrite(requestType, requestContentType)) {
                    if (!requestHeaders.isEmpty()) {
                        this.getClientHttpRequest().getHeaders().putAll(requestHeaders);
                    }
                    try {
                        ((HttpMessageConverter<Object>) messageConverter).write(requestEntityData, requestContentType, clientHttpRequest);
                    } catch (IOException ioe) {
                        throw new GatewayException(ioe);
                    }
                    return;
                }
            }
            String message = "Could not write request: no suitable HttpMessageConverter found for request type [" + requestType.getName() + "]";
            if (requestContentType != null) {
                message += " and content type [" + requestContentType + "]";
            }
            throw new GatewayException(message);
        }
    }

    @Override
    public RequestCallback getComponent() throws GatewayException {
        return this;
    }

    public ClientHttpRequest getClientHttpRequest() {
        return clientHttpRequest;
    }

    public GatewayRequestCallback setClientHttpRequest(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
        return this;
    }

}
