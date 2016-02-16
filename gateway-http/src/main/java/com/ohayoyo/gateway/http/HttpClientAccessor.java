package com.ohayoyo.gateway.http;

import org.springframework.http.client.ClientHttpRequestFactory;

/**
 * @author 蓝明乐
 */
public interface HttpClientAccessor {

    ClientHttpRequestFactory getClientHttpRequestFactory();

    HttpClientAccessor setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory);

    //AsyncClientHttpRequestFactory getAsyncClientHttpRequestFactory();

    //HttpClientAccessor setAsyncClientHttpRequestFactory(AsyncClientHttpRequestFactory asyncClientHttpRequestFactory);

}
