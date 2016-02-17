package com.ohayoyo.gateway.client.restful.builder;

import com.ohayoyo.gateway.client.restful.RestfulResponse;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.*;

import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class RestfulResponseBuilder {

    private ConversionService conversionService;

    private Integer statusCode;

    private String reasonPhrase;

    private MultiValueMap<String, String> responseHeaders;

    private Object responseBody;

    public RestfulResponseBuilder() {
    }

    public static RestfulResponseBuilder newInstance() {
        return new RestfulResponseBuilder();
    }

    public RestfulResponseBuilder conversionService(ConversionService conversionService) {
        Assert.notNull(conversionService);
        this.conversionService = conversionService;
        return this;
    }

    private void checkConversionService() {
        if (ObjectUtils.isEmpty(this.conversionService)) {
            this.conversionService = new DefaultFormattingConversionService();
        }
    }

    public RestfulResponseBuilder statusCode(int statusCode) {
        Assert.state(statusCode > 0 || statusCode < 600);
        this.statusCode = statusCode;
        return this;
    }

    public RestfulResponseBuilder reasonPhrase(String reasonPhrase) {
        Assert.notNull(reasonPhrase);
        this.reasonPhrase = reasonPhrase;
        return this;
    }

    public RestfulResponseBuilder responseHeaders(MultiValueMap<String, String> responseHeaders) {
        Assert.notEmpty(responseHeaders);
        if (ObjectUtils.isEmpty(this.responseHeaders)) {
            this.responseHeaders = new LinkedMultiValueMap<String, String>();
        }
        this.responseHeaders.putAll(responseHeaders);
        return this;
    }

    public RestfulResponseBuilder responseHeaders(Map<String, Object> responseHeaders) {
        Assert.notEmpty(responseHeaders);
        if (CollectionUtils.isEmpty(this.responseHeaders)) {
            this.responseHeaders = new LinkedMultiValueMap<String, String>();
        }
        Set<Map.Entry<String, Object>> responseHeaderEntries = responseHeaders.entrySet();
        for (Map.Entry<String, Object> responseHeaderEntry : responseHeaderEntries) {
            String key = responseHeaderEntry.getKey();
            Object value = responseHeaderEntry.getValue();
            if (!ObjectUtils.isEmpty(value)) {
                Class<?> sourceType = value.getClass();
                Class<String> targetType = String.class;
                checkConversionService();
                if (this.conversionService.canConvert(sourceType, targetType)) {
                    String responseHeaderValue = this.conversionService.convert(value, targetType);
                    this.responseHeaders.add(key, responseHeaderValue);
                }
            }
        }
        return this;
    }

    public RestfulResponseBuilder responseHeaders(String responseHeaderKey, String responseHeaderValue) {
        Assert.notNull(responseHeaderKey);
        if (CollectionUtils.isEmpty(this.responseHeaders)) {
            this.responseHeaders = new LinkedMultiValueMap<String, String>();
        }
        this.responseHeaders.add(responseHeaderKey, responseHeaderValue);
        return this;
    }

    public RestfulResponseBuilder responseHeaders(String responseHeaderKey, Object responseHeaderValueObject) {
        Assert.notNull(responseHeaderKey);
        Assert.notNull(responseHeaderValueObject);
        if (CollectionUtils.isEmpty(this.responseHeaders)) {
            this.responseHeaders = new LinkedMultiValueMap<String, String>();
        }
        Class<?> sourceType = responseHeaderValueObject.getClass();
        Class<String> targetType = String.class;
        checkConversionService();
        if (this.conversionService.canConvert(sourceType, targetType)) {
            String requestHeaderValueString = this.conversionService.convert(responseHeaderValueObject, targetType);
            this.responseHeaders.add(responseHeaderKey, requestHeaderValueString);
        }
        return this;
    }

    public RestfulResponseBuilder responseBody(Object responseBody) {
        Assert.notNull(responseBody);
        this.responseBody = responseBody;
        return this;
    }

    public <ResponseBody> RestfulResponse<ResponseBody> build() {
        return new RestfulResponse<ResponseBody>()
                .setStatusCode(this.statusCode)
                .setReasonPhrase(this.reasonPhrase)
                .setResponseHeaders(this.responseHeaders)
                .setResponseBody((ResponseBody) this.responseBody);
    }

}
