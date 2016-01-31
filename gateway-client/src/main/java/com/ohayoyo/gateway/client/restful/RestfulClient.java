package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.components.*;
import com.ohayoyo.gateway.client.core.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.net.URI;

public class RestfulClient implements GatewayClient {

    private GatewayConfig gatewayConfig;

    private GatewayExecutor gatewayExecutor;

    public RestfulClient() {
        this(null, null);
    }

    public RestfulClient(GatewayConfig gatewayConfig) {
        this(gatewayConfig, null);
    }

    public RestfulClient(GatewayExecutor gatewayExecutor) {
        this(null, gatewayExecutor);
    }

    public RestfulClient(GatewayConfig gatewayConfig, GatewayExecutor gatewayExecutor) {
        this.gatewayConfig = gatewayConfig;
        this.gatewayExecutor = gatewayExecutor;
        if (null == this.gatewayConfig) {
            this.gatewayConfig = new RestfulConfig();
        }
        if (null == this.gatewayExecutor) {
            this.gatewayExecutor = new RestfulExecutor();
        }
    }

    @Override
    public GatewayResponse<Object> session(GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        return session(Object.class, gatewayDefine, gatewayRequest);
    }

    @Override
    public <T> GatewayResponse<T> session(Class<T> responseType, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        GatewayResponse<T> gatewayResponse = null;
        gatewayExecutor.open();
        try {
            GatewayConfig gatewayConfig = this.getGatewayConfig();
            URI uri = createUri(gatewayConfig, gatewayDefine, gatewayRequest);
            HttpMethod httpMethod = createHttpMethod(gatewayConfig, gatewayDefine, gatewayRequest);
            GatewayRequestCallback requestCallback = createRequestCallback(gatewayConfig, gatewayDefine, gatewayRequest);
            GatewayResponseExtractor<T> responseExtractor = createResponseExtractor(responseType, gatewayConfig, gatewayDefine, gatewayRequest);
            T responseResult = gatewayExecutor.execute(uri, httpMethod, requestCallback, responseExtractor);
            GatewayWrapper<T> gatewayWrapper = createGatewayWrapper(responseType, responseResult, gatewayConfig, gatewayDefine, gatewayRequest, requestCallback.getClientHttpRequest(), responseExtractor.getClientHttpResponse());
            gatewayResponse = gatewayWrapper.wrapGatewayResponse();
        } catch (Exception ex) {
            throw new GatewayException(ex.getMessage());
        } finally {
            gatewayExecutor.close();
        }
        return gatewayResponse;
    }

    protected <Component> GatewayComponent<Component> compileGatewayComponent(GatewayComponent<Component> component, GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) {
        return component.setGatewayConfig(gatewayConfig).setGatewayDefine(gatewayDefine).setGatewayRequest(gatewayRequest);
    }

    protected URI createUri(GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        return compileGatewayComponent(new GatewayUri(), gatewayConfig, gatewayDefine, gatewayRequest).getComponent();
    }

    protected HttpMethod createHttpMethod(GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        return compileGatewayComponent(new GatewayHttpMethod(), gatewayConfig, gatewayDefine, gatewayRequest).getComponent();
    }

    protected GatewayRequestCallback createRequestCallback(GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        return (GatewayRequestCallback) compileGatewayComponent(new GatewayRequestCallback(), gatewayConfig, gatewayDefine, gatewayRequest).getComponent();
    }

    protected <T> GatewayResponseExtractor<T> createResponseExtractor(Class<T> responseType, GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        return (GatewayResponseExtractor<T>) compileGatewayComponent(new GatewayResponseExtractor<T>(responseType), gatewayConfig, gatewayDefine, gatewayRequest).getComponent();
    }

    private <T> GatewayWrapper<T> createGatewayWrapper(Class<T> responseType, T responseResult, GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest, ClientHttpRequest clientHttpRequest, ClientHttpResponse clientHttpResponse) throws GatewayException {
        return compileGatewayComponent(new GatewayWrapperEntity<T>(responseType, responseResult, clientHttpRequest, clientHttpResponse), gatewayConfig, gatewayDefine, gatewayRequest).getComponent();
    }

    @Override
    public GatewayConfig getGatewayConfig() {
        return gatewayConfig;
    }

    @Override
    public RestfulClient setGatewayConfig(GatewayConfig gatewayConfig) {
        this.gatewayConfig = gatewayConfig;
        return this;
    }

    @Override
    public GatewayExecutor getGatewayExecutor() {
        return gatewayExecutor;
    }

    @Override
    public RestfulClient setGatewayExecutor(GatewayExecutor gatewayExecutor) {
        this.gatewayExecutor = gatewayExecutor;
        return this;
    }

}
