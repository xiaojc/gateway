package com.ohayoyo.gateway.session.builder;

import com.ohayoyo.gateway.session.core.GatewayContext;
import com.ohayoyo.gateway.session.restful.RestfulSessionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class RestfulSessionRequestBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulSessionRequestBuilder.class);

    private String select;

    private Map<String, String> requestPathVariables;

    private MultiValueMap<String, String> requestQueries;

    private MultiValueMap<String, String> requestHeaders;

    private Object requestBody;

    private GatewayContext gatewayContext;

    public RestfulSessionRequestBuilder select(String select) {
        this.select = select;
        return this;
    }

    public RestfulSessionRequestBuilder requestPathVariables(Map<String, String> requestPathVariables) {
        Assert.notEmpty(requestPathVariables);
        if (CollectionUtils.isEmpty(this.requestPathVariables)) {
            this.requestPathVariables = new HashMap<String, String>();
        }
        this.requestPathVariables.putAll(requestPathVariables);
        return this;
    }

    public RestfulSessionRequestBuilder requestPathVariables(String requestPathVariableKey, String requestPathVariableValue) {
        Assert.notNull(requestPathVariableKey);
        if (ObjectUtils.isEmpty(this.requestPathVariables)) {
            this.requestPathVariables = new HashMap<String, String>();
        }
        this.requestPathVariables.put(requestPathVariableKey, requestPathVariableValue);
        return this;
    }

    public RestfulSessionRequestBuilder requestPathVariables(String requestPathVariableKey, Object requestPathVariableValueObject) {
        Assert.notNull(requestPathVariableKey);
        Assert.notNull(requestPathVariableValueObject);
        Class<?> sourceType = requestPathVariableValueObject.getClass();
        Class<String> targetType = String.class;
        ConversionService conversionService = gatewayContext.getConversionService();
        if (conversionService.canConvert(sourceType, targetType)) {
            String requestPathVariableValueString = conversionService.convert(requestPathVariableValueObject, targetType);
            this.requestPathVariables.put(requestPathVariableKey, requestPathVariableValueString);
        }
        return this;
    }

    public RestfulSessionRequestBuilder requestQueries(MultiValueMap<String, String> requestQueries) {
        Assert.notEmpty(requestQueries);
        if (ObjectUtils.isEmpty(this.requestQueries)) {
            this.requestQueries = new LinkedMultiValueMap<String, String>();
        }
        this.requestQueries.putAll(requestQueries);
        return this;
    }

    public RestfulSessionRequestBuilder requestQueries(Map<String, Object> requestQueries) {
        Assert.notEmpty(requestQueries);
        if (CollectionUtils.isEmpty(this.requestQueries)) {
            this.requestQueries = new LinkedMultiValueMap<String, String>();
        }
        Set<Map.Entry<String, Object>> requestQueryEntries = requestQueries.entrySet();
        for (Map.Entry<String, Object> requestQueryEntry : requestQueryEntries) {
            String key = requestQueryEntry.getKey();
            Object value = requestQueryEntry.getValue();
            if (!ObjectUtils.isEmpty(value)) {
                Class<?> sourceType = value.getClass();
                Class<String> targetType = String.class;
                ConversionService conversionService = gatewayContext.getConversionService();
                if (conversionService.canConvert(sourceType, targetType)) {
                    String requestQueryValue = conversionService.convert(value, targetType);
                    this.requestQueries.add(key, requestQueryValue);
                }
            }
        }
        return this;
    }

    public RestfulSessionRequestBuilder requestQueries(String requestQueryKey, String requestQueryValue) {
        Assert.notNull(requestQueryKey);
        if (CollectionUtils.isEmpty(this.requestQueries)) {
            this.requestQueries = new LinkedMultiValueMap<String, String>();
        }
        this.requestQueries.add(requestQueryKey, requestQueryValue);
        return this;
    }

    public RestfulSessionRequestBuilder requestQueries(String requestQueryKey, Object requestQueryValueObject) {
        Assert.notNull(requestQueryKey);
        Assert.notNull(requestQueryValueObject);
        if (CollectionUtils.isEmpty(this.requestQueries)) {
            this.requestQueries = new LinkedMultiValueMap<String, String>();
        }
        Class<?> sourceType = requestQueryValueObject.getClass();
        Class<String> targetType = String.class;
        ConversionService conversionService = gatewayContext.getConversionService();
        if (conversionService.canConvert(sourceType, targetType)) {
            String requestQueryValueString = conversionService.convert(requestQueryValueObject, targetType);
            this.requestQueries.add(requestQueryKey, requestQueryValueString);
        }
        return this;
    }

    public RestfulSessionRequestBuilder requestHeaders(MultiValueMap<String, String> requestHeaders) {
        Assert.notEmpty(requestHeaders);
        if (ObjectUtils.isEmpty(this.requestHeaders)) {
            this.requestHeaders = new LinkedMultiValueMap<String, String>();
        }
        this.requestHeaders.putAll(requestHeaders);
        return this;
    }

    public RestfulSessionRequestBuilder requestHeaders(Map<String, Object> requestHeaders) {
        Assert.notEmpty(requestHeaders);
        if (CollectionUtils.isEmpty(this.requestHeaders)) {
            this.requestHeaders = new LinkedMultiValueMap<String, String>();
        }
        Set<Map.Entry<String, Object>> requestHeaderEntries = requestHeaders.entrySet();
        for (Map.Entry<String, Object> requestHeaderEntry : requestHeaderEntries) {
            String key = requestHeaderEntry.getKey();
            Object value = requestHeaderEntry.getValue();
            if (!ObjectUtils.isEmpty(value)) {
                Class<?> sourceType = value.getClass();
                Class<String> targetType = String.class;
                ConversionService conversionService = gatewayContext.getConversionService();
                if (conversionService.canConvert(sourceType, targetType)) {
                    String requestHeaderValue = conversionService.convert(value, targetType);
                    this.requestHeaders.add(key, requestHeaderValue);
                }
            }
        }
        return this;
    }

    public RestfulSessionRequestBuilder requestHeaders(String requestHeaderKey, String requestHeaderValue) {
        Assert.notNull(requestHeaderKey);
        if (CollectionUtils.isEmpty(this.requestHeaders)) {
            this.requestHeaders = new LinkedMultiValueMap<String, String>();
        }
        this.requestHeaders.add(requestHeaderKey, requestHeaderValue);
        return this;
    }

    public RestfulSessionRequestBuilder requestHeaders(String requestHeaderKey, Object requestHeaderValueObject) {
        Assert.notNull(requestHeaderKey);
        Assert.notNull(requestHeaderValueObject);
        if (CollectionUtils.isEmpty(this.requestHeaders)) {
            this.requestHeaders = new LinkedMultiValueMap<String, String>();
        }
        Class<?> sourceType = requestHeaderValueObject.getClass();
        Class<String> targetType = String.class;
        ConversionService conversionService = gatewayContext.getConversionService();
        if (conversionService.canConvert(sourceType, targetType)) {
            String requestHeaderValueString = conversionService.convert(requestHeaderValueObject, targetType);
            this.requestHeaders.add(requestHeaderKey, requestHeaderValueString);
        }
        return this;
    }

    public RestfulSessionRequestBuilder requestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <RequestBody> RestfulSessionRequest<RequestBody> build() {
        RestfulSessionRequest<RequestBody> restfulRequest = new RestfulSessionRequest<RequestBody>();
        restfulRequest.setSelect(this.select);
        restfulRequest.setRequestPathVariables(this.requestPathVariables);
        restfulRequest.setRequestQueries(this.requestQueries);
        restfulRequest.setRequestHeaders(this.requestHeaders);
        restfulRequest.setRequestBody((RequestBody) this.requestBody);
        return restfulRequest;
    }

    public GatewayContext getGatewayContext() {
        return gatewayContext;
    }

    public RestfulSessionRequestBuilder setGatewayContext(GatewayContext gatewayContext) {
        this.gatewayContext = gatewayContext;
        return this;
    }

}
