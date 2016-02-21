package com.ohayoyo.gateway.channel.core;

import com.ohayoyo.gateway.channel.exception.GatewayChannelException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGatewayChannel.class);

    @Override
    public String channel(String key) throws GatewayChannelException {
        return channel(key, null);
    }

    @Override
    public String channel(String key, SessionRequest<Object> sessionRequest) throws GatewayChannelException {
        return channel(String.class, key, sessionRequest);
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String key) throws GatewayChannelException {
        return channel(responseType, key, null);
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String key, SessionRequest<Object> sessionRequest) throws GatewayChannelException {
        Assert.notNull(responseType);
        Assert.notNull(key);
        Result result = null;
        try {
            GatewayContext gatewayContext = this.getGatewayContext();
            GatewayContainer gatewayContainer = gatewayContext.getGatewayContainer();
            GatewaySession gatewaySession = gatewayContext.getGatewaySession();
            GatewayInterface GatewayInterface = gatewayContainer.query(key);
            if (!ObjectUtils.isEmpty(GatewayInterface)) {
                Class<?> responseBodyClass = this.resolveResponseType(responseType, GatewayInterface);
                SessionResponse<?> sessionResponse = gatewaySession.session(responseBodyClass, GatewayInterface, sessionRequest);
                result = this.resolveGatewayResult(responseType, GatewayInterface, sessionResponse);
            } else {
                GatewayChannelException.exception("根据给定的接口定义键,没有找合适的接口定义,这个键是:%s", key);
            }
        } catch (GatewaySessionException ex) {
            GatewayChannelException.exception("%s", ex.getMessage());
        }
        return result;
    }


    protected <ResponseBody, Result> Class<ResponseBody> resolveResponseType(Class<Result> responseType, GatewayInterface gatewayInterface) {
        return (Class<ResponseBody>) responseType;
    }

    protected <Result> Result resolveGatewayResult(Class<Result> responseType, GatewayInterface GatewayInterface, SessionResponse<?> sessionResponse) {
        Object responseBody = sessionResponse.getResponseBody();
        if ((!ObjectUtils.isEmpty(responseBody)) && responseBody.getClass().isAssignableFrom(responseType)) {
            return (Result) responseBody;
        }
        return null;
    }

}
