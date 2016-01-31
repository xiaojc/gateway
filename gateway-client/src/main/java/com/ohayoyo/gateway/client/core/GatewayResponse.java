package com.ohayoyo.gateway.client.core;

import org.springframework.util.MultiValueMap;

public interface GatewayResponse<T> {

    String getStatusCode();

    String getReasonPhrase();

    MultiValueMap<String, String> getResponseHeaders();

    T getResponseEntity();

}
