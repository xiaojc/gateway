package com.ohayoyo.gateway.channel;

import com.ohayoyo.gateway.client.GatewayClient;
import com.ohayoyo.gateway.client.GatewayDefine;
import com.ohayoyo.gateway.client.GatewayRequest;
import com.ohayoyo.gateway.client.GatewayResponse;
import com.ohayoyo.gateway.client.restful.RestfulGatewayClient;
import com.ohayoyo.gateway.client.restful.RestfulGatewayDefine;
import com.ohayoyo.gateway.client.restful.RestfulGatewayRequestBuilder;
import com.ohayoyo.gateway.container.GatewayContainer;
import com.ohayoyo.gateway.define.http.InterfaceDefine;
import org.springframework.util.Assert;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewayChannel implements GatewayChannel {

    private GatewayContainer gatewayContainer;

    private GatewayClient gatewayClient;

    public AbstractGatewayChannel(GatewayContainer gatewayContainer) {
        this(gatewayContainer, RestfulGatewayClient.getDefaultGatewayClient());
    }

    public AbstractGatewayChannel(GatewayContainer gatewayContainer, GatewayClient gatewayClient) {
        this.gatewayContainer = gatewayContainer;
        this.gatewayClient = gatewayClient;
    }

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

    public RestfulGatewayRequestBuilder newRestfulGatewayRequestBuilder() {
        if (this.gatewayClient instanceof RestfulGatewayClient) {
            return ((RestfulGatewayClient) this.gatewayClient).newRestfulGatewayRequestBuilder();
        }
        return null;
    }


    @Override
    public <Result> Result channel(Class<Result> gatewayResultClass, String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws Exception {
        Assert.notNull(gatewayResultClass);
        Assert.notNull(interfaceDefineKey);
        Result result = null;
        InterfaceDefine interfaceDefine = this.gatewayContainer.query(interfaceDefineKey);
        if (null != interfaceDefine) {
            Class<?> responseBodyClass = this.resolveResponseBodyClass(gatewayResultClass, interfaceDefine);
            GatewayDefine gatewayDefine = this.resolveGatewayDefine(interfaceDefine);
            GatewayResponse<?> gatewayResponse = this.gatewayClient.session(responseBodyClass, gatewayDefine, gatewayRequest);
            result = this.resolveResult(gatewayResultClass, interfaceDefine, gatewayResponse);
        }
        return result;
    }

    protected <ResponseBody, Result> Class<ResponseBody> resolveResponseBodyClass(Class<Result> gatewayResultClass, InterfaceDefine interfaceDefine) {
        return (Class<ResponseBody>) gatewayResultClass;
    }

    protected GatewayDefine resolveGatewayDefine(InterfaceDefine interfaceDefine) {
        return new RestfulGatewayDefine(interfaceDefine);
    }

    protected abstract <Result> Result resolveResult(Class<Result> gatewayResultClass, InterfaceDefine interfaceDefine, GatewayResponse<?> gatewayResponse);

}
