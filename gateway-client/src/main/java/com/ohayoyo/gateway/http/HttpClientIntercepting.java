package com.ohayoyo.gateway.http;

import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.util.List;

public interface HttpClientIntercepting {

    List<ClientHttpRequestInterceptor> getClientHttpRequestInterceptors();

    HttpClientIntercepting setClientHttpRequestInterceptors(List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors);

}
