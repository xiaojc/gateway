package com.ohayoyo.gateway.session;

import com.ohayoyo.gateway.define.GatewayContainer;
import com.ohayoyo.gateway.http.*;
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

    RestfulSessionRequestBuilder newRestfulRequestBuilder();

    RestfulSessionResponseBuilder newRestfulResponseBuilder();

}
