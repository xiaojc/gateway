package com.ohayoyo.gateway.http.interceptor;

import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.util.List;

/**
 * @author 蓝明乐
 */
public interface GatewayHttpRequestInterceptors extends List<ClientHttpRequestInterceptor> {
}
