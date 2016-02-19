package com.ohayoyo.gateway.client.restful.builder;

import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.restful.RestfulRequest;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class RestfulRequestBuilder {

    private String select;

    private Map<String, String> requestPathVariables;

    private MultiValueMap<String, String> requestQueries;

    private MultiValueMap<String, String> requestHeaders;

    private Object requestBody;

    private GatewayContext gatewayContext;

    public RestfulRequestBuilder select(String select) {
        this.select = select;
        return this;
    }

    public RestfulRequestBuilder requestPathVariables(Map<String, String> requestPathVariables) {
        Assert.notEmpty(requestPathVariables);
        if (CollectionUtils.isEmpty(this.requestPathVariables)) {
            this.requestPathVariables = new HashMap<String, String>();
        }
        this.requestPathVariables.putAll(requestPathVariables);
        return this;
    }

    public RestfulRequestBuilder requestPathVariables(String requestPathVariableKey, String requestPathVariableValue) {
        Assert.notNull(requestPathVariableKey);
        if (ObjectUtils.isEmpty(this.requestPathVariables)) {
            this.requestPathVariables = new HashMap<String, String>();
        }
        this.requestPathVariables.put(requestPathVariableKey, requestPathVariableValue);
        return this;
    }

    public RestfulRequestBuilder requestPathVariables(String requestPathVariableKey, Object requestPathVariableValueObject) {
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

    public RestfulRequestBuilder requestQueries(MultiValueMap<String, String> requestQueries) {
        Assert.notEmpty(requestQueries);
        if (ObjectUtils.isEmpty(this.requestQueries)) {
            this.requestQueries = new LinkedMultiValueMap<String, String>();
        }
        this.requestQueries.putAll(requestQueries);
        return this;
    }

    public RestfulRequestBuilder requestQueries(Map<String, Object> requestQueries) {
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

    public RestfulRequestBuilder requestQueries(String requestQueryKey, String requestQueryValue) {
        Assert.notNull(requestQueryKey);
        if (CollectionUtils.isEmpty(this.requestQueries)) {
            this.requestQueries = new LinkedMultiValueMap<String, String>();
        }
        this.requestQueries.add(requestQueryKey, requestQueryValue);
        return this;
    }

    public RestfulRequestBuilder requestQueries(String requestQueryKey, Object requestQueryValueObject) {
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

    public RestfulRequestBuilder requestHeaders(MultiValueMap<String, String> requestHeaders) {
        Assert.notEmpty(requestHeaders);
        if (ObjectUtils.isEmpty(this.requestHeaders)) {
            this.requestHeaders = new LinkedMultiValueMap<String, String>();
        }
        this.requestHeaders.putAll(requestHeaders);
        return this;
    }

    public RestfulRequestBuilder requestHeaders(Map<String, Object> requestHeaders) {
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

    public RestfulRequestBuilder requestHeaders(String requestHeaderKey, String requestHeaderValue) {
        Assert.notNull(requestHeaderKey);
        if (CollectionUtils.isEmpty(this.requestHeaders)) {
            this.requestHeaders = new LinkedMultiValueMap<String, String>();
        }
        this.requestHeaders.add(requestHeaderKey, requestHeaderValue);
        return this;
    }

    public RestfulRequestBuilder requestHeaders(String requestHeaderKey, Object requestHeaderValueObject) {
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

    public RestfulRequestBuilder requestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <RequestBody> RestfulRequest<RequestBody> build() {
        return new RestfulRequest<RequestBody>()
                .setSelect(this.select)
                .setRequestPathVariables(this.requestPathVariables)
                .setRequestQueries(this.requestQueries)
                .setRequestHeaders(this.requestHeaders)
                .setRequestBody((RequestBody) this.requestBody);
    }

    public GatewayContext getGatewayContext() {
        return gatewayContext;
    }

    public RestfulRequestBuilder setGatewayContext(GatewayContext gatewayContext) {
        this.gatewayContext = gatewayContext;
        return this;
    }

}
