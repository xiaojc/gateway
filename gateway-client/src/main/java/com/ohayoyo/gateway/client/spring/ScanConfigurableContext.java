package com.ohayoyo.gateway.client.spring;

import com.ohayoyo.gateway.client.autofill.GatewayAutofill;
import com.ohayoyo.gateway.client.core.AbstractContext;
import com.ohayoyo.gateway.client.core.ConfigurableContext;
import com.ohayoyo.gateway.client.core.GatewayClient;
import com.ohayoyo.gateway.client.utils.ApplicationContextUtils;
import com.ohayoyo.gateway.client.validator.GatewayDataValidator;
import com.ohayoyo.gateway.client.validator.GatewayDefineValidator;
import com.ohayoyo.gateway.client.validator.GatewayResultValidator;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.core.HttpGatewayRequest;
import com.ohayoyo.gateway.http.core.HttpGatewayResponse;
import com.ohayoyo.gateway.http.interceptor.HttpGatewayRequestIntercepting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public class ScanConfigurableContext extends ConfigurableContext implements InitializingBean {

    public static final Logger LOGGER = LoggerFactory.getLogger(ScanConfigurableContext.class);

    public ScanConfigurableContext() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ApplicationContext applicationContext = this.getApplicationContext();
        if (ObjectUtils.isEmpty(applicationContext)) {
            return;
        }
        tryAdaptiveConversionService(applicationContext);
        tryAdaptiveHttpGatewayMessageConverters(applicationContext);
        tryAdaptiveHttpGatewayRequest(applicationContext);
        tryAdaptiveHttpGatewayResponse(applicationContext);
        tryAdaptiveClientHttpRequestFactory(applicationContext);
        tryAdaptiveHttpGatewayRequestIntercepting(applicationContext);
        tryAdaptiveHttpGateway(applicationContext);
        tryAdaptiveGatewayContainer(applicationContext);
        tryAdaptiveGatewayClient(applicationContext);
        tryAdaptiveGatewayDefineValidator(applicationContext);
        tryAdaptiveGatewayDataValidator(applicationContext);
        tryAdaptiveGatewayResultValidator(applicationContext);
        tryAdaptiveGatewayAutofill(applicationContext);
        this.configDefaults();
    }

    protected void tryAdaptiveConversionService(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getConversionService())) {
                this.setConversionService(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, ConversionService.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", ConversionService.class, ex);
        }
    }

    protected void tryAdaptiveHttpGatewayMessageConverters(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getHttpGatewayMessageConverters())) {
                this.setHttpGatewayMessageConverters(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, HttpGatewayMessageConverters.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", HttpGatewayMessageConverters.class, ex);
        }
    }

    protected void tryAdaptiveHttpGatewayRequest(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getHttpGatewayRequest())) {
                this.setHttpGatewayRequest(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, HttpGatewayRequest.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", HttpGatewayRequest.class, ex);
        }
    }

    protected void tryAdaptiveHttpGatewayResponse(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getHttpGatewayResponse())) {
                this.setHttpGatewayResponse(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, HttpGatewayResponse.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", HttpGatewayResponse.class, ex);
        }
    }

    protected void tryAdaptiveClientHttpRequestFactory(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getClientHttpRequestFactory())) {
                this.setClientHttpRequestFactory(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, ClientHttpRequestFactory.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", ClientHttpRequestFactory.class, ex);
        }
    }

    protected void tryAdaptiveHttpGatewayRequestIntercepting(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getHttpGatewayRequestIntercepting())) {
                this.setHttpGatewayRequestIntercepting(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, HttpGatewayRequestIntercepting.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", HttpGatewayRequestIntercepting.class, ex);
        }
    }

    protected void tryAdaptiveHttpGateway(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getHttpGateway())) {
                this.setHttpGateway(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, HttpGateway.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("{}", ex);
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", HttpGateway.class, ex);
        }
    }

    protected void tryAdaptiveGatewayContainer(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getGatewayContainer())) {
                this.setGatewayContainer(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, GatewayContainer.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", GatewayContainer.class, ex);
        }
    }

    protected void tryAdaptiveGatewayClient(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getGatewayClient())) {
                this.setGatewayClient(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, GatewayClient.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", GatewayClient.class, ex);
        }
    }

    protected void tryAdaptiveGatewayDefineValidator(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getGatewayDefineValidator())) {
                this.setGatewayDefineValidator(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, GatewayDefineValidator.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", GatewayDefineValidator.class, ex);
        }
    }

    protected void tryAdaptiveGatewayDataValidator(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getGatewayDataValidator())) {
                this.setGatewayDataValidator(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, GatewayDataValidator.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", GatewayDataValidator.class, ex);
        }
    }

    protected void tryAdaptiveGatewayResultValidator(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getGatewayResultValidator())) {
                this.setGatewayResultValidator(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, GatewayResultValidator.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", GatewayResultValidator.class, ex);
        }
    }

    protected void tryAdaptiveGatewayAutofill(ApplicationContext applicationContext) {
        try {
            if (ObjectUtils.isEmpty(this.getGatewayAutofill())) {
                this.setGatewayAutofill(ApplicationContextUtils.tryAdaptiveFirstBean(applicationContext, GatewayAutofill.class));
            }
        } catch (Exception ex) {
            LOGGER.warn("尝试自动适配类型{}失败,信息:{}", GatewayAutofill.class, ex);
        }
    }

    @Override
    @Autowired
    public AbstractContext setEnvironment(Environment environment) {
        return super.setEnvironment(environment);
    }

    @Override
    @Autowired
    public AbstractContext setApplicationContext(ApplicationContext applicationContext) {
        return super.setApplicationContext(applicationContext);
    }

}
