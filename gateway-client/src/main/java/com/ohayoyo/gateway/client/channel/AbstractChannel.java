package com.ohayoyo.gateway.client.channel;

import com.ohayoyo.gateway.client.core.GatewayClient;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.core.GatewayResponse;
import com.ohayoyo.gateway.client.restful.RestfulDefine;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.core.InterfaceDefine;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public abstract class AbstractChannel implements GatewayChannel {

    private GatewayContainer gatewayContainer;

    private GatewayClient gatewayClient;

    public AbstractChannel(GatewayContainer gatewayContainer, GatewayClient gatewayClient) {
        Assert.notNull(gatewayContainer);
        Assert.notNull(gatewayClient);
        this.gatewayContainer = gatewayContainer;
        this.gatewayClient = gatewayClient;
    }

    @Override
    public GatewayContainer getGatewayContainer() {
        return gatewayContainer;
    }

    @Override
    public AbstractChannel setGatewayContainer(GatewayContainer gatewayContainer) {
        this.gatewayContainer = gatewayContainer;
        return this;
    }

    @Override
    public GatewayClient getGatewayClient() {
        return gatewayClient;
    }

    @Override
    public AbstractChannel setGatewayClient(GatewayClient gatewayClient) {
        this.gatewayClient = gatewayClient;
        return this;
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws Exception {
        Assert.notNull(responseType);
        Assert.notNull(interfaceDefineKey);
        Result result = null;
        InterfaceDefine interfaceDefine = this.gatewayContainer.query(interfaceDefineKey);
        if (!ObjectUtils.isEmpty(interfaceDefine)) {
            Class<?> responseBodyClass = this.resolveResponseType(responseType, interfaceDefine);
            GatewayDefine gatewayDefine = this.resolveGatewayDefine(interfaceDefine);
            GatewayResponse<?> gatewayResponse = this.gatewayClient.session(responseBodyClass, gatewayDefine, gatewayRequest);
            result = this.resolveGatewayResult(responseType, interfaceDefine, gatewayResponse);
        }
        return result;
    }

    protected <ResponseBody, Result> Class<ResponseBody> resolveResponseType(Class<Result> responseType, InterfaceDefine interfaceDefine) {
        return (Class<ResponseBody>) responseType;
    }

    protected GatewayDefine resolveGatewayDefine(InterfaceDefine interfaceDefine) {
        return new RestfulDefine(interfaceDefine);
    }

    protected abstract <Result> Result resolveGatewayResult(Class<Result> responseType, InterfaceDefine interfaceDefine, GatewayResponse<?> gatewayResponse);

}
