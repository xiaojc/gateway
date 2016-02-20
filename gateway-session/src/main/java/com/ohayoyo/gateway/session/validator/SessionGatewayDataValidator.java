package com.ohayoyo.gateway.session.validator;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.core.GatewayField;
import com.ohayoyo.gateway.define.core.GatewayType;
import com.ohayoyo.gateway.define.http.*;
import com.ohayoyo.gateway.define.resolver.GatewayTypeResolver;
import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.session.core.GatewayContext;
import com.ohayoyo.gateway.session.core.GatewaySessionRequest;
import com.ohayoyo.gateway.session.exception.VerifySessionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
@SuppressWarnings("unchecked")
public class SessionGatewayDataValidator extends AbstractGatewayAccessor implements GatewayDataValidator {

    protected Map<String, GatewayType> validateRequiredRequestData(Set<GatewayField> fields, Map<String, ?> requestData, ConversionService conversionService, GatewayContainer container) {
        Map<String, GatewayType> requiredRequestData = new HashMap<String, GatewayType>();
        for (GatewayField field : fields) {
            Boolean nullable = field.getNullable();
            if (ObjectUtils.isEmpty(nullable)) {
                continue;
            }
            String name = field.getName();
            if (!nullable) {
                GatewayType type = field.getType();
                GatewayTypeResolver typeResolver = container.getTypeResolver();
                if (requestData.containsKey(name)) {
                    Class<?> defineDataTye = typeResolver.resolve(type);
                    Object dataValue = requestData.get(name);
                    if (!ObjectUtils.isEmpty(dataValue)) {
                        Class<?> valueDataTye = dataValue.getClass();
                        if (conversionService.canConvert(valueDataTye, defineDataTye) || defineDataTye.isAssignableFrom(valueDataTye)) {
                            continue;
                        }
                    }
                }
                requiredRequestData.put(name, type);
            }
        }
        return requiredRequestData;
    }

    protected void validateRequestPathVariablesData(GatewayInterface gatewayInterface, GatewaySessionRequest gatewaySessionRequest, ConversionService conversionService, GatewayContainer container) throws VerifySessionException {
        GatewayRequest request = gatewayInterface.getRequest();
        GatewayPath path = request.getPath();
        if (ObjectUtils.isEmpty(path)) {
            return;
        }
        GatewayVariables variables = path.getVariables();
        if (ObjectUtils.isEmpty(variables)) {
            return;
        }
        Set<GatewayField> fields = variables.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return;
        }
        Map<String, String> requestPathVariables = gatewaySessionRequest.getRequestPathVariables();
        Map<String, GatewayType> requiredRequestData = validateRequiredRequestData(fields, requestPathVariables, conversionService, container);
        if (!CollectionUtils.isEmpty(requiredRequestData)) {
            VerifySessionException.exception("请求路径值的必须数据不完全,信息:%s", requiredRequestData);
        }
    }

    protected void validateRequestQueriesData(GatewayInterface gatewayInterface, GatewaySessionRequest gatewaySessionRequest, ConversionService conversionService, GatewayContainer container) throws VerifySessionException {
        GatewayRequest request = gatewayInterface.getRequest();
        GatewayQueries queries = request.getQueries();
        if (ObjectUtils.isEmpty(queries)) {
            return;
        }
        Set<GatewayField> fields = queries.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return;
        }
        MultiValueMap<String, String> requestQueries = gatewaySessionRequest.getRequestQueries();
        Map<String, GatewayType> requiredRequestData = validateRequiredRequestData(fields, requestQueries, conversionService, container);
        if (!CollectionUtils.isEmpty(requiredRequestData)) {
            VerifySessionException.exception("请求查询参数的必须数据不完全,信息:%s", requiredRequestData);
        }
    }

    protected void validateRequestHeadersData(GatewayInterface gatewayInterface, GatewaySessionRequest gatewaySessionRequest, ConversionService conversionService, GatewayContainer container) throws VerifySessionException {
        GatewayRequest request = gatewayInterface.getRequest();
        GatewayHeaders headers = request.getHeaders();
        if (ObjectUtils.isEmpty(headers)) {
            return;
        }
        Set<GatewayField> fields = headers.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return;
        }
        MultiValueMap<String, String> requestHeaders = gatewaySessionRequest.getRequestHeaders();
        Map<String, GatewayType> requiredRequestData = validateRequiredRequestData(fields, requestHeaders, conversionService, container);
        if (!CollectionUtils.isEmpty(requiredRequestData)) {
            VerifySessionException.exception("请求头参数的必须数据不完全,信息:%s", requiredRequestData);
        }
    }

    @Override
    public void validate(GatewayInterface gatewayInterface, GatewaySessionRequest gatewaySessionRequest) throws VerifySessionException {
        GatewayContext gatewayContext = this.getGatewayContext();
        ConversionService conversionService = gatewayContext.getConversionService();
        GatewayContainer container = gatewayContext.getGatewayContainer();
        validateRequestPathVariablesData(gatewayInterface, gatewaySessionRequest, conversionService, container);
        validateRequestQueriesData(gatewayInterface, gatewaySessionRequest, conversionService, container);
        validateRequestHeadersData(gatewayInterface, gatewaySessionRequest, conversionService, container);
    }

}
