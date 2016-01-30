package com.ohayoyo.gateway.client.core;

import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;
import java.util.Set;

public interface GatewayConfig {

    List<HttpMessageConverter<?>> getHttpMessageConverters();

    Set<Class<? extends GatewayPart<?>>> getGatewayPartClasses();

}
