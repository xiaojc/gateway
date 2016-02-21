package com.ohayoyo.gateway.session.support;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.http.client.GatewayHttpClient;
import com.ohayoyo.gateway.http.client.GatewayHttpRequest;
import com.ohayoyo.gateway.http.client.GatewayHttpResponse;
import com.ohayoyo.gateway.http.converter.GatewayHttpMessageConverters;
import com.ohayoyo.gateway.http.interceptor.GatewayHttpRequestInterceptors;
import com.ohayoyo.gateway.session.autofill.GatewayDataAutoFill;
import com.ohayoyo.gateway.session.core.GatewaySession;
import com.ohayoyo.gateway.session.utils.ApplicationContextUtils;
import com.ohayoyo.gateway.session.validator.GatewayDataValidator;
import com.ohayoyo.gateway.session.validator.GatewayInterfaceValidator;
import com.ohayoyo.gateway.session.validator.GatewayResultValidator;
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
public class AutoScanGatewayContext extends ConfigurableGatewayContext implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoScanGatewayContext.class);

    public AutoScanGatewayContext() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ApplicationContext applicationContext = this.getApplicationContext();
        if (!ObjectUtils.isEmpty(applicationContext)) {
            autoScanConversionService(applicationContext);
            autoScanHttpMessageConverters(applicationContext);
            autoScanHttpRequest(applicationContext);
            autoScanHttpResponse(applicationContext);
            autoScanClientHttpRequestFactory(applicationContext);
            autoScanHttpRequestIntercepting(applicationContext);
            autoScanHttpClient(applicationContext);
            autoScanContainer(applicationContext);
            autoScanGatewaySession(applicationContext);
            autoScanGatewayInterfaceValidator(applicationContext);
            autoScanGatewayDataValidator(applicationContext);
            autoScanGatewayResultValidator(applicationContext);
            autoScanGatewayDataAutofill(applicationContext);
        }
        this.adaptiveDefaults();
    }

    protected void autoScanConversionService(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getConversionService())) {
            this.setConversionService(ApplicationContextUtils.tryScanFirstBean(applicationContext, ConversionService.class));
        }
    }

    protected void autoScanHttpMessageConverters(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewayHttpMessageConverters())) {
            this.setGatewayHttpMessageConverters(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewayHttpMessageConverters.class));
        }
    }

    protected void autoScanHttpRequest(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewayHttpRequest())) {
            this.setGatewayHttpRequest(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewayHttpRequest.class));
        }
    }

    protected void autoScanHttpResponse(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewayHttpResponse())) {
            this.setGatewayHttpResponse(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewayHttpResponse.class));
        }
    }

    protected void autoScanClientHttpRequestFactory(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getClientHttpRequestFactory())) {
            this.setClientHttpRequestFactory(ApplicationContextUtils.tryScanFirstBean(applicationContext, ClientHttpRequestFactory.class));
        }
    }

    protected void autoScanHttpRequestIntercepting(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewayHttpRequestInterceptors())) {
            this.setGatewayHttpRequestInterceptors(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewayHttpRequestInterceptors.class));
        }
    }

    protected void autoScanHttpClient(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewayHttpClient())) {
            this.setGatewayHttpClient(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewayHttpClient.class));
        }
    }

    protected void autoScanContainer(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewayContainer())) {
            this.setGatewayContainer(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewayContainer.class));
        }
    }

    protected void autoScanGatewaySession(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewaySession())) {
            this.setGatewaySession(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewaySession.class));
        }
    }

    protected void autoScanGatewayInterfaceValidator(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewayInterfaceValidator())) {
            this.setGatewayInterfaceValidator(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewayInterfaceValidator.class));
        }
    }

    protected void autoScanGatewayDataValidator(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewayDataValidator())) {
            this.setGatewayDataValidator(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewayDataValidator.class));
        }
    }

    protected void autoScanGatewayResultValidator(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewayResultValidator())) {
            this.setGatewayResultValidator(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewayResultValidator.class));
        }
    }

    protected void autoScanGatewayDataAutofill(ApplicationContext applicationContext) {
        if (ObjectUtils.isEmpty(this.getGatewayDataAutoFill())) {
            this.setGatewayDataAutoFill(ApplicationContextUtils.tryScanFirstBean(applicationContext, GatewayDataAutoFill.class));
        }
    }

    @Override
    @Autowired
    public void setEnvironment(Environment environment) {
        super.setEnvironment(environment);
    }

    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
    }

}
