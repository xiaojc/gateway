package com.ohayoyo.gateway.client.autofill;

import com.ohayoyo.gateway.client.core.AbstractContextAccessor;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.utils.ParameterDefineUtils;
import com.ohayoyo.gateway.define.core.Parameter;
import com.ohayoyo.gateway.define.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class ClientAutofill extends AbstractContextAccessor implements GatewayAutofill {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClientAutofill.class);

    private static String ENVIRONMENT_SELECT = null;

    protected String contextEnvironmentSelect() {
        if (StringUtils.isEmpty(ENVIRONMENT_SELECT)) {
            Environment environment = this.getGatewayContext().getEnvironment();
            ENVIRONMENT_SELECT = environment.getProperty(CLIENT_AUTOFILL_SELECT_ENVIRONMENT);
        }
        return ENVIRONMENT_SELECT;
    }

    protected void autofillRequestSelectData(GatewayRequest<?> gatewayRequest) {
        String select = gatewayRequest.getSelect();
        if (StringUtils.isEmpty(select)) {
            gatewayRequest.setSelect(contextEnvironmentSelect());
        }
    }

    protected void autofillRequestDataValues(ConversionService conversionService, Set<Parameter> parameters, Map<String, String> singleValueData, MultiValueMap<String, String> multiValueData) {
        if (CollectionUtils.isEmpty(parameters)) {
            return;
        }
        for (Parameter parameter : parameters) {
            Object defaultValue = parameter.getDefaultValue();
            if (ObjectUtils.isEmpty(defaultValue)) {
                continue;
            }
            String parameterName = parameter.getName();
//            Boolean parameterNullable = parameter.getNullable();
//            if (ObjectUtils.isEmpty(parameterNullable)) {
//                parameterNullable = true;
//            }
//            if ((!parameterNullable) && (null != singleValueData) && (!singleValueData.containsKey(parameterName))) {
//                String valueString = ParameterDefineUtils.resolveParameterDefaultValueToString(parameter, conversionService);
//                if (!StringUtils.isEmpty(valueString)) {
//                    singleValueData.put(parameterName, valueString);
//                }
//            } else if ((!parameterNullable) && (null != multiValueData) && (!multiValueData.containsKey(parameterName))) {
//                String valueString = ParameterDefineUtils.resolveParameterDefaultValueToString(parameter, conversionService);
//                if (!StringUtils.isEmpty(valueString)) {
//                    multiValueData.add(parameterName, valueString);
//                }
//            }
            if ((null != singleValueData) && (!singleValueData.containsKey(parameterName))) {
                String valueString = ParameterDefineUtils.resolveParameterDefaultValueToString(parameter, conversionService);
                if (!StringUtils.isEmpty(valueString)) {
                    singleValueData.put(parameterName, valueString);
                }
            } else if ((null != multiValueData) && (!multiValueData.containsKey(parameterName))) {
                String valueString = ParameterDefineUtils.resolveParameterDefaultValueToString(parameter, conversionService);
                if (!StringUtils.isEmpty(valueString)) {
                    multiValueData.add(parameterName, valueString);
                }
            }
        }
    }

    protected void autofillRequestPathData(ConversionService conversionService, RequestDefine requestDefine, GatewayRequest<?> gatewayRequest) {
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
        if (CollectionUtils.isEmpty(requestPathVariables)) {
            requestPathVariables = new HashMap<String, String>();
            gatewayRequest.setRequestPathVariables(requestPathVariables);
        }
        autofillRequestDataValues(conversionService, parameters, requestPathVariables, null);
    }

    protected void autofillRequestQueriesData(ConversionService conversionService, RequestDefine requestDefine, GatewayRequest<?> gatewayRequest) {
        QueriesDefine queriesDefine = requestDefine.getQueries();
        if (ObjectUtils.isEmpty(queriesDefine)) {
            return;
        }
        Set<Parameter> parameters = queriesDefine.getParameters();
        if (CollectionUtils.isEmpty(parameters)) {
            return;
        }
        MultiValueMap<String, String> requestQueries = gatewayRequest.getRequestQueries();
        if (CollectionUtils.isEmpty(requestQueries)) {
            requestQueries = new LinkedMultiValueMap<String, String>();
            gatewayRequest.setRequestQueries(requestQueries);
        }
        autofillRequestDataValues(conversionService, parameters, null, requestQueries);
    }

    protected void autofillRequestHeaders(ConversionService conversionService, RequestDefine requestDefine, GatewayRequest<?> gatewayRequest) {
        HeadersDefine headersDefine = requestDefine.getHeaders();
        if (ObjectUtils.isEmpty(headersDefine)) {
            return;
        }
        Set<Parameter> parameters = headersDefine.getParameters();
        if (CollectionUtils.isEmpty(parameters)) {
            return;
        }
        MultiValueMap<String, String> requestHeaders = gatewayRequest.getRequestHeaders();
        if (CollectionUtils.isEmpty(requestHeaders)) {
            requestHeaders = new LinkedMultiValueMap<String, String>();
            gatewayRequest.setRequestHeaders(requestHeaders);
        }
        autofillRequestDataValues(conversionService, parameters, null, requestHeaders);
    }

    @Override
    public <RequestBody> void autofill(RequestDefine requestDefine, GatewayRequest<RequestBody> gatewayRequest) {
        if (ObjectUtils.isEmpty(gatewayRequest)) {
            return;
        }
        ConversionService conversionService = this.getGatewayContext().getConversionService();
        autofillRequestSelectData(gatewayRequest);
        autofillRequestPathData(conversionService, requestDefine, gatewayRequest);
        autofillRequestQueriesData(conversionService, requestDefine, gatewayRequest);
        autofillRequestHeaders(conversionService, requestDefine, gatewayRequest);
    }

}
