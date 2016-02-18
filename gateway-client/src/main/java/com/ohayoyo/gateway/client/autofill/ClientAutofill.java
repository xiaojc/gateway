package com.ohayoyo.gateway.client.autofill;

import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.define.ParameterDefine;
import com.ohayoyo.gateway.define.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClientAutofill implements GatewayAutofill ,ApplicationContextAware {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClientAutofill.class);

    public static final String CLIENT_AUTOFILL_SELECT_ENVIRONMENT = "CLIENT_AUTOFILL_SELECT_ENVIRONMENT";

    public static final String CLIENT_AUTOFILL_SELECT_ENVIRONMENT_CONFIG = "gateway.ohayoyo.com";

    public static final String CLIENT_AUTOFILL_SELECT_ENVIRONMENT_BEAN = "CLIENT_AUTOFILL_SELECT_ENVIRONMENT_BEAN";

    @Autowired(required = false)
    private ConversionService conversionService;

    //自动选择环境 CLIENT_AUTOFILL_SELECT_ENVIRONMENT | CLIENT_AUTOFILL_SELECT_ENVIRONMENT_CONFIG
    private volatile String environmentSelect = null;

    @Autowired(required = false)
    private Environment environment;

    //是否可为空值并存在默认值同时也使用自动填充
    private boolean nullableAutofill = true;

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

    private void checkConversionService() {
        if (ObjectUtils.isEmpty(this.conversionService)) {
            this.conversionService = new DefaultFormattingConversionService();
        }
    }

    protected void environmentSelect() {
        if (!StringUtils.isEmpty(this.environmentSelect)) {
            return;
        }
        String select = null;
        if (ObjectUtils.isEmpty(environment)) {
            this.environment = new StandardEnvironment();
        }
        if(!ObjectUtils.isEmpty(this.applicationContext)){
            try{
                select =this.applicationContext.getBean(CLIENT_AUTOFILL_SELECT_ENVIRONMENT_BEAN,String.class) ;
            }catch (Exception ex){
                LOGGER.info("获取配置{}失败,信息:{}",CLIENT_AUTOFILL_SELECT_ENVIRONMENT_BEAN,ex.getMessage());
            }
        }
        if (StringUtils.isEmpty(select)) {
            try {
                select = this.environment.getProperty(CLIENT_AUTOFILL_SELECT_ENVIRONMENT_CONFIG);
                if (StringUtils.isEmpty(select)) {
                    select = this.environment.getProperty(CLIENT_AUTOFILL_SELECT_ENVIRONMENT);
                }
            } catch (Exception ex) {
                select = null;
                LOGGER.info("自动选择环境错误:{}", ex);
            }
        }

        this.environmentSelect = select;
    }

    protected void autofillSelect(GatewayRequest<?> gatewayRequest) {
        String select = gatewayRequest.getSelect();
        if (StringUtils.isEmpty(select)) {
            environmentSelect();
            gatewayRequest.setSelect(environmentSelect);
        }
    }

    protected void autofillValue(Set<ParameterDefine> parameterDefines, Map<String, String> dataMapString, MultiValueMap<String, String> dataMapStrings) {
        if (CollectionUtils.isEmpty(parameterDefines)) {
            return;
        }
        for (ParameterDefine parameterDefine : parameterDefines) {
            Object defaultValue = parameterDefine.getDefaultValue();
            if (ObjectUtils.isEmpty(defaultValue)) {
                continue;
            }
            String parameterName = parameterDefine.getName();
            Boolean parameterNullable = parameterDefine.getNullable();
            if (ObjectUtils.isEmpty(parameterNullable)) {
                parameterNullable = true;
            }
            boolean isAutofill;
            if (parameterNullable && isNullableAutofill()) {
                isAutofill = true;
            } else {
                isAutofill = !parameterNullable;
            }
            if (null != dataMapString) {
                autofillValueMapString(isAutofill, parameterName, defaultValue, dataMapString);
            } else if (null != dataMapStrings) {
                autofillValueMapStrings(isAutofill, parameterName, defaultValue, dataMapStrings);
            }
        }
    }

    protected void autofillValueMapStrings(boolean isAutofill, String parameterName, Object defaultValue, MultiValueMap<String, String> dataStrings) {
        if (isAutofill && (!dataStrings.containsKey(parameterName))) {
            Class<?> defaultValueType = defaultValue.getClass();
            Class<String> stringType = String.class;
            Class<List> listType = List.class;
            boolean isHasError = false;
            checkConversionService();
            if (this.conversionService.canConvert(defaultValueType, listType)) {
                try {
                    List<Object> valueObjects = this.conversionService.convert(defaultValue, listType);
                    for (Object valueObject : valueObjects) {
                        if (ObjectUtils.isEmpty(valueObject)) {
                            continue;
                        }
                        if (this.conversionService.canConvert(valueObject.getClass(), stringType)) {
                            String valueString = this.conversionService.convert(valueObject, stringType);
                            dataStrings.add(parameterName, valueString);
                        }
                    }
                } catch (Exception ex) {
                    isHasError = true;
                }
            }
            if (isHasError && this.conversionService.canConvert(defaultValueType, stringType)) {
                String valueString = this.conversionService.convert(defaultValue, stringType);
                dataStrings.add(parameterName, valueString);
            }
        }
    }

    protected void autofillValueMapString(boolean isAutofill, String parameterName, Object defaultValue, Map<String, String> dataString) {
        if (isAutofill && (!dataString.containsKey(parameterName))) {
            Class<?> defaultValueType = defaultValue.getClass();
            Class<String> stringType = String.class;
            checkConversionService();
            if (this.conversionService.canConvert(defaultValueType, stringType)) {
                String valueString = this.conversionService.convert(defaultValue, stringType);
                dataString.put(parameterName, valueString);
            }
        }
    }

    protected void autofillPath(RequestDefine requestDefine, GatewayRequest<?> gatewayRequest) {
        PathDefine pathDefine = requestDefine.getPath();
        if (ObjectUtils.isEmpty(pathDefine)) {
            return;
        }
        VariablesDefine variablesDefine = pathDefine.getVariables();
        if (ObjectUtils.isEmpty(variablesDefine)) {
            return;
        }
        Set<ParameterDefine> parameterDefines = variablesDefine.getFields();
        if (CollectionUtils.isEmpty(parameterDefines)) {
            return;
        }
        Map<String, String> requestPathVariables = gatewayRequest.getRequestPathVariables();
        if (CollectionUtils.isEmpty(requestPathVariables)) {
            requestPathVariables = new HashMap<String, String>();
            gatewayRequest.setRequestPathVariables(requestPathVariables);
        }
        autofillValue(parameterDefines, requestPathVariables, null);
    }


    protected void autofillQueries(RequestDefine requestDefine, GatewayRequest<?> gatewayRequest) {
        QueriesDefine queriesDefine = requestDefine.getQueries();
        if (ObjectUtils.isEmpty(queriesDefine)) {
            return;
        }
        Set<ParameterDefine> parameterDefines = queriesDefine.getFields();
        if (CollectionUtils.isEmpty(parameterDefines)) {
            return;
        }
        MultiValueMap<String, String> requestQueries = gatewayRequest.getRequestQueries();
        if (CollectionUtils.isEmpty(requestQueries)) {
            requestQueries = new LinkedMultiValueMap<String, String>();
            gatewayRequest.setRequestQueries(requestQueries);
        }
        autofillValue(parameterDefines, null, requestQueries);
    }

    protected void autofillHeaders(RequestDefine requestDefine, GatewayRequest<?> gatewayRequest) {
        HeadersDefine headersDefine = requestDefine.getHeaders();
        if (ObjectUtils.isEmpty(headersDefine)) {
            return;
        }
        Set<ParameterDefine> parameterDefines = headersDefine.getFields();
        if (CollectionUtils.isEmpty(parameterDefines)) {
            return;
        }
        MultiValueMap<String, String> requestHeaders = gatewayRequest.getRequestHeaders();
        if (CollectionUtils.isEmpty(requestHeaders)) {
            requestHeaders = new LinkedMultiValueMap<String, String>();
            gatewayRequest.setRequestHeaders(requestHeaders);
        }
        autofillValue(parameterDefines, null, requestHeaders);
    }

    @Override
    public <RequestBody> void autofill(RequestDefine requestDefine, GatewayRequest<RequestBody> gatewayRequest) {

        LOGGER.debug("客户端自动填充进行实际的填充处理.");

        if (ObjectUtils.isEmpty(gatewayRequest)) {
            return;
        }

        LOGGER.debug("选择作用域环境自动填充.");
        autofillSelect(gatewayRequest);

        LOGGER.debug("请求路径值自动填充.");
        autofillPath(requestDefine, gatewayRequest);

        LOGGER.debug("请求查询参数自动填充");
        autofillQueries(requestDefine, gatewayRequest);

        LOGGER.debug("请求头参数自动填充");
        autofillHeaders(requestDefine, gatewayRequest);

        //请求实体自动填充
        //目前还没有可支持

    }

    @Autowired
    private ApplicationContext applicationContext ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext ;
    }
}
