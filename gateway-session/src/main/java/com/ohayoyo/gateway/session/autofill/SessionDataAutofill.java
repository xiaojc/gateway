package com.ohayoyo.gateway.session.autofill;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.core.GatewayField;
import com.ohayoyo.gateway.define.http.*;
import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.session.core.GatewayContext;
import com.ohayoyo.gateway.session.core.GatewaySessionRequest;
import com.ohayoyo.gateway.session.utils.GatewayFieldUtils;
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
@SuppressWarnings("unchecked")
public class SessionDataAutofill extends AbstractGatewayAccessor implements GatewayDataAutofill {

    public static final Logger LOGGER = LoggerFactory.getLogger(SessionDataAutofill.class);

    private static String ENVIRONMENT_SELECT = null;

    protected String contextEnvironmentSelect() {
        if (StringUtils.isEmpty(ENVIRONMENT_SELECT)) {
            Environment environment = this.getGatewayContext().getEnvironment();
            ENVIRONMENT_SELECT = environment.getProperty(CLIENT_AUTOFILL_SELECT_ENVIRONMENT);
        }
        return ENVIRONMENT_SELECT;
    }

    protected void autofillRequestSelectData(GatewaySessionRequest gatewaySessionRequest) {
        String select = gatewaySessionRequest.getSelect();
        if (StringUtils.isEmpty(select)) {
            gatewaySessionRequest.setSelect(contextEnvironmentSelect());
        }
    }

    protected void autofillRequestDataValues(ConversionService conversionService, GatewayContainer container, Set<GatewayField> fields, Map<String, String> singleValueData, MultiValueMap<String, String> multiValueData) {
        if (CollectionUtils.isEmpty(fields)) {
            return;
        }
        for (GatewayField field : fields) {
            Object defaultValue = field.getDefaultValue();
            if (ObjectUtils.isEmpty(defaultValue)) {
                continue;
            }
            String name = field.getName();
            if ((null != singleValueData) && (!singleValueData.containsKey(name))) {
                String valueString = GatewayFieldUtils.resolveFieldDefaultValueToString(field, conversionService, container);
                if (!StringUtils.isEmpty(valueString)) {
                    singleValueData.put(name, valueString);
                }
            } else if ((null != multiValueData) && (!multiValueData.containsKey(name))) {
                String valueString = GatewayFieldUtils.resolveFieldDefaultValueToString(field, conversionService, container);
                if (!StringUtils.isEmpty(valueString)) {
                    multiValueData.add(name, valueString);
                }
            }
        }
    }

    protected void autofillRequestPathData(ConversionService conversionService, GatewayRequest requestDefine, GatewaySessionRequest gatewaySessionRequest, GatewayContainer container) {
        GatewayPath path = requestDefine.getPath();
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
        if (CollectionUtils.isEmpty(requestPathVariables)) {
            requestPathVariables = new HashMap<String, String>();
            gatewaySessionRequest.setRequestPathVariables(requestPathVariables);
        }
        autofillRequestDataValues(conversionService, container, fields, requestPathVariables, null);
    }

    protected void autofillRequestQueriesData(ConversionService conversionService, GatewayRequest requestDefine, GatewaySessionRequest gatewaySessionRequest, GatewayContainer container) {
        GatewayQueries queries = requestDefine.getQueries();
        if (ObjectUtils.isEmpty(queries)) {
            return;
        }
        Set<GatewayField> fields = queries.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return;
        }
        MultiValueMap<String, String> requestQueries = gatewaySessionRequest.getRequestQueries();
        if (CollectionUtils.isEmpty(requestQueries)) {
            requestQueries = new LinkedMultiValueMap<String, String>();
            gatewaySessionRequest.setRequestQueries(requestQueries);
        }
        autofillRequestDataValues(conversionService, container, fields, null, requestQueries);
    }

    protected void autofillRequestHeaders(ConversionService conversionService, GatewayRequest requestDefine, GatewaySessionRequest gatewaySessionRequest, GatewayContainer container) {
        GatewayHeaders headers = requestDefine.getHeaders();
        if (ObjectUtils.isEmpty(headers)) {
            return;
        }
        Set<GatewayField> fields = headers.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return;
        }
        MultiValueMap<String, String> requestHeaders = gatewaySessionRequest.getRequestHeaders();
        if (CollectionUtils.isEmpty(requestHeaders)) {
            requestHeaders = new LinkedMultiValueMap<String, String>();
            gatewaySessionRequest.setRequestHeaders(requestHeaders);
        }
        autofillRequestDataValues(conversionService, container, fields, null, requestHeaders);
    }

    @Override
    public <RequestBody> void autofill(GatewayRequest request, GatewaySessionRequest gatewaySessionRequest) {
        if (ObjectUtils.isEmpty(gatewaySessionRequest)) {
            return;
        }
        GatewayContext gatewayContext = this.getGatewayContext();
        ConversionService conversionService = gatewayContext.getConversionService();
        GatewayContainer container = gatewayContext.getGatewayContainer();
        autofillRequestSelectData(gatewaySessionRequest);
        autofillRequestPathData(conversionService, request, gatewaySessionRequest, container);
        autofillRequestQueriesData(conversionService, request, gatewaySessionRequest, container);
        autofillRequestHeaders(conversionService, request, gatewaySessionRequest, container);
    }

}
