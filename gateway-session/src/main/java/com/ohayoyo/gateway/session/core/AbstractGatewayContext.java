package com.ohayoyo.gateway.session.core;

import com.ohayoyo.gateway.session.autofill.GatewayDataAutoFill;
import com.ohayoyo.gateway.session.builder.RestfulSessionRequestBuilder;
import com.ohayoyo.gateway.session.builder.RestfulSessionResponseBuilder;
import com.ohayoyo.gateway.session.validator.GatewayDataValidator;
import com.ohayoyo.gateway.session.validator.GatewayInterfaceValidator;
import com.ohayoyo.gateway.session.validator.GatewayResultValidator;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.http.client.GatewayHttpClient;
import com.ohayoyo.gateway.http.client.GatewayHttpRequest;
import com.ohayoyo.gateway.http.client.GatewayHttpResponse;
import com.ohayoyo.gateway.http.converter.GatewayHttpMessageConverters;
import com.ohayoyo.gateway.http.interceptor.GatewayHttpRequestInterceptors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewayContext implements GatewayContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGatewayContext.class);

    private ConversionService conversionService;

    private GatewayHttpMessageConverters gatewayHttpMessageConverters;

    private GatewayHttpRequest gatewayHttpRequest;

    private GatewayHttpResponse gatewayHttpResponse;

    private GatewayHttpRequestInterceptors gatewayHttpRequestInterceptors;

    private ClientHttpRequestFactory clientHttpRequestFactory;

    private GatewayHttpClient gatewayHttpClient;

    private GatewayContainer containerDefine;

    private GatewaySession gatewaySession;

    private GatewayInterfaceValidator gatewayInterfaceValidator;

    private GatewayDataValidator gatewayDataValidator;

    private GatewayResultValidator gatewayResultValidator;

    private GatewayDataAutoFill gatewayDataAutoFill;

    private Environment environment;

    private ApplicationContext applicationContext;

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
        if (!ObjectUtils.isEmpty(this.gatewayHttpMessageConverters)) {
            this.gatewayHttpMessageConverters.setConversionService(this.conversionService);
        }
    }

    public GatewayHttpMessageConverters getGatewayHttpMessageConverters() {
        return gatewayHttpMessageConverters;
    }

    public void setGatewayHttpMessageConverters(GatewayHttpMessageConverters gatewayHttpMessageConverters) {
        this.gatewayHttpMessageConverters = gatewayHttpMessageConverters;
        if (!ObjectUtils.isEmpty(this.gatewayHttpClient)) {
            this.gatewayHttpClient.setGatewayHttpMessageConverters(this.gatewayHttpMessageConverters);
        }
        if (!ObjectUtils.isEmpty(this.gatewayHttpMessageConverters)) {
            this.gatewayHttpMessageConverters.setConversionService(this.conversionService);
        }
    }

    public GatewayHttpRequest getGatewayHttpRequest() {
        return gatewayHttpRequest;
    }

    public void setGatewayHttpRequest(GatewayHttpRequest gatewayHttpRequest) {
        this.gatewayHttpRequest = gatewayHttpRequest;
        if (!ObjectUtils.isEmpty(this.gatewayHttpClient)) {
            this.gatewayHttpClient.setGatewayHttpRequest(this.gatewayHttpRequest);
        }
    }

    public GatewayHttpResponse getGatewayHttpResponse() {
        return gatewayHttpResponse;
    }

    public void setGatewayHttpResponse(GatewayHttpResponse gatewayHttpResponse) {
        this.gatewayHttpResponse = gatewayHttpResponse;
        if (!ObjectUtils.isEmpty(this.gatewayHttpClient)) {
            this.gatewayHttpClient.setGatewayHttpResponse(this.gatewayHttpResponse);
        }
    }

    public GatewayHttpRequestInterceptors getGatewayHttpRequestInterceptors() {
        return gatewayHttpRequestInterceptors;
    }

    public void setGatewayHttpRequestInterceptors(GatewayHttpRequestInterceptors gatewayHttpRequestInterceptors) {
        this.gatewayHttpRequestInterceptors = gatewayHttpRequestInterceptors;
        if (!ObjectUtils.isEmpty(this.gatewayHttpClient)) {
            this.gatewayHttpClient.setGatewayHttpRequestInterceptors(this.gatewayHttpRequestInterceptors);
        }
    }

    @Override
    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        return clientHttpRequestFactory;
    }

    @Override
    public void setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory) {
        this.clientHttpRequestFactory = clientHttpRequestFactory;
        if (!ObjectUtils.isEmpty(this.gatewayHttpClient)) {
            this.gatewayHttpClient.setClientHttpRequestFactory(this.clientHttpRequestFactory);
        }
    }

    public GatewayHttpClient getGatewayHttpClient() {
        return gatewayHttpClient;
    }

    public void setGatewayHttpClient(GatewayHttpClient gatewayHttpClient) {
        this.gatewayHttpClient = gatewayHttpClient;
        if (!ObjectUtils.isEmpty(this.gatewayHttpClient)) {
            this.gatewayHttpClient.setGatewayHttpRequest(this.gatewayHttpRequest);
            this.gatewayHttpClient.setGatewayHttpResponse(this.gatewayHttpResponse);
            this.gatewayHttpClient.setGatewayHttpMessageConverters(this.gatewayHttpMessageConverters);
            this.gatewayHttpClient.setGatewayHttpRequestInterceptors(this.gatewayHttpRequestInterceptors);
            this.gatewayHttpClient.setClientHttpRequestFactory(this.clientHttpRequestFactory);
        }
    }

    public GatewayContainer getGatewayContainer() {
        return containerDefine;
    }

    public void setGatewayContainer(GatewayContainer gatewayContainer) {
        this.containerDefine = gatewayContainer;
    }

    public GatewaySession getGatewaySession() {
        return gatewaySession;
    }

    public void setGatewaySession(GatewaySession gatewaySession) {
        this.gatewaySession = gatewaySession;
    }

    public GatewayInterfaceValidator getGatewayInterfaceValidator() {
        return gatewayInterfaceValidator;
    }

    public void setGatewayInterfaceValidator(GatewayInterfaceValidator gatewayInterfaceValidator) {
        this.gatewayInterfaceValidator = gatewayInterfaceValidator;
    }

    @Override
    public GatewayDataValidator getGatewayDataValidator() {
        return gatewayDataValidator;
    }

    @Override
    public void setGatewayDataValidator(GatewayDataValidator gatewayDataValidator) {
        this.gatewayDataValidator = gatewayDataValidator;
    }

    @Override
    public GatewayResultValidator getGatewayResultValidator() {
        return gatewayResultValidator;
    }

    @Override
    public void setGatewayResultValidator(GatewayResultValidator gatewayResultValidator) {
        this.gatewayResultValidator = gatewayResultValidator;
    }

    public GatewayDataAutoFill getGatewayDataAutoFill() {
        return gatewayDataAutoFill;
    }

    public void setGatewayDataAutoFill(GatewayDataAutoFill gatewayDataAutoFill) {
        this.gatewayDataAutoFill = gatewayDataAutoFill;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public RestfulSessionRequestBuilder newRestfulRequestBuilder() {
        RestfulSessionRequestBuilder restfulSessionRequestBuilder = null;
        if (!ObjectUtils.isEmpty(applicationContext)) {
            try {
                restfulSessionRequestBuilder = applicationContext.getBean(RestfulSessionRequestBuilder.class);
            } catch (Exception ex) {
            }
        }
        if (ObjectUtils.isEmpty(applicationContext)) {
            restfulSessionRequestBuilder = new RestfulSessionRequestBuilder().setGatewayContext(this);
        }
        return restfulSessionRequestBuilder;
    }

    @Override
    public RestfulSessionResponseBuilder newRestfulResponseBuilder() {
        RestfulSessionResponseBuilder restfulSessionResponseBuilder = null;
        if (!ObjectUtils.isEmpty(applicationContext)) {
            try {
                restfulSessionResponseBuilder = applicationContext.getBean(RestfulSessionResponseBuilder.class);
            } catch (Exception ex) {
            }
        }
        if (ObjectUtils.isEmpty(restfulSessionResponseBuilder)) {
            restfulSessionResponseBuilder = new RestfulSessionResponseBuilder().setGatewayContext(this);
        }
        return restfulSessionResponseBuilder;
    }

}
