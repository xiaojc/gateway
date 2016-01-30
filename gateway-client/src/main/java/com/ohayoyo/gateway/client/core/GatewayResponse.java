package com.ohayoyo.gateway.client.core;

import java.util.Map;

public interface GatewayResponse {

    String getStatusCode();

    String getReasonPhrase();

    Map<String, Object> getResponseHeaders();

    Object getResponseEntity();

}
