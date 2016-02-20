package com.ohayoyo.gateway.http.client;

import org.springframework.http.client.ClientHttpRequestFactory;

/**
 * @author 蓝明乐
 */
public interface GatewayHttpAccessor {

    ClientHttpRequestFactory getClientHttpRequestFactory();

    void setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory);

    //AsyncClientHttpRequestFactory getAsyncClientHttpRequestFactory();

    //void setAsyncClientHttpRequestFactory(AsyncClientHttpRequestFactory asyncClientHttpRequestFactory);

}
