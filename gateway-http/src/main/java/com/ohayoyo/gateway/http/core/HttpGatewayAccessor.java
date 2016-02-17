package com.ohayoyo.gateway.http.core;

import org.springframework.http.client.ClientHttpRequestFactory;

/**
 * @author 蓝明乐
 */
public interface HttpGatewayAccessor {

    ClientHttpRequestFactory getClientHttpRequestFactory();

    HttpGatewayAccessor setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory);

    //AsyncClientHttpRequestFactory getAsyncClientHttpRequestFactory();

    //HttpGatewayAccessor setAsyncClientHttpRequestFactory(AsyncClientHttpRequestFactory asyncClientHttpRequestFactory);

}
