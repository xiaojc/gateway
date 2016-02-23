package com.ohayoyo.gateway.session.support;

import com.ohayoyo.gateway.define.GatewayContainer;
import com.ohayoyo.gateway.http.*;
import com.ohayoyo.gateway.session.*;
import com.ohayoyo.gateway.utils.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
@Configuration
public class AutoScanGatewayContext extends AbstractGatewayContext implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoScanGatewayContext.class);

    public AutoScanGatewayContext() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.configAutoScans();
        this.configDefaults();
    }

    private void configAutoScans() {
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
    }

    private void configDefaults() {
        configDefaultConversionService();
        configDefaultHttpMessageConverters();
        configDefaultHttpRequest();
        configDefaultHttpResponse();
        configDefaultClientHttpRequestFactory();
        configDefaultHttpClient();
        configDefaultGatewaySession();
        configDefaultGatewayInterfaceValidator();
        configDefaultGatewayDataValidator();
        configDefaultGatewayResultValidator();
        configDefaultGatewayDataAutofill();
        configDefaultEnvironment();
    }

    private void configDefaultConversionService() {
        if (ObjectUtils.isEmpty(this.getConversionService())) {
            ConversionService conversionService = new DefaultFormattingConversionService();
            this.setConversionService(conversionService);
        }
    }

    private void configDefaultHttpMessageConverters() {
        if (ObjectUtils.isEmpty(this.getGatewayHttpMessageConverters())) {
            GatewayHttpMessageConverters gatewayHttpMessageConverters = new GatewayHttpMessageConvertersHandler();
            this.setGatewayHttpMessageConverters(gatewayHttpMessageConverters);
        }
    }

    private void configDefaultHttpRequest() {
        if (ObjectUtils.isEmpty(this.getGatewayHttpRequest())) {
            GatewayHttpRequest gatewayHttpRequest = new GatewayHttpRequestHandler();
            this.setGatewayHttpRequest(gatewayHttpRequest);
        }
    }

    private void configDefaultHttpResponse() {
        if (ObjectUtils.isEmpty(this.getGatewayHttpResponse())) {
            GatewayHttpResponse gatewayHttpResponse = new GatewayHttpResponseHandler();
            this.setGatewayHttpResponse(gatewayHttpResponse);
        }
    }

    private void configDefaultClientHttpRequestFactory() {
        if (ObjectUtils.isEmpty(this.getClientHttpRequestFactory())) {
            ClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            this.setClientHttpRequestFactory(clientHttpRequestFactory);
        }
    }

    private void configDefaultHttpClient() {
        if (ObjectUtils.isEmpty(this.getGatewayHttpClient())) {
            GatewayHttpClient gatewayHttpClient = new GatewayHttpClientHandler();
            this.setGatewayHttpClient(gatewayHttpClient);
        }
    }

    private void configDefaultGatewaySession() {
        if (ObjectUtils.isEmpty(this.getGatewaySession())) {
            GatewaySession gatewaySession = new RestfulGatewaySession();
            gatewaySession.setGatewayContext(this);
            this.setGatewaySession(gatewaySession);
        }
    }

    private void configDefaultGatewayInterfaceValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayInterfaceValidator())) {
            GatewayInterfaceValidator gatewayInterfaceValidator = new SessionInterfaceValidator();
            gatewayInterfaceValidator.setGatewayContext(this);
            this.setGatewayInterfaceValidator(gatewayInterfaceValidator);
        }
    }

    private void configDefaultGatewayDataValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayDataValidator())) {
            GatewayDataValidator gatewayDataValidator = new SessionDataValidator();
            gatewayDataValidator.setGatewayContext(this);
            this.setGatewayDataValidator(gatewayDataValidator);
        }
    }

    private void configDefaultGatewayResultValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayResultValidator())) {
            GatewayResultValidator gatewayResultValidator = new SessionResultValidator();
            gatewayResultValidator.setGatewayContext(this);
            this.setGatewayResultValidator(gatewayResultValidator);
        }
    }

    private void configDefaultGatewayDataAutofill() {
        if (ObjectUtils.isEmpty(this.getGatewayDataAutoFill())) {
            GatewayDataAutoFill gatewayDataAutoFill = new SessionDataAutoFill();
            gatewayDataAutoFill.setGatewayContext(this);
            this.setGatewayDataAutoFill(gatewayDataAutoFill);
        }
    }

    private void configDefaultEnvironment() {
        if (ObjectUtils.isEmpty(this.getEnvironment())) {
            ApplicationContext applicationContext = this.getApplicationContext();
            Environment environment = null;
            if (!ObjectUtils.isEmpty(this.getApplicationContext())) {
                applicationContext.getEnvironment();
            } else {
                environment = new StandardEnvironment();
            }
            this.setEnvironment(environment);
        }
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
