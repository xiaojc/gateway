package com.ohayoyo.gateway.client.parts;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.utils.MapUtil;
import com.ohayoyo.gateway.client.utils.MediaTypeUtil;
import com.ohayoyo.gateway.client.utils.ResponseTypeUtil;
import com.ohayoyo.gateway.define.core.EntityDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RequestCallbackPart extends AbstractGatewayPart<RequestCallback> implements RequestCallback {

    private static final String HTTP_HEADER_VALUE_AS_MAP_DELIMITER = ",";

    @Override
    public RequestCallback getPart() throws GatewayException {
        return this;
    }

    @Override
    public void doWithRequest(ClientHttpRequest request) throws IOException {

        this.setClientHttpRequest(request);

        GatewayConfig gatewayConfig = this.getGatewayConfig();
        GatewayRequest gatewayRequest = this.getGatewayRequest();
        GatewayDefine gatewayDefine = this.getGatewayDefine();
        ClientHttpRequest clientHttpRequest = this.getClientHttpRequest();
        ClientHttpResponse clientHttpResponse = this.getClientHttpResponse();

        try {
            this.doParameterHeaderRequest(gatewayConfig, gatewayRequest, gatewayDefine, clientHttpRequest, clientHttpResponse);
            this.doAcceptHeaderRequest(gatewayConfig, gatewayRequest, gatewayDefine, clientHttpRequest, clientHttpResponse);
            this.doHttpEntityRequest(gatewayConfig, gatewayRequest, gatewayDefine, clientHttpRequest, clientHttpResponse);
        } catch (GatewayException e) {
            throw new IOException(e);
        }
    }

    private void doParameterHeaderRequest(GatewayConfig gatewayConfig, GatewayRequest gatewayRequest, GatewayDefine gatewayDefine, ClientHttpRequest clientHttpRequest, ClientHttpResponse clientHttpResponse) throws GatewayException {
        Map<String, Object> requestHeadersData = gatewayRequest.getRequestHeaders();
        if (!CollectionUtils.isEmpty(requestHeadersData)) {
            HttpHeaders httpHeaders = clientHttpRequest.getHeaders();
            Map<String, List<String>> requestHeaders = MapUtil.stringObjectMapToStringListMap(requestHeadersData, HTTP_HEADER_VALUE_AS_MAP_DELIMITER);
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

    private void doAcceptHeaderRequest(GatewayConfig gatewayConfig, GatewayRequest gatewayRequest, GatewayDefine gatewayDefine, ClientHttpRequest clientHttpRequest, ClientHttpResponse clientHttpResponse) throws GatewayException {
        Type responseType = ResponseTypeUtil.resolve(gatewayConfig, gatewayRequest, gatewayDefine, clientHttpRequest, clientHttpResponse);
        if (null != responseType) {
            Class<?> responseClass = null;
            if (responseType instanceof Class) {
                responseClass = (Class<?>) responseType;
            }
            List<MediaType> allSupportedMediaTypes = new ArrayList<MediaType>();
            for (HttpMessageConverter<?> converter : gatewayConfig.getHttpMessageConverters()) {
                if (null != responseClass) {
                    if (converter.canRead(responseClass, null)) {
                        allSupportedMediaTypes.addAll(MediaTypeUtil.supportedMediaTypes(converter));
                    }
                } else if (converter instanceof GenericHttpMessageConverter) {
                    GenericHttpMessageConverter<?> genericConverter = (GenericHttpMessageConverter<?>) converter;
                    if (genericConverter.canRead(responseType, null, null)) {
                        allSupportedMediaTypes.addAll(MediaTypeUtil.supportedMediaTypes(converter));
                    }
                }
            }
            if (!allSupportedMediaTypes.isEmpty()) {
                MediaType.sortBySpecificity(allSupportedMediaTypes);
                clientHttpRequest.getHeaders().setAccept(allSupportedMediaTypes);
            }
        }
    }

    private void doHttpEntityRequest(GatewayConfig gatewayConfig, GatewayRequest gatewayRequest, GatewayDefine gatewayDefine, ClientHttpRequest clientHttpRequest, ClientHttpResponse clientHttpResponse) throws GatewayException {
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

}
