package com.ohayoyo.gateway.client.restful;

import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class RestfulGatewayRequestBuilder {

    private ConversionService conversionService;

    private String select;

    private Map<String, String> requestPathVariables;

    private MultiValueMap<String, String> requestQueries;

    private MultiValueMap<String, String> requestHeaders;

    private Object requestBody;

    public RestfulGatewayRequestBuilder() {
    }

    public static RestfulGatewayRequestBuilder newInstance() {
        return new RestfulGatewayRequestBuilder();
    }

    public RestfulGatewayRequestBuilder conversionService(ConversionService conversionService) {
        Assert.notNull(conversionService);
        this.conversionService = conversionService;
        return this;
    }

    private void checkConversionService() {
        if (ObjectUtils.isEmpty(this.conversionService)) {
            this.conversionService = new DefaultFormattingConversionService();
        }
    }

    public RestfulGatewayRequestBuilder select(String select) {
        this.select = select;
        return this;
    }

    public RestfulGatewayRequestBuilder requestPathVariables(Map<String, String> requestPathVariables) {
        Assert.notEmpty(requestPathVariables);
        if (CollectionUtils.isEmpty(this.requestPathVariables)) {
            this.requestPathVariables = new HashMap<String, String>();
        }
        this.requestPathVariables.putAll(requestPathVariables);
        return this;
    }

    public RestfulGatewayRequestBuilder requestPathVariables(String requestPathVariableKey, String requestPathVariableValue) {
        Assert.notNull(requestPathVariableKey);
        if (ObjectUtils.isEmpty(this.requestPathVariables)) {
            this.requestPathVariables = new HashMap<String, String>();
        }
        this.requestPathVariables.put(requestPathVariableKey, requestPathVariableValue);
        return this;
    }

    public RestfulGatewayRequestBuilder requestPathVariables(String requestPathVariableKey, Object requestPathVariableValueObject) {
        Assert.notNull(requestPathVariableKey);
        Assert.notNull(requestPathVariableValueObject);
        Class<?> sourceType = requestPathVariableValueObject.getClass();
        Class<String> targetType = String.class;
        checkConversionService();
        if (this.conversionService.canConvert(sourceType, targetType)) {
            String requestPathVariableValueString = this.conversionService.convert(requestPathVariableValueObject, targetType);
            this.requestPathVariables.put(requestPathVariableKey, requestPathVariableValueString);
        }
        return this;
    }

    public RestfulGatewayRequestBuilder requestQueries(MultiValueMap<String, String> requestQueries) {
        Assert.notEmpty(requestQueries);
        if (ObjectUtils.isEmpty(this.requestQueries)) {
            this.requestQueries = new LinkedMultiValueMap<String, String>();
        }
        this.requestQueries.putAll(requestQueries);
        return this;
    }

    public RestfulGatewayRequestBuilder requestQueries(Map<String, Object> requestQueries) {
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
                checkConversionService();
                if (this.conversionService.canConvert(sourceType, targetType)) {
                    String requestQueryValue = this.conversionService.convert(value, targetType);
                    this.requestQueries.add(key, requestQueryValue);
                }
            }
        }
        return this;
    }

    public RestfulGatewayRequestBuilder requestQueries(String requestQueryKey, String requestQueryValue) {
        Assert.notNull(requestQueryKey);
        if (CollectionUtils.isEmpty(this.requestQueries)) {
            this.requestQueries = new LinkedMultiValueMap<String, String>();
        }
        this.requestQueries.add(requestQueryKey, requestQueryValue);
        return this;
    }

    public RestfulGatewayRequestBuilder requestQueries(String requestQueryKey, Object requestQueryValueObject) {
        Assert.notNull(requestQueryKey);
        Assert.notNull(requestQueryValueObject);
        if (CollectionUtils.isEmpty(this.requestQueries)) {
            this.requestQueries = new LinkedMultiValueMap<String, String>();
        }
        Class<?> sourceType = requestQueryValueObject.getClass();
        Class<String> targetType = String.class;
        checkConversionService();
        if (this.conversionService.canConvert(sourceType, targetType)) {
            String requestQueryValueString = this.conversionService.convert(requestQueryValueObject, targetType);
            this.requestQueries.add(requestQueryKey, requestQueryValueString);
        }
        return this;
    }

    public RestfulGatewayRequestBuilder requestHeaders(MultiValueMap<String, String> requestHeaders) {
        Assert.notEmpty(requestHeaders);
        if (ObjectUtils.isEmpty(this.requestHeaders)) {
            this.requestHeaders = new LinkedMultiValueMap<String, String>();
        }
        this.requestHeaders.putAll(requestHeaders);
        return this;
    }

    public RestfulGatewayRequestBuilder requestHeaders(Map<String, Object> requestHeaders) {
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
                checkConversionService();
                if (this.conversionService.canConvert(sourceType, targetType)) {
                    String requestHeaderValue = this.conversionService.convert(value, targetType);
                    this.requestHeaders.add(key, requestHeaderValue);
                }
            }
        }
        return this;
    }

    public RestfulGatewayRequestBuilder requestHeaders(String requestHeaderKey, String requestHeaderValue) {
        Assert.notNull(requestHeaderKey);
        if (CollectionUtils.isEmpty(this.requestHeaders)) {
            this.requestHeaders = new LinkedMultiValueMap<String, String>();
        }
        this.requestHeaders.add(requestHeaderKey, requestHeaderValue);
        return this;
    }

    public RestfulGatewayRequestBuilder requestHeaders(String requestHeaderKey, Object requestHeaderValueObject) {
        Assert.notNull(requestHeaderKey);
        Assert.notNull(requestHeaderValueObject);
        if (CollectionUtils.isEmpty(this.requestHeaders)) {
            this.requestHeaders = new LinkedMultiValueMap<String, String>();
        }
        Class<?> sourceType = requestHeaderValueObject.getClass();
        Class<String> targetType = String.class;
        checkConversionService();
        if (this.conversionService.canConvert(sourceType, targetType)) {
            String requestHeaderValueString = this.conversionService.convert(requestHeaderValueObject, targetType);
            this.requestHeaders.add(requestHeaderKey, requestHeaderValueString);
        }
        return this;
    }

    public RestfulGatewayRequestBuilder requestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public <RequestBody> RestfulGatewayRequest<RequestBody> build() {
        return new RestfulGatewayRequest<RequestBody>()
                .setSelect(this.select)
                .setRequestPathVariables(this.requestPathVariables)
                .setRequestQueries(this.requestQueries)
                .setRequestHeaders(this.requestHeaders)
                .setRequestBody((RequestBody) this.requestBody);
    }

}
