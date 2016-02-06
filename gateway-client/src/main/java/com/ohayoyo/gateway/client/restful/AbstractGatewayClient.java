package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.*;
import com.ohayoyo.gateway.http.DefaultHttpClientHandler;
import com.ohayoyo.gateway.http.HttpClientHandler;
import org.springframework.util.Assert;

public abstract class AbstractGatewayClient implements GatewayClient {

    private HttpClientHandler httpClientHandler = new DefaultHttpClientHandler();

    @Override
    public HttpClientHandler getHttpClientHandler() {
        return httpClientHandler;
    }

    @Override
    public AbstractGatewayClient setHttpClientHandler(HttpClientHandler httpClientHandler) {
        Assert.notNull(httpClientHandler);
        this.httpClientHandler = httpClientHandler;
        return this;
    }

    @Override
    public <RequestBody> GatewayResponse<String> session(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        return this.session(String.class, gatewayDefine, gatewayRequest);
    }

    @Override
    public <ResponseBody, RequestBody> GatewayResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        Assert.notNull(responseBodyClass);
        HttpClientHandler httpClientHandler = this.getHttpClientHandler();
        GatewayResponse<ResponseBody> gatewayResponse = new RestfulGatewayResponse<ResponseBody>();
        this.defineVerify(gatewayDefine);
        this.requestVerify(gatewayDefine, gatewayRequest);
        this.requestFill(gatewayDefine, gatewayRequest);
        this.doSession(httpClientHandler, gatewayResponse, responseBodyClass, gatewayDefine, gatewayRequest);
        this.resultVerify(gatewayResponse, responseBodyClass, gatewayDefine);
        return gatewayResponse;
    }

    protected abstract void defineVerify(GatewayDefine gatewayDefine) throws GatewayException;

    protected abstract <RequestBody> void requestVerify(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    protected abstract <RequestBody> void requestFill(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    protected abstract <ResponseBody, RequestBody> void doSession(HttpClientHandler httpClientHandler, GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    protected abstract <ResponseBody> void resultVerify(GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine) throws GatewayException;

}
