package com.ohayoyo.gateway.http.core;

import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.util.List;

/**
 * @author 蓝明乐
 */
public interface HttpGatewayRequestIntercepting {

    List<ClientHttpRequestInterceptor> getClientHttpRequestInterceptors();

    HttpGatewayRequestIntercepting setClientHttpRequestInterceptors(List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors);

}
