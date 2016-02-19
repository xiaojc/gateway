package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.core.AbstractContextAccessor;
import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.exception.ValidatorException;
import com.ohayoyo.gateway.define.core.Parameter;
import com.ohayoyo.gateway.define.core.ResolveDataType;
import com.ohayoyo.gateway.define.http.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class ClientDataValidator extends AbstractContextAccessor implements GatewayDataValidator {

    protected Map<String, String> validateRequiredRequestData(Set<Parameter> parameters, Map<String, ?> requestData, ConversionService conversionService) {
        Map<String, String> requiredRequestData = new HashMap<String, String>();
        for (Parameter parameter : parameters) {
            Boolean parameterNullable = parameter.getNullable();
            if (ObjectUtils.isEmpty(parameterNullable)) {
                continue;
            }
            String parameterName = parameter.getName();
            if (!parameterNullable) {
                String dataType = parameter.getDataType();
                String referenceClass = parameter.getReferenceClass();
                if (requestData.containsKey(parameterName) && (!StringUtils.isEmpty(dataType))) {
                    Class<?> defineDataTye = ResolveDataType.DEFAULT.resolve(dataType, referenceClass);
                    Object dataValue = requestData.get(parameterName);
                    if (!ObjectUtils.isEmpty(dataValue)) {
                        Class<?> valueDataTye = dataValue.getClass();
                        if (conversionService.canConvert(valueDataTye, defineDataTye) || defineDataTye.isAssignableFrom(valueDataTye)) {
                            continue;
                        }
                    }
                }
                requiredRequestData.put(parameterName, dataType);
            }
        }
        return requiredRequestData;
    }

    protected void validateRequestPathVariablesData(GatewayDefine gatewayDefine, GatewayRequest<?> gatewayRequest, ConversionService conversionService) throws ValidatorException {
        RequestDefine requestDefine = gatewayDefine.getRequest();
        PathDefine pathDefine = requestDefine.getPath();
        if (ObjectUtils.isEmpty(pathDefine)) {
            return;
        }
        VariablesDefine variablesDefine = pathDefine.getVariables();
        if (ObjectUtils.isEmpty(variablesDefine)) {
            return;
        }
        Set<Parameter> parameters = variablesDefine.getParameters();
        if (CollectionUtils.isEmpty(parameters)) {
            return;
        }
        Map<String, String> requestPathVariables = gatewayRequest.getRequestPathVariables();
        Map<String, String> requiredRequestData = validateRequiredRequestData(parameters, requestPathVariables, conversionService);
        if (!CollectionUtils.isEmpty(requiredRequestData)) {
            ValidatorException.exception("请求路径值的必须数据不完全,信息:%s", requiredRequestData);
        }
    }

    protected void validateRequestQueriesData(GatewayDefine gatewayDefine, GatewayRequest<?> gatewayRequest, ConversionService conversionService) throws ValidatorException {
        RequestDefine requestDefine = gatewayDefine.getRequest();
        QueriesDefine queriesDefine = requestDefine.getQueries();
        if (ObjectUtils.isEmpty(queriesDefine)) {
            return;
        }
        Set<Parameter> parameters = queriesDefine.getParameters();
        if (CollectionUtils.isEmpty(parameters)) {
            return;
        }
        MultiValueMap<String, String> requestQueries = gatewayRequest.getRequestQueries();
        Map<String, String> requiredRequestData = validateRequiredRequestData(parameters, requestQueries, conversionService);
        if (!CollectionUtils.isEmpty(requiredRequestData)) {
            ValidatorException.exception("请求查询参数的必须数据不完全,信息:%s", requiredRequestData);
        }
    }

    protected void validateRequestHeadersData(GatewayDefine gatewayDefine, GatewayRequest<?> gatewayRequest, ConversionService conversionService) throws ValidatorException {
        RequestDefine requestDefine = gatewayDefine.getRequest();
        HeadersDefine headersDefine = requestDefine.getHeaders();
        if (ObjectUtils.isEmpty(headersDefine)) {
            return;
        }
        Set<Parameter> parameters = headersDefine.getParameters();
        if (CollectionUtils.isEmpty(parameters)) {
            return;
        }
        MultiValueMap<String, String> requestHeaders = gatewayRequest.getRequestHeaders();
        Map<String, String> requiredRequestData = validateRequiredRequestData(parameters, requestHeaders, conversionService);
        if (!CollectionUtils.isEmpty(requiredRequestData)) {
            ValidatorException.exception("请求头参数的必须数据不完全,信息:%s", requiredRequestData);
        }
    }

    @Override
    public void validate(GatewayDefine gatewayDefine, GatewayRequest<?> gatewayRequest) throws ValidatorException {
        GatewayContext gatewayContext = this.getGatewayContext();
        ConversionService conversionService = gatewayContext.getConversionService();
        validateRequestPathVariablesData(gatewayDefine, gatewayRequest, conversionService);
        validateRequestQueriesData(gatewayDefine, gatewayRequest, conversionService);
        validateRequestHeadersData(gatewayDefine, gatewayRequest, conversionService);
    }

}
