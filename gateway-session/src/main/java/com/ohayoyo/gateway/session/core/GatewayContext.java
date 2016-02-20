package com.ohayoyo.gateway.session.core;

import com.ohayoyo.gateway.session.autofill.GatewayDataAutoFill;
import com.ohayoyo.gateway.session.builder.RestfulRequestBuilder;
import com.ohayoyo.gateway.session.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.session.validator.GatewayDataValidator;
import com.ohayoyo.gateway.session.validator.GatewayInterfaceValidator;
import com.ohayoyo.gateway.session.validator.GatewayResultValidator;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.http.client.GatewayHttpClient;
import com.ohayoyo.gateway.http.client.GatewayHttpRequest;
import com.ohayoyo.gateway.http.client.GatewayHttpResponse;
import com.ohayoyo.gateway.http.converter.GatewayHttpMessageConverters;
import com.ohayoyo.gateway.http.interceptor.GatewayHttpRequestInterceptors;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;

/**
 * @author 蓝明乐
 */
public interface GatewayContext {

    ConversionService getConversionService();

    void setConversionService(ConversionService conversionService);

    GatewayHttpMessageConverters getGatewayHttpMessageConverters();

    void setGatewayHttpMessageConverters(GatewayHttpMessageConverters gatewayHttpMessageConverters);

    GatewayHttpRequest getGatewayHttpRequest();

    void setGatewayHttpRequest(GatewayHttpRequest gatewayHttpRequest);

    GatewayHttpResponse getGatewayHttpResponse();

    void setGatewayHttpResponse(GatewayHttpResponse gatewayHttpResponse);

    ClientHttpRequestFactory getClientHttpRequestFactory();

    void setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory);

    GatewayHttpRequestInterceptors getGatewayHttpRequestInterceptors();

    void setGatewayHttpRequestInterceptors(GatewayHttpRequestInterceptors gatewayHttpRequestInterceptors);

    GatewayHttpClient getGatewayHttpClient();

    void setGatewayHttpClient(GatewayHttpClient gatewayHttpClient);

    GatewayContainer getGatewayContainer();

    void setGatewayContainer(GatewayContainer gatewayContainer);

    GatewaySession getGatewaySession();

    void setGatewaySession(GatewaySession gatewaySession);

    GatewayInterfaceValidator getGatewayInterfaceValidator();

    void setGatewayInterfaceValidator(GatewayInterfaceValidator gatewayInterfaceValidator);

    GatewayDataValidator getGatewayDataValidator();

    void setGatewayDataValidator(GatewayDataValidator gatewayDataValidator);

    GatewayResultValidator getGatewayResultValidator();

    void setGatewayResultValidator(GatewayResultValidator gatewayResultValidator);

    GatewayDataAutoFill getGatewayDataAutoFill();

    void setGatewayDataAutoFill(GatewayDataAutoFill gatewayDataAutoFill);

    Environment getEnvironment();

    void setEnvironment(Environment environment);

    ApplicationContext getApplicationContext();

    void setApplicationContext(ApplicationContext applicationContext);

    RestfulRequestBuilder newRestfulRequestBuilder();

    RestfulResponseBuilder newRestfulResponseBuilder();

}
