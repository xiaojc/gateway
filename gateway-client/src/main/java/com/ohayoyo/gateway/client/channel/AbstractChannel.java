package com.ohayoyo.gateway.client.channel;

import com.ohayoyo.gateway.client.core.*;
import com.ohayoyo.gateway.client.exception.ChannelException;
import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.restful.RestfulDefine;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.http.InterfaceDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public abstract class AbstractChannel implements GatewayChannel {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractChannel.class);

    private GatewayContext gatewayContext;

    @Override
    public String channel(String interfaceDefineKey) throws GatewayException {
        return channel(interfaceDefineKey, null);
    }

    @Override
    public String channel(String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws GatewayException {
        return channel(String.class, interfaceDefineKey, gatewayRequest);
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String interfaceDefineKey) throws GatewayException {
        return channel(responseType, interfaceDefineKey, null);
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws GatewayException {
        Assert.notNull(responseType);
        Assert.notNull(interfaceDefineKey);
        Result result = null;
        GatewayContainer gatewayContainer = gatewayContext.getGatewayContainer();
        GatewayClient gatewayClient = gatewayContext.getGatewayClient();
        InterfaceDefine interfaceDefine = gatewayContainer.query(interfaceDefineKey);
        if (!ObjectUtils.isEmpty(interfaceDefine)) {
            Class<?> responseBodyClass = this.resolveResponseType(responseType, interfaceDefine);
            GatewayDefine gatewayDefine = this.resolveGatewayDefine(interfaceDefine);
            GatewayResponse<?> gatewayResponse = gatewayClient.session(responseBodyClass, gatewayDefine, gatewayRequest);
            result = this.resolveGatewayResult(responseType, interfaceDefine, gatewayResponse);
        } else {
            ChannelException.exception("根据给定的接口定义键,没有找合适的接口定义,这个键是:%s", interfaceDefineKey);
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

    @Override
    public GatewayContext getGatewayContext() {
        return gatewayContext;
    }

    @Override
    public AbstractChannel setGatewayContext(GatewayContext gatewayContext) {
        this.gatewayContext = gatewayContext;
        return this;
    }

}
