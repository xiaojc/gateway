package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.autofill.GatewayAutofill;
import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;
import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.client.validator.GatewayDataValidator;
import com.ohayoyo.gateway.client.validator.GatewayDefineValidator;
import com.ohayoyo.gateway.client.validator.GatewayResultValidator;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.core.HttpGatewayRequest;
import com.ohayoyo.gateway.http.core.HttpGatewayResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;

/**
 * @author 蓝明乐
 */
public interface GatewayContext {

    ConversionService getConversionService();

    GatewayContext setConversionService(ConversionService conversionService);

    List<HttpMessageConverter<?>> getHttpMessageConverters();

    GatewayContext setHttpMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters);

    HttpGatewayRequest getHttpGatewayRequest();

    GatewayContext setHttpGatewayRequest(HttpGatewayRequest httpGatewayRequest);

    HttpGatewayResponse getHttpGatewayResponse();

    GatewayContext setHttpGatewayResponse(HttpGatewayResponse httpGatewayResponse);

    ClientHttpRequestFactory getClientHttpRequestFactory();

    GatewayContext setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory);

    List<ClientHttpRequestInterceptor> getClientHttpRequestInterceptors();

    GatewayContext setClientHttpRequestInterceptors(List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors);

    HttpGateway getHttpGateway();

    GatewayContext setHttpGateway(HttpGateway httpGateway);

    GatewayContainer getGatewayContainer();

    GatewayContext setGatewayContainer(GatewayContainer gatewayContainer);

    GatewayClient getGatewayClient();

    GatewayContext setGatewayClient(GatewayClient gatewayClient);

    GatewayDefineValidator getGatewayDefineValidator();

    GatewayContext setGatewayDefineValidator(GatewayDefineValidator gatewayDefineValidator);

    GatewayDataValidator getGatewayDataValidator();

    GatewayContext setGatewayDataValidator(GatewayDataValidator gatewayDataValidator);

    GatewayResultValidator getGatewayResultValidator();

    GatewayContext setGatewayResultValidator(GatewayResultValidator gatewayResultValidator);

    GatewayAutofill getGatewayAutofill();

    GatewayContext setGatewayAutofill(GatewayAutofill gatewayAutofill);

    Environment getEnvironment();

    GatewayContext setEnvironment(Environment environment);

    ApplicationContext getApplicationContext();

    GatewayContext setApplicationContext(ApplicationContext applicationContext);

    RestfulRequestBuilder newRestfulRequestBuilder();

    RestfulResponseBuilder newRestfulResponseBuilder();

}
