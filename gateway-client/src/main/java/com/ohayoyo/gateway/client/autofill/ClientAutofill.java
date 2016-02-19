package com.ohayoyo.gateway.client.autofill;

import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.utils.ParameterUtils;
import com.ohayoyo.gateway.define.core.Parameter;
import com.ohayoyo.gateway.define.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class ClientAutofill implements GatewayAutofill, ApplicationContextAware {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClientAutofill.class);

    public static final String CLIENT_AUTOFILL_SELECT_ENVIRONMENT = "CLIENT_AUTOFILL_SELECT_ENVIRONMENT";

    public static final String CLIENT_AUTOFILL_SELECT_ENVIRONMENT_CONFIG = "gateway.ohayoyo.com";

    public static final String CLIENT_AUTOFILL_SELECT_ENVIRONMENT_BEAN = "CLIENT_AUTOFILL_SELECT_ENVIRONMENT_BEAN";

    @Autowired(required = false)
    private ConversionService conversionService;

    @Autowired(required = false)
    private Environment environment;

    @Autowired
    private ApplicationContext applicationContext;

    //自动选择环境 CLIENT_AUTOFILL_SELECT_ENVIRONMENT | CLIENT_AUTOFILL_SELECT_ENVIRONMENT_CONFIG
    private volatile String applicationEnvironmentSelect = null;

    //是否可为空值并存在默认值同时也使用自动填充
    private boolean nullableAutofill = true;

    protected ConversionService checkConversionService() {
        if (ObjectUtils.isEmpty(this.conversionService)) {
            this.conversionService = new DefaultFormattingConversionService();
        }
        return this.conversionService;
    }

    protected void applicationContextEnvironmentSelect() {
        if (!StringUtils.isEmpty(this.applicationEnvironmentSelect)) {
            return;
        }
        String select;
        if (ObjectUtils.isEmpty(environment)) {
            this.environment = new StandardEnvironment();
        }
        try {
            select = this.environment.getProperty(CLIENT_AUTOFILL_SELECT_ENVIRONMENT);
            if (StringUtils.isEmpty(select)) {
                select = this.environment.getProperty(CLIENT_AUTOFILL_SELECT_ENVIRONMENT_CONFIG);
            }
        } catch (Exception ex) {
            select = null;
            LOGGER.info("自动选择环境错误:{}", ex);
        }
        if (StringUtils.isEmpty(select) && (!ObjectUtils.isEmpty(this.applicationContext))) {
            try {
                select = this.applicationContext.getBean(CLIENT_AUTOFILL_SELECT_ENVIRONMENT_BEAN, String.class);
            } catch (Exception ex) {
                LOGGER.info("获取选择环境Bean配置{}失败,信息:{}", CLIENT_AUTOFILL_SELECT_ENVIRONMENT_BEAN, ex.getMessage());
            }
        }
        this.applicationEnvironmentSelect = select;
    }

    protected void autofillRequestSelect(GatewayRequest<?> gatewayRequest) {
        String select = gatewayRequest.getSelect();
        if (StringUtils.isEmpty(select)) {
            applicationContextEnvironmentSelect();
            gatewayRequest.setSelect(applicationEnvironmentSelect);
        }
    }

    protected void autofillRequestDataValues(Set<Parameter> parameters, Map<String, String> singleValueData, MultiValueMap<String, String> multiValueData) {
        if (CollectionUtils.isEmpty(parameters)) {
            return;
        }
        for (Parameter parameter : parameters) {
            Object defaultValue = parameter.getDefaultValue();
            if (ObjectUtils.isEmpty(defaultValue)) {
                continue;
            }
            String parameterName = parameter.getName();
            Boolean parameterNullable = parameter.getNullable();
            if (ObjectUtils.isEmpty(parameterNullable)) {
                parameterNullable = true;
            }
            boolean isAutofill;
            if (parameterNullable && isNullableAutofill()) {
                isAutofill = true;
            } else {
                isAutofill = !parameterNullable;
            }
            if (isAutofill && (null != singleValueData) && (!singleValueData.containsKey(parameterName))) {
                String valueString = ParameterUtils.resolveParameterDefaultValueToString(parameter, checkConversionService());
                if (!StringUtils.isEmpty(valueString)) {
                    singleValueData.put(parameterName, valueString);
                }
            } else if (isAutofill && (null != multiValueData) && (!multiValueData.containsKey(parameterName))) {
                String valueString = ParameterUtils.resolveParameterDefaultValueToString(parameter, checkConversionService());
                if (!StringUtils.isEmpty(valueString)) {
                    multiValueData.add(parameterName, valueString);
                }
            }
        }
    }

    protected void autofillRequestPath(RequestDefine requestDefine, GatewayRequest<?> gatewayRequest) {
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
        autofillRequestDataValues(parameters, requestPathVariables, null);
    }

    protected void autofillRequestQueries(RequestDefine requestDefine, GatewayRequest<?> gatewayRequest) {
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
        autofillRequestDataValues(parameters, null, requestQueries);
    }

    protected void autofillRequestHeaders(RequestDefine requestDefine, GatewayRequest<?> gatewayRequest) {
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
        autofillRequestDataValues(parameters, null, requestHeaders);
    }

    @Override
    public <RequestBody> void autofill(RequestDefine requestDefine, GatewayRequest<RequestBody> gatewayRequest) {

        LOGGER.debug("客户端自动填充进行实际的填充处理.");

        if (ObjectUtils.isEmpty(gatewayRequest)) {
            return;
        }

        LOGGER.debug("选择作用域环境自动填充.");
        autofillRequestSelect(gatewayRequest);

        LOGGER.debug("请求路径值自动填充.");
        autofillRequestPath(requestDefine, gatewayRequest);

        LOGGER.debug("请求查询参数自动填充");
        autofillRequestQueries(requestDefine, gatewayRequest);

        LOGGER.debug("请求头参数自动填充");
        autofillRequestHeaders(requestDefine, gatewayRequest);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public boolean isNullableAutofill() {
        return nullableAutofill;
    }

    public ClientAutofill setNullableAutofill(boolean nullableAutofill) {
        this.nullableAutofill = nullableAutofill;
        return this;
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

    public ClientAutofill setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
        return this;
    }

}
