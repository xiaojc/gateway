package com.ohayoyo.gateway.client.core;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import java.net.URI;

public interface GatewayExecutor {

    void enableBuffering();

    void disableBuffering();

    void open() throws GatewayException;

    <T> T execute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws GatewayException;

    void close() throws GatewayException;

}
