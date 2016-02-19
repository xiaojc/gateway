package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.autofill.GatewayAutofill;
import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;
import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.client.validator.GatewayDataValidator;
import com.ohayoyo.gateway.client.validator.GatewayDefineValidator;
import com.ohayoyo.gateway.client.validator.GatewayResultValidator;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.core.HttpGatewayRequest;
import com.ohayoyo.gateway.http.core.HttpGatewayResponse;
import com.ohayoyo.gateway.http.interceptor.HttpGatewayRequestIntercepting;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public abstract class AbstractContext implements GatewayContext {

    private ConversionService conversionService;

    private HttpGatewayMessageConverters httpGatewayMessageConverters;

    private HttpGatewayRequest httpGatewayRequest;

    private HttpGatewayResponse httpGatewayResponse;

    private HttpGatewayRequestIntercepting httpGatewayRequestIntercepting;

    private ClientHttpRequestFactory clientHttpRequestFactory;

    private HttpGateway httpGateway;

    private GatewayContainer gatewayContainer;

    private GatewayClient gatewayClient;

    private GatewayDefineValidator gatewayDefineValidator;

    private GatewayDataValidator gatewayDataValidator;

    private GatewayResultValidator gatewayResultValidator;

    private GatewayAutofill gatewayAutofill;

    private Environment environment;

    private ApplicationContext applicationContext;

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    @Override
    public AbstractContext setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
        if (!ObjectUtils.isEmpty(this.httpGatewayMessageConverters)) {
            this.httpGatewayMessageConverters.setConversionService(this.conversionService);
        }
        return this;
    }

    @Override
    public HttpGatewayMessageConverters getHttpGatewayMessageConverters() {
        return httpGatewayMessageConverters;
    }

    @Override
    public AbstractContext setHttpGatewayMessageConverters(HttpGatewayMessageConverters httpGatewayMessageConverters) {
        this.httpGatewayMessageConverters = httpGatewayMessageConverters;
        if (!ObjectUtils.isEmpty(this.httpGateway)) {
            this.httpGateway.setHttpGatewayMessageConverters(this.httpGatewayMessageConverters);
        }
        if (!ObjectUtils.isEmpty(this.httpGatewayMessageConverters)) {
            this.httpGatewayMessageConverters.setConversionService(this.conversionService);
        }
        return this;
    }

    @Override
    public HttpGatewayRequest getHttpGatewayRequest() {
        return httpGatewayRequest;
    }

    @Override
    public AbstractContext setHttpGatewayRequest(HttpGatewayRequest httpGatewayRequest) {
        this.httpGatewayRequest = httpGatewayRequest;
        if (!ObjectUtils.isEmpty(this.httpGateway)) {
            this.httpGateway.setHttpGatewayRequest(this.httpGatewayRequest);
        }
        return this;
    }

    @Override
    public HttpGatewayResponse getHttpGatewayResponse() {
        return httpGatewayResponse;
    }

    @Override
    public AbstractContext setHttpGatewayResponse(HttpGatewayResponse httpGatewayResponse) {
        this.httpGatewayResponse = httpGatewayResponse;
        if (!ObjectUtils.isEmpty(this.httpGateway)) {
            this.httpGateway.setHttpGatewayResponse(this.httpGatewayResponse);
        }
        return this;
    }

    @Override
    public HttpGatewayRequestIntercepting getHttpGatewayRequestIntercepting() {
        return httpGatewayRequestIntercepting;
    }

    @Override
    public AbstractContext setHttpGatewayRequestIntercepting(HttpGatewayRequestIntercepting httpGatewayRequestIntercepting) {
        this.httpGatewayRequestIntercepting = httpGatewayRequestIntercepting;
        if (!ObjectUtils.isEmpty(this.httpGateway)) {
            this.httpGateway.setHttpGatewayRequestIntercepting(this.httpGatewayRequestIntercepting);
        }
        return this;
    }

    @Override
    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        return clientHttpRequestFactory;
    }

    @Override
    public AbstractContext setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory) {
        this.clientHttpRequestFactory = clientHttpRequestFactory;
        if (!ObjectUtils.isEmpty(this.httpGateway)) {
            this.httpGateway.setClientHttpRequestFactory(this.clientHttpRequestFactory);
        }
        return this;
    }

    @Override
    public HttpGateway getHttpGateway() {
        return httpGateway;
    }

    @Override
    public AbstractContext setHttpGateway(HttpGateway httpGateway) {
        this.httpGateway = httpGateway;
        if (!ObjectUtils.isEmpty(this.httpGateway)) {
            this.httpGateway.setHttpGatewayRequest(this.httpGatewayRequest);
            this.httpGateway.setHttpGatewayResponse(this.httpGatewayResponse);
            this.httpGateway.setHttpGatewayMessageConverters(this.httpGatewayMessageConverters);
            this.httpGateway.setHttpGatewayRequestIntercepting(this.httpGatewayRequestIntercepting);
            this.httpGateway.setClientHttpRequestFactory(this.clientHttpRequestFactory);
        }
        return this;
    }

    @Override
    public GatewayContainer getGatewayContainer() {
        return gatewayContainer;
    }

    @Override
    public AbstractContext setGatewayContainer(GatewayContainer gatewayContainer) {
        this.gatewayContainer = gatewayContainer;
        return this;
    }

    @Override
    public GatewayClient getGatewayClient() {
        return gatewayClient;
    }

    @Override
    public AbstractContext setGatewayClient(GatewayClient gatewayClient) {
        this.gatewayClient = gatewayClient;
        return this;
    }

    @Override
    public GatewayDefineValidator getGatewayDefineValidator() {
        return gatewayDefineValidator;
    }

    @Override
    public AbstractContext setGatewayDefineValidator(GatewayDefineValidator gatewayDefineValidator) {
        this.gatewayDefineValidator = gatewayDefineValidator;
        return this;
    }

    @Override
    public GatewayDataValidator getGatewayDataValidator() {
        return gatewayDataValidator;
    }

    @Override
    public AbstractContext setGatewayDataValidator(GatewayDataValidator gatewayDataValidator) {
        this.gatewayDataValidator = gatewayDataValidator;
        return this;
    }

    @Override
    public GatewayResultValidator getGatewayResultValidator() {
        return gatewayResultValidator;
    }

    @Override
    public AbstractContext setGatewayResultValidator(GatewayResultValidator gatewayResultValidator) {
        this.gatewayResultValidator = gatewayResultValidator;
        return this;
    }

    @Override
    public GatewayAutofill getGatewayAutofill() {
        return gatewayAutofill;
    }

    @Override
    public AbstractContext setGatewayAutofill(GatewayAutofill gatewayAutofill) {
        this.gatewayAutofill = gatewayAutofill;
        return this;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public AbstractContext setEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public AbstractContext setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        return this;
    }

    @Override
    public RestfulRequestBuilder newRestfulRequestBuilder() {
        RestfulRequestBuilder restfulRequestBuilder = null;
        if (!ObjectUtils.isEmpty(applicationContext)) {
            try {
                restfulRequestBuilder = applicationContext.getBean(RestfulRequestBuilder.class);
            } catch (Exception ex) {
            }
        }
        if (ObjectUtils.isEmpty(applicationContext)) {
            restfulRequestBuilder = new RestfulRequestBuilder().setGatewayContext(this);
        }
        return restfulRequestBuilder;
    }

    @Override
    public RestfulResponseBuilder newRestfulResponseBuilder() {
        RestfulResponseBuilder restfulResponseBuilder = null;
        if (!ObjectUtils.isEmpty(applicationContext)) {
            try {
                restfulResponseBuilder = applicationContext.getBean(RestfulResponseBuilder.class);
            } catch (Exception ex) {
            }
        }
        if (ObjectUtils.isEmpty(restfulResponseBuilder)) {
            restfulResponseBuilder = new RestfulResponseBuilder().setGatewayContext(this);
        }
        return restfulResponseBuilder;
    }

}
