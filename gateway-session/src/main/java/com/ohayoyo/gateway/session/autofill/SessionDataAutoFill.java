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
public class SessionDataAutoFill extends AbstractGatewayAccessor implements GatewayDataAutoFill {

    public static final Logger LOGGER = LoggerFactory.getLogger(SessionDataAutoFill.class);

    private static String ENVIRONMENT_SELECT = null;

    protected String contextEnvironmentSelect() {
        if (StringUtils.isEmpty(ENVIRONMENT_SELECT)) {
            Environment environment = this.getGatewayContext().getEnvironment();
            ENVIRONMENT_SELECT = environment.getProperty(GATEWAY_AUTO_FILL_SELECT_ENVIRONMENT);
        }
        return ENVIRONMENT_SELECT;
    }

    protected void autoFillRequestSelectData(GatewaySessionRequest gatewaySessionRequest) {
        String select = gatewaySessionRequest.getSelect();
        if (StringUtils.isEmpty(select)) {
            gatewaySessionRequest.setSelect(contextEnvironmentSelect());
        }
    }

    protected void autoFillRequestDataValues(ConversionService conversionService, GatewayContainer container, Set<GatewayField> fields, Map<String, String> singleValueData, MultiValueMap<String, String> multiValueData) {
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

    protected void autoFillRequestPathData(ConversionService conversionService, GatewayRequest requestDefine, GatewaySessionRequest gatewaySessionRequest, GatewayContainer container) {
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
        autoFillRequestDataValues(conversionService, container, fields, requestPathVariables, null);
    }

    protected void autoFillRequestQueriesData(ConversionService conversionService, GatewayRequest requestDefine, GatewaySessionRequest gatewaySessionRequest, GatewayContainer container) {
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
        autoFillRequestDataValues(conversionService, container, fields, null, requestQueries);
    }

    protected void autoFillRequestHeaders(ConversionService conversionService, GatewayRequest requestDefine, GatewaySessionRequest gatewaySessionRequest, GatewayContainer container) {
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
        autoFillRequestDataValues(conversionService, container, fields, null, requestHeaders);
    }

    @Override
    public <RequestBody> void dataAutoFill(GatewayRequest request, GatewaySessionRequest<RequestBody> gatewaySessionRequest) {
        if (ObjectUtils.isEmpty(gatewaySessionRequest)) {
            return;
        }
        GatewayContext gatewayContext = this.getGatewayContext();
        ConversionService conversionService = gatewayContext.getConversionService();
        GatewayContainer container = gatewayContext.getGatewayContainer();
        autoFillRequestSelectData(gatewaySessionRequest);
        autoFillRequestPathData(conversionService, request, gatewaySessionRequest, container);
        autoFillRequestQueriesData(conversionService, request, gatewaySessionRequest, container);
        autoFillRequestHeaders(conversionService, request, gatewaySessionRequest, container);
    }

}
