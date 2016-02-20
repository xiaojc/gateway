package com.ohayoyo.gateway.channel.core;

import com.ohayoyo.gateway.channel.exception.ChannelException;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.*;
import com.ohayoyo.gateway.session.exception.GatewaySessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
@SuppressWarnings("unchecked")
public abstract class AbstractGatewayChannel extends AbstractGatewayAccessor implements GatewayChannel {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractGatewayChannel.class);

    @Override
    public String channel(String key) throws ChannelException {
        return channel(key, null);
    }

    @Override
    public String channel(String interfaceDefineKey, GatewaySessionRequest<Object> gatewaySessionRequest) throws ChannelException {
        return channel(String.class, interfaceDefineKey, gatewaySessionRequest);
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String key) throws ChannelException {
        return channel(responseType, key, null);
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String interfaceDefineKey, GatewaySessionRequest<Object> gatewaySessionRequest) throws ChannelException {
        Assert.notNull(responseType);
        Assert.notNull(interfaceDefineKey);
        Result result = null;
        try {
            GatewayContext gatewayContext = this.getGatewayContext();
            GatewayContainer containerDefine = gatewayContext.getGatewayContainer();
            GatewaySession gatewayClient = gatewayContext.getGatewaySession();
            GatewayInterface GatewayInterface = containerDefine.query(interfaceDefineKey);
            if (!ObjectUtils.isEmpty(GatewayInterface)) {
                Class<?> responseBodyClass = this.resolveResponseType(responseType, GatewayInterface);
                GatewaySessionResponse<?> gatewaySessionResponse = gatewayClient.session(responseBodyClass, GatewayInterface, gatewaySessionRequest);
                result = this.resolveGatewayResult(responseType, GatewayInterface, gatewaySessionResponse);
            } else {
                ChannelException.exception("根据给定的接口定义键,没有找合适的接口定义,这个键是:%s", interfaceDefineKey);
            }
        } catch (GatewaySessionException ex) {
            ChannelException.exception("%s", ex.getMessage());
        }
        return result;
    }

    protected abstract <ResponseBody, Result> Class<ResponseBody> resolveResponseType(Class<Result> responseType, GatewayInterface gatewayInterface);

    protected abstract <Result> Result resolveGatewayResult(Class<Result> responseType, GatewayInterface GatewayInterface, GatewaySessionResponse<?> gatewaySessionResponse);

}
