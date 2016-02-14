package com.ohayoyo.gateway.channel;

import com.ohayoyo.gateway.client.GatewayClient;
import com.ohayoyo.gateway.client.GatewayDefine;
import com.ohayoyo.gateway.client.GatewayRequest;
import com.ohayoyo.gateway.client.GatewayResponse;
import com.ohayoyo.gateway.container.GatewayAction;
import com.ohayoyo.gateway.container.GatewayContainer;
import com.ohayoyo.gateway.container.GatewayQuery;
import com.ohayoyo.gateway.define.http.InterfaceDefine;

public abstract class AbstractGatewayChannel implements GatewayChannel {

    private GatewayContainer gatewayContainer;

    private GatewayClient gatewayClient;

    @Override
    public GatewayContainer getGatewayContainer() {
        return gatewayContainer;
    }

    @Override
    public AbstractGatewayChannel setGatewayContainer(GatewayContainer gatewayContainer) {
        this.gatewayContainer = gatewayContainer;
        return this;
    }

    @Override
    public GatewayClient getGatewayClient() {
        return gatewayClient;
    }

    @Override
    public AbstractGatewayChannel setGatewayClient(GatewayClient gatewayClient) {
        this.gatewayClient = gatewayClient;
        return this;
    }

    @Override
    public <Result> Result channel(Class<Result> resultClass, String key, GatewayWrapper gatewayWrapper) throws Exception {
        GatewayQuery gatewayQuery = gatewayContainer.createGatewayQuery();
        GatewayAction<String> gatewayAction = this.createGatewayAction(key, gatewayWrapper);
        InterfaceDefine interfaceDefine = gatewayQuery.query(gatewayAction);
        Class<?> responseBodyClass = this.resolveResponseBodyClass(resultClass, interfaceDefine);
        GatewayDefine gatewayDefine = this.resolveGatewayDefine(interfaceDefine);
        GatewayRequest<?> gatewayRequest = this.resolveGatewayRequest(gatewayAction, interfaceDefine);
        GatewayResponse<?> gatewayResponse = gatewayClient.session(responseBodyClass, gatewayDefine, gatewayRequest);
        Result result = this.resolveResult(interfaceDefine, gatewayResponse);
        return result;
    }

    protected abstract GatewayAction<String> createGatewayAction(String key, GatewayWrapper gatewayWrapper);

    protected abstract <ResponseBody, Result> Class<ResponseBody> resolveResponseBodyClass(Class<Result> resultClass, InterfaceDefine interfaceDefine);

    protected abstract GatewayDefine resolveGatewayDefine(InterfaceDefine interfaceDefine);

    protected abstract GatewayRequest<?> resolveGatewayRequest(GatewayAction<String> gatewayAction, InterfaceDefine interfaceDefine);

    protected abstract <Result> Result resolveResult(InterfaceDefine interfaceDefine, GatewayResponse<?> gatewayResponse);

}
